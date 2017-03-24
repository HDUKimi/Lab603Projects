package com.horstmann.violet.application.gui.util.ckt.testcase;
/////解析xml文档后存入数据结构   输出结果
/////没有时间约束
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;



public class yzsc {
	public static void main(String[] args) {
		String xml="loop10ForXStream.xml";
		//String xml="read_radioForXStream.xml";
		Automatic automatic1=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto=AddType.addType(automatic1);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列		
		
//======================================================================满足状态覆盖的测试序列，存入对应数据结构中结束
		System.out.println("===========================正在遍历时间自动机");
		System.out.println("===========================");
		System.out.println("===========================正在生成深度优先生成树");
		System.out.println("===========================");
		System.out.println("===========================正在遍历深度优先生成树");
		System.out.println("===========================");
		System.out.println("===========================正在生成满足状态覆盖的抽象测试序列");
		System.out.println("===========================");
		System.out.println();
		System.out.println("===========================正在获取抽象测试序列详细信息");		
		System.out.println();
		System.out.println("  ===>  抽象测试序列个数："+testCase.size());
		System.out.println("-------------------------------------------------");
		for(Automatic a:testCase){			
			System.out.println("    ===>  测试用例名字："+a.getName());
			for(Transition tran:a.getTransitionSet()){	
				System.out.println("       -----------------------------");
				System.out.println("      ===>  迁移(激励)名称--->"+tran.getName());
				System.out.println("        ===>  迁移Id："+tran.getId());								
				System.out.println("        ===>  源状态名称："+tran.getSource());
				System.out.println("        ===>  目的状态名称："+tran.getTarget());	
			//未处理的约束条件	
				System.out.println("        ===>  约束："+tran.getEventSet());
			//输出in里面的约束
				System.out.print("        ===>  in(约束条件)："+tran.getIn());
				if(tran.getIn().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getIn());
				}					
			//输出out里面的约束
				System.out.print("        ===>  out(输出信息)：");
				if(tran.getOut().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getOut());
				}
			//输出condition里面的约束
				//System.out.println("        ===>  get-condition："+tran.getCondition());
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的
						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
						System.out.println("        ===>  condition(约束条件)//："+conditonValue);
					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("        ===>  condition(约束条件)："+tran.getCondition());
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						System.out.println("        ===>  condition(约束条件)：null-->"+tran.getCondition());	
					}
				}																																									
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("---------------------------------------------------------");
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//===========================================================================正在解析xml文档
		/*System.out.println("===========================正在解析xml文档");
		System.out.println("===========================");
		System.out.println();
		System.out.println("===========================正在获取xml文档信息");		
		System.out.println("  ===>  时间自动机名字："+auto.getName());	
		System.out.print("  ===>  时间自动机时钟集合：");
		if((auto.getClockSet().toString().equals("[]"))){
			System.out.println("null");
		}
		else{
		for(String c:auto.getClockSet()){
			System.out.print(" "+c+"  ");
		}
		}
		System.out.println("  ===>  模型中总状态个数："+auto.getStateSet().size());
		System.out.println("  ===>  模型中总迁移个数："+auto.getTransitionSet().size());
		System.out.println();*/
//========================================================================================xml总体信息结束
		
		
//=========================================================================================获取模型初始状态信息
		/*System.out.println("===========================正在获取初始状态信息");
		System.out.println("===========================");
		State iniState=auto.getInitState();
		System.out.println("  ===>  初始状态名字："+iniState.getName());
		System.out.println("  ===>  状态位置："+iniState.getPosition());
		System.out.println("  ===>  是否为终止状态 ："+iniState.isFinalState());
		System.out.println("  ===>  Type类型(是否为初始)："+iniState.getType());
		DBM_element[][] DBM=iniState.getInvariantDBM();
		if(auto.getClockSet().size()>0){
			for(int i=0;i<auto.getClockSet().size()+1;i++){
				for(int j=0;j<auto.getClockSet().size()+1;j++){
					DBM_element cons=DBM[i][j];
					//System.out.println("DBM_i："+cons.getDBM_i());
					//System.out.println("DBM_j："+cons.getDBM_j());
					System.out.println("  ===>  value："+cons.getValue());
					System.out.println("  ===>  Strictness："+cons.isStrictness());					
				}
			}
		}		
		System.out.println();*/
