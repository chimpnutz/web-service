package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pc2mweb.controller.ChangePasswordController;
import com.pc2mweb.model.ChangePasswordModel;
import com.pc2mweb.model.WalletTransferModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class ChangePasswordDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(ChangePasswordDAO.class);
	
	public List<ChangePasswordModel>  getUserPass(BigDecimal userid,HttpSession session)
	   {
		   ArrayList<ChangePasswordModel> passwd = new ArrayList<ChangePasswordModel>();		
		 
		   StringBuilder strSQL = new StringBuilder();
		   
		//   strSQL.append("SELECT username, password from agents where agentid  = ?   ");
		   
		   strSQL.append("SELECT a.agentid,a.username,c.partnerid,c.partnername FROM agents a  ");
		   strSQL.append("INNER JOIN agents_partners b ON a.agentid = b.agentid " );
		   strSQL.append("INNER JOIN partners c ON b.partnerid = c.partner " );
		   strSQL.append("WHERE c.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ?)  and a.agentid = ?" );

		   passwd.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID"),userid);
			for (Map row : rows) {
				
				ChangePasswordModel newpass = new ChangePasswordModel();
	
				newpass.setUsername((String)(row.get("username")));
				newpass.setOldpass((String)(row.get("password")));
				passwd.add(newpass);			
			}
			return passwd;
		   	  
	   }
	
	public List<ChangePasswordModel>  getUserPass(String uname,HttpSession session)
	   {
		   ArrayList<ChangePasswordModel> passwd = new ArrayList<ChangePasswordModel>();		
		 
		   StringBuilder strSQL = new StringBuilder();
		   
		//   strSQL.append("SELECT username, password from agents where agentid  = ?   ");
		   
		   strSQL.append("SELECT a.agentid,a.username,c.partnerid,c.partnername FROM agents a  ");
		   strSQL.append("INNER JOIN agents_partners b ON a.agentid = b.agentid " );
		   strSQL.append("INNER JOIN partners c ON b.partnerid = c.partner " );
		   strSQL.append("WHERE c.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ?)  and a.username = ?" );

		   passwd.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), session.getAttribute("PID"),uname);
			for (Map row : rows) {
				
				ChangePasswordModel newpass = new ChangePasswordModel();
	
				newpass.setUsername((String)(row.get("username")));
				newpass.setOldpass((String)(row.get("password")));
				passwd.add(newpass);			
			}
			return passwd;
		   	  
	   }
	
	public List<ChangePasswordModel>  getMyPass(BigDecimal userid)
	   {
		   ArrayList<ChangePasswordModel> passwd = new ArrayList<ChangePasswordModel>();		
		
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT username, password from agents where agentid  = ?   ");

		   passwd.clear();
		   
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(), userid);
			for (Map row : rows) {
				
				ChangePasswordModel newpass = new ChangePasswordModel();
	
				newpass.setUsername((String)(row.get("username")));
				newpass.setOldpass((String)(row.get("password")));
				passwd.add(newpass);			
			}
			return passwd;
		   	  
	   }
	
	
	public boolean changePassword(ChangePasswordModel changepass,HttpSession session){
		
		   String newpass = changepass.getNewpass();

		   if(newpass.equals("")){
			   
			   return false;
			   
		   }
		   else
		   
		   {

		   StringBuilder strSQL = new StringBuilder();
		   
//		   strSQL.append("UPDATE agents set agents.password = ? ");
//		   strSQL.append("WHERE agents.username = ? and partners.partnerid = ? and ");
//		   strSQL.append("partners.partnerid = branches.partnerid and agents.branchid = branches.branchid");
//		   
//		   
//		   strSQL.append("update agents set password = ? where  "); 
//		   strSQL.append("username = (select agents.username from agents,partners ");
//		   strSQL.append("where partners.partnerid = ?   and agents.USERNAME = ?) " );
	
		   strSQL.append("UPDATE agents SET PASSWORD = ? WHERE username = ?  "); 
		   try{
			    
				MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
				mdEnc.update(newpass.getBytes(), 0, newpass.length());
				String agentpwd = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
				
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					agentpwd,changepass.getUsername()
				});
 
				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException | NoSuchAlgorithmException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		   }
		   
		return false;
		
		
	}
	
	public boolean changemyPassword(ChangePasswordModel changepass,HttpSession session){
		
		   String newpass = changepass.getNewpass();

		   if(newpass.equals("")){
			   
			   return false;
			   
		   }
		   else
		   
		   {

		   StringBuilder strSQL = new StringBuilder();
		   
//		   strSQL.append("UPDATE agents set agents.password = ? ");
//		   strSQL.append("WHERE agents.username = ? and partners.partnerid = ? and ");
//		   strSQL.append("partners.partnerid = branches.partnerid and agents.branchid = branches.branchid");
//		   
		   
		   strSQL.append("UPDATE agents SET PASSWORD = ? WHERE username = ?  "); 

		   try{

				MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
				mdEnc.update(newpass.getBytes(), 0, newpass.length());
				String agentpwd = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
				
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					agentpwd, session.getAttribute("USER")
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException | NoSuchAlgorithmException ex){
	            ex.printStackTrace();
	            return false;
	        } 
		   
		   }
		   
		return false;
		
		
	}
	
	

}
