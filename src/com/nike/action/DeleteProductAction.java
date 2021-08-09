package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;

public class DeleteProductAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int pnum = 0;
		if(request.getParameter("pnum")!=null) {
			pnum = Integer.parseInt(request.getParameter("pnum"));
		}
		
		String gender = request.getParameter("gender");
		String type = request.getParameter("type");
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.deleteProduct(pnum);
	//	out.println("<script>alert('가입완료'); history.go(-2);</script>");
		
		request.getRequestDispatcher("ProductController?command=product&type="+type+"&gender="+gender).forward(request, response);
		
		
		
	}

}
