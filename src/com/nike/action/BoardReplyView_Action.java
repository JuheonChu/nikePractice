package com.nike.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;

public class BoardReplyView_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		
		//bDao.updateHitcount(boardNumber);
		board = bDao.selectBoardByKey(boardNumber); //모든정보가여기다있음
	
		//board.setRef(board.getBoardNumber());
		
		if(board == null) {
			System.out.println("답글 페이지로의 이동실패");
		}else {
			System.out.println("답글 페이지로 이동성공");
			
			request.getSession().setAttribute("board", board);
			
			request.getRequestDispatcher("/BulletinBoard/reply.jsp").forward(request, response);
		}
		
	}

}
