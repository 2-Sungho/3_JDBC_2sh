package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.main.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MainDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그인
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {
		Member loginMember=null;
		try {
			String sql=prop.getProperty("login");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember=new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberId(memberId);
				loginMember.setMemberPw(memberPw);
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setaccountNo(memberPw);
				loginMember.setSecessionFlag(rs.getString("SECESSION_FL"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}

	/** 아이디 중복 체크
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("idDupCheck");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
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

	/** 회원가입
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("signUp");
			pstmt=conn.prepareStatement(sql);
			pstmt.setNString(1, member.getMemberId());
			pstmt.setNString(2, member.getMemberPw());
			pstmt.setNString(3, member.getMemberName());
			
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
