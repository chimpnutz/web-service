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
				+ "type," 
				+ "requirements_no)"
				+ "VALUES(?,?,?,?,?)";

		Object[]  params ={
				image.getId(),
				image.getApplication_id(),
				image.getFilename(),
				image.getType(),
				image.getNumber(),
		};
		
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				
		};
		
	
		String checkImage = "SELECT * FROM image WHERE type = ? and application_id = ?";
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(checkImage,image.getType(),image.getApplication_id());
		
		if(rs.next()){
			
			StringBuilder updateImage = new StringBuilder();
			   
			updateImage.append("update image set type = ?, filename=?,number = ? where type = ? and application_id = ? ");
		   
			int poRow=0;
			
			System.out.println("updating image with application_id: "+image.getApplication_id());	
		   try{
			   
			   poRow = jdbcTemplate.update(updateImage.toString(), new Object[] { 
				   image.getType(),image.getFilename(),image.getNumber(),image.getType(),image.getApplication_id()
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
		String sql = "SELECT * FROM image WHERE application_id = ? ";
		
		return jdbcTemplate.query(sql.toString(), new Object[]{image.getApplication_id()},new ImageMapper());
		
		
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
			image.setNumber(rs.getString("requirements_no"));
			
			return image;
		}
		
	}
	
	
}
