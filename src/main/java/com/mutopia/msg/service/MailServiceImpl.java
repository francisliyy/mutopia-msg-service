/**
 * <p>Title: MailServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.service;

import java.io.FileOutputStream;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutopia.msg.constants.Constants;
import com.mutopia.msg.exceptions.MailGenerateException;
import com.mutopia.msg.mail.MailClient;
import com.mutopia.msg.model.MailSendLog;
import com.mutopia.msg.repository.MailRepository;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailRepository mailRepository;
	@Autowired
	private MailClient mailClient;

	/* (non-Javadoc)
	 * @see com.mutopia.msg.service.MailService#sendImmediate(com.mutopia.msg.model.MailSendLog)
	 */
	@Override
	public boolean sendImmediate(MailSendLog mail) {
		
		int result = 0;

		result = sendMail(mail);

		return result == Constants.MSG_SEND_SUCCESS ? true : false;
	}
	
	private int sendMail(MailSendLog mail) {

		int result = 0;

		MailSendLog mailSendLog = new MailSendLog();
		BeanUtils.copyProperties(mail, mailSendLog);
		// mailSendLog.setMailId(this.systemMapper.getSequenceNextValue("seq_msg_mail_log"));
		mailSendLog.setMailTime(new Date());
		//mailSendLog.setIsImmediate(isMailImmediate);// 是否立即发送

		MimeMessage message = null;
		try {
			checkMailSource(mail);
			message = this.mailClient.getMail(mail);
			message.writeTo(new FileOutputStream("MutopiaMail[" + mailSendLog.getMailId() + "].eml"));
		} catch (Exception e) {
			e.printStackTrace();
			mailSendLog.setMailResult(Constants.MSG_SEND_FAIL+"");
			mailSendLog.setMailFailDesc(e.getLocalizedMessage());
			mailRepository.save(mailSendLog);
		}
		try {
			Transport.send(message);
			mailSendLog.setMailResult(Constants.MSG_SEND_SUCCESS+"");
			mailRepository.save(mailSendLog);
			result = 1;
		} catch (MessagingException e) {
			e.printStackTrace();
			mailSendLog.setMailResult(Constants.MSG_SEND_FAIL+"");
			mailSendLog.setMailFailDesc(e.getLocalizedMessage());
			mailRepository.save(mailSendLog);
		}
		return result;
	}
	
	private void checkMailSource(MailSendLog mail) throws MailGenerateException{

		if (mail.getMailSourceCtlUrl() == null || "".equals(mail.getMailSourceCtlUrl())) {
			throw new MailGenerateException("邮件发送系统功能不允许为空！");
		}
		
		if (mail.getMailSourceSystem() == null || "".equals(mail.getMailSourceSystem())) {
			throw new MailGenerateException("邮件发送源系统不允许为空！");
		}

	}

}
