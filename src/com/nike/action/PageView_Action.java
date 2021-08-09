package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;
import com.nike.dto.BoardDTO;

public class PageView_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		BoardDAO bDao = BoardDAO.getInstance();
		ArrayList<BoardDTO> boards = null;

		int page = 1;
		int limit = 10;
		
		if (request.getParameter("currentPage") != null) {
			page = Integer.parseInt(request.getParameter("currentPage"));		//여길 안들어옴
		}
		int beginRow = (page - 1) * 10;
		System.out.println("현제페이지는? " + page);
	//	System.out.println("a쿼리넘어왔을까? " +((Integer)request.getAttribute("a")).intValue());
		int totalRowCount = bDao.getTotalBoardCount();
		try {
			boards = bDao.selectBoardListPerPage(beginRow);
			//boards = bDao.getBoardList(page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//System.out.println("몇번째서 부터 게시글 출력? " + beginRow);

		
		
	
	//	int lastPage = totalRowCount / totalRowCount;
	//	if (totalRowCount % limit != 0) {
		//	lastPage++;
		//}

		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		
		
		
		
		
		

		int maxPage = (int) ((double) totalRowCount / limit + 0.95);

		int endPage = maxPage;
		
		if (endPage>startPage+10-1) endPage=startPage+10-1;

		

		request.setAttribute("list", boards);//
		request.setAttribute("page", page);//
		request.setAttribute("totalRowCount", totalRowCount);//
		request.setAttribute("pagePerRow", maxPage);
	///	request.setAttribute("lastPage", lastPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("endPage", endPage);
	//	request.setAttribute("beginRow", beginRow);

		//request.getSession().setAttribute("beginRow", beginRow);
	//	request.getSession().setAttribute("currentPage", currentPage);



		request.getRequestDispatcher("BulletinBoard/boards.jsp").forward(request, response);
		
	}

}
