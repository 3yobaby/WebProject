package com.oneplace.data;

import java.sql.Date;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.JSONObjectAble;

/*
 * 개인정보, 가입시간 등등
 */
public class Member implements JSONObjectAble{
	private String id;
	private String name;
	private String pass;
	private Date birthday;
	private String email;
	private String tel;
	private Date date;
	private boolean isValid = true;
	private boolean isOrganizationManager = false;
	private ArrayList<Cafe> cafeList = new ArrayList<Cafe>();
	
	public Member() {}
	
	public Member(JSONObject member) {
		this.id = (String) member.get("id");
		this.name = (String) member.get("name");
		this.pass = (String) member.get("pass");
		this.birthday= (Date) member.get("birthday");
		this.email = (String) member.get("email");
		this.tel = (String) member.get("tel");
		this.date = (Date) member.get("date");
		this.isValid = (boolean) member.get("is_valid");
		this.isOrganizationManager = (boolean) member.get("is_organization_manager");
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isValid() {
		return isValid;
	}



	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}


	public ArrayList<Cafe> getCafeArray() {
		return cafeList;
	}



	public void setCafeArray(ArrayList<Cafe> cafeArray) {
		this.cafeList = cafeArray;
	}


	public boolean isOrganizationManager() {
		return isOrganizationManager;
	}

	public void setOrganizationManager(boolean isOrganizationManager) {
		this.isOrganizationManager = isOrganizationManager;
	}

	public ArrayList<Cafe> getCafeList() {
		return cafeList;
	}

	public void setCafeList(ArrayList<Cafe> cafeList) {
		this.cafeList = cafeList;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("pass", pass);
		json.put("birthday", birthday);
		json.put("email", email);
		json.put("tel", tel);
		json.put("date", String.valueOf(date));
		json.put("is_valid", isValid);
		json.put("is_organization_manager", isOrganizationManager);
		JSONArray array = new JSONArray();
		for(Cafe cafe : cafeList){
			array.add(cafe.getUri());
		}
		json.put("cafe_list", array);
		return json;
	}
}
