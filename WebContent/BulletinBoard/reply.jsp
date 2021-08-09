<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.nike.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>댓글</title>
<% BoardDTO board = (BoardDTO)session.getAttribute("board"); %>
</head>

<script>
	function replyboard(){
		boardform.submit();
	}

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

<style>
	.submit{
		align-items: center;
		valign:center;
	}
</style>



<body>
	<form action = "NikeBulletinBoardController?command=comment" method = "post" name = "boardform" accept-charset="UTF-8" enctype="multipart/form-data">
		<input type = "hidden" name = "boardNumber" value = "<%= board.getBoardNumber() %>"/>
		<input type = "hidden" name = "ref" value = "<%= board.getRef()%>" />
		<input type = "hidden" name = "step" value = "<%= board.getStep()%>"/>
		<input type = "hidden" name = "level" value = "<%= board.getLevel()%>"/>
		
		<table>
			<tr>
				<td><div style = "text-align:center;">글쓴이</div></td>
			</tr>
			<tr>
				<td><input type = "text" name = "name" value = "<%=session.getAttribute("userid")%>"/></td><!-- 게시글 이름 -->
			</tr>
			
			<tr>
				<td><div style = "text-align:center;">제목</div></td>
			</tr>
			<tr>
				<td><input type = "text" name = "title" maxlength = "100" value = "Re:<%=board.getTitle()%>"/></td><!-- 원본글에대한 정보를얻기위함 -->
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
				<td><div style = "text-align:center;">내용</div></td>
			</tr>
			
			<tr>
				<td><textarea name = "content"  value = "Re:<%=board.getContent()%>" cols= "67" rows = "15"/></textarea></td><!-- 어느글에대한댓글 -->
			</tr>
			
			<tr class = "submit">
				<td><input type = "submit" class = "btn btn-primary" value = "등록"></td>&nbsp;&nbsp;&nbsp;
				<td><input type = "button" class = "btn btn-success" onclick = "history.go(-1)" value = "이전"></td>
			</tr>
		</table>
	</form>
</body>
</html>