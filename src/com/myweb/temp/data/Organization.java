package com.oneplace.data;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.util.kht.JSONObjectAble;

public class Organization extends Cafe implements JSONObjectAble{
	private ArrayList<Cafe> cafeList = new ArrayList<Cafe>();
	public Organization(Member manager) {
		super((OrganizationManager)manager);
	}

	public void addCafe(Cafe cafe) {
		cafeList.add(cafe);
	}
	
	@Override
	public JSONObject toJSONObject() {
		return super.toJSONObject();
	}

}
