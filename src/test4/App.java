package test4;

import java.util.Scanner;

import test4.controller.ArticleController;
import test4.controller.Controller;
import test4.controller.MemberController;

public class App {
	public void start() {
		System.out.println("프로그램 시작");
		Scanner sc = new Scanner(System.in);
		
		Controller controller;
		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		
		while (true) {
			System.out.print("명령어 >> ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.length() == 0) {
				System.out.println("명령어 입력 바람");
				continue;
			}
			
			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdDiv = cmd.split(" ");
			
			if (cmdDiv.length == 1) {
				System.out.println("명령어 확인 바람1");
				continue;
			}
			
			String controllerName = cmdDiv[0];
			String actionName = cmdDiv[1];
			String strForLoginCheck = controllerName + "/" + actionName;
			
//			switch (strForLoginCheck) {
//			case "article/write":
//				if (!Controller.isLogined()) {
//					System.out.println("로그인 후 이용 바람");
//					continue;
//				}
//				break;
//			case "member/join":
//				if (Controller.isLogined()) {
//					System.out.println("로그아웃 후 이용 바람");
//					continue;
//				}
//			}
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("명령어 확인 바람2");
				continue;
			}
			
			controller.doAction(actionName, cmd);
		}
		
		sc.close();
		System.out.println("프로그램 종료");
	}
}
