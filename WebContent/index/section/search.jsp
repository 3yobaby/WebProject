<%@page import="com.myweb.database.cafe.CafeMembersDB"%>
<%@page import="com.myweb.database.cafe.CafeDB"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONArray array = null;
	CafeDB db = new CafeDB();
	String select = null;
	try{
		String search = request.getParameter("search");
		if(search == null){
			search = "all";
		}
		if(search.equals("all")){ // 전체 검색 
			array = db.getAllCafeArray();
		}else if(search.equals("new")){ // 새로 생성된 카페
			array = db.getNewCafeArray();
		}else if(search.equals("org")){ // 기관
			array = db.getAllOrganizationArray();
		}else if(search.equals("join")){ //가입한 카페
			JSONObject member = (JSONObject)session.getAttribute("member");
			if(member == null){
				array = new JSONArray();
			}
			else {
				CafeMembersDB cdb = new CafeMembersDB();
				array = cdb.getCafeArray((Integer)member.get("pk"));
			}
		}else if(search.equals("word")){ // 검색어
			select = request.getParameter("select");
			if(select.equals("word")){ // 검색어로 검색
				String value = request.getParameter("value");
				array = db.getCafeArrayByWord(value);
			}else if(select.equals("name")){ // 카페 타이틀로 검색
				String value = request.getParameter("value");
				array = db.getCafeArrayByTitle(value);
			}
			
		}
	}finally{
		db.close();
	}
%>
<style>
	section #search_result{
		text-align : center;
		margin : 0 auto;
		width : 100%;
		padding : 0px;
	}
	
	section #search_result p {
		margin: 0 auto;
		width : 50%;
		border: dotted;
		clear : both;
		padding : 5px;
		text-align: center;
	}
	
	section #search_result > button {
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
		display : inline;
		float : left;
		font-weight: bold;
		margin: 0px;
	}
	
	.title:HOVER{
		border-color: black;
		border-style: solid;
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
		clear : both;
	}
	
	.cafe_type{
		display : inline;
		float : lest;
	}
</style>
<form method="get" action="index.jsp">
<select name="select">
	<option value="word"></option>
	<option value="name" <%if(select != null && select.equals("name")){%>selected="selected"<%} %>>카페 이름</option>
</select>
<input type="hidden" name="search" value="word"/>
<input type="search" size="20" autofocus="autofocus" name="value" placeholder="검색어"/>
<input type="submit" value="검색"/>
</form>
<div>
	<hr>
	<div id="search_result">
		<button onclick="location.href='?search=all'">전체</button>
		<button onclick="location.href='?search=org'">기관</button>
		<button onclick="location.href='?search=new'">신규</button>
		<button onclick="location.href='?search=join'">가입</button>
		<%
		for(int i=0; i<array.size(); i++){ 
		JSONObject info = (JSONObject)array.get(i);
		if(info.get("is_search").equals("false"))
			continue;
		%>
		
		<p>
		<%if(info.get("is_organization").equals("true")){ %>
		<span class="cafe_type">기관</span>
		<%}else{ %>
		<span class="cafe_type">카페</span>
		<%} %>
		<span class="title" onclick="location.href='<%= info.get("uri") + ".cafe"%>'"><%= info.get("title") %></span>
		<span class="date"><%= info.get("created").toString()%></span>
		<span class="name"><%= info.get("manager_id")%></span><br>
		<span class="detail"><%= info.get("detail") %></span>
		</p>
		<%} %>
	</div>
	<button onclick="location.href='index.jsp?section=add_cafe'">카페 만들기</button>
</div>