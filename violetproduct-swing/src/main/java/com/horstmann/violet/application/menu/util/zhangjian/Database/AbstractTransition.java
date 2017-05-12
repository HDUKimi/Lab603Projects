package com.horstmann.violet.application.menu.util.zhangjian.Database;

import java.io.Serializable;
/**
 * 抽象测试序列中的消息的数据结构
 * @author ZhangJian
 *
 */
public class AbstractTransition  implements Serializable{
	private int tid;
	private String tname;
	private String targetID;
	private String target;
	private String sourceID;
	private String source;
	private String constraintDBM;
	private String type;//标记消息属于哪一类型的组合片段
	private String resetClockSet;//标注消息上的时钟复位集合
	private String tranTimeName;//去除抽象时间延迟迁移再迁移上添加的t；
	
	public String getTranTimeName() {
		return tranTimeName;
	}
	public void setTranTimeName(String tranTimeName) {
		this.tranTimeName = tranTimeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResetClockSet() {
		return resetClockSet;
	}
	public void setResetClockSet(String resetClockSet) {
		this.resetClockSet = resetClockSet;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	public String getSourceID() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
	public String getConstraintDBM() {
		return constraintDBM;
	}
	public void setConstraintDBM(String constraintDBM) {
		this.constraintDBM = constraintDBM;
	}
	
}
