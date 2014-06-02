package com.circles.model;

public class Residential {


	private String plan_code;
	private String cis_no;
	private String title;
	private String lastName;
	private String firstName;
	private String middleName;
	private String motherMaidenName;
	private String birthday;
	private String civil_status;
	private String address;
	private String home_ownership;
	private String years_of_stay;
	private String emergency_person;
	private String emergency_contact;
	private String email;
	private String contact;
	private String employment_status;
	private String spouse;
	private String attachments;

	

	public String getPlan_code(){
		return plan_code;
	}
	public void setPlan_code(String plan_code){
		this.plan_code = plan_code;
	}
	
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getMiddleName(){
		return middleName;
	}
	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}
	
	public String getMotherMaidenName() {
		return motherMaidenName;
	}
	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}
	public String getBirthday(){
		return birthday;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	public String getCivil_status(){
		return civil_status;
	}
	public void setCivil_status(String civil_status){
		this.civil_status = civil_status;
	}
	
	public String getCis_no() {
		return cis_no;
	}
	public void setCis_no(String cis_no) {
		this.cis_no = cis_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress(){
		return address;
	}
	public void setAddres(String address){
		this.address = address;
	}
	public String getHome_ownership() {
		return home_ownership;
	}
	public void setHome_ownership(String home_ownership) {
		this.home_ownership = home_ownership;
	}
	public String getYears_of_stay() {
		return years_of_stay;
	}
	public void setYears_of_stay(String years_of_stay) {
		this.years_of_stay = years_of_stay;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmployment_status() {
		return employment_status;
	}
	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}
	public String getSpouse() {
		return spouse;
	}
	
	public void setEmergency_person(String emergency_person) {
		this.emergency_person = emergency_person;
	}
	public String getEmergency_person(){
		return emergency_person;
	}
	
	public String getEmergency_contact() {
		return emergency_contact;
	}
	public void setEmergency_contact(String emergency_contact) {
		this.emergency_contact = emergency_contact;
	}
	public String getAttachments() {
		return attachments;
	}
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
	
}
