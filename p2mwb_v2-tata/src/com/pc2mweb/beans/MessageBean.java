package com.pc2mweb.beans;

import java.math.BigDecimal;

	
	public class MessageBean {
		
		
		String success;
		Float wallet;
		String partnername;
		String partnerid;
		String type;
		String branchname;
		String branchid;
		
		
		public String getPartnerid() {
			return partnerid;
		}

		public void setPartnerid(String partnerid) {
			this.partnerid = partnerid;
		}

		public String getBranchname() {
			return branchname;
		}

		public void setBranchname(String branchname) {
			this.branchname = branchname;
		}

		public String getBranchid() {
			return branchid;
		}

		public void setBranchid(String branchid) {
			this.branchid = branchid;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Float getWallet() {
			return wallet;
		}

		public void setWallet(Float wallet) {
			this.wallet = wallet;
		}

		public String getPartnername() {
			return partnername;
		}

		public void setPartnername(String partnername) {
			this.partnername = partnername;
		}

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

	}



