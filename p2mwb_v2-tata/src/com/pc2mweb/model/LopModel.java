package com.pc2mweb.model;


public class LopModel {

	String prefix;
	String mobnum;
	String amount;
	String pid;
	String bid;
	String message;
	String prodtype;
	String requestype;
	String txtype;
	String paypaltoken;
	int walletid;
	public int    agentid= -1;
	public Long    txid   = (long) 0;
	

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getMobnum() {
		return mobnum;
	}
	public void setMobnum(String mobnum) {
		this.mobnum = mobnum;
	}
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
	public int getWalletid() {
		return walletid;
	}
	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}
	
	public String getPaypaltoken() {
		return paypaltoken;
	}
	public void setPaypaltoken(String paypaltoken) {
		this.paypaltoken = paypaltoken;
	}
	
	
	

}
