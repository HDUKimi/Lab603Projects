package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class LimitativeTextArea extends JFrame{

    private JTextArea logInfo;
    private JTextField content;
    private JButton addButton;
    private final int contentMax = 5;
    
    public LimitativeTextArea(){
        initComponent();
    }
    
    private void initComponent(){
        Container pane  = getContentPane();
        pane.setLayout(new BorderLayout());
        
        logInfo = new JTextArea(22,9);
        logInfo.setDocument(new LimitativeDocument(logInfo,contentMax));
        pane.add(new JScrollPane(logInfo),BorderLayout.CENTER);
        
        content = new JTextField(18);
        addButton = new JButton("Submit");
        JPanel contentPanel = new JPanel();
        contentPanel.add(content);
        contentPanel.add(addButton);
        pane.add(contentPanel,BorderLayout.SOUTH);
        
        InputListener listener = new InputListener();
        content.registerKeyboardAction(listener,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),JComponent.WHEN_FOCUSED);
        
        addButton.addActionListener(listener);
    }
    
    public void showFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void appendContent(){
        String contentText = content.getText();
        if(!contentText.equals("")){
            logInfo.append(contentText+" ");
            logInfo.setCaretPosition(logInfo.getText().length());
        }
        content.setText("");
    }
    
    public static void main(String[] args) {
        new LimitativeTextArea().showFrame();
    }

    class InputListener extends MouseAdapter implements ActionListener{

        public void actionPerformed(ActionEvent e){
            
            executeClick();
        }
        public void mouseClicked(MouseEvent event){
            executeClick();
        }
        private void executeClick(){
            appendContent();
        }
    }
}
