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


public class CreditDAO extends JdbcDaoSupport{
	
	private static final Logger logger = Logger.getLogger(CreditDAO.class);
	
//	public List<CreditLogsModel>  getUnpaidCreditLines()
//	   {
//			
//			   ArrayList<CreditLogsModel> userlogs = new ArrayList<CreditLogsModel>();
//				
//			   StringBuilder strSQL = new StringBuilder()	;
//			   
//			   strSQL.append("SELECT * from credit_logs where status = ? or status = ? ");
//			   
//			   userlogs.clear();
//			 
//			   List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),"partial","unpaid");
//			   
//				for (Map row : rows) {
//					
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//					//String validfr = simpleDateFormat.format(row.get("valid_from"));
//					//String validto = simpleDateFormat.format(row.get("valid_to"));
//					//String due = simpleDateFormat.format(row.get("date_due"));
//					
//					CreditLogsModel logs = new CreditLogsModel();
//					
//					logs.setPid((String)row.get("partnerid"));
//					logs.setBid((String)row.get("branchid"));
//					logs.setAmount((BigDecimal)row.get("amount"));
//					logs.setAmountpaid((BigDecimal)row.get("amount_paid"));
//					logs.setStatus((String)row.get("status"));
//					logs.setApproval_status((String)row.get("approval_status"));
//
//	
//					//logs.setAvailedamount((BigDecimal)(row.get("availed")));
//					//logs.setDuedate(due);
//					userlogs.add(logs);			
//				}
//			
//				return userlogs;
//				
//	   }
//	
//	
	

}
