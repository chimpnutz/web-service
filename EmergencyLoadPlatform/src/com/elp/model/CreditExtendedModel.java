package com.elp.model;

import java.math.BigDecimal;

public class CreditExtendedModel {


	BigDecimal creditid;
	String status;
	BigDecimal amount_paid;
	String pid;
	String bid;
	
	public BigDecimal getCreditid() {
		return creditid;
	}
	public void setCreditid(BigDecimal creditid) {
		this.creditid = creditid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
}
