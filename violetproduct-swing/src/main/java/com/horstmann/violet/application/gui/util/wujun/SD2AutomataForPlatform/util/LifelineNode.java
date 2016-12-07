package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("LifelineNode")
public class LifelineNode extends Entity{
	
	@XStreamAlias("ID")
	String EAID;
	
	Name name;
	/* 子节点的元素<text>timer</text>
	 * <name id="18">
	 * 	<text>timer</text>
	 * </name>
	*/
	@XStreamAlias("name")
	public class Name {
		@XStreamAsAttribute
		String id;
		@XStreamAlias("text")
		public String text;
		
	}
	
	// lifeline 包含的激活点 第一层
	// 还有包含的激活点
	@XStreamAlias("children")
	Children c;

	public String getEAID() {
		return EAID;
	}

	public void setEAID(String eAID) {
		EAID = eAID;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Children getC() {
		return c;
	}

	public void setC(Children c) {
		this.c = c;
	}
	
	
}
