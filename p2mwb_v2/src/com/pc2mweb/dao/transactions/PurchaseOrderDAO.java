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
	
	public boolean insertPurchaseOrder(HttpSession session,List<PurchaseOrderModel> po ){
		
		
		   String role = session.getAttribute("USERLEVEL").toString();
		   final String pid = session.getAttribute("PID").toString();
		   
		   BigDecimal discount_amt = new BigDecimal(0);
		   int qty = 0;
		      
		   
		   if(role.equals("manager"))
		   {
			   
			   //final PurchaseOrderModel PO = purchaseForm;
				
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
				    		
				            for (PurchaseOrderModel model : po) 
				            {
				            	
				         
				            	   
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
									   insertPOItems.append(" VALUES (?,?,?,(select face_value_amount from purchase_items where item_name = ?),?,?) ");
									   
									   try{
										 
										   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
												poid,model.getItem(),model.getQuantity(),model.getItem(),discount_amt,walletid
											});

											
									   
									
										  
									
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
					            	  
					            		   
				            		   discount_amt = facevalue.subtract(discount);
				            		   
				            		   BigDecimal totaldcamount = discount_amt.multiply(new BigDecimal(qty));
				            				   
									   StringBuilder insertPOItems = new StringBuilder();
									   
									   insertPOItems.append("INSERT INTO po_orders_items  ");
									   insertPOItems.append("(POId,itemcode,qty,price,discount_amount,walletid) ");
									   insertPOItems.append(" VALUES (?,?,?,?,?,?) ");
									   
									   try{
										   
										   poRow = getJdbcTemplate().update(insertPOItems.toString(), new Object[] { 
												poid,model.getItem(),model.getQuantity(),facevalue,totaldcamount,walletid
											});

											
									
												
									
									   }catch(DataAccessException ex){
								            ex.printStackTrace();
								
								        }
				            		   
				            		   
				            		   
				            	   }
				            	
		
								   
								   
				         
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
												poid,model.getItem(),model.getQuantity(),model.getQuantity(),discount_amt,walletid
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
				          
				            		   discount_amt = facevalue.subtract(discount);
				            		   
				            		   BigDecimal totaldcamount = discount_amt.multiply(new BigDecimal(qty));
				            				   
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

	public List<PurchaseOrderModel>  getPurchaseOrderItemsDetails(HttpSession session, int poid)
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
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("po_date"));
					String cancelAsString = "";
					String deliverAsString = "";
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
		   else if (role.equals("superadmin")){
			   
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE b.partnerid = ? OR  b.parent_partnerid = (select partner from partners where partnerid = ?)");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"Requested",poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();

					
					PO.setPoid((int)(row.get("poid")));
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
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRequestListHistory(HttpSession session)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   if(session.getAttribute("USERLEVEL").equals("manager"))
		   
		   
		   {
			   

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
			   strSQL.append("SELECT a.poid, a.po_date, a.order_amount, a.payment_status,a.delivery_status, a.po_status,a.partnerid  ");
			   strSQL.append("FROM po_orders a  ");
			   strSQL.append("INNER JOIN  partners b ON a.partnerid = b.partnerid ");
			   strSQL.append("WHERE  b.parent_partnerid is null");
			   
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
