package com.tapsend.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.json.*;

import com.tapsend.model.Image;
import com.tapsend.utils.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tapsend.model.*;
import com.tapsend.dao.impl.*;
import com.tapsend.utils.*;

@Controller
public class MobileUserController {
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Autowired
	ApplicationDAOImpl applicationDAOImpl;
	
	@Autowired
	ImageDAOImpl imageDAOImpl;
	
	@Autowired
	PagibigDAOImpl pagibigDAOImpl;
	
	@Autowired
	SssDAOImpl sssDAOImpl;
	
	@Autowired
	PhilhealthDAOImpl philhealthDAOImpl;
	
	@Autowired
	BeneficiaryDAOImpl beneficiaryDAOImpl;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MobileUserController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/register",produces="application/json")
	public @ResponseBody String register(
			HttpServletRequest request,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="email",required=false)String email,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="firstName",required=false)String firstName,
			@RequestParam(value="middleName",required=false)String middleName,
			@RequestParam(value="lastName",required=false)String lastName,
			@RequestParam(value="birthday",required=false)String birthday,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="image",required=false)String image,
			@RequestParam(value="termination_image",required=false)String termination_image,
			@RequestParam(value="addRegion",required=false)String add_region,
			@RequestParam(value="addCity",required=false)String add_city,
			@RequestParam(value="addBrgy",required=false)String add_brgy,
			@RequestParam(value="add_line1",required=false)String add_line1,
			@RequestParam(value="add_line2",required=false)String add_line2,
			@RequestParam(value="zipCode",required=false)String zipCode,
			@RequestParam(value="sss",required=false)String sss,
			@RequestParam(value="pagibig",required=false)String pagibig,
			@RequestParam(value="philhealth",required=false)String philhealth,
			@RequestParam(value="tin",required=false)String tin,
			@RequestParam(value="gender",required=false)String gender,
			@RequestParam(value="civilstatus",required=false)String civilstatus,
			@RequestParam(value="emergency_person",required=false)String emergency_person,
			@RequestParam(value="emergency_number",required=false)String emergency_number,
			@RequestParam(value="landline",required=false)String landline,
			@RequestParam(value="mobile",required=false)String mobile,
			@RequestParam(value="suffix",required=false)String suffix,
			@RequestParam(value="maiden_name",required=false)String maiden_name,
			@RequestParam(value="ofc_number",required=false)String ofc_number,
			@RequestParam(value="ben_firstName",required=false)String ben_firstName,
			@RequestParam(value="ben_middleName",required=false)String ben_middleName,
			@RequestParam(value="ben_lastName",required=false)String ben_lastName,
			@RequestParam(value="ben_suffix",required=false)String ben_suffix,
			@RequestParam(value="ben_birthday",required=false)String ben_birthday,
			@RequestParam(value="ben_gender",required=false)String ben_gender,
			@RequestParam(value="ben_civilstatus",required=false)String ben_civilstatus,
			@RequestParam(value="ben_maiden_name",required=false)String ben_maiden_name,
			@RequestParam(value="ben_disabled",required=false)String ben_disabled,
			@RequestParam(value="ph_pen",required=false)String ph_pen,
			@RequestParam(value="ph_employer_name",required=false)String ph_employer_name,
			@RequestParam(value="ph_employer_address",required=false)String ph_employer_address,
			@RequestParam(value="ph_date_hired",required=false)String ph_date_hired,
			@RequestParam(value="ph_profession",required=false)String ph_profession,
			@RequestParam(value="ph_country_based",required=false) String ph_country_based,
			@RequestParam(value="ph_foreign_address",required=false)String ph_foreign_address,
			@RequestParam(value="ph_duration_from",required=false)String ph_duration_from,
			@RequestParam(value="ph_duration_to",required=false)String ph_duration_to,
			@RequestParam(value="ph_member_type",required=false)String ph_member_type,
			@RequestParam(value="ph_family_income",required=false)String ph_family_income,
			@RequestParam(value="ph_doc1",required=false)String ph_doc1,
			@RequestParam(value="ph_doc2",required=false)String ph_doc2,
			@RequestParam(value="ph_doc3",required=false)String ph_doc3,
			@RequestParam(value="ph_doc4",required=false)String ph_doc4,
			@RequestParam(value="ss_pension_type",required=false)String ss_pension_type,
			@RequestParam(value="ss_pension_account_number",required=false)String ss_pension_account_number,
			@RequestParam(value="ss_membership_type",required=false)String ss_membership_type,
			@RequestParam(value="ss_employer_id",required=false)String ss_employer_id,
			@RequestParam(value="ss_receipt_number",required=false)String ss_receipt_number,
			@RequestParam(value="ss_doc1",required=false)String ss_doc1,
			@RequestParam(value="ss_doc2",required=false)String ss_doc2,
			@RequestParam(value="ss_doc3",required=false)String ss_doc3,
			@RequestParam(value="ss_doc4",required=false)String ss_doc4,
			@RequestParam(value="pi_membership_type",required=false)String pi_membership_type,
			@RequestParam(value="pi_employer_business_name",required=false)String pi_employer_business_name,
			@RequestParam(value="pi_employee_number",required=false)String pi_employee_number,
			@RequestParam(value="pi_date_start",required=false)String pi_date_start,
			@RequestParam(value="pi_profession",required=false)String pi_profession,
			@RequestParam(value="pi_employment_status",required=false)String pi_employment_status,
			@RequestParam(value="pi_address",required=false)String pi_address,
			@RequestParam(value="pi_basic",required=false)String pi_basic,
			@RequestParam(value="pi_allowance",required=false)String pi_allowance,
			@RequestParam(value="pi_total",required=false)String pi_total,
			@RequestParam(value="pi_doc1",required=false)String pi_doc1,
			@RequestParam(value="pi_doc2",required=false)String pi_doc2,
			@RequestParam(value="pi_doc3",required=false)String pi_doc3,
			@RequestParam(value="pi_doc4",required=false)String pi_doc4,
			@RequestParam(value="company_name",required=false)String company_name,
			@RequestParam(value="company_address",required=false)String company_address,
			@RequestParam(value="company_est",required=false)String company_est,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false)String created,
			@RequestParam(value="updated",required=false)String updated,
			@RequestParam(value="version",required=false)String version,
			@RequestParam(value="extension_name",required=false)String extension_name,
			@RequestParam(value="relation",required=false)String relation,
			@RequestParam(value="params",required=false)String params,
			Model model) throws  SQLException {	
		Gson gson = new Gson();
		User user = new User();
		UserDetails usr = new UserDetails();
		usr.setUsername(username);
		usr.setEmail(email);
		usr.setFirstName(firstName);
		usr.setMiddleName(middleName);
		usr.setLastName(lastName);
		usr.setBirthday(birthday);
		usr.setType(type);
		usr.setImage(image);
		usr.setTermination_image(termination_image);
		usr.setAddRegion(add_region);
		usr.setAddBrgy(add_brgy);
		usr.setAddCity(add_city);
		usr.setAdd_line1(add_line1);
		usr.setAdd_line2(add_line2);
		usr.setZipCode(zipCode);
		usr.setSss(sss);
		usr.setPagibig(pagibig);
		usr.setPhilhealth(philhealth);
		usr.setTin(tin);
		usr.setGender(gender);
		usr.setCivilstatus(civilstatus);
		usr.setEmergency_person(emergency_person);
		usr.setEmergency_number(emergency_number);
		usr.setLandline(landline);
		usr.setMobile(mobile);
		usr.setSuffix(ben_suffix);
		usr.setMaiden_name(ben_maiden_name);
		usr.setOfc_number(ofc_number);
		usr.setBen_firstName(ben_firstName);
		usr.setBen_lastName(ben_lastName);
		usr.setBen_middleName(ben_middleName);
		usr.setBen_suffix(ben_suffix);
		usr.setBen_birthday(ben_birthday);
		usr.setBen_gender(ben_gender);
		usr.setBen_civilstatus(ben_civilstatus);
		usr.setBen_maiden_name(ben_maiden_name);
		usr.setBen_disabled(ben_disabled);
		usr.setPh_pen(ph_pen);
		usr.setPh_employer_name(ph_employer_name);
		usr.setPh_employer_address(ph_employer_address);
		usr.setPh_date_hired(ph_date_hired);
		usr.setPh_profession(ph_profession);
		usr.setPh_country_based(ph_country_based);
		usr.setPh_foreign_address(ph_foreign_address);
		usr.setPh_duration_from(ph_duration_from);
		usr.setPh_duration_to(ph_duration_to);
		usr.setPh_member_type(ph_member_type);
		usr.setPh_family_income(ph_family_income);
		usr.setPh_doc1(ph_doc1);
		usr.setPh_doc2(ph_doc2);
		usr.setPh_doc3(ph_doc3);
		usr.setPh_doc4(ph_doc4);
		usr.setSs_pension_type(ss_pension_type);
		usr.setSs_pension_account_number(ss_pension_account_number);
		usr.setSs_membership_type(ss_membership_type);
		usr.setSs_employer_id(ss_employer_id);
		usr.setSs_receipt_number(ss_receipt_number);
		usr.setSs_doc1(ss_doc1);
		usr.setSs_doc2(ss_doc2);
		usr.setSs_doc3(ss_doc3);
		usr.setSs_doc4(ss_doc4);
		usr.setPi_membership_type(pi_membership_type);
		usr.setPi_employer_business_name(pi_employer_business_name);
		usr.setPi_employee_number(pi_employee_number);
		usr.setPi_date_start(pi_date_start);
		usr.setPi_profession(pi_profession);
		usr.setPi_employment_status(pi_employment_status);
		usr.setPi_address(pi_address);
		usr.setPi_basic(pi_basic);
		usr.setPi_allowance(pi_allowance);
		usr.setPi_total(pi_total);
		usr.setPi_doc1(pi_doc1);
		usr.setPi_doc2(pi_doc2);
		usr.setPi_doc3(pi_doc3);
		usr.setPi_doc4(pi_doc4);
		usr.setCompany_name(company_name);
		usr.setCompany_address(company_address);
		usr.setCompany_est(company_est);
		usr.setIspushed(ispushed);
		usr.setEdited_by(edited_by);
		usr.setCreated(created);
		usr.setUpdated(updated);
		usr.setVersion(version);
		usr.setExtension_name(extension_name);
		

		System.out.println(params);
		Gson gson2 = new Gson();
		UserDetails display = gson2.fromJson(params, UserDetails.class);
		user.setId(display.getId());
	    user.setFirstName(display.getFirstName());
	    user.setMiddleName(display.getMiddleName());
	    user.setLastName(display.getLastName());
	    user.setPassword(display.getPassword());
	    user.setRole("6");
	    user.setStatus("1");
	    user.setDetails(params);
	    user.setEmail(display.getEmail());
	    user.setUser_id(display.getEmail());
	    user.setParentuser_id(display.getParent());
	    user.setType(display.getType()+"");
	    user.setRelation(display.getRelation());
	    System.out.println(display.getUsername());
	    System.out.println(display.getRelation());
		int isSaved= userDAOImpl.save(user);
		
		System.out.println("isSave = "+isSaved);
		if(isSaved==1){
			if(user.getType().equalsIgnoreCase("4")||user.getType().equalsIgnoreCase("3")){
				
			}
			else{
//				sendEmail(display,request);
				System.out.println("JSON:"+gson.toJson(usr));
			}
			
		}
		
		
		return isSaved+"";
	
	}
	
	
