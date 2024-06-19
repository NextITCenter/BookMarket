<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<div class="d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom">
			<a href="${pageContext.request.contextPath }/" class="d-flex align-items-center link-body-emphasis text-decoration-none">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-door-fill" viewBox="0 0 16 16">
					<path d="M6.5 14.5v-3.505c0-.245.25-.495.5-.495h2c.25 0 .5.25.5.5v3.5a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5"/>
				</svg>
				<span class="fs-4">Home</span>
			</a>
			<nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
				<a class="me-3 py-2 link-body-emphasis text-decoration-none" href='<c:url value="/books"/>'>도서 목록</a>
				<a class="py-2 link-body-emphasis text-decoration-none" href="${pageContext.request.contextPath }/books/insert">도서 등록</a>
			</nav>
		</div>
	</header>
	
	
	
	
	