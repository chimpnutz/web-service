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
@RequestMapping("purchaseorder-view-retailer")
public class PurchaseOrderRetailerViewController {
	
	private static final Logger logger = Logger.getLogger(PurchaseOrderViewController.class);
	
	@RequestMapping(method = RequestMethod.GET,params={"poid"})
	 public ModelAndView payCredit(@RequestParam("poid") int id,@ModelAttribute("paymentorderForm2") PaymentOrderModel paymentorderForm2,BindingResult result,ModelMap model,
			 HttpServletRequest request,HttpSession session) throws NamingException
	 {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer");
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
	
	@RequestMapping(method = RequestMethod.GET,params={"poid","result"})
	 public ModelAndView payCreditResult(@RequestParam("poid") int id,@RequestParam("result") int result,@ModelAttribute("paymentorderForm2") PaymentOrderModel paymentorderForm2,BindingResult results,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-view-retailer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		

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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());

				if(result == 0)
				{
					modelAndView.addObject("msg", "Adding of Payment Successful.");
				}
				else if(result == 1)
				{					
					modelAndView.addObject("msg", "Please input branch name.");
				}
				else if(result == 2)
				{
					modelAndView.addObject("msg", "Please input bank name.");
				}
				else if(result == 3)
				{
					modelAndView.addObject("msg", "Please input remarks.");
				}
				else if(result == 4)
				{
					modelAndView.addObject("msg", "No File Selected.");
				}
				else if(result == 5)
				{
					modelAndView.addObject("msg", "Please choose jpeg, gif or pdf image only.");
				}
				else if(result == 6)
				{
					modelAndView.addObject("msg", "Updating of Payment Successful.");	
				}
				else if(result == 7)
				{
					modelAndView.addObject("msg", "Image size is too, Maximum of 5MB only.");	
				}
				else
				{
					modelAndView.addObject("msg", "Adding of Payment Failed. Please Contact Admin.");
				}

				return modelAndView;
					
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST,params={"poid"})
	public ModelAndView processOrder(@ModelAttribute("paymentorderForm2") PaymentOrderModel paymentorderForm2,BindingResult result,
			@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		ModelAndView modelAndview = new ModelAndView("purchaseorder-view-retailer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		TransactionIDObject obj = new TransactionIDObject();
		
		HttpSession isSession = request.getSession();
		
		
		if(request.getParameter("submit")!= null)
		{
			
			SimpleDateFormat datetodayFormat = new SimpleDateFormat("yyyyMMddkmS");
			
			String filename = datetodayFormat.format(new Date());
			
			paymentorderForm2.setAttachment(filename+".jpg");
			
			long filesize = paymentorderForm2.getCfile().getSize();
			
			String filetype = paymentorderForm2.getCfile().getContentType();
			
			if(filetype.equalsIgnoreCase("image/jpeg") || filetype.equalsIgnoreCase("image/gif") || filetype.equalsIgnoreCase("application/pdf") ){
				
				if(filesize>5120000){
					
					ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=7#modal");
					
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
					modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
					
					
					
					return modelAndView;
					
				}
			}
			else{
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=5#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				
				
				return modelAndView;
			}
			
			if(request.getParameter("branch").equalsIgnoreCase("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=1#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Please input branch name.");
				
				return modelAndView;
			}
			
			else if(request.getParameter("bank").equalsIgnoreCase("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=2#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Please input bank name.");
				
				return modelAndView;
			}
			
			else if(request.getParameter("text").equalsIgnoreCase("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=3#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Please input remarks.");
				
				return modelAndView;
			}
			
			else if(request.getParameter("text")==null){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=4#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("No file selected.");
				
				return modelAndView;
			}
					
			else if(dao.insertPaymentOrder(session, id, request, paymentorderForm2))
			{
				
				Props props2 = new Props();
				
				String orginalName = paymentorderForm2.getCfile().getOriginalFilename();
				
				String filepath = props2.getPath()+paymentorderForm2.getAttachment();
				
				File destination = new File(filepath);
				
				paymentorderForm2.getCfile().transferTo(destination);

				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=0#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());

				
				logger.info("Payment has been inserted.");
				
				return modelAndView;
				
			}
			else
			{
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=8#modal");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());

				logger.info("Payment insertion failed.");
				
				return modelAndView;
			}

		}
		
		else if(request.getParameter("save")!=null){
			
			if(dao.updatePaymentOrderForm(session,id,request)){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=6#modalupdate");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Payment has been updated.");
				
				return modelAndView;
			}
			else{
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view-retailer.html?poid="+id+"&result=8#modalupdate");
				
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
				modelAndView.addObject("paymentorderForm", new PaymentOrderModel());
				
				logger.info("Payment failed to update.");
				
				return modelAndView;
				
			}
		}
		return modelAndview;
	}

}
