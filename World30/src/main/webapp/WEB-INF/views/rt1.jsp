<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<button>rt1</button>
	
	<script type="text/javascript">
	$(document).ready(function(){

		//동적객체, 정적객체 둘다 적용되는 jquery문
		$("body").on("click", "button", function(){
		//부모객체  --버튼을 클릭하면 함수 실행

			var test1 = "hello";

			$.ajax({
				type: 'post',
				url: 'rt1',
				headers: {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
											//└위의 type값과 맞춰준다					
				},
				dataType: 'text',
				//date를 JSON형태로 만들어주기
				data: JSON.stringify({
					test1 : test1
				//└속성명	└속성값
				}),
				success: function(result) {
					console.log(result);
				}

			});

		});
		
	});
	</script>

</body>
</html>