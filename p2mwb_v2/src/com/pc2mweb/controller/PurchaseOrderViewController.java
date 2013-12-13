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
@RequestMapping("purchaseorder-view")
public class PurchaseOrderViewController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderViewController.class);
	


	
	@RequestMapping(method = RequestMethod.GET,params={"poid"})
	 public ModelAndView payCredit(@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {

				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
	}
	
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView Order(@ModelAttribute("purchaseorderForm") PurchaseOrderModel purchaseForm,  
//            BindingResult result,HttpSession session,HttpServletRequest request) throws AutoloadMaxException 
//	{
//		
//		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
//		
//		ModelAndView modelAndView = new ModelAndView("purchaseorder-retailer");
//		
//		int status = dao.updatePurchaseOrder(session, purchaseForm);
//		
//		if(status == 1)
//		
//		
//		{
//			
//			List<PurchaseOrderModel> poList = dao.getFinishedPurchasedOrder(session,purchaseForm.getId());
//			modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
//			modelAndView.addObject("type","result");
//			modelAndView.addObject("finishPO", poList);
//			modelAndView.addObject("status","success");
//			modelAndView.addObject("msg", "Purchase Order Request Approved!");
//			return modelAndView;
//			
//		}
//		else if(status == 2)
//		
//		
//		{
//			List<PurchaseOrderModel> poList = dao.getFinishedPurchasedOrder(session,purchaseForm.getId());
//			modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
//			modelAndView.addObject("type","result");
//			modelAndView.addObject("polist", poList);
//			modelAndView.addObject("status","success");
//			modelAndView.addObject("msg", "Purchase Order Request Declined!");
//			return modelAndView;
//			
//		}else{
//			
//			List<PurchaseOrderModel> poList = dao.getFinishedPurchasedOrder(session,purchaseForm.getId());
//			modelAndView.addObject("user",session.getAttribute("USERLEVEL"));
//			modelAndView.addObject("type","result");
//			modelAndView.addObject("polist", poList);
//			modelAndView.addObject("status","fail");
//			modelAndView.addObject("msg", "Purchase Order Request Failed!");
//			return modelAndView;
//			
//		}w
//
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
