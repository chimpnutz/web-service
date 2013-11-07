package com.pc2mweb.dao.transactions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.model.ManageBranchesModel;
import com.pc2mweb.model.TransfertoRetailerModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.utility.function.pc2mwebFunc;



public class ManageBranchesDAO extends JdbcDaoSupport
{
	private static final Logger logger = Logger.getLogger(TopupDAO.class);
	
	
	public int insertBranch(HttpSession usersession,ManageBranchesModel model){
			
		   int partner = 0;
		   String partnerid = "";
		   
		   String runmode = "";
		   String responseurl = "";
		   int decremented_commission_group = 0;
		   String appname = "";
		   String communicationmode = "";
		   String active = "";
		   int channel_id = 0;
		   
		   
		   String productcode = "";
		   String allows = "";
		   String loadproductappname = "";
		   

		   
		   String wallet_type="";
		   
		   
		   String ranges_productcode = "";
		   float fee = (float) 0.0;
		   float minamount = (float) 0.0;
		   float maxamount = (float) 0.0;
		   String fee_type = "";
		   String ranges_currency = "";
		   
		   
		   
		   String forex_currency = "";
		   float forex_fee = (float) 0.0;
		   String forex_feetype = "";
		   float forex_amount = (float) 0.0;
		   float forex_toamount = (float) 0.0;
		   
		   
		   int insertPartnerrow = -1;
		   int insertloadproductallowsRow = -1;
		   int insertWalletRow = -1;
		   int insertWalletTypeRow = -1;
		   int insertRangesSQLRow = -1;
		   int getforexfeeRow = -1;
		   int insertAgentsRow = -1;
		   int insertagentspartnersRow = -1;


		   pc2mwebFunc generateId = new pc2mwebFunc();
		   
		   String branchid = generateId.generateID();
		   try{
			   
			   StringBuilder checkBranchid = new StringBuilder();
			   
			   checkBranchid.append("select * from partners where partnerid = ?");
			   
			   SqlRowSet checkBranchidrs   = getJdbcTemplate().queryForRowSet(checkBranchid.toString(),branchid);
			   
			   if(checkBranchidrs.next()){
				   
				   logger.info("Branch ID "+branchid+" already exist.");
				   return -1;
			   }
			   
			   StringBuilder getparentSql = new StringBuilder();
			   
			   getparentSql.append("select partner,partnerid,runmode,responseurl,decremented_commission_group,appname,");
			   getparentSql.append("communicationmode,active,channel_id ");
			   getparentSql.append("from partners where partnerid = ? ");

			   
			   SqlRowSet rs   = getJdbcTemplate().queryForRowSet(getparentSql.toString(),usersession.getAttribute("PID"));
			   
			   if(rs.next()) {
					
				
				   
				   partner = (int)(rs.getInt("partner"));
				   partnerid = (String)(rs.getString("partnerid"));
				   
				   logger.info("Get Details from Parent Partnerid "+partnerid);
	   
				}

			   
			   StringBuilder insertPartnerSQL = new StringBuilder();
			   
			   insertPartnerSQL.append("INSERT INTO partners  ");
			   insertPartnerSQL.append("(partnerid,partnername,runmode,responseurl,decremented_commission_group,appname,channel_id,parent_partnerid) ");
			   insertPartnerSQL.append(" select ?, ?, runmode, responseurl, decremented_commission_group,appname, channel_id, ? from partners where partnerid = ?");
			   
			   
			   
				 insertPartnerrow = getJdbcTemplate().update(insertPartnerSQL.toString(), new Object[] { 
					 branchid,model.getBranchname(),partner,partnerid
				});

		 
				 logger.info("Inserting new partner: "+branchid +" for parent partnerid: "+partnerid);
				   
				 if(insertPartnerrow > 0)
				 {
					 
					   StringBuilder insertloadproductallowsSql = new StringBuilder();
					   
					   insertloadproductallowsSql.append("INSERT INTO loadproducts_allows  ");
					   insertloadproductallowsSql.append("(partnerid,productcode,allows,appname) ");
					   insertloadproductallowsSql.append(" select (select partner from partners where partnerid = ?), productcode,allows,appname from loadproducts_allows where partnerid = ? ");
					   
						 insertloadproductallowsRow = getJdbcTemplate().update(insertloadproductallowsSql.toString(), new Object[] { 
							 branchid, partner
						});
						
		
						   logger.info("Inserting new partner "+branchid+ " into: load_products_allows ");
						 
						   if(insertloadproductallowsRow>0)
						   {
							   StringBuilder getwalletsSql = new StringBuilder();
							   
							   getwalletsSql.append("select walletid,paymenttype,isdefault,outright_discount,currency from wallets where partnerid = ?");
					
							   SqlRowSet getwalletsRs   = getJdbcTemplate().queryForRowSet(getwalletsSql.toString(),partnerid);
							   
							   logger.info("Get Wallet Details from Parent Partnerid: "+partnerid);
							   
							   while (getwalletsRs.next())
							   {
								   
							   logger.info("Inserting Wallet Details for  Partnerid: "+branchid);	   
							   
							   
							   final int walletid = (int)(getwalletsRs.getInt("walletid"));
						   
							   final StringBuilder insertWalletSql = new StringBuilder();
							   
							   final ManageBranchesModel walletmodel = model;
							   
							   final String bid = branchid;
							  
							   final String pid = partnerid;
							   
							   insertWalletSql.append("INSERT INTO wallets  ");
							   insertWalletSql.append("(partnerid,wallet,paymenttype,isdefault,outright_discount,currency,partnertxid) ");
							   insertWalletSql.append(" select ?,?,paymenttype,isdefault,outright_discount,currency,? from wallets where partnerid = ? ");

								KeyHolder keyHolder = new GeneratedKeyHolder();
								
								 insertWalletRow = getJdbcTemplate().update(new PreparedStatementCreator() 
								{
									  public PreparedStatement createPreparedStatement(Connection con)
											    throws SQLException 
											    {
											   PreparedStatement ps = con.prepareStatement(insertWalletSql.toString(),Statement.RETURN_GENERATED_KEYS);

											   
											   ps.setString(1, bid);
											   ps.setFloat(2, (float) 0.0);
											   ps.setString(3, "");
											   ps.setString(4, pid);
												
								
											  
											 
											   return ps;
											     }

							
								}, keyHolder);
							   
								   int wid = keyHolder.getKey().intValue();
								   
								   StringBuilder getWallettypeSQL = new StringBuilder();
								   
								   getWallettypeSQL.append("select wallet_type from partners_wallet where walletid = ?");
							
								   SqlRowSet getwallettypeRs   = getJdbcTemplate().queryForRowSet(getWallettypeSQL.toString(),walletid);
								   
								   while (getwallettypeRs.next())
								   {
									   
									   
									   wallet_type = (String)(getwallettypeRs.getString("wallet_type"));
									   		 
									   logger.info("Inserting Wallet Details for  Partnerid: "+branchid +" into partners_wallet");	   
									   
									   StringBuilder insertWalletTypeSQL = new StringBuilder();
									   
									   insertWalletTypeSQL.append("INSERT INTO partners_wallet  ");
									   insertWalletTypeSQL.append("(walletid,wallet_type)");
									   insertWalletTypeSQL.append(" values (?,?) ");
									   
										 insertWalletTypeRow = getJdbcTemplate().update(insertWalletTypeSQL.toString(), new Object[] { 
											wid,wallet_type

										});
										 
										
								   }
								   
							   }
							   
							   
							   
							   
											
											
										if(insertWalletRow > 0 && insertWalletTypeRow >0)
										   
										{
											   logger.info("Inserting Wallet Details for  Partnerid: "+branchid +" successful");	   
											   
											   StringBuilder getrangesSql = new StringBuilder();
											   
											   getrangesSql.append("select productcode,fee,minamount,maxamount,fee_type,currency from ranges where partnerid = ?");
								
											   SqlRowSet getrangesSqlRs   = getJdbcTemplate().queryForRowSet(getrangesSql.toString(),partnerid);
											   
											   logger.info("Getting Ranges Details for  Partnerid: "+partnerid);	   
											   
											   while (getrangesSqlRs.next())
											   {

												   logger.info("Inserting Ranges Details for  Partnerid: "+branchid);	 
												   
												   StringBuilder insertRangesSQL = new StringBuilder();
												   
												   insertRangesSQL.append("INSERT INTO ranges  ");
												   insertRangesSQL.append("(partnerid,productcode,fee,minamount,maxamount,fee_type,currency)");
												   insertRangesSQL.append(" select ?,productcode,fee,minamount,maxamount,fee_type,currency from ranges where partnerid = ? ");
												   
													 insertRangesSQLRow = getJdbcTemplate().update(insertRangesSQL.toString(), new Object[] { 
														branchid,partnerid
													});
												   
												   
											   }
											   
											   if(insertRangesSQLRow > 0)
											   
											
										   
										   {
											   
											   StringBuilder getforexfeeSql = new StringBuilder();
											   
											   getforexfeeSql.append("select currency,fee,feetype,amount,to_amount,partnerid from forex_fee_tbl where partnerid = ?");
								
											   SqlRowSet getforexfeeSqlRs   = getJdbcTemplate().queryForRowSet(getforexfeeSql.toString(),partnerid);
											   
											   logger.info("Getting Forex_fee Details for  Partnerid: "+partnerid);	
											   
											   while (getforexfeeSqlRs.next())
											   {
												   logger.info("Insert Ranges Details for  Partnerid: "+branchid);	
												   
												   StringBuilder insertRangesSQL = new StringBuilder();
												   
												   insertRangesSQL.append("INSERT INTO forex_fee_tbl  ");
												   insertRangesSQL.append("(currency,fee,feetype,amount,to_amount,partnerid) ");
												   insertRangesSQL.append(" select currency,fee,feetype,amount,to_amount,? from forex_fee_tbl where partnerid = ? ");
												   
													 getforexfeeRow = getJdbcTemplate().update(insertRangesSQL.toString(), new Object[] { 
														branchid,partnerid
													});
												   
												   
											   }
											   
											   
											   
											   if(getforexfeeRow>0)
												   
												   logger.info("Insert Ranges Details for  Partnerid: "+branchid +" successful");	
											   
											   {
												   
												   StringBuilder checkAgentSQL = new StringBuilder();
												   
												   checkAgentSQL.append("select username from agents where username = ?");
									
												   SqlRowSet checkAgentrow   = getJdbcTemplate().queryForRowSet(getforexfeeSql.toString(),model.getUsername());
									
												   if(checkAgentrow.next()){
													   
													   logger.info("username: "+ model.getUsername() + " already exist. please select new username.");	
													   return -2;
												   }
												   
												   
												   int aid = -1;
												   final StringBuilder insertAgentsSQL = new StringBuilder();
												   
												   logger.info("Inserting new agent "+model.getUsername()+" for new Partnerid: "+branchid);	
												   
												   
												   insertAgentsSQL.append("INSERT INTO agents  ");
												   insertAgentsSQL.append("(username,password,status) ");
												   insertAgentsSQL.append(" VALUES (?,?,? )");
												   
												   final ManageBranchesModel agent = model;
												   
													KeyHolder akeyHolder = new GeneratedKeyHolder();
													
													MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
													mdEnc.update(agent.getPassword().getBytes(), 0, agent.getPassword().length());
													final String agentpwd = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypt
													
													
													 insertAgentsRow= getJdbcTemplate().update(new PreparedStatementCreator() 
													{
														  public PreparedStatement createPreparedStatement(Connection con)
																    throws SQLException 
																    {
																   PreparedStatement ps = con.prepareStatement(insertAgentsSQL.toString(),Statement.RETURN_GENERATED_KEYS);
		
																   ps.setString(1, agent.getUsername());
																   ps.setString(2, agentpwd);
																   ps.setString(3, "active");
												
													
																   return ps;
																     }
		
												
													}, akeyHolder);
												
													aid = akeyHolder.getKey().intValue();
													
													if(insertAgentsRow>0)
														
														   logger.info("Inserting new agent "+model.getUsername()+" for new Partnerid: "+branchid+ " successful");	
													{
														    StringBuilder insertagentspartnersSQL = new StringBuilder();
														   
														    insertagentspartnersSQL.append("INSERT INTO agents_partners  ");
														    insertagentspartnersSQL.append("(agentid,partnerid,roleid) ");
														    insertagentspartnersSQL.append(" VALUES (?,(select partner from partners where partnerid = ?),? )");
														
														    
															 insertagentspartnersRow = getJdbcTemplate().update(insertagentspartnersSQL.toString(), new Object[] { 
																aid, branchid,5
															});
														
													}
													
													
													
													if(insertagentspartnersRow > 0)
													{
													
														 logger.info("Inserting new agent "+model.getUsername()+" roles for new Partnerid: "+branchid+ " successful");	
														
														if(insertPartnerrow > 0 && insertloadproductallowsRow > 0 && insertWalletRow > 0 && insertWalletTypeRow > 0
																&& insertRangesSQLRow > 0 && getforexfeeRow > 0 && insertAgentsRow >0 && insertagentspartnersRow >0)
														{

															 logger.info("Inserting new branch: "+model.getBranchname()+",branchid: "+branchid+", and agent: "+model.getUsername()+"  for Parent Partnerid: "+partnerid+ " successful");	
															
															return 1;
														}
									
														
														
														
													}
											   
											   }
											   
										   }   
										}

										
								   
							  
						   }
					 
				 }
				 
				 
				 
				 
					   
		   }catch(DataAccessException | NoSuchAlgorithmException ex){
	            ex.printStackTrace();
	            return -3;
	        }
		   
		return -4;

		
	}
	
	
	
