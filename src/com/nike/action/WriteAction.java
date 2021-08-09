package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;
import com.oreilly.servlet.MultipartRequest;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 여기서부터 이미 인코딩이 다깨짐
		request.setCharacterEncoding("UTF-8");
		System.out.println("request내장객체에 있는 캐릭터 인코딩: " + request.getCharacterEncoding());// UTF-8
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("response내장객체에 있는 캐릭터 인코딩: " + response.getCharacterEncoding()); // UTF-8
		PrintWriter out = response.getWriter();
		
		// 파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
		// String savePath = "D:/Projects/workspace/projectName/WebContent/folderName";
		String saveDirectory = request.getServletContext().getRealPath("file"); // 절대경로
		MultipartRequest multi = new MultipartRequest(request, saveDirectory,"UTF-8");	// 이때 업로드
		
		//int num = 0;
//		String title = request.getParameter("title");
//		System.out.println("글 제목: " + title);
//		String content = request.getParameter("content");
//		String writer = request.getParameter("writer");
		String title = multi.getParameter("title");
		System.out.println("글 제목: " + title);
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String file=multi.getFilesystemName("file");	// 파일이름 가져옴
		System.out.println("글쓰끼 파일이름/"+file);
		String filePath = saveDirectory+"/"+file;	// 파일전체 경로
		
		String fileNameTotal="";
		Enumeration en = multi.getFileNames();	// 파일 목록 저장
		while(en.hasMoreElements()) {
			String file2 = (String)en.nextElement();
			String fileName = multi.getFilesystemName(file2);
			fileNameTotal+=fileName+"_";
		}
		System.out.println(fileNameTotal);
		System.out.println("글쓰기값들다들어왔는지 확인~" + title + " , " + content + " , " + writer+","+fileNameTotal+","+filePath);

		if (writer.equals("null") || writer == null) {
			System.out.println("writer가 널임");
			out.println("<script>alert('로그인부터 해주세요!');location.href='http://localhost:8080/nike/index.jsp';</script>");
		}else {
			System.out.println("저자가 있음");
			BoardDTO boardBean = new BoardDTO();
			BoardDAO bDao = BoardDAO.getInstance();
			
			boardBean.setContent(content);
			boardBean.setTitle(title);
			boardBean.setWriter(writer);
			boardBean.setFile(fileNameTotal);
			boardBean.setFilePath(filePath);
			try {

				bDao.WriteBoard(boardBean);

			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println("<script>alert('글이 작성되었습니다!'); history.go(-2);</script>");// working good (이건 한글안깨짐)
			// request.getRequestDispatcher("/nike/NikeBulletinBoardController?command=pageview").forward(request,
			// response);

		}
	}
}
