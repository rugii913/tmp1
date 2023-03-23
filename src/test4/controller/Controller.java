package test4.controller;

import test4.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	
	public static boolean isLogined() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public abstract void doAction(String actionName, String cmd);
	
	public abstract void makeTestData();


}
