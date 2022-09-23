package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;

public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public BoardDAO() {
		try {
			prop=new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 상세조회
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllboard(Connection conn) throws Exception {
		List<Board> boardList=new ArrayList<>();
		try {
			String sql=prop.getProperty("selectAllboard");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setMemberName(rs.getString("MEMBER_NM"));
				b.setReadCount(rs.getInt("READ_COUNT"));
				b.setCreateDate(rs.getString("CREATE_DT"));
				b.setCommentCount(rs.getInt("COMMENT_COUNT"));
				boardList.add(b);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return boardList;
	}

	/** 게시글 상세 조회(게시글 내용 조회)
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception {
		Board board=null;
		try {
			String sql=prop.getProperty("selectBoard");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board=new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	/** 상세 조회된 게시글의 조회 수 증가
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("increaseReadCount");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 수정
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("updateBoard");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 삭제
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		int result=0;
		try {
			String sql=prop.getProperty("deleteBoard");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result=pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
