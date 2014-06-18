package com.oneplace.dao;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.oneplace.data.Member;
import com.util.kht.ConnectionGetter;
import com.util.kht.SampleData;

public class MemberDAO extends ConnectionGetter{
	private SampleData data = SampleData.getInstance();
	public MemberDAO() {}

	public JSONObject getMember(String id, String pass){
		HashMap<String, Member> map = data.getAllMember();
		Member member = map.get(id);
		if(member.getPass().equals(pass))
			return member.toJSONObject();
		else return null;
	}

	public boolean addMember(JSONObject member) {
		HashMap<String, Member> map = data.getAllMember();
		if(map.get(member.get("id")) == null){
			map.put((String)member.get("id"), new Member(member));
		}
		return false;
	}

	public Member getMember(String id) {
		HashMap<String, Member> map = data.getAllMember();
		Member member = map.get(id);
		return member;
	}

	public Map<String, Member> getAllMember() {
		return data.getAllMember();
	}

	
}
