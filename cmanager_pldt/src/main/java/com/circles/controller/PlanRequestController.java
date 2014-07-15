package com.circles.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.circles.model.*;
import com.circles.dao.impl.PlanDAOImpl;
import com.circles.dao.impl.ProductDAOImpl;
import com.circles.utils.*;
import com.circles.model.Plan;
@Controller
@SessionAttributes("userid")
public class PlanRequestController {


	@Autowired
	private ProductDAOImpl productDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	

	@RequestMapping(value="/savePlan", produces="application/json")
	public @ResponseBody  String savePlan(
			@RequestParam(value="product_id", required=false) String product_id,
			@RequestParam(value="product_name", required=false) String product_name,
			@RequestParam(value="product_desc",required=false) String product_desc,
			@RequestParam(value="conditions",required=false) String conditions,
			@RequestParam(value="device",required=false) String device,
			@RequestParam(value="monthly_price",required=false) String monthly_price,
			@RequestParam(value="one_time_price",required=false) String one_time_price,
			@RequestParam(value="image_name",required=false) MultipartFile image,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{

		Products pd = new Products();
		
		pd.setProduct_id(product_id);
		pd.setProduct_name(product_name);
		pd.setProduct_desc(product_desc);
		pd.setCondition(conditions);
		pd.setDevice(device);
		pd.setMonthly_price(monthly_price);
		pd.setOne_time_price(one_time_price);
		pd.setFilename(image.getOriginalFilename());
		pd.setEdited_by(edited_by);
		pd.setIspushed(ispushed);
		pd.setVersion(version);
		pd.setCreated(created);
		pd.setUpdated(updated);
		
		int isSaved = productDAOImpl.save(pd);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updatePlan", produces="application/json")
	public @ResponseBody  String updatePlan(
			@RequestParam(value="product_id", required=false) String product_id,
			@RequestParam(value="product_name", required=false) String product_name,
			@RequestParam(value="product_desc",required=false) String product_desc,
			@RequestParam(value="conditions",required=false) String conditions,
			@RequestParam(value="device",required=false) String device,
			@RequestParam(value="monthly_price",required=false) String monthly_price,
			@RequestParam(value="one_time_price",required=false) String one_time_price,
			@RequestParam(value="image_name",required=false) MultipartFile image,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{
		
		String date  = ""+new Date().getTime()+"";

		Products pd = new Products();
		
		pd.setProduct_id(product_id);
		pd.setProduct_name(product_name);
		pd.setProduct_desc(product_desc);
		pd.setCondition(conditions);
		pd.setDevice(device);
		pd.setMonthly_price(monthly_price);
		pd.setOne_time_price(one_time_price);
		pd.setFilename(image.getOriginalFilename());
		pd.setEdited_by(edited_by);
		pd.setIspushed(ispushed);
		pd.setVersion(version);
		pd.setCreated(created);
		pd.setUpdated(updated);
		
		int isSaved = productDAOImpl.save(pd);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deletePlan", produces="application/json")
	public @ResponseBody  String deletePlan(
			@RequestParam(value="product_id", required=false) String product_id) throws SQLException{

		Products pd = new Products();
		
		pd.setProduct_id(product_id);

		int isDeleted = productDAOImpl.delete(pd);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllPlan", produces="application/json")
	public @ResponseBody  Collection selectAllPlans() throws SQLException{
		
		Products pd = new Products();
		return productDAOImpl.viewAllProduct(pd);
		
	}
	@RequestMapping(value="/selectPlan", produces="application/json")
	public @ResponseBody  Collection selectPlan(
			@RequestParam(value="product_id", required=false) String product_id,
			@RequestParam(value="product_name", required=false) String product_name,
			@RequestParam(value="product_desc",required=false) String product_desc,
			@RequestParam(value="conditions",required=false) String conditions,
			@RequestParam(value="device",required=false) String device,
			@RequestParam(value="monthly_price",required=false) String monthly_price,
			@RequestParam(value="one_time_price",required=false) String one_time_price,
			@RequestParam(value="image_name",required=false) MultipartFile image,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{

		Products pd = new Products();
		
		pd.setProduct_id(product_id);
		pd.setProduct_name(product_name);
		pd.setProduct_desc(product_desc);
		pd.setCondition(conditions);
		pd.setDevice(device);
		pd.setMonthly_price(monthly_price);
		pd.setOne_time_price(one_time_price);
		pd.setFilename(image.getOriginalFilename());
		pd.setEdited_by(edited_by);
		pd.setIspushed(ispushed);
		pd.setVersion(version);
		pd.setCreated(created);
		pd.setUpdated(updated);
		
		Collection s = productDAOImpl.viewEdit(pd);
		
		return s;
		
	}
//	------------------------------------------------------CATEGORY-------------------------------------------------
	
	@RequestMapping(value="/saveCategory", produces="application/json")
	public @ResponseBody  String saveCategory(
			@RequestParam(value="groupid", required=false) String groupid,
			@RequestParam(value="category_name", required=false) String category_name,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false) String edited_by
			)throws SQLException{

		Category cat = new Category();
		
		cat.setGroupid(groupid);
		cat.setCategory_name(category_name);
		cat.setCreated(created);
		cat.setUpdated(updated);
		cat.setVersion(version);
		cat.setEdited_by(edited_by);
		
		int isSaved = productDAOImpl.addCategory(cat);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updateCategory", produces="application/json")
	public @ResponseBody  String updateCategory(
			@RequestParam(value="groupid", required=false) String groupid,
			@RequestParam(value="category_name", required=false) String category_name,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false) String edited_by
			)throws SQLException{

		Category cat = new Category();
		
		cat.setGroupid(groupid);
		cat.setCategory_name(category_name);
		cat.setCreated(created);
		cat.setUpdated(updated);
		cat.setVersion(version);
		cat.setEdited_by(edited_by);
		
		int isSaved = productDAOImpl.addCategory(cat);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deleteCategory", produces="application/json")
	public @ResponseBody  String deleteCategory(
			@RequestParam(value="groupid", required=false) String groupid) throws SQLException{

		Category cat = new Category();
		
		cat.setGroupid(groupid);

		int isDeleted = productDAOImpl.deleteCat(cat);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllCategory", produces="application/json")
	public @ResponseBody  Collection selectAllCategory() throws SQLException{
		
		Category cat = new Category();
		return productDAOImpl.viewCategory(cat);
		
	}
	@RequestMapping(value="/selectCategory", produces="application/json")
	public @ResponseBody  Collection selectCategory(
			@RequestParam(value="groupid", required=false) String groupid,
			@RequestParam(value="category_name", required=false) String category_name,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false) String edited_by
			)throws SQLException{

		Category cat = new Category();
		
		cat.setGroupid(groupid);
		cat.setCategory_name(category_name);
		cat.setCreated(created);
		cat.setUpdated(updated);
		cat.setVersion(version);
		cat.setEdited_by(edited_by);
		
		Collection s = productDAOImpl.viewSelectedCategory(cat);
		
		return s;
		
	}
}
