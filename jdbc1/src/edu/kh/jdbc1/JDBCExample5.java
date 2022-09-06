package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee2;

public class JDBCExample5 {

	public static void main(String[] args) {
		
		// 입사일을 입력("2022-09-06")받아
		// 입력받은 값보다 먼저 입사한 사원의
		// 이름, 입사일, 성별(M/F) 조회
		
		Scanner sc=new Scanner(System.in);
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user="kh_lsh"; 
			String pw="kh1234";
			
			conn=DriverManager.getConnection(url,user,pw);
			
			System.out.print("입사일 입력(YYYY-MM-DD): ");
			String ipHireDate=sc.nextLine();
			
			stmt=conn.createStatement();
			
			String sql="SELECT EMP_NAME 이름 ,TO_CHAR(HIRE_DATE,'YYYY\"년\" MM\"월\" DD\"일\"') 입사일,"
					+ " DECODE(SUBSTR(EMP_NO,8,1),'1','M','2','F') 성별"
					+ " FROM EMPLOYEE"
					+ " WHERE HIRE_DATE < TO_DATE('"+ipHireDate+"')";
			// 문자열 내부에 쌍따옴표 작성시 \"로 작성해야한다 -> Escape문자
			
			rs=stmt.executeQuery(sql);
			
			List<Employee2> list=new ArrayList<>();
			
			while(rs.next()) {
				// 기본 생성자로 employee객체 생성, 필드초기화X, setter를 이용해서 하나씩 세팅
				Employee2 e=new Employee2();
				
				e.setEmpName(rs.getString("이름"));
				e.setHireDate(rs.getString("입사일"));
				e.setGender(rs.getString("성별").charAt(0));
				// -> char자료형 매개변수가 필요
				// *중요*
				// java의 char: 문자 한개
				// DB의 char: 문자열
				// DB 컬럼 값을 char자료형에 저장하고 싶으면 String.charAt()을 이용한다
				
				list.add(e);
				
//				String empName=rs.getString("EMP_NAME");
//				String hireDate=rs.getString("HIRE_DATE");
//				char gender=rs.getString("GENDER").charAt(0);
				
//				list.add(new Employee2(empName, hireDate, gender));
			}
			if(list.isEmpty()) { // list.size()==0
				System.out.println("조회 결과 없음");
			}else {
				for(int i=0;i<list.size();i++) {
					System.out.printf("%02d) %s / %s / %c\n",
							i+1,
							list.get(i).getEmpName(),
							list.get(i).getHireDate(),
							list.get(i).getGender());
				}
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
	}
}
