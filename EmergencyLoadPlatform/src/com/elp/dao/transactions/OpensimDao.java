package com.elp.dao.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.elp.model.OpenSimModel;
import com.elp.model.UserManagementModel;

public class OpensimDao extends JdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(OpensimDao.class);
	
	public Map getopensimlist(){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> opensim = new LinkedHashMap<String,String>();
		   
		   strSQL.append("SELECT opensim FROM opensim_tbl");
			
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				opensim.put((String)(row.get("opensim")), (String)row.get("opensim"));//dito ako
		
			}
			
			return opensim;
			
	}
	
	
	public List<OpenSimModel> getOpenTx(String opensim)
	   {
				System.out.println(opensim);
			   ArrayList<OpenSimModel> openList= new ArrayList<OpenSimModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   //strSQL.append("select balance_date, opensim, balance from opensim_balance_history where opensim = ?");
			   strSQL.append("SELECT DATE_FORMAT(balance_date,'%d %b %Y %T') as date,opensim,balance   ");
			   strSQL.append("FROM opensim_balance_history WHERE opensim= ?");
			   
			   
			   openList.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),opensim);
			   
				for (Map row : rows) {

					OpenSimModel opensimmod = new OpenSimModel();
					
					opensimmod.setDate((String)row.get("date"));
					opensimmod.setOpensim((String)row.get("opensim"));
					opensimmod.setBalance((Float)row.get("balance"));
					
					openList.add(opensimmod);			
				}
			
				return openList;
				
	   }
}
