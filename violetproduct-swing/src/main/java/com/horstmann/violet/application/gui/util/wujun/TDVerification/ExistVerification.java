package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.MainFrame;
import com.thoughtworks.xstream.core.util.Types;

public class ExistVerification {

	private MainFrame mainFrame;

	public static final int VERIFICATION_TYPE_EXIST = 1;
	public static final int VERIFICATION_TYPE_FRONT = 2;
	public static final int VERIFICATION_TYPE_BACK = 3;
	public static final int VERIFICATION_TYPE_TWOWAY = 4;
	private static String[] types = { "", "存在一致性验证", "前向一致性验证", "逆向一致性验证", "双向一致性验证" };
	private String filePath;
	private static ArrayList<UppaalTemPlate> templates = new ArrayList<UppaalTemPlate>();
	private static ArrayList<UppaalTransition> transitions = new ArrayList<>();
	private static ArrayList<UppaalLocation> locations = new ArrayList<>();
	private static HashMap<String, UppaalLocation> locationById = new HashMap<>();
	private static ArrayList<PathTuple> pathTuples = new ArrayList<>();// 路径
	private static ArrayList<UppaalTransition> messages = new ArrayList<>();// 消息序列

	// 1输入
	// 文件路径
	public ExistVerification(String filePath) throws Exception {
		this.filePath = filePath;

		Display.println("================================正在读取自动机================================");

		SAXReader reader = new SAXReader();// 获取解析器
		Document dom = reader.read(filePath);// 解析XML获取代表整个文档的dom对象
		Element root = dom.getRootElement();// 获取根节点

		ReadAutomata uppaal = new ReadAutomata();
		uppaal.load(root);

		templates = uppaal.getUppaalTemplates();
		transitions = templates.get(0).getTransitions();
		locations = templates.get(0).getLocations();
		Display.println("===>  读取的自动机名：" + templates.get(0).getName());
		// 添加transiton到sourceLocation的transitionList中
		setTansitionsToSourceLocation();

		// 对transition 根据时间进行排序 那么 路径一定是按照消息的顺序来走的
		sortTransitions();

		// 找出消息序列
		messages = sortedMessages();
		// 找出路径 按照时间顺序
		pathTuples = findPathByTime();
	}

	// 输出
	// 获取路径
	public ArrayList<PathTuple> getPath() {
		return pathTuples;
	}

	// 输出
	// 获取消息序列
	public ArrayList<UppaalTransition> getMessages() {
		Display.println("-------------------------获取消息序列-------------------------\n");
		for (UppaalTransition transition : messages) {
			Display.println(transition.getName());
		}
		return messages;
	}

	// 输入 return 输出
	// 返回需要标记的边
	public List<UppaalTransition> getSelectedTransitionsIfExist(List<UppaalTransition> selectedTransition) {

		Display.println("-------------------------正在进行存在一致性验证-------------------------\n");
		Display.println("选择的消息如下：");
		for (UppaalTransition transition : selectedTransition) {
			Display.println(transition.getName() + "\n");
		}
		int i = 0;
		int j = 0;
		while (i < selectedTransition.size() && j < pathTuples.size() - 1) {// 最后一个tuple没有transition
			UppaalTransition transitionI = selectedTransition.get(i);
			UppaalTransition transitionJ = pathTuples.get(j).transition;
			if (transitionI.getName().equals(transitionJ.getName())) {
				Display.println("匹配到消息：" + transitionI.toString());
				i++;
				j++;
			} else {
				j++;
			}
		}
		if (i == selectedTransition.size()) {
			Display.println("一致性验证完成");
			return selectedTransition;
		} else {
			Display.println("一致性验证失败");
			return null;
		}
	}

	// 输入 return 输出
	// 返回路径
	public List<PathTuple> getPathOfSelectedTransitions(List<UppaalTransition> selectedTransition) {
		ArrayList<PathTuple> res = new ArrayList<>();
		Display.println("-------------------------正在进行顺序性验证-------------------------\n");
		Display.println("选择的消息如下：");
		for (UppaalTransition transition : selectedTransition) {
			Display.println(transition.getName() + "\n");
		}
		int i = 0;
		int j = 0;
		boolean findFirstTransition = false;
		while (i < selectedTransition.size() && j < pathTuples.size() - 1) {// 最后一个tuple没有transition
			UppaalTransition transitionI = selectedTransition.get(i);
			UppaalTransition transitionJ = pathTuples.get(j).transition;
			if (transitionI.getName().equals(transitionJ.getName())) {
				findFirstTransition = true;
				res.add(pathTuples.get(j));
				Display.println("匹配到消息：" + transitionI.toString());
				i++;
				j++;
			} else {
				if (findFirstTransition) {
					res.add(pathTuples.get(j));
				}
				j++;
			}
		}
		if (i == selectedTransition.size()) {
			Display.println("找到路径");
			return res;
		} else {
			Display.println("找不到路径");
			return null;
		}
	}

