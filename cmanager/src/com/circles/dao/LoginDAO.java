package com.circles.dao;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.circles.beans.UserBean;
import com.circles.dao.LoginDAO;
import com.circles.dao.UserDAO;

public class LoginDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(LoginDAO.class);
	
	public UserBean login(String username, String uPassword){


		UserDAO userDAO = new UserDAO();
		
		UserBean userBean = null;
		
		String uname;
		
		String sql = "SELECT userid FROM user WHERE userid = ?" ;
	
		try{
			
		uname = (String)getJdbcTemplate().queryForObject(
				sql, new Object[] {username}, String.class);
		
		userBean = userDAO.getUserBean(uname);
	
		MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
		mdEnc.update(uPassword.getBytes(), 0, uPassword.length());
		String password = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
		
		
		
		if ( !userBean.getUserName().equals(username) || !userBean.getPassword().equals(password)) {
			System.out.println(userBean.getUserName());
			System.out.println("should be equal to: "+username);
			System.out.println(userBean.getPassword());
			System.out.println("should be equal to: "+password);
			userBean = null;
		}
		

		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
		return userBean;
		
	}
}
