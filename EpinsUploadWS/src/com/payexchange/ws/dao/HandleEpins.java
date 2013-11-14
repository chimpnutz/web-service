package com.payexchange.ws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.payexchange.ws.enc.AesZipFileEncrypter;

import com.payexchange.ws.beans.DetailsBean;
import com.payexchange.ws.beans.EpinsUploadResponse;
import com.payexchange.ws.beans.MailModel;
import com.payexchange.ws.connection.ConnectionManager;
import com.payexchange.ws.connection.EpinsConnectionManager;
import com.payexchange.ws.connection.SMSConnectionManager;
import com.payexchange.ws.connection.SubwayConnection;
import com.payexchange.ws.utility.MessageModels;
import com.payexchange.ws.utility.Utility;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.soap.util.Bean;
import org.eclipse.jetty.server.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ServletContextAware;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.payexchange.ws.utility.Property;
import com.payexchange.ws.utility.MessageModels;
import com.paysetter.security.Encrypter;

import com.payexchange.ws.utility.OutgoingSMSModel;
import com.payexchange.ws.dao.OutgoingSMSWriterDAO;



import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;



public class HandleEpins  {
	
	private static final Logger logger = Logger.getLogger(HandleEpins.class);

	public EpinsUploadResponse reqUpload(DetailsBean bean) throws SQLException {
		
		
		EpinsUploadResponse response = new EpinsUploadResponse();
		OutgoingSMSWriterDAO smsdao = new  OutgoingSMSWriterDAO();
		
		if(checkip(bean))
		{
			
			response.setPassword("123456");
			response.setResultcode(0);
			response.setTracenumber("8357235");
						
			int qty = bean.getQty();
			
			if(qty >= 2){
				//email
				long tranid = this.insertTxLogs2(bean);	
				
				String message = "";
				
				if(getDenom(bean)){
					
					System.out.println(this.getEmail());
					
					if(updateEpins(bean)){
						
						if(checkEpins(bean)){
							
							PreparedStatement ps = null;
							ResultSet rs = null;
							Connection conn = null;
							
							int denom = Integer.parseInt(bean.getDenom());
							String telco = bean.getProdCode();
							String sql = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status=? and telco_type=? and denom=? limit ?";
											
							try{
							       conn = EpinsConnectionManager.getConnection();
							       ps = conn.prepareStatement(sql);
							            
							       ps.setInt(1,6);
							       ps.setString(2,telco);
							       ps.setInt(3,denom);
							       ps.setInt(4,qty);     
							       rs = ps.executeQuery();
							       
							      
									int i=1;
							       while(rs.next())
							       {
							            							        
									try{
									
									    String dec = goDecryption(rs.getString("epin"));
										String[] decArray = this.getDecrypted(dec);
											int j = 0;				
	
								           for( j = 0;j<decArray.length;j++) {
								               
								               logger.info(decArray[j]);
								               message = message+"     "+decArray[j];					               
								           }
								    
	
	
										} catch ( Exception ex ) {
											logger.info(ex);
	
										}
											i++;
							         }
							   }
									catch(Exception ex){
									ex.printStackTrace();
					        
									}
										finally{
						        	
							        	Utility.closeQuietly(rs);
							        	Utility.closeQuietly(ps);
							        	Utility.closeQuietly(conn);
				   	
										}
							
									this.writeExcel(bean.getUsername(), Integer.parseInt(bean.getDenom()), bean.getProdCode(), bean.getQty(), bean.getPassword(), bean.getTarget());
									this.updateTX(tranid,MessageModels.EPIN_SUCCESS);
								}
							}

						}
				
				
			}
			if(qty == 1){
				//mobile 
				long tranid = this.insertTxLogs(bean);				
				
				
				String message = "";
				
				
				if(getDenom(bean)){
					
						if(updateEpins(bean)){
							
							if(checkEpins(bean)){
								
								PreparedStatement ps = null;
								ResultSet rs = null;
								Connection conn = null;
								
								String telco = bean.getProdCode();
								int denom = Integer.parseInt(bean.getDenom());
								
								String sql = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status=? and telco_type=? and denom=? limit ?";
								
								try{
								       conn = EpinsConnectionManager.getConnection();
								       ps = conn.prepareStatement(sql);
								            
								       ps.setInt(1,6);
								       ps.setString(2,telco);
								       ps.setInt(3,denom);
								       ps.setInt(4,1);     
								       rs = ps.executeQuery();
								       
								      
										int i=1;
								       while(rs.next())
								       {
								            							        
										try{
										
										    String dec = goDecryption(rs.getString("epin"));
											String[] decArray = this.getDecrypted(dec);
												int j = 0;				

									           for( j = 0;j<decArray.length;j++) {
									               
									               logger.info(decArray[j]);
									               message = message+"     "+decArray[j];					               
									           }
									    


											} catch ( Exception ex ) {
												logger.info(ex);

											}
												i++;
								         }
								}
								catch(Exception ex){
						            ex.printStackTrace();
						        
						        }
								finally{
						        	
						        	Utility.closeQuietly(rs);
						        	Utility.closeQuietly(ps);
						        	Utility.closeQuietly(conn);
						   	
						        }
								
								OutgoingSMSModel SMSout = new OutgoingSMSModel();										
								String target = bean.getTarget();
								String topup = null;
													
								SMSout.setSender("2808");
								SMSout.setRecipient(target);
								SMSout.setSmsData(message);
								SMSout.setTariffClass("");
								SMSout.setServiceType("");
								
								try {
					
										smsdao.insertSmsSent(SMSout);
														
									} catch (Exception e) {
									  e.printStackTrace();
									  logger.info("***** Failed to insert SMS *****");
									  } finally {
													SMSout = null;
										}
								this.updateTX(tranid,MessageModels.EPIN_SUCCESS);
								}
						}
					
										
				}
				
							
			}
			           				       												
	}	  
	
	  return response;
			  
}

