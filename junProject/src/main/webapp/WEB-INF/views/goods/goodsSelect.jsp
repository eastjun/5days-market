<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% String userid = (String) session.getAttribute("userid"); %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop Homepage </title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />

<style>
.navbar-nav .nav-item a:hover {
	background-color: lightgray;
	padding: 10px;
	border-radius: 4px;
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="mainView">사이트 명</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="mainView">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="boardSelectAll">Notice</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="goodsList">All
									Products</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">Popular Items</a></li>
							<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<% if (userid == null) { %>
            <!-- 로그아웃 상태일 때 -->
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="login">로그인</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="signup">회원가입</a></li>
        <% } else { %>
            <!-- 로그인 상태일 때 -->
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="logout">로그아웃</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="mypage">마이페이지</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="orderCheck">주문조회</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="cart">장바구니 <span class="badge bg-dark text-white ms-1 rounded-pill">0</span></a></li>
        <% } %>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Section-->
	<section class="py-5 mt-5">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<img src="/project/resources/upload/${goodsDTO.image}" alt="상품 이미지" class="img-fluid"
						style="max-width: 500px; max-height: 500px;">
				</div>
				<div class="col-md-6">
					<h2 class="fw-bold">${goodsDTO.title}</h2>
					 	<div class="text-end">
							<small class="text-muted">판매자: ${goodsDTO.userid}</small>
						</div>
					<hr>
					<p class="fs-5">가격: ${goodsDTO.price} 원</p>	
					<hr>
					<p>원산지: ${goodsDTO.origin }</p>
					<hr>
					<p>배송: 
						  <c:choose>
						      <c:when test="${goodsDTO.parcel eq 'free'}">무료배송</c:when>
						      <c:otherwise>  ${goodsDTO.shipprice } 원</c:otherwise>
						  </c:choose>
					</p>
					<hr>
					<form>
						<div class="mb-4">
							<label for="quantity" class="form-label">상품 갯수</label>
							<div class="input-group" style="width: 120px;">
								<button type="button" class="btn btn-outline-primary"
									id="decreaseBtn">-</button>
								<input type="text" class="form-control text-center"
									id="quantity" name="quantity" value="1">
								<button type="button" class="btn btn-outline-primary"
									id="increaseBtn">+</button>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">장바구니에 추가</button>
						<button type="submit" class="btn btn-success">구매하기</button>
						<c:choose>
							<c:when test="${goodsDTO.userid eq userid}">
								<a class="btn btn-warning" href="goodsUpdate?goodsid=${goodsDTO.goodsid}">수정하기</a>
								<a class="btn btn-danger" id="deleteButton" href="#">삭제하기</a>
								
							</c:when>
						</c:choose>
					</form>
				</div>
				<script>
					document.getElementById('decreaseBtn').addEventListener(
							'click',
							function() {
								const quantityInput = document
										.getElementById('quantity');
								let quantity = parseInt(quantityInput.value);
								if (quantity > 1) {
									quantity--;
									quantityInput.value = quantity;
								}
							});

					document.getElementById('increaseBtn').addEventListener(
							'click',
							function() {
								const quantityInput = document
										.getElementById('quantity');
								let quantity = parseInt(quantityInput.value);
								quantity++;
								quantityInput.value = quantity;
							});
					//삭제 버튼
					document.getElementById('deleteButton').addEventListener(
							'click',
							function(event) {
								const result = confirm('정말로 이 상품을 삭제하시겠습니까?');
								if (result) {
									window.location.href = "goodsDelete?goodsid=${goodsDTO.goodsid}";
								} else {
									event.preventDefault();
								}
							});
				</script>
			</div>
		</div>
	</section>

	<!-- Banner-->
	<section class="py-5">
		<div class="container">
			<h2>상품 설명</h2>
			<hr>
			${goodsDTO.content }
			</div>
	</section>

	<!-- User Reviews -->
<section class="py-5">
    <div class="container">
        <div class="border-bottom mb-3">
            <h2>사용자 후기</h2>
        </div>
        <div class="review-list">
            <!-- 리뷰 목록 표시 -->
            <div class="review-item">
                <div class="review-content-wrapper">
                    <p class="review-content">리뷰 내용 1</p>
                    <p class="review-username">- 사용자1</p>
                </div>
            </div>
            <hr class="review-separator"> <!-- 구분선 추가 -->
            <div class="review-item">
                <div class="review-content-wrapper">
                    <p class="review-content">리뷰 내용 2</p>
                    <p class="review-username">- 사용자2</p>
                </div>
            </div>
            <!-- 추가적인 리뷰 목록 -->
        </div>
    </div>
</section>


	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2023</p>
		</div>
	</footer>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
