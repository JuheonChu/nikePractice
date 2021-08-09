package com.nike.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.SalesDAO;
import com.nike.dto.OrderDTO;
import com.nike.dto.SalesDTO;

public class SalesMonthAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		ArrayList<SalesDTO> olist = null;
		int year = Integer.parseInt(request.getParameter("year"));
		SalesDAO dao = SalesDAO.getInstance();
		olist = dao.monthSales(year);
		
		System.out.println("가져온 정보");
		for(SalesDTO list : olist) {
			System.out.print(list.getDate() + " | ");
			System.out.println(list.getTotal());
		}
		
		String[] month = new String[olist.size()];
		int[] total = new int[olist.size()];
		
		for(int i = 0; i < olist.size(); i++) {
			month[i] = String.valueOf(olist.get(i).getDate());
		}
		
		for(int i = 0; i < olist.size(); i++) {
			total[i] = olist.get(i).getTotal();
		}
		
		request.setAttribute("month", month);
		request.setAttribute("total", total);
		request.setAttribute("year", year);
		request.getRequestDispatcher("./Admin/salesMonthAdmin.jsp").forward(request, response);
	}

}
