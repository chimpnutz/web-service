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
import com.elp.model.PrivilegesManagementModel;
import com.elp.model.RoleManagementModel;


public class PrivilegesManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(PrivilegesManagementDAO.class);
	
	public List<PrivilegesManagementModel>  getPrivileges()
	{
		
		  ArrayList<PrivilegesManagementModel> privilegelist = new ArrayList<PrivilegesManagementModel>();
		  
		  String getSql = "Select privilegeid,privilege from privileges where active = 'Y' ";
		  
		  privilegelist.clear();
		  
		  List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getSql.toString());
		
			for (Map row : rows) {

				PrivilegesManagementModel privilege = new PrivilegesManagementModel();
				
				privilege.setPrivilegeid((Integer)(row.get("privilegeid")));
				privilege.setPrivilege((String)(row.get("privilege")));

				privilegelist.add(privilege);			
			}
			
			return privilegelist;
		
	}
	
	public boolean addnewpriv(String priv){
		
		   
		   String strSQL = "insert into privileges (privilege) values (?)";
		   
		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL, new Object[] { 
						priv
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
	
	   public boolean deletepriv(int id){

		   StringBuilder strSQL = new StringBuilder();
		      
		   strSQL.append("update privileges set active = ?   "); 
		   strSQL.append("where  privilegeid = ? " );

		   
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
