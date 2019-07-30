package com.kingboot.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


/**
 * <p class="detail">
 * 功能:md5工具类
 * </p>
 * @author Kings
 * @ClassName Md5Util
 * @Version V1.0.
 * @date 2019.07.30 11:37:01
 */
public class Md5Util {
	
	public static String encrypt32(String strObj) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = strObj.getBytes(StandardCharsets.UTF_8);
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