package com.payexchange.ws.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.payexchange.ws.beans.DetailsBean;
import com.payexchange.ws.beans.EpinsUploadResponse;
import com.payexchange.ws.connection.ConnectionManager;
import com.payexchange.ws.connection.ConnectionManager2;
import com.payexchange.ws.utility.MailService;
import com.payexchange.ws.utility.Utility;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
			response.setTracenumber("12345678");
			
			if(checkDenom(bean) || checkTelco(bean)){
				
			}
			
		}	  
	  logger.info("Ip Address: "+bean.getIpaddress()+" don't have permission to do transaction.");
	  return response;
			  
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
	
	public static void main(String[] args) throws MessagingException {
	  System.out.println("--------Start---------");
		  ApplicationContext context = new ClassPathXmlApplicationContext("springapp-servlet.xml");

		  MailService mm = (MailService) context.getBean("mailservice");
		  mm.sendMail("", "This is text content","","","","");

		 }
	
	public boolean checkDenom(DetailsBean bean) {
		
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String updateSQL = "SELECT DENOM from epins where DENOM =? ";
        try{
        	   conn = ConnectionManager2.getConnection();
		       ps = conn.prepareStatement(updateSQL);
		            
		       ps.setString(1,bean.getDenom());
		      
		            
		       rs = ps.executeQuery();
		        if(rs.next()){
		        	
			           logger.info("Denom is valid");		
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
	      
	        
	        logger.info("Denom or invalid");		
	        return false;
    }
	
	private boolean checkTelco(DetailsBean bean) {
			 
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String updateSQL = "SELECT TELCO_TYPE from epins where TELCO_TYPE =? ";
        try{
        	   conn = ConnectionManager2.getConnection();
		       ps = conn.prepareStatement(updateSQL);
		            
		       ps.setString(1,bean.getProdCode());
		      
		            
		       rs = ps.executeQuery();
		        if(rs.next()){
		        	
			           logger.info(" telco is valid");		
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
	      
	        
	        logger.info("telco or invalid");		
	        return false;
	}
	
	//private boolean checkQty(DetailsBean bean) {
		// TODO Auto-generated method stub
		//return false;
	//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*	  public class getTransactions {

		public void emailEPINS(HttpServletRequest request) {
			DetailsBean detailsbean = new DetailsBean();
			
			String epinFile = "";
	        String zipFile = "";
	        String rptDate = "";
	        Date date = new Date();
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMMddHHmmss");
	        rptDate=sdf.format(date);
      
	       String checkSQL = "Select ipaddress, appname,txtype from permissions where ipaddress = ? and appname = ? and txtype = ?";
	              
	     try{   
	        PreparedStatement ps2 = null;
			ResultSet rs2 = null;
			Connection conn2 = null;
			
			
	        String denom = request.getParameter("denom");
			String email = request.getParameter("email");
			String qty = request.getParameter("qty");
			String telco = request.getParameter("telco");
			String password = request.getParameter("password");
			
			conn2 = ConnectionManager.getConnection();
            ps2 = conn2.prepareStatement(checkSQL);
			
	        return;
	     }  
	     catch(Exception ex){
	            ex.printStackTrace();
	            
	     }dummy values for now   
		}
	}*/
		
}
