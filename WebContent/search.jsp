<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nike.dto.SearchBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nike.dao.SearchDAO"%>
<%@ include file ="./includeFile.jsp" %>
<link rel="stylesheet" type="text/css" href="./css/product.css"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.search-text {
	text-align: center;
	padding: 100px 0px;
	border-bottom: 1px solid gray;
}

.search-result {
	width: 100%;
	padding: 10px;
}

.search-item {
	padding-inline-start: 0px;
	list-style-type: none;
}

.search-item {
	padding: 10px;
}

.product{
	text-align:center;
}
</style>
</head>
<body>
	
	<div class="header">
		<%@ include file="./header.jsp"%>
	</div>
	
	<div class="content">
		<div class="search-text">
			<h2>${search}
			
			</h2>
		</div>
		
		<ul>
		
			<li class="searchResultList">
				<c:forEach var="product" items="${sb}">
					<a href="ProductController?command=detail&pnum=${product.getPnum()}">
						<div class = "product">
							<img src="${product.getImg()}"/><br/>
							<span class="product-name"> ${product.getName()} </span><br/>
							<span class="product-price"> ${product.getPrice()} </span><br/>
							<span class="product-explain"> ${product.getExplain()} </span>
						</div>
					</a>
				</c:forEach>
			</li>
			
		</ul>
		
	</div>
	
	
	<div class="footer">
		<%@ include file="./footer.jsp"%>
	</div>
</body>
</html>