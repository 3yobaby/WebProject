<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONObject member = (JSONObject)session.getAttribute("member");
%>
<style>
	#member_info button{
		border: none;
		background-color: red;
		color: white;
	}
</style>
<script>
	function login(){
		$.post('login.do',$('#member_info form').serialize(), function(){
			location.reload();
		});
	}
</script>
<div id="member_info">
<h2>회원정보</h2>
<%if(member != null){ %>
<%= member.get("id")%>
<%= member.get("name")%>
<%}else { %>
	<form>
	<input type="text" name="id" placeholder="아이디"/>
	<input type="password" name="pass" placeholder="비밀번호"/>
	<button onclick="login()">로그인</button>
	</form>
<%} %>
<hr>
</div>