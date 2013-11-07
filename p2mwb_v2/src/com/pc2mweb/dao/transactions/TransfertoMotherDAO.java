package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
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

import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;

public class TransfertoMotherDAO extends JdbcDaoSupport {
	
	public List<WalletTransferModel>  getPartnerInfo(HttpSession session, String bid)
	   {	
		
		   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();
		   
		   String pid = (String) session.getAttribute("PID");
		   
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT partners.partnerid, partners.partnername, partners.wallet as partnerwallet, branches.branchid,branches.wallet ");
		   strSQL.append("FROM partners, branches ");
		   strSQL.append("WHERE partners.partnerid= branches.partnerid and partners.partnerid = ? ");
		   strSQL.append("AND branches.branchid = ? ");
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), pid,bid);
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partner.clear();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("partnerwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("wallet")));
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
	
	public Float getpidWallet(String pid){
		
		   StringBuilder strSQL = new StringBuilder();
			 
		   BigDecimal wallet = null;
		   
		   strSQL.append("SELECT wallet from wallets where partnerid  = ? and isdefault = ?");
		
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),pid,1);
			
		   for (Map row : rows) {
				
			   wallet = (BigDecimal)row.get("wallet");
		
			}
			
		   return wallet.floatValue();
		
	}
	
	public Float getbidWallet(String bid){
		
		   StringBuilder strSQL = new StringBuilder();
			 
		   BigDecimal wallet = null;
		   
		   strSQL.append("SELECT wallet from wallets where partnerid  = ? and  isdefault = ?");
		
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),bid,1);
			
		   for (Map row : rows) {
				
			   wallet = (BigDecimal)row.get("wallet");
		
			}
			
		   return wallet.floatValue();
		
	}
	
	public String getTransid(){
		
		   String updateSeq = "update wallet_transfer_sequence set seq = seq + 1";
		   
		   String txid = "BTM";
		   
			try{
		 		 
				int row2 = getJdbcTemplate().update(updateSeq, new Object[] { 
						
				});
				
				if(row2>0 )
				{
					
					   StringBuilder strSQL = new StringBuilder();
						 
					   strSQL.append("SELECT seq from wallet_transfer_sequence");
					
					   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
						
					   for (Map row : rows) {
							
						   txid = txid+(Integer)row.get("seq");
					
						}
						
					   return txid;
					
				
				}
				
				
				   return txid;
				
			
			}catch(Exception ex){
            ex.printStackTrace();
            return txid;
			}
		
	}
	
	 public boolean transfertoMother(String pid,String bid,Float amount,TransactionIDObject obj)
	   {
		 	String txid = this.getTransid();
		 	
		 	obj.transactionID = txid;
		 
			String addPartner = "Update wallets set wallet = wallet + ?, partnertxid = ? where  partnerid = ? and (wallet + ?) >= 0 and isdefault = ?";
			
			String deductBranch = "Update wallets set wallet = wallet - ?, partnertxid = ? where partnerid = ? and (wallet - ?) >= 0 and  isdefault = ?";
			

			try{
				
				int row2 = getJdbcTemplate().update(deductBranch, new Object[] { 
						amount,txid,bid,amount,1
				});
				
				int row = 0;
				
				if (row2 > 0)
				{
					row = getJdbcTemplate().update(addPartner, new Object[] { 
							amount,txid,pid,amount,1
					});
				}
				
				if(row>0 && row2>0){
					return true;
				}

			}catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
			return false;
	   }
	 
	 
	 public boolean insertWalletHistory(String bid, String pid, 
			 Float amount, Float oldSource, Float NewSource,
			 Float oldDest, Float newDest,String transfertype,TransactionIDObject obj)
		 
		 {
		 	
		 
			 StringBuilder strSQL = new StringBuilder();
			 
			 strSQL.append("insert into wallet_transfer (transdate,sender,receiver,amount,");
			 strSQL.append("sender_start_bal,sender_end_bal,receiver_start_bal,receiver_end_bal,transfertype,transid)");
			 strSQL.append("VALUES (now(),?,?,?,?,?,?,?,?,?)"); 	 
			 
				try{
			 		 
					int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
						bid,pid,amount,oldSource,NewSource,oldDest,newDest,transfertype,obj.transactionID
					});

					if(row>0){
						return true;
					}
				
				}catch(Exception ex){
	            ex.printStackTrace();
	            return false;
	        }
				
				return false;
			 
		 }
	 



}