	private boolean getDenom(DetailsBean bean) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		
		String selectSQL = "Select denom from epins where denom =?";
		
		try{
			conn = EpinsConnectionManager.getConnection();
			ps = conn.prepareStatement(selectSQL);
			
			ps.setString(1, bean.getDenom());
			
			rs = ps.executeQuery();
			
			logger.info("checking denom "+bean.getDenom());
			
			if(rs.next()){
            	
		           logger.info("Denom is Valid");		
		           return true;
		         }
		
		        }
		catch(Exception ex){
		            ex.printStackTrace();
		            return false;
		        }
		finally{
     	
     	Utility.closeQuietly(rs);
     	Utility.closeQuietly(ps);
     	Utility.closeQuietly(conn);
	
     		}
		      
		        
		        logger.info("Invalid Denom");		
		        return false;
	
	}
	
	

	private String writePrefix(String username, int denom, String telco, String rptDate) {
	       return username+""+denom+""+telco+""+rptDate;
	   }


	private boolean checkip(DetailsBean bean) {
		
		
					
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
					
		String updateSQL = "Select ipaddress, appname, txtype from permissions where ipaddress = ? and appname = ? and txtype = ?";
			
			       
			try{
			       conn = ConnectionManager.getConnection();
			       ps = conn.prepareStatement(updateSQL);
			            
			       ps.setString(1,bean.getIpaddress());
			       ps.setString(2,bean.getAppname());
			       ps.setString(3,bean.getTrantype());
			            
			       rs = ps.executeQuery();
			       
			       logger.info("Request epins from: "+bean.getIpaddress());
			
			       if(rs.next()){
			            	
			           logger.info("IP address, application name, and transaction type are Valid");		
			           return true;
			         }
			
			        }
			catch(Exception ex){
			            ex.printStackTrace();
			            return false;
			        }
			finally{
	        	
	        	Utility.closeQuietly(rs);
	        	Utility.closeQuietly(ps);
	        	Utility.closeQuietly(conn);
	   	
	        		}
			      
			        
			        logger.info("IP address or  application name or transaction type are invalid");		
			        return false;
		
		}

	
public long insertTxLogs(DetailsBean bean) {
			PreparedStatement ps = null;
			Connection conn = null;
			long key = -1;
			
			int i = 0;
			
			
			String addSQL = "insert into transactions (transactiondate,targetmsisdn,amount,transactiontype,ipaddress,appname)" +
	        		"values (now(),?,?,?,?,?)";
	        try{
	        	conn = ConnectionManager.getConnection();
	            ps = conn.prepareStatement(addSQL,PreparedStatement.RETURN_GENERATED_KEYS);
	            	    		
	    		String target = bean.getTarget();
	    		String denom = bean.getDenom();
	    		String trantype = bean.getTrantype();
	    		String ipaddress = bean.getIpaddress();
	    		String appname = bean.getAppname();
	    		
	    		ps.setString(1, target);
	            ps.setString(2, denom);          
	            ps.setString(3, trantype);
	            ps.setString(4, ipaddress);
	            ps.setString(5, appname);
	           
	            if(ps.executeUpdate()>0){
	            	ResultSet rs =  ps.getGeneratedKeys();
	            	if (rs.next()) {
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		    int colCount = rsmd.getColumnCount();
	            		    do {
	            		        for (int t = 1; t <= colCount; t++) {
	            		            key = rs.getLong(t);

	            		        }
	            		    }
	            		    while (rs.next());
	            		    
	            	}
	            	return key;
	            }

	        }catch(Exception ex){
	            ex.printStackTrace();
	            key = -1;
	            return key;
	        }
	        finally{
	        	
	        	Utility.closeQuietly(ps);
	        	Utility.closeQuietly(conn);
	        	
	        }
			return key;
	}

