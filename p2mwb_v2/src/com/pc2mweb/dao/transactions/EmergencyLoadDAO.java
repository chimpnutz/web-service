package com.pc2mweb.dao.transactions;

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

import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.controller.EmergencyLoadManagementController;
import com.pc2mweb.model.CreditModel;
import com.pc2mweb.model.EmergencyLoadHistoryModel;
import com.pc2mweb.model.EmergencyLoadManagementModel;
import com.pc2mweb.model.EmergencyLoadModel;
import com.pc2mweb.model.TransactionHistoryModel;

public class EmergencyLoadDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(EmergencyLoadDAO.class);
	
	public Map fillBox(HttpSession usersession){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		  // strSQL.append("SELECT case when branchname is null then branchid else branchname end as branchname, branchid  from branches where partnerid = ?");
		   //strSQL.append("SELECT  branchid  from branches where paymenttype = 'PREPAID' AND partnerid = ?");
		 
		    strSQL.append("SELECT  branchid  from branches where paymenttype = 'PREPAID' AND partnerid = ?");
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"));
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
					
			}
			
			return prefix;
			
	}
	
	public Map availBranch(HttpSession usersession){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		  // strSQL.append("SELECT case when branchname is null then branchid else branchname end as branchname, branchid  from branches where partnerid = ?");
		   //strSQL.append("SELECT  branchid  from branches where paymenttype = 'PREPAID' AND partnerid = ?");
		   strSQL.append("SELECT  branchid  from  credit_limit where  partnerid = ? and branchid is not null and amount <> 0");
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"));
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
				//prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
		
			}
			
			return prefix;
			
	}
	
	public BigDecimal getWallet(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   BigDecimal wallet = null;
		   String type = null;
		   
		   strSQL.append("SELECT wallet,paymenttype from branches where partnerid = ? and branchid = ?");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),session.getAttribute("BID"));
			
		   for (Map row : rows) {
			   
			   type = (String)row.get("paymenttype");
			   wallet = (BigDecimal)row.get("wallet");   
			   
			   if(!type.equalsIgnoreCase("TRANSIENT")){
				   
				   return wallet;
				   
			   }

			   else if(type.equalsIgnoreCase("TRANSIENT")){
			   
			   StringBuilder getType = new StringBuilder();
			   
			   getType.append("SELECT wallet,paymenttype from partners where partnerid = ?");
			   
			   List<Map<String,Object>> rowsTrans = getJdbcTemplate().queryForList(getType.toString(),session.getAttribute("PID"));
			   
			   for (Map rowTran : rowsTrans) {

				   wallet = (BigDecimal)rowTran.get("wallet");   
				  
			   }
			   
			   return wallet;
			   
		   	}
			   			   
		   }
		return wallet;  
		   
			  
		
	}
	
	
	public boolean getHoliday(String today,HttpSession session){
		
		 String paymenttype = session.getAttribute("type").toString();
		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
		 DataSource ds = (DataSource) context.getBean("dataSource");
						
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			
		//	SqlRowSet rs = jdbcTemplate.queryForRowSet(checkifBIDexist.toString(), new Object[] {pid,frDate,"approved",pid} );	
		 
		 if(paymenttype.equalsIgnoreCase("transient")){
			 
			 StringBuilder strSQL = new StringBuilder();
			 
//			 strSQL.append("SELECT valid_from,valid_to from credit_limit ");
//			 strSQL.append("WHERE valid_from <= TO_DATE(?, 'MM-DD-YYYY') ");    
//			 strSQL.append("AND valid_to >= TO_DATE(?, 'MM-DD-YYYY') and partnerid = ? and status = ?");
			 
			 strSQL.append("SELECT valid_from,valid_to from credit_limit ");
			 strSQL.append("WHERE ");    
			 strSQL.append(" partnerid = ? and status = ?");
			 
			 SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), new Object[] {session.getAttribute("PID"),"approved"} );	
			 
			 while(rs.next())
			 {			 
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				 String validfr = simpleDateFormat.format(rs.getString("valid_from"));
				 String validto = simpleDateFormat.format(rs.getString("valid_to"));
				 try {
					Date dateToday = simpleDateFormat.parse(today);
					Date validFr = simpleDateFormat.parse(validfr);
					Date validTo = simpleDateFormat.parse(validto);
					
					if(dateToday.after(validFr) && dateToday.before(validTo)){
						
						return true;
						
					}
				
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
				 
				 
			 }
			 
			 return false;
//			 List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),today,today,session.getAttribute("PID"),session.getAttribute("BID"),"approved");
//				
//			 for (Map row : rows) {
//				 
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY");
//					
//					String from_string = simpleDateFormat.format(row.get("valid_from"));
//					String to_string = simpleDateFormat.format(row.get("valid_to"));
//					
//				     if(from_string != null && to_string != null){	
//				    	 
//					   		return true;
//				        }else{
//				        	return false;
//				        }
//				 }
//				 
//				 return false;
				 
		 }
		 
		 else
		 {
			 
			 StringBuilder strSQL = new StringBuilder();
			 
			 strSQL.append("SELECT valid_from,valid_to from credit_limit ");
			 strSQL.append("WHERE ");    
			 strSQL.append("partnerid = ? and branchid = ? and status = ?");
			 
//			 strSQL.append("SELECT valid_from,valid_to from credit_limit ");
//			 strSQL.append("WHERE valid_from <= TO_DATE(?, 'MM-DD-YYYY') ");    
//			 strSQL.append("AND valid_to >= TO_DATE(?, 'MM-DD-YYYY') and partnerid = ? and branchid = ? and status = ?");
			 
			 SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), new Object[] {session.getAttribute("PID"),session.getAttribute("BID"),"approved"} );	
			 
			 while(rs.next())
			 {
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				 Date validfr = rs.getDate("valid_from");
				 Date validto = rs.getDate("valid_to");
				 
				 try {
					 
					Date dateToday = simpleDateFormat.parse(today);

					

					if(dateToday.after(validfr) && dateToday.before(validto)){
						
						return true;
						
					}
				
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
			 }
	 
			 return false;
//			 List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),today,today,session.getAttribute("PID"),session.getAttribute("BID"),"approved");
//				
//			 for (Map row : rows) {
//				 
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY");
//					
//					String from_string = simpleDateFormat.format(row.get("valid_from"));
//					String to_string = simpleDateFormat.format(row.get("valid_to"));
//					
//				     if(from_string != null && to_string != null){	
//				    	 
//					   		return true;
//				        }else{
//				        	return false;
//				        }
//				 }
//				 
//				 return false;
		 }
	

		 
		

	}
	
	
	 public int addCredit(HttpSession session,CreditModel cModel)
	  {	
		 	String paymenttype = this.getPaymenttype(session);
		 	
		 	if(paymenttype.equalsIgnoreCase("TRANSIENT"))
		 	{
		 		
		 		String updateTxPartner = "Update partners set wallet = wallet + ? ,transactionid = ? where  partnerid = ? and (wallet + ?) >= 0";
		 		
		 		String updatePIDcreditlimit = "";
		 		
				try{
			 		 
					int row = getJdbcTemplate().update(updateTxPartner, new Object[] { 
							cModel.getAmount(),this.getTransid(),session.getAttribute("PID"),cModel.getAmount()
					});
					
					
					if(row>0)
					{
						
						return 1;
						
//						String updateCredit = "Update credit_limit set amount = amount - ?, where  partnerid = ? and (amount - ?) >= 0";
//						
//						try
//						{
//							
//							int cRows = getJdbcTemplate().update(updateCredit, new Object[] { 
//									cModel.getAmount(),session.getAttribute("PID"),cModel.getAmount()
//							});
//							
//							if(cRows>0){
//								
//								return 1;
//							}
//							
//							
//						}catch(Exception ex){
//					         ex.printStackTrace();
//					         return 0;
//					     }
						
					}
					
					}catch(Exception ex){
				         ex.printStackTrace();
				         return 0;
				     }
					
				
		 	}
		 	else if(paymenttype.equalsIgnoreCase("PREPAID"))
		 	{
		 		
		 		String updateTxBranch = "Update branches set  wallet = wallet + ?, transactionid = ? where branchid = ? and partnerid = ? and paymenttype='PREPAID' and (wallet + ?) >= 0";
		 		
		 		String updateBIDcreditlimit = "";
		 		
				try{
			 		 
					int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
							cModel.getAmount(),this.getTransid(),session.getAttribute("BID"),session.getAttribute("PID"),cModel.getAmount()
					});
					
					
					if(row>0)
					{
						
						return 1;
//						String updateCredit = "Update credit_limit set  amount = amount - ? where branchid = ? and partnerid = ?  and (amount - ?) >= 0";
//						
//						int cRow = getJdbcTemplate().update(updateCredit, new Object[] { 
//								cModel.getAmount(),session.getAttribute("BID"),session.getAttribute("PID"),cModel.getAmount()
//						});
//						
//						if(cRow>0){
//							
//							return 1;
//						}
															
						
					}
					}catch(Exception ex){
				         ex.printStackTrace();
				         return 0;
				     }
		 	}			

			return 0;
			

	   }
	 

		public BigDecimal getCreditWallet(HttpSession session,String paymenttype)
		{
			
			   StringBuilder strSQL = new StringBuilder();
			   
			   BigDecimal creditWallet = null;
			   String type = null;
			   
			   if(paymenttype.equalsIgnoreCase("TRANSIENT")){
				   
				   strSQL.append("SELECT amount,status from credit_limit where partnerid = ?");
				   
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
					
				   for (Map row : rows) {
					   
					   creditWallet = (BigDecimal)  row.get("amount");
					   return creditWallet;
				   }
				   
			   }else    if(paymenttype.equalsIgnoreCase("PREPAID"))
			   {
				   
				   strSQL.append("SELECT amount,status from credit_limit where partnerid = ? and branchid = ?");
				   
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),session.getAttribute("BID"));
					
				   for (Map row : rows) {
					   
					   creditWallet = (BigDecimal)  row.get("amount");
					   
					   return creditWallet;
				   }
			   }
				   

			return creditWallet;
	 
	 
	 
	}
		
		public boolean getCreditStatus(HttpSession session)
		{
			
			 StringBuilder strSQL = new StringBuilder();
				
				
			 strSQL.append("SELECT * from credit_logs ");
			 strSQL.append("where status = 'partial' OR status = 'unpaid' and approval_status='approved' and partnerid = ? and branchid = ? ");    

			 List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),session.getAttribute("BID"));
			
			 for (Map row : rows) {
				 
				 
				 return true;

			 }
			 
			 return false;
			
		}
		
		 public CreditModel getCreditInfo(HttpSession session)
		 {

			 StringBuilder strSQL = new StringBuilder();
							
			 strSQL.append("SELECT partnerid,branchid,amount,status from credit_limit ");
			 strSQL.append("WHERE partnerid = ? AND  branchid = ? AND status = ?");    
			 
			 List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),session.getAttribute("BID"),"approved");
		
			   for (Map row : rows) {
				   
				   CreditModel cModel = new CreditModel();
				   
				   BigDecimal amt = (BigDecimal) row.get("amount");
				   String bid = (String) row.get("branchid");
				   
				   cModel.setAmount((BigDecimal) row.get("amount"));
				   cModel.setStatus((String) row.get("status"));
				   cModel.setTerm((BigDecimal) row.get("credit_term"));
				   
				   if(bid.equals(null))
				   {
					   cModel.setPaymenttype("TRANSIENT");
				
				   }else{
					   cModel.setPaymenttype("PREPAID");
				   }

				   
				   return cModel;
				   
			   }
			 
			 
			return null;
			 
		 }
		 
			public BigDecimal getCreditId(){
				
				StringBuilder strSQL = new StringBuilder();
				 
				   BigDecimal txid = null;
				   
				   strSQL.append("select creditid_seq.nextval as transid from dual");
				
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
					
				   for (Map row : rows) {
						
					   txid = (BigDecimal)row.get("transid");
					   
					   return txid;
					}
					
				   return txid;
				
				
			}
			
		public String getTransid(){
				
				StringBuilder strSQL = new StringBuilder();
				 
				   String txid = "WBC";
				   
				   strSQL.append("select creditid_seq.nextval as transid from dual");
				
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
					
				   for (Map row : rows) {
						
					   txid = txid+(BigDecimal)row.get("transid");
				
					}
					
				   return txid;
				
				
			}
		 
		 
		public boolean insertCreditLog(HttpSession usersession,CreditModel cModel){
				

				   StringBuilder strSQL = new StringBuilder();
				   
				   Date sysDate = new Date();
				   String dateString = sysDate.toString(); 
				   
				   strSQL.append("INSERT INTO credit_logs  ");
				   strSQL.append("(credit_id,partnerid,branchid,amount,status,approval_status,date_due,date_borrowed) ");
				   strSQL.append(" VALUES (?,?,?,?,?,?,systimestamp+7 ,systimestamp) ");
				   
				   
				   try{
					   
						int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
							this.getCreditId(),usersession.getAttribute("PID"),usersession.getAttribute("BID"),cModel.getAmount(),
							"initial","approved"
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
	
		public boolean checkCreditLimitStatus(HttpSession usersession){
			
			 StringBuilder strSQL = new StringBuilder();
			
			 DateFormat  df = new SimpleDateFormat("MM/dd/YYYY");
			 Date today = Calendar.getInstance().getTime(); 
				
			 strSQL.append("SELECT branchid,status from credit_limit ");
			 strSQL.append("WHERE partnerid = ? and branchid is null and VALID_FROM <= TO_DATE(?, 'MM-DD-YYYY') ");    
			 strSQL.append("AND VALID_TO >= TO_DATE(?, 'MM-DD-YYYY') AND status = ? ");
			
			 
			 List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"),
					df.format(today),df.format(today),"approved");
			
			 for (Map row : rows) 
			 {
				 
				 String stat = (String)(row.get("status"));
				 String bid = (String)(row.get("branchid"));
				 	 		
				 if(stat.equals("approved")){
					 return true;
				 }else
				 {
					 return false;
				 }

			 }
			 
			 return false;
		}
		
		 public boolean transfertoBranch(HttpSession usersession,String bid, BigDecimal amount)
		   {
				ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
				
				DataSource ds = (DataSource) context.getBean("dataSource");
							
				JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
				
				String pid = usersession.getAttribute("PID").toString();
				
				String deductCreditLimit = "Update credit_limit set amount = amount - ? where  partnerid = ? and branchid is null and (amount - ?) >= 0";
				
				String addCreditLimit= "Insert into credit_limit (partnerid,branchid,amount,status,created_date,valid_from,valid_to) values (?,?,?,?," +
						"(Select created_date from credit_limit where partnerid = ? and branchid is null)," +
						"(Select valid_from from credit_limit where partnerid = ? and branchid is null)," +
						"(Select valid_to from credit_limit where partnerid = ? and branchid is null))";
				
				String checkifBIDexist = "select branchid from credit_limit where partnerid = ? and branchid = ?";

				String updateCreditimit = "update credit_limit set amount = amount + ? where partnerid = ? and branchid = ? and (amount + ?) >= 0";
			
				try{
	
				    SqlRowSet rs = jdbcTemplate.queryForRowSet(checkifBIDexist, new Object[] {pid,bid} );	
					
				    if(rs.next()) 
				    {
				    	
						int row3 = getJdbcTemplate().update(deductCreditLimit, new Object[] { 
								amount,usersession.getAttribute("PID"),amount
						});
						
						int row2 = 0;
						
						if ( row3 > 0)
						{
						    row2 = getJdbcTemplate().update(updateCreditimit, new Object[] { 
						    		amount,pid,bid,amount
						});
						
						}
						
						if(row3>0 && row2>0){
							return true;
						}
				    }
				    
				    else
				    {
						int row3 = getJdbcTemplate().update(deductCreditLimit, new Object[] { 
								amount,usersession.getAttribute("PID"),amount
						});
						
						int row2 = 0;
						
						if ( row3> 0)
						{
						    row2 = getJdbcTemplate().update(addCreditLimit, new Object[] { 
						    		pid,bid,amount,"approved",pid,pid,pid
						});
						
						}
						
						if(row3>0 && row2>0){
							return true;
						}
				    }
				   
				}
				catch(Exception ex)
				{
	            ex.printStackTrace();
	            return false;
				}
				return false;
				

		   }
		 
		 public boolean revokefromBranch(HttpSession usersession,String bid, BigDecimal amount)
		   {
				
  
				String deductCreditLimit = "Update credit_limit set amount = amount - ? where  partnerid = ? and branchid = ? and (amount - ?) >= 0";
				
				String addCreditLimit= "Update credit_limit set amount = amount + ? where  partnerid = ? and branchid is null and (amount + ?) >= 0";
				

				try{
					 		 
					int row = getJdbcTemplate().update(deductCreditLimit, new Object[] { 
							amount,usersession.getAttribute("PID"),bid,amount
					});
					
					int row2 = 0;
					
					if ( row > 0)
					{
					    row2 = getJdbcTemplate().update(addCreditLimit, new Object[] { 
					    		amount,usersession.getAttribute("PID"),amount
					});
					
					}
					
					if(row>0 && row2>0){
						return true;
					}
						
					
				
				}catch(Exception ex){
	            ex.printStackTrace();
	            return false;
	        }
				return false;
				

		   }
		 
			public BigDecimal getAvailableCreditLimit(HttpSession session)
			{
				
				   StringBuilder strSQL = new StringBuilder();
				   
				   BigDecimal wallet = null;
				   String type = null;
				   
				   strSQL.append("SELECT amount from credit_limit where partnerid = ? and branchid is null and status = ?");
				   
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"approved");
					
				   for (Map row : rows) {
					   		
					   wallet = (BigDecimal)row.get("amount");   
					   
					   return wallet;
					   
				   	}
					   			   
				   
				return wallet;  
				   
					  
				
			}
			
			public BigDecimal getAvailableCreditLimitforBranch(HttpSession session, EmergencyLoadManagementModel emodel)
			{
				
				   StringBuilder strSQL = new StringBuilder();
				   
				   BigDecimal wallet = null;
				   String type = null;
				   
				   strSQL.append("SELECT amount from credit_limit where partnerid = ? and branchid = ? and status = ?");
				   
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),emodel.getBranch(),"approved");
					
				   for (Map row : rows) {
					   		
					   wallet = (BigDecimal)row.get("amount");   
					   
					   return wallet;
					   
				   	}
					   			   
				   
				return wallet;  
				   
					  
				
			}
			
			 public boolean insertcreditLimit(HttpSession usersession,EmergencyLoadModel eModel)
			   {
					String pid = usersession.getAttribute("PID").toString();
					
					int row;
					BigDecimal amount = new BigDecimal(eModel.getAmount());
					
					StringBuilder strSQL = new StringBuilder();
							
					strSQL.append("insert into credit_limit (partnerid,amount,status,created_date,valid_from,valid_to) ");
					strSQL.append("values (?,?,?,CURRENT_TIMESTAMP,TO_DATE(?, 'MM-DD-YYYY'),TO_DATE(?, 'MM-DD-YYYY'))");
					
					try{
						 		 
						 row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						    		pid,amount,"initial",eModel.getFrdate(),eModel.getTodate()
						});
						
						
						
						if(row>0){
							return true;
						}
							
						
					
					}catch(Exception ex){
		            ex.printStackTrace();
		            return false;
		        }
					return false;
					

			   }
			 
			 
				public List<EmergencyLoadHistoryModel>  getHistory(HttpSession usersession)
				   {
					   String usertype = (String) usersession.getAttribute("USERLEVEL");
					   String type = (String) usersession.getAttribute("type");
					   String pid = (String) usersession.getAttribute("PID");
					   String bid = (String) usersession.getAttribute("BID");
					   
					   
					   if(usertype.equalsIgnoreCase("manager")){
						  
						   ArrayList<EmergencyLoadHistoryModel> userlogs = new ArrayList<EmergencyLoadHistoryModel>();
							
						   StringBuilder strSQL = new StringBuilder()	;
						   
						   strSQL.append("SELECT a.amount,a.status,a.branchid,a.valid_from,a.valid_to, ");
						   strSQL.append("b.amount AS availed, b.date_due ");
						   strSQL.append("from credit_limit a, credit_logs b where a.partnerid = b.partnerid ");
						   strSQL.append("AND b.partnerid = ?");
						   
						   userlogs.clear();
						 
							List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid);
							for (Map row : rows) {
								
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String validfr = simpleDateFormat.format(row.get("valid_from"));
								String validto = simpleDateFormat.format(row.get("valid_to"));
								String due = simpleDateFormat.format(row.get("date_due"));
								
								EmergencyLoadHistoryModel logs = new EmergencyLoadHistoryModel();
								
								logs.setAmount((BigDecimal)(row.get("amount")));
								logs.setStatus((String)(row.get("status")));
								logs.setBranch((String)(row.get("branchid")));
								logs.setValidfrom(validfr);
								logs.setValidto(validto);
								logs.setAvailedamount((BigDecimal)(row.get("availed")));
								logs.setDuedate(due);
								userlogs.add(logs);			
							}
						
							return userlogs;
							
					   }
					   
					   else if (usertype.equalsIgnoreCase("user") && type.equalsIgnoreCase("prepaid") )
					   
					   {
						   
						   ArrayList<EmergencyLoadHistoryModel> userlogs = new ArrayList<EmergencyLoadHistoryModel>();
							
						   StringBuilder strSQL = new StringBuilder()	;
						   
						   strSQL.append("SELECT a.amount,a.status,a.branchid,a.valid_from,a.valid_to, ");
						   strSQL.append("b.amount AS availed, b.date_due ");
						   strSQL.append("from credit_limit a, credit_logs b where a.partnerid = b.partnerid and a.branchid = b.branchid");
						   strSQL.append("AND b.partnerid = ? and b.branchid = ?");
						   
						   userlogs.clear();
						 
							List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid,bid);
							for (Map row : rows) {
								
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String validfr = simpleDateFormat.format(row.get("valid_from"));
								String validto = simpleDateFormat.format(row.get("valid_to"));
								String due = simpleDateFormat.format(row.get("date_due"));
								
								EmergencyLoadHistoryModel logs = new EmergencyLoadHistoryModel();
								
								logs.setAmount((BigDecimal)(row.get("amount")));
								logs.setStatus((String)(row.get("status")));
								logs.setBranch((String)(row.get("branchid")));
								logs.setValidfrom(validfr);
								logs.setValidto(validto);
								logs.setAvailedamount((BigDecimal)(row.get("availed")));
								logs.setDuedate(due);
								userlogs.add(logs);			
							}
						
							return userlogs;
						   
						   
						   
						   
					   }else{
						   
						   ArrayList<EmergencyLoadHistoryModel> userlogs = new ArrayList<EmergencyLoadHistoryModel>();
							
						   StringBuilder strSQL = new StringBuilder()	;
						   
						   strSQL.append("SELECT a.amount,a.status,a.branchid,a.valid_from,a.valid_to, ");
						   strSQL.append(" b.date_due ");
						   strSQL.append("from credit_limit a, credit_logs b where ");
						   strSQL.append("  a.partnerid = b.partnerid and a.partnerid = ? and a.branchid is null");
						   
						   userlogs.clear();
						 
							List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid);
							for (Map row : rows) {
								
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String validfr = simpleDateFormat.format(row.get("valid_from"));
								String validto = simpleDateFormat.format(row.get("valid_to"));
								String due = simpleDateFormat.format(row.get("date_due"));
								
								EmergencyLoadHistoryModel logs = new EmergencyLoadHistoryModel();
								
								logs.setAmount((BigDecimal)(row.get("amount")));
								logs.setStatus((String)(row.get("status")));
								logs.setBranch((String)(row.get("branchid")));
								logs.setValidfrom(validfr);
								logs.setValidto(validto);
								logs.setAvailedamount((BigDecimal)(row.get("availed")));
								logs.setDuedate(due);
								userlogs.add(logs);			
							}
						
							return userlogs;
						   
						   
						   
						   
					   }
					
					   
					   	  
				   }
		
				
				public List<EmergencyLoadHistoryModel>  getListCreditLimit(HttpSession usersession)
				   {
					   String usertype = (String) usersession.getAttribute("USERLEVEL");
					   String type = (String) usersession.getAttribute("type");
					   String pid = (String) usersession.getAttribute("PID");
					   String bid = (String) usersession.getAttribute("BID");
					   
					   					
						   ArrayList<EmergencyLoadHistoryModel> userlogs = new ArrayList<EmergencyLoadHistoryModel>();
							
						   StringBuilder strSQL = new StringBuilder()	;
						   
						   strSQL.append("SELECT branchid,amount,status,credit_term,valid_from,valid_to ");
						   strSQL.append("from credit_limit ");
						   strSQL.append("where partnerid = ? and branchid is not null and amount <> 0");
						   
						   userlogs.clear();
						 
							List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid);
							for (Map row : rows) {
								
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String validfr = simpleDateFormat.format(row.get("valid_from"));
								String validto = simpleDateFormat.format(row.get("valid_to"));
								//String due = simpleDateFormat.format(row.get("date_due"));
								
								EmergencyLoadHistoryModel logs = new EmergencyLoadHistoryModel();
								
								logs.setAmount((BigDecimal)(row.get("amount")));
								logs.setStatus((String)(row.get("status")));
								logs.setBranch((String)(row.get("branchid")));
								logs.setValidfrom(validfr);
								logs.setValidto(validto);
								//logs.setAvailedamount((BigDecimal)(row.get("availed")));
								//logs.setDuedate(due);
								userlogs.add(logs);			
							}
						
							return userlogs;
   	  
				   }
		
				public String getPaymenttype(HttpSession usersession){
					
					   StringBuilder strSQL = new StringBuilder();
					   
					   String type = null;
					   
					   strSQL.append("SELECT paymenttype from branches where partnerid = ? and branchid = ?");
					   
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"),usersession.getAttribute("BID"));
						
					   for (Map row : rows) {
						   		
						   type = (String)row.get("paymenttype");   
						   
						   return type;
						   
					   	}
						   			   
					   
					return type;  
					
					
				}

}
