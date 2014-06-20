package com.myweb.application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.database.cafe.CategoryDB;
import com.myweb.database.cafe.OrganizationCafeDB;
import com.util.kht.JSONArraySerializer;

public class CategoryGetter {
	public JSONArray getCategory(int cafeKey){
		CategoryDB db = new CategoryDB();
		return db.getCategories(cafeKey);
	}
	public JSONArray getOrganizationCategory(int cafeKey){
		OrganizationCafeDB db = new OrganizationCafeDB();
		JSONArray array = db.getArray(cafeKey);
		JSONArray temp = new JSONArray();
		for(int i=0; i<array.size(); i++){
			JSONObject json = (JSONObject) array.get(i);
			int key = (int) json.get("fk_cafe");
			temp.add(getCategory(key));
		}
		return JSONArraySerializer.serialize(temp);
	}
}
