package com.elp.dao.transactions;

import java.math.BigDecimal;
import java.text.DateFormat;
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
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.UserManagementModel;



public class NetworkManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(NetworkManagementDAO.class);
	
	public List<NetworkManagementModel> getCompany()
	   {
			
			   ArrayList<NetworkManagementModel> networkList= new ArrayList<NetworkManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.companyid,a.parentcompanyid,a.companyname,a.companyaddress,a.contactperson,a.status,b.levelname  ");
			   strSQL.append("FROM company_tbl a, level_tbl b WHERE  ");
			   strSQL.append("a.levelid = b.levelid and status = ? ");
			   
			   
			   networkList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"active");
			   
				for (Map row : rows) {

					NetworkManagementModel network = new NetworkManagementModel();
					
					network.setCompanyid((Integer)(row.get("companyid")));
					network.setCompanyname((String)(row.get("companyname")));
					network.setCompanyaddress((String)(row.get("companyaddress")));
					network.setContactperson((String)(row.get("contactperson")));
					network.setRetailersim((String)(row.get("mobile")));
					network.setStatus((String)(row.get("status")));
					network.setLevel((String)(row.get("levelname")));
					
					int companyid = (Integer)(row.get("parentcompanyid"));
					
					if(companyid==0){
						
						network.setParentcompany("None");
						
					}else
					{
						
						String getparentcompany = "select companyname from company_tbl where companyid = ?";
						
						List<Map<String,Object>> rows2 = getJdbcTemplate().queryForList(getparentcompany,companyid);
						
						for (Map row2 : rows2) 
						{
							
							network.setParentcompany((String)(row2.get("companyname")));
							
							
						}
						
					}
					
					networkList.add(network);			
				}
			
				return networkList;
				
	   }
	
	
	public List<NetworkManagementModel> getCompany(HttpSession session)
	   {
		
		
	    String rolename = session.getAttribute("rolename")+"";	
	    
	   	  ArrayList<NetworkManagementModel> networkList= new ArrayList<NetworkManagementModel>();
		
	   	if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
		{
	   		

			
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   strSQL.append("SELECT a.companyid,a.parentcompanyid,a.companyname,a.companyaddress,a.contactperson,a.status,b.levelname  ");
		   strSQL.append("FROM company_tbl a, level_tbl b WHERE  ");
		   strSQL.append("a.levelid = b.levelid and status = ? ");
		   
		   
		   networkList.clear();
		 
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"active");
		   
			for (Map row : rows) {

				NetworkManagementModel network = new NetworkManagementModel();
				
				network.setCompanyid((Integer)(row.get("companyid")));
				network.setCompanyname((String)(row.get("companyname")));
				network.setCompanyaddress((String)(row.get("companyaddress")));
				network.setContactperson((String)(row.get("contactperson")));
				network.setRetailersim((String)(row.get("mobile")));
				network.setStatus((String)(row.get("status")));
				network.setLevel((String)(row.get("levelname")));
				
				int companyid = (Integer)(row.get("parentcompanyid"));
				
				if(companyid==0){
					
					network.setParentcompany("None");
					
				}else
				{
					
					String getparentcompany = "select companyname from company_tbl where companyid = ?";
					
					List<Map<String,Object>> rows2 = getJdbcTemplate().queryForList(getparentcompany,companyid);
					
					for (Map row2 : rows2) 
					{
						
						network.setParentcompany((String)(row2.get("companyname")));
						
						
					}
					
				}
				
				networkList.add(network);			
			}
	   		
	   		
	   		
		} 	
	   	
	   	
	   	else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		{

			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid FROM company_tbl a		");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" INNER JOIN company_tbl c ON a.companyid = c.companyid  ");
			   strSQL.append(" LEFT JOIN company_tbl d ON a.companyid = d.companyid  ");
			   strSQL.append("LEFT JOIN company_tbl e ON a.companyid = e.companyid  ");
			   strSQL.append(" WHERE b.parentcompanyid = (select companyid from company_tbl where companyname = ?) OR a.parentcompanyid = (select companyid from company_tbl where companyname = ?)");
			   
			   
			   networkList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),session.getAttribute("companyname"));
			   
				for (Map row : rows) {

					NetworkManagementModel network = new NetworkManagementModel();
					
					network.setCompanyid((Integer)(row.get("companyid")));
					network.setCompanyname((String)(row.get("companyname")));
					network.setCompanyaddress((String)(row.get("companyaddress")));
					network.setContactperson((String)(row.get("contactperson")));
					network.setRetailersim((String)(row.get("mobile")));
					network.setStatus((String)(row.get("status")));
					network.setLevel((String)(row.get("levelname")));
					
					int companyid = (Integer)(row.get("parentcompanyid"));
					
					if(companyid==0){
						
						network.setParentcompany("None");
						
					}else
					{
						
						String getparentcompany = "select companyname from company_tbl where companyid = ?";
						
						List<Map<String,Object>> rows2 = getJdbcTemplate().queryForList(getparentcompany,companyid);
						
						for (Map row2 : rows2) 
						{
							
							network.setParentcompany((String)(row2.get("companyname")));
							
							
						}
						
					}
					
					networkList.add(network);			
				}
		   		
			
			
		}
			 
			
				return networkList;
				
	   }
	
	
	public List<NetworkManagementModel> getCompany(int cid)
	   {
			
			   ArrayList<NetworkManagementModel> networkList= new ArrayList<NetworkManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.companyid,a.companyid,a.parentcompanyid,a.companyname,a.companyaddress,a.contactperson,a.status,b.levelname,a.mobile,a.retailersim  ");
			   strSQL.append("FROM company_tbl a, level_tbl b WHERE  ");
			   strSQL.append("a.levelid = b.levelid and a.companyid = ? ");
			   
			   
			   networkList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),cid);
			   
				for (Map row : rows) {

					NetworkManagementModel network = new NetworkManagementModel();
					
					network.setCompanyid((Integer)(row.get("companyid")));
					network.setCompanyname((String)(row.get("companyname")));
					network.setCompanyaddress((String)(row.get("companyaddress")));
					network.setContactperson((String)(row.get("contactperson")));
					network.setRetailersim((String)(row.get("retailersim")));
					network.setMobile((String)(row.get("mobile")));
					network.setStatus((String)(row.get("status")));
					network.setLevel((String)(row.get("levelname")));
					
					int companyid = (Integer)(row.get("parentcompanyid"));
					
					if(companyid==0){
						
						network.setParentcompany("None");
						
					}else
					{
						
						String getparentcompany = "select companyid,companyname from company_tbl where companyid = ?";
						
						List<Map<String,Object>> rows2 = getJdbcTemplate().queryForList(getparentcompany,cid);
						
						for (Map row2 : rows2) 
						{
							
							network.setParentcompany((String)(row2.get("companyname")));
							
							
						}
						
					}
					
					networkList.add(network);			
				}
			
				return networkList;
				
	   }
	
	
	public boolean addCompany(HttpSession session,NetworkManagementModel model){
		
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
	   	   {
	   			
	   	   
			   StringBuilder strSQL = new StringBuilder();
	   		   
			   strSQL.append("INSERT INTO company_tbl  ");
			   strSQL.append("(levelid,parentcompanyid,companyname,companyaddress,contactperson,datecreated,createdby,status,retailersim,mobile) ");
			   strSQL.append(" VALUES (?,?,?,?,?,current_timestamp,?,?,?,?) ");
			   
			   
			   try{
				   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					2,1,model.getCompanyname(),model.getCompanyaddress(),
					model.getContactperson(),session.getAttribute("username"),model.getStatus(),model.getRetailersim(),model.getMobile()
					});

					
					if(row>0){
						return true;
					}
						
			
			   }catch(DataAccessException ex){
		            ex.printStackTrace();
		            return false;
		        }
	   	   
	   	   
	   	   
	   	   
	   	   }
	      	
	       
	       else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		   
	       {
	    	   
	    	   
			   StringBuilder strSQL = new StringBuilder();
	   		   
			   strSQL.append("INSERT INTO company_tbl  ");
			   strSQL.append("(levelid,parentcompanyid,companyname,companyaddress,contactperson,datecreated,createdby,status,retailersim,mobile) ");
			   strSQL.append(" VALUES (?,?,?,?,?,current_timestamp,?,?,?,?) ");
			   
			   
			   try{
				   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					this.getlevelid(model.getParentcompany())+1,this.getcompanyid(model.getParentcompany()),model.getCompanyname(),model.getCompanyaddress(),
					model.getContactperson(),session.getAttribute("username"),model.getStatus(),model.getRetailersim(),model.getMobile()
					});

					
					if(row>0){
						return true;
					}
						
			
			   }catch(DataAccessException ex){
		            ex.printStackTrace();
		            return false;
		        }
	   	   

	    	   
	    	   
	    	   
	    	   
	    	   
	    	   
	       }
		

		   
		return false;

		
	}
	
	
	public boolean updateCompany(HttpSession session, NetworkManagementModel model){

		
			String strSQL = "update company_tbl set mobile = ?,retailersim = ?, status = ?, parentcompanyid = ? where companyid = ?";
		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					model.getMobile(),model.getRetailersim(),model.getStatus(),this.getcompanyid(model.getParentcompany()),model.getCompanyid()
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
	
	public boolean deleteCompany(NetworkManagementModel model){
		
		   StringBuilder strSQL = new StringBuilder();
		   		   
		   strSQL.append("update company_tbl set status = ? ");		 
		   strSQL.append("where companyid = ?");
		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
				
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
	
	public Map getLevellist(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> roles = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT levelname from level_tbl where levelid <> (Select levelid from company_tbl where companyname = ?) ");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"));
			
			for (Map row : rows) {
				
				roles.put((String)(row.get("levelname")), (String)row.get("levelname"));
		
			}
			
			return roles;
			
	}
	
	public Map getparentcompanylist(HttpSession session)
	{

	       
			 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   strSQL.append("SELECT a.levelid,a.companyid,a.parentcompanyid,a.companyname,a.companyaddress,a.contactperson,a.status,b.levelname  ");
		   strSQL.append("FROM company_tbl a, level_tbl b WHERE  ");
		   strSQL.append("a.levelid = b.levelid  ");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) 
			{
				
				int companyid = (Integer)(row.get("parentcompanyid"));
				
				if(companyid==0)
				{
					String getAllCompany = "SELECT a.companyname FROM company_tbl a, company_tbl b WHERE b.companyid = a.parentcompanyid";
//					network.setParentcompany("None");
					company.put((String)(row.get("companyname")), (String)row.get("companyname"));
					
				}else
				{
					
					String getparentcompany = "select companyname from company_tbl where levelid  = (select )";
					
					List<Map<String,Object>> rows2 = getJdbcTemplate().queryForList(getparentcompany,companyid);
					
					for (Map row2 : rows2) 
					{
						
						//network.setParentcompany((String)(row2.get("companyname")));
						company.put((String)(row.get("companyname")), (String)row.get("companyname"));
						
						
					}
					
				}
				
				
		
			}
			
			return company;
			
	}
	
	public List<NetworkManagementModel> getCompanylist(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
		   ArrayList<NetworkManagementModel> networkList= new ArrayList<NetworkManagementModel>();
		   
		   strSQL.append("SELECT companyname from company_tbl where status = 'active '");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				NetworkManagementModel network = new NetworkManagementModel();
				
//				company.put((String)(row.get("companyname")), (String)row.get("companyname"));
				
				network.setCompanyname((String)(row.get("companyname")));
				
				networkList.add(network);		
		
			}
			
			return networkList;
			
	}
	
	public Map getcompanylist(HttpSession session){
		
		String rolename = session.getAttribute("rolename")+"";
		
		   Map<String,String> company = new LinkedHashMap<String,String>();
		
	   	if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
		{
			String strSQL = "select companyname from company_tbl where status = ? and levelid <> 4";
				 
		
			   
			 	
						List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"active");
						
						for (Map row : rows) {
							
							company.put((String)(row.get("companyname")), (String)row.get("companyname"));
					
						}
	   		
	   		
	   		
	   		
		}
	   	
	   	else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		{
	   		   StringBuilder strSQL = new StringBuilder();
	   		   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid FROM company_tbl a		");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" INNER JOIN company_tbl c ON a.companyid = c.companyid  ");
			   strSQL.append(" LEFT JOIN company_tbl d ON a.companyid = d.companyid  ");
			   strSQL.append("LEFT JOIN company_tbl e ON a.companyid = e.companyid  ");
			   strSQL.append(" WHERE  a.parentcompanyid = (select companyid from company_tbl where companyname = ?) or  a.companyid = (select companyid from company_tbl where companyname = ?)");
			   
			 
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),session.getAttribute("companyname"));
			
			for (Map row : rows) {
				
				company.put((String)(row.get("companyname")), (String)row.get("companyname"));
		
			}
			
			
			
		}
		


			return company;
			
	}
	
	public Map getcompanylist(int cid,HttpSession session){
		
		int lvl = this.getlevelid(cid);
		 
		String strSQL = null;
		
		if(lvl == 4){
			
			 strSQL = "select companyname from company_tbl where status = ? and levelid = 3";
			
		}else if(lvl == 3){
			 strSQL = "select companyname from company_tbl where status = ? and levelid = 2";
			
		}else if(lvl == 2){
			 strSQL = "select companyname from company_tbl where status = ? and levelid = 1";
		}else if(lvl == 1){
			 strSQL = "select companyname from company_tbl where status = ? and levelid = 1";
		}
			
		
		  				 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
		 	
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"active");
					
					for (Map row : rows) {
						
						company.put((String)(row.get("companyname")), (String)row.get("companyname"));
				
					}

			return company;
			
	}

	   public boolean deletecomp(int id){

		   StringBuilder strSQL = new StringBuilder();
		      
		   strSQL.append("update company_tbl set status = ?   "); 
		   strSQL.append("where  companyid = ? " );

		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					"inactive",id
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
	   
	   public int getlevelid(String companyname)
	   {
		   int companyid = 0;
		   
		   String getName = "select levelid from company_tbl where companyname = ?";
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getName, companyname);
		   
			for (Map row : rows) {

				companyid = (Integer)(row.get("levelid"));
				
				return companyid;
			}
			
			return companyid;
		   
		   
	   }
	   
	   
	   public int getlevelid(int cid)
	   {
		   int companyid = 0;
		   
		   String getName = "select levelid from company_tbl where companyid = ?";
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getName, cid);
		   
			for (Map row : rows) {

				companyid = (Integer)(row.get("levelid"));
				
				return companyid;
			}
			
			return companyid;
		   
		   
	   }
	   
	   
	   
	

}
