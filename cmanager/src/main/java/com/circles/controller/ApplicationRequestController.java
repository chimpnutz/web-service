package com.circles.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.ServletContextAware;

import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.CommentDAOImpl;
import com.circles.utils.*;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ApplicationRequestController {
	
	@Autowired
	private ApplicationDAOImpl applicationDAOImpl;
	
	@Autowired
	private CommentDAOImpl commentDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	
	@RequestMapping(value="/getApplications", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getApplications(){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		Collection s = applicationDAOImpl.findAllApplication();		

		
		return s;
		
	}

	@RequestMapping(value="/getApplication", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getExistingApplication(
			@RequestParam(value="applicationid",required=true) String applicationid) throws SQLException{
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection s = null;	
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication(application);
		}
		return s;
		
	}
	@RequestMapping(value="/uploadForm")
	public String uploadFormApplication(
		) throws SQLException{
		return "manager/simpleUploader";
		
	}
	/*@RequestMapping(value="/uploadApplication")
	public @ResponseBody String uploadApplication(
			HttpServletRequest request,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="birthplace",required=false) String birthplace,
			@RequestParam(value="occupation",required=false) String occupation,
			@RequestParam(value="position_level",required=false) String position_level,
			@RequestParam(value="position_title",required=false) String position_title,
			@RequestParam(value="tenure",required=false) String tenure,
			@RequestParam(value="monthly_income",required=false) String monthly_income,
			@RequestParam(value="company_name",required=false) String company_name,
			@RequestParam(value="business_nature",required=false) String business_nature,
			@RequestParam(value="tin",required=false) String tin,
			@RequestParam(value="sss",required=false) String sss,
			@RequestParam(value="civil_status",required=false) String civil_status,
			@RequestParam(value="gender",required=false) String gender,
			@RequestParam(value="dependents",required=false) String dependents,
			@RequestParam(value="other_phone_subscriptions",required=false) String other_phone_subscriptions,
			@RequestParam(value="fundssource",required=false) String fundssource,
			@RequestParam(value="spouse_firstName",required=false) String spouse_firstName,
			@RequestParam(value="spouse_middleName",required=false) String spouse_middleName,
			@RequestParam(value="spouse_lastName",required=false) String spouse_lastName,
			@RequestParam(value="spouse_birthday",required=false) String spouse_birthday,
			@RequestParam(value="mothers_maidenname",required=false) String mothers_maidenname,
			@RequestParam(value="phone_code",required=false) String phone_code,
			@RequestParam(value="plan_code",required=false) String plan_code,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="ref_no",required=false) String ref_no,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="firstName",required=false) String firstName,
			@RequestParam(value="middleName",required=false) String middleName,
			@RequestParam(value="lastName",required=false) String lastName,
			@RequestParam(value="birthday",required=false) String birthday,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="image",required=false) MultipartFile image
			
			)throws NullPointerException{
        Application application = new Application();
        Comment comment = new Comment();
        String applicationUID = "{"+UUID.randomUUID().toString()+"}";
        application.setApplication_id(applicationUID);
        int isExisting = applicationDAOImpl.checkIfExists(application);
        if(isExisting == 1){
        	applicationUID = "{"+UUID.randomUUID().toString()+"}";
		}
        
        String commentUID = "{"+UUID.randomUUID().toString()+"}";
        comment.setId(commentUID);
        isExisting = commentDAOImpl.checkIfExists(comment);
        if(isExisting == 1){
        	commentUID = "{"+UUID.randomUUID().toString()+"}";
		}
        
        
        
        application.setApplication_id(application_id);
        application.setTitle(title);
        application.setBirthplace(birthplace);
        application.setOccupation(occupation);
        application.setPosition_level(position_level);
        application.setPosition_title(position_title);
        application.setTenure(tenure);
        application.setMonthly_income(monthly_income);
        application.setCompany_name(company_name);
        application.setBusiness_nature(business_nature);
        application.setTin(tin);
        application.setSss(sss);
        application.setCivil_status(civil_status);
        application.setGender(gender);
        application.setDependents(dependents);
        application.setOther_phone_subscriptions(other_phone_subscriptions);
        application.setFundssource(fundssource);
        application.setSpouse_firstName(spouse_firstName);
        application.setSpouse_middleName(spouse_middleName);
        application.setSpouse_lastName(spouse_lastName);
        application.setSpouse_birthday(spouse_birthday);
        application.setMothers_maidenname(mothers_maidenname);
        application.setPhone_code(phone_code);
        application.setPlan_code(plan_code);
        application.setStatus("0");
        application.setRef_no(ref_no);
        application.setEmail(email);
        application.setFirstName(firstName);
        application.setMiddleName(middleName);
        application.setLastName(lastName);
        application.setBirthday(birthday);
        application.setType(type);
        application.setImage(image.getOriginalFilename());


        String date  = ""+new Date().getTime()+"";
		//System.out.println(date);
        application.setCreated(date);
        application.setUpdate(date);

		int	s= 0;
		//applicationDAOImpl.save(application)
		ServletContext servletContext = request.getSession().getServletContext();
		String filepath ="C:/Users/PayExchange/workspace/cmanager/src/main/webapp/resources/uploaded/";
		//System.out.println(filepath);
		File dest = new File(filepath+image.getOriginalFilename());
		 try {
	            image.transferTo(dest);
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	            return "File uploaded failed:" + image.getOriginalFilename();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "File uploaded failed:" + image.getOriginalFilename();
	        }
		int s2 = 0;


		 	
		
		if(s ==1 ){

				return "true";

		}
		return "true";
		
	}*/
	
	public boolean FileUpload(MultipartFile fileName){
		String filepath ="C:/Users/PayExchange/workspace/cmanager/src/main/webapp/resources/uploaded/";
		
		File dest = new File(filepath+fileName.getOriginalFilename());
		 try {
	            fileName.transferTo(dest);
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	            return false;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
		return false;
	}
	
	@RequestMapping(value="/saveApplication",produces="application/json")
	public @ResponseBody boolean saveApplication(
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="birthplace",required=false) String birthplace,
			@RequestParam(value="occupation",required=false) String occupation,
			@RequestParam(value="position_level",required=false) String position_level,
			@RequestParam(value="position_title",required=false) String position_title,
			@RequestParam(value="tenure",required=false) String tenure,
			@RequestParam(value="monthly_income",required=false) String monthly_income,
			@RequestParam(value="company_name",required=false) String company_name,
			@RequestParam(value="business_nature",required=false) String business_nature,
			@RequestParam(value="tin",required=false) String tin,
			@RequestParam(value="sss",required=false) String sss,
			@RequestParam(value="civil_status",required=false) String civil_status,
			@RequestParam(value="gender",required=false) String gender,
			@RequestParam(value="dependents",required=false) String dependents,
			@RequestParam(value="other_phone_subscriptions",required=false) String other_phone_subscriptions,
			@RequestParam(value="fundssource",required=false) String fundssource,
			@RequestParam(value="spouse_firstName",required=false) String spouse_firstName,
			@RequestParam(value="spouse_middleName",required=false) String spouse_middleName,
			@RequestParam(value="spouse_lastName",required=false) String spouse_lastName,
			@RequestParam(value="spouse_birthday",required=false) String spouse_birthday,
			@RequestParam(value="mothers_maidenname",required=false) String mothers_maidenname,
			@RequestParam(value="phone_code",required=false) String phone_code,
			@RequestParam(value="plan_code",required=false) String plan_code,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="ref_no",required=false) String ref_no,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="firstName",required=false) String firstName,
			@RequestParam(value="middleName",required=false) String middleName,
			@RequestParam(value="lastName",required=false) String lastName,
			@RequestParam(value="birthday",required=false) String birthday,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="image",required=false) MultipartFile image,
			@RequestParam(value="termination_image",required=false) MultipartFile termination_image,
			@RequestParam(value="addRegion",required=false) String addRegion,
			@RequestParam(value="addCity",required=false) String addCity,
			@RequestParam(value="addBrgy",required=false) String addBrgy,
			@RequestParam(value="addLine1",required=false) String addLine1,
			@RequestParam(value="addLine2",required=false) String addLine2,
			@RequestParam(value="zipCode",required=false) String zipCode,
			@RequestParam(value="addWorkRegion",required=false) String addWorkRegion,
			@RequestParam(value="addWorkCity",required=false) String addWorkCity,
			@RequestParam(value="addWorkBrgy",required=false) String addWorkBrgy,
			@RequestParam(value="addWorLine1",required=false) String addWorkLine1,
			@RequestParam(value="addWorLine2",required=false) String addWorkLine2,
			@RequestParam(value="workZipCode",required=false) String workZipCode,
			@RequestParam(value="sendBillTo",required=false) String sendBillTo,
			@RequestParam(value="telephone",required=false) String telephone,
			@RequestParam(value="mobile",required=false) String mobile,
			@RequestParam(value="phone_retrieval_type",required=false) String phone_retrieval_type,
			@RequestParam(value="business_center_name",required=false) String business_center_name,
			@RequestParam(value="business_center_lng",required=false) String business_center_lng,
			@RequestParam(value="business_center_lat",required=false) String business_center_lat,
			@RequestParam(value="payment_type",required=false) String payment_type,
			@RequestParam(value="cc_number",required=false) String cc_number,
			@RequestParam(value="cc_bank",required=false) String cc_bank,
			@RequestParam(value="pp_email",required=false) String pp_email,
			@RequestParam(value="pp_refno",required=false) String pp_refno,
			@RequestParam(value="cod_amt",required=false) String cod_amt,
			@RequestParam(value="doc_identity_sss",required=false) MultipartFile doc_identity_sss,
			@RequestParam(value="doc_identity_sss_no",required=false) String doc_identity_sss_no,
			@RequestParam(value="doc_identity_pagibig",required=false) MultipartFile doc_identity_pagibig,
			@RequestParam(value="doc_identity_pagibig_no",required=false) String doc_identity_pagibig_no,
			@RequestParam(value="doc_identity_philhealth",required=false) MultipartFile doc_identity_philhealth,
			@RequestParam(value="doc_identity_philhealth_no",required=false) String doc_identity_philhealth_no,
			@RequestParam(value="doc_identity_tin",required=false) MultipartFile doc_identity_tin,
			@RequestParam(value="doc_identity_tin_no",required=false) String doc_identity_tin_no,
			@RequestParam(value="doc_identity_driverslicense",required=false) MultipartFile doc_identity_driverslicense,
			@RequestParam(value="doc_identity_driverslicense_no",required=false) String doc_identity_driverslicense_no,
			@RequestParam(value="doc_identity_passport",required=false) MultipartFile doc_identity_passport,
			@RequestParam(value="doc_identity_passport_no",required=false) String doc_identity_passport_no,
			@RequestParam(value="doc_identity_companyid",required=false) MultipartFile doc_identity_companyid,
			@RequestParam(value="doc_identity_companyid_no",required=false) String doc_identity_companyid_no,
			@RequestParam(value="doc_identity_others",required=false) MultipartFile doc_identity_others,
			@RequestParam(value="doc_identity_others_no",required=false) String doc_identity_others_no,
			@RequestParam(value="doc_address_electricity",required=false) MultipartFile doc_address_electricity,
			@RequestParam(value="doc_address_electricity_no",required=false) String doc_address_electricity_no,
			@RequestParam(value="doc_address_water",required=false) MultipartFile doc_address_water,
			@RequestParam(value="doc_address_water_no",required=false) String doc_address_water_no,
			@RequestParam(value="doc_address_isp",required=false) MultipartFile doc_address_isp,
			@RequestParam(value="doc_address_isp_no",required=false) String doc_address_isp_no,
			@RequestParam(value="doc_address_cable",required=false) MultipartFile doc_address_cable,
			@RequestParam(value="doc_address_cable_no",required=false) String doc_address_cable_no,
			@RequestParam(value="doc_address_telecom",required=false) MultipartFile doc_address_telecom,
			@RequestParam(value="doc_address_telecom_no",required=false) String doc_address_telecom_no,
			@RequestParam(value="doc_address_bankloan",required=false) MultipartFile doc_address_bankloan,
			@RequestParam(value="doc_address_bankloan_no",required=false) String doc_address_bankloan_no,
			@RequestParam(value="doc_address_others",required=false) MultipartFile doc_address_others,
			@RequestParam(value="doc_address_others_no",required=false) String doc_address_others_no,
			@RequestParam(value="doc_income_bankstatement",required=false) MultipartFile doc_income_bankstatement,
			@RequestParam(value="doc_income_bankstatement_no",required=false) String doc_income_bankstatement_no,
			@RequestParam(value="doc_income_payslip",required=false) MultipartFile doc_income_payslip,
			@RequestParam(value="doc_income_payslip_no",required=false) String doc_income_payslip_no,
			@RequestParam(value="doc_income_security",required=false) MultipartFile doc_income_security,
			@RequestParam(value="doc_income_secuirty_no",required=false) String doc_income_security_no,
			@RequestParam(value="doc_income_bonds",required=false) MultipartFile doc_income_bonds,
			@RequestParam(value="doc_income_bonds_no",required=false) String doc_income_bonds_no,
			@RequestParam(value="doc_income_stockcert",required=false) MultipartFile doc_income_stockcert,
			@RequestParam(value="doc_income_stockcert_no",required=false) String doc_income_stockcert_no,
			@RequestParam(value="doc_income_companyownership",required=false) MultipartFile doc_income_companyownership,
			@RequestParam(value="doc_income_companyownership_no",required=false) String doc_income_companyownership_no,
			@RequestParam(value="doc_income_others",required=false) MultipartFile doc_income_others,
			@RequestParam(value="doc_income_others_no",required=false) String doc_income_others_no,
			@RequestParam(value="doc_income_autocharge",required=false) MultipartFile doc_income_autocharge,
			@RequestParam(value="doc_income_autocharge_no",required=false) String doc_income_autocharge_no,
			@RequestParam(value="doc_terms_sig1",required=false) MultipartFile doc_terms_sig1,
			@RequestParam(value="doc_terms_sig2",required=false) MultipartFile doc_terms_sig2,
			@RequestParam(value="doc_terms_sig3",required=false) MultipartFile doc_terms_sig3,
			@RequestParam(value="step1Done",required=false) String step1Done,
			@RequestParam(value="step2Done",required=false) String step2Done,
			@RequestParam(value="step3Done",required=false) String step3Done,
			@RequestParam(value="decline_remarks",required=false) String decline_remarks,
			@RequestParam(value="comment_id",required=false) String id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed_comment,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="version",required=false) String version
			)throws NullPointerException{
        Application application = new Application();
        Comment comment = new Comment();
      /*  String applicationUID = "{"+UUID.randomUUID().toString()+"}";
        application.setApplication_id(applicationUID);
        int isExisting = applicationDAOImpl.checkIfExists(application);
        if(isExisting == 1){
        	applicationUID = "{"+UUID.randomUUID().toString()+"}";
		}
        
        String commentUID = "{"+UUID.randomUUID().toString()+"}";
        comment.setId(commentUID);
        isExisting = commentDAOImpl.checkIfExists(comment);
        if(isExisting == 1){
        	commentUID = "{"+UUID.randomUUID().toString()+"}";
		}*/
        
        
        
        application.setApplication_id(application_id);
        application.setTitle(title);
        application.setBirthplace(birthplace);
        application.setOccupation(occupation);
        application.setPosition_level(position_level);
        application.setPosition_title(position_title);
        application.setTenure(tenure);
        application.setMonthly_income(monthly_income);
        application.setCompany_name(company_name);
        application.setBusiness_nature(business_nature);
        application.setTin(tin);
        application.setSss(sss);
        application.setCivil_status(civil_status);
        application.setGender(gender);
        application.setDependents(dependents);
        application.setOther_phone_subscriptions(other_phone_subscriptions);
        application.setFundssource(fundssource);
        application.setSpouse_firstName(spouse_firstName);
        application.setSpouse_middleName(spouse_middleName);
        application.setSpouse_lastName(spouse_lastName);
        application.setSpouse_birthday(spouse_birthday);
        application.setMothers_maidenname(mothers_maidenname);
        application.setPhone_code(phone_code);
        application.setPlan_code(plan_code);
        application.setStatus("0");
        application.setRef_no(ref_no);
        application.setEmail(email);
        application.setFirstName(firstName);
        application.setMiddleName(middleName);
        application.setLastName(lastName);
        application.setBirthday(birthday);
        application.setType(type);
        application.setImage(image.getOriginalFilename());
        application.setTermination_image(termination_image.getOriginalFilename());
        application.setAddRegion(addRegion);
        application.setAddCity(addCity);
        application.setAddBrgy(addBrgy);
        application.setAddLine1(addLine1);
        application.setAddLine2(addLine2);
        application.setZipCode(zipCode);
        application.setAddWorkRegion(addWorkRegion);
        application.setAddWorkCity(addWorkCity);
        application.setAddWorkBrgy(addWorkBrgy);
        application.setAddWorkLine1(addWorkLine1);
        application.setAddWorkLine2(addWorkLine2);
        application.setWorkZipCode(workZipCode);
        application.setSendBillTo(sendBillTo);
        application.setTelephone(telephone);
        application.setMobile(mobile);
        application.setPhone_retrieval_type(phone_retrieval_type);
        application.setBusiness_center_name(business_center_name);
        application.setBusiness_center_lng(business_center_lng);
        application.setBusiness_center_lat(business_center_lat);
        application.setPayment_type(payment_type);
        application.setCc_number(cc_number);
        application.setCc_bank(cc_bank);
        application.setPp_email(pp_email);
        application.setPp_refNo(pp_refno);
        application.setCod_amt(cod_amt);
        application.setDoc_identity_sss(doc_identity_sss.getOriginalFilename());
        application.setDoc_identity_sss_no(doc_identity_sss_no);
        application.setDoc_identity_pagibig(doc_identity_pagibig.getOriginalFilename());
        application.setDoc_identity_pagibig_no(doc_identity_pagibig_no);
        application.setDoc_identity_philhealth(doc_identity_philhealth.getOriginalFilename());
        application.setDoc_identity_philhealth_no(doc_identity_philhealth_no);
        application.setDoc_identity_tin(doc_identity_tin.getOriginalFilename());
        application.setDoc_identity_tin_no(doc_identity_tin_no);
        application.setDoc_identity_driverslicense(doc_identity_driverslicense.getOriginalFilename());
        application.setDoc_identity_driverslicense_no(doc_identity_driverslicense_no);
        application.setDoc_identity_passport(doc_identity_passport.getOriginalFilename());
        application.setDoc_identity_passport_no(doc_identity_passport_no);
        application.setDoc_identity_companyid(doc_identity_companyid.getOriginalFilename());
        application.setDoc_identity_companyid_no(doc_identity_companyid_no);
        application.setDoc_identity_others(doc_identity_others.getOriginalFilename());
        application.setDoc_identity_others_no(doc_identity_others_no);
        application.setDoc_address_electricity(doc_address_electricity.getOriginalFilename());
        application.setDoc_address_electricity_no(doc_address_electricity_no);
        application.setDoc_address_water(doc_address_water.getOriginalFilename());
        application.setDoc_address_water_no(doc_address_water_no);
        application.setDoc_address_isp(doc_address_isp.getOriginalFilename());
        application.setDoc_address_isp_no(doc_address_isp_no);
        application.setDoc_address_cable(doc_address_cable.getOriginalFilename());
        application.setDoc_address_cable_no(doc_address_cable_no);
        application.setDoc_address_telecom(doc_address_telecom.getOriginalFilename());
        application.setDoc_address_telecom_no(doc_address_telecom_no);
        application.setDoc_address_bankloan(doc_address_bankloan.getOriginalFilename());
        application.setDoc_address_bankloan(doc_address_bankloan_no);
        application.setDoc_address_others(doc_address_others.getOriginalFilename());
        application.setDoc_address_others_no(doc_address_others_no);
        application.setDoc_income_bankstatement(doc_income_bankstatement.getOriginalFilename());
        application.setDoc_income_bankstatement_no(doc_income_bankstatement_no);
        application.setDoc_income_payslip(doc_income_payslip.getOriginalFilename());
        application.setDoc_income_payslip_no(doc_income_payslip_no);
        application.setDoc_income_security(doc_income_security.getOriginalFilename());
        application.setDoc_income_security_no(doc_income_security_no);
        application.setDoc_income_bonds(doc_income_bonds.getOriginalFilename());
        application.setDoc_income_bonds_no(doc_income_bonds_no);
        application.setDoc_income_stockcert(doc_income_stockcert.getOriginalFilename());
        application.setDoc_income_stockcert_no(doc_income_stockcert_no);
        application.setDoc_income_companyownership(doc_income_companyownership.getOriginalFilename());
        application.setDoc_income_companyownership_no(doc_income_companyownership_no);
        application.setDoc_income_others(doc_income_others.getOriginalFilename());
        application.setDoc_income_others_no(doc_income_others_no);
        application.setDoc_income_autocharge(doc_income_autocharge.getOriginalFilename());
        application.setDoc_income_autocharge_no(doc_income_autocharge_no);
        application.setDoc_terms_sig1(doc_terms_sig1.getOriginalFilename());
        application.setDoc_terms_sig2(doc_terms_sig2.getOriginalFilename());
        application.setDoc_terms_sig3(doc_terms_sig3.getOriginalFilename());
        application.setStep1Done(step1Done);
        application.setStep2Done(step2Done);
        application.setStep3Done(step3Done);
        application.setDecline_remarks(decline_remarks);
        application.setIspushed(ispushed);
        application.setEditedBy(edited_by);
        String date  = ""+new Date().getTime()+"";
		//System.out.println(date);
        application.setCreated(date);
        application.setUpdate(date);
        application.setVersion(version);
        application.setUser_id(user_id);
		int	s= applicationDAOImpl.save(application);
		int s2 = 0;
			try{
				if(!id.isEmpty() || !id.equals(null) ||!id.equals("") || id != null || id != ""){
					comment.setId(id);
					comment.setIspushed("1");
					comment.setEdited_by(edited_by);
					comment.setApplication_id(application_id);
					comment.setUser_id(user_id);
					comment.setVersion(version);
					comment.setCreated(date);
					comment.setContent(content);
					s2 = commentDAOImpl.save(comment);
				}
			}catch(NullPointerException e){
				
			}

		
		
		if(s ==1 ){

				return true;

		}
		return false;
		
	}
	@RequestMapping(value="/updateApplication", method = RequestMethod.GET,produces="application/json")
			public @ResponseBody int updateApplication(
					@RequestParam(value="applicationid",required=true) String applicationid,
					@RequestParam(value="title",required=false) String title,
					@RequestParam(value="birthplace",required=false) String birthplace,
					@RequestParam(value="occupation",required=false) String occupation,
					@RequestParam(value="position_level",required=false) String position_level,
					@RequestParam(value="position_title",required=false) String position_title,
					@RequestParam(value="tenure",required=false) String tenure,
					@RequestParam(value="monthly_income",required=false) String monthly_income,
					@RequestParam(value="company_name",required=false) String company_name,
					@RequestParam(value="business_nature",required=false) String business_nature,
					@RequestParam(value="tin",required=false) String tin,
					@RequestParam(value="sss",required=false) String sss,
					@RequestParam(value="civil_status",required=false) String civil_status,
					@RequestParam(value="gender",required=false) String gender,
					@RequestParam(value="dependents",required=false) String dependents,
					@RequestParam(value="other_phone_subscriptions",required=false) String other_phone_subscriptions,
					@RequestParam(value="fundssource",required=false) String fundssource,
					@RequestParam(value="spouse_firstName",required=false) String spouse_firstName,
					@RequestParam(value="spouse_middleName",required=false) String spouse_middleName,
					@RequestParam(value="spouse_lastName",required=false) String spouse_lastName,
					@RequestParam(value="spouse_birthday",required=false) String spouse_birthday,
					@RequestParam(value="mothers_maidenname",required=false) String mothers_maidenname,
					@RequestParam(value="phone_code",required=false) String phone_code,
					@RequestParam(value="plan_code",required=false) String plan_code,
					@RequestParam(value="status",required=false) String status,
					@RequestParam(value="ref_no",required=false) String ref_no,
					@RequestParam(value="email",required=false) String email,
					@RequestParam(value="firstName",required=false) String firstName,
					@RequestParam(value="middleName",required=false) String middleName,
					@RequestParam(value="lastName",required=false) String lastName,
					@RequestParam(value="birthday",required=false) String birthday,
					@RequestParam(value="type",required=false) String type,
					@RequestParam(value="image",required=false) String image,
					@RequestParam(value="termination_image",required=false) String termination_image,
					@RequestParam(value="addRegion",required=false) String addRegion,
					@RequestParam(value="addCity",required=false) String addCity,
					@RequestParam(value="addBrgy",required=false) String addBrgy,
					@RequestParam(value="addLine1",required=false) String addLine1,
					@RequestParam(value="addLine2",required=false) String addLine2,
					@RequestParam(value="zipCode",required=false) String zipCode,
					@RequestParam(value="addWorkRegion",required=false) String addWorkRegion,
					@RequestParam(value="addWorkCity",required=false) String addWorkCity,
					@RequestParam(value="addWorkBrgy",required=false) String addWorkBrgy,
					@RequestParam(value="addWorLine1",required=false) String addWorkLine1,
					@RequestParam(value="addWorLine2",required=false) String addWorkLine2,
					@RequestParam(value="workZipCode",required=false) String workZipCode,
					@RequestParam(value="sendBillTo",required=false) String sendBillTo,
					@RequestParam(value="telephone",required=false) String telephone,
					@RequestParam(value="mobile",required=false) String mobile,
					@RequestParam(value="phone_retrieval_type",required=false) String phone_retrieval_type,
					@RequestParam(value="business_center_name",required=false) String business_center_name,
					@RequestParam(value="business_center_lng",required=false) String business_center_lng,
					@RequestParam(value="business_center_lat",required=false) String business_center_lat,
					@RequestParam(value="payment_type",required=false) String payment_type,
					@RequestParam(value="cc_number",required=false) String cc_number,
					@RequestParam(value="cc_bank",required=false) String cc_bank,
					@RequestParam(value="pp_email",required=false) String pp_email,
					@RequestParam(value="pp_refno",required=false) String pp_refno,
					@RequestParam(value="cod_amt",required=false) String cod_amt,
					@RequestParam(value="doc_identity_sss",required=false) String doc_identity_sss,
					@RequestParam(value="doc_identity_sss_no",required=false) String doc_identity_sss_no,
					@RequestParam(value="doc_identity_pagibig",required=false) String doc_identity_pagibig,
					@RequestParam(value="doc_identity_pagibig_no",required=false) String doc_identity_pagibig_no,
					@RequestParam(value="doc_identity_philhealth",required=false) String doc_identity_philhealth,
					@RequestParam(value="doc_identity_philhealth_no",required=false) String doc_identity_philhealth_no,
					@RequestParam(value="doc_identity_tin",required=false) String doc_identity_tin,
					@RequestParam(value="doc_identity_tin_no",required=false) String doc_identity_tin_no,
					@RequestParam(value="doc_identity_driverslicense",required=false) String doc_identity_driverslicense,
					@RequestParam(value="doc_identity_driverslicense_no",required=false) String doc_identity_driverslicense_no,
					@RequestParam(value="doc_identity_passport",required=false) String doc_identity_passport,
					@RequestParam(value="doc_identity_passport_no",required=false) String doc_identity_passport_no,
					@RequestParam(value="doc_identity_companyid",required=false) String doc_identity_companyid,
					@RequestParam(value="doc_identity_companyid_no",required=false) String doc_identity_companyid_no,
					@RequestParam(value="doc_identity_others",required=false) String doc_identity_others,
					@RequestParam(value="doc_identity_others_no",required=false) String doc_identity_others_no,
					@RequestParam(value="doc_address_electricity",required=false) String doc_address_electricity,
					@RequestParam(value="doc_address_electricity_no",required=false) String doc_address_electricity_no,
					@RequestParam(value="doc_address_water",required=false) String doc_address_water,
					@RequestParam(value="doc_address_water_no",required=false) String doc_address_water_no,
					@RequestParam(value="doc_address_isp",required=false) String doc_address_isp,
					@RequestParam(value="doc_address_isp_no",required=false) String doc_address_isp_no,
					@RequestParam(value="doc_address_cable",required=false) String doc_address_cable,
					@RequestParam(value="doc_address_cable_no",required=false) String doc_address_cable_no,
					@RequestParam(value="doc_address_telecom",required=false) String doc_address_telecom,
					@RequestParam(value="doc_address_telecom_no",required=false) String doc_address_telecom_no,
					@RequestParam(value="doc_address_bankloan",required=false) String doc_address_bankloan,
					@RequestParam(value="doc_address_bankloan_no",required=false) String doc_address_bankloan_no,
					@RequestParam(value="doc_address_others",required=false) String doc_address_others,
					@RequestParam(value="doc_address_others_no",required=false) String doc_address_others_no,
					@RequestParam(value="doc_income_bankstatement",required=false) String doc_income_bankstatement,
					@RequestParam(value="doc_income_bankstatement_no",required=false) String doc_income_bankstatement_no,
					@RequestParam(value="doc_income_payslip",required=false) String doc_income_payslip,
					@RequestParam(value="doc_income_payslip_no",required=false) String doc_income_payslip_no,
					@RequestParam(value="doc_income_security",required=false) String doc_income_security,
					@RequestParam(value="doc_income_secuirty_no",required=false) String doc_income_security_no,
					@RequestParam(value="doc_income_bonds",required=false) String doc_income_bonds,
					@RequestParam(value="doc_income_bonds_no",required=false) String doc_income_bonds_no,
					@RequestParam(value="doc_income_stockcert",required=false) String doc_income_stockcert,
					@RequestParam(value="doc_income_stockcert_no",required=false) String doc_income_stockcert_no,
					@RequestParam(value="doc_income_companyownership",required=false) String doc_income_companyownership,
					@RequestParam(value="doc_income_companyownership_no",required=false) String doc_income_companyownership_no,
					@RequestParam(value="doc_income_others",required=false) String doc_income_others,
					@RequestParam(value="doc_income_others_no",required=false) String doc_income_others_no,
					@RequestParam(value="doc_income_autocharge",required=false) String doc_income_autocharge,
					@RequestParam(value="doc_income_autocharge_no",required=false) String doc_income_autocharge_no,
					@RequestParam(value="doc_terms_sig1",required=false) String doc_terms_sig1,
					@RequestParam(value="doc_terms_sig2",required=false) String doc_terms_sig2,
					@RequestParam(value="doc_terms_sig3",required=false) String doc_terms_sig3,
					@RequestParam(value="step1Done",required=false) String step1Done,
					@RequestParam(value="step2Done",required=false) String step2Done,
					@RequestParam(value="step3Done",required=false) String step3Done,
					@RequestParam(value="decline_remarks",required=false) String decline_remarks,
					@RequestParam(value="ispushed",required=false) String ispushed,
					@RequestParam(value="edited_by",required=false) String edited_by,
					@RequestParam(value="version",required=false) String version
					)throws NullPointerException{
		        Application application = new Application();
		        application.setApplication_id(applicationid);
		        application.setTitle(title);
		        application.setBirthplace(birthplace);
		        application.setOccupation(occupation);
		        application.setPosition_level(position_level);
		        application.setPosition_title(position_title);
		        application.setTenure(tenure);
		        application.setMonthly_income(monthly_income);
		        application.setCompany_name(company_name);
		        application.setBusiness_nature(business_nature);
		        application.setTin(tin);
		        application.setSss(sss);
		        application.setCivil_status(civil_status);
		        application.setGender(gender);
		        application.setDependents(dependents);
		        application.setOther_phone_subscriptions(other_phone_subscriptions);
		        application.setFundssource(fundssource);
		        application.setSpouse_firstName(spouse_firstName);
		        application.setSpouse_middleName(spouse_middleName);
		        application.setSpouse_lastName(spouse_lastName);
		        application.setSpouse_birthday(spouse_birthday);
		        application.setMothers_maidenname(mothers_maidenname);
		        application.setPhone_code(phone_code);
		        application.setPlan_code(plan_code);
		        application.setStatus(status);
		        application.setRef_no(ref_no);
		        application.setEmail(email);
		        application.setFirstName(firstName);
		        application.setMiddleName(middleName);
		        application.setLastName(lastName);
		        application.setBirthday(birthday);
		        application.setType(type);
		        application.setImage(image);
		        application.setTermination_image(termination_image);
		        application.setAddRegion(addRegion);
		        application.setAddCity(addCity);
		        application.setAddBrgy(addBrgy);
		        application.setAddLine1(addLine1);
		        application.setAddLine2(addLine2);
		        application.setZipCode(zipCode);
		        application.setAddWorkRegion(addWorkRegion);
		        application.setAddWorkCity(addWorkCity);
		        application.setAddWorkBrgy(addWorkBrgy);
		        application.setAddWorkLine1(addWorkLine1);
		        application.setAddWorkLine2(addWorkLine2);
		        application.setWorkZipCode(workZipCode);
		        application.setSendBillTo(sendBillTo);
		        application.setTelephone(telephone);
		        application.setMobile(mobile);
		        application.setPhone_retrieval_type(phone_retrieval_type);
		        application.setBusiness_center_name(business_center_name);
		        application.setBusiness_center_lng(business_center_lng);
		        application.setBusiness_center_lat(business_center_lat);
		        application.setPayment_type(payment_type);
		        application.setCc_number(cc_number);
		        application.setCc_bank(cc_bank);
		        application.setPp_email(pp_email);
		        application.setPp_refNo(pp_refno);
		        application.setCod_amt(cod_amt);
		        application.setDoc_identity_sss(doc_identity_sss);
		        application.setDoc_identity_sss_no(doc_identity_sss_no);
		        application.setDoc_identity_pagibig(doc_identity_pagibig);
		        application.setDoc_identity_pagibig_no(doc_identity_pagibig_no);
		        application.setDoc_identity_philhealth(doc_identity_philhealth);
		        application.setDoc_identity_philhealth_no(doc_identity_philhealth_no);
		        application.setDoc_identity_tin(doc_identity_tin);
		        application.setDoc_identity_tin_no(doc_identity_tin_no);
		        application.setDoc_identity_driverslicense(doc_identity_driverslicense);
		        application.setDoc_identity_driverslicense_no(doc_identity_driverslicense_no);
		        application.setDoc_identity_passport(doc_identity_passport);
		        application.setDoc_identity_passport_no(doc_identity_passport_no);
		        application.setDoc_identity_companyid(doc_identity_companyid);
		        application.setDoc_identity_companyid_no(doc_identity_companyid_no);
		        application.setDoc_identity_others(doc_identity_others);
		        application.setDoc_identity_others_no(doc_identity_others_no);
		        application.setDoc_address_electricity(doc_address_electricity);
		        application.setDoc_address_electricity_no(doc_address_electricity_no);
		        application.setDoc_address_water(doc_address_water);
		        application.setDoc_address_water_no(doc_address_water_no);
		        application.setDoc_address_isp(doc_address_isp);
		        application.setDoc_address_isp_no(doc_address_isp_no);
		        application.setDoc_address_cable(doc_address_cable);
		        application.setDoc_address_cable_no(doc_address_cable_no);
		        application.setDoc_address_telecom(doc_address_telecom);
		        application.setDoc_address_telecom_no(doc_address_telecom_no);
		        application.setDoc_address_bankloan(doc_address_bankloan);
		        application.setDoc_address_bankloan(doc_address_bankloan_no);
		        application.setDoc_address_others(doc_address_others);
		        application.setDoc_address_others_no(doc_address_others_no);
		        application.setDoc_income_bankstatement(doc_income_bankstatement);
		        application.setDoc_income_bankstatement_no(doc_income_bankstatement_no);
		        application.setDoc_income_payslip(doc_income_payslip);
		        application.setDoc_income_payslip_no(doc_income_payslip_no);
		        application.setDoc_income_security(doc_income_security);
		        application.setDoc_income_security_no(doc_income_security_no);
		        application.setDoc_income_bonds(doc_income_bonds);
		        application.setDoc_income_bonds_no(doc_income_bonds_no);
		        application.setDoc_income_stockcert(doc_income_stockcert);
		        application.setDoc_income_stockcert_no(doc_income_stockcert_no);
		        application.setDoc_income_companyownership(doc_income_companyownership);
		        application.setDoc_income_companyownership_no(doc_income_companyownership_no);
		        application.setDoc_income_others(doc_income_others);
		        application.setDoc_income_others_no(doc_income_others);
		        application.setDoc_income_autocharge(doc_income_autocharge);
		        application.setDoc_income_autocharge_no(doc_income_autocharge);
		        application.setDoc_terms_sig1(doc_terms_sig1);
		        application.setDoc_terms_sig2(doc_terms_sig2);
		        application.setDoc_terms_sig3(doc_terms_sig3);
		        application.setStep1Done(step1Done);
		        application.setStep2Done(step2Done);
		        application.setStep3Done(step3Done);
		        application.setDecline_remarks(decline_remarks);
		        application.setIspushed(ispushed);
		        application.setEditedBy(edited_by);
		        String date  = ""+new Date().getTime()+"";
				//System.out.println(date);
		        application.setUpdate(date);
		        application.setVersion(version);
		        int	s= applicationDAOImpl.update(application);
				
				return s;
				
			}
	@RequestMapping(value="/deleteApplication", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  int deleteApplication(
			@RequestParam(value="applicationid",required=true) String applicationid){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		Application application = new Application();
		application.setApplication_id(applicationid);
		int s = applicationDAOImpl.delete(application);		
		return s;
		
	}
	
	@RequestMapping(value="/uploadTest")
	public String upload(@ModelAttribute("applicationForm") Application application, Model model,
			@RequestParam(value="invalid",required =false) String invalid){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		model.addAttribute("error","display:none");
		return "index";
	}

	/*@RequestMapping(value="/authJson", method = RequestMethod.GET,produces="application/json	
	public @ResponseBody  Collection getUser (
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = false) String role)throws NullPointerException{
		User userParams = new User();
		userParams.setUserid(userid);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		Collection s = userDAOImpl.findUser(userParams);

		
		return s;
		
	}
	@RequestMapping(value="/saveJson", method = RequestMethod.GET,produces="application/json	
	public @ResponseBody  int saveUser (
			@RequestParam(value = "userid", required = true) String userid,
			@RequestParam(value= "password", required = true) String password,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = true) String role)throws NullPointerException{
		User userParams = new User();
		Md5Hasher md = new Md5Hasher();
		userParams.setPassword(md.md5(password.trim()));
		userParams.setUserid(userid);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		int s = userDAOImpl.save(userParams);
		return s;	
	}
	@RequestMapping(value="/updateJson", method = RequestMethod.GET,produces="application/json	
	public @ResponseBody  int updateUser (
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "olduserid" ,required = false) String oUserid,
			@RequestParam(value= "password", required = false) String password,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = false) String role)throws NullPointerException{
		User userParams = new User();
		Md5Hasher md = new Md5Hasher();
		
		try{
			if(!password.isEmpty()||!password.equals(null)||!password.equals("){
				userParams.setPassword(md.md5(password.trim()));
			}
		}catch(NullPointerException e){
			
		}
	
		
		userParams.setUserid(userid);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		int s = userDAOImpl.update(userParams,oUserid);
		return s;	
	}*/

}
