<%@page import="com.myweb.database.cafe.BoardDB"%>
<%@page import="com.myweb.database.cafe.CategoryDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int category_pk = Integer.valueOf(request.getParameter("category"));
	BoardDB db = new BoardDB();
	CategoryDB cdb = new CategoryDB();
	String cname = cdb.getCategoryName(category_pk);
	JSONArray boards = db.getBoards(category_pk);
	String board = request.getParameter("board");
%>
<style>
	#boards_div td{
		padding : 10px;
	}
	
	#boards_div tr{
		
	}
	
	#boards_div .board_title{
		width : 400px;
		cursor: pointer;
		padding : 1px;
	}
	
	.board_title:HOVER{
		border-bottom: solid #aa0000;
	}
</style>
<div id="boards_div">
	<h2><%= cname %></h2>
	<hr>
	<table>
	<% for(int i=0; i< boards.size(); i++){ 
		JSONObject temp = (JSONObject)boards.get(i);%>
		<tr>
			<td class="board_title" onclick="location.href='?board=<%= temp.get("pk") %>'">
			<%= temp.get("title") %>
			</td>
			<td><%= temp.get("name") %></td>
			<td><%= temp.get("created") %></td>
		</tr>
	<%} %>
	</table>
	<hr>
	<button onclick="location.href='?write=true&category='+<%=category_pk %>">글쓰기</button>
</div>