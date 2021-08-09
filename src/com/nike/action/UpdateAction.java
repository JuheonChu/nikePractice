package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;
import com.oreilly.servlet.MultipartRequest;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String writer = request.getParameter("writer");
		
		String saveDirectory = request.getServletContext().getRealPath("file");
		MultipartRequest multi = new MultipartRequest(request, saveDirectory,"UTF-8");	// 이때 업로드
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String file=multi.getFilesystemName("file");	// 파일이름 가져옴
		String filePath = saveDirectory+"/"+file;	// 파일전체 경로
		
		String fileNameTotal="";
		Enumeration en = multi.getFileNames();	// 파일 목록 저장
		while(en.hasMoreElements()) {
			String file2 = (String)en.nextElement();
			String fileName = multi.getFilesystemName(file2);
			fileNameTotal+=fileName+"_";
		}
		System.out.println("수정파일"+fileNameTotal);

		int boardNumber = (Integer) request.getSession().getAttribute("boardNumber");/// NullPointerException

		System.out.println("수정하려는 게시글의 제목, 내용, 저자, 게시글번호: " + title + "," + content + ", " + writer + ", " + boardNumber+","+fileNameTotal);

		System.out.println();

		BoardDAO boardDao = BoardDAO.getInstance();

		BoardDTO bean = new BoardDTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setWriter(writer);
		bean.setBoardNumber(boardNumber);
		bean.setFile(fileNameTotal);
		bean.setFilePath(filePath);

		boardDao.boardModify(bean);

		PrintWriter out = response.getWriter();
		out.println("<script>alert('수정되었습니다'); window.history.go(-2); </script>");
		request.getSession().removeAttribute("boardNumber"); // session에저장해뒀던 번호 클리어해드림.

	}

}
