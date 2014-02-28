	package com.pc2mweb.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.pc2mweb.model.LopModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.utility.function.PaypalProps;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("lop")
public class LOPController implements ServletContextAware   {
	
	@Autowired
	private ServletContext servletContext;
	

	private static final Logger logger = Logger.getLogger(LOPController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("lop");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = dao.getWallet(session);
				
				modelAndView.addObject("lopForm", new LopModel());
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}		
	


	@RequestMapping(method = RequestMethod.GET,params={"cancel"})
	 public ModelAndView cancelCheckout(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("lop");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = dao.getWallet(session);
				
				modelAndView.addObject("lopForm", new LopModel());
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}
	
	@RequestMapping(method = RequestMethod.GET,params={"return"})
	 public ModelAndView returnCheckout(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("lop");
		ModelAndView redirect = new ModelAndView("redirect:main.html");

			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {	

				
				String token = session.getAttribute("TOKEN").toString();
				
				System.out.println(token);
				
				try {
					boolean isComplete = this.doExpressCheckoutService(this.getExpressCheckoutDetails(token));
					Float wallet = dao.getWallet(session);
					modelAndView.addObject("lopForm", new LopModel());
					modelAndView.addObject("currentwallet", wallet);
					modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
					if(isComplete)
					{
					
						

						modelAndView.addObject("msg","Payment Successful.");
						
					}
				} catch (PayPalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}
				
				
			
				return modelAndView;

				
				}
			
		}
	
	@RequestMapping(method = RequestMethod.POST)
	  public ModelAndView getBillerfield(@ModelAttribute("lopForm") LopModel lop, HttpSession usersession) throws NamingException, ParseException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ApplicationContext  smscontext = new ClassPathXmlApplicationContext("Spring-SMS.xml");
		
		LopDAO dao = (LopDAO)context.getBean("lopDAO");
		
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		
		OutgoingSMSWriterDAO smsdao =  (OutgoingSMSWriterDAO)smscontext.getBean("smsDAO");
		
		String pid = (String) usersession.getAttribute("PID");
		lop.setPid(pid);	
		String bid = (String) usersession.getAttribute("BID");
		lop.setBid(bid);
		
	
		
		lop.setRequestype("LOP");
		lop.setTxtype("LOP");
		lop.setProdtype("LOP");

		Float wallet = dao.getWallet(usersession);
		
