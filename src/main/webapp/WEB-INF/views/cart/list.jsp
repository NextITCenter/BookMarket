<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
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
</body>
</html>






