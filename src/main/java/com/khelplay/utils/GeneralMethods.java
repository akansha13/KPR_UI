package com.khelplay.utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;

public class GeneralMethods {

	public static boolean validateStatusCodes(int statusCode) {
		boolean flag = false;
		if (statusCode == 200) {
			System.out.println("Request succeeded with Status Code " + statusCode);
			flag = true;
		} else {
			System.out.println("Request failed with Status Code " + statusCode);
			flag = false;
		}
		return flag;
	}

	public static boolean validateErrorCodes(String errorCode) {
		boolean flag = false;
		if (errorCode.equalsIgnoreCase("0")) {

			System.out.println("verification with DB Starts");
			flag = true;
		} else {
			System.out.println("No need to Verify with DB");
			flag = false;
		}
		return flag;
	}

	// public static String generateVerificationCode(String query, String
	// emailId) throws SQLException{
	// String dbDataVerificationCode="";
	// String dbDataPlayerId="";
	// String playerIdandCode="";
	// Connection con;
	// DBConnection connection=DBConnection.getInstance();
	// con = connection.getDBConnection();
	// ResultSet rs = connection.ExecuteQuery(con, query, emailId);
	// while(rs.next())
	// {
	// dbDataVerificationCode= rs.getString(1);
	// dbDataPlayerId= rs.getString(2);
	// playerIdandCode = dbDataPlayerId + "_" + dbDataVerificationCode;
	// }
	// return playerIdandCode;
	// }

	// Only use this Method when you have two maps consisting only key value
	// pair
	public static boolean validationWithDb(HashMap<String, String> apiDataMap, HashMap<String, String> dbDataMap)
			throws ParseException {
		boolean flag = false;
		for (String key : dbDataMap.keySet()) {
			if (apiDataMap.keySet().contains(key)) {
				String apiData = String.valueOf(apiDataMap.get(key));
				String dbData = String.valueOf(dbDataMap.get(key));
				if ((dbData.equalsIgnoreCase("null") && apiData.equalsIgnoreCase("0"))
						|| (dbData.equalsIgnoreCase("null") && apiData.equalsIgnoreCase("null"))
						|| (apiData.equalsIgnoreCase("0") && dbData.equalsIgnoreCase("0"))
						|| (apiData.equalsIgnoreCase("null") && dbData.equalsIgnoreCase("0"))
						|| (apiData.toString().length() == 0 && dbData.equalsIgnoreCase("null"))
						|| (apiData.toString().length() == 0 && dbData.equals("NONE"))
						|| (apiData.equalsIgnoreCase("true") && dbData.equalsIgnoreCase("Y"))
						|| (apiData.equalsIgnoreCase("false") && dbData.equalsIgnoreCase("n"))
						|| (apiData.toString().length() == 0 && dbData.toString().length() == 0)
						|| (apiData.equalsIgnoreCase("0") && dbData.toString().length() == 0)) {

					System.out.println(key + " matched");
					flag = true;
				} else if (dbData instanceof String) {

					if (dbData.replaceAll("\\.0*$|(?<=\\.[0-9]{0,2147483646})0*$", "").trim()
							.equalsIgnoreCase(apiData.replaceAll("\\.0*$|(?<=\\.[0-9]{0,2147483646})0*$", "").trim())) {
						flag = true;
						System.out.println(key + " matched");
					} else {
						flag = false;
						System.out.println(key + "Not matched");
						System.out.println("Apidata:- " + apiData);
						System.out.println("Dbdata:- " + dbData);

						break;
					}

				}
				/*
				 * else if(dbData instanceof String){
				 * if((apiData.toString().length()==0 && dbData==null) ||
				 * (apiData==null && dbData==null)){
				 * System.out.println(key+" matched"); flag=true;} else
				 * if((apiData.toString().trim()).equalsIgnoreCase(dbData.
				 * toString().trim())){ System.out.println(key + " matched");
				 * flag=true; } else{ flag=false; System.out.println(key +
				 * " didn't match"); break; } } else if(dbData instanceof Double
				 * || dbData instanceof Integer|| dbData instanceof Long ||
				 * dbData instanceof BigDecimal || dbData instanceof BigInteger
				 * ) { if((dbData==null && apiData.equals(0)) || (dbData==null
				 * && apiData==null)) { System.out.println(key + " matched");
				 * flag=true; } else if(dbData!=null && apiData!=null){ Double
				 * dbValue=Double.parseDouble(String.valueOf(dbData)); Double
				 * apiValue=Double.parseDouble(String.valueOf(apiData));
				 * if(dbValue.equals(apiValue)){ System.out.println(key+
				 * " matched"); flag=true; } } else{ flag=false;
				 * System.out.println(key+ " didn't match"); break; } } else
				 * if(dbData instanceof Date || dbData instanceof Timestamp) {
				 * if(dbData==null && apiData==null){ System.out.println(key +
				 * " matched"); flag=true; } else if(dbData!=null && apiData
				 * !=null) { String format="yyyy-MM-dd HH:mm:ss";
				 * SimpleDateFormat formatter = new SimpleDateFormat(format);
				 * dbData=formatter.format(dbData); Date apiDateFormat=(Date)
				 * formatter.parse(String.valueOf(apiData)); String
				 * apidate=formatter.format(apiDateFormat);
				 * if(apidate.equals(dbData)){ System.out.println(key +
				 * " matched"); flag=true; } else{ System.out.println(key
				 * +" didn't match"); flag=false; break; } } }
				 */
				else {
					System.out.println(key + " didn't matched");
					System.out.println("Apidata:- " + apiData);
					System.out.println("Dbdata:- " + dbData);
					flag = false;
					break;
				}
			} else
				continue;
		}
		return flag;
	}

