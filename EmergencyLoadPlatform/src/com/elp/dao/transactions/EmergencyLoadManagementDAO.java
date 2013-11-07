package com.elp.dao.transactions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.elp.beans.UserBean;
import com.elp.model.CreditExtendedModel;
import com.elp.model.CreditHistoryModel;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.CreditTransferModel;
import com.elp.model.DailyTransferReportModel;
import com.elp.model.EmergencyLoadManagementModel;
import com.elp.model.EmergencyLoadReportModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.OpenSimModel;
import com.elp.model.UserManagementModel;



public class EmergencyLoadManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(EmergencyLoadManagementDAO.class);
	
	
	public Map fillBox(HttpSession session){
			 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   strSQL.append("SELECT distinct b.companyname as parentcompanyname FROM company_tbl a	");
		   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
		   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
		   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
		   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? and c.delivery_status = ? order by parentcompanyname");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"unpaid","completed");
			
			for (Map row : rows) {
				
				company.put((String)(row.get("parentcompanyname")), (String)row.get("parentcompanyname"));
		
			}
			
			return company;
			
	}
	
	public Map getcompanylist(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT companyname from company_tbl where status = 'active '");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				company.put((String)(row.get("companyname")), (String)row.get("companyname"));
		
			}
			
			return company;
			
	}
	
	public boolean addCreditLimit(CreditLimitManagementModel model){

		
			StringBuilder strSQL = new StringBuilder();
			
			strSQL.append("insert into credit_limit (companyid,amount,status) values ");
			strSQL.append("((select companyid from company_tbl where companyname = ?),?,?)");
		    try{
			   
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
							model.getCompanyname(),Integer.parseInt(model.getCreditlimit()),model.getStatus()
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
	
	public List<EmergencyLoadReportModel> getDeliveredEmergencyLoad(HttpSession session){
		
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<EmergencyLoadReportModel> emeregencyList= new ArrayList<EmergencyLoadReportModel>();
	       
	       if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
		 	
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT DISTINCT a.companyname ,c.delivery_status,c.status,c.amount FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? and c.delivery_status = ? ");
			   			   
			   emeregencyList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"unpaid","completed");
			   
				for (Map row : rows) {

					EmergencyLoadReportModel emergency = new EmergencyLoadReportModel();
					
					emergency.setCompanyname((String)(row.get("companyname")));
					emergency.setAmount((Integer)(row.get("amount")));
					emergency.setDelivery_status((String)(row.get("delivery_status")));
					emergency.setStatus((String)(row.get("status")));
					emeregencyList.add(emergency);			
				}
				
		
				
				return emeregencyList;
		  }
		
	       return emeregencyList;
		
	}
	
	
	public List<EmergencyLoadReportModel> getDeliveredEmergencyLoadReport(HttpSession session){
		
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<EmergencyLoadReportModel> emeregencyList= new ArrayList<EmergencyLoadReportModel>();
	       
	       if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
		 	
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT  a.companyname ,b.companyname as parentcompanyname,c.delivery_status,c.status,c.amount, DATE_FORMAT(c.date_borrowed,'%d %b %Y %T') as dateavailed FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? and c.delivery_status = ? order by parentcompanyname");
			   			   
			   emeregencyList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"unpaid","completed");
			   
				for (Map row : rows) {

					EmergencyLoadReportModel emergency = new EmergencyLoadReportModel();
					
					emergency.setCompanyname((String)(row.get("companyname")));
					emergency.setParentcompanyname((String)(row.get("parentcompanyname")));
					emergency.setAmount((Integer)(row.get("amount")));
					emergency.setDelivery_status((String)(row.get("delivery_status")));
					emergency.setStatus((String)(row.get("status")));
					emergency.setDateavailed((String)(row.get("dateavailed")));
					emeregencyList.add(emergency);			
				}
				
		
				
				session.setAttribute("report", emeregencyList);
				return emeregencyList;
		  }
		
	       return emeregencyList;
		
	}
	
	public List<EmergencyLoadReportModel> getDeliveredEmergencyLoadReport(HttpSession session,String dsp){
		
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<EmergencyLoadReportModel> emeregencyList= new ArrayList<EmergencyLoadReportModel>();
	       
	       if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
		 	
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT  a.companyname ,b.companyname as parentcompanyname,c.delivery_status,c.status,c.amount, DATE_FORMAT(c.date_borrowed,'%d %b %Y %T') as dateavailed FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? and c.delivery_status = ? and b.companyname = ? order by parentcompanyname");
			   			   
			   emeregencyList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"unpaid","completed",dsp);
			   
				for (Map row : rows) {

					EmergencyLoadReportModel emergency = new EmergencyLoadReportModel();
					
					emergency.setCompanyname((String)(row.get("companyname")));
					emergency.setParentcompanyname((String)(row.get("parentcompanyname")));
					emergency.setAmount((Integer)(row.get("amount")));
					emergency.setDelivery_status((String)(row.get("delivery_status")));
					emergency.setStatus((String)(row.get("status")));
					emergency.setDateavailed((String)(row.get("dateavailed")));
					emeregencyList.add(emergency);			
				}
				
		
				session.setAttribute("report", emeregencyList);
				return emeregencyList;
		  }
		
	       return emeregencyList;
		
	}
	
	
	
	public List<CreditLogsModel> getAvailedEmergencyLoadList(HttpSession session,String pStatus){
			
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditLogsModel> creditList= new ArrayList<CreditLogsModel>();
	       
		   	if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
			{
		 	   
				   StringBuilder strSQL = new StringBuilder()	;
				   
//				   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,d.credit_id FROM company_tbl a	");
//				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
//				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//				   strSQL.append("WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) c.status = ? AND d.status = ? ");
				   
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,e.amount,e.status,e.userid,e.levelid FROM company_tbl a ");		
				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
				   strSQL.append(" WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ?");
		
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved",pStatus);
				   
					for (Map row : rows) {

						CreditLogsModel credit = new CreditLogsModel();
						
						credit.setCreditid((Integer)(row.get("credit_id")));
						credit.setCompanyid((Integer)(row.get("companyid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setAmount((Integer)(row.get("creditlimit")));
						credit.setDelivery_status((String)(row.get("delivery_status")));
						
		
						
						String status = (String)(row.get("status"));
						
						if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
							credit.setStatus("unpaid");
						}else{
							credit.setStatus(status);
						}
						
						
			
						creditList.add(credit);			
					}
				
			}
		   	else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
		 	
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.credit_id,c.delivery_status,c.status as stat,d.status as status,c.amount FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs d ON c.credit_id = d.credit_id   ");
			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? ");
			   
//			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.credit_id,c.delivery_status,c.status as stat,d.status as status,c.amount FROM company_tbl a	");
//			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
//			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,d.amount,e.status,e.userid,e.levelid FROM company_tbl a ");		
//			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//			   strSQL.append("INNER JOIN credit_limit c ON b.parentcompanyid = c.companyid   ");
//			   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//			   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//			   strSQL.append(" WHERE c.companyid  =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ?");
			   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),pStatus);
			   
				for (Map row : rows) {

					CreditLogsModel credit = new CreditLogsModel();
					
					credit.setCreditid((Integer)(row.get("credit_id")));
					credit.setCompanyid((Integer)(row.get("companyid")));
					credit.setCompanyname((String)(row.get("companyname")));
					credit.setAmount((Integer)(row.get("amount")));
					credit.setDelivery_status((String)(row.get("delivery_status")));
					credit.setStatus((String)(row.get("stat")));
		
					
//					String status = (String)(row.get("status"));
//					
//					if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
//						credit.setStatus("unpaid");
//					}else{
//						credit.setStatus(status);
//					}
					
					
		
					creditList.add(credit);			
				}
			
				
			}	   	else if (rolename.equalsIgnoreCase("Retailer Operations"))
			{
			 	
				
				   StringBuilder strSQL = new StringBuilder()	;
				   
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.credit_id,c.delivery_status,c.status as stat,d.status as status,c.amount,DATE_FORMAT(c.date_borrowed,'%d %b %Y %T') as dateavailed FROM company_tbl a	");
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
				   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
				   strSQL.append("WHERE  a.companyid = (select companyid from company_tbl where companyname = ?) and c.status = ?");
				   
//				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,d.amount,e.status,e.userid,e.levelid FROM company_tbl a ");		
//				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
//				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//				   strSQL.append(" WHERE a.companyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ?");
//		
				   
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),pStatus);
				   
					for (Map row : rows) {

						CreditLogsModel credit = new CreditLogsModel();
						
						credit.setCreditid((Integer)(row.get("credit_id")));
						credit.setCompanyid((Integer)(row.get("companyid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setAmount((Integer)(row.get("amount")));
						credit.setDelivery_status((String)(row.get("delivery_status")));
						credit.setStatus((String)(row.get("stat")));
						credit.setDate_borrowed((String)(row.get("dateavailed")));
						
//						String status = (String)(row.get("status"));
//						
//						if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
//							credit.setStatus("unpaid");
//						}else{
//							credit.setStatus(status);
//						}
//						
						
			
						creditList.add(credit);			
					}
				
					
				}
		   	
			else if (rolename.equalsIgnoreCase("DSP Operations"))
			{
			 	
				
				   StringBuilder strSQL = new StringBuilder()	;
				   
//				   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.delivery_status,c.status as stat,d.status as status,c.amount FROM company_tbl a	");
//				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//				   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
//				   strSQL.append("WHERE  a.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ?");
				   
//				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid ,d.credit_id,d.delivery_status,d.status AS stat,e.status AS STATUS,d.amount FROM company_tbl a  ");		
//				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
//				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//				   strSQL.append(" WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND d.status = ?");
				   
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid ,c.credit_id,c.delivery_status,c.status AS stat,d.status AS STATUS,c.amount FROM company_tbl a 	");		
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs c ON a.companyid = c.companyid    ");
				   strSQL.append("LEFT JOIN credit_payment_logs d ON c.companyid = d.companyid   ");
				   strSQL.append(" WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ?");
				   
				   
		
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),pStatus);
				   
					for (Map row : rows) {

						CreditLogsModel credit = new CreditLogsModel();
						
						credit.setCreditid((Integer)(row.get("credit_id")));
						credit.setCompanyid((Integer)(row.get("companyid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setAmount((Integer)(row.get("amount")));
						credit.setDelivery_status((String)(row.get("delivery_status")));
						credit.setStatus((String)(row.get("stat")));
						
//						if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
//							credit.setStatus("unpaid");
//						}else{
//							credit.setStatus(status);
//						}
//						
						
			
						creditList.add(credit);			
					}
				
					
				}
		   	
		   	
		
	
		
		   	return creditList;
		
		
	}
	

	
	public List<CreditLogsModel> getAvailedEmergencyLoad(int cid,HttpSession session,String pStatus){
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditLogsModel> creditList= new ArrayList<CreditLogsModel>();
		   
	       if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
			{
		   		
	    	   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,d.credit_id FROM company_tbl a	");
//			   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
//			   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
//			   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//			   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//			   strSQL.append("WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) and d.credit_id = ? and d.status = ? and c.status = ?");
	    	   
			   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,e.amount,e.status,e.userid,e.levelid FROM company_tbl a ");		
			   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
			   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
			   strSQL.append(" WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ? AND d.credit_id = ?");
			   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved",pStatus,cid);
			   
				for (Map row : rows) {

					CreditLogsModel credit = new CreditLogsModel();
					
					credit.setCreditid((Integer)(row.get("credit_id")));
					credit.setCompanyid((Integer)(row.get("companyid")));
					credit.setCompanyname((String)(row.get("companyname")));
					credit.setAmount((Integer)(row.get("creditlimit")));
					credit.setDelivery_status((String)(row.get("delivery_status")));
					
				
					String status = (String)(row.get("status"));
					
					if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
						credit.setStatus("unpaid");
					}else{
						credit.setStatus(status);
					}
					
					
		
					creditList.add(credit);			
				}
		   		
		   		
			}
	       
	       else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
	 		   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//			   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//			   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.delivery_status,d.status,c.amount,DATE_FORMAT(c.date_borrowed,'%m/%d/%Y') as dateBorrowed FROM company_tbl a	");
//			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//			   strSQL.append("LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
//			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) AND c.status = ? AND d.credit_id = ?");

			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,d.delivery_status,d.amount,DATE_FORMAT(d.date_borrowed,'%m/%d/%Y') as dateBorrowed,e.status,e.userid,e.levelid FROM company_tbl a ");		
//			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//			   strSQL.append("INNER JOIN credit_limit c ON b.parentcompanyid = c.companyid   ");
//			   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
//			   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
//			   strSQL.append(" WHERE c.companyid  =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ? AND d.credit_id = ?");
			   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.credit_id,c.delivery_status,c.status as stat,d.status as status,c.amount,DATE_FORMAT(c.date_borrowed,'%m/%d/%Y') as dateBorrowed FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
			   strSQL.append(" LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
			   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and c.status = ? and  c.credit_id = ? ");
			   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),pStatus,cid);
			   
				for (Map row : rows) {

					CreditLogsModel credit = new CreditLogsModel();
					
					
					credit.setCreditid((Integer)(row.get("credit_id")));
					credit.setCompanyid((Integer)(row.get("companyid")));
					credit.setCompanyname((String)(row.get("companyname")));
					credit.setAmount((Integer)(row.get("amount")));
					credit.setDelivery_status((String)(row.get("delivery_status")));
					credit.setDate_borrowed((String)(row.get("dateBorrowed")));
					credit.setStatus((String)(row.get("stat")));
		
					
//					String status = (String)(row.get("status"));
//					
//					if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
//						credit.setStatus("unpaid");
//					}else{
//						credit.setStatus(status);
//					}
					

							
					creditList.add(credit);			
				}
		   		
			}else
		        if (rolename.equalsIgnoreCase("DSP Operations"))
				{
		 		   StringBuilder strSQL = new StringBuilder()	;
				   
//				   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//				   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//				   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//				   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
				   
//				   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.delivery_status,d.status,c.amount,DATE_FORMAT(c.date_borrowed,'%m/%d/%Y') as dateBorrowed FROM company_tbl a	");
//				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//				   strSQL.append("LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
//				   strSQL.append("WHERE  a.parentcompanyid = (select companyid from company_tbl where companyname = ?)  AND c.credit_id = ? and c.status = ?");

				   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.delivery_status,d.status,d.amount,DATE_FORMAT(d.date_borrowed,'%m/%d/%Y') as dateBorrowed,e.status,e.userid,e.levelid FROM company_tbl a ");		
				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
				   strSQL.append(" WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ? AND d.credit_id = ?");
				   
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),"approved",pStatus,cid);
				   
					for (Map row : rows) {

						CreditLogsModel credit = new CreditLogsModel();
						
						
						credit.setCreditid((Integer)(row.get("credit_id")));
						credit.setCompanyid((Integer)(row.get("companyid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setAmount((Integer)(row.get("amount")));
						credit.setDelivery_status((String)(row.get("delivery_status")));
						credit.setDate_borrowed((String)(row.get("dateBorrowed")));
			
						String status = (String)(row.get("status"));
						
						if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
							credit.setStatus("unpaid");
						}else{
							credit.setStatus(status);
						}
						

								
						creditList.add(credit);			
					}
			   		
				}
	       
			else if (rolename.equalsIgnoreCase("Retailer Operations"))
			{
				   StringBuilder strSQL = new StringBuilder()	;
				   
//				   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//				   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//				   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//				   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
				   
//				   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.delivery_status,d.status,c.amount,DATE_FORMAT(c.date_borrowed,'%m/%d/%Y') as dateBorrowed FROM company_tbl a	");
//				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
//				   strSQL.append("LEFT JOIN credit_logs c ON a.companyid = c.companyid  ");
//				   strSQL.append("LEFT JOIN credit_payment_logs d  ON c.companyid = d.companyid  ");
//				   strSQL.append("WHERE  a.companyid = (select companyid from company_tbl where companyname = ?)  AND c.credit_id = ? and c.status = ?");

				   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.delivery_status,d.status,d.amount,DATE_FORMAT(d.date_borrowed,'%m/%d/%Y') as dateBorrowed,e.status,e.userid,e.levelid FROM company_tbl a ");		
				   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
				   strSQL.append(" WHERE a.companyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) AND c.status = ? AND d.status = ? AND d.credit_id = ?");
				   
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),"approved",pStatus,cid);
				   
					for (Map row : rows) {

						CreditLogsModel credit = new CreditLogsModel();
						
						
						credit.setCreditid((Integer)(row.get("credit_id")));
						credit.setCompanyid((Integer)(row.get("companyid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setAmount((Integer)(row.get("amount")));
						credit.setDelivery_status((String)(row.get("delivery_status")));
						credit.setDate_borrowed((String)(row.get("dateBorrowed")));
			
//						String status = (String)(row.get("status1"));
//						
//						if(status == null || status.equals("") || status.equals(null) || status.length() == 0){
//							credit.setStatus("unpaid");
//						}else{
//							credit.setStatus(status);
//						}
						
						
						credit.setStatus(pStatus);

								
						creditList.add(credit);			
					}
			}
			 
				
	
			

		
			return creditList;
		
		
		
		
	}
	

	public List<CreditHistoryModel> getPaymentHistory(HttpSession session, int cid){
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditHistoryModel> creditList= new ArrayList<CreditHistoryModel>();
	       
	        if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
	        	
	 		   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//			   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//			   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.newstatus,DATE_FORMAT(c.changedate,'%m/%d/%Y') as dateBorrowed	");
//			   strSQL.append("FROM company_tbl a INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
//			   strSQL.append("INNER JOIN  credit_payment_logs_history c ON a.companyid = c.companyid ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) ");
			   
	 		   strSQL.append("SELECT b.parentcompanyid,d.credit_id,a.companyid, a.companyname,c.amount,e.newstatus,DATE_FORMAT(e.changedate,'%m/%d/%Y') as dateBorrowed ");
	 		   strSQL.append(" FROM company_tbl a ");
	 		   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
	 		   strSQL.append("INNER JOIN credit_logs c ON a.companyid = c.companyid   ");
	 		   strSQL.append("INNER JOIN credit_payment_logs d ON c.credit_id = d.credit_id  ");
	 		   strSQL.append("INNER JOIN credit_payment_logs_history e ON d.credit_id = e.credit_id    ");
	 		   strSQL.append(" WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) and d.credit_id = ? ");
	 		   
	 		   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),cid);
			   
				for (Map row : rows) {

					CreditHistoryModel credit = new CreditHistoryModel();
					
					
					credit.setCreditid((Integer)(row.get("credit_id")));
					credit.setCompanyid((Integer)(row.get("companyid")));
					credit.setChangedate((String)(row.get("dateBorrowed")));
					credit.setNewstatus((String)(row.get("newstatus")));
					credit.setAmount((String)(row.get("amount")+""));
	

							
					creditList.add(credit);			
				}
		   		
			}
			
	
			

		
			return creditList;
		
		
		
		
	}
	
	public List<CreditTransferModel> getTransferlist(HttpSession session){
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditTransferModel> transferlist= new ArrayList<CreditTransferModel>();
	       
	        if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
	        	
	 		   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//			   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//			   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.newstatus,DATE_FORMAT(c.changedate,'%m/%d/%Y') as dateBorrowed	");
//			   strSQL.append("FROM company_tbl a INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
//			   strSQL.append("INNER JOIN  credit_payment_logs_history c ON a.companyid = c.companyid ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) ");
			   
	 		  strSQL.append("SELECT  c.companyname,c.parentcompanyid,e.credit_id,DATE_FORMAT(f.createddate,'%m/%d/%Y') as createdDate,f.amount,f.transfer_status,DATE_FORMAT(f.transferdate,'%m/%d/%Y') as transferDate ");
	 		  strSQL.append("FROM company_tbl c ");
	 		  strSQL.append("INNER JOIN  company_tbl d ON c.companyid = d.companyid ");
	 		  strSQL.append("INNER JOIN credit_logs e ON d.companyid = e.companyid ");
	 		  strSQL.append("INNER JOIN credit_transfer_logs f ON e.credit_id = f.credit_id  ");
	 		  strSQL.append("INNER JOIN company_tbl g ON d.parentcompanyid = g.companyid ");
	 		  strSQL.append("WHERE g.parentcompanyid = (select companyid from company_tbl where companyname = ?)");
	 		  	 
			   transferlist.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"));
			   
				for (Map row : rows) {

					CreditTransferModel  transfer = new CreditTransferModel();
					
					transfer.setCreditid((Integer)(row.get("credit_id")));
					transfer.setCompanyname((String)(row.get("companyname")));
					transfer.setAmount((String)(row.get("amount")+""));
					transfer.setTransfer_status((String)(row.get("transfer_status")));
					transfer.setTransferdate((String)(row.get("transferDate")));
					transfer.setCreateddate((String)(row.get("createdDate")));

	

							
					transferlist.add(transfer);			
				}
		   		
			}
			
	
			

		
			return transferlist;
		
	}
	
	
	public List<CreditTransferModel> getTransferlist(HttpSession session,int cid){
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditTransferModel> transferlist= new ArrayList<CreditTransferModel>();
	       
	        if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
	        	
	 		   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//			   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//			   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.newstatus,DATE_FORMAT(c.changedate,'%m/%d/%Y') as dateBorrowed	");
//			   strSQL.append("FROM company_tbl a INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
//			   strSQL.append("INNER JOIN  credit_payment_logs_history c ON a.companyid = c.companyid ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) ");
			   
	 		  strSQL.append("SELECT  c.companyname,c.parentcompanyid,e.credit_id,DATE_FORMAT(f.createddate,'%m/%d/%Y') as createdDate,f.amount,f.transfer_status,DATE_FORMAT(f.transferdate,'%m/%d/%Y') as transferDate ");
	 		  strSQL.append("FROM company_tbl c ");
	 		  strSQL.append("INNER JOIN  company_tbl d ON c.companyid = d.companyid ");
	 		  strSQL.append("INNER JOIN credit_logs e ON d.companyid = e.companyid ");
	 		  strSQL.append("INNER JOIN credit_transfer_logs f ON e.credit_id = f.credit_id  ");
	 		  strSQL.append("INNER JOIN company_tbl g ON d.parentcompanyid = g.companyid ");
	 		  strSQL.append("WHERE g.parentcompanyid = (select companyid from company_tbl where companyname = ?) and f.credit_id  = ?");
	 		  	 
			   transferlist.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),cid);
			   
				for (Map row : rows) {

					CreditTransferModel  transfer = new CreditTransferModel();
					
					transfer.setCreditid((Integer)(row.get("credit_id")));
					transfer.setCompanyname((String)(row.get("companyname")));
					transfer.setAmount((String)(row.get("amount")+""));
					transfer.setTransfer_status((String)(row.get("transfer_status")));
					transfer.setTransferdate((String)(row.get("transferDate")));
					transfer.setCreateddate((String)(row.get("createdDate")));

	

							
					transferlist.add(transfer);			
				}
		   		
			}
			
	
			

		
			return transferlist;
		
	}
	
	public List<CreditTransferModel> getTransferlist(int cid,HttpSession session){
		
	       String rolename = session.getAttribute("rolename")+"";
	       
	       ArrayList<CreditTransferModel> transferlist= new ArrayList<CreditTransferModel>();
	       
	        if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
	        	
	 		   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.credit_id,a.companyid,a.amount,DATE_FORMAT(a.date_borrowed,'%m/%d/%Y') as dateBorrowed,a.status, ");
//			   strSQL.append("a.approval_status,a.delivery_status,a.delivered_amount,a.amount_paid,DATE_FORMAT(a.date_due,'%m/%d/%Y') as duedate,b.companyname  ");
//			   strSQL.append("FROM credit_logs a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid and a.credit_id = ? ");
			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.credit_id,c.newstatus,DATE_FORMAT(c.changedate,'%m/%d/%Y') as dateBorrowed	");
//			   strSQL.append("FROM company_tbl a INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid  ");
//			   strSQL.append("INNER JOIN  credit_payment_logs_history c ON a.companyid = c.companyid ");
//			   strSQL.append("WHERE  b.parentcompanyid = (select companyid from company_tbl where companyname = ?) ");
			   
	 		  strSQL.append("SELECT  c.companyname,c.parentcompanyid,e.credit_id,DATE_FORMAT(f.createddate,'%m/%d/%Y') as createdDate,f.amount,f.transfer_status,DATE_FORMAT(f.transferdate,'%m/%d/%Y') as transferDate ");
	 		  strSQL.append("FROM company_tbl c ");
	 		  strSQL.append("INNER JOIN  company_tbl d ON c.companyid = d.companyid ");
	 		  strSQL.append("INNER JOIN credit_logs e ON d.companyid = e.companyid ");
	 		  strSQL.append("INNER JOIN credit_transfer_logs f ON e.credit_id = f.credit_id  ");
	 		  strSQL.append("INNER JOIN company_tbl g ON d.parentcompanyid = g.companyid ");
	 		  strSQL.append("WHERE g.parentcompanyid = (select companyid from company_tbl where companyname = ?) and f.credit_id = ?");
	 		  	 
			   transferlist.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("companyname"),cid);
			   
				for (Map row : rows) {

					CreditTransferModel  transfer = new CreditTransferModel();
					
					transfer.setCreditid((Integer)(row.get("credit_id")));
					transfer.setCompanyname((String)(row.get("companyname")));
					transfer.setAmount((String)(row.get("amount")+""));
					transfer.setTransfer_status((String)(row.get("transfer_status")));
					transfer.setTransferdate((String)(row.get("transferDate")));
					transfer.setCreateddate((String)(row.get("createdDate")));

	

							
					transferlist.add(transfer);			
				}
		   		
			}
			
	
			

		
			return transferlist;
		
		
		
		
	}
	//add exception
	public int addEmergencyLoad(final String eAmount, final HttpSession session) throws ParseException{
		
		String rolename = session.getAttribute("rolename")+"";
		
		
	     if (rolename.equalsIgnoreCase("Retailer Operations"))
		 {
	    	  
	    	 
				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount);
		
	      
		int creditid = 0;
		int creditlimit = 0;
		final int amount = Integer.parseInt(eAmount);
		int pd = 0;

		
		//String checkcreditlimit = "select remaininglimit,re from credit_limit where companyid = (select companyid from company_tbl where companyname = ?) and status = ?";
		//String checkcreditlimit = "select remaininglimit,remaining_balance from credit_limit where companyid = (select companyid from company_tbl where companyname = ?) and status = ?";
		
		StringBuilder checkcreditlimit = new StringBuilder()	;
		
		checkcreditlimit.append("SELECT a.companyid,a.mobile,a.retailersim,a.opensim,c.creditlimit,c.remaininglimit,c.remaining_balance,d.modemname FROM company_tbl a ");
		checkcreditlimit.append("INNER JOIN company_tbl b ON a.parentcompanyid = b.companyid ");
		checkcreditlimit.append("INNER JOIN credit_limit c ON a.companyid = c.companyid ");
		checkcreditlimit.append("LEFT JOIN opensim_tbl d ON a.opensim = d.opensim ");
		checkcreditlimit.append("WHERE a.companyid = (select companyid from company_tbl where companyname = ?)  AND c.status = ? AND b.status = ?");
		
		
		String getretailerPD = "SELECT a.companyid,a.companyname,b.companyid,b.companyname,c.companyid,c.companyname FROM company_tbl a INNER JOIN company_tbl b ON a.companyid = b.parentcompanyid INNER JOIN company_tbl c ON b.companyid = c.parentcompanyid AND c.companyid = (select companyid from company_tbl where companyname = ?)";

		String getretailersim = "select retailersim from company_tbl where companyname = ? and status = ?";
	
		//String checkoverdue = "SELECT  COUNT(1)  FROM credit_logs c INNER JOIN credit_payment_logs t  ON c.credit_id = t.credit_id WHERE c.delivery_status = ? AND c.status = ? AND c.date_due < NOW()";
		
		
		String begintime = "";
		String endtime = "";
		
		String holidaystart = "";
		String holidayend = "";
		
		String getbegintime = "Select setting_value from settings_tbl where setting_name = ?";
		
		SqlRowSet getbegintimeRs = getJdbcTemplate().queryForRowSet(getbegintime,"close_begintime");
		
		if(getbegintimeRs.next())
		
		{
			begintime = getbegintimeRs.getString("setting_value");
			
		}
		
		String getendtime = "Select setting_value from settings_tbl where setting_name = ?";
		
		SqlRowSet getendtimeRs = getJdbcTemplate().queryForRowSet(getendtime,"close_endtime");
		
		if(getendtimeRs.next())
		
		{
			endtime = getendtimeRs.getString("setting_value");

		}
		

		
        SimpleDateFormat  sdf = new SimpleDateFormat("HH:mm");
    
        String todaytime = sdf.format(new Date());
        
 			
            Date Before = sdf.parse(begintime);
            Date After = sdf.parse(endtime);
            Date Present = sdf.parse(todaytime);
		
        if(!Present.before(Before) && Present.after(After))
          {
            			
			SqlRowSet srs1 = getJdbcTemplate().queryForRowSet(checkcreditlimit.toString(),session.getAttribute("companyname"),"approved","active");
        	
			
            	if(srs1.next())
            	{
    			
	    			int currentcreditlimit = srs1.getInt("creditlimit");
	    			int availcreditlimit = srs1.getInt("remaininglimit") - srs1.getInt("remaining_balance");
	    			String mobile = srs1.getString("mobile");
	    			String retailersim = srs1.getString("retailersim");
	    			String opensim = srs1.getString("opensim");
	    			String modemname = srs1.getString("modemname");
	    			
	    			//creditlimit = srs.getInt("remaininglimit") - rs.getInt("outstanding_balance");
	    			//creditlimit = srs.getInt("remaininglimit") - rs.getInt("remaining_balance");
    			
	    			if(availcreditlimit<=0)
	    			{
    				
    				
	    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Insufficient Funds");
	    				return 4;
	    			}
    			
	    				if(retailersim.equalsIgnoreCase("") || retailersim.equals(""))
	    				{
    				
		    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: No Available Retailer Sim.");
		    				return 5;
	    				}
    			
	    					if(amount<availcreditlimit)
	    					{

			    				final String insertcreditlogs = "insert into credit_logs(companyid,amount,approval_status,delivery_status,status,date_borrowed,date_due) values ((select companyid from company_tbl where companyname = ?),?,?,?,?,now(),now()+1)";
			    				
			    				KeyHolder keyHolder = new GeneratedKeyHolder();
			    				
			    				int row = getJdbcTemplate().update(new PreparedStatementCreator() 
			    				{
		    					  public PreparedStatement createPreparedStatement(Connection connection)
		    							    throws SQLException 
		    							    {
		    							   PreparedStatement ps = connection.prepareStatement(insertcreditlogs.toString(),Statement.RETURN_GENERATED_KEYS);
		    							   ps.setString(1,  session.getAttribute("companyname").toString());
		    							   ps.setInt(2, amount);
		    							   ps.setString(3, "approved");
		    							   ps.setString(4, "requested");
		    							   ps.setString(5, "unpaid");
		    							   return ps;
    							 }
    				}, keyHolder);
    			
    				if(row>0)
    				{
    					creditid = keyHolder.getKey().intValue();
    					
    					SqlRowSet rsPD = getJdbcTemplate().queryForRowSet(getretailerPD,session.getAttribute("companyname"));
    				
    					
    					if(rsPD.next())
    					{
    						pd = rsPD.getInt("companyid");
    					}
    					

    						String updateOutstandingBalance = "update credit_limit set outstanding_balance = outstanding_balance + ?, remaining_balance = remaining_balance + ?,credit_id = ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and outstanding_balance + ? <= creditlimit  ";
    					
    	
    						int row2 = getJdbcTemplate().update(updateOutstandingBalance, new Object[] { 
    								amount,amount,creditid,session.getAttribute("companyname"),"approved",amount
    							});
    						
    						
    						String updatePDOutstandingBalance = "update credit_limit set outstanding_balance = outstanding_balance + ?,remaining_balance = remaining_balance = + ?, credit_id = ?  where companyid = ? and status = ? and outstanding_balance + ? <= creditlimit ";
    						
    						
    						int row5 = getJdbcTemplate().update(updatePDOutstandingBalance, new Object[] { 
    								amount,amount,creditid,pd,"approved",amount
    							});
    		
    										
    						if(row>0 && row2>0 && row5>0)
    						{
    							String insertransferlogs = "insert into credit_transfer_logs(credit_id,transfer_status,transferby,amount,retailersim,transferdate,createddate) values (?,?,?,?,?,now(),now())";
    							
    							int row3 = getJdbcTemplate().update(insertransferlogs, new Object[] { 
    									creditid,"initial",session.getAttribute("username"),amount,retailersim
    								});
    							
    							String updateCreditLimit = "update credit_limit set credit_id = ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? ";
    							
    							
    							int row4 = getJdbcTemplate().update(updateCreditLimit, new Object[] { 
    									creditid,session.getAttribute("companyname"),"approved"
    								});

    							if (row3>0 && row4>0){
    								
    								
    			    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", SUCCESS: Application of Emergency Load Successful");
    								
    								return 1;
    							}
    							

    						}
    				
		    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
    						return 0;
    					
    									
    				}
    				
    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
    				return 0;
    			}
				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
    			return -1;
    		}
   
			logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Not Enough Balance");
    		return 0;

		
			
		}	//ito bago	
		
		else{
			
			
			String checkoverdue = "SELECT  COUNT(1)  FROM credit_logs c WHERE c.delivery_status = ? AND c.status = ? AND c.date_due < NOW()";
			
			SqlRowSet overdueRS = getJdbcTemplate().queryForRowSet(checkoverdue,"completed","unpaid");
			
			if(overdueRS.next())
			
			{
				
				if(overdueRS.getInt(1)>0)
				{
					logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Settle first your unpaid dues before applying for Emergency Load.");
					return 2;
					
				}
			}
		}
        
        Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		
		SimpleDateFormat  datetodayFormat = new SimpleDateFormat("MM/dd/yyyy 12:00:00");
        
        String datetoday = datetodayFormat.format(new Date()); 
        
        Date startHoliday = null;
        Date endHoliday = null;
        
        long holidaylength = 0;
        
