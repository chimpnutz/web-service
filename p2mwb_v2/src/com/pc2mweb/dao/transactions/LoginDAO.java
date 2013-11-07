package com.pc2mweb.dao.transactions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import com.pc2mweb.beans.UserBean;
import com.pc2mweb.controller.MainController;


public class LoginDAO  extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(LoginDAO.class);
	
public UserBean login(String username, String uPassword){
		
		
		UserDAO userDAO = new UserDAO();
		
		UserBean userBean = null;
		
		String uname;
		
		String sql = "SELECT username FROM agents WHERE username = ?" ;
	
		try{
			
		uname = (String)getJdbcTemplate().queryForObject(
				sql, new Object[] {username}, String.class);
		
		userBean = userDAO.getUserBean(uname);
	
		MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
		mdEnc.update(uPassword.getBytes(), 0, uPassword.length());
		String agentpwd = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
		

		
		if ( !userBean.getUserName().equals(username) || !userBean.getPassword().equals(agentpwd)) {
			
			userBean = null;
		}
		

		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
		return userBean;
		

		
	}
	
}
