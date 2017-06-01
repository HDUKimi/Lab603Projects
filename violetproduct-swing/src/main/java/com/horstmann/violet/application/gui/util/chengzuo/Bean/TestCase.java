package com.horstmann.violet.application.gui.util.chengzuo.Bean;
import java.io.Serializable;
import java.util.List;

/***
 * 
 * @author tiffy
 */
public class TestCase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7785205898142381116L;
	//测试用例ID
	String  testCaseID;
	//测试用例 激励链表
	List<myProcess> processList;
	//测试用例执行状态
	String  state;
	//测试用例执行结果
	TestCaseResult  result;
	//测试用例细节
	String  detail;
	//时间约束不等式
	List<String> limit;
	
	public TestCase() {
	}

	public TestCase(String testCaseID, String state, TestCaseResult result, String detail) {
		this.testCaseID = testCaseID;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public TestCaseResult getResult() {
		return result;
	}

	public void setResult(TestCaseResult result) {
		this.result = result;
	}

	public List<myProcess> getProcessList() {
		return processList;
	}

	public void setProcessList(List<myProcess> list) {
		this.processList = list;
	}
	
	public List<String> getLimit() {
		return limit;
	}

	public void setLimit(List<String> limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		String tmp = "TestCase [testCaseID=" + testCaseID + ", processList=\n";
		for (myProcess m : processList) {
			tmp = tmp + "\tmyProcess [processID=" + m.processID + ", processName=" + m.processName + ", processParam=" + m.processParam
					+ ", processStatus=" + m.processStatus + ", processExec=" + m.processExec + "]\n";
		}
		tmp = tmp + ", state=" + state + ", result="+ result + ", detail=" + detail + "]";
		return tmp;
	}
	/***
	 * 按照规定显示格式 转换测试用例格式
	 * @return
	 */
	public String showTestCase(){
		String tmp="测试用例ID:" + testCaseID + "\n  -->激励链表: [ ";
		for (myProcess m : processList) {
			tmp = tmp + "\n\t" +(m.isProcessExec() ? "√" : "x")
					+" 激励ID : " +m.processID 
					+ " 激励名称 : " + m.processName 
					+ "( 激励参数 : " + ((m.processParam == "NULL")?"空":m.getProcessParam())
					+ " 激励状态 :" + ((m.processStatus == "NULL")?"空":m.getProcessStatus())
					+")";
		}
		tmp = tmp + " ]\n  -->测试执行状态: [ " + state + " ]\n  -->结果状态: [ "+ result.getResultDetail()+" ]";
		return tmp;
	}
}