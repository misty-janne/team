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
			//삭제아이콘 클릭시 리스트에서 삭제
			$(".uploadedList").on("click", ".deletefile", function(event){
				event.preventDefault();
				var that = $(this); //지금 클릭하고있는 대상 (통신종료전 미리 지정)

				$.ajax({
					type: 'post',
					url: '/deletefile',
					dataType: 'text',
					data: {
						//result = 파일이름(이미지는 썸네일이름) 이므로
						fileName : $(this).attr("href") //href에 들어가는 result
					},
					success : function(result){
						that.parent("div").remove();
						alert(result);
					}

				});

			});
			
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
						//a 링크 클릭시 파일 열기
//						var str = "<div><a href = '#'>";
						var str = "<div><a href = '/displayfile?fileName=" + getImgLink(result) + "'>";

						//썸네일이미지 삽입
						if(checkImage(result)){
							//이미지파일이면: result = 썸네일이미지 (원본의 100*100)
							str += "<img src = '/displayfile?fileName=" + result + "'/>";
						}else{
							//이미지파일이 아니면
							str += "<img src = '/resources/et.png'/>";
						}
						
						str += getOriginalName(result);
						//삭제아이콘
//						str += "</a><a><span class='glyphicon glyphicon-trash'></span></a></div>";
						//첨부파일 삭제
						str += "</a><a class='deletefile' href='" + result 
										+ "'><span class='glyphicon glyphicon-trash'></span></a></div>";
						//업로드된 파일 uploadList로 쌓이도록
						$(".uploadedList").append(str);
					},
					error: function(request, status, error){
						console.log(error);
					}
				});					
			});				
		});

		//원본이미지파일 주소 출력 함수("s_" 제외)
		function getImgLink(result){
//			/2020/07/10/s_xxxxxxx_a.jpg
			if(checkImage(result)){
				//이미지파일의 경우
				return result.substring(0,12) + result.substring(14);
//			/2020/07/10/ + xxxxxxx_a.jpg				
			}else{
				//이미지파일이 아니면
				return result;	
			}
		}
		
		//오리지널네임 받는 함수
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