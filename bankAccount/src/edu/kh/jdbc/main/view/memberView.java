package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.memberService;
import edu.kh.jdbc.main.model.vo.Account;
import edu.kh.jdbc.main.model.vo.Member;

public class memberView {

	private Scanner sc = new Scanner(System.in);
	private Member loginMember = null;
	private memberService service = new memberService();
	private List<Account> accountList = null;

	public void memberMenu(Member loginMember) {

		int input = -1;

		this.loginMember = loginMember;
		do {
			try {
				System.out.println("\n***** [로그인 메뉴] *****\n");
				System.out.println(" | 1. [내 정보 조회]");
				System.out.println(" | 2. [내 계좌 등록]");
				System.out.println(" | 3. [입금]");
				System.out.println(" | 4. [출금]");
				System.out.println(" | 5. [송금]");
				System.out.println(" | 99. [로그 아웃]");

				System.out.print("\n[메뉴 선택]: ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				System.out.println("====================");

				switch (input) {
				case 1:
					selectInfo(); // 내 정보(계좌) 조회
					break;
				case 2:
					insertAccount(); // 내 계좌 등록
					break;
				case 3:
					deposit(); // 입금
					break;
				case 4:
					withdraw(); // 출금
					break;
				case 5:
					remit(); // 송금
					break;
				case 99:
					System.out.println("[로그 아웃 후 홈 화면으로 이동합니다.]");
					MainView.loginMember = null;
					input = 0;
					break;
				default:
					System.out.println("[메뉴에 작성된 번호만 입력해주세요.]");
				}

			} catch (InputMismatchException e) {
				System.out.println("\n!![입력 형식이 올바르지 않습니다.]!!\n");
				e.printStackTrace();
			}
		} while (input != 0);
	}

	private void printAccount(List<Account> accountList) {
		try {
			accountList = service.selectInfo(loginMember);
			System.out.println("============= [계좌 목록] =============");
			for (Account a : accountList) {
				System.out.printf("[계좌 번호] : %s || [잔액] : %d 원\n", a.getAccountNo(), a.getBalance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 내정보조회
	 */
	private void selectInfo() {
		System.out.println("***[내 정보 조회]***");
		try {
			List<Account> accountList = service.selectInfo(loginMember);
			System.out.println("[회원 번호]: " + loginMember.getMemberNo());
			System.out.println("[회원 I D]: " + loginMember.getMemberId());
			System.out.println("[회원 이름]: " + loginMember.getMemberName());
			for (Account a : accountList) {
				System.out.printf("[계좌 번호] : %s // [잔액] : %d 원\n", a.getAccountNo(), a.getBalance());
			}

		} catch (Exception e) {
			System.out.println("!![로그인 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	/**
	 * 내 계좌 등록
	 */
	private void insertAccount() {
		System.out.println("***[내 계좌 등록]***");
		try {
			System.out.print("[계좌 번호 입력]: ");
			String accountNo = sc.next();
			System.out.print("[계좌 비밀번호 입력]: ");
			String accountPw = sc.next();
			Account account = new Account();
			account.setMemberNo(loginMember.getMemberNo());
			account.setMemberName(loginMember.getMemberName());
			account.setAccountNo(accountNo);
			account.setAccountPw(accountPw);
			int result = service.insertAccount(account);
			if (result > 0) {
				System.out.println("***[내 계좌 등록 성공]***");
			} else {
				System.out.println("!![계좌 등록 실패]!!");
			}
		} catch (Exception e) {
			System.out.println("!![계좌 등록 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	private void deposit() {
		System.out.println("***[입금]***");
		try {
			System.out.println("[입금할 계좌를 입력하세요.]");

			printAccount(accountList); // 로그인 계정의 계좌 조회
			System.out.println("--------------------------------------");
			while (true) {
				System.out.print("[계좌 입력]: ");
				String accountNo = sc.next();
				int result = service.checkAccountNo(accountNo);
				if (result == 0) {
					System.out.println("!![해당 계좌가 존재하지 않습니다]!!");
					System.out.println(" [다시 입력해주세요] ");
					System.out.println("--------------------------------------");
				} else {
					Account a = service.selectAccount(accountNo);
					if (a.getAccountNo().equals(accountNo)) {
						System.out.print("[입금 금액 입력]: ");
						int dMoney = sc.nextInt();
						sc.nextLine();

						if (dMoney <= 0) {
							System.out.println("[0원 이하는 입금이 불가능합니다.]");
						} else {
							System.out.print("[정말 입금하시겠습니까?(Y/N)]: ");
							char ch = sc.next().toUpperCase().charAt(0);
							if (ch == 'Y') {
								int result2 = service.deposit(accountNo, dMoney);
								if (result2 > 0) {
									System.out.println("***[입금 성공]***");
									break;
								} else {
									System.out.println("!![입금 실패]!!");
									break;
								}
							} else {
								System.out.println("...[입금 취소]...");
								break;
							}
						}
					}

				}
			}

		} catch (Exception e) {
			System.out.println("!![입금 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	private void withdraw() {
		System.out.println("***[출금]***");
		try {
			System.out.println("[출금할 계좌를 입력하세요.]");

			printAccount(accountList);
			System.out.println("--------------------------------------");
			while (true) {
				System.out.print("[계좌 입력]: ");
				String accountNo = sc.next();
				int result = service.checkAccountNo(accountNo);
				if (result == 0) {
					System.out.println("!![해당 계좌가 존재하지 않습니다]!!");
					System.out.println(" [다시 입력해주세요] ");
					System.out.println("--------------------------------------");
				} else {
					while (true) {
						System.out.print("[비밀번호 입력]: ");
						String accountPw = sc.next();
						int result2 = service.checkAccountPw(accountNo, accountPw);
						if (result2 == 0) {
							System.out.println("!![비밀번호를 잘못 입력하셨습니다.]!!");
							System.out.println(" [다시 입력해주세요] ");
							System.out.println("--------------------------------------");
						} else {
							System.out.print("[출금 금액 입력]: ");
							int wMoney = sc.nextInt();
							sc.nextLine();

							Account a = service.selectAccount(accountNo);
							if (a.getBalance() < wMoney) {
								System.out.println("!![잔액이 부족합니다]!!");
								break;
							} else {
								int result3 = service.withdraw(accountNo, wMoney);
								if (result3 > 0) {
									System.out.println("***[출금 성공]***");
									break;
								} else {
									System.out.println("!![출금 실패]!!");
									break;
								}
							}
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("!![출금 중 예외 발생]!!");
			e.printStackTrace();
		}
	}

	private void remit() {
		System.out.println("***[송금]***");
		try {
			System.out.println("[송금하실 계좌를 입력하세요.]");

			printAccount(accountList);
			System.out.println("--------------------------------------");
			while (true) {
				System.out.print("[송금하는 계좌 입력]: ");
				String accountNo = sc.next();
				int result = service.checkAccountNo(accountNo);
				if (result == 0) {
					System.out.println("!![해당 계좌가 존재하지 않습니다]!!");
					System.out.println(" [다시 입력해주세요] ");
					System.out.println("--------------------------------------");
				} else {
					while (true) {
						System.out.print("[송금받는 계좌 입력]: ");
						String accountNo2 = sc.next();
						int result2 = service.checkAccountNo(accountNo2);
						if (result2 == 0) {
							System.out.println("!![해당 계좌가 존재하지 않습니다]!!");
							System.out.println(" [다시 입력해주세요] ");
							System.out.println("--------------------------------------");
						} else {
							System.out.print("[송금 금액 입력]: ");
							int rMoney = sc.nextInt();
							sc.nextLine();

							Account a = service.selectAccount(accountNo);

							if (a.getBalance() < rMoney) {
								System.out.println("!![잔액이 부족합니다]!!");
								break;
							} else {
								while (true) {
									System.out.print("[비밀번호 입력]: ");
									String accountPw = sc.next();

									int result3 = service.checkAccountPw(accountNo, accountPw);
									if (result3 == 0) {
										System.out.println("!![비밀번호를 잘못 입력하셨습니다.]!!");
										System.out.println(" [다시 입력해주세요] ");
										System.out.println("--------------------------------------");
									} else {
										System.out.print("[정말 송금하시겠습니까?(Y/N)]: ");
										char ch = sc.next().toUpperCase().charAt(0);
										if (ch == 'Y') {
											int remit1 = service.remit(accountNo, rMoney);
											int remit2 = service.remit2(accountNo2, rMoney);
											if (remit1 + remit2 > 1) {
												System.out.println("***[송금 성공]***");
												break;
											} else {
												System.out.println("!![송금 실패]!!");
												break;
											}
										} else {
											System.out.println("...[송금 취소]...");
											break;
										}
									}
								}
								break;
							}
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("!![송금 중 예외 발생]!!");
			e.printStackTrace();
		}
	}
}