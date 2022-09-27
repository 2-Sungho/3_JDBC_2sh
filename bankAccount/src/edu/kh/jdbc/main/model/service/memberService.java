package edu.kh.jdbc.main.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.main.model.dao.memberDAO;
import edu.kh.jdbc.main.model.vo.Account;
import edu.kh.jdbc.main.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class memberService {

	private memberDAO dao=new memberDAO();
	
	public List<Account> selectInfo(Member loginMember) throws Exception {
		Connection conn=getConnection();
		List<Account> accountList=dao.selectInfo(conn,loginMember);
		close(conn);
		return accountList;
	}

	public int insertAccount(Account account) throws Exception {
		Connection conn=getConnection();
		int result=dao.insertAccount(conn,account);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deposit(String accountNo, int dMoney) throws Exception {
		Connection conn=getConnection();
		int result=dao.deposit(conn,accountNo,dMoney);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int withdraw(String accountNo, int wMoney) throws Exception {
		Connection conn=getConnection();
		int result=dao.withdraw(conn,accountNo,wMoney);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int remit(String accountNo, int rMoney) throws Exception {
		Connection conn=getConnection();
		int result=dao.remit(conn,accountNo,rMoney);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int remit2(String accountNo2, int rMoney) throws Exception {
		Connection conn=getConnection();
		int result2=dao.remit2(conn,accountNo2,rMoney);
		if(result2>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result2;
	}

	public List<Account> selectAll() throws Exception {
		Connection conn=getConnection();
		List<Account> allAccount=dao.selectAll(conn);
		close(conn);
		return allAccount;
	}

	public Account selectAccount(String accountNo) throws Exception {
		Connection conn=getConnection();
		Account account=dao.selectAccount(conn,accountNo);
		close(conn);
		return account;
	}

	public int checkAccountNo(String accountNo) throws Exception {
		Connection conn=getConnection();
		int result=dao.checkAccountNo(conn,accountNo);
		close(conn);
		return result;
	}

	public int checkAccountPw(String accountPw) throws Exception{
		Connection conn=getConnection();
		int result=dao.checkAccountPw(conn,accountPw);
		close(conn);
		return result;
	}

}
