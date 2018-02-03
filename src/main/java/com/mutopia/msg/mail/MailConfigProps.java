/**
 * <p>Title: ConfigProps</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.mail;

import java.io.InputStream;
import java.util.Properties;

public class MailConfigProps {
	
	public static Properties mailprops;
	
	public synchronized static Properties getMailProperties() {
		if (mailprops == null) {
			InputStream is = MailConfigProps.class.getResourceAsStream("/mail.properties");
			mailprops = new Properties();
			try {
				mailprops.load(is);
			} catch (Exception e) {
				System.out.println("获取配置文件mail.properties异常！");
				e.printStackTrace();
			}
		}
		return mailprops;
	}
	
	public static String getMail_from() {
		return getMailProperties().getProperty("mail.from");
	}
	
	public static String getMail_password() {
		return getMailProperties().getProperty("mail.password");
	}

	public static String getMail_smtp_host() {
		return getMailProperties().getProperty("mail.smtp.host");
	}
	
	public static String getMail_smtp_port() {
		return getMailProperties().getProperty("mail.smtp.port");
	}

	public static String getMail_username() {
		return getMailProperties().getProperty("mail.username");
	}

	public static String getMail_Time_start() {
		return getMailProperties().getProperty("time.start");
	}

	public static String getMail_Time_end() {
		return getMailProperties().getProperty("time.end");
	}
	
}
