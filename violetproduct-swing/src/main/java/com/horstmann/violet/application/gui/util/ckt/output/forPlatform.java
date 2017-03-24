package com.horstmann.violet.application.gui.util.ckt.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dom4j.Element;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;
public class forPlatform {

	public static void main(String[] args) {
		//解析时间自动机，得到一个Automatic
		String xml="UAVForXStreamPerformTestV6.xml";
		Automatic a = GetAutomatic.getAutomatic(xml);
	}
	/**
	 * 解析时间自动机，得到一个Automatic
	 * @param xml 传入一个xml文档
	 * @return
	 */
	public static Automatic plat1(String xml){
		Automatic a = GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
//		auto.getClockSet().toString().equals("[]")
		return a;
	}
	/**
	 * 符号状态拆分，得到一个Automatic
	 * @param a 传入原始时间自动机
	 * @return
	 */
	public static Automatic plat2(Automatic a){
		//Automatic a = GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		return automatic;
	}
	/**
	 * 去除抽象时间迁移，得到一个Automatic
	 * @param a 传入原始时间自动机
	 * @return
	 */
	public static Automatic plat3(Automatic a){
		//Automatic a = GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		return aTDRTAutomatic;
	}
	/**
	 * 状态覆盖，得到一个状态生成树Automatic
	 * @param a 传入要进行状态覆盖的时间自动机
	 * @return
	 */
	public static Automatic plat4(Automatic a){
		//Automatic a = GetAutomatic.getAutomatic(xml);
		//Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		//Automatic aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		Automatic DFStree=StateCoverage__1.DFSTree(a);
		return DFStree;
	}
	/**
	 * 状态覆盖，得到一个测试序列ArrayList<Automatic>
	 * @param a 传入要进行状态覆盖的时间自动机，不是进行DFSTree的时间自动机
	 * @return
	 */
	public static ArrayList<Automatic> plat5(Automatic a){
		//Automatic a = GetAutomatic.getAutomatic(xml);
		//Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		//Automatic aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(a);//获得满足状态覆盖的抽象测试序列
		return testCase;
	}
	/**
	 * 路径覆盖，得到一个测试序列ArrayList<Automatic>，in，condition上未处理
	 * @param auto 传入要进行状态覆盖的时间自动机
	 * @param n 传入要生成的路径数目
	 * @return
	 */
	public static ArrayList<Automatic> plat6(Automatic auto,int n){
		//Automatic a = GetAutomatic.getAutomatic(xml);
		//Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		//Automatic auto=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, n);//n=2000,获得满足路径覆盖的抽象测试序列
		return testCase;
	}
	/**
	 * 处理测试序列，得到抽象测试用例ArrayList<Automatic>
	 * 把迁移上的不等式组合起来，放入limit属性中
	 * @param xml 
	 * @return
	 */
	public static ArrayList<Automatic> plat7(ArrayList<Automatic> testcase){
		//Automatic a = GetAutomatic.getAutomatic(xml);
		//Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		//Automatic auto=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		//ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 2000);//获得满足路径覆盖的抽象测试序列
		ArrayList<Automatic> collectLimit = collectLimit(testcase);
		return collectLimit;
	}
	/**实例化测试用例，把解组合在一起 
	 * @param testcase
	 * @return
	 */
	public static ArrayList<Automatic> plat8(ArrayList<Automatic> collectLimit){
		//Automatic a = GetAutomatic.getAutomatic(xml);
		//Automatic automatic=IPR__1.iPR(a);//获得拆分后的时间自动机
		//Automatic auto=ATDTR__1.aTDRT(automatic,a);//获得去除抽象时间迁移后的时间自动机
		//ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 2000);//获得满足路径覆盖的抽象测试序列
		//ArrayList<Automatic> collectLimit = collectLimit(testcase);//把测试用例不等式组合一起，写入时间自动机
		ArrayList<Automatic> collectResult = collectResult(collectLimit);//把实例化结果组合一起，写入时间自动机
		return collectResult;
	}
	
