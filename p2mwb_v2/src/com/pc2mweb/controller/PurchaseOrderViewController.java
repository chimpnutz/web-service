package com.pc2mweb.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.paysetter.topup.amax.exceptions.AutoloadMaxException;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.PaymentModelList;
import com.pc2mweb.model.PaymentOrderModel;
import com.pc2mweb.model.PurchaseModelList;
import com.pc2mweb.model.PurchaseOrderModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.utility.function.Props;

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
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());

				return modelAndView;
					
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET,params={"poid","result"})
	 public ModelAndView payCreditResult(@RequestParam("poid") int id,@RequestParam("result") int result,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
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
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("otlist",otList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				
				if(result == 0){
				modelAndView.addObject("msg", "Adding of Payment Successful.");
				}
				else if(result ==1){
					modelAndView.addObject("msg", "Updating of Payment Successful.");
					}
				else if(result ==2){
					modelAndView.addObject("msg", "Please input remarks.");
					}
				else if(result ==3){
					modelAndView.addObject("msg", "Please input bank name.");
					}
				else if(result ==4){
					modelAndView.addObject("msg", "Please input branch name.");
					}
				else if(result ==5){
					modelAndView.addObject("msg", "No file selected.");
					}
				else if(result ==6){
					modelAndView.addObject("msg", "Please use jpeg, gif or pdf image only.");
					}
				else if(result ==7){
					modelAndView.addObject("msg", "Image Size too large, Maximum of 5MB only.");
					}
				else
					modelAndView.addObject("msg", "Adding of Payment Failed. Please Contact Admin.");

				return modelAndView;
					
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST,params={"poid"})
	public ModelAndView processOrder(@ModelAttribute("paymentorderForm") PaymentOrderModel model,BindingResult result,
			@RequestParam("poid") int id,HttpServletRequest request,HttpSession session,PurchaseModelList purchaseForm, PaymentModelList paymentorderForm) throws IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");
		
		TransactionIDObject obj = new TransactionIDObject();
		
		List<PurchaseOrderModel> po = purchaseForm.getPO();
		List<PaymentOrderModel> pom = paymentorderForm.getPOMS();
		HttpSession isSession = request.getSession();
		
		String NUMB_REGEX = "\\d+";
		
		
		
		
		
		
		if(request.getParameter("processOrder")!= null){
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
			
			if(dao.insertProcessOrder(session, id, obj)){
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("valid","ok");
				modelAndView.addObject("message", "Processing Order Success.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				
				return modelAndView;
				
				
			}
			

		}

		else if(request.getParameter("submit")!= null)
		{
			
			SimpleDateFormat datetodayFormat = new SimpleDateFormat("yyyyMMddkmsS");
			
			String filename = datetodayFormat.format(new Date());
			
			model.setAttachment(filename+".jpg");
			
			long filesize = model.getCfile().getSize();
			
			String filetype = model.getCfile().getContentType();

			
			 if(filetype.equalsIgnoreCase("image/jpeg") || filetype.equalsIgnoreCase("image/gif") || filetype.equalsIgnoreCase("application/jpg"))
			 {
				 
	
				
					if(filesize>5120000)
					{
						
						ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=7#modal");
						
						List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
						List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
						List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
						List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
						List<PaymentOrderModel> payList = dao.getAttachment(session,id);
						modelAndView.addObject("paylist", payList);
						modelAndView.addObject("pomlist",pomList);
						modelAndView.addObject("list", List);
						modelAndView.addObject("polist", poList);
						modelAndView.addObject("pototal", poTotal);
						modelAndView.addObject("type", "poid");
						modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
						modelAndView.addObject("error1","error1");
						modelAndView.addObject("message","Please use jpeg or jpg image only");
						modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
						return modelAndView;
						
					}
					

				
			}else
			{
				
				
				
			ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=6#modal");
			
			List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
			List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
			List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
			List<PaymentOrderModel> payList = dao.getAttachment(session,id);
			modelAndView.addObject("paylist", payList);
			modelAndView.addObject("pomlist",pomList);
			modelAndView.addObject("list", List);
			modelAndView.addObject("polist", poList);
			modelAndView.addObject("pototal", poTotal);
			modelAndView.addObject("type", "poid");
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("error1","error1");
			modelAndView.addObject("message","Please use jpeg or jpg image only");
			modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
			return modelAndView;
				
				
				
			}
			 		 
			
			 if(filename.equals("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=5#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("error1","error1");
				modelAndView.addObject("message","Please choose an image");
				
				return modelAndView;
				
			}
			
			if(request.getParameter("branch").equals("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=4#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("error2","error2");
				modelAndView.addObject("message","Please input branch name.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
				
			}
			
			else if(request.getParameter("bank").equals("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=3#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("error3","error3");
				modelAndView.addObject("message","Please input bank name.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
				
			}
			
			else if(request.getParameter("text").equals("")){
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=2#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("error4","error4");
				modelAndView.addObject("message","Please input remarks.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
				
			}
					
			else if(dao.insertPaymentOrder(session, id, request,model))
			{
			    			    
			    Props props2 = new Props();
			    			    
			    String originalName = model.getCfile().getOriginalFilename();
			    
			    String filepath = props2.getPath()+model.getAttachment();			   
			    
			    File destination = new File(filepath);
			    
			    model.getCfile().transferTo(destination);
			    
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=0#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("valid","msg");
				modelAndView.addObject("mesg","Payment has been inserted.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
				
			}else{
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=8#modal");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("message","Payment insertion failed.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
			}
			
			

		}
		
		else if(request.getParameter("save")!= null)
		{
			
					
			if(dao.updatePaymentOrderForm(session, id, request))
			{
				
				
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=1#modalupdate");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("success","success.");
				modelAndView.addObject("message","Payment has been updated.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
				
			}else{
				ModelAndView modelAndView = new ModelAndView("redirect:purchaseorder-view.html?poid="+id+"&result=8#modalupdate");
				
				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
				List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
				List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
				List<PaymentOrderModel> payList = dao.getAttachment(session,id);
				modelAndView.addObject("paylist", payList);
				modelAndView.addObject("pomlist",pomList);
				modelAndView.addObject("list", List);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("pototal", poTotal);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("message","Payment update failed.");
				modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
				return modelAndView;
			}
			
			

		}
		
		else if(request.getParameter("confirmPayment")!= null)
		{
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
			
			dao.confirmPayment(session, id);
			
			List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
			List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
			List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
			List<PaymentOrderModel> payList = dao.getAttachment(session,id);
			modelAndView.addObject("paylist", payList);
			modelAndView.addObject("pomlist",pomList);
			modelAndView.addObject("list", List);
			modelAndView.addObject("polist", poList);
			modelAndView.addObject("pototal", poTotal);
			modelAndView.addObject("type", "poid");
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("message","Payment has been confirmed.");
			modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
			return modelAndView;
		}
		
		else if(request.getParameter("cancelPayment")!= null){
			
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
			
			dao.cancelPayment(session, id);
			
			List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
			List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
			List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
			List<PaymentOrderModel> payList = dao.getAttachment(session,id);
			modelAndView.addObject("paylist", payList);
			modelAndView.addObject("pomlist",pomList);
			modelAndView.addObject("list", List);
			modelAndView.addObject("polist", poList);
			modelAndView.addObject("pototal", poTotal);
			modelAndView.addObject("type", "poid");
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("valid","ok");
			modelAndView.addObject("message","Payment has been cancelled.");
			modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
			return modelAndView;
		}
		
		
			ModelAndView modelAndView = new ModelAndView("purchaseorder-view");
			
			dao.cancelPayment(session, id);
			
			List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
			List<PurchaseOrderModel> poTotal = dao.getPurchaseTotal(session,id);
			List<PurchaseOrderModel> List = dao.getPurchaseList(isSession, id);
			List<PaymentOrderModel> pomList= dao.getPaymentOrderItemsDetails(session, id);
			List<PaymentOrderModel> payList = dao.getAttachment(session,id);
			modelAndView.addObject("paylist", payList);
			modelAndView.addObject("pomlist",pomList);
			modelAndView.addObject("list", List);
			modelAndView.addObject("polist", poList);
			modelAndView.addObject("pototal", poTotal);
			modelAndView.addObject("type", "poid");
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("valid","ok");
			modelAndView.addObject("message","Updating of Payment Failed. Please Contact Admin");
			modelAndView.addObject("paymentorderForm",new PaymentOrderModel());
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
