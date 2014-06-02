package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.WalletTransferHistoryModel;



public class WalletTransferHistoryDAO extends JdbcDaoSupport {
	
	public Map fillBox(HttpSession usersession){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT partnername from partners where parent_partnerid = (select partner from partners where partnerid = ?)");
		   //strSQL.append("SELECT  branchid  from branches where paymenttype = 'PREPAID' AND partnerid = ?");
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"));
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("partnername")), (String)row.get("partnername"));
				//prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
		
			}
			
			return prefix;
			
	}
	
	
	public List<WalletTransferHistoryModel>  getbranchidTrans(String bid,HttpSession session)
	   {
		
		
		   
		   
		   if(bid.equalsIgnoreCase("NONE")){
			   
			 //  ArrayList<TransactionReportsResultModel> partners = new ArrayList<TransactionReportsResultModel>();
			   ArrayList<WalletTransferHistoryModel> partner = new ArrayList<WalletTransferHistoryModel>();
			   
			   StringBuilder strSQL = new StringBuilder();
		
			   String pid = this.getpid(bid);

			   partner.clear();
			   

			   strSQL.append("SELECT   DISTINCT transid,transdate, sender,receiver,amount,sender_start_bal,");
			   strSQL.append("sender_end_bal,receiver_start_bal,receiver_end_bal ,transfertype FROM  wallet_transfer w ");
			   strSQL.append("INNER JOIN partners b on b.partnerid = w.sender or b.partnerid = w.receiver ");
			   strSQL.append("WHERE b.partnerid = ?    order by  transdate desc");
			   
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID").toString());
	  
				for (Map row : rows) { 
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String dateAsString = simpleDateFormat.format(row.get("transdate"));
					
					java.util.Date parsedDate;
					
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						
						WalletTransferHistoryModel history = new WalletTransferHistoryModel();
						history.setTransid((String)(row.get("transid")));
						history.setTransdate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						history.setSender((String)(row.get("sender")));
						history.setReceiver((String)(row.get("receiver")));
						history.setAmount((Double)(row.get("amount")));
						history.setSender_start_bal((Double)(row.get("sender_start_bal")));
						history.setSender_end_bal((Double)(row.get("sender_end_bal")));
						history.setReceiver_start_bal((Double)(row.get("receiver_start_bal")));
						history.setReceiver_end_bal((Double)(row.get("receiver_end_bal")));
						history.setTransfertype((String)(row.get("transfertype")));
						//	partnerlist.setPartnerid((String)(row.get("partnerid")));
						partner.add(history);	
						
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		
					

					
				}
				session.setAttribute("reports", partner);
				return partner;
			   
			   
		   }else{
			   
			   
			   //ArrayList<TransactionReportsResultModel> partners = new ArrayList<TransactionReportsResultModel>();
			   ArrayList<WalletTransferHistoryModel> partner = new ArrayList<WalletTransferHistoryModel>();
			   
			   StringBuilder strSQL = new StringBuilder();
		
			   String pid = this.getpid(bid);

			   partner.clear();
			   

			   strSQL.append("SELECT   DISTINCT transid,transdate, sender,receiver,amount,sender_start_bal,");
			   strSQL.append("sender_end_bal,receiver_start_bal,receiver_end_bal ,transfertype FROM  wallet_transfer w ");
			   strSQL.append("INNER JOIN partners b on b.partnerid = w.sender or b.partnerid = w.receiver ");
			   strSQL.append("WHERE w.sender = ?  or w.receiver = ?  and  b.partnerid = ?    order by  transdate desc");
			   
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid,pid, session.getAttribute("PID").toString());
	  
				for (Map row : rows) { 
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String dateAsString = simpleDateFormat.format(row.get("transdate"));
					
					java.util.Date parsedDate;
					
					try {
						parsedDate = simpleDateFormat.parse(dateAsString);
						WalletTransferHistoryModel history = new WalletTransferHistoryModel();
						history.setTransid((String)(row.get("transid")));
						history.setTransdate((java.sql.Timestamp)(new java.sql.Timestamp(parsedDate.getTime())));
						history.setSender((String)(row.get("sender")));
						history.setReceiver((String)(row.get("receiver")));
						history.setAmount((Double)(row.get("amount")));
						history.setSender_start_bal((Double)(row.get("sender_start_bal")));
						history.setSender_end_bal((Double)(row.get("sender_end_bal")));
						history.setReceiver_start_bal((Double)(row.get("receiver_start_bal")));
						history.setReceiver_end_bal((Double)(row.get("receiver_end_bal")));
						history.setTransfertype((String)(row.get("transfertype")));
						//	partnerlist.setPartnerid((String)(row.get("partnerid")));
						partner.add(history);	
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		
					
				}
				session.setAttribute("reports", partner);
				return partner;
			   
			   
		   }
	
		   	  
	 }
	
