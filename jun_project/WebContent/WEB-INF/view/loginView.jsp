<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>


<%
    String message = (String)request.getAttribute("message");
%>

<script>
<% if (message != null) { %>
    alert('<%=message%>');
    <% if ((Boolean)request.getAttribute("loginSuccess")) { %>
        window.location.href = "./MainView.do"; 
    <% } else { %>
        window.location.href = "./MemberLoginView.do"; // 실패시 다시 로그인 페이지로 돌아감
    <% } %>
<% } %>
</script>

</head>
<body>

</body>
</html>
