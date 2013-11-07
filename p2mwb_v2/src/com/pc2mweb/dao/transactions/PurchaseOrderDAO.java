package com.pc2mweb.dao.transactions;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.RetailerTransactionHistoryModel;

public class PurchaseOrderDAO extends JdbcDaoSupport 
{
	
	
	
	public boolean insertPurchaseOrder(HttpSession session,PurchaseOrderModel purchaseForm){

		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equals("manager"))
		   {
			
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("INSERT INTO purchase_orders  ");
		   strSQL.append("(agentid,amount,bank,branch,attachment,remarks,status,requesteddate) ");
		   strSQL.append(" VALUES (?,?,?,?,?,?,?,now() )");
		   


		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
				Integer.parseInt(session.getAttribute("AID").toString()),Integer.parseInt(purchaseForm.getAmount()),purchaseForm.getBank(),purchaseForm.getBranch(),
				purchaseForm.getAttachment(),purchaseForm.getRemarks(),"Requested"
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		return false;
		   }
		   
		   else 		   if(role.equals("user"))
		   {
			
		   StringBuilder strSQL = new StringBuilder();
		   
		   strSQL.append("INSERT INTO purchase_orders  ");
		   strSQL.append("(agentid,amount,bank,branch,attachment,remarks,status,requesteddate) ");
		   strSQL.append(" VALUES (?,?,?,?,?,?,?,now() )");
		   
		   String attachment = null;


		   try{
			   
				int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
				Integer.parseInt(session.getAttribute("AID").toString()),Integer.parseInt(purchaseForm.getAmount()),purchaseForm.getBank(),purchaseForm.getBranch(),
				attachment,purchaseForm.getRemarks(),"Requested"
				});

				
				if(row>0){
					return true;
				}
					
		
		   }catch(DataAccessException ex){
	            ex.printStackTrace();
	            return false;
	        }
		   
		return false;
		   }
		   
		   return false;
		   
		
	}
	
	
	public int updatePurchaseOrder(HttpSession session,PurchaseOrderModel purchaseForm){

				if(purchaseForm.getStatus().equals("Approved"))
				{
					
					StringBuilder strSQL = new StringBuilder();
					   
					   strSQL.append("update purchase_orders  ");
					   strSQL.append("set status = ?, remarks = ? where id = ? ");
				

					   try{
						   
							int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
							purchaseForm.getStatus(),purchaseForm.getRemarks(),purchaseForm.getId()
							});

							
							if(row>0)
							{
								
								
								   String pid = this.getPid(purchaseForm.getUsername());
					
								   StringBuilder updateSQL = new StringBuilder();
								   
								   updateSQL.append("update wallets set wallet = wallet + ?  ");
								   updateSQL.append("where partnerid = ? and isdefault = ?");
								   
									int row1 = getJdbcTemplate().update(updateSQL.toString(), new Object[] { 
										Float.parseFloat(purchaseForm.getAmount()),pid,1
										});
									
									if(row>0 && row1>0){
										
										return 1;
									}
							
							}
							
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return -1;
				        }
					   
					   
					
				}
					   
				else
				
				{
					StringBuilder strSQL = new StringBuilder();
					   
					   strSQL.append("update purchase_orders  ");
					   strSQL.append("set status = ?, remarks = ? where id = ? ");
				

					   try{
						   
							int row = getJdbcTemplate().update(strSQL.toString(), new Object[] { 
							purchaseForm.getStatus(),purchaseForm.getRemarks(),purchaseForm.getId()
							});

							
							if(row>0){
								
								return 2;
							}
								
					
					   }catch(DataAccessException ex){
				            ex.printStackTrace();
				            return -1;
				        }
					   
			
					
				}
				
				
				return -1;
		 

		
	}
	
	public String getPid(String username){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   String pid = null;
		   
		   strSQL.append("SELECT a.partnerid FROM partners a  ");
		   strSQL.append("INNER JOIN agents_partners b ON a.partner = b.partnerid  ");
		   strSQL.append("INNER JOIN agents c ON b.agentid = c.agentid  ");
		   strSQL.append("WHERE c.username = ?  ");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),username);
			
			for (Map row : rows) {
				
				pid = (String)(row.get("partnerid"));
			
			}
			
			return pid;
			
	}
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRequestList(HttpSession session)
	   {	
		
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		   
		   if(role.equals("manager"))
		   {
			   
	
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   
		   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,c.attachment,d.partnername,d.partnerid  ");
		   strSQL.append("FROM agents a  ");
		   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
		   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
		   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
		   strSQL.append("WHERE   d.parent_partnerid = (select partner from partners where partnerid = ?) and c.status = ?");
		   
		   purchaseorderlist.clear();
		 
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"Requested");
			
			for (Map row : rows) {
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
				String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
				
				
				PurchaseOrderModel PO = new PurchaseOrderModel();
				PO.setId((Integer)(row.get("id")));
				PO.setUsername((String)(row.get("username")));
				PO.setDate((String)(dateAsString));
				PO.setAmount((String)(row.get("amount")+""));
				PO.setBank((String)(row.get("bank")));
				PO.setBranch((String)(row.get("branch")));
				PO.setRemarks((String)(row.get("remarks")));
				PO.setStatus((String)(row.get("status")));
				PO.setPartnername((String)(row.get("partnername")));
				PO.setAttachment((String)(row.get("attachment")));
				
	
				
				purchaseorderlist.add(PO);			
			}
		
			
			
		   }else if (role.equals("superadmin")){
			   
			   
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,c.attachment,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.parent_partnerid is null and c.status = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"Requested");
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					PO.setAttachment((String)(row.get("attachment")));
	
					
					purchaseorderlist.add(PO);			
				}
			   
		   }
		   
		   return purchaseorderlist;
		   	  
	   }  
	
	public List<PurchaseOrderModel>  getSinglePurchaseOrder(HttpSession session, int poid)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   String role = session.getAttribute("USERLEVEL").toString();
		   
		   if(role.equals("manager"))
		   {
			   
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.parent_partnerid = (select partner from partners where partnerid = ?) and c.status = ? and c.id = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),"Requested",poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }else if (role.equals("superadmin")){
			   
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.parent_partnerid is null and c.status = ? and c.id = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"Requested",poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
			   
			   
		   }
			   
		   

		
			return purchaseorderlist;
		   	  
	   } 
	
	public List<PurchaseOrderModel>  getPurchaseOrdersRequestListHistory(HttpSession session,String type)
	   {
		   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
		
		   StringBuilder strSQL = new StringBuilder()	;
		   
		   if(session.getAttribute("USERLEVEL").equals("manager"))
		   
		   
		   {
			   
			   if(type.equalsIgnoreCase("mypohistory"))
			   {
				   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
				   strSQL.append("FROM agents a  ");
				   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
				   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
				   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
				   strSQL.append("WHERE   d.partnerid = ? ");
				   
				   purchaseorderlist.clear();
				 
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
					
					for (Map row : rows) {
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
						String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
						
						
						PurchaseOrderModel PO = new PurchaseOrderModel();
						PO.setId((Integer)(row.get("id")));
						PO.setUsername((String)(row.get("username")));
						PO.setDate((String)(dateAsString));
						PO.setAmount((String)(row.get("amount")+""));
						PO.setBank((String)(row.get("bank")));
						PO.setBranch((String)(row.get("branch")));
						PO.setRemarks((String)(row.get("remarks")));
						PO.setStatus((String)(row.get("status")));
						PO.setPartnername((String)(row.get("partnername")));
						
						purchaseorderlist.add(PO);			
					}
				
					return purchaseorderlist;
				   
			   }else if (type.equalsIgnoreCase("retailerhistory"))
			   {
				   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
				   strSQL.append("FROM agents a  ");
				   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
				   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
				   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
				   strSQL.append("WHERE   d.parent_partnerid = (select partner from partners where partnerid = ?)");
				   
				   purchaseorderlist.clear();
				 
					List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
					
					for (Map row : rows) {
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
						String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
						
						
						PurchaseOrderModel PO = new PurchaseOrderModel();
						PO.setId((Integer)(row.get("id")));
						PO.setUsername((String)(row.get("username")));
						PO.setDate((String)(dateAsString));
						PO.setAmount((String)(row.get("amount")+""));
						PO.setBank((String)(row.get("bank")));
						PO.setBranch((String)(row.get("branch")));
						PO.setRemarks((String)(row.get("remarks")));
						PO.setStatus((String)(row.get("status")));
						PO.setPartnername((String)(row.get("partnername")));
						
						purchaseorderlist.add(PO);			
					}
				
					return purchaseorderlist;
			   }
			   
	
			   	  
		   } 
			   
		   else    if(session.getAttribute("USERLEVEL").equals("user"))
			   
		   {
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.partnerid = ? ");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"));
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;
			   	  
		   } else    if(session.getAttribute("USERLEVEL").equals("superadmin"))
			   
		   {
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE    d.parent_partnerid is null");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
				
		   }
			
				return purchaseorderlist;
			   
			   
		   }
		   
	
	public List<PurchaseOrderModel>  getFinishedPurchasedOrder(HttpSession session, int poid)
	   {
		
		 if(session.getAttribute("USERLEVEL").equals("superadmin"))
			 
		 {
			 
			 
			   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.parent_partnerid is null   and c.id = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;
			 
			 
		 }else{
			 
			   ArrayList<PurchaseOrderModel> purchaseorderlist = new ArrayList<PurchaseOrderModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   
			   strSQL.append("SELECT a.username, c.id,c.requesteddate,c.amount,c.bank,c.branch,c.remarks,c.status,d.partnername,d.partnerid  ");
			   strSQL.append("FROM agents a  ");
			   strSQL.append("INNER JOIN  agents_partners b ON a.agentid = b.agentid    ");
			   strSQL.append("INNER JOIN purchase_orders c ON b.agentid = c.agentid ");
			   strSQL.append("INNER JOIN  partners d ON b.partnerid = d.partner ");
			   strSQL.append("WHERE   d.parent_partnerid = (select partner from partners where partnerid = ?)   and c.id = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),session.getAttribute("PID"),poid);
				
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
					String dateAsString = simpleDateFormat.format(row.get("requesteddate"));
					
					
					PurchaseOrderModel PO = new PurchaseOrderModel();
					PO.setId((Integer)(row.get("id")));
					PO.setUsername((String)(row.get("username")));
					PO.setDate((String)(dateAsString));
					PO.setAmount((String)(row.get("amount")+""));
					PO.setBank((String)(row.get("bank")));
					PO.setBranch((String)(row.get("branch")));
					PO.setRemarks((String)(row.get("remarks")));
					PO.setStatus((String)(row.get("status")));
					PO.setPartnername((String)(row.get("partnername")));
					
					purchaseorderlist.add(PO);			
				}
			
				return purchaseorderlist;
			 
			 
			 
		 }

		   	  
	   } 
	

	


}
