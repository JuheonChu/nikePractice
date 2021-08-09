package com.nike.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;


public class DetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = new ProductDTO();
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		dto = dao.detailProduct(pnum);
		
		request.setAttribute("type",dto.getType());
		request.setAttribute("productdetail", dto);
		String url="product_detail.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
