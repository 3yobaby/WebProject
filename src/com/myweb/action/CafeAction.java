package com.myweb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.myweb.application.CafeApplication;
import com.util.kht.Action;
import com.util.kht.Forward;

public class CafeAction extends Action{
	private CafeApplication app = CafeApplication.getInstance();
	@Override
	public Forward execute(String command, HttpServletRequest request,
			HttpServletResponse response) {
		switch(command){
		case "add_cafe.do":
			addCafe(request, response);
			break;
		}
		return forward;
	}

	private void addCafe(HttpServletRequest request,
			HttpServletResponse response) {
		String uri = request.getParameter("uri");
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		JSONObject member = (JSONObject)request.getSession().getAttribute("member");
		if(member == null){
			setPath("./");
			setForwarding(false);
			return;
		}
		String id = (String) member.get("id");
		app.createCafe(title, uri, id, detail, false);
		setPath("./");
		setForwarding(false);
		
	}

}
