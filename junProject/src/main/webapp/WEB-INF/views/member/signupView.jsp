<<<<<<< HEAD
<!-- success.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <script>
    // 회원가입 성공 여부 확인
    var success = <%= request.getAttribute("success") %>;
    if (success) {
      alert("회원가입이 성공했습니다.");
      location.href = "mainView";
    }
  </script>
</head>
<body>
  <!-- 회원가입 성공 페이지 내용 -->
</body>
</html>
=======
<!-- success.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <script>
    // 회원가입 성공 여부 확인
    var success = <%= request.getAttribute("success") %>;
    if (success) {
      alert("회원가입이 성공했습니다.");
      location.href = "mainView";
    }
  </script>
</head>
<body>
  <!-- 회원가입 성공 페이지 내용 -->
</body>
</html>
>>>>>>> 0fcca063f8cad73f99bf00026c779225a30764b5
