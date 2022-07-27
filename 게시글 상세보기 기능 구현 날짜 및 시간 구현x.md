- [Github - 게시글 상세보기 기능 구현 날짜 및 시간 구현x](https://github.com/kingssik/Practice_Room/commit/704c23178093d5c43f536d2a1c5338a03a89a506)

```java
else if (cmd.startsWith("article detail ")) {

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
					System.out.printf("날짜 : 2022-02-05 12:09:00\n");
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
				}

			}
```