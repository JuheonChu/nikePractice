package com.nike.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.MemberDAO;
import com.nike.dto.MemberDTO;

public class Sign_Up_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");


		String poc = request.getParameter("poc");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		
		MemberDTO bean = new MemberDTO();
		
		bean.setId(id);
		bean.setPw(pw);
		bean.setGrade("user");
		bean.setAddress(address);
		bean.setName(name);
		bean.setPoc(poc);

		MemberDAO mDAO = MemberDAO.getInstance();
		
		//System.out.println(pw.matches("[0-9]+"));//false
		System.out.println(pw.matches("(.*?\\d){4,}"));//false
		System.out.println(poc.matches("[0-9]+"));//true
		
		if(mDAO.idOverlapCheck(id)) {
			System.out.println("id 중복");
			out.println("<script>alert('중복된 id');history.go(-1);</script>");
		}else if(poc.trim().length()>=7 && poc.matches("[0-9]+")&& pw.length()>= 5){
			  
				   mDAO.insertMember(bean);
				   System.out.println("회원가입 성공");
				out.println("<script>alert('가입완료'); history.go(-2);</script>");
			  
			}else {
				
				System.out.println("회원가입 실패");
				out.println("<script>alert('가입실패'); history.go(-1);</script>");
			}
		
		
		
		
		

		

	}

}
