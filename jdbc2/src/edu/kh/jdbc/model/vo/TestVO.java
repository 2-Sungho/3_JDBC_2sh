package edu.kh.jdbc.model.vo;

public class TestVO {

	private int testNO;
	private String testTitle;
	private String testContent;
	
	public TestVO() { }

	public TestVO(int testNO, String testTitle, String testContent) {
		super();
		this.testNO = testNO;
		this.testTitle = testTitle;
		this.testContent = testContent;
	}

	public int getTestNO() {
		return testNO;
	}

	public void setTestNO(int testNO) {
		this.testNO = testNO;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	@Override
	public String toString() {
		return "TestVO [testNO=" + testNO + ", testTitle=" + testTitle + ", testContent=" + testContent + "]";
	}
	
	
}
