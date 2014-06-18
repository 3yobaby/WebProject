package com.myweb.application;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.database.MemberDB;

public class CafeApplication {
	private static CafeApplication instance;
	private HashMap<String, HttpSession> loginMap;
	public CafeApplication() {
		loginMap = new HashMap<String, HttpSession>();
	}
	
	public JSONObject login(String id, String pass, HttpSession session){
		HttpSession temp = loginMap.get(id);
		if(temp != null){
			temp.invalidate();
			loginMap.remove(id);
		}
		MemberDB db = new MemberDB();
		JSONObject json = db.getLMember(id, pass);
		if(json == null){
			return null;
		}
		loginMap.put(id, session);
		return json;
	}

	public static CafeApplication getInstance() {
		if(instance == null){
			return new CafeApplication();
		}else{
			return instance;
		}
		
	}

	public void logout(String id) {
		loginMap.remove(id);
	}

	public boolean join(String id, String pass, String name, String email, String tel) {
		MemberDB db = new MemberDB();
		JSONObject member = db.getMember(id);
		if(member != null){
			System.out.println("아이디 중복");
			return false;
		}
		db.insert(id, pass, name, email, tel);
		db.close();
		return true;
	}
}
