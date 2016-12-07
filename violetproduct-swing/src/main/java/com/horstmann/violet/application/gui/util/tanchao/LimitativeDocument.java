package com.horstmann.violet.application.gui.util.tanchao;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

//重写jtextArea 的PlainDocument使得jtextArea可以控制文本域的行数
public class LimitativeDocument extends PlainDocument{
	private JTextComponent textComponent;//文本的空间
	private int lineMax=10;//最大的行数
	//构造
	public LimitativeDocument(JTextComponent tc,int lineMax){
		textComponent=tc;
		this.lineMax=lineMax;
	}
	
	//构造
	public LimitativeDocument(JTextComponent tc){
		textComponent=tc;
	}
	//插入String
	public void insertString(int offset,String s,AttributeSet attributeSet) throws BadLocationException{
		String value=textComponent.getText();
		int overrun=0;
//		if(value!=null&&value.indexOf(' ')>=0&&value.split(" ").length>=lineMax){
//			overrun=value.indexOf(' ')+1;
//			super.remove(0, overrun);
//		}
		if(value!=null&&value.indexOf("\n")>0&&value.split("\n").length>=lineMax){
			overrun=value.indexOf("\n")+1;
			super.remove(0, overrun);
		}
		super.insertString(offset-overrun, s, attributeSet);
	}

}
