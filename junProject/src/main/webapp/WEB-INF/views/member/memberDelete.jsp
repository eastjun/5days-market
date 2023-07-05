<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userid = (String) session.getAttribute("userid");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>My Page</title>
<!-- Bootstrap CSS-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<style>
.navbar-nav .nav-item a:hover {
	background-color: lightgray;
	padding: 10px; /* 원하는 크기로 조정 */
	border-radius: 4px; /* 원하는 크기로 조정 */
}
</style>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="./GoodsListView.do">All
									Products</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#">Popular Items</a></li>
							<li><a class="dropdown-item" href="#">New Arrivals</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<%
						if (userid == null) {
					%>
					<!-- 로그아웃 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="./MemberLoginView.do">로그인</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="./Sign-upView.do">회원가입</a></li>
					<%
						} else {
					%>
					<!-- 로그인 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="mypage">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="./OrderCheckView.do">주문조회</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="./CartView.do">장바구니 <span
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
		style="background-image: url('assets/img/home-bg.jpg')">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-black">
				<h1 class="display-4 fw-bolder">마이 페이지</h1>
				<p class="lead fw-normal text-black-50 mb-0">최근 주문 내역</p>
			</div>
		</div>
	</header>

	<!-- Section -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<!-- 좌측 메뉴 -->
			<div class="row">
				<div class="col-lg-3 mb-4">
					<!-- 주문 그룹 -->
					<div>
						<h4 class="list-group-item fw-bolder">주문</h4>
						<div class="list-group">
							<a href="#"
								class="list-group-item list-group-item-action border-0">-
								주문목록/배송조회</a> <a href="#"
								class="list-group-item list-group-item-action border-0">-
								취소/반품</a> <a href="#"
								class="list-group-item list-group-item-action border-0">-
								환불내역</a>
						</div>
					</div>
					<!-- 고객 센터 -->
					<div class="mt-4">
						<h4 class="list-group-item fw-bolder">고객센터</h4>
						<div class="list-group">
							<a href="#"
								class="list-group-item list-group-item-action border-0">-
								1:1문의</a>
						</div>
					</div>
					<!-- 회원정보 -->
					<div class="mt-4">
						<h4 class="list-group-item fw-bolder">회원정보</h4>
						<div class="list-group">
							<a href="memberUpdate" class="list-group-item list-group-item-action border-0">-회원정보변경</a>
							<a href="memberDelete" class="list-group-item list-group-item-action border-0">-회원탈퇴</a>
							<a href="#"	class="list-group-item list-group-item-action border-0">-배송지수정</a>
						</div>
					</div>
				</div>

				<!-- 회원탈퇴 -->
				<div class="col-lg-6">
					<h2 class="fw-bolder">회원 탈퇴</h2>
					<form action="memberDelete" method="post" class="mt-4">
						<div class="form-group">
							<label for="userid">사용자 ID</label> <input type="text" class="form-control" id="userid" name="userid"
								value="${userid}" readonly>
						</div>
						<div class="form-group">
							<label for="password">비밀번호</label> 
							<input type="password"	class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요.">
								</div>	
								<div class="form-group">
            <label>정말 탈퇴하시겠습니까?</label><br>
            <button type="submit" name="confirm" value="yes" class="btn btn-primary mt-3"> 예 </button>
            <button type="submit" name="confirm" value="no" class="btn btn-primary mt-3"> 아니오 </button>
        </div>

					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">© Your Website 2023. All
				rights reserved.</p>
		</div>
	</footer>

	<!-- Bootstrap core JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
