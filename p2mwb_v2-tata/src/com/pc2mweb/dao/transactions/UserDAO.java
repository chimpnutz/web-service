package com.pc2mweb.dao.transactions;

import com.pc2mweb.beans.UserBean;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserDAO
{
  public UserBean getUserBean(String username)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");

    DataSource ds = (DataSource)context.getBean("dataSource");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

    StringBuilder strSQL = new StringBuilder();

    UserBean bean = new UserBean();

    strSQL.append("SELECT a.agentid, a.username,a.password,b.roleid,c.partnerid,c.partnername,c.email,c.runmode,d.rolename,e.paymenttype,e.walletid ");
    strSQL.append("FROM agents a  ");
    strSQL.append("INNER JOIN agents_partners b ");
    strSQL.append("ON a.agentid = b.agentid ");
    strSQL.append("INNER JOIN partners c ");
    strSQL.append(" ON b.partnerid = c.partner ");
    strSQL.append(" INNER JOIN roles d ");
    strSQL.append(" ON b.roleid = d.roleid ");
    strSQL.append(" INNER JOIN wallets e ");
    strSQL.append(" ON c.partnerid = e.partnerid ");
    strSQL.append(" WHERE a.username = ? AND a.status = ? AND e.isdefault = ?");

    Object[] parameters = { username, "active", Integer.valueOf(1) };

    SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), parameters);

    if (rs.next())
    {
      bean.setEmail(rs.getString("email"));
      bean.setUserName(rs.getString("username"));
      bean.setUserLevel(rs.getString("rolename"));
      bean.setPassword(rs.getString("password"));
      bean.setPid(rs.getString("partnerid"));
      bean.setAid(rs.getString("agentid"));
      bean.setPaymenttype(rs.getString("paymenttype"));
      bean.setWalletid(rs.getInt("walletid"));
      bean.setRunmode(rs.getString("runmode"));
      bean.setPartnername(rs.getString("partnername"));
      
    }

    return bean;
  }
}