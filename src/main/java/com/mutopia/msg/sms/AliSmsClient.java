/**
 * <p>Title: AliSmsClient</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.sms;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mutopia.msg.constants.Constants;

@Service
public class AliSmsClient {
	
	private String aliAccessKeyId = "";
	
	private String aliAccessKeySecret = "";
	
	public AliSmsClient() {
		this.aliAccessKeyId = SmsConfigProps.getAliAccessKeyId();
		this.aliAccessKeySecret = SmsConfigProps.getAliaccessKeySecret();
	}



	/**
	 * 
	 * @param phoneNumbers:短信接收号码,支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
	 * @param templateParam:短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。{“code”:”1234”,”product”:”ytx”}
	 * @return
	 * @throws ClientException 
	 * @throws ServerException 
	 */
	public SendSmsResponse sendAliSms(String phoneNumbers,String templateParam) throws ServerException, ClientException{
		
		//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.aliAccessKeyId, this.aliAccessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Constants.PRODUCT, Constants.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SmsConfigProps.getSignName());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(SmsConfigProps.getTemplateCode());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
		
	}
	

}
