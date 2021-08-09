package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;

public class ModifyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("text/html; charset=UTF-8");
boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("boardNumber"));
		String userid = (String)request.getSession().getAttribute("userid"); //null
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardDTO bean = new BoardDTO();
		
		boolean userCheck = bDao.isBoardWriter(num, userid);
		
		//System.out.println("들어온 수정저자: " + request.getParameter("writer"));
		//System.out.println("수정하려고 시도하는게시글번호: " + num);
		
		if(userCheck == false) {
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정할 권한이 없습니다!');history.back();</script>");
			out.close();
		}
		
		else {
			System.out.println("들어옴");
		//	forward(request, response)===> html 글 form ==> jsp ==> controller
			RequestDispatcher rd = request.getRequestDispatcher("/BulletinBoard/modify.jsp");
			request.getSession().setAttribute("boardNumber",num);
			rd.forward(request, response);
		}
		
		
	
	
	}

}
