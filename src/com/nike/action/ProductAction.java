package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;


public class ProductAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		ProductDTO dto = new ProductDTO();
		ProductDAO dao = ProductDAO.getInstance();
		
		String type=request.getParameter("type");
		String gender= request.getParameter("gender");
		
		ArrayList<ProductDTO> dtoArr = dao.selectProductDefault(type,gender);
		
		String sortCriteria = "";
		String [] sort = {"default", "price-desc","price-asc"};
//		String[] explainArr = new String[3];
//		if(type.equals("shoes")) {
//			explainArr[0]=("라이프스타일");
//			explainArr[1]=("러닝");
//			explainArr[2]=("농구");
//		}else if(type.equals("clothes")) {
//			explainArr[0]=("티셔츠");
//			explainArr[1]=("팬츠");
//			explainArr[2]=("재킷");
//		};
//		ArrayList<String> explainArr = new ArrayList<String>();
//		if(type.equals("shoes")) {
//			explainArr.add("라이프스타일");
//			explainArr.add("러닝");
//			explainArr.add("농구");
//		}else if(type.equals("clothes")) {
//			explainArr.add("티셔츠");
//			explainArr.add("팬츠");
//			explainArr.add("재킷");
//		};
//		request.setAttribute("explainArr", explainArr);
		request.setAttribute("product", dtoArr);
		//request.getSession().setAttribute("product", dtoArr);
		request.setAttribute("gender", gender);
		request.setAttribute("type", type);
		request.setAttribute("sort",sort);
		request.setAttribute("criteria", sortCriteria);
	//	request.setAttribute("type", "gender");
		String url="product_list.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
