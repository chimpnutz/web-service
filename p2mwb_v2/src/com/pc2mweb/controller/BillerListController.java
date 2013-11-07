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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

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

import com.pc2mweb.beans.BillerBean;
import com.pc2mweb.beans.BillerFieldBean;
import com.pc2mweb.beans.BulkOffersBean;

import com.pc2mweb.dao.transactions.BillsPaymentDAO;
import com.pc2mweb.dao.transactions.TopupDAO;


@Controller
@RequestMapping("billerlist")
public class BillerListController implements ServletContextAware  {
	
	@Autowired
	private ServletContext servletContext;
	

	//private static WalletManager  walletManager   = null;
	private static GlobeUtil globeUtil;
	private static TouchMobileUtil tmUtil;
	
	private static String GHP_NUMRANGE  = null;
	private static String TM_NUMRANGE   = null;

	
	
	
	private static final Logger logger = Logger.getLogger(CheckBrandController.class);

	

	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody List<BillerBean> getBiller(HttpSession session)
    {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		BillsPaymentDAO dao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		

		return dao.fillBiller();
    	

    }
	
	
//	@RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody String  getbrand(@RequestParam("mobnum") String mobile, HttpSession session)
//    {
//		
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
//		
//	
//		GHP_NUMRANGE = servletContext.getInitParameter("GHP_NUMRANGE");
//		TM_NUMRANGE  = servletContext.getInitParameter("TM_NUMRANGE");
//
//		try {
//			globeUtil = new GlobeUtil(new FileInputStream(GHP_NUMRANGE));
//			tmUtil    = new TouchMobileUtil(new FileInputStream(TM_NUMRANGE));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String brand =  null;
//		
//		System.out.println(mobile);
//
//		if (globeUtil.isPrepaidNumber(mobile)) {
//			brand = "GHP";
//		} else if (tmUtil.isPrepaidNumber(mobile)) {
//			brand = "TM";
//		}
//		
//		return brand;
//    	
//
//    }
	

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
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
