<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String userid = (String) session.getAttribute("userid"); %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원가입 - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
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
					
					<li class="nav-item"><a class="nav-link" href="main">공지사항</a></li>
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
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto py-4 py-lg-0">
          <% if (userid == null) { %>
      		  <!-- 로그아웃 상태일 때 -->
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="login">로그인</a></li>
            <li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4" href="signup">회원가입</a></li>
        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <!-- 회원가입 폼-->
    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="fw-bold mb-0">회원가입</h5>
                        </div>
                        <div class="card-body">
                            <form action="memberInsert" method="post" onsubmit="return validateForm();">
                                <div class="mb-3">
                               		 
		                                    <label for="userid" class="form-label" style="margin-right: 20px;"> 아이디  </label>
		                                    <div class = "input-group">
		                                    <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디 입력 후 중복체크">
		                                    <button type="button" id="checkId" class="btn btn-secondary">중복확인</button>
                               		 </div>
                                     	<span id="idCheckResult" style="color:red;"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label"> 비밀번호</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호 입력">
                                </div>
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label"> 비밀번호 확인</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="다시 입력해주세요">
                                </div>
                                <div class="mb-3">
                                	
	                                    <label for="nickname" class="form-label" style="margin-right: 20px;"> 닉네임</label>
	                                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임을 설정해주세요">
                               	      	<span id="nicknameCheckResult" style="color:red;"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="phonenum" class="form-label"> 전화번호</label>
                                    <input type="text" class="form-control" id="phonenum" name="phonenum" placeholder="숫자만 입력">
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label"> 이메일</label>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요">
                                    <span id="emailCheckResult" style ="color:red;"></span>
                                </div>
                                <div class="mb-3">    
	                                    <label for="birthday" class="form-label" > 생년월일</label>
	                                    <input type="date" class="form-control" id="birthday" name="birthday">                
                                </div>
                                <div class="mb-3 form-check">
                                    <input type="checkbox" class="form-check-input" id="agreeTerms" name="agreeTerms">
                                    <label class="form-check-label" for="agreeTerms"> 이용 약관에 동의합니다.</label>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">회원가입</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container px-4 px-lg-5">
            <div class="small text-center text-muted">서비스 소개 - 이용약관 - 개인정보처리방침 - 제휴문의</div>
        </div>
    </footer>
    
    <script>
    var idCheckPassed = false;
	function validateForm() {
    // 필요한 정보를 얻기 위해 form 요소에 접근
    var form = document.forms[0];

    // 아이디가 5글자 이상인지 확인
    var userid = form["userid"].value;
    if (userid.length < 4) {
        alert("아이디는 4글자 이상이어야 합니다.");
        return false;
    }
 // 아이디 중복확인 통과 여부 검사
    if (!idCheckPassed) {
        alert("아이디 중복확인을 해야 합니다.");
        return false;
    }
    // 비밀번호와 확인 비밀번호가 일치하는지 확인
    var password = form["password"].value;
    var confirmPassword = form["confirmPassword"].value;
    if (password !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    // 비밀번호가 8글자 이상인지 확인
    if (password.length < 4) {
        alert("비밀번호는 4글자 이상이어야 합니다.");
        return false;
    }

    // 이메일 형식 확인
    var email = form["email"].value;
    var emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
    if (!emailRegex.test(email)) {
        alert("유효하지 않은 이메일 형식입니다.");
        return false;
    }

    // 전화번호 형식 확인
    var phonenum = form["phonenum"].value;
    var phoneRegex = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
    if (!phoneRegex.test(phonenum)) {
        alert("유효하지 않은 전화번호 형식입니다.");
        return false;
    }

    // 이용약관 동의 확인
    var agreeTerms = form["agreeTerms"].checked;
    if (!agreeTerms) {
        alert("이용약관에 동의해야 합니다.");
        return false;
    }

    // 모든 검사를 통과한 경우 폼 제출
    return true;
}
	//아이디 중복체크
	$(document).ready(function() {
	    $("#checkId").click(function() {
	        var userid = $("#userid").val();
	        $.ajax({
	            url: 'checkID',  // change it to your URL
	            type: 'POST',
	            data: {userid: userid},
	            success: function(response) {
	                if (response === "duplicate") {
	                    $("#idCheckResult").text("아이디가 이미 사용중입니다.");
	                    idCheckPassed= false;
	                } else {
	                    $("#idCheckResult").text("사용 가능한 아이디입니다.");
	                    idCheckPassed= true;
	                }
	            }
	        });
	    });
	});
		//닉네임 중복 체크
		$(document).ready(function() {
		    $("#nickname").blur(function() {
		        var nickname = $("#nickname").val();
		        $.ajax({
		            url: 'checkNickname', // update with your URL
		            type: 'POST',
		            data: {nickname: nickname},
		            success: function(response) {
		                if (response === "duplicate") {
		                    $("#nicknameCheckResult").text("닉네임이 이미 사용중입니다.");
		                } else {
		                    $("#nicknameCheckResult").text("사용 가능한 닉네임입니다.");
		                }
		            }
		        });
		    });

	//이메일 중복체크
    $("#email").blur(function() {
        var email = $("#email").val();
        $.ajax({
            url: 'checkEmail', // update with your URL
            type: 'POST',
            data: {email: email},
            success: function(response) {
                if (response === "duplicate") {
                    $("#emailCheckResult").text("이메일이 이미 사용중입니다.");
                } else {
                    $("#emailCheckResult").text("사용 가능한 이메일입니다.");
                }
            }
        });
    });
});
</script>
    
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.7.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="resource/js/scripts.js"></script>
</body>
</html>
