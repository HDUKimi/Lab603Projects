package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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

public class TranMessageColorize {
	// ���ڶ�̬�ĸı�Ǩ�Ƶ���ɫ(������ͷ)
	public void ColorizeTran(List<UppaalTransition> list, IWorkspace workspace) {
		// ���edge��list�ļ���
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		// �ָ���ǰ�����
		for (IEdge edge : edges) {
			IEdgeColorable colorableEdge = (IEdgeColorable) edge;
			colorableEdge.setEdgeColor(Color.BLACK);
		}
		for (UppaalTransition upt : list) {
			String name = upt.getName();
			for (IEdge edge : edges) {
				String labelName = ((TransitionEdge) edge).getLabel();
//				if (name.equals(labelName)) {
				if (labelName.contains(name)) {
					if (edge != null && IEdgeColorable.class.isInstance(edge)) {
						IEdgeColorable colorableEdge = (IEdgeColorable) edge;
						colorableEdge.setEdgeColor(Color.RED);
					}
				}
			}
		}
	}

	// ���ڸı�״̬��Ǩ��һ��·������ɫ
	public void ColorizeTranAndState(List<PathTuple> pathTupleList, IWorkspace workspace) {
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		// ���ڻظ��������ɫ
		for (IEdge edge : edges) {
			IEdgeColorable colorableEdge = (IEdgeColorable) edge;
			colorableEdge.setEdgeColor(Color.BLACK);
		}
		// ���ڻָ��������ɫ
		for (INode node : nodes) {// (Color.WHITE, new Color(191,191,191), new
									// Color(51,51,51)Ĭ�ϵ���ɫ
			IColorable colorableNode = (IColorable) node;
			colorableNode.setBackgroundColor(Color.WHITE);
			colorableNode.setBorderColor(new Color(191, 191, 191));
			colorableNode.setTextColor(new Color(51, 51, 51));
		}
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
				if (labelName.contains(tranName)) {
					if (edge != null && IEdgeColorable.class.isInstance(edge)) {
						
						System.out.println(((CircularNode)edge.getStart()).getName()+"-----------"+((CircularNode)edge.getEnd()).getName());
						
						IEdgeColorable colorableEdge = (IEdgeColorable) edge;
						colorableEdge.setEdgeColor(Color.RED);
					}
				}
			}
		}

	}
}
