package com.tapsend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;



import com.tapsend.model.Beneficiary;


public class BeneficiaryDAOImpl {
	
    private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveBen(Beneficiary ben){
		String sql = "INSERT INTO beneficiary ("
				+ "ben_parentid,"
				+ "firstName,"
				+ "middleName,"
				+ "lastName,"
				+ "suffix,"
				+ "birthday,"
				+ "gender,"
				+ "civil_status,"
				+ "maiden_name,"
				+ "disabled,ispushed) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		
		int[] types ={
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER
		};
		Object[] params = {
				ben.getBen_parentid(),
				ben.getFirstName(),
				ben.getMiddleName(),
				ben.getLastName(),
				ben.getSuffix(),
				ben.getBirthday(),
				ben.getGender(),
				ben.getCivil_status(),
				ben.getMaiden_name(),
				ben.getDisabled(),
				ben.getIspushed()
		};
		
		System.out.println("parameters: ");
		for(Object o : params){
			System.out.println(o+", ");
		}
		int isSaved = jdbcTemplate.update(sql, params, types);	
		System.out.println("Save status:"+isSaved);
		return isSaved;
		
	}

public Collection viewBen(Beneficiary ben){
	Collection s=null;
	String sql = "SELECT * FROM beneficiary ";
	int iterator=1;
	try{
		if(!ben.getBen_parentid().isEmpty()||!ben.getBen_parentid().equals(null)||!ben.getBen_parentid().equals("")){
			sql+=" WHERE ben_parentid=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getFirstName().isEmpty()||!ben.getFirstName().equals(null)||!ben.getFirstName().equals("")){

			sql+=" WHERE firstName=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getMiddleName().isEmpty()||!ben.getMiddleName().equals(null)||!ben.getMiddleName().equals("")){

			sql+=" WHERE middleName=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ben.getLastName().isEmpty()||!ben.getLastName().equals(null)||!ben.getLastName().equals("")){

			sql+=" WHERE lastName=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getSuffix().isEmpty()||!ben.getSuffix().equals(null)||!ben.getSuffix().equals("")){

			sql+=" WHERE suffix=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getBirthday().isEmpty()||!ben.getBirthday().equals(null)||!ben.getBirthday().equals("")){

			sql+=" WHERE birthday=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getGender().isEmpty()||!ben.getGender().equals(null)||!ben.getGender().equals("")){

			sql+=" WHERE gender=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getCivil_status().isEmpty()||!ben.getCivil_status().equals(null)||!ben.getCivil_status().equals("")){

			sql+=" WHERE civil_status=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getMaiden_name().isEmpty()||!ben.getMaiden_name().equals(null)||!ben.getMaiden_name().equals("")){

			sql+=" WHERE maiden_name=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getDisabled().isEmpty()||!ben.getDisabled().equals(null)||!ben.getDisabled().equals("")){

			sql+=" WHERE disabled=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getIspushed().isEmpty()||!ben.getIspushed().equals(null)||!ben.getIspushed().equals("")){

			sql+=" WHERE ispushed=? ";
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	Object[] params=new Object[iterator];
	params[0]=1;
	iterator = 1;
	
	try{
		if(!ben.getBen_parentid().isEmpty()||!ben.getBen_parentid().equals(null)||!ben.getBen_parentid().equals("")){
	
			params[iterator]=ben.getBen_parentid();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getFirstName().isEmpty()||!ben.getFirstName().equals(null)||!ben.getFirstName().equals("")){

			params[iterator]=ben.getFirstName();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getMiddleName().isEmpty()||!ben.getMiddleName().equals(null)||!ben.getMiddleName().equals("")){

			params[iterator]=ben.getMiddleName();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ben.getLastName().isEmpty()||!ben.getLastName().equals(null)||!ben.getLastName().equals("")){

			params[iterator]=ben.getLastName();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getSuffix().isEmpty()||!ben.getSuffix().equals(null)||!ben.getSuffix().equals("")){

			params[iterator]=ben.getSuffix();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getBirthday().isEmpty()||!ben.getBirthday().equals(null)||!ben.getBirthday().equals("")){

			params[iterator]=ben.getBirthday();
			iterator++;
		}
	}catch(NullPointerException e){
		
	}
	try{
		if(!ben.getGender().isEmpty()||!ben.getGender().equals(null)||!ben.getGender().equals("")){

			params[iterator]=ben.getGender();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	
	try{
		if(!ben.getCivil_status().isEmpty()||!ben.getCivil_status().equals(null)||!ben.getCivil_status().equals("")){

			params[iterator]=ben.getCivil_status();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getMaiden_name().isEmpty()||!ben.getMaiden_name().equals(null)||!ben.getMaiden_name().equals("")){

			params[iterator]=ben.getMaiden_name();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getDisabled().isEmpty()||!ben.getDisabled().equals(null)||!ben.getDisabled().equals("")){

			params[iterator]=ben.getDisabled();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	try{
		if(!ben.getIspushed().isEmpty()||!ben.getIspushed().equals(null)||!ben.getIspushed().equals("")){

			params[iterator]=ben.getIspushed();
			iterator++;
		}
	}catch(NullPointerException e){
	
	}
	System.out.println("SQL: "+sql);
	System.out.println("params: ");
	
	for(Object o:params){
		System.out.println(o+", ");
	}
	
	s = jdbcTemplate.query(sql,params, new BenMapper());
	return s;
	}

//	public int checkIfExists(Beneficiary ben){
//		String sql = "SELECT * FROM beneficiary WHERE ben_parentid=? ";
//		Object[] params= {ben.getBen_parentid()};
//		int isExisting = 0;
//		System.out.println(ben.getBen_parentid()+" ");
//		SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql,params,new int[] {Types.VARCHAR});
//		
//		if(rs.next()){
//			do{
//				isExisting++;
//			}while(rs.next());
//		}
//		return isExisting;
//	}

	private static final class BenMapper implements RowMapper<Beneficiary>{
		public Beneficiary mapRow(ResultSet rs, int rowNum)throws SQLException{
			Beneficiary ben = new Beneficiary();
			
			ben.setBen_parentid(rs.getString("ben_parentid"));
			ben.setFirstName(rs.getString("firstName"));
			ben.setMiddleName(rs.getString("middleName"));
			ben.setLastName(rs.getString("lastName"));
			ben.setSuffix(rs.getString("suffix"));
			ben.setBirthday(rs.getString("birthday"));
			ben.setGender(rs.getString("gender"));
			ben.setCivil_status(rs.getString("civil_status"));
			ben.setMaiden_name(rs.getString("maiden_name"));
			ben.setDisabled(rs.getString("disabled"));
			ben.setIspushed(rs.getString("ispushed"));
			return ben;
		}
}

}
