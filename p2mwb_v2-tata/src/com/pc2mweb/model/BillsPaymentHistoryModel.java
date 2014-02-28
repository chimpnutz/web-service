package com.pc2mweb.model;

public class BillsPaymentHistoryModel {


	java.sql.Timestamp datepaid;
	String transactionid;
	String companyid;
	String accountnumber;
	Float amount;
	String responsemsg;
	
	public java.sql.Timestamp getDatepaid() {
		return datepaid;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public Float getAmount() {
		return amount;
	}
	public String getResponsemsg() {
		return responsemsg;
	}
	public void setDatepaid(java.sql.Timestamp datepaid) {
		this.datepaid = datepaid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public void setResponsemsg(String responsemsg) {
		this.responsemsg = responsemsg;
	}

}
