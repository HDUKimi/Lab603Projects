package com.horstmann.violet.application.gui.util.tanchao;

public class CalculateWidth {

	public static int getWidth(String str){
		
//		int bl=str.getBytes().length;
//		int cl=str.length();
		
		int Asum,asum,nsum,Csum;
		Asum=0;
		asum=0;
		nsum=0;
		Csum=0;
		
		for (int i = 0; i < str.length(); i++) {
			char c=str.charAt(i);
			if(c>='0'&&c<='9'){
				nsum++;
			}
			else if(c>='a'&&c<='z'){
				asum++;
			}
			else if(c>='A'&&c<='Z'){
				Asum++;
			}
			else{
				Csum++;
			}
		}
		
		int width;
//		width=nsum*6+asum*6+Asum*10+Csum*15+60;
		width=nsum*7+asum*7+Asum*9+Csum*13+50;
		
		System.out.println(nsum+" - - "+asum+" - - "+Asum+" - - "+Csum+" - - "+width);
    	
		return width;
		
	}
	
}
