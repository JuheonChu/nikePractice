<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<%@ include file="../includeFile.jsp"%>
<script>
	$(function(){
		var count=0;
	    $("#fileAdd").click(function(){
		    count++;
			var str="<tr><td><input type=file name=file"+count+" id=file"+count+"/></td></tr>";
			$("#fileUpload").append(str);
		});
	});
</script>
</head>
<body>
	<form action = "../nike/NikeBulletinBoardController?command=update"  method = "post"  enctype="multipart/form-data">
			<table>
			<tr>
				<td><label for="title" style="font-weight: bold;">제목</label></td>
				<td ><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td><label for="file">파일첨부</label> </td>
				<td><input type="button" value="추가" name="fileAdd" id="fileAdd"></td>
				<!-- <td><input type="file" name="file" id="file" multiple="multiple"/></td> -->
			</tr>
			<tr class="fileUpload" id="fileUpload">
				<td></td>
			</tr>
			<tr>
				<td><label for="content" style="vertical-align: top;">내용</label></td>
				<td><textarea style="width: 630px; height: 580px;" name="content"></textarea></td>
			</tr>
			<tr>
				<td> <label for="writer">저자</label></td>         
				<td><input type="text" name="writer" value='<%=session.getAttribute("userid")%>' readonly="readonly"/></td>
			</tr>
			<tr style = "float:right;">
				<td colspan = '3'><input type="submit" class="btn btn-primary" value="게시글수정" /></td>				
			</tr>
		</table>
		
	</form>
</body>
</html>