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

<style type="text/css">
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px dotted red;
	}
</style>

</head>
<body>
	
	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//웹의 기본적인 이벤트 막기 (화면에 파일 드롭하면 그대로 열림)
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});

			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
				
				//드래그해온 파일들의 정보 변수에 담기
				var files = event.originalEvent.dataTransfer.files;
				//드래그해온 여러 파일들중 하나
				var file = files[0];
				//폼태그 기능 만들기
				var formData = new FormData(); //자바스크립트 기본클래스
				formData.append("file", file);

				$.ajax({
					type: 'post',
					url: "/uploadajax",
					dataType: 'text',
					data: formData,
					processData: false,
					contentType: false,					
					success: function(result){
						var str = "<div><a href = '#'>";
						str += getOriginalName(result);
						str += "</a></div>";
						//업로드된 파일 uploadList로 쌓이도록
						$(".uploadedList").append(str);
					},
					error: function(request, status, error){
						console.log(error);
					}
				});					
			});
				
		});

		function getOriginalName(fileName){
			if(checkImage(fileName)){
				var idx = fileName.indexOf("_"); //13
				idx = fileName.indexOf("_", idx + 1); //14
				return fileName.substring(idx + 1);
					//└s_이후의 언더바 뒤에있는 오리지널네임 반환				

			}else{
				var idx = fileName.indexOf("_");
				return fileName.substring(idx + 1);
					//└오리지널네임 반환
			} 
		}
		
		//이미지파일인지 확인
		function checkImage(fileName){
			
			var idx = fileName.lastIndexOf(".");
			var format = fileName.substring(idx + 1).toUpperCase();
			if(format == 'PNG' || format == 'JPEG' || format == 'JPG' || format == 'GIF'){
				return true;
			}else{
				return false;
			}
		}
		
	</script>
	
</body>
</html>