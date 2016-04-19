package com.home.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class SystemUtil {

	public static void main(String[] args) {
		System.out.println(getCurrentDate().toString());
	}
	
	public static String getUserDir() {
		String userDir = System.getProperty("user.dir").replace("\\", "/");
		return userDir;
	}
	
	public static Date getCurrentDate() {
		return convertStringDateToDate(convertDateToString(new Date()), "yyyy-MM-dd");
	}

	public static Date convertStringDateToDate(String strDate, String pattern) {
		try {
			SimpleDateFormat format =  new SimpleDateFormat(pattern);
			return strDate != null ? format.parse(strDate) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertDateToString(Date pDate) {
		String temp = "";
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		temp = d.format(pDate);
		return temp;
	}
	
	public static String convertDateToString(Date pDate, String pattern) {
		String temp = "";
		SimpleDateFormat d = new SimpleDateFormat(pattern);
		temp = d.format(pDate);
		return temp;
	}

	/**
	 * @todo get current date as format: yyyy-MM-dd
	 * @return current date as yyyy-MM-dd
	 */
	public static String getCurrentDateYYYY_MM_DD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 *
	 * @return
	 */
	public static ScriptEngine compileScript(String source) throws Exception{
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
			engine.eval(source);
			return engine;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public static String getValuePropertiesByKey(String key) throws FileNotFoundException, IOException {
		String f = SystemUtil.class.getClassLoader().getResource("config.properties").getFile();
		Properties prop = new Properties();
		prop.load(new FileInputStream(f));
		return prop.getProperty(key);

	}
}
