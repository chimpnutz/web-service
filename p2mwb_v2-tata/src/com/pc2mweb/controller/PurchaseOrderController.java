package com.pc2mweb.controller;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.paysetter.topup.amax.exceptions.AutoloadMaxException;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.PurchaseOrderBean;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.dao.transactions.LoginDAO;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.MailModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.utility.function.Props;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("purchaseorder-ordernow")
public class PurchaseOrderController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderController.class);
	

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView WalletView(ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		ModelAndView modelAndView = new ModelAndView("purchaseorder-ordernow");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		List<PurchaseOrderModel> po = new ArrayList<PurchaseOrderModel>();
		
		PurchaseModelList poList = new PurchaseModelList();
		poList.setPO(po);

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
				List<PurchaseOrderBean> item = dao.fillItemCodeList(usersession);
				List<PurchaseOrderBean> wallet = dao.fillWalletlist(usersession);
				modelAndView.addObject("purchaseorderForm", poList);
				modelAndView.addObject("wallet", wallet);
				modelAndView.addObject("item", item);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView Order(@ModelAttribute("purchaseorderForm") PurchaseModelList purchaseForm,  
            BindingResult result,HttpSession session,HttpServletRequest request) throws AutoloadMaxException 
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		String NUMB_REGEX = "\\d+";
		
		ModelAndView modelAndView = new ModelAndView("purchaseorder-ordernow");
		
		List<PurchaseOrderModel> po = purchaseForm.getPO();
		
		PurchaseModelList poList = new PurchaseModelList();

		
	      for (PurchaseOrderModel model : po) 
          {
	    	   
	       	   if(model.getQuantity().equals(""))
	       	   {
	       		List<PurchaseOrderBean> item = dao.fillItemCodeList(session);
	    		List<PurchaseOrderBean> wallet = dao.fillWalletlist(session);
	    		modelAndView.addObject("purchaseorderForm", poList);
	    		modelAndView.addObject("item", item);
	    		modelAndView.addObject("wallet", wallet);
	    		modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
	   			modelAndView.addObject("valid","fail");
				modelAndView.addObject("message", "Please input quantity.");
				
				logger.info("Please input quantity.");
				
				return modelAndView;
	       	   }
	       	   
	       	   if(!model.getQuantity().matches(NUMB_REGEX))
	       	   {
	       		List<PurchaseOrderBean> item = dao.fillItemCodeList(session);
	    		List<PurchaseOrderBean> wallet = dao.fillWalletlist(session);
	    		modelAndView.addObject("purchaseorderForm", poList);
	    		modelAndView.addObject("item", item);
	    		modelAndView.addObject("wallet", wallet);
	    		modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
	   			modelAndView.addObject("valid","fail");
				modelAndView.addObject("message", "Please input numbers only.");
				
				logger.info("Please input numbers only.");
				
				return modelAndView;  
	       	   }
	       	   
	       	   if(model.getItem().equals("none"))
	       	   {
	       		List<PurchaseOrderBean> item = dao.fillItemCodeList(session);
	    		List<PurchaseOrderBean> wallet = dao.fillWalletlist(session);
	    		modelAndView.addObject("purchaseorderForm", poList);
	    		modelAndView.addObject("item", item);
	    		modelAndView.addObject("wallet", wallet);
	    		modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
	   			modelAndView.addObject("valid","fails");
				modelAndView.addObject("message", "Please select an item.");
				
				logger.info("Please select an item.");
				
				return modelAndView;
	       	   }
	    	  
	    	  
	    	  
          }
          	

	
		
		if(dao.insertPurchaseOrder(session, po))
		{
			
			List<PurchaseOrderBean> item = dao.fillItemCodeList(session);
			List<PurchaseOrderBean> wallet = dao.fillWalletlist(session);
			modelAndView.addObject("purchaseorderForm", poList);
			modelAndView.addObject("item", item);
			modelAndView.addObject("wallet", wallet);
			modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
			modelAndView.addObject("msg","success");
			modelAndView.addObject("message", "Purchase Successfully Ordered. ");
			
			logger.info("Inserting Purchase Order Successful.");
			
			
		}
		else
		{
			
			List<PurchaseOrderBean> item = dao.fillItemCodeList(session);
			List<PurchaseOrderBean> wallet = dao.fillWalletlist(session);
			modelAndView.addObject("purchaseorderForm", poList);
			modelAndView.addObject("item", item);
			modelAndView.addObject("wallet", wallet);
			modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
			modelAndView.addObject("msg","success");
			modelAndView.addObject("message", "Purchase Successfully Ordered.");
			
			logger.info("Inserting Purchase Order Failed.");
		}
		

		
		return modelAndView;
		
		
		
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
