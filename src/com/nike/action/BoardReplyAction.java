package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;
import com.oreilly.servlet.MultipartRequest;

public class BoardReplyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String saveDirectory = request.getServletContext().getRealPath("file");
		MultipartRequest multi = new MultipartRequest(request, saveDirectory,"UTF-8");	// 이때 업로드
		
		//ArrayList<BoardDTO> commentList = new  ArrayList<BoardDTO>();
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
	//	BoardDTP boardDTO = request.getParameter("board")
		System.out.println("타이틀"+multi.getParameter("title"));
		System.out.println("파일이름"+multi.getFilesystemName("file"));
		int result = 0;
		String file=multi.getFilesystemName("file");	// 파일이름 가져옴
		String filePath = saveDirectory+"/"+file;	// 파일전체 경로
		
		String fileNameTotal="";
		Enumeration en = multi.getFileNames();	// 파일 목록 저장
		while(en.hasMoreElements()) {
			String file2 = (String)en.nextElement();
			String fileName = multi.getFilesystemName(file2);
			fileNameTotal+=fileName+"_";
			System.out.println("파일네밍ㅁ12121"+fileNameTotal);
		}
		dto.setBoardNumber(Integer.parseInt(multi.getParameter("boardNumber")));
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content"));
		dto.setWriter(multi.getParameter("name"));
		//date 는 default
		dto.setRef(Integer.parseInt(multi.getParameter("ref")));
		dto.setStep(Integer.parseInt(multi.getParameter("step")));
		dto.setLevel(Integer.parseInt(multi.getParameter("level"))); //null (NumberFormatException)
		dto.setFile(fileNameTotal);
		////////////////////////////////////////////////////////////////////
		result = dao.boardReply(dto); // bno값을 받은 result (writeBoard)
		
		
		if(result == 0) {
			System.out.println("답장 실패!"); // 여기서 걸림.
		}else {
			System.out.println("답장성공!");
			
			//request.setAttribute("boardDTO", dto);
			request.getRequestDispatcher("NikeBulletinBoardController?command=boardNumber&boardNumber="+result).forward(request, response);
		}
		
	}

}
