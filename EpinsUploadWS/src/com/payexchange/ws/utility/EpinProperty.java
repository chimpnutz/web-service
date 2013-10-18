package com.payexchange.ws.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EpinProperty {

    //private String propertyFile;
    
    public EpinProperty() {
        
    }
    
    private Properties getProperties()  {
      
    	Properties prop = new Properties();
 
        try {
        	
			//prop.load(new FileInputStream("iamaxws.properties"));
        	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("epins.properties"));
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
     
      
      
      
      
}

