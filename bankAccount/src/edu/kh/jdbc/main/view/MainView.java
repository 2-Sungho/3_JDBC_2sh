package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.main.model.vo.Account;
import edu.kh.jdbc.main.model.vo.Member;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service=new MainService();
	
	private static Member loginMember=null;
	
	public void mainMenu() {
		int input=-1;
		
		do {
			try {
				if(loginMember==null) { // 로그인 X
					System.out.println("\n***** 계좌관리 프로그램 *****\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴 선택: ");
					input=sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1: login();
						break;
					case 2: signUp();
						break;
					case 0: System.out.println("프로그램 종료");
						break;
					default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O
					System.out.println("\n***** 로그인 메뉴 *****\n");
					System.out.println("1. 내 정보 조회");
					System.out.println("2. 입금");
					System.out.println("3. 출금");
					System.out.println("4. 송금");
					System.out.println("99. 프로그램 종료");
					
					System.out.print("\n메뉴 선택: ");
					input=sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1 : selectInfo();
						break;
					case 2 : deposit();
						break;
					case 3 : withdraw();
						break;
					case 4 : remit();
						break;
					case 99 : 
						break;
					}
				}
			} catch(InputMismatchException e) {
				System.out.println("\n<<입력 방식이 올바르지 않습니다.>>\n");
				e.printStackTrace();
			}
		}while(input!=0);
	}

	private void login() {
		System.out.println("*** [로그인] ***");
		System.out.print("아이디: ");
		String memberId=sc.next();
		System.out.print("비밀번호: ");
		String memberPw=sc.next();
		
		try {
			loginMember=service.login(memberId,memberPw);
			System.out.println();
			if(loginMember!=null) { // 로그인 성공
				System.out.println(loginMember.getMemberName()+"님 환영합니다.");
			} else { // 로그인 실패
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println();
			}
		} catch(Exception e) {
			System.out.println("!![로그인 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	private void signUp() {
		
	}

	private void selectInfo() {
		
	}

	private void deposit() {
		
	}

	private void withdraw() {
		
	}

	private void remit() {
		
	}

}
