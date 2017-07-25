package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalLocation;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;
import com.horstmann.violet.product.diagram.abstracts.IColorable;
import com.horstmann.violet.product.diagram.abstracts.IEdgeColorable;
import com.horstmann.violet.product.diagram.abstracts.Id;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.uppaal.CircularNode;
import com.horstmann.violet.product.diagram.uppaal.CircularStartNode;
import com.horstmann.violet.product.diagram.uppaal.TransitionEdge;
//���ڸı�Ǩ�Ƶ���ɫ
import com.horstmann.violet.workspace.IWorkspace;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TranMessageColorize {
	
	public void CleanColorize(IWorkspace workspace) {
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		// ���ڻظ��������ɫ
		for (IEdge edge : edges) {
			IEdgeColorable colorableEdge = (IEdgeColorable) edge;
			colorableEdge.setEdgeColor(Color.GRAY);
		}
		// ���ڻָ��������ɫ
		for (INode node : nodes) {// (Color.WHITE, new Color(191,191,191), new
									// Color(51,51,51)Ĭ�ϵ���ɫ
			IColorable colorableNode = (IColorable) node;
			colorableNode.setBackgroundColor(Color.WHITE);
			colorableNode.setBorderColor(new Color(191, 191, 191));
			colorableNode.setTextColor(new Color(51, 51, 51));
		}
	}
	
	// ���ڶ�̬�ĸı�Ǩ�Ƶ���ɫ(������ͷ)
	public void ColorizeTran(List<UppaalTransition> list, IWorkspace workspace) {
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		
		CleanColorize(workspace);
		
		for (UppaalTransition upt : list) {
			String name = upt.getName();
			for (IEdge edge : edges) {
				String labelName = ((TransitionEdge) edge).getLabel();
				
				String[] labeltext=labelName.split(" ");
				System.out.println("--------"+labeltext.length);
				for(int i=0;i<labeltext.length;i++){
					System.out.println(labeltext[i]);
					if (name.equals(labeltext[i])) {
						if (edge != null && IEdgeColorable.class.isInstance(edge)) {
							IEdgeColorable colorableEdge = (IEdgeColorable) edge;
							colorableEdge.setEdgeColor(Color.RED);
							
//							((TransitionEdge) edge).setLabel(labelName+"<br>-+- ");
							
							break;
						}
					}
				}
				
//				if (name.equals(labelName)) {
//				if (labelName.contains(name)) {
//					if (edge != null && IEdgeColorable.class.isInstance(edge)) {
//						IEdgeColorable colorableEdge = (IEdgeColorable) edge;
//						colorableEdge.setEdgeColor(Color.RED);
//					}
//				}
			}
		}
	}

	// ���ڸı�״̬��Ǩ��һ��·������ɫ
	public void ColorizeTranAndState(List<PathTuple> pathTupleList, IWorkspace workspace) {
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		
		CleanColorize(workspace);
		
		// �����Ҹı���ɫ
		for (PathTuple pt : pathTupleList) {// ���node��Ǩ�Ƶļ���
			System.out.println("333333333333333" + pathTupleList.size());
			UppaalLocation location = pt.getLocation();
			String locationName = location.getName();// node
			UppaalTransition transition = pt.getTransition();
			String tranName = transition.getName();// edge
			for (INode node : nodes) {
				// ��һ��node
				if (CircularStartNode.class.isInstance(node)) {
					String startName = ((CircularStartNode) node).getName();
					if (locationName.equals(startName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
						}
					}
				}

				// ���˵�һ��֮�������node
				if (CircularNode.class.isInstance(node)) {
					String labelName = ((CircularNode) node).getName();
					if (locationName.equals(labelName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
						}
					}
				}

			}
			for (IEdge edge : edges) {
				String labelName = ((TransitionEdge) edge).getLabel();

//				if (tranName.equals(labelName)) {
				
				String[] labeltext = labelName.split(" ");
				System.out.println("--------" + labeltext.length);
				for (int i = 0; i < labeltext.length; i++) {
					System.out.println(labeltext[i]);
					if (tranName.equals(labeltext[i])) {
						// if (labelName.contains(tranName)) {
						if (edge != null && IEdgeColorable.class.isInstance(edge)) {
							String startname = ((CircularNode) edge.getStart()).getName().toString();
							System.out.println(locationName + " ******* " + startname);
							if (locationName.equals(startname)) {
								IEdgeColorable colorableEdge = (IEdgeColorable) edge;
								colorableEdge.setEdgeColor(Color.RED);
								break;
							}
						}
					}
				}
			}
		}

	}
	
	public void ColorizeDFSTree(Automatic automatic, MainFrame mainFrame, IWorkspace workspace){
		
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		
		CleanColorize(workspace);
		
//		int trantextstate=mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getTrantextstate();
		int trantextstate=1;
		
		for (Transition t : automatic.getTransitionSet()) {
			String id;
			
			if(trantextstate==1){
				id = t.getId() + "";
			}
			else{
				id = t.getId()+"<br>"+t.getName();
			}

			for (IEdge edge : edges) {
				String labelName = ((TransitionEdge) edge).getLabel();
//				System.out.println(id+" - "+labelName+" - "+id.equals(labelName)+" - "+(index++)+" - "+(edge==null));
//				if (id.equals(labelName)) {
				if (labelName.contains(id)) {
					if (edge != null && IEdgeColorable.class.isInstance(edge)) {
						
						IEdgeColorable colorableEdge = (IEdgeColorable) edge;
						colorableEdge.setEdgeColor(Color.RED);
						
						colorableEdge.setEdgeStroke(new BasicStroke(5.0f));
						
						break;
					}
				}
			}
		}
		
		for(State s:automatic.getStateSet()){
			String id;
			
			if(trantextstate==1){
				id = s.getId() + "";
			}
			else{
				id = s.getId()+" "+s.getName();
			}
			
			
			for (INode node : nodes) {
				// ��һ��node
				if (CircularStartNode.class.isInstance(node)) {
					String startName = ((CircularStartNode) node).getName();
					if (id.equals(startName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
							
							break;
						}
					}
				}

				// ���˵�һ��֮�������node
				if (CircularNode.class.isInstance(node)) {
					String labelName = ((CircularNode) node).getName();
					if (id.equals(labelName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
							
							break;
						}
					}
				}

			}
		}
		
	}
	
	public void ColorizeDFSPath(Automatic automatic,MainFrame mainFrame,IWorkspace workspace){
		
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		
		CleanColorize(workspace);
		
		int trantextstate=mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getTrantextstate();
//		int trantextstate=1;
		
		for (Transition t : automatic.getTransitionSet()) {
			String id;
			String endid;
			
			int tid;
//			if(mainFrame.getHomeAllTabbedPanel().getStarttype()==3){//ʱ��Լ����ֺ�Ǩ��id������ظ�������ʱ������t1,t2
//				tid=Integer.valueOf(t.getTranTimeName().replace("t", ""));
//			}
//			else{
				tid=t.getId();
//			}
			
			if(trantextstate==1){
				id = tid + "";
				endid=t.getTarget();
			}
			else{
				id = tid+"<br>"+t.getName();
				endid=t.getTarget();
			}

			//1<br>setup()�������
			
			for (IEdge edge : edges) {
				String labelName = ((TransitionEdge) edge).getLabel();
				String endName=((CircularNode)((TransitionEdge) edge).getEnd()).getName();
//				System.out.println(id+" - ********* - "+endid+"    "+labelName+" - - - - "+endName);
//				System.out.println(id+" - "+labelName+" - "+id.equals(labelName)+" - "+(index++)+" - "+(edge==null));
//				if (id.equals(labelName)) {
				if ((id.equals(labelName)&&trantextstate==1)||(labelName.contains(id)&&trantextstate==0)) {
					System.out.println(id+" - ********* - "+endid+"    "+labelName+" - - - - "+endName);
					if (edge != null && IEdgeColorable.class.isInstance(edge)) {
						
						IEdgeColorable colorableEdge = (IEdgeColorable) edge;
						colorableEdge.setEdgeColor(Color.RED);
						
						break;
					}
				}
			}
		}
		
		for(State s:automatic.getStateSet()){
			String id;
			
			if(trantextstate==1){
				id = s.getId() + "";
			}
			else{
				id = s.getId()+" "+s.getName();
			}
			
			
			for (INode node : nodes) {
				// ��һ��node
				if (CircularStartNode.class.isInstance(node)) {
					String startName = ((CircularStartNode) node).getName();
					if (id.equals(startName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
							
							break;
						}
					}
				}

				// ���˵�һ��֮�������node
				if (CircularNode.class.isInstance(node)) {
					String labelName = ((CircularNode) node).getName();
					if (id.equals(labelName)) {
						if (node != null && IColorable.class.isInstance(node)) {
							IColorable colorableNode = (IColorable) node;
							colorableNode.setBackgroundColor(Color.RED);
							colorableNode.setBorderColor(Color.RED);
							colorableNode.setTextColor(Color.RED);
							
							break;
						}
					}
				}

			}
		}
		
	}
}


