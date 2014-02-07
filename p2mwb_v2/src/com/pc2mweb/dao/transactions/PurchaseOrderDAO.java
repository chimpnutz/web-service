package com.pc2mweb.dao.transactions;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;
import com.pc2mweb.model.MailModel;
import com.pc2mweb.beans.PurchaseOrderBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.utility.function.Props;

public class PurchaseOrderDAO extends JdbcDaoSupport 
{
	
	public String discount_type;
	public BigDecimal discount;
	public String partner_type;
	public int walletid;
	public BigDecimal facevalue;
	public BigDecimal total_amount;
	public BigDecimal total_order;
	public String status;
	String pid;
	String wallet_type;
	
	public boolean insertPurchaseOrder(HttpSession session,List<PurchaseOrderModel> po ){
		
		
		   String role = session.getAttribute("USERLEVEL").toString();
		   final String pid = session.getAttribute("PID").toString();
		   
		   BigDecimal discount_amt = new BigDecimal(0);
		   BigDecimal discount_amt1 = new BigDecimal(0);
		   int qty = 0;
		      
		   int totalqty = 0;
		   int totalqty1 =0;
		   BigDecimal total_dsct = new BigDecimal(0);
		   BigDecimal totalface = new BigDecimal(0);
		   BigDecimal totalface2 = new BigDecimal(0);
		   
		   
		   
		   if(role.equals("manager"))
		   {
			   
			   
				
			   final  StringBuilder strSQL = new StringBuilder();
			  
			
			   strSQL.append("Insert into po_orders (partnerid) ");
			   strSQL.append("Values (?) ");
			   
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   
				int row = getJdbcTemplate().update(new PreparedStatementCreator() 
				{
					  public PreparedStatement createPreparedStatement(Connection con)
							    throws SQLException 
							    {
							   PreparedStatement ps = con.prepareStatement(strSQL.toString(),Statement.RETURN_GENERATED_KEYS);

							   ps.setString(1, pid);

							  
							 
							   return ps;
							     }

			
				}, keyHolder);
			   
			   
				int poid = keyHolder.getKey().intValue();
			   
				if(row>0)
					
					
				{
				
				      if(null != po && po.size() > 0) {
				    	  
				    		int poRow = 0;
				    		int i = 0;
				            for (PurchaseOrderModel model : po) 
				            {
				            	
				            	   if(model.getItem().equalsIgnoreCase("LOP"))
				            	   {
				            		   
				            	
				            		   int qty2 = Integer.parseInt(model.getQuantity());
				            		   int walletid = 0;
				            		   totalqty = totalqty + qty2;
				            		   			            		  
				            		   
				            		   this.getPartnerDiscount(session, model.getItem());
					            	   
					            	   this.getfaceValue(model.getItem());
					            	   
				            		   discount_amt = new BigDecimal(Integer.parseInt(model.getQuantity()));
				            		   
				            		   BigDecimal div = new BigDecimal (100);
				            		   
				            		   BigDecimal amt =  discount.divide(div);
				            		   
				            		   BigDecimal totalfaceqty2 = new BigDecimal(totalqty);
				            		   
					            	   totalface2 = facevalue.multiply(totalfaceqty2); 
					            	   
				            		   discount_amt = discount_amt.subtract(amt);
				            		   
				            		   total_dsct = total_dsct.add(discount_amt);
				            		   
				            		   System.out.println(totalface2);
				            		   System.out.println(discount_amt);


				            		   StringBuilder strSql = new StringBuilder();
				            		   
				            		   strSql.append("SELECT a.walletid FROM wallets a ");
									   strSql.append("INNER JOIN partners_wallet b ON a.walletid = b.walletid ");
									   strSql.append("WHERE a.partnerid = ? ");
										
										SqlRowSet rss   = getJdbcTemplate().queryForRowSet(strSql.toString(),session.getAttribute("PID"));
										
										if(rss.next()) {
											
											   walletid = rss.getInt("walletid");
				            		   
					            			   StringBuilder checkPOItems = new StringBuilder();  
					            			   checkPOItems.append("SELECT POId, itemcode FROM po_orders_items ");
					            			   checkPOItems.append("WHERE poid = ? AND itemcode=? ");
					            			   
					            			   try{
					            				   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(checkPOItems.toString(),new Object[]{
					            				   					poid,model.getItem()
					            				   	});
					            				   
					            				   if(!rs.next()) 
					            				   {
					            											            			   
		
						            			   		   
								            			   StringBuilder insertPOItems = new StringBuilder();
														   
								            			   
														  
														   insertPOItems.append("INSERT INTO po_orders_items  ");
														   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
														   insertPOItems.append(" VALUES (?,?,?,(select face_value_amount from purchase_items where item_name = ?),?,?) ");
														   
														  
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																	poid,model.getItem(),totalqty,model.getItem(),totalface2.subtract(discount_amt),walletid
																});
															   		
															  
															  
															   System.out.println(poRow);
															  
															 
														
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else{
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = ?, price =?, discount_amount = ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
					    									   totalqty,totalface2,discount_amt,walletid,poid,model.getItem()
					    									});
		
					    								   
					    										
					    							
					    							   }catch(DataAccessException ex){
					    						            ex.printStackTrace();
					    						
					    						        }
					            					   
					            					   
					            				   }
					            				   
					            				  
					            				   
					            			   }catch(DataAccessException ex){
								                ex.printStackTrace();
											
					            			    }
										}
										   
			            		   }
				            	 
				            		   

				            	   
				            	   
				            	   
				            	   else
				            	   {
				            		   this.getPartnerDiscount(session, model.getItem());		            	   
					            	   this.getfaceValue(model.getItem());
					            	   int walletid = 0;
				             		   qty = Integer.parseInt(model.getQuantity());
					            	  
				             		   totalqty1 = totalqty1 + qty;
				            		   			            	   
				             		   
					            	   
				             		   BigDecimal totalfaceqty = new BigDecimal(totalqty1);
				             		   
					            	   totalface = facevalue.multiply(totalfaceqty);   
					            	   
				            		   discount_amt = facevalue.subtract(discount);
				            		   
				            		   System.out.println(totalface);
				            		   System.out.println(facevalue);
				            		   System.out.println(discount_amt);
				            		   System.out.println(totalfaceqty);
				            		   System.out.println(facevalue.subtract(discount_amt).multiply(totalfaceqty));
				            		   
				            		   BigDecimal totaldcamount = discount_amt.multiply(new BigDecimal(totalqty1));
				            		   
				            		   StringBuilder strSql = new StringBuilder();
										
										strSql.append("SELECT a.walletid FROM wallets a ");
										strSql.append("INNER JOIN partners_wallet b ON a.walletid = b.walletid ");
										strSql.append("WHERE a.partnerid = ? ");
										
										SqlRowSet rss   = getJdbcTemplate().queryForRowSet(strSql.toString(),session.getAttribute("PID"));
										
										if(rss.next()) {
											
											walletid = rss.getInt("walletid");
			
						            		   StringBuilder checkPOItems = new StringBuilder();  
					            			   checkPOItems.append("SELECT POId, itemcode FROM po_orders_items ");
					            			   checkPOItems.append("WHERE poid = ? AND itemcode=? ");
				            			   
					            			   try{
					            				   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(checkPOItems.toString(),new Object[]{
					            				   					poid,model.getItem()
					            				   	});
					            				   
					            				   if(!rs.next()) 
					            				   {
					            					   			            			   
		
						            			   		   
								            			   StringBuilder insertPOItems = new StringBuilder();
														   
								            			  
														  
														   insertPOItems.append("INSERT INTO po_orders_items  ");
														   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
														   insertPOItems.append(" VALUES (?,?,?,(select face_value_amount from purchase_items where item_name = ?),?,?) ");
														   
														  
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																	poid,model.getItem(),totalqty1,model.getItem(),facevalue.subtract(discount_amt).multiply(totalfaceqty),walletid
																});
															   		
															  
															   System.out.println(poRow);
															  
															 
														
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else{
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = ?, price = (select face_value_amount from purchase_items where item_name = ?), discount_amount = ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
																totalqty1,model.getItem(),discount_amt,walletid,poid,model.getItem()
															});
		
					    								 
					    							
					    							   }catch(DataAccessException ex){
					    						            ex.printStackTrace();
					    						
					    						        }
					            					   
					            					   
					            				   }
					            				   
					            				  
					            				   
					            			   }catch(DataAccessException ex){
									            ex.printStackTrace();
					            			  }	
									        }
				            		   
				            		   
				            		   
				            	   }
								   
				         i++;
				            }
				            
				            
				            
				            
				            
				    		if(poRow>0){
								
								
				 			   StringBuilder insertPOItems = new StringBuilder();
							   
							   insertPOItems.append("update po_orders  ");
							   insertPOItems.append("set order_amount = (select sum(price * qty - discount_amount) from po_orders_items where POID = ?)");
							   insertPOItems.append(" where  poid = ? ");
							   
							   try{
								   
								   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
									   poid,poid
									});

								   
										
							
							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						
						        }
				    			
				    			
				    			
				    			
								return true;
								
								
							}
				        }
					
					
					
				}
		
			   
			   
			   
			   
			   
			   
			   
			   
			   
		   }
		   
		   else
		   {
			   final  StringBuilder strSQL = new StringBuilder();
	 			  
				
			   strSQL.append("Insert into po_orders (partnerid) ");
			   strSQL.append("Values (?) ");
			   
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   
				int row = getJdbcTemplate().update(new PreparedStatementCreator() 
				{
					  public PreparedStatement createPreparedStatement(Connection con)
							    throws SQLException 
							    {
							   PreparedStatement ps = con.prepareStatement(strSQL.toString(),Statement.RETURN_GENERATED_KEYS);
							   
							  
							   
							   ps.setString(1, pid);
							   //ps.setInt(2, Integer.parseInt(model.getAmount()));

							  
							 
							   return ps;
							     }

			
				}, keyHolder);
			   
			   
				int poid = keyHolder.getKey().intValue();
			   
			      if(null != po && po.size() > 0) 
			      {
			    	    int poRow = 0;
			            for (final PurchaseOrderModel model : po) 
			            {
			            	   	
			            	
							if(row>0)
								
								
							{
								
								
								if(model.getItem().equalsIgnoreCase("LOP")){
									
								
									this.getPartnerDiscount(session, model.getItem());
					            	this.getWalletid(session, model.getWallet());
					            	this.getfaceValue(model.getItem());

			            	
				            		   int qty2 = Integer.parseInt(model.getQuantity());
				            		   
				            		   totalqty = totalqty + qty2;
				            		   			            		  
				            		   discount_amt = new BigDecimal(Integer.parseInt(model.getQuantity()));
				            		   
				            		   BigDecimal div = new BigDecimal (100);
				            		   
				            		   BigDecimal amt =  discount.divide(div);
				            		   
				            		   BigDecimal totalfaceqty2 = new BigDecimal(totalqty);
				            		   
					            	   totalface2 = facevalue.multiply(totalfaceqty2); 
					            	   
				            		   discount_amt = discount_amt.subtract(amt);
				            		   
				            		   total_dsct = total_dsct.add(discount_amt);	
				            		   
				            		  
				            		   
			            			   StringBuilder checkPOItems = new StringBuilder();  
			            			   checkPOItems.append("SELECT POId, itemcode FROM po_orders_items ");
			            			   checkPOItems.append("WHERE poid = ? AND itemcode=? ");
			            			   
			            			   try{
			            				   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(checkPOItems.toString(),new Object[]{
			            				   					poid,model.getItem()
			            				   	});
			            				   
			            				   if(!rs.next()) 
			            				   {
			            											            			   

				            			   		   
						            			   StringBuilder insertPOItems = new StringBuilder();
												   
						            			   
												  
												   insertPOItems.append("INSERT INTO po_orders_items  ");
												   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
												   insertPOItems.append(" VALUES (?,?,?,?,?,?) ");
												   
												  
												   try{
														   
													   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
															poid,model.getItem(),totalqty,totalface2,discount_amt,walletid
														});
													   		
													  
													  
													   System.out.println(poRow);
													  
													 
												
												   }catch(DataAccessException ex){
											            ex.printStackTrace();
											
											        }
			            						
			            					}
			            				   else{
			            					   
			            					   StringBuilder updatePOItems = new StringBuilder();
			    							   
			    							   updatePOItems.append("update po_orders_items  ");
			    							   updatePOItems.append("set qty = ?, price =?, discount_amount = ?, walletid=?");
			    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
			    							   
			    							   try{
			    								   
			    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
			    									   totalqty,totalface2,discount_amt,walletid,poid,model.getItem()
			    									});

			    								   
			    										
			    							
			    							   }catch(DataAccessException ex){
			    						            ex.printStackTrace();
			    						
			    						        }
			            					   
			            					   
			            				   }
			            				   
			            				  
			            				   
			            			   }catch(DataAccessException ex){
								            ex.printStackTrace();
											
								        }
			            		
										   
			            		   }
								
								
				            	   
				            				            	   
								
								
								
							}
			            	
			        
			            }
			            if(poRow>0){
							
							
				 			   StringBuilder insertPOItems = new StringBuilder();
							   
							   insertPOItems.append("update po_orders  ");
							   insertPOItems.append("set order_amount = (select sum(price * qty - discount_amount) from po_orders_items where POID = ?)");
							   insertPOItems.append(" where  poid = ? ");
							   
							   try{
								   
								   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
									   poid,poid
									});

								   
										
							
							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						
						        }
			            }
		            
			            return true;
			            
			        }
			   
			   
			   
			   
			   
		   }
		
		

		return false;
	}
	
	public String getTransidLOP(){
		
		   String updateSeq = "update wallet_transfer_sequence set seq = seq + 1";
		   
		   String txid = "LOPPO";
		   
			try{
		 		 
				int row2 = getJdbcTemplate().update(updateSeq, new Object[] { 
						
				});
				
				if(row2>0 )
				{
					
					   StringBuilder strSQL = new StringBuilder();
						 
					 
					   
					   strSQL.append("SELECT seq from wallet_transfer_sequence");
					
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
						
					   for (Map row : rows) {
							
						   txid = txid+(Integer)row.get("seq");
					
						}
						
					   return txid;
					
				
				}
				
				
				   return txid;
				
			
			}catch(Exception ex){
      ex.printStackTrace();
      return txid;
    
  }
		

		
		
	}
	
	
