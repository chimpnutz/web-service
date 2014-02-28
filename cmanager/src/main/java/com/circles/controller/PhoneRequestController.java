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

import javax.servlet.http.HttpServletResponse;

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

import com.circles.model.*;
import com.circles.dao.impl.PhoneDAOImpl;
import com.circles.utils.*;
import com.circles.model.Phone;
@Controller
public class PhoneRequestController {


	@Autowired
	private PhoneDAOImpl phoneDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	
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

	@RequestMapping(value="/savePhone", produces="application/json")
	public @ResponseBody  String savePhone(
			@RequestParam(value="phone_id", required=false) String phone_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="phone_model",required=false) String phone_model,
			@RequestParam(value="phone_code",required=false) String phone_code,
			@RequestParam(value="phone_mfr",required=false) String phone_mfr,
			@RequestParam(value="phone_desc",required=false) String phone_desc,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="image",required=false) MultipartFile image,
			@RequestParam(value="image",required=false) MultipartFile image2,
			@RequestParam(value="image",required=false) MultipartFile image3,
			@RequestParam(value="image",required=false) MultipartFile image4
			)throws SQLException{

		Phone phone = new Phone();
		
		phone.setPhone_id(phone_id);
		phone.setAvailability(availability);
		phone.setPhone_model(phone_model);
		phone.setPhone_code(phone_code);
		phone.setPhone_mfr(phone_mfr);
		phone.setPhone_desc(phone_desc);
		phone.setEdited_by(edited_by);
		phone.setIspushed(ispushed);
		phone.setVersion(version);
		phone.setCreated(created);
		phone.setUpdated(updated);
		phone.setImage_name(image.getOriginalFilename());

	
		try{
			if(!image.isEmpty()||!image.equals(null)||!image.equals("")){
				FileUpload(image);
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image2.isEmpty()||!image2.equals(null)||!image2.equals("")){
				FileUpload(image2);
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image3.isEmpty()||!image3.equals(null)||!image3.equals("")){
				FileUpload(image3);
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image4.isEmpty()||!image4.equals(null)||!image4.equals("")){
				FileUpload(image4);
			}
		}catch(NullPointerException e){
			
		}
		int isSaved = phoneDAOImpl.save(phone);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updatePhone", produces="application/json")
	public @ResponseBody  String updatePhone(
			@RequestParam(value="phone_id", required=false) String phone_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="phone_model",required=false) String phone_model,
			@RequestParam(value="phone_code",required=false) String phone_code,
			@RequestParam(value="phone_mfr",required=false) String phone_mfr,
			@RequestParam(value="phone_desc",required=false) String phone_desc,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="image",required=false) MultipartFile image

			)throws SQLException{

		Phone phone = new Phone();
		
		phone.setPhone_id(phone_id);
		phone.setAvailability(availability);
		phone.setPhone_model(phone_model);
		phone.setPhone_code(phone_code);
		phone.setPhone_mfr(phone_mfr);
		phone.setPhone_desc(phone_desc);
		phone.setEdited_by(edited_by);
		phone.setIspushed(ispushed);
		phone.setVersion(version);
		phone.setCreated(created);
		phone.setUpdated(updated);
		phone.setImage_name(image.getOriginalFilename());

		
		try{
			if(!image.isEmpty()||!image.equals(null)||!image.equals("")){
				FileUpload(image);
			}
		}catch(NullPointerException e){
			
		}

		int isSaved = phoneDAOImpl.save(phone);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deletePhone", produces="application/json")
	public @ResponseBody  String deletePhone(
			@RequestParam(value="phone_id", required=false) String phone_id) throws SQLException{

		Phone phone = new Phone();
		
		phone.setPhone_id(phone_id);

		int isDeleted = phoneDAOImpl.delete(phone);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllPhone", produces="application/json")
	public @ResponseBody  Collection selectAllPhones() throws SQLException{

		return phoneDAOImpl.selectAllPhone();
		
	}
	
	@RequestMapping("/getImage_name")
	@ResponseBody
	public byte[] getImage_name(@RequestParam("filename") String filename,HttpServletResponse response) throws IOException {

	    File imageFile = new File("C:/Users/PayExchange/workspace/cmanager/src/main/webapp/resources/uploaded/"+filename);
	    byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(imageFile);

	    response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
	    response.setContentLength(bytes.length);
	    response.setContentType("image/jpeg");


	    return bytes;
	}
	
	@RequestMapping(value="/selectPhone", produces="application/json")
	public @ResponseBody  Collection selectPhone(
			@RequestParam(value="phone_id", required=false) String phone_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="phone_model",required=false) String phone_model,
			@RequestParam(value="phone_code",required=false) String phone_code,
			@RequestParam(value="phone_mfr",required=false) String phone_mfr,
			@RequestParam(value="phone_desc",required=false) String phone_desc,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="image",required=false) String image

			)throws SQLException{

		Phone phone = new Phone();
		
		phone.setPhone_id(phone_id);
		phone.setAvailability(availability);
		phone.setPhone_model(phone_model);
		phone.setPhone_code(phone_code);
		phone.setPhone_mfr(phone_mfr);
		phone.setPhone_desc(phone_desc);
		phone.setEdited_by(edited_by);
		phone.setIspushed(ispushed);
		phone.setVersion(version);
		phone.setCreated(created);
		phone.setUpdated(updated);
		phone.setImage_name(image);

		
		Collection s = phoneDAOImpl.selectPhone(phone);
		
		return s;
		
	}
	
}
