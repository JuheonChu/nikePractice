package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;

public class SignoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		boolean result = false;
		
		String id = (String)request.getSession().getAttribute("userid");
		//String pw = (String)request.getSession().getAttribute("userpw");
		
		if(id==null) {
			out.println("<script>alert('등록된 아이디가없습니다');</script>");
			response.sendRedirect("/index.jsp");
		}
		
		String warning = "";
		if("yes".equals(request.getParameter("warning"))){
			warning = request.getParameter("yes");
		}
		
		if(mDao.signOut(id)==false) {
			System.out.println("회원삭제 실패");
			out.println("<script>alert('회원삭제실패!');history.go(-1);</script>");
		}else {
			out.println("<script>alert('탈퇴완료!');</script>");
			request.getRequestDispatcher("/MembershipLogin/signout.jsp").forward(request, response);
			
		}
		
		
		
		
		
	}

}
