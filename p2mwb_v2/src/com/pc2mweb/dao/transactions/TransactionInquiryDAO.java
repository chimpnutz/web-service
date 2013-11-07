package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;


public class TransactionInquiryDAO extends JdbcDaoSupport {

	public Map fillBox(String pid){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT * from branches where paymenttype = 'PREPAID' and partnerid = ?");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("paymenttype")), (String)row.get("paymenttype"));
		
			}
			
			return prefix;
			
	}
	
	
	public List<TransactionReportsResultModel>  getTransactions(TransactionInquiryModel txModel,HttpSession session)
	   {   
		   ArrayList<TransactionReportsResultModel> partners = new ArrayList<TransactionReportsResultModel>();
		
		   StringBuilder strSQL = new StringBuilder();
		   

		   String msisdn = txModel.getMsisdn();
		   String txdate = txModel.getTransactiondate();
		   String user = (String) session.getAttribute("PID");
		   partners.clear();


		   
		   if(!msisdn.equals("")  && txdate.equals("")){
			   
			   strSQL.append("SELECT DISTINCT a.transactiondate ,b.targetmsisdn,a.amount,a.status,a.agentid,a.responsemsg,a.productcode,c.username,e.partnerid,e.partnername ");
			   strSQL.append(",g.status AS retailerlogs, h.status AS userlogs  ");
			   strSQL.append("FROM 	transactions a  ");
			   strSQL.append("INNER JOIN mobile_transaction b  ");
			   strSQL.append("ON a.transactionid = b.transactionid ");
			   strSQL.append("INNER JOIN agents c ");
			   strSQL.append("ON a.agentid = c.agentid ");
			   strSQL.append("INNER JOIN agents_partners d ");
			   strSQL.append("ON c.agentid = d.agentid ");
			   strSQL.append("INNER JOIN partners e ");
			   strSQL.append("on  d.partnerid = e.partner "); 
			   strSQL.append("INNER JOIN partners f ");
			   strSQL.append("on  e.partner = f.parent_partnerid "); 
			   strSQL.append("LEFT  JOIN retailer_sim_transfer_logs g  ");  
			   strSQL.append("on a.partnertxid = g.transactionid	 ");
			   strSQL.append("LEFT  JOIN user_transactions_logs h "); 
			   strSQL.append("on a.partnertxid = h.transactionid ");
			   strSQL.append("WHERE b.targetmsisdn = ? and f.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) OR f.partnerid = ? ");
//			   strSQL.append(" AND b.targetmsisdn = ? ");
//			   strSQL.append("ORDER BY a.TRANSACTIONDATE desc");
		     
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),msisdn,user,user);
				
			   
			   for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
					String dateAsString = simpleDateFormat.format(row.get("transactiondate"));
					
					java.util.Date parsedDate;
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						
						TransactionReportsResultModel reports = new TransactionReportsResultModel();
						
//						String ret = (String)row.get("retailerlogs");
//						String con = (String)row.get("userlogs");
//						String type = null;
//						
//						if(ret == null && con != null ){
//							type = "To Consumer Sim";
//						}else if (ret != null && con == null ){
//							type = "To Retailer Sim";
//						}else{
//							type = "Not Available";
//						}
						
						String prodcode = (String)row.get("productcode");
						
						if(prodcode.equalsIgnoreCase("transfer")){
							
							reports.setType("To Retailer Sim");
							
						}else
						{
							reports.setType("To Consumer Sim");
						}
						
						
						String respmsg = (String)row.get("responsemsg");
						String stat = (String)row.get("status");
			
						
						if(respmsg == null || respmsg.isEmpty()){
							reports.setStatus((String)(row.get("status")));
						}else{
							reports.setStatus((String)(row.get("responsemsg")));
						}
					
					
						
				
						reports.setTransactiondate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						reports.setTargetmsisdn((String)(row.get("targetmsisdn")));
						reports.setAmount((Float)(row.get("amount")));
						//reports.setStatus((String)(row.get("status")));
						reports.setPartnername((String)(row.get("partnername")));
						reports.setPartnerid((String)(row.get("partnerid")));
						reports.setUsername((String)(row.get("username")));
						reports.setRetailerlogs((String)(row.get("retailerlogs")));
						reports.setUserlogs((String)(row.get("userlogs")));
					//	reports.setType(type);
						partners.add(reports);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				return partners;
			   
			
		   }else if(msisdn.equals("")  && !txdate.equals("")){
			   

			   strSQL.append("SELECT DISTINCT a.transactiondate ,b.targetmsisdn,a.amount,a.status,a.agentid,a.responsemsg,a.productcode,c.username,e.partnerid,e.partnername ");
			   strSQL.append(",g.status AS retailerlogs, h.status AS userlogs  ");
			   strSQL.append("FROM 	transactions a  ");
			   strSQL.append("INNER JOIN mobile_transaction b  ");
			   strSQL.append("ON a.transactionid = b.transactionid ");
			   strSQL.append("INNER JOIN agents c ");
			   strSQL.append("ON a.agentid = c.agentid ");
			   strSQL.append("INNER JOIN agents_partners d ");
			   strSQL.append("ON c.agentid = d.agentid ");
			   strSQL.append("INNER JOIN partners e ");
			   strSQL.append("on  d.partnerid = e.partner "); 
			   strSQL.append("INNER JOIN partners f ");
			   strSQL.append("on  e.partner = f.parent_partnerid "); 
			   strSQL.append("LEFT  JOIN retailer_sim_transfer_logs g  ");  
			   strSQL.append("on a.partnertxid = g.transactionid	 ");
			   strSQL.append("LEFT  JOIN user_transactions_logs h "); 
			   strSQL.append("on a.partnertxid = h.transactionid ");
			   strSQL.append("WHERE   ");
			   strSQL.append("   DATE(a.transactiondate ) = DATE(STR_TO_DATE(?, '%m/%d/%Y')) and f.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) OR f.partnerid = ? ");
			   strSQL.append("ORDER BY partnerid desc ");
			   
		
	
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),txdate,user,user);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
					String dateAsString = simpleDateFormat.format(row.get("transactiondate"));
				
					java.util.Date parsedDate;
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						
						TransactionReportsResultModel reports = new TransactionReportsResultModel();
						
