package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String id, pw;
		
		boolean $login = false;
		HttpSession session = request.getSession();
		
		id = request.getParameter("id");
		
		pw = request.getParameter("pw");
		
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		MemberDTO dto = mDao.selectMember(id);

		try{
			$login = mDao.login(id, pw);
			//session.setAttribute("userid", id);
			System.out.println("login 됨? " + $login);
			System.out.println("받아온 idx: " + id + "\t" + "비밀번호: " + pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		String url = "NikeactionController?command=result_page";
		
		if($login == true) {
			//request.setAttribute("$login",$login);
			session.setAttribute("loginTrue", "true");
			session.setAttribute("userid",id);
			session.setAttribute("userpw", pw);
			session.setAttribute("grade", dto.getGrade());
			session.setAttribute("memberDTO",dto);
			request.getRequestDispatcher(url).forward(request, response);
		}else {
			//out.println("<script>alert('failed to login');</script>");
			
			session.setAttribute("loginTrue","false");
			//request.getRequestDispatcher(url).forward(request, response);
			out.println("<script>alert('failed to login');history.back();</script>");
		//	request.getRequestDispatcher("./MembershipLogin/LogIn.html").forward(request, response);
			//request.getRequestDispatcher("http://localhost:8080/nike/WebContent/MembershipLogin/LogIn.html");
		}
		
		
	}

}
