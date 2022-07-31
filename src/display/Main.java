package display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("====프로그램 시작====");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.print("명령어 ) ");
			String cmd = sc.nextLine();

			if (cmd.length() == 0) {
				System.out.println("명령어를 다시 입력하세요");
				continue;
			}
			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getNowDateStr();

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body); // Article 조립
				articles.add(article); // 게시글 배열에 작성한 게시글 저장

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("번호    |    제목");
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.printf("%d       |       %s\n", article.id, article.title);
				}

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = -1;	// 배열에 원소가 없다는 의미(null의 의미)

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;		// 찾은 게시물의 인덱스번호 i
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 없습니다.\n", id);
					continue;
				}
				
 				articles.remove(foundIndex);		// 문제 해결 : 게시물 구분을 id에서 배열의 인덱스 번호로 변경
				System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				} else {
					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("날짜 : %s\n", foundArticle.regDate);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
				}

			} else {
				System.out.println("없는 명령어입니다.");
			}
			if (cmd.equals("exit")) {
				break;
			}
		}

		System.out.println("====프로그램 종료====");
		sc.close();
	}

}

class Article {
	int id;
	String title;
	String body;
	String regDate;

	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}

}