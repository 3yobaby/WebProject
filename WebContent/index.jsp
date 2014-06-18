<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String section = request.getParameter("section");
	if(section == null)
		section = "search";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	// member_login form
	document.createElement("id");
	// search form
	document.createElement("title");
	document.createElement("uri");
	document.createElement("date");
	document.createElement("detail");
</script>
<style>
	header{
		background-color: #aa0000;
		height : 30px;
	}
	header > *{
		float : right;
	}
	header > id{
		color: white;
	}
	
	section{
		width : 100%;
		height : 500px;
		float : left;
		text-align: center;
	}
	
	footer{
		width : 100%;
		background-color: #00aaaa;
		clear: both;
		position: fixed;
		bottom: 0px;
	}
</style>
</head>
<body>
<header>
	<%if(session.getAttribute("login_info") != null){ %>
	<jsp:include page="index/header/member_info.jsp"></jsp:include>
	<%}else{ %>
	<jsp:include page="index/header/member_login.jsp"></jsp:include>
	<%} %>
</header>
<section>
	<%if(section.equals("search")){ %>
	<jsp:include page="index/section/search.jsp"></jsp:include>
	<%}else if(section.equals("join")){ %>
	<jsp:include page="index/section/join.jsp"></jsp:include>
	<%}%>
</section>
<footer>
	footer
</footer>
</body>
</html>