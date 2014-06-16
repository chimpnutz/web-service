package com.tapsend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.tapsend.model.Pagibig;


public class PagibigDAOImpl {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int savePagibig(Pagibig pi){
		
		String sql = "INSERT INTO pagibig ("
				+ "pagibig_id,"
				+ "id,"
				+ "user_id,"
				+ "member_type,"
				+ "employer_business_name,"
				+ "employee_number,"
				+ "profession,"
				+ "employment_status,"
				+ "address,"
				+ "basic,allowance,total,doc1,doc2,doc3,doc4,ispushed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
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
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER
		};
		Object[] params = {
				pi.getPagibig_id(),
				pi.getId(),
				pi.getUser_id(),
				pi.getMember_type(),
				pi.getEmployer_business_name(),
				pi.getEmployee_number(),
				pi.getProfession(),
				pi.getEmployment_status(),
				pi.getAddress(),
				pi.getBasic(),
				pi.getAllowance(),
				pi.getTotal(),
				pi.getDoc1(),
				pi.getDoc2(),
				pi.getDoc3(),
				pi.getDoc4(),
				pi.getIspushed()
		};
		
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.println(o+", ");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		System.out.println("Save status:"+isSaved);
		return isSaved;
		
	}
	
	public Collection viewPagibig(Pagibig pi){
		Collection s=null;
		String sql = "SELECT * FROM pagibig WHERE dvar=? ";
		int iterator=1;
		try{
			if(!pi.getPagibig_id().isEmpty()||!pi.getPagibig_id().equals(null)||!pi.getPagibig_id().equals("")){
				sql+=" AND pagibig_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getId().isEmpty()||!pi.getId().equals(null)||!pi.getId().equals("")){

				sql+=" AND id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getUser_id().isEmpty()||!pi.getUser_id().equals(null)||!pi.getUser_id().equals("")){
	
				sql+=" AND user_id=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getMember_type().isEmpty()||!pi.getMember_type().equals(null)||!pi.getMember_type().equals("")){

				sql+=" AND member_type=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployer_business_name().isEmpty()||!pi.getEmployer_business_name().equals(null)||!pi.getEmployer_business_name().equals("")){
	
				sql+=" AND employer_business_name=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployee_number().isEmpty()||!pi.getEmployee_number().equals(null)||!pi.getEmployee_number().equals("")){
	
				sql+=" AND employee_number=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDate_start().isEmpty()||!pi.getDate_start().equals(null)||!pi.getDate_start().equals("")){
	
				sql+=" AND date_start=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getProfession().isEmpty()||!pi.getProfession().equals(null)||!pi.getProfession().equals("")){
	
				sql+=" AND profession=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployment_status().isEmpty()||!pi.getEmployment_status().equals(null)||!pi.getEmployment_status().equals("")){
			
				sql+=" AND employment_status=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getAddress().isEmpty()||!pi.getAddress().equals(null)||!pi.getAddress().equals("")){
			
				sql+=" AND address=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getBasic().isEmpty()||!pi.getBasic().equals(null)||!pi.getBasic().equals("")){
		;
				sql+=" AND basic=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getAllowance().isEmpty()||!pi.getAllowance().equals(null)||!pi.getAllowance().equals("")){
				
				sql+=" AND allowance=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getTotal().isEmpty()||!pi.getTotal().equals(null)||!pi.getTotal().equals("")){
			
				sql+=" AND total=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		
		try{
			if(!pi.getDoc1().isEmpty()||!pi.getDoc1().equals(null)||!pi.getDoc1().equals("")){
			
				sql+=" AND doc1=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc2().isEmpty()||!pi.getDoc2().equals(null)||!pi.getDoc2().equals("")){
			
				sql+=" AND doc2=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc3().isEmpty()||!pi.getDoc3().equals(null)||!pi.getDoc3().equals("")){
			
				sql+=" AND doc3=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc4().isEmpty()||!pi.getDoc4().equals(null)||!pi.getDoc4().equals("")){
			
				sql+=" AND doc4=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getIspushed().isEmpty()||!pi.getIspushed().equals(null)||!pi.getIspushed().equals("")){
			
				sql+=" AND ispushed=? ";
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		Object[] params=new Object[iterator];
		params[0]=1;
		iterator = 1;
		
		try{
			if(!pi.getPagibig_id().isEmpty()||!pi.getPagibig_id().equals(null)||!pi.getPagibig_id().equals("")){
		
				params[iterator]=pi.getPagibig_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getId().isEmpty()||!pi.getId().equals(null)||!pi.getId().equals("")){

				params[iterator]=pi.getId();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getUser_id().isEmpty()||!pi.getUser_id().equals(null)||!pi.getUser_id().equals("")){

				params[iterator]=pi.getUser_id();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getMember_type().isEmpty()||!pi.getMember_type().equals(null)||!pi.getMember_type().equals("")){

				params[iterator]=pi.getMember_type();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployer_business_name().isEmpty()||!pi.getEmployer_business_name().equals(null)||!pi.getEmployer_business_name().equals("")){

				params[iterator]=pi.getEmployer_business_name();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployee_number().isEmpty()||!pi.getEmployee_number().equals(null)||!pi.getEmployee_number().equals("")){

				params[iterator]=pi.getEmployee_number();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDate_start().isEmpty()||!pi.getDate_start().equals(null)||!pi.getDate_start().equals("")){

				params[iterator]=pi.getDate_start();
				iterator++;
			}
		}catch(NullPointerException e){
			
		}
		try{
			if(!pi.getProfession().isEmpty()||!pi.getProfession().equals(null)||!pi.getProfession().equals("")){

				params[iterator]=pi.getProfession();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getEmployment_status().isEmpty()||!pi.getEmployment_status().equals(null)||!pi.getEmployment_status().equals("")){

				params[iterator]=pi.getEmployment_status();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getAddress().isEmpty()||!pi.getAddress().equals(null)||!pi.getAddress().equals("")){

				params[iterator]=pi.getAddress();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getBasic().isEmpty()||!pi.getBasic().equals(null)||!pi.getBasic().equals("")){

				params[iterator]=pi.getBasic();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getAllowance().isEmpty()||!pi.getAllowance().equals(null)||!pi.getAllowance().equals("")){

				params[iterator]=pi.getAllowance();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getTotal().isEmpty()||!pi.getTotal().equals(null)||!pi.getTotal().equals("")){

				params[iterator]=pi.getTotal();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc1().isEmpty()||!pi.getDoc1().equals(null)||!pi.getDoc1().equals("")){

				params[iterator]=pi.getDoc1();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc2().isEmpty()||!pi.getDoc2().equals(null)||!pi.getDoc2().equals("")){

				params[iterator]=pi.getDoc2();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc3().isEmpty()||!pi.getDoc3().equals(null)||!pi.getDoc3().equals("")){

				params[iterator]=pi.getDoc3();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getDoc4().isEmpty()||!pi.getDoc4().equals(null)||!pi.getDoc4().equals("")){

				params[iterator]=pi.getDoc4();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		try{
			if(!pi.getIspushed().isEmpty()||!pi.getIspushed().equals(null)||!pi.getIspushed().equals("")){

				params[iterator]=pi.getIspushed();
				iterator++;
			}
		}catch(NullPointerException e){
		
		}
		System.out.println("SQL: "+sql);
		System.out.println("params: ");
		
		for(Object o:params){
			System.out.println(o+", ");
		}
		
		s = jdbcTemplate.query(sql,params, new PagibigMapper());
		return s;
	}
	
	public int checkIfExists(Pagibig pi){
		String sql = "SELECT * FROM pagibig WHERE user_id=? ";
		Object[] params= {pi.getUser_id()};
		int isExisting = 0;
		System.out.println(pi.getUser_id()+" ");
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
		
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}
	private static final class PagibigMapper implements RowMapper<Pagibig>{
		public Pagibig mapRow(ResultSet rs, int rowNum)throws SQLException{
			Pagibig pi = new Pagibig();
			pi.setPagibig_id(rs.getString("pagibig_id"));
			pi.setId(rs.getString("id"));
			pi.setUser_id(rs.getString("user_id"));
			pi.setMember_type(rs.getString("member_type"));
			pi.setEmployer_business_name(rs.getString("employer_business_name"));
			pi.setEmployee_number(rs.getString("employee_number"));
			pi.setDate_start(rs.getString("date_start"));
			pi.setProfession(rs.getString("profession"));
			pi.setEmployment_status(rs.getString("employment_status"));
			pi.setAddress(rs.getString("address"));
			pi.setBasic(rs.getString("basic"));
			pi.setAllowance(rs.getString("allowance"));
			pi.setTotal(rs.getString("total"));
			pi.setDoc1(rs.getString("doc1"));
			pi.setDoc2(rs.getString("doc2"));
			pi.setDoc3(rs.getString("doc3"));
			pi.setDoc4(rs.getString("doc4"));
			pi.setIspushed(rs.getString("ispushed"));
			return pi;
		}
	}
}
