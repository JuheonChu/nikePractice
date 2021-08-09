package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class FindPWAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name= request.getParameter("name");
		String poc = request.getParameter("poc");
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		dto=dao.selectMember(id);
		boolean check = false;
		
		if(name.equals(dto.getName()) && poc.equals(dto.getPoc())) {
			check=true;
		}
		
		System.out.println(dto);
		String pw = dto.getPw();
		if(name.equals("") || poc.equals("") || id.equals("")) {
			out.println("<script>alert('정보를 입력해주세요.'); history.back();</script>");
		}else if(!check) {
			out.println("<script>alert('정보가 맞지않습니다. 다시 입력해주세요.'); history.back();</script>");
		}else {
			out.println("<script>alert('비밀번호 : "+pw+"'); location.href ='MembershipLogin/Login.html'; </script>");
		}
	}
}





