/**
 * <p>Title: ConfigProps</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.sms;

import java.io.InputStream;
import java.util.Properties;

public class SmsConfigProps {
	
	public static Properties smsprops;
	
	public synchronized static Properties getSmsProperties() {
		if (smsprops == null) {
			InputStream is = SmsConfigProps.class.getResourceAsStream("/sms.properties");
			smsprops = new Properties();
			try {
				smsprops.load(is);
			} catch (Exception e) {
				System.out.println("获取配置文件sms.properties异常！");
				e.printStackTrace();
			}
		}
		return smsprops;
	}
	
	public static String getAliAccessKeyId() {
		return getSmsProperties().getProperty("sms.ali.accessKeyId");
	}
	
	public static String getAliaccessKeySecret() {
		return getSmsProperties().getProperty("sms.ali.accessKeySecret");
	}
	
	public static String getSignName() {
		return getSmsProperties().getProperty("sms.ali.signName");
	}
	
	public static String getTemplateCode() {
		return getSmsProperties().getProperty("sms.ali.templateCode");
	}
	
}
