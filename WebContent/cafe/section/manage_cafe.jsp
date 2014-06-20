<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.myweb.application.CafeGetter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONObject cafe= (JSONObject)session.getAttribute("cafe");
	JSONArray cafes = null;
	if(cafe.get("is_organization").equals("true")){
		CafeGetter getter = new CafeGetter();
		cafes = getter.getCafes((Integer)cafe.get("pk"));
	}
%>
<h2> <%=cafe.get("title") %></h2>
<hr>
<%
if(cafes !=null){
	for(int i=0; i<cafes.size(); i++){
		JSONObject temp = (JSONObject)cafes.get(i);
		%>
		<h2><%= temp.get("title") %></h2>
		<%
	}	
}
%>