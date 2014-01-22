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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.PurchaseOrderBean;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TopupModel;

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
				            		   
				            		   totalqty = totalqty + qty2;
				            		   			            		  
				            		   
				            		   this.getPartnerDiscount(session, model.getItem());
					            	   this.getWalletid(session, model.getWallet());
					            	   this.getfaceValue(model.getItem());
					            	   
					            	  
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
				            	 
				            		   

				            	   
				            	   
				            	   
				            	   if(model.getItem().equalsIgnoreCase("GHP 300"))
				            	   {
				            		   

				             		   qty = Integer.parseInt(model.getQuantity());
					            	  
				             		   totalqty1 = totalqty1 + qty;
				            		   
				            		   	
				             		  this.getPartnerDiscount(session, model.getItem());
					            	   this.getWalletid(session, model.getWallet());
					            	   this.getfaceValue(model.getItem());
					            	   
					            	   
					            	   
				             		   BigDecimal totalfaceqty = new BigDecimal(totalqty1);
				             		   
					            	   totalface = facevalue.multiply(totalfaceqty);   
				            		   discount_amt = facevalue.subtract(discount);
				            		   
				            		   BigDecimal totaldcamount = discount_amt.multiply(new BigDecimal(totalqty1));
			            			   
									   
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
															poid,model.getItem(),totalqty1,model.getItem(),discount_amt,walletid
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
								   
				         i++;
				            }
				            
				            
				            
				            
				            
				    		if(poRow>0){
								
								
				 			   StringBuilder insertPOItems = new StringBuilder();
							   
							   insertPOItems.append("update po_orders  ");
							   insertPOItems.append("set order_amount = (select sum(discount_amount) from po_orders_items where POID = ?)");
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
		
			   
			   
			   
			   
			   
			   
			   
			   
			   
		   }else
		   {
			   
			      if(null != po && po.size() > 0) 
			      {

			            for (final PurchaseOrderModel model : po) 
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
			            	
			            	
			            	
							if(row>0)
								
								
							{
								int poRow = 0;
								
				            	   if(model.getItem().equalsIgnoreCase("LOP"))
				            	   {
				            		   	
					            	   this.getPartnerDiscount(session, model.getItem());
					            	   this.getWalletid(session, model.getWallet());
					            	   
				            		   discount_amt = new BigDecimal(Integer.parseInt(model.getQuantity()));
				            		   BigDecimal div = new BigDecimal (100);
				            		   BigDecimal amt =  discount.divide(div);
				            		   
				            		   System.out.println(discount);

				            		   discount_amt = discount_amt.subtract(amt);
				            		   
									   StringBuilder insertPOItems = new StringBuilder();
									   
									   insertPOItems.append("INSERT INTO po_orders_items  ");
									   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
									   insertPOItems.append(" VALUES (?,?,?,?,?,?) ");
									   
									   try{
										   
										   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
												poid,model.getItem(),model.getQuantity(),model.getQuantity(),discount_amt,walletid,model.getItem()
											});

											
								    		if(poRow>0){
												
												
									 			   StringBuilder insertPO = new StringBuilder();
												   
									 			   insertPO.append("update po_orders  ");
									 			   insertPO.append("set order_amount = (select sum(discount_amount) from po_orders_items where POID = ?)");
									 			   insertPO.append(" where  poid = ? ");
												   
												   try{
													   
													   poRow = getJdbcTemplate().update(insertPO.toString(), new Object[] { 
														   poid,poid
														});

														
												
															
												
												   }catch(DataAccessException ex){
											            ex.printStackTrace();
											
											        }
									    			
									    			
									    			
									    			
												
													
												}
									
												
									
									   }catch(DataAccessException ex){
								            ex.printStackTrace();
								
								        }
				            		   
				            	   }
				            	   
				            	   
				            	   
				            	   else
				            	   {
				            		   
				            		   	
					            	   this.getPartnerDiscount(session, model.getItem());
					            	   this.getWalletid(session, model.getWallet());
					            	   this.getfaceValue(model.getItem());
				            		   
				             		   qty = Integer.parseInt(model.getQuantity());
				          
				            		   discount_amt1 = facevalue.subtract(discount);
				            		   
				            		   BigDecimal totaldcamount = discount_amt1.multiply(new BigDecimal(qty));
				            				   
									   StringBuilder insertPOItems = new StringBuilder();
									   
									   insertPOItems.append("INSERT INTO po_orders_items  ");
									   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
									   insertPOItems.append(" VALUES (?,?,?,?,?,?) ");
									   
									   try{
										   
										   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
												poid,model.getItem(),model.getQuantity(),facevalue,totaldcamount,walletid
											});

											
								    		if(poRow>0)
								    		{
												
												
									 			   StringBuilder insertPO = new StringBuilder();
												   
									 			   insertPO.append("update po_orders  ");
									 			   insertPO.append("set order_amount = (select sum(discount_amount) from po_orders_items where POID = ?)");
									 			   insertPO.append(" where  poid = ? ");
												   
												   try{
													   
													   poRow = getJdbcTemplate().update(insertPO.toString(), new Object[] { 
														   poid,poid
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
		            
			            return true;
			            
			        }
			   
			   
			   
			   
			   
		   }
		
		

		return false;
	}
	
	public boolean insertProcessOrder(HttpSession session, int poid ){
		
		final String pid = session.getAttribute("PID").toString();
		String role = session.getAttribute("USERLEVEL").toString();
			
		String status = "processing";
		String po_status = "active";
		
		if(role.equals("superadmin")){
			
			
		      int poRow = 0;
		      
		    	  
					StringBuilder update = new StringBuilder();
					
					update.append("update po_orders set delivery_status = ? ");
					update.append("where POId = ? and PO_status = ?");
					
					try{
						   
						   poRow = getJdbcTemplate().update(update.toString(), new Object[] { 
							   status,poid,po_status
							});
						   
						   
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				
				        }

}
			
			if(role.equals("manager")){
				
								
					      int poRow = 0;
					      
					    	  
								StringBuilder update = new StringBuilder();
								
								update.append("update po_orders set delivery_status = ? ");
								update.append("where POId = ? and PO_status = ?");
								
								try{
									   
									   poRow = getJdbcTemplate().update(update.toString(), new Object[] { 
										   status,poid,po_status
										});
									   
									   
								
								   }catch(DataAccessException ex){
							            ex.printStackTrace();
							
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
	
	public boolean insertPaymentOrder(HttpSession session, int poid, HttpServletRequest request){
				
		String type = request.getParameter("type");
		String amt = request.getParameter("amount");
		String bank = request.getParameter("bank");
		String branch = request.getParameter("branch");
		String txt = request.getParameter("text");
		String type2 = "Manual";
		String type3 = "Bancnet";
		
		this.getPurchaseTotal(session, poid);
    	this.getPurchaseList(session, poid);
		this.getPaymentOrderItemsDetails(session, poid);
		
		PaymentOrderModel pom = new PaymentOrderModel();
		
		if(type.equalsIgnoreCase("2")){
			
			StringBuilder chkSql = new StringBuilder();
			
			chkSql.append("SELECT status FROM po_payment ");
			chkSql.append("WHERE POId = ?");
			
			SqlRowSet rs   = getJdbcTemplate().queryForRowSet(chkSql.toString(),poid);
			
			if(!rs.next()) {
				
						type = type2;
						
						int poRow = 0;
						
						
						 StringBuilder insertPayment = new StringBuilder();
						 
						 insertPayment.append("INSERT INTO po_payment (Payment_TXID, payment_type, total_amount, total_order, total_fee, POId)");
						 insertPayment.append("VALUES (?,?,?,?,?,?)");
						 
						 try{
							 poRow = getJdbcTemplate().update(insertPayment.toString(), new Object[] {
								 	poid,type,total_amount,total_order,null,poid
							 });
							 
							 StringBuilder insertManual = new StringBuilder();
							 
							 insertManual.append("INSERT INTO po_payment_manual_details(branch, amount, attachments, remarks)");
							 insertManual.append("VALUES (?, ?, ?, ?)");
							 
							 try{
								 poRow = getJdbcTemplate().update(insertManual.toString(), new Object[]{
									 branch,total_amount,null,txt
								 });
								 
								 
							 }catch(DataAccessException ex){
						            ex.printStackTrace();
							 }
							 
						 }catch(DataAccessException ex){
					            ex.printStackTrace();
						 }
			
			}
			else{
				System.out.println("Error! Payment is already exist!");
				
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
			
			if(rs.next()) {
				
				discount_type = rs.getString("discount_type");
				discount = rs.getBigDecimal("discount");
			
			}else{
				
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
			strSql.append("a.datecreated, a.payment_date, a.returned_amount, a.returned_date, b.branch, b.amount, b.remarks, c.payment_status, c.po_status ");
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
				pom.setReturned_amount((String)(row.get("returned_amount")));
				pom.setStatus((String)(row.get("status")));
				pom.setTotal_amount((String)(row.get("total_amount")+""));
				pom.setTotal_fee((String)(row.get("total_fee")));
				pom.setTotal_order((String)(row.get("total_order")+""));
				pom.setType((String)(row.get("type")));
				pom.setPayment_status((String)(row.get("payment_status")));
				pom.setPo_status((String)(row.get("po_status")));
				pom.setPoid((int)(row.get("POId")));
				
				 status = pom.getStatus();
				
				paymentorderList.add(pom);	
		}
			
	}
		   if(role.equals("manager"))
		   {
		
			strSql.append("SELECT a.PaymentId, a.payment_type, a.total_amount, a.total_order, a.total_fee, a.POId, a.status,");
			strSql.append("a.datecreated, a.payment_date, a.returned_amount, a.returned_date, b.branch, b.amount, b.remarks, c.payment_status, c.po_status ");
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
				pom.setReturned_amount((String)(row.get("returned_amount")));
				pom.setStatus((String)(row.get("status")));
				pom.setTotal_amount((String)(row.get("total_amount")+""));
				pom.setTotal_fee((String)(row.get("total_fee")));
				pom.setTotal_order((String)(row.get("total_order")+""));
				pom.setType((String)(row.get("type")));
				pom.setPayment_status((String)(row.get("payment_status")));
				pom.setPo_status((String)(row.get("po_status")));
				pom.setPoid((int)(row.get("POId")));
				
				 status = pom.getStatus();
				
				paymentorderList.add(pom);	
		}
			
	}  
		
		return paymentorderList;
		
	}

	public List<PurchaseOrderModel>  getPurchaseOrderItemsDetails(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equalsIgnoreCase("manager"))
		   {
			   System.out.println(session.getAttribute("USERLEVEL"));
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
			   strSQL.append("WHERE ");
			   strSQL.append(" c.poid = ?");
			   
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
				
				
//			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
//			   strSQL.append("FROM po_orders a  ");
//			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
//			   strSQL.append("WHERE b.partnerid = ? OR  b.parent_partnerid = (select partner from partners where partnerid = ?)");
//			   
//			   purchaseorderlist.clear();
//			 
//				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"Requested",poid);
//				
//				for (Map row : rows) {
//					
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
//					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
//					
//					
//					PurchaseOrderModel PO = new PurchaseOrderModel();
//
//					
//					PO.setPoid((int)(row.get("poid")));
//					PO.setPodate((String)(dateAsString));
//					PO.setOrder_amount((String)(row.get("order_amount")+""));
//					PO.setPayment_status((String)(row.get("payment_status")));
//					PO.setDelivery_status((String)(row.get("delivery_status")));
//					PO.setPo_status((String)(row.get("po_status")));
//					
//					purchaseorderlist.add(PO);			
//				}
//			   
			   
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
					
					if(PO.getItem().equals("GHP 300")||PO.getItem().equals("LOP"))
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
					
					if(PO.getItem().equals("GHP 300")||PO.getItem().equals("LOP"))
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
				
				
			   
//			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
//			   strSQL.append("FROM po_orders a  ");
//			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
//			   strSQL.append("WHERE b.partnerid = ? OR  b.parent_partnerid = (select partner from partners where partnerid = ?)");
//			   
//			   purchaseorderlist.clear();
//			 
//				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"Requested",poid);
//				
//				for (Map row : rows) {
//					
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
//					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
//					
//					
//					PurchaseOrderModel PO = new PurchaseOrderModel();
//
//					
//					PO.setPoid((int)(row.get("poid")));
//					PO.setPodate((String)(dateAsString));
//					PO.setOrder_amount((String)(row.get("order_amount")+""));
//					PO.setPayment_status((String)(row.get("payment_status")));
//					PO.setDelivery_status((String)(row.get("delivery_status")));
//					PO.setPo_status((String)(row.get("po_status")));
//					
//					purchaseorderlist.add(PO);			
//				}
//			   
			   
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
			   strSQL.append("WHERE b.partnerid = ? OR  b.parent_partnerid = (select partner from partners where partnerid = ?)");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),session.getAttribute("PID"));
				
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
			   StringBuilder strSQL = new StringBuilder()	;
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE b.parent_partnerid is null");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
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
				
		   }
			
				return purchaseorderlist;
			   
			   
		   }
	
	
	
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRetailersList(HttpSession session)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   if(session.getAttribute("USERLEVEL").equals("manager"))
		   
		   
		   {
			   

			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status,a.partnerid ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE  b.parent_partnerid = (select partner from partners where partnerid = ?)");
			   
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
	
	public List<PurchaseOrderBean> fillItemCodeList(){
		
	
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   ArrayList<PurchaseOrderBean> purchaseorderlist = new ArrayList<PurchaseOrderBean>();
		   
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

}
