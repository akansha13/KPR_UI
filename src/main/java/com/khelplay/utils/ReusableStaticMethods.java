package com.khelplay.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.common.DBConnection;

public class ReusableStaticMethods {
	private static Logger logger = LoggerFactory.getLogger(ReusableStaticMethods.class);

	/*
	 * numcount is total numbers which will select numcount should not be
	 * greater than upper bound
	 */
	public static List<Integer> randomNumber(int lowerbound, int upperbound, int numcount) {
		List<Integer> random = new ArrayList<Integer>();
		final int[] ints = new Random().ints(lowerbound, upperbound + 1).distinct().limit(numcount).toArray();
		for (int i = 0; i < ints.length; i++) {
			System.out.println(ints[i]);
			random.add(ints[i]);
		}
		System.out.println(random);
		return random;
	}

	public static List<Integer> randomNumberDuplicate(int lowerbound, int upperbound, int numcount) {
		List<Integer> random = new ArrayList<Integer>();
		final int[] ints = new Random().ints(lowerbound, upperbound + 1).limit(numcount).toArray();
		for (int i = 0; i < ints.length; i++) {
			System.out.println(ints[i]);
			random.add(ints[i]);
		}
		System.out.println(random);
		return random;
	}

	public static List<String> convertIntegerListToString(List<Integer> list) {
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < 10) {
				temp.add("0" + list.get(i));
			} else {
				temp.add(String.valueOf(list.get(i)));
			}

		}
		return temp;

	}

	public static String IntegerListToString(List<Integer> list, String string) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + list.get(i);
		}
		return string + str;

	}

	public static String StringListToString(List<String> list) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + list.get(i) + ",";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	public static String covertStringToTwoDecimalNum(String input) {
		float ftnum = Float.parseFloat(input);
		return String.format("%.2f", ftnum);

	}

	public static String removeZeroFromticket(String val) {
		if (val.length() != 0) {
			val = val.substring(0, val.length() - 1);
			return val;

		} else {
			return null;
		}
	}

	public static String getLastDigitFromticket(String val) {
		if (val.length() != 0) {
			val = val.substring(val.length() - 1, val.length());
			return val;

		} else {
			return null;
		}
	}

	public static String convertsToDateFormate(String Dateinput) {
		java.util.Date date = new Date(Dateinput);
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = formate.format(date);
		System.out.println(format);
		return format;
	}

	public static String convertsToDateFormate2(String Dateinput) {
		java.util.Date date = new Date(Dateinput);
		SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String format = formate.format(date);
		System.out.println(format);
		return format;
	}

	public static String convertDate(String DateInput) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = dateFormat.parse(DateInput);
			return date.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static long fact(int num) {
		long fact = 1;
		for (int i = 1; i <= num; i++) {
			fact = fact * i;
		}
		System.out.println(fact);
		return fact;
	}

	public static int calculateNoOfline(int n, int r) {
		long result = fact(n) / (fact(n - r) * fact(r));
		return (int) result;
	}

	public static String formatDate(String strDate) throws ParseException {
		SimpleDateFormat strDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date dt = strDt.parse(strDate);
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = sm.format(dt);
		return date;

	}

	public static double fmtToTwoDecimal(double number) {
		return Math.round((number * 100)) / 100.0;
	}

	public static String convertDateTime(String DateInput) {
		String ds1 = DateInput;
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String ds2 = sdf2.format(sdf1.parse(ds1));
			return ds2;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;

	}

	public static String getCurrentDateFromDB(Connection connection) throws SQLException {
		ResultSet rs = DBConnection.executeQuery(connection, "select DATE_FORMAT(NOW(),'%Y-%m-%d %T')");
		rs.first();
		return rs.getString(1);
	}

	public static List<String> convertIntegerListToStringList(List<Integer> list) {
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(String.valueOf(list.get(i)));
		}
		return temp;
	}

}
