package com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom;

import java.util.ArrayList;

import org.dom4j.Element;

public class WJMessage implements Cloneable{//消息
	public Object clone() {   
		WJMessage o = null;   
        try {   
            o = (WJMessage) super.clone();   
        } catch (CloneNotSupportedException e) {   
            e.printStackTrace();   
        }   
        return o;   
    }   
	// 从xml读出
	String inFragId="null";//在哪个片段中
	String inFragName="null"; // 组合片段的名称 opt break...
	String id="null";   // 消息的id
	String sourceId="null"; // 激活点id
	String targetId="null"; // 激活点id
	String name="null"; // 消息的名字
	String fromId;   //从哪个对象到哪个对象
	String toId;	//从哪个对象到哪个对象
	
	String inString; // 输入
	String outString;// 输出
	
	// 在转换的时候才会生成
	String condition;// 条件 a=x--y=z
	String typeId; // 组合片段的[id-id-id]
	String RESET; 
	String receiveAndSend = "null";// 这个消息是一个自己到自己才不为空 如果是发出 则为！ 如果是接受 则为？
	
	// 测试用例排序需要的东西 和大项目无关
	String use;
	String def;
	
	
	String T1;
	String T2;
	String Energe;
	String R1;
	String R2;
	
	String SEQDC;
	String SEQDO;//状态约束
	String SEQTC;//消息约束
	String SEQTO;
	String DCBM;
	
	double pointY;
	
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getConditions() {
		return condition;
	}
	public void setConditions(String conditions) {
		this.condition = conditions;
	}
	public double getPointY() {
		return pointY;
	}
	public void setPointY(double pointY) {
		this.pointY = pointY;
	}
	public String getInString() {
		return inString;
	}
	public void setInString(String inString) {
		this.inString = inString;
	}
	public String getOutString() {
		return outString;
	}
	public void setOutString(String outString) {
		this.outString = outString;
	}
	public String getSEQDC() {
		return SEQDC;
	}
	public void setSEQDC(String sEQDC) {
		SEQDC = sEQDC;
	}
	public String getSEQDO() {
		return SEQDO;
	}
	public void setSEQDO(String sEQDO) {
		SEQDO = sEQDO;
	}
	public String getSEQTC() {
		return SEQTC;
	}
	public void setSEQTC(String sEQTC) {
		SEQTC = sEQTC;
	}
	public String getSEQTO() {
		return SEQTO;
	}
	public void setSEQTO(String sEQTO) {
		SEQTO = sEQTO;
	}
	public String getDCBM() {
		return DCBM;
	}
	public void setDCBM(String dCBM) {
		DCBM = dCBM;
	}
	public String getReceiveAndSend() {
		return receiveAndSend;
	}
	public void setReceiveAndSend(String receiveAndSend) {
		this.receiveAndSend = receiveAndSend;
	}
	public String getT1() {
		return T1;
	}
	public void setT1(String t1) {
		T1 = t1;
	}
	public String getT2() {
		return T2;
	}
	public void setT2(String t2) {
		T2 = t2;
	}
	public String getEnerge() {
		return Energe;
	}
	public void setEnerge(String energe) {
		Energe = energe;
	}
	public String getR1() {
		return R1;
	}
	public void setR1(String r1) {
		R1 = r1;
	}
	public String getR2() {
		return R2;
	}
	public void setR2(String r2) {
		R2 = r2;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	
	public String getInFragName() {
		return inFragName;
	}
	public void setInFragName(String inFragName) {
		this.inFragName = inFragName;
	}
	public String getInFragId() {
		return inFragId;
	}
	public void setInFragId(String inFragId) {
		this.inFragId = inFragId;
	}
	ArrayList<Element> frag=new ArrayList();
	
	public String getConnectorId() 
	{
		return id;
	}
	public void setConnectorId(String connectorId)
	{
		this.id = connectorId;
	}
	public String getSourceId()
	{
		return sourceId;
	}
	public void setSourceId(String sourceId)
	{
		this.sourceId = sourceId;
	}
	public String getTargetId() 
	{
		return targetId;
	}
	public void setTargetId(String tragetId) 
	{
		this.targetId = tragetId;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	
	
	
	public ArrayList<Element> getFrag()
	{
		return frag;
	}
	public void setFrag(ArrayList<Element> frag)
	{
		this.frag=frag;
	}
}
