package com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom;

import org.dom4j.DocumentException;

public class TransToVioletUppaal {

	/**
	 * 
	 * @param filename
	 * @param flag �Ƿ���ʾǨ���ϵ���Ϣ,1����ʾ,0�ǲ���ʾ
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToViolet(String filename,int flag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//����wjת����ʱ���Զ�����xml(�ҵ�������Ҫ����Ϣ)
    	writevioletuppaal.find();
    	//��ʱ���Զ�����xml-->ת�������ǵ�xml(�������ǻ�ȡ����Ϣ����)
  	    writevioletuppaal.writeVioletUppaal("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\"+filename+"Uppaal.uppaal.violet.xml",flag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}

}
