package com.myweb.application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.database.cafe.CafeDB;
import com.myweb.database.cafe.CategoryDB;
import com.myweb.database.cafe.OrganizationCafeDB;

public class CafeGetter {
	public JSONArray getCafes(int cafeKey){
		CategoryDB db = new CategoryDB();
		JSONArray array = db.getCategories(cafeKey);
		db.close();
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getOrganization(int cafeKey){
		OrganizationCafeDB ocdb = new OrganizationCafeDB();
		JSONObject temp =  ocdb.getObject(cafeKey);
		JSONObject organization = null;
		if(temp != null){
			int key = (Integer)temp.get("fk_organization");
			CafeDB cdb = new CafeDB();
			organization = cdb.getCafe(key);
			organization.put("cafe_type", temp.get("cafe_type"));
		}
		ocdb.close();
		return organization;
	}
}
