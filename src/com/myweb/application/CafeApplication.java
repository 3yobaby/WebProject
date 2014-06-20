package com.myweb.application;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.database.MemberDB;
import com.myweb.database.cafe.CafeDB;
import com.myweb.database.cafe.CafeMembersDB;
import com.myweb.database.cafe.CategoryDB;

public class CafeApplication {
	private String initCategoryTitle[] = {
		"자유게시판"	
	};
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
		JSONObject json = db.getMember(id, pass);
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

	public boolean modifyMember(String id, String pass, String name, String email,
			String tel) {
		MemberDB db = new MemberDB();
		JSONObject member = db.getMember(id);
		int result = 0;
		if(member.get("pass").equals(pass)){
			 result = db.update(id, pass, name, email, tel);
		}
		if(result<0)
			return true;
		else return false;
	}

	public boolean createCafe(String title, String uri, String id,String detail, boolean isOrganization) {
		CafeDB cdb = new CafeDB();
		cdb.insert(title, uri, id, detail, isOrganization);
		CategoryDB ccdb = new CategoryDB();
		JSONObject cafe = cdb.getCafe(uri);
		for (int i = 0; i < initCategoryTitle.length; i++) {
			String cTitle= initCategoryTitle[i];
			ccdb.insert((Integer)cafe.get("pk"), cTitle, false);
		}
		MemberDB mdb = new MemberDB();
		JSONObject member = mdb.getMember(id);
		CafeMembersDB cmdb = new CafeMembersDB();
		cmdb.insert((Integer)cafe.get("pk"), (Integer)member.get("pk"), 3);
		cmdb.close();
		cdb.close();
		ccdb.close();
		return false;
	}
}
