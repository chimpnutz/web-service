package com.pc2mweb.model;

import java.math.BigDecimal;

public class TransactionHistoryModel {
	
	String transactionid;
	java.sql.Timestamp txdate;
	String tracenumber;
	String mobilenumber;
	Float amount;
	String status;
	Float amountdeducted;
	Float retainedearnings;
	
	public String getTransactionid() {
		return transactionid;
	}
	public java.sql.Timestamp getTxdate() {
		return txdate;
	}
	public String getTracenumber() {
		return tracenumber;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public Float getAmount() {
		return amount;
	}
	public String getStatus() {
		return status;
	}
	public Float getAmountdeducted() {
		return amountdeducted;
	}
	public Float getRetainedearnings() {
		return retainedearnings;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public void setTxdate(java.sql.Timestamp txdate) {
		this.txdate = txdate;
	}
	public void setTracenumber(String tracenumber) {
		this.tracenumber = tracenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setAmountdeducted(Float amountdeducted) {
		this.amountdeducted = amountdeducted;
	}
	public void setRetainedearnings(Float retainedearnings) {
		this.retainedearnings = retainedearnings;
	}
	
}
	