public boolean insertProcessOrder(HttpSession session, int poid ,TransactionIDObject obj){
		
		String txid = this.getTransidLOP();
		int walletid = 0;
		final String pid = session.getAttribute("PID").toString();
		String role = session.getAttribute("USERLEVEL").toString();
				
		String status = "processing";
		String po_status = "active";
		String del_status = "delivered";
		
		this.fillWalletlist(session);
		this.getPurchaseTotal(session, poid);
		
		obj.transactionID = txid;
		
		if(role.equals("superadmin"))
		{
			
			  
		      int poRow = 0;
		      
		    	  
				StringBuilder update = new StringBuilder();
					
				update.append("update po_orders set delivery_status = ? ");
				update.append("where POId = ? and PO_status = ?");
					
				try{
						   
					poRow = getJdbcTemplate().update(update.toString(), new Object[] { 
								del_status,poid,po_status
							});
						   
						   
					
				}
				 catch(DataAccessException ex){
				       ex.printStackTrace();
				
				 }
					
					StringBuilder STRSQL = new StringBuilder();
					String ptype = "";
					
					STRSQL.append("SELECT partnerid, paymenttype ");
					STRSQL.append("FROM wallets ");
					STRSQL.append("WHERE partnerid = ?");
					
					SqlRowSet rs2   = getJdbcTemplate().queryForRowSet(STRSQL.toString(),session.getAttribute("PID"));
									
					if(rs2.next())
					{
						ptype = rs2.getString("paymenttype");
						
						if(ptype.equalsIgnoreCase("PREPAID")){
					
							ptype = rs2.getString("paymenttype");
						StringBuilder strSql = new StringBuilder();
						
						strSql.append("SELECT a.walletid FROM wallets a ");
						strSql.append("INNER JOIN partners_wallet b ON a.walletid = b.walletid ");
						strSql.append("WHERE a.partnerid = ? ");
						
						SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSql.toString(),session.getAttribute("PID"));
						
						if(rs.next()) 
						{
							
							walletid = rs.getInt("walletid");
							
							StringBuilder updateWallet = new StringBuilder();
							
							updateWallet.append("UPDATE wallets set wallet = wallet + ?, partnertxid = ? ");
							updateWallet.append("WHERE walletid = ? and partnerid = ?");
							
							try{
								
								poRow = getJdbcTemplate().update(updateWallet.toString(), new Object[] { 
									   total_amount,txid,walletid,session.getAttribute("PID")
									});
								
								
							}catch(DataAccessException ex){
					            ex.printStackTrace();
							 }
							
							return true;
						   }
					}





						else if(ptype.equalsIgnoreCase("SETTLEMENT")){
								
						ApplicationContext mailcontext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
							
							
						Props props = new Props();
								
						MailModel mm = (MailModel) mailcontext.getBean("mail");
								
						String [] recipient =  props.getRecipients().split(",");
								
						String sender = props.getSender();
								
						mm.sendMail(sender,
								recipient,
							    	"Settlement Payment Notification", 
							    	"Hi, \n\n Settlement by "+pid+"  of PHP "+total_amount+" has been paid with PO "+poid+" please process settlement of transactions. \n\nThanks, \n\n"+session.getAttribute("USER")+"");
								
								StringBuilder updateStatus = new StringBuilder();
								
								updateStatus.append("UPDATE po_orders SET delivery_status = ? ");
								updateStatus.append("where POId = ?");
								
									try{
										poRow = getJdbcTemplate().update(updateStatus.toString(), new Object[] { 
											   del_status,poid
											});
										
									}
									  catch(DataAccessException ex){
						            		  ex.printStackTrace();
									  }
									
									return true;
							}
						
					
					}			
		}	
		
		
		return false;
		
	}
	
