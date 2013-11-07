package com.payexchange.ws.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

public Property() {
        
    }
    
    private Properties getProperties()  {
      
    	Properties prop = new Properties();
 
        try {
			//prop.load(new FileInputStream("iamax.properties"));
        	//	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("iamax.properties"));
			//prop.load(this.getClass().getResourceAsStream("iamax.properties"));
        	// File input = new File("iamax.properties");
            // FileInputStream stream = new FileInputStream(path+"\\"+input);
        	// InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("iamax.properties");
        	String path = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        	String[] urls = path.split("/");
        	StringBuffer url = new StringBuffer("/");
        	for( int i = 1; i<urls.length -1; i++ ) {
        		url.append(urls[i]);
        		url.append("/");
        	}
        	
        	prop.load(new FileInputStream(url.toString() + "epinsupload.properties"));
      
            //System.out.println(prop.getProperty("appName"));
             
         
             
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        return prop;
    }

    
    public String getSoapURL()  {
        String url = this.getProperties().getProperty("url");
        return url;
    }
}
