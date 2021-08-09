package com.nike.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class AdminPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		MemberDTO dto = (MemberDTO) request.getSession().getAttribute("dto"); //admin이어야함(Admin정보).
		
		
		
	
		request.setAttribute("dto",dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
		rd.forward(request, response);
		
		
	}

}
