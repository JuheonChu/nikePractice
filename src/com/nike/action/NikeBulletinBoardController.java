package com.nike.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.action.Action;
import com.nike.action.ActionFactory;


@WebServlet("/NikeBulletinBoardController")
public class NikeBulletinBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String command = request.getParameter("command");//calculate를 담고있었음
		System.out.println("명령어: " + command);
		
		String id = (String)request.getSession().getAttribute("userid");
	//	String pw = request.getParameter("pw");
		
		request.setAttribute("id",id);
		//request.setAttribute("pw",pw);
		
		BoardActionFactory af = BoardActionFactory.getInstance();//Singleton 생제
		Action action = null;
		
		action = af.getAction(command);
		
		if(action != null) {
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
