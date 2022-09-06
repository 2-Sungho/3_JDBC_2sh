package edu.kh.jdbc1.model.vo;

public class Employee2 {

	private String empName;
	private String hireDate; // 조회된느 입사일의 데이터 타입이 문자열이기 때문에 String
	private char gender; // DB에는 문자하나를 나타내는 자료형이 없으므로 어떻게 처리해야될지 생각이 필요
	
	public Employee2() { }

	public Employee2(String empName, String hireDate, char gender) {
		super();
		this.empName = empName;
		this.hireDate = hireDate;
		this.gender = gender;
	}

	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return empName+" / "+hireDate+" / "+gender;
	}
	
		

}
