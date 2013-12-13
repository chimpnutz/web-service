package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.PreparedStatementCreator;


import com.paysetter.amax.AMAXConstants;


import com.paysetter.commons.pctomobile.P2MAmaxRequest;
import com.paysetter.commons.pctomobile.P2MConstants;


import com.pc2mweb.beans.BulkOffersBean;
import com.pc2mweb.beans.CredentialsBean;
import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.beans.Wallet;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransfertoRetailerModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class TopupDAO extends JdbcDaoSupport {
	

	private static DataSource ds;
	private static final Logger logger = Logger.getLogger(TopupDAO.class);

	
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
	
//	public Map fillprodtype(String brand){
//		
//		   StringBuilder strSQL = new StringBuilder();
//		  				 
//		   Map<String,String> prefix = new LinkedHashMap<String,String>();
//		   
//		   strSQL.append("SELECT productcode from loadproducts where wallet_type = ? and transaction_type = ? and status = ? and supplier = ?");
//			
//			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),1,"mobile","active",brand);
//			
//			for (Map row : rows) {
//				
//				prefix.put((String)(row.get("productcode")), (String)row.get("productcode"));
//		
//			}
//			
//			return prefix;
//			
//	}
	
	public List<BulkOffersBean> fillprodtype(String brand){
	
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<BulkOffersBean> bulkofferList= new ArrayList<BulkOffersBean>();
		   
		   strSQL.append("SELECT productcode from loadproducts where wallet_type = ? and transaction_type = ? and status = ? and supplier = ? or supplier = ? order by productcode");
			
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),1,"mobile","active",brand,"GHP/TM");
			
			
			for (Map row : rows) {
				
				BulkOffersBean bulkoffer = new BulkOffersBean();
				
				bulkoffer.setKeyword((String)(row.get("productcode")));
				
				bulkofferList.add(bulkoffer);		
		
			}
			
			return bulkofferList;
			
	}

	
	public BigDecimal getAmount(String prodtype){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   BigDecimal amount = null;
		   
		   strSQL.append("SELECT amount from loadproducts where auth = 0 and partnerproductcode = 'AMAX' and productcode = ? ");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),prodtype);
			
			for (Map row : rows) {
				
			amount = (BigDecimal)(row.get("amount"));
			
			}
			
			return amount;
			
	}
	
	
	public float getProductAmount(String prodtype){
		
		float amount = -1;
		
		String getamountsql = "select amount from decrementation_commission where  productcode = ?";
		
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getamountsql,prodtype);
		
		for (Map row : rows) {
			
		amount = (float)(row.get("amount"));
		
		}
		
		return amount;
		
		
		
	}
	
	public int insertTransaction(TopupModel r,HttpSession session) {

			  
				
			   Long id = (long) 0;
		
			   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
			   
			   final int walletid = (int) session.getAttribute("walletid");
			   
			   final TopupModel topup = r;
			
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

							   ps.setString(1, topup.getPid());
							   ps.setString(2, "");
							   ps.setInt(3, aid);
							   ps.setString(4, topup.getRequestype());
							   ps.setString(5, topup.getTxtype());
							   ps.setString(6, topup.getProdtype());
							   ps.setFloat(7, Float.parseFloat(topup.getAmount()));
							   ps.setInt(8, walletid);
							   ps.setString(9, "PENDING");
							   ps.setFloat(10, Float.parseFloat(topup.getAmount()));
							   ps.setFloat(11, Float.parseFloat(topup.getAmount()));
							   ps.setFloat(12, 0);
							  
							 
							   return ps;
							     }

			
				}, keyHolder);
			
				int txid = keyHolder.getKey().intValue();
				//r.ptxid = txid;

					
					if(row>0)
					
					
					{
						
					if(topup.getTxtype().equals("mobile"))
					
					{
						
						
						   String mobile = topup.getPrefix()+topup.getMobnum();
						   StringBuilder insertMobileTx = new StringBuilder();
						   
						   insertMobileTx.append("INSERT INTO mobile_transaction  ");
						   insertMobileTx.append("(transactionid,targetmsisdn) ");
						   insertMobileTx.append(" VALUES (?,?) ");
						   
						   
						   try{
							   
								int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
									txid, mobile
								});

								
								if(mobileRow>0){
									
									
							        SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
							        
							        String datetoday = datetodayFormat.format(new Date())+txid; 
							        
									String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";
									
							    	Long partnertxid = Long.parseLong(datetoday);
							    	
							    	
									int updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
											datetoday,txid
										});
									
									
									if(row>0 && mobileRow>0 && updaterow>0){
										
										r.ptxid = partnertxid;
										return txid;
										
									}
									
									
								}
									
						
						   }catch(DataAccessException ex){
					            ex.printStackTrace();
					
					        }
						
						
					}
					
							
