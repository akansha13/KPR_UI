package com.khelplay.DataBaseQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.khelplay.utils.ConfigManager;

public class DBConnection {
	private static DBConnection instance = new DBConnection();
	private DBConnection(){}
	public static DBConnection getInstance(){
		return instance;
	}
	static{		
		try {
			String driver = ConfigManager.getProperty("DBConnDriver");
			Class.forName(driver);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	private Connection getDBConnection(String gameEngine) throws Exception {
		Connection con = null;
		try {
			if(gameEngine.equalsIgnoreCase("rummy"))
		{
			String url = ConfigManager.getProperty("Weaver_rummy_DBConURL");
			String user = ConfigManager.getProperty("Weaver_rummy_DBConnUser");
			String password = ConfigManager.getProperty("Weaver_rummy_DBConnPwd");
			con = DriverManager.getConnection(url, user, password);
		}
			else if(gameEngine.equalsIgnoreCase("otherGame"))
			{
				String url = ConfigManager.getProperty("Weaver_otherGame_DBConURL");
				String user = ConfigManager.getProperty("Weaver_otherGame_DBConnUser");
				String password = ConfigManager.getProperty("Weaver_otherGame_DBConnPwd");
				con = DriverManager.getConnection(url, user, password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return con;
	}

	public List<HashMap<String,String>> ExecuteQuery(String gameEngine,String Query,Object ...parameters) throws Exception {
		ResultSet rs =null;
		Connection connection =null;
		PreparedStatement ps =null;
		List<HashMap<String, String>> data=null;
		try{	
			 connection =getDBConnection(gameEngine);
			  ps = connection.prepareStatement(Query);
			int index=1;
			for (Object param : parameters) {
				ps.setObject(index, param);
				++index;
			}
			rs= ps.executeQuery();
			data=resultSetToList(rs);		
			
		}catch (Exception e) {
			throw e;
		}finally{
			if(!rs.isClosed())
				rs.close();
			if(!ps.isClosed())
				ps.close();
			if(!connection.isClosed())
				connection.close();
		}
		
		
		return data;
	}

	public static List<HashMap<String, String>> resultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		while (rs.next()) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String key = metaData.getColumnLabel(i);
				String value = rs.getString(key);
				tempMap.put(key, value);
			}
			newList.add(tempMap);
		}
		return newList;
	}	
		
	public void ExecuteUpdate(String gameEngine,String Query) 
			 throws Exception {
		Connection connection =null;
		Statement stmt =null;
		try{
				connection =getDBConnection(gameEngine);
			 stmt = connection.createStatement();
			stmt.executeUpdate(Query);
		}catch (Exception e) {
			throw e;
		}finally{			
			if(!stmt.isClosed())
				stmt.close();
			if(!connection.isClosed())
				connection.close();
		}	
		
	}
	

}
