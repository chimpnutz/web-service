package com.circles.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.User;

public class AgentDAOImpl {

private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveAgent(User user){
		
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO user(id,username,email,password,firstName,lastName,role,created,status) ");
		str.append("VALUES(?,?,?,?,?,?,?,?,?) ");
		
		int iSave = jdbcTemplate.update(str.toString(),new Object[]{
			user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),
			user.getFirstName(),user.getLastName(),user.getRole(),user.getDatecreated(),
			user.getStatus()
		});
		
		return iSave;
	}
	
	public int checkUser(User user){
		
		StringBuilder str = new StringBuilder();
		str.append("SELECT username from user where username = ? ");
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(str.toString(),new Object[]{user.getUsername()});
		
		if(rs.next()){
			System.out.println("Error, username is already exist!");
			return 1;		
		}
		else{
			System.out.println("username is available!");
			return 0;
		}
				
	}
}
