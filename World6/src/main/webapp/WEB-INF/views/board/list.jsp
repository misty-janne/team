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
<style>
	.th-center{
		text-align: center;
	}
</style>
<body>

	<div class="container">
		<div class="row text-center">
			<h1>목록</h1>
		</div>
		
		<div class="row">
			<button class="btn btn-primary" onclick="location.href='/board/insert'">글쓰기</button>
		</div>
		
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th class="th-center">글번호</th>
						<th class="th-center" style="width: 50%">제목</th>
						<th class="th-center">작성자</th>
						<th class="th-center">작성일</th>
						<th class="th-center">조회수</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.bno }</td>
							<td>
								<a href="/board/read/${dto.bno }">${dto.title }</a>
							</td>
							<td>${dto.writer }</td>
							<td>
								<c:choose>
								<c:when test="${fn:length(dto.regDate)>10 }">
									<c:out value="${fn:substring(dto.regDate,0,10)}" />
								</c:when>
								</c:choose>
							</td>
							<td>${dto.viewCnt }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div><!-- class = row (table) -->
		<div class="row text-center">
		<nav aria-label="Page navigation">
  		<ul class="pagination">
    		<li>
      		<a href="/board/list?curPage=${to.curPage > 1 ? to.curPage-1 : 1 }" aria-label="Previous">
        		<span aria-hidden="true">&laquo;</span>
      		</a>
    		</li>
    		<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="page">
    			<li class="${to.curPage == page? 'active' : '' }">
    			<a href="/board/list?curPage=${page}">${page }</a>
    			</li>
    		</c:forEach>
			<li>
      		<a href="/board/list?curPage=${to.curPage < to.totalPage ? to.curPage+1 : to.curPage }" aria-label="Next">
        		<span aria-hidden="true">&raquo;</span>
      		</a>
    		</li>
  		</ul>
		</nav>
		</div><!-- class = row (page) -->
		
		<div class="row" style="padding:0% 25%">
			<!-- 검색 시, 별도의 탭에서 열리도록 _blank -->
			<form target="_blank" action="/board/searchlist" method="get">
				<div class="input-group">
					<span class="input-group-addon">
						<select name="searchType">
							<option disabled>검색 기준</option>
							<option value="title">제목</option>
							<option value="writer">글쓴이</option>
							<option value="content">내용</option>
						</select>
					</span>					
					<input class="form-control" name="keyword">
					<span class="input-group-btn">
						<button class="btn btn-info">검색</button>
					</span>
				</div>
			</form>
		
		</div>
		
	</div><!-- class = container -->

</body>
</html>