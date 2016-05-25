package com.home.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class SystemUtil {

	public static void main(String[] args) {
		try {
			System.out.println(format2Money(new BigDecimal("60076000.00")));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			String f = SystemUtil.class.getClassLoader().getResource("config.properties").getFile();
			Properties prop = new Properties();
			prop.load(new FileInputStream(f));
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public static String format2Money(Object value) throws Exception{
		if(value != null){
			ScriptEngine engine = compileScript("function format(num){var n = num.toString(), p = n.indexOf('.');return n.replace(/\\d(?=(?:\\d{3})+(?:\\.|$))/g, function($0, i){return p<0 || i<p ? ($0+',') : $0; });}");
			String rs=StringUtil.notNull(engine.eval("format("+value+")"));	
			if(rs.length() > 0){
				return rs + " VNĐ";
			}
		}
		return "";
	}
	
	public static byte[] getCustomImageInBytes(File image) {
		BufferedImage originalImage;
		byte[] imageInByte = null;
		try {
			originalImage = ImageIO.read(image);
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageInByte;
	}
}
