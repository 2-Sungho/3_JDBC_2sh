package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.emp.model.vo.Employee;

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
	String driver="oracle.jdbc.driver.OracleDriver";
	
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="kh_lsh";
	private String pw="kh1234";
	
	
	/** 전체 사원 정보 조회 DAO
	 * @return empList
	 */
	public List<Employee> sellectAll() {
		// 1. 결과 저장용 변수 선언
		List<Employee> empList=new ArrayList<>();
		
		try {
			Class.forName(driver); // 오라클 jdbc 드라이버 객체 메모리 로드
			conn=DriverManager.getConnection(url,user,pw);
			// 오라클 jdbc 드라이버 객체를 이용하여 Connection
			stmt=conn.createStatement();
		
			String sql="SELECT EMP_ID ,EMP_NAME ,EMP_NO ,EMAIL ,PHONE ,NVL(DEPT_TITLE,'부서없음'),JOB_NAME, SALARY"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID=DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)";
			
			rs=stmt.executeQuery(sql);
			
			// 3. 조회 결과를 얻어와 한행씩 접근하여 Employee객체 생성 후 컬럼 값 옮겨 담기
			while(rs.next()) {
				int empId=rs.getInt("EMP_ID");
				// EMP_ID 컬럼은 문자열 컬럼이지만 저장된 값들이 모두 숫자형태
				// -> DB에서 자동으로 형변환 진행해서 얻어옴
				String empName=rs.getString("EMP_NAME");
				String empNo=rs.getString("EMP_NO");
				String email=rs.getString("EMAIL");
				String phone=rs.getString("PHONE");
				String deptTitle=rs.getString("NVL(DEPT_TITLE,'부서없음')");
				String jobName=rs.getString("JOB_NAME");
				int salary=rs.getInt("SALARY");
				// List 담기
				empList.add(new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		// 5. 결과 반환
		return empList;
	}
	
}
