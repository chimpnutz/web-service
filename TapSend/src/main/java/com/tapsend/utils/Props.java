package com.tapsend.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Props {

    public Props() {
        
    }
    
    private Properties getProperties()  {
      
    	Properties prop = new Properties();
 
        try {
        	
			//prop.load(new FileInputStream("iamaxws.properties"));
        	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
        	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
          
        return prop;
    }
    
    public String getPath()  {
        String path = this.getProperties().getProperty("path");
        return path;
    }
  
    public String getSender()  {
        String sender = this.getProperties().getProperty("sender");
        return sender;
    }
    
    public String getRecipients()  {
        String recipient = this.getProperties().getProperty("recipient");
        return recipient;
    }


}

