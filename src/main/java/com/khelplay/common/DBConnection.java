package com.khelplay.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.utils.ConfigManager;

public class DBConnection {
	private static Logger logger = LoggerFactory.getLogger(DBConnection.class);
	private static DBConnection dbconnection = null;
	private static Connection conLms = null;
	private static Connection conDge = null;
	private static Connection conIge = null;
	private static Connection conSle = null;
	private static Connection conOla = null;
	private DBConnection(){}
	
	public static DBConnection getInstance() {
		if (dbconnection == null) {
			synchronized (DBConnection.class){
				if(dbconnection==null)
					dbconnection= new DBConnection();
			}
		}
			return dbconnection;		
	}

	public static Connection getDBConnection() {
		try {
			if (conLms == null) {

				String url = ConfigManager.getProperty("DBConURL");
				String user = ConfigManager.getProperty("DBConnUser");
				String password = ConfigManager.getProperty("DBConnPwd");
				// String driver = ConfigManager.getProperty("DBConnDriver");
				Class.forName("com.mysql.jdbc.Driver");
				conLms = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conLms;
	}

	public static Connection getDBConnectionDge() {
		try {
			if (conDge == null) {
				String url = ConfigManager.getProperty("DBConDgeURL");
				String user = ConfigManager.getProperty("DBConnDgeUser");
				String password = ConfigManager.getProperty("DBConnDgePwd");
				String driver = ConfigManager.getProperty("DBConnDgeDriver");
				Class.forName(driver);
				conDge = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conDge;
	}

	public static Connection getDBConnectionIge() {
		try {
			if (conIge == null) {
				String url = ConfigManager.getProperty("DBConIgeURL");
				String user = ConfigManager.getProperty("DBConnIgeUser");
				String password = ConfigManager.getProperty("DBConnIgePwd");
				String driver = ConfigManager.getProperty("DBConnIgeDriver");
				Class.forName(driver);
				conIge = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conIge;
	}

	public static Connection getDBConnectionSle() {
		try {
			if (conSle == null) {
				String url = ConfigManager.getProperty("DBConSleURL");
				String user = ConfigManager.getProperty("DBConnSleUser");
				String password = ConfigManager.getProperty("DBConnSlePwd");
				String driver = ConfigManager.getProperty("DBConnSleDriver");
				Class.forName(driver);
				conSle = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conSle;
	}

	public static Connection getDBConnectionOla() {
		try {
			if (conOla == null) {
				String url = ConfigManager.getProperty("DBConOlaURL");
				String user = ConfigManager.getProperty("DBConnOlaUser");
				String password = ConfigManager.getProperty("DBConnOlaPwd");
				String driver = ConfigManager.getProperty("DBConnOlaDriver");
				Class.forName(driver);
				conOla = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			System.out.println(e);// e.printStackTrace();
		}
		return conOla;
	}

	public static Connection getDBConnectionPms() {
		try {
			if (conDge == null) {
				String url = ConfigManager.getProperty("DBConPmsURL");
				String user = ConfigManager.getProperty("DBConnPmsUser");
				String password = ConfigManager.getProperty("DBConnPmsPwd");
				String driver = ConfigManager.getProperty("DBConnPmsDriver");
				Class.forName(driver);
				conDge = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conDge;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int Executeupdate(Connection connection, String Query, String param1) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		ps.setString(1, param1);
		return ps.executeUpdate();
	}

	public String getFinalQuery(String value) {

		String tempQuery = value;

		tempQuery = tempQuery.contains("DBLMS") ? tempQuery.replace("DBLMS", ConfigManager.getProperty("DBLMS"))
				: tempQuery;

		tempQuery = tempQuery.contains("dbDGE") ? tempQuery.replace("dbDGE", ConfigManager.getProperty("dbDGE"))
				: tempQuery;
		return tempQuery;

	}

	public boolean executeUpdate(Connection connection, String Query, int param1, String param2, String param3,
			String param4, String param5) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		ps.setInt(1, param1);
		ps.setString(2, param2);
		ps.setString(3, param3);
		ps.setString(4, param4);
		ps.setString(5, param5);
		int found = ps.executeUpdate();
		return true;
	}

	public static ResultSet executeQuery(Connection connection,String Query,Object... args) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		int i=1;
		 for(Object arg:args) {
			 logger.info("Arg Class : "+arg.getClass());
			 if(arg.getClass().toString().trim().equals("class java.lang.String"))
			  ps.setString(i++, arg.toString());
			 else if(arg.getClass().toString().trim().equals("class java.lang.Integer"))
				  ps.setInt(i++, (int) arg);
			 else if(arg.getClass().toString().trim().equals("class java.lang.Float"))
				  ps.setFloat(i++, (float) arg);
			 else if(arg.getClass().toString().trim().equals("class java.lang.Double"))
				  ps.setDouble(i++, (double) arg);
		   }
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static void Executeupdate(Connection connection, String Query,String... args)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		int i=1;
		 for(String arg:args) 		 
			  ps.setString(i++, arg);
		ps.executeUpdate();
	}
	
	public static void Executeupdate(Connection connection, String Query,int arg1,String... args)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		ps.setInt(1, arg1);
		int i=2;
		 for(String arg:args) 		 
			  ps.setString(i++, arg);
		ps.executeUpdate();
	}
	public static void Executeupdate(Connection connection, String Query,int... args)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		int i=1;
		 for(int arg:args) 		 
			  ps.setInt(i++, arg);
		ps.executeUpdate();
	}
	public static void Executeupdate(Connection connection, String Query,String arg1,int... args)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query);
		ps.setString(1, arg1);
		int i=2;
		 for(int arg:args) 		 
			  ps.setInt(i++, arg);
		ps.executeUpdate();
	}

	public static ResultSet SpecificExecuteQuery(Connection connection, String Query,String args) throws SQLException
	{
		String s[] = args.split(",");
		if (Query.contains("?")) {
			Query = Query.replace("?", updateQueryQuestionMarkCount(s.length));
		}
		PreparedStatement ps = connection.prepareStatement(Query);
		int i=1;
		 for(String arg:s) 		 
			  ps.setString(i++, arg);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static String updateQueryQuestionMarkCount(int length) {
		String temp = "?";
		for (int i = 0; i < length - 1; i++) {
			temp += "," + "?";
		}
		return temp;
	}
}
