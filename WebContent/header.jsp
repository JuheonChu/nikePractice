<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class = "loginstatus">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/footer.css" />
<link rel="stylesheet" type="text/css" href="./css/header.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		$(".searchBar").focus(function() {
			this.placeholder = "";
			$(".dark").show();
		});
		$(".searchBar").blur(function() {
			this.placeholder = "검색";
			$(".dark").hide();
		});
	});
</script>
<style>
.dark {
	display: none;
	z-index: 1000px;
	position: fixed;
	bottom: 0;
	left: 0;
	right: 0;
	width: 100%;
	height: 775px;
	background-color: rgba(0, 0, 0, 0.4);
	overflow-x: hidden;
}
</style>


</head>
<body>
	<div class="subMenu">
		<ul class="others">
			<li><a href="#">Nike</a></li>
			<li><a href="#">Join Us</a></li>
			<li><a href="#"><img src = "./images/jordan.png" alt = "Jordan" style = "max-width:60px; max-height:31px;"></a></li>
			<li><a href="#">CONVERSE</a></li>
			<li><a href = "NikeBulletinBoardController?command=pageview">User Board</a></li>
		</ul>
		
		<ul class="userMenu" style = "width:425px;">
		
			
		<%	
			
			if("true".equals((String)session.getAttribute("loginTrue"))){
				if("user".equals(session.getAttribute("grade"))){
					//System.out.println("true 입니다");
				%>
				<li><a href = "NikeactionController?command=mypage">구매목록</a></li>
				<li><a href = "NikeactionController?command=logout">로그아웃</a></li>
			<li><a href = "NikeactionController?command=signout&warning=yes" onclick= "return confirm('탈퇴하시겠습니까?')">회원탈퇴</a></li>
				<% 
				}else if("admin".equals(session.getAttribute("grade"))){
					%>
					<li><a href = "NikeactionController?command=admin">관리자 페이지</a></li>
					<li><a href = "NikeactionController?command=logout">로그아웃</a></li>
					
					<%
				}
			}else{
				%>
				<li><a href="./MembershipLogin/Login.html">회원가입/로그인</a></li>
				<%
			}
		%>
			
			<li><a href="servicecenter.jsp">고객센터</a></li>
			<li><a href="ProductController?command=mybucketlist"><svg width="1.5em" height="1.5em"
						viewBox="0 0 16 16" class="bi bi-cart-fill" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
							d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z" />
</svg></a></li>
			<li><a href="#"><svg width="1em" height="1em"
						viewBox="0 0 16 16" class="bi bi-geo-alt" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
							d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
</svg>&nbsp;대한민국</a></li>
	
		</ul>
	</div>
	<div class="menu">
		<span class="logo"><a href="NikeactionController?command=result_page"><img
				src="./images/logo.png" /></a></span>
		<ul class="mainMenu">
			<li><a href="#">NEW RELEASES</a></li>
			<li><a href="./men_main.jsp">MEN</a></li>
			<li><a href="./women_main.jsp">WOMEN</a></li>
			<li><a href="./kid_main.jsp">KIDS</a></li>
		</ul>
		<div class="searchDiv">
		
		<form action="../nike/ProductController?command=surf" method="post">
			<input type="search" placeholder="검색"
				onblur="this.placeholder='검색'" name="search" class="searchBar" />
			<input type="submit" id="searchIcon" value="" />
		</form>
		
	</div>
</div>



</body>
</html>