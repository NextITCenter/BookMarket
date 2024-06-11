<%@page import="book.BookVO"%>
<%@page import="book.BookRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
BookRepository repository = new BookRepository();
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=pageContext.getServletContext().getContextPath() %>/css/bootstrap.min.css">
<title>도서 정보</title>
</head>
<body>
<body>
<div class="container py-4">
	<jsp:include page="fragments/header.jsp" />
	<main>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서 정보</h1>
				<p class="col-md-8 fs-4">BookInfo</p>
			</div>
		</div>
		<%
			String id = request.getParameter("id");
			BookRepository repository = BookRepository.getInstance();
			BookVO book = repository.getBook(id);
		%>
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<h3 class="fw-bold"><%=book.getTitle() %></h3>
				<p><%=book.getDescription() %></p>
				<p><%=book.getId() %></p>
				<p><%=book.getAuthor() %></p>
				<p><%=book.getPublisher() %></p>
				<p><%=book.getReleaseDate() %></p>
				<p><%=book.getCategory() %></p>
				<p><%=book.getQuantity() %></p>
				<p><%=book.getPrice() %></p>
				<p>
					<a href="#" class="btn btn-info">도서주문</a>
					<a href="${pageContext.request.contextPath }/books.jsp" class="btn btn-secondary">도서목록</a>
				</p>
			</div>
		</div>
	</main>
	<jsp:include page="fragments/footer.jsp" />
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</html>