//		String getholidays = "SELECT holiday_from, holiday_to FROM holiday_tbl WHERE holiday_from = (DATE_FORMAT(NOW(),'%Y-%m-%d 12:00:00'))";
        
        String getholidays = "SELECT * FROM holiday_tbl WHERE CURDATE() BETWEEN holiday_from AND holiday_to";
		
		SqlRowSet getholidayRS = getJdbcTemplate().queryForRowSet(getholidays);
		
		long daysBetween = 0; 
		  
		if(getholidayRS.next() || day == Calendar.SUNDAY)
		
		{
		SqlRowSet srs = getJdbcTemplate().queryForRowSet(checkcreditlimit.toString(),session.getAttribute("companyname"),"approved","active");
    	
		
    	if(srs.next())
    	{
		
			int currentcreditlimit = srs.getInt("creditlimit");
			int availcreditlimit = srs.getInt("remaininglimit") - srs.getInt("remaining_balance");
			String mobile = srs.getString("mobile");
			String retailersim = srs.getString("retailersim");
			String opensim = srs.getString("opensim");
			String modemname = srs.getString("modemname");
			
			//creditlimit = srs.getInt("remaininglimit") - rs.getInt("outstanding_balance");
			//creditlimit = srs.getInt("remaininglimit") - rs.getInt("remaining_balance");
		
			if(availcreditlimit<=0)
			{
			
			
				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Insufficient Funds");
				return 4;
			}
		
				if(retailersim.equalsIgnoreCase("") || retailersim.equals(""))
				{
			
    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: No Available Retailer Sim.");
    				return 5;
				}
		
					if(amount<availcreditlimit)
					{

	    				final String insertcreditlogs = "insert into credit_logs(companyid,amount,approval_status,delivery_status,status,date_borrowed,date_due) values ((select companyid from company_tbl where companyname = ?),?,?,?,?,now(),now()+1)";
	    				
	    				KeyHolder keyHolder = new GeneratedKeyHolder();
	    				
	    				int row = getJdbcTemplate().update(new PreparedStatementCreator() 
	    				{
    					  public PreparedStatement createPreparedStatement(Connection connection)
    							    throws SQLException 
    							    {
    							   PreparedStatement ps = connection.prepareStatement(insertcreditlogs.toString(),Statement.RETURN_GENERATED_KEYS);
    							   ps.setString(1,  session.getAttribute("companyname").toString());
    							   ps.setInt(2, amount);
    							   ps.setString(3, "approved");
    							   ps.setString(4, "requested");
    							   ps.setString(5, "unpaid");
    							   return ps;
						 }
			}, keyHolder);
		
			if(row>0)
			{
				creditid = keyHolder.getKey().intValue();
				
				SqlRowSet rsPD = getJdbcTemplate().queryForRowSet(getretailerPD,session.getAttribute("companyname"));
			
				
				if(rsPD.next())
				{
					pd = rsPD.getInt("companyid");
				}
				

					String updateOutstandingBalance = "update credit_limit set outstanding_balance = outstanding_balance + ?, remaining_balance = remaining_balance + ?,credit_id = ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and outstanding_balance + ? <= creditlimit  ";
				

					int row2 = getJdbcTemplate().update(updateOutstandingBalance, new Object[] { 
							amount,amount,creditid,session.getAttribute("companyname"),"approved",amount
						});
					
					
					String updatePDOutstandingBalance = "update credit_limit set outstanding_balance = outstanding_balance + ?,remaining_balance = remaining_balance = + ?, credit_id = ?  where companyid = ? and status = ? and outstanding_balance + ? <= creditlimit ";
					
					
					int row5 = getJdbcTemplate().update(updatePDOutstandingBalance, new Object[] { 
							amount,amount,creditid,pd,"approved",amount
						});
	
									
					if(row>0 && row2>0 && row5>0)
					{
						String insertransferlogs = "insert into credit_transfer_logs(credit_id,transfer_status,transferby,amount,retailersim,transferdate,createddate) values (?,?,?,?,?,now(),now())";
						
						int row3 = getJdbcTemplate().update(insertransferlogs, new Object[] { 
								creditid,"initial",session.getAttribute("username"),amount,retailersim
							});
						
						String updateCreditLimit = "update credit_limit set credit_id = ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? ";
						
						
						int row4 = getJdbcTemplate().update(updateCreditLimit, new Object[] { 
								creditid,session.getAttribute("companyname"),"approved"
							});

						if (row3>0 && row4>0){
							
							
		    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", SUCCESS: Application of Emergency Load Successful");
							
							return 1;
						}
						

					}
			
    				logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
					return 0;
				
								
			}
			
			logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
			return 0;
		}
		logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Application of Emergency Load Failed.");
		return -1;
	}

	logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Not Enough Balance");
	return 0;
			
		 
			
			}	     
			
			logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Cannot Process Request.  No Permit to make request for Emergency Load.");
		     return 6;
			
		}
		
			logger.info("Requesting Emergency Load for company: "+session.getAttribute("companyname").toString()+", amount: "+eAmount +", Failed: Cannot Process Request.  No Permit to make request for Emergency Load.");
	     return 6;
		
	
	     
	
	}
	
	
	public int updatePayment(CreditLogsModel model,HttpSession session,String oldstat){
		
		String rolename = session.getAttribute("rolename")+"";
		
	
		//String updatePaymentPaid = "update credit_payment_logs (credit_id,status,userid,levelid) values (?,?,?,?)";
		String updatePaymentPaid = "update credit_payment_logs set status=?,userid = ?,levelid = ? where credit_id = ?";
		
		
		String insertPaymentCancel = "insert into credit_payment_logs (credit_id,amount,status,companyid,userid,levelid) values (?,?,?,?,?,?)";
		String updatePaymentCancel= "update credit_payment_logs (credit_id,status,userid,levelid) values (?,?,?,?)";
		
		String checkCreditTransferLogs = "select a.credit_id from credit_transfer_logs inner join credit_payment_logs b on a.credit_id = b.credit_id where b.credit_id = ?  and a.transfer_status = ? or a.transfer_status = ?";
		String checkDSPCreditTransferLogs = "select a.credit_id from credit_transfer_logs inner join credit_payment_logs b on a.credit_id = b.credit_id where b.credit_id = ? and b.levelid >= ? and a.transfer_status = ? or a.transfer_status = ?";
		
		String updatePdBalance = "update credit_limit set outstanding_balance = outstanding_balance - ? ,remaininglimit = remaininglimit + ? where companyid = ? and status = ?";
		
		String checkifCreditIdExists = "select credit_id from credit_payment_logs where credit_id = ?";
	
//		String updateRetailerPaid = "update credit_limit set remaininglimit = remaininglimit + ? where companyid = ? and status =? ";
		
		String updateRetailerPaid = "update credit_limit set  remaining_balance = remaining_balance - ?  where companyid = ? and status =? ";
		
		String updatePDOutstandingBalance = "update credit_limit set outstanding_balance = outstanding_balance - ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? ";
		
		if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
		{
			
			
			logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +"");
			
			if(model.getStatus().equalsIgnoreCase("paid"))
			{
				int outstandingbalance = 0;
				
				String getoutstandingbalance = "select outstanding_balance from credit_limit where creditlimitid = ? and companyid = ?";
				
				
				SqlRowSet rs =getJdbcTemplate().queryForRowSet(getoutstandingbalance,model.getCreditid(),model.getCompanyid());
				
				if(rs.next()){
					
					outstandingbalance = rs.getInt("outstanding_balance");
				}
				
				
				if(model.getAmount()>outstandingbalance)
				{

				logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Failed: Update Payment Fail. Cannot pay amount higher than your outstanding balance.");
				return 1;	
				
				
				}else
					
				if (model.getAmount()<=outstandingbalance)
				{
				
					String updateDailyTransferReport = "update daily_transfer_report set payment_date = now(), amount_paid = ?, payment_status = ? where companyid = ?";
					
					int row2 = getJdbcTemplate().update(updateDailyTransferReport, new Object[] { 
							model.getAmount(),"paid",model.getCompanyid()
						});
					
					if(row2 > 0)
					{
						
						StringBuilder updateOutstandingbalance = new StringBuilder();
						
						updateOutstandingbalance.append("Update credit_limit set outstanding_balance = outstanding_balance - ?,");
						updateOutstandingbalance.append("remaining_balance  = case when remaining_balance - ? > 0 then ? else ");
						updateOutstandingbalance.append("remaining_balance = remaining_balance - remaining_balance end where companyid = ? and status = ? and outstanding_balance - ? >=0");
					
						int row3 = getJdbcTemplate().update(updateOutstandingbalance.toString(), new Object[] { 
								model.getAmount(),model.getAmount(),model.getAmount(),model.getCompanyid(),"approved",model.getAmount()
							});
						
						if(row2>0 && row3>0){
							
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful");
							return 0;
						}
						
						
					}
					
				}
				
					logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Failed: Update Payment Fail. Update Payment Fail. Cannot pay amount lower than your outstanding balance.");
					return 2;
				
			}
			
			
			
			
		}
		
		
		else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		{
			
		
			logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +"");
		
			
			if(oldstat.equals("unpaid"))
			{
				
				
				if(model.getStatus().equalsIgnoreCase("paid"))
				{
					
					String checkStatus = "select status from credit_logs where credit_id = ? and status = ?";
					
					
					SqlRowSet rs =getJdbcTemplate().queryForRowSet(checkStatus,model.getCreditid(),"paid");
					
					if(rs.next()){
						
						logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Cannot Process Request. Status already changed to unpaid");
						
						return 4;
						
					}
					
					String checkStatusCompleted = "select status from credit_logs where credit_id = ? and status = ? and delivery_status = ?";
					
					
					SqlRowSet rs2 =getJdbcTemplate().queryForRowSet(checkStatusCompleted,model.getCreditid(),"unpaid","requested");
					
					if(rs2.next()){
						
						logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Cannot Process Request. Delivery Status must be completed before paying.");
						
						return 5;
						
					}
					
					
					
					logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Unpaid Status to Paid Status");

						String updateOutstandingBalancePaid = "update credit_limit set outstanding_balance = outstanding_balance-?,remaining_balance = remaining_balance - ?,credit_id = ? where companyid = ? and status =?  and (outstanding_balance - ?) >= 0";
						
						int row = getJdbcTemplate().update(updateOutstandingBalancePaid, new Object[] { 
								model.getAmount(),model.getAmount(),model.getCreditid(),model.getCompanyid(),"approved",model.getAmount()
							});


						
						if(row>0)
						{
							
							
							String updateUnpaid = "update credit_logs set status = ?  where credit_id = ? ";
							
							int row3 = getJdbcTemplate().update(updateUnpaid, new Object[] { 
									model.getStatus(),model.getCreditid()
								});

							
							
							String insertPaymentPaid = "insert into credit_payment_logs (credit_id,amount,status,companyid,userid,date_payment,levelid) values (?,?,?,(select companyid from company_tbl where companyname = ?),?,now(),(select levelid from company_tbl where companyname = ?))";
							
							int row2 = getJdbcTemplate().update(insertPaymentPaid, new Object[] 
							{ 
											model.getCreditid(),model.getAmount(),model.getStatus(),session.getAttribute("companyname"),session.getAttribute("username"),session.getAttribute("companyname")
							});
							
							
							
//							String updatepaid = "update credit_logs set status = ?  where credit_id = ? ";
//							
//							int row3 = getJdbcTemplate().update(updatepaid, new Object[] { 
//									model.getStatus(),model.getCreditid()
//								});
							
							if(row>0 && row2>0 && row3>0)
							{
								
								logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful, Unpaid Status to Paid Status");
								
								return 0;
							}
						}
						

				}
				
				
			}
			else if(oldstat.equals("paid"))
			{
			
			 if(model.getStatus().equalsIgnoreCase("unpaid"))
				{
				 
				 
					String checkStatus = "select status from credit_logs where credit_id = ? and status = ?";
					
					
					SqlRowSet rs =getJdbcTemplate().queryForRowSet(checkStatus,model.getCreditid(),"cancelled");
					
					if(rs.next()){
						
						logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Cannot Process Request. Status already changed to cancelled");
						
						return 3;
						
					}
		
					logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Paid Status to Cancelled Status");
			
				 	
					String updateOutstandingBalanceCancel= "update credit_limit set outstanding_balance = outstanding_balance+?, remaining_balance = remaining_balance +?,credit_id = ? where companyid = ? and status =?  and (outstanding_balance + ?) >= 0 ";
					
					int row = getJdbcTemplate().update(updateOutstandingBalanceCancel, new Object[] { 
							model.getAmount(),model.getAmount(),model.getCreditid(),model.getCompanyid(),"approved",model.getAmount()
						});
					
				 
					if(row>0)
					{
			
					
//						String updateCancel = "update credit_payment_logs set status = ?, userid=? where credit_id = ? ";
//						
//						int row2 = getJdbcTemplate().update(updateCancel, new Object[] { 
//								"cancelled",session.getAttribute("username"),model.getCreditid()
//							});

						String updateCancel = "update credit_logs set status = ?  where credit_id = ? ";
						
						int row2 = getJdbcTemplate().update(updateCancel, new Object[] { 
								"unpaid",model.getCreditid()
							});

						
					 	
						if(row>0 && row2>0)
						{
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful, Unpaid Paid to Cancelled Status");
							return 0;
							
						}
						
						
						
					}
			
					
			
					
					
					
				}
				
				
				
			}else if(oldstat.equals("cancelled"))
			{
				

		
				if(model.getStatus().equalsIgnoreCase("paid"))
				{
					
					String checkStatus = "select status from credit_logs where credit_id = ? and status = ?";
					
					
					SqlRowSet rs =getJdbcTemplate().queryForRowSet(checkStatus,model.getCreditid(),"paid");
					
					if(rs.next()){
						
						logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Cannot Process Request. Status already changed to paid");
						
						return 4;
						
					}
					
					logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Cancelled Status to Paid Status");
					
					String updateOutstandingBalanceCancel= "update credit_limit set outstanding_balance = outstanding_balance-?, remaining_balance = remaining_balance - ?,credit_id = ? where  companyid = ? and status =? and  (outstanding_balance - ?) >= 0 ";
						
					int row = getJdbcTemplate().update(updateOutstandingBalanceCancel, new Object[] { 
								model.getAmount(),model.getAmount(),model.getCreditid(),model.getCompanyid(),"approved",model.getAmount()
					});
						

						if(row>0)
						{
						
//						String updateCancel = "update credit_payment_logs set status = ?, userid=? where credit_id = ? ";
//						
//						int row2 = getJdbcTemplate().update(updateCancel, new Object[] { 
//								"paid",session.getAttribute("username"),model.getCreditid()
//							});
						
						String updatePaid = "update credit_logs set status = ?  where credit_id = ? ";
						
						int row2 = getJdbcTemplate().update(updatePaid, new Object[] { 
								"paid",model.getCreditid()
							});

				
						if(row>0 && row2>0){
							
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful, , Unpaid Cancelled to Paid Status");
							return 0;
							
						}
						
						}
		
				}
				
				logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Fail");
				return 3;
				
			}
			
			
			
		}
		
		else if (rolename.equalsIgnoreCase("DSP Operations"))			
		{
			
	
			
			
			if(oldstat.equals("unpaid"))
			{
				
				if(model.getStatus().equalsIgnoreCase("paid"))
				{
					
					String updateOutstandingBalancePaid = "update credit_limit set outstanding_balance = outstanding_balance-?,remaining_balance = remaining_balance - ?,credit_id = ? where companyid = ? and status =? ";
					
					int row = getJdbcTemplate().update(updateOutstandingBalancePaid, new Object[] { 
							model.getAmount(),model.getAmount(),model.getCreditid(),model.getCompanyid(),"approved"
						});
		
					
					if(row>0)
					{
						String insertPaymentPaid = "insert into credit_payment_logs (credit_id,amount,status,companyid,userid,date_payment,levelid) values (?,?,?,(select companyid from company_tbl where companyname = ?),?,now(),(select levelid from company_tbl where companyname = ?))";
						
						int row2 = getJdbcTemplate().update(insertPaymentPaid, new Object[] 
						{ 
										model.getCreditid(),model.getAmount(),model.getStatus(),session.getAttribute("companyname"),session.getAttribute("username"),session.getAttribute("companyname")
						});
						
				
						
						
						if(row>0 && row2>0)
						{
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful");
							return 0;
						}
					}
					

				}
				
				
				
			}
			else if(oldstat.equals("paid"))
			{
				
			 if(model.getStatus().equalsIgnoreCase("cancel"))
				{
				 
					String updateOutstandingBalanceCancel= "update credit_limit set outstanding_balance = outstanding_balance+?, remaining_balance = remaining_balance +? where credit_id = ? and companyid = ? and status =? ";
					
					int row = getJdbcTemplate().update(updateOutstandingBalanceCancel, new Object[] { 
							model.getAmount(),model.getAmount(),model.getCreditid(),model.getCompanyid(),"approved"
						});
					
					if(row>0)
					{
						String updateCancel = "update credit_payment_logs set status = ?, userid=? where credit_id = ? and companyid = (select companyid from company_tbl where companyname = ?)";
						
						int row2 = getJdbcTemplate().update(updateCancel, new Object[] { 
								"cancelled",session.getAttribute("username"),model.getCreditid(),session.getAttribute("companyname")
							});
						
						if(row>0 && row2>0){
							
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful");
							return 0;
							
						}
						
					}

					
				}
				
				
				
			}else if(oldstat.equals("cancelled"))
			{
				
				if(model.getStatus().equalsIgnoreCase("paid"))
				{
					
					
					String updateOutstandingBalanceCancel= "update credit_limit set outstanding_balance = outstanding_balance-?, remaining_balance = remaining_balance - ? where credit_id = ? and companyid = ? and status =? ";
					
					int row = getJdbcTemplate().update(updateOutstandingBalanceCancel, new Object[] { 
							model.getAmount(),model.getCompanyid(),"approved"
						});
					
					if(row>0)
					{
			
						String updateCancel = "update credit_payment_logs set status = ?, userid=? where credit_id = ? and companyid = (select companyid from company_tbl where companyname = ?)";
						
						int row2 = getJdbcTemplate().update(updateCancel, new Object[] { 
								"paid",session.getAttribute("username"),model.getCreditid(),session.getAttribute("companyname")
							});
						
						if(row>0 && row2>0){
							
							logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Successful");
							return 0;
							
						}
						
					}

				}
				
				
				
			}
			

			
			
			
		}
		
		

			
		logger.info("Update payment for company: "+model.getCompanyid()+", amount: "+model.getAmount() +", Update Payment Fail");
		return 3;
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
	
	   

	   

		public boolean cancelPayment(String amount,String status,int cid, HttpSession session){
		    
			   int credid = 0;
			   int paymentlevelid = 0;
			   int lvl = Integer.parseInt(session.getAttribute("levelid").toString());
			   String stat = null;
				
			   String getcid = "select credit_id, from credit_payment_logs where credit_id = ?";
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getcid, cid);
			   
				for (Map row : rows) {

					credid = (Integer)(row.get("credit_id"));
					paymentlevelid = (Integer)(row.get("payment_levelid"));
					stat =  (String)(row.get("status"));
					
				}
				
				
				if(credid == 0){
					
					return true;
					
				}else{
					
					if(lvl == paymentlevelid)
					{
						
						   StringBuilder strSQL = new StringBuilder();
				   		   
						   strSQL.append("update credit_payment_logs set credit_id = ?,amount = ? , status = ?,payment_levelid = ?");
						   strSQL.append("where credit_id = ?");					   
						   try{
							   
								int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
									cid,Integer.parseInt(amount),status,session.getAttribute("levelid"),cid
								});

								
								if(row>0){
									return true;
								}
									
						
						   }catch(DataAccessException ex){
					            ex.printStackTrace();
					            return false;
					        }
						
						
					}else if (lvl != paymentlevelid && !status.equalsIgnoreCase("paid")){
						
						StringBuilder strSQL = new StringBuilder();
				   		   
						   strSQL.append("update credit_payment_logs set credit_id = ?,amount = ? , status = ?,payment_levelid = ?");
						   strSQL.append("where credit_id = ?");					   
						   try{
							   
								int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
									cid,Integer.parseInt(amount),status,session.getAttribute("levelid"),cid
								});

								
								if(row>0){
									return true;
								}
									
						
						   }catch(DataAccessException ex){
					            ex.printStackTrace();
					            return false;
					        }
						
						
						
					}
					
					
				}
				
		
	
			   
			return false;

			
		}
		
		
		public boolean checkRetailerCreditLimit(HttpSession session){
			
			String rolename = session.getAttribute("rolename")+"";
			
			if (rolename.equalsIgnoreCase("Retailer Operations"))
			{
				
				String checkcreditlimit = "select creditlimit from credit_limit where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and remaininglimit <> 0";
				
				SqlRowSet rs =getJdbcTemplate().queryForRowSet(checkcreditlimit,session.getAttribute("companyname"),"approved");
				
				if(rs.next()){
					
					return true;
				}
				
			}
			
	
			return false;
		}
		
		
