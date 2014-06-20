<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.myweb.application.CategoryGetter"%>
<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONObject cafe = (JSONObject)session.getAttribute("cafe");
	CategoryGetter getter = new CategoryGetter();
	JSONArray categories = getter.getCategory((Integer)cafe.get("pk"));
	%> <h2><%= cafe.get("title") %></h2><%
	for(int i=0; i< categories.size(); i++){
		JSONObject category = (JSONObject)categories.get(i);
		%><h3><%= category.get("title") %></h3> <%
	}
%>
