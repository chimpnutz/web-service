package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.Branch;

public class BranchDAOImpl {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public int save(Branch branch){
		String sql = "INSERT INTO branch("
				+ "branch_id,"
				+ "branch_name,"
				+ "longitude,"
				+ "latitude,"
				+ "ispushed,"
				+ "edited_by,"
				+ "created,"
				+ "updated,"
				+ "version)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				branch.getBranch_id(),
				branch.getBranch_name(),
				branch.getLongitude(),
				branch.getLatitude(),
				branch.getIspushed(),
				branch.getEdited_by(),
				branch.getCreated(),
				branch.getUpdated(),
				branch.getVersion()
		};
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER,
				Types.VARCHAR,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER
		};
		
		int isSaved  = jdbcTemplate.update(sql,params,types);
		
		return 1;
	}
	public int update(Branch branch){
		String sql = "UPDATE branch SET dvar=?";
		int iterator = 1;
		try{
			if(!branch.getBranch_id().isEmpty()||!branch.getBranch_id().equals(null)||!branch.getBranch_id().equals("")){
				sql+=", branch_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getBranch_name().isEmpty()||!branch.getBranch_name().equals(null)||!branch.getBranch_name().equals("")){
				sql+=", branch_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLongitude().isEmpty()||!branch.getLongitude().equals(null)||!branch.getLongitude().equals("")){
				sql+=", longitude=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLatitude().isEmpty()||!branch.getLatitude().equals(null)||!branch.getLatitude().equals("")){
				sql+=", latitude=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getIspushed().isEmpty()||!branch.getIspushed().equals(null)||!branch.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getEdited_by().isEmpty()||!branch.getEdited_by().equals(null)||!branch.getEdited_by().equals("")){
				sql+=", edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getCreated().isEmpty()||!branch.getCreated().equals(null)||!branch.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getUpdated().isEmpty()||!branch.getUpdated().equals(null)||!branch.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getVersion().isEmpty()||!branch.getVersion().equals(null)||!branch.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		sql+="WHERE branch_id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = branch.getBranch_id();
		iterator = 1;
		
		try{
			if(!branch.getBranch_id().isEmpty()||!branch.getBranch_id().equals(null)||!branch.getBranch_id().equals("")){
				params[iterator] = branch.getBranch_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getBranch_name().isEmpty()||!branch.getBranch_name().equals(null)||!branch.getBranch_name().equals("")){
				params[iterator] = branch.getBranch_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLongitude().isEmpty()||!branch.getLongitude().equals(null)||!branch.getLongitude().equals("")){
				params[iterator] = branch.getLongitude();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLatitude().isEmpty()||!branch.getLatitude().equals(null)||!branch.getLatitude().equals("")){
				params[iterator] = branch.getLatitude();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getIspushed().isEmpty()||!branch.getIspushed().equals(null)||!branch.getIspushed().equals("")){
				params[iterator] = branch.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getEdited_by().isEmpty()||!branch.getEdited_by().equals(null)||!branch.getEdited_by().equals("")){
				params[iterator] = branch.getEdited_by();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getCreated().isEmpty()||!branch.getCreated().equals(null)||!branch.getCreated().equals("")){
				params[iterator] = branch.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getUpdated().isEmpty()||!branch.getUpdated().equals(null)||!branch.getUpdated().equals("")){
				params[iterator] = branch.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getVersion().isEmpty()||!branch.getVersion().equals(null)||!branch.getVersion().equals("")){
				params[iterator] = branch.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		int isUpdated = jdbcTemplate.update(sql,params);
		return isUpdated;
	}
	public int delete(Branch branch){
		String sql = "DELETE FROM branch WHERE branch_id=?";
		int isDeleted = jdbcTemplate.update(sql,new Object[]{branch.getBranch_id()});
		return 1;
	}
	
	public Collection selectBranch(Branch branch){
		String sql = "SELECT * FROM branch WHERE dvar=?";
		int iterator = 1;
		try{
			if(!branch.getBranch_id().isEmpty()||!branch.getBranch_id().equals(null)||!branch.getBranch_id().equals("")){
				sql+="AND branch_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getBranch_name().isEmpty()||!branch.getBranch_name().equals(null)||!branch.getBranch_name().equals("")){
				sql+="AND branch_name=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLongitude().isEmpty()||!branch.getLongitude().equals(null)||!branch.getLongitude().equals("")){
				sql+="AND longitude=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLatitude().isEmpty()||!branch.getLatitude().equals(null)||!branch.getLatitude().equals("")){
				sql+="AND latitude=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getIspushed().isEmpty()||!branch.getIspushed().equals(null)||!branch.getIspushed().equals("")){
				sql+="AND ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getEdited_by().isEmpty()||!branch.getEdited_by().equals(null)||!branch.getEdited_by().equals("")){
				sql+="AND edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getCreated().isEmpty()||!branch.getCreated().equals(null)||!branch.getCreated().equals("")){
				sql+="AND created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getUpdated().isEmpty()||!branch.getUpdated().equals(null)||!branch.getUpdated().equals("")){
				sql+="AND updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getVersion().isEmpty()||!branch.getVersion().equals(null)||!branch.getVersion().equals("")){
				sql+="AND version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		

		Object[] params = new Object[iterator];
		params[0] = "1";

		iterator = 1;
		
		try{
			if(!branch.getBranch_id().isEmpty()||!branch.getBranch_id().equals(null)||!branch.getBranch_id().equals("")){
				params[iterator] = branch.getBranch_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getBranch_name().isEmpty()||!branch.getBranch_name().equals(null)||!branch.getBranch_name().equals("")){
				params[iterator] = branch.getBranch_name();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLongitude().isEmpty()||!branch.getLongitude().equals(null)||!branch.getLongitude().equals("")){
				params[iterator] = branch.getLongitude();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getLatitude().isEmpty()||!branch.getLatitude().equals(null)||!branch.getLatitude().equals("")){
				params[iterator] = branch.getLatitude();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getIspushed().isEmpty()||!branch.getIspushed().equals(null)||!branch.getIspushed().equals("")){
				params[iterator] = branch.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getEdited_by().isEmpty()||!branch.getEdited_by().equals(null)||!branch.getEdited_by().equals("")){
				params[iterator] = branch.getEdited_by();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getCreated().isEmpty()||!branch.getCreated().equals(null)||!branch.getCreated().equals("")){
				params[iterator] = branch.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getUpdated().isEmpty()||!branch.getUpdated().equals(null)||!branch.getUpdated().equals("")){
				params[iterator] = branch.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!branch.getVersion().isEmpty()||!branch.getVersion().equals(null)||!branch.getVersion().equals("")){
				params[iterator] = branch.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		Collection s = null;
		s =  jdbcTemplate.query(sql, params, new BranchMapper());
		return s;
	}
	public Collection selectAllBranch(){
		return jdbcTemplate.query("SELECT * FROM branch", new BranchMapper());
	}
	private static final class BranchMapper implements RowMapper<Branch>{
		
		public Branch mapRow(ResultSet rs, int rowNum) throws SQLException{
			Branch branch = new Branch();
			
			branch.setBranch_id(rs.getString("branch_id"));
			branch.setBranch_name(rs.getString("branch_name"));
			branch.setLongitude(rs.getString("longitude"));
			branch.setLatitude(rs.getString("latitude"));
			branch.setIspushed(rs.getString("ispushed"));
			branch.setEdited_by(rs.getString("edited_by"));
			branch.setCreated(rs.getString("created"));
			branch.setUpdated(rs.getString("updated"));
			branch.setVersion(rs.getString("version"));

			return branch;
		}
		
	}
	

}
