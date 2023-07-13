<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/color-modes.js"></script>
<!-- Custom styles for this template -->
<link href="resources/css/sign-in.css" rel="stylesheet" type="text/css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        p {
            margin-bottom: 10px;
        }

        .result-container {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 50px;
            max-width: 400px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="result-container m-auto">
        <img class="mb-3" src="resources/assets/img/login.png" alt="" width="70" height="70" style="display: block; margin: 0 auto;">
        <h1 class="h3 mb-3 fw-normal" style="text-align: center;">아이디 찾기 결과</h1>
        <!-- Start Card -->
        <div class="card">
            <div class="card-body">
                <p class="card-text">${message}</p>
            </div>
        </div>
        <!-- End Card -->

        <div class="mt-3 text-center">
            <a class="link-opacity-100" href="login">로그인</a> 
                &nbsp; | &nbsp; 
            <a class="link-opacity-100" href="reset password">비밀번호 찾기</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.7.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="resources/js/checkout.js"></script>
</body>
</html>
