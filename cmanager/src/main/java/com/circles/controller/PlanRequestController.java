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

import com.circles.model.*;

import com.circles.dao.impl.PlanDAOImpl;
import com.circles.utils.*;


import com.circles.model.Plan;
@Controller
public class PlanRequestController {


	@Autowired
	private PlanDAOImpl planDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	

	@RequestMapping(value="/savePlan", produces="application/json")
	public @ResponseBody  String savePlan(
			@RequestParam(value="plan_id", required=false) String plan_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="plan_name",required=false) String plan_name,
			@RequestParam(value="plan_code",required=false) String plan_code,
			@RequestParam(value="plan_type",required=false) String plan_type,
			@RequestParam(value="plan_desc",required=false) String plan_desc,
			@RequestParam(value="plan_price",required=false) String plan_price,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{

		Plan plan = new Plan();
		
		plan.setPlan_id(plan_id);
		plan.setAvailability(availability);
		plan.setPlan_name(plan_name);
		plan.setPlan_code(plan_code);
		plan.setPlan_type(plan_type);
		plan.setPlan_desc(plan_desc);
		plan.setPlan_price(plan_price);
		plan.setEdited_by(edited_by);
		plan.setIspushed(ispushed);
		plan.setVersion(version);
		plan.setCreated(created);
		plan.setUpdated(updated);
		int isSaved = planDAOImpl.save(plan);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updatePlan", produces="application/json")
	public @ResponseBody  String updatePlan(
			@RequestParam(value="plan_id", required=false) String plan_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="plan_name",required=false) String plan_name,
			@RequestParam(value="plan_code",required=false) String plan_code,
			@RequestParam(value="plan_type",required=false) String plan_type,
			@RequestParam(value="plan_desc",required=false) String plan_desc,
			@RequestParam(value="plan_price",required=false) String plan_price,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{

		Plan plan = new Plan();
		
		plan.setPlan_id(plan_id);
		plan.setAvailability(availability);
		plan.setPlan_name(plan_name);
		plan.setPlan_code(plan_code);
		plan.setPlan_type(plan_type);
		plan.setPlan_desc(plan_desc);
		plan.setPlan_price(plan_price);
		plan.setEdited_by(edited_by);
		plan.setIspushed(ispushed);
		plan.setVersion(version);
		plan.setCreated(created);
		plan.setUpdated(updated);
		int isSaved = planDAOImpl.save(plan);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deletePlan", produces="application/json")
	public @ResponseBody  String deletePlan(
			@RequestParam(value="plan_id", required=false) String plan_id) throws SQLException{

		Plan plan = new Plan();
		
		plan.setPlan_id(plan_id);

		int isDeleted = planDAOImpl.delete(plan);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllPlan", produces="application/json")
	public @ResponseBody  Collection selectAllPlans() throws SQLException{

		return planDAOImpl.selectAllPlan();
		
	}
	@RequestMapping(value="/selectPlan", produces="application/json")
	public @ResponseBody  Collection selectPlan(
			@RequestParam(value="plan_id", required=false) String plan_id,
			@RequestParam(value="availability", required=false) String availability,
			@RequestParam(value="plan_name",required=false) String plan_name,
			@RequestParam(value="plan_code",required=false) String plan_code,
			@RequestParam(value="plan_type",required=false) String plan_type,
			@RequestParam(value="plan_desc",required=false) String plan_desc,
			@RequestParam(value="plan_price",required=false) String plan_price,
			@RequestParam(value="edited_by",required=false) String edited_by,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated
			)throws SQLException{

		Plan plan = new Plan();
		
		plan.setPlan_id(plan_id);
		plan.setAvailability(availability);
		plan.setPlan_name(plan_name);
		plan.setPlan_code(plan_code);
		plan.setPlan_type(plan_type);
		plan.setPlan_desc(plan_desc);
		plan.setPlan_price(plan_price);
		plan.setEdited_by(edited_by);
		plan.setIspushed(ispushed);
		plan.setVersion(version);
		plan.setCreated(created);
		plan.setUpdated(updated);
		
		Collection s = planDAOImpl.selectPlan(plan);
		
		return s;
		
	}
	
}
