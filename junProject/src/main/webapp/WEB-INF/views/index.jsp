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
<title>5일장에 오신걸 환영합니다</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="resources/css/styles.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.navbar-nav .nav-item a:hover {
	background-color: lightgray;
	padding: 10px; /* 원하는 크기로 조정 */
	border-radius: 4px; /* 원하는 크기로 조정 */
}

.card-img-top {
    object-fit: cover;  /* 이미지가 컨테이너를 꽉 채우면서 비율을 유지하도록 합니다. */
    width: 374px;  /* 이미지의 가로 폭을 설정합니다. */
    height: 265px;  /* 이미지의 세로 높이를 설정합니다. */
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
		<div class="container px-4 px-lg-5">
			<a href="main">
				  <img src="resources/assets/img/logo.png" class="navbar-brand" style="width: 120px; height: 80px;">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					
					<li class="nav-item"><a class="nav-link" href="boardSelectAll">공지사항</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#!"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">상품목록</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="goodsList">모든 상품</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="goodsList">인기 상품</a></li>
							<li><a class="dropdown-item" href="goodsList">새로 나온 상품</a></li>
						</ul></li>
				</ul>
			</div>
			<div class="collapse navbar-collapse" id="navbarResponsive">
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
	
	<!-- Header-->
<header class="bg-black py-5" style="height: 300px; position: relative;">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row">
            <div class="col-12 col-lg-6 mx-auto">
                <div id="banner-container" style="position: absolute; top: 0; bottom: 0; left: 50%; transform: translateX(-50%); overflow: hidden;">
                    <img id="banner-image" src="resources/assets/img/home-bg2.jpg" alt="Your Image" style="width: 100%; height: 100%; object-fit: cover;">
                </div>
                <a class="carousel-control-prev" href="#" role="button" data-bs-target="#banner-carousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </a>
                <a class="carousel-control-next" href="#" role="button" data-bs-target="#banner-carousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </a>
            </div>
        </div>
    </div>
</header>

	<!-- Section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-2 row-cols-xl-3 justify-content-center">
				<c:forEach var="goods" items="${goodsList}" begin="0" end="5"> 
				<div class="col mb-5">
					<div class="card h-100">
					<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Sale</div>
						<!-- Product image-->
						<img class="card-img-top" src="/project/resources/upload/${goods.image}" alt="..." />
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name-->
								<h5 class="fw-bolder">${goods.title}</h5>
								<!-- Product reviews-->
								<div class="d-flex justify-content-center small text-warning mb-2">
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
								</div>
								<!-- Product price-->
								<span class="text-center">${goods.price} 원  </span>
							</div>
						</div>
						<!-- Product actions-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="goodsSelect?goodsid=${goods.goodsid}">상세 보기</a>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
				
				
				<!-- <div class="col mb-5">
					<div class="card h-100">
						Sale badge
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Sale</div>
						Product image
						<img class="card-img-top"
							src="resources/assets/img/iphone.png" alt="..." />
						Product details
						<div class="card-body p-4">
							<div class="text-center">
								Product name
								<h5 class="fw-bolder">아이뻐 14</h5>
								Product reviews
								<div class="d-flex justify-content-center small text-warning mb-2">
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
								</div>
								Product price
								<span class="text-muted text-decoration-line-through">$20.00</span>
								$18.00
							</div>
						</div>
						Product actions
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="#">상세 보기</a>
							</div>
						</div>
					</div>
				</div> -->
			
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="resources/js/scripts.js"></script>
	
	<!-- 배너 이동 -->
	<script>
    var currentImageIndex = 0;
    var images = [
    	 "resources/assets/img/home-bg2.jpg",
    	 "resources/assets/img/home-bg3.jpg",
    	 "resources/assets/img/home-bg4.jpg",	
    ];
    var bannerImage = document.getElementById("banner-image");

    function showNextImage() {
        currentImageIndex++;
        if (currentImageIndex >= images.length) {
            currentImageIndex = 0;
        }
        bannerImage.src = images[currentImageIndex];
    }

    function showPreviousImage() {
        currentImageIndex--;
        if (currentImageIndex < 0) {
            currentImageIndex = images.length - 1;
        }
        bannerImage.src = images[currentImageIndex];
    }
    setInterval(showNextImage, 7000);

    var prevButton = document.querySelector(".carousel-control-prev");
    var nextButton = document.querySelector(".carousel-control-next");
    prevButton.addEventListener("click", showPreviousImage);
    nextButton.addEventListener("click", showNextImage);
</script>
</body>
</html>
