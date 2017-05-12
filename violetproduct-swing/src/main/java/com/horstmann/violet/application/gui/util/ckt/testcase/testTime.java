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
//		System.out.println("��ʼ����ʱ��"+TimeString);
		long time=System.currentTimeMillis();
		System.out.println("��ʼ����ʱ��"+time);
		//String xml = "UAVForXStreamTimeV4.1.0.xml";
		String xml = "Package1ForXStream.xml";

		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		
//		SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
//		String TimeString1 = time1.format(new java.util.Date());
//		System.out.println("��ȡxml��ʱ��"+TimeString1);
		long time1=System.currentTimeMillis();
		System.out.println("��ȡXML����ʱ��-----"+(time1-time)+"ms");
		
		
		System.out.println("-----"+automatic.getStateSet().size());
		System.out.println("-----"+automatic.getTransitionSet().size());
		// print(automatic);
		for (String c : automatic.getClockSet()) {
			System.out.println(c);
		}
		// ArrayList<State>
		// new_stateSet=Minimization__1.minimization(automatic);
		long time2=System.currentTimeMillis();
		
		Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		
		long time3=System.currentTimeMillis();
		System.out.println("IPR�������ʱ��-----"+(time3-time2)+"ms");
		
		System.out.println("---=====--"+new_automatic.getStateSet().size());
		System.out.println("---=====--"+new_automatic.getTransitionSet().size());
		
//		SimpleDateFormat time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString2 = time2.format(new java.util.Date());
//		System.out.println("��ֺ�ʱ��"+TimeString2);
		
		System.out.println("-----------1");
		// print(new_automatic);
		long time4=System.currentTimeMillis();
		
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		
		long time5=System.currentTimeMillis();
		System.out.println("ATDTR����ʱ��-----"+(time5-time4)+"ms");
//		
//		SimpleDateFormat time3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString3 = time3.format(new java.util.Date());
//		System.out.println("ȥ������Ǩ�ƺ�ʱ��"+TimeString3);
		
		// print(aTDRTAutomatic);
		System.out.println(automatic.getStateSet().size()+"---"+automatic.getTransitionSet().size());
		System.out.println(new_automatic.getStateSet().size()+"---"+new_automatic.getTransitionSet().size());
		System.out.println(aTDRTAutomatic.getStateSet().size()+"---"+aTDRTAutomatic.getTransitionSet().size());
		System.out.println("-----------2");
		// Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		long time6=System.currentTimeMillis();
		
		ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// �������״̬���ǵĳ����������
		//ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic,1);//�������·�����ǵĳ����������
		
		
		long time7=System.currentTimeMillis();
		System.out.println("testcase����ʱ��-----"+(time7-time6)+"ms");
		
