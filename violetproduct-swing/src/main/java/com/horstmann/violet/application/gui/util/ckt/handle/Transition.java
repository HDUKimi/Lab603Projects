package com.horstmann.violet.application.gui.util.ckt.handle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3628461472940745522L;
	
	private String name;//�������Ǩ������/////
	private int id;//�������//////
	private String in;//////
	private String out;//////
	private String condition;///////
	private ArrayList<String> EventSet;//�¼�����	
	private String target;//ת��������Ŀ��״̬����
	private String source;//ת�������Դ״̬����
	private ArrayList<String> ResetClockSet;//Ǩ���и�λ��ʱ���������(ʱ����Ϊ0)
	private DBM_element[][] constraintDBM;//Ǩ���ϵ�ʱ��Լ������
	private ArrayList<String> types;
	private ArrayList<String> typeIds;
	private List<String> result;//Ǩ�ƽ������   
	private String limit;//Ǩ����������
	private String tranTimeName;//ȥ������ʱ���ӳ�Ǩ����Ǩ������ӵ�t��
	boolean visited = false; 

	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public String getTranTimeName() {
		return tranTimeName;
	}
	public void setTranTimeName(String tranTimeName) {
		this.tranTimeName = tranTimeName;
	}
	public List<String> getResult() {
		return result;
	}
	public void setResult(List<String> result2) {
		this.result = result2;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}	
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//private ArrayList<DBM_element[][]> com_constraint;//Ǩ����ʱ��Լ���Ĳ���
	//private ArrayList<String> SetClock;//Ǩ�������õ�ʱ�ӣ�y=2��
	
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public ArrayList<String> getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(ArrayList<String> typeIds) {
		this.typeIds = typeIds;
	}
	/*public ArrayList<DBM_element[][]> getCom_constraint() {
		return com_constraint;
	}
	public void setCom_constraint(ArrayList<DBM_element[][]> com_constraint) {
		this.com_constraint = com_constraint;
	}*/
	/*public ArrayList<String> getSetClock() {
		return SetClock;
	}
	public void setSetClock(ArrayList<String> setClock) {
		SetClock = setClock;
	}*/
	public ArrayList<String> getEventSet() {
		return EventSet;
	}
	public void setEventSet(ArrayList<String> eventSet) {
		EventSet = eventSet;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public ArrayList<String> getResetClockSet() {
		return ResetClockSet;
	}
	public void setResetClockSet(ArrayList<String> resetClockSet) {
		ResetClockSet = resetClockSet;
	}
	public DBM_element[][] getConstraintDBM() {
		return constraintDBM;
	}
	public void setConstraintDBM(DBM_element[][] constraintDBM) {
		this.constraintDBM = constraintDBM;
	}
	
	@Override
	public String toString() {
		return "Ǩ�� " + id + " [id=" + id + ", name=" + name + ", source=" + source + ", target=" + target + ", in=" + in + ", out=" + out + ", condition=" + condition
				+ "]";
	}
	
	public String toString1() {
		return "Ǩ�� " + id + " [id=" + id + ", name=" + name + ", source=" + source + ", target=" + target + ", limit=" + limit + "]";
	}
	
	public String toString2() {
		return "Ǩ�� " + id + " [id=" + id + ", name=" + name + ", source=" + source + ", target=" + target + ", result=" + result + "]";
	}
	
	
	
}

