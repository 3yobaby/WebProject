package com.myweb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.application.CafeApplication;
import com.util.kht.Action;
import com.util.kht.Forward;

public class MemberAction extends Action{
	private CafeApplication app = CafeApplication.getInstance();
	@Override
	public Forward execute(String command, HttpServletRequest request,
			HttpServletResponse response) {
		switch(command){
		case "login.do":
			login(request, response);
			break;
		case "logout.do":
			logout(request,response);
			break;
		case "join.do":
			join(request, response);
			break;
		}
		return forward;
	}

	private void join(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		boolean result = app.join(id, name, pass, email, tel);
		if(result){
			forward.setForwarding(false);
			setPath("./");
		}else{
			forward.setForwarding(false);
			setPath("index.jsp?section=join");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject info = (JSONObject)session.getAttribute("login_info");
		if(info != null){
			app.logout((String)info.get("id"));
			session.invalidate();
		}
		setPath("./");
		setForwarding(false);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject info = app.login(request.getParameter("id"), request.getParameter("pass"), session);
		if(info != null){
			session.setAttribute("login_info", info);
		}
		setPath("./");
		setForwarding(false);
	}
	
}
