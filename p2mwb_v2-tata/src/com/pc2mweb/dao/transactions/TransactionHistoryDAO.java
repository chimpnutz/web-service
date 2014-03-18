package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.UserBean;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;


public class TransactionHistoryDAO extends JdbcDaoSupport {
	
	private static final Logger logger = Logger.getLogger(TopupDAO.class);
	
	public List<TransactionHistoryModel>  getTransactionHistory(HttpSession session)
	   {
		   ArrayList<TransactionHistoryModel> userlogs = new ArrayList<TransactionHistoryModel>();
		
			ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
			TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		
			
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   
//		   strSQL.append("SELECT txdate, ");
//		   strSQL.append("mobilenumber, amount, status, tracenumber,transactionid ");
//		   strSQL.append("from user_transactions_logs  where username = ? ORDER BY txdate desc");
		   
		   strSQL.append("SELECT a.transactionid,a.transactiondate,a.topuptrace,a.amount,a.status, b.targetmsisdn  ");
		   strSQL.append("FROM transactions a INNER JOIN mobile_transaction b ON a.transactionid = b.transactionid ");
		   strSQL.append("WHERE a.agentid = ? AND a.partnerid = ?   order by a.transactiondate desc");
		  
		   userlogs.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("AID"),session.getAttribute("PID"));
			for (Map row : rows) {

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
				String dateAsString = simpleDateFormat.format(row.get("transactiondate"));
				
				java.util.Date parsedDate;
				
				try {
					parsedDate = simpleDateFormat.parse(dateAsString);
					
					TransactionHistoryModel logs = new TransactionHistoryModel();
					logs.setTransactionid((String)(row.get("transactionid")+""));
					//logs.setTxdate((String)(dateAsString));
					
					logs.setTxdate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
					logs.setTracenumber((String)(row.get("topuptrace")));
					logs.setMobilenumber((String)(row.get("targetmsisdn")));
					logs.setAmount((Float)(row.get("amount")));
					
					Float amount = ( Float)(row.get("amount"));
					
					Float commission = dao.getCommission(amount);
					
					if(commission != null){
						
						Float divisor = (float) 100;
						
						Float decreamount = amount * (commission/divisor);
									
						Float deducted = amount - decreamount;
						
						logs.setAmountdeducted(deducted);
						
						logs.setRetainedearnings(decreamount);
						
					}
					

					
					
					String stat = row.get("status").toString();
					
					if(stat.equalsIgnoreCase("GCASH_CASHIN_SUCCESS")){
						
						logs.setStatus("Gcash Cashin Success");
						userlogs.add(logs);	
						
					}else if(stat.equalsIgnoreCase("GCASH CASHIN SUCCESS")){
						
						logs.setStatus("Gcash Cashin Success");
						userlogs.add(logs);	
						
					}else if(stat.equalsIgnoreCase("AMAX_TOPUP_SUCCESSFUL"))  
					{
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
						
					}
					else if(stat.equalsIgnoreCase("AMAX TOPUP SUCCESSFUL"))  
					{
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.equalsIgnoreCase("Topup Successful!")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.contains("00")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.equalsIgnoreCase("0")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}
				
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		
			//session.setAttribute("consumerreports", userlogs);
			return userlogs;
		   	  
	   }
	
	public List<RetailerTransactionHistoryModel>  getRetailerTransactionHistory(HttpSession session)
	   {
		   ArrayList<RetailerTransactionHistoryModel> userlogs = new ArrayList<RetailerTransactionHistoryModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   
//		   strSQL.append("SELECT transferdate, trace, ");
//		   strSQL.append("msisdn, transactionid, amount,status,username ");
//		   strSQL.append("from retailer_sim_transfer_logs ");
//		   strSQL.append("where username = ? ORDER BY transferdate desc ");
//			tatanong ko pa kay mam..		   
		   
		   strSQL.append("SELECT a.transactionid,a.transactiondate,a.topuptrace,a.amount,a.status,b.targetmsisdn  ");
		   strSQL.append("FROM transactions a INNER JOIN mobile_transaction b ON a.transactionid = b.transactionid ");
		   strSQL.append("WHERE a.agentid = ? and a.partnerid = ? and a.productcode = ? order by a.transactiondate desc");
		  
		   
		   userlogs.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("AID"),session.getAttribute("PID"),"TRANSFER");
			
			for (Map row : rows) {
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
				String dateAsString = simpleDateFormat.format(row.get("transactiondate"));
				
				java.util.Date parsedDate;
			
				try {
					parsedDate = simpleDateFormat.parse(dateAsString);
					
					RetailerTransactionHistoryModel logs = new RetailerTransactionHistoryModel();
//					logs.setTransactionid((String)(row.get("transactionid")));
//					logs.setTrace((String)(row.get("trace")));	
//					logs.setTransferdate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
//					logs.setMsisdn((String)(row.get("msisdn")));
//					logs.setAmount((Float)(row.get("amount")));
//					logs.setUsername((String)(row.get("username	")));
					
					logs.setTransactionid((String)(row.get("transactionid")+""));
					//logs.setTxdate((String)(dateAsString));
					logs.setTransferdate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
					logs.setTrace((String)(row.get("topuptrace")));
					logs.setMsisdn((String)(row.get("targetmsisdn")));
					logs.setAmount((Float)(row.get("amount")));
					
					String stat = row.get("status").toString();
					
					if(stat.equalsIgnoreCase("GCASH_CASHIN_SUCCESS")){
						
						logs.setStatus("Gcash Cashin Success");
						userlogs.add(logs);	
						
					}else if(stat.equalsIgnoreCase("GCASH CASHIN SUCCESS")){
						
						logs.setStatus("Gcash Cashin Success");
						userlogs.add(logs);	
						
					}else if(stat.equalsIgnoreCase("AMAX_TOPUP_SUCCESSFUL"))  
					{
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else if(stat.equalsIgnoreCase("AMAX TOPUP SUCCESSFUL"))  
					{
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.equalsIgnoreCase("Topup Successful!")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.contains("00")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}else  if(stat.equalsIgnoreCase("0")){
						logs.setStatus("Amax Topup Successful");
						userlogs.add(logs);	
					}	
					
					
				
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
			

			return userlogs;
		   	  
	   }
	
}
