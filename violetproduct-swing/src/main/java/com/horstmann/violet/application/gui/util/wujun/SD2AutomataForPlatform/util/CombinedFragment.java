package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;


@XStreamAlias("CombinedFragment")
public class CombinedFragment extends Entity{
	// type ？？？
	@XStreamAlias("type")
	Type type;
	public class Type {
		@XStreamAsAttribute
		@XStreamAlias("id")
		public String id = "null";
		
		@XStreamAsAttribute
		@XStreamAlias("name")
		public String name = "null";
		
		@XStreamAsAttribute
		@XStreamAlias("reference")
		public String reference = "null";
	}
	
	
	Contidions conditions;
	@XStreamAlias("conditions")
	public class Contidions {
		@XStreamImplicit(itemFieldName = "string")
		public List<String> strings;
	}
	
	@XStreamAlias("ID")
	String EAID;
	
	@XStreamAlias("id")
	Id id;
	// 子节点的属性 <id id="41"/>
	@XStreamAlias("id")
	class Id {
		@XStreamAsAttribute
		public String id;
	}
	
	// fragmentParts
	@XStreamAlias("fragmentParts")
	FragmentParts fragmentParts;
	@XStreamAlias("fragmentParts")
	public class FragmentParts {
		@XStreamAsAttribute
		@XStreamAlias("class")
		public String clazz;
		@XStreamImplicit(itemFieldName = "com.horstmann.violet.product.diagram.abstracts.property.FragmentPart")
		public List<FragmentPart> fragmentParts;
	}
	
	// 组合片段的宽度
	@XStreamAlias("width")
	double width;
	// 组合片段的高度
	@XStreamAlias("height")
	double height;
	
	
	public Contidions getConditions() {
		return conditions;
	}
	public void setConditions(Contidions conditions) {
		this.conditions = conditions;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getEAID() {
		return EAID;
	}
	public void setEAID(String eAID) {
		EAID = eAID;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public FragmentParts getFragmentParts() {
		return fragmentParts;
	}
	public void setFragmentParts(FragmentParts fragmentParts) {
		this.fragmentParts = fragmentParts;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
	
}
