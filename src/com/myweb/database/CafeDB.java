package com.myweb.database;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class CafeDB extends DatabaseConnector{
//	  pk number primary key,
//	  title varchar2(40) not null,
//	  uri varchar2(20) not null,
//	  manager_id varchar2(20),
//	  detail varchar2(400),
//	  created date,
//	  is_valid varchar2(5)
	
	public JSONArray getAllCafeArray() {
		setSql("select * from cafe");
		return getJSONArray();
	}
	
	
	public int insert(String title, String uri, String managerId, String detail){
		setSql("insert into cafe values(seq_cafe.nextval, ?,?,?,?, sysdate, 'true')");
		setString(1, title);
		setString(2, uri);
		setString(3, managerId);
		setString(4, detail);
		return insertData();
	}
	
	public JSONArray getNewCafeInfoList(int size) {
		setSql("select * from cafe order by created desc");
		JSONArray array = getJSONArray();
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		JSONObject json = new JSONObject();
		try{
			json.put("pk", rs.getInt("pk"));
			json.put("title", rs.getString("title"));
			json.put("uri", rs.getString("uri"));
			json.put("manager_id", rs.getString("manager_id"));
			json.put("detail", rs.getString("detail"));
			json.put("created", rs.getDate("created").toString());
			json.put("is_valid", rs.getString("is_valid"));
			return json;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
}
