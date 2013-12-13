package com.pc2mweb.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PurchaseOrderModel {
	
	String item;
	String quantity;
	String face_value_amount;
	String discount_amount;
	String wallet;
	String amount;
	String total_amount;
	
	int poid;
	String podate;
	String order_amount;
	String payment_status;
	String delivery_status;
	String po_status;
	
	
	
	String retailer;
	
	public String getItem() {
		return item;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getWallet() {
		return wallet;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getPoid() {
		return poid;
	}
	public String getPodate() {
		return podate;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public String getPo_status() {
		return po_status;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public void setPodate(String podate) {
		this.podate = podate;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getFace_value_amount() {
		return face_value_amount;
	}
	public String getDiscount_amount() {
		return discount_amount;
	}
	public void setFace_value_amount(String face_value_amount) {
		this.face_value_amount = face_value_amount;
	}
	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}


}
