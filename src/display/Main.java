package display;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("====프로그램 시작====");
		
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		while (true) {
			System.out.print("명령어 ) ");

			String cmd = sc.nextLine();
			if(cmd.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번글이 생성되었습니다.\n", id);
			} else if(cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			}
			if(cmd.equals("exit")) {
				break;
			}
		}

		System.out.println("====프로그램 종료====");
		sc.close();
	}

}
