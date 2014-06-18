package com.oneplace.data;

import org.json.simple.JSONObject;

import com.util.kht.JSONObjectAble;

public class OrganizationManager extends Manager implements JSONObjectAble{
	public OrganizationManager() {}
	
	@Override
	public Cafe getCafe() {
		return super.getCafe();
	};
	
	@Override
	public void setCafe(Cafe cafe) {
		super.setCafe(cafe);
	};
	
	@Override
	public JSONObject toJSONObject() {
		return super.toJSONObject();
	}

}
