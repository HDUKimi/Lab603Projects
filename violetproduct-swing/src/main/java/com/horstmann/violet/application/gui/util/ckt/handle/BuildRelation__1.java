package com.horstmann.violet.application.gui.util.ckt.handle;

import java.util.ArrayList;



public class BuildRelation__1 {
	public static void main(String[] args) {
		/*Automatic automatic=Test_split_01_new.getAutomatic();
		ArrayList<Transition> transitions=bulidRelation(automatic);
		System.out.println("边的数量："+transitions.size());
		for(Transition t:transitions){
			System.out.println(t.getSource());
			System.out.println(t.getTarget());
			System.out.println(t.getEventSet().get(0));
			System.out.println("********");
		}*/
	}
	
	public static ArrayList<Transition> bulidRelation(Automatic automatic,ArrayList<State> new_stateSet) {
		long time=System.currentTimeMillis();
		//System.out.println("  bulidRelation内开始时间-----"+time+"ms");
		ArrayList<Transition> TransitionSet=automatic.getTransitionSet();//获得时间自动机迁移集合
		ArrayList<String> ClockSet=automatic.getClockSet();//获得时间自动机时钟集合

		//ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);//拆分时间自动机
		
		ArrayList<Transition> transitions=new ArrayList<Transition>();//要返回的迁移集合
		long time1=System.currentTimeMillis();
		//System.out.println("  遍历状态集合前所用时间-----"+(time1-time)+"ms");
		for(State source:new_stateSet){//遍历状态集合	
			long time2=System.currentTimeMillis();
		//	System.out.println("  获得后继前所用时间-----"+(time2-time)+"ms");
			
			ArrayList<State> posts=PostAndPre__1.post(source, new_stateSet, TransitionSet, ClockSet);//获得source后继
			long time3=System.currentTimeMillis();
		//	System.out.println("  获得后继所用时间-----"+(time3-time2)+"ms");
			
			for(State target:posts){//遍历后继集合，建立迁移
				Transition tran=new Transition();
				tran.setSource(source.getName());
				tran.setTarget(target.getName());
				//////////////////////////////////////////////////////////

				//////////////////////////////////////////////////////////				
				if(source.getPosition().equals(target.getPosition())){//如果源和目的相同，则迁移上为*
					ArrayList<String> events=new ArrayList<String>();
					events.add("*");
					tran.setEventSet(events);
					///////////////////////////////////////////////////
					tran.setId(0000);
					tran.setName("*");
					//////////////////////////////////////////////////
				}
				else{//如果源和目的不同，则迁移上为相应事件
					for(Transition t:TransitionSet){
						if(source.getPosition().equals(t.getSource())&&target.getPosition().equals(t.getTarget())){
							tran.setEventSet(t.getEventSet());
							tran.setTypeIds(t.getTypeIds());
							tran.setTypes(t.getTypes());
							//////////////////////////////////////////////////////ckt添加
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

	//	System.out.println("  遍历状态集合前所用时间-----"+(time1-time)+"ms");
		long time2=System.currentTimeMillis();
	//	System.out.println("  遍历状态集合所用时间-----"+(time2-time1)+"ms");
		return transitions;
	}
}
