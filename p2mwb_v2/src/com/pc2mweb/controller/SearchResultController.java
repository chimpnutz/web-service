package com.pc2mweb.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.model.TransferModel;

@Controller
@RequestMapping("searchresults")
public class SearchResultController {
	
	private static final Logger logger = Logger.getLogger(SearchResultController.class);
	
	@RequestMapping(method = RequestMethod.GET)
 	 public ModelAndView login(ModelMap model,HttpServletRequest request) {
			
		ModelAndView modelAndView = new ModelAndView("searchresults");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		

		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {	
			
			redirect.addObject("login", "no");
			return redirect;	
			
		} else {
			
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