//public boolean sendEmail(UserDetails userDetails, HttpServletRequest request) throws SQLException{
//		
//		ApplicationContext a = new ClassPathXmlApplicationContext("classpath:Spring-Mail.xml");
//		
//		/*Application application = new Application();
//		Collection s = null;
//		s = applicationDAOImpl.findApplication(application);
//		ArrayList<Application> b  = new ArrayList<Application> (s);*/
//		
//		Props props = new Props();
//		
//        long unix = Long.parseLong(userDetails.getCreated());
//        Date date = new Date(unix);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy"); // the format of your date
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//    
//        String formattedDate = sdf.format(date);
//
//		
//		MailModel mail = (MailModel) a.getBean("mail");
//				
//		String recipient =  props.getRecipients();
//				
//		String sender = props.getSender();
//		
//		mail.sendMail(userDetails,request,formattedDate);
//		
//		
//		return true;
//	}
	
	@RequestMapping(value="/checkEmail",produces="application/json")
	public @ResponseBody String checkEmail(@RequestParam(value="email",required=false)String email,
			Model model){
		User user = new User();
		user.setEmail(email);
		int isExisting = userDAOImpl.checkIfEmailExists(user);
		System.out.println(email);
		if(isExisting==1){
			return "-1";
			
		}
		return "1";
	}
	@RequestMapping(value = "/update",produces="application/json")
	public @ResponseBody String update(
			@RequestParam(value="params",required=false)String params,
			Model model, HttpServletRequest request)  {	
		Gson gson = new Gson();
		User user = new User();
		UserDetails usr = new UserDetails();
		
		String json = gson.toJson(usr);
		String json2 = gson.toJson(params);
		System.out.println(params);
		Gson gson2 = new GsonBuilder().create();
		UserDetails display = new UserDetails();
		display = gson2.fromJson(params, UserDetails.class);
		
	    user.setFirstName(display.getFirstName());
	    user.setMiddleName(display.getMiddleName());
	    user.setLastName(display.getLastName());
	    user.setPassword(display.getPassword());
	    user.setRole("6");
	    user.setStatus("1");
	    user.setDetails(params);
	    user.setEmail(display.getEmail());
	    user.setUser_id(display.getEmail());
	    System.out.println(display.getUsername());
	    Image image = new Image();
	    image.setUser_id(display.getUsername());
	    int isDeleted = imageDAOImpl.delete(image);
		int isUpdated= userDAOImpl.update(user);
		System.out.println("JSON:"+gson.toJson(usr));
		return isUpdated+"";
		
	
	}
	
	@RequestMapping(value = "/regPagibig",produces="application/json")
	public @ResponseBody String regPagibig(
			HttpServletRequest request,
			@RequestParam(value="pagibig_id",required=false) String pagibig_id,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="member_type",required=false)String member_type,
			@RequestParam(value="employer_business_name",required=false)String employer_business_name,
			@RequestParam(value="employee_number",required=false)String employee_number,
			@RequestParam(value="date_start",required=false)String date_start,
			@RequestParam(value="profession",required=false)String profession,
			@RequestParam(value="employment_status",required=false)String employment_status,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="basic",required=false)String basic,
			@RequestParam(value="allowance",required=false)String allowance,
			@RequestParam(value="total",required=false)String total,
			@RequestParam(value="doc1",required=false)String doc1,
			@RequestParam(value="doc2",required=false)String doc2,
			@RequestParam(value="doc3",required=false)String doc3,
			@RequestParam(value="doc4",required=false)String doc4,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="params",required=false)String params,
			Model model) throws  SQLException {	
		Gson gson = new Gson();
		
		Pagibig pi = new Pagibig();
		pi.setPagibig_id(pagibig_id);
		pi.setId(id);
		pi.setUser_id(user_id);
		pi.setMember_type(member_type);
		pi.setEmployer_business_name(employer_business_name);
		pi.setEmployee_number(employee_number);
		pi.setDate_start(date_start);
		pi.setProfession(profession);
		pi.setEmployment_status(employment_status);
		pi.setAddress(address);
		pi.setBasic(basic);
		pi.setAllowance(allowance);
		pi.setTotal(total);
		pi.setDoc1(doc1);
		pi.setDoc2(doc2);
		pi.setDoc3(doc3);
		pi.setDoc4(doc4);
		pi.setIspushed(ispushed);
		
		String json = gson.toJson(pi);
		String json2 = gson.toJson(params);
		System.out.println("pagibig params:"+params);
		

	    System.out.println("user id: "+pi.getUser_id());
		int isSaved= pagibigDAOImpl.savePagibig(pi);
		System.out.println("isSave = "+isSaved);
		if(isSaved==1){
			
				System.out.println("JSON:"+gson.toJson(pi));
			}
		
		
		return isSaved+"";
	
	}
	
	@RequestMapping(value = "/regSSS",produces="application/json")
	public @ResponseBody String regSSS(
			HttpServletRequest request,
			@RequestParam(value="sss_id",required=false) String sss_id,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="pension_type",required=false)String pension_type,
			@RequestParam(value="pension_account_number",required=false)String pension_account_number,
			@RequestParam(value="member_type",required=false)String member_type,
			@RequestParam(value="employer_id",required=false)String employer_id,
			@RequestParam(value="receipt_number",required=false)String receipt_number,
			@RequestParam(value="doc1",required=false)String doc1,
			@RequestParam(value="doc2",required=false)String doc2,
			@RequestParam(value="doc3",required=false)String doc3,
			@RequestParam(value="doc4",required=false)String doc4,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="params",required=false)String params,
			Model model) throws  SQLException {	
		Gson gson = new Gson();
		
		SSS ss = new SSS();
		ss.setSss_id(sss_id);
		ss.setId(id);
		ss.setUser_id(user_id);
		ss.setPension_type(pension_type);
		ss.setPension_account_number(pension_account_number);
		ss.setMember_type(member_type);
		ss.setEmployer_id(employer_id);
		ss.setReceipt_number(receipt_number);
		ss.setDoc1(doc1);
		ss.setDoc2(doc2);
		ss.setDoc3(doc3);
		ss.setDoc4(doc4);
		ss.setIspushed(ispushed);
	
		String json = gson.toJson(ss);
		String json2 = gson.toJson(params);
		System.out.println("sss params:"+params);
		

	    System.out.println("user id: "+ss.getUser_id());
		int isSaved= sssDAOImpl.saveSSS(ss);
		System.out.println("isSave = "+isSaved);
		if(isSaved==1){
			
				System.out.println("JSON:"+gson.toJson(ss));
			}
		
		
		return isSaved+"";
	
	}
	
	@RequestMapping(value = "/regPhilhealth",produces="application/json")
	public @ResponseBody String regPhilhealth(
			HttpServletRequest request,
			@RequestParam(value="philhealth_id",required=false) String philhealth_id,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="pen",required=false)String pen,
			@RequestParam(value="employer_name",required=false)String employer_name,
			@RequestParam(value="employer_address",required=false)String employer_address,
			@RequestParam(value="date_hired",required=false)String date_hired,
			@RequestParam(value="country_based",required=false)String country_based,
			@RequestParam(value="foreign_address",required=false)String foreign_address,
			@RequestParam(value="duration_from",required=false)String duration_from,
			@RequestParam(value="duration_to",required=false)String duration_to,
			@RequestParam(value="member_type",required=false)String member_type,
			@RequestParam(value="family_income",required=false)String family_income,
			@RequestParam(value="doc1",required=false)String doc1,
			@RequestParam(value="doc2",required=false)String doc2,
			@RequestParam(value="doc3",required=false)String doc3,
			@RequestParam(value="doc4",required=false)String doc4,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="params",required=false)String params,
			Model model) throws  SQLException {	
		Gson gson = new Gson();
		
		Philhealth ph = new Philhealth();
		
			ph.setPhilhealth_id(philhealth_id);
			ph.setId(id);
			ph.setUser_id(user_id);
			ph.setPen(pen);
			ph.setEmployer_name(employer_name);
			ph.setEmployer_name(employer_name);
			ph.setDate_hired(date_hired);
			ph.setCountry_based(country_based);
			ph.setForeign_address(foreign_address);
			ph.setDuration_from(duration_from);
			ph.setDuration_to(duration_to);
			ph.setMember_type(member_type);
			ph.setFamily_income(family_income);
			ph.setDoc1(doc1);
			ph.setDoc2(doc2);
			ph.setDoc3(doc3);
			ph.setDoc4(doc4);
			ph.setIspushed(ispushed);
	
		String json = gson.toJson(ph);
		String json2 = gson.toJson(params);
		System.out.println("sss params:"+params);
		

	    System.out.println("user id: "+ph.getUser_id());
		int isSaved= philhealthDAOImpl.savePhilhealth(ph);
		System.out.println("isSave = "+isSaved);
		if(isSaved==1){
			
				System.out.println("JSON:"+gson.toJson(ph));
			}
		
		
		return isSaved+"";
	
	}
	@RequestMapping(value = "/regBen",produces="application/json")
	public @ResponseBody String regBen(
			HttpServletRequest request,
			@RequestParam(value="ben_parentid",required=false) String ben_parentid,
			@RequestParam(value="firstName",required=false) String firstName,
			@RequestParam(value="middleName",required=false) String middleName,
			@RequestParam(value="lastName",required=false)String lastName,
			@RequestParam(value="suffix",required=false)String suffix,
			@RequestParam(value="birthday",required=false)String birthday,
			@RequestParam(value="gender",required=false)String gender,
			@RequestParam(value="civil_status",required=false)String civil_status,
			@RequestParam(value="maiden_name",required=false)String maiden_name,
			@RequestParam(value="disabled",required=false)String disabled,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="params",required=false)String params,
			Model model) throws  SQLException {	
		Gson gson = new Gson();

		Beneficiary ben = new Beneficiary();
		
		ben.setBen_parentid(ben_parentid);
		ben.setFirstName(firstName);
		ben.setMiddleName(middleName);
		ben.setLastName(lastName);
		ben.setSuffix(suffix);
		ben.setBirthday(birthday);
		ben.setGender(gender);
		ben.setCivil_status(civil_status);
		ben.setMaiden_name(maiden_name);
		ben.setDisabled(disabled);
		ben.setIspushed(ispushed);
			
	
		String json = gson.toJson(ben);
		String json2 = gson.toJson(params);
		System.out.println("sss params:"+params);
		

	    System.out.println("parentid: "+ben.getBen_parentid());
		int isSaved= beneficiaryDAOImpl.saveBen(ben);
		System.out.println("isSave = "+isSaved);
		if(isSaved==1){
			
				System.out.println("JSON:"+gson.toJson(ben));
			}
		
		
		return isSaved+"";
	
	}

	@RequestMapping(value="/loginApp",produces="application/json")
	public @ResponseBody String login(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password,
			Model model){
		User user = new User();
		Md5Hasher md5 = new Md5Hasher();
		user.setUser_id(username);
		user.setPassword(md5.md5(password));
		int isExisting = userDAOImpl.checkIfExists(user);

		if(isExisting==1){
			return "1";
		}
		return "-1";
	}
	
	@RequestMapping(value="/selectMyUsers",produces="application/json")
	public @ResponseBody Collection selectMyUsersDetails(
		   @RequestParam(value="username",required=false)String username,
			Model model){

		return applicationDAOImpl.selectMyUsersDetail(username);
	}
	
	@RequestMapping(value="/selectUser",produces="application/json")
	public @ResponseBody Collection selectUserdetails(
		   @RequestParam(value="username",required=false)String username,
			Model model){

		return applicationDAOImpl.selectUserDetail(username);
	}
	
	public boolean FileUpload(String imagename, String file) throws IOException{
		String[] allowedExts = {"gif", "jpeg", "jpg", "png","GIF","JPEG","JPG","PNG"};
		String[] temp = imagename.split(".");
		//String ext = temp[temp.length-1];
		Resource resource = new ClassPathResource("../properties/filepath.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		String filepath = props.getProperty("filepath.url");
		Base64 decoder = new Base64();
		//for(int i = 0;i<allowedExts.length;i++){
			//if(allowedExts[i]==ext){
				//BufferedImage  bi = ImageIO.read(new File(filepath+image));
				//ImageIO.write(, formatName, output)
				@SuppressWarnings("static-access")
				
				byte[] bi = decoder.decode(file);
				FileOutputStream fileOuputStream = 
		                  new FileOutputStream(filepath+imagename); 
			    fileOuputStream.write(bi);
			    fileOuputStream.close();
			    return true;
			//}
		//}

	}
	
	@RequestMapping(value="/saveImage",produces="application/json")
	public @ResponseBody String saveImage(
			@RequestParam(value="id",required=false) String id,
//			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="filename",required=false) String filename,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="image",required=false) String image_store
//			@RequestParam(value="user_id",required=false)String user_id
			) {
				
			Image image = new Image();	
			
			System.out.println("image store: "+image_store);
			image.setId(id);
//			image.setApplication_id(application_id);
			image.setFilename(filename);
		    image.setType(type);

//		    image.setUser_id(user_id);
//			System.out.println("userid: "+user_id);
			int isExisting = imageDAOImpl.checkIfExists(image);
			System.out.println(isExisting);
			if(isExisting == 0){
				Boolean i;
				try {
					i = FileUpload(filename, image_store);
					
					if(i){
						System.out.println("image stored!");
					}
					int isSaved = imageDAOImpl.save(image);
					if(isSaved == 1){
						return "1";
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			return "-1";
		
	}
}
