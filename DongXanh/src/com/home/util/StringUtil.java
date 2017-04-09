package com.home.util;

import java.sql.Date;
import java.text.Normalizer;
import java.util.Calendar;
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
	
	public static String getDayName(Date datePlan) throws Exception{
		if(datePlan != null){
			String[] arr = new String[]{"Chủ Nhật", "Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy"};
			Calendar cal = Calendar.getInstance();
			cal.setTime(datePlan);
			return arr[cal.get(Calendar.DAY_OF_WEEK)-1];
		}
		return "";
	}
	
	public static String getDaySection(Date datePlan) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(datePlan);
		//System.out.println(datePlan + " ------> " + cal.get(Calendar.HOUR_OF_DAY));
		if(cal.get(Calendar.HOUR_OF_DAY) >= 12){
			return "C";
		}else{
			return "S";
		}
	}
	
	public static String roundZero(Object value, int len){
		String v = notNull(value);
		if(len > 0){
			int more = len - v.length();
			if(more > 0){
				for (int i = 0; i < more; i++) {
					v = "0" + v;
				}
			}
		}
		return v;
	}
	
	public static void main(String[] args) {
		System.out.println(roundZero("12345", 2));
	}
}
