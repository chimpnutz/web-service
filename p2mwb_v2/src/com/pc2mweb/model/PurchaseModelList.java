package com.pc2mweb.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PurchaseModelList {

	List<PurchaseOrderModel> po;


	public List<PurchaseOrderModel> getPO() {
		return po;
	}

	public void setPO(List<PurchaseOrderModel> po) {
		this.po = po;
	}

}
