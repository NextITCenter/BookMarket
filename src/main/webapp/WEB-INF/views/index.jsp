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
	<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
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
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</div>

<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</body>
</html>


