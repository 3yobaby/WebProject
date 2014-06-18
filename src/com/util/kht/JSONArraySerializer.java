package com.util.kht;

import org.json.simple.JSONArray;

public class JSONArraySerializer {
	@SuppressWarnings("unchecked")
	public static JSONArray serialize(JSONArray array){
		if(array == null)
			return null;
		JSONArray result = new JSONArray();
		for (int i = 0; i < array.size(); i++) {
			JSONArray temp = (JSONArray) array.get(i);
			for(int j=0 ; j<temp.size(); j++){
				result.add(temp.get(j));
			}
		}
		return result;
	}
}
