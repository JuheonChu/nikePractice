package com.nike.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;

public class BoardAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		BoardDAO boardDao = BoardDAO.getInstance();
		BoardDTO boardBean = new BoardDTO();
		
		int boardNumber = 0;
		if(request.getParameter("boardNumber")!=null) {
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			System.out.println(boardNumber);
		}
		
		
		boardDao.updateHitcount(boardNumber);
		boardBean = boardDao.selectBoardByKey(boardNumber);
		
		if(boardBean == null) {
			System.out.println("상세보기실패!");
		}else {
			System.out.println("상세보기 성공");
			request.setAttribute("boardBean", boardBean);
			
			RequestDispatcher rd = request.getRequestDispatcher("/BulletinBoard/BoardInDetail.jsp");
			rd.forward(request, response);
		}
		
	}

}
