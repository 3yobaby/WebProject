package com.myweb.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.action.CafeAction;
import com.myweb.action.MemberAction;
import com.myweb.application.CafeApplication;
import com.myweb.database.cafe.CafeDB;
import com.myweb.database.cafe.CategoryDB;
import com.util.kht.Forward;
import com.util.kht.RequestURIParser;


@WebServlet({"*.cafe", "*.do"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		CafeApplication app = (CafeApplication) context.getAttribute("cafeApplication");
		if(app == null){
			app = CafeApplication.getInstance();
			context.setAttribute("cafeApplication", app);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String map = RequestURIParser.getMapping(request);
		switch(map){
		case "do":
			doProcess(request, response);
			break;
		case "cafe":
			goCafe(request,response);
			break;
		case "org":
			goOrg(request,response);
			break;
		}
	}

	private void goOrg(HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void goCafe(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = RequestURIParser.getAction(request);
		String uri = action.substring(0, action.indexOf("."));
		CafeDB db = new CafeDB();
		JSONObject json = db.getCafe(uri);
		HttpSession session = request.getSession();
		if(json == null){
			response.sendRedirect("index.jsp?error=cafe");
		}else{
			session.setAttribute("cafe", json);
			CategoryDB ccdb = new CategoryDB();
			JSONArray categories = ccdb.getCategories((Integer)json.get("pk"));
			session.setAttribute("categories", categories);
			response.sendRedirect("cafe/index.jsp");
			ccdb.close();
		}
		db.close();
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = RequestURIParser.getAction(request);
		Forward forward = null;
		switch(action){
		case "login.do":
		case "logout.do":
		case "join.do":
		case "modify.do":
			forward = new MemberAction().execute(action, request, response);
			break;
		case "add_cafe.do":
		case "join_cafe.do":
		case "join_cancel.do":
		case "approve.do": // 가입승인
		case "cancel_join_organization.do":
		case "quit_organization.do":
		case "request_organization.do":
			forward = new CafeAction().execute(action, request, response);
			break;
		
		}
		if(forward.isForwarding()){
			request.getRequestDispatcher(forward.getPath()).forward(request, response);;
		}else{
			response.sendRedirect(forward.getPath());
		}
	}

}
