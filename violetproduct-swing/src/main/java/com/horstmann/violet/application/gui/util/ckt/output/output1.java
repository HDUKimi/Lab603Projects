package com.horstmann.violet.application.gui.util.ckt.output;
/**
 * �����ʽ
 * ģ��������һ����xml����ʱ���Զ���
 * ������Զ������ݽṹ�У�
 */

import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.ckt.handle.AddType;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.DBM_element;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
public class output1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * ��ʱ��Լ���������ʽ                     ��һ��                ��ȡxml�������Ӧ���ݽṹ��
	 * @param xml
	 */
	public static  void output1(String xml){
		Automatic automatic1=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic auto=AddType.addType(automatic1);
		//	ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//�������״̬���ǵĳ����������		



		//########################################        ��һ��             ###############################################
		//#########################################################################################################
		//======================================================================��ȡxml�������Ӧ���ݽṹ��		
		//======================================================================���ڽ���xml�ĵ�
		
		System.out.println("=============================��ȡxml�ĵ���Ϣ=============================");
		//System.out.println();
		System.out.println("----------------------���ڶ�ȡ��õ�xml�ĵ���Ϣ----------------------");
	
		System.out.println("===>  ʱ���Զ���������Ϣ");
		System.out.println("ʱ���Զ������֣�"+auto.getName());	
		System.out.print("ʱ���Զ���ʱ�Ӽ��ϣ�");
		if((auto.getClockSet().toString().equals("[]"))){
			System.out.println("null");
		}
		else{
			for(String c:auto.getClockSet()){
				System.out.print(" "+c+"  ");
			}
		}
		System.out.println("ģ������״̬������"+auto.getStateSet().size());
		System.out.println("ģ������Ǩ�Ƹ�����"+auto.getTransitionSet().size());
		System.out.println();
		//=========================================================================xml������Ϣ����


		//=========================================================================��ȡģ�ͳ�ʼ״̬��Ϣ
		System.out.println("-------------------------��ȡ��ʼ״̬��Ϣ-------------------------");
		
		State iniState=auto.getInitState();
		System.out.println("===>  ��ʼ״̬��Ϣ");
		System.out.println("��ʼ״̬���֣�"+iniState.getName());
		System.out.println("״̬λ�ã�"+iniState.getPosition());
		System.out.println("�Ƿ�Ϊ��ֹ״̬ ��"+iniState.isFinalState());
		System.out.println("Type����(�Ƿ�Ϊ��ʼ)��"+iniState.getType());
		DBM_element[][] DBM=iniState.getInvariantDBM();
		if(auto.getClockSet().size()>0){
			for(int i=0;i<auto.getClockSet().size()+1;i++){
				for(int j=0;j<auto.getClockSet().size()+1;j++){
					DBM_element cons=DBM[i][j];
					//System.out.println("DBM_i��"+cons.getDBM_i());
					//System.out.println("DBM_j��"+cons.getDBM_j());
					//System.out.println("value��"+cons.getValue());
					//System.out.println("Strictness��"+cons.isStrictness());					
				}
			}
		}		
		System.out.println();
		//============================================================================ģ�ͳ�ʼ״̬��Ϣ��ȡ����


		//============================================================================��ȡģ��״̬�ľ�����Ϣ
		System.out.println("-------------------------��ȡ����״̬��Ϣ-------------------------");
		System.out.println("===>  ״̬�ܸ�����"+auto.getStateSet().size());
		System.out.println();
		int k=0;
		for(State state:auto.getStateSet()){
			System.out.println("��"+k+"��״̬");
			k++;
			DBM_element[][] dbm=state.getInvariantDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=dbm[i][j];
						//System.out.println("DBM_i��"+cons.getDBM_i());
						//System.out.println("DBM_j��"+cons.getDBM_j());
						//System.out.println("value��"+cons.getValue());
						//System.out.println("Strictness��"+cons.isStrictness());					
					}
				}
			}			
			System.out.println("״̬���ƣ�"+state.getName());
			System.out.println("״̬λ�ã�"+state.getPosition());
			System.out.println("�Ƿ�Ϊ��ֹ״̬��"+state.isFinalState());
			System.out.println("Type����(�Ƿ�Ϊ��ʼ)��"+state.getType());
			DBM_element[][] adddbm=state.getAddClockRelationDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=adddbm[i][j];
						//System.out.println("DBM_i��"+cons.getDBM_i());
						//System.out.println("DBM_j��"+cons.getDBM_j());
						//System.out.println("value��"+cons.getValue());
						//System.out.println("Strictness��"+cons.isStrictness());					
					}
				}
			}			
			//System.out.println("--------------------------");
			System.out.println();
		}
		System.out.println();		
		//=========================================================================ģ�;���״̬��Ϣ��ȡ����




		//=========================================================================��ȡģ��Ǩ��(����)��Ϣ
		System.out.println("-------------------------��ȡ����Ǩ��(����)��Ϣ-------------------------");
		
		System.out.println("===>  Ǩ���ܸ�����"+auto.getTransitionSet().size());
		int p=0;
		for(Transition tran:auto.getTransitionSet()){
			System.out.println("��"+p+"��Ǩ��(����)");
			System.out.println("Ǩ��(����)���ƣ�"+tran.getName().replace("(", "").replace(")", "").replace("!", "").replace("?", ""));
			System.out.println("Ǩ��Id��"+tran.getId());
			p++;
			if(tran.getConstraintDBM()!=null){
				DBM_element[][] dbm=tran.getConstraintDBM();
				if(auto.getClockSet().size()>0){
					for(int i=0;i<auto.getClockSet().size()+1;i++){
						for(int j=0;j<auto.getClockSet().size()+1;j++){
							DBM_element cons=dbm[i][j];
							//System.out.println("DBM_i��"+cons.getDBM_i());
							//System.out.println("DBM_j��"+cons.getDBM_j());
							//System.out.println("value��"+cons.getValue());
							//System.out.println("Strictness��"+cons.isStrictness());					
						}
					}
				}				
			}
			else System.out.println("ʱ��Լ��Ϊ��");			
//			System.out.println("      ===>  Դ״̬���ƣ�"+tran.getSource());
//			System.out.println("      ===>  Ŀ��״̬���ƣ�"+tran.getTarget());						
			if(tran.getEventSet()==null){
				System.out.println("�¼�Ϊ��");
			}
			else if(tran.getEventSet().size()==0){
				System.out.println("�¼�Ϊ���գ�����û������");
			}
			else{				
				String in=tran.getIn();		
				String con=tran.getCondition();
				String out=tran.getOut();
				System.out.print("in(Լ������)��");
				if("".equals(in.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(in.toString());
				}
				System.out.println("condition(Լ������)��"+con);
				System.out.println("out(�����Ϣ)��"+out.toString());	
				////////////////out.toString()Ϊ��ʱ�����
				/*if("".equals(out.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(out.toString());
				}*/
				//ArrayList<String> events=tran.getEventSet();
				//				for(String e��events){
				//					System.out.println("      ===>  �¼���"+e);
				//				}								
			}			
			if(tran.getResetClockSet()==null){
				System.out.println("����ʱ�ӣ�null");
			}
			else if(tran.getResetClockSet().size()==0){
				System.out.println("����ʱ�ӣ����գ�����û������");
			}
			else{
				ArrayList<String> reset=tran.getResetClockSet();
				for(String r:reset){
					System.out.println("���õ�ʱ�ӣ�"+r);
				}
			}

			if(tran.getTypeIds()==null){
				//System.out.println("      ===>  typeID��null");
			}
			else if(tran.getTypeIds().size()==0){
				//System.out.println("      ===>  typeID�����գ�����û������");
			}
			else{
				ArrayList<String> typeid=tran.getTypeIds();
				for(String i:typeid){
					//System.out.println("      ===>  typeId��"+i);
				}
			}

			if(tran.getTypes()==null){
				//System.out.println("      ===>  types��null");
			}
			else if(tran.getTypes().size()==0){
				//System.out.println("      ===>  types�����գ�����û������");
			}
			else{
				ArrayList<String> type=tran.getTypes();
				for(String t:type){
					//System.out.println("      ===>  types��"+t);
				}
			}

			//System.out.println("--------------------------");
			System.out.println();
		}
		System.out.println();
		//=====================================================================ģ��Ǩ��(����)��Ϣ��ȡ����	
		//=====================================================================��ȡxml����	
	}


	
	

}
