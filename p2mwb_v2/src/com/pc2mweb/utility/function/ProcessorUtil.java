package com.pc2mweb.utility.function;

import com.pc2mweb.beans.AMAXGCASHConstants;


public class ProcessorUtil {
	
	public static void main(String args []){
		ProcessorUtil p = new ProcessorUtil();
		boolean p1 = p.isValidDenom("25","25,50,150,300,500,1000");
		System.out.println(p1);
	}
	public static boolean isValidDenomRange(String denom, String topupValues) {
		boolean inRange = false;
		
		if (topupValues.indexOf("-") > 0) {
		      String[] arrTopupValues	= topupValues.split("-");
		      int startRange = Integer.parseInt(arrTopupValues[0]);
		      int endRange = Integer.parseInt(arrTopupValues[1]);
		      int intDenom = Integer.parseInt(denom);
		      if (intDenom < startRange || intDenom > endRange) {
		    	  inRange = false;
		      } else if ( (intDenom >= startRange) && (intDenom <= endRange)) {
		    	  inRange = true;
		      }
		      
		} else if (topupValues.indexOf(",") > 0) {
			inRange = isValidDenom(denom, topupValues);
		}
		
		
		return inRange;
	}

	public static boolean isValidDenom(String denom, String dbTopupVal) {
//		String[] arrTopupvalues = topupValues.split(",");
//		boolean isValid = false;
//		for (String a : arrTopupvalues) {
//			if (a.trim().equals(denom)) {
//				isValid = true;
//			}
//		}
//		return isValid;
		
		String denoms[];
		boolean validDenom = false;
		
		int dashPos;
		
		dashPos = dbTopupVal.indexOf("-");
		if (dashPos > 0) {
			// Range specified in denominations
			int min, max, cnt, num;
			
			min = Integer.parseInt(dbTopupVal.substring(0,dashPos));
			max = Integer.parseInt(dbTopupVal.substring(dashPos+1,dbTopupVal.length()));
			num = min;
			
			cnt = max - min + 1;
			denoms = new String[cnt];
			for (int i = 0; i < cnt; i++) {
				denoms[i] = Integer.toString(num++);
			}
		} else {
			denoms = dbTopupVal.split(",");
		}
		
		for (int i = 0; i < denoms.length; i++) {
			if ( Integer.parseInt(denoms[i]) == Integer.parseInt(denom)) {
				validDenom = true;
				break;
			}
		}
		
		return validDenom;
	}


	public static boolean isValidGlobeNumber(String targetmobileno, String globeprefix) {
		boolean isValid = false;
		if (targetmobileno.startsWith("+63")) {
			targetmobileno = "0" + targetmobileno.substring(3,6);
		} else if (targetmobileno.startsWith("63")) {
			targetmobileno = "0" + targetmobileno.substring(2,5);
		}else{
			targetmobileno = targetmobileno.substring(0, 4);
		}
		String[] arrTopupvalues = globeprefix.split(",");		
		for (String a : arrTopupvalues) {
			if (a.trim().equals(targetmobileno)) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	public static boolean isValidPartner(String partner, String partners) {
		String[] arrTopupvalues = partners.split(",");
		boolean isValid = false;
		for (String a : arrTopupvalues) {
			if (a.trim().equals(partner)) {
				isValid = true;
			}
		}
		return isValid;
	}

}