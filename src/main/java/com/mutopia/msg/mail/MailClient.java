/**
 * <p>Title: CtosMailClient</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.mail;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import com.mutopia.msg.exceptions.MailGenerateException;
import com.mutopia.msg.model.MailSendLog;

@Service
public class MailClient {
	
	private Properties props = System.getProperties(); // 获取配置文件信息
    private String mailfrom = "";
    private Session mailsession = null;
    private Map<Long, MimeMessage> mailResultMap = null;

    public MailClient() {
        this.props.put("mail.smtp.host", MailConfigProps.getMail_smtp_host());
        this.props.put("mail.smtp.port", MailConfigProps.getMail_smtp_port());
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.debug", "true");
        this.mailfrom = MailConfigProps.getMail_from();
        this.mailsession = Session.getInstance(this.props, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MailConfigProps.getMail_from(),
                		MailConfigProps.getMail_password());
            }
        });
        
    }

    public MimeMessage getMail(MailSendLog mail) throws MailGenerateException {

        MimeMessage msg = new MimeMessage(mailsession);
        MimeMultipart allPart = new MimeMultipart("mixed");//
        MimeBodyPart mailcontent = null;
        
        try {
			msg.setFrom(new InternetAddress(this.mailfrom));
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
            msg.setRecipients(Message.RecipientType.TO, getAddrList(mail.getMailReceiver()));//
        } catch (AddressException e) {
            throw new MailGenerateException("set mail receiver error!" + e.getLocalizedMessage());
        } catch (MessagingException e) {
            throw new MailGenerateException("set mail receiver error!" + e.getLocalizedMessage());
        }
        try {
            if (mail.getMailCc() != null && !"".equals(mail.getMailCc()))
                msg.setRecipients(Message.RecipientType.CC, getAddrList(mail.getMailCc()));
        } catch (AddressException e) {
            throw new MailGenerateException("set mail cc error!" + e.getLocalizedMessage());
        } catch (MessagingException e) {
            throw new MailGenerateException("set mail cc error!" + e.getLocalizedMessage());
        }
        try {
            msg.setSubject(mail.getMailSubject());
        } catch (MessagingException e) {
            throw new MailGenerateException("set mail subject error!" + e.getLocalizedMessage());
        }
        try {
            mailcontent = createContent(mail.getMailContent());
        } catch (Exception e) {
            throw new MailGenerateException("get mail content error!" + e.getLocalizedMessage());
        }

        try {
            allPart.addBodyPart(mailcontent);
        } catch (MessagingException e) {
            throw new MailGenerateException("set mail content  error!" + e.getLocalizedMessage());
        }
        if (mail.getMailAttachment() != null && !"".equals(mail.getMailAttachment())) {

            String[] attachments = mail.getMailAttachment().split(";");
            for (String att : attachments) {
                MimeBodyPart attachment = createAttachment(att);
                try {
                    allPart.addBodyPart(attachment);
                } catch (MessagingException e) {
                    throw new MailGenerateException(
                                    "set mail attachment [" + attachment + "] error!" + e.getLocalizedMessage());
                }
            }
        }

        try {
            msg.setContent(allPart);
            msg.saveChanges();
        } catch (MessagingException e) {
            throw new MailGenerateException("save mail error!" + e.getLocalizedMessage());
        }
        return msg;

    }

    /*public Map<Long, MimeMessage> getMailResultMap(List<MailSendSchedule> mailList) throws MailGenerateException {

        if (mailList == null)
            return null;

        mailResultMap = new HashMap<Long, MimeMessage>();
        for (MailSendSchedule mail : mailList) {
            MimeMessage mimeMessage = getMail(mail);
            mailResultMap.put(mail.getMailId(), mimeMessage);
        }
        return mailResultMap;

    }*/


    public MimeBodyPart createAttachment(String fileName) {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = null;
        try {
            fds = new FileDataSource(new String(fileName.getBytes(), "gbk"));
            attachmentPart.setDataHandler(new DataHandler(fds));
            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            // attachmentPart.setFileName("=?GBK?B?"+enc.encode(fds.getName().getBytes())+"?=");
            attachmentPart.setFileName(MimeUtility.encodeText(fds.getName()));
            // System.out.println("attachmentPart.getFileName():"+attachmentPart.getFileName());


        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return attachmentPart;
    }


    // public MimeBodyPart createContent(String body, String fileName)
    public MimeBodyPart createContent(String body) throws Exception {

        MimeBodyPart contentBody = new MimeBodyPart();
        MimeMultipart contentMulti = new MimeMultipart("related");
        MimeBodyPart textBody = new MimeBodyPart();
        textBody.setContent(body, "text/html;charset=gbk");
        contentMulti.addBodyPart(textBody);
        contentBody.setContent(contentMulti);
        return contentBody;
    }


    public void setSocksProxy(boolean set, String host, String port) {
        System.out.println("设置代理 " + set);
        if (set) {
            props.setProperty("proxySet", "true");
            props.setProperty("socksProxyHost", host);
            props.setProperty("socksProxyPort", port);
        }
    }

    private InternetAddress[] getAddrList(String addrs) throws AddressException {
        InternetAddress[] resultAddr = null;
        String[] addrarray = addrs.split(";");
        resultAddr = new InternetAddress[addrarray.length];
        for (int i = 0; i < addrarray.length; i++) {
            resultAddr[i] = new InternetAddress(addrarray[i]);
        }
        return resultAddr;
    }

}
