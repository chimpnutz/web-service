package com.pc2mweb.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.dao.transactions.TransactionHistoryDAO;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransactionReportsResultModel;


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
 	public ModelAndView exportResult(ModelAndView modelAndView, HttpSession excel)  {
		
		
//	  if(excel.getAttribute("USERLEVEL").equals("manager")){
		  
	   logger.info("Received request to download Excel report");
	      		
	   List<TransactionReportsResultModel> searchresult = (List<TransactionReportsResultModel>) excel.getAttribute("reports");
	   
	   JRDataSource datasource = new JRBeanCollectionDataSource(searchresult,false); 

	   Map<String,Object> parameterMap = new HashMap<String,Object>();
	   
	   parameterMap.put("datasource", datasource);
	   
	   modelAndView = new ModelAndView("xlsReport", parameterMap);
	   
       return modelAndView;
//		}
//		else {
//			logger.info("You don't have permission to do transaction in this page.");
//	    	modelAndView.addObject("access","no");
//	    	return modelAndView;
//		}
	}
	
	@RequestMapping(value = "/export/xls/consumer",method = RequestMethod.GET)
 	public ModelAndView exportSalesConsumerReport(ModelAndView modelAndView, HttpSession session)  {
		 
		  ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		  TransactionHistoryDAO dao = (TransactionHistoryDAO)context.getBean("transactionhistoryDAO");
		
// 		  if(session.getAttribute("USERLEVEL").equals("manager")){
 			  
 		   logger.info("Received request to download Excel report");
 		      		
 		   List<TransactionHistoryModel> searchresult = dao.getTransactionHistory(session);
 		   
 		   JRDataSource datasource = new JRBeanCollectionDataSource(searchresult,false); 

 		   Map<String,Object> parameterMap = new HashMap<String,Object>();
 		   
 		   parameterMap.put("datasource", datasource);
 		   
 		   modelAndView = new ModelAndView("xlsSalesConsumerReport", parameterMap);
 		   
 	       return modelAndView;
// 			}
// 			else {
// 				logger.info("You don't have permission to do transaction in this page.");
// 		    	modelAndView.addObject("access","no");
// 		    	return modelAndView;
// 			}
 		}
 	
	@RequestMapping(value = "/export/xls/retailer",method = RequestMethod.GET)
 	public ModelAndView exportRetailerConsumerReport(ModelAndView modelAndView, HttpSession session)  {
		
		  ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			
		  TransactionHistoryDAO dao = (TransactionHistoryDAO)context.getBean("transactionhistoryDAO");
		
//		  if(session.getAttribute("USERLEVEL").equals("manager")){
			  
		   logger.info("Received request to download Excel report");
		      		
		   List<RetailerTransactionHistoryModel> searchresult = dao.getRetailerTransactionHistory(session);
		   
		   JRDataSource datasource = new JRBeanCollectionDataSource(searchresult,false); 

		   Map<String,Object> parameterMap = new HashMap<String,Object>();
		   
		   parameterMap.put("datasource", datasource);
		   
		   modelAndView = new ModelAndView("xlsSalesRetailerReport", parameterMap);
		   
	       return modelAndView;
//			}
//			else {
//				logger.info("You don't have permission to do transaction in this page.");
//		    	modelAndView.addObject("access","no");
//		    	return modelAndView;
//			}
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
	
	