public long insertTxLogs2(DetailsBean bean) {
	PreparedStatement ps = null;
	Connection conn = null;
	long key = -1;
	
	int i = 0;
	
	
	String addSQL = "insert into transactions (transactiondate,targetmsisdn,amount,transactiontype,ipaddress,appname)" +
    		"values (now(),?,?,?,?,?)";
    try{
    	conn = ConnectionManager.getConnection();
        ps = conn.prepareStatement(addSQL,PreparedStatement.RETURN_GENERATED_KEYS);
        	    		
		String target = bean.getTarget();
		int denom = Integer.parseInt(bean.getDenom());
		String trantype = bean.getTrantype();
		String ipaddress = bean.getIpaddress();
		String appname = bean.getAppname();
		
		ps.setString(1, target);
        ps.setInt(2, denom);          
        ps.setString(3, trantype);
        ps.setString(4, ipaddress);
        ps.setString(5, appname);
       
        if(ps.executeUpdate()>0){
        	ResultSet rs =  ps.getGeneratedKeys();
        	if (rs.next()) {
        		 ResultSetMetaData rsmd = rs.getMetaData();
        		    int colCount = rsmd.getColumnCount();
        		    do {
        		        for (int t = 1; t <= colCount; t++) {
        		            key = rs.getLong(t);

        		        }
        		    }
        		    while (rs.next());
        	}
        	return key;
        }

    }catch(Exception ex){
        ex.printStackTrace();
        key = -1;
        return key;
    }
    finally{
    	
    	Utility.closeQuietly(ps);
    	Utility.closeQuietly(conn);
    	
    }
	return key;
}

public boolean updateEpins(DetailsBean bean){
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

			
	String sql = "update epins set status = 6, transactionid = ? where status = ? and transactionid is null and denom = ? and telco_type = ? limit ?" ;

		       
		try{
		       conn = EpinsConnectionManager.getConnection();
		       ps = conn.prepareStatement(sql);
		       
		       ps.setString(1, bean.getTransid());
		       ps.setInt(2, 0);
		       ps.setString(3, bean.getDenom());
		       ps.setString(4, bean.getProdCode());
		       ps.setInt(5, bean.getQty());
		       
		     
		            
		       if(ps.executeUpdate()>0){
		    	   logger.info("Epins Updated");
	            	return true;
	            }
		       
		
		   }
		catch(Exception ex){
		            ex.printStackTrace();
		            return false;
		        }
		finally{
        	
        	Utility.closeQuietly(rs);
        	Utility.closeQuietly(ps);
        	Utility.closeQuietly(conn);
   	
        		}
		      
		        
		        logger.info("Epins failed to update");		
		        return false;
	
	
}

