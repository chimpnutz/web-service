package com.payexchange.epins.ws.dao;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.payexchange.ws.utility.Property;
import com.payexchange.ws.dao.EpinsUploadDAOSEI;
import com.payexchange.ws.dao.EpinsUploadDAOService;
import com.payexchange.ws.dao.EpinsUploadResponse;

public class EpinsUpload {
	
	private static final QName SERVICE_NAME = new QName("http://dao.ws.payexchange.com/", "EpinsUploadDAOService");
	
	public static String soapurl;
    
    private static Property property;
    
    public EpinsUploadResponse epinsupload(String Trace, String ProdCode, int Qty, String type, String target, String appname, String ipaddress, String trantype, String Denom, String message, String username, String password, String transid){
    
    

		
					URL wsdlURL = null;
					
					property = new Property();
					
					soapurl = property.getSoapURL();
					
			        try {
			
			        	wsdlURL = new URL(soapurl);
			        } catch (MalformedURLException e) {
			            e.printStackTrace();
			        }
		
		   EpinsUploadDAOService ss = new EpinsUploadDAOService(wsdlURL, SERVICE_NAME);
	       EpinsUploadDAOSEI port = ss.getEpinsUploadDAOPort();  	
	       
	       EpinsUploadResponse response = new EpinsUploadResponse();
	       
	       System.out.println("Invoking EpinsUpload...");
	       

	        com.payexchange.ws.dao.EpinsUploadResponse _reqUpload__return = port.reqUpload(Trace, ProdCode, Qty, type, target, appname, ipaddress, trantype, Denom, message, username, password, transid);
	        
	        response.setPassword(_reqUpload__return.getPassword());
	        response.setResultcode(_reqUpload__return.getResultcode());
	        response.setTracenumber(_reqUpload__return.getTracenumber());
	        System.out.println("reqUpload.result=" + _reqUpload__return.getPassword());
	        System.out.println("reqUpload.result=" + _reqUpload__return.getResultcode());
	        System.out.println("reqUpload.result=" + _reqUpload__return.getTracenumber());
	        
	        System.out.println("EpinsUpload.result=" + _reqUpload__return);
			return response;
	        
	        

    }
}
