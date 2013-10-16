package com.payexchange.ws.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property2 {

//private String propertyFile;
    
    public Property2() {
        
    }
    
    private Properties getProperties()  {
      
    	Properties prop = new Properties();
 
        try {
        	
			//prop.load(new FileInputStream("iamaxws.properties"));
        	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("iamaxws2.properties"));
        	//prop.load(new FileInputStream("gmax.properties"));
        	
        	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
          
        return prop;
    }

    public String getDBFDriver()  {
        String dbfdriver = this.getProperties().getProperty("dbdriver");
        return dbfdriver;
    }
  
    public String getDBFURL()  {
        String dbfURL = this.getProperties().getProperty("dbURL");
        return dbfURL;
    }
      public String getDBFUser()  {
        String dbfuser = this.getProperties().getProperty("dbuser");
        return dbfuser;
    }
      public String getDBFPassword()  {
        String dbfpassword = this.getProperties().getProperty("dbpassword");
        return dbfpassword;
    }
      public String getAmaxHost()  {
          String amax_host = this.getProperties().getProperty("amax_host");
          return amax_host;
    }
      public String getAmaxURI()  {
          String amax_uri = this.getProperties().getProperty("amax_uri");
          return amax_uri;
    }
      
      public String getGCashHost()  {
          String gcashhost = this.getProperties().getProperty("gcash.host");
          return gcashhost;
      }
    
      public String getGCashUri()  {
          String gcashuri = this.getProperties().getProperty("gcash.uri");
          return gcashuri;
      }
        public String getGCashUser()  {
          String gcashuser = this.getProperties().getProperty("gcash.user");
          return gcashuser;
      }
        public String getGcashPass()  {
          String gcashpass = this.getProperties().getProperty("gcash.pass");
          return gcashpass;
      }
        public String getGcashServiceMpin()  {
            String gcashmpin = this.getProperties().getProperty("gcash.service.mpin");
            return gcashmpin;
      }
        public String getGcashAppname()  {
            String gcashappname = this.getProperties().getProperty("gcash.appname");
            return gcashappname;
      }
}
