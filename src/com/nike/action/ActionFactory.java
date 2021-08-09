package com.nike.action;

public class ActionFactory {
	
	
	private static final ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		
	}
	
	static ActionFactory getInstance() {
		return instance;
	}
	
	Action getAction(String command) {
		Action action = null;
		
		switch(command) {
			case "login":
				action = new LoginAction();
				break;
			case "result_page":
				action = new Login_Result_Action();
				break;
			case "signup":
				action = new Sign_Up_Action();
				break;
				
			case "surf":
				action = new SearchAction();
				break;
				
			case "mypage": //구현해야됨 (아직안했음)
				action = new MyPageAction();
				break;
			case "admin": 
				action = new AdminPageAction();
				break;
			case "logout":
				action = new Logout_Action();
				break;
			case "signout":
				action = new SignoutAction();
				break;
			
			case "periodSales":
	            action = new SalesPeriodAction();
	            break;
			case "monthSales":
				action = new SalesMonthAction();
				break;
			
			case "findID":
				action = new FindIDAction();
				break;
			case "findPW":
				action = new FindPWAction();
				break;
			
		}
		return action;
	}
}