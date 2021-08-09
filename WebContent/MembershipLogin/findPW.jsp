<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<title>비밀번호 찾기</title>
<style>
	.login-wrap{
		text-align:center;
	}
	
	.body table{
		margin : 0 auto;
	}
	.login-wrap .header-login{
		margin-bottom:40px;
	}
	.body{
		text-align:center;
	}
</style>
</head>
<body>
	<div class = "login-wrap">
			<div class = "header-login">
				<span class = "logo">
					<a href="../index.jsp"><img src=".././images/logo.png"/></a>
				</span>
				<h2 class = "title">비밀번호 찾기</h2>
			</div>
	</div>
	<form action="../NikeactionController?command=findPW" method="post">
	<div class="body">
			<div class = "body">
			<input type = "text" placeholder="아이디" name = "id" style = "margin-right:-23px; width:300px;"/><br/><br/>
			<input type = "text" placeholder="이름" name = "name" style = "margin-right:-23px; width:300px;"/><br/><br/>
			<input type = "text" placeholder="전화번호" name = "poc" style = "margin-right:-23px; width:300px;"/><br/><br/>
			<div>
			<input type = "submit" value = "비밀번호 찾기" class = "btn btn-primary" style = "margin-right:-23px; width:300px;"/>
			</div><br/>
	</div>
	</div>
	</form>
</body>
</html>





