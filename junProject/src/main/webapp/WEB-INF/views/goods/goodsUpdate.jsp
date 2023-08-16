<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userid = (String) session.getAttribute("userid");
	Integer uniqueid = (Integer) session.getAttribute("uniqueid");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>상품 등록 수정</title>

<!-- 글쓰기 에디터 -->
<script src="/resources/ckeditor/ckeditor.js"></script>


<!-- Bootstrap CSS-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<!-- Core theme CSS (includes Bootstrap)-->
<link href="/resources/css/styles.css" rel="stylesheet" />

<style>
.navbar-nav .nav-item a:hover {
	background-color: lightgray;
	padding: 10px; /* 원하는 크기로 조정 */
	border-radius: 4px; /* 원하는 크기로 조정 */
}

</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
		<div class="container px-4 px-lg-5">
			<a href="/main">
				  <img src="/resources/assets/img/logo.png" class="navbar-brand" style="width: 120px; height: 80px;">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					
					<li class="nav-item"><a class="nav-link" href="/main">공지사항</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#!"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">상품목록</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/goods">모든 상품</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="/goods">인기 상품</a></li>
							<li><a class="dropdown-item" href="/goods">새로 나온 상품</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<%
						if (userid == null) {
					%>
					<!-- 로그아웃 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/signup">회원가입</a></li>
					<%
						} else {
					%>
					<!-- 로그인 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/mypage">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/orderCheck">주문조회</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/cart">장바구니 <span
							class="badge bg-dark text-white ms-1 rounded-pill">0</span></a></li>
					<%
						}
					%>

				</ul>
			</div>
		</div>
	</nav>
	<!-- Header -->
	<header class="bg-dark py-5"
		style="background-image: url('resources/assets/img/home-bg.jpg')">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-black">
				<h1 class="display-4 fw-bolder">상품 수정하기</h1>

			</div>
		</div>
	</header>

	<!-- Section -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-2 mb-5 d-flex justify-content-center">
			<form method="post" id="goodsForm" enctype="multipart/form-data">
			<h3>상품 수정</h3>
			<hr class="mb-5">
				<!-- 제목 입력 -->
				<div class="mb-3">
					<label for="title" class="form-label">제목</label>
					<input type="text" class="form-control" id="title" name="title" value="${goodsDTO.title}" placeholder="제목을 입력하세요." required>
				</div>
				<div class="row">
					  <div class="col-md-6 mb-3">
					    <label for="price" class="form-label">가격</label>
					    <input type="text" class="form-control" id="price" name="price" value="${goodsDTO.price}" placeholder="숫자만 입력하세요" required>
					  </div>
					  <div class="col-md-6 mb-3">
					    <label for="origin" class="form-label">원산지</label>
					    <input type="text" class="form-control" id="origin" name="origin" value="${goodsDTO.origin}" placeholder="원산지를 입력해주세요" required>
					  </div>
				</div>
				<div class="row">
			        <div class="col-md-6 mb-3">
			          <label for="parcel" class="form-label">배송여부</label>
			          <select class="form-select" id="parcel" name="parcel" onchange="toggleShipPriceField()">
			            <option value="free">무료배송</option>
			            <option value="paid">유료배송</option>
			          </select>
			         </div>
			          <div class="col-md-6 mb-3" id="shipFee" style="display: none;">
					    <label for="shipprice" class="form-label">배송비</label>
					    <input type="text" class="form-control" id="shipprice" name="shipprice" value="${goodsDTO.shipprice}" placeholder="숫자만 입력">
					  </div>
      			</div>
				<textarea id="editor1" name="content">
					${goodsDTO.content}
				</textarea>
				<div class="mb-3">
				    <label for="imageFile" class="form-label">이미지 파일</label>
				    <input type="file" class="form-control" id="imageFile" name="imageFile">
				</div>
				<br>
				<div class="mt-2 d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary me-3">상품 등록</button>
                        <button type="reset" class="btn btn-secondary">취소</button>  
                </div> 
			</form>
		</div>
	</section>

	<script>
		CKEDITOR.replace('editor1',{
			height: 500,
			filebrowserUploadUrl: '${pageContext.request.contextPath}/adm/fileupload.do' // 파일 업로드를 처리하는 URL 주소 설정,
			
		});
		// 폼 제출 시 CKEditor 내용을 반영
		document.querySelector('form').addEventListener('submit', function() {
			var editorData = CKEDITOR.instances.editor1.getData();
			document.getElementById('editor1').value = editorData;
		});
		
		 function toggleShipPriceField() {
			    var shippingSelect = document.getElementById("parcel");
			    var shipPriceField = document.getElementById("shipFee");
			   
			    if (shippingSelect.value === "paid") {
			      shipPriceField.style.display = "block";
			     
			    } else {
			      shipPriceField.style.display = "none";
			    
			    }
			  }
	</script>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">© Your Website 2023. All rights reserved.</p>
		</div>
	</footer>

	<!-- Bootstrap core JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
