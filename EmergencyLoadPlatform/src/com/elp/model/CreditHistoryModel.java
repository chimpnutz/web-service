package com.elp.model;

import java.math.BigDecimal;

public class CreditHistoryModel 
{

	int creditid;
	String newstatus;
	String changedate;
	int companyid;
	String amount;
	
	public int getCreditid() {
		return creditid;
	}
	public void setCreditid(int creditid) {
		this.creditid = creditid;
	}
	public String getNewstatus() {
		return newstatus;
	}
	public void setNewstatus(String newstatus) {
		this.newstatus = newstatus;
	}
	public String getChangedate() {
		return changedate;
	}
	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
