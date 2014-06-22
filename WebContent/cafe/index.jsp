<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONObject cafe = (JSONObject)session.getAttribute("cafe");
	String category_pk = request.getParameter("category");
	String board_pk = request.getParameter("board");
	String write = request.getParameter("write");
	String section = request.getParameter("section");
	JSONObject member = (JSONObject)session.getAttribute("member");
	CafeMembersDB cmdb = new CafeMembersDB();
	int type = 0;
	if(member != null){
		type = cmdb.getType((Integer)cafe.get("pk"), (Integer)member.get("pk"));
	}
	cmdb.close();
	String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= cafe.get("title") %></title>
<script src="jquery-1.11.1.min.js" type="text/javascript"></script>
<script>
	document.createElement("uri");
	document.createElement("category");
	<% if(message != null){%>
		alert('<%= message %>');
	<% }%>
</script>
<style>
header {
	height : 100px;
	background: #aa0000;
	margin : 1%;
	padding : 10px;
	color : white;
}

aside {
	width : 15%;
	height : 500px;
	float : left;
	border-right-style: solid;
	border-right-color: red;
	margin : 1%;
	padding : 10px;
	clear : both;
}

section {
	width : 77%;
	height : 500px;
	float : left;
	margin : 1%;
}
footer {
	clear : both;
	background: #aaaa00;
	margin : 10px;
}

uri {
	cursor: pointer;
}

category {
	display : block;
	cursor: pointer;
	font-weight: bold;
	font-style: italic;
}

category:HOVER{
	border-right: solid #aa0000;
}

button:HOVER {
	border-color: red;
}

body > nav{
	width : 95%;
	height : 30px;
	margin : 0 auto;
}

body > nav > span{
	display : inline;
	float : left;
	size : 2em;
	font-weight: bold;
	margin-right : 10px;
}

body > nav:HOVER ul{
	opacity : 1;
} 

body > nav ul{
	float : left;
	opacity : 0;
	list-style: none;
	padding : 0px;
	margin : 0px;
}

body > nav li {
	float : left;
	display : inline;
	margin : 0px;
}

body > nav button{
	color : red;
	border: none;
}

body > nav button:HOVER {
	color : white;
	border : thin;
	border-radius : 5px;
	background-color: red;
}

button{
	border: none;
	cursor: pointer;
}

button:HOVER{
	background : red;
	color: white;
	border-radius : 4px;
}
hr{
	border-color: #aa0000;
}
</style>
</head>
<body>
<header>
	<h1><uri onclick="location.reload()"><%= cafe.get("title") %></uri></h1>
</header>
<nav>
	<jsp:include page="nav/navigation.jsp"></jsp:include>
</nav>
<hr>
<aside>
	<%if(type > 2){ %>
	<jsp:include page="aside/manage_form.jsp"></jsp:include>
	<%}else if(type <= 1 && member != null){ %>
		<jsp:include page="aside/join_form.jsp"></jsp:include>
	<%} %>
	<jsp:include page="aside/member_info.jsp"></jsp:include>
	<jsp:include page="aside/categories.jsp"></jsp:include>
</aside>
<section>
	<% if(write != null){ %>
		<jsp:include page="section/write.jsp"></jsp:include>
	<% } %>
	<% if(board_pk != null){ %>
		<jsp:include page="section/board.jsp"></jsp:include>
	<%} %>
	<% if(category_pk != null){ %>
		<jsp:include page="section/boards.jsp"></jsp:include>
	<% } %>
	<% if(section != null && section.equals("manage_category")){ %>
		<jsp:include page="section/manage_category.jsp"></jsp:include>
	<%}else if(section != null && section.equals("manage_member")){ %>
		<jsp:include page="section/manage_member.jsp"></jsp:include>
	<%}else if(section != null && section.equals("manage_cafe")){ %>
		<jsp:include page="section/manage_cafe.jsp"></jsp:include>
	<%}else if(section != null && section.equals("join_form")){ %>
	<jsp:include page="section/join_form.jsp"></jsp:include>
	<%} %>
</section>
<footer></footer>
</body>
</html>