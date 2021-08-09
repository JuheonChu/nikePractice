package com.nike.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;

public class Add_Product_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ProductDTO pDto = new ProductDTO();
		
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String explain = request.getParameter("explain");
		String detailexplain= request.getParameter("detailexplain");
		String img= request.getParameter("img");
		String gender = request.getParameter("gender");
		
		pDto.setType(type);
		pDto.setName(name);
		pDto.setPrice(price);
		pDto.setExplain(explain);
		pDto.setDetailexplain(detailexplain);
		pDto.setImg(img);
		pDto.setGender(gender);
		
	//	System.out.println(pDto);//여기서부터 이미 인코딩이 다깨짐
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.insertProduct(pDto);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
		rd.forward(request, response);
		
	}

}
