<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <style>
 	#search_organization{
 		color : silver;
 	}
 </style>
 <script>
function search_organization(){
	var temp = {};
	temp.word = $('#org_search').val();
	$.post('get_organization.ajax', temp, function(result){
		var $result = $.parseJSON(result);
		var $select = $('#org_result');
		if($result == ""){
			$select.empty();
			$('#org_search').val("");
			$('#org_search').attr('placeholder', '기관이 존재하지 않습니다');
		}else{
			$select.empty();
			$.each($result, function(index, org){
				$('<option value='+org.pk+'>'+org.title+'</option>').appendTo($select);
			});
		}
	});
}
</script>
<div id="search_organization">
<select name="organization_pk" id="org_result">
</select>
<input type="text" id="org_search" placeholder="기관 이름" size="30"/>
<input type="button" value="찾기" onclick="search_organization()"><br>
<span>기관의 가입 설정에 따라 바로 가입이 안될 수 있습니다</span>
</div>