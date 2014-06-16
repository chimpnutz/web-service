package com.tapsend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.tapsend.model.SSS;

public class SssDAOImpl {

private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveSSS(SSS ss){
		
		String sql = "INSERT INTO sss ("
				+ "sss_id,"
				+ "id,"
				+ "user_id,"
				+ "pension_type,"
				+ "pension_account_number,"
				+ "member_type,"
				+ "employer_id,"
				+ "receipt_number,"
				+ "doc1,"
				+ "doc2,doc3,doc4,ispushed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER
		};
		Object[] params = {
				ss.getSss_id(),
				ss.getId(),
				ss.getUser_id(),
				ss.getPension_type(),
				ss.getPension_account_number(),
				ss.getMember_type(),
				ss.getEmployer_id(),
				ss.getReceipt_number(),
				ss.getDoc1(),
				ss.getDoc2(),
				ss.getDoc3(),
				ss.getDoc4(),
				ss.getIspushed()
		};
		
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.println(o+", ");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		System.out.println("Save status:"+isSaved);
		return isSaved;
		
	}

	public Collection viewSSS(SSS ss){
		Collection s=null;
		String sql = "SELECT * FROM sss WHERE dvar=? ";
		int iterator=1;
		try{
			if(!ss.getSss_id().isEmpty()||!ss.getSss_id().equals(null)||!ss.getSss_id().equals("")){
				sql+=" AND sss_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getId().isEmpty()||!ss.getId().equals(null)||!ss.getId().equals("")){
	
				sql+=" AND id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getUser_id().isEmpty()||!ss.getUser_id().equals(null)||!ss.getUser_id().equals("")){
	
				sql+=" AND user_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		
		try{
			if(!ss.getPension_type().isEmpty()||!ss.getPension_type().equals(null)||!ss.getPension_type().equals("")){
	
				sql+=" AND pension_type=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getPension_account_number().isEmpty()||!ss.getPension_account_number().equals(null)||!ss.getPension_account_number().equals("")){
	
				sql+=" AND pension_account_number=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getMember_type().isEmpty()||!ss.getMember_type().equals(null)||!ss.getMember_type().equals("")){
	
				sql+=" AND member_type=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getEmployer_id().isEmpty()||!ss.getEmployer_id().equals(null)||!ss.getEmployer_id().equals("")){
	
				sql+=" AND employer_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getReceipt_number().isEmpty()||!ss.getReceipt_number().equals(null)||!ss.getReceipt_number().equals("")){
	
				sql+=" AND receipt_number=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc1().isEmpty()||!ss.getDoc1().equals(null)||!ss.getDoc1().equals("")){
			
				sql+=" AND doc1=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc2().isEmpty()||!ss.getDoc2().equals(null)||!ss.getDoc2().equals("")){
			
				sql+=" AND doc2=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc3().isEmpty()||!ss.getDoc3().equals(null)||!ss.getDoc3().equals("")){
			
				sql+=" AND doc3=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc4().isEmpty()||!ss.getDoc4().equals(null)||!ss.getDoc4().equals("")){
			
				sql+=" AND doc4=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getIspushed().isEmpty()||!ss.getIspushed().equals(null)||!ss.getIspushed().equals("")){
			
				sql+=" AND ispushed=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		Object[] params=new Object[iterator];
		params[0]=1;
		iterator = 1;
		
		try{
			if(!ss.getSss_id().isEmpty()||!ss.getSss_id().equals(null)||!ss.getSss_id().equals("")){
		
				params[iterator]=ss.getSss_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getId().isEmpty()||!ss.getId().equals(null)||!ss.getId().equals("")){
	
				params[iterator]=ss.getId();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getUser_id().isEmpty()||!ss.getUser_id().equals(null)||!ss.getUser_id().equals("")){
	
				params[iterator]=ss.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		
		try{
			if(!ss.getPension_type().isEmpty()||!ss.getPension_type().equals(null)||!ss.getPension_type().equals("")){
	
				params[iterator]=ss.getPension_type();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getPension_account_number().isEmpty()||!ss.getPension_account_number().equals(null)||!ss.getPension_account_number().equals("")){
	
				params[iterator]=ss.getPension_account_number();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getMember_type().isEmpty()||!ss.getMember_type().equals(null)||!ss.getMember_type().equals("")){
	
				params[iterator]=ss.getMember_type();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getEmployer_id().isEmpty()||!ss.getEmployer_id().equals(null)||!ss.getEmployer_id().equals("")){
	
				params[iterator]=ss.getEmployer_id();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!ss.getReceipt_number().isEmpty()||!ss.getReceipt_number().equals(null)||!ss.getReceipt_number().equals("")){
	
				params[iterator]=ss.getReceipt_number();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc1().isEmpty()||!ss.getDoc1().equals(null)||!ss.getDoc1().equals("")){
	
				params[iterator]=ss.getDoc1();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc2().isEmpty()||!ss.getDoc2().equals(null)||!ss.getDoc2().equals("")){
	
				params[iterator]=ss.getDoc2();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc3().isEmpty()||!ss.getDoc3().equals(null)||!ss.getDoc3().equals("")){
	
				params[iterator]=ss.getDoc3();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getDoc4().isEmpty()||!ss.getDoc4().equals(null)||!ss.getDoc4().equals("")){
	
				params[iterator]=ss.getDoc4();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!ss.getIspushed().isEmpty()||!ss.getIspushed().equals(null)||!ss.getIspushed().equals("")){
	
				params[iterator]=ss.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		System.out.println("SQL: "+sql);
		System.out.println("params: ");
		
		for(Object o:params){
			System.out.println(o+", ");
		}
		
		s = jdbcTemplate.query(sql,params, new SssMapper());
		return s;
	}

	public int checkIfExists(SSS ss){
		String sql = "SELECT * FROM sss WHERE user_id=? ";
		Object[] params= {ss.getUser_id()};
		int isExisting = 0;
		System.out.println(ss.getUser_id()+" ");
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
		
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	
	private static final class SssMapper implements RowMapper<SSS>{
		public SSS mapRow(ResultSet rs, int rowNum)throws SQLException{
			SSS ss = new SSS();
			ss.setSss_id(rs.getString("sss_id"));
			ss.setId(rs.getString("id"));
			ss.setUser_id(rs.getString("user_id"));
			ss.setPension_type(rs.getString("pension_type"));
			ss.setPension_account_number(rs.getString("pension_account_number"));
			ss.setMember_type(rs.getString("member_type"));			
			ss.setEmployer_id(rs.getString("employer_id"));
			ss.setReceipt_number(rs.getString("receipt_number"));
			ss.setDoc1(rs.getString("doc1"));
			ss.setDoc2(rs.getString("doc2"));
			ss.setDoc3(rs.getString("doc3"));
			ss.setDoc4(rs.getString("doc4"));
			ss.setIspushed(rs.getString("ispushed"));
			return ss;
		}
	}

}