//	public void writeExcelWallet(String bid,HttpSession session){
//		
//		String rptDate = "";
//		Date date = new Date();
//	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd");
//	    rptDate=sdf.format(date);
//			
//		try{ 
//		      HSSFWorkbook hwb=new HSSFWorkbook();
//		      HSSFSheet sheet =  hwb.createSheet("Wallet_History_"+rptDate);
//		     
//		      
//		      HSSFRow rowhead=   sheet.createRow((short)0);
//				rowhead.createCell((short) 0).setCellValue("Trans ID");
//				rowhead.createCell((short) 1).setCellValue("Transdate");
//				rowhead.createCell((short) 2).setCellValue("Source ID");
//				rowhead.createCell((short) 3).setCellValue("Beginning Balance");
//				rowhead.createCell((short) 4).setCellValue("amount");
//				rowhead.createCell((short) 5).setCellValue("Ending Balance");
//				rowhead.createCell((short) 6).setCellValue("Recipient ID");
//				rowhead.createCell((short) 7).setCellValue("Beginning Balance");
//				rowhead.createCell((short) 8).setCellValue("Ending Balance");
//		
//			   StringBuilder strSQL = new StringBuilder();
//		
//			   String pid = this.getpid(bid);
//	
//			   strSQL.append("SELECT DISTINCT transid,transdate, sender,receiver,amount,sender_start_bal,");
//			   strSQL.append("sender_end_bal,receiver_start_bal,receiver_end_bal ,transfertype FROM  wallet_transfer w ");
//			   strSQL.append("INNER JOIN partners b on b.partnerid = w.sender or b.partnerid = w.receiver ");
//			   strSQL.append("WHERE b.partnerid = ? order by  transdate desc");
//			   
//			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(strSQL.toString(),session.getAttribute("PID").toString());
//			   
//			   int i=1;
//				while(rs.next()){
//				HSSFRow row=   sheet.createRow((short)i);
//				row.createCell((short) 0).setCellValue(rs.getString("transid"));
//				row.createCell((short) 1).setCellValue(rs.getString("transdate"));
//				row.createCell((short) 2).setCellValue(rs.getString("sender"));
//				row.createCell((short) 3).setCellValue(rs.getString("sender_start_bal"));
//				row.createCell((short) 4).setCellValue(rs.getString("amount"));
//				row.createCell((short) 5).setCellValue(rs.getString("sender_end_bal"));
//				row.createCell((short) 6).setCellValue(rs.getString("receiver"));
//				row.createCell((short) 7).setCellValue(rs.getString("receiver_start_bal"));
//				row.createCell((short) 8).setCellValue(rs.getString("receiver_end_bal"));	
//				sheet.autoSizeColumn(i);
//				i++;		
//				}
//				
//		}catch ( Exception ex ) {
//		    System.out.println(ex);
//		}
//		
//	}
	

	
	public String getpid(String pid){
		
		   StringBuilder strSQL = new StringBuilder();

		   String branchid = null;
		   
		   strSQL.append("SELECT partnerid from partners where partnername = ?");
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid);
			
			for (Map row : rows) {
				
	
				branchid = (String)(row.get("partnerid"));
				
			}
			
			return branchid;
			
	}


}
