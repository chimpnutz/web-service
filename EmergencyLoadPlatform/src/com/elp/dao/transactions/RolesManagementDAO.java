package com.elp.dao.transactions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.elp.beans.UserBean;
import com.elp.model.CreditExtendedModel;
import com.elp.model.CreditHistoryModel;
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.RoleManagementModel;
import com.elp.model.UserManagementModel;


public class RolesManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(RolesManagementDAO.class);
	
	public List<RoleManagementModel>  getRoles()
	{
		
		  ArrayList<RoleManagementModel> roles = new ArrayList<RoleManagementModel>();
		  
		  String getSql = "Select roleid,rolename from roles where active = 'Y' ";
		  
		  roles.clear();
		  
		  List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getSql.toString());
		
			for (Map row : rows) {

				RoleManagementModel role = new RoleManagementModel();
				
				role.setRoleid((Integer)row.get("roleid"));
				role.setRolename((String)row.get("rolename"));

				roles.add(role);			
			}
			
			return roles;
		
	}
	
	public boolean addnewrole(String rolename){
		
		   
		   String strSQL = "insert into roles (rolename) values (?)";
		   
		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL, new Object[] { 
						rolename
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		return false;

		
	}
	
	   public boolean deleterole(int id){

		   StringBuilder strSQL = new StringBuilder();
		      
		   strSQL.append("update roles set active = ?   "); 
		   strSQL.append("where  roleid = ? " );

		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					"N",id
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		   
		   
		return false;
		
		
	}
	
	
	
	
	
	

}
