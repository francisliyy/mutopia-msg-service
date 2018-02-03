/**
 * <p>Title: MailController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutopia.msg.model.MailSendLog;
import com.mutopia.msg.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "邮件服务")
@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@ApiOperation(value="发送邮件", notes="发送邮件")
	@ApiImplicitParam(name = "mail", value = "邮件信息", required = true, dataType = "MailSendLog")
	@PostMapping("")
	public boolean sendMail(@RequestBody MailSendLog mail){
        
        return this.mailService.sendImmediate(mail);
		
	}

}
