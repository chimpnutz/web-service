package com.elp.dao.transactions;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.elp.beans.UserBean;


public class UserDAO {
	
	public UserBean getUserBean(String username){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		DataSource ds = (DataSource) context.getBean("dataSource");
					
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		StringBuilder strSQL = new StringBuilder();
		
		UserBean bean = new UserBean();

		
		strSQL.append("select a.username,a.password,");
		strSQL.append("b.rolename, ");
		strSQL.append("c.companyname, ");
		strSQL.append("d.levelid,d.roleid ");
		strSQL.append("from user a, roles b, company_tbl c, role_level_permissions d  ");
		strSQL.append("where a.roleid = b.roleid and a.companyid = c.companyid ");
		strSQL.append(" AND b.roleid = d.roleid AND a.username = ?");
	    Object[] parameters = {username};
	    
	    SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), parameters);
	
	    if(rs.next()) {

            bean.setUserName(rs.getString("username"));
            bean.setPassword(rs.getString("password"));
            bean.setRolename(rs.getString("rolename"));
            bean.setCompanyname(rs.getString("companyname"));
            bean.setLevelid(rs.getInt("levelid"));
            bean.setRoleid(rs.getInt("roleid"));
     

	    }
	    return bean;
	    	        
	 }

}
