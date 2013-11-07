package com.elp.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.elp.beans.UserBean;
import com.elp.dao.transactions.CreditLimitManagementDAO;
import com.elp.dao.transactions.EmergencyLoadManagementDAO;
import com.elp.dao.transactions.LoginDAO;
import com.elp.dao.transactions.NetworkManagementDAO;
import com.elp.dao.transactions.OpensimDao;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.DailyTransferReportModel;
import com.elp.model.EmergencyLoadManagementModel;
import com.elp.model.LoginModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.OpenSimModel;
import com.elp.model.PermissionModel;
import com.elp.model.UserManagementModel;


@Controller
@RequestMapping("opensimtransactions")
public class OpenSimTransactionsController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(OpenSimTransactionsController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView unpaid(HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");	
		OpensimDao odao = (OpensimDao)context.getBean("OpensimDAO");	
		 
 		HttpSession isSession = request.getSession();
 		
		ModelAndView page = new ModelAndView("opensimtransactions");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
			
					return loginpage;	
			
		} else
				 {		
	
					Map opensimlist = odao.getopensimlist();
					Map reference = new HashMap();
					List<OpenSimModel> opensimlogs = dao.getOpenSimBalance();
					page.addObject("opensimtransactionsForm",new OpenSimModel());
					page.addObject("opensimlist",opensimlist);
					
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("opensimlogs",opensimlogs);
			
			    	return page;
				}
		
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	 public @ResponseBody List <OpenSimModel> getOpenSimTx(@RequestParam("opensim") String opensim,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
		OpensimDao dao = (OpensimDao)context.getBean("OpensimDAO");	
		
		List <OpenSimModel> opensimtx = dao.getOpenTx(opensim);
		
		
		return opensimtx;
		 
//		HttpSession isSession = request.getSession();
//		
//		ModelAndView page = new ModelAndView("opensimtransactions");
//		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
//		
//		
//		if (null == isSession.getAttribute("username")) {		
//				
//					loginpage.addObject("login","no");
//			
//					return loginpage;	
//			
//		} else
//				 {		List <OpenSimModel> datetx = dao.getOpenTx(opensim);
//		List <OpenSimModel> balancetx = dao.getOpenTx(opensim);
//	
//					Map opensimlist = odao.getopensimlist();
//					Map reference = new HashMap();
//					List<OpenSimModel> opensimlogs = dao.getOpenSimBalance();
//					page.addObject("opensimtransactionsForm",new OpenSimModel());
//					page.addObject("opensimlist",opensimlist);
//					
//					page.addObject("username",session.getAttribute("username"));	
//					page.addObject("rolename",session.getAttribute("rolename"));
//					page.addObject("companyname",session.getAttribute("companyname"));
//					page.addObject("roleid",session.getAttribute("roleid"));
//					page.addObject("opensimlogs",opensimlogs);
//			
//			    	return page;
//				}
		
	
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
