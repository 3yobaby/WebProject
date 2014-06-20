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
	<form method="post" action="add_cafe.do">
	<h2>카페 만들기</h2>
	<hr>
	<ul>
		<li>
			<label class="should" for="title">카페이름*</label>
			<input type="text" id="title" name="title">
		</li>
		<li>
			<label class="should" for="uri">카페주소*</label>
			<input type="text" id="uri" name="uri">
		</li>
		<li>
			<label class="should" for="detail">상세설명*</label>
			<input type="text" id="detail" name="detail">
		</li>
	</ul>
	<input type="button" value="취소" onclick="location.href = './'"/>
	<input type="submit" value="생성"/>
	</form>
</div>