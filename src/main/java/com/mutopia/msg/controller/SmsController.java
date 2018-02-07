/**
 * <p>Title: SmsController</p>
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

import com.mutopia.msg.model.SmsSendLog;
import com.mutopia.msg.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "阿里云短信服务")
@RestController
@RequestMapping("/alisms")
public class SmsController {
	
	@Autowired
	private SmsService smsService;	

	@ApiOperation(value="发送阿里短信", notes="发送阿里短信")
	@ApiImplicitParam(name = "sms", value = "短信信息", required = true, dataType = "SmsSendLog")
	@PostMapping("")
	public boolean sendSms(@RequestBody SmsSendLog sms){
        
        return this.smsService.sendImmediate(sms);
		
	}

}
