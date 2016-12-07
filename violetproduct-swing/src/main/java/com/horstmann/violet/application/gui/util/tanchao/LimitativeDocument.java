package com.horstmann.violet.application.gui.util.tanchao;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

//��дjtextArea ��PlainDocumentʹ��jtextArea���Կ����ı��������
public class LimitativeDocument extends PlainDocument{
	private JTextComponent textComponent;//�ı��Ŀռ�
	private int lineMax=10;//��������
	//����
	public LimitativeDocument(JTextComponent tc,int lineMax){
		textComponent=tc;
		this.lineMax=lineMax;
	}
	
	//����
	public LimitativeDocument(JTextComponent tc){
		textComponent=tc;
	}
	//����String
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
