package com.pc2mweb.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PaymentModelList {

	List<PaymentOrderModel> poms;
	List<MultipartFile> file;
	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
	public List<PaymentOrderModel> getPOMS() {
		return poms;
	}

	public void setPOMS(List<PaymentOrderModel> poms) {
		this.poms = poms;
	}
}
