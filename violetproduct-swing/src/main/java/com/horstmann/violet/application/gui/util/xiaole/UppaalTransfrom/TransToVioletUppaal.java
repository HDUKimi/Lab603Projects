package com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom;

import org.dom4j.DocumentException;

public class TransToVioletUppaal {

	/**
	 * 
	 * @param filename
	 * @param sflag		���ϵı�ǩ��ʾ��0 ����ʾ��1 ˳��ͼ��ʾ��2 ʱ��ͼ��ʾ 
	 * @param tflag		���ϵı�ǩ��ʾ��0 ����ʾ��1 ˳��ͼ��ʾ��2 ʱ��ͼ��ʾ
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToViolet(String filename,int sflag,int tflag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//����wjת����ʱ���Զ�����xml(�ҵ�������Ҫ����Ϣ)
    	writevioletuppaal.find();
    	//��ʱ���Զ�����xml-->ת�������ǵ�xml(�������ǻ�ȡ����Ϣ����)
  	    writevioletuppaal.writeVioletUppaal("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\"+filename+"Uppaal.uppaal.violet.xml",sflag,tflag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}
	
	/**
	 * 
	 * @param path
	 * @param filename
	 * @param sflag		���ϵı�ǩ��ʾ��0 ����ʾ��1 ˳��ͼ��ʾ��2 ʱ��ͼ��ʾ 
	 * @param tflag		���ϵı�ǩ��ʾ��0 ����ʾ��1 ˳��ͼ��ʾ��2 ʱ��ͼ��ʾ
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToVioletAddPath(String path,String filename,int sflag,int tflag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//����wjת����ʱ���Զ�����xml(�ҵ�������Ҫ����Ϣ)
    	writevioletuppaal.find();
    	//��ʱ���Զ�����xml-->ת�������ǵ�xml(�������ǻ�ȡ����Ϣ����)
  	    writevioletuppaal.writeVioletUppaal(path+filename+"Uppaal.uppaal.violet.xml",sflag,tflag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}

}
