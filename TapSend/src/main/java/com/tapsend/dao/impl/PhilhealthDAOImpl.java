package com.tapsend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.tapsend.model.Philhealth;


public class PhilhealthDAOImpl {

    private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
public int savePhilhealth(Philhealth ph){
		
		String sql = "INSERT INTO philhealth ("
				+ "philhealth_id,"
				+ "id,"
				+ "user_id,"
				+ "pen,"
				+ "employer_name,"
				+ "employer_address,"
				+ "date_hired,"
				+ "country_based,"
				+ "foreign_address,"
				+ "duration_from,duration_to,member_type,family_income,doc1,doc2,doc3,doc4,ispushed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
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
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER
		};
		Object[] params = {
				ph.getPhilhealth_id(),
				ph.getId(),
				ph.getUser_id(),
				ph.getPen(),
				ph.getEmployer_name(),
				ph.getEmployer_address(),
				ph.getDate_hired(),
				ph.getCountry_based(),
				ph.getForeign_address(),
				ph.getDuration_from(),
				ph.getDuration_to(),
				ph.getMember_type(),
				ph.getFamily_income(),
				ph.getDoc1(),
				ph.getDoc2(),
				ph.getDoc3(),
				ph.getDoc4(),
				ph.getIspushed()
		};
		
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.println(o+", ");
		}
		int iphaved = jdbcTemplate.update(sql, params, types);	
		System.out.println("Save status:"+iphaved);
		return iphaved;
		
	}

