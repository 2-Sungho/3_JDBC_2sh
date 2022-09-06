package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// DAO(Data Access Object, 데이터 접근 객체)
// -> 데이터베이스에 접근(연결)하는 객체
// -> JDBC 코드 작성
public class EmployeeDAO {

	// JDBC 객체 참조 변수로 필드 선언(Class 내부에서 공통 사용)
	// 지역변수 Stack 변수가 비어있을 수 있음
	// 필드 Heap 변수가 비어있을 수 없음 -> JVM이 지정한 기본값으로 초기화 -> 참조형의 초기값은 Null , 별도 초기화 안해도 됨
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="kh_lsh";
	private String pw="kh1234";
	
}