//	public boolean insertProcessOrder(HttpSession session, int poid ,TransactionIDObject obj)
//	{
//		
//		String txid = this.getTransidLOP();
//		int walletid = 0;
//		final String pid = session.getAttribute("PID").toString();
//		String role = session.getAttribute("USERLEVEL").toString();
//				
//		String status = "processing";
//		String po_status = "active";
//		String del_status = "delivery";
//		
//		this.fillWalletlist(session);
//		
//		this.getPurchaseTotal(session, poid);
//		
//		obj.transactionID = txid;
//		
//		if(role.equals("superadmin"))
//		{
//			
//					int poRow = 0;
//		      
//					StringBuilder update = new StringBuilder();
//					
//					update.append("update po_orders set delivery_status = ? ");
//					update.append("where POId = ? and PO_status = ?");
//					
//					try{
//						   
//						   poRow = getJdbcTemplate().update(update.toString(), new Object[] { 
//							   status,poid,po_status
//							});
//						   
//						   
//					
//					   }catch(DataAccessException ex){
//				            ex.printStackTrace();
//				
//				        }
//					
//					if(wallet_type.equalsIgnoreCase(wallet_type)){
//						
//						StringBuilder strSql = new StringBuilder();
//						
//						strSql.append("SELECT a.walletid FROM wallets a ");
//						strSql.append("INNER JOIN partners_wallet b ON a.walletid = b.walletid ");
//						strSql.append("WHERE a.partnerid = ? ");
//						
//						SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSql.toString(),session.getAttribute("PID"));
//						
//						if(rs.next()) 
//						{
//							
//							walletid = rs.getInt("walletid");
//							
//							StringBuilder updateWallet = new StringBuilder();
//							
//							updateWallet.append("UPDATE wallets set wallet = wallet + ?, partnertxid = ? ");
//							
//							updateWallet.append("WHERE walletid = ? and partnerid = ?");
//							
//							try{
//								
//								poRow = getJdbcTemplate().update(updateWallet.toString(), new Object[] { 
//									   total_amount,txid,walletid,session.getAttribute("PID")
//									});
//								
//								
//							}catch(DataAccessException ex){
//					            ex.printStackTrace();
//							}
//							
//							ApplicationContext mailcontext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
//						
//						
//							Props props = new Props();
//							
//							MailModel mm = (MailModel) mailcontext.getBean("mail");
//							
//							String [] recipient =  props.getRecipients().split(",");
//							
//							String sender = props.getSender();
//							
//							mm.sendMail(sender,
//									    recipient,
//						    		   "Settlement Payment Notification", 
//						    		   "Hi, \n\n Settlement by "+pid+"  of PHP "+total_amount+" has been paid with PO "+poid+" please process settlement of transactions. \n\nThanks, \n\n"+session.getAttribute("USER")+"");
//							
//							StringBuilder updateStatus = new StringBuilder();
//							
//							updateStatus.append("UPDATE po_orders SET delivery_status = ? ");
//							updateStatus.append("where POId = ?");
//							
//							try{
//								poRow = getJdbcTemplate().update(updateStatus.toString(), new Object[] { 
//									   del_status,poid
//									});
//							}catch(DataAccessException ex){
//					            ex.printStackTrace();
//							}
//						}
//						
//					}
//					
//		}	
//		
//		
//		return false;
//		
//	}
	
	public boolean insertDeliveredOrder(HttpSession session, int poid ){
		
		String status = "delivered";
		
		int poRow = 0;
				
				StringBuilder updateDeliver = new StringBuilder();
				
				updateDeliver.append("update po_orders set delivery_status = ? where POId = ?");
				
				try{
					   
					   poRow = getJdbcTemplate().update(updateDeliver.toString(), new Object[] { 
						   status,poid,
						});
					   
					   
				
				   }catch(DataAccessException ex){
			            ex.printStackTrace();
			
			        }
			
		
		return false;
	}
	

	public boolean insertPaymentOrder(HttpSession session, int poid, HttpServletRequest request,PaymentOrderModel model) throws IOException{
				
		String type2 = "Manual";
		String type3 = "Bancnet";
		
		this.getPurchaseTotal(session, poid);
    	this.getPurchaseList(session, poid);
		this.getPaymentOrderItemsDetails(session, poid);
		
		PaymentOrderModel pom = new PaymentOrderModel();
		
		
				
		RestTemplate template = new RestTemplate();
		
		pom.setBranch(request.getParameter("branch"));
		pom.setRemarks(request.getParameter("text"));
		pom.setType(request.getParameter("type"));
	
		final int purchaseid = poid;
	
		
		if(pom.getType().equalsIgnoreCase("1"))
		
		{
			
			StringBuilder chkSql = new StringBuilder();
			
			chkSql.append("SELECT status FROM po_payment ");
			chkSql.append("WHERE POId = ?");
			
			SqlRowSet rs   = getJdbcTemplate().queryForRowSet(chkSql.toString(),poid);
			
			if(!rs.next()) {

						
						int poRow = 0;
						
						
						
						   final  StringBuilder strSQL = new StringBuilder();
							  
							
						   strSQL.append("INSERT INTO po_payment (Payment_TXID, payment_type, total_amount, total_order, total_fee, POId)");
						   strSQL.append("VALUES (?,?,(select order_amount from po_orders where poid = ?),(select order_amount from po_orders where poid = ?),?,?)");
						   
						   KeyHolder keyHolder = new GeneratedKeyHolder();
						   
							int row = getJdbcTemplate().update(new PreparedStatementCreator() 
							{
								  public PreparedStatement createPreparedStatement(Connection con)
										    throws SQLException 
										    {
										   PreparedStatement ps = con.prepareStatement(strSQL.toString(),Statement.RETURN_GENERATED_KEYS);

										   ps.setInt(1, purchaseid);
										   ps.setString(2, "Manual");
										   ps.setInt(3, purchaseid);
										   ps.setInt(4, purchaseid);
										   ps.setString(5, null);
										   ps.setInt(6, purchaseid);
										  
										 
										   return ps;
										     }

						
							}, keyHolder);
						   
						   
							int paymentid = keyHolder.getKey().intValue();
							
							if(row>0)
								
								
							{
							

								try{
									 									
									 StringBuilder insertManual = new StringBuilder();
									 
									 insertManual.append("INSERT INTO po_payment_manual_details(paymentId,branch, amount, attachments, remarks)");
									 insertManual.append("VALUES (?,?, (select order_amount from po_orders where poid = ?), ?, ?)");
									 
									 try{
										 poRow = getJdbcTemplate().update(insertManual.toString(), new Object[]{
											 paymentid,pom.getBranch(),purchaseid,model.getAttachment(),pom.getRemarks()
										 });
										 
										 
										 
											if(poRow>0)
											
											
											{
												
												StringBuilder updateSql = new StringBuilder();
												
												updateSql.append("UPDATE po_orders SET ");
												updateSql.append("payment_status = ? ");
												updateSql.append("WHERE POId = ? and partnerid = ?");
												
												try{
													   
													   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
														   	"for verification",purchaseid,session.getAttribute("PID")
														});
													   
													   
												
												   }catch(DataAccessException ex){
											            ex.printStackTrace();
											
											        }
												
												return true;
												
												
											}
										 
										 									 
										 return true;
										 
										 
									 }catch(DataAccessException ex){
								            ex.printStackTrace();
									 }
									 
								 }catch(DataAccessException ex){
							            ex.printStackTrace();
								 }
							
							}
	
			}
			else
			{	
				System.out.println("Payment Already Exist!");
			}
			return false;		
		}	
		 
		return false;
	}
