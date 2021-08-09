<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import = "com.nike.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ArrayList <BoardDTO> boardList = (ArrayList<BoardDTO>) (request.getAttribute("list"));
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
               <a href="NikeactionController?command=result_page"><img src=".././images/logo.png"/></a>
            </span><br/><br/>
            <h2 class = "title">나이키 회원 게시판</h2>
         </div>
      </div>
   

   <table class="table table-striped table-bordered table-hover">
      <thead>
         <tr>
            <th>게시글번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>저자</th>
            <th>게시일</th>
            <th>조회수</th>
         </tr>
      </thead>
      <tbody>
         <!-- 이자리였어요 -->
            <%for(int i = 0; i < boardList.size();i++){
               BoardDTO dto = boardList.get(i);%>
            
            <tr>
               <td><%=dto.getBoardNumber() %></td> <!-- 게시글 번호 -->
               
               <td>
                  <div style = "align-content: flex-start;">
                     <% if(dto.getLevel()!=0){%>
                     <% for(int a = 0; a <= dto.getLevel()*2;a++){ %>
                        &nbsp;
                     <% } %>
                     <%} else {%>
                     <% }%>
                     <a href="NikeBulletinBoardController?command=boardNumber&boardNumber=<%=dto.getBoardNumber()%>"><%=dto.getTitle() %></a>
                  </div>
               </td>
               
               <!-- 컨틀롤러 -->
               <td><%=dto.getTitle() %></td>
               <td><%=dto.getWriter() %></td>
               <td><%=dto.getDate() %></td>
               <td><%=dto.getHitcount() %></td>
            </tr>
            <%}%>
         <!-- 이자리였어요 -->
         <tr align="center">
            <td colspan='7'>
               <%
                  if(currentPage >= 10){
               %>
                  <a href="NikeBulletinBoardController?command=pageview&currentPage=<%=currentPage-10%>">[이전페이지]</a>               
               <%      
                  }
               //endPage
               for (int a = startPage; a <= endPage; a++) {
                  if (a == currentPage) {
               %> [<%=a%>] <%
                  } else {
               %> <a href="NikeBulletinBoardController?command=pageview&currentPage=<%=a%>">[<%=a%>]</a>
               <%
                      }
                   }
                if(currentPage != maxPage){
                %>
                   <a href="NikeBulletinBoardController?command=pageview&currentPage=<%=currentPage+10%>">[다음페이지]</a>
                <%      
                }
               %>
            </td>
         </tr>
      </tbody>
   </table>

   <div>
      <a href="BulletinBoard/Write.jsp">게시글 입력</a> 
   </div>

</body>
</html>