import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;


public class Simulate {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		String counter = "300";
		StringBuffer sb = new StringBuffer(counter);
		
		int accountlength = counter.length();
		
		for(int i = 0;i<4-accountlength;i++)
		{
			
			sb.insert(i, "0");
		}
		
		System.out.println(sb);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		
//		
//	    Date parsedfromdate = sdf.parse("08/01/2013");
//
//	    java.sql.Timestamp sqlfromdate = new java.sql.Timestamp(parsedfromdate.getTime());
//	    
//	    System.out.println(sqlfromdate);
	    
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(date));
		
//		String name = "runjel10";
		
		
//		StringBuffer sb = new StringBuffer(name);
//		
//		int accountlength = name.length();
//		
//		for(int i = 0;i<10-accountlength;i++)
//		{
//			
//			sb.insert(i, "0");
//		}
//		
//
//		
//
//	   
//	    System.out.println(sb);
	    
		//billerfield = billerfield.replace(accountnumber, "");
		
		
//		StringBuilder namelist = new StringBuilder();
//		
//		String billerfield = "ACCNTYPE|C~ACCNTNO|0117902838~RETYPE|0117902838~TELNO|2131231~BILLAMT|500";
//		
//
//
//		//int del = billerfield.indexOf("~");
//   		String name = billerfield.substring(billerfield.indexOf("TELNO")+"TELNO".length()+1);
//   		
//   		//System.out.println(del);
//   		System.out.println(name.substring(0,name.indexOf("~")));

//		if(billerfield.contains("NAME|"))
//		{
//	        int wordFound = StringUtils.countOccurrencesOf(billerfield, "NAME|");
//	        
//	        if(wordFound>1)
//	        {
//	        	
//	        	for(int i=0; i<wordFound;i++)
//	        	{
//	        			
//	        			String names = name.substring(0,name.indexOf("~"));
//	        			System.out.println(names);
//	        			String pattern = name.substring(name.indexOf("NAME|")+"NAME|".length());
//	        			System.out.println(pattern);
//	        			name = pattern;
//
//	        			
//				        if(i==wordFound-1){
//				            namelist.append(names);
//				        }else{
//				            namelist.append(names+"~");
//				        }
//				       
//				   
//	        	}
//	        	
//	            System.out.println(namelist);
//	        }
//	        
//	        else
//	        {
//				String Name = "NAME";
//				
//				String namematch = billerfield.substring(billerfield.indexOf(Name)+Name.length()+1);
//				
//				String nameval = namematch.substring(0,namematch.indexOf("~"));
//				
//				
//				
//				System.out.println(nameval);	
//	        }
//	        
//
//		}
		// TODO Auto-generated method stub

	}

}
