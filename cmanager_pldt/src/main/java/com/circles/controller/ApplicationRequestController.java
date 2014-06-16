package com.circles.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;







import com.circles.model.*;
import com.circles.dao.impl.*;
import com.circles.utils.*;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")
public class ApplicationRequestController {
	
	@Autowired
	private ApplicationDAOImpl applicationDAOImpl;
	
	@Autowired 
	Application2DAOImpl application2DAOImpl;
	
	@Autowired
	private CommentDAOImpl commentDAOImpl;
	
	@Autowired
	private PhoneDAOImpl phoneDAOImpl;
	
	@Autowired
	private ImageDAOImpl imageDAOImpl;
	
	@Autowired
	private GcmDAOImpl gcmDAOImpl;
	
//	@Autowired ResetPasswordDAOImpl resetpasswordDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	
	@RequestMapping(value="/getApplications", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getApplications(){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
	
		Collection s = applicationDAOImpl.findAllApplication();		

        
		return s;
		
	}
	
	@RequestMapping(value="/getApplications1", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getApplications1(HttpSession session){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
	
		System.out.println(session.getAttribute("username"));
		Collection s = applicationDAOImpl.findAllApplication(session.getAttribute("username").toString());		

        
		return s;
		
	}
	
	public boolean checksession(HttpSession session) throws ConversionNotSupportedException
	{
		boolean flg = true;
		String username = (String) session.getAttribute("username");
		String imei	= (String) session.getAttribute("imei");
		if (username == null || imei == null)
			{
			 throw new ConversionNotSupportedException("unauthrized", null, null);
			}
		return flg;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getApplication",produces="application/json")
	public @ResponseBody  Collection getExistingApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,HttpSession session) throws SQLException, NoSuchFieldException, SecurityException,ConversionNotSupportedException{
//		checksession(session);
		Application application = new Application();
		Phone phone = new Phone();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection<Application> s = null;
		
		Collection test = null;
		Collection phoneInfo =  null;
		Collection plan = null;
		ArrayList<Application> copy = null;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication(application);
			copy = new ArrayList<Application>(s);
			System.out.println(copy.get(0).getPhone_id());
			phone.setPhone_id(copy.get(0).getPhone_id());
			phoneInfo = phoneDAOImpl.selectPhone(phone);
			test = s;
			test.add(phoneInfo);

		}
		return test;
		
	}
	@RequestMapping(value="/uploadForm")
	public String uploadFormApplication(
		) throws SQLException{
		return "manager/simpleUploader";
		
	}

	
	public boolean FileUpload(String imagename, String file) throws IOException{
		String[] allowedExts = {"gif", "jpeg", "jpg", "png","GIF","JPEG","JPG","PNG"};
		String[] temp = imagename.split(".");
		//String ext = temp[temp.length-1];
		Resource resource = new ClassPathResource("../properties/filepath.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		String filepath = props.getProperty("filepath.url");
//				"C:/Users/tata/workspace/cmanager-jc/src/main/webapp/resources/uploaded";
//				
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
	
	@SuppressWarnings("deprecation")
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
			@RequestParam(value="phone_id",required=false) String phone_id,
			@RequestParam(value="plan_id",required=false) String plan_code,
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
			@RequestParam(value="step0Done",required=false) String step0Done,
			@RequestParam(value="step1Done",required=false) String step1Done,
			@RequestParam(value="step2Done",required=false) String step2Done,
			@RequestParam(value="step3Done",required=false) String step3Done,
			@RequestParam(value="isaddressdone",required=false) String isaddressDone,
			@RequestParam(value="isidentitydone",required=false) String isidentityDone,
			@RequestParam(value="isincomedone",required=false) String isincomeDone,
			@RequestParam(value="contact_person",required=false) String contact_person,
			@RequestParam(value="contact_number",required=false) String contact_number,
			@RequestParam(value="decline_remarks",required=false) String decline_remarks,
			@RequestParam(value="comment_id",required=false) String id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed_comment,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="application_type",required=false) String application_type,
			HttpSession session
		
			)throws NullPointerException, IOException, SQLException,ConversionNotSupportedException{

        Application application = new Application();
        Comment comment = new Comment();
        
        CompanyDetails company = new CompanyDetails();
        AddressDetails address = new AddressDetails();
        SpouseDetails spouse = new SpouseDetails();
        
        Gson gson = new Gson();
        
        company=gson.fromJson(company_name, CompanyDetails.class);
        address=gson.fromJson(addLine1,AddressDetails.class);
        spouse=gson.fromJson(spouse_firstName, SpouseDetails.class);
        
        System.out.println("company details: "+company);
        System.out.println("address details: "+address);
        System.out.println("spouse details: "+spouse);
        
        System.out.println(birthday);
        application.setApplication_id(application_id);
        application.setTitle(title);
        application.setBirthplace(birthplace);
        application.setOccupation(occupation);
        application.setPosition_level(position_level);
        application.setPosition_title(position_title);
        application.setTenure(tenure);
        application.setMonthly_income(monthly_income);
        application.setCompany_name(company_name); //companyjson
        application.setBusiness_nature(business_nature);
        application.setTin(tin);
        application.setSss(sss);
        application.setCivil_status(civil_status);
        application.setGender(gender);
        application.setDependents(dependents);
        application.setOther_phone_subscriptions(other_phone_subscriptions);
        application.setFundssource(fundssource);
        application.setSpouse_firstName(spouse_firstName); //spousejson
        application.setSpouse_middleName(spouse_middleName);
        application.setSpouse_lastName(spouse_lastName);
        application.setSpouse_birthday(spouse_birthday);
        application.setMothers_maidenname(mothers_maidenname);
        application.setPhone_id(phone_id);
        application.setPlan_code(plan_code);
        application.setStatus("0");
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
        application.setAddWorkLine1(addWorkLine1); //addressjson
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
        application.setDoc_income_others_no(doc_income_others_no);
        application.setDoc_income_autocharge(doc_income_autocharge);
        application.setDoc_income_autocharge_no(doc_income_autocharge_no);
        application.setDoc_terms_sig1(doc_terms_sig1);
        application.setDoc_terms_sig2(doc_terms_sig2);
        application.setDoc_terms_sig3(doc_terms_sig3);
        application.setStep0Done(step0Done);
        application.setStep1Done(step1Done);
        application.setStep2Done(step2Done);
        application.setStep3Done(step3Done);
        application.setIsaddressDone(isaddressDone);
        application.setIsidentityDone(isidentityDone);
        application.setIsincomeDone(isincomeDone);
        application.setContact_person(contact_person);
        application.setContact_number(contact_number);
        application.setDecline_remarks(decline_remarks);
        application.setIspushed(ispushed);
        application.setEditedBy(edited_by);
        String date  = ""+new Date().getTime()+"";
		//System.out.println(date);
        application.setCreated(date);
        application.setUpdate(date);
        application.setVersion("1");
        application.setUser_id(user_id);
        application.setApplication_type(application_type);
		int	s= 0;
		
		int isExisting = applicationDAOImpl.checkIfExists(application);
		
		if(isExisting == 0){
			s = applicationDAOImpl.save(application);
		
		}
		else{
			Image imageDelete = new Image();
			int delete = imageDAOImpl.delete(imageDelete);
			System.out.println("Deleting images from application id:"+application.getApplication_id()+" for updated images.");
			System.out.println("this will be an update of application:"+application.getApplication_id());
			int newVer = Integer.parseInt(version);
			newVer++;
			
			application.setVersion(newVer+"");
			s = applicationDAOImpl.update(application);
		}
		


		
		
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

		
		Collection gcms = null;
		
		String regid = "";
		if(s ==1 ){
				System.out.println(plan_code);
				System.out.println("Added new Application by "+user_id+" on "+new Date().getDate());
				return true;

		}
		return false;
		
	}
	@RequestMapping(value="/saveImage",produces="application/json")
	public @ResponseBody String saveImage(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="filename",required=false) String filename,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="image",required=false) String image_store,HttpSession session) throws IOException,ConversionNotSupportedException {
//		checksession(session);
				
			Image image = new Image();
			
			
			System.out.println(image_store);
			image.setId(id);
			image.setApplication_id(application_id);
			image.setFilename(filename);
		    image.setType(type);
			System.out.println(application_id);
			int isExisting = imageDAOImpl.checkIfExists(image);
			System.out.println(isExisting);
			if(isExisting == 0){
				Boolean i = FileUpload(filename, image_store);
				if(i){
					System.out.println("image stored!");
				}
				int isSaved = imageDAOImpl.save(image);
				if(isSaved == 1){
					return "1";
				}
			}
			return "1";
		
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/updateApplication", produces="application/json")
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
					@RequestParam(value="phone_id",required=false) String phone_id,
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
					@RequestParam(value="step0Done",required=false) String step0Done,
					@RequestParam(value="step1Done",required=false) String step1Done,
					@RequestParam(value="step2Done",required=false) String step2Done,
					@RequestParam(value="step3Done",required=false) String step3Done,
					@RequestParam(value="contact_person",required=false) String contact_person,
					@RequestParam(value="contact_number",required=false) String contact_number,
					@RequestParam(value="isaddressdone",required=false) String isaddressDone,
					@RequestParam(value="isincomedone",required=false) String isincomeDone,
					@RequestParam(value="isidentitydone",required=false) String isidentityDone,
					@RequestParam(value="ispushed",required=false) String ispushed,
					@RequestParam(value="decline_remarks",required=false) String decline_remarks,
					@RequestParam(value="edited_by",required=false) String edited_by,
					@RequestParam(value="version",required=false) String version,
					@RequestParam(value="application_type",required=false) String application_type,
//					@RequestParam(value="companyid",required=false) String companyid
//					@RequestParam(value="sketch",required=false) String sketch
					HttpSession session)throws NullPointerException, IOException, SQLException,ConversionNotSupportedException{
//		checksession(session);
		        Application application = new Application();
		        
		        CompanyDetails company = new CompanyDetails();
		        AddressDetails address = new AddressDetails();
		        SpouseDetails spouse = new SpouseDetails();
		        
		        Gson gson = new Gson();
		        
		        company=gson.fromJson(company_name, CompanyDetails.class);
		        address=gson.fromJson(addLine1,AddressDetails.class);
		        spouse=gson.fromJson(spouse_firstName, SpouseDetails.class);
		        
		        System.out.println("company details: "+company);
		        System.out.println("address details: "+address);
		        System.out.println("spouse details: "+spouse);
		        
		        
		        application.setApplication_id(applicationid);
		        application.setTitle(title);
		        application.setBirthplace(birthplace);
		        application.setOccupation(occupation);
		        application.setPosition_level(position_level);
		        application.setPosition_title(position_title);
		        application.setTenure(tenure);
		        application.setMonthly_income(monthly_income);
		        application.setCompany_name(company_name); // companyjson
		        application.setBusiness_nature(business_nature);
		        application.setTin(tin);
		        application.setSss(sss);
		        application.setCivil_status(civil_status);
		        application.setGender(gender);
		        application.setDependents(dependents);
		        application.setOther_phone_subscriptions(other_phone_subscriptions);
		        application.setFundssource(fundssource);
		        application.setSpouse_firstName(spouse_firstName); //spousejson
		        application.setSpouse_middleName(spouse_middleName);
		        application.setSpouse_lastName(spouse_lastName);
		        application.setSpouse_birthday(spouse_birthday);
		        application.setMothers_maidenname(mothers_maidenname);
		        application.setPhone_id(phone_id);
		        application.setPlan_code(plan_code);
		        application.setStatus("0");
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
		        application.setAddWorkLine1(addWorkLine1); // addressjson
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
		        application.setStep0Done(step0Done);
		        application.setStep1Done(step1Done);
		        application.setStep2Done(step2Done);
		        application.setStep3Done(step3Done);
		        application.setIsaddressDone(isaddressDone);
		        application.setIsidentityDone(isidentityDone);
		        application.setIsincomeDone(isincomeDone);
		        application.setContact_person(contact_person);
		        application.setContact_number(contact_number);
		        application.setDecline_remarks(decline_remarks);
		        application.setIspushed(ispushed);
		        application.setEditedBy(edited_by);
		        String date  = ""+new Date().getTime()+"";
				//System.out.println(date);
		        application.setUpdate(date);
		        Application getVersion = new Application();
		        getVersion.setApplication_id(applicationid);
		        Collection getAppVersion = applicationDAOImpl.findApplication(getVersion);
		        ArrayList<Application> appVersion = new ArrayList<Application>(getAppVersion);
		        int newVersion = Integer.parseInt(appVersion.get(0).getVersion());
		        newVersion+=1;
		        application.setVersion(newVersion+"");
		        application.setApplication_type(application_type);
//		        application.setCompanyid(companyid);
		        
		        
		        Resource resource = new ClassPathResource("../properties/filepath.properties");
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				String GOOGLE_SERVER_KEY = props.getProperty("GOOGLE_API_KEY");
				Gcm gcm = new Gcm();
				String regid="";
				ArrayList<Gcm> copy = null;
				Collection gcms = null;
				Collection app_user = null;
			    Image imageDelete = new Image();
			    imageDelete.setApplication_id(applicationid);
			    imageDAOImpl.delete(imageDelete);
		        int	s= applicationDAOImpl.update(application);
				if(s==1){
					
					
					
					MulticastResult result2 = null;
					Result result = null;
					Application newApp = new Application();
					newApp.setApplication_id(applicationid);
					app_user = applicationDAOImpl.findApplication(application);
					ArrayList<Application> copy2 = new ArrayList<Application>(app_user);
					gcm.setUsername(copy2.get(0).getEditedBy());
					System.out.println(copy2.get(0).getEditedBy());
					gcms = gcmDAOImpl.findGcm(gcm);
					copy = new ArrayList<Gcm>(gcms); 
					regid = copy.get(0).getRegid();
					Sender sender = new Sender(GOOGLE_SERVER_KEY);
			        Message messages = new Message.Builder().delayWhileIdle(false).addData("table", "application").build();
			        //result = sender.send(messages, regid, 5);
			        ArrayList<String> devices = new ArrayList<String>();
			        devices.add(regid);
			        System.out.println(messages);	
			        result2 = sender.send(messages, devices, 5);
			        System.out.println(regid);
			        System.out.println(result2.toString());
			        
			        System.out.println("Updated Application by "+edited_by+" on "+new Date().getDate());
			        resource = null;
					
				}
				return s;
				
			}
	@RequestMapping(value="/deleteApplication", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  int deleteApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,
			HttpSession session) throws ConversionNotSupportedException{
//		checksession(session);
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
	
//	@RequestMapping(value = "/saveResetPassword",produces="application/json")
//	public @ResponseBody String resetPass(
//			HttpServletRequest request,
//			@RequestParam(value="password",required=false)String password,
//			@RequestParam(value="params",required=false)String params,
//			Model model) throws  SQLException {	
//		Gson gson = new Gson();
//		User user = new User();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		Date date = new Date();
//		long unixDate = date.getTime();
//		System.out.println("UNIX CURRENT TIME: "+unixDate);
//	    Calendar cal = Calendar.getInstance();
//	    cal.setTime(date);
//	    cal.add(Calendar.DAY_OF_WEEK, 1);
//
//	            java.util.Date expirationDate = cal.getTime();
//
//	    System.err.println("expire date"+expirationDate.getTime());
//	    
//		long expiration = expirationDate.getTime();
//		
//		System.out.println("EXPIRATION: "+expiration);
//		
//		String resetUID = "{"+UUID.randomUUID().toString()+"}";
//		ResetPassword rp = new ResetPassword();
//		
//		rp.setResetid(resetUID);
//		rp.setCode(RandomStringUtils.randomAlphanumeric(6));
//		rp.setCodetype("activation");
//		rp.setUserid(user.getUserid());
//		rp.setPassword(user.getPassword());
//		rp.setCreated(unixDate+"");
//		rp.setExpiration(expiration+"");
//		
//		String json = gson.toJson(rp);
//		String json2 = gson.toJson(params);
//		System.out.println("reset password params:"+params);
//		
//		System.out.println("user id: "+rp.getUserid());
//		int isSaved= resetpasswordDAOImpl.save(rp);
//		System.out.println("isSave = "+isSaved);
//		if(isSaved==1){
//			
//				System.out.println("JSON:"+gson.toJson(rp));
//			}
//		
//		System.out.println("code: "+RandomStringUtils.randomAlphanumeric(6));
//		
//		if( unixDate <  expiration){
//	
//				ApplicationContext a = new ClassPathXmlApplicationContext("Spring-Mail.xml");
//				System.out.println(a);
//				MailModel mail = (MailModel) a.getBean("mail");
//				
//				mail.ResetPass(user,rp,request);
//				logger.info("success");
//						
//		
//		}
//		return isSaved+"";
//	
//	}

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
//	@SuppressWarnings("deprecation")
//	@RequestMapping(value="saveApplication2",produces="application/json")
//	public @ResponseBody boolean saveApplication2(
//			@RequestParam(value="jsondetails",required=false) String app,
//			@RequestParam(value = "ispushed", required = false) String lastName,
//			@RequestParam(value = "created", required = false) String created,
//			@RequestParam(value = "version", required = false) String version,
//			@RequestParam(value = "edited_by", required = false) String edited_by,
//			@RequestParam(value = "updated", required = false) String updated,
//			@RequestParam(value = "application_id", required = false) String application_id,
//			@RequestParam(value = "application_type", required = false) String application_type,
//			@RequestParam(value = "user_id", required = false) String user_id
//			)throws NullPointerException, IOException, SQLException{
        //parse request parameter to json
//		System.out.println(app);
//		JsonElement root = new JsonParser().parse(app);
		//initiate the POJO (plain old java object)
//		Application2 application = new Application2();
		//get elements by name then bind it to the model
		/*application.setApplication_id(root.getAsJsonObject().get("application_id").getAsString());
		application.setApplication_type(root.getAsJsonObject().get("application_type").getAsString());
		application.setCompany_id(root.getAsJsonObject().get("company_id").getAsString());
		application.setUser_id(root.getAsJsonObject().get("userid").getAsString());
		application.setCreated(root.getAsJsonObject().get("created").getAsString());
		application.setUpdated(root.getAsJsonObject().get("updated").getAsString());
		application.setStatus(root.getAsJsonObject().get("status").getAsString());
		application.setIspushed(root.getAsJsonObject().get("ispushed").getAsString());
		application.setVersion(version);
		application.setDetails(app);*/
		//String[] detailArray = root.getAsJsonObject().get("details").getAsJsonArray();
		
//		if(application_type=="personal"){
			/*JsonElement details = new JsonParser().parse(application.getDetails());
			//get json array from details 
			JsonArray attachments = (JsonArray) details.getAsJsonObject().get("attachments").getAsJsonArray();
			//iterate array elements from with in attachments
			for(int i = 0; i< attachments.size();i++){
				System.out.println(((JsonObject)attachments.get(i)).get("attachment_id"));
			}
			//same as above using a different procedure binding it to a POJO
			Gson gson = new Gson();
			Attachment[] attachmentData = gson.fromJson(attachments,Attachment[].class);
			for(int i = 0;i< attachmentData.length;i++){
				System.out.println(attachmentData[i].getAttachment_id());
			}*/
			//JsonElement details = new JsonParser().parse(app);
//			Gson gson = new Gson();
			//Residential residential = gson.fromJson(app,Residential.class);
//			System.out.println(app);
//		}
//		else if(application_type=="business"){
//			Gson gson = new Gson();
//			Commercial commercial = gson.fromJson(app,Commercial.class);
//			System.out.println(app);
//		}
//		int saveApplication = application2DAOImpl.save(application);
//		System.out.println(saveApplication);
//		System.out.println(application.getDetails());
//		return false;
		
		/// parse json request parameter to application class first, then get the detail json object then parse it with
		/// the corresponding POJO(residential/commercial). to get the attachments, get the attachment json array from 
		/// the detail class then parse it to the attachment POJO 
		
		
		/// just add a request mapper for the update application.
//	}
	

}
