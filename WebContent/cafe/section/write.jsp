<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int category_pk = Integer.valueOf(request.getParameter("category"));
%>

<style>
#write input[type=text]{
	display : block;
}
</style>
<div id="write">
<h2>글쓰기</h2>
<form method="post" action="../Cafe/Board?request=add">
<input type="hidden" value="<%= category_pk%>" name="category"/>
<input type="text" size="20" name="title" placeholder="제목" autofocus="autofocus"/>
<textarea name="content" cols="80" rows="20"></textarea>
<input type="submit" value="등록"/>
<input type="button" value="취소" onclick="history.back()"/>
</form>
</div>