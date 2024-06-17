<%@page import="book.BookVO"%>
<%@page import="java.util.List"%>
<%@page import="book.BookRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>도서 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
</head>
<body>
<div class="container py-4">
	<%@include file="/WEB-INF/views/fragments/header.jsp" %>
	<main>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서 목록</h1>
				<p class="col-md-8 fs-4">BookList</p>
			</div>
		</div>
		<!-- 자바의 BookRepository 클래스에서 getBooks()호출 -->
		<%
			BookRepository repository = BookRepository.getInstance();
			List<BookVO> books = repository.getBooks();
		%>
		<div class="row align-items-md-stretch text-center">
			<%
			for (BookVO book: books) {
			%>
			<div class="col-md-4">
				<div class="h-100 p-2">
					<h5 class="fw-bold"><%=book.getTitle() %></h5>
					<p><%=book.getAuthor() %></p>
					<p><%=book.getPublisher() %> | <%=book.getPrice() %></p>
					<p><%=book.getDescription() %></p>
					<p><%=book.getPrice() %></p>
					<p><a href="${pageContext.request.contextPath }/book.jsp?id=<%=book.getId() %>" class="btn btn-secondary">상세정보 &raquo;</a></p>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</div>

<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</html>