package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.CartDAO;
import com.nike.dao.MemberDAO;
import com.nike.dto.CartDTO;

public class BucketListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		MemberDAO member = MemberDAO.getInstance();
		CartDAO cart = CartDAO.getInstance();
		ArrayList<CartDTO>boards = null;
		
		int mid = -1;
		if(request.getAttribute("member_id")!=null) {
			mid = (Integer)request.getAttribute("member_id");
		}
		
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("currentPage") != null) {
			page = Integer.parseInt(request.getParameter("currentPage"));		//여길 안들어옴
		}
		int beginRow = (page - 1) * 10;
		
		
		int totalRowCount = cart.getTotalBoardCount();
		//System.out.println("장바구니에 row 수?" + totalRowCount);
		try {
			if(mid==-1) {
				boards = cart.selectCartListPerPage(beginRow, member.getMemberID((String)request.getSession().getAttribute("userid")));
			}else {
				boards = cart.selectCartListPerPage(beginRow,mid);
			}
			
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

		//System.out.println("장바구니 아이템: " + boards);

		request.setAttribute("member_id", mid);
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



		request.getRequestDispatcher("/barsket.jsp").forward(request, response);
		
	}

}
