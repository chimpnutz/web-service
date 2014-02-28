package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.pc2mweb.model.MailModel;
import com.pc2mweb.beans.PurchaseOrderBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.controller.PurchaseOrderController;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.utility.function.Props;

public class PurchaseOrderDAO extends JdbcDaoSupport 
{
	private static final Logger logger = Logger.getLogger(PurchaseOrderDAO.class);
	
	public String discount_type;
	public BigDecimal discount = new BigDecimal(0);
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

		   int qty = 0;
		      
		   int totalqty = 0;
		   int totalqty1 =0;
		   BigDecimal total_dsct = new BigDecimal(0);
		   BigDecimal totalface = new BigDecimal(0);
		   BigDecimal totalface2 = new BigDecimal(0);
		   
		   
		   
		   if(role.equals("manager"))
		   {
			   
			   
			   logger.info("Inserting Purchase Order for Partnerid: "+pid);
			   
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
				            		   
				            		   
				            		   logger.info("Inserting LOP for Partner ID: "+pid);
				            	
				            		   int qty2 = Integer.parseInt(model.getQuantity());
				            		   int walletid = 0;
				            		   totalqty = totalqty + qty2;
				            		   			            		  
				            		   
				            		   this.getPartnerDiscount(session, model.getItem());
					            	   
					            	   this.getfaceValue(model.getItem());
					            	   
				            		 //  discount_amt = new BigDecimal(Integer.parseInt(model.getQuantity()));
				            		   
				            		   BigDecimal div = new BigDecimal (100);
				            		   
				            		   BigDecimal amt =  discount.divide(div);
				            		   
				            		   BigDecimal totalfaceqty2 = new BigDecimal(totalqty);
				            		   
					            	   totalface2 = facevalue.multiply(totalfaceqty2); 
					            	   
				            		   discount_amt = amt;
				            		   
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
														   
														   logger.info("Inserting Purchase Order Items on: poid: "+poid+", itemcode: "+model.getItem()+", quantity: "+Integer.parseInt(po.get(i).getQuantity()));
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																   poid,po.get(i).getItem(),Integer.parseInt(po.get(i).getQuantity()),po.get(i).getItem(),discount_amt,walletid
																});
															   			   
														   logger.info("inserting  Purchase Order Items on poid:"+poid+"has been inserted");
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else
					            				   {
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = ?, price =(select face_value_amount from purchase_items where item_name = ?), discount_amount = ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   logger.info("updating Purchase Order Items of poid:"+poid);
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
					    									   totalqty,po.get(i).getItem(),discount_amt,walletid,poid,po.get(i).getItem()
					    									});
					    								   
					    								   logger.info("updating Purchase Order Items of poid:"+poid+" has been updated");	   
						    						            
					    						    		
					    								   
					    							
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
														   
														   logger.info("Inserting Purchase Order Items on poid: "+poid+", item:"+model.getItem()+", quantity"+Integer.parseInt(po.get(i).getQuantity()));
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																	poid,po.get(i).getItem(),Integer.parseInt(po.get(i).getQuantity()),po.get(i).getItem(),discount.multiply(new BigDecimal(po.get(i).getQuantity())),walletid
																});
															   		
														
															   logger.info("Inserting Purchase Order Items on: poid: "+poid+" has been updated");
															  
															 
														
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else
					            				   {
					            					   
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = qty + ?, price = (select face_value_amount from purchase_items where item_name = ?), discount_amount = discount_amount + ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   logger.info("updating Purchase Order Items on poid: "+poid);
					    							   
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
					    									   po.get(i).getQuantity(),po.get(i).getItem(),discount.multiply(new BigDecimal(po.get(i).getQuantity())),walletid,poid,po.get(i).getItem()
															});
		
					    								 
					    								   logger.info("updating Purchase Order Items on poid: "+poid+" has been successfully updated");
					    						    		
					    						    		
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
								   insertPOItems.append("set order_amount = order_amount + (select sum(qty * price  - discount_amount ) from po_orders_items where POID = ? )");
								   insertPOItems.append(" where  poid = ? ");
								   
								   logger.info("updating po_orders on poid:"+poid);
								   
								   try{
									   
									   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
										   poid,poid
										});

									
									   logger.info("updating po_orders on poid:"+poid+" has been successfully updated");
								
								   }catch(DataAccessException ex){
							            ex.printStackTrace();
							
							        }
									
								}
				            
				            

				        }
					
					
					
				}
		
			   
			   
			   
			   
			   
			   
			   
			   
			   
		   }
		   
		   else
		   {
			   

			      if(null != po && po.size() > 0) 
			      {
			    	    int poRow = 0;
			            for (final PurchaseOrderModel model : po) 
			            {
			            	   	
			            	
							//if(row>0)
								
								
							//{
								
								
								if(model.getItem().equalsIgnoreCase("LOP"))
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
									   
										
						     		   int qty2 = Integer.parseInt(model.getQuantity());
				            		   int walletid = 0;
				            		   totalqty = totalqty + qty2;
				            		   			            		  
				            		   
				            		   this.getPartnerDiscount(session, model.getItem());
					            	   
					            	   this.getfaceValue(model.getItem());
					            	   
				            		   //discount_amt = new BigDecimal(Integer.parseInt(model.getQuantity()));
				            		   
				            		   BigDecimal div = new BigDecimal (100);
				            		   
				            		   BigDecimal amt =  discount.divide(div);
				            		   
				            		   BigDecimal totalfaceqty2 = new BigDecimal(totalqty);
				            		   
					            	   totalface2 = facevalue.multiply(totalfaceqty2); 
					            	   
				            		   discount_amt = amt;
				            		   
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
														   
														   logger.info("Inserting Purchase Order Items on: poid: "+poid+", itemcode: "+model.getItem()+", quantity: "+totalqty);
														   
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																   poid,model.getItem(),Integer.parseInt(model.getQuantity()),model.getItem(),discount_amt,walletid
																});
															   		
															  
															   logger.info("Inserting Purchase Order items on poid: "+poid+" has been successfully inserted");
															   System.out.println(poRow);
															   
															  
															 
														
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else{
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = ?, price =(select face_value_amount from purchase_items where item_name = ?), discount_amount = ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   logger.info("updating po_orders_items on poid:"+poid);
					    							   
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
					    									   totalqty,model.getItem(),discount_amt,walletid,poid,model.getItem()
					    									});
		
					    						            
					    								   logger.info("updating po_order_items on poid:"+poid+"has been successfully updated");
					    								   
					    										
					    							
					    							   }catch(DataAccessException ex){
					    						            ex.printStackTrace();
					    						
					    						        }
					            					   
					            					   
					            				   }
					            				   
					            				  
					            				   
					            			   }catch(DataAccessException ex){
								                ex.printStackTrace();
											
					            			    }
										}

										if(poRow>0){
	   										
	   										
	    						 			   StringBuilder insertPOItems = new StringBuilder();
	    									   
	    						 			   insertPOItems.append("update po_orders  ");
	    									   insertPOItems.append("set order_amount = order_amount + (select sum(qty * price  - discount_amount ) from po_orders_items where POID = ? )");
	    									   insertPOItems.append(" where  poid = ? ");
	    									   
	    									   logger.info("updating po_orders on poid:"+poid);
	    									   
	    									   try{
	    										   
	    										   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
	    											   poid,poid
	    											});

	    										   
	    										   logger.info("updating po_orders on poid:"+poid+" has been successfully updated");
	    									
	    									   }catch(DataAccessException ex){
	    								            ex.printStackTrace();
	    								
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
									   
									
				            		   this.getPartnerDiscount(session, model.getItem());		            	   
					            	   this.getfaceValue(model.getItem());
					            	   int walletid = 0;
				             		   qty = Integer.parseInt(model.getQuantity());
					            	  
				             		   totalqty1 = totalqty1 + qty;
				            		   			            	   
				             		   
					            	   
				             		   BigDecimal totalfaceqty = new BigDecimal(totalqty1);
				             		   
					            	   totalface = facevalue.multiply(totalfaceqty);   
					            	   
				            		   discount_amt = facevalue.subtract(discount);
				            		   
				            		   
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
														   
														   logger.info("Inserting Purchase Order Items on: poid: "+poid+", itemcode: "+model.getItem()+", quantity: "+model.getQuantity());
														  
														   try{
																   
															   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
																	poid,model.getItem(),model.getQuantity(),model.getItem(),discount,walletid
																});
															   		
															   logger.info("inserting purchase order items on poid: "+poid+" has been successfully inserted");
															   logger.info("discount:"+discount);
															 
														
														   }catch(DataAccessException ex){
													            ex.printStackTrace();
													
													        }
					            						
					            					}
					            				   else{
					            					   
					            					   StringBuilder updatePOItems = new StringBuilder();
					    							   
					    							   updatePOItems.append("update po_orders_items  ");
					    							   updatePOItems.append("set qty = qty + ?, price = (select face_value_amount from purchase_items where item_name = ?), discount_amount = discount_amount + ?, walletid=?");
					    							   updatePOItems.append(" where  poid = ? and itemcode = ? ");
					    							   
					    							   logger.info("updating po_orders_items on poid:"+poid);
					    							   
					    							   try{
					    								   
					    								   poRow = getJdbcTemplate().update(updatePOItems.toString(), new Object[] { 
					    									   model.getQuantity(),model.getItem(),discount,walletid,poid,model.getItem()
															});
		
					    						            
					    								   logger.info("updating po_orders_items on poid:"+poid+ " has been successfully updated");
					    								 
					    							
					    								   
					    							   }catch(DataAccessException ex){
					    						            ex.printStackTrace();
					    						
					    						        }
					            					   
					            					   
					            				   }
					            				   
					            				  
					            				   
					            			   }catch(DataAccessException ex){
									            ex.printStackTrace();
					            			  }	
									        }
				            		   
									
									
									
										if(poRow>0){
											
											
								 			   StringBuilder insertPOItems = new StringBuilder();
											   
								 			   insertPOItems.append("update po_orders  ");
	    									   insertPOItems.append("set order_amount = order_amount + (select sum(qty * price  - discount_amount ) from po_orders_items where POID = ? )");
	    									   insertPOItems.append(" where  poid = ? ");
											   
	    									   logger.info("updating po_orders on poid:"+poid);
	    									   
											   try{
												   
												   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
													   poid,poid
													});

												   logger.info("updating po_orders on poid:"+poid+" has been successfully updated");
														
											
											   }catch(DataAccessException ex){
										            ex.printStackTrace();
										
										        }
							            }
									
									
									
									
								}
				            				            	   
								
								
								
							//}
			            	
			        
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
		      

					StringBuilder STRSQL = new StringBuilder();
					String ptype = "";
					
					STRSQL.append("SELECT partnerid, paymenttype ");
					STRSQL.append("FROM wallets ");
					STRSQL.append("WHERE partnerid = (select partnerid from po_orders where poid = ?)");
					
					SqlRowSet rs2   = getJdbcTemplate().queryForRowSet(STRSQL.toString(),poid);
									
					if(rs2.next())
					{
						ptype = rs2.getString("paymenttype");
						
						if(ptype.equalsIgnoreCase("PREPAID"))
						{
							
							
					
							
						StringBuilder strSql = new StringBuilder();
						
						strSql.append("SELECT a.walletid FROM wallets a ");
						strSql.append("INNER JOIN partners_wallet b ON a.walletid = b.walletid ");
						strSql.append("WHERE a.partnerid = (select partnerid from po_orders where poid = ?) ");
						
						SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSql.toString(),poid);
						
						if(rs.next()) 
						{
							
							walletid = rs.getInt("walletid");
							
							StringBuilder updateWallet = new StringBuilder();
							
							updateWallet.append("UPDATE wallets set wallet = wallet + (select qty from po_orders_items where poid = ? and itemcode = ?), partnertxid = ? ");
							updateWallet.append("WHERE walletid = ? and partnerid = (select partnerid from po_orders where poid = ?)");
							
							try{
								
								poRow = getJdbcTemplate().update(updateWallet.toString(), new Object[] { 
									poid,"LOP",txid,walletid,poid
									});
								
								
								logger.info("Trying to LOP Purchase Order Number: "+poid+" ");
								
								if(poRow>0)
								{
									
									
									
									StringBuilder update = new StringBuilder();
										
									update.append("update po_orders set delivery_status = ? ");
									update.append("where POId = ? and PO_status = ?");
										
									try{
											   
										int poRowUpdate = getJdbcTemplate().update(update.toString(), new Object[] { 
													del_status,poid,po_status
												});
											   
											   
										if(poRowUpdate>0)
										{
											logger.info("Processing LOP Purchase Order Number: "+poid+" , Success");
											
											return true;
										}
										
									}
									 catch(DataAccessException ex){
									       ex.printStackTrace();
									
									 }
										
								}
								
								
							}catch(DataAccessException ex){
					            ex.printStackTrace();
							 }
							
							return false;
						   }
					}





						else if(ptype.equalsIgnoreCase("SETTLEMENT"))
						{
								
						ApplicationContext mailcontext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
							
						Props props = new Props();
								
						MailModel mm = (MailModel) mailcontext.getBean("mail");
								
						String [] recipient =  props.getRecipients().split(",");
								
						String sender = props.getSender();
						
						StringBuilder getPidSQL = new StringBuilder();
				
						
						getPidSQL.append("SELECT partnerid, paymenttype ");
						getPidSQL.append("FROM wallets ");
						getPidSQL.append("WHERE partnerid = (select partnerid from po_orders where poid = ?)");
						
						SqlRowSet getPidSQLRs   = getJdbcTemplate().queryForRowSet(getPidSQL.toString(),poid);
										
						if(getPidSQLRs.next())
						{
							
							
							StringBuilder getTotalLOPSql = new StringBuilder();
							
							
							getTotalLOPSql.append("select qty from po_orders_items where poid = ? and itemcode = ? ");

							
							SqlRowSet getTotalLOPSqlRS   = getJdbcTemplate().queryForRowSet(getTotalLOPSql.toString(),poid,"LOP");
							
							if(getTotalLOPSqlRS.next())
							{
								mm.sendMail(sender,
										recipient,
									    	"Settlement Payment Notification", 
									    	"Hi, \n\n Settlement by Partner ID: "+getPidSQLRs.getString("partnerid")+"  of PHP "+getTotalLOPSqlRS.getInt("qty")+" has been process with the PO Number of:  "+poid+" please process settlement of transactions. \n\nThanks, \n\n"+session.getAttribute("USER")+"");
										
								
								logger.info("Processing LOP Purchase Order Number: "+poid+" ,Sending Email to NOC and Payphil Admin");
											return true;
								
								
							}
	
						}
								
			
						}
						
					
					}			
		}	
		
		
		return false;
		
	}
	

	
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
	
	public boolean insertPaymentOrder(HttpSession session, int poid, HttpServletRequest request, PaymentOrderModel model){
				
		String type2 = "Manual";
		String type3 = "Bancnet";
		
		this.getPurchaseTotal(session, poid);
    	this.getPurchaseList(session, poid);
		this.getPaymentOrderItemsDetails(session, poid);
		
		PaymentOrderModel pom = new PaymentOrderModel();
		
		
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

						
						logger.info("Trying to insert payment for poid: "+poid);
						
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
							
							logger.info("Payment Insertion Success. Generated Payment id is: "+paymentid);
							
							if(row>0)
								
								
							{

								try{

									 StringBuilder insertManual = new StringBuilder();
									 
									 insertManual.append("INSERT INTO po_payment_manual_details(paymentId,branch, amount, attachments, remarks)");
									 insertManual.append("VALUES (?,?, (select order_amount from po_orders where poid = ?), ?, ?)");
									 
									 try{
										 int poRow = getJdbcTemplate().update(insertManual.toString(), new Object[]{
											 paymentid,pom.getBranch(),purchaseid,model.getAttachment(),pom.getRemarks()
										 });
										 
										 logger.info("Inserting details to payment manual table.");
										 
											if(poRow>0)
											
											
											{
												
												StringBuilder updateSql = new StringBuilder();
												
												updateSql.append("UPDATE po_orders SET ");
												updateSql.append("payment_status = ? ");
												updateSql.append("WHERE POId = ? and partnerid = ?");
												
												try{
													   
													   int updateSqlRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
														   	"for verification",purchaseid,session.getAttribute("PID")
														});
													   
													   if(updateSqlRow>0)
													   {
														   
														   logger.info("Inserting payment Success for POID: "+purchaseid);
														   
														   return true;
													   }
													   
													
												
												   }catch(DataAccessException ex){
											            ex.printStackTrace();
											
											        }
				
											}
 
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
				System.out.println("Error! Payment is already existing!");
				
				return false;
			}
					
		}
		

		 
		return false;
	}
	
	public boolean updatePaymentOrderForm(HttpSession session, int id, HttpServletRequest request){
		
		int poRow = 0;
		
		StringBuilder strSQL = new StringBuilder();
		
		strSQL.append("SELECT status FROM po_payment ");
		strSQL.append("WHERE POId = ?");
		
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),id);
		
		if(rs.next()){
			
			String status = rs.getString("status");
			
			if(status.equalsIgnoreCase("checking"))
			{
				
				StringBuilder update = new StringBuilder();
				
				update.append("UPDATE po_payment a INNER JOIN po_orders b ON a.POId = b.POId SET a.status = ?, b.delivery_status =?, b.PO_status = ? ");
				update.append("WHERE a.POId = ?");
				
				try{
					
					logger.info("updating status of po_payment and po_orders.");
					
					poRow = getJdbcTemplate().update(update.toString(), new Object[] {
						"cancelled","cancelled","cancelled",id
					});
					
					if(poRow>0){
						
						StringBuilder checkSQL = new StringBuilder();
						
						checkSQL.append("SELECT payment_status FROM po_orders ");
						checkSQL.append("WHERE POId = ?");
						
						SqlRowSet rs2 = getJdbcTemplate().queryForRowSet(checkSQL.toString(),id);
						
						if(rs2.next()){
							
							StringBuilder updatePO = new StringBuilder();
							
							updatePO.append("Update po_orders SET payment_status = ?");
							updatePO.append("WHERE POId = ?");
							
							logger.info("updating payment_status.");
							
							try{
								
								int updatePORow = getJdbcTemplate().update(updatePO.toString(), new Object[] {
									"cancelled",id
								});
								
								
								if(updatePORow>0)
								{
									
									
									logger.info("status of po_payment and po_orders has been updated by poid: 71");
									return true;
									
								}
								
								
								
							}catch(DataAccessException ex){
					            ex.printStackTrace();
							}
							
					
						}
						
					}
					

					
				}catch(DataAccessException ex){
		            ex.printStackTrace();
				}

			}
			else{
				logger.info("Payment status failed to update.");
				return false;
			}
		}
		
		return false;
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
				
				System.out.println(discount_type);
				System.out.println(discount+" waha");
			
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
		   else if(role.equals("superadmin"))
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
		   else if(role.equals("user"))
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
		   
		   else if(role.equalsIgnoreCase("superadmin"))
		   {
			   StringBuilder getpartnerid = new StringBuilder();
			   
			   getpartnerid.append("SELECT b.partnerid FROM agents_partners a ");
			   getpartnerid.append("INNER JOIN partners b ON a.partnerid = b.partner ");
			   getpartnerid.append("WHERE a.roleid = ? ");
			   
			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(getpartnerid.toString(),3);
			   
			   if(rs.next()){
				   
				   String partnerid = rs.getString("partnerid");
				   
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
				 
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),partnerid,poid);
					
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
			   
			   
		   
		   }
		   else if(role.equalsIgnoreCase("user"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
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

		
			return purchaseorderlist;
		   	  
	   }
	
	public List<PurchaseOrderModel>  getPOItemsDetails(HttpSession session, int poid)
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
			   strSQL.append("WHERE  b.partnerid = ?  ");
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
		   
		   else if (role.equalsIgnoreCase("superadmin"))
		   {
				   
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
			   

			return purchaseorderlist;
		   	  
	   }
	
	public List<PurchaseOrderModel>  getPOItemsDetails2(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   StringBuilder getpoitemid = new StringBuilder();
		   
		   getpoitemid.append("SELECT POItemId FROM po_orders_items where poid = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(getpoitemid.toString(),poid);
		   
		   if(rs.next()){
		   
			   int poitemid = rs.getInt("POItemId");
			   
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
					   strSQL.append("WHERE  b.partnerid = ? and c.POItemId = ? ");
					   strSQL.append("and c.poid = ?");
					   
					   purchaseorderlist.clear();
					 
						List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poitemid,poid);
						
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
					   
				  
				   
				   
			   
			   else if (role.equalsIgnoreCase("superadmin"))
			   {
					   
				   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
				   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
				   strSQL.append("FROM po_orders a  ");
				   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
				   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
				   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
				   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
				   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
				   strSQL.append("WHERE  b.parent_partnerid is null and c.POItemId = ? ");
				   strSQL.append("and c.poid = ?");
					   
					   
					   purchaseorderlist.clear();
					 
						List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poitemid,poid);
						
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
			   strSQL.append("WHERE  b.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? )  ");
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

		   else if (role.equalsIgnoreCase("superadmin"))
		   {
			   StringBuilder getpartnerid = new StringBuilder();
			   
			   getpartnerid.append("SELECT b.partnerid FROM agents_partners a ");
			   getpartnerid.append("INNER JOIN partners b ON a.partnerid = b.partner ");
			   getpartnerid.append("WHERE a.roleid = ? ");
			   
			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(getpartnerid.toString(),3);
			   
			   if(rs.next()){
				   
				   String partnerid = rs.getString("partnerid");
				   
				   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
				   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
				   strSQL.append("FROM po_orders a  ");
				   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
				   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
				   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
				   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
				   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
				   strSQL.append("WHERE  b.parent_partnerid =(SELECT partner FROM partners WHERE partnerid = ?) and c.poid = ? ");
				   
				   
				   purchaseorderlist.clear();
				 
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),partnerid,poid);
					
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
			   
			   
			   
			   
		   }
		   
		   else if(role.equalsIgnoreCase("user"))
		   {
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
			   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid ");
			   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid ");
			   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
			   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type  ");
			   strSQL.append("WHERE  b.partnerid = ?  ");
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
		   else if(role.equals("user"))
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
		   
		   if(role.equalsIgnoreCase("manager"))
		   {
			   
			   strSQL.append("SELECT order_amount from po_orders ");
			   strSQL.append("WHERE partnerid = ? ");
			   strSQL.append("and poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				BigDecimal total3 = new BigDecimal(0);
				
				PurchaseOrderModel PO = new PurchaseOrderModel();
				
				for (Map row : rows) {
									
		
		
						
						PO.setTotal_amount((String)(row.get("order_amount")+""));
					

					
						
					
				}
	
				purchaseorderlist.add(PO);		
				
		   }
		   else if (role.equalsIgnoreCase("superadmin"))
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
		   if(role.equalsIgnoreCase("user"))
		   {
			   
			   strSQL.append("SELECT order_amount from po_orders ");
			   strSQL.append("WHERE partnerid = ? ");
			   strSQL.append("and poid = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				BigDecimal total3 = new BigDecimal(0);
				
				PurchaseOrderModel PO = new PurchaseOrderModel();
				
				for (Map row : rows) {
									
		
		
						
						PO.setTotal_amount((String)(row.get("order_amount")+""));
					

					
						
					
				}
	
				purchaseorderlist.add(PO);		
				
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
					
					BigDecimal totls = new BigDecimal(0);
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
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, c.itemcode ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("INNER JOIN  po_orders_items c ON a.POId = c.POId ");
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
					PO.setItem((String)(row.get("itemcode")));
					
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
			   strSQL.append("WHERE b.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) and c.itemcode = ? ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"LOP");
				
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
			   	  
		   } else if(session.getAttribute("USERLEVEL").equals("superadmin"))			   
   		     {
			   
			   StringBuilder getpartnerid = new StringBuilder();
			   
			   getpartnerid.append("SELECT b.partnerid FROM agents_partners a ");
			   getpartnerid.append("INNER JOIN partners b ON a.partnerid = b.partner ");
			   getpartnerid.append("WHERE a.roleid = ? ");
			   
			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(getpartnerid.toString(),3);
			   
			   if(rs.next()){
				   
				   String partnerid = rs.getString("partnerid");
				   
				   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status, ");
				   strSQL.append("a.paid_amount, a.partnerid, a.cancelled_date, a.delivered_date, ");
				   strSQL.append("c.itemcode, c.qty, c.price, c.discount_amount, f.wallet_name, g.status, h.attachments "); 
				   strSQL.append("FROM po_orders a ");  
				   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid  ");
				   strSQL.append("INNER JOIN  po_orders_items c ON a.poid = c.poid  ");
				   strSQL.append("LEFT JOIN  wallets d ON c.walletid = d.walletid  ");
				   strSQL.append("LEFT JOIN  partners_wallet e ON d.walletid = e.walletid  ");
				   strSQL.append("LEFT JOIN  wallet_types f ON e.wallet_type = f.wallet_type "); 
				   strSQL.append("INNER JOIN po_payment g ON a.poid = g.poid ");
				   strSQL.append("INNER JOIN po_payment_manual_details h ON g.PaymentId = h.PaymentId ");
				   strSQL.append("WHERE b.parent_partnerid = (SELECT partner FROM partners  WHERE partnerid =? ) AND c.itemcode <> ? ");
				   
				   purchaseorderlist.clear();
				 
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),partnerid,"LOP");
					
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
						PO.setStatus((String)(row.get("status")));
						PO.setFile((String)(row.get("attachments")));
						
						purchaseorderlist.add(PO);			
					}
				   
			   }

			  
				
   		     	}
			
				return purchaseorderlist;
			   
			   
		   }
	
	public boolean cancelPayment(HttpSession session, int poid){
			
		StringBuilder strSQL = new StringBuilder();
		int PayId = 0;
		String Paytype = "";
		String paymentstat = "";
		String delivery_status = "";   
		String popaymentstat = "";   
		String stat = "cancelled";
		int poRow = 0;
		
		   strSQL.append("SELECT a.PaymentId, a.payment_type, a.status , b.delivery_status,b.payment_status   ");
		   strSQL.append("from po_payment a  INNER JOIN po_orders b ON a.POId = b.POId ");
		   strSQL.append("where a.POId = ?");		   
		   
		
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
			
			if(rs.next()) {
				
				PayId = rs.getInt("PaymentId");
				Paytype = rs.getString("payment_type");
				paymentstat = rs.getString("status");
				delivery_status = rs.getString("delivery_status");
				popaymentstat = rs.getString("payment_status");
				
				if(Paytype.equalsIgnoreCase("Manual"))
				{
					
					if(popaymentstat.equalsIgnoreCase("paid") && delivery_status.equalsIgnoreCase("for delivery"))
					{
							logger.info("Trying to cancel payment for poid: "+poid+" and paymentid: "+PayId);
						
							StringBuilder updateSql = new StringBuilder();
							
							updateSql.append("UPDATE po_payment SET ");
							updateSql.append("status = ? ");
							updateSql.append("WHERE PaymentId = ?");
							
							try{
								   
								   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
									   stat,PayId
									});
								   
								 
								   if(poRow>0)
								   {
									   
										StringBuilder cancelSQL = new StringBuilder();
										
										cancelSQL.append("UPDATE po_orders SET ");
										cancelSQL.append("payment_status = ?, PO_status = ?, delivery_status = ? ");
										cancelSQL.append("WHERE poid = ? and PO_status = ?");
										
										try{
											   
											   int cancelSQLRs = getJdbcTemplate().update(cancelSQL.toString(), new Object[] { 
												"cancelled","cancelled","cancelled",poid,"active"
												});
											   
											   if(cancelSQLRs>0){
												   logger.info("Cancelling of payment for poid: "+poid+" and paymentid: "+PayId+" success");
												   return true;
											   }
											   
										
										   }catch(DataAccessException ex){
									            ex.printStackTrace();
									
									        }
									   
									   
								   }
							
							   }catch(DataAccessException ex){
						            ex.printStackTrace();
						
						        }
					
						
					}
				}
				else if(!Paytype.equalsIgnoreCase("Manual")){
					
					if(status.equalsIgnoreCase("checking"))
					{
						
						if(!delivery_status.equalsIgnoreCase("delivered")){
							
							StringBuilder updateSql = new StringBuilder();
							
							updateSql.append("UPDATE po_payment SET ");
							updateSql.append("status = ? ");
							updateSql.append("WHERE PaymentId = ?");
							
							try{
								   
								   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
									   stat,PayId,
									});
								   
								   return true;
							
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
		int poRow = 0;
		String pid = "";
		
		
		   strSQL.append("SELECT a.PaymentId, a.total_amount, a.payment_type, a.status, b.delivery_status, b.partnerid from po_payment a  ");
		   strSQL.append("INNER JOIN po_orders b ON a.POId = b.POId ");
		   strSQL.append("where a.POId = ?");		   
		   
		   logger.info("Trying to confirm payment for poid: "+poid);
		   
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),poid);
			
			if(rs.next()) {
				
				PayId = rs.getInt("PaymentId");
				Paytype = rs.getString("payment_type");
				status = rs.getString("status");
				delivery_status = rs.getString("delivery_status");
				total_amount = rs.getBigDecimal("total_amount");
				pid = rs.getString("partnerid");
				

				if(Paytype.equalsIgnoreCase("Manual"))
				{
					logger.info(status);
					if(status.equalsIgnoreCase("checking"))
					{
						
						StringBuilder updateSql = new StringBuilder();
						
						updateSql.append("UPDATE po_payment SET ");
						updateSql.append("status = ? ");
						updateSql.append("WHERE PaymentId = ?");
						
						logger.info("updating po_payment of poid:"+poid);
						
						try{
							   
							   poRow = getJdbcTemplate().update(updateSql.toString(), new Object[] { 
								   "confirmed",PayId,
								});
							   logger.info("updating po_payment of poid:"+poid+" has successfully updated");
							   
							   if(poRow>0)
							   {
								   
				
									StringBuilder updateSql1 = new StringBuilder();
									
									updateSql1.append("UPDATE po_orders SET ");
									updateSql1.append("payment_status = ?, delivery_status = ? ");
									updateSql1.append("WHERE poid = ? AND partnerid = ?");
									
									logger.info("updating po_orders of poid:"+poid);
									
									try{
										   
										   int PaymentStatRow = getJdbcTemplate().update(updateSql1.toString(), new Object[] { 
											   "paid","for delivery", poid, pid										
											   });
										   
										   
										   if(PaymentStatRow>0){
											   
											   
											   logger.info("Payment confirmed for poid: "+poid +", status updated to paid, ");
											   return true;
										   }
									
										   
										   
										   
									   }catch(DataAccessException ex){
								            ex.printStackTrace();
								
								        }
								   
								   
								   
								   
							   }
							   

							   
						
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
		
		if(type.equalsIgnoreCase("approve"))
		{	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			String txid = this.getTransid();
			String status = "delivered";			
			
			obj.transactionID = txid;
			
			int wallet_type = 0;
			StringBuilder getWalletid = new StringBuilder();
			
			String item = "";
			
			getWalletid.append("SELECT c.wallet_type FROM wallets a, partners_wallet b, wallet_types c ");
			getWalletid.append("WHERE a.partnerid = ? AND a.walletid = b.walletid ");
			getWalletid.append("AND b.wallet_type = c.wallet_type ");
			
			SqlRowSet rs1   = getJdbcTemplate().queryForRowSet(getWalletid.toString(),session.getAttribute("PID"));
			
			if(rs1.next()) {
						
				wallet_type = rs1.getInt("wallet_type");
				
				StringBuilder strSql = new StringBuilder();
				
				strSql.append("SELECT a.order_amount,a.partnerid, b.partnerid, e.wallet_type, f.itemcode FROM po_orders a ");
				strSql.append("INNER JOIN partners b ON a.partnerid = b.partnerid ");
				strSql.append("INNER JOIN wallets c ON b.partnerid = c.partnerid ");
				strSql.append("INNER JOIN partners_wallet d ON c.walletid = d.walletid ");
				strSql.append("INNER JOIN wallet_types e ON d.wallet_type = e.wallet_type ");
				strSql.append("INNER JOIN po_orders_items f ON a.poid = f.poid ");
				strSql.append("WHERE a.poid = ? AND b.parent_partnerid = (SELECT partner FROM partners  WHERE partnerid = ? )");
				
				SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSql.toString(),poid,session.getAttribute("PID"));
				
				if(rs.next()) {
					
				item = rs.getString("itemcode");
				
				pid = rs.getString("partnerid");
				
				total_amount = rs.getBigDecimal("order_amount");
				
				String deductSubdealerWallet = "UPDATE wallets a INNER JOIN partners_wallet b ON a.walletid = b.walletid INNER JOIN wallet_types c ON b.wallet_type = c.wallet_type SET a.wallet = a.wallet -  (SELECT qty FROM po_orders_items WHERE poid = ? AND itemcode = ?),a.partnertxid = ?  WHERE  a.partnerid = ?  AND (a.wallet - (SELECT qty FROM po_orders_items WHERE poid = ? AND itemcode = ?)) >= 0 AND a.isdefault = ?  AND c.wallet_type = ?";
				
				String addRetailerWallet = "UPDATE wallets a INNER JOIN partners_wallet b ON a.walletid = b.walletid INNER JOIN wallet_types c ON b.wallet_type = c.wallet_type SET a.wallet = a.wallet +  (SELECT qty FROM po_orders_items WHERE poid = ? AND itemcode = ?),a.partnertxid = ?  WHERE  a.partnerid = ?  AND (a.wallet + (SELECT qty FROM po_orders_items WHERE poid = ? AND itemcode = ?)) >= 0 AND a.isdefault = ?  AND c.wallet_type = ?";
				
				String updateDeliver = "Update po_orders set payment_status = ?, delivery_status = ? where POId = ? ";
				
				String updatePOItems = "Update po_orders_items set delivery_status = ? , delivered_date = ? WHERE POId = ? ";
				
					if(item.equalsIgnoreCase("LOP")){
						
						try{
							logger.info("updating wallets of pid:"+session.getAttribute("PID"));
							
							int row = getJdbcTemplate().update(deductSubdealerWallet, new Object[] { 
									poid,"LOP",txid,session.getAttribute("PID"),poid,"LOP",1,wallet_type
							});
							
							logger.info("wallet of pid: "+session.getAttribute("PID")+" has been updated");
							
							int row2 = 0;
							
							if ( row > 0)
							{
								logger.info("updating wallets of pid:"+pid);
								
							    row2 = getJdbcTemplate().update(addRetailerWallet, new Object[] { 
							    		poid,"LOP",txid,pid,poid,"LOP",1,wallet_type
							});
							    logger.info("wallet of pid: "+pid+" has been updated");
							}
							else{
								logger.info("failed to update wallet of pid: "+pid);
								return false;
							}
							
							if(row>0 && row2>0)
							{
								logger.info("updating po_orders status of poid:"+poid);
						
								try{
									
									int row3 = getJdbcTemplate().update(updateDeliver, new Object[] { 
											"paid",status,poid
									});
									
									logger.info("po_orders status of poid: "+poid+" has been updated");
									if(row3>0){
										
										logger.info("updating po_orders_itemes status of poid:"+poid);
										
										int row4 = getJdbcTemplate().update(updatePOItems, new Object[] {
												"delivered",dateFormat.format(date),poid
										});
										logger.info("po_orders status_items of poid: "+poid+" has been updated");
										return true;
									}
									
									else{
										logger.info("po_orders status_items of poid: "+poid+" failed to update");
										return false;
									}
									
									
								}catch(Exception ex){
						            ex.printStackTrace();
								}
								
							}
							else{
								logger.info("po_orders status of poid: "+poid+" failed to update");
								return false;
								
							}
							
							}catch(Exception ex){
					            ex.printStackTrace();
					         
					        }
						
					}
					else{
						
						logger.info("updating po_orders status of poid:"+poid);
						
						try{
							
							int row3 = getJdbcTemplate().update(updateDeliver, new Object[] { 
									"paid",status,poid
							});
							
							logger.info("po_orders status of poid: "+poid+" has been updated");
							if(row3>0){
								
								logger.info("updating po_orders_items status of poid:"+poid);
								
								int row4 = getJdbcTemplate().update(updatePOItems, new Object[] {
										"delivered",dateFormat.format(date),poid
								});
								logger.info("po_orders status_items of poid: "+poid+" has been updated");
								return true;
							}
							
							else{
								logger.info("po_orders status_items of poid: "+poid+" failed to update");
								return false;
							}
							
							
						}catch(Exception ex){
				            ex.printStackTrace();
						}
					}
				
				}
				else{
					logger.info("error on approving po_orders");
					return false;
				}
			
			}
			
			
			
		}
		else if(type.equalsIgnoreCase("decline"))
		{
			
			String updateDeliver = "Update po_orders set delivery_status = ?, payment_status =? , PO_status = ?  where POId = ? ";
			
			String cancelDeliver = "UPDATE po_orders_items SET delivery_status = ? WHERE POId = ? ";
			
			try{
				
				logger.info("updating po_orders status of poid: "+poid);
				
				int row3 = getJdbcTemplate().update(updateDeliver, new Object[] { 
						"cancelled","cancelled","cancelled",poid
				});
				logger.info("po_orders of poid: "+poid+" has been cancelled");
				
				
				if(row3>0){
					
					logger.info("updating po_orders_items status of poid: "+poid);
					
					int row4 = getJdbcTemplate().update(cancelDeliver, new Object[] { 
							"cancelled",poid
					});
					
					
					if(row4>0){
						logger.info("updating po_orders_items status of poid: "+poid+" has been cancelled");
						return true;
					}
					
					else{
						logger.info("updating po_orders_items status of poid: "+poid+" has not been cancelled");
						return false;
					}
					
					
				}
				else{
					logger.info("po_orders status of poid: "+poid+" has not been cancelled");
					return false;
				}
				
				
				
			}catch(Exception ex){
	            ex.printStackTrace();
			}
		}
		return false;
	}
	

}
