package com.myweb.database.cafe;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class OrganizationCafeDB extends DatabaseConnector{
//	pk number primary key,
//	fk_organization number,
//	fk_cafe number,
//	cafe_type number

	public int delete(int cafeKey) {
		setSql("delete organization_cafe where fk_cafe=?");
		setInt(1, cafeKey);
		return deleteData();
	}
	
	public int insert(int organizationKey, int cafeKey, int type) {
		setSql("insert into organization_cafe values(seq_organization_cafe.nextval, ?, ?,?)");
		setInt(1, organizationKey);
		setInt(2, cafeKey);
		setInt(3, type);
		return insertData();
	}
	
	public JSONObject getObject(int cafeKey){
		setSql("select * from organization_cafe where fk_cafe=?");
		setInt(1, cafeKey);
		return getJSONObject();
	}
	
	public JSONArray getArray(int orgKey) {
		setSql("select * from organization_cafe where fk_organization =?");
		setInt(1, orgKey);
		return getJSONArray();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		try {
			JSONObject json = new JSONObject();
			json.put("pk", rs.getInt("pk"));
			json.put("fk_organization", rs.getInt("fk_organization"));
			json.put("fk_cafe", rs.getInt("fk_cafe"));
			json.put("cafe_type", rs.getInt("cafe_type"));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
