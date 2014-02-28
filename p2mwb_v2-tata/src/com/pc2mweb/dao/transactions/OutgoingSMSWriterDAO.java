package com.pc2mweb.dao.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pc2mweb.model.OutgoingSMSModel;



public class OutgoingSMSWriterDAO extends JdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(OutgoingSMSWriterDAO.class);
	
	
	
	public int insertSmsSent(Connection conn, OutgoingSMSModel sms) throws SQLException {
		if (sms == null) return 0;
		String sql = "Insert into SmsSent (SmsId, SmsDate, SmsGID, Sender, Recipient, SmsData, Tariff_Class, Service_Type, Status) Values (SmsId_Snt.nextVal, SysDate,?,?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sms.getGatewayID() == null ? "" : sms.getGatewayID());
			stmt.setString(2, sms.getSender());
			stmt.setString(3, sms.getRecipient());
			stmt.setString(4, sms.getSmsData());
			stmt.setString(5, sms.getTariffClass());
			stmt.setString(6, sms.getServiceType());
			stmt.setInt(7, 0);
			rows = stmt.executeUpdate();
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
		return rows;
	}
	
	public int insertSmsSent(OutgoingSMSModel sms) {
		
		logger.info("here");
		String sql = "Insert into SmsSent (SmsId, SmsDate, SmsGID, Sender, Recipient, SmsData, Tariff_Class, Service_Type, Status) Values (SmsId_Snt.nextVal, SysDate,?,?, ?, ?, ?, ?, ?)";
		
		int row = 0;
		   
		   try{
			   
				 row = getJdbcTemplate().update(sql, new Object[] { 
						 sms.getGatewayID() == null ? "" : sms.getGatewayID(),sms.getSender(),sms.getRecipient(),
								sms.getSmsData(),sms.getTariffClass(),sms.getServiceType(),0		
				});

				
				if(row>0){
					logger.info("row: "+row);
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
