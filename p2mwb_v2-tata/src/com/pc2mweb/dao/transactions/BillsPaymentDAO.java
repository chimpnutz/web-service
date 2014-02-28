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
import com.pc2mweb.model.BillsPaymentHistoryModel;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class BillsPaymentDAO extends JdbcDaoSupport {


	public Map fillBox(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT * from request_types where transaction_type = ?");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"billspayment");
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("request_type")), (String)row.get("request_type"));
		
			}
			
			return prefix;
			
	}
	
	public List<BillerBean> fillBiller(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<BillerBean> billerList= new ArrayList<BillerBean>();
		   
		   strSQL.append("SELECT * from request_types where transaction_type = ?");
			
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"billspayment");
			
			for (Map row : rows) {
				
				BillerBean biller = new BillerBean();
				
				biller.setBiller((String)(row.get("request_name").toString().toUpperCase()));
				biller.setBillercode((String)(row.get("request_type")));
				
				billerList.add(biller);		
		
			}
			
			return billerList;
			
	}
	
	public List<BillerFieldBean> getBillerField(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<BillerFieldBean> billerfieldList= new ArrayList<BillerFieldBean>();
		   
		   System.out.println(biller);
		  // strSQL.append("select description,parameter_name from request_parameters where request_type = ?");
		   strSQL.append("SELECT a.description,a.parameter_name,a.parameter_type,a.format,a.label,b.biller_fields_format,b.payer_fields_format ");
		   strSQL.append("FROM request_parameters a, biller_fields_format b  ");
		   strSQL.append("WHERE a.request_type = b.biller_code AND b.biller_code = ? order by a.position");
			
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),biller);
			
			
			for (Map row : rows) {
				
				BillerFieldBean billerfield = new BillerFieldBean();
				
				billerfield.setParameter_name((String)(row.get("parameter_name")));
				billerfield.setDescription((String)(row.get("description")));
				billerfield.setBillerfield((String)(row.get("biller_fields_format")));
				billerfield.setPayfields((String)(row.get("payer_fields_format")));
				billerfield.setParameter_type((String)(row.get("parameter_type")));
				billerfield.setFormat((String)(row.get("format")));
				
				String label = (String)(row.get("label"));
				
				if(label == null){
					billerfield.setLabel("");
				}else{
					billerfield.setLabel((String)(row.get("label")));
				}
		
						
				billerfieldList.add(billerfield);		
		
			}
			
			return billerfieldList;
			
	}
	
	public ArrayList<String> getUploadAddressFields(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<String> billerfieldList= new ArrayList<String> ();
		   

		   strSQL.append("SELECT parameter_name from request_parameters where isUploadAddressField = ? and request_type = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),1,biller);
		   
		   while(rs.next())
		   {
			   billerfieldList.add(rs.getString("parameter_name"));
			   		   
		   }

			return billerfieldList;
			
	}
	
	
	
	public ArrayList<String> getUploadAddressFieldsPattern(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<String> billerfieldList= new ArrayList<String> ();
		   

		   strSQL.append("SELECT upload_mapping_address_field from biller_fields_format where biller_code = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),biller);
		   
		   if(rs.next())
		   {
			   if(rs.getString("upload_mapping_address_field") != null){
				  
			
				   billerfieldList.add(rs.getString("upload_mapping_address_field"));
			   }
			   

			   		   
		   }

			return billerfieldList;
			
	}
	
	public ArrayList<String> getUploadBranchFields(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<String> billerfieldList= new ArrayList<String> ();
		   

		   strSQL.append("SELECT parameter_name from request_parameters where isUploadBranchCodeField = ? and request_type = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),1,biller);
		   
		   while(rs.next())
		   {
			   billerfieldList.add(rs.getString("parameter_name"));
			   
			   
		   }

			return billerfieldList;
			
	}
	
	public ArrayList<String> getUploadBranchFieldsPattern(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<String> billerfieldList= new ArrayList<String> ();
		   

		   strSQL.append("SELECT upload_mapping_branchode_field from biller_fields_format where biller_code = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),biller);
		   
		   if(rs.next())
		   {
			  
			   if(rs.getString("upload_mapping_branchode_field") != null){
					  
					
				   billerfieldList.add(rs.getString("upload_mapping_branchode_field"));
			   }
			   			   		   
		   }

			return billerfieldList;
			
	}
	
	public ArrayList<String> getBillerFieldRequired(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		  				
		   ArrayList<String> billerfieldList= new ArrayList<String> ();
		   

		   strSQL.append("SELECT parameter_name from request_parameters where isBillerFieldRequired = ?");
		   
		   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),1);
		   
		   while(rs.next())
		   {
			   billerfieldList.add(rs.getString("parameter_name"));
			   
			   
		   }

			return billerfieldList;
			
	}
	
	public Long insertTransaction(BillsPaymentModel r,HttpSession session) {

		  
		
		   Long id = (long) 0;
	
		   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
		   
		   final int walletid = (int) session.getAttribute("walletid");
		   
		   final BillsPaymentModel bill = r;
		
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
		
			int txid = keyHolder.getKey().intValue();
			
			System.out.println("txid "+txid);
				

				
				if(row>0)
				
				
				{
					

					
					   StringBuilder insertMobileTx = new StringBuilder();
					   
					   insertMobileTx.append("INSERT INTO bills_payment_transaction  ");
					   insertMobileTx.append("(transactionid,company_id,user_id,account_number,cash_amount,bill_date,expiry_date,name,address,account_type,bill_amount,date_paid,time_paid,check_amount,biller_post_tran_field,payfield,branch_code) ");
					   insertMobileTx.append(" VALUES (?,(select biller from biller_fields_format where biller_code = ?),?,?,?,?,?,?,?,?,?,CURDATE(),CURTIME(),?,?,?,?)");
					   
					   
					   try{
						   
//							int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
//								txid,bill.getCompanyid(),session.getAttribute("USER"),bill.getAccountnumber(),Float.parseFloat(bill.getCash_amount()),bill.getBill_date(),bill.getExpiry_date(),bill.getName(),bill.getAddress(),bill.getAccount_type(),Float.parseFloat(bill.getBill_amount()),0.0
//								,bill.getBiller_post_tran_field(),bill.getPayfield(),bill.getBranchcode()
//							
//							});
						   
							int mobileRow = getJdbcTemplate().update(insertMobileTx.toString(), new Object[] { 
								txid,bill.getCompanyid(),"pcswsusr",bill.getAccountnumber(),Float.parseFloat(bill.getCash_amount()),bill.getBill_date(),bill.getExpiry_date(),bill.getName(),bill.getAddress(),bill.getAccount_type(),Float.parseFloat(bill.getBill_amount()),0.0
								,bill.getBiller_post_tran_field(),bill.getPayfield(),bill.getBranchcode()
							
							});

							
							if(mobileRow>0)
							{
								
								
							    SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
						        
						        String datetoday = datetodayFormat.format(new Date())+txid; 
						        
						        Long partnertxid = Long.parseLong(datetoday);
						        
						        int updaterow = 0;
						        int updatesssrow = 0;
						        int counter = 0;
						        
						        int updateSSStxRS = 0;
						        int resetSSStxRS = 0;
						        
						        
						        
								if(bill.getCompanyid().equalsIgnoreCase("SSS01") || bill.getCompanyid().equalsIgnoreCase("SSS02") || bill.getCompanyid().equalsIgnoreCase("SSS03")  )
								{
									
									String getSSStx = "select counter from sss_counter w";
									String updateSSStx = "update sss_counter set counter = counter + 1";
									String resetSSStx = "update sss_counter set counter = ?";
									
									SqlRowSet getSSStxRS   = getJdbcTemplate().queryForRowSet(getSSStx.toString());
									
									if(getSSStxRS.next())
									{
										
										counter = getSSStxRS.getInt("counter");
										
										
									}
									
									
									if(counter > 9999)
									{
									
										counter = 1;
										
										resetSSStxRS = getJdbcTemplate().update(resetSSStx, new Object[] { 
													counter
												});
										
									}else
									{
										
										updateSSStxRS = getJdbcTemplate().update(updateSSStx, new Object[] { 
											
											});
										
										
									}
									
									String ctr = counter +"";
									
									StringBuffer sb = new StringBuffer(ctr);
									
									int accountlength = ctr.length();
									
									for(int i = 0;i<4-accountlength;i++)
									{
										
										sb.insert(i, "0");
									}
									
									String ssstx = sb+"z"+"0000";
									
									
									
									String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";

							    	
									 updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
											datetoday,txid
										});
									 
									 
									String updatessstx = "update bills_payment_transaction set ssstransactionid = ? where transactionid=?";

								    	
										 updatesssrow = getJdbcTemplate().update(updatessstx, new Object[] { 
												 ssstx,txid
											});
				
									
									
								}else
								{
									
			
										String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";

								    	
										 updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
												datetoday,txid
											});
				
										
								}
								
						     
								
								

								
								if(row>0 && mobileRow>0 && updaterow>0)
								{		
										String field = "";
										String type = "";
										String required = "";
										StringBuilder createUploadField = new StringBuilder();
										
										String getuploadfields = "SELECT a.upload_field,a.position,a.type,a.isrequired FROM upload_field_format a,biller_fields_format b  WHERE a.biller = b.biller_code and b.biller_code =  ? ORDER BY position";
									
										SqlRowSet getuploadfieldsRS   = getJdbcTemplate().queryForRowSet(getuploadfields.toString(),bill.getCompanyid());
										
										logger.info("biller: "+bill.getCompanyid());
										
										while(getuploadfieldsRS.next())
										{
										
											field = getuploadfieldsRS.getString("upload_field");
											
											type = getuploadfieldsRS.getString("type");
											
											required = getuploadfieldsRS.getString("isrequired");
											
											String getFieldValue = "select "+field+" from bills_payment_transaction where transactionid = ?"; 
											
											SqlRowSet getFieldValueRS   = getJdbcTemplate().queryForRowSet(getFieldValue.toString(),txid);
											   
											if(getFieldValueRS.next())
											{
												 //Map map  = new HashMap();
												String uploadfield = "";  
												SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
												DecimalFormat df = new DecimalFormat("0.00");
												
												if(type.equalsIgnoreCase("varchar"))
												{
													logger.info("field: "+field);
													 uploadfield =  getFieldValueRS.getString(field);
													 
													 if(uploadfield == null){
														 uploadfield = "";
													 }
													 
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
													 
													 
													//map.put(field, getFieldValueRS.getString(field));
												}
												else if (type.equalsIgnoreCase("float"))
												{
													float fieldfloat = (float) (Math.round(getFieldValueRS.getFloat(field)*100.0f)/100.0f);
													uploadfield = df.format(fieldfloat);
													//map.put(field, getFieldValueRS.getFloat(field));
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
												}
												else if (type.equalsIgnoreCase("date"))
												{											
										
													 uploadfield = date.format(getFieldValueRS.getDate(field));	
													 
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
													 
				
													
												}
												else if (type.equalsIgnoreCase("time"))
												{
													SimpleDateFormat time = new SimpleDateFormat("KK:mm:ss");
													uploadfield = time.format(getFieldValueRS.getTime(field));
													
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
												}
												else if (type.equalsIgnoreCase("datetime"))
												{
													 uploadfield = date.format(getFieldValueRS.getDate(field));
													//map.put(field, getFieldValueRS.getDate(field));
													 
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
												}
												else if (type.equalsIgnoreCase("int"))
												{
													uploadfield =  getFieldValueRS.getInt(field)+"";
													
													 if(required.equalsIgnoreCase("n")){
														 uploadfield = "";
													 }
												}
										
				
												createUploadField.append(uploadfield+"|");
												System.out.println(createUploadField.toString());
											}
											
											
											
										}		String updatetx2 = "update bills_payment_transaction set upload_mapping = ? where transactionid=?";

												int updaterow2 = getJdbcTemplate().update(updatetx2, new Object[] { 
														createUploadField.toString(),txid
												});
										

										   
								
		

						
									return partnertxid;
									
								}
								
								
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				
				        }
					return id;
				
						

				
			
				
				}

	
	return id;
}
	
	
	public Long insertTransactionWithCISFI(BillsPaymentModel r,HttpSession session) {

		  
		
		   Long id = (long) 0;
	
		   final int aid =  Integer.parseInt( session.getAttribute("AID").toString());
		   
		   final int walletid = (int) session.getAttribute("walletid");
		   
		   final BillsPaymentModel bill = r;
		
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
		
			final int txid = keyHolder.getKey().intValue();
			
			System.out.println("txid "+txid);
			
				
//			final String user = session.getAttribute("USER").toString();
			final String user = "pcswsusr";
				
				if(row>0)
				
				
				{
					

					
						final StringBuilder insertMobileTx = new StringBuilder();
					   
					   insertMobileTx.append("INSERT INTO bills_payment_transaction  ");
					   insertMobileTx.append("(transactionid,company_id,user_id,account_number,cash_amount,bill_date,expiry_date,name,address,account_type,bill_amount,date_paid,time_paid,check_amount,biller_post_tran_field,payfield,branch_code) ");
					   insertMobileTx.append(" VALUES (?,(select biller from biller_fields_format where biller_code = ?),?,?,?,?,?,?,?,?,?,CURDATE(),CURTIME(),?,?,?,?)");

					   try
					   {
						   
						   
							KeyHolder keyHolderCisfi = new GeneratedKeyHolder();
							
							int rowcisfi = getJdbcTemplate().update(new PreparedStatementCreator() 
							{
								  public PreparedStatement createPreparedStatement(Connection con)
										    throws SQLException 
										    {
										   PreparedStatement ps = con.prepareStatement(insertMobileTx.toString(),Statement.RETURN_GENERATED_KEYS);

										   ps.setInt(1, txid);
										   ps.setString(2, bill.getCompanyid());
										   ps.setString(3, user);
										   ps.setString(4, bill.getAccountnumber());
										   ps.setFloat(5, Float.parseFloat(bill.getCash_amount()));
										   ps.setTimestamp(6, (java.sql.Timestamp) bill.getBill_date());
										   ps.setTimestamp(7, (java.sql.Timestamp) bill.getExpiry_date());
										   ps.setString(8, bill.getName());
										   ps.setString(9, bill.getAddress());
										   ps.setString(10, bill.getAccount_type());
										   ps.setFloat(11, Float.parseFloat(bill.getBill_amount()));
										   ps.setFloat(12, (float) 0.0);
										   ps.setString(13,bill.getBiller_post_tran_field());
										   ps.setString(14,bill.getPayfield());
										   ps.setString(15,bill.getBranchcode());
								
										  
										 
										   return ps;
										     }

						
							}, keyHolderCisfi);
							
							
							String field = "";
							String type = "";
							StringBuilder createUploadField = new StringBuilder();
							
							//String getuploadfields = "SELECT upload_field,POSITION,type FROM upload_field_format WHERE biller =(select biller from biller_fields_format where biller_code = ?) ORDER BY position";
							String getuploadfields = "SELECT a.upload_field,a.position,a.type,a.isrequired FROM upload_field_format a,biller_fields_format b  WHERE a.biller = b.biller_code and b.biller_code =  ? ORDER BY position";
							
							SqlRowSet getuploadfieldsRS   = getJdbcTemplate().queryForRowSet(getuploadfields.toString(),bill.getCompanyid());
							logger.info("biller: "+bill.getCompanyid());
							while(getuploadfieldsRS.next())
							{
							
								field = getuploadfieldsRS.getString("upload_field");
								
								type = getuploadfieldsRS.getString("type");
								
								String getFieldValue = "select "+field+" from bills_payment_transaction where transactionid = ?"; 
								
								SqlRowSet getFieldValueRS   = getJdbcTemplate().queryForRowSet(getFieldValue.toString(),txid);
								   
								if(getFieldValueRS.next())
								{
									 //Map map  = new HashMap();
									String uploadfield = "";  
									SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
									DecimalFormat df = new DecimalFormat("0.00");
									
									if(type.equalsIgnoreCase("varchar"))
									{
										 uploadfield =  getFieldValueRS.getString(field);
										 
										 if(uploadfield == null){
											 uploadfield = "";
										 }
										//map.put(field, getFieldValueRS.getString(field));
									}
									else if (type.equalsIgnoreCase("float"))
									{
										float fieldfloat = (float) (Math.round(getFieldValueRS.getFloat(field)*100.0f)/100.0f);
										uploadfield = df.format(fieldfloat);
										//map.put(field, getFieldValueRS.getFloat(field));
									}
									else if (type.equalsIgnoreCase("date"))
									{											
							
										 uploadfield = date.format(getFieldValueRS.getDate(field));	
										
									}
									else if (type.equalsIgnoreCase("time"))
									{
										SimpleDateFormat time = new SimpleDateFormat("KK:mm:ss");
										uploadfield = time.format(getFieldValueRS.getTime(field));
									}
									else if (type.equalsIgnoreCase("datetime"))
									{
										 uploadfield = date.format(getFieldValueRS.getDate(field));
										//map.put(field, getFieldValueRS.getDate(field));
									}
									else if (type.equalsIgnoreCase("int"))
									{
										uploadfield =  getFieldValueRS.getInt(field)+"";
									}
							
	
									createUploadField.append(uploadfield+"|");
									System.out.println(createUploadField.toString());
								}
								
								
								
							}
							
					        
										String updatetx2 = "update bills_payment_transaction set upload_mapping = ? where transactionid=?";
										
						
										
								    	
										int updaterow2 = getJdbcTemplate().update(updatetx2, new Object[] { 
												createUploadField.toString(),txid
										});
//								
//							}
								   
//							}
//							
//							else if(isExtraTran.equalsIgnoreCase("N"))
//							{
//								
//								StringBuilder getUploadMappingWithoutExtraTran = new StringBuilder();
//								   
//								getUploadMappingWithoutExtraTran.append("SELECT company_id,id,user_id,account_number,cash_amount,check_amount,date_paid, ");
//								getUploadMappingWithoutExtraTran.append("time_paid,bill_date,expiry_date,bank_code,check_number,name, address,branch_code, account_type, bill_amount ");
//								getUploadMappingWithoutExtraTran.append("FROM bills_payment_transaction WHERE transactionid = ?");
//								   
//								   SqlRowSet getUploadWithoutExtraTranMappingRs   = getJdbcTemplate().queryForRowSet(getUploadMappingWithoutExtraTran.toString(),txid);
//								   
//								   if(getUploadWithoutExtraTranMappingRs.next())
//								   {
//									    
//									   
//									    String company = getUploadWithoutExtraTranMappingRs.getString("company_id");
//									    int tx = getUploadWithoutExtraTranMappingRs.getInt("id");
//									    String username = getUploadWithoutExtraTranMappingRs.getString("user_id");
//									    String reference = getUploadWithoutExtraTranMappingRs.getString("account_number");
//									    
//									    float cash = (float) (Math.round(getUploadWithoutExtraTranMappingRs.getFloat("cash_amount")*100.0f)/100.0f);
//							    		
//							    		
//									    float check = (float) (Math.round(getUploadWithoutExtraTranMappingRs.getFloat("check_amount")*100.0f)/100.0f);
//									    
//									    DecimalFormat df = new DecimalFormat("0.00");
//									    String cashformatted = df.format(cash);
//									    String checkformatted = df.format(check);
//
//									    
//										SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
//										String dateAsString = date.format(getUploadWithoutExtraTranMappingRs.getDate("date_paid"));
//										
//										SimpleDateFormat time = new SimpleDateFormat("KK:mm:ss");
//										String timeAsString = time.format(getUploadWithoutExtraTranMappingRs.getTime("time_paid"));
//									    
//										String billdate = date.format(getUploadWithoutExtraTranMappingRs.getDate("bill_date"));
//										String expirydate = date.format(getUploadWithoutExtraTranMappingRs.getDate("expiry_date"));
//										
//										String bank_code = getUploadWithoutExtraTranMappingRs.getString("bank_code");
//										String check_number = getUploadWithoutExtraTranMappingRs.getString("check_number");
//										String name = getUploadWithoutExtraTranMappingRs.getString("name");
//										
//										String address = getUploadWithoutExtraTranMappingRs.getString("address");
//										String branchcode = getUploadWithoutExtraTranMappingRs.getString("branch_code");
//										String account_type = getUploadWithoutExtraTranMappingRs.getString("account_type");
//										
//										float  billamt = getUploadWithoutExtraTranMappingRs.getFloat("bill_amount");
//										
//										String billamtformatted = df.format(billamt);
//										
//										if(bank_code == null){
//											bank_code = "";
//										}
//										
//										if(check_number == null){
//											check_number = "";
//										}
//										
//										if(name == null){
//											name = "";
//										}
//										
//										if(address == null){
//											address = "";
//										}
//										
//										if(account_type == null ){
//											account_type = "";
//										}
//										
//										if(branchcode == null){
//											branchcode = "";
//										}
//										
//										StringBuilder uploadmapping= new StringBuilder();
//
//										uploadmapping.append(company.toUpperCase()+"|"+tx+"|"+username+"|"+reference+"|"+cashformatted+"|"+checkformatted+"|"+dateAsString+"|"+timeAsString+"|"+billdate+"|"+expirydate+"|"+bank_code+"|"+check_number+"|"+name+"|"+address+"|"+branchcode+"|"+account_type+"|"+billamtformatted+"|");
//
//								        
//										String updatetx2 = "update bills_payment_transaction set upload_mapping = ? where transactionid=?";
//										
//						
//										
//								    	
//										int updaterow2 = getJdbcTemplate().update(updatetx2, new Object[] { 
//												uploadmapping.toString(),txid
//										});
//								
//								   }
//								
//							}
//							

							
							
							final int cisfitxid = keyHolderCisfi.getKey().intValue();
							
							System.out.println("cisfitxid "+cisfitxid);
				
							if(rowcisfi>0)
							{
								
								
						        SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
						        
						        String datetoday = datetodayFormat.format(new Date())+txid; 
						        
								String updatetx = "update transactions set PartnerTXID = ? where transactionid=?";
								
								Long partnertxid = Long.parseLong(datetoday);
								
						    	
								int updaterow = getJdbcTemplate().update(updatetx, new Object[] { 
										datetoday,txid
									});
								
								
								
								if(updaterow>0)
								{
									
									   final float scharge = this.getServiceCharge(bill.getCompanyid());
									
									   final StringBuilder insertCISFItx = new StringBuilder();
									   
									   insertCISFItx.append("INSERT INTO bills_payment_transaction  ");
									   insertCISFItx.append("(transactionid,company_id,user_id,account_number,cash_amount,bill_date,expiry_date,name,address,bank_code,bill_amount,date_paid,time_paid,check_amount,status) ");
									   insertCISFItx.append(" VALUES (?,?,?,?,?,now(),now(),?,?,(select biller from biller_fields_format where biller_code = ?),?,CURDATE(),CURTIME(),?,?)");
									
//										int cisfiRow = getJdbcTemplate().update(insertCISFItx.toString(), new Object[] { 
//											txid,"CISFI",session.getAttribute("USER"),cisfitxid+"",scharge,bill.getName(),bill.getAddress(),bill.getCompanyid(),Float.parseFloat(bill.getBill_amount()),0.0
//										});
									   
									   
										KeyHolder keyHolderNewCisfiID = new GeneratedKeyHolder();
										
										final int cisfiRow = getJdbcTemplate().update(new PreparedStatementCreator() 
										{
											  public PreparedStatement createPreparedStatement(Connection con)
													    throws SQLException 
													    {
													   PreparedStatement ps = con.prepareStatement(insertCISFItx.toString(),Statement.RETURN_GENERATED_KEYS);

													   ps.setInt(1, txid);
													   ps.setString(2, "CISFI");
													   ps.setString(3, user);
													   ps.setString(4, cisfitxid+"");
													   ps.setFloat(5, scharge);
													   ps.setString(6, "");
													   ps.setString(7, "");
													   ps.setString(8, bill.getCompanyid());
													   ps.setFloat(9, scharge);
													   ps.setFloat(10, (float) 0.0);
													   ps.setString(11, "Success");
												
								
													   return ps;
													     }

									
										}, keyHolderNewCisfiID);
										
										
										int newcisfiid = keyHolderNewCisfiID.getKey().intValue();
										
										System.out.println("newcisfiid: "+newcisfiid);
										
										if(row>0 && rowcisfi>0 && updaterow>0 && cisfiRow > 0)
										{
											
			
										        

												StringBuilder getUploadMappingCISFI = new StringBuilder();
												   
												getUploadMappingCISFI.append("SELECT company_id,id,user_id,account_number,cash_amount,check_amount,date_paid, ");
												getUploadMappingCISFI.append("time_paid,bill_date,expiry_date,bank_code,check_number,name, address,branch_code, account_type, bill_amount ");
												getUploadMappingCISFI.append("FROM bills_payment_transaction WHERE id = ?");
												   
												   SqlRowSet getUploadMappingCISFIRs   = getJdbcTemplate().queryForRowSet(getUploadMappingCISFI.toString(),newcisfiid);
												   
												   if(getUploadMappingCISFIRs.next()){
													    
													   
													    String company = getUploadMappingCISFIRs.getString("company_id");
													    int tx = getUploadMappingCISFIRs.getInt("id");
													    String username = getUploadMappingCISFIRs.getString("user_id");
													    String reference = getUploadMappingCISFIRs.getString("account_number");
													    
													    
													    float cash = (float) (Math.round(getUploadMappingCISFIRs.getFloat("cash_amount")*100.0f)/100.0f);
											    		
											    		
													    float check = (float) (Math.round(getUploadMappingCISFIRs.getFloat("check_amount")*100.0f)/100.0f);
													    
													    DecimalFormat df = new DecimalFormat("0.00");
													    String cashformatted = df.format(cash);
													    String checkformatted = df.format(check);

													    
														SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
														String dateAsString = date.format(getUploadMappingCISFIRs.getDate("date_paid"));
														
														SimpleDateFormat time = new SimpleDateFormat("KK:mm:ss");
														String timeAsString = time.format(getUploadMappingCISFIRs.getTime("time_paid"));
													    
														String billdate = date.format(getUploadMappingCISFIRs.getDate("bill_date"));
														String expirydate = date.format(getUploadMappingCISFIRs.getDate("expiry_date"));
														
														String bank_code = getUploadMappingCISFIRs.getString("bank_code");
														String check_number = getUploadMappingCISFIRs.getString("check_number");
														String name = getUploadMappingCISFIRs.getString("name");
														
														String address = getUploadMappingCISFIRs.getString("address");
														String branchcode = getUploadMappingCISFIRs.getString("branch_code");
														String account_type = getUploadMappingCISFIRs.getString("account_type");
													
														float  billamt = getUploadMappingCISFIRs.getFloat("bill_amount");
														
														String billamtformatted = df.format(billamt);
														
														if(bank_code == null){
															bank_code = "";
														}
														
														if(check_number == null){
															check_number = "";
														}
														
														if(name == null){
															name = "";
														}
														
														if(address == null){
															address = "";
														}
														
														if(account_type == null ){
															account_type = "";
														}
														
														if(branchcode == null ){
															branchcode = "";
														}
														
														StringBuilder uploadmapping= new StringBuilder();

														uploadmapping.append(company.toUpperCase()+"|"+tx+"|"+username+"|"+reference+"|"+cashformatted+"|"+checkformatted+"|"+dateAsString+"|"+timeAsString+"|"+billdate+"|"+expirydate+"|"+bank_code+"|"+check_number+"|"+name+"|"+address+"|"+account_type+"|"+billamtformatted+"|");

												        
														String updateUpload = "update bills_payment_transaction set upload_mapping = ? where id=?";
														

												    	
														int updateUploadRS = getJdbcTemplate().update(updateUpload.toString(), new Object[] { 
																uploadmapping.toString(),newcisfiid
														});
														
														
												   }
												   
												   
											return partnertxid;
											
										}
		
								}
								
		
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				
				        }
					return id;
				
						

				
			
				
				}

	
	return id;
}
	
	
	
public void updateTransaction(Long txid, String status) {

		
		try {
			
			String statuscode = "-1";
			
			if(status.equalsIgnoreCase("")){
				status = "Exact payment received";
				statuscode = "0";
			}else if (status.equalsIgnoreCase("Under payment received")){
				statuscode = "1";
			}else if (status.equalsIgnoreCase("Over payment received")){
				statuscode = "2";
			}
			
			
			String updatetx = "update transactions set status=? ,responsemsg = ? where partnertxid=?";
			
	    	
			int row = getJdbcTemplate().update(updatetx, new Object[] { 
						statuscode,status,txid+""
				});
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}


public void updateBillTransaction(Long txid, String status) {

	
	try {
		
		
		
		String updatetx = "update bills_payment_transaction set status= ?  where transactionid=?";
		
    	
		int row = getJdbcTemplate().update(updatetx, new Object[] { 
				status,txid+""
			});
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.info("update tx exception: "+e.getMessage());
	} finally {

	}
}
	
	
	public void updateTransaction(Long txid, int errorState) {

		
		try {
			

				String updatetx = "update transactions set status=?, responsemsg=? where partnertxid=?";
				
		    	
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						errorState+"",P2MConstants.getMessage(errorState),txid+""
					});
				

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	public void updateServicefee(Long txid, Float scharge) {
		
		
		try {
		
				//String updatetx = "update transactions set transactionfee=?, px_transactionfeetype = ? where partnertxid=?";
				
		    	
				String updatetx = "UPDATE transactions SET transactionfee= ?, px_transactionfeetype = ? WHERE partnertxid= ?";
				int row = getJdbcTemplate().update(updatetx, new Object[] { 
						scharge,"COLLECTED",txid+""
					});
				

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update tx exception: "+e.getMessage());
		} finally {
	
		}
	}
	
	

	
	public int updateTransactionDecre(Long txid, float amount) throws SQLException {
		
			int rows = 0;
			
			String sql = "update transactions set discount_amount = ? where partnertxid = ?";
	 		
			try{
		 		 
				int row = getJdbcTemplate().update(sql, new Object[] { 
						amount,txid+""
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
	
	
	
	
//	public Float getWallet(HttpSession session){
//		
//		   StringBuilder strSQL = new StringBuilder();
//		   
//		   Float wallet = null;
//		   String type = null;
//		   
//		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
//		   
//		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
//			
//		   for (Map row : rows) {
//			   
//
//			   wallet = (Float)row.get("wallet");   
//			   		   
//			   return wallet;
//				   	   			   
//		   }
//		return wallet;  
//		   
//			  
//		
//	}
	
//	public Float getWallet(HttpSession session){
//		
//		   StringBuilder strSQL = new StringBuilder();
//		   
//		   Float wallet = null;
//		   String type = null;
//		   
////		   strSQL.append("SELECT wallet,paymenttype from wallets where partnerid = ? ");
//		   
//		   strSQL.append("SELECT a.walletid, a.wallet,a.paymenttype from wallets a, partners_wallet b, wallet_types c "); 
//		   strSQL.append("where a.walletid = b.walletid  and b.wallet_type = c.wallet_type and a.partnerid = ? ");
//		   strSQL.append("and c.wallet_name = ?");
//		   
//		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"Bayad Center");
//			
//		   for (Map row : rows) {
//			   
//
//			   wallet = (Float)row.get("wallet");   
//			   		   
//			   session.setAttribute("BayadWallet", (int)row.get("walletid"));
//			   return wallet;
//				   	   			   
//		   }
//		return wallet;  
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
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"Bayad Center");
			
		   for (Map row : rows) {
			   

			   wallet = (BigDecimal)row.get("wallet");   
			   		   
			   session.setAttribute("BayadWallet", (int)row.get("walletid"));
			   return wallet.floatValue();
				   	   			   
		   }
		return wallet.floatValue();  
		   
			  
		
	}
	
	public Float getServiceCharge(String biller){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   Float servicecharge = (float) 0;
		   
		   
		   strSQL.append("SELECT service_charge from biller_fields_format  where biller_code = ? ");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),biller);
			
		   for (Map row : rows) {
			   

			   servicecharge = (Float)row.get("service_charge");   
	   	   			   
		   }
		return servicecharge;  
		   
			  
		
	}
	
	 public int deductWallet(HttpSession session, Long txid, float topupamount)
	  {
		 
		 
		 		
		 		String updateTxBranch = "update wallets set wallet = wallet - ? , partnertxid = ? " +
		 					"where partnerid = ? and walletid = ? and (wallet + ?) >= 0";
		 		
		 		String transid = txid+"";
				
				
				
				try{
			 		 
					int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
							topupamount,transid,session.getAttribute("PID").toString(), Integer.parseInt(session.getAttribute("bayadwallet")+""), topupamount
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
							topupamount,transid,session.getAttribute("PID"), topupamount,session.getAttribute("BayadWallet")
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
	 
	 
		public String getBillerCode(String billername){
			
			   StringBuilder strSQL = new StringBuilder();

			   String biller = null;
			   
			   strSQL.append("SELECT * from request_types where request_name = ? ");
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),billername);
				
			   for (Map row : rows) {
				   

				   biller = (String)row.get("request_type");   
				   		   
				   return biller;
					   	   			   
			   }
			return biller;  
			   
				  
			
		}
		
		
		public String getAccounttype(String biller){
			
			   StringBuilder strSQL = new StringBuilder();

			   String accounttype = null;
			   
			   strSQL.append("SELECT * from request_types where request_type = ? ");
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),biller);
				
			   for (Map row : rows) {
				   

				   accounttype = (String)row.get("account_type");   
				   		   
				   return accounttype;
					   	   			   
			   }
			return accounttype;  
			   
				  
			
		}
		
		
		public List<BillsPaymentHistoryModel>  getTransactionHistory(HttpSession session)
		   {
			   ArrayList<BillsPaymentHistoryModel> billsList = new ArrayList<BillsPaymentHistoryModel>();
			
				ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
				
				BillsPaymentDAO dao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
			
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.date_paid,a.transactionid,a.company_id,a.account_number,a.cash_amount,b.status,b.responsemsg   ");
			   strSQL.append("FROM bills_payment_transaction a INNER JOIN transactions b ON a.transactionid = b.transactionid ");
			   strSQL.append("WHERE b.agentid = ?   and b.status = ? order by a.date_paid desc");
			  
			   billsList.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("AID"),"0");
				for (Map row : rows) {

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("date_paid"));
					
					java.util.Date parsedDate;
					
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						
						BillsPaymentHistoryModel bills = new BillsPaymentHistoryModel();
						bills.setDatepaid((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						bills.setTransactionid((String)(row.get("transactionid")+""));
						bills.setCompanyid((String)(row.get("company_id")));
						bills.setAccountnumber((String)(row.get("account_number")));
						bills.setAmount((Float)(row.get("cash_amount")));
						bills.setResponsemsg((String)(row.get("responsemsg")));
						
						billsList.add(bills);	
						
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
			
				//session.setAttribute("consumerreports", userlogs);
				return billsList;
			   	  
		   }

		
		public String checkDefaultAccnType(String biller){
			
			   StringBuilder strSQL = new StringBuilder();
			  				
			   String accnt = null;
			   

			   strSQL.append("SELECT upload_mapping_account_type from biller_fields_format where biller_code = ?");
			   
			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),biller);
			   
			   if(rs.next())
			   {
				   if(rs.getString("upload_mapping_account_type") != null){
					  
				
					   accnt = rs.getString("upload_mapping_account_type");
				   }
				   

				   		   
			   }

				return accnt;
				
		}

}
