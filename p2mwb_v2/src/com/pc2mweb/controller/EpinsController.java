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
import com.payexchange.epins.ws.dao.EpinsUpload;
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
import com.pc2mweb.dao.transactions.EpinDAO;
import com.pc2mweb.dao.transactions.LopDAO;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.EpinModel;
import com.pc2mweb.model.LopModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.services.EpinsUploadServices;
import com.pc2mweb.services.LoadCustomerServices;
import com.pc2mweb.utility.function.PaypalProps;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("epins")
public class EpinsController implements ServletContextAware   {
	
	@Autowired
	private ServletContext servletContext;
	

	private static final Logger logger = Logger.getLogger(EpinsController.class);

	@RequestMapping(method = RequestMethod.GET,params={"bulk"})
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EpinDAO dao = (EpinDAO)context.getBean("epinDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("epins");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = dao.getWallet(session);
				Map fillbox = dao.fillBox();
				Map fillprodtype = dao.fillprodtype();
				
				modelAndView.addObject("epinForm", new EpinModel());
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillprodtype", fillprodtype);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				modelAndView.addObject("type", "bulk");
				return modelAndView;
				
				}
			
		}		
	


	@RequestMapping(method = RequestMethod.GET,params={"individual"})
	 public ModelAndView cancelCheckout(ModelMap model,HttpServletRequest request, HttpSession session) {
	//	
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EpinDAO dao = (EpinDAO)context.getBean("epinDAO");

		
		ModelAndView modelAndView = new ModelAndView("epins");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = dao.getWallet(session);
				Map fillbox = dao.fillBox();
				Map fillprodtype = dao.fillprodtype();
				
				
				modelAndView.addObject("epinForm", new EpinModel());
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("type", "individual");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}
	

	@RequestMapping(method = RequestMethod.POST)
	  public ModelAndView getBillerfield(@ModelAttribute("epinForm") EpinModel epins, HttpSession usersession) throws NamingException, ParseException {
		
		
		EpinsUploadServices services = new EpinsUploadServices();
		
		return services.epinsUpload(epins, usersession, servletContext);
	
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