		ModelAndView modelAndView = new ModelAndView("lop");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		modelAndView.addObject("topupForm", new TopupModel());
		modelAndView.addObject("currentwallet", wallet);
		modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));	
		

		PartnerProfile partner = null;

		int errorState = 0;


		
		try {
			logger.info("get profile: " + usersession.getAttribute("USER"));
			partner = profiledao.GetProfilebyUsername(usersession.getAttribute("USER").toString());
			lop.agentid = partner.agentid;
			lop.txid = dao.insertTransaction(lop,usersession);
			logger.info("topup tx id is: "+lop.txid);
						

			
			
			logger.info("topup tx id is: "+lop.txid);
		} catch (Exception e) {
			e.printStackTrace();	
			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
			logger.info(P2MConstants.getMessage(errorState));
			//return "fail";
		}
		
		

		if ((partner.wallet <= 0) || (Float.parseFloat(lop.getAmount()) <= 0) || ( partner.wallet < Float.parseFloat(lop.getAmount()) )) {
			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}

		if ( errorState != 0 ) {
			
			dao.updateTransaction(lop.txid,errorState,"0");


			logger.info(P2MConstants.getMessage(errorState));
		
			//return "fail";
		}

		int debit = 0;
		String session = null;
		String resp = null;
			
			if(partner.runmode.equalsIgnoreCase("TEST"))
			
			{
				
				String paymenttype = (String) usersession.getAttribute("paymenttype");
				
				if(paymenttype.equalsIgnoreCase("PREPAID"))
				{
					
					dao.deductWallet(usersession, lop.txid, Float.parseFloat(lop.getAmount()));

					
					logger.info("deducting prepaid wallet done");
				}
				else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
					
					dao.creditWallet(usersession, lop.txid, Float.parseFloat(lop.getAmount()));
					
					logger.info("deducting settlement done");
					
				}
				else if(paymenttype.equalsIgnoreCase("FEES")){
					
					dao.creditWallet(usersession, lop.txid, Float.parseFloat(lop.getAmount()));
					
					logger.info("deducting fees wallet done");
				}
				

			}
			
			
			else
			
			{
				

				
				
				try {
				

					String paymenttype = (String) usersession.getAttribute("paymenttype");
					
					if(paymenttype.equalsIgnoreCase("PREPAID"))
					{
						
						debit = dao.updatetxid(usersession, lop.txid, Float.parseFloat(lop.getAmount()) * -1);
						
						
						logger.info("deducting prepaid wallet done");
					}
					else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
						
						debit = dao.creditWallet(usersession, lop.txid, Float.parseFloat(lop.getAmount()));
					
						
						logger.info("deducting settlement done");
						
					}
					else if(paymenttype.equalsIgnoreCase("FEES")){
						
						debit = dao.creditWallet(usersession, lop.txid, Float.parseFloat(lop.getAmount()));
						
						
						logger.info("deducting fees wallet done");
					}
					
				logger.info("Debit result: " + debit);
				logger.info("+++++++++++++++++++LOP++++++++++++++++");
				
				if (debit != 1) {
					logger.info("Unable to deduct from wallet from  " + partner.partnerid + "; Amount: " + lop.getAmount());	
					errorState = P2MConstants.DEBIT_FAILED_CODE;				
				}
				
				else		
				{


					PaypalProps props = new PaypalProps();
					//the parameters for the service
					Long userId = 5l;
//					String amount = "25";
//					String returnURL = "http://localhost:8080/p2mwb_v2/jsp/lop.html?return";
//					String cancelURL = "http://localhost:8080/p2mwb_v2/jsp/lop.html?cancel";
					
					String returnURL = props.getReturn();
					String cancelURL = props.getCancel();
					
					
					PaymentActionCodeType paymentAction = PaymentActionCodeType.Sale;
					
					CurrencyCodeType currencyCode = CurrencyCodeType.USD;

					try {
						//calling the service, setting up the checkoutpage
						logger.info("Checking Out..........");
						
						String token = this.setExpressCheckout(userId, lop.txid, lop.getAmount(), currencyCode, returnURL,cancelURL,paymentAction);
					
						logger.info("Url to redirect to: https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=" + token);
						
//						ModelAndView paypalredirect = new ModelAndView("redirect:https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=" + token);
						
						ModelAndView paypalredirect = new ModelAndView("redirect:"+props.getRedirect()+""+token);
						
						usersession.setAttribute("TOKEN", token);
						
						dao.updateTransactionToken(lop.txid , token);
						
						return paypalredirect;
						
					} catch (PayPalException e) {
						logger.error(e);
					}
					
					
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
				
				// Refund wallet
				if ( debit == 1 ) {
				//	walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
					dao.updatetxid(usersession, lop.txid, Float.parseFloat(lop.getAmount()));
				}
			}
			try {
				//logger.info("ErrorState on update for txn " + bill.txid + " = " + errorState);
				

				

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			
				
			}
				
				
				
				
			}
		

		logger.info("+++++++++++++++++++LOP Payment Status! "+resp+" ++++++++++++++++");
		return modelAndView;


		
	}
	
	
	
	public String setExpressCheckout(Long userId, Long txid, String paymentAmount,
			CurrencyCodeType currencyCodeType, String returnURL, String cancelURL,
				PaymentActionCodeType paymentAction) throws PayPalException{
		CallerServices caller = new CallerServices();

		//construct and set the profile, these are the credentials we establish as "the shop" with Paypal
//		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
//		profile.setAPIUsername("p2mweb_api1.yahoo.com");
//		profile.setAPIPassword("1377766335");
//		profile.setSignature("AfTUigirOi4Z-pzTrHndW1OFUbN1AFDvHkUqkrxb6ZggeSpmkwJ.MyBf");
//		profile.setEnvironment("sandbox");
//		caller.setAPIProfile(profile);
		
		logger.info("Setting Paypal API Profile for SetExpressCheckout..........");
		
		PaypalProps props = new PaypalProps();
		
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(props.getPaypalUsername());
		profile.setAPIPassword(props.getPaypalPassword());
		profile.setSignature(props.getPaypalSignature());
		profile.setEnvironment(props.getPaypalEnvironment());
		caller.setAPIProfile(profile);


		//construct the request
		SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
		pprequest.setVersion("63.0");

		//construct the details for the request
		SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setOrderDescription("Autoload Max LOP");
		paymentDetails.setInvoiceID("INVOICE-" + Math.random());
		//paymentDetails.setCustom(txid+"");
		//paymentDetails.setInvoiceID(txid+"");
		BasicAmountType orderTotal = new BasicAmountType(paymentAmount);
		orderTotal.setCurrencyID(currencyCodeType);
		paymentDetails.setOrderTotal(orderTotal);
		paymentDetails.setPaymentAction(paymentAction);
		details.setPaymentDetails(new PaymentDetailsType[]{paymentDetails});

		details.setReturnURL(returnURL);
		details.setCancelURL(cancelURL);
		details.setCustom(txid.toString());

		//set the details on the request
		pprequest.setSetExpressCheckoutRequestDetails(details);

		//call the actual webservice, passing the constructed request
		
		logger.info("Calling SetExpressCheckout......");
		
		SetExpressCheckoutResponseType ppresponse = (SetExpressCheckoutResponseType) caller.call("SetExpressCheckout", pprequest);

		//get the token from the response
		return ppresponse.getToken();
	}



	public GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetails(String token) throws PayPalException{
		CallerServices caller = new CallerServices();

//		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
//		profile.setAPIUsername("p2mweb_api1.yahoo.com");
//		profile.setAPIPassword("1377766335");
//		profile.setSignature("AfTUigirOi4Z-pzTrHndW1OFUbN1AFDvHkUqkrxb6ZggeSpmkwJ.MyBf");
//		profile.setEnvironment("sandbox");
//		caller.setAPIProfile(profile);
		
		logger.info("Setting Paypal API Profile for GetExpressCheckoutDetails..........");
		
		PaypalProps props = new PaypalProps();
		
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(props.getPaypalUsername());
		profile.setAPIPassword(props.getPaypalPassword());
		profile.setSignature(props.getPaypalSignature());
		profile.setEnvironment(props.getPaypalEnvironment());
		caller.setAPIProfile(profile);



		GetExpressCheckoutDetailsRequestType pprequest = new GetExpressCheckoutDetailsRequestType();
		pprequest.setVersion("63.0");
		pprequest.setToken(token);

		
		logger.info("Calling GetExpressCheckoutDetails..........");
		GetExpressCheckoutDetailsResponseType ppresponse = (GetExpressCheckoutDetailsResponseType)caller.call("GetExpressCheckoutDetails", pprequest);

		return ppresponse.getGetExpressCheckoutDetailsResponseDetails();
	}
	
	
	public boolean doExpressCheckoutService(GetExpressCheckoutDetailsResponseDetailsType response) throws PayPalException{
		CallerServices caller = new CallerServices();

//		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
//		profile.setAPIUsername("p2mweb_api1.yahoo.com");
//		profile.setAPIPassword("1377766335");
//		profile.setSignature("AfTUigirOi4Z-pzTrHndW1OFUbN1AFDvHkUqkrxb6ZggeSpmkwJ.MyBf");
//		profile.setEnvironment("sandbox");
//		caller.setAPIProfile(profile);
		
		PaypalProps props = new PaypalProps();
		
		logger.info("Setting Paypal API Profile for doExpressCheckoutService..........");
		
		
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(props.getPaypalUsername());
		profile.setAPIPassword(props.getPaypalPassword());
		profile.setSignature(props.getPaypalSignature());
		profile.setEnvironment(props.getPaypalEnvironment());
		caller.setAPIProfile(profile);

		DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
		pprequest.setVersion("63.0");

		DoExpressCheckoutPaymentResponseType ppresponse= new DoExpressCheckoutPaymentResponseType();

		DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();
		paymentDetailsRequestType.setToken(response.getToken());

		PayerInfoType payerInfo = response.getPayerInfo();
		paymentDetailsRequestType.setPayerID(payerInfo.getPayerID());

		PaymentDetailsType paymentDetails = response.getPaymentDetails(0);
		paymentDetailsRequestType.setPaymentAction(paymentDetails.getPaymentAction());

		paymentDetailsRequestType.setPaymentDetails(response.getPaymentDetails());
		pprequest.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);
		
		
		
		logger.info("Calling DoExpressCheckoutPayment..........");
		
		
		ppresponse= (DoExpressCheckoutPaymentResponseType) caller.call("DoExpressCheckoutPayment", pprequest);
		DoExpressCheckoutPaymentResponseDetailsType type = ppresponse.getDoExpressCheckoutPaymentResponseDetails();

		if (type != null){
			PaymentInfoType paymentInfo = type.getPaymentInfo(0);
			logger.info(paymentInfo.getPaymentStatus());
			if (paymentInfo.getPaymentStatus().equals(PaymentStatusCodeType.fromString("Completed"))){

				logger.info("Payment completed.");
				return true;
			}
			else {
				logger.info("Payment not completed.. (" + paymentInfo.getPaymentStatus() + ")");
				return false;
			}
		}
		else {
			logger.info("Problem executing DoExpressCheckoutPayment.. Maybe you tried to process an ExpressCheckout that has already been processed.");
			return false;
		}
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
