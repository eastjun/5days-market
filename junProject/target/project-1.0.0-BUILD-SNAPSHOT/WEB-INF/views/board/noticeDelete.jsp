<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제 완료</title>
</head>
<body>
<script type="text/javascript">
 var success = <%= request.getAttribute("success") %>;
    if (success) {
      alert("삭제가 완료되었습니다.");
      location.href = "boardSelectAll";
    }
    else {
		alert("등록 실패!");
		window.history.back();
	}
    </script>
</body>
</html>