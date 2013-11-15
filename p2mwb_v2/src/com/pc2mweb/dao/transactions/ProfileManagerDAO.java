package com.pc2mweb.dao.transactions;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.PartnerProfile;



public class ProfileManagerDAO extends JdbcDaoSupport
{
	private static final Logger logger = Logger.getLogger(TopupDAO.class);
	
	public PartnerProfile GetProfilebyUsername(String username)
	
	{
		PartnerProfile profile = null;
		
		StringBuilder strSQL = new StringBuilder();
		
		strSQL.append("select a.agentid,a.username,b.roleid,c.partnerid,c.partnername,c.appname,c.parent_partnerid,c.email,c.runmode,  d.paymenttype, d.isdefault,d.wallet ");
		strSQL.append("from agents a inner join agents_partners b on a.agentid = b.agentid ");
		strSQL.append("inner join partners c on b.partnerid = c.partner  ");
		strSQL.append("INNER JOIN wallets d ON c.partnerid = d.partnerid ");
		strSQL.append("where a.username = ? AND d.isdefault = 1 ");
		
		
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString(),username);
		
		if(rs.next())
		
		{

			profile = new PartnerProfile();
			
			
			profile.agentid = rs.getInt("agentid");
			profile.username = rs.getString("username");
			profile.roleid = rs.getInt("roleid");
			profile.email = rs.getString("email");
			profile.paymenttype = rs.getString("paymenttype");
			profile.partnerid = rs.getString("partnerid");
			profile.partnername = rs.getString("partnername");
			profile.wallet = rs.getFloat("wallet");
			profile.runmode = rs.getString("runmode");


		}
		
		
		
		
		return profile;
		
		
		
		
	}
	
	
	public PartnerProfile GetProfilebyPID(String partnerid)
	
	{
		
		StringBuilder strSQL = new StringBuilder();
		
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(strSQL.toString());
		
		
		
		
		
		return null;
		
		
		
		
	}

}
