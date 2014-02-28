	package com.pc2mweb.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;




import com.payexchange.bayadcenter.ws.BayadCenter;
import com.paysetter.amax.AMAXConstants;
import com.paysetter.commons.pctomobile.P2MConstants;

import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.TopUpResponse;
import com.paysetter.topup.gcash.dao.HandleGcash2Load;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;

import com.pc2mweb.beans.BillerFieldBean;
import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.dao.transactions.BillsPaymentDAO;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.BillsPaymentModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("billspayment")
public class BillsPaymentController implements ServletContextAware   {
	
	@Autowired
	private ServletContext servletContext;
	

	private static final Logger logger = Logger.getLogger(BillsPaymentController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("billspayment");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = billdao.getWallet(session);

				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}		
	
	
	@RequestMapping(method = RequestMethod.GET,params = {"result"})
	 public ModelAndView resultView(@RequestParam(value  = "result") String result, ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		BillsPaymentDAO billdao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ModelAndView modelAndView = new ModelAndView("billspayment");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {

			
				Float wallet = billdao.getWallet(session);
				modelAndView.addObject("result", result);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}	


	
	@RequestMapping(method = RequestMethod.POST)
	  public @ResponseBody String getBillerfield(@RequestParam("biller") String biller,@RequestParam("billerfield") String billerfield, @RequestParam("payfield") String payfield,HttpSession usersession) throws NamingException, ParseException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ApplicationContext  smscontext = new ClassPathXmlApplicationContext("Spring-SMS.xml");
		
		BillsPaymentDAO dao = (BillsPaymentDAO)context.getBean("billspaymentDAO");
		
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		
		OutgoingSMSWriterDAO smsdao =  (OutgoingSMSWriterDAO)smscontext.getBean("smsDAO");
		
		BillsPaymentModel bill = new BillsPaymentModel();
		
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		StringBuilder uploadaddressfieldBuilder = new StringBuilder(); 
		StringBuilder uploadbranchfieldBuilder = new StringBuilder(); 
		
		bill.setBiller_post_tran_field(billerfield);
		bill.setPayfield(payfield);
		bill.setAddress("");
		bill.setAccount_type("");
		bill.setName("");
		bill.setBankcode("");
		bill.setCheck_number("");
		
		bill.setBill_date(date);
		bill.setExpiry_date(date);
		
		
		logger.info("Processing Biller: "+biller+".......");
		
		if(biller.equalsIgnoreCase("MECOA"))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
		        
			String billdate = "FROMDATE";

			String billdatematch = billerfield.substring(billerfield.indexOf(billdate)+billdate.length()+1);
			
			String fromdate = billdatematch.substring(0,billdatematch.indexOf("~"));
			
			String expireddate = "TODATE";

			String expireddatematch = billerfield.substring(billerfield.indexOf(expireddate)+expireddate.length()+1);
			
			String todate = expireddatematch.substring(0,expireddatematch.indexOf("~"));
			
		    Date parsedfromdate = sdf.parse(fromdate);
		    
		    Date parsedtodate = sdf.parse(todate);
		    
		    java.sql.Timestamp sqlfromdate = new java.sql.Timestamp(parsedfromdate.getTime());
		    
		    java.sql.Timestamp sqltodate = new java.sql.Timestamp(parsedtodate.getTime());
			
			bill.setBill_date(sqlfromdate);
			
			bill.setExpiry_date(sqltodate);
		}
		
		if(biller.equalsIgnoreCase("SSS01"))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
	
		        
			String billdate = "FROMDATE";

			String billdatematch = billerfield.substring(billerfield.indexOf(billdate)+billdate.length()+1);
			
			String fromdate = billdatematch.substring(0,billdatematch.indexOf("~"));
			
			String expireddate = "TODATE";

			String expireddatematch = billerfield.substring(billerfield.indexOf(expireddate)+expireddate.length()+1);
			
			String todate = expireddatematch.substring(0,expireddatematch.indexOf("~"));
			
		    Date parsedfromdate = sdf.parse(fromdate);
		    
		    Date parsedtodate = sdf.parse(todate);
		    
		    java.sql.Timestamp sqlfromdate = new java.sql.Timestamp(parsedfromdate.getTime());
		    
		    java.sql.Timestamp sqltodate = new java.sql.Timestamp(parsedtodate.getTime());
			
			bill.setBill_date(sqlfromdate);
			
