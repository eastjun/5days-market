<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정 완료</title>
</head>
<body>
<script type="text/javascript">
 var result = <%= request.getAttribute("result") %>;
    if (result) {
      alert("수정이 완료되었습니다.");
      location.href = "/goods";
    }
    else if (result==null) {
    	alert("이미지를 등록해주세요.");
		window.history.back();
	}
    else {
		alert("등록 실패!");
		window.history.back();
	}
    </script>
</body>
</html>