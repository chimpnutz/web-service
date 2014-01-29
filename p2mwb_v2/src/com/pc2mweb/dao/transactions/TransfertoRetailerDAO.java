package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.sql.Connection;
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

import com.paysetter.amax.TransferStocksConstant;
import com.paysetter.commons.pctomobile.P2MAmaxRequest;
import com.pc2mweb.beans.RetailerSimBean;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;


public class TransfertoRetailerDAO extends JdbcDaoSupport {
	
	private static final Logger logger = Logger.getLogger(TransfertoRetailerDAO.class);
	
	public List<WalletTransferModel>  getInfo(HttpSession session, String bid)
	   {	
		
		   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();
  
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT a.partnerid, a.partnername, a.wallet as pwallet, a.decrementation, b.branchid,b.wallet as bwallet, ");
		   strSQL.append("c.msisdn ");
		   strSQL.append("FROM partners a, branches b ,retailer_sim_table c, agents d ");
		   strSQL.append("WHERE a.partnerid = b.partnerid and a.partnerid = ? ");
		   strSQL.append("AND b.branchid = d.branchid AND c.agentid = d.agentid AND d.branchid = ? AND c.status = 1 ");
		   
		   partner.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID"), bid);
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("pwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("bwallet")));
				partnerlist.setMsisdn((String)(row.get("msisdn")));
				partner.add(partnerlist);			
			}
			return partner;
		   	  
	   }
	
	public List<WalletTransferModel>  getInfo(HttpSession session)
	   {	
		
		
		 
		 if(session.getAttribute("USERLEVEL").toString().equalsIgnoreCase("manager"))
		 {
			 
			   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();

			   StringBuilder strSQL = new StringBuilder();
			   

			   
			   strSQL.append("SELECT a.msisdn,a.agentid ,c.partnerid,c.partnername FROM retailer_sim_table a ");
			   strSQL.append("INNER JOIN agents_partners b ");
			   strSQL.append("ON a.agentid = b.agentid  INNER JOIN partners c ON b.partnerid = c.partner ");
			   strSQL.append("WHERE parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? ) OR c.partnerid = ?");
			   
			   partner.clear();
			   logger.info("get retailer sim info " + session.getAttribute("PID") + " " + strSQL.toString());
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID"), session.getAttribute("PID"));
				for (Map row : rows) {
					
					WalletTransferModel partnerlist = new WalletTransferModel();
					partnerlist.setPartnerid((String)(row.get("partnerid")));
					partnerlist.setPartnername((String)(row.get("partnername")));
					partnerlist.setMsisdn((String)(row.get("msisdn")));
					logger.info("retailer " + partnerlist.getPartnerid() + " " + partnerlist.getPartnername() + " " + partnerlist.getMsisdn());
					partner.add(partnerlist);			
				}
				return partner;
			 
			 
		 }else{
			 
			 
			   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();

			   StringBuilder strSQL = new StringBuilder();
			   

			   
			   strSQL.append("SELECT a.msisdn,a.agentid ,c.partnerid,c.partnername FROM retailer_sim_table a ");
			   strSQL.append("INNER JOIN agents_partners b ");
			   strSQL.append("ON a.agentid = b.agentid  INNER JOIN partners c ON b.partnerid = c.partner ");
			   strSQL.append("WHERE  c.partnerid = ?");
			   
			   partner.clear();
			   logger.info("get retailer sim info " + session.getAttribute("PID") + " " + strSQL.toString());
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				for (Map row : rows) {
					
					WalletTransferModel partnerlist = new WalletTransferModel();
					partnerlist.setPartnerid((String)(row.get("partnerid")));
					partnerlist.setPartnername((String)(row.get("partnername")));
					partnerlist.setMsisdn((String)(row.get("msisdn")));
					logger.info("retailer " + partnerlist.getPartnerid() + " " + partnerlist.getPartnername() + " " + partnerlist.getMsisdn());
					partner.add(partnerlist);			
				}
				return partner;
			 
			 
		 }
		

		
  
		   	  
	   }
	
	
	public List<WalletTransferModel>  getInfo(HttpSession session, String bid,String msisdn)
	   {	
		
		   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();

		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT a.partnerid, a.partnername, a.wallet as pwallet, a.decrementation, b.branchid,b.wallet as bwallet, ");
		   strSQL.append("c.msisdn ");
		   strSQL.append("FROM partners a, branches b ,retailer_sim_table c,agents d  ");
		   strSQL.append("WHERE a.partnerid= b.partnerid and a.partnerid = ? ");
		   strSQL.append("AND b.branchid = d.branchid AND c.agentid = d.agentid AND d.branchid = ?   and c.msisdn = ? ");
		   
		   partner.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID"), bid,msisdn);
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("pwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("bwallet")));
				partnerlist.setMsisdn((String)(row.get("msisdn")));
				partner.add(partnerlist);			
			}
			session.setAttribute("URL", partner);
			return partner;
		   	  
	   }
	
