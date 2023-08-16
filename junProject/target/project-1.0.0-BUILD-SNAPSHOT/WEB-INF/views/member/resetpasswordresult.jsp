<!-- success.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <script>
    // 회원가입 성공 여부 확인
    var success = <%= request.getAttribute("success") %>;
    if (success) {
      alert("비밀번호를 변경했습니다");
      location.href = "main";
    }
    else {
		alert("오류가 발생했습니다");
		location.href = "login"
	}
  </script>
</head>
<body>
 
</body>
</html>
