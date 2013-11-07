package com.elp.model;

import java.math.BigDecimal;

public class CreditLogsModel {

	
	int creditid;
	int companyid;
	String companyname;
	int amount;
	String amountavailed;
	String date_borrowed;
	String status;
	String approval_status;
	String delivery_status;
	int delivered_amount;
	int amount_paid;
	String datedue;
	String dateavailed;
	
	public int getCreditid() {
		return creditid;
	}
	public void setCreditid(int creditid) {
		this.creditid = creditid;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate_borrowed() {
		return date_borrowed;
	}
	public void setDate_borrowed(String date_borrowed) {
		this.date_borrowed = date_borrowed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public int getDelivered_amount() {
		return delivered_amount;
	}
	public void setDelivered_amount(int delivered_amount) {
		this.delivered_amount = delivered_amount;
	}
	public int getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getDatedue() {
		return datedue;
	}
	public void setDatedue(String datedue) {
		this.datedue = datedue;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAmountavailed() {
		return amountavailed;
	}
	public void setAmountavailed(String amountavailed) {
		this.amountavailed = amountavailed;
	}
	public String getDateavailed() {
		return dateavailed;
	}
	public void setDateavailed(String dateavailed) {
		this.dateavailed = dateavailed;
	}
	



}
