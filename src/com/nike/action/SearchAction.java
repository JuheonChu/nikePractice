package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.SearchDAO;
import com.nike.dto.SearchBean;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		String search = request.getParameter("search").trim();
		System.out.println("검색결과 ");

		SearchDAO sd = new SearchDAO();
		
		ArrayList<SearchBean> sb = new ArrayList<SearchBean>();
		
		sb = sd.selectSearch(search); // nullpointerexception
		
		System.out.println("검색된 카테고리의 배열리스트: " + sb); // arrayIndexoutoufbounds exception
		
		if(sb==null) {
			search = "검색결과가 없습니다";
		}else {
			search += " 에 대한 검색결과입니다.";
			request.setAttribute("sb",sb);
			request.setAttribute("search",search);
			RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
			rd.forward(request, response);
		}

	}

}
