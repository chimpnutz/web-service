package com.pc2mweb.utility.function;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pc2mweb.beans.CredentialsBean;

public class pc2mwebFunc extends JdbcDaoSupport {
	
	private SecureRandom rand;
	
		 public boolean getGlobeCredentials(String pid, CredentialsBean obj){
			 
			 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
				
			 DataSource ds = (DataSource) context.getBean("dataSource");
							
			 JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			 
			 StringBuilder strSQL = new StringBuilder();

			 strSQL.append("select globe_wallet,globe_password from partners where partnerid = ?");

			// SqlRowSet rs = jdbcTemplate.queryForRowSet(strSQL.toString(), pid);
			 
			 List<Map<String,Object>> rows = jdbcTemplate.queryForList(strSQL.toString(), pid);
			
			 for (Map row : rows) {
				 
	   
				        String guser = (String)(String)(row.get("globe_wallet"));
				        String gpass = (String)(String)(row.get("globe_password"));		        
				  
				        if(guser != null || gpass != null){
				        	obj.amax_user = guser;
				        	obj.amax_pass = gpass;
					   		
					   		return true;
				        }else{
				        	return false;
				        }
		   	
			   	}
			 return false;
		 }
		 
		 
		 
		    public  static String P2MConstantsgetMessage(int errcode)
		    {
		        String retval;
		        switch(errcode)
		        {
		        case -1: // '\013'
		            retval = "No Wallet Information";
		            break;
		        case -2: // '\013'
		            retval = "No Permission to do Transaction";
		            break;
		            
		        case 11: // '\013'
		            retval = "Profile not found";
		            break;

		        case 12: // '\f'
		            retval = "Mobile phone number not in range";
		            break;

		        case 13: // '\r'
		            retval = "Insufficient wallet balance";
		            break;

		        case 14: // '\016'
		            retval = "Wallet debit failed";
		            break;

		        case 15: // '\017'
		            retval = "No response from amax";
		            break;

		        case 16: // '\020'
		            retval = "General error";
		            break;

		        case 17: // '\021'
		            retval = "Amax session not established";
		            break;

		        case 62: // '>'
		            retval = "Amax top up error";
		            break;

		        case 63: // '?'
		            retval = "Amax login error";
		            break;
		            
		        case 64: // '?'
		            retval = "Session Result Invalid";
		            break;

		        case 90: // 'Z'
		            retval = "Agent permission error";
		            break;

		        default:
		            retval = "Unknown error";
		            break;
		        }
		        return retval;
		    }
			
			
//			public static String AmaxgetMessage(int errorstate){
//				
//		        String retval;
//		        switch(errorstate)
//		        {
//		        case 0: // '\0'
//		            retval = "Topup Successful!";
//		            break;
//
//		        case 1: // '\001'
//		            retval = "Insufficient Wallet Balance";
//		            break;
//
//		        case 2: // '\002'
//		            retval = "Invalid Mobile Phone Number";
//		            break;
//
//		        case 3: // '\003'
//		            retval = "Invalid Topup Amount";
//		            break;
//
//		        case 4: // '\004'
//		            retval = "Amax Operation Failed";
//		            break;
//
//		        default:
//		            retval = "Unknown Error";
//		            break;
//		        }
//		        return retval;
//				
//			}
		    
			public static String AmaxgetMessage(int errorstate){
				
		        String retval;
		        switch(errorstate)
		        {
		        case 0: // '\0'
		            retval = "Amax Topup Successful";
		            break;

		        case 1: // '\001'
		            retval = "Session Error";
		            break;

		        case 2: // '\002'
		            retval = "Invalid Credentials";
		            break;

		        case 3: // '\003'
		            retval = "Insufficient Funds";
		            break;

		        case 4: // '\004'
		            retval = "Invalid Mobile Number";
		            break;
		            
		        case 5: // '\004'
		            retval = "Invalid Amount";
		            break;
		            
		        case 6: // '\004'
		            retval = "Operation Failed";
		            break;

		        default:
		            retval = "Unknown Error";
		            break;
		        }
		        return retval;
				
			}
		 
		 
			public static String Gcashgetmessage(int errorstate){
				
		        String retval;
		        switch(errorstate)
		        {
		        case 0: // '\0'
		            retval = "Gcash Cashin Successful";
		            break;

		        case 1: // '\001'
		            retval = "Session Error";
		            break;

		        case 2: // '\002'
		            retval = "Invalid Credentials";
		            break;

		        case 3: // '\003'
		            retval = "Insufficient Funds";
		            break;

		        case 4: // '\004'
		            retval = "Invalid Mobile Number";
		            break;
		            
		        case 5: // '\004'
		            retval = "Invalid_Amount";
		            break;
		            
		        case 6: // '\004'
		            retval = "Operation_Failed";
		            break;

		        default:
		            retval = "Unknown Error";
		            break;
		        }
		        return retval;
				
			}

	   
			public String generateID() {
				StringBuffer inputMask = new StringBuffer();
				String algorithm = "SHA1PRNG";
				StringBuffer id = new StringBuffer("");
				String inputMaskType[] = {"x", "9", "x"};
				for(int i = 0; i < 12; i++) {
				inputMask.append(inputMaskType[(int)(Math.random() + 0.5D)]);
				}
				try {
				rand = SecureRandom.getInstance(algorithm);
				} catch(NoSuchAlgorithmException ne){
				System.out.print(ne.getMessage());
				ne.printStackTrace();
				}
				int len = inputMask.length();
				for(int j = 0; j < len; j++) {
				if(inputMask.charAt(j) == 'x') {
				id.append(nextLetter(true));
				continue;
				}
				if(inputMask.charAt(j) == '9') {
				id.append(nextNumber());
				}
				}
				return id.toString();
				}

	   
			private char nextLetter(boolean allCaps) {
				if(allCaps) {
				return (char)(65 + rand.nextInt(26));
				} else {
				return (char)(rand.nextInt(1) != 0 ? 97 + rand.nextInt(26) : 65 + rand.nextInt(26));
				}
				}
				private int nextNumber() {
				return rand.nextInt(9);
				}




}
