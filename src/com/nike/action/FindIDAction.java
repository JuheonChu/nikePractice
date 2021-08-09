package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class FindIDAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		String name = (String)request.getParameter("name");
		String poc = (String)request.getParameter("poc");
		System.out.println("이름"+name);
		System.out.println("번호"+poc);
		PrintWriter out = response.getWriter();
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto=dao.selectMemberToName(name, poc);
		String id = dto.getId();
		System.out.println("dto 확인"+dto);
		if(name.equals("") || poc.equals("")) {
			out.println("<script>alert('정보를 입력해주세요.'); history.back();</script>");
		}else {
			out.println("<script>alert('아이디 : "+id+"'); location.href ='MembershipLogin/Login.html';</script>");
		}
		
	}
}
