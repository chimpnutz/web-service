package com.tapsend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;




import com.google.gson.Gson;
import com.tapsend.model.*;


public class UserDAOImpl {
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public int save(User user){
		
		String sql = "INSERT INTO user ("
				+ "user_id,"
				+ "password,"
				+ "role,"
				+ "status,"
				+ "firstName,"
				+ "middleName,"
				+ "lastName,"
				+ "company_id,"
				+ "details,email,id,type,parent_userid,relationship) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR
		};
		Object[] params = {
				user.getUser_id(),
				user.getPassword(),
				user.getRole(),
				user.getStatus(),
				user.getFirstName(),
				user.getMiddleName(),
				user.getLastName(),
				user.getCompany_id(),
				user.getDetails(),
				user.getEmail(),
				user.getId(),
				user.getType(),
				user.getParentuser_id(),
				user.getRelation()
		};
		
		String updatesql = "update user set "
				+ "user_id = ?,"
				+ "password = ?,"
				+ "role = ?,"
				+ "status = ?,"
				+ "firstName= ?,"
				+ "middleName= ?,"
				+ "lastName= ?,"
				+ "company_id = ?,"
				+ "details= ?,email= ?,type= ?, parent_userid =?  where id = ? ";
		
		int[] updatetypes ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR
		};
		Object[] updateparams = {
				user.getUser_id(),
				user.getPassword(),
				user.getRole(),
				user.getStatus(),
				user.getFirstName(),
				user.getMiddleName(),
				user.getLastName(),
				user.getCompany_id(),
				user.getDetails(),
				user.getEmail(),
				user.getType(),
				user.getParentuser_id(),
				user.getId()
				
		};

		
		//System.out.println("SQL: "+sql);
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.println(o+", ");
		}
		
		System.out.println("type: "+user.getType());
		
		
		if(user.getType().equalsIgnoreCase("4") || user.getType().equalsIgnoreCase("3") )
		{
			
			
		String checkexistingsql = "SELECT * FROM user WHERE  id = ?";
			 
		System.out.println("checkexistingsql "+checkexistingsql);
			
			SqlRowSet rs  =  jdbcTemplate.queryForRowSet(checkexistingsql,user.getId());
			
			if(rs.next()){
				try{
					System.out.println("updatesql: "+updatesql);
					int isSaved = jdbcTemplate.update(updatesql,updateparams,updatetypes);
					System.out.println("Update Status: "+isSaved);
					return isSaved;
				}catch(Exception a){
					System.out.println(a.getMessage());
				}
				
			}else{
				try{
					System.out.println("sql: "+sql);
					int isSaved = jdbcTemplate.update(sql,params,types);
					System.out.println("Save Status: "+isSaved);
					return isSaved;
				}catch(Exception a){
					System.out.println(a.getMessage());
				}
				
			}
			
//			int isSaved = jdbcTemplate.update(sql,params,types);
//			System.out.println("Save Status: "+isSaved);
//			return isSaved;
			
		}
		
		else
		
		{
			String checkexistingsql = "SELECT * FROM user WHERE  email = ?";
			
			
			
			SqlRowSet rs  =  jdbcTemplate.queryForRowSet(checkexistingsql,user.getEmail());
			
			if(rs.next()){
				int isSaved = jdbcTemplate.update(updatesql,updateparams,updatetypes);
				System.out.println("Update Status: "+isSaved);
				return isSaved;
			}else{
				int isSaved = jdbcTemplate.update(sql,params,types);
				System.out.println("Save Status: "+isSaved);
				return isSaved;
			}
			
		}
		return 0;
	
		

	
	}

	public int update(User user){
		String sql="UPDATE user SET dvar=?";
		int iterator=1;
		try{
			if(!user.getUser_id().isEmpty()||!user.getUser_id().equals(null)||!user.getUser_id().equals("")){
				sql+=", user_id=?";
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
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				sql+=", role=?";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){
				sql+=", status=?";
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
				sql+=", middleName=?";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getCompany_id().isEmpty()||!user.getCompany_id().equals(null)||!user.getCompany_id().equals("")){
				sql+=", company_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getDetails().isEmpty()||!user.getDetails().equals(null)||!user.getDetails().equals("")){
				sql+=", details=?";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getEmail().isEmpty()||!user.getEmail().equals(null)||!user.getEmail().equals("")){
				sql+=", email=?";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		sql+="WHERE user_id=?";
		Object[] params=new Object[iterator+1];
		params[0] = "1";
		params[iterator] = user.getUser_id();
		iterator = 1;
		
		try{
			if(!user.getUser_id().isEmpty()||!user.getUser_id().equals(null)||!user.getUser_id().equals("")){
				params[iterator]=user.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getPassword().isEmpty()||!user.getPassword().equals(null)||!user.getPassword().equals("")){
				params[iterator]=user.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				params[iterator]=user.getRole();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){
				params[iterator]=user.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				params[iterator]=user.getFirstName();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				params[iterator]=user.getMiddleName();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				params[iterator]=user.getLastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getCompany_id().isEmpty()||!user.getCompany_id().equals(null)||!user.getCompany_id().equals("")){
				params[iterator]=user.getCompany_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getDetails().isEmpty()||!user.getDetails().equals(null)||!user.getDetails().equals("")){
				params[iterator]=user.getDetails();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getEmail().isEmpty()||!user.getEmail().equals(null)||!user.getEmail().equals("")){
				params[iterator]=user.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		System.out.println("SQL: "+sql);
		System.out.println("params: ");
		for(Object o:params){
			System.out.println(o+", ");
		}
		int isUpdated=jdbcTemplate.update(sql,params);
		System.out.println(isUpdated);
		return isUpdated;
	}
	public int delete(User user){
		String sql = "DELETE FROM user WHERE user_id=?";
		Object[] params = {user.getUser_id()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection findUser(User user){
		Collection s=null;
		String sql = "SELECT * FROM user WHERE dvar=? ";
		int iterator=1;

		try{
			if(!user.getUser_id().isEmpty()||!user.getUser_id().equals(null)||!user.getUser_id().equals("")){
				sql+=" AND user_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getPassword().isEmpty()||!user.getPassword().equals(null)||!user.getPassword().equals("")){

				sql+=" AND password=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
	
				sql+=" AND role=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){

				sql+=" AND status=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
	
				sql+=" AND firstName=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
	
				sql+=" AND middleName=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
	
				sql+=" AND middleName=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getCompany_id().isEmpty()||!user.getCompany_id().equals(null)||!user.getCompany_id().equals("")){
			
				sql+=" AND company_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getDetails().isEmpty()||!user.getDetails().equals(null)||!user.getDetails().equals("")){
			
				sql+=" AND details=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getEmail().isEmpty()||!user.getEmail().equals(null)||!user.getEmail().equals("")){
		;
				sql+=" AND email=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getType().isEmpty()||!user.getType().equals(null)||!user.getType().equals("")){
				
				sql+=" AND type=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getId().isEmpty()||!user.getId().equals(null)||!user.getId().equals("")){
			
				sql+=" AND id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getRelation().isEmpty()||!user.getRelation().equals(null)||!user.getRelation().equals("")){
			
				sql+=" AND relationship=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		Object[] params=new Object[iterator];
		params[0]=1;
		iterator = 1;
		
		try{
			if(!user.getUser_id().isEmpty()||!user.getUser_id().equals(null)||!user.getUser_id().equals("")){
				System.out.println("userid: "+user.getUser_id());
				System.out.println("password: "+user.getPassword());

				params[iterator]=user.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getPassword().isEmpty()||!user.getPassword().equals(null)||!user.getPassword().equals("")){
				params[iterator]=user.getPassword();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getRole().isEmpty()||!user.getRole().equals(null)||!user.getRole().equals("")){
				params[iterator]=user.getRole();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getStatus().isEmpty()||!user.getStatus().equals(null)||!user.getStatus().equals("")){
				params[iterator]=user.getStatus();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getFirstName().isEmpty()||!user.getFirstName().equals(null)||!user.getFirstName().equals("")){
				params[iterator]=user.getFirstName();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getMiddleName().isEmpty()||!user.getMiddleName().equals(null)||!user.getMiddleName().equals("")){
				params[iterator]=user.getMiddleName();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getLastName().isEmpty()||!user.getLastName().equals(null)||!user.getLastName().equals("")){
				params[iterator]=user.getLastName();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!user.getCompany_id().isEmpty()||!user.getCompany_id().equals(null)||!user.getCompany_id().equals("")){
				params[iterator]=user.getCompany_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getDetails().isEmpty()||!user.getDetails().equals(null)||!user.getDetails().equals("")){
				params[iterator]=user.getDetails();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getEmail().isEmpty()||!user.getEmail().equals(null)||!user.getEmail().equals("")){
				params[iterator] = user.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getType().isEmpty()||!user.getType().equals(null)||!user.getType().equals("")){
				params[iterator] = user.getType();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getId().isEmpty()||!user.getId().equals(null)||!user.getId().equals("")){
				params[iterator] = user.getId();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!user.getRelation().isEmpty()||!user.getRelation().equals(null)||!user.getRelation().equals("")){
				params[iterator] = user.getRelation();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		System.out.println("SQL: "+sql);
		System.out.println("params: ");
		
		for(Object o:params){
			System.out.println(o+", ");
		}
		
		s = jdbcTemplate.query(sql,params, new UserMapper());
		return s;
	}
	
	public int checkIfExists(User user){
		String sql = "SELECT * FROM user WHERE user_id=? AND password=?";
		Object[] params= {user.getUser_id(),user.getPassword()};
		int isExisting = 0;
		System.out.println(user.getUser_id()+" "+user.getPassword());
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR,Types.VARCHAR});
		
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	public int checkIfEmailExists(User user){
		String sql = "SELECT * FROM user WHERE email=?";
		Object[] params= {user.getEmail()};
		int isExisting = 0;
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
		
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	
	private static final class UserMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum)throws SQLException{
			User user = new User();
			user.setUser_id(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setStatus(rs.getString("status"));
			user.setFirstName(rs.getString("firstName"));
			user.setMiddleName(rs.getString("middleName"));
			user.setLastName(rs.getString("lastName"));
			user.setCompany_id(rs.getString("company_id"));
			user.setDetails(rs.getString("details"));
			user.setType(rs.getString("type"));
			user.setId(rs.getString("id"));
			user.setEmail(rs.getString("email"));
			user.setParentuser_id(rs.getString("parent_userid"));
			user.setRelation(rs.getString("relationship"));
			
			System.out.println("Type: "+user.getType());
			System.out.println("parent: "+rs.getString("parent_userid"));
			
			
			try{
				if(Integer.parseInt(user.getType())==4 || Integer.parseInt(user.getType())==3){
					Gson gson2 = new Gson();
					System.out.println("Details: "+user.getDetails());
					UserDetails detail = gson2.fromJson(user.getDetails(), UserDetails.class);
					user.setSuffix(detail.getSuffix());
					user.setAddRegion(detail.getAddRegion());
					user.setAddBrgy(detail.getAddBrgy());
					user.setAddCity(detail.getAddCity());
					user.setAdd_line1(detail.getAdd_line1());
					user.setAdd_line2(detail.getAdd_line2());
					
					System.out.println("region: "+user.getAddRegion());
					System.out.println("city: "+user.getAddCity());
					System.out.println("brgy: "+user.getAddBrgy());
					System.out.println("line1: "+user.getAdd_line1());
					System.out.println("line2: "+user.getAdd_line2());
					System.out.println("Suffix: "+user.getSuffix());
				}
			}catch(NumberFormatException ex){ // handle your exception
				  
				}
			
			
			return user;
		}
	}
	
	
	public Collection getEmployer(User user){
		System.out.println("parent_userid before: "+user.getParentuser_id());
		System.out.println("id "+user.getId());
		System.out.println("type before: "+ user.getType());
		
		String test = "";
		int type = 0;
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select parent_userid, type, details from user where id = ?");
		
		 SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString(), new Object[] {user.getId()});	
		
		 if(rs.next()){
			 
			 test = rs.getString("parent_userid");
			 type = rs.getInt("type");
			
			 
			 System.out.println("parent_userid "+test);
			 System.out.println("type "+type);
			
			 
			 if(type == 3){
			 
			 StringBuilder str = new StringBuilder();
			 
			 str.append("select * from user where id = ? and parent_userid IS NULL ");
			 
			 return jdbcTemplate.query(str.toString(), new Object[] {test},new UserMapper());
			 } 
			 else if(type == 4){
				
				 StringBuilder str = new StringBuilder();
				 
				 str.append("select * from user where id = ? and parent_userid = ? ");
				 
				 return jdbcTemplate.query(str.toString(), new Object[] {user.getId(),test},new UserMapper());
			} 
		 }
		return null;
		
	}
	
	public Collection getBeneficiary(User user){
		System.out.println("ID of Beneficiary: "+ user.getId());
		return jdbcTemplate.query("SELECT * FROM user WHERE type = ? AND parent_userid = ?",new Object[]{4,user.getId()},new UserMapper());
	}
	
	public ArrayList<User> ViewUsersDetail(String id){
		System.out.println(id);
		String sql = "SELECT details FROM user WHERE  id = ?";
		String result = "-1";
		List<Map<String,Object>> rows  =  jdbcTemplate.queryForList(sql,id);
		//System.out.println(isExisting);

		
		ArrayList<User> userlist = new ArrayList<User>();
		
		for(Map row:rows)
		{
		
			User user = new User();
			
			
			result = (String)(row.get("details"));
			user.setDetails(result);
			
			System.out.println("details: "+result);
			
//			Gson gson = new Gson();
//			
//			user = gson.fromJson(result,UserDetails.class);
//			
//			result = gson.toJson(user);
				
			userlist.add(user);
		}
		
		return userlist;

	}
	public Collection getallUser(User user){
	
			StringBuilder str = new StringBuilder();
			
//			str.append("SELECT a.id,a.parent_userid,b.firstName, b.middleName, b.lastName , ");
//			str.append("a.type, b.role, a.user_id, b.user_id, ");
//			str.append("a.firstName, a.lastName, a.middleName, a.email FROM USER a ");
//			str.append("LEFT JOIN USER b ON a.parent_userid = b.id ");
			
			str.append("SELECT * FROM USER");
			
			String parentid = "";
			String type = "";
	
			 SqlRowSet rs = jdbcTemplate.queryForRowSet(str.toString(),new Object[] {});
			 
			 if(rs.next());
			 
			 parentid = rs.getString("parent_userid");
			 type = rs.getString("type");
			 
			 StringBuilder strs = new StringBuilder();
			 
			 strs.append("SELECT a.* FROM user a LEFT JOIN user b ON a.parent_userid = b.id  ");
			 
			 return jdbcTemplate.query(strs.toString(), new Object[] {}, new UserMapper());
			
		}

	public ArrayList<User> getallUser2(){
		
	
		ArrayList<User> userList = new ArrayList<User>();
		 
		 StringBuilder strs = new StringBuilder();
		 
		 strs.append("SELECT a.id,a.parent_userid,b.firstName as bfname, b.middleName as bmname, b.lastName as blname, ");
		 strs.append("a.type, b.role, a.user_id, b.user_id as buserid, a.role, a.type, ");
		 strs.append("a.firstName, a.lastName, a.middleName,a.email  FROM user a LEFT JOIN user b ON a.parent_userid = b.id  ");
		 
		 SqlRowSet rs = jdbcTemplate.queryForRowSet(strs.toString());
		 
		 
		 
		 while(rs.next())
		 {
			 User user = new User();
				

				user.setFirstName(rs.getString("firstName"));
				user.setMiddleName(rs.getString("middleName"));
				user.setLastName(rs.getString("lastName"));
				user.setBfirstname(rs.getString("bfname"));
				user.setBmiddlename(rs.getString("bmname"));
				user.setBlastname(rs.getString("blname"));
				user.setBuserid(rs.getString("buserid"));
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setParentuser_id(rs.getString("parent_userid"));
				user.setType(rs.getString("type"));
				user.setRole(rs.getString("role"));
				
				
				
			 System.out.println("a "+rs.getString("id"));
			 System.out.println("a "+rs.getString("parent_userid"));
			 System.out.println("a "+rs.getString("firstName"));
			 System.out.println("a "+rs.getString("middleName"));
			 System.out.println("a "+rs.getString("lastName"));
			 System.out.println("a "+rs.getString("type"));
			 System.out.println("a "+rs.getString("role"));
			 System.out.println("a "+rs.getString("user_id"));
			 System.out.println("a "+rs.getString("user_id"));
			 System.out.println("a "+rs.getString("firstName"));
			 System.out.println("a "+rs.getString("lastName"));
			 System.out.println("a "+rs.getString("middleName"));
			 System.out.println("a "+rs.getString("email"));
			 
			 userList.add(user);
		 }
		 
		
		 
		 
		 return userList;
		
	}
	 
	
		
}
