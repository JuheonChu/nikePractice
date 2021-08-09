package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.BoardDAO;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		String userid = (String) request.getSession().getAttribute("userid");
		BoardDAO bDao = BoardDAO.getInstance();

		if (bDao.isBoardWriter(boardNumber, userid) == true) {
			bDao.deleteBoard(boardNumber);
			out.println("<script>alert('게시글이 삭제되었습니다!'); history.go(-2);</script>");
		} else {
			out.println("<script>alert('삭제할권한이없습니다!'); history.go(-2);</script>");
		}
	}

}
