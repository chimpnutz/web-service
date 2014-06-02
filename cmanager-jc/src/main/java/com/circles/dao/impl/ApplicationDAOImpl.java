package com.circles.dao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.circles.model.AddressDetails;
import com.circles.model.Application;
import com.circles.model.CompanyDetails;
import com.circles.model.Phone;
import com.circles.model.Plan;
import com.circles.model.SpouseDetails;
import com.google.gson.Gson;

public class ApplicationDAOImpl {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Application application){
		String sql = "INSERT INTO application("
				+ "application_id,"
				+ "title,"
				+ "birthplace,"
				+ "occupation,"
				+ "position_level,"
				+ "position_title,"
				+ "tenure,"
				+ "monthly_income,"
				+ "company_name,"
				+ "business_nature,"
				+ "tin,"
				+ "sss,"
				+ "civil_status,"
				+ "gender,"
				+ "dependents,"
				+ "other_phone_subscriptions,"
				+ "fundssource,"
				+ "spouse_firstName,"
				+ "spouse_middleName,"
				+ "spouse_lastName,"
				+ "spouse_birthday,"
				+ "mothers_maidenname,"
				+ "phone_id,"
				+ "plan_code,"
				+ "status,"
				+ "ref_no,"
				+ "email,"
				+ "firstName,"
				+ "middleName,"
				+ "lastName,"
				+ "birthday,"
				+ "type,"
				+ "image,"
				+ "termination_image,"
				+ "addRegion,"
				+ "addCity,"
				+ "addBrgy,"
				+ "addLine1,"
				+ "addLine2,"
				+ "zipCode,"
				+ "addWorkRegion,"
				+ "addWorkCity,"
				+ "addWorkBrgy,"
				+ "addWorkLine1,"
				+ "addWorkLine2,"
				+ "workZipCode,"
				+ "sendBillTo,"
				+ "telephone,"
				+ "mobile,"
				+ "phone_retrieval_type,"
				+ "business_center_name,"
				+ "business_center_lng,"
				+ "business_center_lat,"
				+ "payment_type,"
				+ "cc_number,"
				+ "cc_bank,"
				+ "pp_email,"
				+ "pp_refno,"
				+ "cod_amt,"
				+ "doc_identity_sss,"
				+ "doc_identity_sss_no,"
				+ "doc_identity_pagibig,"
				+ "doc_identity_pagibig_no,"
				+ "doc_identity_philhealth,"
				+ "doc_identity_philhealth_no,"
				+ "doc_identity_tin,"
				+ "doc_identity_tin_no,"
				+ "doc_identity_driverslicense,"
				+ "doc_identity_driverslicense_no,"
				+ "doc_identity_passport,"
				+ "doc_identity_passport_no,"
				+ "doc_identity_companyid,"
				+ "doc_identity_companyid_no,"
				+ "doc_identity_others,"
				+ "doc_identity_others_no,"
				+ "doc_address_electricity,"
				+ "doc_address_electricity_no,"
				+ "doc_address_water,"
				+ "doc_address_water_no,"
				+ "doc_address_isp,"
				+ "doc_address_isp_no,"
				+ "doc_address_cable,"
				+ "doc_address_cable_no,"
				+ "doc_address_telecom,"
				+ "doc_address_telecom_no,"
				+ "doc_address_bankloan,"
				+ "doc_address_bankloan_no,"
				+ "doc_address_others,"
				+ "doc_address_others_no,"
				+ "doc_income_bankstatement,"
				+ "doc_income_bankstatement_no,"
				+ "doc_income_payslip,"
				+ "doc_income_payslip_no,"
				+ "doc_income_security,"
				+ "doc_income_security_no,"
				+ "doc_income_bonds,"
				+ "doc_income_bonds_no,"
				+ "doc_income_stockcert,"
				+ "doc_income_stockcert_no,"
				+ "doc_income_companyownership,"
				+ "doc_income_companyownership_no,"
				+ "doc_income_others,"
				+ "doc_income_others_no,"
				+ "doc_income_autocharge,"
				+ "doc_income_autocharge_no,"
				+ "doc_terms_sig1,"
				+ "doc_terms_sig2,"
				+ "doc_terms_sig3,"
				+ "step0Done,"
				+ "step1Done,"
				+ "step2Done,"
				+ "step3Done,"
				+ "isaddressDone,"
				+ "isidentityDone,"
				+ "isincomeDone,"
				+ "contact_person,"
				+ "contact_number,"
				+ "decline_remarks,"
				+ "ispushed,"
				+ "edited_by,"
				+ "created,"
				+ "updated,"
				+ "version,"
				+ "application_type,"
				+ "user_id"
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
				+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
				+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
				+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Object[] params = {
					application.getApplication_id(),
					application.getTitle(),
					application.getBirthplace(),
					application.getOccupation(),
					application.getPosition_level(),
					application.getPosition_title(),
					application.getTenure(),
					application.getMonthly_income(),
					application.getCompany_name(),
					application.getBusiness_nature(),
					application.getTin(),
					application.getSss(),
					application.getCivil_status(),
					application.getGender(),
					application.getDependents(),
					application.getOther_phone_subscriptions(),
					application.getFundssource(),
					application.getSpouse_firstName(),
					application.getSpouse_middleName(),
					application.getSpouse_lastName(),
					application.getSpouse_birthday(),
					application.getMothers_maidenname(),
					application.getPhone_id(),
					application.getPlan_code(),
					application.getStatus(),
					application.getRef_no(),
					application.getEmail(),
					application.getFirstName(),
					application.getMiddleName(),
					application.getLastName(),
					application.getBirthday(),
					application.getType(),
					application.getImage(),
					application.getTermination_image(),
					application.getAddRegion(),
					application.getAddCity(),
					application.getAddBrgy(),
					application.getAddLine1(),
					application.getAddLine2(),
					application.getZipCode(),
					application.getAddWorkRegion(),
					application.getAddWorkCity(),
					application.getAddWorkBrgy(),
					application.getAddWorkLine1(),
					application.getAddWorkLine2(),
					application.getWorkZipCode(),
					application.getSendBillTo(),
					application.getTelephone(),
					application.getMobile(),
					application.getPhone_retrieval_type(),
					application.getBusiness_center_name(),
					application.getBusiness_center_lng(),
					application.getBusiness_center_lat(),
					application.getPayment_type(),
					application.getCc_number(),
					application.getCc_bank(),
					application.getPp_email(),
					application.getPp_refNo(),
					application.getCod_amt(),
					application.getDoc_identity_sss(),
					application.getDoc_identity_sss_no(),
					application.getDoc_identity_pagibig(),
					application.getDoc_identity_pagibig_no(),
					application.getDoc_identity_philhealth(),
					application.getDoc_identity_philhealth_no(),
					application.getDoc_identity_tin(),
					application.getDoc_identity_tin_no(),
					application.getDoc_identity_driverslicense(),
					application.getDoc_identity_driverslicense_no(),
					application.getDoc_identity_passport(),
					application.getDoc_identity_passport_no(),
					application.getDoc_identity_companyid(),
					application.getDoc_identity_companyid_no(),
					application.getDoc_identity_others(),
					application.getDoc_identity_others_no(),
					application.getDoc_address_electricity(),
					application.getDoc_address_electricity_no(),
					application.getDoc_address_water(),
					application.getDoc_address_water_no(),
					application.getDoc_address_isp(),
					application.getDoc_address_isp_no(),
					application.getDoc_address_cable(),
					application.getDoc_address_cable_no(),
					application.getDoc_address_telecom(),
					application.getDoc_address_telecom_no(),
					application.getDoc_address_bankloan(),
					application.getDoc_address_bankloan_no(),
					application.getDoc_address_others(),
					application.getDoc_address_others_no(),
					application.getDoc_income_bankstatement(),
					application.getDoc_income_bankstatement_no(),
					application.getDoc_income_payslip(),
					application.getDoc_income_payslip_no(),
					application.getDoc_income_security(),
					application.getDoc_income_security_no(),
					application.getDoc_income_bonds(),
					application.getDoc_income_bonds_no(),
					application.getDoc_income_stockcert(),
					application.getDoc_income_stockcert_no(),
					application.getDoc_income_companyownership(),
					application.getDoc_income_companyownership_no(),
					application.getDoc_income_others(),
					application.getDoc_income_others_no(),
					application.getDoc_income_autocharge(),
					application.getDoc_income_autocharge_no(),
					application.getDoc_terms_sig1(),
					application.getDoc_terms_sig2(),
					application.getDoc_terms_sig3(),
					application.getStep0Done(),
					application.getStep1Done(),
					application.getStep2Done(),
					application.getStep3Done(),
					application.getIsaddressDone(),
					application.getIsidentityDone(),
					application.getIsincomeDone(),
					application.getContact_person(),
					application.getContact_number(),
					application.getDecline_remarks(),
					application.getIspushed(),
					application.getEditedBy(),
					application.getCreated(),
					application.getUpdate(),
					application.getVersion(),
					application.getApplication_type(),
					application.getUser_id()
				};
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR
		};
		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		return isSaved;
	}
	public int update(Application application){
		String sql = "UPDATE application SET dvar=?";
		int iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				sql+=", application_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTitle().isEmpty()||!application.getTitle().equals(null)||!application.getTitle().equals("")){
				sql+=", title=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthplace().isEmpty()||!application.getBirthplace().equals(null)||!application.getBirthplace().equals("")){
				sql+=", birthplace=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOccupation().isEmpty()||!application.getOccupation().equals(null)||!application.getOccupation().equals("")){
				sql+=", occupation=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_level().isEmpty()||!application.getPosition_level().equals(null)||!application.getPosition_level().equals("")){
				sql+=", position_level=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_title().isEmpty()||!application.getPosition_title().equals(null)||!application.getPosition_title().equals("")){
				sql+=", position_title=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTenure().isEmpty()||!application.getTenure().equals(null)||!application.getTenure().equals("")){
				sql+=", tenure=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMonthly_income().isEmpty()||!application.getMonthly_income().equals(null)||!application.getMonthly_income().equals("")){
				sql+=", monthly_income=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCompany_name().isEmpty()||!application.getCompany_name().equals(null)||!application.getCompany_name().equals("")){
				sql+=", company_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_nature().isEmpty()||!application.getBusiness_nature().equals(null)||!application.getBusiness_nature().equals("")){
				sql+=", business_nature=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTin().isEmpty()||!application.getTin().equals(null)||!application.getTin().equals("")){
				sql+=", tin=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSss().isEmpty()||!application.getSss().equals(null)||!application.getSss().equals("")){
				sql+=", sss=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCivil_status().isEmpty()||!application.getCivil_status().equals(null)||!application.getCivil_status().equals("")){
				sql+=", civil_status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getGender().isEmpty()||!application.getGender().equals(null)||!application.getGender().equals("")){
				sql+=", gender=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDependents().isEmpty()||!application.getDependents().equals(null)||!application.getDependents().equals("")){
				sql+=", dependents=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOther_phone_subscriptions().isEmpty()||!application.getOther_phone_subscriptions().equals(null)||!application.getOther_phone_subscriptions().equals("")){
				sql+=", other_phone_subscriptions=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFundssource().isEmpty()||!application.getFundssource().equals(null)||!application.getFundssource().equals("")){
				sql+=", fundssource=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_firstName().isEmpty()||!application.getSpouse_firstName().equals(null)||!application.getSpouse_firstName().equals("")){
				sql+=", spouse_firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_middleName().isEmpty()||!application.getSpouse_middleName().equals(null)||!application.getSpouse_middleName().equals("")){
				sql+=", spouse_middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_lastName().isEmpty()||!application.getSpouse_lastName().equals(null)||!application.getSpouse_lastName().equals("")){
				sql+=", spouse_lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_birthday().isEmpty()||!application.getSpouse_birthday().equals(null)||!application.getSpouse_birthday().equals("")){
				sql+=", spouse_birthday=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMothers_maidenname().isEmpty()||!application.getMothers_maidenname().equals(null)||!application.getMothers_maidenname().equals("")){
				sql+=", mothers_maidenname=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_id().isEmpty()||!application.getPhone_id().equals(null)||!application.getPhone_id().equals("")){
				sql+=", phone_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPlan_code().isEmpty()||!application.getPlan_code().equals(null)||!application.getPlan_code().equals("")){
				sql+=", plan_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				sql+=", status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getRef_no().isEmpty()||!application.getRef_no().equals(null)||!application.getRef_no().equals("")){
				sql+=", ref_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEmail().isEmpty()||!application.getEmail().equals(null)||!application.getEmail().equals("")){
				sql+=", email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFirstName().isEmpty()||!application.getFirstName().equals(null)||!application.getFirstName().equals("")){
				sql+=", firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMiddleName().isEmpty()||!application.getMiddleName().equals(null)||!application.getMiddleName().equals("")){
				sql+=", middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getLastName().isEmpty()||!application.getLastName().equals(null)||!application.getLastName().equals("")){
				sql+=", lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthday().isEmpty()||!application.getBirthday().equals(null)||!application.getBirthday().equals("")){
				sql+=", birthday=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getType().isEmpty()||!application.getType().equals(null)||!application.getType().equals("")){
				sql+=", type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getImage().isEmpty()||!application.getImage().equals(null)||!application.getImage().equals("")){
				sql+=", image=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTermination_image().isEmpty()||!application.getTermination_image().equals(null)||!application.getTermination_image().equals("")){
				sql+=", termination_image=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddRegion().isEmpty()||!application.getAddRegion().equals(null)||!application.getAddRegion().equals("")){
				sql+=", addRegion=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddCity().isEmpty()||!application.getAddCity().equals(null)||!application.getAddCity().equals("")){
				sql+=", addCity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddBrgy().isEmpty()||!application.getAddBrgy().equals(null)||!application.getAddBrgy().equals("")){
				sql+=", addBrgy=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine1().isEmpty()||!application.getAddLine1().equals(null)||!application.getAddLine1().equals("")){
				sql+=", addLine1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine2().isEmpty()||!application.getAddLine2().equals(null)||!application.getAddLine2().equals("")){
				sql+=", addLine2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getZipCode().isEmpty()||!application.getZipCode().equals(null)||!application.getZipCode().equals("")){
				sql+=", zipCode=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkRegion().isEmpty()||!application.getAddWorkRegion().equals(null)||!application.getAddWorkRegion().equals("")){
				sql+=", addWorkRegion=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkCity().isEmpty()||!application.getAddWorkCity().equals(null)||!application.getAddWorkCity().equals("")){
				sql+=", addWorkCity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkBrgy().isEmpty()||!application.getAddWorkBrgy().equals(null)||!application.getAddWorkBrgy().equals("")){
				sql+=", addWorkBrgy=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine1().isEmpty()||!application.getAddWorkLine1().equals(null)||!application.getAddWorkLine1().equals("")){
				sql+=", addWorkLine1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine2().isEmpty()||!application.getAddWorkLine2().equals(null)||!application.getAddWorkLine2().equals("")){
				sql+=", addWorkLine2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getWorkZipCode().isEmpty()||!application.getWorkZipCode().equals(null)||!application.getWorkZipCode().equals("")){
				sql+=", workZipCode=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSendBillTo().isEmpty()||!application.getSendBillTo().equals(null)||!application.getSendBillTo().equals("")){
				sql+=", sendBillTo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTelephone().isEmpty()||!application.getTelephone().equals(null)||!application.getTelephone().equals("")){
				sql+=", telephone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMobile().isEmpty()||!application.getMobile().equals(null)||!application.getMobile().equals("")){
				sql+=", mobile=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_retrieval_type().isEmpty()||!application.getPhone_retrieval_type().equals(null)||!application.getPhone_retrieval_type().equals("")){
				sql+=", phone_retrieval_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_name().isEmpty()||!application.getBusiness_center_name().equals(null)||!application.getBusiness_center_name().equals("")){
				sql+=", business_center_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lng().isEmpty()||!application.getBusiness_center_lng().equals(null)||!application.getBusiness_center_lng().equals("")){
				sql+=", business_center_lng=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lat().isEmpty()||!application.getBusiness_center_lat().equals(null)||!application.getBusiness_center_lat().equals("")){
				sql+=", business_center_lat=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPayment_type().isEmpty()||!application.getPayment_type().equals(null)||!application.getPayment_type().equals("")){
				sql+=", payment_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_number().isEmpty()||!application.getCc_number().equals(null)||!application.getCc_number().equals("")){
				sql+=", cc_number=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_bank().isEmpty()||!application.getCc_bank().equals(null)||!application.getCc_bank().equals("")){
				sql+=", cc_bank=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_email().isEmpty()||!application.getPp_email().equals(null)||!application.getPp_email().equals("")){
				sql+=", pp_email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_refNo().isEmpty()||!application.getPp_refNo().equals(null)||!application.getPp_refNo().equals("")){
				sql+=", pp_refNo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCod_amt().isEmpty()||!application.getCod_amt().equals(null)||!application.getCod_amt().equals("")){
				sql+=", cod_amt=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss().isEmpty()||!application.getDoc_identity_sss().equals(null)||!application.getDoc_identity_sss().equals("")){
				sql+=", doc_identity_sss=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss_no().isEmpty()||!application.getDoc_identity_sss_no().equals(null)||!application.getDoc_identity_sss_no().equals("")){
				sql+=", pp_refNo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig().isEmpty()||!application.getDoc_identity_pagibig().equals(null)||!application.getDoc_identity_pagibig().equals("")){
				sql+=", doc_identity_pagibig=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig_no().isEmpty()||!application.getDoc_identity_pagibig_no().equals(null)||!application.getDoc_identity_pagibig_no().equals("")){
				sql+=", doc_identity_pagibig_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth().isEmpty()||!application.getDoc_identity_philhealth().equals(null)||!application.getDoc_identity_philhealth().equals("")){
				sql+=", doc_identity_philhealth=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth_no().isEmpty()||!application.getDoc_identity_philhealth_no().equals(null)||!application.getDoc_identity_philhealth_no().equals("")){
				sql+=", doc_identity_philhealth_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin().isEmpty()||!application.getDoc_identity_tin().equals(null)||!application.getDoc_identity_tin().equals("")){
				sql+=", doc_identity_tin=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin_no().isEmpty()||!application.getDoc_identity_tin_no().equals(null)||!application.getDoc_identity_tin_no().equals("")){
				sql+=", doc_identity_tin_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense().isEmpty()||!application.getDoc_identity_driverslicense().equals(null)||!application.getDoc_identity_driverslicense().equals("")){
				sql+=", doc_identity_driverslicense=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense_no().isEmpty()||!application.getDoc_identity_driverslicense_no().equals(null)||!application.getDoc_identity_driverslicense_no().equals("")){
				sql+=", doc_identity_driverslicense_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport().isEmpty()||!application.getDoc_identity_passport().equals(null)||!application.getDoc_identity_passport().equals("")){
				sql+=", doc_identity_passport=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport_no().isEmpty()||!application.getDoc_identity_passport_no().equals(null)||!application.getDoc_identity_passport_no().equals("")){
				sql+=", doc_identity_passport_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid().isEmpty()||!application.getDoc_identity_companyid().equals(null)||!application.getDoc_identity_companyid().equals("")){
				sql+=", doc_identity_companyid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid_no().isEmpty()||!application.getDoc_identity_companyid_no().equals(null)||!application.getDoc_identity_companyid_no().equals("")){
				sql+=", doc_identity_companyid_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others().isEmpty()||!application.getDoc_identity_others().equals(null)||!application.getDoc_identity_others().equals("")){
				sql+=", doc_identity_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others_no().isEmpty()||!application.getDoc_identity_others_no().equals(null)||!application.getDoc_identity_others_no().equals("")){
				sql+=", doc_identity_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity().isEmpty()||!application.getDoc_address_electricity().equals(null)||!application.getDoc_address_electricity().equals("")){
				sql+=", doc_address_electricity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity_no().isEmpty()||!application.getDoc_address_electricity_no().equals(null)||!application.getDoc_address_electricity_no().equals("")){
				sql+=", doc_address_electricity_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water().isEmpty()||!application.getDoc_address_water().equals(null)||!application.getDoc_address_water().equals("")){
				sql+=", doc_address_water=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water_no().isEmpty()||!application.getDoc_address_water_no().equals(null)||!application.getDoc_address_water_no().equals("")){
				sql+=", doc_address_water_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp().isEmpty()||!application.getDoc_address_isp().equals(null)||!application.getDoc_address_isp().equals("")){
				sql+=", doc_address_isp=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp_no().isEmpty()||!application.getDoc_address_isp_no().equals(null)||!application.getDoc_address_isp_no().equals("")){
				sql+=", doc_address_isp_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable().isEmpty()||!application.getDoc_address_cable().equals(null)||!application.getDoc_address_cable().equals("")){
				sql+=", doc_address_cable=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable_no().isEmpty()||!application.getDoc_address_cable_no().equals(null)||!application.getDoc_address_cable_no().equals("")){
				sql+=", doc_address_cable_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom().isEmpty()||!application.getDoc_address_telecom().equals(null)||!application.getDoc_address_telecom().equals("")){
				sql+=", doc_address_telecom=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom_no().isEmpty()||!application.getDoc_address_telecom_no().equals(null)||!application.getDoc_address_telecom_no().equals("")){
				sql+=", doc_address_telecom_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan().isEmpty()||!application.getDoc_address_bankloan().equals(null)||!application.getDoc_address_bankloan().equals("")){
				sql+=", doc_address_bankloan=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan_no().isEmpty()||!application.getDoc_address_bankloan_no().equals(null)||!application.getDoc_address_bankloan_no().equals("")){
				sql+=", doc_address_bankloan_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others().isEmpty()||!application.getDoc_address_others().equals(null)||!application.getDoc_address_others().equals("")){
				sql+=", doc_address_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others_no().isEmpty()||!application.getDoc_address_others_no().equals(null)||!application.getDoc_address_others_no().equals("")){
				sql+=", doc_address_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement().isEmpty()||!application.getDoc_income_bankstatement().equals(null)||!application.getDoc_income_bankstatement().equals("")){
				sql+=", doc_income_bankstatement=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement_no().isEmpty()||!application.getDoc_income_bankstatement_no().equals(null)||!application.getDoc_income_bankstatement_no().equals("")){
				sql+=", doc_income_bankstatement_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip().isEmpty()||!application.getDoc_income_payslip().equals(null)||!application.getDoc_income_payslip().equals("")){
				sql+=", doc_income_payslip=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip_no().isEmpty()||!application.getDoc_income_payslip_no().equals(null)||!application.getDoc_income_payslip_no().equals("")){
				sql+=", doc_income_payslip_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security().isEmpty()||!application.getDoc_income_security().equals(null)||!application.getDoc_income_security().equals("")){
				sql+=", doc_income_security=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security_no().isEmpty()||!application.getDoc_income_security_no().equals(null)||!application.getDoc_income_security_no().equals("")){
				sql+=", doc_income_security_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds().isEmpty()||!application.getDoc_income_bonds().equals(null)||!application.getDoc_income_bonds().equals("")){
				sql+=", doc_income_bonds=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds_no().isEmpty()||!application.getDoc_income_bonds_no().equals(null)||!application.getDoc_income_bonds_no().equals("")){
				sql+=", doc_income_bonds_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert().isEmpty()||!application.getDoc_income_stockcert().equals(null)||!application.getDoc_income_stockcert().equals("")){
				sql+=", doc_income_stockcert=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert_no().isEmpty()||!application.getDoc_income_stockcert_no().equals(null)||!application.getDoc_income_stockcert_no().equals("")){
				sql+=", doc_income_stockcert_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership().isEmpty()||!application.getDoc_income_companyownership().equals(null)||!application.getDoc_income_companyownership().equals("")){
				sql+=", doc_income_companyownership=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership_no().isEmpty()||!application.getDoc_income_companyownership_no().equals(null)||!application.getDoc_income_companyownership_no().equals("")){
				sql+=", doc_income_companyownership_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others().isEmpty()||!application.getDoc_income_others().equals(null)||!application.getDoc_income_others().equals("")){
				sql+=", doc_income_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others_no().isEmpty()||!application.getDoc_income_others_no().equals(null)||!application.getDoc_income_others_no().equals("")){
				sql+=", doc_income_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge().isEmpty()||!application.getDoc_income_autocharge().equals(null)||!application.getDoc_income_autocharge().equals("")){
				sql+=", doc_income_autocharge=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge_no().isEmpty()||!application.getDoc_income_autocharge_no().equals(null)||!application.getDoc_income_autocharge_no().equals("")){
				sql+=", doc_income_autocharge_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig1().isEmpty()||!application.getDoc_terms_sig1().equals(null)||!application.getDoc_terms_sig1().equals("")){
				sql+=", doc_terms_sig1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig2().isEmpty()||!application.getDoc_terms_sig2().equals(null)||!application.getDoc_terms_sig2().equals("")){
				sql+=", doc_terms_sig2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig3().isEmpty()||!application.getDoc_terms_sig3().equals(null)||!application.getDoc_terms_sig3().equals("")){
				sql+=", doc_terms_sig3=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep0Done().isEmpty()||!application.getStep0Done().equals(null)||!application.getStep0Done().equals("")){
				sql+=", step0Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep1Done().isEmpty()||!application.getStep1Done().equals(null)||!application.getStep1Done().equals("")){
				sql+=", step1Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep2Done().isEmpty()||!application.getStep2Done().equals(null)||!application.getStep2Done().equals("")){
				sql+=", step2Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep3Done().isEmpty()||!application.getStep3Done().equals(null)||!application.getStep3Done().equals("")){
				sql+=", step3Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsaddressDone().isEmpty()||!application.getIsaddressDone().equals(null)||!application.getIsaddressDone().equals("")){
				sql+=", isaddressDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsidentityDone().isEmpty()||!application.getIsidentityDone().equals(null)||!application.getIsidentityDone().equals("")){
				sql+=", isidentityDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsincomeDone().isEmpty()||!application.getIsincomeDone().equals(null)||!application.getIsincomeDone().equals("")){
				sql+=", isincomeDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_person().isEmpty()||!application.getContact_person().equals(null)||!application.getContact_person().equals("")){
				sql+=", contact_person=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_number().isEmpty()||!application.getContact_number().equals(null)||!application.getContact_number().equals("")){
				sql+=", contact_number=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDecline_remarks().isEmpty()||!application.getDecline_remarks().equals(null)||!application.getDecline_remarks().equals("")){
				sql+=", decline_remarks=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEditedBy().isEmpty()||!application.getEditedBy().equals(null)||!application.getEditedBy().equals("")){
				sql+=", edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdate().isEmpty()||!application.getUpdate().equals(null)||!application.getUpdate().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				sql+=", application_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		sql+=" WHERE application_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = application.getApplication_id();
		iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				params[iterator] = application.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTitle().isEmpty()||!application.getTitle().equals(null)||!application.getTitle().equals("")){
				params[iterator] = application.getTitle();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthplace().isEmpty()||!application.getBirthplace().equals(null)||!application.getBirthplace().equals("")){
				params[iterator] = application.getBirthplace();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOccupation().isEmpty()||!application.getOccupation().equals(null)||!application.getOccupation().equals("")){
				params[iterator] = application.getOccupation();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_level().isEmpty()||!application.getPosition_level().equals(null)||!application.getPosition_level().equals("")){
				params[iterator] = application.getPosition_level();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_title().isEmpty()||!application.getPosition_title().equals(null)||!application.getPosition_title().equals("")){
				params[iterator] = application.getPosition_title();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTenure().isEmpty()||!application.getTenure().equals(null)||!application.getTenure().equals("")){
				params[iterator] = application.getTenure();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMonthly_income().isEmpty()||!application.getMonthly_income().equals(null)||!application.getMonthly_income().equals("")){
				params[iterator] = application.getMonthly_income();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCompany_name().isEmpty()||!application.getCompany_name().equals(null)||!application.getCompany_name().equals("")){
				params[iterator] = application.getCompany_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_nature().isEmpty()||!application.getBusiness_nature().equals(null)||!application.getBusiness_nature().equals("")){
				params[iterator] = application.getBusiness_nature();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTin().isEmpty()||!application.getTin().equals(null)||!application.getTin().equals("")){
				params[iterator] = application.getTin();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSss().isEmpty()||!application.getSss().equals(null)||!application.getSss().equals("")){
				params[iterator] = application.getSss();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCivil_status().isEmpty()||!application.getCivil_status().equals(null)||!application.getCivil_status().equals("")){
				params[iterator] = application.getCivil_status();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getGender().isEmpty()||!application.getGender().equals(null)||!application.getGender().equals("")){
				params[iterator] = application.getGender();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDependents().isEmpty()||!application.getDependents().equals(null)||!application.getDependents().equals("")){
				params[iterator] = application.getDependents();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOther_phone_subscriptions().isEmpty()||!application.getOther_phone_subscriptions().equals(null)||!application.getOther_phone_subscriptions().equals("")){
				params[iterator] = application.getOther_phone_subscriptions();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFundssource().isEmpty()||!application.getFundssource().equals(null)||!application.getFundssource().equals("")){
				params[iterator] = application.getFundssource();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_firstName().isEmpty()||!application.getSpouse_firstName().equals(null)||!application.getSpouse_firstName().equals("")){
				params[iterator] = application.getSpouse_firstName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_middleName().isEmpty()||!application.getSpouse_middleName().equals(null)||!application.getSpouse_middleName().equals("")){
				params[iterator] = application.getSpouse_middleName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_lastName().isEmpty()||!application.getSpouse_lastName().equals(null)||!application.getSpouse_lastName().equals("")){
				params[iterator] = application.getSpouse_lastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_birthday().isEmpty()||!application.getSpouse_birthday().equals(null)||!application.getSpouse_birthday().equals("")){
				params[iterator] = application.getSpouse_birthday();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMothers_maidenname().isEmpty()||!application.getMothers_maidenname().equals(null)||!application.getMothers_maidenname().equals("")){
				params[iterator] = application.getMothers_maidenname();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_id().isEmpty()||!application.getPhone_id().equals(null)||!application.getPhone_id().equals("")){
				params[iterator] = application.getPhone_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPlan_code().isEmpty()||!application.getPlan_code().equals(null)||!application.getPlan_code().equals("")){
				params[iterator] = application.getPlan_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				params[iterator] = application.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getRef_no().isEmpty()||!application.getRef_no().equals(null)||!application.getRef_no().equals("")){
				params[iterator] = application.getRef_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEmail().isEmpty()||!application.getEmail().equals(null)||!application.getEmail().equals("")){
				params[iterator] = application.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFirstName().isEmpty()||!application.getFirstName().equals(null)||!application.getFirstName().equals("")){
				params[iterator] = application.getFirstName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMiddleName().isEmpty()||!application.getMiddleName().equals(null)||!application.getMiddleName().equals("")){
				params[iterator] = application.getMiddleName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getLastName().isEmpty()||!application.getLastName().equals(null)||!application.getLastName().equals("")){
				params[iterator] = application.getLastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthday().isEmpty()||!application.getBirthday().equals(null)||!application.getBirthday().equals("")){
				params[iterator] = application.getBirthday();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getType().isEmpty()||!application.getType().equals(null)||!application.getType().equals("")){
				params[iterator] = application.getType();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getImage().isEmpty()||!application.getImage().equals(null)||!application.getImage().equals("")){
				params[iterator] = application.getImage();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTermination_image().isEmpty()||!application.getTermination_image().equals(null)||!application.getTermination_image().equals("")){
				params[iterator] = application.getTermination_image();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddRegion().isEmpty()||!application.getAddRegion().equals(null)||!application.getAddRegion().equals("")){
				params[iterator] = application.getAddRegion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddCity().isEmpty()||!application.getAddCity().equals(null)||!application.getAddCity().equals("")){
				params[iterator] = application.getAddCity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddBrgy().isEmpty()||!application.getAddBrgy().equals(null)||!application.getAddBrgy().equals("")){
				params[iterator] = application.getAddBrgy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine1().isEmpty()||!application.getAddLine1().equals(null)||!application.getAddLine1().equals("")){
				params[iterator] = application.getAddLine1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine2().isEmpty()||!application.getAddLine2().equals(null)||!application.getAddLine2().equals("")){
				params[iterator] = application.getAddLine2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getZipCode().isEmpty()||!application.getZipCode().equals(null)||!application.getZipCode().equals("")){
				params[iterator] = application.getZipCode();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkRegion().isEmpty()||!application.getAddWorkRegion().equals(null)||!application.getAddWorkRegion().equals("")){
				params[iterator] = application.getAddWorkRegion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkCity().isEmpty()||!application.getAddWorkCity().equals(null)||!application.getAddWorkCity().equals("")){
				params[iterator] = application.getAddWorkCity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkBrgy().isEmpty()||!application.getAddWorkBrgy().equals(null)||!application.getAddWorkBrgy().equals("")){
				params[iterator] = application.getAddWorkBrgy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine1().isEmpty()||!application.getAddWorkLine1().equals(null)||!application.getAddWorkLine1().equals("")){
				params[iterator] = application.getAddWorkLine1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine2().isEmpty()||!application.getAddWorkLine2().equals(null)||!application.getAddWorkLine2().equals("")){
				params[iterator] = application.getAddWorkLine2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getWorkZipCode().isEmpty()||!application.getWorkZipCode().equals(null)||!application.getWorkZipCode().equals("")){
				params[iterator] = application.getWorkZipCode();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSendBillTo().isEmpty()||!application.getSendBillTo().equals(null)||!application.getSendBillTo().equals("")){
				params[iterator] = application.getSendBillTo();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTelephone().isEmpty()||!application.getTelephone().equals(null)||!application.getTelephone().equals("")){
				params[iterator] = application.getTelephone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMobile().isEmpty()||!application.getMobile().equals(null)||!application.getMobile().equals("")){
				params[iterator] = application.getMobile();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_retrieval_type().isEmpty()||!application.getPhone_retrieval_type().equals(null)||!application.getPhone_retrieval_type().equals("")){
				params[iterator] = application.getPhone_retrieval_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_name().isEmpty()||!application.getBusiness_center_name().equals(null)||!application.getBusiness_center_name().equals("")){
				params[iterator] = application.getBusiness_center_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lng().isEmpty()||!application.getBusiness_center_lng().equals(null)||!application.getBusiness_center_lng().equals("")){
				params[iterator] = application.getBusiness_center_lng();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lat().isEmpty()||!application.getBusiness_center_lat().equals(null)||!application.getBusiness_center_lat().equals("")){
				params[iterator] = application.getBusiness_center_lat();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPayment_type().isEmpty()||!application.getPayment_type().equals(null)||!application.getPayment_type().equals("")){
				params[iterator] = application.getPayment_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_number().isEmpty()||!application.getCc_number().equals(null)||!application.getCc_number().equals("")){
				params[iterator] = application.getPayment_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_bank().isEmpty()||!application.getCc_bank().equals(null)||!application.getCc_bank().equals("")){
				params[iterator] = application.getCc_bank();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_email().isEmpty()||!application.getPp_email().equals(null)||!application.getPp_email().equals("")){
				params[iterator] = application.getPp_email();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_refNo().isEmpty()||!application.getPp_refNo().equals(null)||!application.getPp_refNo().equals("")){
				params[iterator] = application.getPp_refNo();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCod_amt().isEmpty()||!application.getCod_amt().equals(null)||!application.getCod_amt().equals("")){
				params[iterator] = application.getCod_amt();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss().isEmpty()||!application.getDoc_identity_sss().equals(null)||!application.getDoc_identity_sss().equals("")){
				params[iterator] = application.getDoc_identity_sss();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss_no().isEmpty()||!application.getDoc_identity_sss_no().equals(null)||!application.getDoc_identity_sss_no().equals("")){
				params[iterator] = application.getDoc_identity_sss_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig().isEmpty()||!application.getDoc_identity_pagibig().equals(null)||!application.getDoc_identity_pagibig().equals("")){
				params[iterator] = application.getDoc_identity_pagibig();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig_no().isEmpty()||!application.getDoc_identity_pagibig_no().equals(null)||!application.getDoc_identity_pagibig_no().equals("")){
				params[iterator] = application.getDoc_identity_pagibig_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth().isEmpty()||!application.getDoc_identity_philhealth().equals(null)||!application.getDoc_identity_philhealth().equals("")){
				params[iterator] = application.getDoc_identity_philhealth();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth_no().isEmpty()||!application.getDoc_identity_philhealth_no().equals(null)||!application.getDoc_identity_philhealth_no().equals("")){
				params[iterator] = application.getDoc_identity_philhealth_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin().isEmpty()||!application.getDoc_identity_tin().equals(null)||!application.getDoc_identity_tin().equals("")){
				params[iterator] = application.getDoc_identity_tin();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin_no().isEmpty()||!application.getDoc_identity_tin_no().equals(null)||!application.getDoc_identity_tin_no().equals("")){
				params[iterator] = application.getDoc_identity_tin_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense().isEmpty()||!application.getDoc_identity_driverslicense().equals(null)||!application.getDoc_identity_driverslicense().equals("")){
				params[iterator] = application.getDoc_identity_driverslicense();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense_no().isEmpty()||!application.getDoc_identity_driverslicense_no().equals(null)||!application.getDoc_identity_driverslicense_no().equals("")){
				params[iterator] = application.getDoc_identity_driverslicense_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport().isEmpty()||!application.getDoc_identity_passport().equals(null)||!application.getDoc_identity_passport().equals("")){
				params[iterator] = application.getDoc_identity_passport();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport_no().isEmpty()||!application.getDoc_identity_passport_no().equals(null)||!application.getDoc_identity_passport_no().equals("")){
				params[iterator] = application.getDoc_identity_passport_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid().isEmpty()||!application.getDoc_identity_companyid().equals(null)||!application.getDoc_identity_companyid().equals("")){
				params[iterator] = application.getDoc_identity_companyid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid_no().isEmpty()||!application.getDoc_identity_companyid_no().equals(null)||!application.getDoc_identity_companyid_no().equals("")){
				params[iterator] = application.getDoc_identity_companyid_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others().isEmpty()||!application.getDoc_identity_others().equals(null)||!application.getDoc_identity_others().equals("")){
				params[iterator] = application.getDoc_identity_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others_no().isEmpty()||!application.getDoc_identity_others_no().equals(null)||!application.getDoc_identity_others_no().equals("")){
				params[iterator] = application.getDoc_identity_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity().isEmpty()||!application.getDoc_address_electricity().equals(null)||!application.getDoc_address_electricity().equals("")){
				params[iterator] = application.getDoc_address_electricity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity_no().isEmpty()||!application.getDoc_address_electricity_no().equals(null)||!application.getDoc_address_electricity_no().equals("")){
				params[iterator] = application.getDoc_address_electricity_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water().isEmpty()||!application.getDoc_address_water().equals(null)||!application.getDoc_address_water().equals("")){
				params[iterator] = application.getDoc_address_water();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water_no().isEmpty()||!application.getDoc_address_water_no().equals(null)||!application.getDoc_address_water_no().equals("")){
				params[iterator] = application.getDoc_address_water_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp().isEmpty()||!application.getDoc_address_isp().equals(null)||!application.getDoc_address_isp().equals("")){
				params[iterator] = application.getDoc_address_isp();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp_no().isEmpty()||!application.getDoc_address_isp_no().equals(null)||!application.getDoc_address_isp_no().equals("")){
				params[iterator] = application.getDoc_address_isp_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable().isEmpty()||!application.getDoc_address_cable().equals(null)||!application.getDoc_address_cable().equals("")){
				params[iterator] = application.getDoc_address_cable();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable_no().isEmpty()||!application.getDoc_address_cable_no().equals(null)||!application.getDoc_address_cable_no().equals("")){
				params[iterator] = application.getDoc_address_cable_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom().isEmpty()||!application.getDoc_address_telecom().equals(null)||!application.getDoc_address_telecom().equals("")){
				params[iterator] = application.getDoc_address_telecom();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom_no().isEmpty()||!application.getDoc_address_telecom_no().equals(null)||!application.getDoc_address_telecom_no().equals("")){
				params[iterator] = application.getDoc_address_telecom_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan().isEmpty()||!application.getDoc_address_bankloan().equals(null)||!application.getDoc_address_bankloan().equals("")){
				params[iterator] = application.getDoc_address_bankloan();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan_no().isEmpty()||!application.getDoc_address_bankloan_no().equals(null)||!application.getDoc_address_bankloan_no().equals("")){
				params[iterator] = application.getDoc_address_bankloan_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others().isEmpty()||!application.getDoc_address_others().equals(null)||!application.getDoc_address_others().equals("")){
				params[iterator] = application.getDoc_address_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others_no().isEmpty()||!application.getDoc_address_others_no().equals(null)||!application.getDoc_address_others_no().equals("")){
				params[iterator] = application.getDoc_address_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement().isEmpty()||!application.getDoc_income_bankstatement().equals(null)||!application.getDoc_income_bankstatement().equals("")){
				params[iterator] = application.getDoc_income_bankstatement();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement_no().isEmpty()||!application.getDoc_income_bankstatement_no().equals(null)||!application.getDoc_income_bankstatement_no().equals("")){
				params[iterator] = application.getDoc_income_bankstatement_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip().isEmpty()||!application.getDoc_income_payslip().equals(null)||!application.getDoc_income_payslip().equals("")){
				params[iterator] = application.getDoc_income_payslip();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip_no().isEmpty()||!application.getDoc_income_payslip_no().equals(null)||!application.getDoc_income_payslip_no().equals("")){
				params[iterator] = application.getDoc_income_payslip_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security().isEmpty()||!application.getDoc_income_security().equals(null)||!application.getDoc_income_security().equals("")){
				params[iterator] = application.getDoc_income_security();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security_no().isEmpty()||!application.getDoc_income_security_no().equals(null)||!application.getDoc_income_security_no().equals("")){
				params[iterator] = application.getDoc_income_security_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds().isEmpty()||!application.getDoc_income_bonds().equals(null)||!application.getDoc_income_bonds().equals("")){
				params[iterator] = application.getDoc_income_bonds();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds_no().isEmpty()||!application.getDoc_income_bonds_no().equals(null)||!application.getDoc_income_bonds_no().equals("")){
				params[iterator] = application.getDoc_income_bonds_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert().isEmpty()||!application.getDoc_income_stockcert().equals(null)||!application.getDoc_income_stockcert().equals("")){
				params[iterator] = application.getDoc_income_stockcert();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert_no().isEmpty()||!application.getDoc_income_stockcert_no().equals(null)||!application.getDoc_income_stockcert_no().equals("")){
				params[iterator] = application.getDoc_income_stockcert_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership().isEmpty()||!application.getDoc_income_companyownership().equals(null)||!application.getDoc_income_companyownership().equals("")){
				params[iterator] = application.getDoc_income_companyownership();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership_no().isEmpty()||!application.getDoc_income_companyownership_no().equals(null)||!application.getDoc_income_companyownership_no().equals("")){
				params[iterator] = application.getDoc_income_companyownership_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others().isEmpty()||!application.getDoc_income_others().equals(null)||!application.getDoc_income_others().equals("")){
				params[iterator] = application.getDoc_income_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others_no().isEmpty()||!application.getDoc_income_others_no().equals(null)||!application.getDoc_income_others_no().equals("")){
				params[iterator] = application.getDoc_income_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge().isEmpty()||!application.getDoc_income_autocharge().equals(null)||!application.getDoc_income_autocharge().equals("")){
				params[iterator] = application.getDoc_income_autocharge();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge_no().isEmpty()||!application.getDoc_income_autocharge_no().equals(null)||!application.getDoc_income_autocharge_no().equals("")){
				params[iterator] = application.getDoc_income_autocharge_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig1().isEmpty()||!application.getDoc_terms_sig1().equals(null)||!application.getDoc_terms_sig1().equals("")){
				params[iterator] = application.getDoc_terms_sig1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig2().isEmpty()||!application.getDoc_terms_sig2().equals(null)||!application.getDoc_terms_sig2().equals("")){
				params[iterator] = application.getDoc_terms_sig2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig3().isEmpty()||!application.getDoc_terms_sig3().equals(null)||!application.getDoc_terms_sig3().equals("")){
				params[iterator] = application.getDoc_terms_sig3();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep0Done().isEmpty()||!application.getStep0Done().equals(null)||!application.getStep0Done().equals("")){
				params[iterator] = application.getStep0Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep1Done().isEmpty()||!application.getStep1Done().equals(null)||!application.getStep1Done().equals("")){
				params[iterator] = application.getStep1Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep2Done().isEmpty()||!application.getStep2Done().equals(null)||!application.getStep2Done().equals("")){
				params[iterator] = application.getStep2Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep3Done().isEmpty()||!application.getStep3Done().equals(null)||!application.getStep3Done().equals("")){
				params[iterator] = application.getStep3Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsaddressDone().isEmpty()||!application.getIsaddressDone().equals(null)||!application.getIsaddressDone().equals("")){
				params[iterator] = application.getIsaddressDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsidentityDone().isEmpty()||!application.getIsidentityDone().equals(null)||!application.getIsidentityDone().equals("")){
				params[iterator] = application.getIsidentityDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsincomeDone().isEmpty()||!application.getIsincomeDone().equals(null)||!application.getIsincomeDone().equals("")){
				params[iterator] = application.getIsincomeDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_person().isEmpty()||!application.getContact_person().equals(null)||!application.getContact_person().equals("")){
				params[iterator] = application.getContact_person();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_number().isEmpty()||!application.getContact_number().equals(null)||!application.getContact_number().equals("")){
				params[iterator] = application.getContact_number();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDecline_remarks().isEmpty()||!application.getDecline_remarks().equals(null)||!application.getDecline_remarks().equals("")){
				params[iterator] = application.getDecline_remarks();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				params[iterator] = application.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEditedBy().isEmpty()||!application.getEditedBy().equals(null)||!application.getEditedBy().equals("")){
				params[iterator] = application.getEditedBy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				params[iterator] = application.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdate().isEmpty()||!application.getUpdate().equals(null)||!application.getUpdate().equals("")){
				params[iterator] = application.getUpdate();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				params[iterator] = application.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				params[iterator] = application.getApplication_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		int isUpdated = jdbcTemplate.update(sql, params);	
		return isUpdated;
	}
	public int delete(Application application){
		String sql = "DELETE FROM application WHERE application_id=?";
		Object[] params = {application.getApplication_id()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	
	@SuppressWarnings("deprecation")
	public int checkIfExists(Application application){
		String sql="SELECT count(*) FROM application WHERE application_id=?";
		//Object[] params ={user.getUserid(),user.getPassword()};
		int isExisting =jdbcTemplate.queryForInt(sql,new Object[] {application.getApplication_id()},new int[] {Types.VARCHAR});
		return isExisting;
	}
	@SuppressWarnings("unchecked")
	public Collection findAllApplication(){
		return jdbcTemplate.query("SELECT * FROM application", new ApplicationMapper());
	}
	
	@SuppressWarnings("unchecked")
	public Collection findAllApplication(String username){
	
		return jdbcTemplate.query("SELECT * FROM application where user_id = ?",new Object[] {username}, new ApplicationMapper());
	}
	
//	SELECT a.*, b.*, c.* FROM application a
//	INNER JOIN plan b ON a.plan_code = b.plan_id
//	INNER JOIN phone c ON a.phone_id = c.phone_id
	
	public Collection findApplication2(Application application) throws SQLException{
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id WHERE application_id = ?",
				new Object[] {application.getApplication_id()}, new ApplicationMapper5());
		
	}
	
	public Collection findApplication(Application application) throws SQLException{
		String sql = "SELECT * FROM application WHERE dvar=? ";
		int iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				sql+=" AND application_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTitle().isEmpty()||!application.getTitle().equals(null)||!application.getTitle().equals("")){
				sql+=" AND title=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthplace().isEmpty()||!application.getBirthplace().equals(null)||!application.getBirthplace().equals("")){
				sql+=" AND birthplace=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOccupation().isEmpty()||!application.getOccupation().equals(null)||!application.getOccupation().equals("")){
				sql+=" AND occupation=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_level().isEmpty()||!application.getPosition_level().equals(null)||!application.getPosition_level().equals("")){
				sql+=" AND position_level=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_title().isEmpty()||!application.getPosition_title().equals(null)||!application.getPosition_title().equals("")){
				sql+=" AND position_title=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTenure().isEmpty()||!application.getTenure().equals(null)||!application.getTenure().equals("")){
				sql+=" AND tenure=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMonthly_income().isEmpty()||!application.getMonthly_income().equals(null)||!application.getMonthly_income().equals("")){
				sql+=" AND monthly_income=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCompany_name().isEmpty()||!application.getCompany_name().equals(null)||!application.getCompany_name().equals("")){
				sql+=" AND company_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_nature().isEmpty()||!application.getBusiness_nature().equals(null)||!application.getBusiness_nature().equals("")){
				sql+=" AND business_nature=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTin().isEmpty()||!application.getTin().equals(null)||!application.getTin().equals("")){
				sql+=" AND tin=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSss().isEmpty()||!application.getSss().equals(null)||!application.getSss().equals("")){
				sql+=" AND sss=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCivil_status().isEmpty()||!application.getCivil_status().equals(null)||!application.getCivil_status().equals("")){
				sql+=" AND civil_status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getGender().isEmpty()||!application.getGender().equals(null)||!application.getGender().equals("")){
				sql+=" AND gender=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDependents().isEmpty()||!application.getDependents().equals(null)||!application.getDependents().equals("")){
				sql+=" AND dependents=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOther_phone_subscriptions().isEmpty()||!application.getOther_phone_subscriptions().equals(null)||!application.getOther_phone_subscriptions().equals("")){
				sql+=" AND other_phone_subscriptions=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFundssource().isEmpty()||!application.getFundssource().equals(null)||!application.getFundssource().equals("")){
				sql+=" AND fundssource=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_firstName().isEmpty()||!application.getSpouse_firstName().equals(null)||!application.getSpouse_firstName().equals("")){
				sql+=" AND spouse_firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_middleName().isEmpty()||!application.getSpouse_middleName().equals(null)||!application.getSpouse_middleName().equals("")){
				sql+=" AND spouse_middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_lastName().isEmpty()||!application.getSpouse_lastName().equals(null)||!application.getSpouse_lastName().equals("")){
				sql+=" AND spouse_lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_birthday().isEmpty()||!application.getSpouse_birthday().equals(null)||!application.getSpouse_birthday().equals("")){
				sql+=" AND spouse_birthday=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMothers_maidenname().isEmpty()||!application.getMothers_maidenname().equals(null)||!application.getMothers_maidenname().equals("")){
				sql+=" AND mothers_maidenname=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_id().isEmpty()||!application.getPhone_id().equals(null)||!application.getPhone_id().equals("")){
				sql+=" AND phone_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPlan_code().isEmpty()||!application.getPlan_code().equals(null)||!application.getPlan_code().equals("")){
				sql+=" AND plan_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				sql+=" AND status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getRef_no().isEmpty()||!application.getRef_no().equals(null)||!application.getRef_no().equals("")){
				sql+=" AND ref_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEmail().isEmpty()||!application.getEmail().equals(null)||!application.getEmail().equals("")){
				sql+=" AND email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFirstName().isEmpty()||!application.getFirstName().equals(null)||!application.getFirstName().equals("")){
				sql+=" AND firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMiddleName().isEmpty()||!application.getMiddleName().equals(null)||!application.getMiddleName().equals("")){
				sql+=" AND middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getLastName().isEmpty()||!application.getLastName().equals(null)||!application.getLastName().equals("")){
				sql+=" AND lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthday().isEmpty()||!application.getBirthday().equals(null)||!application.getBirthday().equals("")){
				sql+=" AND birthday=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getType().isEmpty()||!application.getType().equals(null)||!application.getType().equals("")){
				sql+=" AND type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getImage().isEmpty()||!application.getImage().equals(null)||!application.getImage().equals("")){
				sql+=" AND image=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTermination_image().isEmpty()||!application.getTermination_image().equals(null)||!application.getTermination_image().equals("")){
				sql+=" AND termination_image=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddRegion().isEmpty()||!application.getAddRegion().equals(null)||!application.getAddRegion().equals("")){
				sql+=" AND addRegion=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddCity().isEmpty()||!application.getAddCity().equals(null)||!application.getAddCity().equals("")){
				sql+=" AND addCity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddBrgy().isEmpty()||!application.getAddBrgy().equals(null)||!application.getAddBrgy().equals("")){
				sql+=" AND addBrgy=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine1().isEmpty()||!application.getAddLine1().equals(null)||!application.getAddLine1().equals("")){
				sql+=" AND addLine1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine2().isEmpty()||!application.getAddLine2().equals(null)||!application.getAddLine2().equals("")){
				sql+=" AND addLine2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getZipCode().isEmpty()||!application.getZipCode().equals(null)||!application.getZipCode().equals("")){
				sql+=" AND zipCode=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkRegion().isEmpty()||!application.getAddWorkRegion().equals(null)||!application.getAddWorkRegion().equals("")){
				sql+=" AND addWorkRegion=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkCity().isEmpty()||!application.getAddWorkCity().equals(null)||!application.getAddWorkCity().equals("")){
				sql+=" AND addWorkCity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkBrgy().isEmpty()||!application.getAddWorkBrgy().equals(null)||!application.getAddWorkBrgy().equals("")){
				sql+=" AND addWorkBrgy=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine1().isEmpty()||!application.getAddWorkLine1().equals(null)||!application.getAddWorkLine1().equals("")){
				sql+=" AND addWorkLine1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine2().isEmpty()||!application.getAddWorkLine2().equals(null)||!application.getAddWorkLine2().equals("")){
				sql+=" AND addWorkLine2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getWorkZipCode().isEmpty()||!application.getWorkZipCode().equals(null)||!application.getWorkZipCode().equals("")){
				sql+=" AND workZipCode=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSendBillTo().isEmpty()||!application.getSendBillTo().equals(null)||!application.getSendBillTo().equals("")){
				sql+=" AND sendBillTo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTelephone().isEmpty()||!application.getTelephone().equals(null)||!application.getTelephone().equals("")){
				sql+=" AND telephone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMobile().isEmpty()||!application.getMobile().equals(null)||!application.getMobile().equals("")){
				sql+=" AND mobile=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_retrieval_type().isEmpty()||!application.getPhone_retrieval_type().equals(null)||!application.getPhone_retrieval_type().equals("")){
				sql+=" AND phone_retrieval_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_name().isEmpty()||!application.getBusiness_center_name().equals(null)||!application.getBusiness_center_name().equals("")){
				sql+=" AND business_center_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lng().isEmpty()||!application.getBusiness_center_lng().equals(null)||!application.getBusiness_center_lng().equals("")){
				sql+=" AND business_center_lng=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lat().isEmpty()||!application.getBusiness_center_lat().equals(null)||!application.getBusiness_center_lat().equals("")){
				sql+=" AND business_center_lat=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPayment_type().isEmpty()||!application.getPayment_type().equals(null)||!application.getPayment_type().equals("")){
				sql+=" AND payment_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_number().isEmpty()||!application.getCc_number().equals(null)||!application.getCc_number().equals("")){
				sql+=" AND cc_number=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_bank().isEmpty()||!application.getCc_bank().equals(null)||!application.getCc_bank().equals("")){
				sql+=" AND cc_bank=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_email().isEmpty()||!application.getPp_email().equals(null)||!application.getPp_email().equals("")){
				sql+=" AND pp_email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_refNo().isEmpty()||!application.getPp_refNo().equals(null)||!application.getPp_refNo().equals("")){
				sql+=" AND pp_refNo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCod_amt().isEmpty()||!application.getCod_amt().equals(null)||!application.getCod_amt().equals("")){
				sql+=" AND cod_amt=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss().isEmpty()||!application.getDoc_identity_sss().equals(null)||!application.getDoc_identity_sss().equals("")){
				sql+=" AND doc_identity_sss=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss_no().isEmpty()||!application.getDoc_identity_sss_no().equals(null)||!application.getDoc_identity_sss_no().equals("")){
				sql+=" AND pp_refNo=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig().isEmpty()||!application.getDoc_identity_pagibig().equals(null)||!application.getDoc_identity_pagibig().equals("")){
				sql+=" AND doc_identity_pagibig=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig_no().isEmpty()||!application.getDoc_identity_pagibig_no().equals(null)||!application.getDoc_identity_pagibig_no().equals("")){
				sql+=" AND doc_identity_pagibig_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth().isEmpty()||!application.getDoc_identity_philhealth().equals(null)||!application.getDoc_identity_philhealth().equals("")){
				sql+=" AND doc_identity_philhealth=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth_no().isEmpty()||!application.getDoc_identity_philhealth_no().equals(null)||!application.getDoc_identity_philhealth_no().equals("")){
				sql+=" AND doc_identity_philhealth_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin().isEmpty()||!application.getDoc_identity_tin().equals(null)||!application.getDoc_identity_tin().equals("")){
				sql+=" AND doc_identity_tin=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin_no().isEmpty()||!application.getDoc_identity_tin_no().equals(null)||!application.getDoc_identity_tin_no().equals("")){
				sql+=" AND doc_identity_tin_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense().isEmpty()||!application.getDoc_identity_driverslicense().equals(null)||!application.getDoc_identity_driverslicense().equals("")){
				sql+=" AND doc_identity_driverslicense=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense_no().isEmpty()||!application.getDoc_identity_driverslicense_no().equals(null)||!application.getDoc_identity_driverslicense_no().equals("")){
				sql+=" AND doc_identity_driverslicense_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport().isEmpty()||!application.getDoc_identity_passport().equals(null)||!application.getDoc_identity_passport().equals("")){
				sql+=" AND doc_identity_passport=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport_no().isEmpty()||!application.getDoc_identity_passport_no().equals(null)||!application.getDoc_identity_passport_no().equals("")){
				sql+=" AND doc_identity_passport_no=?";
				iterator++
			
				;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid().isEmpty()||!application.getDoc_identity_companyid().equals(null)||!application.getDoc_identity_companyid().equals("")){
				sql+=" AND doc_identity_companyid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid_no().isEmpty()||!application.getDoc_identity_companyid_no().equals(null)||!application.getDoc_identity_companyid_no().equals("")){
				sql+=" AND doc_identity_companyid_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others().isEmpty()||!application.getDoc_identity_others().equals(null)||!application.getDoc_identity_others().equals("")){
				sql+=" AND doc_identity_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others_no().isEmpty()||!application.getDoc_identity_others_no().equals(null)||!application.getDoc_identity_others_no().equals("")){
				sql+=" AND doc_identity_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity().isEmpty()||!application.getDoc_address_electricity().equals(null)||!application.getDoc_address_electricity().equals("")){
				sql+=" AND doc_address_electricity=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity_no().isEmpty()||!application.getDoc_address_electricity_no().equals(null)||!application.getDoc_address_electricity_no().equals("")){
				sql+=" AND doc_address_electricity_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water().isEmpty()||!application.getDoc_address_water().equals(null)||!application.getDoc_address_water().equals("")){
				sql+=" AND doc_address_water=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water_no().isEmpty()||!application.getDoc_address_water_no().equals(null)||!application.getDoc_address_water_no().equals("")){
				sql+=" AND doc_address_water_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp().isEmpty()||!application.getDoc_address_isp().equals(null)||!application.getDoc_address_isp().equals("")){
				sql+=" AND doc_address_isp=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp_no().isEmpty()||!application.getDoc_address_isp_no().equals(null)||!application.getDoc_address_isp_no().equals("")){
				sql+=", doc_address_isp_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable().isEmpty()||!application.getDoc_address_cable().equals(null)||!application.getDoc_address_cable().equals("")){
				sql+=" AND doc_address_cable=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable_no().isEmpty()||!application.getDoc_address_cable_no().equals(null)||!application.getDoc_address_cable_no().equals("")){
				sql+=" AND doc_address_cable_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom().isEmpty()||!application.getDoc_address_telecom().equals(null)||!application.getDoc_address_telecom().equals("")){
				sql+=" AND doc_address_telecom=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom_no().isEmpty()||!application.getDoc_address_telecom_no().equals(null)||!application.getDoc_address_telecom_no().equals("")){
				sql+=" AND doc_address_telecom_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan().isEmpty()||!application.getDoc_address_bankloan().equals(null)||!application.getDoc_address_bankloan().equals("")){
				sql+=" AND doc_address_bankloan=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan_no().isEmpty()||!application.getDoc_address_bankloan_no().equals(null)||!application.getDoc_address_bankloan_no().equals("")){
				sql+=" AND doc_address_bankloan_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others().isEmpty()||!application.getDoc_address_others().equals(null)||!application.getDoc_address_others().equals("")){
				sql+=" AND doc_address_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others_no().isEmpty()||!application.getDoc_address_others_no().equals(null)||!application.getDoc_address_others_no().equals("")){
				sql+=" AND doc_address_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement().isEmpty()||!application.getDoc_income_bankstatement().equals(null)||!application.getDoc_income_bankstatement().equals("")){
				sql+=" AND doc_income_bankstatement=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement_no().isEmpty()||!application.getDoc_income_bankstatement_no().equals(null)||!application.getDoc_income_bankstatement_no().equals("")){
				sql+=" AND doc_income_bankstatement_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip().isEmpty()||!application.getDoc_income_payslip().equals(null)||!application.getDoc_income_payslip().equals("")){
				sql+=" AND doc_income_payslip=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip_no().isEmpty()||!application.getDoc_income_payslip_no().equals(null)||!application.getDoc_income_payslip_no().equals("")){
				sql+=" AND doc_income_payslip_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security().isEmpty()||!application.getDoc_income_security().equals(null)||!application.getDoc_income_security().equals("")){
				sql+=" AND doc_income_security=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security_no().isEmpty()||!application.getDoc_income_security_no().equals(null)||!application.getDoc_income_security_no().equals("")){
				sql+=" AND doc_income_security_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds().isEmpty()||!application.getDoc_income_bonds().equals(null)||!application.getDoc_income_bonds().equals("")){
				sql+=" AND doc_income_bonds=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds_no().isEmpty()||!application.getDoc_income_bonds_no().equals(null)||!application.getDoc_income_bonds_no().equals("")){
				sql+=" AND doc_income_bonds_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert().isEmpty()||!application.getDoc_income_stockcert().equals(null)||!application.getDoc_income_stockcert().equals("")){
				sql+=" AND doc_income_stockcert=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert_no().isEmpty()||!application.getDoc_income_stockcert_no().equals(null)||!application.getDoc_income_stockcert_no().equals("")){
				sql+=" AND doc_income_stockcert_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership().isEmpty()||!application.getDoc_income_companyownership().equals(null)||!application.getDoc_income_companyownership().equals("")){
				sql+=" AND doc_income_companyownership=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership_no().isEmpty()||!application.getDoc_income_companyownership_no().equals(null)||!application.getDoc_income_companyownership_no().equals("")){
				sql+=" AND doc_income_companyownership_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others().isEmpty()||!application.getDoc_income_others().equals(null)||!application.getDoc_income_others().equals("")){
				sql+=" AND doc_income_others=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others_no().isEmpty()||!application.getDoc_income_others_no().equals(null)||!application.getDoc_income_others_no().equals("")){
				sql+=" AND doc_income_others_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge().isEmpty()||!application.getDoc_income_autocharge().equals(null)||!application.getDoc_income_autocharge().equals("")){
				sql+=" AND doc_income_autocharge=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge_no().isEmpty()||!application.getDoc_income_autocharge_no().equals(null)||!application.getDoc_income_autocharge_no().equals("")){
				sql+=" AND doc_income_autocharge_no=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig1().isEmpty()||!application.getDoc_terms_sig1().equals(null)||!application.getDoc_terms_sig1().equals("")){
				sql+=" AND doc_terms_sig1=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig2().isEmpty()||!application.getDoc_terms_sig2().equals(null)||!application.getDoc_terms_sig2().equals("")){
				sql+=" AND doc_terms_sig2=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig3().isEmpty()||!application.getDoc_terms_sig3().equals(null)||!application.getDoc_terms_sig3().equals("")){
				sql+=" AND doc_terms_sig3=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep0Done().isEmpty()||!application.getStep0Done().equals(null)||!application.getStep0Done().equals("")){
				sql+=" AND step0Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep1Done().isEmpty()||!application.getStep1Done().equals(null)||!application.getStep1Done().equals("")){
				sql+=" AND step1Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep2Done().isEmpty()||!application.getStep2Done().equals(null)||!application.getStep2Done().equals("")){
				sql+=" AND step2Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep3Done().isEmpty()||!application.getStep3Done().equals(null)||!application.getStep3Done().equals("")){
				sql+=" AND step3Done=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsaddressDone().isEmpty()||!application.getIsaddressDone().equals(null)||!application.getIsaddressDone().equals("")){
				sql+=" AND isaddressDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsidentityDone().isEmpty()||!application.getIsidentityDone().equals(null)||!application.getIsidentityDone().equals("")){
				sql+=" AND isidentityDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsincomeDone().isEmpty()||!application.getIsincomeDone().equals(null)||!application.getIsincomeDone().equals("")){
				sql+=" AND isaincomeDone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_person().isEmpty()||!application.getContact_person().equals(null)||!application.getContact_person().equals("")){
				sql+=" AND contact_person=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_number().isEmpty()||!application.getContact_number().equals(null)||!application.getContact_number().equals("")){
				sql+=" AND contact_number=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDecline_remarks().isEmpty()||!application.getDecline_remarks().equals(null)||!application.getDecline_remarks().equals("")){
				sql+=" AND  decline_remarks=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				sql+=" AND ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEditedBy().isEmpty()||!application.getEditedBy().equals(null)||!application.getEditedBy().equals("")){
				sql+=" AND edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				sql+=" AND created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdate().isEmpty()||!application.getUpdate().equals(null)||!application.getUpdate().equals("")){
				sql+=" AND updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				sql+=" AND version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				sql+=" AND application_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
			
		Object[] params = new Object[iterator];

		params[0] = "1";
		iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				params[iterator] = application.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTitle().isEmpty()||!application.getTitle().equals(null)||!application.getTitle().equals("")){
				params[iterator] = application.getTitle();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthplace().isEmpty()||!application.getBirthplace().equals(null)||!application.getBirthplace().equals("")){
				params[iterator] = application.getBirthplace();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOccupation().isEmpty()||!application.getOccupation().equals(null)||!application.getOccupation().equals("")){
				params[iterator] = application.getOccupation();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_level().isEmpty()||!application.getPosition_level().equals(null)||!application.getPosition_level().equals("")){
				params[iterator] = application.getPosition_level();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPosition_title().isEmpty()||!application.getPosition_title().equals(null)||!application.getPosition_title().equals("")){
				params[iterator] = application.getPosition_title();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTenure().isEmpty()||!application.getTenure().equals(null)||!application.getTenure().equals("")){
				params[iterator] = application.getTenure();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMonthly_income().isEmpty()||!application.getMonthly_income().equals(null)||!application.getMonthly_income().equals("")){
				params[iterator] = application.getMonthly_income();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCompany_name().isEmpty()||!application.getCompany_name().equals(null)||!application.getCompany_name().equals("")){
				params[iterator] = application.getCompany_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_nature().isEmpty()||!application.getBusiness_nature().equals(null)||!application.getBusiness_nature().equals("")){
				params[iterator] = application.getBusiness_nature();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTin().isEmpty()||!application.getTin().equals(null)||!application.getTin().equals("")){
				params[iterator] = application.getTin();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSss().isEmpty()||!application.getSss().equals(null)||!application.getSss().equals("")){
				params[iterator] = application.getSss();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCivil_status().isEmpty()||!application.getCivil_status().equals(null)||!application.getCivil_status().equals("")){
				params[iterator] = application.getCivil_status();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getGender().isEmpty()||!application.getGender().equals(null)||!application.getGender().equals("")){
				params[iterator] = application.getGender();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDependents().isEmpty()||!application.getDependents().equals(null)||!application.getDependents().equals("")){
				params[iterator] = application.getDependents();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getOther_phone_subscriptions().isEmpty()||!application.getOther_phone_subscriptions().equals(null)||!application.getOther_phone_subscriptions().equals("")){
				params[iterator] = application.getOther_phone_subscriptions();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFundssource().isEmpty()||!application.getFundssource().equals(null)||!application.getFundssource().equals("")){
				params[iterator] = application.getFundssource();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_firstName().isEmpty()||!application.getSpouse_firstName().equals(null)||!application.getSpouse_firstName().equals("")){
				params[iterator] = application.getSpouse_firstName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_middleName().isEmpty()||!application.getSpouse_middleName().equals(null)||!application.getSpouse_middleName().equals("")){
				params[iterator] = application.getSpouse_middleName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_lastName().isEmpty()||!application.getSpouse_lastName().equals(null)||!application.getSpouse_lastName().equals("")){
				params[iterator] = application.getSpouse_lastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSpouse_birthday().isEmpty()||!application.getSpouse_birthday().equals(null)||!application.getSpouse_birthday().equals("")){
				params[iterator] = application.getSpouse_birthday();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMothers_maidenname().isEmpty()||!application.getMothers_maidenname().equals(null)||!application.getMothers_maidenname().equals("")){
				params[iterator] = application.getMothers_maidenname();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_id().isEmpty()||!application.getPhone_id().equals(null)||!application.getPhone_id().equals("")){
				params[iterator] = application.getPhone_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPlan_code().isEmpty()||!application.getPlan_code().equals(null)||!application.getPlan_code().equals("")){
				params[iterator] = application.getPlan_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				params[iterator] = application.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getRef_no().isEmpty()||!application.getRef_no().equals(null)||!application.getRef_no().equals("")){
				params[iterator] = application.getRef_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEmail().isEmpty()||!application.getEmail().equals(null)||!application.getEmail().equals("")){
				params[iterator] = application.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getFirstName().isEmpty()||!application.getFirstName().equals(null)||!application.getFirstName().equals("")){
				params[iterator] = application.getFirstName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMiddleName().isEmpty()||!application.getMiddleName().equals(null)||!application.getMiddleName().equals("")){
				params[iterator] = application.getMiddleName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getLastName().isEmpty()||!application.getLastName().equals(null)||!application.getLastName().equals("")){
				params[iterator] = application.getLastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBirthday().isEmpty()||!application.getBirthday().equals(null)||!application.getBirthday().equals("")){
				params[iterator] = application.getBirthday();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getType().isEmpty()||!application.getType().equals(null)||!application.getType().equals("")){
				params[iterator] = application.getType();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getImage().isEmpty()||!application.getImage().equals(null)||!application.getImage().equals("")){
				params[iterator] = application.getImage();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTermination_image().isEmpty()||!application.getTermination_image().equals(null)||!application.getTermination_image().equals("")){
				params[iterator] = application.getTermination_image();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddRegion().isEmpty()||!application.getAddRegion().equals(null)||!application.getAddRegion().equals("")){
				params[iterator] = application.getAddRegion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddCity().isEmpty()||!application.getAddCity().equals(null)||!application.getAddCity().equals("")){
				params[iterator] = application.getAddCity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddBrgy().isEmpty()||!application.getAddBrgy().equals(null)||!application.getAddBrgy().equals("")){
				params[iterator] = application.getAddBrgy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine1().isEmpty()||!application.getAddLine1().equals(null)||!application.getAddLine1().equals("")){
				params[iterator] = application.getAddLine1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddLine2().isEmpty()||!application.getAddLine2().equals(null)||!application.getAddLine2().equals("")){
				params[iterator] = application.getAddLine2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getZipCode().isEmpty()||!application.getZipCode().equals(null)||!application.getZipCode().equals("")){
				params[iterator] = application.getZipCode();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkRegion().isEmpty()||!application.getAddWorkRegion().equals(null)||!application.getAddWorkRegion().equals("")){
				params[iterator] = application.getAddWorkRegion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkCity().isEmpty()||!application.getAddWorkCity().equals(null)||!application.getAddWorkCity().equals("")){
				params[iterator] = application.getAddWorkCity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkBrgy().isEmpty()||!application.getAddWorkBrgy().equals(null)||!application.getAddWorkBrgy().equals("")){
				params[iterator] = application.getAddWorkBrgy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine1().isEmpty()||!application.getAddWorkLine1().equals(null)||!application.getAddWorkLine1().equals("")){
				params[iterator] = application.getAddWorkLine1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getAddWorkLine2().isEmpty()||!application.getAddWorkLine2().equals(null)||!application.getAddWorkLine2().equals("")){
				params[iterator] = application.getAddWorkLine2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getWorkZipCode().isEmpty()||!application.getWorkZipCode().equals(null)||!application.getWorkZipCode().equals("")){
				params[iterator] = application.getWorkZipCode();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSendBillTo().isEmpty()||!application.getSendBillTo().equals(null)||!application.getSendBillTo().equals("")){
				params[iterator] = application.getSendBillTo();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getTelephone().isEmpty()||!application.getTelephone().equals(null)||!application.getTelephone().equals("")){
				params[iterator] = application.getTelephone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getMobile().isEmpty()||!application.getMobile().equals(null)||!application.getMobile().equals("")){
				params[iterator] = application.getMobile();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPhone_retrieval_type().isEmpty()||!application.getPhone_retrieval_type().equals(null)||!application.getPhone_retrieval_type().equals("")){
				params[iterator] = application.getPhone_retrieval_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_name().isEmpty()||!application.getBusiness_center_name().equals(null)||!application.getBusiness_center_name().equals("")){
				params[iterator] = application.getBusiness_center_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lng().isEmpty()||!application.getBusiness_center_lng().equals(null)||!application.getBusiness_center_lng().equals("")){
				params[iterator] = application.getBusiness_center_lng();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getBusiness_center_lat().isEmpty()||!application.getBusiness_center_lat().equals(null)||!application.getBusiness_center_lat().equals("")){
				params[iterator] = application.getBusiness_center_lat();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPayment_type().isEmpty()||!application.getPayment_type().equals(null)||!application.getPayment_type().equals("")){
				params[iterator] = application.getPayment_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_number().isEmpty()||!application.getCc_number().equals(null)||!application.getCc_number().equals("")){
				params[iterator] = application.getPayment_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCc_bank().isEmpty()||!application.getCc_bank().equals(null)||!application.getCc_bank().equals("")){
				params[iterator] = application.getCc_bank();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_email().isEmpty()||!application.getPp_email().equals(null)||!application.getPp_email().equals("")){
				params[iterator] = application.getPp_email();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getPp_refNo().isEmpty()||!application.getPp_refNo().equals(null)||!application.getPp_refNo().equals("")){
				params[iterator] = application.getPp_refNo();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCod_amt().isEmpty()||!application.getCod_amt().equals(null)||!application.getCod_amt().equals("")){
				params[iterator] = application.getCod_amt();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss().isEmpty()||!application.getDoc_identity_sss().equals(null)||!application.getDoc_identity_sss().equals("")){
				params[iterator] = application.getDoc_identity_sss();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_sss_no().isEmpty()||!application.getDoc_identity_sss_no().equals(null)||!application.getDoc_identity_sss_no().equals("")){
				params[iterator] = application.getDoc_identity_sss_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig().isEmpty()||!application.getDoc_identity_pagibig().equals(null)||!application.getDoc_identity_pagibig().equals("")){
				params[iterator] = application.getDoc_identity_pagibig();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_pagibig_no().isEmpty()||!application.getDoc_identity_pagibig_no().equals(null)||!application.getDoc_identity_pagibig_no().equals("")){
				params[iterator] = application.getDoc_identity_pagibig_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth().isEmpty()||!application.getDoc_identity_philhealth().equals(null)||!application.getDoc_identity_philhealth().equals("")){
				params[iterator] = application.getDoc_identity_philhealth();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_philhealth_no().isEmpty()||!application.getDoc_identity_philhealth_no().equals(null)||!application.getDoc_identity_philhealth_no().equals("")){
				params[iterator] = application.getDoc_identity_philhealth_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin().isEmpty()||!application.getDoc_identity_tin().equals(null)||!application.getDoc_identity_tin().equals("")){
				params[iterator] = application.getDoc_identity_tin();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_tin_no().isEmpty()||!application.getDoc_identity_tin_no().equals(null)||!application.getDoc_identity_tin_no().equals("")){
				params[iterator] = application.getDoc_identity_tin_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense().isEmpty()||!application.getDoc_identity_driverslicense().equals(null)||!application.getDoc_identity_driverslicense().equals("")){
				params[iterator] = application.getDoc_identity_driverslicense();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_driverslicense_no().isEmpty()||!application.getDoc_identity_driverslicense_no().equals(null)||!application.getDoc_identity_driverslicense_no().equals("")){
				params[iterator] = application.getDoc_identity_driverslicense_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport().isEmpty()||!application.getDoc_identity_passport().equals(null)||!application.getDoc_identity_passport().equals("")){
				params[iterator] = application.getDoc_identity_passport();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_passport_no().isEmpty()||!application.getDoc_identity_passport_no().equals(null)||!application.getDoc_identity_passport_no().equals("")){
				params[iterator] = application.getDoc_identity_passport_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid().isEmpty()||!application.getDoc_identity_companyid().equals(null)||!application.getDoc_identity_companyid().equals("")){
				params[iterator] = application.getDoc_identity_companyid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_companyid_no().isEmpty()||!application.getDoc_identity_companyid_no().equals(null)||!application.getDoc_identity_companyid_no().equals("")){
				params[iterator] = application.getDoc_identity_companyid_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others().isEmpty()||!application.getDoc_identity_others().equals(null)||!application.getDoc_identity_others().equals("")){
				params[iterator] = application.getDoc_identity_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_identity_others_no().isEmpty()||!application.getDoc_identity_others_no().equals(null)||!application.getDoc_identity_others_no().equals("")){
				params[iterator] = application.getDoc_identity_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity().isEmpty()||!application.getDoc_address_electricity().equals(null)||!application.getDoc_address_electricity().equals("")){
				params[iterator] = application.getDoc_address_electricity();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_electricity_no().isEmpty()||!application.getDoc_address_electricity_no().equals(null)||!application.getDoc_address_electricity_no().equals("")){
				params[iterator] = application.getDoc_address_electricity_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water().isEmpty()||!application.getDoc_address_water().equals(null)||!application.getDoc_address_water().equals("")){
				params[iterator] = application.getDoc_address_water();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_water_no().isEmpty()||!application.getDoc_address_water_no().equals(null)||!application.getDoc_address_water_no().equals("")){
				params[iterator] = application.getDoc_address_water_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp().isEmpty()||!application.getDoc_address_isp().equals(null)||!application.getDoc_address_isp().equals("")){
				params[iterator] = application.getDoc_address_isp();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_isp_no().isEmpty()||!application.getDoc_address_isp_no().equals(null)||!application.getDoc_address_isp_no().equals("")){
				params[iterator] = application.getDoc_address_isp_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable().isEmpty()||!application.getDoc_address_cable().equals(null)||!application.getDoc_address_cable().equals("")){
				params[iterator] = application.getDoc_address_cable();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_cable_no().isEmpty()||!application.getDoc_address_cable_no().equals(null)||!application.getDoc_address_cable_no().equals("")){
				params[iterator] = application.getDoc_address_cable_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom().isEmpty()||!application.getDoc_address_telecom().equals(null)||!application.getDoc_address_telecom().equals("")){
				params[iterator] = application.getDoc_address_telecom();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_telecom_no().isEmpty()||!application.getDoc_address_telecom_no().equals(null)||!application.getDoc_address_telecom_no().equals("")){
				params[iterator] = application.getDoc_address_telecom_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan().isEmpty()||!application.getDoc_address_bankloan().equals(null)||!application.getDoc_address_bankloan().equals("")){
				params[iterator] = application.getDoc_address_bankloan();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_bankloan_no().isEmpty()||!application.getDoc_address_bankloan_no().equals(null)||!application.getDoc_address_bankloan_no().equals("")){
				params[iterator] = application.getDoc_address_bankloan_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others().isEmpty()||!application.getDoc_address_others().equals(null)||!application.getDoc_address_others().equals("")){
				params[iterator] = application.getDoc_address_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_address_others_no().isEmpty()||!application.getDoc_address_others_no().equals(null)||!application.getDoc_address_others_no().equals("")){
				params[iterator] = application.getDoc_address_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement().isEmpty()||!application.getDoc_income_bankstatement().equals(null)||!application.getDoc_income_bankstatement().equals("")){
				params[iterator] = application.getDoc_income_bankstatement();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bankstatement_no().isEmpty()||!application.getDoc_income_bankstatement_no().equals(null)||!application.getDoc_income_bankstatement_no().equals("")){
				params[iterator] = application.getDoc_income_bankstatement_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip().isEmpty()||!application.getDoc_income_payslip().equals(null)||!application.getDoc_income_payslip().equals("")){
				params[iterator] = application.getDoc_income_payslip();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_payslip_no().isEmpty()||!application.getDoc_income_payslip_no().equals(null)||!application.getDoc_income_payslip_no().equals("")){
				params[iterator] = application.getDoc_income_payslip_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security().isEmpty()||!application.getDoc_income_security().equals(null)||!application.getDoc_income_security().equals("")){
				params[iterator] = application.getDoc_income_security();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_security_no().isEmpty()||!application.getDoc_income_security_no().equals(null)||!application.getDoc_income_security_no().equals("")){
				params[iterator] = application.getDoc_income_security_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds().isEmpty()||!application.getDoc_income_bonds().equals(null)||!application.getDoc_income_bonds().equals("")){
				params[iterator] = application.getDoc_income_bonds();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_bonds_no().isEmpty()||!application.getDoc_income_bonds_no().equals(null)||!application.getDoc_income_bonds_no().equals("")){
				params[iterator] = application.getDoc_income_bonds_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert().isEmpty()||!application.getDoc_income_stockcert().equals(null)||!application.getDoc_income_stockcert().equals("")){
				params[iterator] = application.getDoc_income_stockcert();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_stockcert_no().isEmpty()||!application.getDoc_income_stockcert_no().equals(null)||!application.getDoc_income_stockcert_no().equals("")){
				params[iterator] = application.getDoc_income_stockcert_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership().isEmpty()||!application.getDoc_income_companyownership().equals(null)||!application.getDoc_income_companyownership().equals("")){
				params[iterator] = application.getDoc_income_companyownership();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_companyownership_no().isEmpty()||!application.getDoc_income_companyownership_no().equals(null)||!application.getDoc_income_companyownership_no().equals("")){
				params[iterator] = application.getDoc_income_companyownership_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others().isEmpty()||!application.getDoc_income_others().equals(null)||!application.getDoc_income_others().equals("")){
				params[iterator] = application.getDoc_income_others();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_others_no().isEmpty()||!application.getDoc_income_others_no().equals(null)||!application.getDoc_income_others_no().equals("")){
				params[iterator] = application.getDoc_income_others_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge().isEmpty()||!application.getDoc_income_autocharge().equals(null)||!application.getDoc_income_autocharge().equals("")){
				params[iterator] = application.getDoc_income_autocharge();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_income_autocharge_no().isEmpty()||!application.getDoc_income_autocharge_no().equals(null)||!application.getDoc_income_autocharge_no().equals("")){
				params[iterator] = application.getDoc_income_autocharge_no();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig1().isEmpty()||!application.getDoc_terms_sig1().equals(null)||!application.getDoc_terms_sig1().equals("")){
				params[iterator] = application.getDoc_terms_sig1();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig2().isEmpty()||!application.getDoc_terms_sig2().equals(null)||!application.getDoc_terms_sig2().equals("")){
				params[iterator] = application.getDoc_terms_sig2();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDoc_terms_sig3().isEmpty()||!application.getDoc_terms_sig3().equals(null)||!application.getDoc_terms_sig3().equals("")){
				params[iterator] = application.getDoc_terms_sig3();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep0Done().isEmpty()||!application.getStep0Done().equals(null)||!application.getStep0Done().equals("")){
				params[iterator] = application.getStep0Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep1Done().isEmpty()||!application.getStep1Done().equals(null)||!application.getStep1Done().equals("")){
				params[iterator] = application.getStep1Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep2Done().isEmpty()||!application.getStep2Done().equals(null)||!application.getStep2Done().equals("")){
				params[iterator] = application.getStep2Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStep3Done().isEmpty()||!application.getStep3Done().equals(null)||!application.getStep3Done().equals("")){
				params[iterator] = application.getStep3Done();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsaddressDone().isEmpty()||!application.getIsaddressDone().equals(null)||!application.getIsaddressDone().equals("")){
				params[iterator] = application.getIsaddressDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsidentityDone().isEmpty()||!application.getIsidentityDone().equals(null)||!application.getIsidentityDone().equals("")){
				params[iterator] = application.getIsidentityDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIsincomeDone().isEmpty()||!application.getIsincomeDone().equals(null)||!application.getIsincomeDone().equals("")){
				params[iterator] = application.getIsincomeDone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_person().isEmpty()||!application.getContact_person().equals(null)||!application.getContact_person().equals("")){
				params[iterator] = application.getContact_person();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getContact_number().isEmpty()||!application.getContact_number().equals(null)||!application.getContact_number().equals("")){
				params[iterator] = application.getContact_number();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getDecline_remarks().isEmpty()||!application.getDecline_remarks().equals(null)||!application.getDecline_remarks().equals("")){
				params[iterator] = application.getDecline_remarks();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				params[iterator] = application.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getEditedBy().isEmpty()||!application.getEditedBy().equals(null)||!application.getEditedBy().equals("")){
				params[iterator] = application.getEditedBy();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				params[iterator] = application.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdate().isEmpty()||!application.getUpdate().equals(null)||!application.getUpdate().equals("")){
				params[iterator] = application.getUpdate();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				params[iterator] = application.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				params[iterator] = application.getApplication_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		return jdbcTemplate.query(sql, params,new ApplicationMapper());
	
	}
/*	@SuppressWarnings("rawtypes")
	private static final class ApplicationMapper implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setTitle(rs.getString("title"));
	        application.setBirthplace(rs.getString("birthplace"));
	        application.setOccupation(rs.getString("occupation"));
	        application.setPosition_level(rs.getString("position_level"));
	        application.setPosition_title(rs.getString("position_title"));
	        application.setTenure(rs.getString("tenure"));
	        application.setMonthly_income(rs.getString("monthly_income"));
	        application.setCompany_name(rs.getString("company_name"));
	        application.setBusiness_nature(rs.getString("business_nature"));
	        application.setTin(rs.getString("tin"));
	        application.setSss(rs.getString("sss"));
	        application.setCivil_status(rs.getString("civil_status"));
	        application.setGender(rs.getString("gender"));
	        application.setDependents(rs.getString("dependents"));
	        application.setOther_phone_subscriptions(rs.getString("other_phone_subscriptions"));
	        application.setFundssource(rs.getString("fundssource"));
	        application.setSpouse_firstName(rs.getString("spouse_firstName"));
	        application.setSpouse_middleName(rs.getString("spouse_middleName"));
	        application.setSpouse_lastName(rs.getString("spouse_lastName"));
	        application.setSpouse_birthday(rs.getString("spouse_birthday"));
	        application.setMothers_maidenname(rs.getString("mothers_maidenname"));
	        application.setPhone_id(rs.getString("phone_id"));
	        application.setPlan_code(rs.getString("plan_code"));
	        if(rs.getString("status").equals("0")){
	        	application.setStatus("Pending");
	        }
	        if(rs.getString("status").equals("1")){
	        	application.setStatus("Approved");
	        }
	        if(rs.getString("status").equals("2")){
	        	application.setStatus("Not Approved");
	        }
	        application.setRef_no(rs.getString("ref_no"));
	        application.setEmail(rs.getString("email"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setMiddleName(rs.getString("middleName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setBirthday(rs.getString("birthday"));
	        application.setType(rs.getString("type"));
	        application.setImage(rs.getString("image"));
	        application.setTermination_image(rs.getString("termination_image"));
	        application.setAddRegion(rs.getString("addRegion"));
	        application.setAddCity(rs.getString("addCity"));
	        application.setAddBrgy(rs.getString("addBrgy"));
	        application.setAddLine1(rs.getString("addLine1"));
	        application.setAddLine2(rs.getString("addLine2"));
	        application.setZipCode(rs.getString("zipCode"));
	        application.setAddWorkRegion(rs.getString("addWorkRegion"));
	        application.setAddWorkCity(rs.getString("addWorkCity"));
	        application.setAddWorkBrgy(rs.getString("addWorkBrgy"));
	        application.setAddWorkLine1(rs.getString("addWorkLine1"));
	        application.setAddWorkLine2(rs.getString("addWorkLine2"));
	        application.setWorkZipCode(rs.getString("workZipCode"));
	        application.setSendBillTo(rs.getString("sendBillTo"));
	        application.setTelephone(rs.getString("telephone"));
	        application.setMobile(rs.getString("mobile"));
	        application.setPhone_retrieval_type(rs.getString("phone_retrieval_type"));
	        application.setBusiness_center_name(rs.getString("business_center_name"));
	        application.setBusiness_center_lng(rs.getString("business_center_lng"));
	        application.setBusiness_center_lat(rs.getString("business_center_lat"));
	        application.setPayment_type(rs.getString("payment_type"));
	        application.setCc_number(rs.getString("cc_number"));
	        application.setCc_bank(rs.getString("cc_bank"));
	        application.setPp_email(rs.getString("pp_email"));
	        application.setPp_refNo(rs.getString("pp_refno"));
	        application.setCod_amt(rs.getString("cod_amt"));
	        application.setDoc_identity_sss(rs.getString("doc_identity_sss"));
	        application.setDoc_identity_sss_no(rs.getString("doc_identity_sss_no"));
	        application.setDoc_identity_pagibig(rs.getString("doc_identity_pagibig"));
	        application.setDoc_identity_pagibig_no(rs.getString("doc_identity_pagibig_no"));
	        application.setDoc_identity_philhealth(rs.getString("doc_identity_philhealth"));
	        application.setDoc_identity_philhealth_no(rs.getString("doc_identity_philhealth_no"));
	        application.setDoc_identity_tin(rs.getString("doc_identity_tin"));
	        application.setDoc_identity_tin_no(rs.getString("doc_identity_tin_no"));
	        application.setDoc_identity_driverslicense(rs.getString("doc_identity_driverslicense"));
	        application.setDoc_identity_driverslicense_no(rs.getString("doc_identity_driverslicense_no"));
	        application.setDoc_identity_passport(rs.getString("doc_identity_passport"));
	        application.setDoc_identity_passport_no(rs.getString("doc_identity_passport_no"));
	        application.setDoc_identity_companyid(rs.getString("doc_identity_companyid"));
	        application.setDoc_identity_companyid_no(rs.getString("doc_identity_companyid_no"));
	        application.setDoc_identity_others(rs.getString("doc_identity_others"));
	        application.setDoc_identity_others_no(rs.getString("doc_identity_others_no"));
	        application.setDoc_address_electricity(rs.getString("doc_address_electricity"));
	        application.setDoc_address_electricity_no(rs.getString("doc_address_electricity_no"));
	        application.setDoc_address_water(rs.getString("doc_address_water"));
	        application.setDoc_address_water_no(rs.getString("doc_address_water_no"));
	        application.setDoc_address_isp(rs.getString("doc_address_isp"));
	        application.setDoc_address_isp_no(rs.getString("doc_address_isp_no"));
	        application.setDoc_address_cable(rs.getString("doc_address_cable"));
	        application.setDoc_address_cable_no(rs.getString("doc_address_cable_no"));
	        application.setDoc_address_telecom(rs.getString("doc_address_telecom"));
	        application.setDoc_address_telecom_no(rs.getString("doc_address_telecom_no"));
	        application.setDoc_address_bankloan(rs.getString("doc_address_bankloan"));
	        application.setDoc_address_bankloan(rs.getString("doc_address_bankloan_no"));
	        application.setDoc_address_others(rs.getString("doc_address_others"));
	        application.setDoc_address_others_no(rs.getString("doc_address_others_no"));
	        application.setDoc_income_bankstatement(rs.getString("doc_income_bankstatement"));
	        application.setDoc_income_bankstatement_no(rs.getString("doc_income_bankstatement_no"));
	        application.setDoc_income_payslip(rs.getString("doc_income_payslip"));
	        application.setDoc_income_payslip_no(rs.getString("doc_income_payslip_no"));
	        application.setDoc_income_security(rs.getString("doc_income_security"));
	        application.setDoc_income_security_no(rs.getString("doc_income_security_no"));
	        application.setDoc_income_bonds(rs.getString("doc_income_bonds"));
	        application.setDoc_income_bonds_no(rs.getString("doc_income_bonds_no"));
	        application.setDoc_income_stockcert(rs.getString("doc_income_stockcert"));
	        application.setDoc_income_stockcert_no(rs.getString("doc_income_stockcert_no"));
	        application.setDoc_income_companyownership(rs.getString("doc_income_companyownership"));
	        application.setDoc_income_companyownership_no(rs.getString("doc_income_companyownership_no"));
	        application.setDoc_income_others(rs.getString("doc_income_others"));
	        application.setDoc_income_others_no(rs.getString("doc_income_others"));
	        application.setDoc_income_autocharge(rs.getString("doc_income_autocharge"));
	        application.setDoc_income_autocharge_no(rs.getString("doc_income_autocharge"));
	        application.setDoc_terms_sig1(rs.getString("doc_terms_sig1"));
	        application.setDoc_terms_sig2(rs.getString("doc_terms_sig2"));
	        application.setDoc_terms_sig3(rs.getString("doc_terms_sig3"));
	        application.setStep1Done(rs.getString("step1Done"));
	        application.setStep2Done(rs.getString("step2Done"));
	        application.setStep3Done(rs.getString("step3Done"));
	        application.setDecline_remarks(rs.getString("decline_remarks"));
	        application.setIspushed(rs.getString("ispushed"));
	        application.setEditedBy(rs.getString("edited_by"));
	        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
	        Date dateStr = new Date(Long.parseLong(rs.getString("created")));
	        application.setCreated(formatter.format(dateStr).toString());
	        application.setUpdate(rs.getString("updated"));
	        application.setVersion(rs.getString("version"));
	       
	        return application;
	        
	    }
	}*/
	private static final class ApplicationMapper implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setTitle(rs.getString("title"));
	        application.setBirthplace(rs.getString("birthplace"));
	        application.setOccupation(rs.getString("occupation"));
	        application.setPosition_level(rs.getString("position_level"));
	        application.setPosition_title(rs.getString("position_title"));
	        application.setTenure(rs.getString("tenure"));
	        application.setMonthly_income(rs.getString("monthly_income"));
	        application.setCompany_name(rs.getString("company_name"));
	        application.setBusiness_nature(rs.getString("business_nature"));
	        application.setTin(rs.getString("tin"));
	        application.setSss(rs.getString("sss"));
	        application.setCivil_status(rs.getString("civil_status"));
	        application.setGender(rs.getString("gender"));
	        application.setDependents(rs.getString("dependents"));
	        application.setOther_phone_subscriptions(rs.getString("other_phone_subscriptions"));
	        application.setFundssource(rs.getString("fundssource"));
	        application.setSpouse_firstName(rs.getString("spouse_firstName"));
	        application.setSpouse_middleName(rs.getString("spouse_middleName"));
	        application.setSpouse_lastName(rs.getString("spouse_lastName"));
	        application.setSpouse_birthday(rs.getString("spouse_birthday"));
	        application.setMothers_maidenname(rs.getString("mothers_maidenname"));
	        application.setPhone_id(rs.getString("phone_id"));
	        application.setPlan_code(rs.getString("plan_code"));
	        application.setStatus(rs.getString("status"));
	        application.setRef_no(rs.getString("ref_no"));
	        application.setEmail(rs.getString("email"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setMiddleName(rs.getString("middleName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setBirthday(rs.getString("birthday"));
	        application.setType(rs.getString("type"));
	        application.setImage(rs.getString("image"));
	        application.setTermination_image(rs.getString("termination_image"));
	        application.setAddRegion(rs.getString("addRegion"));
	        application.setAddCity(rs.getString("addCity"));
	        application.setAddBrgy(rs.getString("addBrgy"));
	        application.setAddLine1(rs.getString("addLine1"));
	        application.setAddLine2(rs.getString("addLine2"));
	        application.setZipCode(rs.getString("zipCode"));
	        application.setAddWorkRegion(rs.getString("addWorkRegion"));
	        application.setAddWorkCity(rs.getString("addWorkCity"));
	        application.setAddWorkBrgy(rs.getString("addWorkBrgy"));
	        application.setAddWorkLine1(rs.getString("addWorkLine1"));
	        application.setAddWorkLine2(rs.getString("addWorkLine2"));
	        application.setWorkZipCode(rs.getString("workZipCode"));
	        application.setSendBillTo(rs.getString("sendBillTo"));
	        application.setTelephone(rs.getString("telephone"));
	        application.setMobile(rs.getString("mobile"));
	        application.setPhone_retrieval_type(rs.getString("phone_retrieval_type"));
	        application.setBusiness_center_name(rs.getString("business_center_name"));
	        application.setBusiness_center_lng(rs.getString("business_center_lng"));
	        application.setBusiness_center_lat(rs.getString("business_center_lat"));
	        application.setPayment_type(rs.getString("payment_type"));
	        application.setCc_number(rs.getString("cc_number"));
	        application.setCc_bank(rs.getString("cc_bank"));
	        application.setPp_email(rs.getString("pp_email"));
	        application.setPp_refNo(rs.getString("pp_refno"));
	        application.setCod_amt(rs.getString("cod_amt"));
	        application.setDoc_identity_sss(rs.getString("doc_identity_sss"));
	        application.setDoc_identity_sss_no(rs.getString("doc_identity_sss_no"));
	        application.setDoc_identity_pagibig(rs.getString("doc_identity_pagibig"));
	        application.setDoc_identity_pagibig_no(rs.getString("doc_identity_pagibig_no"));
	        application.setDoc_identity_philhealth(rs.getString("doc_identity_philhealth"));
	        application.setDoc_identity_philhealth_no(rs.getString("doc_identity_philhealth_no"));
	        application.setDoc_identity_tin(rs.getString("doc_identity_tin"));
	        application.setDoc_identity_tin_no(rs.getString("doc_identity_tin_no"));
	        application.setDoc_identity_driverslicense(rs.getString("doc_identity_driverslicense"));
	        application.setDoc_identity_driverslicense_no(rs.getString("doc_identity_driverslicense_no"));
	        application.setDoc_identity_passport(rs.getString("doc_identity_passport"));
	        application.setDoc_identity_passport_no(rs.getString("doc_identity_passport_no"));
	        application.setDoc_identity_companyid(rs.getString("doc_identity_companyid"));
	        application.setDoc_identity_companyid_no(rs.getString("doc_identity_companyid_no"));
	        application.setDoc_identity_others(rs.getString("doc_identity_others"));
	        application.setDoc_identity_others_no(rs.getString("doc_identity_others_no"));
	        application.setDoc_address_electricity(rs.getString("doc_address_electricity"));
	        application.setDoc_address_electricity_no(rs.getString("doc_address_electricity_no"));
	        application.setDoc_address_water(rs.getString("doc_address_water"));
	        application.setDoc_address_water_no(rs.getString("doc_address_water_no"));
	        application.setDoc_address_isp(rs.getString("doc_address_isp"));
	        application.setDoc_address_isp_no(rs.getString("doc_address_isp_no"));
	        application.setDoc_address_cable(rs.getString("doc_address_cable"));
	        application.setDoc_address_cable_no(rs.getString("doc_address_cable_no"));
	        application.setDoc_address_telecom(rs.getString("doc_address_telecom"));
	        application.setDoc_address_telecom_no(rs.getString("doc_address_telecom_no"));
	        application.setDoc_address_bankloan(rs.getString("doc_address_bankloan"));
	        application.setDoc_address_bankloan_no(rs.getString("doc_address_bankloan_no"));
	        application.setDoc_address_others(rs.getString("doc_address_others"));
	        application.setDoc_address_others_no(rs.getString("doc_address_others_no"));
	        application.setDoc_income_bankstatement(rs.getString("doc_income_bankstatement"));
	        application.setDoc_income_bankstatement_no(rs.getString("doc_income_bankstatement_no"));
	        application.setDoc_income_payslip(rs.getString("doc_income_payslip"));
	        application.setDoc_income_payslip_no(rs.getString("doc_income_payslip_no"));
	        application.setDoc_income_security(rs.getString("doc_income_security"));
	        application.setDoc_income_security_no(rs.getString("doc_income_security_no"));
	        application.setDoc_income_bonds(rs.getString("doc_income_bonds"));
	        application.setDoc_income_bonds_no(rs.getString("doc_income_bonds_no"));
	        application.setDoc_income_stockcert(rs.getString("doc_income_stockcert"));
	        application.setDoc_income_stockcert_no(rs.getString("doc_income_stockcert_no"));
	        application.setDoc_income_companyownership(rs.getString("doc_income_companyownership"));
	        application.setDoc_income_companyownership_no(rs.getString("doc_income_companyownership_no"));
	        application.setDoc_income_others(rs.getString("doc_income_others"));
	        application.setDoc_income_others_no(rs.getString("doc_income_others_no"));
	        application.setDoc_income_autocharge(rs.getString("doc_income_autocharge"));
	        application.setDoc_income_autocharge_no(rs.getString("doc_income_autocharge_no"));
	        application.setDoc_terms_sig1(rs.getString("doc_terms_sig1"));
	        application.setDoc_terms_sig2(rs.getString("doc_terms_sig2"));
	        application.setDoc_terms_sig3(rs.getString("doc_terms_sig3"));
	        application.setStep0Done(rs.getString("step0Done"));
	        application.setStep1Done(rs.getString("step1Done"));
	        application.setStep2Done(rs.getString("step2Done"));
	        application.setStep3Done(rs.getString("step3Done"));
	        application.setIsaddressDone(rs.getString("isaddressDone"));
	        application.setIsidentityDone(rs.getString("isidentityDone"));
	        application.setIsincomeDone(rs.getString("isincomeDone"));
	        application.setContact_person(rs.getString("contact_person"));
	        application.setContact_number(rs.getString("contact_number"));
	        application.setDecline_remarks(rs.getString("decline_remarks"));
	        application.setIspushed(rs.getString("ispushed"));
	        application.setEditedBy(rs.getString("edited_by"));
	       
	        application.setCreated(rs.getString("created"));
	        application.setUpdate(rs.getString("updated"));
	        application.setVersion(rs.getString("version"));
	        application.setApplication_type(rs.getString("application_type"));
	        application.setUser_id(rs.getString("user_id"));
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper5 implements RowMapper<Application> {

		private int ctr;
	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	

	        Application application = new Application();
	        
	        
	        
	        application.setCount(++ctr);
	        application.setApplication_id(rs.getString("application_id"));
	        application.setTitle(rs.getString("title"));
	        application.setBirthplace(rs.getString("birthplace"));
	        application.setOccupation(rs.getString("occupation"));
	        application.setPosition_level(rs.getString("position_level"));
	        application.setPosition_title(rs.getString("position_title"));
	        application.setTenure(rs.getString("tenure"));
	        application.setMonthly_income(rs.getString("monthly_income"));
	        application.setCompany_name(rs.getString("company_name"));
	        application.setBusiness_nature(rs.getString("business_nature"));
	        application.setTin(rs.getString("tin"));
	        application.setSss(rs.getString("sss"));
	        application.setCivil_status(rs.getString("civil_status"));
	        application.setGender(rs.getString("gender"));
	        application.setDependents(rs.getString("dependents"));
	        application.setOther_phone_subscriptions(rs.getString("other_phone_subscriptions"));
	        application.setFundssource(rs.getString("fundssource"));
	        application.setSpouse_firstName(rs.getString("spouse_firstName"));
	        application.setSpouse_middleName(rs.getString("spouse_middleName"));
	        application.setSpouse_lastName(rs.getString("spouse_lastName"));
	        application.setSpouse_birthday(rs.getString("spouse_birthday"));
	        application.setMothers_maidenname(rs.getString("mothers_maidenname"));
	        application.setPhone_id(rs.getString("phone_model"));
	        application.setPlan_code(rs.getString("plan_name"));
	        application.setStatus(rs.getString("status"));
	        application.setRef_no(rs.getString("ref_no"));
	        application.setEmail(rs.getString("email"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setMiddleName(rs.getString("middleName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setBirthday(rs.getString("birthday"));
	        application.setType(rs.getString("type"));
	        application.setImage(rs.getString("image"));
	        application.setTermination_image(rs.getString("termination_image"));
	        application.setAddRegion(rs.getString("addRegion"));
	        application.setAddCity(rs.getString("addCity"));
	        application.setAddBrgy(rs.getString("addBrgy"));
	        application.setAddLine1(rs.getString("addLine1"));
	        application.setAddLine2(rs.getString("addLine2"));
	        application.setZipCode(rs.getString("zipCode"));
	        application.setAddWorkRegion(rs.getString("addWorkRegion"));
	        application.setAddWorkCity(rs.getString("addWorkCity"));
	        application.setAddWorkBrgy(rs.getString("addWorkBrgy"));
	        application.setAddWorkLine1(rs.getString("addWorkLine1"));
	        application.setAddWorkLine2(rs.getString("addWorkLine2"));
	        application.setWorkZipCode(rs.getString("workZipCode"));
	        application.setSendBillTo(rs.getString("sendBillTo"));
	        application.setTelephone(rs.getString("telephone"));
	        application.setMobile(rs.getString("mobile"));
	        application.setPhone_retrieval_type(rs.getString("phone_retrieval_type"));
	        application.setBusiness_center_name(rs.getString("business_center_name"));
	        application.setBusiness_center_lng(rs.getString("business_center_lng"));
	        application.setBusiness_center_lat(rs.getString("business_center_lat"));
	        application.setPayment_type(rs.getString("payment_type"));
	        application.setCc_number(rs.getString("cc_number"));
	        application.setCc_bank(rs.getString("cc_bank"));
	        application.setPp_email(rs.getString("pp_email"));
	        application.setPp_refNo(rs.getString("pp_refno"));
	        application.setCod_amt(rs.getString("cod_amt"));
	        application.setDoc_identity_sss(rs.getString("doc_identity_sss"));
	        application.setDoc_identity_sss_no(rs.getString("doc_identity_sss_no"));
	        application.setDoc_identity_pagibig(rs.getString("doc_identity_pagibig"));
	        application.setDoc_identity_pagibig_no(rs.getString("doc_identity_pagibig_no"));
	        application.setDoc_identity_philhealth(rs.getString("doc_identity_philhealth"));
	        application.setDoc_identity_philhealth_no(rs.getString("doc_identity_philhealth_no"));
	        application.setDoc_identity_tin(rs.getString("doc_identity_tin"));
	        application.setDoc_identity_tin_no(rs.getString("doc_identity_tin_no"));
	        application.setDoc_identity_driverslicense(rs.getString("doc_identity_driverslicense"));
	        application.setDoc_identity_driverslicense_no(rs.getString("doc_identity_driverslicense_no"));
	        application.setDoc_identity_passport(rs.getString("doc_identity_passport"));
	        application.setDoc_identity_passport_no(rs.getString("doc_identity_passport_no"));
	        application.setDoc_identity_companyid(rs.getString("doc_identity_companyid"));
	        application.setDoc_identity_companyid_no(rs.getString("doc_identity_companyid_no"));
	        application.setDoc_identity_others(rs.getString("doc_identity_others"));
	        application.setDoc_identity_others_no(rs.getString("doc_identity_others_no"));
	        application.setDoc_address_electricity(rs.getString("doc_address_electricity"));
	        application.setDoc_address_electricity_no(rs.getString("doc_address_electricity_no"));
	        application.setDoc_address_water(rs.getString("doc_address_water"));
	        application.setDoc_address_water_no(rs.getString("doc_address_water_no"));
	        application.setDoc_address_isp(rs.getString("doc_address_isp"));
	        application.setDoc_address_isp_no(rs.getString("doc_address_isp_no"));
	        application.setDoc_address_cable(rs.getString("doc_address_cable"));
	        application.setDoc_address_cable_no(rs.getString("doc_address_cable_no"));
	        application.setDoc_address_telecom(rs.getString("doc_address_telecom"));
	        application.setDoc_address_telecom_no(rs.getString("doc_address_telecom_no"));
	        application.setDoc_address_bankloan(rs.getString("doc_address_bankloan"));
	        application.setDoc_address_bankloan_no(rs.getString("doc_address_bankloan_no"));
	        application.setDoc_address_others(rs.getString("doc_address_others"));
	        application.setDoc_address_others_no(rs.getString("doc_address_others_no"));
	        application.setDoc_income_bankstatement(rs.getString("doc_income_bankstatement"));
	        application.setDoc_income_bankstatement_no(rs.getString("doc_income_bankstatement_no"));
	        application.setDoc_income_payslip(rs.getString("doc_income_payslip"));
	        application.setDoc_income_payslip_no(rs.getString("doc_income_payslip_no"));
	        application.setDoc_income_security(rs.getString("doc_income_security"));
	        application.setDoc_income_security_no(rs.getString("doc_income_security_no"));
	        application.setDoc_income_bonds(rs.getString("doc_income_bonds"));
	        application.setDoc_income_bonds_no(rs.getString("doc_income_bonds_no"));
	        application.setDoc_income_stockcert(rs.getString("doc_income_stockcert"));
	        application.setDoc_income_stockcert_no(rs.getString("doc_income_stockcert_no"));
	        application.setDoc_income_companyownership(rs.getString("doc_income_companyownership"));
	        application.setDoc_income_companyownership_no(rs.getString("doc_income_companyownership_no"));
	        application.setDoc_income_others(rs.getString("doc_income_others"));
	        application.setDoc_income_others_no(rs.getString("doc_income_others_no"));
	        application.setDoc_income_autocharge(rs.getString("doc_income_autocharge"));
	        application.setDoc_income_autocharge_no(rs.getString("doc_income_autocharge_no"));
	        application.setDoc_terms_sig1(rs.getString("doc_terms_sig1"));
	        application.setDoc_terms_sig2(rs.getString("doc_terms_sig2"));
	        application.setDoc_terms_sig3(rs.getString("doc_terms_sig3"));
	        application.setStep0Done(rs.getString("step0Done"));
	        application.setStep1Done(rs.getString("step1Done"));
	        application.setStep2Done(rs.getString("step2Done"));
	        application.setStep3Done(rs.getString("step3Done"));
	        application.setIsaddressDone(rs.getString("isaddressDone"));
	        application.setIsidentityDone(rs.getString("isidentityDone"));
	        application.setIsincomeDone(rs.getString("isincomeDone"));
	        application.setContact_person(rs.getString("contact_person"));
	        application.setContact_number(rs.getString("contact_number"));
	        application.setDecline_remarks(rs.getString("decline_remarks"));
	        application.setIspushed(rs.getString("ispushed"));
	        application.setEditedBy(rs.getString("edited_by"));
	       
	        application.setCreated(rs.getString("created"));
	        application.setUpdate(rs.getString("updated"));
	        application.setVersion(rs.getString("version"));
	        application.setApplication_type(rs.getString("application_type"));
	        application.setUser_id(rs.getString("user_id"));

	        if(application.getApplication_type().equalsIgnoreCase("personal")){
	            
		        Gson gson2 = new Gson();
		        
		        System.out.println(application.getAddLine1());
		        AddressDetails add = gson2.fromJson(application.getAddLine1(), AddressDetails.class);
	        	SpouseDetails spouse = gson2.fromJson(application.getSpouse_firstName(), SpouseDetails.class);
	        	application.setHomeownership(add.getHomeownership());
	        	application.setYearsofstay(add.getYearsofstay());
	        	application.setHousenumber(add.getHousenumber());
	        	application.setBuilding(add.getBuilding());
	        	application.setStreetname(add.getStreetname());
	        	application.setVillage(add.getVillage());
	        	application.setSpousefullname(spouse.getSpousefullname());
	        	application.setSpousecontact(spouse.getSpousecontact());
	        	application.setSpousebirthday(spouse.getSpousebirthday());
	        	
	        	
	        }else{
	            
		        Gson gson2 = new Gson();
		        System.out.println(application.getAddLine1());
	            AddressDetails add = gson2.fromJson(application.getAddLine1(), AddressDetails.class);
	            CompanyDetails comp = gson2.fromJson(application.getCompany_name(), CompanyDetails.class);
	        	application.setHousenumber(add.getHousenumber());
	        	application.setBuilding(add.getBuilding());
	        	application.setStreetname(add.getStreetname());
	        	application.setVillage(add.getVillage());
	        	application.setCompanyname(comp.getCompanyname());
	        	application.setCompanynature(comp.getCompanynature());
	        	application.setCompanyauth(comp.getCompanyauth());
	        	application.setCompanypos(comp.getCompanypos());
	        	
	        }
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper2 implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        Phone phone = new Phone();
	        Plan plan = new Plan();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setPlan_code((rs.getString("plan_name")));
	        application.setRef_no((rs.getString("ref_no")));
	        application.setPhone_id((rs.getString("phone_model")));
	        application.setStatus(rs.getString("status"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setCreated(rs.getString("created"));
	        
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper3 implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setUser_id(rs.getString("user_id"));
	        application.setStatus(rs.getString("status"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setCreated(rs.getString("created"));
	        application.setRef_no(rs.getString("ref_no"));
	        
	        return application;
	        
	    }
	}
	
	public ArrayList<Application> ViewAppDetail(String id){
		System.out.println(id);
		String sql = "SELECT addLine1 FROM application WHERE  application_id = ?";
		String result = "-1";
		List<Map<String,Object>> rows  =  jdbcTemplate.queryForList(sql,id);
		//System.out.println(isExisting);

		
		ArrayList<Application> applist = new ArrayList<Application>();
		
		for(Map row:rows)
		{
		
			Application app = new Application();
			
			
			result = (String)(row.get("details"));
			app.setAddLine1(result);
			
			System.out.println("details: "+result);
			
//			Gson gson = new Gson();
//			
//			user = gson.fromJson(result,UserDetails.class);
//			
//			result = gson.toJson(user);
				
			applist.add(app);
		}
		
		return applist;

	}
	
	
	
	
	public Collection getApplication(Application application, String id){
		return jdbcTemplate.query("SELECT * FROM application WHERE application_id=?", new ApplicationMapper3(),id);
		
	}

	public Collection getReturnApplication(Application application){
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id WHERE a.application_type IS NOT NULL AND a.status = ?" , new ApplicationMapper5(),3);
		
	}
	public Collection getEncodedApplication(Application application){
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id WHERE a.application_type IS NOT NULL AND a.status = ?",new ApplicationMapper5(),1);
		
	}
	
	public Collection getOngoingApplication(Application application){
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id WHERE a.application_type IS NOT NULL AND a.status = ?", new ApplicationMapper5(),2);
		
	}
	public Collection getRecentApplication(Application application){
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id where application_type is not null" , new ApplicationMapper5());
			
		
	}
	public Collection getStatus(Application application){
		return jdbcTemplate.query("SELECT a.application_id, a.firstName, a.lastName, a.status, a.created, b.plan_name, c.phone_model FROM application a INNER JOIN plan b ON a.plan_code = b.plan_id INNER JOIN phone c ON a.phone_id = c.phone_id WHERE status =?", new ApplicationMapper2(),application.getStatus());
	}

}
