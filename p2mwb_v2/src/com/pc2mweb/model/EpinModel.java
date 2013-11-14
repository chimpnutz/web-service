package com.pc2mweb.model;


public class EpinModel {

	String prefix;
	String mobnum;
	String amount;
	String pid;
	String bid; 
	String message;
	String prodtype;
	String requestype;
	String txtype;
	String quantity;
	String type;
	String username;
	long transid;
	
	
	int walletid;
	public int    agentid= -1;
	public Long    txid   = (long) 0;
	String denom;
	String target;
	String password;
	String password2;
	String prodcode;
	public String getProdcode() {
		return prodcode;
	}
	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}
	public long getTransid() {
		return transid;
	}
	public void setTransid(long transid) {
		this.transid = transid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDenom() {
		return denom;
	}
	public String getTarget() {
		return target;
	}
	public String getPassword() {
		return password;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	
	
	

}
