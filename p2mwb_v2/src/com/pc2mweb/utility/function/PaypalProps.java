package com.pc2mweb.utility.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PaypalProps {

    public PaypalProps() {
        
    }
    
    private Properties getProperties()  {
      
    	Properties prop = new Properties();
 
        try {
        	
			//prop.load(new FileInputStream("iamaxws.properties"));
        	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("paypal.properties"));
        	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
          
        return prop;
    }
    
    public String getReturn()  {
        String path = this.getProperties().getProperty("returnurl");
        return path;
    }
  
    public String getCancel()  {
        String sender = this.getProperties().getProperty("cancelurl");
        return sender;
    }
    
    public String getRedirect()  {
        String recipient = this.getProperties().getProperty("redirecturl");
        return recipient;
    }

    
    public String getPaypalUsername()  {
        String recipient = this.getProperties().getProperty("username");
        return recipient;
    }
    
    public String getPaypalPassword()  {
        String recipient = this.getProperties().getProperty("password");
        return recipient;
    }
    
    public String getPaypalSignature()  {
        String recipient = this.getProperties().getProperty("signature");
        return recipient;
    }
    
    public String getPaypalEnvironment()  {
        String recipient = this.getProperties().getProperty("env");
        return recipient;
    }

}
