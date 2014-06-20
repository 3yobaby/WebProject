package com.myweb.application;

import org.json.simple.JSONArray;

import com.myweb.database.cafe.CategoryDB;

public class CafeGetter {
	//
	public JSONArray getCafes(int cafeKey){
		CategoryDB db = new CategoryDB();
		JSONArray array = db.getCategories(cafeKey);
		db.close();
		return array;
	}
}
