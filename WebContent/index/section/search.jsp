<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myweb.application.CafeSearch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<JSONObject> array = CafeSearch.getInstance().getInitCafes();
%>
<style>
	section #search_result{
		text-align : left;
		margin : 0 auto;
		width : 800px;
	}
</style>
<select>
	<option></option>
	<option>카페 이름</option>
</select>
<input type="search" size="50" autofocus="autofocus" placeholder="검색"/>
<div id="search_result">
	<fieldset>
		<%for(int i=0; i<array.size(); i++){ 
		JSONObject info = array.get(i);
		%>
		<p>
		<title><%= info.get("title")%></title>
		<uri><%= info.get("uri") %><uri>
		<date><%= info.get("created").toString()%></date>
		<detail><%= info.get("detail") %></detail>
		</p>
		<%} %>
	</fieldset>
</div>