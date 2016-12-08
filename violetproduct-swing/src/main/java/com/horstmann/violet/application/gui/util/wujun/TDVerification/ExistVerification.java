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


public class ExistVerification {
    //static Scanner cin = new Scanner(System.in);
    private String filePath;
    private static ArrayList<UppaalTemPlate> templates = new ArrayList<UppaalTemPlate>();
    private static ArrayList<UppaalTransition> transitions = new ArrayList<>();
    private static HashMap<String, UppaalLocation> locationById = new HashMap<>();
    private static ArrayList<PathTuple> pathTuples = new ArrayList<>();// 路径
    private static ArrayList<UppaalTransition> messages = new ArrayList<>();// 消息序列

    // 给出文件路径
    public ExistVerification(String filePath) throws Exception {
        this.filePath = filePath;

        SAXReader reader = new SAXReader();//获取解析器
        Document dom = reader.read(filePath);//解析XML获取代表整个文档的dom对象
        Element root = dom.getRootElement();//获取根节点

        Read uppaal = new Read();
        uppaal.load(root);

        templates = uppaal.getUppaalTemplates();
        transitions = templates.get(0).getTransitions();

        // 添加transiton到sourceLocation的transitionList中
        setTansitionsToSourceLocation();

        // 对transition 根据时间进行排序  那么 路径一定是按照消息的顺序来走的
        sortTransitions();

        // 找出消息序列
        messages = sortedMessages();
        // 找出路径 按照时间顺序
        pathTuples = findPathByTime();
    }
    // 获取路径
    public ArrayList<PathTuple> getPath() {
        return pathTuples;
    }
    // 获取消息序列
    public ArrayList<UppaalTransition> getMessages() {
        return messages;
    }

    public void loadXml(String filePath) throws Exception {

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
        for (UppaalTransition transition : templates.get(0).getTransitions()) {
            System.out.println(transition.getName() + " " + transition.getTime());
        }
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
