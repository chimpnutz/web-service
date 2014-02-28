package com.pc2mweb.beans;

public class BillerFieldBean {


	String parameter_name;
	String description;
	String billerfield;
	String payfields;
	String parameter_type;
	String format;
	String label;
	
	public String getParameter_name() {
		return parameter_name;
	}

	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBillerfield() {
		return billerfield;
	}

	public String getPayfields() {
		return payfields;
	}

	public void setBillerfield(String billerfield) {
		this.billerfield = billerfield;
	}

	public void setPayfields(String payfields) {
		this.payfields = payfields;
	}

	public String getParameter_type() {
		return parameter_type;
	}

	public void setParameter_type(String parameter_type) {
		this.parameter_type = parameter_type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
