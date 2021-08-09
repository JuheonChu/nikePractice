<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nike.dto.MemberDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ArrayList<MemberDTO> memberList = (ArrayList<MemberDTO>) (request.getAttribute("list"));
int totalRowCount = ((Integer) request.getAttribute("totalRowCount")).intValue();
int currentPage = ((Integer) request.getAttribute("page")).intValue();
int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
int startPage = ((Integer) request.getAttribute("startPage")).intValue();
int endPage = ((Integer) request.getAttribute("endPage")).intValue();
//int boardNumber = ((Integer) request.getAttribute("boardNumber")).intValue();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	
<style>
	.login-wrap{
		text-align:center;
	}
	
	.body{
		text-align:center;
	}
	.login-wrap .header-login{
		margin-bottom:40px;
	}

</style>	
	
</head>
<body>
	<div class = "login-wrap">
			<div class = "header-login">
				<span class = "logo">
					<a href="./index.jsp"><img src=".././images/logo.png"/></a>
				</span><br/><br/>
				<h2 class = "title">나이키 회원정보 조회 게시판</h2>
			</div>
		</div>
	

	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>연락처</th>
				<th>등급</th>
				<th>구매목록</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list}">
				<tr>
					<td>${b.member_id}</a></td>
					<td><a
						href="NikeactionController?command=boardNumber&boardNumber=${b.member_id}">${b.id}</a></td>
					<!-- 컨틀롤러 -->
					<td>${b.pw}</td>
					<td>${b.name}</td>
					<td>${b.address}</td>
					<td>${b.poc}</td>
					<td>${b.grade}</td>
					<td><a href="NikeactionController?command=mypage&member_id=${b.member_id}&id=${b.id}">구매목록</a></td>
				</tr>
			</c:forEach>
			<tr align="center">
				<td colspan='8'>
					<%
						for (int a = startPage; a <= endPage; a++) {
						if (a == currentPage) {
					%> [<%=a%>] <%
						} else {
					%> <a
					href="NikeBulletinBoardController?command=select&currentPage=<%=a%>">[<%=a%>]
				</a>&nbsp; <%
 	}
 %> <%
 	}
 %>


				</td>
			</tr>
		</tbody>
	</table>



	<div>
		<input type = "button" class = "btn btn-primary" value = "이전으로" onclick = "location.href='http://localhost:8080/nike/NikeactionController?command=admin'";>
	</div>

</body>
</html>
