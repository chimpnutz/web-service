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


public class CreditExtendedDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(CreditExtendedDAO.class);
	

	
	public List<CreditExtendedModel>  getListCreditExtended()
	   {
			
			   ArrayList<CreditExtendedModel> userlogs = new ArrayList<CreditExtendedModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT * from credit_logs where status <> ? ");
			   
			   userlogs.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"paid");
			   
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					//String validfr = simpleDateFormat.format(row.get("valid_from"));
					//String validto = simpleDateFormat.format(row.get("valid_to"));
					//String due = simpleDateFormat.format(row.get("date_due"));
					
					CreditExtendedModel logs = new CreditExtendedModel();
					logs.setCreditid((BigDecimal)row.get("credit_id"));
					logs.setPid((String)row.get("partnerid"));
					logs.setBid((String)row.get("branchid"));
					logs.setAmount_paid((BigDecimal)row.get("amount_paid"));
					logs.setStatus((String)row.get("status"));
		
	
					//logs.setAvailedamount((BigDecimal)(row.get("availed")));
					//logs.setDuedate(due);
					userlogs.add(logs);			
				}
			
				return userlogs;

	   }
	
	
	
	
	public int revokePayment(CreditExtendedModel cExtended)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		DataSource ds = (DataSource) context.getBean("dataSource");
					
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		BigDecimal cid = cExtended.getCreditid();
		String pid = cExtended.getPid();
		String bid = cExtended.getBid();
		BigDecimal amt = cExtended.getAmount_paid();
		
		String getusedCredit = "Select amount,amount_paid from credit_logs where credit_id = ? and partnerid = ? and branchid = ? and status <> ?";
		
		StringBuilder revokeSql = new StringBuilder();
		
		revokeSql.append("insert into credit_payment_logs (credit_id,partnerid,branchid,amount,date_payment,status) ");
		revokeSql.append("values (?,?,?,?,current_timestamp,?)");
		
		try
		{
			
			SqlRowSet rs = jdbcTemplate.queryForRowSet(getusedCredit, new Object[] {cid,pid,bid,"paid"} );	
			
			if(rs.next())
			{
				
				BigDecimal bal = rs.getBigDecimal("amount").subtract(rs.getBigDecimal("amount_paid"));
				
				if(bal.compareTo(amt) == -1){
					
					System.out.println(bal.compareTo(amt));
					System.out.println(amt);
					System.out.println(bal);
					
					return 3;
				}
				
				int row = getJdbcTemplate().update(revokeSql.toString(), new Object[] { 
		    		cid,pid,bid,amt,"revoked"
					});
				

					if(row>0)
					{
						
						
						String type = this.getPayment(pid, bid);
						
						
						if(type.equalsIgnoreCase("transient"))
						{
					 		String updateTxPartner = "Update partners set wallet = wallet + ? ,transactionid = ? where  partnerid = ? and (wallet + ?) >= 0";
					 		

					 		try{
					 			
							int row1 = getJdbcTemplate().update(updateTxPartner, new Object[] { 
									amt,this.getTransid(),pid,amt
							});
							
							
							if(row1>0)
							{
								
								return 1;
								
							}
							
							}catch(Exception ex){
						         ex.printStackTrace();
						         return 0;
						     }
							
						}
						
						else if (type.equalsIgnoreCase("prepaid")){
							
							String updateTxBranch = "Update branches set  wallet = wallet + ?, transactionid = ? where branchid = ? and partnerid = ? and paymenttype='PREPAID' and (wallet + ?) >= 0";
					 		
					 		
							try{
						 		 
								int row3 = getJdbcTemplate().update(updateTxBranch, new Object[] { 
										amt,this.getTransid(),bid,pid,amt
								});
								
								
								if(row3>0)
								{
									
									return 1;

																		
									
								}
								}catch(Exception ex){
							         ex.printStackTrace();
							         return 0;
							     }
					 	}			

						return 0;
							
						}
						
						
						
						return 0;

			}
			
				
					}

		catch(Exception ex){
	          ex.printStackTrace();
	          return -1;
	      }
		return -1;

	}

	
	 public int addPayment(CreditExtendedModel cExtended)
	   {
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
			DataSource ds = (DataSource) context.getBean("dataSource");
						
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

			BigDecimal cid = cExtended.getCreditid();
			String pid = cExtended.getPid();
			String bid = cExtended.getBid();
			BigDecimal amt = cExtended.getAmount_paid();
					
			String getusedCredit = "Select * from credit_logs where credit_id = ? and partnerid = ? and branchid = ? and status <> ?";

			String checkifPaymentexist = "select * from credit_payment_logs where credit_id = ? and partnerid = ? and branchid = ? and status <> ?";
			
			StringBuilder addSQL = new StringBuilder();
					
			addSQL.append("insert into credit_payment_logs (credit_id,partnerid,branchid,amount,date_payment,status) ");
			addSQL.append("values (?,?,?,?,current_timestamp,?)");

			String updateSQL = "Update credit_payment_logs set amount = amount + ?,status = ? where  credit_id = ? and partnerid = ? and branchid = ? and (amount + ?) >= 0";
			
			String updatePaidSQL = "Update credit_payment_logs set status = ? where credit_id = ? and partnerid = ? and branchid = ?";
			try
			{
				
//				SqlRowSet rs = jdbcTemplate.queryForRowSet(getusedCredit, new Object[] {cid,pid,bid,"paid"} );	
//				
//				if(rs.next()) 
//			    {
//					
//					BigDecimal usedCredit = rs.getBigDecimal("amount");
//					
//					if(usedCredit.compareTo(amt) == 0)
//					{
						//status paid
//						
//						SqlRowSet rs2 = jdbcTemplate.queryForRowSet(checkifPaymentexist, new Object[] {cid,pid,bid,"paid"} );	
//						
//						if(rs2.next())
//						{
							
//						int row = getJdbcTemplate().update(addSQL.toString(), new Object[] { 
//				    			amt,"paid",cid,pid,bid,amt
//						});
						int row = getJdbcTemplate().update(addSQL.toString(), new Object[] { 
			    		cid,pid,bid,amt,"paid"
						});
						
						
						
						if(row>0){
							return 0;
						}
							
//						}
//						else
//						{
//							
//							int row = getJdbcTemplate().update(addSQL.toString(), new Object[] { 
//					    		cid,pid,bid,amt,"paid"
//							});
//							
//							
//							
//							if(row>0){
//								return 0;
//							}
//							
//						}
//						
//						System.out.println(usedCredit);
//						System.out.println(amt);
//						System.out.println("0");
//						return 0;
							
//					}
//					else if(usedCredit.compareTo(amt) >= 1)
//					{
//						
//						
//						
//						SqlRowSet rs2 = jdbcTemplate.queryForRowSet(checkifPaymentexist, new Object[] {cid,pid,bid,"paid"} );	
//
//						if(rs2.next())
//						{
//							
//							
//							int row = getJdbcTemplate().update(updateSQL.toString(), new Object[] { 
//					    			amt,"partial",cid,pid,bid,amt
//							});
//							
//
//							if(row>0)
//							{
//								SqlRowSet rs4 = jdbcTemplate.queryForRowSet(getusedCredit, new Object[] {cid,pid,bid,"paid"} );	
//								SqlRowSet rs3 = jdbcTemplate.queryForRowSet(checkifPaymentexist, new Object[] {cid,pid,bid,"paid"} );	
//								
//								if(rs4.next() && rs3.next())
//								{
//									
//									BigDecimal existbal = rs3.getBigDecimal("amount");
//									BigDecimal currpay = rs4.getBigDecimal("amount");
//									System.out.println("existbal: "+existbal +"curr: "+currpay);
//									
//									if(currpay.compareTo(existbal) == 0)
//									{
//										int rows = getJdbcTemplate().update(updatePaidSQL.toString(), new Object[] { 
//							    			"paid",cid,pid,bid
//									});
//										if(rows>0){
//											return 0;
//										}
//									}
//									else
//									{
//										return 1;
//									}
//								}
//								
//								
//								
//							}
//												
//						}
//						else
//						{
//							
//							int row = getJdbcTemplate().update(addSQL.toString(), new Object[] { 
//					    		cid,pid,bid,amt,"partial"
//							});
//							
//							
//							
//							if(row>0){
//								return 1;
//							}
//							
//						}
//						
//						
//						//status partial
//						System.out.println(usedCredit);
//						System.out.println(amt);
//						System.out.println("1");
//						return 1;
//						
//					}
//					else if(usedCredit.compareTo(amt) > -1)
//					{
//						//status the same
//						System.out.println(usedCredit);
//						System.out.println(amt);
//						System.out.println("-1");
//						return -1;
//					}
//									   
//				}
//				
				
		 
//				 row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
//				    		
//				});
//				
//				
//				
//				if(row>0){
//					return true;
//				}
//					
				
			
			}catch(Exception ex){
          ex.printStackTrace();
          return -1;
      }
			return -1;
			

	   }
	 
	 
	 public String getPayment(String pid,String bid){
		 
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
			DataSource ds = (DataSource) context.getBean("dataSource");
						
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			
			String gettype = "select paymenttype from branches where partnerid = ? and branchid = ?";
			
			SqlRowSet rs = jdbcTemplate.queryForRowSet(gettype, new Object[] {pid,bid} );	
			
			String type = null;
			
			if(rs.next()){
				
				 type = rs.getString("paymenttype");
				
				return type;
			}
			return type;
	 }
	 
		public String getTransid(){
			
			StringBuilder strSQL = new StringBuilder();
			 
			   String txid = "REV";
			   
			   strSQL.append("select creditid_seq.nextval as transid from dual");
			
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
			   for (Map row : rows) {
					
				   txid = txid+(BigDecimal)row.get("transid");
			
				}
				
			   return txid;
			
			
		}
	 


}
