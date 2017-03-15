package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.product.diagram.abstracts.IColorable;
import com.horstmann.violet.product.diagram.abstracts.IEdgeColorable;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.uppaal.CircularNode;
import com.horstmann.violet.product.diagram.uppaal.CircularStartNode;
import com.horstmann.violet.product.diagram.uppaal.TransitionEdge;
import com.horstmann.violet.workspace.IWorkspace;

public class TranMessageText {

	public void TranTextToId(MainFrame mainFrame, IWorkspace workspace) {

		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();

		Map<String, String> stateNameToIdMap = mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel()
				.getStateNameToIdMap();
		Map<String, String> transitionNameToIdMap = mainFrame.getStepThreeCenterTabbedPane()
				.getTestCaseProcessTabbedPanel().getTransitionNameToIdMap();
		
		System.out.println("*******************************");
		for(String key:stateNameToIdMap.keySet()){
			System.out.println(key+"  "+stateNameToIdMap.get(key));
		}
		System.out.println("-------------------------------");
		for(String key:transitionNameToIdMap.keySet()){
			System.out.println(key+"  "+transitionNameToIdMap.get(key));
		}

		for (IEdge edge : edges) {
			String labelName = ((TransitionEdge) edge).getLabel();
			if(transitionNameToIdMap.get(labelName)!=null){
				if (edge != null && IEdgeColorable.class.isInstance(edge)) {
//					((TransitionEdge) edge).setLabel(transitionNameToIdMap.get(labelName));
					((TransitionEdge) edge).setLabel(labelName.split("<br>")[0]);
				}
			}
		}

		for (INode node : nodes) {
			// 第一个node
			if (CircularStartNode.class.isInstance(node)) {
				String startName = ((CircularStartNode) node).getName();
				if(stateNameToIdMap.get(startName)!=null){
				if (node != null && IColorable.class.isInstance(node)) {
//					((CircularNode) node).setName(stateNameToIdMap.get(startName));
					((CircularNode) node).setName(startName.split(" ")[0]);
				}
				}
			}

			// 除了第一个之外的其他node
			if (CircularNode.class.isInstance(node)) {
				String labelName = ((CircularNode) node).getName();
				if(stateNameToIdMap.get(labelName)!=null){
				if (node != null && IColorable.class.isInstance(node)) {
//					((CircularNode) node).setName(stateNameToIdMap.get(labelName));
					((CircularNode) node).setName(labelName.split(" ")[0]);
				}
				}
			}

		}
	}
	
	public void TranTextToName(MainFrame mainFrame, IWorkspace workspace) {

		Collection<IEdge> edges = workspace.getGraphFile().getGraph().getAllEdges();
		Collection<INode> nodes = workspace.getGraphFile().getGraph().getAllNodes();

		Map<String, String> stateIdToNameMap = mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().getStateIdToNameMap();
		Map<String, String> transitionIdToNameMap = mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().getTransitionIdToNameMap();

		System.out.println("*******************************");
		for(String key:stateIdToNameMap.keySet()){
			System.out.println(key+"  "+stateIdToNameMap.get(key));
		}
		System.out.println("-------------------------------");
		for(String key:transitionIdToNameMap.keySet()){
			System.out.println(key+"  "+transitionIdToNameMap.get(key));
		}
		
		for (IEdge edge : edges) {
			String labelName = ((TransitionEdge) edge).getLabel();
			if(transitionIdToNameMap.get(labelName)!=null){
			if (edge != null && IEdgeColorable.class.isInstance(edge)) {
				((TransitionEdge) edge).setLabel(transitionIdToNameMap.get(labelName));
			}
			}
		}

		for (INode node : nodes) {
			// 第一个node
			if (CircularStartNode.class.isInstance(node)) {
				String startName = ((CircularStartNode) node).getName();
				if(stateIdToNameMap.get(startName)!=null){
				if (node != null && IColorable.class.isInstance(node)) {
					((CircularNode) node).setName(stateIdToNameMap.get(startName));
				}
				}
			}

			// 除了第一个之外的其他node
			if (CircularNode.class.isInstance(node)) {
				String labelName = ((CircularNode) node).getName();
				if(stateIdToNameMap.get(labelName)!=null){
				if (node != null && IColorable.class.isInstance(node)) {
					((CircularNode) node).setName(stateIdToNameMap.get(labelName));
				}
				}
			}

		}
	}

}
