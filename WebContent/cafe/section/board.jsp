<%@page import="com.myweb.database.cafe.BoardDB"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	JSONObject member = (JSONObject)session.getAttribute("member");
	String board_pk = request.getParameter("board");
	int pk = Integer.valueOf(board_pk);
	BoardDB db = new BoardDB();
	JSONObject board = (JSONObject)db.getBoard(pk);
%>
<script>
	function delete_board(pk){
		$.get('../Cafe/Board?request=delete&pk='+pk, function(result){
			alert(result);
		});
	}
</script>
<h3><%= board.get("title") %></h3>
<p><%= board.get("name") %></p>
<p><%= board.get("created") %></p>
<hr>
<%= board.get("content")%><br>
<hr>
<%if(member.get("pk").equals(board.get("fk_member"))){ %>
<button onclick="delete_board(<%= board.get("pk")%>)">삭제</button>
<button>수정</button>
<%} %>
<button>답글</button>
<button onclick="history.back()">뒤로가기</button>