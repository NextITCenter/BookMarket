<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css"></head>
</head>
<body>
<div class="container py-4">
	<jsp:include page="/WEB-INF/views/fragments/header.jsp" />
	<main>
		<div class="align-items-md-stretch">
			<div class="row my-3">
				<label class="col-md-2">제목</label>
				<div class="col-md-5">
					<input type="text" name="title" readonly="readonly" class="form-control" value="${board.title }">
				</div>
			</div>
			<div class="row my-3">
				<label class="col-md-2">내용</label>
				<div class="col-md-5">
					<textarea name="content" class="form-control" readonly rows="7">${board.content }</textarea>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-7 d-grid">
					<div class="input-group">
						<label class="input-group-text" for="files">첨부파일</label>
						<input type="file" class="form-control" id="files" name="files">
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-7 text-center">
					<a href="${pageContext.request.contextPath }/boards/update?no=${board.no}" class="btn btn-primary">수정</a>
					<a href="#" data-url="${pageContext.request.contextPath }/boards/delete?no=${board.no}" id="deleteBtn" class="btn btn-primary">삭제</a>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
<script>
	// 보통 자바스크립트에서 원하는 요소(Element)를 선택해서 가져올 때 id, name, class, tagname 등을
	// 사용해서 가져온다. 그 중에서 id는 크롬 브라우저에서 상수값으로 자동으로 등록해준다.
// 	const deleteBtn = document.querySelector("deleteBtn");
	deleteBtn.addEventListener("click", (e) => {
		if (confirm("선택한 게시글을 삭제하시겠습니까?")) {
			location.href = e.target.dataset.url
		} else {
			alert("삭제를 취소합니다.");
		}
	})
</script>
</body>
</html>