	/**
	 * 搜集时间自动机上条件，写入时间自动机中
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> collectLimit(ArrayList<Automatic> auto){
		//ArrayList<Automatic> automatic = new ArrayList<Automatic>();
		for(Automatic a:auto){	
			//ArrayList<Transition> TransitionSet = new ArrayList<Transition>();
			for(Transition tran:a.getTransitionSet()){
				String cond = "";
				//System.out.println("in里面内容："+tran.getIn());
//				if(tran.getIn().equals("null")){	
//				}
				if(tran.getIn()==null||tran.getIn().equals("null")){						
				}
				else{
					if(tran.getIn().contains("--")){
						String s[] = tran.getIn().split("--");
						for(String s1:s){
							if(cond==""){
								cond = s1.split("#")[0];
							}else{
								cond = cond + "," + s1.split("#")[0];
							}						 
						}
					}else{
						if(cond==""){
							cond = tran.getIn().split("#")[0];
						}else{
							cond = cond + "," +tran.getIn().split("#")[0];
						}						
					}
				}
				//System.out.println("condition里面内容："+tran.getCondition());
//				if(tran.getCondition().equals("null")){					
//				}
				if(tran.getCondition()==null||tran.getCondition().equals("null")){					
				}
				else{
					if(tran.getCondition().contains("--")){
						String s[] = tran.getCondition().split("--");
						for(String s1:s){
							if(cond==""){
								cond = s1.split("#")[0];
							}else{
								cond = cond + "," + s1.split("#")[0];
							}						 
						}
					}else{
						if(cond==""){
							cond = tran.getCondition().split("#")[0];
						}else{
							cond = cond + "," + tran.getCondition().split("#")[0];
						}	
						
					}
				}
				tran.setLimit(cond); //搜集条件约束，成为测试用例								
			}					
		}
		return auto;
	}
	
	/**
	 * 搜集时间自动机实例化后结果，写入时间自动机中
	 * @param testcase
	 * @return
	 */
	public static ArrayList<Automatic> collectResult(ArrayList<Automatic> testcase){
		for(Automatic a:testcase){		
			for(Transition tran:a.getTransitionSet()){
				//System.out.println("迁移内容:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果
				List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果
				if(tran.getName()==null){
					
				}
				else{
					if(tran.getName().contains("(")){
						int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
						sss=tran.getName().substring(0,index11);
						//System.out.println("迁移(激励)名称："+sss);
					}
					else{
						sss = tran.getName().replace("!", "").replace("?", "");
						//System.out.println("迁移(激励)名称："+sss);
					}
				}
				
				tran.setName(sss);//把迁移名称上特殊符号以及后面括号内容去掉								
				//处理in里面的内容
				//System.out.println("in---->"+tran.getIn());	//in里面的内容	
				if(tran.getIn()==null){	
					result1.add(null);
				}else{
					if(tran.getIn().contains("--")){
						List<List<String>> in_result = new ArrayList<List<String>>();
						List<String> in_result1;
						String getin[] = tran.getIn().split("--");
						for(int ii=0;ii<getin.length;ii++){
							if(!(GetMap.get_inMap(getin[ii])==null)){
								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->", "$");
								in_result1 = Result1.getResult(inn);
								if((in_result1.size()>0)&&!(in_result1.get(0).equals(null))){
									in_result.add(in_result1);
								}
							}
						}
						if((in_result.size()>0)&&!(in_result.get(0).equals(null))){
							//System.out.println("in_result.size()--->"+in_result.size());
							dis(0,in_result);
							result1 = re;
						}
					}else{
						if(!(GetMap.get_inMap(tran.getIn())==null)){//map里面为空，即没有参数
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
							result1 = Result1.getResult(inn);
						}else{
							if((GetMap.get_inMap(tran.getIn())==null)){
								result1.add(null);
							}
						}
					}
					}				
				//处理condition
				//System.out.println("condition---->"+tran.getCondition());
				if(tran.getCondition()==null){	
					result2.add(null);
				}else{
					if(tran.getCondition()!=null){
						if(GetMap.get_condMap(tran.getCondition())==null){
							result2.add(null);
						}else{
							if(!(GetMap.get_condMap(tran.getCondition())==null)){
								String tra = tran.getCondition().replace("false", "False").replace("true", "True").replace("->", "$");
								//System.out.println("tra----"+tra);
								result2 = Result1.getResult(tra);														
								for(int ii=0;ii<result2.size();ii++){
									//System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}
					}					
				}
				//组合解
				List<String> result=new ArrayList<String>();//存放一条迁移上的结果
				String res = new String();
				if((result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
					res = null;
					result.add(res);
				}else{
					if(!(result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							if(ttt2!=null){
								res = ttt2;
								result.add(res.toString());
							}
						}
					}
					if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt3:result2){
							if(ttt3!=null){
								res = ttt3;
								result.add(res.toString());
							}
						}
					}
					if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									res = ttt2+","+ttt3;
									result.add(res.toString());
								}									
							}
						}
					}
				}	
				tran.setResult(result);
			}//for(Transition tran:a.getTransitionSet())				
		}
		return testcase;
	}	
	/**
	 * 深度组合解
	 */
	public static String s="";
	public static List<String> re = new ArrayList<String>();
	public static void dis(int n, List<List<String>> l) {
		if (n == l.size()) {
			re.add(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				if(s==""){
					s  = sCollection.get(i);
				}else{
					s =s + ","+ sCollection.get(i);
				}				
				dis(n+1,l);
				s = s1;
			}
		}
	}
}
