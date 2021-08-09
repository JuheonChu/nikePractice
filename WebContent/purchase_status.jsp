<%@page import="com.nike.dao.*"%>

<%@page import="com.nike.dto.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img {
   width: 105px;
}

div {
   text-align: center;
}

<!-- -->

table {
   width: 1000px;
}

td{
   border: 2px solid #e5e5e5;
}

th{
   border:2px solid #e5e5e5;
   padding : 5px 10px;
}

.pageNum td{
   border:1px solid #e5e5e5;
}
</style>

<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
   integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
   crossorigin="anonymous">
</head>

<body>
<%
System.out.println(request.getAttribute("currentPage"));
System.out.println(request.getAttribute("maxPage"));
System.out.println(request.getAttribute("startPage"));
System.out.println(request.getAttribute("endPage"));
int currentPage = (Integer)request.getAttribute("currentPage");
int maxPage = (Integer)request.getAttribute("maxPage");
int startPage = (Integer)request.getAttribute("startPage");
int endPage = (Integer)request.getAttribute("endPage");

ProductDTO pp = (ProductDTO)session.getAttribute("productDTO");

int totalFmCart = 0;
if(request.getParameter("total")!=null){
   totalFmCart = Integer.parseInt(request.getParameter("total").trim());
}
%>

   
   <div class="header">
      <%@ include file="./header.jsp"%>

   </div>

   
   <div class="first" style = "margin-top:35px;">
      <h1>${id }님 구매목록
      </h1>

      <table style="margin: auto; border: 1px solid black; margin-top:35px;">
         <tr>
            <th colspan="2">주문상품 정보</th>
            <th>상품명</th>
            <th>주문번호</th>
            <th>상품번호</th>
            <th>수량</th>
            <th>상품금액</th>
            <th>상품 사이즈</th>
            <th>상품 구매 날짜</th>
         </tr>

         <%
            ArrayList<OrderDTO> mypage = (ArrayList<OrderDTO>)session.getAttribute("mypage");
            int total =0;
            
            for (int i = 0; i < mypage.size(); i++) {
               OrderDTO oo = mypage.get(i);
               total += mypage.get(i).getTotal();
         %>
         
         <tr>
            <td colspan = '2'><img src="<%=oo.getImg()%>"></td>
            <td><a href="ProductController?command=detail&pnum=<%=oo.getPnum()%>" target="blank"><%=oo.getName()%></a></td>
            <td><%=oo.getOrder_id()%></td>
            <td><%=oo.getPnum()%></td>
            <td><%=oo.getQty()%></td>
            <%
                  int price = oo.getTotal();
                  String commaPrice = NumberFormat.getInstance().format(price);
               %>
            <td id = "price"><%=commaPrice%></td>
            <td><%=oo.getSize()%></td>
            <td><%=oo.getDate()%></td>
         </tr>
         <%
            }
         %>

         <tr align="center">
            <td colspan='9'>
               <%
                  if(currentPage >= 10){
               %>
                  <a href="NikeactionController?command=mypage&currentPage=<%=currentPage-10%>">[이전페이지]</a>               
               <%      
                  }
               //endPage
               for (int a = startPage; a <= endPage; a++) {
                  if (a == currentPage) {
               %> [<%=a%>] <%
                  } else {
               %> <a href="NikeactionController?command=mypage&currentPage=<%=a%>">[<%=a%>]</a>
               <%
                      }
                   }
                if(currentPage != maxPage){
                %>
                   <a href="NikeactionController?command=mypage&currentPage=<%=currentPage+10%>">[다음페이지]</a>
                <%      
                }
               %>
            </td>
         </tr>
         
      </table>
      
      <%
         int allTotal = total + totalFmCart;
         String commaTotal = NumberFormat.getInstance().format(allTotal);
      %>
      <h3 style="text-align: right; margin-top:40px; padding-right:10%;" class= "total">총지출 : <%=commaTotal %>원</h3>
      <input type = "button" style = "margin-bottom:35px;" class ="btn btn-primary" value ="처음으로"  onclick="location.href='NikeactionController?command=result_page' "/>
   </div>


   <div class="footer">
      <%@ include file="./footer.jsp"%>
   </div>
</body>
</html>