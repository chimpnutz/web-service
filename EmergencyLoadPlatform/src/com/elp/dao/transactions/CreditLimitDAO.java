package com.elp.dao.transactions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.elp.beans.UserBean;
import com.elp.model.CreditExtendedModel;
import com.elp.model.CreditHistoryModel;
import com.elp.model.CreditLimitModel;
import com.elp.model.CreditLogsModel;


public class CreditLimitDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(CreditLimitDAO.class);
	
	public Map fillBox(HttpSession usersession){
		
		   StringBuilder strSQL = new StringBuilder();
		  				 
		   Map<String,String> prefix = new LinkedHashMap<String,String>();
		   
		  // strSQL.append("SELECT case when branchname is null then branchid else branchname end as branchname, branchid  from branches where partnerid = ?");
		   //strSQL.append("SELECT  branchid  from branches where paymenttype = 'PREPAID' AND partnerid = ?");
		   strSQL.append("SELECT  partnername  from partners order by partnername");
			List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			
			for (Map row : rows) {
				
				prefix.put((String)(row.get("partnername")), (String)row.get("partnername"));
				//prefix.put((String)(row.get("branchid")), (String)row.get("branchid"));
		
			}
			
			return prefix;
			
	}
	


	public List<CreditLimitModel>  getListCreditLimit()
	   {
			
			   ArrayList<CreditLimitModel> userlogs = new ArrayList<CreditLimitModel>();
				
			   StringBuilder strSQL = new StringBuilder()	;
			   
			   strSQL.append("SELECT a.partnerid, a.status, a.status, a.amount, a.credit_term, a.valid_from, a.valid_to, "); 
			   strSQL.append("b.partnerid ,b.partnername from credit_limit a ");
			   strSQL.append("INNER JOIN  partners b ");
			   strSQL.append("on a.partnerid = b.partnerid ");
			   strSQL.append("where a.branchid is null ");
			   userlogs.clear();
			 
			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
			   
				for (Map row : rows) {
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String validfr = simpleDateFormat.format(row.get("valid_from"));
					String validto = simpleDateFormat.format(row.get("valid_to"));
					//String due = simpleDateFormat.format(row.get("date_due"));
					
					CreditLimitModel logs = new CreditLimitModel();
					logs.setPid((String)row.get("partnername"));
					logs.setStatus((String)row.get("status"));
					logs.setAmount((BigDecimal)row.get("amount"));
					logs.setTerm((BigDecimal)row.get("credit_term"));
					logs.setValidfr(validfr);
					logs.setValidto(validto);
		
	
					//logs.setAvailedamount((BigDecimal)(row.get("availed")));
					//logs.setDuedate(due);
					userlogs.add(logs);			
				}
			
				return userlogs;

	   }
	
	
	public int cancel(CreditLimitModel cLimit){
		
		String pid = this.getPID(cLimit.getPid());
		
		StringBuilder approveSQL = new StringBuilder();
		
		approveSQL.append("update credit_limit set status = ? where partnerid = ? and branchid is  null");

	    int row = getJdbcTemplate().update(approveSQL.toString(), new Object[] { 
				"cancelled",pid
		});
		
	    if(row>0){
	    	return 1;
	    }
	    
	    return 0;
		
	}
	
	
	public int update(CreditLimitModel cLimit){
		
		String pid = this.getPID(cLimit.getPid());
		
		StringBuilder approveSQL = new StringBuilder();
		
		approveSQL.append("update credit_limit set amount = amount + ?, credit_term = credit_term + ?, status =? where partnerid = ? and branchid is null");

	    int row = getJdbcTemplate().update(approveSQL.toString(), new Object[] { 
				cLimit.getAmount(),cLimit.getTerm(),"approved",pid
		});
		

	    if(row>0){
	    	return 1;
	    }
	    
	    return 0;
	}
	

	
	
	public int addCreditLimit(CreditLimitModel cLimit){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		DataSource ds = (DataSource) context.getBean("dataSource");
					
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		String pid = this.getPID(cLimit.getPid());
	
		if(pid == null){
			return -1;
		}
		
		BigDecimal amount = cLimit.getAmount();
		BigDecimal term = cLimit.getTerm();
		String frDate = cLimit.getValidfr();
		String toDate = cLimit.getValidto();
		
		StringBuilder approveSQL = new StringBuilder();
		
		approveSQL.append("insert into credit_limit (partnerid,status,amount,credit_term,created_date,valid_from,valid_to) ");
		approveSQL.append("values (?,?,?,?,SYSDATE,TO_DATE(?, 'MM-DD-YYYY'),TO_DATE(?, 'MM-DD-YYYY'))");
		
//		StringBuilder updateSQL = new StringBuilder();
//		
//		approveSQL.append("update credit_limit set status = ?, amount = ?, credit_term = ?, created_date = ?, valid_from = TO_DATE(?, 'MM-DD-YYYY'), valid_to = TO_DATE(?, 'MM-DD-YYYY')  ");
//		approveSQL.append("where partnerid = ? ");
		
		StringBuilder checkifBIDexist = new StringBuilder();
		
		checkifBIDexist.append("select partnerid,valid_from,valid_to from credit_limit where partnerid = ? ");
		checkifBIDexist.append("and valid_to  >= TO_DATE(?, 'MM-DD-YYYY') and branchid is null and status = ? ");
		checkifBIDexist.append("and created_date = (select max(created_date) from credit_limit where partnerid = ?)");
		
		try{
			
		    SqlRowSet rs = jdbcTemplate.queryForRowSet(checkifBIDexist.toString(), new Object[] {pid,frDate,"approved",pid} );	
		
		    if(rs.next()) 
		    {
		    	
		    	return 1;
		    	
		    }
		    else
		    {
		    	
		    	 int row = getJdbcTemplate().update(approveSQL.toString(), new Object[] { 
		 	    	pid,"approved",amount,term,frDate,toDate
		 		});
		 		
		 		if(row>0)
		 		{
		 			
		 			return 0;
		 			
		 		}
		    	
		    }
		
		}
		catch(Exception ex)
		{
        ex.printStackTrace();
 
		}
		
	   
		
		return 0;
	}
	
	public String getPID(String partnername){
		
		String getPid = "select partnerid from partners where partnername = ?";
		
		String pid = null;
		
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(getPid,partnername);
		
		for (Map row : rows) {
			
			pid = (String)(row.get("partnerid"));
			
			return pid;
	
		}
		
		return pid;
	}
	 
	 
	
	 
		


}
