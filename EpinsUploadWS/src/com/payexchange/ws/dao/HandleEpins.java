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

import com.payexchange.ws.beans.DetailsBean;
import com.payexchange.ws.beans.EpinsUploadResponse;
import com.payexchange.ws.connection.ConnectionManager;
import com.payexchange.ws.connection.EpinsConnectionManager;
import com.payexchange.ws.connection.SMSConnectionManager;
import com.payexchange.ws.utility.MailService;
import com.payexchange.ws.utility.MessageModels;
import com.payexchange.ws.utility.Utility;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;



public class HandleEpins  {
	
	private static final Logger logger = Logger.getLogger(HandleEpins.class);

	public EpinsUploadResponse reqUpload(DetailsBean bean) throws SQLException {
		
		
		EpinsUploadResponse response = new EpinsUploadResponse();
		
		
		
		
		if(checkip(bean))
		{
			
			response.setPassword("123456");
			response.setResultcode(0);
			response.setTracenumber("8357235");
						
			int qty = bean.getQty();
			
			if(qty >= 1){
				//email
				long tranid = this.insertTxLogs2(bean);	
				this.updateTX(tranid,MessageModels.NETWORK_ERROR_SESSION_MSG);
				
				this.writeExcel(bean.getUsername(), Integer.parseInt(bean.getDenom()), bean.getProdCode(), bean.getQty());
				
				if(updateEpins(bean)){
					
					if(checkEpins(bean)){
						
						
						
					}
			
				}

				
			}
			if(qty == 0){
				//mobile 
				long tranid = this.insertTxLogs(bean);				
				this.updateTX(tranid,MessageModels.NETWORK_ERROR_SESSION_MSG);
				
//				OutgoingSMSModel sms = null;
//				this.insertSmsSent(sms);
				
				if(getDenom(bean)){
					
					if(checkEpins(bean)){
						
						PreparedStatement ps = null;
						ResultSet rs = null;
						Connection conn = null;
						
						String telco = bean.getProdCode();
						int denom = Integer.parseInt(bean.getDenom());
						
						String sql = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status=? and telco_type=? and denom=?";
						
						try{
						       conn = EpinsConnectionManager.getConnection();
						       ps = conn.prepareStatement(sql);
						            
						       ps.setInt(1,0);
						       ps.setString(2,telco);
						       ps.setInt(3,denom);
						            
						       rs = ps.executeQuery();
						       
						      
								int i=1;
						       while(rs.next())
						       {
						            	
						    	System.out.println(rs.getString("epin"));
							        
						        
								try{
							
									
								    String dec = goDecryption(rs.getString("epin"));
									String[] decArray = this.getDecrypted(dec);
														

							           for(int j = 0;j<decArray.length;j++) {
							               System.out.println(decArray[j]);
							           }
									
								
									

									} catch ( Exception ex ) {
									    System.out.println(ex);

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
			       
			       logger.info("Request topup from: "+bean.getIpaddress());
			
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
		int amount = 0;
		String trantype = bean.getTrantype();
		String ipaddress = bean.getIpaddress();
		String appname = bean.getAppname();
		
		ps.setString(1, target);
        ps.setInt(2, amount);          
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

			
	String sql = "update epins set status = 6, transactionid = ? where status = ? and transactionid is null and denom = ? and telco_type = ? limit "+bean.getQty()+"";

		       
		try{
		       conn = EpinsConnectionManager.getConnection();
		       ps = conn.prepareStatement(sql);
		       
		       ps.setString(1, bean.getTransid());
		       ps.setInt(2, 0);
		       ps.setString(3, bean.getDenom());
		       ps.setString(4, bean.getProdCode());
		       
		       
		     
		            
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
	private void writeExcel(String username, int denom, String telco, int Qty) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
					
		String updateSQL = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status=? and telco_type=? and denom=?";
		
		String rptDate = "";
		Date date = new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd");
	    rptDate=sdf.format(date);	
		String filename="D:\\"+this.writePrefix(username, denom, telco, rptDate)+".xls";
		
		HSSFWorkbook hwb=new HSSFWorkbook();
		HSSFSheet sheet =  hwb.createSheet("new sheet");
		
		
		
		HSSFRow rowhead =   sheet.createRow((short)0);
			try{
			       conn = EpinsConnectionManager.getConnection();
			       ps = conn.prepareStatement(updateSQL);
			            
			       ps.setInt(1,0);
			       ps.setString(2,telco);
			       ps.setInt(3,denom);
			            
			       rs = ps.executeQuery();
			       
					int i=1;
			       while(rs.next())
			       {
			            	
			    	System.out.println(rs.getString("epin"));
				        
			        
					try{
				
//						while(rs.next())
//						{
					    String dec = goDecryption(rs.getString("epin"));
//						String dec = rs.getString("epin");
//					    String[] decArray = validatorService.getDecrypted(dec);
						String[] decArray = this.getDecrypted(dec);
						HSSFRow row=   sheet.createRow((short)i);					

				           for(int j = 0;j<decArray.length;j++) {
				               row.createCell(j).setCellValue(decArray[j]);
				           }
						
					
						

						} catch ( Exception ex ) {
						    System.out.println(ex);

						}
							i++;
			         }
					        sheet.autoSizeColumn(1);
							sheet.autoSizeColumn(2);
							sheet.autoSizeColumn(3);
							FileOutputStream fileOut =  new FileOutputStream(filename);
							
							hwb.write(fileOut);
							fileOut.close();
							System.out.println("Your excel file has been generated!");
							
							this.ZipFile(username, denom, telco, rptDate, filename);
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
//zipping file
    public boolean ZipFile(String username, int denom, String telco, String rptDate, String filename){
    	byte[] buffer = new byte[1024];
   	 
    	try{

    		FileOutputStream fos = new FileOutputStream("D:\\Epins.zip");
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry(this.writePrefix(username, denom, telco, rptDate)+".xls");
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream(filename);
    		
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}

    		in.close();
    		zos.closeEntry();

    		//remember close it
    		zos.close();

    		System.out.println("done zipping file");
    		
    		//mail service
    		ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-Mail.xml");
    		 
        	MailService mm = (MailService) context.getBean("mailService");
            mm.sendMail("hello", "Attached here is encrypted file. Use winzip or winrar for the attached file. ");
 		
            System.out.println("email sent!");
    		return true;
    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
		return false;
    	
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
	      
	        
	        logger.info("epin is invalid");
			return false;		
   
    }
    //sending sms
	
	public int insertSmsSent(Connection conn, OutgoingSMSModel sms) throws SQLException {
		if (sms == null) return 0;
		String sql = "Insert into SmsSent (SmsId, SmsDate, SmsGID, Sender, Recipient, SmsData, Tariff_Class, Service_Type, Status) Values (SmsId_Snt.nextVal, SysDate,?,?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			conn = SMSConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sms.getGatewayID() == null ? "" : sms.getGatewayID());
			stmt.setString(2, sms.getSender());
			stmt.setString(3, sms.getRecipient());
			stmt.setString(4, sms.getSmsData());
			stmt.setString(5, sms.getTariffClass());
			stmt.setString(6, sms.getServiceType());
			stmt.setInt(7, 0);
			
			if(stmt.executeUpdate()>0){
		    	   logger.info("SMS Sent has been inserted");
	            	return rs;
	            }
		}
		catch (SQLException sqle) {
			logger.info(sqle.getMessage());
			throw sqle;
		}
		finally {
			try {
				stmt.close();
			}
			catch (Exception e) {
			}
		}
		return rs;
	}
	
	public int insertSmsSent(OutgoingSMSModel sms) throws SQLException {
		
		logger.info("here");
		String sql = "Insert into SmsSent (SmsId, SmsDate, SmsGID, Sender, Recipient, SmsData, Tariff_Class, Service_Type, Status) Values (SmsId_Snt.nextVal, SysDate,?,?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		int row = 0;
		   
		   try{
					     conn = SMSConnectionManager.getConnection();
						 stmt = conn.prepareStatement(sql);
				 	 	 stmt.setString(1, sms.getGatewayID() == null ? "" : sms.getGatewayID());
						 stmt.setString(2, sms.getSender() );
						 stmt.setString(3, sms.getRecipient());
						 stmt.setString(4, sms.getSmsData());
						 stmt.setString(5, sms.getTariffClass());
						 stmt.setString(6, sms.getServiceType());
						 stmt.setInt(7, 0);
				
				
						 if(stmt.executeUpdate()>0){
					    	   logger.info("SMS Sent has been inserted");
				            	return row;
				            }
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            logger.info(ex.getMessage());
	            return row;
	        }
		   
		logger.info("row: "+row);   
		return row;
		   

	}
    

}
