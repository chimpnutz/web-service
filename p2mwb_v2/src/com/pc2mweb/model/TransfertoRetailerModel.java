package com.pc2mweb.model;


public class TransfertoRetailerModel {

	String msisdn;
	String amount;
	String pid;
	String bid;
	String message;
	String type;
	String prodtype;
	String requestype;
	String txtype;
	public int    agentid= -1;
	public Long    ptxid   = (long) 0;
	public int    txid= 0;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProdtype() {
		return prodtype;
	}
	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}
	public String getRequestype() {
		return requestype;
	}
	public void setRequestype(String requestype) {
		this.requestype = requestype;
	}
	public String getTxtype() {
		return txtype;
	}
	public void setTxtype(String txtype) {
		this.txtype = txtype;
	}
	
	
	
	

}
