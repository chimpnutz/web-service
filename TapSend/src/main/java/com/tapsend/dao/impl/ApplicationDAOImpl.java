package com.tapsend.dao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.google.gson.Gson;
import com.tapsend.model.*;

public class ApplicationDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Application application){
		String sql = "INSERT INTO application SET("
				+ "application_id,"
				+ "sub_type,"
				+ "user_id,"
				+ "status,"
				+ "created,"
				+ "updated,"
				+ "ispushed,"
				+ "version) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		Object[] params = {
				application.getApplication_id(),
				application.getSub_type(),
				application.getUserid(),
				application.getStatus(),
				application.getCreated(),
				application.getUpdated(),
				application.getIspushed(),
				application.getVersion()
		};
		
		int[] types={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER,
				Types.INTEGER
		};
		
		System.out.println("SQL:"+sql);
		System.out.println("parameters:");
		for(Object s : params){
			System.out.print(s+",");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		System.out.println("Save status:"+isSaved);
		return isSaved;
	}
	
	public int update(Application application){
		String sql = "UPDATE application SET dvar = 1";
		int iterator=1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				sql+=", application_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSub_type().isEmpty()||!application.getSub_type().equals(null)||!application.getSub_type().equals("")){
			  sql+=", sub_type=?";
			  iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUserid().isEmpty()||!application.getUserid().equals(null)||!application.getUserid().equals("")){
				sql+=", userid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				sql+=", status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		sql+=" WHERE application_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = application.getApplication_id();
		iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				params[iterator]=application.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSub_type().isEmpty()||!application.getSub_type().equals(null)||!application.getSub_type().equals("")){
			  params[iterator]=application.getSub_type();
			  iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUserid().isEmpty()||!application.getUserid().equals(null)||!application.getUserid().equals("")){
				params[iterator]=application.getUserid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				params[iterator]=application.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				params[iterator]=application.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				params[iterator]=application.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				params[iterator]=application.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				params[iterator]=application.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println("SQL:"+sql);
		System.out.println("parameters:");
		for(Object s : params){
			System.out.print(s+",");
		}
		int isUpdated = jdbcTemplate.update(sql,params);
		System.out.println("Update Status: "+isUpdated);
		return isUpdated;
	}
	public int delete(Application application){
		String sql = "DELETE FROM application WHERE application_id=?";
		Object[] params = {application.getApplication_id()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	public int checkIfExists(Application application){
		String sql = "SELECT * FROM application WHERE application_id=?";
		Object[] params= {application.getApplication_id()};
		int isExisting = 0;
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
		//System.out.println(isExisting);
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection findApplication(Application application){

		int iterator=1;
		String sql = "SELECT * FROM application WHERE dvar=1 ";
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				sql+="AND title=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSub_type().isEmpty()||!application.getSub_type().equals(null)||!application.getSub_type().equals("")){
			  sql+="AND sub_type=? ";
			  iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUserid().isEmpty()||!application.getUserid().equals(null)||!application.getUserid().equals("")){
				sql+="AND userid=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				sql+="AND status=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				sql+="AND created=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				sql+="AND updated=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				sql+="AND ispushed=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				sql+="AND version=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		Object[] params = new Object[iterator];

		params[0] = "1";
		iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				params[iterator]=application.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getSub_type().isEmpty()||!application.getSub_type().equals(null)||!application.getSub_type().equals("")){
			  params[iterator]=application.getSub_type();
			  iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUserid().isEmpty()||!application.getUserid().equals(null)||!application.getUserid().equals("")){
				params[iterator]=application.getUserid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				params[iterator]=application.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				params[iterator]=application.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				params[iterator]=application.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				params[iterator]=application.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				params[iterator]=application.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println("SQL:"+sql);
		System.out.println("parameters:");
		for(Object x : params){
			System.out.print(x+",");
		}
		
		return jdbcTemplate.query(sql, params,new ApplicationMapper());
	}
	private static final class ApplicationMapper implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        application.setApplication_id(rs.getString("application_id"));
	        application.setSub_type(rs.getString("sub_type"));
	        application.setUserid(rs.getString("userid"));
	        application.setStatus(rs.getString("status"));
	        application.setCreated(rs.getString("created"));
	        application.setUpdated(rs.getString("updated"));
	        application.setIspushed(rs.getString("ispushed"));
	        application.setVersion(rs.getString("version"));
	        return application;
	    }
	}
	
	
	
	public Collection selectUserDetail(){
		return jdbcTemplate.query("SELECT * FROM phone", new UserMapper());
	}
	
	public Collection selectMyUsersDetail(String username){
		String sql = "SELECT details FROM user WHERE parent_userid = (select id from user where user_id = ?) ";
		String result = "-1";
		List<Map<String,Object>> rows  =  jdbcTemplate.queryForList(sql,username);
		//System.out.println(isExisting);

		
		ArrayList<UserDetails> userlist = new ArrayList<UserDetails>();
		
		for(Map row:rows)
		{
		
			UserDetails user = new UserDetails();
			
			
			result = (String)(row.get("details"));
			
			Gson gson = new Gson();
			
			user = gson.fromJson(result,UserDetails.class);
			
			result = gson.toJson(user);
				
			userlist.add(user);
		}
		
		return userlist;

	}
	
	public Collection selectUserDetail(String username){
		String sql = "SELECT details FROM user WHERE user_id = ?";
		String result = "-1";
		List<Map<String,Object>> rows  =  jdbcTemplate.queryForList(sql,username);
		//System.out.println(isExisting);

		
		ArrayList<UserDetails> userlist = new ArrayList<UserDetails>();
		
		for(Map row:rows)
		{
		
			UserDetails user = new UserDetails();
			
			
			result = (String)(row.get("details"));
			
			Gson gson = new Gson();
			
			user = gson.fromJson(result,UserDetails.class);
			
			result = gson.toJson(user);
				
			userlist.add(user);
		}
		
		return userlist;

	}

	private static final class UserMapper implements RowMapper<User>{
		
		public User mapRow(ResultSet rs, int rowNum) throws SQLException{
			
			
			
			User user = new User();
			
//			phone.setPhone_id(rs.getString("phone_id"));
//			phone.setAvailability(rs.getString("availability"));
//			phone.setPhone_model(rs.getString("phone_model"));
//			phone.setPhone_code(rs.getString("phone_code"));
//			phone.setPhone_mfr(rs.getString("phone_mfr"));
//			phone.setPhone_desc(rs.getString("phone_desc"));
//			phone.setIspushed(rs.getString("ispushed"));	
//			phone.setEdited_by(rs.getString("edited_by"));
//			phone.setVersion(rs.getString("version"));
//			phone.setCreated(rs.getString("created"));
//			phone.setUpdated(rs.getString("updated"));
//			phone.setImage_name(rs.getString("image_name"));

			

			return user;
		}
		
	}
}