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
import com.elp.dao.transactions.EmergencyLoadManagementDAO;
import com.elp.dao.transactions.LoginDAO;
import com.elp.dao.transactions.NetworkManagementDAO;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.LoginModel;
import com.elp.model.PermissionModel;


@Controller
@RequestMapping("emergencyloadmanagement-edit")
public class EmergencyLoadManagementEditController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(EmergencyLoadManagementEditController.class);

	@RequestMapping(method = RequestMethod.GET,params={"unpaid"})
		public ModelAndView editUnpaid(@RequestParam("cid") int cid,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
 		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
 		HttpSession isSession = request.getSession();
 		
		ModelAndView page = new ModelAndView("emergencyloadmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");		

					return loginpage;	
			
		} else
				 {		
	
	
					List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
					List<CreditLogsModel> creditlist = dao.getAvailedEmergencyLoad(cid,session,"unpaid");
					Map company = dao.getcompanylist();
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Approved", "Approved");
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("creditlist",creditlist);
					page.addObject("type","unpaid");
					
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
	}			
	
	@RequestMapping(method = RequestMethod.GET,params={"paid"})
	public ModelAndView editPaid(@RequestParam("cid") int cid,HttpServletRequest request,HttpSession session) throws NamingException {
	
	ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
	
	EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
		
	LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
	 
		HttpSession isSession = request.getSession();
		
	ModelAndView page = new ModelAndView("emergencyloadmanagement-edit");
	ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
	
	if (null == isSession.getAttribute("username")) {		
			
				loginpage.addObject("login","no");		

				return loginpage;	
		
	} else
			 {		


				List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
				List<CreditLogsModel> creditlist = dao.getAvailedEmergencyLoad(cid,session,"paid");
				Map company = dao.getcompanylist();
				Map referenceData = new HashMap();
				Map<String,String> status = new LinkedHashMap<String,String>();
				status.put("Approved", "Approved");
				status.put("Cancel", "Cancel");
				referenceData.put("statuslist", status);
				
				page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
				page.addObject("companylist",company);	
				page.addObject("statuslist",status);
				page.addObject("username",session.getAttribute("username"));	
				page.addObject("rolename",session.getAttribute("rolename"));
				page.addObject("companyname",session.getAttribute("companyname"));
				page.addObject("roleid",session.getAttribute("roleid"));
				page.addObject("creditlist",creditlist);
				page.addObject("type","paid");
		    	//mainpage.addObject("roleid","0");
		    	return page;
			}
	
}	
	
	@RequestMapping(method = RequestMethod.GET,params={"cancelled"})
	public ModelAndView editCancelled(@RequestParam("cid") int cid,HttpServletRequest request,HttpSession session) throws NamingException {
	
	ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
	
	EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
		
	LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
	 
		HttpSession isSession = request.getSession();
		
	ModelAndView page = new ModelAndView("emergencyloadmanagement-edit");
	ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
	
	if (null == isSession.getAttribute("username")) {		
			
				loginpage.addObject("login","no");		

				return loginpage;	
		
	} else
			 {		


				List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
				List<CreditLogsModel> creditlist = dao.getAvailedEmergencyLoad(cid,session,"cancelled");
				Map company = dao.getcompanylist();
				Map referenceData = new HashMap();
				Map<String,String> status = new LinkedHashMap<String,String>();
				status.put("Approved", "Approved");
				status.put("Cancel", "Cancel");
				referenceData.put("statuslist", status);
				
				page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
				page.addObject("companylist",company);	
				page.addObject("statuslist",status);
				page.addObject("username",session.getAttribute("username"));	
				page.addObject("rolename",session.getAttribute("rolename"));
				page.addObject("companyname",session.getAttribute("companyname"));
				page.addObject("roleid",session.getAttribute("roleid"));
				page.addObject("creditlist",creditlist);
				page.addObject("type","cancelled");
		    	//mainpage.addObject("roleid","0");
		    	return page;
			}
	
}	
	
		
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String updatePayment(@RequestParam("compid") int compid,
    		@RequestParam("creditid") int creditid,
    		@RequestParam("amount") int amount,
    		@RequestParam("status") String status,
    		@RequestParam("oldstatus") String oldstat,
    		HttpSession session)
    {
		
	ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
 		
		CreditLogsModel model = new CreditLogsModel();
		
		model.setCompanyid(compid);
		model.setCreditid(creditid);
		model.setAmount(amount);
		model.setStatus(status);
		
		int result = dao.updatePayment(model, session, oldstat);
		
		if(result == 0){
			return "Update Payment Successful";
		}else if(result == 1){
			return "Update Payment Fail. Cannot pay amount higher than your outstanding balance.";
		}else if(result == 2){
			return "Update Payment Fail. Cannot pay amount lower than your outstanding balance.";
		}else if(result == 3){
			return "Cannot Process Request. Status already changed to cancelled";
		}else if(result == 4){
			return "Cannot Process Request. Status already changed to paid";
		}else if(result == 5){
			return "Cannot Process Request. Delivery Status must be completed before paying.";
		}
 		
	
		
		return "Update Payment Fail";
		

    }
	


	public void setServletContext(ServletContext context) {
		this.servletContext = context;	
	}
	
	@ExceptionHandler()
    public ModelAndView iHandleExceptions(Exception e) {
        //do loads of interesting stuff to deal with the exception
		
		ModelAndView modelAndView = new ModelAndView("error");
		
		modelAndView.addObject("error","yes");
		
		logger.info(e.getMessage());
		
        return modelAndView;
    }



}
