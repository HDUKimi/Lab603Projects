package com.horstmann.violet.product.diagram.abstracts;

import java.awt.Color;
import java.awt.Stroke;

public interface IEdgeColorable {
	//���ڱ�
    public void setEdgeColor(Color color);
    public Color getEdgeColor();
    //���ڸı���Ϣ�Ĵ�ϸ
    public Stroke getEdgeStroke();
    public void setEdgeStroke(Stroke stroke);
}
