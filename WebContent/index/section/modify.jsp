<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	JSONObject member = (JSONObject)session.getAttribute("member");
    %>
<style>
	section > div {
		width : 400px;
		margin: 20px auto;
		text-align: left;
	}
	
	section label {
		margin : 0px;
		width : 120px;
		float : left;
	}
	section  .should{
		color : green;
	}
	
	section #div_join ul{
		margin : 0px;
		padding : 0px;
	}
	section #div_join li{
		list-style:none; 
		margin: 5px 0;	
		font-size:14px;	
	}
	section #div_join input[type=button]{
		float : right;
	}
	section #div_join input[type=submit]{
		float : right;
	}
</style>
<div id="div_join">
	<form method="post" action="modify.do">
	<fieldset>
	<legend>정보수정</legend>
	<ul>
		<li>
			<label class="should" for="id">아이디*</label>
			<input type="text" id="id" name="id" value="<%= member.get("id")%>" readonly="readonly">
		</li>
		<li>
			<label class="should" for="name">닉네임*</label>
			<input type="text" id="name" name="name" value="<%= member.get("name")%>">
		</li>
		<li>
			<label class="should" for="pass">비밀번호 확인*</label>
			<input type="text" id="pass" name="pass" placeholder="비밀번호를 입력하세요">
		</li>
		<li>
			<label class="should" for="new_pass">비밀번호*</label>
			<input type="text" id="new_pass" name="new_pass" placeholder="새로운 비밀번호">
		</li>
		<li>
			<label for="email">이메일</label>
			<input type="email" id="email" name="email" value="<%= member.get("email") %>">
		</li>
		<li>
			<label for="tel">연락처</label>
			<input type="tel" id="tel" name="tel" value="<%= member.get("tel") %>">
		</li>
	</ul>
	</fieldset>
	<input type="button" value="취소" onclick="location.href = './'"/>
	<input type="submit" value="수정"/>
	</form>
</div>