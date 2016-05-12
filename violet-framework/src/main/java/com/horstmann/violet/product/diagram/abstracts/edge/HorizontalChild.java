
package com.horstmann.violet.product.diagram.abstracts.edge;


import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.thoughtworks.xstream.annotations.XStreamAlias;
//�������һ��ע�⣬��Ϊ��freamWork������޷�ʵ�����������͵�horizontal,
//�����������ϲ������ʵ�֣��ʽ�ԭʼ�ڵ��ǩ��Ϊhorizontalchild,Ϊ��֮����XML����.
public class HorizontalChild implements IHorizontalChild{
	
	private Point2D startPoint;
	private Point2D endPoint;
	private String state;
	private String condition;
	private String continuetime;

	public HorizontalChild() {
		// TODO Auto-generated constructor stub
		
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getContinuetime() {
		return continuetime;
	}

	public void setContinuetime(String continuetime) {
		this.continuetime = continuetime;
	}

	@Override
	public void setStart(Point2D startpoint) {
		// TODO Auto-generated method stub
		this.startPoint=startpoint;
	}
	@Override
	public void setEnd(Point2D endpoint) {
		// TODO Auto-generated method stub
		this.endPoint=endpoint;
	}
	@Override
	public Point2D getStart() {
		// TODO Auto-generated method stub
		return startPoint;
	}
	@Override
	public Point2D getEnd() {
		// TODO Auto-generated method stub
		return endPoint;
	}

	

}
