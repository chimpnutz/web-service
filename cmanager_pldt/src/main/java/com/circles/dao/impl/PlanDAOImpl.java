package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.Plan;

public class PlanDAOImpl {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public int save(Plan phone){
		String sql = "INSERT INTO plan("
				+ "phone_id,"
				+ "availability,"
				+ "plan_name,"
				+ "plan_code,"
				+ "plan_type,"
				+ "plan_desc,"
				+ "plan_price,"
				+ "edited_by,"
				+ "ispushed,"
				+ "version,"
				+ "created,"
				+ "updated)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				phone.getPlan_id(),
				phone.getAvailability(),
				phone.getPlan_name(),
				phone.getPlan_code(),
				phone.getPlan_type(),
				phone.getPlan_desc(),
				phone.getPlan_price(),
				phone.getEdited_by(),
				phone.getIspushed(),
				phone.getVersion(),
				phone.getCreated(),
				phone.getUpdated()
				
		};
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.BIGINT,
				Types.BIGINT
		};
		
		int isSaved  = jdbcTemplate.update(sql,params,types);
		
		return isSaved;
	}
	public int update(Plan phone){
		String sql = "UPDATE phone SET dvar=?";
		int iterator = 1;
		try{
			if(!phone.getPlan_id().isEmpty()||!phone.getPlan_id().equals(null)||!phone.getPlan_id().equals("")){
				sql+=", phone_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getAvailability().isEmpty()||!phone.getAvailability().equals(null)||!phone.getAvailability().equals("")){
				sql+=", availability=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_name().isEmpty()||!phone.getPlan_name().equals(null)||!phone.getPlan_name().equals("")){
				sql+=", plan_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_code().isEmpty()||!phone.getPlan_code().equals(null)||!phone.getPlan_code().equals("")){
				sql+=", plan_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_type().isEmpty()||!phone.getPlan_type().equals(null)||!phone.getPlan_type().equals("")){
				sql+=", plan_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_desc().isEmpty()||!phone.getPlan_desc().equals(null)||!phone.getPlan_desc().equals("")){
				sql+=", plan_desc=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_price().isEmpty()||!phone.getPlan_price().equals(null)||!phone.getPlan_price().equals("")){
				sql+=", plan_price=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getEdited_by().isEmpty()||!phone.getEdited_by().equals(null)||!phone.getEdited_by().equals("")){
				sql+=", edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getIspushed().isEmpty()||!phone.getIspushed().equals(null)||!phone.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getVersion().isEmpty()||!phone.getVersion().equals(null)||!phone.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getCreated().isEmpty()||!phone.getCreated().equals(null)||!phone.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getUpdated().isEmpty()||!phone.getUpdated().equals(null)||!phone.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		
		sql+="WHERE plan_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = phone.getPlan_id();
		iterator = 1;
		
		try{
			if(!phone.getPlan_id().isEmpty()||!phone.getPlan_id().equals(null)||!phone.getPlan_id().equals("")){
				params[iterator] = phone.getPlan_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getAvailability().isEmpty()||!phone.getAvailability().equals(null)||!phone.getAvailability().equals("")){
				params[iterator] = phone.getAvailability();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_name().isEmpty()||!phone.getPlan_name().equals(null)||!phone.getPlan_name().equals("")){
				params[iterator] = phone.getPlan_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_code().isEmpty()||!phone.getPlan_code().equals(null)||!phone.getPlan_code().equals("")){
				params[iterator] = phone.getPlan_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_type().isEmpty()||!phone.getPlan_type().equals(null)||!phone.getPlan_type().equals("")){
				params[iterator] = phone.getPlan_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_desc().isEmpty()||!phone.getPlan_desc().equals(null)||!phone.getPlan_desc().equals("")){
				params[iterator] = phone.getPlan_desc();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_price().isEmpty()||!phone.getPlan_price().equals(null)||!phone.getPlan_price().equals("")){
				params[iterator] = phone.getPlan_price();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getEdited_by().isEmpty()||!phone.getEdited_by().equals(null)||!phone.getEdited_by().equals("")){
				params[iterator] = phone.getEdited_by();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getIspushed().isEmpty()||!phone.getIspushed().equals(null)||!phone.getIspushed().equals("")){
				params[iterator] = phone.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getVersion().isEmpty()||!phone.getVersion().equals(null)||!phone.getVersion().equals("")){
				params[iterator] = phone.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getCreated().isEmpty()||!phone.getCreated().equals(null)||!phone.getCreated().equals("")){
				params[iterator] = phone.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getUpdated().isEmpty()||!phone.getUpdated().equals(null)||!phone.getUpdated().equals("")){
				params[iterator] = phone.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		int isUpdated = jdbcTemplate.update(sql,params);
		return isUpdated;
	}
	public int delete(Plan phone){
		String sql = "DELETE FROM plan WHERE plan_id=?";
		int isDeleted = jdbcTemplate.update(sql,new Object[]{phone.getPlan_id()});
		return 1;
	}
	
	public Collection selectPlan(Plan phone){
		String sql = "SELECT * FROM plan WHERE dvar=?";
		int iterator = 1;
		try{
			if(!phone.getPlan_id().isEmpty()||!phone.getPlan_id().equals(null)||!phone.getPlan_id().equals("")){
				sql+=", phone_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getAvailability().isEmpty()||!phone.getAvailability().equals(null)||!phone.getAvailability().equals("")){
				sql+=", availability=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_name().isEmpty()||!phone.getPlan_name().equals(null)||!phone.getPlan_name().equals("")){
				sql+=", plan_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_code().isEmpty()||!phone.getPlan_code().equals(null)||!phone.getPlan_code().equals("")){
				sql+=", plan_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_type().isEmpty()||!phone.getPlan_type().equals(null)||!phone.getPlan_type().equals("")){
				sql+=", plan_type=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_desc().isEmpty()||!phone.getPlan_desc().equals(null)||!phone.getPlan_desc().equals("")){
				sql+=", plan_desc=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_price().isEmpty()||!phone.getPlan_price().equals(null)||!phone.getPlan_price().equals("")){
				sql+=", plan_price=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getEdited_by().isEmpty()||!phone.getEdited_by().equals(null)||!phone.getEdited_by().equals("")){
				sql+=", edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getIspushed().isEmpty()||!phone.getIspushed().equals(null)||!phone.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getVersion().isEmpty()||!phone.getVersion().equals(null)||!phone.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getCreated().isEmpty()||!phone.getCreated().equals(null)||!phone.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getUpdated().isEmpty()||!phone.getUpdated().equals(null)||!phone.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		
		Object[] params = new Object[iterator];
		params[0] = "1";
	
		iterator = 1;
		
		try{
			if(!phone.getPlan_id().isEmpty()||!phone.getPlan_id().equals(null)||!phone.getPlan_id().equals("")){
				params[iterator] = phone.getPlan_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getAvailability().isEmpty()||!phone.getAvailability().equals(null)||!phone.getAvailability().equals("")){
				params[iterator] = phone.getAvailability();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_name().isEmpty()||!phone.getPlan_name().equals(null)||!phone.getPlan_name().equals("")){
				params[iterator] = phone.getPlan_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_code().isEmpty()||!phone.getPlan_code().equals(null)||!phone.getPlan_code().equals("")){
				params[iterator] = phone.getPlan_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_type().isEmpty()||!phone.getPlan_type().equals(null)||!phone.getPlan_type().equals("")){
				params[iterator] = phone.getPlan_type();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_desc().isEmpty()||!phone.getPlan_desc().equals(null)||!phone.getPlan_desc().equals("")){
				params[iterator] = phone.getPlan_desc();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPlan_price().isEmpty()||!phone.getPlan_price().equals(null)||!phone.getPlan_price().equals("")){
				params[iterator] = phone.getPlan_price();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getEdited_by().isEmpty()||!phone.getEdited_by().equals(null)||!phone.getEdited_by().equals("")){
				params[iterator] = phone.getEdited_by();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getIspushed().isEmpty()||!phone.getIspushed().equals(null)||!phone.getIspushed().equals("")){
				params[iterator] = phone.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getVersion().isEmpty()||!phone.getVersion().equals(null)||!phone.getVersion().equals("")){
				params[iterator] = phone.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getCreated().isEmpty()||!phone.getCreated().equals(null)||!phone.getCreated().equals("")){
				params[iterator] = phone.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getUpdated().isEmpty()||!phone.getUpdated().equals(null)||!phone.getUpdated().equals("")){
				params[iterator] = phone.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		Collection s = null;
		s =  jdbcTemplate.query(sql, params, new PlanMapper());
		return s;
	}
	public Collection selectAllPlan(){
		return jdbcTemplate.query("SELECT * FROM plan", new PlanMapper());
	}
	private static final class PlanMapper implements RowMapper<Plan>{
		
		public Plan mapRow(ResultSet rs, int rowNum) throws SQLException{
			Plan phone = new Plan();
			
			phone.setPlan_id(rs.getString("plan_id"));
			phone.setAvailability(rs.getString("availability"));
			phone.setPlan_name(rs.getString("plan_name"));
			phone.setPlan_code(rs.getString("plan_code"));
			phone.setPlan_type(rs.getString("plan_type"));
			phone.setPlan_desc(rs.getString("plan_desc"));
			phone.setPlan_price(rs.getString("plan_price"));
			phone.setIspushed(rs.getString("ispushed"));	
			phone.setEdited_by(rs.getString("edited_by"));
			phone.setVersion(rs.getString("version"));
			phone.setCreated(rs.getString("created"));
			phone.setUpdated(rs.getString("updated"));


			return phone;
		}
		
	}
	

}
