<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Result</title>
</head>
<body>
<script type="text/javascript">
 var success = ${success};
    if (success) {
      alert("회원 정보가 수정되었습니다.");
      window.location.href = "/project/mypage";
    }
    else {
		alert("비밀번호가 틀렸습니다!");
		window.history.back();
	}
    </script>
</body>
</html>
