package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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
			   
			   ArrayList<TransactionReportsResultModel> partners = new ArrayList<TransactionReportsResultModel>();
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
					
					WalletTransferHistoryModel history = new WalletTransferHistoryModel();
					history.setTxid((String)(row.get("transid")));
					history.setWallettransferdate(dateAsString);
					history.setSourcewallet((String)(row.get("sender")));
					history.setDestinationwallet((String)(row.get("receiver")));
					history.setAmount((Double)(row.get("amount")));
					history.setBeginningbalancesourcewallet((Double)(row.get("sender_start_bal")));
					history.setEndingbalancesourcewallet((Double)(row.get("sender_end_bal")));
					history.setBeginningbalancedestionationwallet((Double)(row.get("receiver_start_bal")));
					history.setEndingbalancedestinationwallet((Double)(row.get("receiver_end_bal")));
					history.setTransfertype((String)(row.get("transfertype")));
					//	partnerlist.setPartnerid((String)(row.get("partnerid")));
					partner.add(history);	
					
				}
				session.setAttribute("reports", partners);
				return partner;
			   
			   
		   }else{
			   
			   
			   ArrayList<TransactionReportsResultModel> partners = new ArrayList<TransactionReportsResultModel>();
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
					
					WalletTransferHistoryModel history = new WalletTransferHistoryModel();
					history.setTxid((String)(row.get("transid")));
					history.setWallettransferdate(dateAsString);
					history.setSourcewallet((String)(row.get("sender")));
					history.setDestinationwallet((String)(row.get("receiver")));
					history.setAmount((Double)(row.get("amount")));
					history.setBeginningbalancesourcewallet((Double)(row.get("sender_start_bal")));
					history.setEndingbalancesourcewallet((Double)(row.get("sender_end_bal")));
					history.setBeginningbalancedestionationwallet((Double)(row.get("receiver_start_bal")));
					history.setEndingbalancedestinationwallet((Double)(row.get("receiver_end_bal")));
					history.setTransfertype((String)(row.get("transfertype")));
					//	partnerlist.setPartnerid((String)(row.get("partnerid")));
					partner.add(history);	
					
				}
				session.setAttribute("reports", partners);
				return partner;
			   
			   
		   }
	
		   	  
	 }
	
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
