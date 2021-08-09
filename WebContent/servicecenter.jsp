<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.nike.dao.ServiceDAO"%>
<%@ page import="com.nike.dto.ServiceBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" type="text/css" href="./css/footer.css" />
<link rel="stylesheet" type="text/css" href="./css/service.css" />

</head>
<body>
	<%
		String now = "";
	if (request.getParameter("now") != null) {
		request.setCharacterEncoding("utf-8");
		now = request.getParameter("now");
	} else {
		now = "공지사항";
	}

	ServiceDAO sd = new ServiceDAO();
	ServiceBean sb = sd.selectService(now); //    sd.selectService(now);
	%>
	<div class="header">
		<a href="index.jsp"> <img src="images/logo.png" />
		</a>
	</div>
	<div class="body">
		<div class="body_header">
			<ol class="body_header_now">
				<li><a href="nike_service">서비스 센터</a></li>
				<li>> <%=now%></li>
			</ol>
			<form action="search.jsp" method="get" class="search">
				<input type="text" placeholder="검색" name="search" />
			</form>
		</div>
		<div class="body_main" style="margin:0 auto;">
			<div class="select_left">
				<%@ include file="./service_left.jsp"%>
			</div>
			<div class="select_center">
				<div class="center_header">
					<h1><%=now%></h1>
				</div>
				<div class="center_content">
					<ul class="content_list">
						<%
							String[] c = sb.getContent().split("@");
						String[][] content = new String[c.length][];
						for (int i = 0; i < c.length; i++) {
							content[i] = c[i].split("#");
						%>
						<li><strong><%=content[i][0]%></strong>
							<ul>
								<%
									for (int j = 1; j < content[i].length; j++) {
								%>
								<li><a
									href="servicecenter.jsp?now=<%=now%>&<%=sb.getPath()%>=<%=content[i][j]%>"><%=content[i][j]%></a></li>
								<%
									}
								%>
							</ul></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div class="select_right">
				<%@ include file="./service_right.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>