//========================================================================================模型初始状态信息获取结束
		
		
//===========================================================================================获取模型状态的具体信息
		/*System.out.println("===========================正在获取所有状态具体信息");
		System.out.println("===========================");
		System.out.println("  ===>  状态总个数："+auto.getStateSet().size());
		int k=0;
		for(State state:auto.getStateSet()){
			System.out.println("    ===>  第"+k+"个状态");
			k++;
			DBM_element[][] dbm=state.getInvariantDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=dbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						System.out.println("      ===>  value："+cons.getValue());
						System.out.println("      ===>  Strictness："+cons.isStrictness());					
					}
				}
			}			
			System.out.println("      ===>  状态名称："+state.getName());
			System.out.println("      ===>  状态位置："+state.getPosition());
			System.out.println("      ===>  是否为终止状态："+state.isFinalState());
			System.out.println("      ===>  Type类型(是否为初始)："+state.getType());
			DBM_element[][] adddbm=state.getAddClockRelationDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=adddbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						System.out.println("      ===>  value："+cons.getValue());
						System.out.println("      ===>  Strictness："+cons.isStrictness());					
					}
				}
			}			
			System.out.println("--------------------------");
		}
		System.out.println();		*/
//=====================================================================================模型具体状态信息获取结束
		
		
		
		
//====================================================================================获取模型迁移(激励)信息
		/*System.out.println("===========================正在获取所有迁移(激励)具体信息");
		System.out.println("===========================");
		System.out.println("  ===>  迁移总个数："+auto.getTransitionSet().size());
		int p=0;
		for(Transition tran:auto.getTransitionSet()){
			System.out.println("    ===>  第"+p+"条迁移(激励)");
			System.out.println("      ===>  迁移(激励)名称："+tran.getName().replace("(", "").replace(")", "").replace("!", "").replace("?", ""));
			System.out.println("      ===>  迁移Id："+tran.getId());
			p++;
			if(tran.getConstraintDBM()!=null){
				DBM_element[][] dbm=tran.getConstraintDBM();
				if(auto.getClockSet().size()>0){
					for(int i=0;i<auto.getClockSet().size()+1;i++){
						for(int j=0;j<auto.getClockSet().size()+1;j++){
							DBM_element cons=dbm[i][j];
							//System.out.println("DBM_i："+cons.getDBM_i());
							//System.out.println("DBM_j："+cons.getDBM_j());
							System.out.println("      ===>  value："+cons.getValue());
							System.out.println("      ===>  Strictness："+cons.isStrictness());					
						}
					}
				}				
			}
			else System.out.println("时钟约束为空");			
			System.out.println("      ===>  源状态名称："+tran.getSource());
			System.out.println("      ===>  目的状态名称："+tran.getTarget());						
			if(tran.getEventSet()==null){
				System.out.println("事件为空");
			}
			else if(tran.getEventSet().size()==0){
				System.out.println("事件为不空，但是没有内容");
			}
			else{				
				String in=tran.getIn();		
				String con=tran.getCondition();
				String out=tran.getOut();
				System.out.print("      ===>  in(约束条件)：");
				if("".equals(in.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(in.toString());
				}
				System.out.println("      ===>  condition(约束条件)："+con);
				System.out.print("      ===>  out(输出信息)："+out.toString());												
				if("".equals(out.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(out.toString());
				}
				
				ArrayList<String> events=tran.getEventSet();
//				for(String e：events){
//					System.out.println("      ===>  事件："+e);
//				}								
			}			
			if(tran.getResetClockSet()==null){
				System.out.println("      ===>  重置时钟：null");
			}
			else if(tran.getResetClockSet().size()==0){
				System.out.println("      ===>  重置时钟：不空，但是没有内容");
			}
			else{
				ArrayList<String> reset=tran.getResetClockSet();
				for(String r:reset){
					System.out.println("      ===>  重置的时钟："+r);
				}
			}
			
			if(tran.getTypeIds()==null){
				System.out.println("      ===>  typeID：null");
			}
			else if(tran.getTypeIds().size()==0){
				System.out.println("      ===>  typeID：不空，但是没有内容");
			}
			else{
				ArrayList<String> typeid=tran.getTypeIds();
				for(String i:typeid){
//					System.out.println("      ===>  typeId："+i);
				}
			}
			
			if(tran.getTypes()==null){
				System.out.println("      ===>  types：null");
			}
			else if(tran.getTypes().size()==0){
				System.out.println("      ===>  types：不空，但是没有内容");
			}
			else{
				ArrayList<String> type=tran.getTypes();
				for(String t:type){
//					System.out.println("      ===>  types："+t);
				}
			}
			
			System.out.println("--------------------------");
		}
		System.out.println();*/
