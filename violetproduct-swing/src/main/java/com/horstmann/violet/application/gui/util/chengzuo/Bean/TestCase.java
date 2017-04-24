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
	//��������ID
	String  testCaseID;
	//�������� ��������
	List<myProcess> processList;
	//��������ִ��״̬
	String  state;
	//��������ִ�н��
	TestCaseResult  result;
	//��������ϸ��
	String  detail;
	
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
	 * ���չ涨��ʾ��ʽ ת������������ʽ
	 * @return
	 */
	public String showTestCase(){
		String tmp="��������ID:" + testCaseID + "\n  -->��������: [ ";
		for (myProcess m : processList) {
			tmp = tmp + "\n\t" +(m.isProcessExec() ? "��" : "x")
					+" ����ID : " +m.processID 
					+ " �������� : " + m.processName 
					+ "( �������� : " + ((m.processParam == "NULL")?"��":m.getProcessParam())
					+ " ����״̬ :" + ((m.processStatus == "NULL")?"��":m.getProcessStatus())
					+")";
		}
		tmp = tmp + " ]\n  -->����ִ��״̬: [ " + state + " ]\n  -->���״̬: [ "+ result.getResultDetail()+" ]";
		return tmp;
	}
}