package com.myweb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.myweb.application.CafeApplication;
import com.myweb.database.cafe.CafeDB;
import com.myweb.database.cafe.OrganizationCafeDB;
import com.util.kht.Action;
import com.util.kht.Forward;

public class CafeAction extends Action{
	@Override
	public Forward execute(String command, HttpServletRequest request,
			HttpServletResponse response) {
		switch(command){
		case "add_cafe.do":
			addCafe(request, response);
			break;
		case "join_cafe.do":
			joinCafe(request,response);
			break;
		case "join_cancel.do":
			joinCancel(request, response);
			break;
		case "approve.do":
			approve(request, response);
			break;
		case "cancel_join_organization.do":
			cancelJoinOrganization(request, response);
			break;
		case "quit_organization.do":
			quitOrganization(request,response);
			break;
		case "request_organization.do":
			requestOrganization(request, response);
			break;
		}
		return forward;
	}

	// 카페 페이지에서 기관 신청
	private void requestOrganization(HttpServletRequest request,
			HttpServletResponse response) {
		OrganizationCafeDB db = new OrganizationCafeDB();
		String pk = request.getParameter("organization_pk");
		JSONObject cafe = (JSONObject) request.getSession().getAttribute("cafe");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		app.setCafeToOrganization(Integer.valueOf(pk), (Integer)cafe.get("pk"));
		db.close();
		setPath("?section=manage_cafe");
	}

	private void quitOrganization(HttpServletRequest request,
			HttpServletResponse response) {
		cancelJoinOrganization(request, response);
	}

	private void cancelJoinOrganization(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject cafe = (JSONObject)session.getAttribute("cafe");
		OrganizationCafeDB db = new OrganizationCafeDB();
		db.delete((Integer)cafe.get("pk"));
		db.close();
		setPath("?section=manage_cafe");
	}

	private void approve(HttpServletRequest request,
			HttpServletResponse response) {
		int memberKey = Integer.valueOf(request.getParameter("pk"));
		HttpSession session = request.getSession();
		JSONObject cafe = (JSONObject) session.getAttribute("cafe");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		app.approve((Integer)cafe.get("pk"), memberKey);
		setPath("?section=manage_member");
	}

	private void joinCancel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject cafe = (JSONObject) session.getAttribute("cafe");
		JSONObject member = (JSONObject) session.getAttribute("member");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		boolean b = app.dropMember((Integer)cafe.get("pk"), (Integer)member.get("pk"));
		if(b){
			setPath("?message=success");
		}else{
			setPath("?message=fail");
		}
	}

	private void joinCafe(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		JSONObject cafe = (JSONObject) session.getAttribute("cafe");
		JSONObject member = (JSONObject) session.getAttribute("member");
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		boolean result = app.joinCafe((Integer)cafe.get("pk"),(Integer) member.get("pk"));
		if(result){
			setPath("?message=success");
		}else{
			setPath("?message=fail");
		}
	}

	private void addCafe(HttpServletRequest request,
			HttpServletResponse response) {
		String uri = request.getParameter("uri");
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		String searchWords = request.getParameter("search_words");
		String joinRule = request.getParameter("join_rule");
		String organizationKey = request.getParameter("organization_pk");
		boolean isSearch = Boolean.valueOf(request.getParameter("is_search"));
		JSONObject member = (JSONObject)request.getSession().getAttribute("member");
		if(member == null){
			setPath("./");
			setForwarding(false);
			return;
		}
		CafeApplication app = (CafeApplication) request.getServletContext().getAttribute("cafeApplication");
		String id = (String) member.get("id");
		app.createCafe(title, uri, id, detail, searchWords,joinRule, isSearch ,false);
		if(organizationKey != null && !organizationKey.equals("")){
			CafeDB db = new CafeDB();
			JSONObject cafe = db.getCafe(uri);
			app.setCafeToOrganization(Integer.valueOf(organizationKey), (Integer)cafe.get("pk"));
		}
		setPath("./");
		setForwarding(false);
		
	}

}
