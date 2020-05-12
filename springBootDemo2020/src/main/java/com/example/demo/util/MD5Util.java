package com.example.demo.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * MD5 Util，密码加密
 * @author: HymanHu
 * @date: 2019年10月29日
 */
public class MD5Util {
/*	private static final String SALT = "&%5123***&&%%$$#@";
	
	public static String getMD5(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String base = str + "/" + SALT;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5("111111"));
	}*/
	
	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
 
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
 
    }  
 
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String getMD5(String inStr){  
 
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
 
    }  
}