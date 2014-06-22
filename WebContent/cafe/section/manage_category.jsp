<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.myweb.application.CategoryGetter"%>
<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="manage_category">
<%
	JSONObject cafe = (JSONObject)session.getAttribute("cafe");
	CategoryGetter getter = new CategoryGetter();
	JSONArray categories = getter.getCategory((Integer)cafe.get("pk"));
	%> <h2><%= cafe.get("title") %></h2><%
	for(int i=0; i< categories.size(); i++){
		JSONObject category = (JSONObject)categories.get(i);
		%>
		<form action="">
		<h3><%= category.get("title") %></h3>
		<input type="hidden" name="pk" value="<%= category.get("pk")%>"/> 
		<label for="title">제목</label>
		<input id="title" type="text" name="title" value="<%= category.get("title") %>"/><br>
		<select name="is_sub">
			<option value="true" <%if(category.get("is_sub").equals("false")){%> selected="selected"<%}%>>기본 카테고리</option>
			<option value="false"<%if(category.get("is_sub").equals("true")){%> selected="selected"<%}%>>서브 카테고리</option>
		</select><br>
		<button onclick="">수정</button>
		</form>
		<hr>
		<%
	}
%>
</div>