package com.nike.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nike.dao.SalesDAO;
import com.nike.dto.OrderDTO;
import com.nike.dto.SalesDTO;

public class SalesPeriodAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");			
		
		ArrayList<SalesDTO> olist = null;
		SalesDAO dao = SalesDAO.getInstance();
		olist = dao.periodSales(start, end);
		
		request.setAttribute("olist", olist);
		request.getRequestDispatcher("./Admin/salesDataAdmin.jsp").forward(request, response);
	}

}
