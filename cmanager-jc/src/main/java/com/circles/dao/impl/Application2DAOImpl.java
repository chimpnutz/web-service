package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.circles.model.Application2;
import com.circles.model.Phone;
import com.circles.model.Plan;

public class Application2DAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Application2 application){
		String sql = "INSERT INTO application("
				+ "application_id,"
				+ "application_type,"
				+ "company_id,"
				+ "user_id,"
				+ "created,"
				+ "updated,"
				+ "status,"
				+ "version,"
				+ "ispushed,"
				+ "details)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR
		};
		Object[] params = {
				application.getApplication_id(),
				application.getApplication_type(),
				application.getCompany_id(),
				application.getUser_id(),
				application.getCreated(),
				application.getUpdated(),
				application.getStatus(),
				application.getVersion(),
				application.getIspushed(),
				application.getDetails()
		};
		
		System.out.println("SQL: "+sql);
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.print(o+", ");
		}
		int isSaved = jdbcTemplate.update(sql,params,types);
		System.out.println("Save Status: "+isSaved);
		return isSaved;
	}
	public int update(Application2 application){
		String sql =  "UPDATE SET application dvar=? ";
		int iterator = 1;
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				sql+=", application_id=?";
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				sql+=", application_type=?";
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getCompany_id().isEmpty()||!application.getCompany_id().equals(null)||!application.getCompany_id().equals("")){
				sql+=", company_id=?";
				iterator++;
			}
		}
	
		catch(NullPointerException e){}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				sql+=", status=?";
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getUser_id().isEmpty()||!application.getUser_id().equals(null)||!application.getUser_id().equals("")){
				sql+=", user_id=?";
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getDetails().isEmpty()||!application.getDetails().equals(null)||!application.getDetails().equals("")){
				sql+=", details=?";
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		
		sql+="WHERE application_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = application.getApplication_id();
		iterator = 1;
		
		try{
			if(!application.getApplication_id().isEmpty()||!application.getApplication_id().equals(null)||!application.getApplication_id().equals("")){
				params[iterator] = application.getApplication_id();
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getApplication_type().isEmpty()||!application.getApplication_type().equals(null)||!application.getApplication_type().equals("")){
				params[iterator] = application.getApplication_type();
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getCompany_id().isEmpty()||!application.getCompany_id().equals(null)||!application.getCompany_id().equals("")){
				params[iterator] = application.getCompany_id();
				iterator++;
			}
		}
	
		catch(NullPointerException e){}
		try{
			if(!application.getStatus().isEmpty()||!application.getStatus().equals(null)||!application.getStatus().equals("")){
				params[iterator] = application.getStatus();
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getUser_id().isEmpty()||!application.getUser_id().equals(null)||!application.getUser_id().equals("")){
				params[iterator] = application.getUser_id();
				iterator++;
			}
		}
		catch(NullPointerException e){}
		try{
			if(!application.getVersion().isEmpty()||!application.getVersion().equals(null)||!application.getVersion().equals("")){
				params[iterator] = application.getVersion();
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getCreated().isEmpty()||!application.getCreated().equals(null)||!application.getCreated().equals("")){
				params[iterator] = application.getCreated();
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getUpdated().isEmpty()||!application.getUpdated().equals(null)||!application.getUpdated().equals("")){
				params[iterator] = application.getUpdated();
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getIspushed().isEmpty()||!application.getIspushed().equals(null)||!application.getIspushed().equals("")){
				params[iterator] = application.getIspushed();
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		try{
			if(!application.getDetails().isEmpty()||!application.getDetails().equals(null)||!application.getDetails().equals("")){
				params[iterator] = application.getDetails();
				iterator++;
			}
		}	
		catch(NullPointerException e){}
		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		int isUpdated = jdbcTemplate.update(sql, params);	
		return isUpdated;
	}
	private static final class Application2Mapper implements RowMapper<Application2> {

	    public Application2 mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Application2 application = new Application2();
	        
	    	application.setApplication_id(rs.getString("application_id"));
	    	application.setApplication_type(rs.getString("application_type"));
	    	application.setCompany_id(rs.getString("company_id"));
	    	application.setCreated(rs.getString("created"));
	    	application.setUser_id(rs.getString("user_id"));
	    	application.setUpdated(rs.getString("updated"));
	    	application.setStatus(rs.getString("status"));
	    	application.setVersion(rs.getString("version"));
	    	application.setIspushed(rs.getString("ispushed"));
	    	application.setDetails(rs.getString("details"));
	        return application;
	        
	    }
	}
}
