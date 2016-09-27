package com.home.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class DateUtils {
	public static String[] DATE_PATTERNS = new String[] { "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy", "dd-MM-yyyy", "dd MM yyyy", "MM/dd/yyyy", "MM-dd-yyyy", "MM dd yyyy", "yyyy/MM/dd", "yyyy-MM-dd",
			"yyyy MM dd" };
	public static final String HOUR_FORMAT = "##:##";
	public static final String DATE_FORMAT = "##-##-####";
	public static final String DATE_FORMAT_HH_MM = "##-##-#### ##:##";
	public static final String YEAR_FORMAT = "####";
	public static final String MONTH_FORMAT = "##-####";
	public static final String DATEFORMAT = "####-##-##";
	public static final String DATEFORMAT_DD_MM = "##-##";
	public static final char SEPARATE_CHAR = '_';
	public static final char SEPARATECHAR = '_';
	public static final String HOUR_MINUTE = "HH:mm";
	public static final String HOUR = "HH:mm:ss.SS";
	public static final String DEFAULT_STYLE_DATE_STRING = "dd/MM/yyyy";
	public static final String TIME_STYLE_DATE_STRING = "dd-MM-yyyy";
	public static final String TIME_STYLE_DATE_STRING_ = "dd.MM.yyyy";
	public static final String TIME_STYLE_DATE_STRING_MM_yy = "MMyy";
	public static final String TIME_STYLE_dd_MM_yyyy_HH_mm = "dd-MM-yyyy HH:mm";
	public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_MM_DD_YYYY = "MM-dd-yyyy";
	public static final String DATE_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_YYMMDDHH = "yyMMddHH";
	public static final String DATE_YYMMDD = "yyMMdd";
	public static final String TIME_STYLE2 = "HH:mm a";
	public static final String TIME_STYLE3 = "E dd-MM";
	public static final String[] FULLTIME = new String[] { "dd-MM-yyyy HH:mm", "dd-MM-yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss.SS" };

	public static final String DATEWITHTIME = "dd-MM-yyyy HH:mm";
	public static final String DATEWITHOUTTIME = "dd-MM-yyyy";
	public static final String[] DATE_WITHOUT_TIME_TYPE_CHINA = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy MM dd" };

	public static final String FULLTIME_TO_VIEW = "dd-MM-yyyy HH:mm:ss";
	public static final long A_DAY = 86400000;
	public static final int A_YEAR = 365;
	public static final String REPORT_TIME_STYLE = "E  dd-MM-yyyy";
	public static final String[] FULLTIME_REPORT = new String[] { "dd-MM-yyyy HH:mm:ss.SSS", "dd-MM-yyyy HH:mm:ss.S", "dd-MM-yyyy HH:mm:ss.SS" };
	public static final String TIME_STYLE_REPORT_HOUR = "HH:mm:ss";

	public static final String FULL_TIME_SQL = "yyyy-MM-dd HH:mm:ss";
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

	// public static final String[] DATE_PATTERN = new String[]{"yyyy-MM-dd"};

	public static String getDateToString(Date date, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING);
		return date != null ? format.format(date) : "";
	}

	public static String getHourMinute(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE);
		return date != null ? format.format(date) : "";
	}

	public static String getHour(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(HOUR);
		return date != null ? format.format(date) : "";
	}
	
	public static String getHour(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return date != null ? format.format(date) : "";
	}

	public static Date tryConvertStringToDate(String strDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING);
			if(strDate.contains("-")){
				return format.parse(strDate);
			}else if(strDate.contains("/")){
				format = new SimpleDateFormat(DEFAULT_STYLE_DATE_STRING);
				return format.parse(strDate);
			}
			return strDate != null ? format.parse(strDate) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date convertStringDateToDate(String strDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING);
			return strDate != null ? format.parse(strDate) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertStringDateToDate(String strDate, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return strDate != null ? format.parse(strDate) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertStringDateToDate_(String strDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING_);
			return strDate != null ? format.parse(strDate) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateToString_MM_yy(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING_MM_yy);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString_hh_mm(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_dd_MM_yyyy_HH_mm);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString_YYYY_MM_DD(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_YYYY_MM_DD);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString_MM_DD_YYYY(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_MM_DD_YYYY);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString_YYYYMMDD(Date date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATE_YYYYMMDD);
		return date != null ? format.format(date) : "";
	}

	public static String getDateToString2(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERNS[1]);
		return date != null ? format.format(date) : "";
	}

	public static String getTimeToView(Timestamp time) {
		SimpleDateFormat format = new SimpleDateFormat(FULLTIME_TO_VIEW);
		return time != null ? format.format(time) : "";
	}

	public static long getStartOfTheDay(long date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTimeInMillis();
	}

	public static long getEndOfTheDay(long date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static String getStringFromDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE_DATE_STRING);
		return date != null ? format.format(date) : "";
	}

	public static String getStringFromDate(Date date, String formatType) {
		SimpleDateFormat format = new SimpleDateFormat(formatType);
		return date != null ? format.format(date) : "";
	}

	public static long getDate(String str) throws ParseException {
		return str != null ? org.apache.commons.lang.time.DateUtils.parseDate(str, FULLTIME).getTime() : null;
	}

	public static long getDateFromString(String str) throws ParseException {
		return str != null ? org.apache.commons.lang.time.DateUtils.parseDate(str, new String[] { DATEWITHOUTTIME }).getTime() : null;
	}

	public static Long getDateReturnLong(String str) throws ParseException {
		return str != null ? org.apache.commons.lang.time.DateUtils.parseDate(str, DATE_PATTERNS).getTime() : null;
	}

	public static Long getDateTypeChina(String str) throws ParseException {
		return str != null ? org.apache.commons.lang.time.DateUtils.parseDate(str, DATE_WITHOUT_TIME_TYPE_CHINA).getTime() : null;
	}

	public static long countDays(long from, long to) {
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(from);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		from = cal.getTimeInMillis();

		cal.setTimeInMillis(to);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		to = cal.getTimeInMillis();

		return ((to - from) / A_DAY) + 1;
	}

	public static int countYears(long from, long to) {
		int years = (int) (countDays(from, to) / A_YEAR);
		return years;
	}

	public static int getDayOfMonth(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDay(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getYear(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int returnHour(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int returnMinute(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int returnSecond(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * @author ntbang
	 * @param date
	 * @return
	 */
	public static int[] returnMinuteAndHour(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return new int[] { calendar.get(Calendar.MINUTE), calendar.get(Calendar.HOUR_OF_DAY) };
	}

	public static long getExactlyTime(String time) throws ParseException {
		return time != null ? org.apache.commons.lang.time.DateUtils.parseDate(time, FULLTIME).getTime() : null;
	}

	public static boolean isCorrectDate(Object date) {
		boolean test = true;
		try {
			DateFormat formatWithoutTime = new SimpleDateFormat(DATEWITHOUTTIME);
			formatWithoutTime.setLenient(false);
			formatWithoutTime.parse(date.toString());
		} catch (Exception e) {
			try {
				DateFormat formatWithTime = new SimpleDateFormat(DATEWITHTIME);
				formatWithTime.setLenient(false);
				formatWithTime.parse(date.toString());
			} catch (Exception ex) {
				test = false;
			}
		}
		return test;
	}

	public static void main(String[] args) throws ParseException {
		try {

			System.out.println(dateWithoutTime(new Date()));
			System.out.println(getHour(new Date()));

			// long startTime = getDate("31-12-2010 08:00");
			// long endTime = getDate("03-01-2011 08:00");

			/*
			 * startTime = startTime + 86400000;
			 * 
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTimeInMillis(startTime);
			 * 
			 * 
			 * System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			 * System.out.println(cal.get(Calendar.MONTH) + 1);
			 * System.out.println(cal.get(Calendar.YEAR));
			 */

			/*
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTimeInMillis(startTime); cal.set(Calendar.DAY_OF_MONTH,
			 * 1);
			 */

			// System.out.println(totalTimeOfDay(startTime, endTime));

			/*
			 * System.out.println(DateUtils.getDateFromString("1-1-2010 23:59:59"
			 * , DateUtils.FULLTIME_TO_VIEW).getTime());
			 * System.out.println(DateUtils.getDateFromString("1-01-2010",
			 * DateUtils.DD_MM_YYYY).getTime());
			 * System.out.println(DateUtils.formmatStringDate(new
			 * Date(1268240400000l),DateUtils.FULLTIME_TO_VIEW));
			 * System.out.println(DateUtils.formmatStringDate(new
			 * Date(1262019600000l),DateUtils.FULLTIME_TO_VIEW));
			 * System.out.println(DateUtils.formmatStringDate(new
			 * Date(1260575832704l),DateUtils.DD_MM_YYYY));
			 * System.out.println(new Date(1258650000000l));
			 */

			// System.out.println(getDate("15-10-2010 12:30:00"));

			// System.out.println(getEndOfTheDay(86400000));

			// System.out.println(Integer.parseInt("00:00".replace(":", "")));

			/*
			 * Calendar cal = Calendar.getInstance(); cal.setTimeInMillis(new
			 * Date().getTime());
			 * System.out.println(cal.get(Calendar.HOUR_OF_DAY));
			 * System.out.println(cal.get(Calendar.MINUTE));
			 * System.out.println(cal.get(Calendar.DAY_OF_MONTH));
			 * System.out.println(cal.get(Calendar.YEAR));
			 * System.out.println(cal.get(Calendar.MONTH));
			 */

			// Calendar cal = Calendar.getInstance();
			// cal.setTimeInMillis(getDate("08-06-2010 10:00"));
			// cal.set(Calendar.HOUR_OF_DAY, 17);
			// cal.set(Calendar.MINUTE, 30);
			// System.out.println(getDate("08-06-2010 10:00"));
			// System.out.println(cal.getTimeInMillis());

			/*
			 * int day = cal.get(Calendar.DATE); int month =
			 * cal.get(Calendar.MONTH) + 1; int year = cal.get(Calendar.YEAR);
			 * int dow = cal.get(Calendar.DAY_OF_WEEK); int dom =
			 * cal.get(Calendar.DAY_OF_MONTH); int doy =
			 * cal.get(Calendar.DAY_OF_YEAR);
			 */
			// int hour = cal.get(Calendar.HOUR_OF_DAY);
			// int minute = cal.get(Calendar.MINUTE);
			// int second = cal.get(Calendar.SECOND);

			/*
			 * System.out.println("Current Date: " + cal.getTime());
			 * System.out.println("Day: " + day); System.out.println("Month: " +
			 * month); System.out.println("Year: " + year);
			 * System.out.println("Day of Week: " + dow);
			 * System.out.println("Day of Month: " + dom);
			 * System.out.println("Day of Year: " + doy);
			 */
			// System.out.println("hour: " + hour);
			// System.out.println("minute: " + minute);
			// System.out.println("second: " + second);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author ntbang hourMinuteToString
	 * @param time
	 * @return
	 */
	public static String hourMinuteToString(long time) {
		time = time / 60000;
		int minutes = (int) (time % 60);
		int hours = (int) (time / 60);
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + ":" + minutesStr);
	}

	/***
	 * @author ntbang hourMinuteToLong
	 * @param time
	 * @return
	 */
	public static long hourMinuteToLong(String str) {
		long time = (Long.parseLong(str.split(":")[0]) * 60 + Long.parseLong(str.split(":")[1])) * 60000;
		return time;
	}

	public static MaskFormatter getStyleOfDate() throws ParseException {
		MaskFormatter format = new MaskFormatter(DATE_FORMAT);
		format.setPlaceholderCharacter(SEPARATE_CHAR);
		return format;
	}

	public static MaskFormatter getStyleOfDate_hh_mm_ss() throws ParseException {
		MaskFormatter format = new MaskFormatter(DATE_FORMAT_HH_MM);
		format.setPlaceholderCharacter(SEPARATE_CHAR);
		return format;
	}

	public static MaskFormatter getStyleOfHour() throws ParseException {
		MaskFormatter format = new MaskFormatter(HOUR_FORMAT);
		format.setPlaceholderCharacter(SEPARATE_CHAR);
		return format;
	}

	public static MaskFormatter getStyle_DD_MM() throws ParseException {
		MaskFormatter format = new MaskFormatter(DATEFORMAT_DD_MM);
		format.setPlaceholderCharacter(SEPARATECHAR);
		return format;
	}

	public static Date getDateFromString(String date, String amask) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(amask);
			return date != null ? format.parse(date) : null;
		} catch (Exception e) {
			return null;
		}
	}

	public static String formmatStringDate(Date date, String amask) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(amask);
			return date != null ? format.format(date) : null;
		} catch (Exception e) {
			return null;
		}
	}

	public static String formmatStringDate(String date, String amaskInupt, String amaskOutput) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(amaskInupt);
			Date d = date != null ? format.parse(date) : null;

			format = new SimpleDateFormat(amaskOutput);
			return d != null ? format.format(d) : null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * isValidDate Jul 8, 2009
	 * 
	 * @author ntbang
	 * @param inDate
	 * @return
	 */
	public static boolean isValidDate(String inDate) {
		if (inDate == null) {
			return false;
		}
		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}
		dateFormat.setLenient(false);
		try {
			// parse the inDate parameter
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * @author ntbang
	 * @param inDate
	 * @return
	 */
	public static boolean isValidDate_hh_mm(String inDate) {
		if (inDate == null) {
			return false;
		}
		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}
		dateFormat.setLenient(false);
		try {
			// parse the inDate parameter
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static Date startDate(String strDate, String amask) {
		Date d = getDateFromString(formmatStringDate(getDateFromString(strDate, amask), DD_MM_YYYY) + " 00:00:00", DD_MM_YYYY_HH_MM_SS);
		return d;
	}

	public static Date endDate(String strDate, String amask) {
		Date d = getDateFromString(formmatStringDate(getDateFromString(strDate, amask), DD_MM_YYYY) + " 23:59:59", DD_MM_YYYY_HH_MM_SS);
		return d;
	}

	public static String checkFromDateAndToDate(JTextField fromDate, JTextField toDate) throws Exception {
		Long fromDateLong, toDateLong;
		if ("__-__-____".equals(fromDate.getText().trim())) {
			return "Vui lòng chọn từ ngày!";
		} else if (!DateUtils.isValidDate(fromDate.getText().trim())) {
			return "Vui lòng nhập đúng ngày bắt đầu!";
		} else {
			fromDateLong = DateUtils.getDateReturnLong(fromDate.getText());
		}
		if ("__-__-____".equals(toDate.getText().trim())) {
			return "Vui lòng chọn đến ngày!";
		} else if (!DateUtils.isValidDate(toDate.getText().trim())) {
			return "Vui lòng nhập đúng ngày kết thúc!";
		} else {
			toDateLong = DateUtils.getDateReturnLong(toDate.getText());
		}

		if (fromDateLong > toDateLong) {
			return "Từ ngày phải nhỏ hơn đến ngày";
		}
		return null;
	}

	public static String getTimeFromDate(Date date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss a");
		return date != null ? format.format(date) : "";
	}

	public static Date convertToDateTime(String strDate) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
			return strDate != null ? format.parse(strDate) : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	public static Date dateWithoutTime(Date date){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
	
	public static int hourFromDate(Date date){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
	}
}
