package com.pc2mweb.model;

public class LC {
	private String strURL;
	private String strUID;
	private String strPwd;
	private String strRRN;
	private String strPCode;
	private String strBuyer;
	
  public String getURL(){
	return strURL;
  }
  
  public void setURL(String newValue) {
	strURL=newValue;
  }
  
  public String getUID(){
		return strUID;
  }	  
	  
  public void setUID(String newValue) {
	strUID=newValue;
  }
  
  public String getRRN(){
		return strRRN;
  }	  
	  
  public void setRRN(String newValue) {
	strRRN=newValue;
  }
  
  public String getPCode(){
		return strPCode;
  }	  
	  
  public void setPCode(String newValue) {
	strPCode=newValue;
  }
  
  public String getPwd(){
		return strPwd;
  }	  
	  
  public void setPwd(String newValue) {
	strPwd=newValue;
  }
  
  public String getBuyer(){
		return strBuyer;
  }	  
	  
  public void setBuyer(String newValue) {
	strBuyer=newValue;
  }
}
