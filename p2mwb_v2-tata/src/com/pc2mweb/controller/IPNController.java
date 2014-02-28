	package com.pc2mweb.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;




import com.payexchange.bayadcenter.ws.BayadCenter;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.soap.api.BasicAmountType;
import com.paypal.soap.api.CurrencyCodeType;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseType;
import com.paypal.soap.api.GetExpressCheckoutDetailsRequestType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseDetailsType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseType;
import com.paypal.soap.api.PayerInfoType;
import com.paypal.soap.api.PaymentActionCodeType;
import com.paypal.soap.api.PaymentDetailsType;
import com.paypal.soap.api.PaymentInfoType;
import com.paypal.soap.api.PaymentStatusCodeType;
import com.paypal.soap.api.SetExpressCheckoutRequestDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestType;
import com.paypal.soap.api.SetExpressCheckoutResponseType;
import com.paysetter.amax.AMAXConstants;
import com.paysetter.commons.pctomobile.P2MConstants;

import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.TopUpResponse;
import com.paysetter.topup.gcash.dao.HandleGcash2Load;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;

import com.pc2mweb.beans.BillerFieldBean;
import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.dao.transactions.BillsPaymentDAO;
import com.pc2mweb.dao.transactions.LopDAO;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("paypalipn")
public class IPNController implements ServletContextAware   {
	
	@Autowired
	private ServletContext servletContext;
	

	private static final Logger logger = Logger.getLogger(IPNController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	 public @ResponseBody String returnIPN(ModelMap model,HttpServletRequest request, HttpSession session) throws MalformedURLException {
				
			
				
				ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
				

				LopDAO dao = (LopDAO)context.getBean("lopDAO");
				
			// read post from PayPal system and add 'cmd'  
		
				Enumeration en = request.getParameterNames();  
				String str = "cmd=_notify-validate";  
				while(en.hasMoreElements()){  
				String paramName = (String)en.nextElement();  
				String paramValue = request.getParameter(paramName);  
				try {
					str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				}  

				URL u;
				try {
					u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
					URLConnection uc = u.openConnection();  
					uc.setDoOutput(true);  
					uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
					PrintWriter pw = new PrintWriter(uc.getOutputStream());  
					pw.println(str);  
					pw.close();  
					
							logger.info("Getting IPN Paramaters........");
					
							BufferedReader in = new BufferedReader(  
							new InputStreamReader(uc.getInputStream()));  
							String res = in.readLine();  
							in.close();  
							
							// assign posted variables to local variables  
							String itemName = request.getParameter("item_name");  
							String itemNumber = request.getParameter("item_number");  
							String paymentStatus = request.getParameter("payment_status");  
							String paymentAmount = request.getParameter("mc_gross");  
							String paymentCurrency = request.getParameter("mc_currency");  
							String txnId = request.getParameter("txn_id");  
							String receiverEmail = request.getParameter("receiver_email");  
							String payerEmail = request.getParameter("payer_email");  
							String custom = request.getParameter("custom");  
							
							logger.info("itemName "+itemName);
							logger.info("itemNumber "+itemNumber);
							logger.info("paymentStatus "+paymentStatus);
							logger.info("paymentAmount "+paymentAmount);
							logger.info("paymentCurrency "+paymentCurrency);
							logger.info("txnId "+txnId);
							logger.info("receiverEmail "+receiverEmail);
							logger.info("payerEmail "+payerEmail);
							logger.info("custom "+custom);
							
							logger.info("res "+res);
		
							if(res.equals("VERIFIED")) 
							{  
								logger.info("Transaction Verified...........");
								
						        SimpleDateFormat  datetodayFormat = new SimpleDateFormat("yyyyMMdd");
						        
						        String datetoday = "LOPPAYPAL"+datetodayFormat.format(new Date())+custom; 
						        
								dao.updatePaypalTx(itemName,itemNumber,paymentStatus,paymentAmount,paymentCurrency,txnId,receiverEmail,payerEmail,custom);
							
//								if(dao.LOPRetailer(Float.parseFloat(paymentAmount),session,datetoday)){
//									
//									return "valid";
//								}
	//
//								return "invalid";
								
								
								
								
								
								return "valid";
							}  
								
							else if(res.equals("INVALID")) 
							
							{  
								
								dao.updatePaypalTx(itemName,itemNumber,paymentStatus,paymentAmount,paymentCurrency,txnId,receiverEmail,payerEmail,custom);
								
								
								
								logger.info("Transaction Invalid...........");
								
								System.out.println("opsss INVALID :( ");  
								
							}  
							else
							{  	
								
								
									dao.updatePaypalTx(itemName,itemNumber,paymentStatus,paymentAmount,paymentCurrency,txnId,receiverEmail,payerEmail,custom);
								
								
								
									logger.info("Transaction Error...........");
									return "invalid";
									
							}
							
							return "invalidqww";
							  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  


				return "invalidqww";
				

		}
	
	

    public boolean isNumber( String tx )  
    {  
       try  
       {  
          Integer.parseInt( tx );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }

	public void setServletContext(ServletContext context) {
		this.servletContext = context;		
	}
	
	

	
//	@ExceptionHandler()
//    public ModelAndView iHandleExceptions(Exception e) {
//        //do loads of interesting stuff to deal with the exception
//		
//		ModelAndView modelAndView = new ModelAndView("error");
//		
//		modelAndView.addObject("error","yes");
//		
//		logger.info(e.getMessage());
//		
//        return modelAndView;
//    }
	
}
