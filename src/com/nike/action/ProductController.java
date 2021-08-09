package com.nike.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("컬ㅋㅋㅋㅋㅋㅋㅋㅋ트롤러. " + request.getQueryString() + "command? " + command);
		
		Action action =null;
		switch(command) {
		case "product":
			action = new ProductAction();
			break;
		case "detail" :
			action = new DetailAction();
			break;
		case "surf":
			action = new SearchAction();
			break;
		case "sort":
			action = new Sort_Action();
			break;
		
		case "a" :
			PurchaseAction dd = new PurchaseAction();
			dd.dd();
			//System.out.println("purchase");
			break;
		case "modify":
			action = new ProductModifyAction();
			break;
		case "update":
			action = new ProductUpdateAction();
			break;
		case "productAdd" :
			action = new Add_Product_Load_Action();
			break;
		case "addproduct":
			action = new Add_Product_Action();
			break;
		case "purchase":
			action = new PurchaseAction();
			break;
		case "bucketAdd":				
			action = new BucketAddAction();	
			break;				
		case "bucket":			
			action = new BucketAction();		
			break;	
		case "delete":
			action = new DeleteProductAction();
			break;
		case "deleteCart":
			action = new DeleteCartAction();
			break;
		case "purchaseCart":
			action = new PurchaseCartAction();
			break;
		case "mybucketlist":
			action = new BucketListAction();
			break;
		case "explain":
			action = new ExplainAction();
			break;
	
		}
		try {
			action.execute(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
