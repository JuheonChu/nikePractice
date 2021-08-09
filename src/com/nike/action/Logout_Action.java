package com.nike.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		String url = "/MembershipLogin/LogoutPro.jsp";
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
