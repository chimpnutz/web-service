package com.circles.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import com.circles.dao.impl.CommentDAOImpl;
import com.circles.utils.*;
import com.circles.model.Comment;
@Controller
@SessionAttributes("userid")
public class CommentRequestController {


	@Autowired
	private CommentDAOImpl commentDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRequestController.class);
	

	@RequestMapping(value="/saveComment", produces="application/json")
	public @ResponseBody  String saveComment(
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version,
			@RequestParam(value="content",required=false)String content) throws SQLException{

		Comment comment = new Comment();
		String commentUID = "{"+UUID.randomUUID().toString()+"}";

        System.out.println(user_id);
   
		comment.setId(id);
		comment.setApplication_id(application_id);
		comment.setUser_id(user_id);
		comment.setIspushed(ispushed);
		comment.setEdited_by(edited_by);
		comment.setCreated(created);
		comment.setUpdated(updated);
		comment.setVersion(version);
		comment.setContent(content);


		int isSaved = 0;
		int isExisting = commentDAOImpl.checkIfExists(comment);
		System.out.println(isExisting);
		if(isExisting == 0){
			isSaved = commentDAOImpl.save(comment);
		}
		
		
		if(isSaved == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/updateComment", produces="application/json")
	public @ResponseBody  String updateComment(
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version,
			@RequestParam(value="content",required=false)String content) throws SQLException{

		Comment comment = new Comment();
		
		comment.setId(id);
		comment.setApplication_id(application_id);
		comment.setUser_id(user_id);
		comment.setIspushed(ispushed);
		comment.setEdited_by(edited_by);
		comment.setCreated(created);
		comment.setUpdated(updated);
		comment.setVersion(version);
		comment.setContent(content);
	
		int isUpdated = commentDAOImpl.update(comment);
		
		if(isUpdated == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/deleteComment", produces="application/json")
	public @ResponseBody  String deleteComment(
			@RequestParam(value="id", required=false) String id) throws SQLException{

		Comment comment = new Comment();
		
		comment.setId(id);

		int isDeleted = commentDAOImpl.delete(comment);
		
		if(isDeleted == 1){
			return "1";
		}
		return "-1";
		
	}
	@RequestMapping(value="/selectComment", produces="application/json")
	public @ResponseBody  Collection selectComment(
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version) throws SQLException{

		Comment comment = new Comment();
		Collection s = null;
		comment.setId(id);
		comment.setId(id);
		comment.setApplication_id(application_id);
		comment.setUser_id(user_id);
		comment.setIspushed(ispushed);
		comment.setEdited_by(edited_by);
		comment.setCreated(created);
		comment.setUpdated(updated);
		comment.setVersion(version);
	
		s = commentDAOImpl.findComment(comment);

		return s;
		
	}
	@RequestMapping(value="/selectAllComment", produces="application/json")
	public @ResponseBody  Collection selectAllComment(HttpSession session) throws SQLException{
		System.out.println("user: "+session.getAttribute("username"));
		Collection s = commentDAOImpl.getUserComment(session.getAttribute("username").toString());
		

		return s;
		
	}
		
	
	@RequestMapping(value="/selectLatestComment", produces="application/json")
	public @ResponseBody  Collection selectLatestComment(
			@RequestParam(value="application_id",required=false) String application_id) throws SQLException{

		Comment comment = new Comment();
		comment.setApplication_id(application_id);
		Collection s = commentDAOImpl.findUserComment(comment);

		return s;
		
	}
	
}
