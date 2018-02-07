/**
 * <p>Title: AliSmsBean</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.model;

public class AliSmsBean {
	
	//请求ID
	private String requestId;
	
	//状态码-返回OK代表请求成功,其他错误码详见错误码列表
	private String code;
	
	//状态码的描述
	private String message;
	
	//发送回执ID,可根据该ID查询具体的发送状态
	private String bizId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}
