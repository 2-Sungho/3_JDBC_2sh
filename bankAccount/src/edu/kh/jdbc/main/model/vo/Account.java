package edu.kh.jdbc.main.model.vo;

public class Account {

	private int memberNo;
	private String memberName;
	private int accountNo;
	private int accountPw;
	private int balance=0;

	public Account() {
	}

	public Account(int memberNo, String memberName, int accountNo, int accountPw, int balance) {
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

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getAccountPw() {
		return accountPw;
	}

	public void setAccountPw(int accountPw) {
		this.accountPw = accountPw;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	
	
}
