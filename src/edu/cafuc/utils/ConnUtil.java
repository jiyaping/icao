package edu.cafuc.utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnUtil {
	public static Connection getConnection(){
		Connection conn = null;
		String url="jdbc:microsoft:sqlserver://182.132.31.224:1433;DatabaseName=db_icao";
		String username = "sa";
		String password = "123321";
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String args[]){
		Connection c = ConnUtil.getConnection();
		if(c!=null){
			System.out.println("success");
		}
	}
}

