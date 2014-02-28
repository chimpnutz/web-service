package com.pc2mweb.controller;

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

import com.pc2mweb.dao.transactions.PurchaseOrderDAO;
import com.pc2mweb.model.PurchaseOrderModel;

@Controller
@RequestMapping("entryghp1000")
public class GHP1000Controller {

	private static final Logger logger = Logger.getLogger(PaymentEntryController.class);

	@RequestMapping(method = RequestMethod.GET,params={"poid"})
	public ModelAndView payCredit(@RequestParam("poid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		

		ModelAndView modelAndView = new ModelAndView("entryghp1000");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		PurchaseOrderDAO dao = (PurchaseOrderDAO) context.getBean("purchaseorderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
				redirect.addObject("login", "no");
				return redirect;	
			
		}else {

				List<PurchaseOrderModel> poList = dao.getPurchaseOrderItemsDetails(session,id);
				List<PurchaseOrderModel> poList2 = dao.getPOItemsDetails(isSession, id);
				
				modelAndView.addObject("polist2", poList2);
				modelAndView.addObject("polist", poList);
				modelAndView.addObject("type", "poid");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

				return modelAndView;
			}
		
		
		}
	
}
