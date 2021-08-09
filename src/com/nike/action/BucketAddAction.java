package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.CartDAO;
import com.nike.dao.MemberDAO;
import com.nike.dao.OrderDAO;
import com.nike.dao.ProductDAO;
import com.nike.dto.CartDTO;
import com.nike.dto.ProductDTO;



public class BucketAddAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, Exception{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String id= "";
		if(request.getSession().getAttribute("userid")!=null) {
			id = (String)request.getSession().getAttribute("userid");
		}
		
		String name = "";
		if(request.getParameter("name")==null)System.out.println("장바구니 추가 name쿼리 null");
		else {
			name = request.getParameter("name");
		}
		
		int quantity = 0;
		if(request.getParameter("quantity")!=null) {
			quantity =Integer.parseInt( request.getParameter("quantity"));
		}
		
		
		String size = "";
		if(request.getParameter("size")!=null) {
			size = request.getParameter("size");
		}
		
		int pnum = 0;
		if(request.getParameter("pnum")!=null) {
			pnum = Integer.parseInt(request.getParameter("pnum").trim());
		}
			
		
		MemberDAO member = MemberDAO.getInstance();
		
		
		
		ProductDAO pdt = ProductDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();

	
		
		ProductDTO pdtDTO= pdt.detailProduct(pnum);
		
		
		
		if (id== null) {
			out.println("로그인 부터 해주세요!");
			out.println("<script>alert('로그인 부터 해주세요!');location.href='./index.jsp'</script>");
		} else {

			try {
				int mid = member.getMemberID((String)request.getSession().getAttribute("userid"));
				boolean search = cartDAO.searchcart(mid, pnum);
				for(int i=1; i<=1; i++) {
					if(search == true) {
						cartDAO.pluscart(mid, pnum, quantity);
						request.setAttribute("member_id", mid);
						request.getRequestDispatcher("/barsket.jsp").forward(request,response);
					//	response.sendRedirect("Controller?command=product_mycart&member_id="+id);
						
					} if(search == false) {
						//member_id = mDao.getMemberIdFromId(id);
						cartDAO.insertcart(mid, quantity, size, pdtDTO);
					//	response.sendRedirect("Controller?command=product_mycart&member_id="+id);
						request.setAttribute("member_id", mid);
						request.getRequestDispatcher("ProductController?command=mybucketlist").forward(request,response);
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	}
}
