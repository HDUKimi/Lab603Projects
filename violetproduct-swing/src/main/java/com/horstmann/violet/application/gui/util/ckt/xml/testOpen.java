package com.horstmann.violet.application.gui.util.ckt.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;

public class testOpen {

	public static void main(String[] args) {
		String con = "State==0,-1<=State<=1#State:int--callUp[1] != false || callDown[1] != false || callCar[1] != false) && Floor == 1,0<=Floor<=4#callUp[1]:bool,callDown[1]:bool,callCar[1]:bool,Floor:int";
		List<String> result = result(con);
		

	}
	
	
	public static List<String> result(String tran){

		String sss = new String();
		List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
		List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������

		/*// ����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
		// System.out.println("========================in========================");
		System.out.println("in---->" + tran.getIn()); // in���������
		if (tran.getIn().equals("null")) {
			result1.add(null);
		} else {
			
			if (tran.getIn().contains("--")) {
				List<List<String>> in_result = new ArrayList<List<String>>();
				List<String> in_result1;
				// String rrr = null;
				String getin[] = tran.getIn().split("--");
				for (int ii = 0; ii < getin.length; ii++) {
					if (!(GetMap.get_inMap(getin[ii]) == null)) {
						String inn = getin[ii].replace("False", "false").replace("True", "true").replace("->","$");
						in_result1 = Result1.getResult(inn);
						//in_result1 = ResultOfTime.getResultOfTime(inn);
						if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
							in_result.add(in_result1);
						}
					}
				}
				if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
					System.out.println("in_result.size()--->" + in_result.size());
					dis(0, in_result);
					result1 = re;
				}
			} else {
				if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
					String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->","$");
					result1 = Result1.getResult(inn);
					//result1 = ResultOfTime.getResultOfTime(inn);
				} else {
					if ((GetMap.get_inMap(tran.getIn()) == null)) {
						result1.add(null);
					}
				}
			}
		}*/
		///////////////////////////////////////////// condition����ʼ///////////////////////////////////////
		// System.out.println("condition---->"+tran.getCondition());
		if (tran.equals("null")) {
			result2.add(null);
		} else {
			if (!tran.equals("null")) {
				if (GetMap.get_condMap(tran) == null) {
					result2.add(null);
				} else {
					if (!(GetMap.get_condMap(tran) == null)) {
						String tra = tran.replace("False", "false").replace("True", "true").replace("->", "$");
						System.out.println("tra----" + tra);
						result2 = Result1.getResult(tra);
						//result2 = ResultOfTime.getResultOfTime(tra);
						// result2 = testbdscs.getResult(tra);
						System.out.println("condition:"+tran.toString());
						for (int ii = 0; ii < result2.size(); ii++) {
							System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
						}
					}
				}

			}
		}
    ////////////////////Ϊinput���������Ľ�
	List<String> result=new ArrayList<String>();//���һ��Ǩ���ϵĽ��
	String res = new String();
	if((result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
		res = sss+"%"+null;
		//res = null;
		result.add(res.toString());
	}else{
		if(!(result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
			for(String ttt2:result1){
				if(ttt2!=null){
					if(ttt2.contains("flag=1")){
						res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "");
					}else{
						res = sss+"%"+ttt2;
					}
					result.add(res.toString());
				}
			}
		}
		if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
			for(String ttt3:result2){
				if(ttt3!=null){
					if(ttt3.contains("flag=1")){
						res = sss+"flag=1"+"%"+ttt3.replace("flag=1", "");
					}else{
						res = sss+"%"+ttt3;
					}
					result.add(res.toString());
				}
			}
		}
		if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
			for(String ttt2:result1){
				for(String ttt3:result2){
					if(ttt2!=null&&ttt3!=null){
						if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
							res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "");
						}else{
							if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
								res = sss+"%"+ttt2+","+ttt3;
							}
						}
						result.add(res.toString());
					}									
				}
			}
		}
	}	
		System.out.println("result--------------" + result);
		if (result.size() == 0) {
			// System.out.println("-----------------0000000---------------------");
			// Element input = process.addElement("input");
			// input.setText("��1Ϊ:"+null);
		} else {
			for (int ii = 1; ii <= result.size(); ii++) {
				System.out.println("��" + ii + "Ϊ:" + result.get(ii - 1));// ������н�
				String s = "��" + ii + "Ϊ:" + result.get(ii - 1);
				// Element input = process.addElement("input");
				// input.setText(s);
			}
		}
		System.out.println("--------------------------------");
		System.out.println("               �����                                              ");
		System.out.println(result);
		
		////////////////////////////////////////////////////////////////////////////////////
		// System.out.println(" ======��"+j+"��Ǩ�ƽ���======");
		return result;
	}

}
