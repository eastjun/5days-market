<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 완료</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <script type="text/javascript">
            alert("${message}");
            <c:if test="${message eq '회원 탈퇴가 완료되었습니다.'}">
                location.href = "/main"; // 탈퇴가 성공적으로 이루어졌으면 메인 페이지로 이동
            </c:if>
            <c:if test="${message eq '비밀번호가 틀립니다.'}">
                location.href = "/member/delete"; // 비밀번호가 틀렸으면 다시 탈퇴 페이지로 이동
            </c:if>
        </script>
    </c:when>
</c:choose>

</body>
</html>