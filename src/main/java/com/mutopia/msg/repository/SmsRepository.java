/**
 * <p>Title: SmsRepository</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.repository;

import org.springframework.data.repository.Repository;

import com.mutopia.msg.model.SmsSendLog;

@org.springframework.stereotype.Repository
public interface SmsRepository extends Repository<SmsSendLog, Integer> {
	
	SmsSendLog save(SmsSendLog sms);

}
