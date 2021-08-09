<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "com.nike.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	BoardDTO board = (BoardDTO)request.getAttribute("boardBean");
	if(board.getFile()==null){
		System.out.println("file");
	}
	String[] fileArr=board.getFile().split("_");
	int size = fileArr.length;
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../includeFile.jsp"%>
<script>
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	.login-wrap{
		text-align:center;
	}
	
	.body{
		text-align:center;
	}
	.login-wrap .header-login{
		margin-bottom:50px;
	}

</style>	

</head>
<body>

	<div class = "login-wrap">
			<div class = "header-login">
				<span class = "logo">
					<a href="./index.jsp"><img src="images/logo.png"/></a>
				</span><br/><br/>
				<h2><%=board.getTitle() %></h2>
			</div>
		</div>	


<table class="table table-striped table-bordered table-hover">
            <tr>
                <th>작성자</th><td><%= board.getWriter() %></td>
                <th>게시글 번호</th><td><%= board.getBoardNumber() %></td>
            </tr>    
            <tr>
                <th>작성일</th><td><%=board.getDate() %></td>
                <th>조회수</th><td><%=board.getHitcount() %></td>
            </tr>    
            <tr>
                <th>제목</th>
                <td colspan="3"><%=board.getTitle() %></td>
            </tr>
	             <tr>
            		<th>첨부파일</th>
            		<%
            			for(int i=0 ;i<size ;i++){
            				
            		%>
            		<td><a href=file/<%=fileArr[i] %> download name="file"><%=fileArr[i]%></a></td>
            		<% 
            			}
            		%>
           		 </tr>
            	
            <tr>
                <th>내용</th>
                <td colspan="3"><pre><%=board.getContent() %></pre></td>
                        <!-- <pre> 문자열을 쓴 그대로 자유롭게 보여주는 선언자 -->
            </tr>    
            
            
            
        </table>

		<a href="NikeBulletinBoardController?command=reply&boardNumber=<%=board.getBoardNumber() %>">[답변]</a>
		<a href="NikeBulletinBoardController?command=modify&writer=<%=board.getWriter() %>&boardNumber=<%=board.getBoardNumber() %>">[수정]</a>
		<a href="NikeBulletinBoardController?command=delete&boardNumber=<%=board.getBoardNumber() %>">[삭제]</a>
		<input type = "button" class = "btn btn-primary" value ="이전으로" onclick = "location.href='/nike/NikeBulletinBoardController?command=pageview'";/>
		<!--   http://localhost:8080/bulletinboardController?command=result_pageview -->
	
</body>
</html>


