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

	<div class="container">
		<div class="row text-center">
			<h1>글 수정</h1>
		</div>
		
		<div class="row">
		<form action="/board/update" method="post">
			<input type="hidden" name="bno" value="${vo.bno }">
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" name="title" id="title" value="${vo.title }">
			</div>		
			
			<div class="form-group">
				<label for="writer">작성자</label>
				<input class="form-control" name="writer" id="writer" value="${vo.writer }">
			</div>
			
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" name="content" id="content" rows="5">${vo.content }</textarea>
			</div>			
		</form>
		</div>
		
		<div class="row">
			<div class="form-group">
				<button class="btn btn-primary" id="update_btn">수정</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#update_btn").click(function(){
				$("form").submit();
			});
			$("#list_btn").click(function(){
				location.assign("/board/list");
			});
		});
	</script>
	
</body>
</html>