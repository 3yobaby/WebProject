package com.myweb.controller.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.myweb.database.cafe.BoardDB;

@WebServlet("/Cafe/Board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getParameter("request");
		switch(req){
		case "delete":
			delete(request, response);
			break;
		case "add":
			add(request,response);
			break;
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int pk_category = Integer.valueOf(request.getParameter("category"));
		// member
		JSONObject member = (JSONObject)request.getSession().getAttribute("member");
		int pk_member = (Integer) member.get("pk");
		String name = (String) member.get("name");
		BoardDB db = new BoardDB();
		int result = db.insert(pk_category, pk_member, name, title, content);
		response.sendRedirect("../cafe/index.jsp");
		db.close();
	}

	private void delete(HttpServletRequest request,
			HttpServletResponse response) {
		int pk = Integer.valueOf(request.getParameter("pk"));
		JSONObject member = (JSONObject)request.getSession().getAttribute("member");
		BoardDB db = new BoardDB();
		int result = db.delete(pk, (Integer)member.get("pk"));
		PrintWriter out;
		try {
			out = response.getWriter();
			if(result >0){
				out.print("true");
			}else{
				out.print("false");
			}
			db.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
