package com.pc2mweb.model;

import java.math.BigDecimal;

public class UserManagementModel {
	
	int uid;
	String agentuname;
	String partnername;
	String partnerid;
	String branchid;
	String branchname;
	
	public String getAgentuname() {
		return agentuname;
	}
	public void setAgentuname(String agentuname) {
		this.agentuname = agentuname;
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
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

}
