package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class Login_Result_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {

		//String id = request.getParameter("id");
	//	System.out.println(id);
	//	String loginTrue = request.getParameter("loginTrue");
		String loginTrue =(String) request.getSession().getAttribute("loginTrue");
		
	//	String userUrl = "./headerUser.jsp";
		//MemberDAO mDAO = MemberDAO.getInstance();
		
		//MemberDTO mBean = mDAO.selectMember(id);

	//	CalculateDAO cDAo = CalculateDAO.getInstance();
	

		//request.setAttribute("dto", mBean);
		request.getSession().setAttribute("loginTrue",loginTrue);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}
