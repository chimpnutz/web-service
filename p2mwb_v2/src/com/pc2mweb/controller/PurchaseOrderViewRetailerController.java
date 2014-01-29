package com.pc2mweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.beans.ApproveBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.PaymentModelList;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.TransferCreditsModel;
import com.pc2mweb.model.TransfertoRetailerModel;


@Controller
@RequestMapping("purchaseorder-viewretailer")
public class PurchaseOrderViewRetailerController {

private static final Logger logger = Logger.getLogger(PurchaseOrderViewController.class);
	


	
	@RequestMapping(method = RequestMethod.GET,params={"poid"})
	 public ModelAndView payCredit(@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-viewretailer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		List<PurchaseOrderModel> po = new ArrayList<PurchaseOrderModel>();
		List<PaymentOrderModel> pom = new ArrayList<PaymentOrderModel>();
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {

				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
					
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST,params={"poid"})
	public ModelAndView processOrder(@RequestParam("poid") int id,HttpServletRequest request,HttpSession session,PurchaseModelList purchaseForm, TransfertoRetailerModel topup) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		TransactionIDObject obj = new TransactionIDObject();
		
		ModelAndView modelAndView = new ModelAndView("purchaseorder-viewretailer");
		
		ApproveBean pom = new ApproveBean();
		List<PurchaseOrderModel> po = purchaseForm.getPO();
		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();
		
		pom.setType(request.getParameter("type"));
		
		HttpSession isSession = request.getSession();
		
		if(request.getParameter("update")!= null){
			
			dao.transfertobranch(session,obj,id,pom.getType());
			if(pom.getType().equalsIgnoreCase("approve")){
			
			List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
			List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
			List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
			
			modelAndView.addObject("pomlist",pomList);
			modelAndView.addObject("otlist",otList);
			modelAndView.addObject("list", List);
			modelAndView.addObject("polist", poList);
			modelAndView.addObject("pototal", poTotal);
			modelAndView.addObject("type", "poid");
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("status", "approve");
			modelAndView.addObject("message", "Your request has been approved!");
			
			return modelAndView;
			}
			
			if(pom.getType().equalsIgnoreCase("decline")){
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("status", "decline");
				modelAndView.addObject("message", "Your request has been declined!");
				
				return modelAndView;
				}
			
				if(pom.getType().equalsIgnoreCase("")){
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> otList = dao.getOthersPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("status", "blank");
				modelAndView.addObject("message", "Please choose an option!");
				
				return modelAndView;
				}
		}
		
			
		return modelAndView;
	}
	
}
