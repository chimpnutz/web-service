package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.WalletTransferModel;

public class ViewAccountDAO extends JdbcDaoSupport {
	
	
	
	public List<WalletTransferModel>  getPartnerInfo(HttpSession session, String bid)
	   {
		
		   ArrayList<WalletTransferModel> partner = new ArrayList<WalletTransferModel>();
		   
		   String pid = (String) session.getAttribute("PID");
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT partners.partnerid, partners.partnername, partners.wallet as partnerwallet, branches.branchid,branches.wallet ");
		   strSQL.append("FROM partners, branches ");
		   strSQL.append("WHERE partners.partnerid= branches.partnerid and partners.partnerid = ? ");
		   strSQL.append("AND branches.branchid = ? ");
		   
			partner.clear();
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), pid,bid);
		
			for (Map row : rows) {
				
				WalletTransferModel partnerlist = new WalletTransferModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerwallet((Float)(row.get("partnerwallet")));
				partnerlist.setBranchid((String)(row.get("branchid")));
				partnerlist.setBranchwallet((Float)(row.get("wallet")));
				partner.add(partnerlist);			
			}
		
			return partner;
		   	  
	   }

}
