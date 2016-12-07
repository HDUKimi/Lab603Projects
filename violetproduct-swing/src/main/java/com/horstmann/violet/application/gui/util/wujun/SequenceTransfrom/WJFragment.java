package com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom;

import java.util.ArrayList;

import org.dom4j.Element;

public class WJFragment implements Cloneable{//组合片段
	public Object clone() {   
		WJFragment o = null;   
        try {   
            o = (WJFragment) super.clone();   
        } catch (CloneNotSupportedException e) {   
            e.printStackTrace();   
        }   
        return o;   
    }   
	String fragType="null";//opt
	String fragId="null";//自己的id
	
	String fragCondition="null";//条件
		
	String BigId="null"; // 父片段的id
	String comId="null"; // 同一个操作域的话 alt的id
	
	String sourceId[];
	String targetId[];
	
	WJRectangle rectangle;
	
	ArrayList<String> containsMessageEAIDs = new ArrayList<>();
	
	public WJRectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(WJRectangle rectangle) {
		this.rectangle = rectangle;
	}

	public ArrayList<String> getContainsMessageIDs() {
		return containsMessageEAIDs;
	}

	public void setContainsMessageIDs(ArrayList<String> containsMessageId) {
		this.containsMessageEAIDs = containsMessageId;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getBigId() {
		return BigId;
	}

	public void setBigId(String bigId) {
		BigId = bigId;
	}


	


	
	public String getFragType() {
		return fragType;
	}

	public void setFragType(String fragType) {
		this.fragType = fragType;
	}

	public String getFragId() {
		return fragId;
	}

	public void setFragId(String fragId) {
		this.fragId = fragId;
	}

	

	public String getFragCondition() {
		return fragCondition;
	}

	public void setFragCondition(String fragCondition) {
		this.fragCondition = fragCondition;
	}

	
	public String[] getSourceId() {
		return sourceId;
	}

	public void setSourceId(String[] sourceId) {
		this.sourceId = sourceId;
	}

	public String[] getTargetId() {
		return targetId;
	}

	public void setTargetId(String[] targetId) {
		this.targetId = targetId;
	}
//	ArrayList<Element> frag=new ArrayList<Element>();
//	public ArrayList<Element> getFrag() {
//		return frag;
//	}
//
//	public void setFrag(ArrayList<Element> frag) {
//		this.frag = frag;
//	}
	
	
}
