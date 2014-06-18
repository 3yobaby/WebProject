<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	section > div {
		width : 400px;
		margin: 20px auto;
		text-align: left;
	}
	
	section label {
		margin : 0px;
		width : 120px;
		float : left;
	}
	section  .should{
		color : green;
	}
	
	section #div_join ul{
		margin : 0px;
		padding : 0px;
	}
	section #div_join li{
		list-style:none; 
		margin: 5px 0;	
		font-size:14px;	
	}
	section #div_join input[type=button]{
		float : right;
	}
	section #div_join input[type=submit]{
		float : right;
	}
</style>
<div id="div_join">
	<form method="post" action="join.do">
	<fieldset>
	<legend>회원가입</legend>
	<ul>
		<li>
			<label class="should" for="id">아이디*</label>
			<input type="text" id="id" name="id">
		</li>
		<li>
			<label class="should" for="name">닉네임*</label>
			<input type="text" id="name" name="name">
		</li>
		<li>
			<label class="should" for="pass">비밀번호*</label>
			<input type="text" id="pass" name="pass">
		</li>
		<li>
			<label for="email">이메일</label>
			<input type="email" id="email" name="email">
		</li>
		<li>
			<label for="tel">연락처</label>
			<input type="tel" id="tel" name="tel">
		</li>
	</ul>
	</fieldset>
	<input type="button" value="취소" onclick="location.href = './'"/>
	<input type="submit" value="가입"/>
	</form>
</div>