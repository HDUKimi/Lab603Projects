package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

/***
 * 
 * @author tiffy
 *  id content state result
 */
public class TestCase {
	String  testCaseID;
	String  content;
	String  state;
	String  result;
	String  detail;
	
	public TestCase() {
	}

	public TestCase(String testCaseID, String content, String state, String result, String detail) {
		this.testCaseID = testCaseID;
		this.content = content;
		this.state = state;
		this.result = result;
		this.detail = detail;
	}



	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TestCase [testCaseID=" + testCaseID + ", content=" + content + ", state=" + state + ", result=" + result
				+ ", detail=" + detail + "]";
	}

}
