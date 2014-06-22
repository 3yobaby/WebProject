package com.myweb.application;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.database.MemberDB;
import com.myweb.database.cafe.CafeDB;
import com.myweb.database.cafe.CafeMembersDB;
import com.myweb.database.cafe.CategoryDB;
import com.myweb.database.cafe.OrganizationCafeDB;
import com.util.kht.Log;

public class CafeApplication {
	private String initCategoryTitle[] = {
		"자유게시판"	
	};
	private static CafeApplication instance;
	private HashMap<String, HttpSession> loginMap;
	private CafeApplication() {
		loginMap = new HashMap<String, HttpSession>();
	}
	
	public JSONObject login(String id, String pass, HttpSession session){
		HttpSession temp = loginMap.get(id);
		if(temp != null){ 
			temp.setAttribute("state", false);
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
			return false;
		}
		db.insert(id, pass, name, email, tel);
		db.close();
		return true;
	}

	public boolean modifyMember(String id, String pass, String newPass,String name, String email,
			String tel) {
//		app.modifyMember(id,pass,newPass,name,email,tel);
		MemberDB db = new MemberDB();
		JSONObject member = db.getMember(id);
		int result = 0;
		if(member.get("pass").equals(pass)){
			 result = db.update(id, newPass, name, email, tel);
		}
		if(result>0)
			return true;
		else return false;
	}

	public boolean createCafe(String title, String uri, String id,String detail, String searchWords, 
			String joinRule, boolean isSearch,boolean isOrganization) {
		CafeDB cdb = new CafeDB();
//		cdb.insert(title, uri, managerId, detail, searchWords, joinRule, isSearch, isOrganization)
		cdb.insert(title, uri, id, detail, searchWords, joinRule, isSearch, isOrganization);
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

	public boolean joinCafe(int cafeKey, int memberKey) {
		// 카페 가입타입 확인
		// 이미 신청중인지 확인
		int temp = 0;
		CafeDB cdb = new CafeDB();
		CafeMembersDB cmdb = new CafeMembersDB();
		try{
			int type = cmdb.getType(cafeKey, memberKey); // 가입정보 확인
			if(type != -1){ // 가입신청중이거나 가입중이면
				return false;
			}else{
				JSONObject cafe = cdb.getCafe(cafeKey);
				String joinRule = (String)cafe.get("join_rule");
				switch(joinRule){
				case "private": // 가입 불가
					break;
				case "public":
					temp = cmdb.insert(cafeKey, memberKey, 2); // 일반 회원
					break;
				case "cafe":
					temp = cmdb.insert(cafeKey, memberKey, 1); // 신청중
					break;
				case "organization":
					Log.e(this + ":CafeKey : " + cafeKey + ". MemberKey" + memberKey);
					break;
				}
			}
		}finally{
			cmdb.close();
			cdb.close();
		}
		if(temp>0)
			return true;
		return false;
	}

	public boolean dropMember(int cafeKey, int memberKey) {
		CafeMembersDB db = new CafeMembersDB();
		int result = db.delete(cafeKey, memberKey);
		if(result >0){
			return true;
		}else{
			return false;
		}
	}

	public boolean approve(int cafeKey, int memberKey) {
		CafeMembersDB db = new CafeMembersDB();
		int result = db.update(cafeKey, memberKey, 2);
		if(result == 0)
			return false;
		else if(result == 1){
			return true;
		}else{
			Log.e("update error. modified more than one data:"+ "cafeKey : " + cafeKey + ":memberKey :" + memberKey);
			return false;
		}
	}

	public boolean setCafeToOrganization(int organizationKey, int cafeKey) {
		CafeDB db = new CafeDB();
		JSONObject cafe = db.getCafe(cafeKey);
		JSONObject org = db.getCafe(organizationKey);
		int result = 0;
		if(cafe != null && org != null){
			String rule = (String)cafe.get("join_rule");
			OrganizationCafeDB ocdb = new OrganizationCafeDB();
			org = ocdb.getObject(cafeKey);
			if(org != null) // 이미 신청되거나 가입된 기관이 있다.
				return false;
			switch(rule){
			case "public":
				result = ocdb.insert(organizationKey, cafeKey, 2);
				break;
			case "private":
				break;
			case "organization":
				result = ocdb.insert(organizationKey, cafeKey, 1);
				break;
			}
		}
		db.close();
		if(result>0)
			return true;
		else return false;
	}
}
