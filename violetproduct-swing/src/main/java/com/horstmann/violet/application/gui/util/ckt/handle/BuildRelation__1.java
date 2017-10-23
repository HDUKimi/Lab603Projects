package com.horstmann.violet.application.gui.util.ckt.handle;

import java.util.ArrayList;



public class BuildRelation__1 {
	public static void main(String[] args) {
		/*Automatic automatic=Test_split_01_new.getAutomatic();
		ArrayList<Transition> transitions=bulidRelation(automatic);
		System.out.println("�ߵ�������"+transitions.size());
		for(Transition t:transitions){
			System.out.println(t.getSource());
			System.out.println(t.getTarget());
			System.out.println(t.getEventSet().get(0));
			System.out.println("********");
		}*/
	}
	
	public static ArrayList<Transition> bulidRelation(Automatic automatic,ArrayList<State> new_stateSet) {
		long time=System.currentTimeMillis();
		//System.out.println("  bulidRelation�ڿ�ʼʱ��-----"+time+"ms");
		ArrayList<Transition> TransitionSet=automatic.getTransitionSet();//���ʱ���Զ���Ǩ�Ƽ���
		ArrayList<String> ClockSet=automatic.getClockSet();//���ʱ���Զ���ʱ�Ӽ���

		//ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);//���ʱ���Զ���
		
		ArrayList<Transition> transitions=new ArrayList<Transition>();//Ҫ���ص�Ǩ�Ƽ���
		long time1=System.currentTimeMillis();
		//System.out.println("  ����״̬����ǰ����ʱ��-----"+(time1-time)+"ms");
		for(State source:new_stateSet){//����״̬����	
			long time2=System.currentTimeMillis();
		//	System.out.println("  ��ú��ǰ����ʱ��-----"+(time2-time)+"ms");
			
			ArrayList<State> posts=PostAndPre__1.post(source, new_stateSet, TransitionSet, ClockSet);//���source���
			long time3=System.currentTimeMillis();
		//	System.out.println("  ��ú������ʱ��-----"+(time3-time2)+"ms");
			
			for(State target:posts){//������̼��ϣ�����Ǩ��
				Transition tran=new Transition();
				tran.setSource(source.getName());
				tran.setTarget(target.getName());
				//////////////////////////////////////////////////////////

				//////////////////////////////////////////////////////////				
				if(source.getPosition().equals(target.getPosition())){//���Դ��Ŀ����ͬ����Ǩ����Ϊ*
					ArrayList<String> events=new ArrayList<String>();
					events.add("*");
					tran.setEventSet(events);
					///////////////////////////////////////////////////
					tran.setId(0000);
					tran.setName("*");
					//////////////////////////////////////////////////
				}
				else{//���Դ��Ŀ�Ĳ�ͬ����Ǩ����Ϊ��Ӧ�¼�
					for(Transition t:TransitionSet){
						if(source.getPosition().equals(t.getSource())&&target.getPosition().equals(t.getTarget())){
							tran.setEventSet(t.getEventSet());
							tran.setTypeIds(t.getTypeIds());
							tran.setTypes(t.getTypes());
							//////////////////////////////////////////////////////ckt���
							tran.setId(t.getId());
							tran.setName(t.getName());
							tran.setIn(t.getIn());
							tran.setCondition(t.getCondition());
							tran.setOut(t.getOut());
							//////////////////////////////////////////////////////
						}						
					}
					
				}						
				transitions.add(tran);
			}
		}

	//	System.out.println("  ����״̬����ǰ����ʱ��-----"+(time1-time)+"ms");
		long time2=System.currentTimeMillis();
	//	System.out.println("  ����״̬��������ʱ��-----"+(time2-time1)+"ms");
		return transitions;
	}
}
