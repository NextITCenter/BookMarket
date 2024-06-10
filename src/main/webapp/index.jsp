<%@page import="java.util.Calendar"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=pageContext.getServletContext().getContextPath() %>/css/bootstrap.min.css">
</head>
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
				<h1 class="display-5 fw-bold">도서 쇼핑몰에 오신 것을 환영합니다.</h1>
				<p class="col-md-8 fs-4">BookMarket</p>
			</div>
		</div>
		<div class="row align-items-md-stretch text-center">
			<h2>Welcome to Web Market!</h2>
			<h5>현재 접속 시각:
				<%=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh:mm:ss a")) %>
			</h5>
		</div>
	</main>
	<footer class="pt-3 mt-4 text-body-secondary border-top">
		© 2024 BookMarket
	</footer>
</div>


<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</body>
</html>


