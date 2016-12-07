/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.product.diagram.timing;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.edge.IHorizontalChild;
import com.horstmann.violet.product.diagram.abstracts.edge.ISequenceTimeEdge;
import com.horstmann.violet.product.diagram.abstracts.edge.SEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.common.NoteEdge;
import com.horstmann.violet.product.diagram.common.NoteNode;

/**
 * A UML sequence diagram.
 */
public class TimingDiagramGraph extends AbstractGraph
{

    
    public TimingDiagramGraph(){
    	
    }
    public List<INode> getNodePrototypes()
    {
        return NODE_PROTOTYPES;
    }

    public List<IEdge> getEdgePrototypes()
    {
        return EDGE_PROTOTYPES;
    }
  
    private static final List<INode> NODE_PROTOTYPES = new ArrayList<INode>();
    private static final List<IEdge> EDGE_PROTOTYPES = new ArrayList<IEdge>();
 
    static
    {
        ResourceBundle rs = ResourceBundle.getBundle(TimingDiagramConstant.TIMING_DIAGRAM_STRINGS, Locale.getDefault());       
        StateLifeline lifelineNode = new StateLifeline();//新创建一条生命线
        lifelineNode.setToolTip(rs.getString("node0.tooltip"));//Object lifeline
        NODE_PROTOTYPES.add(lifelineNode);  
    	//定义一个内部类，用于node的排序，用于解决当bound扩大的时候造成bound有交叉的部分
//        NODE_PROTOTYPES.sort(new Comparator<Object>() {
//        	@Override
//        	public int compare(Object o1,Object o2){
//        		return (int)(((StateLifeline)o1).getLocation().getY()-((StateLifeline)o2).getLocation().getY());
//        	}
//		});
//        reLocation(NODE_PROTOTYPES);
        SendMessageEdge sMessageEdge =new SendMessageEdge();
        sMessageEdge.setToolTip(rs.getString("edge0.tooltip"));
        EDGE_PROTOTYPES.add(sMessageEdge);                    
    }

	//定义一个内部类，用于node的排序，用于解决当bound扩大的时候造成bound有交叉的部分
//    public class StateLifeLineByComparator implements Comparator<Object>{
//    	@Override
//    	public int compare(Object o1,Object o2){
//    		return (((StateLifeline)o1).getLocation().getY()-((StateLifeline)o2).getLocation().getY())>0?1:0;
//    	}
//    }
    
    //重新定义一个方法，对图中的StateLifeLine，以及里面的图中的状态重新定位
//    public static List<INode> reLocation(List<INode> list){//因为传过来的数据从小到大(从高状态-->低状态)
//    	List<INode> reList=null;
//    	List<INode> reverseList=new ArrayList<INode>(); //用于接收(低状态---->高状态)
//    	int len=list.size();
//    	for(int a=len-1;a>=0;a--){
//    		reverseList.add(list.get(a));
//    	}
//    	for(int i=1;i<len;i++){
//    		INode node1=reverseList.get(i);
//    		INode node2=reverseList.get(i-1);
//    		Rectangle2D rec1=node1.getBounds();
//    		double y1=rec1.getY();
//    		Rectangle2D rec2=node2.getBounds();
//    		double y2=rec2.getMaxX();
//    		if(y2<y1){
//    			double gap=y1-y2;
//    			node2.setLocation(new Point.Double(node2.getLocation().getX(),(node2.getLocation().getY()-gap)));
//    			List<IHorizontalChild> listChild=node2.getChild().gethorizontalChild();
//    			for(IHorizontalChild child:listChild){
//    				child.setStart(new Point.Double(child.getStart().getX(),child.getStart().getY()-gap));
//    				child.setEnd(new Point.Double(child.getEnd().getX(),child.getEnd().getY()-gap));
//    			}
//    		}
//    	}
//    	return null;
//    	
//    }

}
