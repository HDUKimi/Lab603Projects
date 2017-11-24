package com.horstmann.violet.application.gui.util.chenzuo.Bean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/***
 * 
 * @author tiffy
 */
public class TestCase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7785205898142381116L;
	//ID
	String  testCaseID;
	//list of process
	List<myProcess> processList;
	//state of testcase
	String  state;
	//result of testcase
	TestCaseResult  result;
	//detail of testcase, String of socket received
	String  detail;
	//
	String exetime;
	//
	String expectResult="right";
	//被测程序结果
	String programExeResult;

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
	
	public String getExetime() {
		return exetime;
	}

	public void setExetime(String exetime) {
		this.exetime = exetime;
	}

	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}
	
	public String getProgramExeResult() {
		return programExeResult;
	}

	public void setProgramExeResult(String programExeResult) {
		this.programExeResult = programExeResult;
	}

	@Override
	public String toString() {
		String tmp = "TestCase [testCaseID=" + testCaseID + ", processList=\n";
		for (myProcess m : processList) {
			tmp = tmp + "\tmyProcess [processID=" + m.processID + ", processName=" + m.processName + ", processParam=" + m.processParam
					+ ", processStatus=" + m.processStatus + ", processExec=" + m.processExec + "]\n";
		}
		tmp = tmp + ", state=测试耗时:" + exetime + " ms, result="+ result + ",\n detail=" + detail + "]";
		return tmp;
	}
	/***
	 *  show format
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
		tmp = tmp + " ]\n  -->测试执行状态: [ 测试耗时:" + exetime + " ms ]\n"
				+ "  -->结果状态: [ "+ state+" ]\n"
				+ "  -->被测程序执行结果: [ "+ programExeResult+" ]";
		return tmp;
	}
	
	public String SpellFunctionalTestCase(){
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("testCaseID:"+testCaseID+"\n");
		sb.append("-->processList:[");
		for(myProcess process:processList){
			sb.append("\n\t[");
			sb.append("processID:"+process.getProcessID()+"\t");
			sb.append("processName:"+process.getProcessName()+"\t");
			sb.append("processParam:"+process.getProcessParam()+"\t");
			sb.append("processStatus:"+process.getProcessStatus()+"\t");
			sb.append("processExec:"+process.isProcessExec()+"]");
		}
		sb.append("\n]\n");
		sb.append("-->exetime:["+exetime+"]\n");
		sb.append("-->expectResult:["+expectResult+"]\n");
		sb.append("-->state:["+state+"]\n");
		sb.append("-->programExeResult:["+programExeResult+"]\n");
		
		return sb.toString();
	}
	
	public String SpellPerformanceTestCase(){
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("testCaseID:"+testCaseID+"\n");
		sb.append("-->processList:[");
		for(myProcess process:processList){
			sb.append("\n\t[");
			sb.append("processID:"+process.getProcessID()+"\t");
			sb.append("processName:"+process.getProcessName()+"\t");
			sb.append("processParam:"+process.getProcessParam()+"\t");
			sb.append("processStatus:"+process.getProcessStatus()+"\t");
			sb.append("processExec:"+process.isProcessExec()+"]");
		}
		sb.append("\n]\n");
		sb.append("-->performanceParam:[");
		sb.append("wind_speed:"+result.getWind_speed()+"\t");
		sb.append("takeoff_alt:"+result.getTakeoff_alt()+"\t");
		sb.append("battery_remaining:"+result.getBattery_remaining()+"\t");
		sb.append("time:"+result.getTime()+"]");
		sb.append("\n");
		sb.append("-->exetime:["+exetime+"]\n");
		sb.append("-->expectResult:["+expectResult+"]\n");
		sb.append("-->state:["+state+"]\n");
		
		return sb.toString();
	}	
	
	public String SpellTimeTestCase(){
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("testCaseID:"+testCaseID+"\n");
		sb.append("-->processList:[");
		for(myProcess process:processList){
			sb.append("\n\t[");
			sb.append("processID:"+process.getProcessID()+"\t");
			sb.append("processName:"+process.getProcessName()+"\t");
			sb.append("processParam:"+process.getProcessParam()+"\t");
			sb.append("processStatus:"+process.getProcessStatus()+"\t");
			sb.append("processExec:"+process.isProcessExec()+"]");
		}
		sb.append("\n]\n");
		sb.append("-->timeLimit:[");
//		for(Entry<String, Pair<String, String>> entry:result.getTimeLimit().showMap.entrySet()){
//			sb.append("\n\t[");
//			sb.append("uninquery:"+entry.getKey()+"\t");
//			sb.append("timeAttribute:"+entry.getValue().getFirst()+"\t");
//			sb.append("isOk:"+entry.getValue().getSecond()+"]");
//		}
		for(String time:limit){
			sb.append("\n\t[");
			sb.append("uninquery:"+time+"\t");
			Pair<String, String> pair=result.getTimeLimit().getShowMap().get(time);
			sb.append("timeAttribute:"+pair.getFirst()+"\t");
			sb.append("isOk:"+pair.getSecond()+"]");
		}
		sb.append("\n]\n");
		sb.append("-->exetime:["+exetime+"]\n");
		sb.append("-->expectResult:["+expectResult+"]\n");
		sb.append("-->state:["+state+"]\n");
		sb.append("-->programExeResult:["+programExeResult+"]\n");
		
		return sb.toString();
	}
	
}