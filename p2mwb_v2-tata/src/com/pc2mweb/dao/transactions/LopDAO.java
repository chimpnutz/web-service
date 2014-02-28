package com.pc2mweb.dao.transactions;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.paysetter.commons.pctomobile.P2MConstants;
import com.pc2mweb.beans.BillerBean;
import com.pc2mweb.beans.BillerFieldBean;
import com.pc2mweb.beans.BulkOffersBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.model.BillsPaymentHistoryModel;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.LopModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class LopDAO extends JdbcDaoSupport 
{
	
	public Float getWallet(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   Float wallet = null;
		   String type = null;
		   
		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
			
		   for (Map row : rows) {
			   

			   wallet = (Float)row.get("wallet");   
			   		   
			   return wallet;
				   	   			   
		   }
		return wallet;  
		   
			  
		
	}
	
	public Long insertTransaction(LopModel r,HttpSession session) 
	{

		  
		
		   Long id = (long) 0;
	
		   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
		   
		   final int walletid = (int) session.getAttribute("walletid");
		   
		   final LopModel bill = r;
		
		   final  StringBuilder strSQL = new StringBuilder();
		  
		
		   strSQL.append("Insert into transactions ( transactiondate,PartnerID, PartnerTXID, AgentID, request_type,transaction_type,productcode,amount,walletid,status,requested_amount,amount_received,returned_amount) ");
		   strSQL.append("Values (now(), ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?) ");

		   
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			int row = getJdbcTemplate().update(new PreparedStatementCreator() 
			{
				  public PreparedStatement createPreparedStatement(Connection con)
						    throws SQLException 
						    {
						   PreparedStatement ps = con.prepareStatement(strSQL.toString(),Statement.RETURN_GENERATED_KEYS);
						   
						   ps.setString(1, bill.getPid());
						   ps.setString(2, "");
						   ps.setInt(3, aid);
						   ps.setString(4, bill.getRequestype());
						   ps.setString(5, bill.getTxtype());
						   ps.setString(6, bill.getProdtype());
						   ps.setFloat(7, Float.parseFloat(bill.getAmount()));
						   ps.setInt(8, walletid);
						   ps.setString(9, "PENDING");
						   ps.setFloat(10, Float.parseFloat(bill.getAmount()));
						   ps.setFloat(11, Float.parseFloat(bill.getAmount()));
						   ps.setFloat(12, 0);
						  
						 
						   return ps;
						     }

		
			}, keyHolder);
		
			long txid = keyHolder.getKey().longValue();
			
			System.out.println("txid "+txid);
				

				
				if(row>0)
				
				
				{
					

					
					   StringBuilder insertMobileTx = new StringBuilder();
					   
					   insertMobileTx.append("INSERT INTO paypal_transactions  ");
					   insertMobileTx.append("(transactionid) ");
					   insertMobileTx.append(" VALUES (?)");
					   
					   
					   try{
						   

							int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
								txid
							
							});

							
							if(mobileRow>0)
							{
								
								
							    SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
						        
						        String datetoday = datetodayFormat.format(new Date())+txid; 
						        
						        Long partnertxid = Long.parseLong(datetoday);
						        
						        int updaterow = 0;

										String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";

								    	
										 updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
												datetoday,txid
											});
				

									return txid;
									
								
								
								
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				
				        }
					return id;

					
					}
	
		
		return id;
	}
	
	
	public void updateTransaction(Long txid, int errorstate, String trace) 
	{

		
		try {
			

			
			if ( trace.equals("0") ) {
				//p2mReq.updateTransaction(txid, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?, status=? where partnertxid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						pc2mwebFunc.P2MConstantsgetMessage(errorstate),errorstate+"",txid+""
					});
				
			} else {
				//p2mReq.updateTransaction(txid, trace, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=?, topuptrace=? where partnertxid=?";
				
				  String status = null;
				
			      if(errorstate <= 10)
			    	  status = pc2mwebFunc.AmaxgetMessage(errorstate);
			      else
			    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate);
			     
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
								status,errorstate+"",trace,txid+""
					});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
