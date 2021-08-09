<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ include file="./includeFile.jsp"%>
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<title>나이키 남성</title>
<style>
.men_menu{
	display: flex;
    align-items: center;
    justify-items: center;
    width: 30%;
    margin-left: auto;
    margin-right: auto;
    height: 62px;
    text-align: center;
}

.men_menu ul {
	list-style: none;
	margin : 0 auto;
	padding : 0;
}

.men_menu ul li {
	display: inline;
	padding : 10px 30px;
}

.men_menu ul li a{
	text-decoration: none;
	color : black;
}

.sub_menu_name{
	font-weight : bold;
	position : absolute;
	display: inline;
	float: left;
	font-size: 20px;
	width: 200px;
	left: calc((100% - 836px) / 8);
}

.content{
	margin-top: 168px !important;
}
</style>
</head>
<body>
	<div class="header">
	<%@ include file="./header.jsp"%>
		<div class="men_menu">
			<span class="sub_menu_name">NIKE KID</span>
			<ul>
				<li><a href="ProductController?command=product&type=kid-shoes&gender=all">신발</a></li>
				<li><a href="ProductController?command=product&type=kid-clothes&gender=boy">남아 의류</a></li>
				<li><a href="ProductController?command=product&type=kid-clothes&gender=girl">여아 의류</a></li>
			</ul>
		</div>
	</div>
	<div class="content">
		<img src="./images/kid_main_image.jpg" alt="" /> 
		<img src="./images/main2.jpg" alt="" />
		<img src="./images/main3.jpg" alt="" />
	</div>
	<div class="footer">
		<%@ include file="./footer.jsp"%>
	</div>
</body>
</html>