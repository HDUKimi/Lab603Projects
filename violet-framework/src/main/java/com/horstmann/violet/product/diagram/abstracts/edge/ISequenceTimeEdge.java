package com.horstmann.violet.product.diagram.abstracts.edge;


import java.awt.Graphics2D;
import java.io.Serializable;
public interface ISequenceTimeEdge extends Cloneable,Serializable{
	/*
	 * ��ȡ��ʼ��
	 */
	IEdge getStartEdge();
	/*
	 * ������ʼ��
	 */
	void setStartEdge(IEdge edge);
	/*
	 * ��ȡ��ֹ��
	 */
    IEdge getEndEdge();
    /*
     * ������ֹ��
     */
    void setEndEdge(IEdge edge);
    /*
     * ����ʱ��Լ��
     */
    void setTimeCondition(String condition);
    /*
     * ��ȡʱ��Լ��
     */
    String getTimeCondition();
    
    ISequenceTimeEdge clone();
    
    void draw(Graphics2D g2);
}