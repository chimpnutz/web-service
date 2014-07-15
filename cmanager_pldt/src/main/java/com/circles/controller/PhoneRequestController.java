package com.circles.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.CommentDAOImpl;
import com.circles.dao.impl.PhoneDAOImpl;
import com.circles.dao.impl.ProductDAOImpl;
import com.circles.utils.*;
import com.circles.model.Phone;
@Controller
@SessionAttributes("userid")
public class PhoneRequestController {


	@Autowired
	private ProductDAOImpl productDAOImpl;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	
	@RequestMapping(value="/viewproduct")	
	
	public boolean FileUpload(MultipartFile fileName,@RequestParam(value="dev_image",required=false)
			Model model){
		String filepath ="C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
		
		
		
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
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="deviceid",required=false) String deviceid,
			@RequestParam(value="device_name",required=false) String device_name,
			@RequestParam(value="dev_desc",required=false) String dev_desc,
			@RequestParam(value="dev_image",required=false) final MultipartFile filename) throws IOException{
			
		Devices dev = new Devices();
		
		
		String date  = ""+new Date().getTime()+"";
		
		dev.setDeviceid(deviceid);
		dev.setDevice_name(device_name);
		dev.setDescription(dev_desc);
		dev.setCreated(date);
		
		
	
		try{
			if(!filename.isEmpty()||!filename.equals(null)||!filename.equals("")){
				FileUpload(filename, null);
			}
		}catch(NullPointerException e){
			
		}
		
		int save = productDAOImpl.addDevice(dev);
		
		if(save == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updatePhone", produces="application/json")
	public @ResponseBody  String updatePhone(
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="deviceid",required=false) String deviceid,
			@RequestParam(value="device_name",required=false) String device_name,
			@RequestParam(value="dev_desc",required=false) String dev_desc,
			@RequestParam(value="dev_image",required=false) final MultipartFile filename) throws IOException{

		Devices dev = new Devices();
		
		String date  = ""+new Date().getTime()+"";
		
		dev.setDeviceid(deviceid);
		dev.setDevice_name(device_name);
		dev.setDescription(dev_desc);
		dev.setCreated(date);
		
		
	
		try{
			if(!filename.isEmpty()||!filename.equals(null)||!filename.equals("")){
				FileUpload(filename, null);
			}
		}catch(NullPointerException e){
			
		}
		
		int save = productDAOImpl.addDevice(dev);
		
		if(save == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deletePhone", produces="application/json")
	public @ResponseBody  String deletePhone(
			@RequestParam(value="deviceid", required=false) String deviceid) throws SQLException{

		Devices dev = new Devices();
		
		dev.setDeviceid(deviceid);

		int isDeleted = productDAOImpl.deleteDev(dev);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllPhone", produces="application/json")
	public @ResponseBody  Collection selectAllPhones() throws SQLException{
		
		Devices dev = new Devices();
		
		return productDAOImpl.viewDevice(dev);
		
	}
	
	@RequestMapping("/getImage")
	@ResponseBody
	public byte[] getImage_name(@RequestParam("filename") String filename,HttpServletResponse response) throws IOException {
		Resource resource = new ClassPathResource("../properties/filepath.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		String filepath = props.getProperty("filepath.url");
//				"C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/"+filename+"";			
				
	    File imageFile = new File(filepath+filename);
	    byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(imageFile);

	    response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
	    response.setContentLength(bytes.length);
	    response.setContentType("image/jpeg");


	    return bytes;
	}
	
	@RequestMapping(value="/selectPhone", produces="application/json")
	public @ResponseBody  Collection selectPhone(
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="deviceid",required=false) String deviceid,
			@RequestParam(value="device_name",required=false) String device_name,
			@RequestParam(value="dev_desc",required=false) String dev_desc,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="dev_image",required=false) final String filename) throws IOException{
		
		Devices dev = new Devices();
		
		dev.setDeviceid(deviceid);
		dev.setDevice_name(device_name);
		dev.setDescription(dev_desc);
		dev.setCreated(created);
		dev.setDevice_image(filename);

		
		Collection s = productDAOImpl.viewSelectDevice(dev);
		
		return s;
		
	}
	
}
