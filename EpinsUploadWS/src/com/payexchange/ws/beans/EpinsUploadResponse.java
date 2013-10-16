package com.payexchange.ws.beans;

public class EpinsUploadResponse {
	
	String tracenumber;
	int resultcode;
	
	String password;

	public String getTracenumber() {
		return tracenumber;
	}

	public void setTracenumber(String tracenumber) {
		this.tracenumber = tracenumber;
	}

	public int getResultcode() {
		return resultcode;
	}

	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
