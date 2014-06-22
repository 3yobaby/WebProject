package com.myweb.database.cafe;

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
//	  search_words varchar2(200),
//	  join_rule varchar2(20),
//	  is_search varchar2(5),
//	  is_valid varchar2(5),
//	  is_organization varchar2(5)

	public JSONArray getOrganizationByTitle(String word) {
		setSql("select * from cafe where is_organization='true' and title like ?");
		setString(1, "%" +word + "%");
		return getJSONArray();
	}
	
	public JSONArray getAllOrganizationArray(){
		setSql("select * from cafe where is_organization =?");
		setString(1, "true");
		return getJSONArray();
	}
	
	public JSONArray getCafeArrayByTitle(String title){
		setSql("select * from cafe where title like ?");
		setString(1, "%" +title+ "%");
		return getJSONArray();
	}
	
	public JSONArray getCafeArrayByWord(String word){
		setSql("select * from cafe where search_words like ?");
		setString(1, "%"+word+"%");
		JSONArray result = getJSONArray();
		return result;
	}
	
	public JSONObject getCafeByTitle(String cafeTitle) {
		setSql("select * from cafe where title=?");
		setString(1, cafeTitle);
		return getJSONObject();
	}
	
	public JSONArray getNewCafeArray(){
		setSql("select * from cafe order by created desc");
		return getJSONArray();
	}
	
	public JSONObject getCafe(int cafeKey) {
		setSql("select * from cafe where pk = ?");
		setInt(1, cafeKey);
		return getJSONObject();
	}
	
	public JSONObject getCafe(String uri) {
		setSql("select * from cafe where uri = ?");
		setString(1, uri);
		return getJSONObject();
	}
	
	public JSONArray getAllCafeArray() {
		setSql("select * from cafe order by created desc");
		return getJSONArray();
	}
	
	
	public int insert(String title, String uri, String managerId, String detail, 
			String searchWords, String joinRule, boolean isSearch, boolean isOrganization){
		setSql("insert into cafe values(seq_cafe.nextval, ?,?,?,?, sysdate, ?, ?,?,'true', ?)");
		setString(1, title);
		setString(2, uri);
		setString(3, managerId);
		setString(4, detail);
		setString(5, searchWords);
		setString(6, joinRule);
		setString(7, String.valueOf(isSearch));
		setString(8, String.valueOf(isOrganization));
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
			json.put("search_words", rs.getString("search_words"));
			json.put("join_rule", rs.getString("join_rule"));
			json.put("is_search", rs.getString("is_search"));
			json.put("is_valid", rs.getString("is_valid"));
			json.put("is_organization", rs.getString("is_organization"));
			return json;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

}
