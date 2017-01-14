package com.horstmann.violet.application.gui.util.chengzuo.Bean;
import java.util.List;

/***
 * 
 * @author tiffy
 */
public class TestCase {
	//²âÊÔÓÃÀıID
	String  testCaseID;
	//²âÊÔÓÃÀı ¼¤ÀøÁ´±í
	List<myProcess> processList;
	//²âÊÔÓÃÀıÖ´ĞĞ×´Ì¬
	String  state;
	//²âÊÔÓÃÀıÖ´ĞĞ½á¹û
	String  result;
	//²âÊÔÓÃÀıÏ¸½Ú
	String  detail;
	
	public TestCase() {
	}

	public TestCase(String testCaseID, String state, String result, String detail) {
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
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
	 * °´ÕÕ¹æ¶¨ÏÔÊ¾¸ñÊ½ ×ª»»²âÊÔÓÃÀı¸ñÊ½
	 * @return
	 */
	public String showTestCase(){
		String tmp="²âÊÔÓÃÀıID:" + testCaseID + "\n  -->¼¤ÀøÁ´±í: [ ";
		for (myProcess m : processList) {
			tmp = tmp + "\n\t" +(m.isProcessExec() ? "¡Ì" : "x")
					+" ¼¤ÀøID : " +m.processID 
					+ " ¼¤ÀøÃû³Æ : " + m.processName 
					+ "( ¼¤Àø²ÎÊı : " + ((m.processParam == "NULL")?"¿Õ":m.getProcessParam())
					+ " ¼¤Àø×´Ì¬ :" + ((m.processStatus == "NULL")?"¿Õ":m.getProcessStatus())
					+")";
		}
		tmp = tmp + " ]\n  -->²âÊÔÖ´ĞĞ×´Ì¬: [ " + state + " ]\n  -->½á¹û×´Ì¬: [ "+ result+" ]";
		return tmp;
	}
}