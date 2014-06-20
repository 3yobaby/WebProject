<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONArray categories = (JSONArray) session.getAttribute("categories");
%>

<% 
for(int i=0; i<categories.size(); i++){ 
	JSONObject category = (JSONObject)categories.get(i);
	if(category.get("is_sub").equals("false")){ %>
	<category onclick="location.href='<%= "index.jsp?category=" + category.get("pk") %>'"><%= category.get("title")%></category>
	<%}else{ %>
	<category onclick="location.href='<%= "index.jsp?category=" + category.get("pk") %>'">ㄴ<%= category.get("title")%></category>
	<%} %>
<%} %>