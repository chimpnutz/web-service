package com.circles.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;



import com.circles.model.Gcm;
import com.circles.model.User;

public class GcmDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Gcm gcm){
		String sql = "INSERT INTO gcm("
				+ "id,"
				+ "username,"
				+ "regid,"
				+ "imei,"
				+ "created,"
				+ "activation,"
				+ "email,"
				+ "phone,"
				+ "mac)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
				Object[] params = {
					gcm.getId(),
					gcm.getUsername(),
					gcm.getRegid(),
					gcm.getImei(),
					gcm.getCreated(),
					gcm.getActivation(),
					gcm.getEmail(),
					gcm.getPhone(),
					gcm.getMac()
				};
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.BIGINT,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,

		};
	
		
		int isSaved = jdbcTemplate.update(sql, params, types);	
		return isSaved;
	}
	public int update(Gcm gcm){
		String sql = "UPDATE gcm SET dvar=?";
		int iterator = 1;
		try{
			if(!gcm.getId().isEmpty()||!gcm.getId().equals(null)||!gcm.getId().equals("")){
				sql+=", id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getUsername().isEmpty()||!gcm.getUsername().equals(null)||!gcm.getUsername().equals("")){
				sql+=", username=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getRegid().isEmpty()||!gcm.getRegid().equals(null)||!gcm.getRegid().equals("")){
				sql+=", regid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getImei().isEmpty()||!gcm.getImei().equals(null)||!gcm.getImei().equals("")){
				sql+=", imei=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!gcm.getCreated().isEmpty()||!gcm.getCreated().equals(null)||!gcm.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getEmail().isEmpty()||!gcm.getEmail().equals(null)||!gcm.getEmail().equals("")){
				sql+=", email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getActivation().isEmpty()||!gcm.getActivation().equals(null)||!gcm.getActivation().equals("")){
				sql+=", activation=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getPhone().isEmpty()||!gcm.getPhone().equals(null)||!gcm.getPhone().equals("")){
				sql+=", phone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getMac().isEmpty()||!gcm.getMac().equals(null)||!gcm.getMac().equals("")){
				sql+=", mac=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		sql+=" WHERE imei=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = gcm.getImei();
		iterator = 1;
		
		try{
			if(!gcm.getId().isEmpty()||!gcm.getId().equals(null)||!gcm.getId().equals("")){
				params[iterator] = gcm.getId();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getUsername().isEmpty()||!gcm.getUsername().equals(null)||!gcm.getUsername().equals("")){
				params[iterator] = gcm.getUsername();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getRegid().isEmpty()||!gcm.getRegid().equals(null)||!gcm.getRegid().equals("")){
				params[iterator] = gcm.getRegid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getImei().isEmpty()||!gcm.getImei().equals(null)||!gcm.getImei().equals("")){
				params[iterator] = gcm.getImei();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!gcm.getCreated().isEmpty()||!gcm.getCreated().equals(null)||!gcm.getCreated().equals("")){
				params[iterator] = gcm.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getEmail().isEmpty()||!gcm.getEmail().equals(null)||!gcm.getEmail().equals("")){
				params[iterator] = gcm.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getActivation().isEmpty()||!gcm.getActivation().equals(null)||!gcm.getActivation().equals("")){
				params[iterator] = gcm.getActivation();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getPhone().isEmpty()||!gcm.getPhone().equals(null)||!gcm.getPhone().equals("")){
				params[iterator] = gcm.getPhone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getMac().isEmpty()||!gcm.getMac().equals(null)||!gcm.getMac().equals("")){
				params[iterator] = gcm.getMac();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		int isUpdated = jdbcTemplate.update(sql, params);	
		return isUpdated;
	}
	public int update2(Gcm gcm){
		String sql = "UPDATE gcm SET regid=?, imei =  ?, phone = ?, mac = ? where username = ?";
	
		int isUpdated = jdbcTemplate.update(sql, gcm.getRegid(),gcm.getImei(),gcm.getPhone(),gcm.getMac(),gcm.getUsername());	
		
		return isUpdated;
	}
	public int delete(Gcm gcm){
		String sql = "DELETE FROM gcm WHERE id=?";
		Object[] params = {gcm.getId()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	
	@SuppressWarnings("deprecation")
	public int checkIfExists(Gcm gcm){
		String sql="SELECT * FROM gcm where imei=?";
		//Object[] params ={user.getUserid(),user.getPassword()};
		//System.out.println(gcm.getId());
		int isExisting = 0;
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {gcm.getImei()});
		//System.out.println(isExisting);
		if(rs.next()){


			return 1;
		}
		return isExisting;
	}
	@SuppressWarnings("deprecation")
	public int checkActivationIfExists(Gcm gcm){
		String sql="SELECT * FROM gcm where username=? and activation=?";
		//Object[] params ={user.getUserid(),user.getPassword()};
		//System.out.println(gcm.getId());
		int isExisting = 0;
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {gcm.getUsername(),gcm.getActivation()});
		//System.out.println(isExisting);
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	@SuppressWarnings("unchecked")
	public Collection findAllGcm(){
		return jdbcTemplate.query("SELECT * FROM gcm", new GcmMapper());
	}
	

	
	public Collection selectJoinGcms() throws SQLException{
		String sql = "SELECT * FROM gcm INNER JOIN user ON gcm.user_id = user.username";

		return jdbcTemplate.query(sql,new GcmMapper());
	
	}
	
	public Collection findGcm(Gcm gcm) throws SQLException{
		String sql = "SELECT * FROM gcm WHERE dvar=? ";
		int iterator = 1;
		try{
			if(!gcm.getId().isEmpty()||!gcm.getId().equals(null)||!gcm.getId().equals("")){
				sql+=" AND id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getUsername().isEmpty()||!gcm.getUsername().equals(null)||!gcm.getUsername().equals("")){
				sql+=" AND username=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getRegid().isEmpty()||!gcm.getRegid().equals(null)||!gcm.getRegid().equals("")){
				sql+=" AND regid=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getImei().isEmpty()||!gcm.getImei().equals(null)||!gcm.getImei().equals("")){
				sql+=" AND imei=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!gcm.getCreated().isEmpty()||!gcm.getCreated().equals(null)||!gcm.getCreated().equals("")){
				sql+=" AND created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getEmail().isEmpty()||!gcm.getEmail().equals(null)||!gcm.getEmail().equals("")){
				sql+=" AND email=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getActivation().isEmpty()||!gcm.getActivation().equals(null)||!gcm.getActivation().equals("")){
				sql+=" AND activation=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getPhone().isEmpty()||!gcm.getPhone().equals(null)||!gcm.getPhone().equals("")){
				sql+=" AND phone=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getMac().isEmpty()||!gcm.getMac().equals(null)||!gcm.getMac().equals("")){
				sql+=" AND mac=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		Object[] params = new Object[iterator];
		params[0] = "1";
		iterator = 1;
		
		try{
			if(!gcm.getId().isEmpty()||!gcm.getId().equals(null)||!gcm.getId().equals("")){
				params[iterator] = gcm.getId();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getUsername().isEmpty()||!gcm.getUsername().equals(null)||!gcm.getUsername().equals("")){
				params[iterator] = gcm.getUsername();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getRegid().isEmpty()||!gcm.getRegid().equals(null)||!gcm.getRegid().equals("")){
				params[iterator] = gcm.getRegid();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getImei().isEmpty()||!gcm.getImei().equals(null)||!gcm.getImei().equals("")){
				params[iterator] = gcm.getImei();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!gcm.getCreated().isEmpty()||!gcm.getCreated().equals(null)||!gcm.getCreated().equals("")){
				params[iterator] = gcm.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getEmail().isEmpty()||!gcm.getEmail().equals(null)||!gcm.getEmail().equals("")){
				params[iterator] = gcm.getEmail();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getActivation().isEmpty()||!gcm.getActivation().equals(null)||!gcm.getActivation().equals("")){
				params[iterator] = gcm.getActivation();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getPhone().isEmpty()||!gcm.getPhone().equals(null)||!gcm.getPhone().equals("")){
				params[iterator] = gcm.getPhone();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!gcm.getMac().isEmpty()||!gcm.getMac().equals(null)||!gcm.getMac().equals("")){
				params[iterator] = gcm.getMac();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		

		return jdbcTemplate.query(sql, params,new GcmMapper());
	
	}
	

	
	@SuppressWarnings("rawtypes")
	private static final class GcmMapper implements RowMapper<Gcm> {

	    public Gcm mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Gcm gcm = new Gcm();
	        
	        gcm.setId(rs.getString("id"));
	        gcm.setUsername(rs.getString("username"));
	        gcm.setRegid(rs.getString("regid"));
	        gcm.setImei(rs.getString("imei"));
	        gcm.setCreated(rs.getString("created"));
	        gcm.setActivation(rs.getString("activation"));
	        //gcm.setEmail(rs.getString("email"));
	        gcm.setId(rs.getString("id"));
	        gcm.setPhone(rs.getString("Phone"));
	        gcm.setMac(rs.getString("Mac"));
	        return gcm;
	        
	    }
	}

}
