package com.nike.action;

import java.io.IOException;
import java.lang.String;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BucketAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		// quantity 갯수
		// size 사이즈
		// name 이름
		String product = null;
		System.out.println("BucketAction 들어옴.");

	

		request.getSession().setAttribute("product", product);

		String url = "/barsket.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
