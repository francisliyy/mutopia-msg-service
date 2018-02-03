package com.mutopia.msg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mail_send_log database table.
 * 
 */
@Entity
@Table(name="mail_send_log")
@NamedQuery(name="MailSendLog.findAll", query="SELECT m FROM MailSendLog m")
public class MailSendLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mail_id")
	private int mailId;

	@Column(name="is_immediate")
	private String isImmediate;

	@Column(name="mail_attachment")
	private String mailAttachment;

	@Column(name="mail_cc")
	private String mailCc;

	@Column(name="mail_content")
	private String mailContent;

	@Column(name="mail_fail_desc")
	private String mailFailDesc;

	@Column(name="mail_receiver")
	private String mailReceiver;

	@Column(name="mail_result")
	private String mailResult;

	@Column(name="mail_schedule")
	private String mailSchedule;

	@Column(name="mail_source_ctl_url")
	private String mailSourceCtlUrl;

	@Column(name="mail_source_system")
	private String mailSourceSystem;

	@Column(name="mail_subject")
	private String mailSubject;

	@Column(name="mail_time")
	private Date mailTime;

	public MailSendLog() {
	}

	public int getMailId() {
		return this.mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	public String getIsImmediate() {
		return this.isImmediate;
	}

	public void setIsImmediate(String isImmediate) {
		this.isImmediate = isImmediate;
	}

	public String getMailAttachment() {
		return this.mailAttachment;
	}

	public void setMailAttachment(String mailAttachment) {
		this.mailAttachment = mailAttachment;
	}

	public String getMailCc() {
		return this.mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailFailDesc() {
		return this.mailFailDesc;
	}

	public void setMailFailDesc(String mailFailDesc) {
		this.mailFailDesc = mailFailDesc;
	}

	public String getMailReceiver() {
		return this.mailReceiver;
	}

	public void setMailReceiver(String mailReceiver) {
		this.mailReceiver = mailReceiver;
	}

	public String getMailResult() {
		return this.mailResult;
	}

	public void setMailResult(String mailResult) {
		this.mailResult = mailResult;
	}

	public String getMailSchedule() {
		return this.mailSchedule;
	}

	public void setMailSchedule(String mailSchedule) {
		this.mailSchedule = mailSchedule;
	}

	public String getMailSourceCtlUrl() {
		return this.mailSourceCtlUrl;
	}

	public void setMailSourceCtlUrl(String mailSourceCtlUrl) {
		this.mailSourceCtlUrl = mailSourceCtlUrl;
	}

	public String getMailSourceSystem() {
		return this.mailSourceSystem;
	}

	public void setMailSourceSystem(String mailSourceSystem) {
		this.mailSourceSystem = mailSourceSystem;
	}

	public String getMailSubject() {
		return this.mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public Date getMailTime() {
		return this.mailTime;
	}

	public void setMailTime(Date mailTime) {
		this.mailTime = mailTime;
	}

}