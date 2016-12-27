package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.Arrays;

public class UppaalTransition implements Cloneable {
	public Object clone() {
		UppaalTransition o = null;
		try {
			o = (UppaalTransition) super.clone();
			String[] nameText = o.getNameText().clone();
			o.setNameText(nameText);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	String EAid;


	int sourceId;
	String inner;
	String nameT = new String();
	String fromName;
	String toName;
	int targetId;
	String startTime;
	String endTime;
	public String[] Kind = new String[5];
	String[] nameText = new String[5];
	String nameS = new String();
	int outKindIndex;// �ڼ������֣�kind��synchronisation ���ⲿmessage��
	String duration;

	

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEAid() {
		return EAid;
	}

	public void setEAid(String eAid) {
		EAid = eAid;
	}
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getOutKindIndex() {
		return outKindIndex;
	}

	public void setOutKindIndex(int outKindIndex) {
		this.outKindIndex = outKindIndex;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getInner() {
		return inner;
	}

	public void setInner(String inner) {
		this.inner = inner;
	}

	public String getNameT() {
		return nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}


	public String[] getKind() {
		return Kind;
	}

	public void setKind(String[] kind) {
		Kind = kind;
	}

	public String[] getNameText() {
		return nameText;
	}

	public void setNameText(String[] nameText) {
		this.nameText = nameText;
	}

	public String getNameS() {
		return nameS;
	}

	public void setNameS(String nameS) {
		this.nameS = nameS;
	}

	@Override
	public String toString() {
		return "UppaalTransition [EAid=" + EAid + ", sourceId=" + sourceId + ", inner=" + inner + ", nameT=" + nameT
				+ ", fromName=" + fromName + ", toName=" + toName + ", targetId=" + targetId + ", time=" + startTime
				+ ", Kind=" + Arrays.toString(Kind) + ", nameText=" + Arrays.toString(nameText) + ", nameS=" + nameS
				+ ", outKindIndex=" + outKindIndex + ", duration=" + duration + "]";
	}
	
	

}
