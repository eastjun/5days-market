<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 완료</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script type="text/javascript">
        alert("<%= errorMessage %>");
        location.href = "./MemberDeleteView.do";
    </script>
 <%
    } else {
%>
    <script type="text/javascript">
        alert("탈퇴 하였습니다.");
        location.href = "./MainView.do";
    </script>
<%
session.invalidate(); // 로그아웃   
}
%>   

</body>
</html>