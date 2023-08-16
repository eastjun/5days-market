<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 등록 완료</title>
</head>
<body>
<script type="text/javascript">
 var result = <%= request.getAttribute("result") %>;
 var goodsid = <%= request.getAttribute("goodsid") %>;
    if (result) {
      alert("Q&A 등록이 완료되었습니다.");
      location.href = "/goods/"+goodsid;
    }
    else {
		alert("문의 등록 실패!");
		window.history.back();
	}
    </script>
</body>
</html>