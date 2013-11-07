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
import com.pc2mweb.model.EpinModel;
import com.pc2mweb.model.LopModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class EpinDAO extends JdbcDaoSupport 
{
	
	public Float getWallet(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   BigDecimal wallet = new BigDecimal(0);
		   String type = null;
		   
		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
			
		   for (Map row : rows) {
			   

			   wallet = (BigDecimal)row.get("wallet");   
			   		   
			   return wallet.floatValue();
				   	   			   
		   }
		return wallet.floatValue();  
		   
			  
		
	}
	
	
	public Map fillBox(){
		
		   StringBuilder strSQL = new StringBuilder();
			 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT * from prefixes");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("prefix")), (String)row.get("prefix"));
		
			}
			
			return prefix;
			
	}
	
	public Map fillprodtype(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT productcode from loadproducts where wallet_type = ? and transaction_type = ? and status = ?");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),1,"mobile","active");
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("productcode")), (String)row.get("productcode"));
		
			}
			
			return prefix;
			
	}
	
	public Long insertTransaction(EpinModel r,HttpSession session) 
	{

		  
		
		   Long id = (long) 0;
	
		   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
		   
		   final int walletid = (int) session.getAttribute("walletid");
		   
		   final EpinModel epin = r;
		
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
						   
						   ps.setString(1, epin.getPid());
						   ps.setString(2, "");
						   ps.setInt(3, aid);
						   ps.setString(4, epin.getRequestype());
						   ps.setString(5, epin.getTxtype());
						   ps.setString(6, epin.getProdtype());
						   ps.setFloat(7, Float.parseFloat(epin.getAmount()));
						   ps.setInt(8, walletid);
						   ps.setString(9, "PENDING");
						   ps.setFloat(10, Float.parseFloat(epin.getAmount()));
						   ps.setFloat(11, Float.parseFloat(epin.getAmount()));
						   ps.setFloat(12, 0);
						  
						 
						   return ps;
						     }

		
			}, keyHolder);
		
			long txid = keyHolder.getKey().longValue();
			
			System.out.println("txid "+txid);
				

				
				if(row>0)
				
				
				{
					

					
					   StringBuilder insertMobileTx = new StringBuilder();
					   
					   insertMobileTx.append("INSERT INTO epins_transaction  ");
					   insertMobileTx.append("(transactionid) ");
					   insertMobileTx.append(" VALUES (?)");
					   
					   
					   try{
						   

							int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
								txid,epin.getTarget()
							
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
	
	
}
