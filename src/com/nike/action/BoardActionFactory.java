package com.nike.action;

public class BoardActionFactory {
	
	
	private static final BoardActionFactory instance = new BoardActionFactory();
	
	private BoardActionFactory() {
		
	}
	
	static BoardActionFactory getInstance() {
		return instance;
	}
	
	Action getAction(String command) {
		Action action = null;
		
		switch(command) {
			case "pageview":
				action = new PageView_Action();
				break;
			case "boardNumber":
				action = new BoardAction();
				break;
			case "modify":
				action = new ModifyAction();
				break;
			case "update":
				action =  new UpdateAction();
				break;
			case "write":
				action = new WriteAction();
				break;
			case "delete":
				action = new DeleteAction();
				break;
			case "reply":
				action = new BoardReplyView_Action();
				break;
			case "comment":
				action = new BoardReplyAction();
				break;
			case "select":
				action = new Select_Member_Action();
				break;
			
			
		}
		return action;
	}
}