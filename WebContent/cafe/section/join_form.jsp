<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONObject member = (JSONObject)session.getAttribute("member");
	JSONObject cafe = (JSONObject)session.getAttribute("cafe");
	String joinRule = (String)cafe.get("join_rule");
	CafeMembersDB db = new CafeMembersDB();
	int type = db.getType((Integer)cafe.get("pk"), (Integer)member.get("pk"));
%>
<h2>카페 가입</h2>
<hr>
<%if(type == -1 || type == 0){ %> <%-- 가입신청 x --%>
	<% if(joinRule.equals("private")){ %>
	<span>가입을 받지 않습니다</span>
	<%} else if(joinRule.equals("public")){%>
	<span>바로 가입 가능합니다</span>
	<button onclick="location.href='join_cafe.do'">가입하기</button>
	<%} else if(joinRule.equals("cafe")){%>
	<span>카페 관리자 승인 후 가입</span>
	<button onclick="location.href='join_cafe.do'">가입신청</button>
	<%} else if(joinRule.equals("organization")){%>
	<span>기관 관리자 승인 후 가입</span>
	<button onclick="location.href='join_cafe.do'">가입신청</button>
	<%} %>
<% }else if(type == 1){%> <%-- 가입 신청중 --%>
<span>가입 신청중</span>
<button onclick="location.href='join_cancel.do'">신청 취소</button>
<% }else if(type == 2){%>
<span>일반회원</span>
<% }else if(type == 3){%>
<span>카페 관리자</span>
<% }else if(type == 4){%>
<span>기관 관리자</span>
<% }%>