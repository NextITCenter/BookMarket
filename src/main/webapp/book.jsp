<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<header class="pb-3 mb-4 border-bottom">
		<a href="/" class="d-flex align-items-center text-body-emphasis text-decoration-none">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-door-fill" viewBox="0 0 16 16">
				<path d="M6.5 14.5v-3.505c0-.245.25-.495.5-.495h2c.25 0 .5.25.5.5v3.5a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5"/>
			</svg>
			<span class="fs-4">Home</span>
		</a>
	</header>
	<main>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서 정보</h1>
				<p class="col-md-8 fs-4">BookInfo</p>
			</div>
		</div>
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<h3 class="fw-bold">책 제목</h3>
				<p>상세 설명</p>
				<p>도서 코드</p>
				<p>저자</p>
				<p>출판사</p>
				<p>출판일</p>
				<p>분류</p>
				<p>재고수</p>
				<p>가격</p>
				<p>
					<a href="#" class="btn btn-info">도서주문</a>
					<a href="${pageContext.request.contextPath }/books.jsp" class="btn btn-secondary">도서목록</a>
				</p>
			</div>
		</div>
	</main>
	<footer class="pt-3 mt-4 text-body-secondary border-top">
		© 2024 BookMarket
	</footer>
</div>

<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>

</body>
</html>