	public Map fillBox(HttpSession session)
	{
		   String pid = (String) session.getAttribute("PID");
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT branchid from branches where paymenttype = 'PREPAID' and partnerid = ?");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid);
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
		
			}
			
			return prefix;
			
	}
	
	public String getPartnerid(String bid){
		
		   StringBuilder strSQL = new StringBuilder();
			 
		   String pid = null;
		      
		   strSQL.append("SELECT partnerid from branches where branchid  = ?");
		
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),bid);
			
		   for (Map row : rows) {
				
				pid = (String)row.get("partnerid");
		
			}
			
		   return pid;
			
	}
	
 		
	 public int updatetxid(HttpSession session, Long txid, float topupamount,RetailerSimBean bean)
	  {
		 
		 
		 if(session.getAttribute("USERLEVEL").toString().equalsIgnoreCase("manager"))
		 {
	
					    String updateTxBranch = "Update wallets set  wallet = wallet + ?, partnertxid = ? where  partnerid = ?  and (wallet + ?) >= 0 and walletid = ? ";
						
						String transid = "MTR"+txid+"";
						
						try{
					 		 
							int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
									topupamount,transid,session.getAttribute("PID"),topupamount,session.getAttribute("walletid")
							});
							
							
							if(row>0){
								return 1;
							}
							
							}catch(Exception ex)
							{
								logger.info(ex.getMessage());
						         ex.printStackTrace();
						         return 0;
						    }
					 
					 
		
				 
				 
	
			 
		 }
		 
		 else if(session.getAttribute("USERLEVEL").toString().equalsIgnoreCase("user"))
		 
		 {
			 			String updateTxBranch = "Update wallets set  wallet = wallet + ?, partnertxid = ? where  partnerid = ?  and (wallet + ?) >= 0 and walletid = ?";
				
							String transid = "BTR"+txid+"";
							
							try{
						 		 
								int row = getJdbcTemplate().update(updateTxBranch, new Object[] { 
										topupamount,transid,session.getAttribute("PID"),topupamount,session.getAttribute("walletid")
								});
								
								
								if(row>0){
									return 1;
								}
								
								}catch(Exception ex)
								{
									 logger.info(ex.getMessage());
							         ex.printStackTrace();
							         return 0;
							    }
			 
			 
			 
		 }
		 
		return 0;
			 

			
		 
	   }
	 
	 
	 
	 public boolean insertRetailSimTransferLogs(HttpSession session,String msisdn,String txid,String status,String trace,int amount)
		 
	 {
		     BigDecimal denom = new BigDecimal(amount);
		 
			 String pid = (String) session.getAttribute("PID");
			 String aid = (String) session.getAttribute("AID");
			 String user = (String) session.getAttribute("USER");
			 
			 StringBuilder strSQL = new StringBuilder();
			 
			 strSQL.append("insert into retailer_sim_transfer_logs (transferdate,partnerid,username,");
			 strSQL.append("msisdn,transactionid,status,trace,amount) ");
			 strSQL.append("VALUES (now(),?,?,?,?,?,?,?)"); 	 
			 
				try{
			 		 
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						pid,user,msisdn,txid,status,trace,denom });
					
					if(row>0){
						return true;
					}
				
				}catch(Exception ex){
	            ex.printStackTrace();
	            return false;
	        }
				
				return false;
			 	 	
				
	  }
	 
		public boolean  getSimInfo(String msisdn, RetailerSimBean bean)
		   {	
			
			   String siminfo = null;
			   String pid = null;
			   String bid = null;
			   StringBuilder strSQL = new StringBuilder();
			   
//			   strSQL.append("SELECT a.partnerid, a.partnername, a.wallet as pwallet, a.decrementation, b.branchid,b.wallet as bwallet, ");
//			   strSQL.append("c.msisdn ");
//			   strSQL.append("FROM partners a, branches b ,retailer_sim_table c, agents d ");
//			   strSQL.append("WHERE a.partnerid = b.partnerid and a.partnerid = ? ");
//			   strSQL.append("AND b.branchid = d.branchid AND c.agentid = d.agentid AND d.branchid = ? AND c.status = 1 ");
			   
			   strSQL.append("SELECT a.partnerid , c.msisdn  ");
			   strSQL.append("FROM partners a  INNER JOIN agents_partners b ");
			   strSQL.append("ON a.partner = b.partnerid INNER JOIN retailer_sim_table c ");
			   strSQL.append("ON b.agentid = c.agentid and c.msisdn = ? ");
			   
			   
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),msisdn);
				for (Map row : rows) {
					
					//RetailerSimBean bean = new RetailerSimBean();
					
					bean.msisdn = (String)(row.get("msisdn"));
					bean.pid = (String)(row.get("partnerid"));
//					bean.paymenttype = (String)(row.get("paymenttype"));
//					logger.info("simBid" +bean.msisdn );
//					logger.info("simPID" +bean.pid );
//					logger.info("simMsidn"+bean.bid);
							
					
					return true;
					
				}
				
				return false;		
				
		   }
		
		
		

	 
}
