package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.CartDAO;
import com.nike.dao.MemberDAO;
import com.nike.dao.OrderDAO;
import com.nike.dao.ProductDAO;
import com.nike.dto.OrderDTO;
import com.nike.dto.ProductDTO;

public class PurchaseCartAction implements Action {

	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		//System.out.println(request.getQueryString());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getQueryString()); //이미 name 은 null 상태

		PrintWriter out = response.getWriter();
		OrderDAO order = OrderDAO.getInstance();
		CartDAO cart = CartDAO.getInstance();


		int qty = 0;
		int cart_id = 0;
		String name = request.getParameter("name");

		MemberDAO member = MemberDAO.getInstance();
		ProductDAO pDao = ProductDAO.getInstance();

		if (request.getParameter("cartid") != null) {
			cart_id = Integer.parseInt(request.getParameter("cartid").trim());
		}

		if (request.getParameter("quantity") != null) {
			qty = Integer.parseInt(request.getParameter("quantity").trim());
		}

		String size = request.getParameter("size"); //

		int price = Integer.parseInt(request.getParameter("price")); // NullPointerException || NumberFormatException
		// System.out.println("size: ? " + size + " price = " + price);
		int pnum = Integer.parseInt(request.getParameter("pnum")); // null pointer exception || NumberFormatException

		int member_id = 0;

		String img = request.getParameter("img");
		System.out.println("넘어온 img 쿼리");

		// System.out.println("name query 넘어왔나?" + name); //null

		//ProductDTO pDto = pDao.detailProduct(pnum);

		String userid = (String) request.getSession().getAttribute("userid");


		if (userid == null) {
			out.println("로그인 부터 해주세요!");
			out.println("<script>alert('로그인 부터 해주세요!');location.href='./index.jsp'</script>");
		} else {

			try {
				member_id = member.getMemberID(userid);

				for (int i = 1; i <= 1; i++) {
					order.insertOrder(pnum, qty, member_id, price, size, img, name);

				}
					
				request.setAttribute("totalcart",qty*price);
				cart.deletecart(cart_id);

				
				request.getRequestDispatcher("NikeactionController?command=mypage&warning=yes").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of else

	}
}
