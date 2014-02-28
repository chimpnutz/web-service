package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.model.ChangePasswordModel;
import com.pc2mweb.model.EditPartnerModel;
import com.pc2mweb.model.UserManagementModel;

public class UserManagementDAO extends JdbcDaoSupport {
	
	  	
	   public List<UserManagementModel>  getPartners(HttpSession usersession)
	   {
		   ArrayList<UserManagementModel> partners = new ArrayList<UserManagementModel>();

		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("SELECT a.agentid,a.username,c.partnerid,c.partnername FROM agents a  ");
		   strSQL.append("INNER JOIN agents_partners b ON a.agentid = b.agentid " );
		   strSQL.append("INNER JOIN partners c ON b.partnerid = c.partner " );
		   strSQL.append("WHERE c.parent_partnerid = (SELECT partner FROM partners WHERE partnerid = ?) " );
	
		   partners.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"));
			for (Map row : rows) {
				
				UserManagementModel partnerlist = new UserManagementModel();
				partnerlist.setUid((int)(row.get("agentid")));
				partnerlist.setAgentuname((String)(row.get("username")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partners.add(partnerlist);
				
			}
		
			return partners;
		   	  
	   }
	
	public Map fillBox(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT * from prefixes");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("prefix")), (String)row.get("prefix"));
		
			}
			
			return prefix;
			

	}
	
	public List<MessageBean> getPartnerDetails(HttpSession session){
		
		   ArrayList<MessageBean> details = new ArrayList<MessageBean>();
		  
		   StringBuilder strSQL = new StringBuilder();
		   
		   BigDecimal wallet = null;
		   
		   String type = null;
		  
			   StringBuilder getType = new StringBuilder();
			   
			   getType.append("SELECT partnerid,partnername from partners where partnerid = ?");
			   
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getType.toString(),session.getAttribute("PID"));
			   
			   for (Map row : rows) {
				   
				   MessageBean messagebean = new MessageBean();
				   
				   messagebean.setPartnername((String)(row.get("partnername")));
				   messagebean.setPartnerid((String)(row.get("partnerid")));
				   details.add(messagebean);

			   }
			   
			   return details;
			   
			   
	}
	
	   public List<EditPartnerModel>  getBranchname(String pid,HttpSession usersession)
	   {
		   ArrayList<EditPartnerModel> branch = new ArrayList<EditPartnerModel>();

		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("Select partnerid,partnername from partners  ");
		   strSQL.append("where partnerid = ?");
	
		   branch.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),usersession.getAttribute("PID"),pid);
		
			for (Map row : rows) {
				
				EditPartnerModel partnerlist = new EditPartnerModel();
				partnerlist.setPartnerid((String)(row.get("partnerid")));
				partnerlist.setPartnername((String)(row.get("partnername")));
				branch.add(partnerlist);
				
			}
		
			return branch;
		   	  
	   }
	   
	   public boolean changePartnerName(EditPartnerModel model,HttpSession session){
			
		   String partnername = model.getPartnername();

		   if(partnername.equals("")){
			   
			   return false;
			   
		   }
		   else
		   
		   {

		   StringBuilder strSQL = new StringBuilder();
		      
		   strSQL.append("update partners set partnername = ?,custom_msg = ?,email = ?   "); 
		   strSQL.append("where  partnerid = ? " );

		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
					partnername,model.getMessage(),model.getEmail(),model.getPartnerid()
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		   }
		   
		return false;
		
		
	}
	
	   



}
