package com.payexchange.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.payexchange.ws.connection.SMSConnectionManager;
import com.payexchange.ws.dao.OutgoingSMSWriterDAO;
import com.payexchange.ws.utility.OutgoingSMSModel;

public class OutgoingSMSWriterDAO {

	private static final Logger logger = Logger.getLogger(OutgoingSMSWriterDAO.class);
	
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
	
	public void insertSmsSent(OutgoingSMSModel sms) throws SQLException {
		
		logger.info("here");
		String sql = "Insert into SmsSent (SmsDate, SmsGID, Sender, Recipient, SmsData, Tariff_Class, Service_Type, Status) Values (NOW(),?,?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		int row = 0;
		   
		   try{
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
				            	
				            }
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            logger.info(ex.getMessage());
	            
	        }
		   
		logger.info("row: "+row);   
		
		   

	}
}