	// 根据排序的消息 确定一条路径
	private ArrayList<PathTuple> findPathByTime() {
		ArrayList<PathTuple> res = new ArrayList<>();
		Display.println("-------------------------根据消息确定路径-------------------------\n");
		for (UppaalTransition transition : transitions) {
			UppaalLocation location = locationById.get("id" + transition.getSource());
			Display.println("location: ");
			Display.println(location.toString());
			Display.println("transition: ");
			Display.println(transition.toString());
			PathTuple tuple = new PathTuple(location, transition);
			res.add(tuple);
			Display.println();
		}

		// 最后的一个节点
		UppaalLocation lastLocation = locationById.get("id" + transitions.get(transitions.size() - 1).getTarget());
		PathTuple last = new PathTuple(lastLocation, null);
		res.add(last);
		Display.println("location: ");
		Display.println(last.location.toString());

		return res;
	}

	// 获得按照时间顺序排序的消息序列 给平台显示
	public ArrayList<UppaalTransition> sortedMessages() {

		ArrayList<UppaalTransition> res = new ArrayList<>();
		for (UppaalTransition transition : templates.get(0).getTransitions()) {
			// 消息名不包含? 且不是null
			if (!transition.getName().contains("?") && !transition.getName().equals("null")) {
				res.add(transition);
			}
		}
		return res;
	}

	// 对所有消息进行排序
	private void sortTransitions() {
		Display.println("-------------------------对所有transition进行排序-------------------------\n");
		transitions = templates.get(0).transitions;
		Collections.sort(transitions, new Comparator<UppaalTransition>() {
			@Override
			public int compare(UppaalTransition o1, UppaalTransition o2) {
				return (int) (o1.getStartTime() - o2.getStartTime());
			}
		});
		for (UppaalTransition transition : templates.get(0).getTransitions()) {
			Display.println(transition.toString());
		}
		Display.println();
	}

	// 设置location出发的消息
	private void setTansitionsToSourceLocation() {
		for (UppaalLocation locationI : templates.get(0).getLocations()) {
			locationById.put(locationI.getId(), locationI);
		}
		for (UppaalTransition transitionI : templates.get(0).getTransitions()) {
			String sourceId = "id" + transitionI.getSource();
			UppaalLocation sourceLocation = locationById.get(sourceId);
			sourceLocation.getTransitions().add(transitionI);
		}
	}

	// *实时一致性验证
	public boolean verificationTimeDuration() {
		//1 点
		boolean locationOK = verificationLocationTimeDuration();
		
		//2 边
		boolean transitionOK = verificationTransitionTimeDuration();
		
		//3 路径时间和
		boolean pathTupleOK = verificationPathTupleTime();
		
		//4 矛盾的约束
//		boolean counterDuration = verificationCounterDuration();
		
		System.out.println("实时一致性验证完成" + (locationOK && transitionOK && pathTupleOK));
		return locationOK && transitionOK && pathTupleOK;
	}

