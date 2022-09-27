package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.main.model.vo.Account;
import edu.kh.jdbc.main.model.vo.Member;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	private memberView memberView=new memberView();

	public static Member loginMember = null;

	public void mainMenu() {
		int input = -1;

		do {
			try {
				if (loginMember == null) { // 로그인 X
					System.out.println("\n***** [계좌관리 프로그램] *****\n");
					System.out.println("--------------------");
					System.out.println(" | 1. [로그인]");
					System.out.println(" | 2. [회원 가입]");
					System.out.println(" | 3. [프로그램 종료]");
					System.out.println("--------------------");

					System.out.print("[메뉴 선택]: ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					System.out.println("====================");

					switch (input) {
					case 1:
						login();
						break;
					case 2:
						signUp();
						break;
					case 3:
						System.out.println("***[프로그램 종료]***");
						System.exit(0);
						break;
					default:
						System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O
					memberView.memberMenu(loginMember);
					}
				
			} catch (InputMismatchException e) {
				System.out.println("\n!![입력 형식이 올바르지 않습니다.]!!\n");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
				e.printStackTrace();
			}
		}while (input != 0);
	}

	private void login() {
		System.out.println("*** [로그인] ***");
		System.out.print("아이디: ");
		String memberId = sc.next();
		System.out.print("비밀번호: ");
		String memberPw = sc.next();
		System.out.println("====================");
		
		try {
			loginMember = service.login(memberId, memberPw);
			System.out.println();
			if (loginMember != null) { // 로그인 성공
				System.out.println("[ "+loginMember.getMemberName()+" ]" + "님 환영합니다.");
			} else { // 로그인 실패
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("!![로그인 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	private void signUp() {
		System.out.println("*** [회원 가입] ***");
		String memberId=null;
		
		String memberPw1=null;
		String memberPw2=null;
		
		String memberName=null;
		try {
			while(true) {
				System.out.print("[아이디 입력]: ");
				memberId=sc.next();
				int result=service.idDupCheck(memberId);
				System.out.println();
				if(result==0) { // 아이디 중복 X
					System.out.println("[사용 가능한 아이디 입니다.]\n");
					break;
				} else {
					System.out.println("!![이미 사용중인 아이디 입니다.]!!");
				}
				System.out.println();
			}
			
			while(true) {
				System.out.print("[비밀번호 입력]: ");
				memberPw1 = sc.next();
				System.out.print("[비밀번호 확인]: ");
				memberPw2 = sc.next();
				
				if(memberPw1.equals(memberPw2)) {
					System.out.println("\n[일치합니다.]\n");
					break;
				} else {
					System.out.println("!![비밀번호가 일치하지 않습니다.]!!");
				}
				System.out.println();
			}
			
			System.out.print("[이름 입력]: ");
			memberName=sc.next();
			
			Member member=new Member(memberId, memberPw1, memberName);
			
			int result=service.signUp(member);
			System.out.println();
			
			if(result>0) {
				System.out.println("***[회원 가입 성공]***");
			} else {
				System.out.println("!![회원 가입 실패]!!");
			}
		} catch(Exception e) {
			System.out.println("!![회원가입 중 예외 발생]!!");
			e.printStackTrace();
		}
	}
}