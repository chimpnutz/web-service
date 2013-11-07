package com.elp.dao.transactions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.elp.beans.UserBean;
import com.elp.model.CreditExtendedModel;
import com.elp.model.CreditHistoryModel;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.HolidayModel;
import com.elp.model.UserManagementModel;



public class UserManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(UserManagementDAO.class);
	
	public List<UserManagementModel> getUsers(HttpSession session)
	   {
			
			   ArrayList<UserManagementModel> userList= new ArrayList<UserManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.userid,a.name,a.username,a.email_address,a.mobile,a.status,b.rolename,c.companyname ");
//			   strSQL.append("from user a, roles b, company_tbl c where ");
//			   strSQL.append("a.roleid = b.roleid and a.CompanyID = c.companyid  and a.status =?");
			   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid,f.userid,f.name,f.username,f.email_address,f.mobile,f.status ,g.rolename ");
			   strSQL.append("FROM user f INNER JOIN  roles g ON f.roleid = g.roleid 		");
			   strSQL.append("INNER JOIN company_tbl a ON 	f.companyid = a.companyid ");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
			   strSQL.append("INNER JOIN company_tbl c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN company_tbl d ON a.companyid = d.companyid ");
			   strSQL.append("LEFT JOIN company_tbl e ON a.companyid = e.companyid  ");
			   strSQL.append("WHERE b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) OR a.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) and f.status = ?");
			   
			   userList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),session.getAttribute("companyname"),"active");
			   
				for (Map row : rows) {

					UserManagementModel user = new UserManagementModel();
					
					user.setUserid((Integer)(row.get("userid")));
					user.setName((String)(row.get("name")));
					user.setUsername((String)(row.get("username")));
					user.setEmail_address((String)(row.get("email_address")));
					user.setMobile((String)(row.get("mobile")));
					user.setStatus((String)(row.get("status")));
					user.setRolename((String)(row.get("rolename")));
					user.setCompanyname((String)(row.get("companyname")));


					userList.add(user);			
				}
			
				return userList;
				
	   }
	
	public List<UserManagementModel> getUsers(int userid)
	   {
			
			   ArrayList<UserManagementModel> userList= new ArrayList<UserManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.userid,a.name,a.username,a.email_address,a.mobile,a.status,b.rolename,c.companyname ");
			   strSQL.append("from user a, roles b, company_tbl c where ");
			   strSQL.append("a.roleid = b.roleid and a.CompanyID = c.companyid and a.userid = ?");
			   
			   
			   userList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),userid);
			   
				for (Map row : rows) {

					UserManagementModel user = new UserManagementModel();
					
					user.setUserid((Integer)(row.get("userid")));
					user.setName((String)(row.get("name")));
					user.setUsername((String)(row.get("username")));
					user.setEmail_address((String)(row.get("email_address")));
					user.setMobile((String)(row.get("mobile")));
					user.setStatus((String)(row.get("status")));
					user.setRolename((String)(row.get("rolename")));
					user.setCompanyname((String)(row.get("companyname")));


					userList.add(user);			
				}
			
				return userList;
				
	   }
	
	public List<UserManagementModel> searchUser(String type, String keyword)
	   {
			
			   ArrayList<UserManagementModel> userList= new ArrayList<UserManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.username,a.email_address,a.mobile,a,status,b.rolename,c.companyname ");
			   strSQL.append("from user a, roles b, company_tbl c where ");
			   strSQL.append("a.roleid = b.roleid and a.CompanyID = c.companyid ");
			   
			   
			   userList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			   
				for (Map row : rows) {

					UserManagementModel user = new UserManagementModel();
					
					user.setUsername((String)(row.get("username")));
					user.setEmail_address((String)(row.get("email_address")));
					user.setMobile((String)(row.get("mobile")));
					user.setStatus((String)(row.get("status")));
					user.setRolename((String)(row.get("rolename")));
					user.setCompanyname((String)(row.get("companyname")));


					userList.add(user);			
				}
			
				return userList;
				
	   }
	
	   
	   public int getroleid(String rolename)
	   {
		   int roleid = 0;
		   
		   String getName = "select roleid from roles where rolename = ?";
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getName, rolename);
		   
			for (Map row : rows) {

				roleid = (Integer)(row.get("roleid"));
				
				return roleid;
			}
			
			return roleid;
		   
		   
	   }
	   
	   public int getcompanyid(String companyname)
	   {
		   int companyid = 0;
		   
		   String getName = "select companyid from company_tbl where companyname = ?";
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getName, companyname);
		   
			for (Map row : rows) {

				companyid = (Integer)(row.get("companyid"));
				
				return companyid;
			}
			
			return companyid;
		   
		   
	   }
	   
		public Map getrolelist(){
			
			   StringBuilder strSQL = new StringBuilder();
			  				 
			   Map<String,String> roles = new LinkedHashMap<String,String>();
			   
			   strSQL.append("SELECT rolename from roles where active = 'Y' ");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					roles.put((String)(row.get("rolename")), (String)row.get("rolename"));
			
				}
				
				return roles;
				
		}
		
		public Map getcompanylist(){
			
			   StringBuilder strSQL = new StringBuilder();
			  				 
			   Map<String,String> company = new LinkedHashMap<String,String>();
			   
			   strSQL.append("SELECT companyname from company_tbl");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					company.put((String)(row.get("companyname")), (String)row.get("companyname"));
			
				}
				
				return company;
				
		}
		
		public Map getUserNames(){
			
			   StringBuilder strSQL = new StringBuilder();
			  				 
			   Map<String,String> company = new LinkedHashMap<String,String>();
			   
			   strSQL.append("SELECT name from user");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					company.put((String)(row.get("name")), (String)row.get("name"));
			
				}
				
				return company;
				
		}
	   
	   
		public boolean addnewUser(HttpSession session,UserManagementModel model){
			

			   StringBuilder strSQL = new StringBuilder();
			   
			   Date sysDate = new Date();

			   
			   strSQL.append("INSERT INTO user  ");
			   strSQL.append("(name,username,password,status,email_address,mobile,roleid,companyid,createdby) ");
			   strSQL.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
			   
			   
			   try{
				   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						model.getName(),model.getUsername(),model.getPassword(),model.getStatus(),
						model.getEmail_address(),model.getMobile(), model.getRoleid(),
						this.getcompanyid(model.getCompanyname()),session.getAttribute("username")
					});

					
					if(row>0){
						return true;
					}
						
			
			   }catch(DataAccessException ex){
		            ex.printStackTrace();
		            return false;
		        }
			   
			return false;

			
		}
		
		public boolean updateUser(HttpSession session,UserManagementModel model){
			

			   StringBuilder strSQL = new StringBuilder();
			   			   
			   strSQL.append("update user set name = ?,username = ?,password = ?,status = ?,email_address = ?, mobile = ?,roleid = ?, companyid = ?   "); 
			   strSQL.append("where  userid = ? " );

			   
			   try{
				   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						model.getName(),model.getUsername(),model.getPassword(),model.getStatus(),
						model.getEmail_address(),model.getMobile(), this.getroleid(model.getRolename()),
						this.getcompanyid(model.getCompanyname()),model.getUserid()
					});

					
					if(row>0){
						return true;
					}
						
			
			   }catch(DataAccessException ex){
		            ex.printStackTrace();
		            return false;
		        }
			   
			return false;

			
		}
		
		
		   public boolean deleteUser(int userid){

			   StringBuilder strSQL = new StringBuilder();
			      
			   strSQL.append("update user set status = ?   "); 
			   strSQL.append("where  userid = ? " );

			   
			   try{
				   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						"inactive",userid
					});

					
					if(row>0){
						return true;
					}
						
			
			   }catch(DataAccessException ex){
		            ex.printStackTrace();
		            return false;
		        }
			   
			   
			   
			return false;
			
			
		}
		   
			public List<UserManagementModel> getUsersByCompany(int cid)
			   {
					
					   ArrayList<UserManagementModel> userList= new ArrayList<UserManagementModel>();
						
					   StringBuilder strSQL = new StringBuilder()	;
					   
					   strSQL.append("SELECT a.username,b.rolename from user a, roles b ");
					   strSQL.append("where a.roleid = b.roleid and a.companyid = ? ");

					   
					   
					   userList.clear();
					 
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),cid);
					   
						for (Map row : rows) {

							UserManagementModel user = new UserManagementModel();
							
	
							user.setUsername((String)(row.get("username")));
							user.setRolename((String)(row.get("rolename")));


							userList.add(user);			
						}
					
						return userList;
						
			   }
			
			
			public List<UserManagementModel> getRoles(String cid){
				
				   StringBuilder strSQL = new StringBuilder();
				  						   
				   ArrayList<UserManagementModel> retailersList= new ArrayList<UserManagementModel>();
				   
				   strSQL.append("SELECT a.roleid,a.rolename,c.companyname FROM roles a, role_level_permissions b, company_tbl c   WHERE a.roleid = b.roleid AND b.levelid = c.levelid AND  c.companyname = ?");
					
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),cid);
					
					for (Map row : rows) {
						
						UserManagementModel retailer = new UserManagementModel();
						
						retailer.setRoleid((Integer)(row.get("roleid")));
						retailer.setRolename((String)(row.get("rolename")));
						retailer.setCompanyname((String)(row.get("companyname")));

						retailersList.add(retailer);		
				
					}
					
					return retailersList;
					
			}
			
			
			public List<UserManagementModel> getUsers(String uname)
			   {
						System.out.println(uname);
					   ArrayList<UserManagementModel> userList= new ArrayList<UserManagementModel>();
						
					   StringBuilder strSQL = new StringBuilder()	;
					   
					   strSQL.append("SELECT a.userid,a.name,a.username,a.email_address,a.mobile,a.status,b.rolename,c.companyname ");
					   strSQL.append("from user a, roles b, company_tbl c where ");
					   strSQL.append("a.roleid = b.roleid and a.CompanyID = c.companyid  and a.status = ? and a.name = ?");
					   
					   
					   userList.clear();
					 
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"active",uname);
					   
						for (Map row : rows) {

							UserManagementModel user = new UserManagementModel();
							
							user.setUserid((Integer)(row.get("userid")));
							user.setName((String)(row.get("name")));
							user.setUsername((String)(row.get("username")));
							user.setEmail_address((String)(row.get("email_address")));
							user.setMobile((String)(row.get("mobile")));
							user.setStatus((String)(row.get("status")));
							user.setRolename((String)(row.get("rolename")));
							user.setCompanyname((String)(row.get("companyname")));


							userList.add(user);			
						}
					
						return userList;
						
			   }
	

			public List<HolidayModel> getHolidays()
			   {
					
					   ArrayList<HolidayModel> holidayList= new ArrayList<HolidayModel>();
						
					   StringBuilder strSQL = new StringBuilder()	;
					   
					   strSQL.append("SELECT id,DATE_FORMAT(holiday_from,'%d %b %Y') as hol_from, DATE_FORMAT(holiday_to,'%d %b %Y') as hol_to from holiday_tbl where active = 'y' ");
					  
					   
					   
					   holidayList.clear();
					 
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
					   
						for (Map row : rows) {

							HolidayModel holiday = new HolidayModel();
							
							holiday.setId((Integer)(row.get("id")));
							holiday.setHoliday_from((String)(row.get("hol_from")));
							holiday.setHoliday_to((String)(row.get("hol_to")));

							holidayList.add(holiday);			
						}
					
						return holidayList;
						
			   }
			
			public int addHoliday(HttpSession session,HolidayModel model){
				

				   StringBuilder strSQL = new StringBuilder();
				   
				   strSQL.append("INSERT INTO holiday_tbl  ");
				   strSQL.append("(holiday_from,holiday_to) ");
				   strSQL.append(" VALUES (DATE_FORMAT(?,'%Y-%m-%d 00:00:00'),DATE_FORMAT(?,'%Y-%m-%d 00:00:00'))");
				   

			       SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			       
				try {
					 Date Start = sdf.parse(model.getHoliday_from()+" 00:00:00");
					 Date end = sdf.parse(model.getHoliday_to()+" 00:00:00");
					 
	
					 
					   
					   try{
						   
							int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
								Start,end
							});

							
							if(row>0){
								return 0;
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return 1;
				        }
					   
					return 1;
					 
					 
					 
					 
					 
					 
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			       
				return 1;
			}
			
			
			
			   public boolean deleteholiday(int id){

				   StringBuilder strSQL = new StringBuilder();
				      
				   strSQL.append("update holiday_tbl set active = ?   "); 
				   strSQL.append("where  id = ? " );

				   
				   try{
					   
						int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
							"N",id
						});

						
						if(row>0){
							return true;
						}
							
				
				   }catch(DataAccessException ex){
			            ex.printStackTrace();
			            return false;
			        }
				   
				   
				   
				return false;
				
				
			}
}
