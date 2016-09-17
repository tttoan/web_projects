package com.home.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
	public static String notNull(Object strIn) {
		if (strIn != null) {
			return strIn.toString().trim();
		} else {
			return "";
		}
	}
	
	public static String replaceInvalidChar(String strIn) {
		if (strIn != null) {
			strIn = strIn.replace("\\", "\\\\");
			strIn = strIn.replace("'", "\\'");
			strIn = strIn.replace("\"", "\\\"");
			strIn = strIn.replace("\n", "\\n");
			return strIn;
		} else {
			return "";
		}
	}

	private static String removeAccent(String val) {
		String temp = Normalizer.normalize(val, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace("Đ", "d").replace("đ", "d");
	}
	
	public static String generateUserName(String val){
		String renderVal = removeAccent(val);
		String generateVal = "";
		String[] arrVal = renderVal.split("\\s+");
		for (int i = 0;i < arrVal.length - 1;i++) {
			if(i == 0)
				generateVal += arrVal[arrVal.length-1];
			generateVal += arrVal[i].charAt(0);
			
		}
		return generateVal;
	}
	
}
