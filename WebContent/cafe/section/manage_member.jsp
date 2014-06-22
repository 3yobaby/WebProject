<%@page import="com.myweb.application.MemberGetter"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONObject cafe = (JSONObject)session.getAttribute("cafe");
	int pk = (Integer)cafe.get("pk");
	MemberGetter getter = new MemberGetter();
	JSONArray array = getter.getCafeMember(pk);
	String state = "";
%>
<% for(int i=0; i<array.size(); i++){
	JSONObject member = (JSONObject)array.get(i);
%>
	<p>이름 <%= member.get("name") %></p>
	<%
	switch((Integer)member.get("member_type")){
	case 1:
		state = "가입 신청중";
		%><input type="button" onclick="location.href='approve.do?pk=<%= member.get("pk") %>'" value="가입 승인"/> <%
		break;
	case 2:
		state = "회원";
		break;
	case 3:
		state = "카페 관리자";
		break;
	case 4:
		state = "기관 관리자";
		break;
	}
	%>
	<p>아이디 <%= member.get("id") %></p>
	<hr>	
<%} %>