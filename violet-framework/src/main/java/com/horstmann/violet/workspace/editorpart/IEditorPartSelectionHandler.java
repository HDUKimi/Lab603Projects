package com.horstmann.violet.workspace.editorpart;

import java.util.List;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.edge.IHorizontalChild;
import com.horstmann.violet.product.diagram.abstracts.edge.ISequenceTimeEdge;
import com.horstmann.violet.product.diagram.abstracts.edge.SEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphTool;

public interface IEditorPartSelectionHandler {

	public abstract void setSelectedElement(INode node);

	public abstract void setSelectedElement(ISequenceTimeEdge sequenceTimeEdge);
	
	public abstract void setSelectedElement(IEdge edge);
	
	public abstract void setSelectedElement(IHorizontalChild edge);
	
	public abstract void addSelectedElement(INode node);

	public abstract void addSelectedElement(IEdge edge);
	
	public abstract void addSelectedElement(ISequenceTimeEdge sequenceTimeEdge);
	
	public abstract void addSelectedElement(IHorizontalChild edge);

	public abstract void removeElementFromSelection(INode node);

	public abstract void removeElementFromSelection(IEdge edge);
	
	public abstract void removeElementFromSelection(ISequenceTimeEdge sequenceTimeEdge);
	
	public abstract void removeElementFromSelection(IHorizontalChild edge);

	public abstract boolean isElementAlreadySelected(INode node);

	public abstract boolean isElementAlreadySelected(IEdge edge);
	
	public abstract boolean isElmentAlreadySelected(ISequenceTimeEdge sequenceTimeEdge);

	public abstract boolean isElementAlreadySelected(IHorizontalChild edge);
	
	public abstract void clearSelection();
	
	public abstract INode getLastSelectedNode();

	public abstract IEdge getLastSelectedEdge();
	
	public abstract IHorizontalChild getLastSelectedLine();
	
	public abstract ISequenceTimeEdge getLastSelectedTimeEdge();

	public abstract boolean isNodeSelectedAtLeast();

	public abstract boolean isEdgeSelectedAtLeast();
	
	public abstract boolean isLineSelectedAtLeast();
	
	public abstract boolean isTimeEdgeSelectedAtLeast();

	public abstract List<INode> getSelectedNodes();
	
	public abstract List<IEdge> getSelectedEdges();
	
	public abstract List<IHorizontalChild> getSelectedLines();
	
	public abstract List<ISequenceTimeEdge> getSelectedTimeEdges();
	
	public abstract void setSelectedTool(GraphTool graphTool);
	
	public abstract GraphTool getSelectedTool();

}