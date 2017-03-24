package com.horstmann.violet.application.gui.util.ckt.test;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.testcase.*;
public class testin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tran="x1,x2,x3,x4,x5,x6#x1:float,x2:float,x3:float,x4:float,x5:float,x6:float--x7,x8,x9,x10,x11,x12#x7:float,x8:bool,x9:float,x10:float,x11:float,x12:float--x13,x14,x15#x13:float,x14:float,x15:bool--x16,x17#x16:float,x17:float";
		System.out.println("in---->"+tran);	//in里面的内容	
		List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果
		if(tran.equals("null")){	
			result1.add(null);
		}else{
			if(tran.contains("--")){
				List<List<String>> in_result = new ArrayList<List<String>>();
				List<String> in_result1;
				//String rrr = null;
				String getin[] = tran.split("--");
				for(int ii=0;ii<getin.length;ii++){
					if(!(GetMap.get_inMap(getin[ii])==null)){
						String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->", "$");
						in_result1 = Result1.getResult(inn);
						if((in_result1.size()>0)&&!(in_result1.get(0).equals(null))){
							in_result.add(in_result1);
						}
					}
				}
				if((in_result.size()>0)&&!(in_result.get(0).equals(null))){
					System.out.println("in_result.size()--->"+in_result.size());
					dis(0,in_result);
					result1 = re;
					/*	if(in_result.size()==2){
						for(String rr1:in_result.get(0)){
							for(String rr2:in_result.get(1)){
								if((rr1!=null)&&(rr2!=null)){
									String rr3 = rr1+","+rr2;
									result1.add(rr3);
								}
							}
						}		
					}else{
						if(in_result.size()==3){
							for(String rr1:in_result.get(0)){
								for(String rr2:in_result.get(1)){
									for(String rr3:in_result.get(2))
										if((rr1!=null)&&(rr2!=null)){
											String rr4 = rr1+","+rr2+","+rr3;
											result1.add(rr4);
										}
								}
							}		
						}
					}*/

				}

			}else{
				if(!(GetMap.get_inMap(tran)==null)){//map里面为空，即没有参数
					String inn = tran.replace("false", "False").replace("true", "True").replace("->", "$");
					result1 = Result1.getResult(inn);
				}else{
					if((GetMap.get_inMap(tran)==null)){
						result1.add(null);
					}
				}
			}
		}
		for(int i=0;i<result1.size();i++){
			System.out.println("result1---->"+result1.get(i));
		}

	}
	public static String s="";
	public static List<String> re = new ArrayList<String>();
	public static void dis(int n, List<List<String>> l) {
		if (n == l.size()) {
			re.add(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				if(s==""){
					s  = sCollection.get(i);
				}else{
					s =s + ","+ sCollection.get(i);
				}				
				dis(n+1,l);
				s = s1;
			}
		}
	}




}
