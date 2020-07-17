package kr.co.utils;

import java.io.UnsupportedEncodingException;

public class Utils {
	
	//필터로 인코딩이 안될때	
	public static String toKor(String msg) {
		try {
			return new String(msg.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null; //null포인트익센셥 방지
		}
	}

}
