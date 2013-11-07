package com.elp.dao.transactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.elp.beans.UserBean;
import com.elp.model.NetworkManagementModel;
import com.elp.model.PermissionModel;



public class LoginDAO  extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(LoginDAO.class);
	
	public UserBean login(String username, String uPassword){
		
		
		UserDAO userDAO = new UserDAO();
		
		UserBean userBean = null;
		
		String uname;
		
		char [] password;
		char [] UserPassword = uPassword.toCharArray();
		
		String sql = "SELECT username FROM user WHERE username = ?" ;
	
		try{
			
		uname = (String)getJdbcTemplate().queryForObject(
				sql, new Object[] {username}, String.class);
		
		userBean = userDAO.getUserBean(uname);
				
		password = userBean.getPassword().toCharArray();
		
	    if (UserPassword.length != password.length) {
            userBean = null;
	     } else if(!Arrays.equals (UserPassword, password)) {
             userBean = null;
         }

		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
		return userBean;
		

		
	}
	
	
	public List<PermissionModel>  getmodulePermission(HttpSession session){
		
		   ArrayList<PermissionModel> moduleList= new ArrayList<PermissionModel>();
		
		   StringBuilder strSQL = new StringBuilder();
			 
		   String permission = null;
		   
		   strSQL.append("SELECT roleid,levelid,moduleid ,privilege_mode from role_privileges where roleid = ? and levelid = ?");
			
		   moduleList.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),
					session.getAttribute("roleid"),session.getAttribute("levelid"));
			
			for (Map row : rows) {
				
				PermissionModel module = new PermissionModel();
				
				module.setRoleid(row.get("roleid")+"");
				module.setLevelid(row.get("levelid")+"");
				module.setModuleid(row.get("moduleid")+"");
				module.setPriv(row.get("privilege_mode")+"");
				
			
				moduleList.add(module);
				
			}
		
		return moduleList;
	}
	

	
}
