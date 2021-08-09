<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var warning = confirm('로그아웃 하시겠습니까?');

if(warning){
	alert("로그아웃되었습니다!");
}else{
	
}
</script>
</head>
<body>
	<%
		session.invalidate();
	request.removeAttribute("userid");
	request.removeAttribute("userpw");
	request.removeAttribute("loginTrue");
	request.removeAttribute("memberDTO");
	request.removeAttribute("grade");
	request.getRequestDispatcher("../index.jsp").forward(request, response);
	%>
</body>
</html>