package com.pc2mweb.model;

import java.math.BigDecimal;

public class EmergencyLoadHistoryModel {

	
	BigDecimal amount;
	String status;
	String branch;
	String validfrom;
	String validto;
	BigDecimal availedamount;
	String duedate;
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(String validfrom) {
		this.validfrom = validfrom;
	}
	public String getValidto() {
		return validto;
	}
	public void setValidto(String validto) {
		this.validto = validto;
	}
	public BigDecimal getAvailedamount() {
		return availedamount;
	}
	public void setAvailedamount(BigDecimal availedamount) {
		this.availedamount = availedamount;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

}
