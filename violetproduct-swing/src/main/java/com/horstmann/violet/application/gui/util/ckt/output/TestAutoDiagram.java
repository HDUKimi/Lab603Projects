package com.horstmann.violet.application.gui.util.ckt.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractState;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractTransition;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.CreateAbstractUppaalXML;

public class TestAutoDiagram {
	
	private static List<AbstractState> abStateList =new ArrayList<AbstractState>();
	private static List<AbstractTransition> abTransList =new ArrayList<AbstractTransition>();

	public static void main(String[] args) {
		
		Automatic a=null;
		List<Automatic> alist=new ArrayList<>();
		
//		a=DFSAuto();
		
		System.out.println(a.toString());
		System.out.println(a.getStateSet().size());
		System.out.println(a.getTransitionSet().size());
			
		
//		alist=PathAuto();
//		
//		System.out.println(alist.size());
//		
//		for(Automatic am:alist){
//			System.out.println("----------------");
//			for(Transition t:am.getTransitionSet()){
//				System.out.println(t.toString());
//			}
//		}
		
		try {
			createSequenceXML(a);
			
			LayoutUppaal.layout("D:\\sequence.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(State s :a.getStateSet()){
			//将wqq的相关的信息--->转换为zhangjian的相关的信息(state)
			AbstractState abState =new AbstractState();
			abState.setSid(s.getId());//查询数据库里面状态节点的个数
			abState.setSname(s.getName());
			abState.setPosition(s.getPosition());
			
			if(s.getType()==null){
				abState.setType("CircularNode");
			}
			else if(s.getType().equals("start")){
				abState.setType("Start");
			}
			
			abState.setInvariantDBM(s.getInvariantDBM().toString());
			abStateList.add(abState);
			
		}
		
		for(Transition t :a.getTransitionSet()){
			//将wqq的相关的信息--->转换为zhangjian的相关的信息(transition)
			AbstractTransition abTrans =new AbstractTransition();
			abTrans.setTid(t.getId());
			abTrans.setTname(t.getName());
			abTrans.setSourceID(getStateIdByName(abStateList, t.getSource())+"");
			
			abTrans.setSource(t.getSource());
			abTrans.setTargetID(getStateIdByName(abStateList, t.getTarget())+"");
			abTrans.setTarget(t.getTarget());
			
			abTrans.setType(t.getTypes().toString());
			StringBuilder sb =new StringBuilder();
			for(int i=0;i<t.getResetClockSet().size();i++){
				sb.append(t.getResetClockSet().get(i));
				if(i!=t.getResetClockSet().size()-1){
					sb.append(";");
				}
			}
			abTrans.setResetClockSet(sb.toString());
			abTrans.setConstraintDBM(t.getConstraintDBM().toString());
			//System.out.println(t.getTypes()+"**"+t.getSource()+"**"+t.getTarget()+"**"+t.getResetClockSet()+"**"+t.getConstraintDBM());
			abTransList.add(abTrans);
		}
		
		CreateAbstractUppaalXML c =new CreateAbstractUppaalXML(abStateList, abTransList);
		try {
			 c.create("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\abs.uppaal.violet.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void createSequenceXML(Automatic a) throws IOException{
		
		int x=30,y=30;
		String targetPath="sequence.xml";
		
		System.out.println("开始生成XML文件");
		Document document = DocumentHelper.createDocument();  //创建文档   
		Element nta=document.addElement("nta");  
		Element declaration=nta.addElement("declaration");
		declaration.addText("// Place global declarations here.");
		
	    Element tem=nta.addElement("template");
	    Element nameElement=tem.addElement("name");
	    String xx = String.valueOf(x++);
	    String yy=String.valueOf(y++);
	    nameElement.addAttribute("x",xx );
	    nameElement.addAttribute("y",yy );
	    nameElement.setText("template_");
	    tem.addElement("declaration");
	    
	    
	    for(State ul:a.getStateSet()){
	    	Element loc =tem.addElement("location");
	    	loc.addAttribute("id",ul.getName());
	    	loc.addAttribute("x", xx);
	    	loc.addAttribute("y",yy);
	    	
	    	Element name2=loc.addElement("name");
	    	name2.addAttribute("x", xx);
	    	name2.addAttribute("y", yy);
	    	name2.setText(ul.getName());
	    }
	    
	    for(Transition ut:a.getTransitionSet()){
	    	Element tran=tem.addElement("transition");
	    	tran.addAttribute("id","tran_id"+ut.getSource()+ut.getTarget());
//	    	tran.addAttribute("id","");
	    	tran.addElement("source").addAttribute("ref",ut.getSource());
	    	tran.addElement("target").addAttribute("ref",ut.getTarget());
	    	Element lable=tran.addElement("label");
	    	lable.addAttribute("kind", "synchronisation");
	    	lable.addAttribute("x", xx);
	    	lable.addAttribute("y", yy);
	    	lable.setText("tran_id"+ut.getSource()+ut.getTarget());
	    	
	    }
	    
	    Element sysElement =nta.addElement("system");
		String doString=document.asXML();
		
//		System.out.println(doString);
		
//		String absolutePath=System.getProperty("user.dir");
//		String absolutePath="D:";
		
//		File newFile=new File(absolutePath+"\\"+targetPath);
		File newFile=new File(targetPath);
		FileOutputStream outputStream = new FileOutputStream(newFile);
		outputStream.write(doString.getBytes());
		
		outputStream.close();	
		System.out.println("转换完成!");   
		System.out.println("***************************************");
		
		
	}
	
	public static int getStateIdByName(List<AbstractState> abStateList2 ,String name){
		int num=0;
		for(AbstractState s :abStateList2){
			if(name.equals(s.getSname())){
				num=s.getSid();
			}
		}
		return num;
	}
	
}
