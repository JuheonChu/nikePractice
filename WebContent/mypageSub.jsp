<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지 메뉴선택</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<body>


<div style = "display:inline-block;">
	<input type = "button" class = "btn btn-primary" value = "구매 목록보기" onclick = "location.href = 'NikeactionController?command=mypage' " />
	<input type = "button" class = "btn btn-success" value = "처음으로" onclick = "location.href= 'NikeactionController?command=result_page'"/>
</div>
</body>
</html>