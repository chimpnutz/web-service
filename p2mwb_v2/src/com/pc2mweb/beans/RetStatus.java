package com.pc2mweb.beans;

public class RetStatus {
	  private String strStatus="10";
	  private String strRRN;
	  private String strTID;
	  private String strBal;
	  private String strEPin;
	  private String strErrDesc;
	  //0-> successful Otherwise failed
	  
	  public void setERRDesc(String newValue){
		  strErrDesc=newValue;
	  }
	  
	  public String getERRDEsc() {
		  return strErrDesc;
	  }
	  
	  public void setEPin(String newValue){
		  strEPin=newValue;
	  }
	  
	  public String getEPin() {
		  return strEPin;
	  }
	  
	  
	  public void setBal(String newValue){
		  strBal=newValue;
	  }
	  
	  public String getBal() {
		  return strBal;
	  }
	  
	  
	  public void setRRN(String newValue){
		  strRRN=newValue;
	  }
	  
	  public String getRRN() {
		  return strRRN;
	  }
	  
	  public void setTID(String newValue){
		  strTID=newValue;
	  }
	  
	  public String getTID() {
		  return strTID;
	  }
	  
	  
	  public void setStatus(String newValue){
		  strStatus=newValue;
	  }
	  
	  public String getStatus() {
		  return strStatus;
	  }
	  
	  public String toString() {
		  StringBuffer buff = new StringBuffer();
		  buff.append("strRRN ").append(strRRN)
		  .append("strStatus ").append(strStatus)
		  .append("strTID ").append(strTID)
		  .append("strBal ").append(strBal)
		  .append("strEPin").append(strEPin)
		  .append("strErrDesc ").append(strErrDesc);
		  return buff.toString();
	  }
	  
	  
	}