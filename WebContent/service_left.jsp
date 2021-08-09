<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a{
	text-decoration: none;
	color : black;
}
.list_style, .list_style ul{
list-style-type:none;
}
.list_style ul{
padding-inline-start:0px;
}
.list_style li{
margin-top:10px;
}

.list_style ul li a{
	text-decoration: none;
	color : black;
}
</style>
</head>
<body>
<ul class="list_style">
<li>
<strong>자주찾는 내용</strong>
<ul>
<li><a href="servicecenter.jsp?now=공지사항">공지사항</a></li>
<li><a href="servicecenter.jsp?now=회원혜택/서비스">회원혜택/서비스</a></li>
<li><a href="servicecenter.jsp?now=A/S">A/S</a></li>
<li><a href="servicecenter.jsp?now=THEDRAW">THE DRAW</a></li>
<li><a href="servicecenter.jsp?now=주문결제">주문결제</a></li>
<li><a href="servicecenter.jsp?now=회원정보">회원정보</a></li>
<li><a href="servicecenter.jsp?now=취소/반품">취소/반품</a></li>
<li><a href="servicecenter.jsp?now=기타">기타</a></li>
<li><a href="servicecenter.jsp?now=배송">배송</a></li>
<li><a href="servicecenter.jsp?now=상품">상품</a></li>
</ul>
</li>
<li>
<strong>나의 정보 조회</strong>
<ul>
<li><a href="servicecenter.jsp?now=주문내역조회">주문내역 조회</a></li>
<li><a href="servicecenter.jsp?now=배송조회">배송조회</a></li>
<li><a href="servicecenter.jsp?now=반품신청">반품 신청</a></li>
<li><a href="servicecenter.jsp?now=아이디찾기">아이디 찾기</a></li>
<li><a href="servicecenter.jsp?now=비번찾기">비번 찾기</a></li>
</ul>
</li>
<li>
<strong>서비스 안내</strong>
<ul>
<li><a href="servicecenter.jsp?now=회원혜택">회원 혜택</a></li>
<li><a href="servicecenter.jsp?now=무료반품서비스">무료 반품 서비스</a></li>
<li><a href="servicecenter.jsp?now=입고알림서비스">입고알림 서비스</a></li>
<li><a href="servicecenter.jsp?now=매장상품예약서비스">매장상품 예약 서비스</a></li>
<li><a href="servicecenter.jsp?now=ASSISTSERVICE"> ASSIST SERVICE</a></li>
</ul>
</li>
<li><a href="notice.jsp"><strong>공지사항</strong></a>
</ul>
</body>
</html>