public boolean updateTX(long tranid,String errorState)
{
	 PreparedStatement ps = null;
	 PreparedStatement ps2 = null;
	 ResultSet rs = null;
	 Connection conn = null;

	 int i = 0;
	long id = 0;

        String updateSQL = "UPDATE transactions set status = ? where transactionid = ?";
     
        
        try{
        	
        	conn = ConnectionManager.getConnection();    	
        
        	id = tranid;
            ps2 = conn.prepareStatement(updateSQL);
            ps2.setString(1, errorState);
            ps2.setLong(2, id);

                 
            if(ps2.executeUpdate()>0){
            	return true;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally{
        	
        	Utility.closeQuietly(rs);
        	Utility.closeQuietly(ps);
        	Utility.closeQuietly(conn);
        	
        }
        return false;
}
//creating excel file	
	private void writeExcel(String username, int denom, String telco, int Qty, String password, String recipient) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
					
		String updateSQL = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status=? and telco_type=? and denom=?";
		
		String rptDate = "";
		Date date = new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd");
	    rptDate=sdf.format(date);	
		File filename= new File("D:\\"+this.writePrefix(username, denom, telco, rptDate)+".xls");
		
		
		
		HSSFWorkbook hwb=new HSSFWorkbook();
		HSSFSheet sheet =  hwb.createSheet("new sheet");
		
		
		
		HSSFRow rowhead = sheet.createRow((short)0);
			try{
			       conn = EpinsConnectionManager.getConnection();
			       ps = conn.prepareStatement(updateSQL);
			            
			       ps.setInt(1,6);
			       ps.setString(2,telco);
			       ps.setInt(3,denom);
			            
			       rs = ps.executeQuery();
			       
					int i=1;
			       while(rs.next())
			       {
			            	
			    	  
				        
			        
					try{

					    String dec = goDecryption(rs.getString("epin"));
						String[] decArray = this.getDecrypted(dec);
						HSSFRow row=   sheet.createRow((short)i);					

				           for(int j = 0;j<decArray.length;j++) {
				               row.createCell(j).setCellValue(decArray[j]);
				           }
						
					
						

						} catch ( Exception ex ) {
							logger.info(ex);

						}
							i++;
			         }
					        sheet.autoSizeColumn(1);
							sheet.autoSizeColumn(2);
							sheet.autoSizeColumn(3);
							FileOutputStream fileOut =  new FileOutputStream(filename);
							
							hwb.write(fileOut);
							fileOut.close();
							logger.info("Your Excel file has been generated");
							
//							zipping and encrypting file
					    	File outFile = new File("D:\\Epins.zip");
					    	ObjectUtils.equals(password, null);
					    try{
					    	
					    	AesZipFileEncrypter.zipAndEncrypt(filename, outFile, password) ;	
					        AesZipFileEncrypter enc = new AesZipFileEncrypter(outFile);
					        enc.add(filename, password);
					        enc.close();
					        logger.info("done zipping and encrypting file");
					        
//email
				 
							ApplicationContext mailcontext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
							
							MailModel mm = (MailModel) mailcontext.getBean("mail");
							
							mm.sendMail("tristan.lapidez@payexchangeinc.com",
										recipient,
						    		   "Hello!", 
						    		   "Attached here is encrypted file. Use winzip or winrar for the attached file. ");
						    
						
							
					        logger.info("mail sent!");
					        
					        
					       } catch(IOException ex){
					    	   ex.printStackTrace();
					       }
							

			     }
			catch(Exception ex){
			            ex.printStackTrace();
			        
			        }
			finally{
	        	
	        	Utility.closeQuietly(rs);
	        	Utility.closeQuietly(ps);
	        	Utility.closeQuietly(conn);
	   	
	        		}
			      
			        
			       	
		
		}
//decrypt encrypted epins
    public String[] getDecrypted(String dec) {

        String[] xx = StringUtils.split(dec, " ");
        return xx;
    }
    
    private String goDecryption(String var) {
        Encrypter enc = new Encrypter();
        String xD = enc.decryptBase64String(var);
        return xD;
    }
    
    //checking epins
    
    public boolean checkEpins(DetailsBean bean){
    	
    	PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String transid = bean.getTransid();
		String telco = bean.getProdCode();
		String denom = bean.getDenom();
		
		String checkSQL = "Select id, epin, uploaded_by, date_uploaded from epins where transactionid =? and status=? and telco_type=? and denom=?";
        try{
        	   conn = EpinsConnectionManager.getConnection();
		       ps = conn.prepareStatement(checkSQL);
		       
		       ps.setString(1,transid);
		       ps.setInt(2, 6);
		       ps.setString(3,telco);
		       ps.setString(4,denom);
		       
		            
		       rs = ps.executeQuery();
		        if(rs.next()){
		        	   
			           logger.info("epin is valid");		
			           return true;
			         
		        }
        	}
        		catch(Exception ex){
	            ex.printStackTrace();
	            
	        }
				finally{
			 	
			 	Utility.closeQuietly(rs);
			 	Utility.closeQuietly(ps);
			 	Utility.closeQuietly(conn);
			
			 		}
	      
	        
	        logger.info("epin is already used");
			return false;		
   
    }
    
public boolean getEmail(){
		
    	PreparedStatement ps = null;	
		ResultSet rs = null;
		Connection conn = null;
		
	String pid = "677DS5MIJ7YT";
//	String pid = (String) session.getAttribute("PID");
	System.out.println(pid);	

		String checkSQL = "Select email from partners where partnerid = ?";
        try{
        	   conn = SubwayConnection.getConnection();
		       ps = conn.prepareStatement(checkSQL);
		       
		       ps.setString(1,pid);
		      
		       
		            
		       rs = ps.executeQuery();
		        if(rs.next()){
		        	   
			           logger.info("email is valid");		
			           return true;
			         
		        }
        	}
        		catch(Exception ex){
	            ex.printStackTrace();
	            
	        }
				finally{
			 	
			 	Utility.closeQuietly(rs);
			 	Utility.closeQuietly(ps);
			 	Utility.closeQuietly(conn);
			
			 		}
	      
	        
	        logger.info("email is invalid");
			return false;
			
					
		
		
		
    }
    
}