//		SimpleDateFormat time4=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString4 = time4.format(new java.util.Date());


		
		System.out.println("-----------3");
		System.out.println("���������ĸ���" + testCase.size());
		// ArrayList<Automatic>
		// ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic,2000);//�������·�����ǵĳ����������

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// ÿ���������������һ������ʽ��
		System.out.println("-----------4");
		System.out.println("����ʽ�ĸ���" + testCase.size());

		// System.out.println("ʱ���Զ���ʱ�Ӽ��ϣ�");

		/*
		 * System.out.println("ʱ���Զ�������:"+automatic.getName());
		 * System.out.println("ʱ���Զ���ʱ�Ӽ��ϣ�"); for(String
		 * c:automatic.getClockSet()){ System.out.println(c); } State
		 * iniState=automatic.getInitState();
		 * System.out.println("��ʼ״̬���֣�"+iniState.getName());
		 * System.out.println(iniState.getPosition());
		 * System.out.println(iniState.isFinalState()); DBM_element[][]
		 * DBM=iniState.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=DBM[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("״̬������"+automatic.getStateSet().size()); int k=0;
		 * for(State state:automatic.getStateSet()){
		 * System.out.println("��"+k+"��״̬"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("״̬����: "+state.getName());
		 * System.out.println("״̬λ��: "+state.getPosition());
		 * System.out.println("�Ƿ�Ϊ��ֹ״̬ : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("Ǩ�Ƹ���"+automatic.getTransitionSet().size()); int
		 * p=0; for(Transition tran:automatic.getTransitionSet()){
		 * System.out.println("��"+p+"��Ǩ��"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("ʱ��Լ��Ϊ��");
		 * 
		 * System.out.println("Դ:"+tran.getSource());
		 * System.out.println("Ŀ�ģ�"+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("�¼�Ϊ��"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("�¼�Ϊ���գ�����û������");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("�¼���"+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("����ʱ��Ϊ��"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("����ʱ��Ϊ���գ�����û������"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("���õ�ʱ�ӣ�"+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeIDΪ��"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeIDΪ���գ�����û������"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("typesΪ��"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("typesΪ���գ�����û������");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("��ֺ��ʱ���Զ�������:"+new_automatic.getName());
		 * System.out.println("ʱ���Զ���ʱ�Ӽ��ϣ�"); for(String
		 * c:new_automatic.getClockSet()){ System.out.println(c); } State
		 * iniState=new_automatic.getInitState();
		 * System.out.println("��ʼ״̬���֣�"+iniState.getName());
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
		 * System.out.println("״̬������"+new_automatic.getStateSet().size()); int
		 * k=0; for(State state:new_automatic.getStateSet()){
		 * System.out.println("��"+k+"��״̬"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("״̬����: "+state.getName());
		 * System.out.println("״̬λ��: "+state.getPosition());
		 * System.out.println("�Ƿ�Ϊ��ֹ״̬ : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("Ǩ�Ƹ���"+new_automatic.getTransitionSet().size());
		 * int p=0; for(Transition tran:new_automatic.getTransitionSet()){
		 * System.out.println("��"+p+"��Ǩ��"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("ʱ��Լ��Ϊ��");
		 * 
		 * System.out.println("Դ:"+tran.getSource());
		 * System.out.println("Ŀ�ģ�"+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("�¼�Ϊ��"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("�¼�Ϊ���գ�����û������");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("�¼���"+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("����ʱ��Ϊ��"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("����ʱ��Ϊ���գ�����û������"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("���õ�ʱ�ӣ�"+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeIDΪ��"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeIDΪ���գ�����û������"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("typesΪ��"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("typesΪ���գ�����û������");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("Լ����ʱ���Զ�������:"+aTDRTAutomatic.getName());
		 * System.out.println("ʱ���Զ���ʱ�Ӽ��ϣ�"); for(String
		 * c:aTDRTAutomatic.getClockSet()){ System.out.println(c); } State
		 * iniState=aTDRTAutomatic.getInitState();
		 * System.out.println("��ʼ״̬���֣�"+iniState.getName());
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
		 * System.out.println("״̬������"+aTDRTAutomatic.getStateSet().size()); int
		 * k=0; for(State state:aTDRTAutomatic.getStateSet()){
		 * System.out.println("��"+k+"��״̬"); k++; DBM_element[][]
		 * dbm=state.getInvariantDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("״̬����: "+state.getName());
		 * System.out.println("״̬λ��: "+state.getPosition());
		 * System.out.println("�Ƿ�Ϊ��ֹ״̬ : "+state.isFinalState());
		 * DBM_element[][] adddbm=state.getAddClockRelationDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=adddbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("--------------------"); }
		 * 
		 * System.out.println("Ǩ�Ƹ���"+aTDRTAutomatic.getTransitionSet().size());
		 * int p=0; for(Transition tran:aTDRTAutomatic.getTransitionSet()){
		 * System.out.println("��"+p+"��Ǩ��"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<automatic.getClockSet().size()+1;i++){ for(int
		 * j=0;j<automatic.getClockSet().size()+1;j++){ DBM_element
		 * cons=dbm[i][j]; //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("ʱ��Լ��Ϊ��");
		 * 
		 * System.out.println("Դ:"+tran.getSource());
		 * System.out.println("Ŀ�ģ�"+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("�¼�Ϊ��"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("�¼�Ϊ���գ�����û������");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("�¼���"+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("����ʱ��Ϊ��"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("����ʱ��Ϊ���գ�����û������"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("���õ�ʱ�ӣ�"+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeIDΪ��"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeIDΪ���գ�����û������"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("typesΪ��"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("typesΪ���գ�����û������");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 */

		/*
		 * System.out.println("����������и�����"+testCase.size()); for(Automatic
		 * a:testCase){ System.out.println("ʱ���Զ�������:"+a.getName());
		 * System.out.println("ʱ���Զ���ʱ�Ӽ��ϣ�"); for(String c:a.getClockSet()){
		 * System.out.println(c); } State iniState=a.getInitState();
		 * System.out.println("��ʼ״̬���֣�"+iniState.getName());
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
		 * System.out.println("״̬������"+a.getStateSet().size()); int k=0;
		 * for(State state:a.getStateSet()){ System.out.println("��"+k+"��״̬");
		 * k++; DBM_element[][] dbm=state.getInvariantDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=dbm[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } }
		 * System.out.println("״̬����: "+state.getName());
		 * System.out.println("״̬λ��: "+state.getPosition());
		 * System.out.println("�Ƿ�Ϊ��ֹ״̬ : "+state.isFinalState());
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
		 * System.out.println("Ǩ�Ƹ���"+a.getTransitionSet().size()); int p=0;
		 * for(Transition tran:a.getTransitionSet()){
		 * System.out.println("��"+p+"��Ǩ��"); p++;
		 * if(tran.getConstraintDBM()!=null){ DBM_element[][]
		 * dbm=tran.getConstraintDBM(); for(int
		 * i=0;i<a.getClockSet().size()+1;i++){ for(int
		 * j=0;j<a.getClockSet().size()+1;j++){ DBM_element cons=dbm[i][j];
		 * //System.out.println("DBM_i:"+cons.getDBM_i());
		 * //System.out.println("DBM_j:"+cons.getDBM_j());
		 * System.out.println("value:"+cons.getValue());
		 * System.out.println("Strictness:"+cons.isStrictness()); } } } else
		 * System.out.println("ʱ��Լ��Ϊ��");
		 * 
		 * System.out.println("Դ:"+tran.getSource());
		 * System.out.println("Ŀ�ģ�"+tran.getTarget());
		 * 
		 * 
		 * if(tran.getEventSet()==null){ System.out.println("�¼�Ϊ��"); } else
		 * if(tran.getEventSet().size()==0){ System.out.println("�¼�Ϊ���գ�����û������");
		 * } else{ ArrayList<String> events=tran.getEventSet(); for(String
		 * e:events){ System.out.println("�¼���"+e); } }
		 * 
		 * if(tran.getResetClockSet()==null){ System.out.println("����ʱ��Ϊ��"); }
		 * else if(tran.getResetClockSet().size()==0){
		 * System.out.println("����ʱ��Ϊ���գ�����û������"); } else{ ArrayList<String>
		 * reset=tran.getResetClockSet(); for(String r:reset){
		 * System.out.println("���õ�ʱ�ӣ�"+r); } }
		 * 
		 * if(tran.getTypeIds()==null){ System.out.println("typeIDΪ��"); } else
		 * if(tran.getTypeIds().size()==0){
		 * System.out.println("typeIDΪ���գ�����û������"); } else{ ArrayList<String>
		 * typeid=tran.getTypeIds(); for(String i:typeid){
		 * System.out.println("typeid:"+i); } }
		 * 
		 * if(tran.getTypes()==null){ System.out.println("typesΪ��"); } else
		 * if(tran.getTypes().size()==0){ System.out.println("typesΪ���գ�����û������");
		 * } else{ ArrayList<String> type=tran.getTypes(); for(String t:type){
		 * System.out.println("types:"+t); } }
		 * 
		 * System.out.println("********************"); }
		 * System.out.println("_________________"); }
		 */

		System.out.println("�ܹ�" + all_inequalitys.size() + "������ʽ��");
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("��" + e + "������ʽ��");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		
//		System.out.println("��ʼ����ʱ��"+TimeString);
//		System.out.println("��ȡxml��ʱ��"+TimeString1);
//		System.out.println("��ֺ�ʱ��"+TimeString2);
//		System.out.println("ȥ������Ǩ�ƺ�ʱ��"+TimeString3);
//		System.out.println("���ɲ������к�ʱ��"+TimeString4);
		
		System.out.println("�ܹ�����ʱ��-----"+(time7-time)+"ms");
		System.out.println(" ��ȡXML����ʱ��-----"+(time1-time)+"ms");
		System.out.println(" IPR�������ʱ��-----"+(time3-time2)+"ms");
		System.out.println(" ATDTR����ʱ��-----"+(time5-time4)+"ms");
		System.out.println(" testcase����ʱ��-----"+(time7-time6)+"ms");


	}

	/**
	 * ��ӡʱ���Զ����ϵ�name��id
	 * 
	 * @param a
	 */
	public static void print(Automatic a) {
		int i = 1;
		for (State state : a.getStateSet()) {
			System.out.println("*****************��" + (i++) + "��״̬*****************");
			System.out.println("״̬���ƣ�" + state.getName());
			// System.out.println("״̬Id��"+state.getId());

		}
		i = 1;
		for (Transition t : a.getTransitionSet()) {
			System.out.println("*****************��" + (i++) + "��Ǩ��*****************");
			System.out.println("Ǩ�����ƣ�" + t.getName());
			System.out.println("Ǩ��Id��" + t.getId());
			// System.out.println("Ǩ����Լ����"+t.getEventSet());
			// System.out.println("Ǩ��in��"+t.getIn());
			// System.out.println("Ǩ��condition��"+t.getCondition());
		}
	}
}
