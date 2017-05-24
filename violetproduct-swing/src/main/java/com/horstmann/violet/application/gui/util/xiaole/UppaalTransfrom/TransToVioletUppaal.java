package com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom;

import org.dom4j.DocumentException;

public class TransToVioletUppaal {

	/**
	 * 
	 * @param filename
	 * @param flag 是否显示迁移上的消息,1是显示,0是不显示
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToViolet(String filename,int flag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//解析wj转化的时间自动机的xml(找到我们想要的信息)
    	writevioletuppaal.find();
    	//将时间自动机的xml-->转化成我们的xml(利用我们获取的信息生成)
  	    writevioletuppaal.writeVioletUppaal("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\"+filename+"Uppaal.uppaal.violet.xml",flag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}

}
