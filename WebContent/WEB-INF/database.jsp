<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jquery-1.11.1.min.js" type="text/javascript"></script>
<script>
	var temp;
	$.get('cafelist.data', function(result){
		result = $.parseJSON(result);
		$.each(result, function(index, val){
			var text = "";
			for(key in val){
				text += key + ":";
				text += val[key];
				text += "<br>";
			}
			$('#cafelist').html($('#cafelist').html() + text + "<hr>");
		})
	});
	$.get('memberlist.data', function(result){
		result = $.parseJSON(result);
		$.each(result, function(index, val){
			var text = "";
			for(key in val){
				text += key + ":";
				text += val[key];
				text += "<br>";
			}
			$('#memberlist').html($('#memberlist').html()+text+"<hr>");
		})
	});
</script>
</head>
<body>
<fieldset>
<legend>데이터베이스</legend>
	<div id="cafelist">
	<h2>카페 리스트</h2>
	</div>
	<div id="memberlist">
	<h2>멤버 리스트</h2>
	</div>
	<div id="categorylist">
	<h2>카테고리 리스트</h2>
	</div>
	<div id="boardlist">
	<h2>게시판 리스트</h2>
	</div>
	<div id="memberjoinedcafelist">
	<h2>멤버=카페 관계 리스트</h2>
	</div>
	<div id="cafeorglist">
	<h2>기관-카페 리스트</h2>
	</div>
</fieldset>
</body>
</html>