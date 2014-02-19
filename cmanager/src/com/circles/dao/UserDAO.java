package com.circles.dao;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.beans.UserBean;

public class UserDAO {

	public UserBean getUserBean(String username)
	  {
	    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");

	    DataSource ds = (DataSource)context.getBean("dataSource");

	    JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

	    StringBuilder strSQL = new StringBuilder();

	    UserBean bean = new UserBean();

	    strSQL.append("SELECT userid, password, email, role ");
	    strSQL.append("FROM user ");
	    strSQL.append(" WHERE userid = ?");

	    Object[] parameters = { username };

	    SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), parameters);

	    if (rs.next())
	    {
	      bean.setEmail(rs.getString("email"));
	      bean.setUserName(rs.getString("userid"));
	      bean.setUserLevel(rs.getString("role"));
	      bean.setPassword(rs.getString("password"));
	      
	    }

	    return bean;
	  }
}