	// Resultset to map conversion when there is only one row in the query
	/*
	 * public static HashMap<String, String> resultSetToMap(ResultSet rs) throws
	 * SQLException { ResultSetMetaData metaData = rs.getMetaData();
	 * HashMap<String, String> tempMap = new HashMap<String, String>(); while
	 * (rs.next()) { for (int i = 1; i <= metaData.getColumnCount(); i++) {
	 * String key = metaData.getColumnLabel(i); String value =
	 * rs.getString(key); tempMap.put(key, value); } } return tempMap; }
	 */

	// json String to map conversion
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> jsonToMap(String resbody) throws JSONException {
		JSONObject jObject = new JSONObject(resbody.trim());
		HashMap<String, String> retMap = new HashMap<String, String>();
		HashMap<String, String> tempMap = new HashMap<String, String>();
		if (jObject != JSONObject.NULL) {
			Iterator<String> keystr = jObject.keys();
			retMap = toMap(jObject, keystr, tempMap);
		}
		return retMap;
	}

	// json Object to map conversion
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> jsonToMap(JSONObject jObject) throws JSONException {
		HashMap<String, String> retMap = new HashMap<String, String>();
		HashMap<String, String> tempMap = new HashMap<String, String>();
		if (jObject != JSONObject.NULL) {
			Iterator<String> keystr = jObject.keys();
			retMap = toMap(jObject, keystr, tempMap);
		}
		return retMap;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> toMap(JSONObject object, Iterator<String> keysItr,
			HashMap<String, String> tempMap) throws JSONException {
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);
			if (value instanceof JSONObject) {
				Iterator<String> key1 = ((JSONObject) value).keys();
				value = toMap((JSONObject) value, key1, tempMap);
			} else
				tempMap.put(key, value.toString());
		}
		return tempMap;
	}

	// Resultset to List of maps conversion if you have multiple rows in the
	// query
	/*
	 * public static List<HashMap<String, String>> resultSetToList(ResultSet rs)
	 * throws SQLException { ResultSetMetaData metaData = rs.getMetaData();
	 * List<HashMap<String, String>> newList = new ArrayList<HashMap<String,
	 * String>>(); while (rs.next()) { HashMap<String, String> tempMap = new
	 * HashMap<String, String>(); for (int i = 1; i <=
	 * metaData.getColumnCount(); i++) { String key =
	 * metaData.getColumnLabel(i); String value = rs.getString(key);
	 * tempMap.put(key, value); } newList.add(tempMap); } return newList; }
	 */

	public static JSONObject xmlToJson(String xml) throws JSONException {
		String soapmessageString = xml;
		JSONObject soapDatainJsonObject = XML.toJSONObject(soapmessageString);
		return soapDatainJsonObject;

	}

	public static String concateString(Object... data) {
		StringBuilder sb = new StringBuilder();
		for (Object value : data) {
			sb.append(value);
		}
		return sb.toString();
	}

	public static String rendomAlphabetString(int length) {
		Random random = new Random();
		StringBuilder tmp = new StringBuilder();
		char[] buf = null;
		for (char ch = 'A'; ch <= 'Z'; ++ch)
			tmp.append(ch);
		char[] symbols = tmp.toString().toCharArray();
		if (length >= 1) {
			buf = new char[length];
			for (int idx = 0; idx < buf.length; ++idx)
				buf[idx] = symbols[random.nextInt(symbols.length)];
		} else {
			Assert.fail("length of rendom string is 0 ");
		}
		return new String(buf);
	}

	public static String rendomNumberString(int length) {
		Random random = new Random();
		char[] buf = null;
		StringBuilder tmp = new StringBuilder();
		for (char ch = '1'; ch <= '9'; ++ch)
			tmp.append(ch);
		char[] symbols = tmp.toString().toCharArray();
		if (length >= 1) {
			buf = new char[length];
			for (int idx = 0; idx < buf.length; ++idx)
				buf[idx] = symbols[random.nextInt(symbols.length)];

		} else {
			Assert.fail("length of rendom string is 0 ");
		}
		return new String(buf);
	}

}
