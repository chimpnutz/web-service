package com.elp.model;

public class DailyTransferReportModel {
	
	
	int transferreportid;
	String transferfromtime;
	String transfertotime;
	String opensim;
	float amount;
	float inprogressamount;
	
	public int getTransferreportid() {
		return transferreportid;
	}
	public void setTransferreportid(int transferreportid) {
		this.transferreportid = transferreportid;
	}
	public String getTransferfromtime() {
		return transferfromtime;
	}
	public void setTransferfromtime(String transferfromtime) {
		this.transferfromtime = transferfromtime;
	}
	public String getTransfertotime() {
		return transfertotime;
	}
	public void setTransfertotime(String transfertotime) {
		this.transfertotime = transfertotime;
	}
	public String getOpensim() {
		return opensim;
	}
	public void setOpensim(String opensim) {
		this.opensim = opensim;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getInprogressamount() {
		return inprogressamount;
	}
	public void setInprogressamount(float inprogressamount) {
		this.inprogressamount = inprogressamount;
	}

	
	
	
	

}
