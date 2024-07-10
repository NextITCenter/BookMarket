<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css"></head>
</head>
<body>
<div class="container py-4">
	<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
	<main>
		<a href="${pageContext.request.contextPath }/boards/insert" class="btn btn-primary">게시글 등록</a>
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${boards }" var="board">
			<tr>
				<td>${board.no }</td>
				<td><a href="${pageContext.request.contextPath }/boards/view?no=${board.no}">${board.title }</a></td>
				<td>${board.name }</td>
				<td>${board.registerDate }</td>
				<td>${board.hits }</td>
			</tr>
			</c:forEach>
		</table>
		<nav>
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<c:choose>
						<c:when test="${pagination.currentPageNo > 1}">
							<a class="page-link" href="${pageContext.request.contextPath }/boards?requestPageNo=${pagination.currentPageNo - 1}" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="#" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
				<!-- 한 화면에 보여줄 페이지 갯수만큼 반복 -->
				<!--
				for (int i=0;i<10;i++) {}
				for (String str: strs) {}
				 -->
				<c:forEach begin="${pagination.firstPageNoOnPageList}" end="${pagination.lastPageNoOnPageList}" var="pageNo">
					<li class="page-item">
						<a class="page-link ${pagination.currentPageNo == pageNo?'active':''}" href="${pageContext.request.contextPath }/boards?requestPageNo=${pageNo}">
							${pageNo}
						</a>
					</li>
				</c:forEach>

				<li class="page-item">
					<c:choose>
						<c:when test="${pagination.currentPageNo >= pagination.totalPageCount}">
							<a class="page-link" href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="${pageContext.request.contextPath }/boards?requestPageNo=${pagination.currentPageNo + 1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</nav>
	</main>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</html>