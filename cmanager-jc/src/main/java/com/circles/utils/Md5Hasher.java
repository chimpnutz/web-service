package com.circles.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hasher {

	public String md5(String stringToConvert){
		String converted="";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(stringToConvert.getBytes());
			byte byteData[] = md.digest();
			for(int i = 0;i<byteData.length;i++){
				converted+=Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return converted;
	}
	
}
