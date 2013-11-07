package com.elp.model;

public class OpenSimReplenishmentModel {
	
	int transferreport_id;
	String transferfrom_time;
	String transferto_time;
	String opensim;
	float inprogress_amount;
	float amount_transfered;
	int companyid;
	float outstanding_balance;
	String payment_status;
	String payment_date;
	float amount_paid;
	
	
	public int getTransferreport_id() {
		return transferreport_id;
	}
	public void setTransferreport_id(int transferreport_id) {
		this.transferreport_id = transferreport_id;
	}
	public String getTransferfrom_time() {
		return transferfrom_time;
	}
	public void setTransferfrom_time(String transferfrom_time) {
		this.transferfrom_time = transferfrom_time;
	}
	public String getTransferto_time() {
		return transferto_time;
	}
	public void setTransferto_time(String transferto_time) {
		this.transferto_time = transferto_time;
	}
	public String getOpensim() {
		return opensim;
	}
	public void setOpensim(String opensim) {
		this.opensim = opensim;
	}
	public float getInprogress_amount() {
		return inprogress_amount;
	}
	public void setInprogress_amount(float inprogress_amount) {
		this.inprogress_amount = inprogress_amount;
	}
	public float getAmount_transfered() {
		return amount_transfered;
	}
	public void setAmount_transfered(float amount_transfered) {
		this.amount_transfered = amount_transfered;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public float getOutstanding_balance() {
		return outstanding_balance;
	}
	public void setOutstanding_balance(float outstanding_balance) {
		this.outstanding_balance = outstanding_balance;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public float getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(float amount_paid) {
		this.amount_paid = amount_paid;
	}

	

}
