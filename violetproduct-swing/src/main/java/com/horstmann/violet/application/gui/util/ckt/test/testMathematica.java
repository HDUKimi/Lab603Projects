package com.horstmann.violet.application.gui.util.ckt.test;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.testcase.*;
/**
 * 测试软件mathematica中替换不等式组特殊字符 
 *
 */
public class testMathematica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s1 = "i<5#i:uint8_t--has_new_input==False#has_new_input:bool--((failsafe.rc_override_active==False && (elapsed>=500)) || (failsafe.rc_override_active==True && (elapsed>=2000))) && failsafe.radio==false#failsafe.rc_override_active:bool,elapsed:uint32_t, failsafe.radio:bool--g.failsafe_throttle==1 && (ap.rc_receiver_present==true || is_armed==true)#g.failsafe_throttle:int8_t,ap.rc_receiver_present:bool,is_armed:bool";
		String s1 = "control_mode==9,0<=control_mode<=14,failsafe.radio==0,0<=failsafe.radio<=1,g.land_repositioning==1,control_mode==0||control_mode==14||failsafe.radio==0||failsafe.radio==1,0<=failsafe.radio<=500,-128<=control_mode<=128,land_with_gps=False";
		System.out.println("原不等式："+s1);
		String s = s1.replace("false", "False").replace("true", "True").replace("->", "$");
		List<String> result2 = Result1.getResult(s);
		//String solution1 = Mathematica.getSolution2(s, cs);
		
	}

}
