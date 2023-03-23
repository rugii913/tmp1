package test4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import test4.dto.Member;
import test4.utilTmp.UtilTmp;

public class MemberController extends Controller {
	Scanner sc;
	List<Member> members = new ArrayList<>();
	int lastId = 3;

	public MemberController(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void doAction(String actionName, String cmd) {
		switch (actionName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
		default:
			System.out.println("명령어 확인 바람m");
		}
		
	}

	private void doJoin() {
		String loginId; 
		String loginPw;
		
		while (true) {
			System.out.print("아이디: ");
			loginId = sc.nextLine();
			
			if (isLoginIdDup(loginId)) {
				System.out.println("이미 존재하는 아이디");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.print("비밀번호: ");
			loginPw = sc.nextLine();
			System.out.print("비밀번호 확인: ");
			String loginPwConfirm = sc.nextLine();
			
			if (!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호 불일치");
				continue;
			}
			break;
		}
		
		System.out.print("이름:");
		String name = sc.nextLine();
		
		String now = UtilTmp.getNow();
		members.add(new Member(++lastId, now, now, loginId, loginPw, name));
		
		System.out.println(lastId + "번 회원 가입 완료");
	}



	private void doLogin() {
		String loginId;
		String loginPw;
		
		while (true) {
			System.out.print("아이디: ");
			loginId = sc.nextLine();
			if (getIndexByLoinId(loginId) == -1) {
				System.out.println("일치하는 회원 없음");
				continue;
			}
			break;
		}
		
		Member foundMember = getMemberByLoinId(loginId);
		
		while (true) {
			System.out.print("비밀번호: ");
			loginPw = sc.nextLine();
			if (!foundMember.loginPw.equals(loginPw)) {
				System.out.println("비밀번호 불일치");
				continue;
			}
			break;
		}
		
		loginedMember = foundMember;
		System.out.println("로그인 성공");
	}

	private void doLogout() {
		loginedMember = null;
	}

	
	private int getIndexByLoinId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (loginId.equals(member.loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private Member getMemberByLoinId(String loginId) {
		int index = getIndexByLoinId(loginId);
		if (index == -1) {
			return null;
		}
		return members.get(index);
	}
	
	private boolean isLoginIdDup(String loginId) {
		for (Member member : members) {
			if (loginId.equals(member.loginId)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트 회원 데이터 생성");
		String now = UtilTmp.getNow();
		members.add(new Member(1, now, now, "test1", "test1", "test1"));
		members.add(new Member(2, now, now, "test2", "test2", "test2"));
		members.add(new Member(3, now, now, "test3", "test3", "test3"));
	}

}
