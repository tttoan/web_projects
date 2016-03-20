package com.home.util;

public class StringUtil {
	public static String notNull(Object strIn) {
		if (strIn != null) {
			return strIn.toString().trim();
		} else {
			return "";
		}
	}
}
