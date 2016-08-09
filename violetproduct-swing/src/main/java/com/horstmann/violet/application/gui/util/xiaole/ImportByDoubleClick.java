package com.horstmann.violet.application.gui.util.xiaole;

import java.io.File;
import java.io.IOException;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.LocalFile;

public class ImportByDoubleClick {
	public static GraphFile importFileByDoubleClick(String type,String name){
    	GraphFile graphFile=null;
    
    	String base="D://ModelDriverProjectFile";
    	//����type�ҵ���Ӧ���ļ���
    	if("ClassDiagram".equals(type)){
    		base+="\\ClassDiagram\\Violet";
    	
    	}else if("StateDiagram".equals(type)){
    		base+="\\StateDiagram\\Violet";
    	
    	}else if("UseCaseDiagram".equals(type)){
    		
    		base+="\\UseCaseDiagram\\Violet";
    	
    	}else if("ActivityDiagram".equals(type)){
    		
    		base+="\\ActivityDiagram\\Violet";
    	
    	}else if("ObjectDiagram".equals(type)){
    		
    		base+="\\ObjectDiagram\\Violet";
    	
    	}else if("TimingDiagram".equals(type)){
    		
    		base+="\\TimingDiagram\\Violet";
    	
    	}else if("UPPAAL".equals(type)){
    		System.out.println("-------�ҵ�Ҫת����XML�ļ�-------");
    		base+="\\UPPAL\\2.UML_Model_Transfer\\";
    	
    	}else if("SequenceDiagram".equals(type)){

    		base+="\\SequenceDiagram\\Violet";    		
    	}
    	//�������ֵ����ļ���������ļ�
    	 File file =new File(base);
		 File[] fList=file.listFiles();
		 System.out.println("��Ŀ:"+fList.length);
		 for (int i = 0; i < fList.length; i++) {
			 System.out.println("���֣�"+fList[i].getName());
			 String fname =fList[i].getName();
			 if(name.equals(fname)){
				 try {
					graphFile=new GraphFile(new LocalFile(fList[i]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }	
		}
		 System.out.println("Filename��"+graphFile.getFilename());
    	
    	return graphFile;
    }
}
