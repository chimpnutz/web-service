package com.pc2mweb.model;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class WalletTransferHistoryModel {
	
	String transid;
	String branchid;
	java.sql.Timestamp transdate;
	String sender;
	Double amount;
	Double sender_start_bal;
	Double sender_end_bal;
	String receiver;
	Double receiver_start_bal;
	Double receiver_end_bal;
	String transfertype;
	
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public java.sql.Timestamp  getTransdate() {
		return transdate;
	}
	public void setTransdate(java.sql.Timestamp  transdate) {
		this.transdate = transdate;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getSender_start_bal() {
		return sender_start_bal;
	}
	public void setSender_start_bal(Double sender_start_bal) {
		this.sender_start_bal = sender_start_bal;
	}
	public Double getSender_end_bal() {
		return sender_end_bal;
	}
	public void setSender_end_bal(Double sender_end_bal) {
		this.sender_end_bal = sender_end_bal;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Double getReceiver_start_bal() {
		return receiver_start_bal;
	}
	public void setReceiver_start_bal(Double receiver_start_bal) {
		this.receiver_start_bal = receiver_start_bal;
	}
	public Double getReceiver_end_bal() {
		return receiver_end_bal;
	}
	public void setReceiver_end_bal(Double receiver_end_bal) {
		this.receiver_end_bal = receiver_end_bal;
	}
	public String getTransfertype() {
		return transfertype;
	}
	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	
	
	
	
	
}