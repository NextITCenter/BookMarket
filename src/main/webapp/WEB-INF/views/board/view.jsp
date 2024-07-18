<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
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
						<label class="input-group-text">첨부파일</label>
						<c:forEach items="${board.fileList}" var="file">
							<a href="/download/${file.id}">${file.originalName}</a>
						</c:forEach>
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
		<form action="/comments/new" method="post">
			<div class="row">
				<div class="col-5">
					<div class="form-floating">
						<textarea class="form-control" id="content" name="content"></textarea>
						<label for="content">Comments</label>
					</div>
				</div>
				<div class="col-2">
					<input type="hidden" id="boardNo" name="boardNo" value="${board.no}">
					<button type="button" id="insertBtn" class="btn btn-primary">등록</button>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="col-7">
				<div class="list-group" id="commentsArea">
					<c:forEach items="${board.commentList}" var="comment">
						<a href="#" class="list-group-item list-group-item-action" aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1">${comment.content}</h5>
								<small>${comment.writer}</small>
							</div>
							<p class="mb-1"></p>
							<small>${comment.registerDate}</small>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />
</div>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
<script>
	const contentArea = document.querySelector("#content")
	contentArea.addEventListener("focus", () => {
		console.dir(location);
		fetch("/sessionCheck?retUrl=" + location.pathname + location.search)
				.then(response => response.text())
				.then(result => {
					if (result != "exist") {
						location.href = result
					}
				});
	});


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
	const insertBtn = document.querySelector("#insertBtn");
	const commentsArea = document.querySelector("#commentsArea")
	insertBtn.addEventListener("click", () => {
		// Ajax 방식으로 데이터 전송
		// XMLHTTPRequest 객체 대신 jQuery 라이브러리의 $.ajax() 함수를 많이 사용
		// fetch라는 함수를 제공 (ES5 인지 ES6인지 헷갈림)
		fetch("/comments/new", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				content: document.querySelector("#content").value,
				boardNo: document.querySelector("#boardNo").value
			})
		})
				.then(response => response.json())
				.then(data => {
					// 1. 직접 자바스크립트를 이용해서 html 태그 생성
					// const aTag = document.createElement("a");
					// aTag.classList.add("list-group-item", "list-group-item-action")
					// const divTag = document.createElement("div");
					// const pTag = document.createElement("p");
					// const smallTag = document.createElement("small");
					// smallTag.textContent = data.registerDate;
					//
					// aTag.append(divTag, pTag, smallTag);
					//
					// const h5Tag = document.createElement("h5")
					// const smallInDivTag = document.createElement("small")
					//
					// h5Tag.textContent = data.content;
					// smallInDivTag.textContent = data.writer;
					//
					// divTag.append(h5Tag, smallInDivTag);
					//
					// commentsArea.appendChild(aTag);
					// 2. innerHTML 속성을 활용하는 방법
					// commentsArea.innerHTML +=
					// 		'<a href="#" class="list-group-item list-group-item-action" aria-current="true">' +
					// 		'<div class="d-flex w-100 justify-content-between">' +
					// 		`<h5 class="mb-1">\${data.content}</h5>` +
					// 		`<small>\${data.writer}</small>` +
					// 		'</div>' +
					// 		'<p class="mb-1"></p>' +
					// 		`<small>\${data.registerDate}</small>` +
					// 		'</a>';
					// 3. html에 숨김 화면으로 템플릿을 만들고 그 템플릿을 가져와서 사용
					const commentItem = document.querySelector("#commentTemplate").cloneNode(true);
					commentItem.querySelector("h5").textContent = data.content;
					commentItem.querySelector("div>small").textContent = data.writer;
					commentItem.querySelector("small.date").textContent = data.registerDate;
					commentsArea.appendChild(commentItem);
					const contentArea = document.querySelector("#content")
					contentArea.value = "";
				});
	})
</script>

<a href="#" id="commentTemplate" class="list-group-item list-group-item-action" aria-current="true">
	<div class="d-flex w-100 justify-content-between">
		<h5 class="mb-1"></h5>
		<small></small>
	</div>
	<p class="mb-1"></p>
	<small class="date"></small>
</a>

</body>
</html>






