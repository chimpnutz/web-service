package com.pc2mweb.model;


import java.math.BigDecimal;

public class Wallet_Transaction_Information {
  public BigDecimal beginbalance = new BigDecimal(0);
  public BigDecimal endingbalance= new BigDecimal(0);
  public BigDecimal walletchange = new BigDecimal(0);
  public String partnerid;
  public String partnertxid;
  public Long transactionId;
  public Long walletid;
  
  public  Wallet_Transaction_Information (String pid, String txid, String id,String wid)
  {
	  this.partnerid = pid;
	  this.partnertxid = txid;
	  this.transactionId = Long.getLong(id);
	  this.walletid = Long.getLong(wid);
  }
  
  public void deductAmount(Float amt)
  {
	  BigDecimal amtdec = new BigDecimal(amt.toString());
	  walletchange = walletchange.subtract(amtdec);
	  
	  
  }
  
  public void addAmount(Float amt)
  {
	  BigDecimal amtdec = new BigDecimal(amt.toString());
	  walletchange = walletchange.add(amtdec);
	  
	  
  }
  
}
  
  