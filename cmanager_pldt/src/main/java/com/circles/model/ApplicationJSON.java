package com.circles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;


public class ApplicationJSON implements Serializable, Cloneable{

	private static final long serialVersionUID = 5142856349857391415L;
	
	public static final int PHONE_RETRIEVAL_TYPE_SEND_TO_MY_ADDRESS = 1;
	public static final int PHONE_RETRIEVAL_TYPE_GET_FROM_BUSINESS_CENTER = 2;

	public static final int PAYMENT_TYPE_PAYPAL = 1;
	public static final int PAYMENT_TYPE_CREDITCARD = 2;
	public static final int PAYMENT_TYPE_COD = 3;
	
	public static final int STATUS_DRAFTS = -1;
	public static final int STATUS_SUBMIITED = 0;
	public static final int STATUS_APPROVED = 1;
	public static final int STATUS_ONGOING = 2;
	public static final int STATUS_DECLINED = 3;
	public static final int STATUS_INCOMPLETE = 4;
	
	
	public static final int SEND_BILL_TO_HOME = 1;
	public static final int SEND_BILL_TO_WORK = 2;

	private String application_id;
	private int status;
	private Hashtable<String,Object> details = new Hashtable<String,Object>();
	private String application_type;
	private long created;
	private long  updated;
	private int  version;
	private int ispushed;
	private String user_id;
	private String company_id;
	private String ref_no;
	
	private int step1done;
	private int step2done;
	private int step3done;
	private int step4done;
	private int step5done;
	
	private String doc_terms_sig1;
	private String doc_terms_sig2;
	private String doc_terms_sig3;
	
	public void setDetails(Hashtable<String,Object> vals)
	{
		this.details = vals;
	}
	
	public static String getStatusString(int stat)
	{
		String stats = "";
		switch(stat)
		{
		case STATUS_SUBMIITED:
				stats = "Submitted";
				break;
		case STATUS_APPROVED:
			stats = "Approved";
			break;
		case STATUS_INCOMPLETE:
			stats = "Incomplete";
			break;
		case STATUS_DECLINED:
			stats = "Declined";
			break;
		case STATUS_ONGOING:
			stats = "Ongoing";
			break;
		}
		return stats;
	}
	
	
	public Hashtable<String,Object> getDetail()
	{
		return details;
	}
	
	public Object getdetails(String key)
	{
		Object obj = null;
		if (details.containsKey(key.toLowerCase()))
		{
			obj = details.get(key.toLowerCase());
		}
		return obj;
	}
	
	public void setDetail(String key, Object detail)
	{
		
		
		if (detail != null)
			details.put(key.toLowerCase(), detail);
		else
		{
			if (details.containsKey(key))
				details.remove(key);
		}
	}
	
	
	public String DetailsToJSON()
	{
		Gson gsonDetail = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		 String json = new Gson().toJson(details);
		 return json;
	}
	
	public String ToJSON()
	{
		Gson gsonDetail = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		 String json = new Gson().toJson(this);
		 return json;
	}
	
	public Object processObject(Object child)
	{
		
		Object objresult = null;

		if (child.getClass().equals(Hashtable.class))
		{
			objresult = PopulateFromJSONObjectHash((Hashtable<String,Object>) child);
		}
		else if (child.getClass().equals(LinkedTreeMap.class))
		{
			objresult = PopulateFromJSONObjectMap((LinkedTreeMap<String, Object>)child);
		}
		else if (child.getClass().equals(ArrayList.class))
		{
			objresult = PopulateFromJSONObjectArray((ArrayList)child);
		}
		else
		{
			objresult = child;
		}
		
		return objresult;
			
	}
	
	public Object PopulateFromJSONObjectArray(ArrayList jobj)
	{
		ArrayList childhash = new ArrayList();
		System.out.println("array" + jobj.getClass().toString());

		
		for(int i=0;i< jobj.size();i++)
		{
			Object objchild = processObject(jobj.get(i));
			childhash.add(objchild);
			
		}
		return childhash;
	}
	
	
	public Object PopulateFromJSONObjectHash(Hashtable<String,Object> jobj)
	{
		Hashtable<String,Object> childhash = new Hashtable<String, Object>();
		System.out.println("hash" + jobj.getClass().toString());
		
		Enumeration<?> keys = jobj.keys();

        while( keys.hasMoreElements() ){
            String childkey = (String)keys.nextElement();
            try {
            	
            	Object objchild = processObject(jobj.get(childkey));
                processParent(childkey,childhash,objchild);
            	
            }
            catch (Exception jex)
            {
            	jex.printStackTrace();
            }
        }
        return childhash;
        
        
	}
	
