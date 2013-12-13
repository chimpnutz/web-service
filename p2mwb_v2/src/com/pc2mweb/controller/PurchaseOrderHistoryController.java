package com.pc2mweb.controller;


import java.math.BigDecimal;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.UserManagementModel;

@Controller
@RequestMapping("purchaseorder-history")
public class PurchaseOrderHistoryController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderHistoryController.class);
	

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView WalletView(ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ModelAndView modelAndView = new ModelAndView("purchaseorder-history");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {

				List<PurchaseOrderModel> poList = dao.getPurchaseOrdersRequestListHistory(usersession);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
		
	}
	
//	@RequestMapping(method = RequestMethod.GET,params={"retailerhistory"})
//	 public ModelAndView WalletViewRetailer(ModelMap model,HttpServletRequest request,HttpSession usersession) {
//		
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		ModelAndView modelAndView = new ModelAndView("purchaseorder-history");
//		ModelAndView redirect = new ModelAndView("redirect:main.html");
//		
//		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
//
//		HttpSession isSession = request.getSession();
//
//		if (null == isSession.getAttribute("USER")) {	
//			
//				redirect.addObject("login", "no");
//				return redirect;	
//			
//		} else {
//
//				//List<PurchaseOrderModel> poList = dao.getPurchaseOrdersRequestListHistory(usersession,"retailerhistory");
//				//modelAndView.addObject("polist", poList);
//				modelAndView.addObject("type", "retailerhistory");
//				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
//
//				return modelAndView;
//					
//		}
//		
//		
//	}
//	
//	@RequestMapping(method = RequestMethod.GET,params={"subdealerhistory"})
//	 public ModelAndView WalletViewSubDealer(ModelMap model,HttpServletRequest request,HttpSession usersession) {
//		
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		ModelAndView modelAndView = new ModelAndView("purchaseorder-history");
//		ModelAndView redirect = new ModelAndView("redirect:main.html");
//		
//		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
//
//		HttpSession isSession = request.getSession();
//
//		if (null == isSession.getAttribute("USER")) {	
//			
//				redirect.addObject("login", "no");
//				return redirect;	
//			
//		} else {
//
//				//List<PurchaseOrderModel> poList = dao.getPurchaseOrdersRequestListHistory(usersession,"subdealerhistory");
//				modelAndView.addObject("type", "subdealerhistory");
//				//modelAndView.addObject("polist", poList);
//				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
//
//				return modelAndView;
//					
//		}
//		
//		
//	}
	
	@ExceptionHandler()
    public ModelAndView iHandleExceptions(Exception e) {
        //do loads of interesting stuff to deal with the exception
		
		ModelAndView modelAndView = new ModelAndView("error");
		
		modelAndView.addObject("error","yes");
		
		logger.info(e.getMessage());
		
        return modelAndView;
    }

}
