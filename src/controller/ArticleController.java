package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import display.util.Util;
import dto.Article;

public class ArticleController extends Controller {

	private Scanner sc;
	private List<Article> articles;
	private String actionMethodName;
	private String cmd;

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}

	@Override
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	public void doWrite() {
		int id = articles.size() + 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}

		String searchKeyword = cmd.substring("article list".length()).trim();

		System.out.printf("검색어 : %s\n", searchKeyword);

		List<Article> forPrintArticles = articles; // ArrayList에 접근할 수 있는 변수를 하나 더 만든 것

		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}

			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
				return;
			}
		}

		System.out.printf("번호    |   제목   |   	  %7s        |   조회\n", "날짜");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);

			System.out.printf("%7d | %6s   | %5s   | %5d\n", article.id, article.title, article.regDate, article.hit);
		}

	}

	public void showDetail() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다.\n", id);
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시물을 수정했습니다.\n", id);
	}

	public void doDelete() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		int foundIndex = getArticleIndexById(id);
		;

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 없습니다.\n", id);
			return;
		}

		articles.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

	}

	private int getArticleIndexById(int id) {
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

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

}
