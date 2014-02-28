package com.pc2mweb.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PaymentOrderModel {

	String type;	
	String remarks;
	CommonsMultipartFile cfile;
	
	String attachment;
	
	
	String text;
	
	int paymentID;
	String branch;
	String partnername;
	String partnerid;
	String bank;
	String status;
	String date_created;
	
	String total_amount;
	String total_order;
	String total_fee;
	
	
	String payment_date;
	String returned_amount;
	String returned_date;
	String payment_status;
	int poid;
	String po_status;
	
	String delivery_status;
	
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public CommonsMultipartFile getCfile() {
		return cfile;
	}
	public void setCfile(CommonsMultipartFile cfile) {
		this.cfile = cfile;
	}
	public String getPartnername() {
		return partnername;
	}
	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}	
	public String getPo_status() {
		return po_status;
	}
	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getTotal_order() {
		return total_order;
	}
	public void setTotal_order(String total_order) {
		this.total_order = total_order;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getReturned_amount() {
		return returned_amount;
	}
	public void setReturned_amount(String returned_amount) {
		this.returned_amount = returned_amount;
	}
	public String getReturned_date() {
		return returned_date;
	}
	public void setReturned_date(String returned_date) {
		this.returned_date = returned_date;
	}
	
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
