package com.horstmann.violet.application.gui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class AbstractTestCaseGenerationPanel extends JPanel {

	public DefaultMutableTreeNode abstractuppaalDiagram;
	public DefaultMutableTreeNode uppaalDiagram;
	public DefaultMutableTreeNode alluppaalDiagram;
	public JTree UppaalDiagramTree;
	private String[] abuppaallists;
	private String[] uppaallists;

	public AbstractTestCaseGenerationPanel() {
		initFileList();
		initUI();
		this.setLayout(new GridLayout(1, 1));
		add(UppaalDiagramTree);
	}

	private void initUI() {
		// TODO Auto-generated method stub

		alluppaalDiagram = new DefaultMutableTreeNode("ʱ���Զ����ļ�");
		uppaalDiagram = new DefaultMutableTreeNode("����ʱ��Ǩ�Ƶ�ʱ���Զ����ļ�");
		abstractuppaalDiagram = new DefaultMutableTreeNode("������ʱ��Ǩ�Ƶ��Զ����ļ�");

		for (String ud : uppaallists) {
			uppaalDiagram.add(new DefaultMutableTreeNode(ud));
		}
		for (String ab : abuppaallists) {
			abstractuppaalDiagram.add(new DefaultMutableTreeNode(ab));
		}
		alluppaalDiagram.add(uppaalDiagram);
		alluppaalDiagram.add(abstractuppaalDiagram);
		UppaalDiagramTree = new JTree(alluppaalDiagram);
	}

	private void initFileList() {
		abuppaallists = new String[] { "1.uppaaldiagram", "2.uppaaldiagram", "3.uppaaldiagram" };
		uppaallists = new String[] { "4.uppaaldiagram", "5.uppaaldiagram", "6.uppaaldiagram" };
	}

}
