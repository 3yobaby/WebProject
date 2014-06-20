package com.util.kht;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class DatabaseConnector {
	private ConnectionGetter cg;
	protected String sql;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	public DatabaseConnector() {
		cg = new ConnectionGetter();
	}
	
	public void setConnection(Connection conn){
		cg.setConnection(conn);
	}
	
	public Connection getConnection(){
		return cg.getConnection();
	}
	
	public void close() {
		if(rs != null){try{rs.close();}catch(Exception e){}}
		if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
		cg.close();
	}
	
	protected void setString(int index, String name){
		try {
			pstmt.setString(index, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void setInt(int index, int value){
		try {
			pstmt.setInt(index, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void setSql(String sql){
		this.sql = sql;
		pstmt = cg.getPreparedStatement(sql);
	}
	
	protected JSONObject getJSONObject(){
		try {
			rs = pstmt.executeQuery();
			JSONObject json = null;
			if(rs.next()){
				json = getJSONObjectFromResultset(rs); 
			}
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	@SuppressWarnings("unchecked")
	protected JSONArray getJSONArray() {
		try {
			rs = pstmt.executeQuery();
			JSONArray array = new JSONArray();
			while(rs.next()){
				JSONObject json = getJSONObjectFromResultset(rs);
				array.add(json);
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected HashMap<String, JSONObject> getHashMap() {
		try {
			HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
			rs = pstmt.executeQuery();
			while(rs.next()){
				JSONObject json = getJSONObjectFromResultset(rs);
				map.put((String) json.get("id"), json);
				System.out.println(json);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * set
	 */
	
	protected int insertData(){
		try {
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	protected int updateData(){
		try {
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	protected int deleteData(){
		try {
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	protected abstract JSONObject getJSONObjectFromResultset(ResultSet rs);
}
