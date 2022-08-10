package display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ArticleController;
import controller.Controller;
import controller.MemberController;
import display.util.Util;
import dto.Article;
import dto.Member;

public class App {
	private List<Article> articles;
	private List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		while (true) {

			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인하세요.");
				continue;
			}
			String controllerName = cmdBits[0];
			String actionMethodName = cmdBits[1];
			
			Controller controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			} else if(controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하는 명령어가 없습니다.");
				continue;
			}
			controller.doAction(cmd, actionMethodName);
		}

		System.out.println("==프로그램 종료==");
		sc.close();
	}

	private void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}

}