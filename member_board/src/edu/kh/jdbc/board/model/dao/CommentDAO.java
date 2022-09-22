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

import edu.kh.jdbc.board.model.vo.Comment;

public class CommentDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public CommentDAO() {
		try {
			prop=new Properties();
			prop.loadFromXML(new FileInputStream("comment-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 특정 게시글의 댓글 목록 조회
	 * @param conn
	 * @param boardNo
	 * @return commentList
	 * @throws Exception
	 */
	public List<Comment> selectCommentList(Connection conn, int boardNo) throws Exception {
		List<Comment> commentList=new ArrayList<>();
		try {
			String sql=prop.getProperty("selectCommentList");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Comment c=new Comment();
				c.setCommentNo(rs.getInt("COMMENT_NO"));
				c.setCommentCotent(rs.getString("COMMENT_CONTENT"));
				c.setMemberNo(rs.getInt("MEMBER_NO"));
				c.setMemberName(rs.getString("MEMBER_NM"));
				c.setCreateDate(rs.getString("CREATE_DT"));
				
				commentList.add(c);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentList;
	}
}
