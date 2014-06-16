package com.tapsend.model;

import java.io.Serializable;

public class UserDetails implements Serializable{
	private String id;
	private String	password_hash;
	private boolean	password_changed;
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String birthday;
	private String type;
	private String image;
	private String termination_image;
	private String addCity;
	private String addRegion;
	private String addBrgy;
	private String add_line1;
	private String add_line2;
	private String zipCode;
	private String sss;
	private String pagibig;
	private String philhealth;
	private String tin;
	private String gender;
	private String civilstatus;
	private String emergency_person;
	private String emergency_number;
	private String landline;
	private String mobile;
	private String suffix;
	private String maiden_name;
	private String ofc_number;
	private String ben_firstName;
	private String ben_middleName;
	private String ben_lastName;
	private String ben_suffix;
	private String ben_birthday;
	private String ben_gender;
	private String ben_civilstatus;
	private String ben_maiden_name;
	private String ben_disabled;
	private String ph_pen;
	private String ph_employer_name;
	private String ph_employer_address;
	private String ph_date_hired;
	private String ph_profession;
	private String ph_country_based;
	private String ph_foreign_address;
	private String ph_duration_from;
	private String ph_duration_to;
	private String ph_member_type;
	private String ph_family_income;
	private String ph_doc1;
	private String ph_doc2;
	private String ph_doc3;
	private String ph_doc4;
	private String ss_pension_type;
	private String ss_pension_account_number;
	private String ss_membership_type;
	private String ss_employer_id;
	private String ss_receipt_number;
	private String ss_doc1;
	private String ss_doc2;
	private String ss_doc3;
	private String ss_doc4;
	private String pi_membership_type;
	private String pi_employer_business_name;
	private String pi_employee_number;
	private String pi_date_start;
	private String pi_profession;
	private String pi_employment_status;
	private String pi_address;
	private String pi_basic;
	private String pi_allowance;
	private String pi_total;
	private String pi_doc1;
	private String pi_doc2;
	private String pi_doc3;
	private String pi_doc4;
	private String company_name;
	private String company_address;
	private String company_est;
	private String ispushed;
	private String edited_by;
	private String created;
	private String updated;
	private String version;
	private String extension_name;
	private String parent;
	private String relation;
	
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getExtension_name() {
		return extension_name;
	}
	public void setExtension_name(String extension_name) {
		this.extension_name = extension_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public boolean isPassword_changed() {
		return password_changed;
	}
	public void setPassword_changed(boolean password_changed) {
		this.password_changed = password_changed;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTermination_image() {
		return termination_image;
	}
	public void setTermination_image(String termination_image) {
		this.termination_image = termination_image;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSss() {
		return sss;
	}
	public void setSss(String sss) {
		this.sss = sss;
	}
	public String getPagibig() {
		return pagibig;
	}
	public void setPagibig(String pagibig) {
		this.pagibig = pagibig;
	}
	public String getPhilhealth() {
		return philhealth;
	}
	public void setPhilhealth(String philhealth) {
		this.philhealth = philhealth;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCivilstatus() {
		return civilstatus;
	}
	public void setCivilstatus(String civilstatus) {
		this.civilstatus = civilstatus;
	}
	public String getEmergency_person() {
		return emergency_person;
	}
	public void setEmergency_person(String emergency_person) {
		this.emergency_person = emergency_person;
	}
	public String getEmergency_number() {
		return emergency_number;
	}
	public void setEmergency_number(String emergency_number) {
		this.emergency_number = emergency_number;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getMaiden_name() {
		return maiden_name;
	}
	public void setMaiden_name(String maiden_name) {
		this.maiden_name = maiden_name;
	}
	public String getOfc_number() {
		return ofc_number;
	}
	public void setOfc_number(String ofc_number) {
		this.ofc_number = ofc_number;
	}
	public String getBen_firstName() {
		return ben_firstName;
	}
	public void setBen_firstName(String ben_firstName) {
		this.ben_firstName = ben_firstName;
	}
	public String getBen_middleName() {
		return ben_middleName;
	}
	public void setBen_middleName(String ben_middleName) {
		this.ben_middleName = ben_middleName;
	}
	public String getBen_lastName() {
		return ben_lastName;
	}
	public void setBen_lastName(String ben_lastName) {
		this.ben_lastName = ben_lastName;
	}
	public String getBen_suffix() {
		return ben_suffix;
	}
	public void setBen_suffix(String ben_suffix) {
		this.ben_suffix = ben_suffix;
	}
	public String getBen_birthday() {
		return ben_birthday;
	}
	public void setBen_birthday(String ben_birthday) {
		this.ben_birthday = ben_birthday;
	}
	public String getBen_gender() {
		return ben_gender;
	}
	public void setBen_gender(String ben_gender) {
		this.ben_gender = ben_gender;
	}
	public String getBen_civilstatus() {
		return ben_civilstatus;
	}
	public void setBen_civilstatus(String ben_civilstatus) {
		this.ben_civilstatus = ben_civilstatus;
	}
	public String getBen_maiden_name() {
		return ben_maiden_name;
	}
	public void setBen_maiden_name(String ben_maiden_name) {
		this.ben_maiden_name = ben_maiden_name;
	}
	public String getBen_disabled() {
		return ben_disabled;
	}
	public void setBen_disabled(String ben_disabled) {
		this.ben_disabled = ben_disabled;
	}
	public String getPh_pen() {
		return ph_pen;
	}
	public void setPh_pen(String ph_pen) {
		this.ph_pen = ph_pen;
	}
	public String getPh_employer_name() {
		return ph_employer_name;
	}
	public void setPh_employer_name(String ph_employer_name) {
		this.ph_employer_name = ph_employer_name;
	}
	public String getPh_employer_address() {
		return ph_employer_address;
	}
	public void setPh_employer_address(String ph_employer_address) {
		this.ph_employer_address = ph_employer_address;
	}
	public String getPh_date_hired() {
		return ph_date_hired;
	}
	public void setPh_date_hired(String ph_date_hired) {
		this.ph_date_hired = ph_date_hired;
	}
	public String getPh_profession() {
		return ph_profession;
	}
	public void setPh_profession(String ph_profession) {
		this.ph_profession = ph_profession;
	}
	public String getPh_country_based() {
		return ph_country_based;
	}
	public void setPh_country_based(String ph_country_based) {
		this.ph_country_based = ph_country_based;
	}
	public String getPh_foreign_address() {
		return ph_foreign_address;
	}
	public void setPh_foreign_address(String ph_foreign_address) {
		this.ph_foreign_address = ph_foreign_address;
	}
	public String getPh_duration_from() {
		return ph_duration_from;
	}
	public void setPh_duration_from(String ph_duration_from) {
		this.ph_duration_from = ph_duration_from;
	}
	public String getPh_duration_to() {
		return ph_duration_to;
	}
	public void setPh_duration_to(String ph_duration_to) {
		this.ph_duration_to = ph_duration_to;
	}
	public String getPh_member_type() {
		return ph_member_type;
	}
	public void setPh_member_type(String ph_member_type) {
		this.ph_member_type = ph_member_type;
	}
	public String getPh_family_income() {
		return ph_family_income;
	}
	public void setPh_family_income(String ph_family_income) {
		this.ph_family_income = ph_family_income;
	}
	public String getPh_doc1() {
		return ph_doc1;
	}
	public void setPh_doc1(String ph_doc1) {
		this.ph_doc1 = ph_doc1;
	}
	public String getPh_doc2() {
		return ph_doc2;
	}
	public void setPh_doc2(String ph_doc2) {
		this.ph_doc2 = ph_doc2;
	}
	public String getPh_doc3() {
		return ph_doc3;
	}
	public void setPh_doc3(String ph_doc3) {
		this.ph_doc3 = ph_doc3;
	}
	public String getPh_doc4() {
		return ph_doc4;
	}
	public void setPh_doc4(String ph_doc4) {
		this.ph_doc4 = ph_doc4;
	}
	public String getSs_pension_type() {
		return ss_pension_type;
	}
	public void setSs_pension_type(String ss_pension_type) {
		this.ss_pension_type = ss_pension_type;
	}
	public String getSs_pension_account_number() {
		return ss_pension_account_number;
	}
	public void setSs_pension_account_number(String ss_pension_account_number) {
		this.ss_pension_account_number = ss_pension_account_number;
	}
	public String getSs_membership_type() {
		return ss_membership_type;
	}
	public void setSs_membership_type(String ss_membership_type) {
		this.ss_membership_type = ss_membership_type;
	}
	public String getSs_employer_id() {
		return ss_employer_id;
	}
	public void setSs_employer_id(String ss_employer_id) {
		this.ss_employer_id = ss_employer_id;
	}
	public String getSs_receipt_number() {
		return ss_receipt_number;
	}
	public void setSs_receipt_number(String ss_receipt_number) {
		this.ss_receipt_number = ss_receipt_number;
	}
	public String getSs_doc1() {
		return ss_doc1;
	}
	public void setSs_doc1(String ss_doc1) {
		this.ss_doc1 = ss_doc1;
	}
	public String getSs_doc2() {
		return ss_doc2;
	}
	public void setSs_doc2(String ss_doc2) {
		this.ss_doc2 = ss_doc2;
	}
	public String getSs_doc3() {
		return ss_doc3;
	}
	public void setSs_doc3(String ss_doc3) {
		this.ss_doc3 = ss_doc3;
	}
	public String getSs_doc4() {
		return ss_doc4;
	}
	public void setSs_doc4(String ss_doc4) {
		this.ss_doc4 = ss_doc4;
	}
	public String getPi_membership_type() {
		return pi_membership_type;
	}
	public void setPi_membership_type(String pi_membership_type) {
		this.pi_membership_type = pi_membership_type;
	}
	public String getPi_employer_business_name() {
		return pi_employer_business_name;
	}
	public void setPi_employer_business_name(String pi_employer_business_name) {
		this.pi_employer_business_name = pi_employer_business_name;
	}
	public String getPi_employee_number() {
		return pi_employee_number;
	}
	public void setPi_employee_number(String pi_employee_number) {
		this.pi_employee_number = pi_employee_number;
	}
	public String getPi_date_start() {
		return pi_date_start;
	}
	public void setPi_date_start(String pi_date_start) {
		this.pi_date_start = pi_date_start;
	}
	public String getPi_profession() {
		return pi_profession;
	}
	public void setPi_profession(String pi_profession) {
		this.pi_profession = pi_profession;
	}
	public String getPi_employment_status() {
		return pi_employment_status;
	}
	public void setPi_employment_status(String pi_employment_status) {
		this.pi_employment_status = pi_employment_status;
	}
	public String getPi_address() {
		return pi_address;
	}
	public void setPi_address(String pi_address) {
		this.pi_address = pi_address;
	}
	public String getPi_basic() {
		return pi_basic;
	}
	public void setPi_basic(String pi_basic) {
		this.pi_basic = pi_basic;
	}
	public String getPi_allowance() {
		return pi_allowance;
	}
	public void setPi_allowance(String pi_allowance) {
		this.pi_allowance = pi_allowance;
	}
	public String getPi_total() {
		return pi_total;
	}
	public void setPi_total(String pi_total) {
		this.pi_total = pi_total;
	}
	public String getPi_doc1() {
		return pi_doc1;
	}
	public void setPi_doc1(String pi_doc1) {
		this.pi_doc1 = pi_doc1;
	}
	public String getPi_doc2() {
		return pi_doc2;
	}
	public void setPi_doc2(String pi_doc2) {
		this.pi_doc2 = pi_doc2;
	}
	public String getPi_doc3() {
		return pi_doc3;
	}
	public void setPi_doc3(String pi_doc3) {
		this.pi_doc3 = pi_doc3;
	}
	public String getPi_doc4() {
		return pi_doc4;
	}
	public void setPi_doc4(String pi_doc4) {
		this.pi_doc4 = pi_doc4;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_est() {
		return company_est;
	}
	public void setCompany_est(String company_est) {
		this.company_est = company_est;
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
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
	
}
