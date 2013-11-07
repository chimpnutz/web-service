package com.pc2mweb.model;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class WalletTransferHistoryModel {
	
	String txid;
	String branchid;
	String wallettransferdate;
	String sourcewallet;
	Double amount;
	Double beginningbalancesourcewallet;
	Double endingbalancesourcewallet;
	String destinationwallet;
	Double beginningbalancedestionationwallet;
	Double endingbalancedestinationwallet;
	String transfertype;
	
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public String getTransfertype() {
		return transfertype;
	}
	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getWallettransferdate() {
		return wallettransferdate;
	}
	public void setWallettransferdate(String wallettransferdate) {
		this.wallettransferdate = wallettransferdate;
	}
	public String getSourcewallet() {
		return sourcewallet;
	}
	public void setSourcewallet(String sourcewallet) {
		this.sourcewallet = sourcewallet;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getBeginningbalancesourcewallet() {
		return beginningbalancesourcewallet;
	}
	public void setBeginningbalancesourcewallet(
			Double beginningbalancesourcewallet) {
		this.beginningbalancesourcewallet = beginningbalancesourcewallet;
	}
	public Double getEndingbalancesourcewallet() {
		return endingbalancesourcewallet;
	}
	public void setEndingbalancesourcewallet(Double endingbalancesourcewallet) {
		this.endingbalancesourcewallet = endingbalancesourcewallet;
	}
	public String getDestinationwallet() {
		return destinationwallet;
	}
	public void setDestinationwallet(String destinationwallet) {
		this.destinationwallet = destinationwallet;
	}
	public Double getBeginningbalancedestionationwallet() {
		return beginningbalancedestionationwallet;
	}
	public void setBeginningbalancedestionationwallet(
			Double beginningbalancedestionationwallet) {
		this.beginningbalancedestionationwallet = beginningbalancedestionationwallet;
	}
	public Double getEndingbalancedestinationwallet() {
		return endingbalancedestinationwallet;
	}
	public void setEndingbalancedestinationwallet(
			Double endingbalancedestinationwallet) {
		this.endingbalancedestinationwallet = endingbalancedestinationwallet;
	}
	
	


	
	
	
}