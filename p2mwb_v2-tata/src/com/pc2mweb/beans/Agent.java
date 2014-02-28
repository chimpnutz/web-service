package com.pc2mweb.beans;

public class Agent {

	
	private String username;
	private String password;
	private String topupVal;
	private String smTopupVal;
	private String sunTopupVal;
	private int active;
	private int locked;
	private String allows;
	private int amaxallow;
	private String otherDenom;
	
	//added
	private String ctiusername;
	private String ctipassword;
	private String lcusername;
	private String lcpasssword;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmTopupVal() {
		return smTopupVal;
	}
	public void setSmTopupVal(String smTopupVal) {
		this.smTopupVal = smTopupVal;
	}
	public String getSunTopupVal() {
		return sunTopupVal;
	}
	public void setSunTopupVal(String sunTopupVal) {
		this.sunTopupVal = sunTopupVal;
	}
	public String getTopupVal() {
		return topupVal;
	}
	public void setTopupVal(String topupVal) {
		this.topupVal = topupVal;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getLocked() {
		return locked;
	}
	
	
	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	public int getAmaxAllow() {
		return amaxallow;
	}
	
	
	public void setAmaxAllow(int locked) {
		this.amaxallow = locked;
	}
	public String getAllows() {
		return allows;
	}
	public void setAllows(String allows) {
		this.allows = allows;
	}
	public String getOtherDenom() {
		return otherDenom;
	}
	public void setOtherDenom(String otherDenom) {
		this.otherDenom = otherDenom;
	}

	private int agentid;
	public int getAgentid() {
		return agentid;
	}
	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}
	public String getCtiusername() {
		return ctiusername;
	}
	public void setCtiusername(String ctiusername) {
		this.ctiusername = ctiusername;
	}
	public String getCtipassword() {
		return ctipassword;
	}
	public void setCtipassword(String ctipassword) {
		this.ctipassword = ctipassword;
	}
	public String getLcusername() {
		return lcusername;
	}
	public void setLcusername(String lcusername) {
		this.lcusername = lcusername;
	}
	public String getLcpasssword() {
		return lcpasssword;
	}
	public void setLcpasssword(String lcpasssword) {
		this.lcpasssword = lcpasssword;
	}
	
}
