package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dao.OrderDAO;
import com.nike.dto.MemberDTO;
import com.nike.dto.OrderDTO;
import com.nike.dto.ProductDTO;

public class MyPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {

		ArrayList<OrderDTO> mypage = new ArrayList<OrderDTO>();
		OrderDAO order = OrderDAO.getInstance();
		MemberDAO member = MemberDAO.getInstance();
		
		int pnum = 0;
		if(request.getAttribute("pnum")!=null) {
			pnum =  (Integer)request.getAttribute("pnum");
		}
//////////////////////////////////총지출액 구하기/////////////////////////////////////////////////////////////////////////////		
		int mid = 1;
		if(request.getAttribute("member_id")!=null) {
			mid = (Integer)request.getAttribute("member_id");
		}
		//////////////////////////////////////////////////////////////////////////
		int memberId=-1;
		try {
			memberId = Integer.parseInt(request.getParameter("member_id"));
		}catch(NumberFormatException e){
			//System.out.println("null값");
		}
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int page = 1;
		int limit = 10;

		if (request.getParameter("currentPage") != null) {
			page = Integer.parseInt(request.getParameter("currentPage"));
		}
		int beginRow = (page - 1) * 10;

		int totalRowCount = order.getTotalBoardCount();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;

		int maxPage = (int) ((double) totalRowCount / limit + 0.95);

		int endPage = maxPage;

		if (endPage > startPage + 10 - 1)
			endPage = startPage + 10 - 1;
		
	if(memberId==-1) {
		mypage = order.mypageList(beginRow, member.getMemberID((String)request.getSession().getAttribute("userid")));
		//System.out.println("목록뽑아봐라" + mypage);

	}else {
		mypage = order.mypageList(beginRow,memberId);
	}
	
		int total = 0;
		if(mid!=1) {
			total = order.getTotalProfit(mid);
		}else {
			total = order.getTotalProfit(member.getMemberID((String)request.getSession().getAttribute("userid")));
		}
		
		
		
		System.out.println("총합계는?" + total); //총합계 0?
		
		
		request.getSession().setAttribute("mypage", mypage);
		//
		//request.getSession().setAttribute("startPage", startPage);
		//request.getSession().setAttribute("maxPage", maxPage);
		//request.getSession().setAttribute("endPage", endPage);
		//request.getSession().setAttribute("productDTO", pDto);

		// request.setAttribute("mypage", mypage);//
		request.setAttribute("total", total);
		request.setAttribute("currentPage", page);//
	//	request.getSession().setAttribute("currentPage", page);
		request.setAttribute("totalRowCount", totalRowCount);//
		request.setAttribute("startPage", startPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("endPage", endPage);

		if(memberId==-1) {
			request.getRequestDispatcher("/mypage.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("purchase_status.jsp").forward(request, response);
		}
		
		// request.getRequestDispatcher("/mypage.jsp").forward(request, response);
	}

}
