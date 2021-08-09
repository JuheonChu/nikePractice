package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.CartDAO;

public class DeleteCartAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out= response.getWriter();
		
		int cart_id =0;
		if(request.getParameter("cart_id")!=null) {
			cart_id = Integer.parseInt(request.getParameter("cart_id"));
		}
		
		CartDAO dao = CartDAO.getInstance();
		dao.deletecart(cart_id);
		
		out.println("<script>alert('상품이 장바구니에서 삭제되었습니다.');history.go(-2);</script>");
	}

}
