package com.khelplay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateAndTimeFormatter {
	public static String getDateAndTimeInExpectedFormat(Object dateInString) {
		String temp;
		temp = String.valueOf(dateInString);

		if (!temp.equalsIgnoreCase("Null")) {
			String format = "yyyy-MM-dd HH:mm:ss.S";
			String returnformat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = formatter.parse(temp);
				formatter = new SimpleDateFormat(returnformat);
				return formatter.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return temp;
		}
	}

	public static String getCurrentDateAndTimeInExpectedFormat(Object dateInString, String Returnformat) {
		String temp = String.valueOf(dateInString);

		if ((!temp.equalsIgnoreCase("Null") || !temp.isEmpty()) && temp.contains("current")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Returnformat);
			LocalDateTime localDateTime = LocalDateTime.now();
			return dtf.format(localDateTime);
		} else {
			return temp;
		}
	}

	public static String getDateInExpectedFormat(Object dateInString, String Format,
			String Returnformat) {/*
									 * year-yyyy, month-MM, day-dd
									 */
		String temp;
		temp = String.valueOf(dateInString);

		if (!temp.equalsIgnoreCase("Null") || !temp.isEmpty()) {
			String format = Format;
			String returnformat = Returnformat;
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = formatter.parse(temp);
				formatter = new SimpleDateFormat(returnformat);
				return formatter.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return temp;
		}
	}

	public static String getCurrentDateInExpectedFormat(Object dateInString,
			String Returnformat) {/*
									 * year-yyyy, month-MM, day-dd
									 */
		String temp;
		temp = String.valueOf(dateInString);

		if ((!temp.equalsIgnoreCase("Null") || !temp.isEmpty()) && temp.contains("current")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Returnformat);
			LocalDate localDate = LocalDate.now();
			return dtf.format(localDate);
		} else {
			return temp;
		}
	}

	public static String N_MonthsBeforeDateAndTimeFromCurrentInExpectedFormat(int Months, String Returnformat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -Months);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(Returnformat);
		String dateOutput = format.format(date);
		return dateOutput;
	}

	public static String N_MonthsAfterDateAndTimeFromCurrentInExpectedFormat(int Months, String Returnformat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -Months);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(Returnformat);
		String dateOutput = format.format(date);
		return dateOutput;
	}

	public static String currentDateAndTimeToMilliseconds(String Currdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateInString = Currdate;
		Date date = sdf.parse(dateInString);

		System.out.println(dateInString);
		String date1 = String.valueOf(date.getTime());
		return date1;
	}

	public static HashMap<String, String> previousYear_Month_Week_Day_Code(String dateType) {
		HashMap<String, String> dateinfo = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar aCalendar = Calendar.getInstance();
		if (dateType.equalsIgnoreCase("daily")) {
			aCalendar.set(Calendar.HOUR_OF_DAY, 0);
			aCalendar.set(Calendar.MINUTE, 0);
			aCalendar.set(Calendar.SECOND, 0);
			aCalendar.set(Calendar.MILLISECOND, 0);
			aCalendar.add(Calendar.DAY_OF_WEEK, -2);
			dateinfo.put("Start", format.format(aCalendar.getTime()) + " 00:00:00");
			aCalendar.add(Calendar.DAY_OF_WEEK, 1);
			dateinfo.put("End", format.format(aCalendar.getTime()) + " 00:00:00");
		} else if (dateType.equalsIgnoreCase("weekly")) {
			aCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			aCalendar.add(Calendar.DAY_OF_WEEK, -7);
			dateinfo.put("Start", format.format(aCalendar.getTime()) + " 00:00:00");
			aCalendar.add(Calendar.DAY_OF_WEEK, 6);
			dateinfo.put("End", format.format(aCalendar.getTime()) + " 23:59:59");

		} else if (dateType.equalsIgnoreCase("monthly")) {
			aCalendar.setTime(new Date());
			aCalendar.add(Calendar.DAY_OF_MONTH, -1);
			aCalendar.set(Calendar.DATE, 0);
			dateinfo.put("End", format.format(aCalendar.getTime()) + " 23:59:59");
			aCalendar.set(Calendar.DATE, 1);
			dateinfo.put("Start", format.format(aCalendar.getTime()) + " 00:00:00");

		} else if (dateType.equalsIgnoreCase("yearly")) {
			int year = aCalendar.get(Calendar.YEAR) - 1;
			dateinfo.put("Start", year + "-01-01 00:00:00");
			dateinfo.put("End", year + "-12-31 23:59:59");
		} else if (dateType.equalsIgnoreCase("all")) {
			dateinfo.put("Start", "");
			dateinfo.put("End", "");
		}

		return dateinfo;

	}

}
