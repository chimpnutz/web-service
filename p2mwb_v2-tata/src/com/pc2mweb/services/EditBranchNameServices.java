package com.pc2mweb.services;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.model.EditPartnerModel;

public class EditBranchNameServices {
	
	public  String editBranch (EditPartnerModel changepartnername,HttpSession usersession){
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		UserManagementDAO dao = (UserManagementDAO) context.getBean("usermanagementDAO");
		
		if(usersession.getAttribute("USERLEVEL").equals("manager"))
	    {
	    	
			if(changepartnername.getPartnername() == null){
				

				return "Please input your partnername name";
				
			}else 
				
			if(dao.changePartnerName(changepartnername, usersession)){
				
			  
				return	"Partner name successfully updated." ;
				
			}else
			{	
			
  
				return	"Branch name change not successful!";
				
			}
	
	    }

		else {

	    	return "You don't have permission to do transaction in this page.";
		}
	}

}
