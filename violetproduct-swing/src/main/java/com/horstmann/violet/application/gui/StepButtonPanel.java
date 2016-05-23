package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.horstmann.violet.application.consolepart.ConsoleMessageTabbedPane;
import com.horstmann.violet.application.consolepart.ConsolePart;
import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;
import com.horstmann.violet.application.consolepart.ConsolePartTextArea;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class StepButtonPanel extends JPanel {
	private JButton step1button;
	private JButton step2button;
	private JButton step3button;
	private JButton step4button;
	private JButton step5button;
	private List<JButton> stepButtonGroup;
	private MainFrame mainFrame;
	private IWorkspace workspace;
	private ConsoleMessageTabbedPane consoleMessageTabbedPane;
    private ConsolePartTextArea consolePartTextArea;
    private ConsolePart consolePart;
    private JPanel operationPanel;
    private HashMap<Integer,List<Component>> stepTabbedPaneMap;
    private List<Component> currentTabbedPane;
	public StepButtonPanel(MainFrame mainFrame) {
		this.setBackground(Color.DARK_GRAY);
		this.mainFrame=mainFrame;
		stepTabbedPaneMap=new HashMap<Integer, List<Component>>();
		init();
	}

	private void init() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		initButton();
		GridBagConstraints s = new GridBagConstraints();// ����һ��GridBagConstraints��
		// ������������ӽ����������ʾλ��
		s.fill = GridBagConstraints.BOTH;
		s.anchor = GridBagConstraints.FIRST_LINE_START;
		s.insets = new Insets(0, 0, 0,0);

		this.add(step1button);
		this.add(step2button);
		this.add(step3button);
		this.add(step4button);
		this.add(step5button);
		s.gridx = 0;
		s.gridy = 0;
		s.weighty = 1;
		s.weightx=1;
		layout.setConstraints(step1button, s);// �������
		s.gridx = 0;
		s.gridy = 1;
		layout.setConstraints(step2button, s);
		s.gridx = 0;
		s.gridy = 2;
		layout.setConstraints(step3button, s);
		s.gridx = 0;
		s.gridy = 3;
		layout.setConstraints(step4button, s);
		s.gridx = 0;
		s.gridy = 4;
		layout.setConstraints(step5button, s);
		// TODO Auto-generated method stub

		SetButtonListener();

	}

	private void initButton() {
		step1button = new JButton();
		step2button = new JButton();
		step3button = new JButton();
		step4button = new JButton();
		step5button = new JButton();  
		step1button.setText("��һ��:UMLģ�ͽ���������");
		step2button.setText("�ڶ���:UMLģ��ת��ʱ���Զ���");
		step3button.setText("������:���������������");
		step4button.setText("���Ĳ�:��������ʵ����");
		step5button.setText("���岽:��������ʵ������֤");
		stepButtonGroup = new ArrayList<JButton>();
		stepButtonGroup.add(step1button);
		stepButtonGroup.add(step2button);
		stepButtonGroup.add(step3button);
		stepButtonGroup.add(step4button);
		stepButtonGroup.add(step5button);
		 operationPanel=mainFrame.getOpreationPart();
		 operationPanel.setLayout(new GridLayout(1,1));
		 consolePart=mainFrame.getConsolePart();	
		// TODO Auto-generated method stub

	}

	public void clearSelection() {
		for (JButton stepButton : stepButtonGroup) {
			stepButton.setFocusable(false);
			stepButton.setForeground(Color.BLACK);
		}

	}
	//���浱ǰTabҳ
	public void savaTabbedpane(int step)
	{
		JTabbedPane tabbedPane=mainFrame.getTabbedPane();
		int tabcount=tabbedPane.getTabCount();
		currentTabbedPane=new ArrayList<Component>();
		for(int i=0;i<tabcount;i++)
		{
		    Component component=tabbedPane.getComponentAt(i);
		    currentTabbedPane.add(component);
		}
		stepTabbedPaneMap.put(step,currentTabbedPane);
	}
	//��ȡÿ�������TAPҳ
	public List<Component> loadTabbedpane(int step)
	{
		return stepTabbedPaneMap.get(step);
	}
	public void clearConsolePart(){
		this.consolePart.getContentPane().removeAll();;
	}
  private void ClearOpreationPanel()
  {
	  this.operationPanel.removeAll();
  }
	private void SetButtonListener() {
		// TODO Auto-generated method stub
		step1button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				 
			 
				//����һ��ť����
				step1button.setFocusable(true);
				step1button.setForeground(Color.RED);				
				
				//�����Ƴ���ӭ����
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				//�޸�ԭ���ı������
				JLabel jLabel=new JLabel();
				jLabel.setText(step1button.getText());
				jLabel.setFont(new Font("����", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
//				labelpanel.add(new JButton("��ʼ"),new GBC(1, 0));
//				labelpanel.add(new JButton("��ͣ"),new GBC(2, 0));
				
				 
			    //��Ӳ������
				ClearOpreationPanel();
				operationPanel.add(mainFrame.getProjectTree());
						
				//��ӹ�����Ϣ���
				
			  
			    clearConsolePart();			    
			    consolePart.setTitle("UMLģ�ͽ���������Ϣ");
				consolePart.add(new ConsoleMessageTabbedPane("��ϸ��Ϣ",new JTextArea("UMLģ�����ڽ�����......\n\n\n\n\n\n")));	
				consolePart.setVisible(true);				
				mainFrame.revalidate();  			
			}
		});
		step2button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
			
				step2button.setFocusable(true);
				step2button.setForeground(Color.RED);
				//����
				JLabel jLabel=new JLabel();
				jLabel.setText(step2button.getText());
				jLabel.setFont(new Font("����", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(new JButton("��ʼ"),new GBC(1, 0));
				labelpanel.add(new JButton("��ͣ"),new GBC(2, 0));
				
				ClearOpreationPanel();
			    operationPanel.add(mainFrame.getModelTransformationPanel());
				
			    clearConsolePart();			    
			    consolePart.setTitle("UMLģ��ת��ʱ���Զ���������Ϣ");
				consolePart.add(new ConsoleMessageTabbedPane("��ϸ��Ϣ",new JTextArea("UMLģ������ת����......\n\n\n\n\n\n")));	
				consolePart.setVisible(true);
				
				mainFrame.revalidate();
			}
		});
		step3button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				step3button.setFocusable(true);
				step3button.setForeground(Color.RED);
			
				JLabel jLabel=new JLabel();
				jLabel.setText(step3button.getText());
				jLabel.setFont(new Font("����", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(new JButton("��ʼ"),new GBC(1, 0));
				labelpanel.add(new JButton("��ͣ"),new GBC(2, 0));
				
				ClearOpreationPanel();//
				operationPanel.add(new AbstractTestCaseGenerationPanel());
				
				 clearConsolePart();			    
				    consolePart.setTitle("��������������ɹ�����Ϣ");
					consolePart.add(new ConsoleMessageTabbedPane("��ϸ��Ϣ",new ConsolePartScrollPane(0)));	
					consolePart.setVisible(true);
				mainFrame.getMainPanel().repaint();
			}
		});
		step4button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				step4button.setFocusable(true);
				step4button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step4button.getText());
				jLabel.setFont(new Font("����", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(new JButton("��ʼ"),new GBC(1, 0));
				labelpanel.add(new JButton("��ͣ"),new GBC(2, 0));
				
				ClearOpreationPanel();
				operationPanel.add(new TestCaseInstantiationPanel());
				
				clearConsolePart();	
				consolePart.setTitle("�����������ʵ����������Ϣ");
				consolePart.add(new ConsoleMessageTabbedPane("��ϸ��Ϣ",new ConsolePartScrollPane(1)));	
				consolePart.setVisible(true);
				mainFrame.revalidate();
			}
		});
		step5button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				step5button.setFocusable(true);
				step5button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step5button.getText());
				jLabel.setFont(new Font("����", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(new JButton("��ʼ"),new GBC(1, 0));
				labelpanel.add(new JButton("��ͣ"),new GBC(2, 0));
				
				ClearOpreationPanel();
				operationPanel.add(new TestCaseConfirmationPanel());
				
				
				clearConsolePart();
				consolePart.setTitle("��������ʵ����֤������Ϣ");
				consolePart.add(new ConsoleMessageTabbedPane("��ϸ��Ϣ",new JTextArea("��������ʵ��������֤��......\n\n\n\n\n\n")));	
				consolePart.setVisible(true);
				
				mainFrame.revalidate();
			}
		});
	}

}