public boolean updatePaymentOrderForm(HttpSession session, int id, HttpServletRequest request) {
		
			int poRow = 0;
			
			StringBuilder strSQL = new StringBuilder();
			
			strSQL.append("SELECT status FROM po_payment ");
			strSQL.append("WHERE POId = ?");
			
			SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),id);
			
			if(rs.next()){
				
				String status = rs.getString("status");
				
				if(status.equalsIgnoreCase("checking")){
						
						
						StringBuilder update = new StringBuilder();
						
						update.append("UPDATE po_payment a INNER JOIN po_orders b ON a.POId = b.POId SET a.status = ?, b.delivery_status = ?, b.PO_status = ? ");
						update.append("WHERE a.POId = ?");
						
						try{
							
							poRow = getJdbcTemplate().update(update.toString(), new Object[] {
							 	"cancelled","cancelled","cancelled",id					 								 	
						 });
							
							StringBuilder checkSQL = new StringBuilder();
							
							checkSQL.append("SELECT payment_status FROM po_orders ");
							checkSQL.append("WHERE POId = ?");
							
							SqlRowSet rs2   = getJdbcTemplate().queryForRowSet(checkSQL.toString(),id);
							
							if(rs2.next()){
								
								StringBuilder updatePO = new StringBuilder();
								
								updatePO.append("UPDATE po_orders SET payment_status = ? ");
								updatePO.append("WHERE POId = ? ");
								
								try{
									
									poRow = getJdbcTemplate().update(updatePO.toString(), new Object[] {
										"cancelled",id					 								 	
								 });
								
								}catch(DataAccessException ex){
						            ex.printStackTrace();
								}
								System.out.println("PO Orders Status has been updated!");
								
							}
							
						}
						catch(DataAccessException ex){
				            ex.printStackTrace();
						}
						System.out.println("PO Payment Status has been updated!");
						return true;
				}
				else{
					System.out.println("PO Payment Status has not been updated!");
					return false;
				}
			}
		return false;
	}	
	public List<PaymentOrderModel> getAttachment(HttpSession session, int id){
		
		ArrayList<PaymentOrderModel> attachementlist = new ArrayList<PaymentOrderModel>();
		
		StringBuilder strAttach = new StringBuilder();
		
		strAttach.append("SELECT a.attachments FROM po_payment_manual_details a ");
		strAttach.append("INNER JOIN po_payment b ON a.PaymentId = b.PaymentId ");
		strAttach.append("INNER JOIN po_orders c ON b.POId = c.POId ");
		strAttach.append("WHERE b.POId = ?");
		
		attachementlist.clear();
		
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strAttach.toString(),id);
		
		for (Map row : rows) {
			
			PaymentOrderModel PO = new PaymentOrderModel();
			
			PO.setFile((String) (row.get("attachments")));
			
			attachementlist.add(PO);			
		} 
		
			
			
			
		
		return attachementlist ;
		
	}
	
	
	public void getPartnerDiscount(HttpSession session,String item_name){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("select discount, discount_type from partner_items where partnerid = ? ");
		   strSQL.append(" and item_id = (select item_id from purchase_items where item_name = ?)");

			
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),session.getAttribute("PID"),item_name);
			
			if(rs.next()) 
			{
				
				discount_type = rs.getString("discount_type");
				discount = rs.getBigDecimal("discount");
			
			}
			
			
			else
			
			
			{
				
				   if(session.getAttribute("USERLEVEL").toString().equalsIgnoreCase("manager")){
					   
					   partner_type = "sub_dealer";
					   
				   } else if(session.getAttribute("USERLEVEL").toString().equalsIgnoreCase("user")){
					   
					   partner_type = "retailer";
				   }
				   
				
				   StringBuilder getDefaultSQL = new StringBuilder();
				   
				   getDefaultSQL.append("select discount, discount_type from purchase_item_default_price where partner_type = ? ");
				   getDefaultSQL.append(" and item_id = (select item_id from purchase_items where item_name = ?)");

					
				   SqlRowSet defaultRs   = getJdbcTemplate().queryForRowSet(getDefaultSQL.toString(),partner_type,item_name);
					
					if(defaultRs.next()) {
						
						discount_type = defaultRs.getString("discount_type");
						discount = defaultRs.getBigDecimal("discount");
					
					}
				
				
				
				
				
				
			}
			
			
			
	}
	
	public void getWalletid(HttpSession session,String wallet_name){
		
		   
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT a.walletid,a.partnerid ,c.wallet_name FROM wallets a,partners_wallet b,wallet_types c ");
		   strSQL.append("WHERE a.partnerid = ? AND a.walletid = b.walletid ");
		   strSQL.append("AND b.wallet_type = c.wallet_type and c.wallet_name = ?");


			
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),session.getAttribute("PID"),wallet_name);
			
			if(rs.next()) {
				
				walletid = rs.getInt("walletid");
			
			}
			
	}
	
	public void getfaceValue(String item_name){
		
		   
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT face_value_amount from purchase_items where item_name = ? ");
	


			
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),item_name);
			
			if(rs.next()) {
				
				facevalue = rs.getBigDecimal("face_value_amount");
				
			
			}
	
			
	}
	
	public List<PaymentOrderModel> getPaymentOrderItemsDetails(HttpSession session, int poid){
		
		ArrayList<PaymentOrderModel> paymentorderList = new ArrayList<PaymentOrderModel>();
		
		StringBuilder strSql = new StringBuilder();
		
		String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equals("manager"))
		   {
		
			strSql.append("SELECT a.PaymentId, a.payment_type, a.total_amount, a.total_order, a.total_fee, a.POId, a.status,");
			strSql.append("a.datecreated, a.payment_date, a.returned_amount, a.returned_date, b.branch, b.amount, b.remarks, c.payment_status, c.po_status, c.delivery_status ");
			strSql.append("FROM po_payment a ");
			strSql.append("INNER JOIN po_payment_manual_details b ON a.PaymentId = b.PaymentId ");
			strSql.append("INNER JOIN po_orders c ON a.POId = c.POId ");
			strSql.append("WHERE a.POId = ?");
		
			paymentorderList.clear();
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSql.toString(),poid);
			
			for (Map row: rows){
				
				SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
				String paymentDate = "";
				String returnedDate = "";
				String dateCreated = "";
				
				if(row.get("payment_date")!= null)
				{
					paymentDate = formatDate.format(row.get("payment_date"));
					
				}
				else{
					paymentDate="N/A";	
				}
				if(row.get("returned_date")!=null){
					
					returnedDate = formatDate.format(row.get("returned_date"));
				}
				else{
					
					returnedDate="N/A";
				}
				if(row.get("datecreated")!=null){
					
					dateCreated = formatDate.format(row.get("datecreated"));
				}
				else{
					
					returnedDate="N/A";
				}
				
				
				PaymentOrderModel pom = new PaymentOrderModel();
						
				pom.setBranch((String) (row.get("branch")));
				pom.setRemarks((String)(row.get("remarks")));
				pom.setPaymentID((int) (row.get("PaymentId")));
				pom.setDate_created(dateCreated);
				pom.setPayment_date(paymentDate);
				pom.setReturned_date(returnedDate);
				pom.setStatus((String)(row.get("status")));
				pom.setTotal_amount((String)(row.get("total_amount")+""));
				pom.setTotal_fee((String)(row.get("total_fee")));
				pom.setTotal_order((String)(row.get("total_order")+""));
				pom.setType((String)(row.get("type")));
				pom.setPayment_status((String)(row.get("payment_status")));
				pom.setPo_status((String)(row.get("po_status")));
				pom.setPoid((int)(row.get("POId")));
				pom.setDelivery_status((String)(row.get("delivery_status")));
				
				 status = pom.getStatus();
				
				
				paymentorderList.add(pom);	
		}
			
	}
		   if(role.equals("superadmin"))
		   {
		
			strSql.append("SELECT a.PaymentId, a.payment_type, a.total_amount, a.total_order, a.total_fee, a.POId, a.status,");
			strSql.append("a.datecreated, a.payment_date, a.returned_amount, a.returned_date, b.branch, b.amount, b.remarks, c.payment_status, c.po_status,  c.delivery_status  ");
			strSql.append("FROM po_payment a ");
			strSql.append("INNER JOIN po_payment_manual_details b ON a.PaymentId = b.PaymentId ");
			strSql.append("INNER JOIN po_orders c ON a.POId = c.POId ");
			strSql.append("WHERE a.POId = ? ");
		
			paymentorderList.clear();
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSql.toString(),poid);
			
			for (Map row: rows){
				
				SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
				String paymentDate = "";
				String returnedDate = "";
				String dateCreated = "";
				
				if(row.get("payment_date")!= null)
				{
					paymentDate = formatDate.format(row.get("payment_date"));
					
				}
				else{
					paymentDate="N/A";	
				}
				if(row.get("returned_date")!=null){
					
					returnedDate = formatDate.format(row.get("returned_date"));
				}
				else{
					
					returnedDate="N/A";
				}
				if(row.get("datecreated")!=null){
					
					dateCreated = formatDate.format(row.get("datecreated"));
				}
				else{
					
					returnedDate="N/A";
				}
				
				
				PaymentOrderModel pom = new PaymentOrderModel();
						
				pom.setBranch((String) (row.get("branch")));
				pom.setRemarks((String)(row.get("remarks")));
				pom.setPaymentID((int) (row.get("PaymentId")));
				pom.setDate_created(dateCreated);
				pom.setPayment_date(paymentDate);
				pom.setReturned_date(returnedDate);
				pom.setStatus((String)(row.get("status")));
				pom.setTotal_amount((String)(row.get("total_amount")+""));
				pom.setTotal_fee((String)(row.get("total_fee")));
				pom.setTotal_order((String)(row.get("total_order")+""));
				pom.setType((String)(row.get("type")));
				pom.setPayment_status((String)(row.get("payment_status")));
				pom.setPo_status((String)(row.get("po_status")));
				pom.setPoid((int)(row.get("POId")));
				pom.setDelivery_status((String)(row.get("delivery_status")));
				
				 status = pom.getStatus();
				 
//				BigDecimal return_amount = new BigDecimal(row.get("returned_amount")+"");
//			    pom.setReturned_amount(return_amount+"");
				
				paymentorderList.add(pom);	
		}
			
	}  
		
		return paymentorderList;
		
	}

	public List<PurchaseOrderModel>  getOthersPurchaseOrderItemsDetails(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equalsIgnoreCase("manager"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE   b.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) ");
			   strSQL.append("and c.poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
					//PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					PO.setTotal_amount((String)(row.get("price")+""));
					
					
					
					
					
					
					purchaseorderlist.add(PO);			
				}
			   
		   }

		
			return purchaseorderlist;
		   	  
	   }
	
	public List<PurchaseOrderModel>  getPurchaseOrderItemsDetails(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equalsIgnoreCase("manager"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE   b.partnerid = ? ");
			   strSQL.append("and c.poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					
					if((row.get("itemcode")).toString().equalsIgnoreCase("lop"))
					{
						BigDecimal price = (BigDecimal)(row.get("price"));
						
						BigDecimal dc = (BigDecimal)(row.get("discount_amount"));
						
						BigDecimal qty = new BigDecimal((int)(row.get("qty")));
						
						PO.setTotal_amount(price.multiply(qty).subtract(dc)+"");
					}
					else
					{
						BigDecimal price = (BigDecimal)(row.get("price"));
						
						BigDecimal dc = (BigDecimal)(row.get("discount_amount"));
						
						BigDecimal qty = new BigDecimal((int)(row.get("qty")));
						
						PO.setTotal_amount(price.multiply(qty).subtract(dc)+"");
					}

					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }
		   if (role.equalsIgnoreCase("superadmin")){
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE  b.parent_partnerid is null and c.poid = ? ");
			   
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
					PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					
					
					
					
					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }
			   
		   

		
			return purchaseorderlist;
		   	  
	   }
	
	public List<PurchaseOrderModel>  getPurchaseOrderItemsRetailers(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equalsIgnoreCase("manager"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE c.poid = ? ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
					PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					
					
					
					
					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }
		   if (role.equalsIgnoreCase("superadmin")){
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE c.poid = ? ");
			
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
					PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					
					
					
					
					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }
			   
		   

		
			return purchaseorderlist;
		   	  
	   } 
	
	public List<PurchaseOrderModel>  getPurchaseList(HttpSession session, int poid)
	   {
		ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equals("manager"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, sum(a.order_amount) as total_order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE b.partnerid = ? ");
			   strSQL.append("and c.poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = "";
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						dateAsString = simpleDateFormat.format(row.get("po_date"));
						
					}
					else{
						dateAsString="N/A";	
					}
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
//					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
//					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
//					PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					
					total_order = (BigDecimal) row.get("total_order_amount");
					
					
					
					purchaseorderlist.add(PO);			
				} 
			   
		   }
		   else if (role.equals("superadmin")){
		   
			   
			   strSQL.append("SELECT a.poid, a.po_date, sum(a.order_amount) as total_order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE ");
			   strSQL.append(" c.poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = "";
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					if(row.get("po_date")!=null){
						
						dateAsString = simpleDateFormat.format(row.get("po_date"));
					}
					else{
						
						dateAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					
					BigDecimal dcamount = (BigDecimal)(row.get("discount_amount"));
					BigDecimal qty = new BigDecimal((int)(row.get("qty")));
					
					PO.setTotal_amount(dcamount.multiply(qty)+"");
					
					
					total_order = (BigDecimal) row.get("total_order_amount");
					
					
					
					purchaseorderlist.add(PO);			
				} 	   
			   
		   }
			   
		   

		
			return purchaseorderlist;
		   	  
	   } 
	
	public List<PurchaseOrderModel>  getPurchaseTotal(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equals("manager"))
		   {
			   
			   strSQL.append("SELECT order_amount from po_orders ");
			   strSQL.append("WHERE partnerid = ? ");
			   strSQL.append("and poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				BigDecimal total3 = new BigDecimal(0);
				
				PurchaseOrderModel PO = new PurchaseOrderModel();
				
				for (Map row : rows) {
									
		
					
					System.out.println("dito ako kinukuha hahaha");
		
						
						PO.setTotal_amount((String)(row.get("order_amount")+""));
					

					
						
					
				}
	
				purchaseorderlist.add(PO);		
				
		   }
		   else if (role.equals("superadmin"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE ");
			   strSQL.append(" c.poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
				BigDecimal total3 = new BigDecimal(0);
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
					
					String totalpaid = "";
					
					if(row.get("cancelled_date")!= null)
					{
						cancelAsString = simpleDateFormat.format(row.get("cancelled_date"));
						
					}
					else{
						cancelAsString="N/A";	
					}
					if(row.get("delivered_date")!=null){
						
						deliverAsString = simpleDateFormat.format(row.get("delivered_date"));
					}
					else{
						
						deliverAsString="N/A";
					}
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					
					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					PO.setItem((String)(row.get("itemcode")));
					PO.setQuantity((String)(row.get("qty")+""));
					PO.setFace_value_amount((String)(row.get("price")+""));
					PO.setDiscount_amount((String)(row.get("discount_amount")+""));
					PO.setWallet((String)(row.get("wallet_name")));
					PO.setAmount_paid((String)(row.get("paid_amount")+""));
					PO.setCancel_date((String)(cancelAsString));
					PO.setDeliver_date((String)(row.get("delivered_date")+""));
					PO.setPartner_name((String)(row.get("partnerid")));
					
					if(PO.getItem().equalsIgnoreCase(PO.getItem()))
					{
						BigDecimal dcamount1 = (BigDecimal)(row.get("discount_amount"));
						BigDecimal qty1 = new BigDecimal((int)(row.get("qty")));
						
						BigDecimal total2 = dcamount1.multiply(qty1);
						
						total3 = total3.add(total2);
		
					}
					
				}
				
				PurchaseOrderModel POS = new PurchaseOrderModel();
				
				POS.setTotal_amount(total3+"");
				total_amount = total3;
				
					
				purchaseorderlist.add(POS);
							   
			   
		   }
			   
		   

		
			return purchaseorderlist;
		   	  
	   } 
	
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRequestListHistory(HttpSession session)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   
		   
		   if(session.getAttribute("USERLEVEL").equals("manager"))
		   
		   
		   {
			   StringBuilder strSQL = new StringBuilder()	;

			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE b.partnerid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = "";
					
					if(row.get("po_date")!= null)
					{
						dateAsString = simpleDateFormat.format(row.get("po_date"));
						
					}
					else{
						dateAsString="N/A";	
					}
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));
					
					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;

			   
	
			   	  
		   } 
			   
		   else    if(session.getAttribute("USERLEVEL").equals("user"))
			   
		   {
			   StringBuilder strSQL = new StringBuilder()	;
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE b.partnerid = ? ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));

					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;
			   	  
		   } else    if(session.getAttribute("USERLEVEL").equals("superadmin"))
			   
		   {
			   StringBuilder strSQL = new StringBuilder();
			   
			   strSQL.append("SELECT a.poid, a.datecreated, a.status, b.partnerid, b.payment_status, b.delivery_status,c.partnername, d.amount,  d.branch, d.attachments ");
			   strSQL.append("FROM po_payment a ");
			   strSQL.append("INNER JOIN po_orders b ON a.POId = b.POId ");
			   strSQL.append("INNER JOIN partners c ON b.partnerid = c.partnerid ");
			   strSQL.append("INNER JOIN po_payment_manual_details d ON a.paymentId = d.paymentId ");
			   strSQL.append("WHERE parent_partnerid is null");
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String DateCreated = "";
					
					if(row.get("datecreated")!= null)
					{
						DateCreated = simpleDateFormat.format(row.get("datecreated"));
						
					}
					else{
						DateCreated="N/A";	
					}
					
					
					PurchaseOrderModel POM = new PurchaseOrderModel();

					POM.setPoid((int)(row.get("poid")));
					POM.setPartnerid((String)(row.get("partnerid")));
					POM.setPartnername((String)(row.get("partnername")));
					POM.setBranch((String)(row.get("branch")));
					POM.setStatus((String)(row.get("status")));
					POM.setPayment_status((String)(row.get("payment_status")));
					POM.setDate_created(DateCreated);		
					POM.setDelivery_status((String)(row.get("delivery_status")));
					POM.setFile((String)(row.get("attachments")));
					
					BigDecimal total_amount = new BigDecimal(row.get("amount")+"");
					
					POM.setTotal_amount(total_amount+"");
					
					System.out.println(POM.getFile());
					
					purchaseorderlist.add(POM);			
				}
			
				
				
		   }
			
				return purchaseorderlist;
			   
			   
}	
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRetailersList(HttpSession session)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   if(session.getAttribute("USERLEVEL").equals("manager"))
		   
		   
		   {
			   

			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name  ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type ");
			   strSQL.append("WHERE b.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					PO.setPoid((int)(row.get("poid")));
					PO.setRetailer((String)(row.get("partnerid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));

					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;

			   
	
			   	  
		   } 
			   
		   else    if(session.getAttribute("USERLEVEL").equals("user"))
			   
		   {
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE b.partnerid = ? ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					PO.setPoid((int)(row.get("poid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));

					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;
			   	  
		   } else    if(session.getAttribute("USERLEVEL").equals("superadmin"))
			   
		   {

			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status,a.partnerid ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE  b.parent_partnerid = null ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					PO.setPoid((int)(row.get("poid")));
					PO.setRetailer((String)(row.get("partnerid")));
					PO.setPodate((String)(dateAsString));
					PO.setOrder_amount((String)(row.get("order_amount")+""));
					PO.setPayment_status((String)(row.get("payment_status")));
					PO.setDelivery_status((String)(row.get("delivery_status")));
					PO.setPo_status((String)(row.get("po_status")));

					
					purchaseorderlist.add(PO);			
				}
				
		   }
			
				return purchaseorderlist;
			   
			   
		   }
	
	public boolean cancelPayment(HttpSession session, int poid){
			
		StringBuilder strSQL = new StringBuilder();
		int PayId = 0;
		String Paytype = "";
		String status = "";
		String delivery_status = "";   
		String stat = "cancelled";
		int poRow = 0;
		
		   strSQL.append("SELECT a.PaymentId, a.payment_type, a.status, b.delivery_status from po_payment a  ");
		   strSQL.append("INNER JOIN po_orders b ON a.POId = b.POId ");
		   strSQL.append("where a.POId = ?");		   
		   
		
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
			
			if(rs.next()) {
				
				PayId = rs.getInt("PaymentId");
				Paytype = rs.getString("payment_type");
				status = rs.getString("status");
				delivery_status = rs.getString("delivery_status");
				
				if(Paytype.equalsIgnoreCase("Manual")){
					
					if(status.equalsIgnoreCase("checking")){
						
						if(!delivery_status.equalsIgnoreCase("delivered")){
							
							StringBuilder updateSql = new StringBuilder();
							
							updateSql.append("UPDATE po_payment SET ");
							updateSql.append("status = ? ");
							updateSql.append("WHERE PaymentId = ?");
							
							try{
								   
								   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
									   stat,PayId,
									});
								   
								   
							
							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						
						        }
						}
						
					}
				}
				if(!Paytype.equalsIgnoreCase("Manual")){
					
					if(status.equalsIgnoreCase("checking")){
						
						if(!delivery_status.equalsIgnoreCase("delivered")){
							
							StringBuilder updateSql = new StringBuilder();
							
							updateSql.append("UPDATE po_payment SET ");
							updateSql.append("status = ? ");
							updateSql.append("WHERE PaymentId = ?");
							
							try{
								   
								   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
									   stat,PayId,
									});
								   
								   
							
							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						
						        }
						}
						
					}
				}
			
			}
	
		
		return false;
	}
	
	public boolean confirmPayment(HttpSession session, int poid){
		
		StringBuilder strSQL = new StringBuilder();
		int PayId = 0;
		String Paytype = "";
		String status = "";
		String delivery_status = "";  
		BigDecimal total_amount = new BigDecimal(0);
		String stat = "confirmed";
		String partnerid;
		int poRow = 0;
		
		   strSQL.append("SELECT a.PaymentId, a.total_amount, a.payment_type, a.status, b.delivery_status b.partnerid from po_payment a  ");
		   strSQL.append("INNER JOIN po_orders b ON a.POId = b.POId ");
		   strSQL.append("where a.POId = ?");		   
		   
		
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
			
			if(rs.next()) {
				
				PayId = rs.getInt("PaymentId");
				Paytype = rs.getString("payment_type");
				status = rs.getString("status");
				delivery_status = rs.getString("delivery_status");
				total_amount = rs.getBigDecimal("total_amount");
				partnerid = rs.getString("partnerid");
				
				if(Paytype.equalsIgnoreCase("Manual"))
				{
					
					if(status.equalsIgnoreCase("checking")){
						
						StringBuilder updateSql = new StringBuilder();
						
						updateSql.append("UPDATE po_payment SET ");
						updateSql.append("status = ? ");
						updateSql.append("WHERE PaymentId = ?");
						
						try{
							   
							   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
								   stat,PayId,
								});
							   
							   if(poRow>0)
							   {
								   
				
									StringBuilder updateSql1 = new StringBuilder();
									
									updateSql1.append("UPDATE po_orders SET ");
									updateSql1.append("payment_status = ?, delivery_status = ? ");
									updateSql1.append("WHERE poid = ? AND partnerid = ?");
									
									try{
										   
										   int PaymentStatRow = getJdbcTemplate().update(updateSql1.toString(), new Object[] { 
											   "paid","for delivery", poid, partnerid											
											   });
										   
										   
										   if(PaymentStatRow>0){
											   
											   return true;
										   }
									
										   
										   
										   
									   }catch(DataAccessException ex){
								            ex.printStackTrace();
								
								        }
								   
								   
								   
								   
							   }
							   
//							   StringBuilder strSql = new StringBuilder();
//							   
//							   strSql.append("SELECT status FROM po_payment WHERE POId = ?");
//							   
//							   SqlRowSet rss   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
//							   
//							   if(rss.next())
//							   {
//								   
//								   if(stat.equalsIgnoreCase("confirmed"))
//								   {
//										
//										if(!delivery_status.equalsIgnoreCase("delivered")){
//											
//											String stat1 = "returned";
//											StringBuilder updateSql1 = new StringBuilder();
//											
//											updateSql1.append("UPDATE po_payment SET ");
//											updateSql1.append("status = ?, returned_amount = ?, returned_date = NOW() ");
//											updateSql1.append("WHERE PaymentId = ? AND status = ?");
//											
//											try{
//												   
//												   poRow = getJdbcTemplate().update(updateSql1.toString(), new Object[] { 
//													   stat1,total_amount,PayId,stat,
//													});
//												   
//												   
//											
//											   }catch(DataAccessException ex){
//										            ex.printStackTrace();
//										
//										        }
//										}
//									}
//							   }
							   
							   
						
						   }catch(DataAccessException ex){
					            ex.printStackTrace();
					
					        }
						
						
						
					}
				}
				
				
				
				
				
				
				else if(!Paytype.equalsIgnoreCase("Manual")){
					
					StringBuilder updateSql = new StringBuilder();
					
					updateSql.append("UPDATE po_payment SET ");
					updateSql.append("status = ? ");
					updateSql.append("WHERE PaymentId = ?");
					
					try{
						   
						   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
							   stat,PayId,
							});
						   
						   StringBuilder strSql = new StringBuilder();
						   
						   strSql.append("SELECT status FROM po_payment WHERE POId = ?");
						   
						   SqlRowSet rss   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
						   
						   if(rss.next()){
							   
							   if(stat.equalsIgnoreCase("confirmed")){
									
									if(!delivery_status.equalsIgnoreCase("delivered")){
										
										String stat1 = "returned";
										StringBuilder updateSql1 = new StringBuilder();
										
										updateSql1.append("UPDATE po_payment SET ");
										updateSql1.append("status = ?, returned_amount = ?, returned_date = NOW() ");
										updateSql1.append("WHERE PaymentId = ? AND status = ?");
										
										try{
											   
											   poRow = getJdbcTemplate().update(updateSql1.toString(), new Object[] { 
												   stat1,total_amount,PayId,stat,
												});
											   
											   
										
										   }catch(DataAccessException ex){
									            ex.printStackTrace();
									
									        }
									}
								}
						   }
						   
						   
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				
				        }
				}
			
			}
	
		
		return false;
	}
		   
	
