<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나이키 관리자 페이지</title>
<style>
.login-wrap {
	width: 800px;
	margin: 0 auto;
	text-align: center;
}

.login-wrap .header-login {
	margin-bottom: 20px;
}

ul {
	list-style: none;
	width: 200px;
	margin: 0 auto;
	
}

ul li {
	text-align: center;
	padding:auto;
	width:auto;
}
</style>

</head>
<body>
	<div class="login-wrap">
		<div class="header-login">
			<span class="logo"> <a
				href="NikeactionController?command=result_page"><img
					src="./images/logo.png" /></a>
			</span>
			<h2 class="title">나이키 관리자 페이지</h2>
		</div>
	</div>
	
	<%!//현재 월 2자리로 맞추는 함수
	public String getTwoMonth(int month) {
		if (month > 0 && month < 10) {
			return "0" + String.valueOf(month);
		} else {
			return String.valueOf(month);
		}
	}%>
	<%
		//현재 월, 일을 구하는 부분
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		String twoMonth = getTwoMonth(month);

		//현재 월의 1일부터 시작
		String start = year + "-" + twoMonth + "-" + "01";
		//현재 월, 일로 끝남
		String end = format.format(now);

		String salesAction = "./NikeactionController?command=periodSales&start=" + start + "&end=" + end;
	%>
	<ul style="text-align: center; font-size: auto;">
		
		<li><a href="NikeBulletinBoardController?command=select">회원정보 조회</a></li><br/>
		<li><a href="ProductController?command=productAdd">상품목록 추가</a></li><br/>
		<!-- Insert INTO Product  -->
		<li><a href="<%=salesAction%>" target="_blank">기간별 상품 매출 현황</a></li><br/>
		<li><a href="NikeactionController?command=monthSales&year=<%=year %>"
			target="_blank">월별 매출 현황</a></li><br/>
	</ul>

	<div style ="align-content: center;">
		<input type="button" class="btn btn-primary" value="이전으로"
			onclick="history.back()" style="float : left; margin-left : 10%;" class="btn btn-primary">
	</div>


</body>
</html>