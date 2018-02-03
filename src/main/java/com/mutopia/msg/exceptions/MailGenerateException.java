/**
 * <p>Title: MailGenerateException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: MUTOPIA</p>
 * @author lyx
 * @version 1.0
 */
package com.mutopia.msg.exceptions;

public class MailGenerateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MailGenerateException() {
		this(" generate mail exception!");
	}
	
	public MailGenerateException(String msg) {
		super(msg);
	}
	
	public MailGenerateException(String msg,Throwable cause){
		super(msg,cause);
	}

}
