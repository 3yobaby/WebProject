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
		width : 400px;
		padding : 0px;
	}
	
	section #search_result p {
		margin: 0px;
		border: dotted;
		clear : both;
		padding : 5px;
	}
	
	section #search_result > button {
		float : right;
		border : none;
		background: black;
		color : white;
		margin-bottom: 4px;
	}
	section #search_result > button:hover {
		transform : rotate(15deg);
		-webkit-transform:rotate(10deg);
	}
	
	
	.title {
		cursor: pointer;
		font-weight: bold;
		margin: 0px;
	}
	
	.name {
		display : inline;
		float : right;
		margin : 10px;
	}
	
	.date{
		display : inline;
		float : right;
		margin : 12px;
	}
	
	.detail {
		font-size: 0.7em;
	}
	
	#search_result button {
		float : right;
	}
</style>
<select>
	<option></option>
	<option>카페 이름</option>
</select>
<input type="search" size="20" autofocus="autofocus" placeholder="검색"/>
<div>
	<fieldset id="search_result">
		<button onclick="">전체</button>
		<button onclick="">신규</button>
		<button onclick="">가입</button><br>
		<%
		for(int i=0; i<array.size(); i++){ 
		JSONObject info = array.get(i);
		%>
		<p>
		<span class="title" onclick="location.href='<%= info.get("uri") + ".cafe"%>'"><%= info.get("title") %></span>
		<span class="date"><%= info.get("created").toString()%></span>
		<span class="name"><%= info.get("manager_id")%></span><br>
		<span class="detail"><%= info.get("detail") %></span>
		</p>
		<%} %>
	</fieldset>
	<button onclick="location.href='index.jsp?section=add_cafe'">카페 만들기</button>
</div>