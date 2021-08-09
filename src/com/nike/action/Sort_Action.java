package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;

public class Sort_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String sort = request.getParameter("sort");
		
		
		String select = request.getParameter("select");
		
		String type = request.getParameter("type");
		String gender = request.getParameter("gender");
		System.out.println("sorting방법은? " + sort);//합격 개잘들어옴
		
		ProductDAO dao = ProductDAO.getInstance();
		ArrayList <ProductDTO> product = (ArrayList<ProductDTO>)request.getAttribute("product");

		
		if(sort.equals("price-desc")) {
			product = dao.selectProductDesc(type, gender);
//			out.println("<script>$('#default').setAttribute('selected');</script>");
		}else if(sort.equals("price-asc")) {
			product = dao.selectProductAsc(type, gender);
//			out.println("<script>$('#price-desc').setAttribute('selected');</script>");
		}else if(sort.equals("default")){
			product = dao.selectProductDefault(type, gender);
//			out.println("<script>$('#price-asc').setAttribute('selected');</script>");
		}
		//System.out.println("Sort Action 클래스에서 product: " + product);
		request.setAttribute("product", product);
		request.setAttribute("select", select);
		request.setAttribute("type", type);
		request.setAttribute("gender", gender);
		request.setAttribute("sort",sort);
	
		
		
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
		
		
	}

}
