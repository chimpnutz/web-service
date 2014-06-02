package com.circles.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.Image;


public class ImageDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	
	public int save(Image image){
		String sql = "INSERT INTO image("
				+ "id,"
				+ "application_id,"
				+ "filename,"
				+ "type)"
				+ "VALUES(?,?,?,?)";

		Object[]  params ={
				image.getId(),
				image.getApplication_id(),
				image.getFilename(),
				image.getType(),
		};
		
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR
				
		};
		
	
		String checkImage = "SELECT * FROM image WHERE type = ? and application_id = ?";
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(checkImage,image.getType(),image.getApplication_id());
		
		if(rs.next()){
			
			StringBuilder updateImage = new StringBuilder();
			   
			updateImage.append("update image set type = ?, filename=? where type = ? and application_id = ? ");
		   
			int poRow=0;
			
			System.out.println("updating image with application_id: "+image.getApplication_id());	
		   try{
			   
			   poRow = jdbcTemplate.update(updateImage.toString(), new Object[] { 
				   image.getType(),image.getFilename(),image.getType(),image.getApplication_id()
				});
			   
			   System.out.println("image of application_id: "+image.getApplication_id()+" has been updated");	   
		            
			   return poRow;
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	
	        }
			
		}
		else{
			
			System.out.println("inserting image with application_id: "+image.getApplication_id());
			int isSaved = jdbcTemplate.update(sql,params,types);
			System.out.println("image of application_id: "+image.getApplication_id()+" has been inserted");	
			return isSaved;
		}
		return 0;
	
	}
	public int delete(Image image){
		String sql = "SELECT * FROM image WHERE application_id=?";
		int isExisting = 0;
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {image.getApplication_id()},new int[] {Types.VARCHAR});
		//System.out.println(isExisting);
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	
	public Collection getImages(Image image){
		String sql = "SELECT * FROM image WHERE dvar=? ";
		int iterator = 1;
		System.out.println("it gets here");
		try{
			if(!image.getId().isEmpty()||!image.getId().equals(null)||!image.getId().equals("")){
				sql+="AND id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getApplication_id().isEmpty()||!image.getApplication_id().equals(null)||!image.getApplication_id().equals("")){
				sql+="AND application_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getFilename().isEmpty()||!image.getFilename().equals(null)||!image.getFilename().equals("")){
				sql+="AND filename=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getType().isEmpty()||!image.getType().equals(null)||!image.getType().equals("")){
				sql+="AND type=? ";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		Object[] params = new Object[iterator];
		params[0] = "1";
	
		iterator = 1;
		
		try{
			if(!image.getId().isEmpty()||!image.getId().equals(null)||!image.getId().equals("")){
				params[iterator] = image.getId();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getApplication_id().isEmpty()||!image.getApplication_id().equals(null)||!image.getApplication_id().equals("")){
				params[iterator] = image.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getFilename().isEmpty()||!image.getFilename().equals(null)||!image.getFilename().equals("")){
				params[iterator] = image.getFilename();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!image.getType().isEmpty()||!image.getType().equals(null)||!image.getType().equals("")){
				params[iterator] = image.getType();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}


		
		Collection s = null; 
		System.out.println(sql);
		for(Object o:params){
			System.out.println(o);
		}
		s = jdbcTemplate.query(sql, params, new ImageMapper());
		return s;
	}
	
	public int checkIfExists(Image image){
		String sql = "SELECT * FROM image WHERE id=?";
		int isExisting = 0;
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {image.getId()},new int[] {Types.VARCHAR});
		//System.out.println(isExisting);
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	
private static final class ImageMapper implements RowMapper<Image>{
		
		public Image mapRow(ResultSet rs, int rowNum) throws SQLException{
			Image image = new Image();
			
			image.setId(rs.getString("id"));
			image.setApplication_id(rs.getString("application_id"));
			image.setFilename(rs.getString("filename"));
			image.setType(rs.getString("type"));
			
			return image;
		}
		
	}
	
	
}
