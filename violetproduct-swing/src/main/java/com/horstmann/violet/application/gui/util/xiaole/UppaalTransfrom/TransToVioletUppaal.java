package com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom;

import org.dom4j.DocumentException;

public class TransToVioletUppaal {

	/**
	 * 
	 * @param filename
	 * @param sflag		点上的标签显示：0 不显示；1 顺序图显示；2 时序图显示 
	 * @param tflag		边上的标签显示：0 不显示；1 顺序图显示；2 时序图显示
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToViolet(String filename,int sflag,int tflag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//解析wj转化的时间自动机的xml(找到我们想要的信息)
    	writevioletuppaal.find();
    	//将时间自动机的xml-->转化成我们的xml(利用我们获取的信息生成)
  	    writevioletuppaal.writeVioletUppaal("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\"+filename+"Uppaal.uppaal.violet.xml",sflag,tflag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}
	
	/**
	 * 
	 * @param path
	 * @param filename
	 * @param sflag		点上的标签显示：0 不显示；1 顺序图显示；2 时序图显示 
	 * @param tflag		边上的标签显示：0 不显示；1 顺序图显示；2 时序图显示
	 * @return
	 * @throws DocumentException
	 */
	public static String TransToVioletAddPath(String path,String filename,int sflag,int tflag) throws DocumentException {
		// TODO Auto-generated method stub
		WriteVioletUppaal writevioletuppaal=new WriteVioletUppaal();
		//解析wj转化的时间自动机的xml(找到我们想要的信息)
    	writevioletuppaal.find();
    	//将时间自动机的xml-->转化成我们的xml(利用我们获取的信息生成)
  	    writevioletuppaal.writeVioletUppaal(path+filename+"Uppaal.uppaal.violet.xml",sflag,tflag);
  	    return filename+"Uppaal.uppaal.violet.xml";
	}

}
