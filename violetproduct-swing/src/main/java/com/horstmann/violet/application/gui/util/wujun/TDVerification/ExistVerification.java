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

import com.thoughtworks.xstream.core.util.Types;


public class ExistVerification {
    //static Scanner cin = new Scanner(System.in);
	public static final int VERIFICATION_TYPE_EXIST = 1;
	public static final int VERIFICATION_TYPE_FRONT = 2;
	public static final int VERIFICATION_TYPE_BACK = 3;
	public static final int VERIFICATION_TYPE_TWOWAY = 4;
	private static String[] types = {
			"存在一致性验证", "前向一致性验证", "逆向一致性验证", "双向一致性验证"
	};
    private String filePath;
    private static ArrayList<UppaalTemPlate> templates = new ArrayList<UppaalTemPlate>();
    private static ArrayList<UppaalTransition> transitions = new ArrayList<>();
    private static HashMap<String, UppaalLocation> locationById = new HashMap<>();
    private static ArrayList<PathTuple> pathTuples = new ArrayList<>();// 路径
    private static ArrayList<UppaalTransition> messages = new ArrayList<>();// 消息序列

    // 1输入
    // 文件路径
    public ExistVerification(String filePath) throws Exception {
        this.filePath = filePath;

        Display.println("================================正在读取自动机================================");
        
        SAXReader reader = new SAXReader();//获取解析器
        Document dom = reader.read(filePath);//解析XML获取代表整个文档的dom对象
        Element root = dom.getRootElement();//获取根节点

        Read uppaal = new Read();
        uppaal.load(root);

        templates = uppaal.getUppaalTemplates();
        transitions = templates.get(0).getTransitions();
        Display.println("===>  读取的自动机名：" + templates.get(0).getName());
        // 添加transiton到sourceLocation的transitionList中
        setTansitionsToSourceLocation();

        // 对transition 根据时间进行排序  那么 路径一定是按照消息的顺序来走的
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
        for(UppaalTransition transition : messages) {
        	Display.println(transition.getName());
        }
    	return messages;
    }
    // 输入 return 输出
    // 返回需要标记的序号
    public List<UppaalTransition> getSelectedTransitions(List<UppaalTransition> selectedTransition, int type) {
    	
    	Display.println("-------------------------正在进行"+ types[type] +"-------------------------\n");
        Display.println("选择的消息如下：");
        for(UppaalTransition transition : selectedTransition) {
        	Display.println(transition.getName() + "\n");
        }
    	int i;
    	if (type == VERIFICATION_TYPE_FRONT || type == VERIFICATION_TYPE_TWOWAY) {
    		i = 0;
    		int j = 0;
    		while(i < selectedTransition.size() && j < pathTuples.size() - 1) {// 最后一个tuple没有transition
        		UppaalTransition transitionI = selectedTransition.get(i);
        		UppaalTransition transitionJ = pathTuples.get(j).transition;
        		if (transitionI.getName().equals(transitionJ.getName())) {
        			Display.println("匹配到消息：" + transitionI.getName());
    				i++;
    				j++;
    			} else {
    				j++;
    			}
        	}
		} else {
			i = selectedTransition.size() - 1;
			int  j = 0;
			while(i >= 0 && j < pathTuples.size() - 1) {// 最后一个tuple没有transition
	    		UppaalTransition transitionI = selectedTransition.get(i);
	    		UppaalTransition transitionJ = pathTuples.get(j).transition;
	    		if (transitionI.getName().equals(transitionJ.getName())) {
	    			Display.println("匹配到消息：" + transitionI.getName());
					i--;
					j++;
				} else {
					j++;
				}
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


    // 根据排序的消息 确定一条路径
    private ArrayList<PathTuple> findPathByTime() {
        ArrayList<PathTuple> res = new ArrayList<>();

        for (UppaalTransition transition : transitions) {
            UppaalLocation location = locationById.get(transition.getSource());
            PathTuple tuple = new PathTuple(location, transition);
            res.add(tuple);
        }
        UppaalLocation lastLocation = locationById.get(transitions.get(transitions.size() - 1).getTarget());
        PathTuple last = new PathTuple(lastLocation, null);
        res.add(last);
        return res;
    }


    // 获得按照时间顺序排序的消息序列  给平台显示
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
        transitions = templates.get(0).transitions;
        Collections.sort(transitions, new Comparator<UppaalTransition>() {
            @Override
            public int compare(UppaalTransition o1, UppaalTransition o2) {
                return (int) (o1.getTime() - o2.getTime());
            }
        });
//        for (UppaalTransition transition : templates.get(0).getTransitions()) {
//            System.out.println(transition.getName() + " " + transition.getTime());
//        }
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
}