public boolean resendTransfer(int cid, HttpSession session){
			
			String rolename = session.getAttribute("rolename")+"";
			
			if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
				   StringBuilder strSQL = new StringBuilder();
				   
		 		  strSQL.append("SELECT  c.companyname,c.parentcompanyid,e.credit_id,DATE_FORMAT(f.createddate,'%m/%d/%Y') as createdDate,f.amount,f.transfer_status,DATE_FORMAT(f.transferdate,'%m/%d/%Y') as transferDate ");
		 		  strSQL.append("FROM company_tbl c ");
		 		  strSQL.append("INNER JOIN  company_tbl d ON c.companyid = d.companyid ");
		 		  strSQL.append("INNER JOIN credit_logs e ON d.companyid = e.companyid ");
		 		  strSQL.append("INNER JOIN credit_transfer_logs f ON e.credit_id = f.credit_id  ");
		 		  strSQL.append("INNER JOIN company_tbl g ON d.parentcompanyid = g.companyid ");
		 		  strSQL.append("WHERE g.parentcompanyid = (select companyid from company_tbl where companyname = ?) and f.credit_id = ?");
				
				SqlRowSet rs =getJdbcTemplate().queryForRowSet(strSQL.toString(),session.getAttribute("companyname"),cid);
				
				if(rs.next()){
					
						String update = "update credit_transfer_logs set transfer_status = ? where credit_id =?";
					   try{
						   
							int row = getJdbcTemplate().update(update.toString(), new Object[] { 
								"completed",rs.getInt("credit_id")
							});

							
							if(row>0){
								return true;
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return false;
				        }
				}
				
			}
			
	
			return false;
		}
	
	
	public int getAvailableLimit(HttpSession session){
		
		int balance = 0;
		
		String getAvailable = "select remaininglimit,remaining_balance from credit_limit where status = ? and companyid = (select companyid from company_tbl where companyname = ?)";
		
		
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(getAvailable,"approved",session.getAttribute("companyname"));
		
		if(rs.next()){
			
			balance = rs.getInt("remaininglimit") -  rs.getInt("remaining_balance");
			
		}
		
		return balance;
	}
	
	
	public List<DailyTransferReportModel> getDailyTransferReport(){
		
	      	   ArrayList<DailyTransferReportModel> transferlist= new ArrayList<DailyTransferReportModel>();

	 		   StringBuilder strSQL = new StringBuilder()	;
	 		   
	 		   strSQL.append("SELECT transferreport_id,DATE_FORMAT(transferfrom_time,'%d %b %Y %T:%f') as fromtime,DATE_FORMAT(transferto_time,'%d %b %Y %T:%f') as totime,OPENSIM,amount_transfered,inprogress_amount  ");
	 		   strSQL.append("FROM daily_transfer_report");


	 		  	 
			   transferlist.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			   
				for (Map row : rows) {

					DailyTransferReportModel  transfer = new DailyTransferReportModel();
					
					transfer.setTransferreportid((Integer)(row.get("transferreport_id")));
					transfer.setTransferfromtime((String)(row.get("fromtime")));
					transfer.setTransfertotime((String)(row.get("totime")));
					transfer.setOpensim((String)(row.get("OPENSIM")));
					transfer.setAmount((Float)(row.get("amount_transfered")));
					transfer.setInprogressamount((Float)(row.get("amount_transfered")));
					

	

							
					transferlist.add(transfer);			
				}
		
		
				
				return transferlist;
		
		
	}
	
	
	public List<OpenSimModel> getOpenSimBalance(){
		
   	   ArrayList<OpenSimModel> opensimlist= new ArrayList<OpenSimModel>();

		   StringBuilder strSQL = new StringBuilder()	;
		   
			  strSQL.append("SELECT DATE_FORMAT(balance_date,'%d %b %Y %T') as baldate,opensim,balance   ");
			  strSQL.append("FROM opensim_balance_history ORDER BY balance_date desc");


		  	 
		   opensimlist.clear();
		 
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
		   
			for (Map row : rows) {

				OpenSimModel  opensim = new OpenSimModel();
				
				opensim.setDate((String)(row.get("baldate")));
				opensim.setOpensim((String)(row.get("opensim")));
				opensim.setBalance((Float)(row.get("balance")));

				opensimlist.add(opensim);			
			}
	
	
			
			return opensimlist;
	
	
}

	
	

}
