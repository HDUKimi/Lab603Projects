package com.horstmann.violet.application.consolepart;

import java.util.ArrayList;
import java.util.List;

public class ConsolePartDataTestDao {
	/**
	 * ��ȡ��ϸ��Ϣ
	 * @return
	 */
	public static List<ConsolePartDetailInfo> getDetailInfoList(int index) {
		List<ConsolePartDetailInfo> details = new ArrayList<ConsolePartDetailInfo>();
		switch (index) {
		case 0:
			details.add(new ConsolePartDetailInfo("1","testcase1_done","�����������1�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("2","testcase2_done","�����������2�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("3","testcase3_done","�����������3�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("4","testcase4_done","�����������4�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("5","testcase5_done","�����������5�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("6","testcase6_done","�����������6�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("7","testcase7_done","�����������7�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("8","testcase8_done","�����������8�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("9","testcase9_done","�����������9�ɹ�����","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("10","testcase10_done","�����������10�ɹ�����","2016/5/14",""));
			break;
		case 1:
			details.add(new ConsolePartDetailInfo("1","testcase1_done","�����������1ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("2","testcase2_done","�����������2ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("3","testcase3_done","�����������3ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("4","testcase4_done","�����������4ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("5","testcase5_done","�����������5ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("6","testcase6_done","�����������6ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("7","testcase7_done","�����������7ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("8","testcase8_done","�����������8ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("9","testcase9_done","�����������9ʵ�����ɹ�","2016/5/14",""));
			details.add(new ConsolePartDetailInfo("10","testcase10_done","�����������10ʵ�����ɹ�","2016/5/14",""));
			break;
		}
		
		return details;
	}

}
