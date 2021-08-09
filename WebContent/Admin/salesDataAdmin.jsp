<%@page import="com.nike.dto.SalesDTO"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.nike.dto.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기간별 상품 매출 현황</title>
</head>
<body style = "width : 80%; margin : 0 auto;">
<br />
<form action="NikeactionController?command=periodSales" method="post" class="form-inline">
	<input type="date" name="start" id="start" class="form-control" />&nbsp;~&nbsp;
	<input type="date" name="end" id="end" class="form-control"/>&nbsp;
	<input type="submit" value="조회" class="btn btn-primary" />
</form>
<%
	String start = request.getParameter("start");
	String end = request.getParameter("end");
%>
<br />
<h2><%=start %> ~ <%=end %> 상품별 매출합계</h2>
<br />
<table class="table table-striped">
	<tr>
		<th>상품명</th>
		<th>판매수량</th>
		<th>판매금액</th>
	</tr>
	<%
		ArrayList<SalesDTO> olist = (ArrayList<SalesDTO>)request.getAttribute("olist");
		if(request.getAttribute("olist") != null){			
			for(int i = 0; i<olist.size(); i++){
				%>
					<tr>
						<td><a href="ProductController?command=detail&pnum=<%=olist.get(i).getPnum()%>" target="_blank">
						<%=olist.get(i).getName() %></a></td>
						<td><%=olist.get(i).getQty() %></td>
						<%
							int total = olist.get(i).getTotal();
							String totalComma = NumberFormat.getInstance().format(total);
						%>
						<td><%=totalComma %></td>
					</tr>
				<%
			}
		}
	%>
</table>
<%
	int sum = 0;
	for(int i = 0; i<olist.size(); i++){
		sum += olist.get(i).getTotal();
	}
	String sumComma = NumberFormat.getInstance().format(sum);
%>
<h2 style="float : right;">총 매출 : <%=sumComma %></h2>
</body>
</html>