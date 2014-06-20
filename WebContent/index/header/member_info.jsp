<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONObject member = (JSONObject)session.getAttribute("member");
%>
<style>
	id{
		color : white;
		font-weight: bold;
		cursor: pointer;
	}
	header input{
		border : none;
		margin : 5px;
		padding : 1px;
		color : white;
		background-color: red;
	}
	header input:hover{
		background-color: white;
		color : red;
	}
</style>
<div>
<id><%= member.get("id")%></id>
<input type="button" onclick="location.href='?section=modify'" value="정보수정"/>
<input type="button" onclick="location.href='logout.do'" value="로그아웃"/>
</div>