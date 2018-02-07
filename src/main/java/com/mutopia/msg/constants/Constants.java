/**
 * <p>Title: Constants</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.constants;

public final class Constants {
	
	/**
	 * 消息发送结果定义
	 */
	// 消息发送成功
	public static final int MSG_SEND_SUCCESS = 1;
	// 消息发送失败
	public static final int MSG_SEND_FAIL = 0;
	
	/**
	 * 是否即时发送消息定义
	 */
	// 消息即时发送
	public static final String MSG_SEND_IMMEDIATE = "1";
	// 消息非即时发送
	public static final String MSG_SEND_LATER = "0";
	
	/**
	 * 阿里短信发送结果定义
	 */
	// 消息发送成功
	public static final String SMS_SEND_OK = "OK";
	
	/**
	 * 阿里短信发送参数
	 */
	//产品名称:云通信短信API产品,开发者无需替换
    public static final String PRODUCT = "Dysmsapi";
    //产品域名,开发者无需替换
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

}
