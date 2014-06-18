<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="post" action="login.do">
<input type="text" name="id" placeholder="아이디"/>
<input type="password" name="pass" placeholder="비밀번호"/>
<input type="submit" value="로그인"/>
<input type="button" onclick="location.href='index.jsp?section=join'" value="회원가입"/>
</form>