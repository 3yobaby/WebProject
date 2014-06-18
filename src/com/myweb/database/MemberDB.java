package com.myweb.database;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class MemberDB extends DatabaseConnector{
//	  pk number primary key,
//	  id varchar2(20) not null,
//	  pass varchar2(20) not null,
//	  name varchar2(40) not null,
//	  email varchar2(40),
//	  tel varchar2(20),
//	  joined date,
//	  is_valid varchar2(5)
	
	public JSONArray getAllMemberArray() {
		setSql("select * from member");
		return getJSONArray();
	}
	
	public JSONObject getMember(String id) {
		setSql("select * from member where id=?");
		setString(1, id);
		return getJSONObject();
	}
	
	public JSONObject getLMember(String id, String pass) {
		setSql("select * from member where id=? and pass=?");
		setString(1, id);
		setString(2, pass);
		return getJSONObject();
	}
	
	public int insert(String id, String pass, String name, String email, String tel){
		setSql("insert into member values(seq_member.nextval, ?,?,?,?,?, sysdate, 'true')");
		setString(1, id);
		setString(2, pass);
		setString(3, name);
		setString(4, email);
		setString(5, tel);
		return insertData();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		JSONObject json = new JSONObject();
		try{
			json.put("pk", rs.getInt("pk"));
			json.put("id", rs.getString("id"));
			json.put("pass", rs.getString("pass"));
			json.put("name", rs.getString("name"));
			json.put("email", rs.getString("email"));
			json.put("tel", rs.getString("tel"));
			json.put("joined", rs.getDate("joined").toString());
			json.put("is_valid", rs.getString("is_valid"));
			return json;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