public Collection viewPhilhealth(Philhealth ph){
	Collection s=null;
	String sql = "SELECT * FROM philhealth WHERE dvar=? ";
	int iterator=1;
	try{
		if(!ph.getPhilhealth_id().isEmpty()||!ph.getPhilhealth_id().equals(null)||!ph.getPhilhealth_id().equals("")){
			sql+=" AND Philhealth_id=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getId().isEmpty()||!ph.getId().equals(null)||!ph.getId().equals("")){

			sql+=" AND id=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getUser_id().isEmpty()||!ph.getUser_id().equals(null)||!ph.getUser_id().equals("")){

			sql+=" AND user_id=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ph.getPen().isEmpty()||!ph.getPen().equals(null)||!ph.getPen().equals("")){

			sql+=" AND pen=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getEmployer_name().isEmpty()||!ph.getEmployer_name().equals(null)||!ph.getEmployer_name().equals("")){

			sql+=" AND employer_name=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getEmployer_address().isEmpty()||!ph.getEmployer_address().equals(null)||!ph.getEmployer_address().equals("")){

			sql+=" AND employer_address=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDate_hired().isEmpty()||!ph.getDate_hired().equals(null)||!ph.getDate_hired().equals("")){

			sql+=" AND date_hired=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getCountry_based().isEmpty()||!ph.getCountry_based().equals(null)||!ph.getCountry_based().equals("")){

			sql+=" AND country_based=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getForeign_address().isEmpty()||!ph.getForeign_address().equals(null)||!ph.getForeign_address().equals("")){

			sql+=" AND foreign_address=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDuration_from().isEmpty()||!ph.getDuration_from().equals(null)||!ph.getDuration_from().equals("")){

			sql+=" AND duration_from=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDuration_to().isEmpty()||!ph.getDuration_to().equals(null)||!ph.getDuration_to().equals("")){

			sql+=" AND duration_to=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getMember_type().isEmpty()||!ph.getMember_type().equals(null)||!ph.getMember_type().equals("")){

			sql+=" AND member_type=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getFamily_income().isEmpty()||!ph.getFamily_income().equals(null)||!ph.getFamily_income().equals("")){

			sql+=" AND family_income=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ph.getDoc1().isEmpty()||!ph.getDoc1().equals(null)||!ph.getDoc1().equals("")){
		
			sql+=" AND doc1=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc2().isEmpty()||!ph.getDoc2().equals(null)||!ph.getDoc2().equals("")){
		
			sql+=" AND doc2=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc3().isEmpty()||!ph.getDoc3().equals(null)||!ph.getDoc3().equals("")){
		
			sql+=" AND doc3=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc4().isEmpty()||!ph.getDoc4().equals(null)||!ph.getDoc4().equals("")){
		
			sql+=" AND doc4=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getIspushed().isEmpty()||!ph.getIspushed().equals(null)||!ph.getIspushed().equals("")){
		
			sql+=" AND ispushed=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	Object[] params=new Object[iterator];
	params[0]=1;
	iterator = 1;
	
	try{
		if(!ph.getPhilhealth_id().isEmpty()||!ph.getPhilhealth_id().equals(null)||!ph.getPhilhealth_id().equals("")){
	
			params[iterator]=ph.getPhilhealth_id();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getId().isEmpty()||!ph.getId().equals(null)||!ph.getId().equals("")){

			params[iterator]=ph.getId();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getUser_id().isEmpty()||!ph.getUser_id().equals(null)||!ph.getUser_id().equals("")){

			params[iterator]=ph.getUser_id();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ph.getPen().isEmpty()||!ph.getPen().equals(null)||!ph.getPen().equals("")){

			params[iterator]=ph.getPen();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getEmployer_name().isEmpty()||!ph.getEmployer_name().equals(null)||!ph.getEmployer_name().equals("")){

			params[iterator]=ph.getEmployer_name();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getEmployer_address().isEmpty()||!ph.getEmployer_address().equals(null)||!ph.getEmployer_address().equals("")){

			params[iterator]=ph.getEmployer_address();
			iterator++;
		}
	}catch(NullPointerException e){
		
	}
	try{
		if(!ph.getDate_hired().isEmpty()||!ph.getDate_hired().equals(null)||!ph.getDate_hired().equals("")){

			params[iterator]=ph.getDate_hired();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ph.getCountry_based().isEmpty()||!ph.getCountry_based().equals(null)||!ph.getCountry_based().equals("")){

			params[iterator]=ph.getCountry_based();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getForeign_address().isEmpty()||!ph.getForeign_address().equals(null)||!ph.getForeign_address().equals("")){

			params[iterator]=ph.getForeign_address();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDuration_from().isEmpty()||!ph.getDuration_from().equals(null)||!ph.getDuration_from().equals("")){

			params[iterator]=ph.getDuration_from();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDuration_to().isEmpty()||!ph.getDuration_to().equals(null)||!ph.getDuration_to().equals("")){

			params[iterator]=ph.getDuration_to();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getMember_type().isEmpty()||!ph.getMember_type().equals(null)||!ph.getMember_type().equals("")){

			params[iterator]=ph.getMember_type();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getFamily_income().isEmpty()||!ph.getFamily_income().equals(null)||!ph.getFamily_income().equals("")){

			params[iterator]=ph.getFamily_income();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc1().isEmpty()||!ph.getDoc1().equals(null)||!ph.getDoc1().equals("")){

			params[iterator]=ph.getDoc1();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc2().isEmpty()||!ph.getDoc2().equals(null)||!ph.getDoc2().equals("")){

			params[iterator]=ph.getDoc2();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc3().isEmpty()||!ph.getDoc3().equals(null)||!ph.getDoc3().equals("")){

			params[iterator]=ph.getDoc3();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getDoc4().isEmpty()||!ph.getDoc4().equals(null)||!ph.getDoc4().equals("")){

			params[iterator]=ph.getDoc4();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ph.getIspushed().isEmpty()||!ph.getIspushed().equals(null)||!ph.getIspushed().equals("")){

			params[iterator]=ph.getIspushed();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	System.out.println("SQL: "+sql);
	System.out.println("params: ");
	
	for(Object o:params){
		System.out.println(o+", ");
	}
	
	s = jdbcTemplate.query(sql,params, new PhilhealthMapper());
	return s;
	}

	public int checkIfExists(Philhealth ph){
		String sql = "SELECT * FROM philhealth WHERE user_id=? ";
		Object[] params= {ph.getUser_id()};
		int isExisting = 0;
		System.out.println(ph.getUser_id()+" ");
		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
		
		if(rs.next()){
			do{
				isExisting++;
			}while(rs.next());
		}
		return isExisting;
	}

	private static final class PhilhealthMapper implements RowMapper<Philhealth>{
		public Philhealth mapRow(ResultSet rs, int rowNum)throws SQLException{
			Philhealth ph = new Philhealth();
			ph.setPhilhealth_id(rs.getString("philhealth_id"));
			ph.setId(rs.getString("id"));
			ph.setUser_id(rs.getString("user_id"));
			ph.setPen(rs.getString("pen"));
			ph.setEmployer_name(rs.getString("employer_name"));
			ph.setEmployer_address(rs.getString("employer_address"));
			ph.setDate_hired(rs.getString("date_hired"));
			ph.setCountry_based(rs.getString("country_based"));
			ph.setForeign_address(rs.getString("foreign_address"));
			ph.setDuration_from(rs.getString("duration_from"));
			ph.setDuration_to(rs.getString("duration_to"));
			ph.setMember_type(rs.getString("member_type"));	
			ph.setFamily_income(rs.getString("family_income"));
			ph.setDoc1(rs.getString("doc1"));
			ph.setDoc2(rs.getString("doc2"));
			ph.setDoc3(rs.getString("doc3"));
			ph.setDoc4(rs.getString("doc4"));
			ph.setIspushed(rs.getString("ispushed"));
			return ph;
		}
}
}
