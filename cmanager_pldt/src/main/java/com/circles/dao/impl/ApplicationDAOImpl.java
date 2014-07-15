package com.circles.dao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.controller.ManagerController;
import com.circles.model.AddressDetails;
import com.circles.model.Application;
import com.circles.model.ApplicationJSON;
import com.circles.model.Attachments;
import com.circles.model.CompanyDetails;
import com.circles.model.Details;
import com.circles.model.Details.packages;
import com.circles.model.Phone;
import com.circles.model.Plan;
import com.circles.model.SpouseDetails;
import com.google.gson.Gson;

public class ApplicationDAOImpl {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Application application){
		
		String sql = "INSERT INTO application(application_id," +
				"product_id," +
				"deviceid," +
				"username," +
				"details," +
				"application_type," +
				"edited_by," +
				"ispushed," +
				"version," +
				"created," +
				"updated," +
				"status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		logger.info("inserting "+ application.getApplication_id());
		int iSave = jdbcTemplate.update(sql.toString(), new Object[]{
			application.getApplication_id(),application.getProduct_id(),application.getDeviceid(),
			application.getUser_id(),application.getDetails(),application.getApplication_type(),
			application.getEditedBy(),application.getIspushed(),application.getVersion(),application.getCreated(),
			application.getUpdate(),application.getStatus()
		});
		
