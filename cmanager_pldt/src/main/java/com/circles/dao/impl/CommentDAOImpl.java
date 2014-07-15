package com.circles.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;






import com.circles.model.Comment;

public class CommentDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Comment comment){
		System.out.println("username on comment: "+comment.getUser_id());
		String sql = "INSERT INTO comment("
				+ "id,"
				+ "application_id,"
				+ "user_id,"
				+ "ispushed,"
				+ "edited_by,"
				+ "created,"
				+ "updated,"
				+ "version,"
				+ "content)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		comment.setIspushed("0");
				Object[] params = {
					comment.getId(),
					comment.getApplication_id(),
					comment.getUser_id(),
					comment.getIspushed(),
					comment.getEdited_by(),
					comment.getCreated(),
					comment.getUpdated(),
					comment.getVersion(),
					comment.getContent()
				};
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.BIGINT,
				Types.BIGINT,
				Types.INTEGER,
				Types.VARCHAR
		};
	
		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		return isSaved;
	}
	public int update(Comment comment){
		String sql = "UPDATE comment SET dvar=?";
		int iterator = 1;
		try{
			if(!comment.getId().isEmpty()||!comment.getId().equals(null)||!comment.getId().equals("")){
				sql+=", id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getApplication_id().isEmpty()||!comment.getApplication_id().equals(null)||!comment.getApplication_id().equals("")){
				sql+=", application_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getUser_id().isEmpty()||!comment.getUser_id().equals(null)||!comment.getUser_id().equals("")){
				sql+=", user_id=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getIspushed().isEmpty()||!comment.getIspushed().equals(null)||!comment.getIspushed().equals("")){
				sql+=", ispushed=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!comment.getEdited_by().isEmpty()||!comment.getEdited_by().equals(null)||!comment.getEdited_by().equals("")){
				sql+=", edited_by=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getCreated().isEmpty()||!comment.getCreated().equals(null)||!comment.getCreated().equals("")){
				sql+=", created=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getUpdated().isEmpty()||!comment.getUpdated().equals(null)||!comment.getUpdated().equals("")){
				sql+=", updated=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getVersion().isEmpty()||!comment.getVersion().equals(null)||!comment.getVersion().equals("")){
				sql+=", version=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getContent().isEmpty()||!comment.getContent().equals(null)||!comment.getIspushed().equals("")){
				sql+=", content=?";
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		sql+=" WHERE id=?";
		Object[] params = new Object[iterator+1];
		params[0] = "1";
		params[iterator] = comment.getId();
		iterator = 1;
		
		try{
			if(!comment.getId().isEmpty()||!comment.getId().equals(null)||!comment.getId().equals("")){
				params[iterator] = comment.getId();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getApplication_id().isEmpty()||!comment.getApplication_id().equals(null)||!comment.getApplication_id().equals("")){
				params[iterator] = comment.getApplication_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getUser_id().isEmpty()||!comment.getUser_id().equals(null)||!comment.getUser_id().equals("")){
				params[iterator] = comment.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getIspushed().isEmpty()||!comment.getIspushed().equals(null)||!comment.getIspushed().equals("")){
				params[iterator] = comment.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}

		try{
			if(!comment.getEdited_by().isEmpty()||!comment.getEdited_by().equals(null)||!comment.getEdited_by().equals("")){
				params[iterator] = comment.getEdited_by();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getCreated().isEmpty()||!comment.getCreated().equals(null)||!comment.getCreated().equals("")){
				params[iterator] = comment.getCreated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getUpdated().isEmpty()||!comment.getUpdated().equals(null)||!comment.getUpdated().equals("")){
				params[iterator] = comment.getUpdated();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getVersion().isEmpty()||!comment.getVersion().equals(null)||!comment.getVersion().equals("")){
				params[iterator] = comment.getVersion();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!comment.getContent().isEmpty()||!comment.getContent().equals(null)||!comment.getIspushed().equals("")){
				params[iterator] = comment.getContent();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		System.out.println(sql);
		for(Object s : params){
			System.out.print(s+",");
		}
		int isUpdated = jdbcTemplate.update(sql, params);	
		return isUpdated;
	}
	public int delete(Comment comment){
		String sql = "DELETE FROM comment WHERE id=?";
		Object[] params = {comment.getId()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	
	@SuppressWarnings("deprecation")
	public int checkIfExists(Comment comment){
		String sql="SELECT * FROM comment where id=?";
		int isExisting = 0;
		
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,new Object[] {comment.getId()},new int[] {Types.VARCHAR});
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	@SuppressWarnings("unchecked")
	public Collection findAllComment(){
		return jdbcTemplate.query("SELECT * FROM comment", new CommentMapper());
	}
	
	public Collection findUserComment(Comment comment) throws SQLException{
		String sql = "SELECT * FROM comment WHERE dvar=? ";
		
		int iterator = 1;
		try{
			if(!comment.getApplication_id().isEmpty()||!comment.getApplication_id().equals(null)||!comment.getApplication_id().equals("")){
				sql+="AND application_id=?";
				
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		Object[] params = new Object[iterator];
		params[0] = "1";
		try{
			if(!comment.getApplication_id().isEmpty()||!comment.getApplication_id().equals(null)||!comment.getApplication_id().equals("")){
				params[iterator] = comment.getApplication_id();
				
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		
		return jdbcTemplate.query(sql, params,new CommentMapper());
	
	}
	
	public Collection selectJoinComments() throws SQLException{
		String sql = "SELECT * FROM comment INNER JOIN user ON comment.user_id = user.username";

		return jdbcTemplate.query(sql,new CommentMapper());
	
	}
	
	public Collection findComment(Comment comment) throws SQLException{
		String sql = "SELECT * FROM comment WHERE application_id = ? ORDER BY created DESC";
		
		return jdbcTemplate.query(sql, new Object[]{comment.getApplication_id()},new CommentMapper());
	
	}
	
	public Collection getUserComment(String username){
		
		String sql = "SELECT a.*, b.* FROM comment a INNER JOIN application b ON b.application_id = a.application_id WHERE b.username=?";

		return jdbcTemplate.query(sql,new Object[] {username},new CommentMapper());
	}
	
	@SuppressWarnings("rawtypes")
	private static final class CommentMapper implements RowMapper<Comment> {

	    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Comment comment = new Comment();
	        
	        comment.setId(rs.getString("id"));
	        comment.setApplication_id(rs.getString("application_id"));
	        comment.setUser_id(rs.getString("user_id"));
	        comment.setIspushed(rs.getString("ispushed"));
	        comment.setEdited_by(rs.getString("edited_by"));
	        comment.setCreated(rs.getString("created"));
	        comment.setUpdated(rs.getString("updated"));
	        comment.setContent(rs.getString("content"));
	        return comment;
	        
	    }
	}

}
