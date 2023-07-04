<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Result</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script type="text/javascript">
        alert("<%= errorMessage %>");
        location.href = "./MemberInfoView.do";
    </script>
<%
    } else {
%>
    <script type="text/javascript">
        alert("업데이트 되었습니다.");
        location.href = "./MypageView.do";
    </script>
<%
    }
%>
</body>
</html>
