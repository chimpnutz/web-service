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
import com.payexchange.ws.utility.MailService;
import com.payexchange.ws.utility.MessageModels;
import com.payexchange.ws.utility.Utility;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
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

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class HandleEpins  {
	
	private static final Logger logger = Logger.getLogger(HandleEpins.class);

	public EpinsUploadResponse reqUpload(DetailsBean bean) {
		
		
		EpinsUploadResponse response = new EpinsUploadResponse();
		
		
		
		
		if(checkip(bean))
		{
			response.setPassword("123456");
			response.setResultcode(0);
			response.setTracenumber("8357235");
			
			long tranid = this.insertTxLogs(bean);
			this.updateTX(tranid,MessageModels.NETWORK_ERROR_SESSION_MSG);
			
			int qty = bean.getQty();
			
			if(qty >= 1){
					
				this.writeExcel(bean.getUsername(), Integer.parseInt(bean.getDenom()), bean.getProdCode(), bean.getQty());
			
					if(checkEpins(bean)){
						
			
					}

				
			}
			if(qty == 0){
				//mobile 
				System.out.println("oyeah");
			}
			           
				       					
//			if(updateEpins(bean)){
//				
//			}
				

			
		}	  
	
	  return response;
			  
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
			
			String addSQL = "insert into transactions (transactionid,transactiondate,targetmsisdn,amount,transactiontype,ipaddress,appname)" +
	        		"values (now(),?,?,?,?,?,?)";
	        try{
	        	conn = ConnectionManager.getConnection();
	            ps = conn.prepareStatement(addSQL,PreparedStatement.RETURN_GENERATED_KEYS);
	            
	            
	            
	    		String target = bean.getTarget();
	    		int amount = Integer.parseInt(bean.getAmount());
	    		String trantype = bean.getTrantype();
	    		String ipaddress = bean.getIpaddress();
	    		String appname = bean.getAppname();
	    		String transid = bean.getTransid();
	    		
	            ps.setString(1,target);
	            ps.setInt(2, amount);
	            ps.setString(3, trantype);
	            ps.setString(4, ipaddress);
	            ps.setString(5, appname);
	            ps.setString(6, transid);     
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

//	String transid = bean.getTransid();
//	String telco = bean.getTrantype();
//	String denom = bean.getDenom();
//	int qty = bean.getQty();
				
	String sql = "UPDATE epins set staus='6', transactionid =? WHERE status = '0' AND transactionid is null AND denom =? AND telco_type =? LIMIT"+bean.getQty()+"";
		
		       
		try{
		       conn = EpinsConnectionManager.getConnection();
		       ps = conn.prepareStatement(sql);
		            
		       ps.setString(1, bean.getTransid());
		       ps.setString(2, bean.getTrantype());
		       ps.setString(3, bean.getDenom());
		            
		       if(ps.executeUpdate()>0){
		    	   logger.info("Epins Updated");
	            	return true;
	            }
		       
//		       logger.info("Updating Epins:");
//		
//		       if(rs.next()){
//		            	
//		           		
//		           return true;
//		         }
		
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
//public boolean updateEpins(DetailsBean bean,long tranid,String errorState,String trace){
//	
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	Connection conn = null;
//	
//	int i = 0;
//	long id = 0;
//	String transid = bean.getTransid();
//	String telco = bean.getTrantype();
//	String denom = bean.getDenom();
//	int qty = bean.getQty();
//
//	String sql = "UPDATE epins set staus='6', transactionid ='"+transid+"'WHERE status = '0' AND transactionid is null AND denom ='"+denom+"'AND telco_type ='"+telco+"' LIMIT'"+qty+"'";
//	
//		try{
//			conn = EpinsConnectionManager.getConnection();
//			
//			id = tranid;
//			ps = conn.prepareStatement(sql);
//			
//			ps.setString(1, errorState);
//			ps.setString(2, trace);
//			ps.setLong(3, id);
//			
//			if(ps.executeUpdate()>0){
//				return true;
//			}
//			
//		  }catch(Exception ex){
//	            ex.printStackTrace();
//	            
//	            return false;
//		  }
//		finally{
//        	
//        	Utility.closeQuietly(rs);
//        	Utility.closeQuietly(ps);
//        	Utility.closeQuietly(conn);
//        	
//        }
//        return false;
//	
//}

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

	
	
	
//	private String checkqty(DetailsBean bean) {
//		
//		
//		if(bean.getQty() != null){
//			
//			 logger.info("Quantity is Valid "+bean.getQty());
//			 return null;
//		}
//		
//		logger.info("invalid quantity");
//		return null;
//		
//		
//			
//	}
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
							String password = "l0adcentral";
							this.ZipFile(username, denom, telco, rptDate, filename, password);
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

    public String[] getDecrypted(String dec) {

        String[] xx = StringUtils.split(dec, " ");
        return xx;
    }
    
    private String goDecryption(String var) {
        Encrypter enc = new Encrypter();
        String xD = enc.decryptBase64String(var);
        return xD;
    }
    public boolean ZipFile(String username, int denom, String telco, String rptDate, String filename, String password){
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
    		
    		return true;
    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
		return false;
    	
    }
    
    public boolean checkEpins(DetailsBean bean){
    	
    	PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
//		String transid = bean.getTransid();
//		String telco = bean.getTrantype();
//		String denom = bean.getDenom();
		
		String checkSQL = "Select id,epin,uploaded_by,date_uploaded from epins where transactionid =? and status=6 and telco_type=? and denom=?";
        try{
        	   conn = EpinsConnectionManager.getConnection();
		       ps = conn.prepareStatement(checkSQL);
		       
		       ps.setString(1,bean.getTransid());
		       ps.setString(2,bean.getDenom());
		       ps.setString(3,bean.getTrantype());
		            
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
    

}
