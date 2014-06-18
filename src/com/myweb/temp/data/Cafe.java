package com.oneplace.data;

import java.sql.Date;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.JSONObjectAble;

@SuppressWarnings("unchecked")
public class Cafe implements JSONObjectAble{
	private int key;
	private String name;
	private boolean search = true;
	private boolean isValid;
	private Date date;
	private String detail = "";
	private boolean isOrganization = false;
	private Member manager;
	private Organization organization = null;
	private ArrayList<Member> memberList = new ArrayList<Member>();
	private String uri;
	private String searchWords = "";
	
	public boolean addMember(Member member) {
		if(memberList.indexOf(member) == -1){
			memberList.add(member);
			return true;
		}else{
			return false;
		}
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	public Cafe(Member manager) {
		this.manager = (Manager)manager;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isOrganization() {
		return isOrganization;
	}

	public void setOrganization(boolean isOrganization) {
		this.isOrganization = isOrganization;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<Member> memberList) {
		this.memberList = memberList;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		JSONArray list = new JSONArray();
		json.put("key", key);
		json.put("name", name);
		json.put("search", search);
		json.put("isValid", isValid);
		json.put("date", String.valueOf(date));
		json.put("detail", detail);
		json.put("is_organization", isOrganization);
		json.put("manager", manager.getId());
		if(organization != null)
			json.put("organization", organization.getUri());
		json.put("search_words", searchWords);
		for (Member member : memberList) {
			list.add(member.getId());
		}
		json.put("member_list", memberList);
		return json;
	}

}
