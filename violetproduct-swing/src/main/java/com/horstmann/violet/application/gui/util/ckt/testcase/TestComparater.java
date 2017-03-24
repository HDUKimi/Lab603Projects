package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Minimization__1;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TestComparater {

	public static void main(String[] args) {
		/**
		 * 
		 */
		/*String xml="111.xml";//测试的xml
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic a=DFSTree(auto);*/
		//ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列
		
		
		
		/**
		 * 测试sort
		 */
		/*List<String> list = new ArrayList<String>();
		list.add("i");
		list.add("_num_i");
		list.add("abc");
		System.out.println(list.get(0)+"---"+list.get(1)+"---"+list.get(2));
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				
				return str2.length()-str1.length();
			}
		});
		System.out.println(list.get(0)+"---"+list.get(1)+"---"+list.get(2));*/
		
		/**
		 * 测试random
		 */
		/*int random = -1;
		if (random == -1) {
			random = new Random().nextInt(2);
		}
		System.out.println("random-->"+random);*/
		/**
		 * 
		 */
		/*List<String> result1=new ArrayList<String>();
		result1.add(null);
		System.out.println(result1.toString());
		if(result1.toString().equals("[null]")){
			System.out.println("0000");
		}else{
			System.out.println("1111");
		}
		System.out.println(result1.size());*/
		/**
		 * 
		 */
		/*String con = "i#i:int";
		String[] s = con.split("--");
		System.out.println(s.length);
		System.out.println(s[0]);*/
		//System.out.println(s[1]);

		/*String[] results = null;
		String solution1="{{i-> 34}, {i-> 168}, {i-> 0}}";
		results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");
		for(int i=0;i<results.length;i++){
			System.out.println("results22----"+results[i]);
		}		
		System.out.println("condition上整数型约束解22为："+solution1);*/
		/*String tra = "cycle=2.5ms--control_mode==0#control_mode:int8_t#0<=control_mode<=14--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool";
		List<String> result2=new ArrayList<String>();
		result2 = Result.getResult(tra);
		for(int ii=0;ii<result2.size();ii++){
			System.out.println("condition里解"+ii+"为:"+result2.get(ii));
		}*/
		
//		Date date=new Date();
//		DateFormat format=new SimpleDateFormat("HH:mm:ss");
//		String time=format.format(date);
//		System.out.println(time);
//		
//		Date date1=new Date();
//		DateFormat format1=new SimpleDateFormat("HH:mm:ss");
//		String time1=format1.format(date1);
//		
//		DateFormat df = new SimpleDateFormat("HH:mm:ss");   
//		try  
//		{   System.out.println("time---"+time);
//		System.out.println("time1---"+time1);
//		    Date d1 = df.parse(time);   
//		    Date d2 = df.parse(time1);   
//		    long l = d2.getTime() - d1.getTime();   
//		    long day=l/(24*60*60*1000);   
//		    long hour=(l/(60*60*1000)-day*24);   
//		    long min=((l/(60*1000))-day*24*60-hour*60);   
//		    long s=(l/1000-day*24*60*60-hour*60*60-min*60); 
//		    System.out.println("运行时间："+ ""+day+"天"+hour+"小时"+min+"分"+s+"秒");
//		    //System.out.println("运行时间："+ l);
//		}   
//		catch (Exception e)   
//		{   
//		}
		
		//Mathematica.closes();
	}
	
	/**
	 *根据去除抽象迁移的图生成深度优先生成树
	 * @param auto
	 * @return
	 */
	public static Automatic DFSTree(Automatic auto){
		ArrayList<Transition> auto_Transition=auto.getTransitionSet();//获得时间自动机迁移集合
		ArrayList<Transition> TransitionSet=new ArrayList<Transition>();//保存约生成树的迁移集合
		ArrayList<State> auto_StateSet=auto.getStateSet();//获得时间自动机状态集合
		ArrayList<State> StateSet=new ArrayList<State>();//保存生成树后的状态集合（保存已访问过的状态集合）
		
		Stack<State> sstack=new Stack<State>();//状态栈
		State intstate=auto.getInitState();//获得时间自动机的初始状态
		sstack.push(intstate);//初始状态入栈
		StateSet.add(intstate);//将初始状态加入生成树的状态集合，也表示为已访问过
		System.out.println("栈："+sstack);
		System.out.println("树状态集合："+StateSet);
		while(!sstack.isEmpty()){
			State X=sstack.peek();//获得栈顶元素，但不出栈
			System.out.println("此时获取的栈中元素（状态）："+X.getName());
			int flag=0;//标志X的邻接点都被访问过
			for(Transition tran:auto_Transition){//判断目标状态是否已被访问
				if(X.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					int k=0;
					for(State state:StateSet){//判断迁移的目的状态是否被访问过，用k标记
						if(tran.getTarget().equals(state.getName())){
							k=1;
							break;//for(State state:StateSet)
						}
					}//
					if(k==0){//目标状态没有被访问过，目标状态加入栈，并加入StateSet，并将这条迁移加入TransitionSet
						flag=1;
						for(State s:auto_StateSet){
							if(tran.getTarget().equals(s.getName())){
								sstack.push(s);//将这个目标状态加入栈
								/*State ss=new State();
								ss.setName(s.getName());
								ss.setPosition(s.getPosition());
								ss.setInvariantDBM(s.getInvariantDBM());*/
								StateSet.add(s);//将这个目标状态标志为已访问过
								/*Transition t=new Transition();
								t.setSource(tran.getSource());
								t.setTarget(tran.getTarget());
								ArrayList<String> events=new ArrayList<String>();
								events.add(tran.getEventSet().get(0));
								events.add(tran.getEventSet().get(1));
								t.setEventSet(events);
								ArrayList<String> ResetClockSet=new ArrayList<String>();
								if(tran.getResetClockSet()!=null){
									for(String set:tran.getResetClockSet()){
										ResetClockSet.add(set);
									}
								}
								t.setResetClockSet(ResetClockSet);*/
								TransitionSet.add(tran);//将这条迁移加入迁移集合
								break;//for(State s:auto_StateSet)
							}
						}//
					}
					if(flag==1){break;}//如果这条迁移的目的状态没有被访问过，则跳出遍历迁移的循环
					                   //for(Transition tran:auto_Transition)
					if(k==1){//如果这条迁移的目标状态已被访问过，则遍历下一条迁移
						continue;
					}
				}
			}//
			if(flag==0){sstack.pop();}//标志X的邻接点都被访问过，则出栈
		}
		
	/*	System.out.println("边数： "+TransitionSet.size());
		for(Transition t:TransitionSet){
			System.out.println(t.getSource());
			System.out.println(t.getTarget());
			System.out.println(t.getEventSet().get(0));
			System.out.println(t.getEventSet().get(1));
			System.out.println("-------------");
		}*/
		
		/*System.out.println("状态数： "+StateSet.size());
		for(State s:StateSet){
			System.out.println(s.getName());
			System.out.println(s.getPosition());
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					DBM_element cons=s.getInvariantDBM()[i][j];
					
					//System.out.println("DBM_i:"+cons.getDBM_i());
					//System.out.println("DBM_j:"+cons.getDBM_j());
					System.out.println("value:"+cons.getValue());
					System.out.println("Strictness:"+cons.isStrictness());									
				}
			}
			System.out.println("-------------");
		}*/
		
		Automatic aaa=new Automatic();
		aaa.setClockSet(auto.getClockSet());
		aaa.setName("aaa");
		aaa.setStateSet(StateSet);
		aaa.setTransitionSet(TransitionSet);
		for(State s:StateSet){
			if(s.getName().equals(auto.getInitState().getName())&&Minimization__1.includeZero(s.getInvariantDBM())==1){
				aaa.setInitState(s);//设置时间自动机的初始状态
			}
		}
		return aaa;
		
	}
}