	public Hashtable<String,Object> processParent(String key, Hashtable<String,Object> parent, Object childobj)
	{
		
		Hashtable<String,Object> newhead = parent;
		Hashtable<String,Object> newparent = newhead;
		
		if (!key.equals(""))
		{
			String[] splitkey = key.split("\\.");
			for(int i =0; i < splitkey.length;i++)
			{
				
				if (splitkey.length-1 == i)
				{
					newparent.put(splitkey[i], childobj);
				}
				else
				{
					if (newparent.containsKey(splitkey[i]))
					{
						
						Object parentobj = newparent.get(splitkey[i]);
						if (parentobj.getClass().equals(Hashtable.class))
						{
							newparent = (Hashtable<String,Object>)  parentobj;
						}
						else
						{
							Hashtable<String,Object> childhash = new Hashtable<String,Object>();
							newparent.put(splitkey[i],childhash);
							newparent = childhash;
						}
					}
					else
					{
						Hashtable<String,Object> childhash = new Hashtable<String,Object>();
						newparent.put(splitkey[i],childhash);
						newparent = childhash;
					}
				}
			}
			
		}
		return newhead;
	}
	
	
	public Object PopulateFromJSONObjectMap(LinkedTreeMap<String,Object> jobj)
	{
		Hashtable<String,Object> childhash = new Hashtable<String, Object>();
		System.out.println("map" + jobj.getClass().toString());

		  for(Map.Entry<String, Object> entry : jobj.entrySet())
		    {
			  String childkey = entry.getKey();
			  Object objchild = processObject(jobj.get(childkey));
              processParent(childkey,childhash,objchild);
		    }
		    return childhash;
        
        
	}
	
	
	
