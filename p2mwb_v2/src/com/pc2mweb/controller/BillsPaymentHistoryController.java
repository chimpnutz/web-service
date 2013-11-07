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
import java.util.ArrayList;
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

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;




import com.payexchange.bayadcenter.ws.BayadCenter;
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
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.BillsPaymentHistoryModel;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("billspaymenthistory")
public class BillsPaymentHistoryController implements ServletContextAware   {
	
	@Autowired
	private ServletContext servletContext;
	

	private static final Logger logger = Logger.getLogger(BillsPaymentHistoryController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		BillsPaymentDAO dao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("billspaymenthistory");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
			Float wallet = dao.getWallet(session);
				List<BillsPaymentHistoryModel> billhistory = dao.getTransactionHistory(session);
				modelAndView.addObject("bills",billhistory);	
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
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
