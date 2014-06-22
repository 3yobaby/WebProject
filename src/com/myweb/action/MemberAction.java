package com.myweb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.application.CafeApplication;
import com.util.kht.Action;
import com.util.kht.Forward;

public class MemberAction extends Action{
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
		case "modify.do":
			modify(request, response);
			break;
		}
		return forward;
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String newPass = request.getParameter("new_pass");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		boolean b = app.modifyMember(id,pass,newPass,name,email,tel);
		forward.setForwarding(false);
		if(b)
			setPath("?message=success");
		else setPath("?message=fail");
	}

	private void join(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		boolean result = app.join(id, name, pass, email, tel);
		forward.setForwarding(false);
		if(result)
			setPath("?message=success");
		else setPath("?message=fail");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject info = (JSONObject)session.getAttribute("member");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		if(info != null){
			app.logout((String)info.get("id"));
			session.invalidate();
		}
		setPath("./");
		setForwarding(false);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		JSONObject info = app.login(request.getParameter("id"), request.getParameter("pass"), session);
		if(info != null){
			session.setAttribute("member", info);
			setPath("?message=success");
		}else {
			setPath("?message=fail");
		}
		setForwarding(false);
	}
	
}
