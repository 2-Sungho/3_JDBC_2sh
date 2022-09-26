package edu.kh.jdbc.main.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.main.model.vo.Member;

public class MainService {
	
	private MainDAO dao = new MainDAO();

	/** 로그인
	 * @param memberId
	 * @param memberPw
	 * @return member
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		Connection conn=getConnection();
		Member member=dao.login(conn,memberId,memberPw);
		close(conn);
		return member;
	}

	/** 아이디 중복 체크
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception {
		Connection conn=getConnection();
		int result=dao.idDupCheck(conn, memberId);
		close(conn);
		return result;
	}

	/** 회원가입
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		Connection conn=getConnection();
		int result=dao.signUp(conn,member);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	

}
