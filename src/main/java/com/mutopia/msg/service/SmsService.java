/**
 * <p>Title: SmsService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.service;

import com.mutopia.msg.model.SmsSendLog;

public interface SmsService {
	
	public boolean sendImmediate(SmsSendLog sms) ;

}
