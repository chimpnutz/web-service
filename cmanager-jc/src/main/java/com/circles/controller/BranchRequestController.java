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

import com.circles.model.*;
import com.circles.dao.impl.BranchDAOImpl;
import com.circles.utils.*;
import com.circles.model.Branch;
@Controller
@SessionAttributes("userid")
public class BranchRequestController {


	@Autowired
	private BranchDAOImpl branchDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	

	@RequestMapping(value="/saveBranch", produces="application/json")
	public @ResponseBody  String saveBranch(
			@RequestParam(value="branch_id", required=false) String branch_id,
			@RequestParam(value="branch_name",required=false) String branch_name,
			@RequestParam(value="longitude",required=false) String longitude,
			@RequestParam(value="latitude",required=false) String latitude,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version) throws SQLException{

		Branch branch = new Branch();
		
		branch.setBranch_id(branch_id);
		branch.setBranch_name(branch_name);
		branch.setLongitude(longitude);
		branch.setLatitude(latitude);
		branch.setIspushed(ispushed);
		branch.setEdited_by(edited_by);
		branch.setCreated(created);
		branch.setUpdated(updated);
		branch.setVersion(version);
		int isSaved = branchDAOImpl.save(branch);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updateBranch", produces="application/json")
	public @ResponseBody  String updateBranch(
			@RequestParam(value="branch_id", required=false) String branch_id,
			@RequestParam(value="branch_name",required=false) String branch_name,
			@RequestParam(value="longitude",required=false) String longitude,
			@RequestParam(value="latitude",required=false) String latitude,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version) throws SQLException{

		Branch branch = new Branch();
		
		branch.setBranch_id(branch_id);
		branch.setBranch_name(branch_name);
		branch.setLongitude(longitude);
		branch.setLatitude(latitude);
		branch.setIspushed(ispushed);
		branch.setEdited_by(edited_by);
		branch.setCreated(created);
		branch.setUpdated(updated);
		branch.setVersion(version);
		int isSaved = branchDAOImpl.update(branch);
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deleteBranch", produces="application/json")
	public @ResponseBody  String deleteBranch(
			@RequestParam(value="branch_id", required=false) String branch_id) throws SQLException{

		Branch branch = new Branch();
		
		branch.setBranch_id(branch_id);

		int isDeleted = branchDAOImpl.delete(branch);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	
	@RequestMapping(value="/selectAllBranch", produces="application/json")
	public @ResponseBody  Collection selectAllBranches() throws SQLException{

		return branchDAOImpl.selectAllBranch();
		
	}
	@RequestMapping(value="/selectBranch", produces="application/json")
	public @ResponseBody  Collection selectBranch(
			@RequestParam(value="branch_id", required=false) String branch_id,
			@RequestParam(value="branch_name",required=false) String branch_name,
			@RequestParam(value="longitude",required=false) String longitude,
			@RequestParam(value="latitude",required=false) String latitude,
			@RequestParam(value="ispushed",required=false)String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version) throws SQLException{

		Branch branch = new Branch();
		
		branch.setBranch_id(branch_id);
		branch.setBranch_name(branch_name);
		branch.setLongitude(longitude);
		branch.setLatitude(latitude);
		branch.setIspushed(ispushed);
		branch.setEdited_by(edited_by);
		branch.setCreated(created);
		branch.setUpdated(updated);
		branch.setVersion(version);
		Collection s = null;
		
		s = branchDAOImpl.selectBranch(branch);
		
		return s;
		
	}
	
}
