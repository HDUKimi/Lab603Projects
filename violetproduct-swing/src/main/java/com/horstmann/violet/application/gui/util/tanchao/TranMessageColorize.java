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
//用于改变迁移的颜色
import com.horstmann.violet.workspace.IWorkspace;

public class TranMessageColorize {
	// 用于动态的改变迁移的颜色(包含箭头)
	public void ColorizeTran(List<UppaalTransition> list, IWorkspace workspace) {
		// 获得edge的list的集合
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		// 恢复以前最初的
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

	// 用于改变状态和迁移一条路径的颜色
	public void ColorizeTranAndState(List<PathTuple> pathTupleList, IWorkspace workspace) {
		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();
		// 用于回复最初的颜色
		for (IEdge edge : edges) {
			IEdgeColorable colorableEdge = (IEdgeColorable) edge;
			colorableEdge.setEdgeColor(Color.BLACK);
		}
		// 用于恢复最初的颜色
		for (INode node : nodes) {// (Color.WHITE, new Color(191,191,191), new
									// Color(51,51,51)默认的颜色
			IColorable colorableNode = (IColorable) node;
			colorableNode.setBackgroundColor(Color.WHITE);
			colorableNode.setBorderColor(new Color(191, 191, 191));
			colorableNode.setTextColor(new Color(51, 51, 51));
		}
		// 捕获并且改变颜色
		for (PathTuple pt : pathTupleList) {// 获得node和迁移的集合
			System.out.println("333333333333333" + pathTupleList.size());
			UppaalLocation location = pt.getLocation();
			String locationName = location.getName();// node
			UppaalTransition transition = pt.getTransition();
			String tranName = transition.getName();// edge
			for (INode node : nodes) {
				// 第一个node
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

				// 除了第一个之外的其他node
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
