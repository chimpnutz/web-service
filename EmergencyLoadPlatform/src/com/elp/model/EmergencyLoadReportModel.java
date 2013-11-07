package com.elp.model;

public class EmergencyLoadReportModel {
	
	
	String companyid;
	int credit_id;
	String companyname;
	String parentcompanyname;
	String status;
	String delivery_status;
	String stat;
	String STATUS;
	String dateavailed;
	int amount;
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public int getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(int credit_id) {
		this.credit_id = credit_id;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getDateavailed() {
		return dateavailed;
	}
	public void setDateavailed(String dateavailed) {
		this.dateavailed = dateavailed;
	}
	public String getParentcompanyname() {
		return parentcompanyname;
	}
	public void setParentcompanyname(String parentcompanyname) {
		this.parentcompanyname = parentcompanyname;
	}

}
