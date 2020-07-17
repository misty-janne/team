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
			<h1>글 자세히 보기</h1>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label for="title">제목</label>
				<input readonly class="form-control" value="${vo.title }">
			</div>		
			
			<div class="form-group">
				<label for="writer">작성자</label>
				<input readonly class="form-control" value="${vo.writer }">
			</div>
			
			<div class="form-group">
				<label for="content">내용</label>
				<textarea readonly class="form-control" rows="5">${vo.content }</textarea>
			</div>			
		</div>
		
		<div class="row">
			<div class="form-group">
				<button class="btn btn-warning" id="reply_form">댓글</button>
				<button class="btn btn-primary" id="update_btn">수정</button>
				<button class="btn btn-danger" id="delete_btn">삭제</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div><!-- class = row (btn) -->
		
		<div class="row">
			<div class="collapse" id="myCollapse">
				<div class="form-group">
					<label for="replyer">작성자</label>
					<input class="form-control" id="replyer">
				</div>
				<div class="form-group">
					<label for="replytext">내용</label>
					<input class="form-control" id="replytext">
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary" id="replyInsert_btn">댓글 등록</button>
				</div>
			</div>
		</div><!-- class = row (clps) -->
		
		<div id="replies" class="row">
			<div class="panel panel-success">
  				<div class="panel-heading">
					<span hidden>no: 3</span>, 
					<span>작성자: 홍길동</span>, 
					<span class="pull-right">2020년07월07일</span> 
  				</div>
  				<div class="panel-body">
					<p>댓글 내용</p>
					<button data-name="홍길동" class="btn btn-primary btn-xs">수정</button> 
					<button class="btn btn-danger btn-xs">삭제</button>
  				</div>
			</div>
		</div><!-- class = row (rpls list) -->
		
		<div class="row">
			<div data-backdrop="static" id="myModal" class="modal fade" tabindex="-1" role="dialog">
  				<div class="modal-dialog" role="document">
    				<div class="modal-content">
      					<div class="modal-header">
        					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        					<h4 class="modal-rno">rno 데이터</h4>
      					</div>
      					<div class="modal-body">
        					<p class="modal-replyter">홍길동</p>
        					<input value="댓글내용입니다." id="modal-replytext" class="form-control" />
      					</div>
      					<div class="modal-footer">
        					<button type="button" class="btn btn-primary modal-update-btn">
        						댓글수정
        					</button>
      					</div>
    				</div><!-- /.modal-content -->
  				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		</div><!-- class = row (modal) -->
		
	</div><!-- class = container -->
	
	<script type="text/javascript">		
	
		var bno = ${vo.bno};

		getList(bno);
		
		$(document).ready(function(){

			//수정버튼
			//정적인 조상태그
			$("#replies").on("click", ".replymodify", function(){
				//지금 클릭한 요소
				var rno = $(this).attr("data-rno");
				var replyer = $(this).attr("data-name");
				var replytext = $(this).prev().text(); //text(): get
									//prev(): 앞에있는 태그 <p>태그
				$(".modal-rno").text(rno); //text(~): set
				$(".modal-replyer").text(replyer);
				$("#modal-replytext").val(replytext); //input 태그이므로
				
				$("#myModal").modal("show");
			});
			//삭제버튼
			$("#replies").on("click", ".replydelete", function(){
				var rno = $(this).attr("data-rno");

				$.ajax({
					type: 'delete',
					url: "/replies",
					headers: {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType: 'text',
					data: JSON.stringify({
						rno : rno
					}),
					success: function(result){
						getList(bno);
					},
					error: function(request, status, error){
						console.log(error);
					}
				});

			});

			//수정 수행
			$(".modal-update-btn").click(function(){
				var rno = $(".modal-rno").text();
				var replytext = $("#modal-replytext").val();

				$.ajax({
					type: 'put', //수정시 put
					url: "/replies/"+rno,
					headers: {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					dataType: 'text',
					data: JSON.stringify({
						replytext : replytext
					}),
					success: function(result){
						//=== : 값,타입 완전히 일치
						if(result === "success"){
							getList(bno); //댓글 리스트 갱신
							$("#myModal").modal("hide");
						}
					},
					error: function(request, status, error){
						console.log(error);
					}
				});
				
			});

			
			$("#replyInsert_btn").click(function(){
				var replyer = $("#replyer").val();
				var replytext = $("#replytext").val();

				$.ajax({
					type: 'post',
					url: '/replies',
					headers: {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType: "text",
					data: JSON.stringify({
						bno : bno, //위의 var bno
						replyer : replyer, // var replyer
						replytext : replytext // var replytext
					}),
					success: function(result){
						$("#replyer").val(""); //댓글입력후 초기화
						$("#replytext").val(""); //댓글입력후 초기화

						getList(bno); //댓글입력후 리스트 불러오기
					},
					error: function(request, status, error){
						console.log(error);
					}
				});
					
			});
			
			$("#reply_form").click(function(){
				$("#myCollapse").collapse("toggle");
			});
			
			$("#update_btn").click(function(){
				location.assign("/board/update/${vo.bno}");
			});
			$("#delete_btn").click(function(){
				location.assign("/board/delete/${vo.bno}");
			});
			$("#list_btn").click(function(){
				location.assign("/board/list");
			});
		});

		//글자세히보기 실행시 자동실행
		function getList(bno){

			var str = '';
			
			$.getJSON("/replies/all/"+bno, function(data){
				for(var i=0; i<data.length; i++){					
					str += '<div class="panel panel-success"><div class="panel-heading"><span hidden>no: '+data[i]["rno"]+'</span><span>작성자: '+data[i]["replyer"]+'</span> <span class="pull-right">'+data[i]["updatedate"]+'</span></div><div class="panel-body"><p>'+data[i]["replytext"]+'</p><button data-name="'+data[i]["replyer"]+'" data-rno="'+data[i]["rno"]+'" class="btn btn-primary btn-xs replymodify">수정</button> <button data-rno="'+data[i]["rno"]+'" class="btn btn-danger btn-xs replydelete">삭제</button></div></div>';
				}
				//위의 태그를 for문 함수 결과값으로 바꾸기
				$("#replies").html(str);
			});
			
		}
	</script>
	
</body>
</html>