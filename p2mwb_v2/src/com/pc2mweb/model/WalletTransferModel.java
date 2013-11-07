package com.pc2mweb.model;

import java.math.BigDecimal;

public class WalletTransferModel  {
	
	
	String partnerid;
	String partnername;
	String branchid;
	String branchname;
	String msisdn;
	Float branchwallet;
	Float partnerwallet;
	Float decrementation;
	
	
	
	
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public Float getBranchwallet() {
		return branchwallet;
	}
	public void setBranchwallet(Float branchwallet) {
		this.branchwallet = branchwallet;
	}
	public Float getPartnerwallet() {
		return partnerwallet;
	}
	public void setPartnerwallet(Float partnerwallet) {
		this.partnerwallet = partnerwallet;
	}
	
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPartnername() {
		return partnername;
	}
	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}
	public Float getDecrementation() {
		return decrementation;
	}
	public void setDecrementation(Float decrementation) {
		this.decrementation = decrementation;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}



}
