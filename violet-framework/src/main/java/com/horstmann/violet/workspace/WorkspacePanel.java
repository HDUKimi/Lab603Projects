package com.horstmann.violet.workspace;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.RepaintManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;

import com.horstmann.violet.framework.swingextension.TinyScrollBarUI;
import com.horstmann.violet.framework.theme.ThemeManager;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.workspace.editorpart.IEditorPart;
import com.horstmann.violet.workspace.sidebar.ISideBar;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphTool;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class WorkspacePanel extends JPanel
{

    public WorkspacePanel(IWorkspace workspace)
    {
        this.workspace = workspace;
    }

    public void prepareLayout()
    {    	
        LayoutManager layout = new BorderLayout();
        setLayout(layout);
        JScrollPane scrollGPanel = getScrollableEditorPart();                                			
//		add(scrollGPanel, BorderLayout.CENTER);
        JScrollPane scrollSideBarPanel = getScrollableSideBar();
//		add(scrollSideBarPanel, BorderLayout.EAST);
		workspacejs=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollGPanel,scrollSideBarPanel);
		workspacejs.setOneTouchExpandable(true);
		workspacejs.setContinuousLayout(true);
		workspacejs.setDividerSize(0);
		workspacejs.setResizeWeight(1);
		add(workspacejs,BorderLayout.CENTER);
//		scrollSideBarPanel.setVisible(false);
        refreshDisplay();
    }    
    /**
     * @return the scrollable panel containing the editor
     */
    public JScrollPane getScrollableEditorPart()
    {
        if (this.scrollableEditorPart == null)
        {
            final IEditorPart editorPart = this.workspace.getEditorPart();
            final Component panel = editorPart.getSwingComponent();
//            panel.setPreferredSize(new Dimension(2000, 2000));
            this.scrollableEditorPart = new JScrollPane() {
                @Override
                public void paint(Graphics g)
                {
                    editorPart.getSwingComponent().invalidate();
                    super.paint(g);
                }
                
            };
            this.scrollableEditorPart.getViewport().setView(panel);
            this.scrollableEditorPart.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()
            {               
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e)
                {
                    editorPart.getSwingComponent().invalidate();
                    editorPart.getSwingComponent().repaint();
                }
            });
            this.scrollableEditorPart.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener()
            {               
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e)
                {
                    editorPart.getSwingComponent().invalidate();
                    editorPart.getSwingComponent().repaint();
                }
            });
            this.scrollableEditorPart.setBackground(ThemeManager.getInstance().getTheme().getWhiteColor());
            this.scrollableEditorPart.setBorder(new EmptyBorder(0, 0, 0, 0));
//            this.scrollableEditorPart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            this.scrollableEditorPart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.scrollableEditorPart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            panel.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					
					oldx=e.getX();
					oldy=e.getY();
					
					panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseReleased(e);
					
					newx=e.getX();
					newy=e.getY();
					
					movex=newx-oldx;
					movey=newy-oldy;
					
					JScrollBar hbar=scrollableEditorPart.getVerticalScrollBar();
					JScrollBar vbar=scrollableEditorPart.getHorizontalScrollBar();
					
					hbar.setValue(hbar.getValue()-movey);
					vbar.setValue(vbar.getValue()-movex);
					
					System.out.println(" oldx "+oldx+" oldy "+oldy+" newx "+newx+" newy "+newy+" movex "+movex+" movey "+movey);
				}

			});
            
        }
        return this.scrollableEditorPart;
    }

    /**
     * @param workspace TODO
     * @return scrollpane containing sidebar
     */
    public JScrollPane getScrollableSideBar()
    {
        if (this.scrollableSideBar == null)
        {
            ISideBar sideBar = this.workspace.getSideBar();
            this.scrollableSideBar = new JScrollPane(sideBar.getAWTComponent());
            this.scrollableSideBar.setAlignmentY(Component.TOP_ALIGNMENT);
            this.scrollableSideBar.getHorizontalScrollBar().setUI(new TinyScrollBarUI());
            this.scrollableSideBar.getVerticalScrollBar().setUI(new TinyScrollBarUI());
            this.scrollableSideBar.setBorder(new MatteBorder(0, 1, 0, 0, ThemeManager.getInstance().getTheme()
                    .getSidebarBorderColor()));
            this.scrollableSideBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.scrollableSideBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        }
        return this.scrollableSideBar;
    }

    public void refreshDisplay()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                WorkspacePanel.this.repaint();
            }
        });
    }

	
	
	public JSplitPane getWorkspacejs() {
		return workspacejs;
	}

	public void setWorkspacejs(JSplitPane workspacejs) {
		this.workspacejs = workspacejs;
	}



	public IWorkspace getWorkspace() {
		return workspace;
	}



	private IWorkspace workspace;
	private JScrollPane scrollableSideBar;
	private JScrollPane scrollableEditorPart;
	private JScrollPane scrollableStatusBar;
	private JSplitPane workspacejs;
	
	int oldx=0;
	int oldy=0;
	int newx=0;
	int newy=0;
	int movex=0;
	int movey=0;

}
