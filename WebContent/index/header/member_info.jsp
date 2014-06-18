<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONObject info = (JSONObject)session.getAttribute("login_info");
%>
<div>
<id><%= info.get("id")%></id>
<input type="button" onclick="location.href='logout.do'" value="로그아웃"/>
</div>