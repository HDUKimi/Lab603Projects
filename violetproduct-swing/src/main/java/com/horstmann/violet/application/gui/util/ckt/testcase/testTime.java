package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class testTime {

	public static void main(String[] args) {
		// String xml="Draw MoneyForXStream(2).xml";
		// String xml="UAVForXStream3.1.6.xml";
		// String xml = "UAVForXStreamXuanTing.xml";
//		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
//		String TimeString = time.format(new java.util.Date());
//		System.out.println("开始计算时间"+TimeString);
		long time=System.currentTimeMillis();
		System.out.println("开始计算时间"+time);
		//String xml = "UAVForXStreamTimeV4.1.0.xml";
		String xml = "Package1ForXStream.xml";

		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		
//		SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
//		String TimeString1 = time1.format(new java.util.Date());
//		System.out.println("读取xml后时间"+TimeString1);
		long time1=System.currentTimeMillis();
		System.out.println("读取XML所用时间-----"+(time1-time)+"ms");
		
		
		System.out.println("-----"+automatic.getStateSet().size());
		System.out.println("-----"+automatic.getTransitionSet().size());
		// print(automatic);
		for (String c : automatic.getClockSet()) {
			System.out.println(c);
		}
		// ArrayList<State>
		// new_stateSet=Minimization__1.minimization(automatic);
		long time2=System.currentTimeMillis();
		
		Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		
		long time3=System.currentTimeMillis();
		System.out.println("IPR拆分所用时间-----"+(time3-time2)+"ms");
		
		System.out.println("---=====--"+new_automatic.getStateSet().size());
		System.out.println("---=====--"+new_automatic.getTransitionSet().size());
		
//		SimpleDateFormat time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString2 = time2.format(new java.util.Date());
//		System.out.println("拆分后时间"+TimeString2);
		
		System.out.println("-----------1");
		// print(new_automatic);
		long time4=System.currentTimeMillis();
		
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		
		long time5=System.currentTimeMillis();
		System.out.println("ATDTR所用时间-----"+(time5-time4)+"ms");
//		
//		SimpleDateFormat time3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString3 = time3.format(new java.util.Date());
//		System.out.println("去除抽象迁移后时间"+TimeString3);
		
		// print(aTDRTAutomatic);
		System.out.println(automatic.getStateSet().size()+"---"+automatic.getTransitionSet().size());
		System.out.println(new_automatic.getStateSet().size()+"---"+new_automatic.getTransitionSet().size());
		System.out.println(aTDRTAutomatic.getStateSet().size()+"---"+aTDRTAutomatic.getTransitionSet().size());
		System.out.println("-----------2");
		// Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		long time6=System.currentTimeMillis();
		
		ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// 获得满足状态覆盖的抽象测试序列
		//ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic,1);//获得满足路径覆盖的抽象测试序列
		
		
		long time7=System.currentTimeMillis();
		System.out.println("testcase所用时间-----"+(time7-time6)+"ms");
		
//		SimpleDateFormat time4=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString4 = time4.format(new java.util.Date());


		
		System.out.println("-----------3");
		System.out.println("测试用例的个数" + testCase.size());
		// ArrayList<Automatic>
		// ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic,2000);//获得满足路径覆盖的抽象测试序列

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// 每个抽象测试序列有一个不等式组
		System.out.println("-----------4");
		System.out.println("不等式的个数" + testCase.size());

		// System.out.println("时间自动机时钟集合：");

		/*
		 * System.out.println("时间自动机名字:"+automatic.getName());
		 * System.out.println("时间自动机时钟集合："); for(String
		 * c:automatic.getClockSet()){ System.out.println(c); } State
		 * iniState=automatic.getInitState();
		 * System.out.println("初始状态名字："+iniState.getName());
		 * System.out.println(iniState.getPosition());
		 * System.out.println(iniState.isFinalState()); DBM_element[][]
		 * DBM=iniState.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=DBM[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("状态个数："+automatic.getStateSet().size()); int k=0;
		 * for(State state:automatic.getStateSet()){
		 * System.out.println("第"+k+"个状态"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("状态名称: "+state.getName());
		 * System.out.println("状态位置: "+state.getPosition());
		 * System.out.println("是否为终止状态 : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("迁移个数"+automatic.getTransitionSet().size()); int
		 * p=0; for(Transition tran:automatic.getTransitionSet()){
		 * System.out.println("第"+p+"条迁移"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("时钟约束为空");
		 * 
		 * System.out.println("源:"+tran.getSource());
		 * System.out.println("目的："+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("事件为空"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("事件为不空，但是没有内容");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("事件："+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("重置时钟为空"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("重置时钟为不空，但是没有内容"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("重置的时钟："+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeID为空"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeID为不空，但是没有内容"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("types为空"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("types为不空，但是没有内容");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("拆分后的时间自动机名字:"+new_automatic.getName());
		 * System.out.println("时间自动机时钟集合："); for(String
		 * c:new_automatic.getClockSet()){ System.out.println(c); } State
		 * iniState=new_automatic.getInitState();
		 * System.out.println("初始状态名字："+iniState.getName());
		 * System.out.println(iniState.getPosition());
		 * System.out.println(iniState.isFinalState()); DBM_element[][]
		 * DBM=iniState.getInvariantDBM(); for(int
		 * i=0;i<new_automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<new_automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=DBM[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * 
		 * System.out.println("状态个数："+new_automatic.getStateSet().size()); int
		 * k=0; for(State state:new_automatic.getStateSet()){
		 * System.out.println("第"+k+"个状态"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("状态名称: "+state.getName());
		 * System.out.println("状态位置: "+state.getPosition());
		 * System.out.println("是否为终止状态 : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("迁移个数"+new_automatic.getTransitionSet().size());
		 * int p=0; for(Transition tran:new_automatic.getTransitionSet()){
		 * System.out.println("第"+p+"条迁移"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("时钟约束为空");
		 * 
		 * System.out.println("源:"+tran.getSource());
		 * System.out.println("目的："+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("事件为空"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("事件为不空，但是没有内容");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("事件："+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("重置时钟为空"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("重置时钟为不空，但是没有内容"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("重置的时钟："+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeID为空"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeID为不空，但是没有内容"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("types为空"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("types为不空，但是没有内容");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("约简后的时间自动机名字:"+aTDRTAutomatic.getName());
		 * System.out.println("时间自动机时钟集合："); for(String
		 * c:aTDRTAutomatic.getClockSet()){ System.out.println(c); } State
		 * iniState=aTDRTAutomatic.getInitState();
		 * System.out.println("初始状态名字："+iniState.getName());
		 * System.out.println(iniState.getPosition());
		 * System.out.println(iniState.isFinalState()); DBM_element[][]
		 * DBM=iniState.getInvariantDBM(); for(int
		 * i=0;i<aTDRTAutomatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<aTDRTAutomatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=DBM[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * 
		 * System.out.println("状态个数："+aTDRTAutomatic.getStateSet().size()); int
		 * k=0; for(State state:aTDRTAutomatic.getStateSet()){
		 * System.out.println("第"+k+"个状态"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("状态名称: "+state.getName());
		 * System.out.println("状态位置: "+state.getPosition());
		 * System.out.println("是否为终止状态 : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("迁移个数"+aTDRTAutomatic.getTransitionSet().size());
		 * int p=0; for(Transition tran:aTDRTAutomatic.getTransitionSet()){
		 * System.out.println("第"+p+"条迁移"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("时钟约束为空");
		 * 
		 * System.out.println("源:"+tran.getSource());
		 * System.out.println("目的："+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("事件为空"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("事件为不空，但是没有内容");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("事件："+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("重置时钟为空"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("重置时钟为不空，但是没有内容"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("重置的时钟："+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeID为空"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeID为不空，但是没有内容"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("types为空"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("types为不空，但是没有内容");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("抽象测试序列个数："+testCase.size()); for(Automatic
		 * a:testCase){ System.out.println("时间自动机名字:"+a.getName());
		 * System.out.println("时间自动机时钟集合："); for(String c:a.getClockSet()){
		 * System.out.println(c); } State iniState=a.getInitState();
		 * System.out.println("初始状态名字："+iniState.getName());
		 * System.out.println(iniState.getPosition());
		 * System.out.println(iniState.isFinalState()); DBM_element[][]
		 * DBM=iniState.getInvariantDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=DBM[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("-----------------");
		 * 
		 * System.out.println("状态个数："+a.getStateSet().size()); int k=0;
		 * for(State state:a.getStateSet()){ System.out.println("第"+k+"个状态");
		 * k++; DBM_element[][] dbm=state.getInvariantDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=dbm[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("状态名称: "+state.getName());
		 * System.out.println("状态位置: "+state.getPosition());
		 * System.out.println("是否为终止状态 : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=adddbm[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("------"); }
		 * System.out.println("*****************");
		 * 
		 * System.out.println("迁移个数"+a.getTransitionSet().size()); int p=0;
		 * for(Transition tran:a.getTransitionSet()){
		 * System.out.println("第"+p+"条迁移"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=dbm[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("时钟约束为空");
		 * 
		 * System.out.println("源:"+tran.getSource());
		 * System.out.println("目的："+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("事件为空"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("事件为不空，但是没有内容");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("事件："+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("重置时钟为空"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("重置时钟为不空，但是没有内容"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("重置的时钟："+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeID为空"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeID为不空，但是没有内容"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("types为空"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("types为不空，但是没有内容");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 * System.out.println("_________________"); }
		 */

		System.out.println("总共" + all_inequalitys.size() + "个不等式组");
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("第" + e + "个不等式组");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		
//		System.out.println("开始计算时间"+TimeString);
//		System.out.println("读取xml后时间"+TimeString1);
//		System.out.println("拆分后时间"+TimeString2);
//		System.out.println("去除抽象迁移后时间"+TimeString3);
//		System.out.println("生成测试序列后时间"+TimeString4);
		
		System.out.println("总共所用时间-----"+(time7-time)+"ms");
		System.out.println(" 读取XML所用时间-----"+(time1-time)+"ms");
		System.out.println(" IPR拆分所用时间-----"+(time3-time2)+"ms");
		System.out.println(" ATDTR所用时间-----"+(time5-time4)+"ms");
		System.out.println(" testcase所用时间-----"+(time7-time6)+"ms");


	}

	/**
	 * 打印时间自动机上的name和id
	 * 
	 * @param a
	 */
	public static void print(Automatic a) {
		int i = 1;
		for (State state : a.getStateSet()) {
			System.out.println("*****************第" + (i++) + "个状态*****************");
			System.out.println("状态名称：" + state.getName());
			// System.out.println("状态Id："+state.getId());

		}
		i = 1;
		for (Transition t : a.getTransitionSet()) {
			System.out.println("*****************第" + (i++) + "个迁移*****************");
			System.out.println("迁移名称：" + t.getName());
			System.out.println("迁移Id：" + t.getId());
			// System.out.println("迁移总约束："+t.getEventSet());
			// System.out.println("迁移in："+t.getIn());
			// System.out.println("迁移condition："+t.getCondition());
		}
	}
}
