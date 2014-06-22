package com.myweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.ajax.CafeAjax;
import com.util.kht.Ajax;
import com.util.kht.RequestURIParser;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = RequestURIParser.getAction(request);
		Ajax ajax = null;
		switch(action){
		case "cafe_title_check.ajax":
		case "cafe_uri_check.ajax":
		case "get_organization.ajax":
			ajax = new CafeAjax();
			break;
		}
		ajax.execute(action, request, response);
	}

}
