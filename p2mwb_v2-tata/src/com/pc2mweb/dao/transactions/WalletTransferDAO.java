package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.UserBean;

import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;
import com.pc2mweb.model.Wallet_Transaction_Information;


public class WalletTransferDAO extends JdbcDaoSupport {
	
	public List<WalletTransferModel>  getPartners(HttpSession usersession)
	   {
		   ArrayList<WalletTransferModel> partners = new ArrayList<WalletTransferModel>();
		   
		   StringBuilder strSQL = new StringBuilder();

		   
		   strSQL.append(" SELECT a.partnerid,a.partnername,b.wallet FROM partners a ");
		   strSQL.append("INNER JOIN wallets b ");
		   strSQL.append("ON a.partnerid = b.partnerid AND b.isdefault = ? AND a.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ? )");
		   
		   partners.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), 1, usersession.getAttribute("PID"));
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				
				BigDecimal wallet = (BigDecimal)row.get("wallet");
				
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet(wallet.floatValue());

				partners.add(partnerlist);		
				
			}
		
			return partners;
		   	  
	   }
	
	public List<WalletTransferModel>  getPartnersforUser(HttpSession usersession)
	   {
		   ArrayList<WalletTransferModel> partners = new ArrayList<WalletTransferModel>();
		   
		   StringBuilder strSQL = new StringBuilder();
		   		   
		   strSQL.append("SELECT partners.partnerid, partners.partnername, partners.wallet as partnerwallet, branches.branchid,branches.wallet ");
		   strSQL.append("FROM partners, branches ");
		   strSQL.append("WHERE branches.partnerid = partners.partnerid  ");
		   strSQL.append("AND branches.paymenttype = 'PREPAID' AND partners.partnerid = ? AND branches.branchid =? ");
		   strSQL.append("ORDER BY partners.partnerid");
		   
		   partners.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), usersession.getAttribute("PID"),usersession.getAttribute("BID"));
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("partnerwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("wallet")));
				partners.add(partnerlist);		
				
			}
		
			return partners;
		   	  
	   }
	
	public List<WalletTransferModel>  getPartner(HttpSession usersession)
	   {	
		   String usertype = (String) usersession.getAttribute("USERLEVEL");
		   
		   if(usertype.equals("user")){
			   
		   ArrayList<WalletTransferModel> partners = new ArrayList<WalletTransferModel>();
		   
		   StringBuilder strSQL = new StringBuilder();
		   		   
		   strSQL.append("SELECT partners.partnerid, partners.partnername, partners.wallet as partnerwallet, branches.branchid,branches.wallet ");
		   strSQL.append("FROM partners, branches ");
		   strSQL.append("WHERE branches.partnerid = partners.partnerid  ");
		   strSQL.append(" AND partners.partnerid = ? AND branches.branchid =? ");
		   strSQL.append("ORDER BY partners.partnerid");
		   
		   partners.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), usersession.getAttribute("PID"),usersession.getAttribute("BID"));
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("partnerwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("wallet")));
				partners.add(partnerlist);		
				
			}
		
			return partners;
		   }else
		   {
			   
			   ArrayList<WalletTransferModel> partners = new ArrayList<WalletTransferModel>();
			   
			   StringBuilder strSQL = new StringBuilder();
			   		   
			   strSQL.append("SELECT partners.partnerid, partners.partnername, partners.wallet as partnerwallet, branches.branchid,branches.wallet ");
			   strSQL.append("FROM partners, branches ");
			   strSQL.append("WHERE branches.partnerid = partners.partnerid  ");
			   strSQL.append(" AND partners.partnerid = ? ");
			   strSQL.append("ORDER BY partners.partnerid");
			   
			   partners.clear();
			   
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), usersession.getAttribute("PID"));
				for (Map row : rows) {
					
					WalletTransferModel partnerlist = new WalletTransferModel();
					partnerlist.setPartnerid((String)(row.get("partnerid")));
					partnerlist.setPartnername((String)(row.get("partnername")));
					partnerlist.setPartnerwallet((Float)(row.get("partnerwallet")));
					partnerlist.setBranchid((String)(row.get("branchid")));
					partnerlist.setBranchwallet((Float)(row.get("wallet")));
					partners.add(partnerlist);		
					
				}
			
				return partners;			   
		   }
		   	   	  
	   }
	
