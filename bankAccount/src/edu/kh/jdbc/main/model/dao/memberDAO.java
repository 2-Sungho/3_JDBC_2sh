package edu.kh.jdbc.main.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.main.model.vo.Account;
import edu.kh.jdbc.main.model.vo.Member;

public class memberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public memberDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 내 정보(계좌) 조회
	 * @param conn
	 * @param loginMember
	 * @return accountList
	 * @throws Exception
	 */
	public List<Account> selectInfo(Connection conn, Member loginMember) throws Exception {
		List<Account> accountList = new ArrayList<>();
		try {
			String sql=prop.getProperty("selectInfo");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.getMemberId());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Account account=new Account();
				account.setMemberNo(loginMember.getMemberNo());
				account.setMemberName(loginMember.getMemberName());
				account.setAccountNo(rs.getString("ACCOUNT_NO"));
				account.setAccountPw(rs.getString("ACCOUNT_PW"));
				account.setBalance(rs.getInt("BALANCE"));
				
				accountList.add(account);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return accountList;
	}

	/** 내 계좌 등록
	 * @param conn
	 * @param account
	 * @return result
	 * @throws Exception
	 */
	public int insertAccount(Connection conn, Account account) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("insertAccount");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, account.getMemberNo());
			pstmt.setString(2, account.getMemberName());
			pstmt.setString(3, account.getAccountNo());
			pstmt.setString(4, account.getAccountPw());
			
			result=pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deposit(Connection conn, String accountNo, int dMoney) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("deposit");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dMoney);
			pstmt.setString(2, accountNo);
			
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int withdraw(Connection conn, String accountNo, int wMoney) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("withdraw");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, wMoney);
			pstmt.setString(2, accountNo);
			
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int remit(Connection conn, String accountNo,int rMoney) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("remit1");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rMoney);
			pstmt.setString(2, accountNo);
			
			result=pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int remit2(Connection conn, String accountNo2, int rMoney) throws Exception {
		int result2=0;
		try {
			String sql2=prop.getProperty("remit2");
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, rMoney);
			pstmt.setString(2, accountNo2);
			
			result2=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result2;
	}

	public List<Account> selectAll(Connection conn) throws Exception {
		List<Account> allAccount=new ArrayList<>();
		try {
			String sql=prop.getProperty("selectAll");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Account account=new Account();
				account.setMemberNo(rs.getInt("MEMBER_NO"));
				account.setMemberName(rs.getString("MEMBER_NM"));
				account.setAccountNo(rs.getString("ACCOUNT_NO"));
				account.setBalance(rs.getInt("BALANCE"));
				
				allAccount.add(account);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return allAccount;
	}

	public Account selectAccount(Connection conn, String accountNo) throws Exception {
		Account account=null;
		try {
			String sql=prop.getProperty("selectAccount");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				account=new Account();
				account.setMemberNo(rs.getInt("MEMBER_NO"));
				account.setMemberName(rs.getString("MEMBER_NM"));
				account.setAccountNo(accountNo);
				account.setAccountPw(rs.getString("ACCOUNT_PW"));
				account.setBalance(rs.getInt("BALANCE"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return account;
	}

	public int checkAccountNo(Connection conn, String accountNo) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("checkAccountNo");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int checkAccountPw(Connection conn, String accountPw) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("checkAccountPw");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, accountPw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	
	
}
