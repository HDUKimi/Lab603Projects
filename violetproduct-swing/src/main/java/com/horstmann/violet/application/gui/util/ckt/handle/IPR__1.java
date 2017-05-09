package com.horstmann.violet.application.gui.util.ckt.handle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class IPR__1 {
	public static void main(String[] args) {
		/*Automatic automatic=Test_split_01_new.getAutomatic();
		Automatic newAutomatic=iPR(automatic);*/
	}
	/*
	 ������С���㷨����һ���µ�ʱ���Զ���
	 */
	public static Automatic iPR(Automatic automatic) {
		
//		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String TimeString = time.format(new java.util.Date());
//		System.out.println("  ��ֿ�ʼ����ʱ��"+TimeString);
		
		long time=System.currentTimeMillis();
		System.out.println("  IPR�ڲ���ʼ����ʱ��-----"+time+"ms");
		
		ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);
		
		long time1=System.currentTimeMillis();
		System.out.println("  Minimization������ʱ��-----"+(time1-time)+"ms");
		//System.out.println("��ֺ��״̬������ "+new_stateSet.size());
		/*for(State s:new_stateSet){
			System.out.println("״̬name: "+s.getName());
			System.out.println("״̬position: "+s.getPosition());
			DBM_element[][] DBM=s.getInvariantDBM();
			DBM_element[][] fDBM=Floyds.floyds(DBM);
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					DBM_element cons=fDBM[i][j];
					//System.out.println("DBM_i:"+cons.getDBM_i());
					//System.out.println("DBM_j:"+cons.getDBM_j());
					System.out.println("value:"+cons.getValue());
					System.out.println("Strictness:"+cons.isStrictness());									
				}
			}
			System.out.println("*******************************");
		}*/
		
		long time2=System.currentTimeMillis();
		System.out.println("  bulidRelation��ʼ����ʱ��-----"+time2+"ms");
		ArrayList<Transition> transitions=BuildRelation__1.bulidRelation(automatic,new_stateSet);
		long time3=System.currentTimeMillis();
		System.out.println("  bulidRelation�ܺ�ʱ-----"+(time3-time2)+"ms");
		
		/*System.out.println("�ߵ�������"+transitions.size());
		for(Transition t:transitions){
			System.out.println(t.getSource());
			System.out.println(t.getTarget());
			if(t.getEventSet().size()!=0){
				System.out.println(t.getEventSet().get(0));
			}
			System.out.println(t.getTypeIds());
			System.out.println(t.getTypes());
			System.out.println("********");
		}*/
		
		Automatic newaotu=new Automatic();//����һ���µ�ʱ���Զ���
		State intiState=new State();
		for(State s:new_stateSet){//�ж��ĸ��ǳ�ʼ״̬
			int h=Minimization__1.includeZero(s.getInvariantDBM());
			if(s.getPosition().equals(automatic.getInitState().getPosition())&&h==1){
				intiState.setName(s.getName());
				intiState.setId(s.getId());
				intiState.setPosition(s.getPosition());
				intiState.setInvariantDBM(s.getInvariantDBM());
				//System.out.println(intiState.getName());
				//System.out.println(intiState.getPosition());
			}
		}
		/*///////////////////////ckt���
		for(Transition tran:automatic.getTransitionSet()){
			
		}
			
		////////////////////////////////////////////
*/			
		newaotu.setClockSet(automatic.getClockSet());
		newaotu.setInitState(intiState);
		newaotu.setStateSet(new_stateSet);
		newaotu.setTransitionSet(transitions);
		newaotu.setName("G");
		
//		TimeString = time.format(new java.util.Date());
//		System.out.println("  ��ֽ�����ʱ��"+TimeString);
		System.out.println("  Minimization������ʱ��-----"+(time1-time)+"ms");
		long time4=System.currentTimeMillis();
		System.out.println("  IPR�ڲ��ܺ�ʱ��-----"+(time4-time)+"ms");
		
		return newaotu;
	}
}
