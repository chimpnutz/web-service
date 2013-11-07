package com.pc2mweb.model;

import java.util.Date;

public class BillsPaymentModel {

	String prefix;
	String mobnum;
	String amount;
	String pid;
	String bid;
	String message;
	String prodtype;
	String requestype;
	String txtype;
	int walletid;
	public int    agentid= -1;
	public Long    txid   = (long) 0;
	
	String companyid;
	String userid;
	String accountnumber;
	String cash_amount;
	String check_amount;
	String  bankcode;
	String check_number;
	String name;
	String address;
	String account_type;
	String bill_amount;
	
	String date_paid;
	String time_paid;
	java.sql.Timestamp bill_date;
	java.sql.Timestamp expiry_date;
	
	String biller_post_tran_field;
	String payfield;
	String uploadmapping;
	
	String branchcode;

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
	public String getCompanyid() {
		return companyid;
	}
	public String getUserid() {
		return userid;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public String getCash_amount() {
		return cash_amount;
	}
	public String getCheck_amount() {
		return check_amount;
	}
	public String getBankcode() {
		return bankcode;
	}
	public String getCheck_number() {
		return check_number;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getAccount_type() {
		return account_type;
	}
	public String getDate_paid() {
		return date_paid;
	}
	public String getTime_paid() {
		return time_paid;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setDate_paid(String date_paid) {
		this.date_paid = date_paid;
	}
	public void setTime_paid(String time_paid) {
		this.time_paid = time_paid;
	}
	public void setBill_date(java.sql.Timestamp bill_date) {
		this.bill_date = bill_date;
	}
	public void setExpiry_date(java.sql.Timestamp expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getBill_amount() {
		return bill_amount;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public void setCash_amount(String cash_amount) {
		this.cash_amount = cash_amount;
	}
	public void setCheck_amount(String check_amount) {
		this.check_amount = check_amount;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public void setBill_amount(String bill_amount) {
		this.bill_amount = bill_amount;
	}
	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}
	public String getBiller_post_tran_field() {
		return biller_post_tran_field;
	}
	public String getPayfield() {
		return payfield;
	}
	public void setBiller_post_tran_field(String biller_post_tran_field) {
		this.biller_post_tran_field = biller_post_tran_field;
	}
	public void setPayfield(String payfield) {
		this.payfield = payfield;
	}
	public String getUploadmapping() {
		return uploadmapping;
	}
	public void setUploadmapping(String uploadmapping) {
		this.uploadmapping = uploadmapping;
	}
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

}
