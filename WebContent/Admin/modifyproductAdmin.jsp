<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상품 수정</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
.login-wrap {
	text-align: center;
}

.body {
	text-align: center;
}

.login-wrap .header-login {
	margin-bottom: 40px;
}

<
style>body>form {
	width: 500px;
	margin: 50px auto;
	box-shadow: 0px 0px 20px #afafaf;
	padding: 40px 40px 60px 40px;
	border-radius: 30px;
}

.fr {
	float: right;
}
</style>
</head>
<body>
	<div class="login-wrap">
		<div class="header-login">
			<span class="logo"> <a href="NikeactionController?command=result_page"><img
					src=".././images/logo.png" /></a>
			</span>
			<h2 class="title">나이키 관리자 페이지</h2>
		</div>
	</div>
	<form action="ProductController?command=update&pnum=${product.getPnum()}" method="post" accept-charset="UTF-8">
		제품종류 : <select class = "form-control" name="type">
			<c:forEach var="ceteList" items="${cateList}">
				<option value="${ceteList }">${ceteList }</option>
			</c:forEach>
		</select></br>
		제품이름: <input type="text" name="name" class = "form-control" value="${ product.getName()}"/><br />
		가격: <input type="text" name="price" class = "form-control" value="${ product.getPrice()}"/><br /> 
		설명: <input type="text" name="explain" class = "form-control" value="${ product.getExplain()}"/><br />
		상세설명: <input type="text" name="detailexplain" class = "form-control" value="${ product.getDetailexplain()}"/><br /> 
		이미지: <input type="text" name="img" class = "form-control" value="${ product.getImg()}"/><br /> 
		성별 : <select class = "form-control" name="gender">
			<c:forEach var="genderList" items="${genderList}">
				<option value="${genderList}">${genderList}</option>
			</c:forEach>
		</select></br>
		<div>
			<input type="submit" class="btn btn-info fr" value="작성" /> <input
				type="button" value="뒤로" class="btn btn-primary"
				style="margin: 20px;" onClick="history.back()" />
		</div>
	</form>
</body>
</html>