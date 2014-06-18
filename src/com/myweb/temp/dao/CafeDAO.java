package com.oneplace.temp.dao;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oneplace.temp.data.Cafe;
import com.oneplace.temp.data.Member;
import com.oneplace.temp.data.Organization;

@SuppressWarnings("unchecked")
public class CafeDAO extends ConnectionGetter{
	private SampleData data = SampleData.getInstance();
	public CafeDAO() {}

	public JSONArray getMyJoinCafe(JSONObject json) {
		
		return null;
	}
	
	public JSONArray getAllCafe() {
		HashMap<String, Cafe> map = data.getAllCafe();
		JSONArray array = new JSONArray();
		for (Cafe cafe : map.values()) {
			array.add(cafe.toJSONObject());
		}
		return array;
	}
	
	public JSONObject getMember(String id){
		for(Member member : data.getAllMember().values()){
			if(member.getId().equals(id)){
				return member.toJSONObject();
			}
		}
		return null;
	}
	
	public JSONArray getCafeBySearchWord(String word) {
		JSONArray array = new JSONArray();
		HashMap<String, Cafe> map = data.getAllCafe();
		for(Cafe cafe : map.values()){
			String[] temp = cafe.getSearchWords().split("|");
			for(String temp2 : temp){
				if(temp2.equals(word)){
					array.add(cafe.toJSONObject());
				}
			}
		}
		return array;
	}

	public JSONArray getAllOrganization() {
		JSONArray array = new JSONArray();
		HashMap<String, Cafe> map = data.getAllCafe();
		for(Cafe cafe : map.values()){
			if(cafe instanceof Organization)
				array.add(cafe.toJSONObject());
		}
		return array;
	}

	public JSONObject getCafe(String cafeName) {
		for (Cafe cafe : data.getAllCafe().values()) {
			if(cafe.getName().equals(cafeName))
				return cafe.toJSONObject();
		}
		return null;
	}

	public JSONObject getCafeByUri(String uri) {
		Cafe cafe = data.getAllCafe().get(uri);
		return cafe.toJSONObject();
	}

}