//=======================================================================模型迁移(激励)信息获取结束	
//=======================================================================读取xml结束
		
//==========================================满足状态覆盖的测试序列，存入对应数据结构中====
		/*System.out.println();
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){			
			System.out.println("测试用例名字："+a.getName());
			for(Transition tran:a.getTransitionSet()){
				System.out.println("-------------------------------------------------");
				System.out.println("激励名称--->"+tran.getName());				
			//未处理的约束条件	
				System.out.println("约束："+tran.getEventSet());
			//输出in里面的约束
				System.out.println("in---->"+tran.getIn());	
			//输出out里面的约束
				System.out.println("out---->"+tran.getOut());
			//输出condition里面的约束
				System.out.println("get-condition---->"+tran.getCondition());
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的
						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
						System.out.println("//condition//---->"+conditonValue);
					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("condition---->"+tran.getCondition());
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						System.out.println("condition--null-->"+tran.getCondition());	
					}
				}																																									
			}
		}
		try {
			System.setOut(new PrintStream("E：\\xml\\a.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
//=====================满足状态覆盖的测试序列，存入对应数据结构中结束=======================================	
/*
		//======================================================================读取xml，存入对应数据结构中
		
		//===========================================================================正在解析xml文档
				System.out.println("===========================正在解析xml文档");
				System.out.println("===========================");
				System.out.println();
				System.out.println("===========================正在获取xml文档信息");		
				System.out.println("  ===>  时间自动机名字："+auto.getName());	
				System.out.print("  ===>  时间自动机时钟集合：");
				if((auto.getClockSet().toString().equals("[]"))){
					System.out.println("null");
				}
				else{
				for(String c:auto.getClockSet()){
					System.out.print(" "+c+"  ");
				}
				}
				System.out.println("  ===>  模型中总状态个数："+auto.getStateSet().size());
				System.out.println("  ===>  模型中总迁移个数："+auto.getTransitionSet().size());
				System.out.println();
		//========================================================================================xml总体信息结束
				
				
		//=========================================================================================获取模型初始状态信息
				System.out.println("===========================正在获取初始状态信息");
				System.out.println("===========================");
				State iniState=auto.getInitState();
				System.out.println("  ===>  初始状态名字："+iniState.getName());
				System.out.println("  ===>  状态位置："+iniState.getPosition());
				System.out.println("  ===>  是否为终止状态 ："+iniState.isFinalState());
				System.out.println("  ===>  Type类型(是否为初始)："+iniState.getType());
				DBM_element[][] DBM=iniState.getInvariantDBM();
				if(auto.getClockSet().size()>0){
					for(int i=0;i<auto.getClockSet().size()+1;i++){
						for(int j=0;j<auto.getClockSet().size()+1;j++){
							DBM_element cons=DBM[i][j];
							//System.out.println("DBM_i："+cons.getDBM_i());
							//System.out.println("DBM_j："+cons.getDBM_j());
							System.out.println("  ===>  value："+cons.getValue());
							System.out.println("  ===>  Strictness："+cons.isStrictness());					
						}
					}
				}		
				System.out.println();
		//========================================================================================模型初始状态信息获取结束
				
				
		//===========================================================================================获取模型状态的具体信息
				System.out.println("===========================正在获取所有状态具体信息");
				System.out.println("===========================");
				System.out.println("  ===>  状态总个数："+auto.getStateSet().size());
				int k=0;
				for(State state:auto.getStateSet()){
					System.out.println("    ===>  第"+k+"个状态");
					k++;
					DBM_element[][] dbm=state.getInvariantDBM();
					if(auto.getClockSet().size()>0){
						for(int i=0;i<auto.getClockSet().size()+1;i++){
							for(int j=0;j<auto.getClockSet().size()+1;j++){
								DBM_element cons=dbm[i][j];
								//System.out.println("DBM_i："+cons.getDBM_i());
								//System.out.println("DBM_j："+cons.getDBM_j());
								System.out.println("      ===>  value："+cons.getValue());
								System.out.println("      ===>  Strictness："+cons.isStrictness());					
							}
						}
					}			
					System.out.println("      ===>  状态名称："+state.getName());
					System.out.println("      ===>  状态位置："+state.getPosition());
					System.out.println("      ===>  是否为终止状态："+state.isFinalState());
					System.out.println("      ===>  Type类型(是否为初始)："+state.getType());
					DBM_element[][] adddbm=state.getAddClockRelationDBM();
					if(auto.getClockSet().size()>0){
						for(int i=0;i<auto.getClockSet().size()+1;i++){
							for(int j=0;j<auto.getClockSet().size()+1;j++){
								DBM_element cons=adddbm[i][j];
								//System.out.println("DBM_i："+cons.getDBM_i());
								//System.out.println("DBM_j："+cons.getDBM_j());
								System.out.println("      ===>  value："+cons.getValue());
								System.out.println("      ===>  Strictness："+cons.isStrictness());					
							}
						}
					}			
					System.out.println("--------------------------");
				}
				System.out.println();		
		//=====================================================================================模型具体状态信息获取结束
				
				
				
				
		//====================================================================================获取模型迁移(激励)信息
				System.out.println("===========================正在获取所有迁移(激励)具体信息");
				System.out.println("===========================");
				System.out.println("  ===>  迁移总个数："+auto.getTransitionSet().size());
				int p=0;
				for(Transition tran:auto.getTransitionSet()){
					System.out.println("    ===>  第"+p+"条迁移(激励)");
					System.out.println("      ===>  迁移(激励)名称："+tran.getName().replace("(", "").replace(")", "").replace("!", "").replace("?", ""));
					System.out.println("      ===>  迁移Id："+tran.getId());
					p++;
					if(tran.getConstraintDBM()!=null){
						DBM_element[][] dbm=tran.getConstraintDBM();
						if(auto.getClockSet().size()>0){
							for(int i=0;i<auto.getClockSet().size()+1;i++){
								for(int j=0;j<auto.getClockSet().size()+1;j++){
									DBM_element cons=dbm[i][j];
									//System.out.println("DBM_i："+cons.getDBM_i());
									//System.out.println("DBM_j："+cons.getDBM_j());
									System.out.println("      ===>  value："+cons.getValue());
									System.out.println("      ===>  Strictness："+cons.isStrictness());					
								}
							}
						}				
					}
					else System.out.println("时钟约束为空");			
					System.out.println("      ===>  源状态名称："+tran.getSource());
					System.out.println("      ===>  目的状态名称："+tran.getTarget());						
					if(tran.getEventSet()==null){
						System.out.println("事件为空");
					}
					else if(tran.getEventSet().size()==0){
						System.out.println("事件为不空，但是没有内容");
					}
					else{				
						String in=tran.getIn();		
						String con=tran.getCondition();
						String out=tran.getOut();
						System.out.print("      ===>  in(约束条件)：");
						if("".equals(in.toString())){
							System.out.println("null");
						}
						else{
							System.out.println(in.toString());
						}
						System.out.println("      ===>  condition(约束条件)："+con);
						System.out.print("      ===>  out(输出信息)："+out.toString());												
						if("".equals(out.toString())){
							System.out.println("null");
						}
						else{
							System.out.println(out.toString());
						}
						
						ArrayList<String> events=tran.getEventSet();
//						for(String e：events){
//							System.out.println("      ===>  事件："+e);
//						}								
					}			
					if(tran.getResetClockSet()==null){
						System.out.println("      ===>  重置时钟：null");
					}
					else if(tran.getResetClockSet().size()==0){
						System.out.println("      ===>  重置时钟：不空，但是没有内容");
					}
					else{
						ArrayList<String> reset=tran.getResetClockSet();
						for(String r:reset){
							System.out.println("      ===>  重置的时钟："+r);
						}
					}
					
					if(tran.getTypeIds()==null){
						System.out.println("      ===>  typeID：null");
					}
					else if(tran.getTypeIds().size()==0){
						System.out.println("      ===>  typeID：不空，但是没有内容");
					}
					else{
						ArrayList<String> typeid=tran.getTypeIds();
						for(String i:typeid){
//							System.out.println("      ===>  typeId："+i);
						}
					}
					
					if(tran.getTypes()==null){
						System.out.println("      ===>  types：null");
					}
					else if(tran.getTypes().size()==0){
						System.out.println("      ===>  types：不空，但是没有内容");
					}
					else{
						ArrayList<String> type=tran.getTypes();
						for(String t:type){
//							System.out.println("      ===>  types："+t);
						}
					}
					
					System.out.println("--------------------------");
				}
				System.out.println();
		//=======================================================================模型迁移(激励)信息获取结束	
		//=======================================================================读取xml结束
		
		*/
		
		
		////////////////////////////////////////////////////////////////////////
		
		
	}
}

