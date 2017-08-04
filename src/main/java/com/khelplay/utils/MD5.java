package com.khelplay.utils;

import java.security.MessageDigest;

public class MD5 {
	
	public static String md5(String string) {
		String md5 = null;
		try{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(string.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     		hexString.append(hex);
	    	}
	    	md5 = hexString.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
    	return md5;
	}

}
