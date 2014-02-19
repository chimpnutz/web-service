package com.circles.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.circles.dao.EncoderDAO;
import com.circles.model.ApplicationModel;


public class EncoderDAO extends JdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(EncoderDAO.class);
	
	public List<ApplicationModel>  getRecentEncoderList(HttpSession session)
	   {
		   ArrayList<ApplicationModel> purchaseorderlist = new ArrayList<ApplicationModel>();
		
			   StringBuilder strSQL = new StringBuilder()	;

			   strSQL.append("SELECT * FROM application");
			   
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString());
				
				for (Map row : rows) {
					
					
					
					ApplicationModel app = new ApplicationModel();
					
					app.setApplication_id((String)(row.get("application_id")));
					app.setFirstName((String)(row.get("firstname")));
					app.setLastName((String)(row.get("lastname")));
					app.setMiddleName((String)(row.get("middlename")));
					app.setFullname(app.getFirstName()+" "+app.getLastName());
					
					purchaseorderlist.add(app);			
				}
			
				return purchaseorderlist;

			   
	
	   }
	public List<ApplicationModel>  getApplicationList(HttpSession session, int appid)
	   {
		   ArrayList<ApplicationModel> purchaseorderlist = new ArrayList<ApplicationModel>();
		
			   StringBuilder strSQL = new StringBuilder()	;

			   strSQL.append("SELECT * FROM application ");
			   strSQL.append("WHERE application_id = ?");
			   
			   purchaseorderlist.clear();
			 
				List<Map<String,Object>> rows = getJdbcTemplate().queryForList(strSQL.toString(),appid);
				
				for (Map row : rows) {
					
					
					
					ApplicationModel app = new ApplicationModel();
					
					app.setApplication_id((String)(row.get("application_id")));
					app.setBirthday((BigInteger)(row.get("birthday")));
					app.setBirthPlace((String)(row.get("birthplace")));
					app.setOccupation((String)(row.get("occupation")));
					app.setPositionLevel((String)(row.get("position_level")));
					app.setTin((String)(row.get("tin")));
					app.setSss((String)(row.get("sss")));
					app.setPositionTitle((String)(row.get("positionTitle")));
					app.setTenure((String)(row.get("tenure")));
					app.setMonthlyIncome((String)(row.get("monthlyIncome")));
					app.setCompanyName((String)(row.get("companyName")));
					app.setBusinessNature((String)(row.get("businessNature")));
					app.setCcBank((String)(row.get("ccBank")));
					app.setCivilStatus((String)(row.get("civilStatus")));
					app.setGender((String)(row.get("gender")));
					app.setDependents((int)(row.get("dependents")));
					app.setType((int)(row.get("type")));
					
					app.setFirstName((String)(row.get("firstname")));
					app.setLastName((String)(row.get("lastname")));
					app.setMiddleName((String)(row.get("middlename")));
					
					purchaseorderlist.add(app);			
				}
			
				return purchaseorderlist;

			   
	
	   }		
		   
}
