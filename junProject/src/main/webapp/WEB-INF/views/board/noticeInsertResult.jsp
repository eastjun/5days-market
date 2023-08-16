<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 완료</title>
</head>
<body>
<script type="text/javascript">
 var result = <%= request.getAttribute("result") %>;
    if (result) {
      alert("공지 등록이 완료되었습니다.");
      location.href = "/boards";
    }
    else {
		alert("등록 실패!");
		window.history.back();
	}
    </script>
</body>
</html>