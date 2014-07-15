package com.circles.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.circles.dao.impl.ProductDAOImpl;
import com.circles.model.Category;
import com.circles.model.Devices;
import com.circles.model.Products;
import com.circles.model.User;

@Controller
@SessionAttributes("userid")
public class ProductController {
	
	@Autowired
	ProductDAOImpl productDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/addcategory", method = RequestMethod.GET)
	public String viewCat(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
	    	
	    	return "manager/addcategory";
	    }
		
	}
	
	
	
	@RequestMapping(value="/savedevice", method = RequestMethod.GET)
	public String viewDev(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
	    	
	    	return "manager/savedevice";
	    }
		
	}
	
	@RequestMapping(value="/viewdevice", method = RequestMethod.GET,params="devid")
	public String viewSelectedDevice(@RequestParam("devid") String devid,@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
	    	Devices dev = new Devices();
	    	
	    	Collection y = null;
	    	
	    	dev.setDeviceid(devid);
	    	
    		y = productDAOImpl.viewSelectDevice(dev);
	    	
    		model.addAttribute("devices",y);
	    	
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
	    	
	    	return "manager/viewdevice";
	    }
		
	}
	
	@RequestMapping(value="/editproduct", method = RequestMethod.GET,params="id")
	public String viewEdit(@RequestParam("id") String id,@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			HttpServletRequest request) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	System.out.println("productid: "+id);
	    	
	    	Products pd = new Products();
	    	
	    	pd.setProduct_id(id);
	    	
	    	Collection x = null;
	    	
	    	x = productDAOImpl.viewEdit(pd);
	    	
	    	Devices dev = new Devices();
	    	
	    	Collection y = null;
	    	
    		y = productDAOImpl.viewDevice(dev);
	    	
    		model.addAttribute("devices",y);
	    	model.addAttribute("edit",x);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
	    	return "manager/editproduct";
	    }
	}
	
	
	
	@RequestMapping(value="/viewproduct", method = RequestMethod.GET)
	public String viewProduct(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			HttpServletRequest request) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
	    	Category cat = new Category();
	    	Products pd = new Products();	
	    	
	    	Collection x = null;
	    	
	    		x = productDAOImpl.viewAll(pd);
	    	
			Collection s = null;	
		
				s= productDAOImpl.viewCategory(cat);
	
			model.addAttribute("category",s);
			model.addAttribute("viewall",x);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/viewproduct";	
	    }
	}
	
	
	@RequestMapping(value="/addproduct", method = RequestMethod.GET,params="id")
	public String addProduct(@RequestParam("id") String id,@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,@RequestParam(value="devid", required=false) String devid,
			HttpServletRequest request) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	System.out.println("ID: "+id);
	    	
	    	Category cat = new Category();
	    	Devices dev = new Devices();
	    	
	    	cat.setGroupid(id);
	    		    	
	    	Collection x = null;
	    	
    		x = productDAOImpl.viewDevice(dev);
    		
    		Collection y = null;
    		
    		y = productDAOImpl.viewSelectedCategory(cat);
    		  
    		model.addAttribute("prodid",id);
    		model.addAttribute("devices",x);
    		model.addAttribute("category",y);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
	    	return "manager/addproduct";
	    }
	}
	
	@RequestMapping(value="/saveCategory", method = RequestMethod.POST)
	public String saveCat(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="category_name",required=false) String category_name){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
	    	if(add!=null){
	    		
	    		Category cat = new Category();
	    		String categoryUID = "{"+UUID.randomUUID().toString()+"}";    		
	    		String date = ""+new Date().getTime()+"";
	    		cat.setGroupid(categoryUID);
	    		cat.setCategory_name(category_name);
	    		cat.setCreated(date);
	    		cat.setUpdated(date);
	    		cat.setVersion("1");
	    		cat.setEdited_by(session.getAttribute("USER")+"");
	    		
	    		int save = productDAOImpl.addCategory(cat);
	    		model.addAttribute("check","success");
	    		model.addAttribute("valid","category has been created");
	    		model.addAttribute("role",session.getAttribute("ROLE"));
			 	model.addAttribute("user",session.getAttribute("USER"));
			 	
			 	return "manager/addcategory";
	    	}
	    	else{
	    		System.out.println("Error!");
	    		return "manager/addcategory";
	    	}
			
	    }
		
	}
	
	@RequestMapping(value="/saveDevice", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public String saveDev(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="device_name",required=false) String device_name,
			@RequestParam(value="dev_desc",required=false) String dev_desc,
			@RequestParam(value="dev_image",required=false) final MultipartFile filename) throws IOException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	
	    	if(add!=null){
	    		
	    		Devices dev = new Devices();
	    		    		
	    		String date = ""+new Date().getTime()+"";
	    		String deviceUID = "{"+UUID.randomUUID().toString()+"}";
	    		
	    		dev.setDeviceid(deviceUID);
	    		dev.setDevice_name(device_name);
	    		dev.setDescription(dev_desc);
	    		String s = "";
				try{
//					Resource resource = new ClassPathResource("../properties/filepath.properties");
//					Properties props = PropertiesLoaderUtils.loadProperties(resource);
	
					String filepath = "C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
//							props.getProperty("filepath.url");
					BufferedImage img = ImageIO.read(filename.getInputStream());
			        File file = new File(filepath+filename.getOriginalFilename());
			        s=filename.getOriginalFilename();
			        file.createNewFile();
			        System.out.println("filename: "+file);
			        ImageIO.write(img, "jpg", file);
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}
				dev.setDevice_image(s);
	    		dev.setCreated(date);
	    		dev.setUpdated(date);
	    		dev.setVersion("1");
	    		dev.setEdited_by(session.getAttribute("USER")+"");
	    		
//	    		int save = productDAOImpl.addDevice(dev);
	    		
	    		model.addAttribute("check","success");
	    		model.addAttribute("valid","device/hardware has been created");
	    		model.addAttribute("role",session.getAttribute("ROLE"));
			 	model.addAttribute("user",session.getAttribute("USER"));
			 	
			 	return "manager/savedevice";
	    	}
	    	else{
	    		System.out.println("Error!");
	    		return "manager/savedevice";
	    	}
			
	    }
		
	}
	
	@RequestMapping(value="/saveProduct",headers = "content-type=multipart/*")
	public String saveProduct(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="addprod",required=false) String addprod,
			@RequestParam(value="prod_name",required=false) String prod_name ,
			@RequestParam(value="check1",required=false) String check1 ,
			@RequestParam(value="check2",required=false) String check2 ,
			@RequestParam(value="device",required=false) String device_name ,
			@RequestParam(value="product_desc",required=false) String product_desc ,			
			@RequestParam(value="condition",required=false) String condition,
			@RequestParam(value="monthly_price",required=false) String monthly_price,
			@RequestParam(value="one_time_price",required=false) String one_time_price,
			@RequestParam(value="prod_image",required=false) final MultipartFile filename,
			HttpServletRequest request) throws SQLException, IOException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	if(addprod!=null){
	    		
	    		String NUMB_REGEX = "^((\\d+([\\.]\\d{0,2})*)|([\\.]\\d{0,2})|()|\\s*)$";
	    		
	    		Devices dev = new Devices();
	    		
	    		String date = ""+new Date().getTime()+"";
	    		
	    		Products pd = new Products();
				
				String productUID = "{"+UUID.randomUUID().toString()+"}";
				
				pd.setProduct_name(prod_name);
				
				int checkProduct = productDAOImpl.checkProduct(pd);
				
				if(!monthly_price.matches(NUMB_REGEX)&&!one_time_price.matches(NUMB_REGEX)){
					
					Collection x = null;
			    	
		    		x = productDAOImpl.viewDevice(dev);
		    		
		    		model.addAttribute("check","fail");
		    		model.addAttribute("valid","price should be numeric! and only accepts two decimals");
		    		System.out.println("Error, price should be numeric!");
		    		model.addAttribute("devices",x);
		    		model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));
				 	return "manager/addproduct";
				}
				else if(checkProduct==1){
					Collection x = null;
			    	
		    		x = productDAOImpl.viewDevice(dev);
		    		
		    		model.addAttribute("check","fail");
		    		model.addAttribute("valid","product is already exist!");
		    		System.out.println("Error, product is already exist!");
		    		model.addAttribute("devices",x);
		    		model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));
				 	return "manager/addproduct";
				}
				else{
					
					if(check1!=null){
							
						System.out.println("Groupid "+id);
						System.out.println("san ako nag edit?");
						pd.setGroupid(id);
						pd.setProduct_id(productUID);
						pd.setProduct_name(prod_name);
						pd.setProduct_desc(product_desc);
						pd.setCondition(condition);
						pd.setCreated(date);
						pd.setUpdated(date);

						String s = "";
						if(filename!=null){
							
							try{
//								Resource resource = new ClassPathResource("../properties/filepath.properties");
//								Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
								String filepath = "C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
//										props.getProperty("filepath.url");
								BufferedImage img = ImageIO.read(filename.getInputStream());
						        File file = new File(filepath+filename.getOriginalFilename());
						        s=filename.getOriginalFilename();
						        file.createNewFile();
						        System.out.println("filename: "+file);
						        ImageIO.write(img, "jpg", file);
							}catch(IllegalArgumentException e){
								e.printStackTrace();
							}
							pd.setFilename(s);
							pd.setDevice(device_name);
							pd.setMonthly_price(monthly_price);
							pd.setOne_time_price(one_time_price);
							pd.setEdited_by(session.getAttribute("USER")+"");
							pd.setVersion("1");
							
							int save = productDAOImpl.save(pd);
							
							
							Collection x = null;
					    	
				    		x = productDAOImpl.viewDevice(dev);
				    		
				    		model.addAttribute("check","success");
				    		model.addAttribute("valid","product has been created");
				    		model.addAttribute("devices",x);
				    		model.addAttribute("role",session.getAttribute("ROLE"));
						 	model.addAttribute("user",session.getAttribute("USER"));
						 	
						 	return "manager/addproduct";
						}
						else{
							pd.setFilename(filename+"");
							pd.setDevice(device_name);
							pd.setMonthly_price(monthly_price);
							pd.setOne_time_price(one_time_price);
							pd.setEdited_by(session.getAttribute("USER")+"");
							pd.setVersion("1");
							
							int save = productDAOImpl.save(pd);
							
							
							Collection x = null;
					    	
				    		x = productDAOImpl.viewDevice(dev);
				    		
				    		model.addAttribute("check","success");
				    		model.addAttribute("valid","product has been created");
				    		model.addAttribute("devices",x);
				    		model.addAttribute("role",session.getAttribute("ROLE"));
						 	model.addAttribute("user",session.getAttribute("USER"));
						 	
						 	return "manager/addproduct";
						}
						
	
					
	    		}
				else{
					
					System.out.println("Groupid "+id);
					System.out.println("san ako nag edit?");
					pd.setGroupid(id);
					pd.setProduct_id(productUID);
					pd.setProduct_name(prod_name);
					pd.setProduct_desc(product_desc);
					pd.setCondition(condition);
					pd.setCreated(date);
					pd.setUpdated(date);

					String s = "";
					if(filename!=null){
						
						try{
//							Resource resource = new ClassPathResource("../properties/filepath.properties");
//							Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
							String filepath = "C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
//									props.getProperty("filepath.url");
							BufferedImage img = ImageIO.read(filename.getInputStream());
					        File file = new File(filepath+filename.getOriginalFilename());
					        s=filename.getOriginalFilename();
					        file.createNewFile();
					        System.out.println("filename: "+file);
					        ImageIO.write(img, "jpg", file);
						}catch(IllegalArgumentException e){
							e.printStackTrace();
						}
						pd.setFilename(s);
						pd.setDevice(device_name);
						pd.setMonthly_price(0.00+"");
						pd.setOne_time_price(0.00+"");
						pd.setEdited_by(session.getAttribute("USER")+"");
						pd.setVersion("1");
						
						int save = productDAOImpl.save(pd);
						
						
						Collection x = null;
				    	
			    		x = productDAOImpl.viewDevice(dev);
			    		
			    		model.addAttribute("check","success");
			    		model.addAttribute("valid","product has been created");
			    		model.addAttribute("devices",x);
			    		model.addAttribute("role",session.getAttribute("ROLE"));
					 	model.addAttribute("user",session.getAttribute("USER"));
					 	
					 	return "manager/addproduct";
					}
					else{
						pd.setFilename(filename+"");
						pd.setDevice(device_name);
						pd.setMonthly_price(0.00+"");
						pd.setOne_time_price(0.00+"");
						pd.setEdited_by(session.getAttribute("USER")+"");
						pd.setVersion("1");
						
						int save = productDAOImpl.save(pd);
						
						
						Collection x = null;
				    	
			    		x = productDAOImpl.viewDevice(dev);
			    		
			    		model.addAttribute("check","success");
			    		model.addAttribute("valid","product has been created");
			    		model.addAttribute("devices",x);
			    		model.addAttribute("role",session.getAttribute("ROLE"));
					 	model.addAttribute("user",session.getAttribute("USER"));
					 	
					 	return "manager/addproduct";
					}
					
				}
					
				}
				
	    	}
	    	return "manager/addproduct";
	    }
	}
	
	@RequestMapping(value="/saveEditProduct",headers = "content-type=multipart/*")
	public String saveEditProduct(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="productid", required=false) String prodid,
			@RequestParam(value="addprod",required=false) String addprod,
			@RequestParam(value="prod_name",required=false) String prod_name ,
			@RequestParam(value="check1",required=false) String check1 ,
			@RequestParam(value="check2",required=false) String check2 ,
			@RequestParam(value="device",required=false) String device_name ,
			@RequestParam(value="product_desc",required=false) String product_desc ,
			@RequestParam(value="monthly_price",required=false) String monthly_price,
			@RequestParam(value="one_time_price",required=false) String one_time_price,
			@RequestParam(value="condition",required=false) String condition,
			@RequestParam(value="prod_image",required=false) final MultipartFile filename,
			HttpServletRequest request) throws SQLException, IOException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	if(addprod!=null){
	    		
	    		String NUMB_REGEX = "^((\\d+([\\.]\\d{0,2})*)|([\\.]\\d{0,2})|()|\\s*)$";
	    		
	    		Devices dev = new Devices();
	    		
	    		String date = ""+new Date().getTime()+"";
	    		
	    		Products pd = new Products();
				
				if(!monthly_price.matches(NUMB_REGEX)&&!one_time_price.matches(NUMB_REGEX)){
					
					Collection x = null;
			    	
		    		x = productDAOImpl.viewDevice(dev);
		    		
		    		model.addAttribute("check","fail");
		    		model.addAttribute("valid","price should be numeric! and only accepts two decimals");
		    		System.out.println("Error, price should be numeric!");
		    		model.addAttribute("devices",x);
		    		model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));
				 	return "manager/editproduct";
				}
				
				else{
					
					if(check1!=null){
							
						System.out.println("Groupid "+id);
						pd.setGroupid(id);
						pd.setProduct_id(prodid);
						pd.setProduct_name(prod_name);
						pd.setProduct_desc(product_desc);
						pd.setCondition(condition);
						pd.setCreated(date);
						pd.setUpdated(date);

						String s = "";
						if(filename!=null){
							
							try{
//								Resource resource = new ClassPathResource("../properties/filepath.properties");
//								Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
								String filepath = "C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
//										props.getProperty("filepath.url");
								BufferedImage img = ImageIO.read(filename.getInputStream());
						        File file = new File(filepath+filename.getOriginalFilename());
						        s=filename.getOriginalFilename();
						        file.createNewFile();
						        System.out.println("filename: "+file);
						        ImageIO.write(img, "jpg", file);
							}catch(IllegalArgumentException e){
								e.printStackTrace();
							}
							pd.setFilename(s);
							pd.setDevice(device_name);
							pd.setMonthly_price(monthly_price);
							pd.setOne_time_price(one_time_price);
							pd.setEdited_by(session.getAttribute("USER")+"");
							pd.setVersion("1");
							
							int save = productDAOImpl.save(pd);
							
							
							Collection x = null;
					    	
				    		x = productDAOImpl.viewDevice(dev);
				    		
				    		model.addAttribute("check","success");
				    		model.addAttribute("valid","product has been created");
				    		model.addAttribute("devices",x);
				    		model.addAttribute("role",session.getAttribute("ROLE"));
						 	model.addAttribute("user",session.getAttribute("USER"));
						 	
						 	return "manager/addproduct";
						}
						else{
							pd.setFilename(filename+"");
							pd.setDevice(device_name);
							pd.setMonthly_price(monthly_price);
							pd.setOne_time_price(one_time_price);
							pd.setEdited_by(session.getAttribute("USER")+"");
							pd.setVersion("1");
							
							int save = productDAOImpl.save(pd);
							
							
							Collection x = null;
					    	
				    		x = productDAOImpl.viewDevice(dev);
				    		
				    		Collection y = null;
					    	
				    		y = productDAOImpl.viewSelectedProduct(pd, prodid);
				    		
				    		model.addAttribute("edit",y);				    		
				    		model.addAttribute("check","success");
				    		model.addAttribute("valid","product has been created");
				    		model.addAttribute("devices",x);
				    		model.addAttribute("role",session.getAttribute("ROLE"));
						 	model.addAttribute("user",session.getAttribute("USER"));
						 	
						 	return "manager/editproduct";
						}
					
	    		}
				else{
					
					System.out.println("Groupid "+id);
					pd.setGroupid(id);
					pd.setProduct_id(prodid);
					pd.setProduct_name(prod_name);
					pd.setProduct_desc(product_desc);
					pd.setCondition(condition);
					pd.setCreated(date);
					pd.setUpdated(date);

					String s = "";
					if(filename!=null){
						
						try{
//							Resource resource = new ClassPathResource("../properties/filepath.properties");
//							Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
							String filepath = "C:/Users/tata/workspace/cmanager_pldt/src/main/webapp/resources/uploaded/";
//									props.getProperty("filepath.url");
							BufferedImage img = ImageIO.read(filename.getInputStream());
					        File file = new File(filepath+filename.getOriginalFilename());
					        s=filename.getOriginalFilename();
					        file.createNewFile();
					        System.out.println("filename: "+file);
					        ImageIO.write(img, "jpg", file);
						}catch(IllegalArgumentException e){
							e.printStackTrace();
						}
						pd.setFilename(s);
						pd.setDevice(device_name);
						pd.setMonthly_price(0.00+"");
						pd.setOne_time_price(0.00+"");
						pd.setEdited_by(session.getAttribute("USER")+"");
						pd.setVersion("1");
						
						int save = productDAOImpl.save(pd);
						
						
						Collection x = null;
				    	
			    		x = productDAOImpl.viewDevice(dev);
			    		
			    		model.addAttribute("check","success");
			    		model.addAttribute("valid","product has been created");
			    		model.addAttribute("devices",x);
			    		model.addAttribute("role",session.getAttribute("ROLE"));
					 	model.addAttribute("user",session.getAttribute("USER"));
					 	
					 	return "manager/addproduct";
					}
					else{
						pd.setFilename(filename+"");
						pd.setDevice(device_name);
						pd.setMonthly_price(0.00+"");
						pd.setOne_time_price(0.00+"");
						pd.setEdited_by(session.getAttribute("USER")+"");
						pd.setVersion("1");
						
						int save = productDAOImpl.save(pd);
						
						
						Collection x = null;
				    	
			    		x = productDAOImpl.viewDevice(dev);
			    		
			    		Collection y = null;
				    	
			    		y = productDAOImpl.viewSelectedProduct(pd, prodid);
			    		
			    		model.addAttribute("edit",y);
			    		model.addAttribute("check","success");
			    		model.addAttribute("valid","product has been created");
			    		model.addAttribute("devices",x);
			    		model.addAttribute("role",session.getAttribute("ROLE"));
					 	model.addAttribute("user",session.getAttribute("USER"));
					 	
					 	return "manager/editproduct";
					}
					
				}
					
				}	
				
	    	}
	    	return "manager/editproduct";
	    }
	}
	
	@RequestMapping(value="/getValue",method = RequestMethod.POST,params="getValue")
    public @ResponseBody Collection viewSelectBox(@RequestParam("val") String val,
    		HttpSession usersession,Model model) throws NamingException
    {

		System.out.println("Test Value: "+val);
		
		Devices dev = new Devices();
		
		dev.setDevice_name(val);
		
		Collection x = null;
    	
		x = productDAOImpl.viewNameDevice(dev);
		
		model.addAttribute("selectDevice",x);
		return x;
    }
	
	@RequestMapping(value="/getGroup",method = RequestMethod.POST,params="getGroup")
    public @ResponseBody Collection transfertoRetailer(@RequestParam("id") String id,HttpSession usersession) throws NamingException
    {

		System.out.println("categoryid: "+id);
		
		Products pd = new Products();
		pd.setGroupid(id);
		Collection x = null;
    	
		x = productDAOImpl.viewAll(pd);
		
		return x;
    }
	
	@RequestMapping(value="/prodid",method = RequestMethod.POST,params="prodid")
    public @ResponseBody Collection viewDesc(@RequestParam("id") String id,HttpSession usersession, Model model) throws NamingException
    {

		System.out.println("productid: "+id);
		
		Products pd = new Products();
		
		pd.setProduct_id(id);
		Collection x = null;
    	
		x = productDAOImpl.viewDesc(pd);
		
		
		return x;
    }
	
		

}
