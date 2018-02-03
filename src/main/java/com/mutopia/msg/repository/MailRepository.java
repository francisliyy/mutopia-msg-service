/**
 * <p>Title: MailRepository</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.repository;

import org.springframework.data.repository.Repository;

import com.mutopia.msg.model.MailSendLog;

@org.springframework.stereotype.Repository
public interface MailRepository extends Repository<MailSendLog, Integer> {
	
	MailSendLog save(MailSendLog mail);

}
