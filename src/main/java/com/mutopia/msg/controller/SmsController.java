/**
 * <p>Title: SmsController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutopia.msg.model.MailSendLog;

import io.swagger.annotations.Api;

@Api(description = "阿里云短信服务")
@RestController
@RequestMapping("/alisms")
public class SmsController {
	

	public boolean sendSms(@RequestBody MailSendLog mail){
        
        return this.mailService.sendImmediate(mail);
		
	}

}
