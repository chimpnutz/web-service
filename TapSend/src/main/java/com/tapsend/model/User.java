package com.tapsend.model;

public class User {


	private String user_id;
	private String password;
	private String role;
	private String status;
	private String firstName;
	private String middleName;
	private String lastName;
	private String company_id;
	private String details;
	private String email;
	private String type;

	private String parentuser_id;
	private String relation;
	private String suffix;
	
	private String id;
	private String bfirstname;
	private String bmiddlename;
	private String blastname;
	private String buserid;

	
	public String getParentuser_id() {
		return parentuser_id;
	}
	public void setParentuser_id(String parentuser_id) {
		this.parentuser_id = parentuser_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	private String addCity;
	private String addRegion;
	private String addBrgy;
	private String add_line1;
	private String add_line2;


	public String getAddCity() {
		return addCity;
	}
	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}
	public String getAddRegion() {
		return addRegion;
	}
	public void setAddRegion(String addRegion) {
		this.addRegion = addRegion;
	}
	public String getAddBrgy() {
		return addBrgy;
	}
	public void setAddBrgy(String addBrgy) {
		this.addBrgy = addBrgy;
	}
	public String getAdd_line1() {
		return add_line1;
	}
	public void setAdd_line1(String add_line1) {
		this.add_line1 = add_line1;
	}
	public String getAdd_line2() {
		return add_line2;
	}
	public void setAdd_line2(String add_line2) {
		this.add_line2 = add_line2;
	}
	public String getBfirstname() {
		return bfirstname;
	}
	public void setBfirstname(String bfirstname) {
		this.bfirstname = bfirstname;
	}
	public String getBmiddlename() {
		return bmiddlename;
	}
	public void setBmiddlename(String bmiddlename) {
		this.bmiddlename = bmiddlename;
	}
	public String getBlastname() {
		return blastname;
	}
	public void setBlastname(String blastname) {
		this.blastname = blastname;
	}
	public String getBuserid() {
		return buserid;
	}
	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}
	
}
