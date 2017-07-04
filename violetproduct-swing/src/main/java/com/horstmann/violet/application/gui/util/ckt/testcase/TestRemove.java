package com.horstmann.violet.application.gui.util.ckt.testcase;

public class TestRemove {

	public static void main(String[] args) {
		String bbb = "control_mode==9,0<=control_mode<=14,failsafe.radio==0,0<=failsafe.radio<=1,g.land_repositioning==1,control_mode==0||control_mode==14||failsafe.radio==0||failsafe.radio==1,0<=failsafe.radio<=500,-128<=control_mode<=128,land_with_gps=False";
		System.out.println(Remove(bbb));

	}
	/**
	 * 利用软件mathematica求解为空，即不等式中有矛盾，两两对比
	 * 移出矛盾的不等式，取后一个不等式
	 */
	public static String Remove(String bbb){
		System.out.println("原处理不等式："+bbb);
		String cs1 = null;
		String cs2 = null;
		String bds[] = bbb.split(",");

		for(int i=0;i<bds.length-1;i++){
			for(int j=i+1;j<bds.length;j++){
				//System.out.println(bds[i]+"------"+bds[j]);
				//System.out.println(getNumber(bds[i]));
				//System.out.println(getNumber(bds[j]));
				if(getNumber(bds[i]) && getNumber(bds[j])){					
					String[] css = bds[i].trim().split("[-!+<>=]=?");
					for(String cs:css){						
						if(!cs.equals("")){
							int s1=cs.trim().substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								cs1 = cs;
								//System.out.println("第一个不等式参数："+cs);
							}
						}				
					}
					String[] css1 = bds[j].trim().split("[-!+<>=]=?");
					for(String cs:css1){
						
						if(!cs.equals("")){
							int s1=cs.trim().substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								cs2 = cs;
								//System.out.println("第二个不等式参数："+cs);
							}
						}				
					}
					if(cs1.equals(cs2)){
						String bds1 = bds[i]+","+bds[j];
						String cs = cs1;
						int resultNum = getMathNum(bds1);
						String solution = Mathematica.getSolution2(bds1, cs, resultNum);
						//System.out.println("solution---"+solution);
						if(solution.equals("{}")){
							bbb=bbb.replaceAll(bds[i]+",", "");								
						}						
					}					 
				}
			}
		}
		System.out.println("处理后的不等式"+bbb);
		return bbb;
	}

	/**
	 * 判断每个不等式里参数是否只有一个
	 * @param domain1
	 * @return
	 */
	public static boolean getNumber(String bds) {

		int number=0;
		String[] css = bds.trim().split("[!-+<>=]=?");
		for(String cs:css){
			if(!cs.equals("")){
				int s1=cs.trim().substring(0,1).toCharArray()[0];
				if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
					number++;
				}
			}				
		}
		if(number<=1){
			//System.out.println("一个参数");
			return true;
		}else{
			//System.out.println("多个参数");
			return false;
		}

	}
	public static int getMathNum(String bds) {
		String[] bNum = bds.split(",");
		int resultNum;
		if (bNum.length > 8) {
			resultNum = 1;
		} else {
			resultNum = 5;
		}
		return resultNum;
	}
	
}