//	public Float getPartnerWallet(HttpSession session){
//		
//		   ArrayList<MessageBean> details = new ArrayList<MessageBean>();
//		  
//		   StringBuilder strSQL = new StringBuilder();
//		   
//		   BigDecimal wallet = null;
//		   
//		   String type = null;
//		  
//			   StringBuilder getType = new StringBuilder();
//			   
//			   getType.append("SELECT wallet from wallets where partnerid = ? and isdefault = ?");
//			   
//			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getType.toString(),session.getAttribute("PID"),1);
//			   
//			   for (Map row : rows) {
//				   
//				   wallet = ((BigDecimal)row.get("wallet"));
// 
//			   }
//			   
//			   return wallet.floatValue();
//			   
//			   
//	}
	
	public Float getPartnerWallet(HttpSession session){
		
		   ArrayList<MessageBean> details = new ArrayList<MessageBean>();
		  
		   StringBuilder strSQL = new StringBuilder();
		   
		   BigDecimal wallet = null;
	
			   StringBuilder getType = new StringBuilder();
			   
			   strSQL.append("SELECT a.walletid, a.wallet,a.paymenttype from wallets a, partners_wallet b, wallet_types c "); 
			   strSQL.append("where a.walletid = b.walletid  and b.wallet_type = c.wallet_type and a.partnerid = ? ");
			   strSQL.append("and c.wallet_name = ?");
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"AMAX");
			   
			   for (Map row : rows) {
				   
				   wallet = ((BigDecimal)row.get("wallet"));

			   }
			   
			   return wallet.floatValue();
			   
			   
	}
	
	
	
	
	
	public List<MessageBean> getWallet(HttpSession session){
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   ArrayList<MessageBean> details = new ArrayList<MessageBean>();

		   String type = null;
		   
		   strSQL.append("SELECT a.wallet,a.paymenttype,a.partnerid, b.partnername from wallets a , partners b, partners_wallet c where a.partnerid = b.partnerid and b.partnerid = ? and isdefault = ? AND a.walletid = c.walletid AND c.wallet_type = ?");
		   
		   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),1,1);
			
		   for (Map row : rows) {
			   
			   MessageBean messagebean = new MessageBean();
			   
			   BigDecimal wallet = (BigDecimal)row.get("wallet");
			   
			   messagebean.setPartnername((String)(row.get("partnername")));
			   messagebean.setWallet(wallet.floatValue());
			   details.add(messagebean);
			   
			   
//			   type = (String)row.get("paymenttype");
//			   wallet = (BigDecimal)row.get("wallet");   
//			   
//			   if(!type.equalsIgnoreCase("TRANSIENT")){
//				   
//				   MessageBean messagebean = new MessageBean();
//				   
//				   messagebean.setPartnername((String)(row.get("partnername")));
//				   messagebean.setWallet((BigDecimal)row.get("wallet"));
//				   details.add(messagebean);
//				   
//				   
//				   
//			   }
//
//			   else if(type.equalsIgnoreCase("TRANSIENT")){
//			   
//			   StringBuilder getType = new StringBuilder();
//			   
//			   getType.append("SELECT wallet,paymenttype,partnername from partners where partnerid = ?");
//			   
//			   List<Map<String,Object>> rowsTrans = getJdbcTemplate().queryForList(getType.toString(),session.getAttribute("PID"));
//			   
//			   for (Map rowTran : rowsTrans) {
//
//				   wallet = (BigDecimal)rowTran.get("wallet");  
//				   
//				   MessageBean messagebean = new MessageBean();
//				   
//				   messagebean.setPartnername((String)(row.get("partnername")));
//				   messagebean.setWallet((BigDecimal)row.get("wallet"));
//				   messagebean.setType((String)(row.get("paymenttype")));
//				   details.add(messagebean);
//  
//			   }
//			   
//			  
//		   	}
			   			   
		   }
		return details;
		
		   
			  
		
	}

}
