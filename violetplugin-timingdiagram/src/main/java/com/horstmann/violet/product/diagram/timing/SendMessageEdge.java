package com.horstmann.violet.product.diagram.timing;

import java.awt.geom.Line2D;

import com.horstmann.violet.product.diagram.abstracts.edge.Message;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;

public class SendMessageEdge extends Message{
	
	 @Override
	    public ArrowHead getEndArrowHead()//��ȡβ����ͷ
	    {
	       
	        return ArrowHead.BLACK_TRIANGLE;
	    }	
}
