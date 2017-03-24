package com.horstmann.violet.application.gui.util.ckt.testcase;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class PathCoverage {
	
	private static ArrayList<Transition> Transitions;//保存搜索后一条路上的迁移集合		
	private static ArrayList<State> States;//保存搜索后一条路上的状态集合（保存已访问过的状态集合）
	private static List<ArrayList<Transition>> TransitionSet;//测试序列集合（边）
	private static List<ArrayList<State>> StateSet;//测试序列集合（状态）

	public static void main(String[] args) {
		String xml="rc_loopForXStream1.01.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testcaseSet=testCase(auto);
		System.out.println();       
	}
	
	/**
	 * 根据深度搜索产生满足路径覆盖准则的测试序列集合
	 * @param a
	 * @return
	 */
	public static ArrayList<Automatic> testCase(Automatic auto){
		ArrayList<Transition> auto_Transition=auto.getTransitionSet();//获得时间自动机迁移集合
		ArrayList<State> auto_StateSet=auto.getStateSet();//获得时间自动机状态集合
		ArrayList<State> StateList=new ArrayList<State>();//终止状态集合
		Transitions=new ArrayList<Transition>();//保存搜索后一条路上的迁移集合
		States=new ArrayList<State>();//保存搜索后一条路上的状态集合（保存已访问过的状态集合）
		TransitionSet=new ArrayList<ArrayList<Transition>>();//测试序列集合（边）
		StateSet=new ArrayList<ArrayList<State>>();//测试序列集合（状态）
		
		//搜索终止状态集合
		for(State state1:auto_StateSet){
			int k1= 0;
			for(Transition tran:auto_Transition){//判断目标状态是否已被访问
				if(state1.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				StateList.add(state1);
			}				
		}
		for(int ii=0;ii<StateList.size();ii++){
			System.out.println("终止节点id："+StateList.get(ii).getId());
		}
		//获取时间自动机初始状态
		State intstate = auto.getInitState();
		//调用dfs来搜集测试多条路径上状态和迁移的信息
		dfs(auto,intstate,StateList,auto_Transition,auto_StateSet);
		//获取测试路径的个数
		int n=StateSet.size();//测试路径（用例）个数
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//测试用例集合
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("测试路径"+(i+1));
			test_case.setStateSet(StateSet.get(i));
			test_case.setTransitionSet(TransitionSet.get(i));
			test_case.setInitState(auto.getInitState());
			testcaseSet.add(test_case);
		}
		System.out.println("测试路径个数："+testcaseSet.size());
		for(Automatic a:testcaseSet){
			System.out.println(a.getName());
			for(State s:a.getStateSet()){
				System.out.println(s.getName());
				//System.out.println(s.getPosition());
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
			}
			for(Transition t:a.getTransitionSet()){
				System.out.println(t.getSource()+"-->"+t.getTarget());			
				System.out.println(t.getEventSet().get(0));
				System.out.println(t.getEventSet().get(1));
				if(t.getResetClockSet().size()!=0){
					System.out.println(t.getResetClockSet().get(0));
				}
				System.out.println("-------------");
			}
		}
		
		return testcaseSet;
	}

	
	
	/**
	 * 深度搜索得到路径
	 * 
	 */	
	public static int i = 1;
	public static void dfs(Automatic auto,State intstate,ArrayList<State> StateList,ArrayList<Transition> auto_Transition,ArrayList<State> auto_StateSet){				
		States.add(intstate);
		State aa = intstate;
		//System.out.println("访问节点："+i+"--"+intstate.getId()+"--"+intstate.getName());
		i++;
		for(Transition tran:auto_Transition){//判断目标状态是否已被访问
			if(intstate.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
				Transitions.add(tran);	
				System.out.println("访问迁移："+tran.getId()+"--"+tran.getName());
				for(State s:auto_StateSet){//找到这个迁移的目的状态，令intstate等于这个状态
					if(s.getName().equals(tran.getTarget())){
						aa = s; 
						//System.out.println(s.getId());
					}					
				}				
			}
			//States.add(intstate);
			for(int ii=0;ii<StateList.size();ii++){
				if(StateList.get(ii).getId()==aa.getId()){
					States.add(aa);
					TransitionSet.add(Transitions);
					StateSet.add(States);
					/*for(int i=0;i<Transitions.size();i++){
						System.out.println(Transitions.get(i).getId()+"--"+Transitions.get(i).getName());
					}
					for(int i=0;i<States.size();i++){
						System.out.println(States.get(i).getId()+"--"+States.get(i).getName());
					}*/
				}else{
					dfs(auto,aa,StateList,auto_Transition,auto_StateSet);
					System.out.println("下一个--"+aa.getId()+aa.getName());
				}
			}
			
			Transitions.remove(Transitions.size() - 1);
			States.remove(States.size() - 1);
			if(Transitions.size()<=0){
				return;
			}
		}
				
	}

	
}


