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
		StringBuilder str = new StringBuilder();
		
		str.append("INSERT INTO gcm(id,username,regid,created,activation,imei,mobile_no,mac) ");
		str.append("VALUES(?,?,?,?,?,?,?,?) ");
						
		int isSaved = jdbcTemplate.update(str.toString(),new Object[]{
			gcm.getId(),gcm.getUsername(),gcm.getRegid(),gcm.getCreated(),
			gcm.getActivation(),gcm.getImei(),gcm.getPhone(),gcm.getMac()
		});	
		return isSaved;
	}
	
	public Collection findGcm(String appid){
		StringBuilder str = new StringBuilder();
	
		str.append("SELECT * FROM gcm WHERE username = (select username from application where application_id = ?)");
		
		return jdbcTemplate.query(str.toString(), new Object[]{
			appid
		},new GcmMapper());
	}

	public int update(Gcm gcm){
		String sql = "UPDATE gcm SET regid=?, imei =  ?, mobile_no = ?, mac = ? where username = ?";
		System.out.println("mobile no:" +gcm.getPhone());
		int isUpdated = jdbcTemplate.update(sql.toString(),new Object[] {gcm.getRegid(),gcm.getImei(),gcm.getPhone(),gcm.getMac(),gcm.getUsername()});	
		
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
		int isExisting = 0;
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {gcm.getImei()});
		if(rs.next()){

			System.out.println("imei exist");
			return 1;
		}
		return isExisting;
	}
	@SuppressWarnings("deprecation")
	public int checkActivationIfExists(Gcm gcm){
		String sql="SELECT * FROM gcm where username=? and activation=?";
		int isExisting = 0;
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {gcm.getUsername(),gcm.getActivation()});
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

	

	private static final class GcmMapper implements RowMapper<Gcm> {

	    public Gcm mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Gcm gcm = new Gcm();
	        
	        gcm.setId(rs.getString("id"));
	        gcm.setUsername(rs.getString("username"));
	        gcm.setRegid(rs.getString("regid"));
	        gcm.setImei(rs.getString("imei"));
	        gcm.setCreated(rs.getString("created"));
	        gcm.setActivation(rs.getString("activation"));
	        gcm.setId(rs.getString("id"));
	        gcm.setPhone(rs.getString("mobile_no"));
	        gcm.setMac(rs.getString("Mac"));
	        return gcm;
	        
	    }
	}

}
