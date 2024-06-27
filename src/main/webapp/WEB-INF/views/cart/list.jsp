<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css"></head>
<body>
<div class="container py-4">
	<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
	<main>
		<table class="table">
			<tr>
				<th>번호</th>
				<th>도서명</th>
				<th>가격</th>
				<th>수량</th>
				<th>합계</th>
			</tr>
			<c:forEach items="${sessionScope.carts }" var="cart">
			<tr>
				<td>${cart.no }</td>
				<td>${cart.book.title }</td>
				<td>${cart.book.price }</td>
				<td>${cart.quantity }</td>
				<td>${cart.book.price * cart.quantity }</td>
			</tr>
			</c:forEach>
		</table>
	</main>
	<a href="${pageContext.request.contextPath }/books" class="btn btn-primary">도서 계속 구매하기</a>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
</body>
</html>