		return iSave;
		
	}
	public int update(Application app){
		StringBuilder str = new StringBuilder();
		
		str.append("UPDATE application SET product_id = ?, deviceid = ?,username = ?, ");
		str.append("details = ?, application_type = ?, edited_by = ?, ");
		str.append(" ispushed = ?, version = ?, created =?,");
		str.append("updated = ?, status = ? WHERE application_id = ?");
		
		int iSave = jdbcTemplate.update(str.toString(),new Object[]{
			app.getProduct_id(),app.getDeviceid(),app.getUser_id(),
			app.getDetails(),app.getApplication_type(),app.getEditedBy(),
			app.getIspushed(),app.getVersion(),app.getCreated(),app.getUpdate(),
			app.getStatus(),app.getApplication_id()
		});
		
		return iSave;
	}
	
	public int update2(Application app){
		StringBuilder str = new StringBuilder();
		
		str.append("UPDATE application SET ");
		str.append(" ispushed = ?, version = ?, created=?, ");
		str.append("updated = ?, status = ? WHERE application_id = ?");
		
		int iSave = jdbcTemplate.update(str.toString(),new Object[]{
			
			
			app.getIspushed(),app.getVersion(),app.getCreated(),app.getUpdate(),
			app.getStatus(),app.getApplication_id()
		});
		
		return iSave;
	}
	public int delete(Application application){
		String sql = "DELETE FROM application WHERE application_id=?";
		Object[] params = {application.getApplication_id()};
		int isDeleted = jdbcTemplate.update(sql, params);
		return isDeleted;
	}
	
	@SuppressWarnings("deprecation")
	public int checkIfExists(Application application){
		String sql = "SELECT * FROM application WHERE application_id = ?";
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString(),new Object[]{application.getApplication_id()});
		
		if(rs.next()){
			return 1;
		}
		else{
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	public Collection findAllApplication(){
		return jdbcTemplate.query("SELECT * FROM application", new ApplicationMapper3());
	}
	
	@SuppressWarnings("unchecked")
	public Collection findAllApplication(String username)
	
	{
	
		return jdbcTemplate.query("SELECT application_id,product_id,deviceid,username,details,application_type,edited_by,ispushed,version,created,updated,status FROM application where username = ?",new Object[] {username}, new ApplicationJSONMapper());
	}
	
	
	
	
	public Collection findApplication2(Application application) throws SQLException{
		return jdbcTemplate.query("SELECT a.*, b.*, c.* FROM application a INNER JOIN product b ON a.product_id = b.product_id INNER JOIN device c ON a.deviceid = c.deviceid WHERE a.application_id = ?",
				new Object[] {application.getApplication_id()}, new ApplicationMapper5());
		
	}
	
	public Collection findApplication3(Application application) throws SQLException{
		System.out.println("appid:"+ application.getApplication_id());
		String sql = "SELECT a.*,c.* FROM application a INNER JOIN gcm c ON a.username = c.username WHERE a.application_id = ?";
		return jdbcTemplate.query(sql.toString(),new Object[] {application.getApplication_id()}, new ApplicationMapper5());
		
	}
	
	private static final class ApplicationMapper implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setProduct_id(rs.getString("product_id"));
	        application.setDetails(rs.getString("details"));
	        application.setIspushed(rs.getString("ispushed"));
	        application.setEditedBy(rs.getString("edited_by"));	       
	        application.setCreated(rs.getString("created"));
	        application.setUpdate(rs.getString("updated"));
	        application.setVersion(rs.getString("version"));
	        application.setApplication_type(rs.getString("application_type"));
	        application.setUser_id(rs.getString("username"));
	        return application;
	        
	    }
	}
	
	private static final class ApplicationJSONMapper implements RowMapper<ApplicationJSON> {

	    public ApplicationJSON mapRow(ResultSet rs, int rowNum) throws SQLException {
	        ApplicationJSON application = new ApplicationJSON();
	              
	        Gson gson = new Gson();
	        
	        Hashtable<String,Object>  details = gson.fromJson(rs.getString("details"),Hashtable.class);
	        
	        application.setId(rs.getString("application_id"));
	        //application.setDetails(details);
	        application.setIspushed(rs.getInt("ispushed"));	       
	        application.setUser_id(rs.getString("username"));
	        application.setVersion(Integer.parseInt(rs.getString("version")));
	        application.setApplicationType(rs.getString("application_type"));
	        application.setStatus(rs.getInt("status"));
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper5 implements RowMapper<Application> {

		private int ctr;
	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	

	        Application app = new Application();
	              
	        app.setCount(++ctr);
	        app.setApplication_id(rs.getString("application_id"));
	        app.setProduct_id(rs.getString("product_id"));
	        app.setStatus(rs.getString("status"));	        
	        app.setIspushed(rs.getString("ispushed"));
	        app.setEditedBy(rs.getString("edited_by"));	       
	        app.setCreated(rs.getString("created"));
	        app.setUpdate(rs.getString("updated"));
	        app.setVersion(rs.getString("version"));
	        app.setApplication_type(rs.getString("application_type"));
	        app.setUser_id(rs.getString("username"));
	        app.setDetails(rs.getString("details"));	        
	        app.setAgent_no(rs.getString("mobile_no"));
	        

	        if(app.getApplication_type().equalsIgnoreCase("Residential")){
	            
		        Gson gson2 = new Gson();
		        
		        System.out.println("AppMapper5: "+app.getDetails());
		        
	        	Details details = gson2.fromJson(app.getDetails(), Details.class);
		        app.setFirstName(details.getFirstname());
		        app.setLastName(details.getLastname());
		        app.setTitle(details.getTitle());
		        app.setMothers_maidenname(details.getMothers_maiden_name());
		        
		        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		        Double bday = Double.parseDouble(details.getBirthday());
		        app.setBirthday(df.format(new Date(bday.longValue()))+"");
		        app.setCivil_status(details.getCivilstatus());
		        app.setContact_number(details.getTelephone());
		        app.setMobile(details.getMobile());
		        app.setMothers_maidenname(details.getMothers_maiden_name());
		        app.setEmail(details.getEmail());
		        app.setProduct_name(details.getPackages().getProduct_name());
		        app.setAddBrgy(details.getAddress().getBarangay());
		        app.setAddCity(details.getAddress().getCity());
		        app.setZipCode(details.getAddress().getPostalcode());
		        app.setAddRegion(details.getAddress().getRegion());
		        app.setAddLine1(details.getAddress().getAddress1());
		        app.setSpouse_firstName(details.getSpousedetails().getFirstname());
		        app.setSpouse_lastName(details.getSpousedetails().getFirstname());
		        app.setSpouse_title(details.getSpousedetails().getTitle());
		        app.setSpouse_addLine1(details.getSpousedetails().getSpouseaddress().getAddress1());
		        app.setSpouse_brgy(details.getSpousedetails().getSpouseaddress().getBarangay());
		        app.setSpouse_city(details.getSpousedetails().getSpouseaddress().getCity());
		        app.setSpouse_zipcode(details.getSpousedetails().getSpouseaddress().getPostalcode());
		        app.setSpouse_region(details.getSpousedetails().getSpouseaddress().getRegion());
		        app.setLat(details.getAddress().getLocation().getLat());
		        app.setLng(details.getAddress().getLocation().getLng());
		        
		        System.out.println("lat:"+ details.getAddress().getLocation().getLat());
		        System.out.println("lng:"+ details.getAddress().getLocation().getLng());
		       
		        
		        app.setNumber(details.getAttachments().get(0).getNumber());
		        app.setAttachment_type(details.getAttachments().get(0).getAttachment_type());
		        
		        System.out.println("Number: "+app.getNumber());
		        System.out.println("attach type: "+app.getAttachment_type());
		        System.out.println("packages: "+details.getPackages().getProduct_name());
		        System.out.println("TITLE: "+details.getTitle());
		        System.out.println("Civil STATUS: "+details.getCivilstatus());
		        System.out.println("AppMapper5 firstname:"+app.getFirstName());
		        System.out.println("AppMapper5 lastname:"+app.getLastName());
		        
	        	
	        	
	        }else{
	            
	        	Gson gson2 = new Gson();
		        
		        System.out.println("AppMapper5: "+app.getDetails());
		        
	        	Details details = gson2.fromJson(app.getDetails(), Details.class);
		        app.setFirstName(details.getFirstname());
		        app.setLastName(details.getLastname());
		        app.setTitle(details.getTitle());
		        app.setMothers_maidenname(details.getMothers_maiden_name());
		        
		        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		        Double bday = Double.parseDouble(details.getBirthday());
		        app.setBirthday(df.format(new Date(bday.longValue()))+"");
		        app.setCivil_status(details.getCivilstatus());
		        app.setContact_number(details.getTelephone());
		        app.setMobile(details.getMobile());
		        app.setMothers_maidenname(details.getMothers_maiden_name());
		        app.setEmail(details.getEmail());
		        app.setProduct_name(details.getPackages().getProduct_name());
		        app.setAddBrgy(details.getAddress().getBarangay());
		        app.setAddCity(details.getAddress().getCity());
		        app.setZipCode(details.getAddress().getPostalcode());
		        app.setAddRegion(details.getAddress().getRegion());
		        app.setAddLine1(details.getAddress().getAddress1());
		        app.setSpouse_firstName(details.getSpousedetails().getFirstname());
		        app.setSpouse_lastName(details.getSpousedetails().getFirstname());
		        app.setSpouse_title(details.getSpousedetails().getTitle());
		        app.setSpouse_addLine1(details.getSpousedetails().getSpouseaddress().getAddress1());
		        app.setSpouse_brgy(details.getSpousedetails().getSpouseaddress().getBarangay());
		        app.setSpouse_city(details.getSpousedetails().getSpouseaddress().getCity());
		        app.setSpouse_zipcode(details.getSpousedetails().getSpouseaddress().getPostalcode());
		        app.setSpouse_region(details.getSpousedetails().getSpouseaddress().getRegion());
		        app.setLat(details.getAddress().getLocation().getLat());
		        app.setLng(details.getAddress().getLocation().getLng());
		        
		        System.out.println("long:"+ details.getAddress().getLocation().getLat());
		        System.out.println("lat:"+ details.getAddress().getLocation().getLat());
		       
		        
		        app.setNumber(details.getAttachments().get(0).getNumber());
		        app.setAttachment_type(details.getAttachments().get(0).getAttachment_type());
		        
		        System.out.println("Number: "+app.getNumber());
		        System.out.println("attach type: "+app.getAttachment_type());
		        System.out.println("packages: "+details.getPackages().getProduct_name());
		        System.out.println("TITLE: "+details.getTitle());
		        System.out.println("Civil STATUS: "+details.getCivilstatus());
		        System.out.println("AppMapper5 firstname:"+app.getFirstName());
		        System.out.println("AppMapper5 lastname:"+app.getLastName());
	        }
	        return app;
	        
	    }
	}
	
	private static final class ApplicationMapper2 implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        Phone phone = new Phone();
	        Plan plan = new Plan();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setPlan_code((rs.getString("plan_name")));
	        application.setRef_no((rs.getString("ref_no")));
	        application.setPhone_id((rs.getString("phone_model")));
	        application.setStatus(rs.getString("status"));
	        application.setFirstName(rs.getString("firstName"));
	        application.setLastName(rs.getString("lastName"));
	        application.setCreated(rs.getString("created"));
	        
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper3 implements RowMapper<Application> {

	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application application = new Application();
	        
	        application.setApplication_id(rs.getString("application_id"));
	        application.setApplication_type(rs.getString("application_type"));
	        application.setUser_id(rs.getString("username"));
	        application.setStatus(rs.getString("status"));
	        application.setCreated(rs.getString("created"));
	        
	        return application;
	        
	    }
	}
	
	private static final class ApplicationMapper4 implements RowMapper<Application> {
		private int ctr;
	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Application app = new Application();
	        
	        app.setCount(++ctr);
	        app.setApplication_id(rs.getString("application_id"));
	        app.setApplication_type(rs.getString("application_type"));
	        app.setCreated(rs.getString("created"));
	        app.setStatus(rs.getString("status"));
//	        app.setProduct_name(rs.getString("product_name"));
	        app.setDetails(rs.getString("details"));
	        Gson gson2 = new Gson();
	        
	        System.out.println(app.getDetails());
	        
        	Details details = gson2.fromJson(app.getDetails(), Details.class);
	        app.setFirstName(details.getFirstname());
	        app.setLastName(details.getLastname());
	        app.setProduct_name(details.getPackages().getProduct_name());
	        
	        System.out.println("firstname:"+app.getFirstName());
	        System.out.println("lastname:"+app.getLastName());
	        return app;
	        
	    }
	}
	
	
	public ArrayList<Application> ViewAppDetail(String id){
		System.out.println(id);
		String sql = "SELECT addLine1 FROM application WHERE  application_id = ?";
		String result = "-1";
		List<Map<String,Object>> rows  =  jdbcTemplate.queryForList(sql,id);
		//System.out.println(isExisting);

		
		ArrayList<Application> applist = new ArrayList<Application>();
		
		for(Map row:rows)
		{
		
			Application app = new Application();
			
			
			result = (String)(row.get("details"));
			app.setAddLine1(result);
			
			System.out.println("details: "+result);
			
//			Gson gson = new Gson();
//			
//			user = gson.fromJson(result,UserDetails.class);
//			
//			result = gson.toJson(user);
				
			applist.add(app);
		}
		
		return applist;

	}
	
	
	
	
	public Collection getApplication(Application application, String id){
		return jdbcTemplate.query("SELECT * FROM application WHERE application_id=?", new ApplicationMapper3(),id);
		
	}

	public Collection getReturnApplication(Application application){
		return jdbcTemplate.query("SELECT a.*,  c.* FROM application a  INNER JOIN product c ON a.product_id = c.product_id WHERE a.application_type IS NOT NULL AND a.status = ?" , new ApplicationMapper4(),3);
		
	}
	public Collection getEncodedApplication(Application application){
		return jdbcTemplate.query("SELECT a.*,  c.* FROM application a  INNER JOIN product c ON a.product_id = c.product_id WHERE a.application_type IS NOT NULL AND a.status = ?",new ApplicationMapper4(),1);
		
	}
	
	public Collection getOngoingApplication(Application application){
		return jdbcTemplate.query("SELECT a.*, c.* FROM application a  INNER JOIN product c ON a.product_id = c.product_id WHERE a.application_type IS NOT NULL AND a.status = ?", new ApplicationMapper4(),2);
		
	}
//	public Collection getRecentApplication(Application application){
//		return jdbcTemplate.query("SELECT a.*, c.* FROM application a INNER JOIN product c ON a.product_id = c.product_id where application_type is not null" , new ApplicationMapper5());
//			
//		
//	}
	public Collection getStatus(Application application){
		return jdbcTemplate.query("SELECT a.application_id, a.firstName, a.lastName, a.status, a.created, b.plan_name, c.phone_model FROM application a INNER JOIN product c ON a.product_id = c.product_id WHERE status =?", new ApplicationMapper2(),application.getStatus());
	}
	
	public Collection getRecentApplication(Application application){
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT * FROM application ");
		str.append("WHERE application_type is not null ");
		return jdbcTemplate.query(str.toString(),new ApplicationMapper4());
		
	}
}