			bill.setExpiry_date(sqltodate);
		}



		String pid = (String) usersession.getAttribute("PID");
		
		String bid = (String) usersession.getAttribute("BID");
		

		logger.info("billerfield: "+billerfield);
		
		logger.info("payfield: "+payfield);
		
		

		ArrayList<String> upload_address_field = dao.getUploadAddressFields(biller);
		
		int uploadaddressarraysize = upload_address_field.size();
		
		ArrayList<String> upload_address_field_format = dao.getUploadAddressFieldsPattern(biller);

		
		if(uploadaddressarraysize > 0) 
		{
			
	        for (int i = 0; i < uploadaddressarraysize; i++) 
	        {
	            	
	        	
	        		if(uploadaddressarraysize == 1)
	        		{
	        			
	        		
	
	        			if(upload_address_field_format.size() > 0)
	        			{

	        				
	        				//logger.info("address_format " +upload_address_field_format.get(0).toUpperCase());
	        				
			        		String field = upload_address_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		String value = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        		//		
			        		//logger.info("billfield "+value.substring(0,value.indexOf("~")));
			        		
					        String uploadfield = value.substring(0,value.indexOf("~"));
					        
					       // logger.info("uploadaddress " +uploadfield);
					        
					        String addressfield = upload_address_field_format.get(0).toUpperCase().replace(field, uploadfield);
					        
					       // logger.info("new: "+addressfield);
					        
					        
					        bill.setAddress(addressfield.toUpperCase());
	        				
	        			}
	        			
	        			else
	        			{
			        		String field = upload_address_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		field = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+field.substring(0,field.indexOf("~")));
			        		
					        String uploadfield = "";
					        
					        uploadfield = uploadfield + field.substring(0,field.indexOf("~"));
					        
					        //logger.info("uploadaddress " +uploadfield);
					        
					        bill.setAddress(uploadfield.toString().toUpperCase());
	        			}
	        		

				        
	        			
	        		}
	        		
	        		else
	        		{

	        			
	        			if(upload_address_field_format.size() > 0)
	        			{


	        				
	        				//logger.info("address_format " +upload_address_field_format.get(0).toUpperCase());
	        				     
			        		String field = upload_address_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		String value = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+value.substring(0,value.indexOf("~")));
			        		
					        String uploadfield = value.substring(0,value.indexOf("~"));
					        
					       // logger.info("uploadaddress " +uploadfield);
					        
				
					        String[] addressfield  = new String[uploadaddressarraysize+1];
					        
					        addressfield [i]= upload_address_field_format.get(0).toUpperCase().replace(field, uploadfield);
					        
					        upload_address_field_format.set(0, addressfield [i]).toUpperCase();
					           		
					        System.out.println("new: "+addressfield[i]);

					        bill.setAddress(addressfield[i].toUpperCase());
	        				
					        
	        			}
	        			
	        			
	        			else
	        			{
			        		String field = upload_address_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		field = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+field.substring(0,field.indexOf("~")));
					        
			        		
					        if(i==uploadaddressarraysize-1){
					        	uploadaddressfieldBuilder.append(field.substring(0,field.indexOf("~")));
					        }else{
					        	uploadaddressfieldBuilder.append(field.substring(0,field.indexOf("~")) + "~");
					        }
					          
					      //  logger.info("uploadaddress "+uploadaddressfieldBuilder.toString());
					        
							bill.setAddress(uploadaddressfieldBuilder.toString().toUpperCase());
	        			}
		

	        		}
	
	 
	        }
			
		}

		ArrayList<String> upload_branch_field = dao.getUploadBranchFields(biller);
		
		int uploadbrancharraysize = upload_branch_field.size();
		
		ArrayList<String> upload_branch_field_format = dao.getUploadBranchFieldsPattern(biller);
		
		if(uploadbrancharraysize > 0) 
		{
			
	        for (int i = 0; i < uploadbrancharraysize; i++) 
	        {
	            	
	        	
	        		if(uploadbrancharraysize == 1)
	        		{
	        			if(upload_branch_field_format.size() > 0)
	        			{
	        				
	        				//logger.info("branch format " +upload_branch_field_format.get(0).toUpperCase());
       				     
			        		String field = upload_branch_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		String value = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+value.substring(0,value.indexOf("~")));
			        		
					        String uploadfield = value.substring(0,value.indexOf("~"));
					        
					       // logger.info("uploadaddress " +uploadfield);
					        
				
					        String[] branchfield  = new String[uploadbrancharraysize+1];
					        
					        branchfield [i]= upload_branch_field_format.get(0).toUpperCase().replace(field, uploadfield);
					        
					        upload_branch_field_format.set(0, branchfield [i]).toUpperCase();
					           		
					        System.out.println("new: "+branchfield[i]);

					        bill.setBranchcode(branchfield[i]);
	        				
	        				
	        				
	        			}else
	        			
	        			{
	        				
	        				
			        		String field = upload_branch_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		field = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+field.substring(0,field.indexOf("~")));
			        		
					        String uploadfield = "";
					        
					        uploadfield = uploadfield + field.substring(0,field.indexOf("~"));
					        
					       // logger.info("uploadbranch " +uploadfield);
					        
					        bill.setBranchcode(uploadfield.toString());
	        				
	        			}
	        		

	        			
	        		}
	        		
	        		else
	        		{
	        			
	        			
	        			
	        			
	        			if(upload_branch_field_format.size() > 0)
	        			{
	        				
	        				//logger.info("branch format " +upload_branch_field_format.get(0).toUpperCase());
       				     
			        		String field = upload_branch_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		String value = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+value.substring(0,value.indexOf("~")));
			        		
					        String uploadfield = value.substring(0,value.indexOf("~"));
					        
					       // logger.info("uploadaddress " +uploadfield);
					        
				
					        String[] branchfield  = new String[uploadbrancharraysize+1];
					        
					        branchfield [i]= upload_branch_field_format.get(0).toUpperCase().replace(field, uploadfield);
					        
					        upload_branch_field_format.set(0, branchfield [i]).toUpperCase();
					           		
					      //  System.out.println("new: "+branchfield[i]);

					        bill.setBranchcode(branchfield[i]);
	        				
	        				
	        				
	        			}

	        			else
	        			{
			        		String field = upload_branch_field.get(i).toUpperCase();
			        		
			        		//logger.info("field " +field);
			        		
			        		field = billerfield.substring(billerfield.indexOf(field)+field.length()+1);
			        				
			        		//logger.info("billfield "+field.substring(0,field.indexOf("~")));
					        

					        if(i==uploadbrancharraysize-1)
					        {
					        	uploadbranchfieldBuilder.append(field.substring(0,field.indexOf("~")));
					        }else
					        {
				        	
				        			uploadbranchfieldBuilder.append(field.substring(0,field.indexOf("~")) + "~");
				        		
					        	
					        }
					        				     	        		        
					        //logger.info("uploadbranch "+uploadbranchfieldBuilder.toString());
					        
							
							bill.setBranchcode(uploadbranchfieldBuilder.toString());
	        				
	        			}

	        			

	        		}
	        	


	        		
	 
	        }
			
		}
		
		if(billerfield.contains("ACCNTYPE"))
		{
			String Accounttypematch = "ACCNTYPE";

			String accountntypematch = billerfield.substring(billerfield.indexOf(Accounttypematch)+Accounttypematch.length()+1);
			
			String accounttype = accountntypematch.substring(0,accountntypematch.indexOf("~")).toUpperCase();
			
			if(biller.equalsIgnoreCase("SKY01"))
			{
				bill.setBranchcode(accounttype);
			}
			else
			{
				bill.setAccount_type(accounttype);
				
			}
			
			billerfield = billerfield.substring(billerfield.indexOf("~")+1);
			
			
			
		}

		
		String accnt = dao.checkDefaultAccnType(biller);
		
		if(accnt != null){
			logger.info("account "+accnt);
			bill.setAccount_type(accnt);
		}
			
		
		StringBuilder namelist = new StringBuilder();
		
   		String name = billerfield.substring(billerfield.indexOf("NAME|")+"NAME|".length());

		if(billerfield.contains("NAME|"))
		{
	        int wordFound = StringUtils.countOccurrencesOf(billerfield, "NAME|");
	        
	        if(wordFound>1)
	        {
	        	
	        	for(int i=0; i<wordFound;i++)
	        	{
	        			
	        			String names = name.substring(0,name.indexOf("~"));
	        			//System.out.println(names);
	        			String pattern = name.substring(name.indexOf("NAME|")+"NAME|".length());
	        			//System.out.println(pattern);
	        			name = pattern;

	        			
	        			if(biller.equalsIgnoreCase("SSS03")){
	        		        if(i==wordFound-1){
					            namelist.append(names);
					        }else{
					            namelist.append(names+"~");
					        }
					       
	        				
	        			}
	        			else{
	        		        if(i==wordFound-1){
					            namelist.append(names);
					        }else{
					            namelist.append(names+",");
					        }
					       
	        			}
			
				   
	        	}
	        	
	        	bill.setName(namelist.toString());
	        	
	            //System.out.println(namelist);
	        }
	        
	        else
	        {
				String Name = "NAME";
				
				String namematch = billerfield.substring(billerfield.indexOf(Name)+Name.length()+1);
				
				String nameval = namematch.substring(0,namematch.indexOf("~"));
				
				bill.setName(nameval);
				
				//System.out.println(nameval);	
	        }
	        

		}
		
		if(biller.equalsIgnoreCase("SMT01"))
		{
			
			String phonename = billerfield.substring(billerfield.indexOf("TELNO")+"TELNO".length()+1);
			
			bill.setName(phonename.substring(0,phonename.indexOf("~")));
			
			//logger.info("name "+phonename.substring(0,phonename.indexOf("~")));
			
			
		}

		if(biller.equalsIgnoreCase("MLIFE"))
		{
			String AccountNomatch = "ACCNTNO";

			String accountnumbermatch = billerfield.substring(billerfield.indexOf(AccountNomatch)+AccountNomatch.length()+1);
			
			String accountnumber = accountnumbermatch.substring(0,accountnumbermatch.indexOf("~"));
			
			
			StringBuffer sb = new StringBuffer(accountnumber);
		    sb.insert(accountnumber.length()-1, "-");
		    
		    bill.setAccountnumber(sb.toString());
	
		}else if(biller.equalsIgnoreCase("MWCOM"))
		{
			String AccountNomatch = "ACCNTNO";

			String accountnumbermatch = billerfield.substring(billerfield.indexOf(AccountNomatch)+AccountNomatch.length()+1);
			
			String accountnumber = accountnumbermatch.substring(0,accountnumbermatch.indexOf("~"));
			
			StringBuffer sb = new StringBuffer(accountnumber);
	
			int accountlength = accountnumber.length();
			
			for(int i = 0;i<10-accountlength;i++)
			{
				
				sb.insert(i, "0");
			}
			

			

		    bill.setAccountnumber(sb.toString());
//		    logger.info(sb);
		
			

		}
		else
		{
			String AccountNomatch = "ACCNTNO";

			String accountnumbermatch = billerfield.substring(billerfield.indexOf(AccountNomatch)+AccountNomatch.length()+1);
			
			String accountnumber = accountnumbermatch.substring(0,accountnumbermatch.indexOf("~"));

			bill.setAccountnumber(accountnumber);
		}
		
		

    	String amountpayMatch = "AMT";
    	
    	String amountrecMatch = "REC";
    	
    	String amounttopay = payfield.substring(payfield.indexOf(amountpayMatch)+amountpayMatch.length()+1,payfield.indexOf("~"));
    
    	logger.info("amount: "+amounttopay);

	 	String billamt = payfield.substring(payfield.indexOf(amountrecMatch)+amountrecMatch.length()+1);
	 	
	 	logger.info("billamt: "+billamt);
		
		bill.setTxtype("billspayment");
	
		bill.setRequestype(biller);
		bill.setPid(pid);	
		bill.setBid(bid);
		bill.setProdtype("BayadCenter");
		bill.setAmount(amounttopay);
		
		

		bill.setCompanyid(biller);
		
		bill.setBill_amount(amounttopay);
		bill.setCash_amount(amounttopay);
		


		PartnerProfile partner = null;

		int errorState = 0;


		
		try {
			logger.info("get profile: " + usersession.getAttribute("USER"));
			partner = profiledao.GetProfilebyUsername(usersession.getAttribute("USER").toString());
			bill.agentid = partner.agentid;
			
			float servicecharge = dao.getServiceCharge(biller);
			
			if(servicecharge > 0){
				bill.txid = dao.insertTransactionWithCISFI(bill,usersession);
			}else
			{
				bill.txid = dao.insertTransaction(bill,usersession);
			}
			
			
			
			logger.info("topup tx id is: "+bill.txid);
		} catch (Exception e) {
			e.printStackTrace();	
			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
			logger.info(P2MConstants.getMessage(errorState));
			return "fail";
		}
		
		

		if ((partner.wallet <= 0) || (Float.parseFloat(bill.getAmount()) <= 0) || ( partner.wallet < Float.parseFloat(bill.getAmount()) )) {
			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}

		if ( errorState != 0 ) {
			
			dao.updateTransaction(bill.txid,errorState);


			logger.info(P2MConstants.getMessage(errorState));
		
			return "fail";
		}

		int debit = 0;
		String session = null;
		String resp = null;
			
			if(partner.runmode.equalsIgnoreCase("TEST"))
			
			{
				
				String paymenttype = (String) usersession.getAttribute("paymenttype");
				
				if(paymenttype.equalsIgnoreCase("PREPAID"))
				{
					
					dao.deductWallet(usersession, bill.txid, Float.parseFloat(bill.getAmount()));

					
					logger.info("deducting prepaid wallet done");
				}
				else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
					
					dao.creditWallet(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
					
					logger.info("deducting settlement done");
					
				}
				else if(paymenttype.equalsIgnoreCase("FEES")){
					
					dao.creditWallet(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
					
					logger.info("deducting fees wallet done");
				}
				
				resp = "";
				

			}
			
			
			else
			
			{
				

				
				
				try {
				

					String paymenttype = (String) usersession.getAttribute("paymenttype");
					
					if(paymenttype.equalsIgnoreCase("PREPAID"))
					{
						
						debit = dao.updatetxid(usersession, bill.txid, Float.parseFloat(bill.getAmount()) * -1);
						
						
						logger.info("deducting prepaid wallet done");
					}
					else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
						
						debit = dao.creditWallet(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
					
						
						logger.info("deducting settlement done");
						
					}
					else if(paymenttype.equalsIgnoreCase("FEES")){
						
						debit = dao.creditWallet(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
						
						
						logger.info("deducting fees wallet done");
					}
					
				logger.info("Debit result: " + debit);
				logger.info("+++++++++++++++++++Bayad Center++++++++++++++++");
				
				if (debit != 1) {
					logger.info("Unable to deduct from wallet from  " + partner.partnerid + "; Amount: " + bill.getAmount());	
					errorState = P2MConstants.DEBIT_FAILED_CODE;				
				}
				
				else		
				{

								
								BayadCenter bayad = new BayadCenter();		
								
								logger.info("+++++++++++++++++++Getting Service Charge ++++++++++++++++");
								
								float servicecharge = dao.getServiceCharge(biller);
																
								if(servicecharge > 0){
									logger.info("+++++++++++++++++++ Service Charge is: "+servicecharge+" ++++++++++++++++");
									dao.updateServicefee(bill.txid, servicecharge);
								}
								
								logger.info("+++++++++++++++++++Connecting to Bayad Center++++++++++++++++");
								
								String surcharge = "0.00";
								String surchargerate = "0.00";
								String accntype = dao.getAccounttype(bill.getCompanyid());
								
								String response = bayad.billsPayment(biller.toUpperCase(),billerfield,payfield,bill.txid+"",servicecharge+"",surcharge,surchargerate,accntype);
								
								
							 	int start_pos = response.indexOf('|') + 1;
							 	int end_pos = response.indexOf('|',start_pos);
							 	String status = response.substring(start_pos,end_pos);
	
							 	int nd_pos = response.indexOf('|',end_pos+1);
							 	
								resp = response.substring(end_pos+1,nd_pos);
								
								logger.info("Response: "+resp+", Status: "+status);
								
								if(status.equalsIgnoreCase("false"))
								{
									dao.updatetxid(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
								}
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
				
				// Refund wallet
				if ( debit == 1 ) {
				//	walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
					dao.updatetxid(usersession, bill.txid, Float.parseFloat(bill.getAmount()));
				}
			}
			try {
				//logger.info("ErrorState on update for txn " + bill.txid + " = " + errorState);
				
				if ( resp != null ) {
					dao.updateTransaction(bill.txid,resp);

				}    	
				

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			
				
			}
				
				
				
				
			}
		
		
	
		if(resp.length()==0){
			logger.info("+++++++++++++++++++Bills Payment Successful! Exact Payment Received++++++++++++++++");
			resp = "Transaction Successful";
		}
		
		logger.info("+++++++++++++++++++Bills Payment Status! "+resp+" ++++++++++++++++");
		return resp;


		
	}
	

    public boolean isNumber( String tx )  
    {  
       try  
       {  
          Integer.parseInt( tx );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }

	public void setServletContext(ServletContext context) {
		this.servletContext = context;		
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
