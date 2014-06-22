package com.myweb.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.database.cafe.CafeDB;
import com.util.kht.Ajax;

public class CafeAjax extends Ajax{
	@Override
	public void execute(String command, HttpServletRequest request,
			HttpServletResponse response) {
		switch(command){
		case "cafe_title_check.ajax":
			cafeTitleCheck(request, response);
			break;
		case "cafe_uri_check.ajax":
			cafeUriCheck(request, response);
			break;
		case "get_organization.ajax":
			getOrganization(request, response);
			break;
		}
	}
	private void getOrganization(HttpServletRequest request,
			HttpServletResponse response) {
		String word = request.getParameter("word");
		CafeDB db = new CafeDB();
		JSONArray array = db.getOrganizationByTitle(word);
		submit(array, response);
	}
	private void cafeTitleCheck(HttpServletRequest request,
			HttpServletResponse response) {
		CafeDB db = new CafeDB();
		String cafeTitle = request.getParameter("title");
		JSONObject json = db.getCafeByTitle(cafeTitle);
		if(json == null){
			submit(false, response);
		}else{
			submit(true, response);
		}
		db.close();
	}
	private void cafeUriCheck(HttpServletRequest request,
			HttpServletResponse response) {
		CafeDB db = new CafeDB();
		String cafeUri = request.getParameter("uri");
		JSONObject json = db.getCafe(cafeUri);
		if(json == null){
			submit(false, response);
		}else{
			submit(true, response);
		}
		db.close();
	}

}