	/*
	
	private String phone_id;
	private String plan_code;

	private String ref_no;
	private String email;
	
	private String title;
	private String birthplace;
	private String occupation;
	private String position_level;
	private String position_title;
	private String tenure;
	private String monthly_income;
	private String company_name;
	private String business_nature;
	private String tin;
	private String sss;
	private String civil_status;
	private String gender;
	private int dependents;
	private String other_phone_subscriptions;
	private String fundssource ;
	private String spouse_firstName;
	private String spouse_middleName;
	private String spouse_lastName;
	private long spouse_birthday;
	private String mothers_maidenname;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private long birthday;
	private int  type;
	private String image;
	private String termination_image;
	private String addRegion;
	private String addCity;
	private String addBrgy;
	private String addLine1;
	private String addLine2;
	private String zipCode;
	
	
	private String addWorkRegion;
	private String addWorkCity;
	private String addWorkBrgy;
	private String addWorkLine1;
	private String addWorkLine2;
	private String workZipCode;
	
	private int sendBillTo;
	
	private String telephone;
	private String mobile;
	private int phone_retrieval_type;
	private String business_center_name;
	private int business_center_lng;
	private int business_center_lat;
	
	private int payment_type;

	private String cc_number;
	private String cc_bank;

	private String pp_email;
	private String pp_refno;

	private String cod_amt;

	private String doc_identity_sss;
	private String doc_identity_sss_no;
	private String doc_identity_pagibig;
	private String doc_identity_pagibig_no;
	private String doc_identity_philhealth;
	private String doc_identity_philhealth_no;
	private String doc_identity_tin;
	private String doc_identity_tin_no;
	private String doc_identity_driverslicense;
	private String doc_identity_driverslicense_no;
	private String doc_identity_passport;
	private String doc_identity_passport_no;
	private String doc_identity_companyid;
	private String doc_identity_companyid_no;
	private String doc_identity_others;
	private String doc_identity_others_no;
	
	private String doc_address_electricity;
	private String doc_address_electricity_no;
	private String doc_address_water;
	private String doc_address_water_no;
	private String doc_address_isp;
	private String doc_address_isp_no;
	private String doc_address_cable;
	private String doc_address_cable_no;
	private String doc_address_telecom;
	private String doc_address_telecom_no;
	private String doc_address_bankloan;
	private String doc_address_bankloan_no;
	private String doc_address_others;
	private String doc_address_others_no;
	
	private String doc_income_bankstatement;
	private String doc_income_bankstatement_no;
	private String doc_income_payslip;
	private String doc_income_payslip_no;
	private String doc_income_security;
	private String doc_income_security_no;
	private String doc_income_bonds;
	private String doc_income_bonds_no;
	private String doc_income_stockcert;
	private String doc_income_stockcert_no;
	private String doc_income_companyownership;
	private String doc_income_companyownership_no;
	private String doc_income_others;
	private String doc_income_others_no;
	
	private String doc_income_autocharge;
	private String doc_income_autocharge_no;
	
	private String doc_terms_sig1;
	private String doc_terms_sig2;
	private String doc_terms_sig3;
	
	private String decline_remarks;
	
	private int step1Done;
	private int step2Done;
	private int step3Done;
	

	private Phone phone;
	private Plan plan;

	
	private int identitystep1;
	private int proofofbillingstep2;
	private int workstep3;
	

	private String editedBy;
	private String created_by;

	private User snapShot;
	
	private String imagetype;
	private String imagrefno;
	
	private int attachaddress;
	private int attachidentity;
	private int attachincome;
	
	private int addressDone;
	private int identityDone;
	private int incomeDone;

	
	private String filename;
	
	
	
	*/
	
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
//	public User getSnapShot() {
//		return snapShot;
//	}
//	public void createSnapShot() throws CloneNotSupportedException {
//		this.snapShot = (User)clone();
//	}
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	}

	
	
	public String getId() {
		return application_id;
	}
	public void setId(String id) {
		this.application_id = id;
	}
	
	
	
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getMiddleName() {
//		return middleName;
//	}
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public long getBirthday() {
//		return birthday;
//	}
//	public void setBirthday(long birthday) {
//		this.birthday = birthday;
//	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	public String getImage() {
//		return image;
//	}
//	public void setImage(String image) {
//		this.image = image;
//	}
//	public String getTermination_image() {
//		return termination_image;
//	}
//	public void setTermination_image(String termination_image) {
//		this.termination_image = termination_image;
//	}
//	public String getAddRegion() {
//		return addRegion;
//	}
//	public void setAddRegion(String addRegion) {
//		this.addRegion = addRegion;
//	}
//	public String getAddCity() {
//		return addCity;
//	}
//	public void setAddCity(String addCity) {
//		this.addCity = addCity;
//	}
//	public String getAddBrgy() {
//		return addBrgy;
//	}
//	public void setAddBrgy(String addBrgy) {
//		this.addBrgy = addBrgy;
//	}
//	public String getAddLine1() {
//		return addLine1;
//	}
//	public void setAddLine1(String add_line1) {
//		this.addLine1 = add_line1;
//	}
//	public String getAddLine2() {
//		return addLine2;
//	}
//	public void setAddLine2(String add_line2) {
//		this.addLine2 = add_line2;
//	}
//	public String getZipCode() {
//		return zipCode;
//	}
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
//	public String getTelephone() {
//		return telephone;
//	}
//	public void setTelephone(String telephone) {
//		this.telephone = telephone;
//	}
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public int getPhone_retrieval_type() {
//		return phone_retrieval_type;
//	}
//	public void setPhone_retrieval_type(int phone_retrieval_type) {
//		this.phone_retrieval_type = phone_retrieval_type;
//	}
//	public int getBusiness_center_lng() {
//		return business_center_lng;
//	}
//	public void setBusiness_center_lng(int business_center_lng) {
//		this.business_center_lng = business_center_lng;
//	}
//	public int getBusiness_center_lat() {
//		return business_center_lat;
//	}
//	public void setBusiness_center_lat(int business_center_lat) {
//		this.business_center_lat = business_center_lat;
//	}
//	public int getPayment_type() {
//		return payment_type;
//	}
//	public void setPayment_type(int payment_type) {
//		this.payment_type = payment_type;
//	}
//	public String getCc_number() {
//		return cc_number;
//	}
//	public void setCc_number(String cc_number) {
//		this.cc_number = cc_number;
//	}
//	public String getCc_bank() {
//		return cc_bank;
//	}
//	public void setCc_bank(String cc_bank) {
//		this.cc_bank = cc_bank;
//	}
//	public String getPp_email() {
//		return pp_email;
//	}
//	public void setPp_email(String pp_email) {
//		this.pp_email = pp_email;
//	}
//	public String getPp_refno() {
//		return pp_refno;
//	}
//	public void setPp_refno(String pp_refno) {
//		this.pp_refno = pp_refno;
//	}
//	public String getCod_amt() {
//		return cod_amt;
//	}
//	public void setCod_amt(String cod_amt) {
//		this.cod_amt = cod_amt;
//	}
//	public String getDoc_identity_sss() {
//		return doc_identity_sss;
//	}
//	public void setDoc_identity_sss(String doc_identity_sss) {
//		this.doc_identity_sss = doc_identity_sss;
//	}
//	public String getDoc_identity_sss_no() {
//		return doc_identity_sss_no;
//	}
//	public void setDoc_identity_sss_no(String doc_identity_sss_no) {
//		this.doc_identity_sss_no = doc_identity_sss_no;
//	}
//	public String getDoc_identity_pagibig() {
//		return doc_identity_pagibig;
//	}
//	public void setDoc_identity_pagibig(String doc_identity_pagibig) {
//		this.doc_identity_pagibig = doc_identity_pagibig;
//	}
//	public String getDoc_identity_pagibig_no() {
//		return doc_identity_pagibig_no;
//	}
//	public void setDoc_identity_pagibig_no(String doc_identity_pagibig_no) {
//		this.doc_identity_pagibig_no = doc_identity_pagibig_no;
//	}
//	public String getDoc_identity_philhealth() {
//		return doc_identity_philhealth;
//	}
//	public void setDoc_identity_philhealth(String doc_identity_philhealth) {
//		this.doc_identity_philhealth = doc_identity_philhealth;
//	}
//	public String getDoc_identity_philhealth_no() {
//		return doc_identity_philhealth_no;
//	}
//	public void setDoc_identity_philhealth_no(String doc_identity_philhealth_no) {
//		this.doc_identity_philhealth_no = doc_identity_philhealth_no;
//	}
//	public String getDoc_identity_tin() {
//		return doc_identity_tin;
//	}
//	public void setDoc_identity_tin(String doc_identity_tin) {
//		this.doc_identity_tin = doc_identity_tin;
//	}
//	public String getDoc_identity_tin_no() {
//		return doc_identity_tin_no;
//	}
//	public void setDoc_identity_tin_no(String doc_identity_tin_no) {
//		this.doc_identity_tin_no = doc_identity_tin_no;
//	}
//	public String getDoc_identity_driverslicense() {
//		return doc_identity_driverslicense;
//	}
//	public void setDoc_identity_driverslicense(String doc_identity_driverslicense) {
//		this.doc_identity_driverslicense = doc_identity_driverslicense;
//	}
//	public String getDoc_identity_driverslicense_no() {
//		return doc_identity_driverslicense_no;
//	}
//	public void setDoc_identity_driverslicense_no(String doc_identity_driverslicense_no) {
//		this.doc_identity_driverslicense_no = doc_identity_driverslicense_no;
//	}
//	public String getDoc_identity_passport() {
//		return doc_identity_passport;
//	}
//	public void setDoc_identity_passport(String doc_identity_passport) {
//		this.doc_identity_passport = doc_identity_passport;
//	}
//	public String getDoc_identity_passport_no() {
//		return doc_identity_passport_no;
//	}
//	public void setDoc_identity_passport_no(String doc_identity_passport_no) {
//		this.doc_identity_passport_no = doc_identity_passport_no;
//	}
//	public String getDoc_identity_companyid() {
//		return doc_identity_companyid;
//	}
//	public void setDoc_identity_companyid(String doc_identity_companyid) {
//		this.doc_identity_companyid = doc_identity_companyid;
//	}
//	public String getDoc_identity_companyid_no() {
//		return doc_identity_companyid_no;
//	}
//	public void setDoc_identity_companyid_no(String doc_identity_companyid_no) {
//		this.doc_identity_companyid_no = doc_identity_companyid_no;
//	}
//	public String getDoc_identity_others() {
//		return doc_identity_others;
//	}
//	public void setDoc_identity_others(String doc_identity_others) {
//		this.doc_identity_others = doc_identity_others;
//	}
//	public String getDoc_identity_others_no() {
//		return doc_identity_others_no;
//	}
//	public void setDoc_identity_others_no(String doc_identity_others_no) {
//		this.doc_identity_others_no = doc_identity_others_no;
//	}
//	public String getEdited_by() {
//		return editedBy;
//	}
//	public void setEdited_by(String edited_by) {
//		this.editedBy = edited_by;
//	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
//	public void setSnapShot(User snapShot) {
//		this.snapShot = snapShot;
//	}
//	public String getDoc_address_electricity() {
//		return doc_address_electricity;
//	}
//	public void setDoc_address_electricity(String doc_address_electricity) {
//		this.doc_address_electricity = doc_address_electricity;
//	}
//	public String getDoc_address_electricity_no() {
//		return doc_address_electricity_no;
//	}
//	public void setDoc_address_electricity_no(String doc_address_electricity_no) {
//		this.doc_address_electricity_no = doc_address_electricity_no;
//	}
//	public String getDoc_address_water() {
//		return doc_address_water;
//	}
//	public void setDoc_address_water(String doc_address_water) {
//		this.doc_address_water = doc_address_water;
//	}
//	public String getDoc_address_water_no() {
//		return doc_address_water_no;
//	}
//	public void setDoc_address_water_no(String doc_address_water_no) {
//		this.doc_address_water_no = doc_address_water_no;
//	}
//	public String getDoc_address_isp() {
//		return doc_address_isp;
//	}
//	public void setDoc_address_isp(String doc_address_isp) {
//		this.doc_address_isp = doc_address_isp;
//	}
//	public String getDoc_address_isp_no() {
//		return doc_address_isp_no;
//	}
//	public void setDoc_address_isp_no(String doc_address_isp_no) {
//		this.doc_address_isp_no = doc_address_isp_no;
//	}
//	public String getDoc_address_cable() {
//		return doc_address_cable;
//	}
//	public void setDoc_address_cable(String doc_address_cable) {
//		this.doc_address_cable = doc_address_cable;
//	}
//	public String getDoc_address_cable_no() {
//		return doc_address_cable_no;
//	}
//	public void setDoc_address_cable_no(String doc_address_cable_no) {
//		this.doc_address_cable_no = doc_address_cable_no;
//	}
//	public String getDoc_address_telecom() {
//		return doc_address_telecom;
//	}
//	public void setDoc_address_telecom(String doc_address_telecom) {
//		this.doc_address_telecom = doc_address_telecom;
//	}
//	public String getDoc_address_telecom_no() {
//		return doc_address_telecom_no;
//	}
//	public void setDoc_address_telecom_no(String doc_address_telecom_no) {
//		this.doc_address_telecom_no = doc_address_telecom_no;
//	}
//	public String getDoc_address_bankloan() {
//		return doc_address_bankloan;
//	}
//	public void setDoc_address_bankloan(String doc_address_bankloan) {
//		this.doc_address_bankloan = doc_address_bankloan;
//	}
//	public String getDoc_address_bankloan_no() {
//		return doc_address_bankloan_no;
//	}
//	public void setDoc_address_bankloan_no(String doc_address_bankloan_no) {
//		this.doc_address_bankloan_no = doc_address_bankloan_no;
//	}
//	public String getDoc_address_others() {
//		return doc_address_others;
//	}
//	public void setDoc_address_others(String doc_address_others) {
//		this.doc_address_others = doc_address_others;
//	}
//	public String getDoc_address_others_no() {
//		return doc_address_others_no;
//	}
//	public void setDoc_address_others_no(String doc_address_others_no) {
//		this.doc_address_others_no = doc_address_others_no;
//	}
//	public String getDoc_income_bankstatement() {
//		return doc_income_bankstatement;
//	}
//	public void setDoc_income_bankstatement(String doc_income_bankstatement) {
//		this.doc_income_bankstatement = doc_income_bankstatement;
//	}
//	public String getDoc_income_bankstatement_no() {
//		return doc_income_bankstatement_no;
//	}
//	public void setDoc_income_bankstatement_no(String doc_income_bankstatement_no) {
//		this.doc_income_bankstatement_no = doc_income_bankstatement_no;
//	}
//	public String getDoc_income_payslip() {
//		return doc_income_payslip;
//	}
//	public void setDoc_income_payslip(String doc_income_payslip) {
//		this.doc_income_payslip = doc_income_payslip;
//	}
//	public String getDoc_income_payslip_no() {
//		return doc_income_payslip_no;
//	}
//	public void setDoc_income_payslip_no(String doc_income_payslip_no) {
//		this.doc_income_payslip_no = doc_income_payslip_no;
//	}
//	public String getDoc_income_security() {
//		return doc_income_security;
//	}
//	public void setDoc_income_security(String doc_income_security) {
//		this.doc_income_security = doc_income_security;
//	}
//	public String getDoc_income_security_no() {
//		return doc_income_security_no;
//	}
//	public void setDoc_income_security_no(String doc_income_security_no) {
//		this.doc_income_security_no = doc_income_security_no;
//	}
//	public String getDoc_income_bonds() {
//		return doc_income_bonds;
//	}
//	public void setDoc_income_bonds(String doc_income_bonds) {
//		this.doc_income_bonds = doc_income_bonds;
//	}
//	public String getDoc_income_bonds_no() {
//		return doc_income_bonds_no;
//	}
//	public void setDoc_income_bonds_no(String doc_income_bonds_no) {
//		this.doc_income_bonds_no = doc_income_bonds_no;
//	}
//	public String getDoc_income_stockcert() {
//		return doc_income_stockcert;
//	}
//	public void setDoc_income_stockcert(String doc_income_stockcert) {
//		this.doc_income_stockcert = doc_income_stockcert;
//	}
//	public String getDoc_income_stockcert_no() {
//		return doc_income_stockcert_no;
//	}
//	public void setDoc_income_stockcert_no(String doc_income_stockcert_no) {
//		this.doc_income_stockcert_no = doc_income_stockcert_no;
//	}
//	public String getDoc_income_companyownership() {
//		return doc_income_companyownership;
//	}
//	public void setDoc_income_companyownership(String doc_income_companyownership) {
//		this.doc_income_companyownership = doc_income_companyownership;
//	}
//	public String getDoc_income_companyownership_no() {
//		return doc_income_companyownership_no;
//	}
//	public void setDoc_income_companyownership_no(String doc_income_companyownership_no) {
//		this.doc_income_companyownership_no = doc_income_companyownership_no;
//	}
//	public String getDoc_income_others() {
//		return doc_income_others;
//	}
//	public void setDoc_income_others(String doc_income_others) {
//		this.doc_income_others = doc_income_others;
//	}
//	public String getDoc_income_others_no() {
//		return doc_income_others_no;
//	}
//	public void setDoc_income_others_no(String doc_income_others_no) {
//		this.doc_income_others_no = doc_income_others_no;
//	}
//	public String getDoc_income_autocharge() {
//		return doc_income_autocharge;
//	}
//	public void setDoc_income_autocharge(String doc_income_autocharge) {
//		this.doc_income_autocharge = doc_income_autocharge;
//	}
//	public String getDoc_income_autocharge_no() {
//		return doc_income_autocharge_no;
//	}
//	public void setDoc_income_autocharge_no(String doc_income_autocharge_no) {
//		this.doc_income_autocharge_no = doc_income_autocharge_no;
//	}
//	public String getDoc_terms_sig1() {
//		return doc_terms_sig1;
//	}
//	public void setDoc_terms_sig1(String doc_terms_sig1) {
//		this.doc_terms_sig1 = doc_terms_sig1;
//	}
//	public String getDoc_terms_sig2() {
//		return doc_terms_sig2;
//	}
//	public void setDoc_terms_sig2(String doc_terms_sig2) {
//		this.doc_terms_sig2 = doc_terms_sig2;
//	}
//	public String getDoc_terms_sig3() {
//		return doc_terms_sig3;
//	}
//	public void setDoc_terms_sig3(String doc_terms_sig3) {
//		this.doc_terms_sig3 = doc_terms_sig3;
//	}
//	public int getStep1Done() {
//		return step1Done;
//	}
//	public void setStep1Done(int step1Done) {
//		this.step1Done = step1Done;
//	}
//	public int getStep2Done() {
//		return step2Done;
//	}
//	public void setStep2Done(int step2Done) {
//		this.step2Done = step2Done;
//	}
//	public int getStep3Done() {
//		return step3Done;
//	}
//	public void setStep3Done(int step3Done) {
//		this.step3Done = step3Done;
//	}
//	public String getPhone_id() {
//		return phone_id;
//	}
//	public void setPhone_id(String phone_id) {
//		this.phone_id = phone_id;
//	}
//	public String getPlan_id() {
//		return plan_code;
//	}
//	public void setPlan_id(String plan_code) {
//		this.plan_code = plan_code;
//	}
	
	
	public String getApplicationType() {
		return application_type;
	}
	public void setApplicationType(String status) {
		this.application_type = status;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
//	public String getRef_no() {
//		return ref_no;
//	}
//	public void setRef_no(String ref_no) {
//		this.ref_no = ref_no;
//	}
//	public String getAddWorkRegion() {
//		return addWorkRegion;
//	}
//	public void setAddWorkRegion(String addWorkRegion) {
//		this.addWorkRegion = addWorkRegion;
//	}
//	public String getAddWorkCity() {
//		return addWorkCity;
//	}
//	public void setAddWorkCity(String addWorkCity) {
//		this.addWorkCity = addWorkCity;
//	}
//	public String getAddWorkBrgy() {
//		return addWorkBrgy;
//	}
//	public void setAddWorkBrgy(String addWorkBrgy) {
//		this.addWorkBrgy = addWorkBrgy;
//	}
//	public String getAddWorkLine1() {
//		return addWorkLine1;
//	}
//	public void setAddWorkLine1(String addWorkLine1) {
//		this.addWorkLine1 = addWorkLine1;
//	}
//	public String getAddWorkLine2() {
//		return addWorkLine2;
//	}
//	public void setAddWorkLine2(String addWorkLine2) {
//		this.addWorkLine2 = addWorkLine2;
//	}
//	public int getSendBillTo() {
//		return sendBillTo;
//	}
//	public void setSendBillTo(int sendBillTo) {
//		this.sendBillTo = sendBillTo;
//	}
//	public String getWorkZipCode() {
//		return workZipCode;
//	}
//	public void setWorkZipCode(String workZipCode) {
//		this.workZipCode = workZipCode;
//	}
//	public String getDecline_remarks() {
//		return decline_remarks;
//	}
//	public void setDecline_remarks(String decline_remarks) {
//		this.decline_remarks = decline_remarks;
//	}
//	public String getBusiness_center_name() {
//		return business_center_name;
//	}
//	public void setBusiness_center_name(String business_center_name) {
//		this.business_center_name = business_center_name;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getBirthplace() {
//		return birthplace;
//	}
//	public void setBirthplace(String birthplace) {
//		this.birthplace = birthplace;
//	}
//	public String getOccupation() {
//		return occupation;
//	}
//	public void setOccupation(String occupation) {
//		this.occupation = occupation;
//	}
//	public String getPosition_level() {
//		return position_level;
//	}
//	public void setPosition_level(String position_level) {
//		this.position_level = position_level;
//	}
//	public String getPosition_title() {
//		return position_title;
//	}
//	public void setPosition_title(String position_title) {
//		this.position_title = position_title;
//	}
//	public String getTenure() {
//		return tenure;
//	}
//	public void setTenure(String tenure) {
//		this.tenure = tenure;
//	}
//	public String getMonthly_income() {
//		return monthly_income;
//	}
//	public void setMonthly_income(String monthly_income) {
//		this.monthly_income = monthly_income;
//	}
//	public String getCompany_name() {
//		return company_name;
//	}
//	public void setCompany_name(String company_name) {
//		this.company_name = company_name;
//	}
//	public String getBusiness_nature() {
//		return business_nature;
//	}
//	public void setBusiness_nature(String business_nature) {
//		this.business_nature = business_nature;
//	}
//	public String getTin() {
//		return tin;
//	}
//	public void setTin(String tin) {
//		this.tin = tin;
//	}
//	public String getSss() {
//		return sss;
//	}
//	public void setSss(String sss) {
//		this.sss = sss;
//	}
//	public String getCivil_status() {
//		return civil_status;
//	}
//	public void setCivil_status(String civil_status) {
//		this.civil_status = civil_status;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public int getDependents() {
//		return dependents;
//	}
//	public void setDependents(int dependents) {
//		this.dependents = dependents;
//	}
//	public String getOther_phone_subscriptions() {
//		return other_phone_subscriptions;
//	}
//	public void setOther_phone_subscriptions(String other_phone_subscriptions) {
//		this.other_phone_subscriptions = other_phone_subscriptions;
//	}
//	public String getFundssource() {
//		return fundssource;
//	}
//	public void setFundssource(String fundssource) {
//		this.fundssource = fundssource;
//	}
//	public String getSpouse_firstName() {
//		return spouse_firstName;
//	}
//	public void setSpouse_firstName(String spouse_firstName) {
//		this.spouse_firstName = spouse_firstName;
//	}
//	public String getSpouse_middleName() {
//		return spouse_middleName;
//	}
//	public void setSpouse_middleName(String spouse_middleName) {
//		this.spouse_middleName = spouse_middleName;
//	}
//	public String getSpouse_lastName() {
//		return spouse_lastName;
//	}
//	public void setSpouse_lastName(String spouse_lastName) {
//		this.spouse_lastName = spouse_lastName;
//	}
//	public long getSpouse_birthday() {
//		return spouse_birthday;
//	}
//	public void setSpouse_birthday(long spouse_birthday) {
//		this.spouse_birthday = spouse_birthday;
//	}
//	public String getMothers_maidenname() {
//		return mothers_maidenname;
//	}
//	public void setMothers_maidenname(String mothers_maidenname) {
//		this.mothers_maidenname = mothers_maidenname;
//	}
	
	
	

//	public Phone getPhone() {
//		return phone;
//	}
//	public void setPhone(Phone phone) {
//		this.phone = phone;
//	}
//	public Plan getPlan() {
//		return plan;
//	}
//	public void setPlan(Plan plan) {
//		this.plan = plan;
//	}
	public int getIspushed() {
		return ispushed;
	}
	public void setIspushed(int ispushed) {
		this.ispushed = ispushed;
	}
//	public String getFilename() {
//		return filename;
//	}
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}
//	public String getCreated_by() {
//		return created_by;
//	}
//	public void setCreated_by(String created_by) {
//		this.created_by = created_by;
//	}
//	public int getIdentitystep1() {
//		return identitystep1;
//	}
//	public void setIdentitystep1(int identitystep1) {
//		this.identitystep1 = identitystep1;
//	}
//	public int getProofofbillingstep2() {
//		return proofofbillingstep2;
//	}
//	public void setProofofbillingstep2(int proofofbillingstep2) {
//		this.proofofbillingstep2 = proofofbillingstep2;
//	}
//	public int getWorkstep3() {
//		return workstep3;
//	}
//	public void setWorkstep3(int workstep3) {
//		this.workstep3 = workstep3;
//	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String comp_id) {
		this.company_id = comp_id;
	}
	
//	public String getImagrefno() {
//		return imagrefno;
//	}
//	public void setImagrefno(String imagrefno) {
//		this.imagrefno = imagrefno;
//	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
//	public String getImagetype() {
//		return imagetype;
//	}
//	public void setImagetype(String imagetype) {
//		this.imagetype = imagetype;
//	}
//	public int getAttachaddress() {
//		return attachaddress;
//	}
//	public void setAttachaddress(int attachaddress) {
//		this.attachaddress = attachaddress;
//	}
//	public int getAttachidentity() {
//		return attachidentity;
//	}
//	public void setAttachidentity(int attachidentity) {
//		this.attachidentity = attachidentity;
//	}
//	public int getAttachincome() {
//		return attachincome;
//	}
//	public void setAttachincome(int attachincome) {
//		this.attachincome = attachincome;
//	}
//	public int getAddressDone() {
//		return addressDone;
//	}
//	public void setAddressDone(int addressDone) {
//		this.addressDone = addressDone;
//	}
//	public int getIdentityDone() {
//		return identityDone;
//	}
//	public void setIdentityDone(int identityDone) {
//		this.identityDone = identityDone;
//	}
//	public int getIncomeDone() {
//		return incomeDone;
//	}
//	public void setIncomeDone(int incomeDone) {
//		this.incomeDone = incomeDone;
//	}
	
	
	public int getStep1done() {
		return step1done;
	}

	public void setStep1done(int step1done) {
		this.step1done = step1done;
	}

	public int getStep2done() {
		return step2done;
	}

	public void setStep2done(int step2done) {
		this.step2done = step2done;
	}

	public int getStep3done() {
		return step3done;
	}

	public void setStep3done(int step3done) {
		this.step3done = step3done;
	}

	public int getStep4done() {
		return step4done;
	}

	public void setStep4done(int step4done) {
		this.step4done = step4done;
	}

	public int getStep5done() {
		return step5done;
	}

	public void setStep5done(int step5done) {
		this.step5done = step5done;
	}



	public String getDoc_terms_sig1() {
		return doc_terms_sig1;
	}

	public void setDoc_terms_sig1(String doc_terms_sig1) {
		this.doc_terms_sig1 = doc_terms_sig1;
	}

	public String getDoc_terms_sig2() {
		return doc_terms_sig2;
	}

	public void setDoc_terms_sig2(String doc_terms_sig2) {
		this.doc_terms_sig2 = doc_terms_sig2;
	}

	public String getDoc_terms_sig3() {
		return doc_terms_sig3;
	}

	public void setDoc_terms_sig3(String doc_terms_sig3) {
		this.doc_terms_sig3 = doc_terms_sig3;
	}

	public String getRef_no() {
		return ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}
}
