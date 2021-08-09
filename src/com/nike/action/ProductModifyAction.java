package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;

public class ProductModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = dao.detailProduct(pnum);
		
		ArrayList<String> cateList = null;
		cateList = dao.category();
		System.out.println(cateList.toString());
		ArrayList<String> genderList = null;
		genderList = dao.genderType();
		System.out.println(genderList.toString());
		request.setAttribute("cateList", cateList);
		request.setAttribute("genderList", genderList);
		
		
		request.setAttribute("product", dto);
		String url="./Admin/modifyproductAdmin.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

}
