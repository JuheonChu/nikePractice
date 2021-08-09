<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nike.dto.*"%>
<%@ page import="com.nike.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="./includeFile.jsp"%>
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<link rel="stylesheet" type="text/css" href="./css/product.css" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<head>
<meta charset="UTF-8">
<title>상품목록보기</title>

<style>
.g72-filter:before {
	font-family: nike-glyphs;
	margin-left: 5px;
	content: "\E615";
	display: inline-block;
	font-size: inherit;
	line-height: inherit;
	vertical-align: top;
	font-style: normal;
	font-variant: normal;
	font-weight: 400;
}

.hasFilter {
	background-color: white;
	border: none;
	width: 100px;
	font-size: 18px;
	font-weight: 400;
}

#sort {
	width: 100px;
	border: none;
	font-size: 18px;
	font-weight: 400;
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="
	crossorigin="anonymous"></script>


<%
	ProductDAO dao = ProductDAO.getInstance();
ArrayList<ProductDTO> product = (ArrayList<ProductDTO>) request.getAttribute("product");
request.setAttribute("product", product);
String type = (String)request.getAttribute("type");
//ArrayList<String> explainArr = (ArrayList<String>)request.getAttribute("explainArr");
//request.setAttribute("explainArr", explainArr);
//System.out.print("배열1"+explainArr);
//System.out.println("리퀘스트 받는 product ArrayList");
//System.out.println(product);
String select = (String) request.getAttribute("select");
//System.out.println("select: " + select);
String explainType="";
ArrayList<String> explainArr = new ArrayList<String>();
		if(type.equals("shoes")) {
			explainArr.add("라이프스타일");
			explainArr.add("러닝");
			explainArr.add("트레이닝");
			explainArr.add("농구");
			explainArr.add("조던");
			explainArr.add("축구");
			explainArr.add("슬리퍼");
			explainType="신발 전체";
		}else if(type.equals("clothes")) {
			explainArr.add("티셔츠");
			explainArr.add("후디");
			explainArr.add("크루");
			explainArr.add("팬츠");
			explainArr.add("재킷");
			explainType="의류 전체";
		};
request.setAttribute("explainArr", explainArr);


%>

<style>
select {
	font-size: small;
}

select>option {
	font-size: small;
}
.explain {
	width:200px;
	float:left;
}
.explain a{
	text-decoration: none;
	color:black;
	float:left;
}
.explain a:hover{
	text-decoration: none;
	color:black;
}
.list{
	width: calc(100% - 200px);
	float: left;
}
</style>

</head>
<body>
	<div class="header">
		<%@ include file="./header.jsp"%>
	</div>

	<div class="content" style="display:inline-block;">
		<div class="section-header" style="margin-top:50px;">
			<ul>
			<h1 class="text-color-primary-dark">${gender}'s ${type}</h1>
			</ul>

		</div>
			<div style="text-align: right; vertical-align: top;" class="selectmenu">

				<button class="hasFilter" data-click-area="PW">
					Filter <i><img src="./images/filterdesc.jpg" alt="filter"
						style="max-width: 13.48px; max-height: 24px;" /></i>
				</button>

				<select name="sort" id="sort" class="sort"
					onchange="if(this.value)location.href=(this.value)">
					<option id="default"
						value="ProductController?command=sort&sort=default&type=${type}&gender=${gender}&select=신상품순"
						<%
						if("신상품순".equals(select)) {
					%> selected
						<%
						}
					%>>신상품순</option>

					<option id="price-desc"
						value="ProductController?command=sort&sort=price-desc&type=${type}&gender=${gender}&select=높은 가격순"
						<%
							if("높은 가격순".equals(select)) {
					%> selected
						<%			
							}
					%>>높은가격순</option>

					<option id="price-asc"
						value="ProductController?command=sort&sort=price-asc&type=${type}&gender=${gender}&select=낮은 가격순"
						<%
							if("낮은 가격순".equals(select)){
						%> selected
						<%		
							}
					
					%>>낮은 가격순</option>
				</select>
			</div>
	<div style="margin-top:50px;">
	<div class ="explain">
		<ul>
		<li style="font-weight: bold;"><a href="ProductController?command=product&type=${type}&gender=${gender}"><%=explainType %></a></li></br>
		<c:forEach var="explainArr" items="${explainArr}" varStatus="status">
		<li><a href="ProductController?command=explain&explain=${explainArr}&gender=${gender}&type=${type}">${explainArr}</a></li></br>
		</c:forEach>
		</ul>
	</div>
	<div class="list">
	<ul id="testList">
		<li class="proList">
			<%
				for (int i = 0; i < product.size(); i++) {
				ProductDTO dto = product.get(i);
			%> <a href="ProductController?command=detail&pnum=<%=dto.getPnum()%>">
				<div class="product">
					<img src="<%=dto.getImg()%>" /><br /> <span class="product-name"><%=dto.getName()%></span><br />

					<%
						int price = dto.getPrice();
						String commaPrice = NumberFormat.getInstance().format(price);
					%>

					<input type="hidden" id="price" value="<%=dto.getPrice()%>" /> <span
						class="product-price" id="price"><%=commaPrice%></span><br /> <span
						class="product-explain"> <%=dto.getExplain()%></span>
				</div>
		</a> <%
 	}
 %>
		</li>
	</ul>
	</div>
	</div>
	</div>

	<div class="footer">
		<%@ include file="./footer.jsp"%>
	</div>
</body>
</html>