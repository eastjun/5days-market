<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String userid = (String) session.getAttribute("userid"); %>
    <% Integer uniqueid = (Integer) session.getAttribute("uniqueid"); %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
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
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="mainView">사이트 명</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="mainView">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="boardSelectAll">Notice</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="goodsList">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="goodsList">Popular Items</a></li>
                                <li><a class="dropdown-item" href="goodsList">New Arrivals</a></li>
                            </ul>
                        </li>
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
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="./OrderCheckView.do">주문조회</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="cart">장바구니 <span class="badge bg-dark text-white ms-1 rounded-pill">0</span></a></li>
             
        
        
        <% } %>
                        </ul>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5" style="background-image: url('resources/assets/img/home-bg.jpg')">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-black">
                    <h1 class="display-4 fw-bolder">상품 페이지</h1>
                    <p class="lead fw-normal text-black-50 mb-0">여름 맞이 세일</p>
                </div>
            </div>
        </header>
        <!-- Section-->
			<section class="py-3">
			    <div class="container px-4 px-lg-5 mt-5">
			        <div class="d-flex justify-content-end">
			            <%if (uniqueid != null && uniqueid>=100000){ %>
			                <a class="btn btn-primary me-3 mb-3" href="goodsInsert">상품 등록</a>
			            <%} %>
			        </div>
			        <div class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-1 row-cols-xl-1 justify-content-center">
			            <!-- Product Card -->
			            <c:forEach var="goods" items="${goodsList}">
			                <div class="card mb-3">
			                    <div class="row g-0">
			                        <div class="col-md-4" style="height: 200px;">
			                            <!-- Product image-->
			                            <img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" class="img-fluid rounded-start" alt="..." style="max-height: 100%; max-width: 100%;">
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <!-- Product name-->
			                                <h5 class="card-title"> ${goods.title} </h5>
			                                <hr class="mb-3">
			                                <!-- Product price-->
			                                <p class="card-text">  ${goods.price} 원</p>
			                                <hr class="mb-3">
			                                <!-- Product parcel-->
			                                <c:choose>
										        <c:when test="${goods.parcel eq 'free'}">
										            무료배송
										        </c:when>
										        <c:when test="${goods.parcel eq 'paid'}">
										            유료배송 - ${goods.shipprice} 원
										        </c:when>
										    </c:choose>
			                                <hr class="mb-3">
			                                <!-- Product actions-->
			                                <p class="card-text">
			                                    <small class="text-muted">
			                                        <a href="goodsSelect?goodsid=${goods.goodsid}" class="btn btn-primary">상세보기</a>
			                                        <a href="cart" class="btn btn-secondary">장바구니에 추가</a>
			                                    </small>
			                                </p>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			             </c:forEach>
			        </div>
			    </div>
			</section>

        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
