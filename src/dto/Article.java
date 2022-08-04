package dto;

public class Article extends Dto{

	public int hit;
	public String title;
	public String body;

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}

	public Article(int id, String regDate, String title, String body, int hit) {
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}

}
