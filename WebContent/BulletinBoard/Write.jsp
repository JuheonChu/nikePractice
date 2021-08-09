<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>

<title>게시판 글쓰기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<%@ include file="../includeFile.jsp"%>
<script>
	$(function(){
		/*var fileName="";
        $("input[name=file]").change(function () {
            var fileInput = document.getElementById("file");
            var files = fileInput.files;
            var file;
            for (var i = 0; i < files.length; i++) {
                file = files[i];
                fileName+=file.name+"_";
            }
                alert(fileName);
        });
	    $("#write").click(function(){
		    alert(fileName);
            //location.href = "../NikeBulletinBoardController?command=write&files="+fileName;
	    });*/
	    
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
	<form action="../NikeBulletinBoardController?command=write"method="post" accept-charset="UTF-8" enctype="multipart/form-data">
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
				<td colspan = '3'><input type="submit" class="btn btn-primary" value="게시글등록" /></td>				
			</tr>
		</table>
	</form>
	
	<form action="../NikeBulletinBoardController?command=upload" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
	<table>
	</table>
	</form>
	
</body>
</html>