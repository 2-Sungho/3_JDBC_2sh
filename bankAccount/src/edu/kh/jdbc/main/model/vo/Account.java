package edu.kh.jdbc.main.model.vo;

public class Account {

	private int memberNo;
	private String memberName;
	private String accountNo;
	private String accountPw;
	private int balance=0;

	public Account() {
	}

	public Account(int memberNo, String memberName, String accountNo, String accountPw, int balance) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.accountNo = accountNo;
		this.accountPw = accountPw;
		this.balance = balance;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountPw() {
		return accountPw;
	}

	public void setAccountPw(String accountPw) {
		this.accountPw = accountPw;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	
	
}
