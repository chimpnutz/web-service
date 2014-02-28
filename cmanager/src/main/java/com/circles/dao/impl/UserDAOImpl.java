package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;




import com.circles.model.User;


public class UserDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(User user){
		String sql = "INSERT INTO user ("
				+ "userid,"
				+ "password,"
				+ "email,"
				+ "firstName,"
				+ "middleName,"
				+ "lastName,"
				+ "role,"
				+ "datecreated"
				+ ") VALUES(?,?,?,?,?,?,?,?)";
		Object[] params = {
				user.getUserid(),
				user.getPassword(),
				user.getEmail(),
				user.getFirstName(),
				user.getMiddleName(),
				user.getLastName(),
				user.getRole(),
				user.getDatecreated()
		};
		/*for(int i = 0;i<params.length;i++){
			System.out.println(params[i]);
		}*/
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.BIGINT
				};
		
		int isSaved = jdbcTemplate.update(sql, params, types);	
		return isSaved;
	}
	public int delete(User user){
		String sql = "DELETE FROM user WHERE userid=?";
		Object[] params = {user.getUserid()};
		int isDeleted = jdbcTemplate.update(sql, params);	
		return isDeleted;
	}
	public int update(User user, String oldUserid){
		String sql = "UPDATE user SET dvar=?";
		int iterator = 1;
		try{
			if(!user.getUserid().isEmpty()||!user.getUserid().equals(null)||!user.getUserid().equals("")){
				sql+=", userid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getPassword().isEmpty()||!user.getPassword().equals(null)||!user.getPassword().equals("")){
				sql+=", password=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				sql+=", firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				sql+=", middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				sql+=", lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				sql+=", role=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		//StringUtils.removeEnd(sql, ",");
		//sql.substring(1,sql.length()-2);
		
		sql+= " WHERE userid=?";
		System.out.println(sql);
		Object[] params2 = new Object[iterator+1];
		params2[0] = "1";
		params2[iterator] = oldUserid;
		int NewIterator = 1;
		try{
			if(!user.getUserid().isEmpty()||!user.getUserid().equals(null)||!user.getUserid().equals("")){
				params2[NewIterator] = user.getUserid();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getPassword().isEmpty()||!user.getPassword().equals(null)||!user.getPassword().equals("")){
				params2[NewIterator] = user.getPassword();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				params2[NewIterator] = user.getFirstName();
				NewIterator++;
			}
		}catch(NullPointerException e){		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				params2[NewIterator] = user.getMiddleName();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				params2[NewIterator] = user.getLastName();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				params2[NewIterator] = user.getRole();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		for(int i = 0;i>params2.length;i++){
			System.out.println(params2[i]);
		}
		int isUpdated = jdbcTemplate.update(sql, params2);		
		return isUpdated;
	}

	@SuppressWarnings("deprecation")
	public int checkIfExists(User user){
		String sql="SELECT count(*) FROM user WHERE userid=? and password=?";
		//Object[] params ={user.getUserid(),user.getPassword()};
		int isExisting =jdbcTemplate.queryForInt(sql,new Object[] {user.getUserid(),user.getPassword()},new int[] {Types.VARCHAR,Types.VARCHAR});
		return isExisting;
	}
	public Collection findUser(User user){
		String sql="SELECT * FROM user WHERE 1=? ";
		int iterator = 1;
		
		//return (User)jdbcTemplate.queryForObject(sql,new Object[]{user.getUserid()}, new UserMapper());
		//System.out.println(userId);
		try{
			if(!user.getUserid().isEmpty()||!user.getUserid().equals(null)||!user.getUserid().equals("")){
				sql+=" AND userid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				sql+=" AND firstName=?";
				iterator++;
			}
		}catch(NullPointerException e){		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				sql+=" AND middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				sql+=" AND lastName=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				sql+=" AND role=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getDatecreated().isEmpty()||!user.getDatecreated().equals(null)||!user.getDatecreated().equals("")){
				sql+=" AND datecreated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){
				sql+=" AND status=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println(iterator);
		Object[] params2 = new Object[iterator];
		int NewIterator =1;
		params2[0] ="1";
		try{
			if(!user.getUserid().isEmpty()||!user.getUserid().equals(null)||!user.getUserid().equals("")){
				params2[NewIterator] = user.getUserid();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				params2[NewIterator] = user.getFirstName();
				NewIterator++;
			}
		}catch(NullPointerException e){		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				params2[NewIterator] = user.getMiddleName();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				params2[NewIterator] = user.getLastName();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				params2[NewIterator] = user.getRole();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getDatecreated().isEmpty()||!user.getDatecreated().equals(null)||!user.getDatecreated().equals("")){
				params2[NewIterator] = user.getDatecreated();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){
				params2[NewIterator] = user.getStatus();
				NewIterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println(sql);
		Collection users = jdbcTemplate.query(sql,params2, new UserMapper());
		return users;
			
	}
	
	public Collection findAllUser(){
		return jdbcTemplate.query("SELECT * FROM user", new UserMapper());
	}
	
	public Collection selectAllUser(){
		return jdbcTemplate.query("SELECT * FROM user WHERE role!=0 AND role!=3", new UserMapper());
	}
	
	private static final class UserMapper implements RowMapper<User> {

	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        User user = new User();
	        
	        user.setUserid(rs.getString("userid"));
	        user.setPassword(rs.getString("password"));
	        user.setEmail(rs.getString("email"));
	        user.setFirstName(rs.getString("firstName"));
	        user.setMiddleName(rs.getString("middleName"));
	        user.setLastName(rs.getString("lastName"));
	        user.setRole(rs.getString("role"));
	        user.setDatecreated(rs.getString("datecreated"));
	        user.setStatus(rs.getString("status"));
	        return user;
	    }
	}
}