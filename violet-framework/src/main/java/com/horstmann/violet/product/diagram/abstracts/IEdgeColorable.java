package com.horstmann.violet.product.diagram.abstracts;

import java.awt.Color;
import java.awt.Stroke;

public interface IEdgeColorable {
	//关于边
    public void setEdgeColor(Color color);
    public Color getEdgeColor();
    //用于改变消息的粗细
    public Stroke getEdgeStroke();
    public void setEdgeStroke(Stroke stroke);
}
