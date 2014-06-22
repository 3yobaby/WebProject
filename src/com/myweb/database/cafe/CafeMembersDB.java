package com.myweb.database.cafe;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class CafeMembersDB extends DatabaseConnector{
//	  pk number primary key,
//	  fk_cafe number,
//	  fk_member number,
//	  member_type number

	public int update(int cafeKey, int memberKey, int type) {
		setSql("update cafe_members set member_type=? where fk_cafe=? and fk_member =?");
		setInt(1, type);
		setInt(2, cafeKey);
		setInt(3, memberKey);
		return updateData();
	}
	
	public int delete(int cafeKey, int memberKey) {
		setSql("delete from cafe_members where fk_cafe=? and fk_member=?");
		setInt(1, cafeKey);
		setInt(2, memberKey);
		return deleteData();
	}
	
	public JSONArray getCafeArrayByCafe(int cafeKey){
		setSql("select * from cafe_members where fk_cafe=?");
		setInt(1, cafeKey);
		return getJSONArray();
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getCafeArray(int memberKey){
		setSql("select * from cafe_members where fk_member = ?");
		setInt(1, memberKey);
		JSONArray array = getJSONArray();
		JSONArray result = new JSONArray();
		CafeDB db = new CafeDB();
		for(int i=0; i<array.size(); i++){
			JSONObject temp = (JSONObject)array.get(i);
			JSONObject cafe = db.getCafe((int)temp.get("fk_cafe"));
			result.add(cafe);
		}
		db.close();
		return result;
	}
	
	public int getType(int cafeKey, int memberKey){
		setSql("select * from cafe_members where fk_cafe=? and fk_member=?");
		setInt(1, cafeKey);
		setInt(2, memberKey);
		JSONObject json = getJSONObject();
		if(json == null)
			return -1;
		return (Integer)json.get("member_type");
	}
	
	public int insert(int cafepk, int memberpk, int member_type) {
		setSql("insert into cafe_members values(seq_cafe_members.nextval, ?, ?, ?)");
		setInt(1, cafepk);
		setInt(2, memberpk);
		setInt(3, member_type);
		return insertData();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		JSONObject json = new JSONObject();
		try{
			json.put("pk", rs.getInt("pk"));
			json.put("fk_cafe", rs.getInt("fk_cafe"));
			json.put("fk_member", rs.getInt("fk_member"));
			json.put("member_type", rs.getInt("member_type"));
			return json;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
