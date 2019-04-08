package com.kingboot.common.util;

import java.security.MessageDigest;

/**
 * MD5 Utils
 */

public class MD5Util {
    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    public static String encrypt32(String strObj) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = strObj.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
    }
    
    public static String encrypt32Up(String strObj) {
        return encrypt32(strObj).toUpperCase();
    }
    
    public static String encrypt16(String strObj) {
        return encrypt32(strObj).substring(8, 24);
    }
    
    public static String encrypt16Up(String strObj) {
        return encrypt32Up(strObj).substring(8, 24);
    }
    
    
}