//	public List<PurchaseOrderModel>  getFinishedPurchasedOrder(HttpSession session, int poid)
//	   {
//		
//		 if(session.getAttribute("USERLEVEL").equals("superadmin"))
//			 
//		 {
//			 
//			 
//			   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
//				
//			   StringBuilder strSQL = new StringBuilder()	;
//			   
//			   
//			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
//			   strSQL.append("FROM agents a  ");
//			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
//			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
//			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
//			   strSQL.append("WHERE   d.parent_partnerid is null   and c.id = ?");
//			   
//			   purchaseorderlist.clear();
//			 
//				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
//				
//				for (Map row : rows) {
//					
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
//					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
//					
//					
//					PurchaseOrderModel PO = new PurchaseOrderModel();
//					PO.setId((Integer)(row.get("id")));
//					PO.setUsername((String)(row.get("username")));
//					PO.setDate((String)(dateAsString));
//					PO.setAmount((String)(row.get("amount")+""));
//					PO.setBank((String)(row.get("bank")));
//					PO.setBranch((String)(row.get("branch")));
//					PO.setRemarks((String)(row.get("remarks")));
//					PO.setStatus((String)(row.get("status")));
//					PO.setPartnername((String)(row.get("partnername")));
//					
//					purchaseorderlist.add(PO);			
//				}
//			
//				return purchaseorderlist;
//			 
//			 
//		 }else{
//			 
//			   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
//				
//			   StringBuilder strSQL = new StringBuilder()	;
//			   
//			   
//			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
//			   strSQL.append("FROM agents a  ");
//			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
//			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
//			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
//			   strSQL.append("WHERE   d.parent_partnerid = (select partner from partners where partnerid = ?)   and c.id = ?");
//			   
//			   purchaseorderlist.clear();
//			 
//				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
//				
//				for (Map row : rows) {
//					
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
//					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
//					
//					
//					PurchaseOrderModel PO = new PurchaseOrderModel();
//					PO.setId((Integer)(row.get("id")));
//					PO.setUsername((String)(row.get("username")));
//					PO.setDate((String)(dateAsString));
//					PO.setAmount((String)(row.get("amount")+""));
//					PO.setBank((String)(row.get("bank")));
//					PO.setBranch((String)(row.get("branch")));
//					PO.setRemarks((String)(row.get("remarks")));
//					PO.setStatus((String)(row.get("status")));
//					PO.setPartnername((String)(row.get("partnername")));
//					
//					purchaseorderlist.add(PO);			
//				}
//			
//				return purchaseorderlist;
//			 
//			 
//			 
//		 }
//
//		   	  
//	   } 
	

	
	
	
	public Map fillItemCode(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT item_name from purchase_items ");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("item_name")), (String)row.get("item_name"));
		
			}
			
			return prefix;
			
	}
	
	public List<PurchaseOrderBean> fillItemCodeList(HttpSession session){
		
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   ArrayList<PurchaseOrderBean> purchaseorderlist = new ArrayList<PurchaseOrderBean>();
		
		   if(role.equalsIgnoreCase("manager")){
		   
			   StringBuilder strSQL = new StringBuilder();	  
			   
			   strSQL.append("SELECT item_name,face_value_amount from purchase_items ");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					PurchaseOrderBean PO = new PurchaseOrderBean();
					PO.setItemname((String)(row.get("item_name")));
					PO.setFacevalue((String)(row.get("face_value_amount")+""));
					
					
					purchaseorderlist.add(PO);
			
				}
				return purchaseorderlist;
		   }
		   else if(role.equalsIgnoreCase("user")){
			   
			   StringBuilder strSQL = new StringBuilder();	  
			   
			   strSQL.append("SELECT item_name,face_value_amount from purchase_items WHERE item_id = ? ");
				
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),1);
				
				for (Map row : rows) {
					
					PurchaseOrderBean PO = new PurchaseOrderBean();
					PO.setItemname((String)(row.get("item_name")));
					PO.setFacevalue((String)(row.get("face_value_amount")+""));
					
					
					purchaseorderlist.add(PO);
			
				}
			   
			   
			   return purchaseorderlist;
		   }
		 
			
		   return purchaseorderlist;
	}
	
	public List<PurchaseOrderBean> fillWalletlist(HttpSession session){
		
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   ArrayList<PurchaseOrderBean> purchaseorderlist = new ArrayList<PurchaseOrderBean>();
		   
		   strSQL.append("SELECT a.walletid,a.partnerid ,c.wallet_name FROM wallets a,partners_wallet b,wallet_types c ");
		   strSQL.append("WHERE a.partnerid = ? AND a.walletid = b.walletid ");
		   strSQL.append("AND b.wallet_type = c.wallet_type ");
			
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
			
			for (Map row : rows) {
				
				PurchaseOrderBean PO = new PurchaseOrderBean();
				
				PO.setWallet((String)(row.get("wallet_name")));
	
				wallet_type = PO.getWallet();
				
				purchaseorderlist.add(PO);
		
			}
			
			return purchaseorderlist;
			
	}

	
	public Map fillWallettypes(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT a.walletid,a.partnerid ,c.wallet_name FROM wallets a,partners_wallet b,wallet_types c");
		   strSQL.append("WHERE a.partnerid = ? AND a.walletid = b.walletid");
		   strSQL.append("AND b.wallet_type = c.wallet_type");
		   
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("wallet_name")), (String)row.get("wallet_name"));
		
			}
			
			return prefix;
			
	}
	
	public String getTransid(){
		
		   String updateSeq = "update wallet_transfer_sequence set seq = seq + 1";
		   
		   String txid = "MTB";
		   
			try{
		 		 
				int row2 = getJdbcTemplate().update(updateSeq, new Object[] { 
						
				});
				
				if(row2>0 )
				{
					
					   StringBuilder strSQL = new StringBuilder();
						 
					 
					   
					   strSQL.append("SELECT seq from wallet_transfer_sequence");
					
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
						
					   for (Map row : rows) {
							
						   txid = txid+(Integer)row.get("seq");
					
						}
						
					   return txid;
					
				
				}
				
				
				   return txid;
				
			
			}catch(Exception ex){
         ex.printStackTrace();
         return txid;
       
     }
		

		
		
	}
	public boolean transfertobranch(HttpSession session, TransactionIDObject obj,int poid, String type){
		
		if(type.equalsIgnoreCase("approve")){
		
			String txid = this.getTransid();
			String status = "delivered";
			BigDecimal total_amount;
			
			obj.transactionID = txid;
			
			StringBuilder strSql = new StringBuilder();
			
			strSql.append("SELECT a.order_amount,a.partnerid , b.partnerid FROM po_orders a INNER JOIN partners b ON a.partnerid = b.partnerid ");
			strSql.append("WHERE a.poid = ? AND b.parent_partnerid = (SELECT partner FROM partners  WHERE partnerid = ? )");
			
			SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSql.toString(),poid,session.getAttribute("PID"));
			
			if(rs.next()) {
			
			pid = rs.getString("partnerid");
			total_amount = rs.getBigDecimal("order_amount");
			
			String deductSubdealerWallet = "Update wallets	 set wallet = wallet - ?,partnertxid = ? where  partnerid = ? and (wallet - ?) >= 0 and isdefault = ?";
			
			String addRetailerWallet = "Update wallets set wallet = wallet + ?, partnertxid = ? where partnerid = ?    and (wallet + ?) >= 0 and isdefault = ?";
			
			String updateDeliver = "Update po_orders set delivery_status = ?  where POId = ? ";
			
			try{
				
				int row = getJdbcTemplate().update(deductSubdealerWallet, new Object[] { 
						total_amount,txid,session.getAttribute("PID"),total_amount,1
				});
				
				int row2 = 0;
				if ( row > 0)
				{
				    row2 = getJdbcTemplate().update(addRetailerWallet, new Object[] { 
				    		total_amount,txid,pid,total_amount,1
				});
				
				}
				
				if(row>0 && row2>0){
					return true;
				}
				
					try{
						
						int row3 = getJdbcTemplate().update(updateDeliver, new Object[] { 
								status,poid
						});
						
					}catch(Exception ex){
			            ex.printStackTrace();
					}
				
				}catch(Exception ex){
		            ex.printStackTrace();
		         
		        }
			}
			return false;
			}
		if(type.equalsIgnoreCase("decline")){
			
			String updateDeliver = "Update po_orders set delivery_status = ?  where POId = ? ";
			String stat = "decline";
			
			try{
				
				int row3 = getJdbcTemplate().update(updateDeliver, new Object[] { 
						stat,poid
				});
				
				System.out.println("Yout request has been declined");
				
			}catch(Exception ex){
	            ex.printStackTrace();
			}
		}
		return false;
	}



}
