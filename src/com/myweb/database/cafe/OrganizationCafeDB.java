package com.myweb.database.cafe;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class OrganizationCafeDB extends DatabaseConnector{
	//	pk number primary key,
	//	fk_organization number,
	//	fk_cafe number
	
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
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
