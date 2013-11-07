package com.pc2mweb.model;

public class AgentModel
{	
	private String username;
	private String password;
	private String active;
	private String allows;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	
	public String getAllows() {
		return allows;
	}
	public void setAllows(String allow) {
		this.allows = allow;
	}


	private Long agentid;
	public Long getAgentid() {
		return agentid;
	}
	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}

}