package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TestJTextArea extends JFrame{
	static String s="sadafsertsvfdgrtyhbfhfjfsfdsdadadadfsdfsgsdgsdgs";
	private static JTextArea jta;
	private static JButton jb;
    public TestJTextArea() {
       super("测试文本域的行数");
       
       jb=new JButton("标志");
       jta=new JTextArea(20,20);
       jta.setDocument(new LimitativeDocument(jta, 7));
       this.setLayout(new BorderLayout());
       this.add(jb,BorderLayout.SOUTH);
       this.add(jta,BorderLayout.CENTER);
       Thread t=new Thread(new Runnable() {
			public void run() {
				char[] arr=s.toCharArray();	
				int len=arr.length;
				for(int i=0;i<len;i++){
					jta.append(arr[i]+"\n");
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
   	   t.start();
       this.pack();
       this.setVisible(true);
       
    }  
    public static void main(String[] args) {
    	
    
	   new TestJTextArea();	
	}
}
