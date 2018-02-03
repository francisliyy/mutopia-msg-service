/**
 * <p>Title: MailService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.service;

import com.mutopia.msg.model.MailSendLog;

public interface MailService {
	
	public boolean sendImmediate(MailSendLog mail) ;

}
