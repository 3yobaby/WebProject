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

<script>
	var submit = false;
	function submit_join(form){
		if(submit == true){
			return true;
		}
		var title = $('form input[id=title]').val();
		var uri = $('form input[id=uri]').val();
		$.get('cafe_title_check.ajax?title='+title,function(result){
			if(result == 'false'){
				$.get('cafe_uri_check.ajax?uri='+uri, function(result){
					if(result == 'false'){
						submit= true;
						$('#join_form').submit();
					}else{
						alert('카페 주소 중복');
						submit = false;
					}
				});
			}else{
				alert('카페 이름 중복');
				submit = false;
			}
		});
		return false;
	}
	
	var temp = {}; // search words
	var index = 0;
	function cafe_word_add(){
		var val = $('#cafe_search_word').val();
		var $words = $('input[name=search_words]');
		for(key in temp){
			if(temp[key] == val)
				return;
			else{
				$words.val($words.val() + "|");				
			}
		}
		$words.val($words.val() + val);
		temp[index++] = val;
		var result = "검색어 : ";
		for(key in temp){
			result += temp[key] + " ";
		}
		$('#words').html(result);
	}
</script>
<div id="div_join" onsubmit="return submit_join(this)">
	<h2>카페 만들기</h2>
	<hr>
	<form method="post" action="add_cafe.do" id="join_form">
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
			<jsp:include page="search_organization.jsp"></jsp:include>
		</li>
		<li>
			<label class="should" for="detail">상세설명*</label>
			<textarea rows="10" cols="20" id="detail" name="detail" placeholder="상세설명"></textarea>
		</li>
		<li>
			<label for="cafe_words">카페 검색어</label>
			<input id="cafe_search_word" type="text"/>
			<input type="button" onclick="cafe_word_add()" value="추가">
			<input type="hidden" name="search_words"/>
			<p id="words"></p>
		</li>
		<li>
			<select name="join_rule">
				<option value="public">바로 가입</option>
				<option value="cafe">승인 후 가입</option>
				<option value="private">가입 불가</option>
			</select>
			<select name="is_search">
				<option value="true">검색 공개</option>
				<option value="false">검색 비공개</option>
			</select>
		</li>
	</ul>
	<input type="button" value="취소" onclick="location.href = './'"/>
	<input type="submit" value="생성"/>
	</form>
</div>