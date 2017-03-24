package com.horstmann.violet.application.gui.util.ckt.testcase;
//��7�����ӣ�5����������
import java.util.ArrayList;

import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class Test__6 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String xml="UAV2ForXStream.xml";//��7�����ӣ�5����������
		//String xml="read_radioForXStream.xml";
		/*String xml="Draw MoneyForXStream(2).xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//�������״̬���ǵĳ����������
*/		
		String xml="Draw MoneyForXStream(2).xml";
		Automatic automatic=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic new_automatic=IPR__1.iPR(automatic);//��ò�ֺ��ʱ���Զ���
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(new_automatic,automatic);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(aTDRTAutomatic);//�������״̬���ǵĳ����������
		
		System.out.println("������������:"+automatic.getName());
		for(Transition tran:automatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		System.out.println("������������:"+new_automatic.getName());
		for(Transition tran:new_automatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		System.out.println("������������:"+aTDRTAutomatic.getName());
		for(Transition tran:aTDRTAutomatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		
		System.out.println("����������и�����"+testCase.size());
		for(Automatic a:testCase){
			
			System.out.println("������������:"+a.getName());

            int k=0;
			for(Transition tran:a.getTransitionSet()){
				//System.out.println("��������"+tran.getName());
				//System.out.println(tran.getSource()+"---->"+tran.getTarget()+"Լ���� "+tran.getEventSet());
			//δ�����Լ������	
				System.out.println(++k+"---"+"Լ����"+tran.getEventSet());
				String s[] = tran.getEventSet().toString().replace("[", "").replace("]", "").split(",");
				System.out.println(tran.getId()+"----"+tran.getName()+"----"+s[s.length-1].trim());
				//int i = tran.getEventSet().size();
				//System.out.println("  "+k+"---"+tran.getEventSet().get(i-1).substring(tran.getEventSet().get(i-1).length()-2,tran.getEventSet().get(i-1).length()));
				/*for(int i=0;i<tran.getEventSet().size();i++){
					System.out.println(i+"---"+tran.getEventSet().get(i).substring(tran.getEventSet().get(i).length()-2,tran.getEventSet().get(i).length()));
				    //System.out.println("-----");
				}*/
				
			//�����Ͳ���ʽ�Ͳ���
			/*	String bds1=Get_str.get_bds(tran.getEventSet().toString());
				System.out.println(bds1);
				String cs1=Get_str.get_cs(bds1);
				System.out.println(cs1);
				//System.out.println("bds---------->"+bds);
              //�����Ͳ���ʽ�Ͳ���
				String boolbds=Get_str.get_bool_bds(tran.getEventSet().toString());
				System.out.println(boolbds);
				String boolcs=Get_str.get_bool_cs(boolbds);
				System.out.println(boolcs);
			 //==0�Ĳ���ʽ��Ϊ�� ==��Ϊ=
				String bds0=Get_str.get_bds_0(tran.getEventSet().toString());
			
			//����mma�����ⷽ����
				if((bds1!=null)&&(cs1!=null)){
					String solution1 = Mathematica.getSolution2(bds1, cs1);
					System.out.println("$$$$$$$$$$$$$$$$$$"+solution1);
				}
				if((boolbds!=null)&&(boolcs!=null)){
					String solution2 = Mathematica.getSolution3(boolbds, boolcs);
					System.out.println("##################"+solution2);
				}
			//����mma�����ⷽ����
				//String solution1 = Mathematica2.getSolution2(bds1, cs1);
				//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
				*/
				
			}
			System.out.println("*********************************");
		}
		
		
	
	}

}

