package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.ProductDAO;
import com.nike.dto.ProductDTO;

public class ExplainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		String explain = request.getParameter("explain");
//		String[] arr = request.getParameterValues("explainArr[]");
//		System.out.println("배열확인"+Arrays.toString(arr));
//		for(int i=0 ; i<arr.length;i++) {
//			System.out.println(arr[i]);
//		}
		ProductDAO dao = ProductDAO.getInstance();
		ArrayList<ProductDTO> dtoArr= new ArrayList<ProductDTO>(); 
		String gender = request.getParameter("gender");
		String type = request.getParameter("type");
		dtoArr =dao.selectProductExplain(explain,type,gender);
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
		request.setAttribute("type", type);
		request.setAttribute("gender", gender);
		request.setAttribute("product", dtoArr);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
		
	}
}
