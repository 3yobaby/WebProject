package com.oneplace.data;

import org.json.simple.JSONObject;

import com.util.kht.JSONObjectAble;

public class Manager extends Member implements JSONObjectAble{
	private Cafe cafe;
	public Manager() {
		
	}
	public Cafe getCafe() {
		return cafe;
	}
	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = super.toJSONObject();
		json.put("cafe", cafe.getUri());
		return json;
	}
}
