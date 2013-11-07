package com.pc2mweb.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.dao.transactions.ManageBranchesDAO;
import com.pc2mweb.model.ManageBranchesModel;

public class AddBranchServices {
	
	
	public ModelAndView addBranch(ManageBranchesModel model, HttpSession usersession){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ManageBranchesDAO dao = (ManageBranchesDAO) context.getBean("managebranchesDAO");
		
		ModelAndView modelAndView = new ModelAndView("addbranch");
		
		modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		
		modelAndView.addObject("managebranchesForm", new ManageBranchesModel());
		
	    if(model.getBranchname().equalsIgnoreCase("") || model.getUsername().equalsIgnoreCase("") || model.getPassword().equalsIgnoreCase("") || model.getConfirmpassword().equalsIgnoreCase(""))
	    {
	    	modelAndView.addObject("msg","One of the fields must not be empty.");
	    	return modelAndView;
	    }
	    
	    
	    int insertResult = dao.insertBranch(usersession, model);
		if(insertResult == 1){
			
			List<ManageBranchesModel> partnerlist = dao.getPartners(usersession);
			
			modelAndView.addObject("partners", partnerlist);
			
		  	modelAndView.addObject("msg","New Branch and User Successfully Added.");
			
		}else if(insertResult == -1)
		{
			modelAndView.addObject("msg","Branchid already exist.");
			
		}else if(insertResult == -2)
		{
			modelAndView.addObject("msg","Username already exist.");
			
		}else if(insertResult == -3)
		{
			modelAndView.addObject("msg","Adding of New Branch and User Failed. Please contact administrator.");
			
		}else if(insertResult == -4)
		{
			modelAndView.addObject("msg","Adding of New Branch and User Failed. Please contact administrator.");
			
		}
	
       
       return modelAndView;
		
		
	}

}
