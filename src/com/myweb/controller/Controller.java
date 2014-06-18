package com.myweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.action.MemberAction;
import com.util.kht.Forward;
import com.util.kht.RequestURIParser;


@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = RequestURIParser.getAction(request);
		Forward forward = null;
		switch(action){
		case "login.do":
		case "logout.do":
		case "join.do":
			forward = new MemberAction().execute(action, request, response);
			break;
		
		}
		if(forward.isForwarding()){
			request.getRequestDispatcher(forward.getPath()).forward(request, response);;
		}else{
			response.sendRedirect(forward.getPath());
		}
	}

}
