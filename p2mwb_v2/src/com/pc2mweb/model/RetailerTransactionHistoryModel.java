package com.pc2mweb.model;

import java.math.BigDecimal;

public class RetailerTransactionHistoryModel {
	
	String transactionid;
	java.sql.Timestamp transferdate;
	String trace;
	String msisdn;
	float amount;
	String status;
	String username;
	
	public String getTransactionid() {
		return transactionid;
	}
	public java.sql.Timestamp getTransferdate() {
		return transferdate;
	}
	public String getTrace() {
		return trace;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public float getAmount() {
		return amount;
	}
	public String getStatus() {
		return status;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public void setTransferdate(java.sql.Timestamp transferdate) {
		this.transferdate = transferdate;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}