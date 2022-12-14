package edu.kh.jdbc.main.model.vo;

public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String accountNo;
	private String secessionFlag;
	
	public Member() {
	}

	public Member(int memberNo, String memberId, String memberPw, String memberName, String accountNo,
			String secessionFlag) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.accountNo = accountNo;
		this.secessionFlag = secessionFlag;
	}
	

	public Member(String memberId, String memberPw, String memberName) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
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

	public void setaccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getSecessionFlag() {
		return secessionFlag;
	}

	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}
	
	
}
