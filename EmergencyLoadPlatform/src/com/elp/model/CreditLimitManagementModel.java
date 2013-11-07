package com.elp.model;

public class CreditLimitManagementModel {
	
	int creditlimitid;
	int companyid;
	String companyname;
	String creditlimit;
	String outstandingbalance;
	String remaininglimit;
	int credit_id;
	String status;
	String paymentstatus;
	int creditterm;
	String valid_from;
	String to_date;
	String date;
	
	String dsp;
	String retailers;
	String pd;
	
	
	public int getCreditlimitid() {
		return creditlimitid;
	}
	public void setCreditlimitid(int creditlimitid) {
		this.creditlimitid = creditlimitid;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public String getCreditlimit() {
		return creditlimit;
	}
	public void setCreditlimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}
	public String getOutstandingbalance() {
		return outstandingbalance;
	}
	public void setOutstandingbalance(String outstandingbalance) {
		this.outstandingbalance = outstandingbalance;
	}
	public String getRemaininglimit() {
		return remaininglimit;
	}
	public void setRemaininglimit(String remaininglimit) {
		this.remaininglimit = remaininglimit;
	}
	public int getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(int credit_id) {
		this.credit_id = credit_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreditterm() {
		return creditterm;
	}
	public void setCreditterm(int creditterm) {
		this.creditterm = creditterm;
	}
	public String getValid_from() {
		return valid_from;
	}
	public void setValid_from(String valid_from) {
		this.valid_from = valid_from;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDsp() {
		return dsp;
	}
	public void setDsp(String dsp) {
		this.dsp = dsp;
	}
	public String getRetailers() {
		return retailers;
	}
	public void setRetailers(String retailers) {
		this.retailers = retailers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

}
