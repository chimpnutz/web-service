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
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.DailyTransferReportModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.OpenSimReplenishmentModel;
import com.elp.model.UserManagementModel;



public class CreditLimitManagementDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(CreditLimitManagementDAO.class);
	
	public Map getcompanylist(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> company = new LinkedHashMap<String,String>();
		   
			int roleid = Integer.parseInt(session.getAttribute("roleid").toString());
			int levelid = Integer.parseInt(session.getAttribute("levelid").toString());
			String companyname = session.getAttribute("companyname").toString();
			int compid = this.getcompanyid(companyname);
//			if(roleid == 2){

				StringBuilder query = new StringBuilder();
				
				query.append("SELECT b.companyname FROM company_tbl a ,company_tbl b WHERE a.companyid = b.parentcompanyid ");
				query.append("AND a.CompanyID = ? AND b.levelid = ?");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(query.toString(),
						this.getcompanyid(companyname),levelid+1);
				
				for (Map row : rows) {
					
					company.put((String)(row.get("companyname")), (String)row.get("companyname"));
			
				}
				
			return company;
			
	}
	
	public int addCreditLimit(HttpSession session,CreditLimitManagementModel model){
			
			int pd = 0;

			String rolename = session.getAttribute("rolename")+"";
			
			String checkActivePDCLSQL = "SELECT a.companyid, a.parentcompanyid,c.creditlimit FROM company_tbl a, company_tbl b, credit_limit c WHERE a.companyid = b.companyid AND b.companyid = c.companyid AND c.status = ? AND c.companyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?)";
			
			String checkActiveRetailerCLSQL = "SELECT a.companyid, a.companyname ,c.creditlimitid FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance = 0";
			String checkActiveRetailerCLwithbalanceSQL = "SELECT a.companyid, a.companyname FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance <> 0";
			
			String expireActivePDCL = "update credit_limit set status = ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ?";
		
			StringBuilder addCreditLimitPD = new StringBuilder()	;				
			addCreditLimitPD.append("insert into credit_limit(companyid,creditlimit,remaininglimit,outstanding_balance,created_date,valid_from,status,remaining_balance) values");
			addCreditLimitPD.append("((select companyid from company_tbl where companyname = ?),?,?,?,now(),now(),?,?)");
			
			StringBuilder addCreditLimitRetailer = new StringBuilder()	;				
			addCreditLimitRetailer.append("insert into credit_limit(companyid,creditlimit,remaininglimit,outstanding_balance,created_date,valid_from,status,remaining_balance) values");
			addCreditLimitRetailer.append("(?,?,?,?,now(),now(),?,?)");
			
			String getRetailerRemainingBalance = "select creditlimit,remaininglimit,remaining_balance from credit_limit where companyid = ? and status = ? and creditlimitid = ?";
			
			String getretailerPD = "SELECT a.companyid FROM company_tbl a, company_tbl b,company_tbl c WHERE  a.companyid = b.parentcompanyid AND  b.companyid = c.parentcompanyid AND c.companyid = ?";
			String removeRemainingBalancefromRetailer = "update credit_limit  set  creditlimit = creditlimit - ?, remaininglimit = remaininglimit- ? where  companyid = ? and creditlimitid =?";
			String addCreditLimittoPD = "update credit_limit  set  remaininglimit = remaininglimit + ? where  companyid = (select companyid from company_tbl where companyname = ?) and status = ? ";
			String expireActiveRetailerCL = "update credit_limit set status = ? where companyid = ? and status = ?";
			
			
			String inserttocreditlogs = "insert into credit_logs (companyid,amount,approval_status,status) values ((select companyid from company_tbl where companyname = ?),?,?,?)";
					
			String updateBalance = "update credit_limit set outstanding_balance = ? where companyid = (select companyid from company_tbl where companyname = ?)";
					
			if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
			{
			
				//System.out.println("pd: "+model.getPd());
				
				logger.info("Add Credit Limit for PD: "+model.getPd() +", amount:"+model.getCreditlimit());
				
				SqlRowSet rs = getJdbcTemplate().queryForRowSet(checkActivePDCLSQL,"approved",model.getPd(),session.getAttribute("companyname"));
				
				if(rs.next())
				{

							   try{
								   
		   
										int row = getJdbcTemplate().update(expireActivePDCL, new Object[] { 
											"expired",model.getPd(),"approved"
										});
										
										int row3 = getJdbcTemplate().update(addCreditLimitPD.toString(), new Object[] { 
											model.getPd(),Integer.parseInt(model.getCreditlimit()),
											Integer.parseInt(model.getCreditlimit()),0,"approved",0
											});

										
										if(row>0 && row3 > 0)
										{
											
											int row4 = getJdbcTemplate().update(inserttocreditlogs.toString(), new Object[] { 
												model.getPd(),Integer.parseInt(model.getCreditlimit()),
												"approved","unpaid"
												});
											
											if(row4>0){				
													logger.info("Add Credit Limit for PD: "+model.getPd() +", amount:"+model.getCreditlimit() +", SUCCESS");
													return 1;
											}
											
									
										}


							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						            return 0;
						        }
							   

				
				}
				
				
				else
				{
		
				    try{

								
								int row = getJdbcTemplate().update(addCreditLimitPD.toString(), new Object[] { 
									model.getPd(),Integer.parseInt(model.getCreditlimit()),
									Integer.parseInt(model.getCreditlimit()),0,"approved",0
								});

								
								if(row>0){
									
									int row4 = getJdbcTemplate().update(inserttocreditlogs.toString(), new Object[] { 
										model.getPd(),Integer.parseInt(model.getCreditlimit()),
										"approved","unpaid"
										});
									
									if(row4>0){
										logger.info("Add Credit Limit for PD: "+model.getPd() +", amount:"+model.getCreditlimit() +", SUCCESS");
											return 1;
										
									}
									
								}
								
								
	
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return 0;
				        }
					
				}
				
	
			    
			    
			}
			
			
			
			else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
			{
				int remainingbalance = 0;
				int creditlimit = 0;
				int creditlimitid = 0;

				SqlRowSet rs = getJdbcTemplate().queryForRowSet(checkActiveRetailerCLSQL,session.getAttribute("companyname"),model.getRetailers(),"approved");
				SqlRowSet rs3 = getJdbcTemplate().queryForRowSet(checkActiveRetailerCLwithbalanceSQL,session.getAttribute("companyname"),model.getRetailers(),"approved");
				
				
				logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit());
				
				if(rs.next())
					
				{
					   try
					   {
						   
								int row = getJdbcTemplate().update(expireActiveRetailerCL, new Object[] { 
									"expired",model.getRetailers(),"approved"
								});
								
								if(row>0)
								{
									
									
									SqlRowSet getremainingbalanceRs = getJdbcTemplate().queryForRowSet(getRetailerRemainingBalance,model.getRetailers(),"expired",rs.getInt("creditlimitid"));
									
									if(getremainingbalanceRs.next()){
										
										remainingbalance = getremainingbalanceRs.getInt("remaininglimit");
						
									}
									
									int RemoveRetailerRow = getJdbcTemplate().update(removeRemainingBalancefromRetailer, new Object[] { 
											creditlimit,remainingbalance,model.getRetailers(),rs.getInt("creditlimitid")
									});

									
									if(RemoveRetailerRow>0)
									{
										
										int creditBacktoPD = getJdbcTemplate().update(addCreditLimittoPD, new Object[] { 
												remainingbalance,session.getAttribute("companyname"),"approved"
										});
										
										if(creditBacktoPD>0)
										
										{

												
									
										    try{
										    	


														String deductpd = "update credit_limit set remaininglimit = remaininglimit - ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and (remaininglimit - ?) >= 0";
															
														int updatepdrow = getJdbcTemplate().update(deductpd, new Object[] { 
												
														Integer.parseInt(model.getCreditlimit()),session.getAttribute("companyname"),"approved",Integer.parseInt(model.getCreditlimit())});
														
														int addCreditrow =0 ;
														
														if(updatepdrow>0)
														{
															
															addCreditrow = getJdbcTemplate().update(addCreditLimitRetailer.toString(), new Object[] { 
																Integer.parseInt(model.getRetailers()),Integer.parseInt(model.getCreditlimit()),
																Integer.parseInt(model.getCreditlimit()),0,"approved",0
														});
															
														}	
														
														if(addCreditrow>0 && updatepdrow>0)
														{
															logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", SUCCESS");
															return 2;
														}
														

											   }catch(DataAccessException ex){
										            ex.printStackTrace();
										            return 0;
										        }
											}
											
											else
											{
												logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", Failed: Not Enough Balance");
												return -1;
											}
	
									}
									

									
								}
				
							
						}
						   

					   catch(DataAccessException ex){
				            ex.printStackTrace();
				            return 0;
				        }
					   
					
					
					
					
					
				}else if(rs3.next()){
					
					logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", Failed: Adding Retailer Credit Limit Fail. Please settle first the outstanding balance.");
					return 3;
					
					
					
				}

		
					

				else
				
				{
					
					String getremaininglimit = "select remaininglimit,remaining_balance from credit_limit where companyid = (select companyid from company_tbl where companyname = ?) and status = ?";
					
					SqlRowSet rs5 = getJdbcTemplate().queryForRowSet(getremaininglimit,session.getAttribute("companyname"),"approved");
					int rLimit = 0;
					int rBalance = 0;
					int aBalance = 0;
					
					
					if(rs5.next()){
						
						 rLimit = rs5.getInt("remaininglimit");
						 rBalance = rs5.getInt("remaining_balance");
						 aBalance = rLimit - rBalance;
					
					}
					
					System.out.println("avail balance: "+aBalance);
					if(aBalance>=Integer.parseInt(model.getCreditlimit()))
					{
						
			
				    try{
				    	
							
						String deductpd = "update credit_limit set remaininglimit = remaininglimit - ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and (remaininglimit - ?) >= 0";
						
						int updatepdrow = getJdbcTemplate().update(deductpd, new Object[] { 
				
						Integer.parseInt(model.getCreditlimit()),session.getAttribute("companyname"),"approved",Integer.parseInt(model.getCreditlimit())});
						
						int addCreditrow =0 ;
						
						if(updatepdrow>0)
						{
							
							addCreditrow = getJdbcTemplate().update(addCreditLimitRetailer.toString(), new Object[] { 
								Integer.parseInt(model.getRetailers()),Integer.parseInt(model.getCreditlimit()),
								Integer.parseInt(model.getCreditlimit()),0,"approved",0
						});
							
						}	
						
						if(addCreditrow>0 && updatepdrow>0)
						{
							logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", SUCCES");	
							return 2;
						}
							
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return 0;
				        }
				    
					}
					
					else
					{
						logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", Failed: Not Enough Balance");
						return -1;
					}
					
				}

			
			logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", Failed");	
			return 0;
					
					
			}
			logger.info("Add Credit Limit for retailer: "+model.getRetailers() +", amount:"+model.getCreditlimit() +", Failed");	
			return 0;
				
			
		
		
		
		
	}
	
	
	public int editCreditLimit(HttpSession session,CreditLimitManagementModel model)
	{
	
		String rolename = session.getAttribute("rolename")+"";
		
		String updateCreditLimit = "update credit_limit set creditlimit = ? , remaininglimit = ? where  creditlimitid = ?";
		
		
		
		String addPD = "update credit_limit set remaininglimit = remaininglimit + ? where companyid =(select companyid from company_tbl where companyname = ?) and status = ?";
		
		String subPD = "update credit_limit set remaininglimit = remaininglimit - ? where companyid =(select companyid from company_tbl where companyname = ?) and status = ?";
		
		String getOutstandingBalance = "select creditlimit from credit_limit where creditlimitid = ? and status = ? and outstanding_balance <> 0";
		
		String expireCurrentCreditlimit = "update credit_limit set status = ?, valid_to = now() where creditlimitid = ?";
		
		StringBuilder addCreditLimitPD = new StringBuilder()	;				
		addCreditLimitPD.append("insert into credit_limit(companyid,creditlimit,remaininglimit,outstanding_balance,created_date,valid_from,status,remaining_balance) values");
		addCreditLimitPD.append("(?,?,?,?,now(),now(),?,?)");
		
		String getCompany = "select companyid from credit_limit where creditlimitid = ?";
		
		int beforeCreditLimit = 0;
		int afterCreditLimit = 0;
		int total = 0;
		
		int cid = 0;
		
		
		
		  if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
		   {
			  
			  if(model.getStatus().equalsIgnoreCase("approved"))
			  {
				  
				  SqlRowSet getOutBalRS= getJdbcTemplate().queryForRowSet(getOutstandingBalance,model.getCreditlimitid(),"approved");
				  
				  if(getOutBalRS.next()){
					  
					  return 2;
					  
				  }
				  
					int row = getJdbcTemplate().update(expireCurrentCreditlimit, new Object[] { 
								"expired",model.getCreditlimitid()

					});
							
						int row2 = 0;
						
						if(row>0)
						{
							
							
							  SqlRowSet getCidRS= getJdbcTemplate().queryForRowSet(getCompany,model.getCreditlimitid());
							  
							  if(getCidRS.next()){
								  
								 cid = getCidRS.getInt("companyid");
								
							  }
							
							logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit());	  
							
							row2 = getJdbcTemplate().update(addCreditLimitPD.toString(), new Object[] { 
									cid,Integer.parseInt(model.getCreditlimit()),
									Integer.parseInt(model.getCreditlimit()),0,"approved",0

							});
							

					
							
							if(row>0 && row2>0){
								logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", SUCCESS");
								return 0;
							}
							
						}
						
						logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", FAIL");
						return 1;
				  
			  }
			  else
			  {
				    
				   StringBuilder getCIDstatus = new StringBuilder()	;
				  
				  
				   getCIDstatus.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimit,c.status,d.credit_id,d.status,e.amount,e.status,e.userid,e.levelid FROM company_tbl a ");		
				   getCIDstatus.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
				   getCIDstatus.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
				   getCIDstatus.append("LEFT JOIN credit_logs d ON c.companyid = d.companyid  ");
				   getCIDstatus.append("LEFT JOIN credit_payment_logs e ON d.companyid = e.companyid  ");
				   getCIDstatus.append(" WHERE d.status = ? and c.outstanding_balance <> ? and c.creditlimitid = ?");
				  
				  
				   SqlRowSet cancelRS= getJdbcTemplate().queryForRowSet(getCIDstatus.toString(),"unpaid",0,model.getCreditlimitid());
				   
				   if(cancelRS.next()){
					   
					   logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Cancelling of Credit Limit Failed. Please settle your Outstanding Balance first.");
					   return 4;
				   }
				   
				   
				   String checkNoBal = "SELECT companyid,creditlimit,outstanding_balance,remaininglimit FROM credit_limit WHERE creditlimitid = ? AND outstanding_balance = 0 AND creditlimit = remaininglimit";
				   
				   SqlRowSet checkNoBalRs= getJdbcTemplate().queryForRowSet(checkNoBal.toString(),model.getCreditlimitid());
				   
				   
				   if(checkNoBalRs.next())
				   {
					   
						int row = getJdbcTemplate().update(expireCurrentCreditlimit, new Object[] { 
								"expired",model.getCreditlimitid()

						});
						
						if(row>0){
							
							   logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Cancelling of Credit Limit Successful.");
							return 5;
						}
					   
					   
					   
				   }
			
				  
				  
				  
				  logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", FAIL");
				  return 1;
			  }
			  
		
			
				
			
			  
		   }
		
		  else	if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		  {
			  
			  
			  if(model.getStatus().equalsIgnoreCase("approved"))
			  {
				  
				  
					String getPDremLimit = "select remaininglimit from credit_limit where status = ? and companyid = (select companyid from company_tbl where companyname = ?)";
					int pdremLimit = 0;
					SqlRowSet getPDremLimitRs= getJdbcTemplate().queryForRowSet(getPDremLimit,"approved",session.getAttribute("companyname"));
					
					if(getPDremLimitRs.next())
					{
						pdremLimit = getPDremLimitRs.getInt("remaininglimit");
					}

					
					if(Integer.parseInt(model.getCreditlimit())>=pdremLimit){
						
						logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Fail: Editing of Credit Limit Failed. Not Enough PD Balance.");
						return 3;
					}
					
					String getCreditLimit = "select creditlimit from credit_limit where creditlimitid = ? and status = ?";
					
					SqlRowSet beforeRs= getJdbcTemplate().queryForRowSet(getCreditLimit,model.getCreditlimitid(),"approved");
					
					if(beforeRs.next())
					{
						beforeCreditLimit = beforeRs.getInt("creditlimit");
						System.out.println(beforeCreditLimit);
					}
						
						
						int row2 = getJdbcTemplate().update(updateCreditLimit, new Object[] { 
								Integer.parseInt(model.getCreditlimit()),Integer.parseInt(model.getCreditlimit()),model.getCreditlimitid()
						});
				
						if(row2>0)
						{
							
							SqlRowSet afterRS= getJdbcTemplate().queryForRowSet(getCreditLimit,model.getCreditlimitid(),"approved");
							
							if(afterRS.next())
							{
								afterCreditLimit = afterRS.getInt("creditlimit");
					
								
							
								if(beforeCreditLimit>afterCreditLimit)
								{
								
									int addRow = getJdbcTemplate().update(addPD, new Object[] { 
										beforeCreditLimit-afterCreditLimit,session.getAttribute("companyname"),"approved"
									});
									
									if(addRow>0){
										logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", SUCCESS");
										return 0;
									}
									
								}
								else if (beforeCreditLimit<afterCreditLimit)
								{
									
									int subRow = getJdbcTemplate().update(subPD, new Object[] { 
											afterCreditLimit-beforeCreditLimit,session.getAttribute("companyname"),"approved"
									});
									
									if(subRow>0){
										logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", SUCCESS");
										return 0;
									}
									
								}
			
							}
							
							
						
						}
						logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", FAIL");
						return 1;
					
				  
				  
			  }
			  else if(model.getStatus().equalsIgnoreCase("cancel"))
			  {
				    
				  	int creditlimit = 0;
				  	int pdremlimit = 0;
				  	int pdcreditlimit = 0;
				  	
				  	int totallimit = 0;
				  	
				  	String getCreditLimit = "select creditlimit from credit_limit where creditlimitid = ? and status = ?";
				  	
					SqlRowSet creditlimitRs= getJdbcTemplate().queryForRowSet(getCreditLimit,model.getCreditlimitid(),"approved");
					
					if(creditlimitRs.next())
					{
		
						creditlimit = creditlimitRs.getInt("creditlimit");
						
					}
					
					
					
					String getpdremlimit = "select creditlimit,remaininglimit from credit_limit where  companyid = (select companyid from company_tbl where companyname = ?) and status = ?";
					
					SqlRowSet pdremlimitRs= getJdbcTemplate().queryForRowSet(getpdremlimit,session.getAttribute("companyname"),"approved");
					
					if(pdremlimitRs.next())
					{
		
						pdremlimit = pdremlimitRs.getInt("remaininglimit");
					//	pdcreditlimit = pdremlimitRs.getInt("creditlimit");
						
					}
					
					totallimit = pdremlimit + creditlimit;
					System.out.println(creditlimit);
					System.out.println(totallimit);
					if(totallimit>creditlimit)
					
					{
						
						String updatecreditlimit = "update credit_limit set remaininglimit = remaininglimit + ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? and remaininglimit + ? <= creditlimit";
						
						int row = getJdbcTemplate().update(updatecreditlimit, new Object[] { 
							creditlimit,session.getAttribute("companyname"),"approved",creditlimit
						});
					
						if(row>0)
						{
							
							
							String expirecredit = "update credit_limit set status = ? where creditlimitid = ?";
							
							int row2 = getJdbcTemplate().update(expirecredit, new Object[] { 
								"expired",model.getCreditlimitid()
							});
							
							if(row>0 && row2>0)
							{
								 logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Cancelling of Credit Limit Successful.");
								return 5;
							}
								
								
							
						}
						
							
					}else if(totallimit<=creditlimit)
					{
						
					String updatecreditlimit = "update credit_limit set remaininglimit = remaininglimit + ? where companyid = (select companyid from company_tbl where companyname = ?) and status = ? ";
						
						int row = getJdbcTemplate().update(updatecreditlimit, new Object[] { 
							creditlimit,session.getAttribute("companyname"),"approved"
						});
				
						if(row>0){
							String expirecredit = "update credit_limit set status = ? where creditlimitid = ?";
							
							int row2 = getJdbcTemplate().update(expirecredit, new Object[] { 
								"expired",model.getCreditlimitid()
							});
							
							if(row>0 && row2>0)
							{
								 logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Cancelling of Credit Limit Successful.");
								return 5;
							}
						}
						
						
					}
					
			
					
				  
			  }
				  
			  logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", Cancelling of Credit Limit Failed. Please settle your Outstanding Balance first.");
			  return 4;
				  
		
		
			
		}
		  logger.info("Edit Credit Limit for Companyid: "+cid+", amount:"+model.getCreditlimit()+", FAIL");
		return 1;
		
		
	

	
	}
	
	public List<CreditLimitManagementModel> getCreditlist(HttpSession session,String type)
	{	
		   String rolename = session.getAttribute("rolename")+"";
		
		   ArrayList<CreditLimitManagementModel> creditList= new ArrayList<CreditLimitManagementModel>();
			
		   if(rolename.equalsIgnoreCase("RD Managers") || rolename.equalsIgnoreCase("RD Operations") || rolename.equalsIgnoreCase("Super Administrator") || rolename.equalsIgnoreCase("Super"))
		   {
			   
			   StringBuilder strSQL = new StringBuilder()	;
			   
//			   strSQL.append("SELECT a.creditlimitid,a.companyid,a.creditlimit,a.outstanding_balance,a.remaininglimit,a.credit_id,a.status,b.companyname ");
//			   strSQL.append("FROM credit_limit a, company_tbl b WHERE  ");
//			   strSQL.append("a.companyid = b.companyid  ");
//			   
//			   strSQL.append("SELECT a.companyid, a.companyname ,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status FROM company_tbl a	");
//			   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
//			   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
//			   strSQL.append("WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ?");
//	
			   
			   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status ,d.status as paymentstatus FROM company_tbl a	");
			   strSQL.append("INNER JOIN  company_tbl b ON a.companyid = b.companyid ");
			   strSQL.append("INNER JOIN credit_limit c ON b.companyid = c.companyid  ");
			   strSQL.append(" LEFT JOIN credit_logs d ON b.companyid = d.companyid ");
			   strSQL.append("LEFT JOIN credit_logs e ON d.companyid = e.companyid ");
			   strSQL.append("WHERE a.parentcompanyid =  (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ? ");
			//	
			   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved");
			   
				for (Map row : rows) {

					CreditLimitManagementModel credit = new CreditLimitManagementModel();
					
					credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
					credit.setCompanyname((String)(row.get("companyname")));
					credit.setCreditlimit((String)(row.get("creditlimit")+""));
					credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
					credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
					credit.setStatus((String)(row.get("status")));
					credit.setPaymentstatus((String)(row.get("paymentstatus")));
					
					creditList.add(credit);			
				}
			   
		   }
		   
		   else if (rolename.equalsIgnoreCase("PD Managers") || rolename.equalsIgnoreCase("PD Operations"))
		   {

			   StringBuilder strSQL = new StringBuilder()	;
			   
			   if(type.equals("approved")){
				   
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,a.parentcompanyid,b.parentcompanyid,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status FROM company_tbl a		");
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON a.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON a.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON a.companyid = e.companyid  ");
				   strSQL.append("WHERE b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ?");
				   
					  // String checkActiveRetailerCLSQL = "SELECT a.companyid, a.companyname FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance = 0";
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved");
				   
					for (Map row : rows) {

						CreditLimitManagementModel credit = new CreditLimitManagementModel();
						
						credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setCreditlimit((String)(row.get("creditlimit")+""));
						credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
						credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
						credit.setStatus((String)(row.get("status")));
						//credit.setPaymentstatus((String)(row.get("paymentstatus")));
						
						creditList.add(credit);			
					}
			   }
			   
			   else if(type.equals("unpaid")){
				   
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status,d.status as paymentstatus FROM company_tbl a	");
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON a.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON a.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON a.companyid = e.companyid  ");
				   strSQL.append("WHERE b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ? and d.status = ?");
				   
					  // String checkActiveRetailerCLSQL = "SELECT a.companyid, a.companyname FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance = 0";
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved","unpaid");
				   
					for (Map row : rows) {

						CreditLimitManagementModel credit = new CreditLimitManagementModel();
						
						credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setCreditlimit((String)(row.get("creditlimit")+""));
						credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
						credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
						credit.setStatus((String)(row.get("status")));
						credit.setPaymentstatus((String)(row.get("paymentstatus")));
						
						creditList.add(credit);			
					}
			   }
			   
			   else if(type.equals("paid"))
				   
			   {
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status,d.status as paymentstatus FROM company_tbl a	");
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON a.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON a.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON a.companyid = e.companyid  ");
				   strSQL.append("WHERE b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ? and d.status = ?");
				   
					  // String checkActiveRetailerCLSQL = "SELECT a.companyid, a.companyname FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance = 0";
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"approved","paid");
				   
					for (Map row : rows) {

						CreditLimitManagementModel credit = new CreditLimitManagementModel();
						
						credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setCreditlimit((String)(row.get("creditlimit")+""));
						credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
						credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
						credit.setStatus((String)(row.get("status")));
						credit.setPaymentstatus((String)(row.get("paymentstatus")));
						
						creditList.add(credit);			
					}
				   
			   }
			   
			   
			   else if(type.equals("cancelled"))
				   
			   {
				   strSQL.append("SELECT DISTINCT a.companyid, a.companyname ,c.creditlimitid,c.creditlimit,c.outstanding_balance,c.remaininglimit,c.credit_id,c.status,d.status as paymentstatus FROM company_tbl a	");
				   strSQL.append("INNER JOIN  company_tbl b ON a.parentcompanyid = b.companyid ");
				   strSQL.append("INNER JOIN credit_limit c ON a.companyid = c.companyid  ");
				   strSQL.append("LEFT JOIN credit_logs d ON a.companyid = d.companyid  ");
				   strSQL.append("LEFT JOIN credit_payment_logs e ON a.companyid = e.companyid  ");
				   strSQL.append("WHERE b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) and c.status = ? and d.status = ?");
				   
					  // String checkActiveRetailerCLSQL = "SELECT a.companyid, a.companyname FROM company_tbl a, company_tbl b, credit_limit c WHERE a.parentcompanyid = b.companyid AND  b.parentcompanyid = (SELECT companyid FROM company_tbl WHERE companyname = ?) AND a.companyid = c.companyid AND c.companyid = ? and c.status = ? and c.outstanding_balance = 0";
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"),"expired","paid");
				   
					for (Map row : rows) {

						CreditLimitManagementModel credit = new CreditLimitManagementModel();
						
						credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
						credit.setCompanyname((String)(row.get("companyname")));
						credit.setCreditlimit((String)(row.get("creditlimit")+""));
						credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
						credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
						credit.setStatus((String)(row.get("status")));
						credit.setPaymentstatus((String)(row.get("paymentstatus")));
						
						creditList.add(credit);			
					}
				   
			   }
				   

	
			   
		   }
				 	   
	
		
			return creditList;
		
		
		
		
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
	

		
		public List<CreditLimitManagementModel> getRetailers(String dsp){
			
			   StringBuilder strSQL = new StringBuilder();
			  				 
			   Map<String,String> company = new LinkedHashMap<String,String>();
			   
			   ArrayList<CreditLimitManagementModel> retailersList= new ArrayList<CreditLimitManagementModel>();
			   
			   strSQL.append("SELECT companyid,companyname from company_tbl where parentcompanyid = (select companyid from company_tbl where companyname = ? and status = 'active') and status = 'active'");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),dsp);
				
				for (Map row : rows) {
					
					CreditLimitManagementModel retailer = new CreditLimitManagementModel();
					
					retailer.setCompanyid((Integer)(row.get("companyid")));
					retailer.setCompanyname((String)(row.get("companyname")));
					
					retailersList.add(retailer);		
			
				}
				
				return retailersList;
				
		}
	
		
		public Map getDSP(HttpSession session){
			
			   StringBuilder strSQL = new StringBuilder();
				 
			   Map<String,String> company = new LinkedHashMap<String,String>();
			   
			   ArrayList<CreditLimitManagementModel> retailersList= new ArrayList<CreditLimitManagementModel>();
			   
			   strSQL.append("SELECT companyname from company_tbl where parentcompanyid = (select companyid from company_tbl where companyname = ? and status = 'active') and status = 'active'");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"));
					
					for (Map row : rows) {
						
						company.put((String)(row.get("companyname")), (String)row.get("companyname"));
				
					}
					
					

				return company;
				
		}
		
		public Map getPD(HttpSession session){
			
			   StringBuilder strSQL = new StringBuilder();
				 
			   Map<String,String> company = new LinkedHashMap<String,String>();
			   
			   ArrayList<CreditLimitManagementModel> retailersList= new ArrayList<CreditLimitManagementModel>();
			   
			   strSQL.append("SELECT companyname from company_tbl where parentcompanyid = (select companyid from company_tbl where companyname = ? and status = 'active') and status = 'active'");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("companyname"));
					
					for (Map row : rows) {
						
						company.put((String)(row.get("companyname")), (String)row.get("companyname"));
				
					}
					
					

				return company;
				
		}
	
	
		public List<CreditLimitManagementModel> getCreditLimitlistbyCompany(int cid)
		   {
				
				   ArrayList<CreditLimitManagementModel> creditList= new ArrayList<CreditLimitManagementModel>();
					
				   StringBuilder strSQL = new StringBuilder()	;
				   
				   strSQL.append("SELECT creditlimit,status,DATE_FORMAT(created_date, '%m/%d/%Y') AS date from credit_limit where companyid = ? ");
	
				   
				   
				   creditList.clear();
				 
				   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),cid);
				   
					for (Map row : rows) {

						CreditLimitManagementModel credit = new CreditLimitManagementModel();
						
						credit.setCreditlimit(row.get("creditlimit")+"");
						credit.setStatus((String)row.get("status"));
						credit.setDate((String)row.get("date"));


						creditList.add(credit);			
					}
				
					return creditList;
					
		   }
		
		
		public List<CreditLimitManagementModel> getindividualcredit(int id)
		{
			
			   ArrayList<CreditLimitManagementModel> creditList= new ArrayList<CreditLimitManagementModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.creditlimitid,a.companyid,a.creditlimit,a.outstanding_balance,a.remaininglimit,a.credit_id,a.status,b.companyname ");
			   strSQL.append("FROM credit_limit a, company_tbl b WHERE  ");
			   strSQL.append("a.companyid = b.companyid and a.creditlimitid = ? ");
			   
			   
			   creditList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), id);
			   
				for (Map row : rows) {

					CreditLimitManagementModel credit = new CreditLimitManagementModel();
					
					credit.setCreditlimitid((Integer)(row.get("creditlimitid")));
					credit.setCompanyname((String)(row.get("companyname")));
					credit.setCompanyid((Integer)(row.get("companyid")));
					credit.setCreditlimit((String)(row.get("creditlimit")+""));
					credit.setOutstandingbalance((String)(row.get("outstanding_balance")+""));
					credit.setRemaininglimit((String)(row.get("remaininglimit")+""));
					credit.setStatus((String)(row.get("status")));
					
					creditList.add(credit);			
				}
			
				return creditList;
				
		}
		
		public int getAvailableLimit(HttpSession session){
			
			int balance = 0;
			
			String getAvailable = "select remaininglimit from credit_limit where status = ? and companyid = (select companyid from company_tbl where companyname = ?)";
			
			
			SqlRowSet rs = getJdbcTemplate().queryForRowSet(getAvailable,"approved",session.getAttribute("companyname"));
			
			if(rs.next()){
				
				balance = rs.getInt("remaininglimit");
				
			}
			
			return balance;
		}
		
	
	
		public List<OpenSimReplenishmentModel> getopensimreplenishmentlist(int companyid)
		{
			
			   ArrayList<OpenSimReplenishmentModel> opensimList= new ArrayList<OpenSimReplenishmentModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT transferreport_id,DATE_FORMAT(transferfrom_time,'%d %b %Y %T') as transferfrom,  ");
			   strSQL.append("DATE_FORMAT(transferto_time,'%d %b %Y %T') as transferto,opensim,inprogress_amount,amount_transfered, ");
			   strSQL.append("companyid,outstanding_balance,payment_status,DATE_FORMAT(payment_date,'%d %b %Y %T') as paymentdate,amount_paid ");
			   strSQL.append("from daily_transfer_report where companyid = ?");

			   
			   opensimList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), companyid);
			   
				for (Map row : rows) {

					OpenSimReplenishmentModel opensim = new OpenSimReplenishmentModel();
					
					
					opensim.setTransferreport_id((Integer)(row.get("transferreport_id")));
					opensim.setTransferfrom_time((String)(row.get("transferfrom")));
					opensim.setTransferto_time((String)(row.get("transferto")));
					opensim.setOpensim((String)(row.get("opensim")));
					opensim.setInprogress_amount((Float) (row.get("inprogress_amount")));
					opensim.setAmount_transfered((Float) (row.get("amount_transfered")));
					opensim.setCompanyid((Integer)(row.get("companyid")));
					opensim.setOutstanding_balance((Float) (row.get("outstanding_balance")));
					opensim.setPayment_status((String)(row.get("payment_status")));
					opensim.setPayment_date((String)(row.get("paymentdate")));
					opensim.setAmount_paid((Float) (row.get("amount_paid")));
					
					opensimList.add(opensim);
				}
			
				return opensimList;
				
		}
	

}
