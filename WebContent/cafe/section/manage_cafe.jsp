<%@page import="com.myweb.database.cafe.CafeDB"%>
<%@page import="com.myweb.database.cafe.OrganizationCafeDB"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.myweb.application.CafeGetter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONObject cafe= (JSONObject)session.getAttribute("cafe");
	OrganizationCafeDB db = new OrganizationCafeDB();
	CafeGetter cg = new CafeGetter();
	JSONObject organization = cg.getOrganization((Integer)cafe.get("pk"));
	db.close();
%>
<% if(organization != null){ %>
	<h1>기관 : <%= organization.get("title") %></h1>
	<%
	switch((Integer)organization.get("cafe_type")){
	case 1:
		%> 가입 신청중 <button onclick="location.href='cancel_join_organization.do'">취소</button> <%
		break;
	case 2:
		%> 가입중 <button onclick="location.href='quit_organization.do'">탈퇴</button><%
		break;
	}
}else{%>
	<h1>기관 가입</h1>
	<form method="get" action="request_organization.do">
		<jsp:include page="search_organization.jsp"></jsp:include>
		<button>가입신청</button>
	</form>
<%} %>
<h2> <%=cafe.get("title") %></h2>