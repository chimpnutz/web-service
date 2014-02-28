package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.Phone;

public class PhoneDAOImpl {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public int save(Phone phone){
		String sql = "INSERT INTO phone("
				+ "phone_id,"
				+ "availability,"
				+ "phone_model,"
				+ "phone_code,"
				+ "phone_mfr,"
				+ "phone_desc,"
				+ "edited_by,"
				+ "ispushed,"
				+ "version,"
				+ "created,"
				+ "updated,"
				+ "image_name)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				phone.getPhone_id(),
				phone.getAvailability(),
				phone.getPhone_model(),
				phone.getPhone_code(),
				phone.getPhone_mfr(),
				phone.getPhone_desc(),
				phone.getEdited_by(),
				phone.getIspushed(),
				phone.getVersion(),
				phone.getCreated(),
				phone.getUpdated(),
				phone.getImage_name()
				
		};
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.BIGINT,
				Types.BIGINT,
				Types.VARCHAR
		};
		
		int isSaved  = jdbcTemplate.update(sql,params,types);
		
		return isSaved;
	}
	public int update(Phone phone){
		String sql = "UPDATE phone SET dvar=?";
		int iterator = 1;
		try{
			if(!phone.getPhone_id().isEmpty()||!phone.getPhone_id().equals(null)||!phone.getPhone_id().equals("")){
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
			if(!phone.getPhone_model().isEmpty()||!phone.getPhone_model().equals(null)||!phone.getPhone_model().equals("")){
				sql+=", phone_model=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_code().isEmpty()||!phone.getPhone_code().equals(null)||!phone.getPhone_code().equals("")){
				sql+=", phone_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_mfr().isEmpty()||!phone.getPhone_mfr().equals(null)||!phone.getPhone_mfr().equals("")){
				sql+=", phone_mfr=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_desc().isEmpty()||!phone.getPhone_desc().equals(null)||!phone.getPhone_desc().equals("")){
				sql+=", phone_desc=?";
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
		
		try{
			if(!phone.getImage_name().isEmpty()||!phone.getImage_name().equals(null)||!phone.getImage_name().equals("")){
				sql+=", image_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		sql+="WHERE phone_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = phone.getPhone_id();
		iterator = 1;
		
		try{
			if(!phone.getPhone_id().isEmpty()||!phone.getPhone_id().equals(null)||!phone.getPhone_id().equals("")){
				params[iterator] = phone.getPhone_id();
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
			if(!phone.getPhone_model().isEmpty()||!phone.getPhone_model().equals(null)||!phone.getPhone_model().equals("")){
				params[iterator] = phone.getPhone_model();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_code().isEmpty()||!phone.getPhone_code().equals(null)||!phone.getPhone_code().equals("")){
				params[iterator] = phone.getPhone_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_mfr().isEmpty()||!phone.getPhone_mfr().equals(null)||!phone.getPhone_mfr().equals("")){
				params[iterator] = phone.getPhone_mfr();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_desc().isEmpty()||!phone.getPhone_desc().equals(null)||!phone.getPhone_desc().equals("")){
				params[iterator] = phone.getPhone_desc();
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
		try{
			if(!phone.getImage_name().isEmpty()||!phone.getImage_name().equals(null)||!phone.getImage_name().equals("")){
				params[iterator] = phone.getImage_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		int isUpdated = jdbcTemplate.update(sql,params);
		return isUpdated;
	}
	public int delete(Phone phone){
		String sql = "DELETE FROM phone WHERE phone_id=?";
		int isDeleted = jdbcTemplate.update(sql,new Object[]{phone.getPhone_id()});
		return 1;
	}
	
	public Collection selectPhone(Phone phone){
		String sql = "SELECT * FROM phone WHERE dvar=? ";
		int iterator = 1;
		try{
			if(!phone.getPhone_id().isEmpty()||!phone.getPhone_id().equals(null)||!phone.getPhone_id().equals("")){
				sql+="AND phone_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getAvailability().isEmpty()||!phone.getAvailability().equals(null)||!phone.getAvailability().equals("")){
				sql+="AND availability=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_model().isEmpty()||!phone.getPhone_model().equals(null)||!phone.getPhone_model().equals("")){
				sql+="AND phone_model=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_code().isEmpty()||!phone.getPhone_code().equals(null)||!phone.getPhone_code().equals("")){
				sql+="AND phone_code=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_mfr().isEmpty()||!phone.getPhone_mfr().equals(null)||!phone.getPhone_mfr().equals("")){
				sql+="AND phone_mfr=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_desc().isEmpty()||!phone.getPhone_desc().equals(null)||!phone.getPhone_desc().equals("")){
				sql+="AND phone_desc=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getEdited_by().isEmpty()||!phone.getEdited_by().equals(null)||!phone.getEdited_by().equals("")){
				sql+="AND edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getIspushed().isEmpty()||!phone.getIspushed().equals(null)||!phone.getIspushed().equals("")){
				sql+="AND ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getVersion().isEmpty()||!phone.getVersion().equals(null)||!phone.getVersion().equals("")){
				sql+="AND version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getCreated().isEmpty()||!phone.getCreated().equals(null)||!phone.getCreated().equals("")){
				sql+="AND created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getUpdated().isEmpty()||!phone.getUpdated().equals(null)||!phone.getUpdated().equals("")){
				sql+="AND updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getImage_name().isEmpty()||!phone.getImage_name().equals(null)||!phone.getImage_name().equals("")){
				sql += "AND image=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		Object[] params = new Object[iterator];
		params[0] = "1";
	
		iterator = 1;
		
		try{
			if(!phone.getPhone_id().isEmpty()||!phone.getPhone_id().equals(null)||!phone.getPhone_id().equals("")){
				params[iterator] = phone.getPhone_id();
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
			if(!phone.getPhone_model().isEmpty()||!phone.getPhone_model().equals(null)||!phone.getPhone_model().equals("")){
				params[iterator] = phone.getPhone_model();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_code().isEmpty()||!phone.getPhone_code().equals(null)||!phone.getPhone_code().equals("")){
				params[iterator] = phone.getPhone_code();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_mfr().isEmpty()||!phone.getPhone_mfr().equals(null)||!phone.getPhone_mfr().equals("")){
				params[iterator] = phone.getPhone_mfr();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!phone.getPhone_desc().isEmpty()||!phone.getPhone_desc().equals(null)||!phone.getPhone_desc().equals("")){
				params[iterator] = phone.getPhone_desc();
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
		try{
			if(!phone.getImage_name().isEmpty()||!phone.getImage_name().equals(null)||!phone.getImage_name().equals("")){
				params[iterator] = phone.getImage_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		Collection s = null;
		s =  jdbcTemplate.query(sql, params, new PhoneMapper());
		return s;
	}
	public Collection selectAllPhone(){
		return jdbcTemplate.query("SELECT * FROM phone", new PhoneMapper());
	}
	private static final class PhoneMapper implements RowMapper<Phone>{
		
		public Phone mapRow(ResultSet rs, int rowNum) throws SQLException{
			Phone phone = new Phone();
			
			phone.setPhone_id(rs.getString("phone_id"));
			phone.setAvailability(rs.getString("availability"));
			phone.setPhone_model(rs.getString("phone_model"));
			phone.setPhone_code(rs.getString("phone_code"));
			phone.setPhone_mfr(rs.getString("phone_mfr"));
			phone.setPhone_desc(rs.getString("phone_desc"));
			phone.setIspushed(rs.getString("ispushed"));	
			phone.setEdited_by(rs.getString("edited_by"));
			phone.setVersion(rs.getString("version"));
			phone.setCreated(rs.getString("created"));
			phone.setUpdated(rs.getString("updated"));
			phone.setImage_name(rs.getString("image_name"));

			

			return phone;
		}
		
	}
	

}
