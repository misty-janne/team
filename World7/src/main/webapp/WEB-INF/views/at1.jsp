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

	<button>at1 test</button>
	<p></p>

	<script type="text/javascript">
	$(document).ready(function(){
		$("button").click(function(){
			$.ajax({
				//속성: '속성값',
				type: 'post',
				//어디로 갈지
				url: '/at1',
				//넘어오는데이터타입(통신후 출력)
				dataType: 'text',
				//넘겨주는데이터
				data: {
					'msg' : "hello"
				},
				//성공시
				success: function(result) {
					console.log(result);

					$("p").text(result);

				}
				/*
				,
				error: function(request, status, error) {
					console.log(error);
				},
				//성공하든 실패하든 실행
				complete: function(){
					alert('ok');
				}
				*/

			});
			
				
		});
	});
	</script>	
	
</body>
</html>