package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphTool;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphToolsBarButton;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphToolsBarPanel;

public class GraphToolPanel extends JPanel {

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private int graphtoolCount=0;
	private int selectedgraphtoolbutton=0;
//	private int i;
	
	private List<JButton> graphtoolbuttonGrouph;
	
	private List<GraphTool> nodeTools;
	private List<GraphTool> edgeTools;
	private List<GraphToolsBarButton> nodeBarButtons;
	private List<GraphToolsBarButton> edgeBarButtons;
	
	private JButton graphtoolButton;

	public GraphToolPanel(MainFrame mainFrame,IWorkspace workspace){
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		
		graphtoolbuttonGrouph = new ArrayList<JButton>();
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		nodeTools=this.workspace.getSideBar().getGraphToolsBar().getNodeTools();
		edgeTools=this.workspace.getSideBar().getGraphToolsBar().getEdgeTools();
		graphtoolCount=nodeTools.size()+edgeTools.size();
		
		nodeBarButtons=GraphToolsBarPanel.getToggleButtons(nodeTools);
		edgeBarButtons=GraphToolsBarPanel.getToggleButtons(edgeTools);

		System.out.println("----------------------------");
		for(final GraphToolsBarButton gtb:nodeBarButtons){
			graphtoolButton=new JButton();
			
//			graphtoolButton.setIcon(gtb.getTool().getIcon());
			graphtoolButton.setIcon(getGraphToolButtonIcon(gtb.getTool().getLabel()));
//			System.out.println(gtb.getTool().getLabel());
//			graphtoolButton.setText(gtb.getTool().getLabel());
			graphtoolbuttonGrouph.add(graphtoolButton);
			graphtoolButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					((GraphToolsBarPanel) workspace.getSideBar().getGraphToolsBar().getAWTComponent()).setOnClickSelectedButton(gtb);
					mainFrame.getConsolePartPanel().getTextarea().append("选中了 "+gtb.getTool().getLabel()+"\n");
				}
			});
		}
		
		for(final GraphToolsBarButton gtb:edgeBarButtons){
			graphtoolButton=new JButton();
//			graphtoolButton.setIcon(gtb.getTool().getIcon());
			graphtoolButton.setIcon(getGraphToolButtonIcon(gtb.getTool().getLabel()));
//			System.out.println(gtb.getTool().getLabel());
//			graphtoolButton.setText(gtb.getTool().getLabel());
			graphtoolbuttonGrouph.add(graphtoolButton);
			graphtoolButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					((GraphToolsBarPanel) workspace.getSideBar().getGraphToolsBar().getAWTComponent()).setOnClickSelectedButton(gtb);
					mainFrame.getConsolePartPanel().getTextarea().append("选中了 "+gtb.getTool().getLabel()+"\n");
				}
			});
		}
		
		for(final JButton b:graphtoolbuttonGrouph){
			b.setPreferredSize(new Dimension(21,21));
			b.setMaximumSize(new Dimension(21,21));
			b.setMinimumSize(new Dimension(21,21));
			
			b.setFocusable(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					b.setContentAreaFilled(true);
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					b.setContentAreaFilled(false);
					if(selectedgraphtoolbutton!=graphtoolbuttonGrouph.indexOf(b)){
						b.setBorderPainted(false);
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					b.setBorderPainted(true);
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
					for(JButton jb:graphtoolbuttonGrouph){
						jb.setBorderPainted(false);
					}
					b.setBorderPainted(true);
					selectedgraphtoolbutton=graphtoolbuttonGrouph.indexOf(b);
				}
			});
			b.setMargin(new Insets(5,5,5,5));
		}
		
		graphtoolbuttonGrouph.get(0).setBorderPainted(true);
		selectedgraphtoolbutton=0;
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,4,3));
		for(JButton b:graphtoolbuttonGrouph){
			this.add(b);
		}
		
	}

	private Icon getGraphToolButtonIcon(String label) {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = null;
		
		if(label.length()>23){
			if(label.substring(1, 7).equals("extend")){
				icon = new ImageIcon(path + "extend.png");
			}
			else if(label.substring(1, 8).equals("include")){
				icon = new ImageIcon(path + "include.png");
			}
			else if(label.substring(0, 14).equals("Generalization")){
				icon = new ImageIcon(path + "generalization.png");
			}
		}
		else if(label.equals("Select")){
			icon = new ImageIcon(path + "select.png");
		}
		else if(label.equals("Object lifeline")){
			icon = new ImageIcon(path + "object_lifeline.png");
		}
		else if(label.equals("Activation bar")){
			icon = new ImageIcon(path + "activation_bar.png");
		}
		else if(label.equals("Note")){
			icon = new ImageIcon(path + "note.png");
		}
		else if(label.equals("CombinedFragment")){
			icon = new ImageIcon(path + "combined_fragment.png");
		}
		else if(label.equals("Call / Create message")){
			icon = new ImageIcon(path + "call_message.png");
		}
		else if(label.equals("Return message")){
			icon = new ImageIcon(path + "return_message.png");
		}
		else if(label.equals("Note connector")){
			icon = new ImageIcon(path + "note_connector.png");
		}
		else if(label.equals("Time condition")){
			icon = new ImageIcon(path + "time_condition.png");
		}
		else if(label.equals("State_lifeline")){
			icon = new ImageIcon(path + "state_lifeline.png");
		}
		else if(label.equals("Send_MessageEdge")){
			icon = new ImageIcon(path + "send_message.png");
		}
		else if(label.equals("Scenario start")){
			icon = new ImageIcon(path + "scenario_start.png");
		}
		else if(label.equals("State")){
			icon = new ImageIcon(path + "state.png");
		}
		else if(label.equals("Scenario end")){
			icon = new ImageIcon(path + "scenario_end.png");
		}
		else if(label.equals("Event / Action")){
			icon = new ImageIcon(path + "event_ation.png");
		}
		else if(label.equals("Actor")){
			icon = new ImageIcon(path + "actor.png");
		}
		else if(label.equals("Use case")){
			icon = new ImageIcon(path + "use_case.png");
		}
		else if(label.equals("Interaction")){
			icon = new ImageIcon(path + "interaction.png");
		}
		
		
//		System.out.println("++++++++"+label);
		icon.setImage(icon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		
		return icon;
	}

}
