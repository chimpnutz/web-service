package com.circles.model;

import java.io.Serializable;

public class Comment implements Serializable{

	private String id;
	private String application_id;
	private String user_id;
	private String ispushed;
	private String edited_by;
	private String created;
	private String updated;
	private String version;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void applicationid(String application_id) {
		this.application_id = application_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIspushed() {
		return ispushed;
	}
	public void setIspushed(String ispushed) {
		this.ispushed = ispushed;
	}
	public String getEdited_by() {
		return edited_by;
	}
	public void setEdited_by(String edited_by) {
		this.edited_by = edited_by;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
		
	}
	
		
	
	
	
	
}
