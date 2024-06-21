<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css"><title>도서 정보</title>
</head>
<body>
<body>
<div class="container py-4">
	<jsp:include page="/WEB-INF/views/fragments/header.jsp" />
	<main>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서 정보</h1>
				<p class="col-md-8 fs-4">BookInfo</p>
			</div>
		</div>
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<h3 class="fw-bold">${book.title }</h3>
				<div class="row d-flex justify-content-center">
					<img class="col-md-3" src="${pageContext.request.contextPath }/books/image?id=${book.id}" alt="${book.title }의 도서 표지">
				</div>
				<p>${book.description }</p>
				<p>${book.id }</p>
				<p>${book.author}</p>
				<p>${book.publisher}</p>
				<p>${book.releaseDate}</p>
				<p>${book.category}</p>
				<p>${book.quantity}</p>
				<p>${book.price }</p>
				<p>
					<a href="#" class="btn btn-info">도서주문</a>
					
					<a href="${pageContext.request.contextPath }/books/update?id=${book.id }" class="btn btn-warning">수정</a>
					<a href="${pageContext.request.contextPath }/books/delete?id=${book.id }" class="btn btn-danger">삭제</a>
					
					<a href="${pageContext.request.contextPath }/books" class="btn btn-secondary">도서목록</a>
				</p>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>

</body>
</html>