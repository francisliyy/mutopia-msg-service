package com.mutopia.msg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the sms_send_log database table.
 * 
 */
@Entity
@Table(name="sms_send_log")
@NamedQuery(name="SmsSendLog.findAll", query="SELECT s FROM SmsSendLog s")
public class SmsSendLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sms_id")
	private int smsId;

	private String bizid;

	@Column(name="is_immediate")
	private String isImmediate;

	private String requestid;

	@Column(name="sms_content")
	private String smsContent;

	@Column(name="sms_receiver")
	private String smsReceiver;

	@Column(name="sms_result")
	private String smsResult;

	@Column(name="sms_result_desc")
	private String smsResultDesc;

	@Column(name="sms_schedule")
	private String smsSchedule;

	@Column(name="sms_source_ctl_url")
	private String smsSourceCtlUrl;

	@Column(name="sms_source_system")
	private String smsSourceSystem;

	@Column(name="sms_time")
	private Timestamp smsTime;

	public SmsSendLog() {
	}

	public int getSmsId() {
		return this.smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public String getBizid() {
		return this.bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getIsImmediate() {
		return this.isImmediate;
	}

	public void setIsImmediate(String isImmediate) {
		this.isImmediate = isImmediate;
	}

	public String getRequestid() {
		return this.requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsReceiver() {
		return this.smsReceiver;
	}

	public void setSmsReceiver(String smsReceiver) {
		this.smsReceiver = smsReceiver;
	}

	public String getSmsResult() {
		return this.smsResult;
	}

	public void setSmsResult(String smsResult) {
		this.smsResult = smsResult;
	}

	public String getSmsResultDesc() {
		return this.smsResultDesc;
	}

	public void setSmsResultDesc(String smsResultDesc) {
		this.smsResultDesc = smsResultDesc;
	}

	public String getSmsSchedule() {
		return this.smsSchedule;
	}

	public void setSmsSchedule(String smsSchedule) {
		this.smsSchedule = smsSchedule;
	}

	public String getSmsSourceCtlUrl() {
		return this.smsSourceCtlUrl;
	}

	public void setSmsSourceCtlUrl(String smsSourceCtlUrl) {
		this.smsSourceCtlUrl = smsSourceCtlUrl;
	}

	public String getSmsSourceSystem() {
		return this.smsSourceSystem;
	}

	public void setSmsSourceSystem(String smsSourceSystem) {
		this.smsSourceSystem = smsSourceSystem;
	}

	public Timestamp getSmsTime() {
		return this.smsTime;
	}

	public void setSmsTime(Timestamp smsTime) {
		this.smsTime = smsTime;
	}

}