//						String ret = (String)row.get("retailerlogs");
//						String con = (String)row.get("userlogs");
//						String type = null;
//						
//						if(ret == null && con != null ){
//							type = "To Consumer Sim";
//						}else if (ret != null && con == null ){
//							type = "To Retailer Sim";
//						}else{
//							type = "Not Available";
//						}
						String prodcode = (String)row.get("productcode");
						
						if(prodcode.equalsIgnoreCase("transfer")){
							
							reports.setType("To Retailer Sim");
							
						}else
						{
							reports.setType("To Consumer Sim");
						}
						
						String respmsg = (String)row.get("responsemsg");
						String stat = (String)row.get("status");
			
						
						if(respmsg == null || respmsg.isEmpty()){
							reports.setStatus((String)(row.get("status")));
						}else{
							reports.setStatus((String)(row.get("responsemsg")));
						}
					
						
				
			
						reports.setTransactiondate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						reports.setTargetmsisdn((String)(row.get("targetmsisdn")));
						reports.setAmount((Float)(row.get("amount")));
						//reports.setStatus((String)(row.get("status")));
						reports.setPartnername((String)(row.get("partnername")));
						reports.setPartnerid((String)(row.get("partnerid")));
						reports.setUsername((String)(row.get("username")));
						reports.setRetailerlogs((String)(row.get("retailerlogs")));
						reports.setUserlogs((String)(row.get("userlogs")));
						//reports.setType(type);
						partners.add(reports);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				return partners;
			   
			
		   }else if(!msisdn.equals("")  && !txdate.equals("")){
			   

			   strSQL.append("SELECT DISTINCT a.transactiondate ,b.targetmsisdn,a.amount,a.status,a.agentid,a.responsemsg,a.productcode,c.username,e.partnerid,e.partnername ");
			   strSQL.append(",g.status AS retailerlogs, h.status AS userlogs  ");
			   strSQL.append("FROM 	transactions a  ");
			   strSQL.append("INNER JOIN mobile_transaction b  ");
			   strSQL.append("ON a.transactionid = b.transactionid ");
			   strSQL.append("INNER JOIN agents c ");
			   strSQL.append("ON a.agentid = c.agentid ");
			   strSQL.append("INNER JOIN agents_partners d ");
			   strSQL.append("ON c.agentid = d.agentid ");
			   strSQL.append("INNER JOIN partners e ");
			   strSQL.append("on  d.partnerid = e.partner "); 
			   strSQL.append("INNER JOIN partners f ");
			   strSQL.append("on  e.partner = f.parent_partnerid "); 
			   strSQL.append("LEFT  JOIN retailer_sim_transfer_logs g  ");  
			   strSQL.append("on a.partnertxid = g.transactionid	 ");
			   strSQL.append("LEFT  JOIN user_transactions_logs h "); 
			   strSQL.append("on a.partnertxid = h.transactionid ");
			   strSQL.append("WHERE  ");
			   strSQL.append("  DATE(a.transactiondate ) = DATE(STR_TO_DATE(?, '%m/%d/%Y'))  AND b.targetmsisdn = ? and f.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) OR f.partnerid = ? ");
			   strSQL.append("ORDER BY a.TRANSACTIONDATE ");
			   
		
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),txdate,msisdn,user,user);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
					String dateAsString = simpleDateFormat.format(row.get("transactiondate"));
					
					java.util.Date parsedDate;
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						
						TransactionReportsResultModel reports = new TransactionReportsResultModel();
						
//						String ret = (String)row.get("retailerlogs");
//						String con = (String)row.get("userlogs");
//						String type = null;
//						
//						if(ret == null && con != null ){
//							type = "To Consumer Sim";
//						}else if (ret != null && con == null ){
//							type = "To Retailer Sim";
//						}else{
//							type = "Not Available";
//						}
						
						String prodcode = (String)row.get("productcode");
						
						if(prodcode.equalsIgnoreCase("transfer")){
							
							reports.setType("To Retailer Sim");
							
						}else
						{
							reports.setType("To Consumer Sim");
						}						
				
						String respmsg = (String)row.get("responsemsg");
						String stat = (String)row.get("status");
			
						
						if(respmsg == null || respmsg.isEmpty()){
							reports.setStatus((String)(row.get("status")));
						}else{
							reports.setStatus((String)(row.get("responsemsg")));
						}
						

						reports.setTransactiondate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						reports.setTargetmsisdn((String)(row.get("targetmsisdn")));
						reports.setAmount((Float)(row.get("amount")));
						//reports.setStatus((String)(row.get("status")));
						reports.setPartnername((String)(row.get("partnername")));
						reports.setPartnerid((String)(row.get("partnerid")));
						reports.setUsername((String)(row.get("username")));
						reports.setRetailerlogs((String)(row.get("retailerlogs")));
						reports.setUserlogs((String)(row.get("userlogs")));
						//reports.setType(type);
						partners.add(reports);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				return partners;
			   
			
		   }
		   
		return partners;
  	  
	   }
	
	    public boolean isNumber( String tx )  
	    {  
	       try  
	       {  
	          Integer.parseInt( tx );  
	          return true;  
	       }  
	       catch( Exception e )  
	       {  
	          return false;  
	       }  
	    }  

}
