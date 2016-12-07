package com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom;

import org.dom4j.DocumentException;

public class TransToVioletUppaal {

	public static String TransToViolet() throws DocumentException {
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//解析wj转化的时间自动机的xml(找到我们想要的信息)
    	writevioletuppaal.find();
    	//将时间自动机的xml-->转化成我们的xml(利用我们获取的信息生成)
  	    writevioletuppaal.writeVioletUppaal(//"stabilize_run.xml"
  	    		"D:\\ModelDriverProjectFile\\UPPAL"
  	    		+ "\\2.UML_Model_Transfer\\uppaalTest1.uppaal.violet.xml"
  	    		);
  	    return "uppaalTest1.uppaal.violet.xml";
	}

}
