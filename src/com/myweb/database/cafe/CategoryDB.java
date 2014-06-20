package com.myweb.database.cafe;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class CategoryDB extends DatabaseConnector{
//	  pk number primary key,
//	  fk_cafe number,
//	  title varchar2(30),
//	  is_sub varchar2(5),
//	  ref number
	
	public int insert(int cafeKey, String cTitle, boolean isSub) {
		setSql("insert into category values(seq_category.nextval,?, ?, "
				+ " ?, seq_category.nextval)");
		setInt(1, cafeKey);
		setString(2, cTitle);
		setString(3, String.valueOf(isSub));
		return insertData();
	}
	
	public String getCategoryName(int pk){
		setSql("select * from category where pk=?");
		setInt(1, pk);
		return (String)getJSONObject().get("title");
	}
	
	public JSONArray getCategories(int fk) {
		setSql("select * from category where fk_cafe = ?");
		setInt(1, fk);
		return getJSONArray();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		JSONObject json = new JSONObject();
		try {
			json.put("pk", rs.getInt("pk"));
			json.put("fk_cafe", rs.getInt("fk_cafe"));
			json.put("title", rs.getString("title"));
			json.put("is_sub", rs.getString("is_sub"));
			json.put("ref", rs.getString("ref"));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