public void updateTransaction(Long txid, int errorstate, String trace,String type) {

		
		try {
			

			
			if ( trace.equals("0") ) {
				//p2mReq.updateTransaction(txid, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=? where partnertxid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						pc2mwebFunc.P2MConstantsgetMessage(errorstate),errorstate+"",txid+""
					});
				
			} else {
				//p2mReq.updateTransaction(txid, trace, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=?, topuptrace=? where partnertxid=?";
				
				  String status = null;
				  
				  if(type.equalsIgnoreCase("load")){
					  
				      if(errorstate <= 10)
				    	  status = pc2mwebFunc.AmaxgetMessage(errorstate);
				      else
				    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate);
					  
				  }else if (type.equalsIgnoreCase("gcash")){
					  
				      if(errorstate <= 10)
				    	  status = pc2mwebFunc.AmaxgetMessage(errorstate);
				      else
				    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate);
				  }
				
			      if(errorstate <= 10)
			    	  status = pc2mwebFunc.AmaxgetMessage(errorstate);
			      else
			    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate);
			     
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
								status,errorstate+"",trace,txid+""
					});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	
	public void updateTransaction(Long txid, String errormsg, String trace) {

		
		try {
			

				String updatetx = "update transactions set status=? where partnertxid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						errormsg,txid+""
					});
				

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	public void updateTransactionToken(Long txid,String token) {

		
		try {
			

				String updatetx = "update paypal_transactions set token=? where transactionid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						token,txid
					});
				

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	
	 public int updatetxid(HttpSession session, Long txid, float topupamount)
	  {
		 
		 
		 		
		 		String updateTxBranch = "Update wallets set  wallet = wallet + ?, partnertxid = ? where  partnerid = ?  and (wallet + ?) >= 0 and walletid = ?";
		 		
		 		String transid = txid+"";
				
				
				
				try{
			 		 
					int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
							topupamount,transid,session.getAttribute("PID"), topupamount,session.getAttribute("walletid")
					});
					
					
					if(row>0){
						return 1;
					}
					
					}catch(Exception ex){
				         ex.printStackTrace();
				         return 0;
				     }
		 			

			return 0;
			

	   }
	 
	 
		public boolean CreditWallet(String pid, int walletid,float amount, long partnertxid) throws SQLException {
			int rows = 0;
			
			String sql = "update wallets set wallet = wallet + ? , partnertxid = ? " +
			"where partnerid = ? and walletid = ? and wallet >=0";
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						amount,partnertxid+"",pid,walletid,
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
				 
		 
		
	 	
		public boolean DeductWallet(String pid, int walletid,float amount,long partnertxid) throws SQLException {
			int rows = 0;
			
			String sql = "update wallets set wallet = wallet - ? , partnertxid = ? " +
			"where partnerid = ? and walletid = ? and wallet - ? >= 0";
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						amount,partnertxid+"",pid,walletid,amount
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
		
		
		 public int deductWallet(HttpSession session, Long txid, float topupamount)
		  {
			 
			 
			 		
			 		String updateTxBranch = "update wallets set wallet = wallet - ? , partnertxid = ? " +
			 					"where partnerid = ? and walletid = ? and (wallet + ?) >= 0";
			 		
			 		String transid = txid+"";
					
			

			 		BigDecimal value = new BigDecimal(Float.toString(topupamount));
					
					try{
				 		 
						int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
								value,transid,session.getAttribute("PID").toString(), Integer.parseInt(session.getAttribute("walletid")+""), topupamount
						});
						
						
						if(row>0){
							return 1;
						}
						
						}catch(Exception ex){
					         ex.printStackTrace();
					         return 0;
					     }
			 			

				return 0;
				

		   }
		 
		 public int creditWallet(HttpSession session, Long txid, float topupamount)
		  {
			 
			 
			 		
			 		String updateTxBranch = "update wallets set wallet = wallet + ? , partnertxid = ? " +
			"where partnerid = ? and walletid = ?  and (wallet + ?) >= 0";
			 		
			 		String transid = txid+"";
					
					
					
					try{
				 		 
						int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
								topupamount,transid,session.getAttribute("PID"), session.getAttribute("walletid"),topupamount
						});
						
						
						if(row>0){
							return 1;
						}
						
						}catch(Exception ex){
					         ex.printStackTrace();
					         return 0;
					     }
			 			

				return 0;
				

		   }
		 
		 
		 public void updatePaypalTx(String itemName,String itemNumber,String paymentStatus,String paymentAmount,
				 String paymentCurrency,String txnId,String receiverEmail,String payerEmail,String custom)
		  {
			 
			 
			 		
			 		String updateTxBranch = "update paypal_transactions set paypal_txid = ? , item_name = ?, item_number = ?, payment_status = ?, " +
			 				" payment_amount = ? , payment_currency = ? , payment_txid = ? , receiver_email = ?, payer_email = ? " +
			 				"where transactiondid = ?";

			 		
					try{
				 		 
//						int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
//								Integer.parseInt(txnId),itemName,itemNumber,paymentStatus,new BigDecimal(Float.parseFloat(paymentAmount)),paymentCurrency,txnId,receiverEmail,payerEmail,Integer.parseInt(custom)
//						});
						
						int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
								Integer.parseInt(txnId),itemName,itemNumber,paymentStatus,new BigDecimal(Float.parseFloat(paymentAmount)),paymentCurrency,txnId,receiverEmail,payerEmail,464231
						});
						
						}catch(Exception ex){
					         ex.printStackTrace();
					    
					     }
			 			

			
				

		   }
		 
		 
		 public boolean LOPRetailer(Float amount,HttpSession session,String partnertxid)
		   {
	
		
				String addLoad = "Update wallets set wallet = wallet + ?, partnertxid = ? where partnerid = ?    and (wallet + ?) >= 0 and isdefault = ?";
				
				

				try{
					 		 
					int row = getJdbcTemplate().update(addLoad, new Object[] { 
							amount,partnertxid,session.getAttribute("PID"),amount,1
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
	
}