	// 是否存在矛盾的时间约束
	private boolean verificationCounterDuration() {
		for(UppaalLocation locationP : locations) {
			for(UppaalLocation locationC : locations) {
				if (locationC == locationP) {
					continue;
				}
				
				if (!compareLocation(locationP, locationC)) {
					return false;
				}
			}
		}
		return true;
	}
	// 两个location是否矛盾 矛盾返回false
	private boolean compareLocation(UppaalLocation locationP, UppaalLocation locationC) {
		for(int i = 0; i < locationP.getStartTimeList().size(); i++) {
			for(int j = 0; j < locationC.getStartTimeList().size(); j++) {
				int startP = locationP.getStartTimeList().get(i);
				int startC = locationC.getStartTimeList().get(j);
				int endP = locationP.getEndTimeList().get(i);
				int endC = locationC.getEndTimeList().get(j);
				if (startC >= startP && endC <= endP) { // 是同一个时间段上的状态
					String durationP = locationP.getTimeDurationList().get(i);
					String durationC = locationC.getTimeDurationList().get(j);
					if (!compareDuration(durationP, durationC)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	// 两个时间约束是否矛盾 矛盾返回false
	private boolean compareDuration(String durationP, String durationC) {
		if (durationP.equals("null") || durationC.equals("null")) {
			return true;
		}
		int time = 0;
		if (durationP.contains("=")) {
			time = Integer.valueOf(durationP.split("=")[1]);
		} else {
			if (durationP.contains(">")) {
				time = Integer.valueOf(durationP.split(">")[1]) + 1;
			} else if (durationP.contains("<")) {
				time = Integer.valueOf(durationP.split("<")[1]) - 1;
			}
		}
		return satisfy(time, durationC);
	}

	private boolean verificationPathTupleTime() {
		System.out.println("-------------------------累加路径时间值验证时间刻度-------------------------");
		ArrayList<PathTuple> path = getPath();
		
		System.out.println("初始化时间和");
		int timeSum = 0;
		int nextTime = 0;
		for(int i = 0; i < path.size() - 1; i++) {
			UppaalLocation location = path.get(i).getLocation();
			UppaalTransition transition = path.get(i).getTransition();
			if (transition.out && transition.getName().contains("?")) {// 不重复计算
				continue;
			}
			int LocationCountIndex = 0;
			for(int time : location.getStartTimeList()) {
				if (time == nextTime) {
					break;
				}
				LocationCountIndex++;
			}
			if (LocationCountIndex >= location.getStartTimeList().size()) {
				System.out.println("根据nextTime查找这个location的所有开始时间失败！");
			}
			System.out.println("累加location:" + location.getName() + "的耗时:" + (location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex)));
			timeSum += location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex);
			
			
			System.out.println("累加transition:" + transition.getName() + "的耗时:" + (transition.getEndTime() - transition.getStartTime()));
			timeSum += transition.getEndTime() - transition.getStartTime();
			nextTime = transition.getEndTime();
		}
		
		PathTuple lastPathTuple = path.get(path.size() - 1);
		UppaalLocation lastLocation = lastPathTuple.getLocation();
		int LocationCount = lastLocation.getStartTimeList().size();
		int lastStartTime = lastLocation.getStartTimeList().get(LocationCount - 1);// 最后一个状态的开始时间
		System.out.println("最后一个状态的开始时间：" + lastStartTime);
		System.out.println("累加的时间和:" + timeSum);
		return lastStartTime == timeSum;
	}

	private boolean verificationLocationTimeDuration() {
		System.out.println("-------------------------验证每一个location是否满足时间约束-------------------------");
		for (UppaalLocation location : locations) {
			for (int i = 0; i < location.getStartTimeList().size(); i++) {
				String timeDuration = location.getTimeDurationList().get(i);
				int startTime = location.getStartTimeList().get(i);
				int endTime = location.getEndTimeList().get(i);
				int time = endTime - startTime;
				System.out.println(location.getName());
				System.out.println("所在时间段：" + startTime + "-" + endTime);
				if (!satisfy(time, timeDuration)) {
					System.out.println("不满足时间约束" + " " + timeDuration + "\n");
					return false;
				} else {
					System.out.println("满足时间约束" + " " + timeDuration + "\n");
				}
			}
		}
		return true;
	}

	private boolean verificationTransitionTimeDuration() {
		System.out.println("-------------------------验证每一个transition是否满足时间约束-------------------------");
		for (UppaalTransition transition : transitions) {
			String timeDuration = transition.getTimeDuration();
			int startTime = transition.getStartTime();
			int endTime = transition.getEndTime();
			int time = endTime - startTime;
			System.out.println(transition.getName());
			System.out.println("所在时间段：" + startTime + "-" + endTime);
			if (!satisfy(time, timeDuration)) {
				System.out.println("不满足时间约束" + " " + timeDuration + "\n");
				return false;
			} else {
				System.out.println("满足时间约束" + " " + timeDuration + "\n");
			}
		}
		return true;
	}

	// 状态持续的时间 、 消息的执行时间 是否满足时间约束
	private boolean satisfy(int time, String timeDuration) {
		if (timeDuration == null || timeDuration.equals("null") || timeDuration.equals("0")) {
			return true;
		}
		if (timeDuration.contains("<")) {
			if (timeDuration.contains("<=")) {
				int limit = Integer.valueOf(timeDuration.split("<=")[1]);
				if (time <= limit) {
					return true;
				} else {
					return false;
				}
			} else {
				int limit = Integer.valueOf(timeDuration.split("<")[1]);
				if (time < limit) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (timeDuration.contains(">=")) {
				int limit = Integer.valueOf(timeDuration.split(">=")[1]);
				if (time >= limit) {
					return true;
				} else {
					return false;
				}
			} else {
				int limit = Integer.valueOf(timeDuration.split(">")[1]);
				if (time > limit) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
