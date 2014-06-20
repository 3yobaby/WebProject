package com.util.kht;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ConnectionGetter {
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	protected Connection conn;
	private String id = "scott";
	private String pass = "tiger";
	public ConnectionGetter() {
		conn = getConnection();
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					url, id, pass);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		if(conn != null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
