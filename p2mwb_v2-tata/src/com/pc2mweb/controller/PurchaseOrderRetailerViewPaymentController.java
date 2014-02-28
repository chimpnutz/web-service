package com.pc2mweb.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.model.PaymentModelList;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.utility.function.Props;


@Controller
@RequestMapping("purchaseorder-view-retailer-payment")
public class PurchaseOrderRetailerViewPaymentController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderViewController.class);
	
	@RequestMapping(method = RequestMethod.GET,params={"poid"})
	 public ModelAndView payCredit(@RequestParam("poid") int id,@ModelAttribute("paymentForm") PaymentOrderModel paymentForm,BindingResult result,ModelMap model,
			 HttpServletRequest request,HttpSession session) throws NamingException
	 {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer-payment");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		List<PurchaseOrderModel> po = new ArrayList<PurchaseOrderModel>();
		List<PaymentOrderModel> pom = new ArrayList<PaymentOrderModel>();
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {

				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST,params={"poid"})
	public ModelAndView processOrder(@ModelAttribute("paymentForm") PaymentOrderModel paymentForm,BindingResult result,
			@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		ModelAndView modelAndview = new ModelAndView("purchaseorder-view-retailer-payment");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		TransactionIDObject obj = new TransactionIDObject();
		
		HttpSession isSession = request.getSession();
		
		if(request.getParameter("confirmPayment")!= null){
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer-payment");
			
			if(dao.confirmPayment(session, id)){
				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
	
				modelAndView.addObject("message","Payment has been confirmed.");
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				logger.info("Payment has been confirmed.");
			}
			else{
				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("message","Confirming Payment Failed");
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Confirming Payment Failed");
			}
	
			
			return modelAndView;
		}
		else if(request.getParameter("cancelPayment")!= null){
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer-payment");
			
			if(dao.cancelPayment(session, id)){
				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("valid","ok");
				modelAndView.addObject("message","Payment has been cancelled.");
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Payment has been cancelled.");
				
			}else{
				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("valid","ok");
				modelAndView.addObject("message","Payment has been cancelled.");
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Error cancelling the payment.");
			}
			

			return modelAndView;
		}
		
		if(request.getParameter("processOrder")!= null)
		{
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer-payment");
			
			if(dao.insertProcessOrder(session, id, obj))
			{
				
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("pototal",poTotal);
				modelAndView.addObject("list",List);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("valid","ok");
				modelAndView.addObject("message", "Processing Order Success.");
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Processing Purchase Success.");
				
				return modelAndView;
				
				
			}else
			{
				{
					
					List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
					List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
					List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
					List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
					
					modelAndView.addObject("pomlist",pomList);
					modelAndView.addObject("pototal",poTotal);
					modelAndView.addObject("list",List);
					modelAndView.addObject("otlist",otList);
					modelAndView.addObject("type", "poid");
					modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
					modelAndView.addObject("valid","ok");
					modelAndView.addObject("message", "Processing Order Success.");
					modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
					
					logger.info("Processing Purchase Failed.");
					
					return modelAndView;
					
					
				}
			}
			

		}
		
		
		return modelAndview;
	}

}
