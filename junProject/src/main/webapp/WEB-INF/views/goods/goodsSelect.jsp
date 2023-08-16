<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userid = (String) session.getAttribute("userid");

%>
<% Integer uniqueid = (Integer) session.getAttribute("uniqueid"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop Homepage</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/resources/css/styles.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
.navbar-nav .nav-item a:hover {
	background-color: lightgray;
	padding: 10px;
	border-radius: 4px;
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
		<div class="container px-4 px-lg-5">
			<a href="/main"> 
			<img src="/resources/assets/img/logo.png" class="navbar-brand" style="width: 120px; height: 80px;">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">

					<li class="nav-item"><a class="nav-link" href="/boards">공지사항</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#!"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">상품목록</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/goods">모든 상품</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="/goods">인기 상품</a></li>
							<li><a class="dropdown-item" href="/goods">새로 나온 상품</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<%
						if (userid == null) {
					%>
					<!-- 로그아웃 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/signup">회원가입</a></li>
					<%
						} else {
					%>
					<!-- 로그인 상태일 때 -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/mypage">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/orderCheck">주문조회</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-2 py-lg-4"
						href="/cart">장바구니 <span
							class="badge bg-dark text-white ms-1 rounded-pill">0</span></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Section-->
	<section class="py-5 mt-5">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<img src="/resources/upload/${goodsDTO.image}" alt="상품 이미지"
						class="img-fluid" style="max-width: 500px; max-height: 500px;">
				</div>
				<div class="col-md-6">
					<h2 class="fw-bold">${goodsDTO.title}</h2>
					<div class="text-end">
						<small class="text-muted">판매자: ${goodsDTO.userid}</small>
					</div>
					<hr>
					<p class="fs-5">가격: ${goodsDTO.price} 원</p>
					<hr>
					<p>원산지: ${goodsDTO.origin }</p>
					<hr>
					<p>
						배송:
						<c:choose>
							<c:when test="${goodsDTO.parcel eq 'free'}">무료배송</c:when>
							<c:otherwise>  ${goodsDTO.shipprice } 원</c:otherwise>
						</c:choose>
					</p>
					<hr>
					<form>
						<div class="mb-4">
							<label for="quantity" class="form-label">상품 갯수</label>
							<div class="input-group" style="width: 120px;">
								<button type="button" class="btn btn-outline-primary"
									id="decreaseBtn">-</button>
								<input type="text" class="form-control text-center"
									id="quantity" name="quantity" value="1">
								<button type="button" class="btn btn-outline-primary"
									id="increaseBtn">+</button>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">장바구니에 추가</button>
						<button type="submit" class="btn btn-success">구매하기</button>
						<c:choose>
							<c:when test="${goodsDTO.userid eq userid}">
								<a class="btn btn-warning"
									href="/goods/${goodsDTO.goodsid}/edit">수정하기</a>
								<a class="btn btn-danger" id="deleteButton" href="#">삭제하기</a>
							</c:when>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- 상품 설명-->
	<section class="py-5">
		<div class="container">
			<h2 class="fw-bolder">상세정보</h2>
			<hr>
			${goodsDTO.content }
		</div>
	</section>

	<!-- User Reviews -->
	<section class="py-5">
		<div class="container mt-5 d-flex">
			<div class="col-lg-12">
				<h2 class="fw-bolder">상품후기</h2>
				<hr>
				<table class="table mt-4">
					<thead>
						<tr>
							<th style="width: 10%; text-align: center;">별점</th>
							<th style="width: 50%; text-align: center;">제목</th>
							<th style="width: 20%; text-align: center;">글쓴이</th>
							<th style="width: 20%; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody>
						    
	 				</tbody>
				</table>
			</div>
		</div>
	</section>
	
<!-- User Q&A -->
<section class="py-5">
    <div class="container">
        <div class="mb-3 d-flex justify-content-between align-items-center">
		    <div>
		    <h2 class="fw-bolder">Q&A</h2>
		     <small style="color:gray">문의하실 내용이 있으시면 입력해주세요.</small>
		    </div>
		</div>
		<!-- Button to trigger the modal -->
		    <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal" data-bs-target="#qnaModal">질문하기</button>
        <hr>
          <!-- Q&A Modal -->
			<div class="modal fade" id="qnaModal" tabindex="-1" aria-labelledby="qnaModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h5 class="modal-title" id="qnaModalLabel">Q&A</h5>
			                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			            </div>
			            <div class="modal-body">
			                <!-- Q&A Input Form -->
			                <form id="qnaForm" method="post" action="/qna/new" >
			                 	<input type="hidden" name="goodsid" value="${goodsDTO.goodsid }">
    							<input type="hidden" name="uniqueid" value="<%= uniqueid %>">
			                    <div class="mb-3">
			                        <label for="qnatitle" class="form-label">제목</label>
			                        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요.">
			                    </div>
			                    <div class="mb-3">
			                        <label for="question" class="form-label">문의내용</label>
			                        <textarea class="form-control" id="content" name= "content" rows="3" placeholder="내용을 입력하세요."></textarea>
			                    </div>
			                    <div class="modal-footer">
					            	<button type="submit" class="btn btn-primary">등록하기</button>
					                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								</div>
			                </form>
			            </div>
			            
				     </div>
				</div>
			</div>
			
        <!-- Q&A Table -->
        <table class="table">
            <thead>
                <tr>                   
                    <th style="width: 10%; text-align: center;">답변상태</th>
                    <th style="width: 50%; text-align: center;">제목</th>
                    <th style="width: 10%; text-align: center;">작성자</th>
                    <th style="width: 10%; text-align: center;">작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="qna" items="${qnaList}">
                    <tr> 
                        <td style="text-align: center;">
                        <c:choose>
							<c:when test="${qna.status eq 'wait'}">답변 대기</c:when>
							<c:otherwise> 답변 완료</c:otherwise>
						</c:choose>
						</td>
                        <td style="text-align: left;" class="qna-title" onclick="toggleContent(this, '${qna.status}');">${qna.title}                      
                        <div class="qna-content m-2 p-3" style="display: none; background-color: #f0f0f0;">${qna.content} </div>
                        	<c:if test="${qna.status eq 'complete'}">	
                   				<div class="qna-answer m-2 p-3" style="display: none; background-color: #f0f0f0;">▷ ${qna.answer}</div>                  				
                			</c:if>
                			
                			<div class="answer-input mt-3 mb-2" id="answerInput-${qna.qnaid}" style="display: none;">
								            <textarea id="answer-${qna.qnaid}" placeholder="답변을 입력하세요." rows="2" cols="80"></textarea>
								            <br>
								            <button class="btn btn-primary btn-sm" onclick="submitAnswer('${qna.qnaid}');">등록하기</button>
						     </div>               			
                        </td>
                        <td style="text-align: center;">${qna.nickname}
                        </td>
                        <td style="text-align: center;">
						    <fmt:parseDate value="${qna.writeday}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
						    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
						    <c:choose>
							<c:when test="${goodsDTO.userid eq userid && qna.status eq 'wait'}">
								 <button class="btn btn-secondary btn-sm" onclick="toggleAnswerInput('${qna.qnaid}');">답변하기</button>								        
							</c:when>
						</c:choose>
						</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
	        <div class="pagination" style="center">
			    <a href="?page=1">1</a>
			    <a href="?page=2">2</a>
			    <!-- ... -->
			</div>
		</div>
	</section>
				
	<script>
		//상품 갯수 감소
		document.getElementById('decreaseBtn').addEventListener('click',
				function() {
					const quantityInput = document.getElementById('quantity');
					let quantity = parseInt(quantityInput.value);
					if (quantity > 1) {
						quantity--;
						quantityInput.value = quantity;
					}
				});
		//상품 갯수 증가
		document.getElementById('increaseBtn').addEventListener('click',
				function() {
					const quantityInput = document.getElementById('quantity');
					let quantity = parseInt(quantityInput.value);
					quantity++;
					quantityInput.value = quantity;
				});
		//삭제 버튼 유효성
		document
				.getElementById('deleteButton')
				.addEventListener(
						'click',
						function(event) {
							const result = confirm('정말로 이 상품을 삭제하시겠습니까?');
							if (result) {
								window.location.href = "/goods/${goodsDTO.goodsid}/delete";
							} else {
								event.preventDefault();
							}
						});
		//Q&A 토글
			function toggleContent(element, status) {
			    var contentDiv = element.getElementsByClassName('qna-content')[0];
			
			    if (contentDiv) {
			        contentDiv.style.display = contentDiv.style.display === 'none' ? 'block' : 'none';
			    }
			
			    if (status === 'complete') {
			        var answerDiv = element.getElementsByClassName('qna-answer')[0];
			        
			
			        if (answerDiv) {
			            answerDiv.style.display = answerDiv.style.display === 'none' ? 'block' : 'none';
			        }		       
			    }
			}	
				//댓글 토글
				function toggleAnswerInput(qnaId) {
			    var inputDiv = document.getElementById('answerInput-' + qnaId);
			    inputDiv.style.display = inputDiv.style.display === 'none' ? 'block' : 'none';
			 	}
					//댓글 전송
				function submitAnswer(qnaId) {
			     var answerText = document.getElementById('answer-' + qnaId).value;
					
			     // 서버에 비동기 요청을 보내 답변 등록
			    $.ajax({
			        url: '/qnaAnswer', 
			        method: 'POST',
			        contentType: 'application/json',
			        data: JSON.stringify({ // JSON 형식으로 데이터 직렬화
			            qnaid: qnaId,
			            answer: answerText
			        }),
			        success: function(response) {
			            // 성공 시 처리 (예: 알림 표시, 페이지 새로고침 등)
			            alert('답변이 등록되었습니다.');
			            location.reload(); //새로고침 추가
			        },
			        error: function(error) {
			            // 실패 시 처리 (예: 오류 메시지 표시)
			            alert('답변 등록에 실패하였습니다.');
			        }
			    });
			}


	</script>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/resources/js/scripts.js"></script>
</body>
</html>