	public boolean insertAgent(HttpSession usersession,ManageBranchesModel model){
			
		   int aid = -1;
		   final StringBuilder insertAgentsSQL = new StringBuilder();
		   
		   insertAgentsSQL.append("INSERT INTO agents  ");
		   insertAgentsSQL.append("(username,password,status) ");
		   insertAgentsSQL.append(" VALUES (?,?,? ");
		   
		   final ManageBranchesModel agent = model;
		   
		   try{
			   
			   
				KeyHolder keyHolder = new GeneratedKeyHolder();
				
				int insertAgentsRow= getJdbcTemplate().update(new PreparedStatementCreator() 
				{
					  public PreparedStatement createPreparedStatement(Connection con)
							    throws SQLException 
							    {
							   PreparedStatement ps = con.prepareStatement(insertAgentsSQL.toString(),Statement.RETURN_GENERATED_KEYS);

							   ps.setString(1, agent.getUsername());
							   ps.setString(2, agent.getPassword());
							   ps.setString(3, "active");
			
				
							   return ps;
							     }

			
				}, keyHolder);
			
					aid = keyHolder.getKey().intValue();
			  
					
					if(insertAgentsRow>0)
						
						
					{
						    StringBuilder insertagentspartnersSQL = new StringBuilder();
						   
						    insertagentspartnersSQL.append("INSERT INTO agents_partners  ");
						    insertagentspartnersSQL.append("(agentid,partnerid,roleid) ");
						    insertagentspartnersSQL.append(" VALUES (?,?,? ");
						
						    
							int insertagentspartnersRow = getJdbcTemplate().update(insertagentspartnersSQL.toString(), new Object[] { 
								aid, model.getBranchid(),5
							});
						
					}
			   
		   }catch(DataAccessException ex){
	           ex.printStackTrace();
	           return false;
	       }
	   
	return false;
	}
	
	
	
	
	
	   public List<ManageBranchesModel>  getPartners(HttpSession usersession)
	   {
		   ArrayList<ManageBranchesModel> branch = new ArrayList<ManageBranchesModel>();

		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("select partnerid, partnername, email from partners ");
		   strSQL.append("WHERE parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ?) or partnerid = ?" );
	
		   branch.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"),usersession.getAttribute("PID"));
			for (Map row : rows) {
				
				ManageBranchesModel branchlist = new ManageBranchesModel();
				branchlist.setBranchid((String)(row.get("partnerid")));
				branchlist.setBranchname((String)(row.get("partnername")));
				branchlist.setEmail((String)(row.get("email")));
				branch.add(branchlist);
				
			}
		
			return branch;
		   	  
	   }
	

}
