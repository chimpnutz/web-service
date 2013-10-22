package com.payexchange.ws.dao;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.payexchange.ws.beans.DetailsBean;
import com.payexchange.ws.beans.EpinsUploadResponse;


@WebService(targetNamespace = "http://dao.ws.payexchange.com/", endpointInterface = "com.payexchange.ws.dao.EpinsUploadDAOSEI", portName = "EpinsUploadDAOPort", serviceName = "EpinsUploadDAOService")
public class EpinsUploadDAO   {

	@Resource
	WebServiceContext wsContext; 
	
	public EpinsUploadResponse reqUpload(String Trace, String ProdCode, int Qty,
			   String type,String target,String appname, String ipaddress, String trantype, String Denom, String email, String username, String password, String transid){
			
		  EpinsUploadResponse responseCode = null;
		 		  
		  DetailsBean bean = new  DetailsBean();
		   
		  HandleEpins Upload = new HandleEpins();
		   
		   MessageContext mc = wsContext.getMessageContext();
		   HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
		   System.out.println("Client IP = " + req.getRemoteAddr()); 
		   String ip = req.getRemoteAddr().toString();
		   

		   bean.setTrace(Trace);
		   bean.setProdCode(ProdCode);
		   bean.setQty(Qty);
		   bean.setType(type);
		   bean.setTarget(target);
		   bean.setAppname(appname);
		   bean.setIpaddress(ip);
		   bean.setTrantype(trantype);
		   bean.setDenom(Denom);
		   bean.setEmail(email);
		   bean.setUsername(username);
		   bean.setPassword(password);
		   bean.setTransid(transid);
		  
		   try {
			responseCode = Upload.reqUpload(bean);
			return responseCode;	
		   } catch (Exception e) 
		   {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   
		   return responseCode;
		  	   
		
	}
}