//					else if (r.getTxtype().equals("billspayment"))
//					
//					{
//						
////						   StringBuilder insertbillsTx = new StringBuilder();
////						   
////						   insertbillsTx.append("INSERT INTO bills_payment_transaction  ");
////						   insertbillsTx.append("(transactionid,biller_code,payee_account,payee_name,bill_date,date_due) ");
////						   insertbillsTx.append(" VALUES (?,?,?,?,?,?,SYSDATE) ");
////						   
////						   
////						   try{
////							   
////								int billsrow = getJdbcTemplate().update(insertbillsTx.toString(), new Object[] { 
////									txid
////								});
////
////								if(billsrow>0 && row>0){
////
////									return id;
////								}
////						
////						   }catch(DataAccessException ex){
////					            ex.printStackTrace();
////					        
////					        }
//						
//						
//					}
					
					
				
					
					}

		
		return txid;
	}
	

	
	public int insertTransaction(TransfertoRetailerModel r,HttpSession session) {
		P2MAmaxRequest p2mreq;
		int txid = -1;
		
		Long id = (long) 0;
		
			   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
			   final int walletid = (int) session.getAttribute("walletid");
			   
			   final TransfertoRetailerModel topup = r;
			
			   final  StringBuilder strSQL = new StringBuilder();
			
			   strSQL.append("Insert into transactions ( transactiondate,PartnerID, PartnerTXID, AgentID, request_type,transaction_type,productcode,amount,walletid,status) ");
			   strSQL.append("Values (now(), ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			   
				KeyHolder keyHolder = new GeneratedKeyHolder();
				
				int row = getJdbcTemplate().update(new PreparedStatementCreator() 
				{
					  public PreparedStatement createPreparedStatement(Connection con)
							    throws SQLException 
							    {
							   PreparedStatement ps = con.prepareStatement(strSQL.toString(),Statement.RETURN_GENERATED_KEYS);

							   ps.setString(1, topup.getPid());
							   ps.setString(2, "");
							   ps.setInt(3, aid);
							   ps.setString(4, topup.getRequestype());
							   ps.setString(5, topup.getTxtype());
							   ps.setString(6, topup.getProdtype());
							   ps.setFloat(7, Float.parseFloat(topup.getAmount()));
							   ps.setInt(8, walletid);
							   ps.setString(9, "PENDING");
				
				
							   return ps;
							     }

			
				}, keyHolder);
			
					txid = keyHolder.getKey().intValue();
					
				
					if(row>0){
						
						
						
						   String mobile = topup.getMsisdn();
						   StringBuilder insertMobileTx = new StringBuilder();
						   
						   insertMobileTx.append("INSERT INTO mobile_transaction  ");
						   insertMobileTx.append("(transactionid,targetmsisdn) ");
						   insertMobileTx.append(" VALUES (?,?) ");
						   
						   
						   try{
							   
								int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
									txid, mobile
								});

								
								if(mobileRow>0)
								{
									
									
							        SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
							        
							        String datetoday = datetodayFormat.format(new Date())+txid; 
							        
									String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";
									
									Long partnertxid = Long.parseLong(datetoday);
									
							    	
									int updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
											datetoday,txid
										});
									
									

									
									if(row>0 && mobileRow>0 && updaterow>0){
										r.ptxid = partnertxid;
										return txid;
										
									}
									
									
								}
									
						
						   }catch(DataAccessException ex){
					            ex.printStackTrace();
					
					        }
						   return txid;
					}

		
					return txid;
	}
	
	
	public int insertTransaction(TopupModel r, Connection conn) {
		P2MAmaxRequest p2mreq;
		int txid = -1;
		
		try {
			p2mreq = new P2MAmaxRequest(conn);
			p2mreq.setPartnerId(r.getPid());
			p2mreq.setBranchId(r.getBid());
			p2mreq.setMobileNo(r.getPrefix() + r.getMobnum());
			p2mreq.setDenom(Integer.parseInt(r.getAmount()));
			p2mreq.setAgentId(r.agentid);
			
			txid = p2mreq.insertTransaction();
		} catch (Exception e) {	
			e.printStackTrace();
			logger.info("insert tx exception: "+e.getMessage());
		} finally {
			p2mreq = null;
		}
		
		return txid;
	}
	
	public int insertTransaction(TransfertoRetailerModel r, Connection conn) {
		P2MAmaxRequest p2mreq;
		int txid = -1;
		
		try {
			p2mreq = new P2MAmaxRequest(conn);
			p2mreq.setPartnerId(r.getPid());
			p2mreq.setBranchId(r.getBid());
			p2mreq.setMobileNo(r.getMsisdn());
			p2mreq.setDenom(Integer.parseInt(r.getAmount()));
			p2mreq.setAgentId(r.agentid);
			
			txid = p2mreq.insertTransaction();
		} catch (Exception e) {	
			e.printStackTrace();
			logger.info("insert tx exception: "+e.getMessage());
		} finally {
			p2mreq = null;
		}
		
		return txid;
	}
	
	public void updateTransaction(int txid,Long ptxid, int errorstate, String trace,HttpSession session) 
	{

		
		try {
			

			
			if ( trace.equals("0") ) {
				//p2mReq.updateTransaction(txid, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?, status=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						pc2mwebFunc.P2MConstantsgetMessage(errorstate),errorstate+"",ptxid+"",session.getAttribute("PID"),txid
					});
				
			} else {
				//p2mReq.updateTransaction(txid, trace, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=?, topuptrace=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
				  String status = null;
				
			      if(errorstate <= 10)
			    	  status = pc2mwebFunc.AmaxgetMessage(errorstate);
			      else
			    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate);
			     
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
								status,errorstate+"",trace,ptxid+"",session.getAttribute("PID"),txid
					});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	public void updateTransactionTest(int txid,Long ptxid, int errorstate, String trace,HttpSession session) 
	{

		
		try {
			

			
			if ( trace.equals("0") ) {
				//p2mReq.updateTransaction(txid, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?, status=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						pc2mwebFunc.P2MConstantsgetMessage(errorstate),errorstate+"",ptxid+"",session.getAttribute("PID"),txid
					});
				
			} else {
				//p2mReq.updateTransaction(txid, trace, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=?, topuptrace=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
				  String status = null;
				
			      if(errorstate <= 10)
			    	  status = pc2mwebFunc.AmaxgetMessage(errorstate)+" [TEST]";
			      else
			    	  status = pc2mwebFunc.P2MConstantsgetMessage(errorstate)+" [TEST]";
			     
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
								status,errorstate+" [TEST]",trace,ptxid+"",session.getAttribute("PID"),txid
					});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	public void updateTransaction(int txid, long ptxid, int errorstate, String trace,String type,HttpSession session) {

		
		try {
			

			
			if ( trace.equals("0") ) {
				//p2mReq.updateTransaction(txid, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						pc2mwebFunc.P2MConstantsgetMessage(errorstate),errorstate+"",ptxid+"",session.getAttribute("PID"),txid
					});
				
			} else {
				//p2mReq.updateTransaction(txid, trace, errorstate);
				
				String updatetx = "update transactions set responsemsg = ?,status=?, topuptrace=? where partnertxid=? and partnerid = ? and transactionid = ?";
				
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
								status,errorstate+"",trace,ptxid+"",session.getAttribute("PID"),txid
					});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	
	public void updateTransaction(int txid, long ptxid, String errormsg, String trace,HttpSession session) {

		
		try {
			

				String updatetx = "update transactions set status=? where partnertxid = ?,partnerid = ?,transactionid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						errormsg,ptxid+"",session.getAttribute("PID"),txid
					});
				

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	
	

	
	public int updateTransactionDecre(int txid, long ptxid,float amount,HttpSession session) throws SQLException {
		
			int rows = 0;
			
			String sql = "update transactions set discount_amount = ? where partnertxid = ?,partnerid = ?,transactionid=?";
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						amount,ptxid+"",session.getAttribute("PID"),txid
				});
				
				
				if(row>0){
					return 1;
				}
				
				}catch(Exception ex){
			         ex.printStackTrace();
			         return 0;
			     }
			
			return rows;

		}
	
	
	public boolean insertusertxLog(HttpSession usersession,Long txid,int amount, String mobnum, String status, String tracenum){
		

		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("INSERT INTO user_transactions_logs  ");
		   strSQL.append("(username,transactionid,mobilenumber,amount,status,tracenumber,txdate) ");
		   strSQL.append(" VALUES (?,?,?,?,?,?,now()) ");
		   
		   
		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					usersession.getAttribute("USER"),txid+"",mobnum,Float.parseFloat(amount+""),status,tracenum
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
	

	
//	public Float getWallet(HttpSession session){
//		
//		   StringBuilder strSQL = new StringBuilder();
//		   
//		   BigDecimal wallet = null;
//		   String type = null;
//		   
//		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
//		   
//		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
//			
//		   for (Map row : rows) {
//			   
//
//			   wallet = (BigDecimal)row.get("wallet");   
//			   		   
//			   return wallet.floatValue();
//				   	   			   
//		   }
//		return wallet.floatValue();  
//		   
//			  
//		
//	}

	
	public Float getWallet(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   BigDecimal wallet = null;
		   String type = null;
		   
//		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
		   
		   strSQL.append("SELECT a.walletid, a.wallet,a.paymenttype from wallets a, partners_wallet b, wallet_types c "); 
		   strSQL.append("where a.walletid = b.walletid  and b.wallet_type = c.wallet_type and a.partnerid = ? ");
		   strSQL.append("and c.wallet_name = ?");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"AMAX");
			
		   for (Map row : rows) {
			   

			   wallet = (BigDecimal)row.get("wallet");   
			   		   
			   return wallet.floatValue();
				   	   			   
		   }
		return wallet.floatValue();  
		   
			  
		
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
	 
	 public Float getCommission(float amount){
			
		   StringBuilder strSQL = new StringBuilder();

			 
		   Float com = null;

			   strSQL.append("SELECT commission,amount,to_amount FROM decrementation_commission WHERE amount <= ? AND (to_amount IS NULL OR to_amount >= ?) AND productcode = ? ");
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),amount,amount,"LOAD");
			   for (Map row : rows) {
					
				    com = (Float)(row.get("commission"));
				    
				}
			   return com;

		
	}
	 

	 
	 	public DecrementationBean getDecrementation(String proddesc,String pid, float amount){
	 		
	 	 if(proddesc.equalsIgnoreCase("LD")){
	 		 
	 		 proddesc = "LOAD"; 
	 	 }

		 StringBuilder strSQL = new StringBuilder();
		
		 DecrementationBean bean2 = null;
		 
		 strSQL.append("SELECT c.commission,c.amount,c.to_amount FROM ");
		 strSQL.append("partners b ");
		 strSQL.append("INNER JOIN decrementation_commission c ON b.decremented_commission_group = c.decremented_commission_group ");
		 strSQL.append("WHERE c.productcode = ? ");
		 strSQL.append("AND b.partnerid = ? AND c.amount <= ? AND (c.to_amount IS NULL OR c.to_amount >= ?)");
		 
	 //List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),proddesc,pid,amount,amount,pid);
			SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),proddesc,pid,amount,amount);
        	
			
			if(rs.next())
    		{
				DecrementationBean bean = new DecrementationBean();
				
			    bean.setCommission(rs.getFloat("commission"));
			    bean.setAmount(rs.getFloat("amount"));
			    bean.setToamount(rs.getFloat("to_amount"));

				return bean;
				
    		}else {
    			
    			return bean2;
    		}

	
		   
		 	 
	 }
	 	
	 	public float getTransactionFee(String proddesc,String pid, float amount){
	 		

			 StringBuilder strSQL = new StringBuilder();
			
			 Float fee = null;
			 
			 strSQL.append("select case when fee_type = 'percent' then (fee / 100.0 ) * ? else fee end as fee  from ranges where partnerid = ? and productcode=?  and minamount <= ? and (maxamount is null or maxamount >= ?) ");

				SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),amount,pid,proddesc,amount,amount);
	        	
				
				if(rs.next())
	    		{
	
						fee = rs.getFloat("fee");
				
					
	    		}

		
				return fee;
			 	 
		 }
	 	
	 	
	 	
	 	public String getAllows(String pid, String productcode){
	 		

			 StringBuilder strSQL = new StringBuilder();
			
			 String allows  = "";
			 
			 strSQL.append("select allows from loadproducts_allows where productcode = ? and partnerid = (select partner from partners where partnerid = ?) ");

				SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),productcode,pid);
	        	
				
				if(rs.next())
	    		{
	
					allows = rs.getString("allows");
		
					
	    		}

		
				return allows;
			 	 
		 }
	 	
	 	public Wallet GetWalletInfo(String pid, int walletid){
	 		

			 StringBuilder strSQL = new StringBuilder();
			
			 Wallet wallet  = null;
			 
			 strSQL.append("select w.walletid,w.partnerid,w.partnertxid, w.wallet, pw.wallet_type, w.paymenttype  "); 
			 strSQL.append(" from wallets w inner join partners_wallet pw on pw.walletid = w.walletid where w.partnerid = ? and pw.walletid= ?  ");

				SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),pid,walletid);
	        	
				
				if(rs.next())
	    		{
	
					wallet = new Wallet();
					wallet.partnerid = rs.getString("partnerid");
					wallet.id = rs.getLong("walletid");
					wallet.wallet_type = rs.getLong("wallet_type");
					wallet.wallet = rs.getFloat("wallet");
					wallet.paymenttype = rs.getString("paymenttype");
					wallet.partnertxid = rs.getString("partnertxid");
					
		
					
	    		}

		
				return wallet;
			 	 
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
	 
		
		public int updateTransactionwithFee(String responseCode, String responseMsg, String trace,  float fee, boolean isfeesettlement,String px_transactionfeetype,String partnertxid,long partnerid) {
			
			
			int rows = 0;
			
			String feepaymentmode = "WALLET";
			if (isfeesettlement)
				feepaymentmode = "CASH";
			
			String sql = "update transactions set status = ?, TOPUPTRACE = ?, RESPONSEMSG = ?,TRANSACTIONFEE = ?,  px_transactionfee_paymentmode = ?, px_transactionfeetype = ?  where partnertxid = ? and Partnerid = ?";
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						responseCode,trace,responseMsg,fee,feepaymentmode,partnertxid+"",partnerid
				});
				
				
				if(row>0){
					return rows;
				}
				
				}catch(Exception ex){
			         ex.printStackTrace();
			         return rows;
			     }
			
			return rows;

		}
		
		public int updateTransaction(String responseCode, String responseMsg, String trace, String status,String partnertxid,String partnerid) {
			
			
			int rows = 0;
			
		
			
			String sql = "update transactions set status = ?, RESPONSEMSG = ?, TOPUPTRACE = ?  where partnertxid = ? and Partnerid = ? ";
			
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						status,responseMsg,trace,partnertxid,partnerid
				});
				
				
				if(row>0){
					return rows;
				}
				
				}catch(Exception ex){
			         ex.printStackTrace();
			         return rows;
			     }
			
			return rows;

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
		 
		 public void updatePxTransaction(HttpSession session, Long txid)
		  {
			 
			 
			 		
			 		String updateTxBranch = "update transactions set px_transactionfeetype = ?, px_transactionfee_paymen";
			 		
			 		String transid = txid+"";
			 		
			 		String paymenttype = session.getAttribute("paymenttype").toString();
			 		
			 		String paymentmode = "";
			 		
			 		if(paymenttype.equalsIgnoreCase("settlement")){
			 			
			 			paymentmode = "cash";
			 			
			 		}else if (paymenttype.equalsIgnoreCase("prepaid")){
			 			
			 			paymentmode = "wallet";
			 			
			 		}
					
					try{
				 		 
						int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
								paymenttype,paymentmode,transid
						});
						
			
						
						}catch(Exception ex){
					         ex.printStackTrace();
					        
					     }
			 			

		   }
		 
		 
		 public String getCustomMesasge(HttpSession session)
		  {
			 
			 
			 		
			 StringBuilder strSQL = new StringBuilder();
				
			 String message  = "";
			 
			 strSQL.append("select custom_msg from partners where partnerid = ? ");

				SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),session.getAttribute("PID"));
	        	
				
				if(rs.next())
	    		{
	
					message = rs.getString("custom_msg");
		
					
	    		}

		
				return message;
				

		   }
		 
		 
		 
		 
}
	 


	



