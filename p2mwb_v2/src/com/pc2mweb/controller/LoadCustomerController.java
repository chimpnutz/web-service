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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;




import com.paysetter.amax.AMAXConstants;
import com.paysetter.commons.pctomobile.P2MConstants;

import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.TopUpResponse;
import com.paysetter.topup.gcash.dao.HandleGcash2Load;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;

import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.services.LoadCustomerServices;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("loadcustomer")
public class LoadCustomerController implements ServletContextAware  {
	
	@Autowired
	private ServletContext servletContext;
	
	//private LoadCustomerServices services;
	
	private static DataSource ds;
	private static DataSource SMSds;
	private static ProfileManagerDAO profileManager  = null;
	//private static WalletManager  walletManager   = null;
	private static GlobeUtil globeUtil;
	private static TouchMobileUtil tmUtil;
	
	private static String GHP_NUMRANGE  = null;
	private static String TM_NUMRANGE   = null;
	private static String SMART_NUMRANGE   = null;
	private static String SUN_NUMRANGE   = null;
	
	private static String AMAX_HOST     = null;
	private static String AMAX_URI      = null;
	private static String AMAX_USER     = null;
	private static String AMAX_PASSWORD = null;
	
	private static String dbContext;
	private static String smsDbContext = null;
	
	private static String AMAX_DEBUG = null;
	private static String TOPUP_TYPE = null;
	
	private final static String EPIN_SUCCESS = "EPIN SUCCESS"; 
	private final static String EPIN_ERROR = "error";
	private final static String  EPIN_INVALID_DENOM = "INVALID_DENOM";
	private static final int EPIN_SUCCESS_CODE = 100;
	private final static int EPIN_ERROR_CODE = 101; 
	private final static int EPIN_INVALID_DENOM_CODE = 102;
	private static final String appName = "P2MWEB_INTL";
	private static String EPIN_DB_URL = null;
	private static String EPIN_USER = null;
	private static String EPIN_PASS = null;
	private static String EPIN_INSTRUCTION1 = null;
	private static String EPIN_INSTRUCTION2 = null;
	private static DataSource epinDs;
	private static String[] amaxDenoms;
	private static String epinDbContext;
	
	private InputStream ismLC = null;

	private BufferedReader brReader = null;

	private URL url;

	private StringBuffer strMsg = new StringBuffer();
	
	
	private static final Logger logger = Logger.getLogger(LoadCustomerController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		
		ModelAndView modelAndView = new ModelAndView("loadcustomer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {
			
				Map fillbox = dao.fillBox();
				Map fillprodtype = dao.fillprodtype();
				//	Map prodtype = dao.prodtype();
			
				Float wallet = dao.getWallet(session);

				modelAndView.addObject("topupForm", new TopupModel());
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillprodtype", fillprodtype);
			//	modelAndView.addObject("producttype", prodtype);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}		
	

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView viewResult(@ModelAttribute("topupForm") TopupModel topup, HttpSession usersession) throws NamingException {
		
		//ggyahin
		LoadCustomerServices services = new LoadCustomerServices ();
		
		return services.loadCustomer(topup, usersession, servletContext);

		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST,params="mode=getAmount")
    public @ResponseBody String getAmount(@RequestParam("prodtype") String prodtype,
    		HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		
		BigDecimal amount = dao.getAmount(prodtype);
				
		return amount+"";
		
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
