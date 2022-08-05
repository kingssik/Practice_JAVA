package controller;

import java.util.List;
import java.util.Scanner;

import display.util.Util;
import dto.Member;

public class MemberController {
	
	private Scanner sc;
	private List<Member> members;
	
	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}

	public void doJoin() {

		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();

		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableId(loginId) == false) {
				System.out.printf("%s는 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 재확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입했습니다.\n", id);

	}

	private boolean isJoinableId(String loginId) {
		int index = getMemberIndexById(loginId);

		if (index != -1) {
			return false;
		}
		return true;
	}

	private int getMemberIndexById(String loginId) {
		int i = 0;
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
