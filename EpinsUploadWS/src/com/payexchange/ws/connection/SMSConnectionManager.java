package com.payexchange.ws.connection;

import java.beans.PropertyVetoException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.payexchange.ws.utility.EpinProperty;
import com.payexchange.ws.utility.SMSProperty;

public class SMSConnectionManager {

	private static String driver;
    private static String url;
    private static String userid;
    private static String pwd;
    private static Connection connection;
    private static SMSProperty property;
    private static DataSource dataSource;

    private static final Logger logger = Logger.getLogger(ConnectionManager.class);

    /**
     * @return the driver
     */
    public static String getDriver() {
        return driver;
    }

    /**
     * @param aDriver the driver to set
     */
    public static void setDriver(String aDriver) {
        driver = aDriver;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }

    /**
     * @return the userid
     */
    public static String getUserid() {
        return userid;
    }

    /**
     * @param aUserid the userid to set
     */
    public static void setUserid(String aUserid) {
        userid = aUserid;
    }

    /**
     * @return the pwd
     */
    public static String getPwd() {
        return pwd;
    }

    /**
     * @param aPwd the pwd to set
     */
    public static void setPwd(String aPwd) {
        pwd = aPwd;
    }


    public static ComboPooledDataSource createDataSource() {
    	
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass((getDriver()));
        } catch (PropertyVetoException ex) {
        	 System.out.println(ex.getMessage());
        	 System.out.println("stack trace: " + ExceptionUtils.getFullStackTrace(ex));
            return null;
        }

        cpds.setJdbcUrl(getUrl());
        cpds.setUser(getUserid());
        cpds.setPassword(getPwd());
        cpds.setMaxStatements(180);
        cpds.setMaxPoolSize(50);
        cpds.setMinPoolSize(15);
        cpds.setAcquireIncrement(3);
        cpds.setAcquireRetryAttempts(0);
        cpds.setAcquireRetryDelay(3000);
        cpds.setBreakAfterAcquireFailure(false);
        cpds.setMaxConnectionAge(60);
        cpds.setMaxIdleTime(30);
        cpds.setMaxIdleTimeExcessConnections(10);
        cpds.setIdleConnectionTestPeriod(15);
        cpds.setTestConnectionOnCheckout(true);
        cpds.setPreferredTestQuery("SELECT 1");
        cpds.setDebugUnreturnedConnectionStackTraces(true);
        cpds.setAutoCommitOnClose(true);
        return cpds;
    }


    

    public SMSConnectionManager(){

    }

    static{

        property = new SMSProperty();
        driver = property.getDBFDriver();
        url = property.getDBFURL();
        pwd = property.getDBFPassword();
        userid = property.getDBFUser();
    	
        dataSource = createDataSource();
            
    }

    public static void closeDBResources(Object dbObject) {
        try{
            if (dbObject!=null && dbObject instanceof Connection){
                ((Connection)dbObject).close();
            } else if (dbObject!=null &&  dbObject instanceof ResultSet){
                ((java.sql.ResultSet)dbObject).close();
            } else if (dbObject!=null &&  dbObject instanceof Statement){
                ((Statement)dbObject).close();
            } else if (dbObject!=null &&  dbObject instanceof PreparedStatement){
                ((PreparedStatement)dbObject).close();
            } else if (dbObject!=null &&  dbObject instanceof CallableStatement){
                ((CallableStatement)dbObject).close();
            }
        } catch(SQLException sqlex) {}
           
    
        }

    /**
     * @return the connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
