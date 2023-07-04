<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String userid = (String) session.getAttribute("userid"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>장바구니 - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <a class="navbar-brand" href="./MainView.do">사이트 명</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="./MainView.do">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="./GoodsListView.do">All Products</a></li>
                            <li><hr class="dropdown-divider" /></li>
                            <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                            <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="collapse navbar-collapse" id="navbarResponsive">
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
						href="./Logout.do">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="./MypageView.do">마이페이지</a></li>
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
        </div>
    </nav>
<!-- 장바구니에 담긴 상품 목록 -->
<div class="container px-4 px-lg-5 mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h5 class="fw-bold mb-0">장바구니</h5>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>상품명</th>
                                <th>가격</th>
                                <th>선택</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>상품 1</td>
                                <td>10000원</td>
                                <td><input type="checkbox" name="selectedItem" value="1"></td>
                            </tr>
                            <tr>
                                <td>상품 2</td>
                                <td>20000원</td>
                                <td><input type="checkbox" name="selectedItem" value="2"></td>
                            </tr>
                            <tr>
                                <td>상품 3</td>
                                <td>30000원</td>
                                <td><input type="checkbox" name="selectedItem" value="3"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 선택한 상품 가격 합산 및 구매 버튼 -->
<div class="container px-4 px-lg-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card">
                <div class="card-body">
                    <h5 class="fw-bold mb-3">선택한 상품 가격 합계: <span id="totalPrice">0원</span></h5>
                    <button type="button" class="btn btn-primary" onclick="purchase()">구매하기</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript 코드 -->
<script>
    // 선택한 상품 가격 합산 함수
    function calculateTotalPrice() {
        var totalPrice = 0;
        var selectedItems = document.getElementsByName('selectedItem');
        for (var i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i].checked) {
                var itemPrice = parseInt(selectedItems[i].parentNode.previousElementSibling.innerText.replace('원', ''));
                totalPrice += itemPrice;
            }
        }
        document.getElementById('totalPrice').innerText = totalPrice.toLocaleString() + '원';
    }

    // 체크박스 변경 시 합계 업데이트
    var checkboxes = document.getElementsByName('selectedItem');
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].addEventListener('change', calculateTotalPrice);
    }

    // 구매 버튼 클릭 시 구매 처리
    function purchase() {
        var selectedItems = [];
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedItems.push(checkboxes[i].value);
            }
        }
        if (selectedItems.length > 0) {
            // 여기에 구매 처리 로직 추가
            alert('선택한 상품을 구매합니다.');
        } else {
            alert('구매할 상품을 선택해주세요.');
        }
    }
</script>

    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container px-4 px-lg-5">
            <div class="small text-center text-muted">Start Bootstrap &copy; Your Website 2023</div>
        </div>
    </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
