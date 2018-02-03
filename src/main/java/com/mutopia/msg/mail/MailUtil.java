/**
 * <p>Title: MailUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.mail;

import com.mutopia.msg.utils.Md5Encrypt;

public class MailUtil {
	
	public static void sendRegisterMail(String email){
		
		String verifyCode = Md5Encrypt.encodeByMD5("email"+email);
		///邮件的内容  
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，链接只能使用一次，请尽快激活！</br>");  
        sb.append("<a href=\"http://localhost:8080/springmvc/user/register?action=activate&email=");  
        sb.append(email);   
        sb.append("&validateCode=");   
        sb.append(verifyCode);  
        sb.append("\">激活秒天平台账号"); 
        sb.append("</a>");  
          
        //发送邮件  
        /*MailSendLog mail = new MailSendLog();
		BeanUtils.copyProperties(mail, mailReqVo);
		return mailService.sendImmediate(mail); 
        System.out.println("发送邮件"); */ 
	}
	
}
