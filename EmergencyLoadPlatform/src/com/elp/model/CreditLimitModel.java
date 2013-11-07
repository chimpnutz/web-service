package com.elp.model;

import java.math.BigDecimal;

public class CreditLimitModel {

	String pid;
	String bid;
	BigDecimal amount;
	BigDecimal term;
	String validfr;
	String validto;
	String status;
	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTerm() {
		return term;
	}
	public void setTerm(BigDecimal term) {
		this.term = term;
	}
	public String getValidfr() {
		return validfr;
	}
	public void setValidfr(String validfr) {
		this.validfr = validfr;
	}
	public String getValidto() {
		return validto;
	}
	public void setValidto(String validto) {
		this.validto = validto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
