package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;

public class Add_Product_Load_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ProductDAO dao = ProductDAO.getInstance();
		
		ArrayList<String> cateList = null;
		cateList = dao.category();
		System.out.println(cateList.toString());
		ArrayList<String> genderList = null;
		genderList = dao.genderType();
		System.out.println(genderList.toString());
		request.setAttribute("cateList", cateList);
		request.setAttribute("genderList", genderList);
		RequestDispatcher rd = request.getRequestDispatcher("/Admin/addproductAdmin.jsp");
		rd.forward(request, response);
	}

}
