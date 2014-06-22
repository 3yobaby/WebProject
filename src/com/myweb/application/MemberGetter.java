package com.myweb.application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.database.MemberDB;
import com.myweb.database.cafe.CafeMembersDB;

@SuppressWarnings("unchecked")
public class MemberGetter {
	public JSONArray getCafeMember(int cafeKey){
		CafeMembersDB db = new CafeMembersDB();
		MemberDB mdb = new MemberDB();
		JSONArray array = db.getCafeArrayByCafe(cafeKey); // 카페에 가입신청 혹은 가입된 회원과 관리자
		JSONArray result = new JSONArray();
		for(int i=0; i<array.size(); i++){
			JSONObject temp = (JSONObject) array.get(i);
			int type = (int) temp.get("member_type");
			int memberKey = (int) temp.get("fk_member");
			JSONObject member = mdb.getMember(memberKey);
			member.put("member_type", type);
			result.add(member);
		}
		mdb.close();
		db.close();
		return result;
	}
}
