package com.pc2mweb.controller;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;


import com.paysetter.topup.amax.exceptions.AutoloadMaxException;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.UserManagementModel;

@Controller
@RequestMapping("purchaseorder-retailer")
public class PurchaseOrderRetailerController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderRetailerController.class);
	

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView WalletView(ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-retailer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {

				List<PurchaseOrderModel> poList = dao.getPurchaseOrdersRetailersList(usersession);
				List<PurchaseOrderModel> Retailer = dao.getPurchaseOrdersRetailersList(usersession);
				List<PurchaseOrderModel> ghpRetailer = dao.getPurchaseOrdersRetailersList(usersession);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("retailer", Retailer);
				modelAndView.addObject("ghpretailer", ghpRetailer);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET,params={"view"})
	 public ModelAndView payCredit(@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-retailer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {


				Map<String,String> status = new LinkedHashMap<String,String>();
				status.put("Approved", "Approved");
				status.put("Decline", "Decline");
				modelAndView.addObject("statuslist",status);
				modelAndView.addObject("purchaseorderForm", new PurchaseOrderModel());
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
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
