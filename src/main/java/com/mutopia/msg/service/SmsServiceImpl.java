/**
 * <p>Title: SmsServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.mutopia.msg.constants.Constants;
import com.mutopia.msg.model.SmsSendLog;
import com.mutopia.msg.repository.SmsRepository;
import com.mutopia.msg.sms.AliSmsClient;

@Service
public class SmsServiceImpl implements SmsService {
	
	@Autowired
	private SmsRepository smsRepository;
	@Autowired
    private AliSmsClient aliSmsClient;

	/* (non-Javadoc)
	 * @see com.mutopia.msg.service.SmsService#sendImmediate(com.mutopia.msg.model.SmsSendLog)
	 */
	@Override
	public boolean sendImmediate(SmsSendLog sms) {
		String result = Constants.SMS_SEND_OK;

		result = sendSms(sms);

		return result == Constants.SMS_SEND_OK ? true : false;
	}
	
	private String sendSms(SmsSendLog sms) {
		
		String templateParam = "{\"code\":\""+sms.getSmsContent()+"\"}";
		
		SendSmsResponse result = null;
		try {
			result = this.aliSmsClient.sendAliSms(sms.getSmsReceiver(), templateParam);			
		} catch (ClientException e) {
			e.printStackTrace();
		} finally{
			sms.setBizid(result.getBizId());
			sms.setRequestid(result.getRequestId());
			sms.setSmsResult(result.getCode());
			sms.setSmsResultDesc(result.getMessage());
			sms.setSmsTime(new Date());
			this.smsRepository.save(sms);
		}
		/*result = new SendSmsResponse();
		result.setBizId("111111");
		result.setCode("OK");
		result.setMessage("send successful");
		result.setRequestId("111111");
		
		sms.setBizid(result.getBizId());
		sms.setRequestid(result.getRequestId());
		sms.setSmsResult(result.getCode());
		sms.setSmsResultDesc(result.getMessage());
		sms.setSmsTime(new Date());
		this.smsRepository.save(sms);*/
		
		return result.getCode();
		
	}

}
