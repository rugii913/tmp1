package test4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import test4.dto.Article;
import test4.utilTmp.UtilTmp;

public class ArticleController extends Controller {
	Scanner sc;
	List<Article> articles = new ArrayList<>();
	int lastId = 3;

	public ArticleController(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void doAction(String actionName, String cmd) {
		switch (actionName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify(cmd);
			break;
		case "delete":
			doDelete(cmd);
			break;
		default:
			System.out.println("명령어 확인 바람");
		}
		
	}

	private void doWrite() {
		System.out.println("제목: ");
		String title = sc.nextLine();
		System.out.println("내용: ");
		String body = sc.nextLine();
		
		String now = UtilTmp.getNow();
		
		articles.add(new Article(++lastId, now, now, loginedMember.id, title, body, 0));
		System.out.println(lastId + "번 게시물 생성");
	}

	private void showList() {
		// TODO Auto-generated method stub
		
	}

	private void showDetail() {
		// TODO Auto-generated method stub
		
	}

	private void doModify(String cmd) {
		String[] cmdDiv = cmd.split(" ");
		
		if (cmdDiv.length != 3) {
			System.out.println("명령어 확인 바람");
			return;
		}
		
		int id = Integer.parseInt(cmdDiv[2]);
		Article article = getArticleById(id);
		
		if (article == null) {
			System.out.println(id + "번 게시글은 존재하지 않음");
			return;
		}
		
		if (article.memberId != loginedMember.id) {
			System.out.println("권한 없음");
			return;
		}
		
		System.out.print("새 제목: ");
		String title = sc.nextLine();
		System.out.print("새 내용:");
		String body = sc.nextLine();
		
		article.updateDate = UtilTmp.getNow();
		article.title = title;
		article.body = body;
	}



	private void doDelete(String cmd) {
		String[] cmdDiv = cmd.split(" ");
		
		if (cmdDiv.length != 3) {
			System.out.println("명령어 확인 바람");
			return;
		}
		
		int id = Integer.parseInt(cmdDiv[2]);
		int index = getIndexById(id);
		
		if (index == -1) {
			System.out.println(id + "번 게시글은 존재하지 않음");
			return;
		}
		
		articles.remove(index);
		System.out.println(id + "번 게시글 삭제");
	}

	private int getIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private Article getArticleById(int id) {
		int index = getIndexById(id);
		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}

	@Override
	public void makeTestData() {
		System.out.println("테스트 게시물 데이터 생성");
		String now = UtilTmp.getNow();
		articles.add(new Article(1, now, now, 2, "제목1", "내용1", 11));
		articles.add(new Article(2, now, now, 2, "제목2", "내용2", 22));
		articles.add(new Article(3, now, now, 3, "제목3", "내용3", 33));
	}

}
