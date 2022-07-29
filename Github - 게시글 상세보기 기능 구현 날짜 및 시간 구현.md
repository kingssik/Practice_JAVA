- [Github - 게시글 상세보기 기능 구현 날짜 및 시간 구현](https://github.com/kingssik/Practice_Room/commit/aefcae2d07a2a8ef3cfa7f4bfd5734e62ca40e1b)

```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	// 현재 날짜와 시간 리턴
	public static String getNowDateStr() {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		return sdf1.format(now);
	}
}
// 서로 다른 패키지
import java.text.SimpleDateFormat;
import java.util.Date;

class TimeExample {
	public static void main(String[] args) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		String nowTime1 = sdf1.format(now);

		System.out.println(nowTime1);
	}
}
```