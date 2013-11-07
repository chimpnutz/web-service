package com.elp.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.elp.dao.transactions.EmergencyLoadManagementDAO;
import com.elp.model.EmergencyLoadReportModel;



@Controller
@RequestMapping("download")
public class DownloadController{
	
	
	private static final Logger logger = Logger.getLogger(DownloadController.class);
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String getDownloadPage() {
	 
	// Do your work here. Whatever you like
	// i.e call a custom service to do your business
	// Prepare a model to be used by the JSP page
	 
	// This will resolve to /WEB-INF/jsp/downloadpage.jsp
	return "download";
	}
	
	@RequestMapping(value = "/export/xls",method = RequestMethod.GET)
 	public ModelAndView exportResult(ModelAndView modelAndView, HttpSession session)  {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadManagementDAO dao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
		
	   logger.info("Received request to download Excel report");
	   
	   System.out.println(session.getAttribute("report"));
	      		
	   List<EmergencyLoadReportModel> searchresult =  	(List<EmergencyLoadReportModel>) session.getAttribute("report");
	   
	   JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(searchresult,false); 
	   
	   Map<String,Object> parameterMap = new HashMap<String,Object>();
	   
	   parameterMap.put("datasource", datasource);
	   
	   modelAndView = new ModelAndView("xlsReport", parameterMap);
	   
       return modelAndView;

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
	
	


