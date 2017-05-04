package com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Stack;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.tanchao.ShowOnTableAndConsole;
import com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util.Display;


public class SD2UppaalMain {
	private static HashMap<String , WJFragment> id_fragment = new HashMap<String , WJFragment>();
	private static MainFrame mainFrame;
	private static ArrayList<WJMessage> messageList=new ArrayList<WJMessage>();//这个生命线所有message
	private static ArrayList<WJFragment> fragmentList=new ArrayList<WJFragment>();//这个生命线所有fragment
	private static ArrayList<ArrayList<WJFragment>> table = new ArrayList<ArrayList<WJFragment>>();//表
	
	private static ArrayList<UppaalTransition> transitionList = new ArrayList<UppaalTransition>();//这个生命线所有transition
	private static ArrayList<UppaalLocation> locationList = new ArrayList<UppaalLocation>();	//这个生命线所有location
	private static int [][] map ;
	private static int m_id;
	private static int [] altEndTo;
	private static int [] parEndTo;
	private static ArrayList<HashSet <Integer>>  F = new ArrayList<HashSet <Integer>>();
	private static ArrayList<HashSet <Integer>>  F1 = new ArrayList<HashSet <Integer>>();
	private static ArrayList<HashSet <Integer>>  G = new ArrayList<HashSet <Integer>>();
	private static HashSet <Integer> endState = new HashSet <Integer>();
	private static ArrayList <WJLoopFragment> loopFragment = new ArrayList <WJLoopFragment> ();
	private static Stack <WJParFragment> parFragment = new Stack<WJParFragment>();
	private static ArrayList <Integer> exAdd = new ArrayList <Integer>();
	private static String[][] outOfLoopCondition;
	private static String[][] jumpCondition;//（i - 1）->j是不满足opt loop break 进行跳跃的取反条件（(i - 1) j 之间可能是一个或多个单域组合片段）
//	private static ArrayList<ArrayList<HashSet<String>>> jumpConditions;
	private static String[][] falseConditions;// outLoopConditon + jumpCodition
	private static String[][] breakIgnoreLoopCondition;
	public static int diagramslistsize;
	public static String diagramDataName=null;
	
	public static void transEA(String path,MainFrame mainframe) throws Exception 
	{
		
		mainFrame=mainframe;
		diagramslistsize=0;
		
		//所有图
		ArrayList<WJDiagramsData> DiagramsDataList = new ArrayList<WJDiagramsData>();
		
		//这个图的lifelines 和其他
		ArrayList<WJLifeline> lifeLines=new ArrayList<WJLifeline>();//
		ArrayList<WJMessage> messages=new ArrayList<WJMessage>();
		ArrayList<WJFragment> fragments=new ArrayList<WJFragment>();
		ArrayList<UppaalTemplate> templates=new ArrayList<UppaalTemplate>();
		
		HashSet<String > template_names=new LinkedHashSet<String>();
		
//   ---读取EA导出的---	0	
		SAXReader reader=new SAXReader();//获取解析器
		File file = new File(path);
	    Document dom= reader.read(file);//解析XML获取代表整个文档的dom对象
	    Element root=dom.getRootElement();//获取根节点
	    
	    Read uml=new Read();
	    
//	    DiagramsDataList = uml.getUmlAllDiagramData();
//	    System.out.println("----------"+uml.getUmlAllDiagramData().size());
//	    DiagramsDataList.removeAll(DiagramsDataList);
//	    uml.setUmlAllDiagramData(DiagramsDataList);
	    
//	    System.out.println("++++++++++"+uml.getUmlAllDiagramData().size());
	    
	    uml.load(root);
	    

	    // 得到所有图对应的所有数据
	    
	    DiagramsDataList = uml.getUmlAllDiagramData();
	    
	    diagramslistsize=DiagramsDataList.size();
//   ***END***
		
		
		
		
//   ---读取平台导出的---	1	
//		Display.println("================================正在读取导出的所有顺序图xml================================");
//		String folderPath = "D:\\workspace\\SD2AutomataForPlatform\\platformXMLS\\";
//		File file = new File(folderPath);
//		String fileNames[];
//		fileNames = file.list();
//		Display.println("===>  扫描到" + fileNames.length +"张图：\n");
//		for (int i = 0; i < fileNames.length; i++) {
//			if (!fileNames[i].contains(".seq.violet.xml")) {
//				continue;
//			}
//			Display.println(fileNames[i]);
//			SequenceDiagramGraph sd0 = XML2xmlbeanUtil.getSd(new File(folderPath + fileNames[i]));
//			WJDiagramsData platformDiagram = PlatformBean2WJclass.transform(sd0, fileNames[i].split(".seq.violet.xml")[0]);
//			DiagramsDataList.add(platformDiagram);
//		}
//		Display.println();
//		Read.umlAllDiagramData.addAll(DiagramsDataList);
//		for(WJDiagramsData diagramData : DiagramsDataList) {
//			Read.DFSDiagramByREF(diagramData);
//		}
//   ***END***	    
	
	    
	    // 遍历图DiagramsDataList
	    Iterator<WJDiagramsData> DiagramsDataListIterator = DiagramsDataList.iterator();  
	    while(DiagramsDataListIterator.hasNext())
	    {  
	    	//获得第i个图
	    	WJDiagramsData diagramDaraI = DiagramsDataListIterator.next();
	    	
	    	Display.println("================================正在获取顺序图信息================================");
		    Display.println("===>  图名为:"+diagramDaraI.name + "\n");
		    
			//初始化
		    Display.println("初始化数据...\n");
			
		    lifeLines.clear();
		    messages.clear();
		    fragments.clear();
		    templates.clear();
		    id_fragment.clear();
		    lifeLines = diagramDaraI.getLifelineArray();
		    fragments = diagramDaraI.getFragmentArray();
		    messages = diagramDaraI.getMessageArray();
		    
		    //id——fragment hashmap
	    	id_fragment.clear();
	   	    Iterator<WJFragment> fragmentsIterator = fragments.iterator();
		    while(fragmentsIterator.hasNext())
		    {
		    	WJFragment I = (WJFragment) fragmentsIterator.next();	    		    	    	
		    	id_fragment.put(I.getFragId(),I);
		    }
		  
		    WJFragment sd = new WJFragment();//设置SD的id为null
		    sd.setFragType("SD");
		    sd.setBigId("nothing");
		    id_fragment.put("null", sd);
		    WJFragment y = new WJFragment();
		    id_fragment.put("nothing", y);

		    
		    	UppaalTemplate template=new UppaalTemplate();
		    	messageList.clear();//清空数据
		    	fragmentList.clear();
		    	table.clear();
		    	transitionList.clear();
		    	locationList.clear();
		    	endState.clear();
		    
		    	//对table最上面的一排设置边界
	    		WJFragment none0 = new WJFragment();
		    	none0.setBigId("nothingID");
		    	none0.setFragId("none");
		    	none0.setFragType("none");
		    	ArrayList <WJFragment> temp = new ArrayList <WJFragment>();
		    	temp.add(none0);
		    	table.add(0, temp);
		    	
		    	int I = 0;//表示第几个messageI 用于建立表
		    	
		    	
			    
			    if(messages.size() == 0)//没有消息
			    {	    	
			    	Display.println("没有找到message，退出");
					continue;
			    }
			    else
			    {//有消息
			    	Display.println("-------------------------创建状态-------------------------");
			    	
			    	//创建Q0
			    	Display.println("创建初始状态Q0\n");
			    	
				    String q_id = "_init" ;
				    m_id=0;
				    
				    UppaalLocation location0 = setLocation(q_id,q_id);	 //id,   状态name  
				    
				    location0.setInit(true);
				    location0.setObjId(messages.get(0).getFromId());
			    
				    Iterator<WJLifeline> lifelineIteratorForName = lifeLines.iterator();
				    while(lifelineIteratorForName.hasNext())
				    {//遍历所有lifeline确定id对应的名称
				    	WJLifeline lifeline = lifelineIteratorForName.next();
				    	if (location0.getObjId().substring(13).equals(lifeline.getLifeLineId().substring(13))) {
							location0.setObjName(lifeline.getlifeLineName());
						}
				    }
 
				    template.locations.add(location0);	
				    locationList.add(location0);
				    
			    	Iterator<WJMessage> messageIterator = messages.iterator();
			    	
			    	map = new int[messages.size()*2+3][messages.size()*2+3];
			    	
			    	messageIterator = messages.iterator();
			    	int messageIndex = -1;
			    	
			    	while(messageIterator.hasNext())//遍历message  建location  建表
			    	{

			    			messageIndex ++;
		    				WJMessage messageI = messageIterator.next();//1toN	
			    		
			    			I++;
				    		messageList.add(messageI);									//建表 表示这个生命线的所有message
				    		fragmentList.add(id_fragment.get(messageI.getInFragId()));	//表示这个生命线的所有fragment
				    		WJFragment sa=id_fragment.get(messageI.getInFragId());
				    		
				    		ArrayList <WJFragment> tableI = setTableI(sa);//建立table
				    		
				    		table.add(I, tableI);
				    		
		//添加location
				    		Display.println("创建"+ messageI.getName() + "消息发送后到达的状态\n");
				    		
			    			UppaalLocation location = setLocation(messageI.getConnectorId().substring(4),messageI.getName());
			    			//下一个消息的DCBM
			    			if (messageIndex != messages.size() - 1) {
			    				location.setTimeDuration(messages.get(messageIndex + 1).getDCBM());
							}
			    			location.setT1(messageI.getT1());
			    			location.setT2(messageI.getT2());
			    			location.setR1(messageI.getR1());
		    				location.setR2(messageI.getR2()); 
		    				location.setEnerge(messageI.getEnerge());
		    				location.setObjId(messageI.getToId());
						    Iterator<WJLifeline> lifelineIteratorForName2 = lifeLines.iterator();
						    while(lifelineIteratorForName2.hasNext())
						    {//遍历所有lifeline确定id对应的名称
						    	WJLifeline lifeline = lifelineIteratorForName2.next();
						    	
						    	if (location.getObjId().substring(13).equals(lifeline.getLifeLineId().substring(13))) {
									location.setObjName(lifeline.getlifeLineName());
								}	
						    }
//warning在这里添加messageI的信息到location中
			        		template.locations.add(location);
			        		locationList.add(location);
			        		
			        		// 对alt交接处的处理	        		 标记map
			        		if(I != 0 && hasAlt(table.get(I-1))&&hasAlt(table.get(I)))//两个都有alt   不能添加alt中的交接处
			        		{
			        			int thelength = table.get(I).size()<table.get(I-1).size()? table.get(I).size():table.get(I-1).size();
			        			//不同于break的算法 ，alt只需要看短的一个就可以了
			        			for(int c =0;c<thelength;c++)
			        			{
			        				if(table.get(I-1).get(c).getFragType() .equals ("alt")&&
			        						table.get(I).get(c).getFragType() .equals ("alt")&&
			        					!table.get(I-1).get(c).getFragId() .equals (table.get(I).get(c).getFragId())&&
			        						table.get(I-1).get(c).getComId().equals(table.get(I).get(c).getComId())
			        						)//同一个alt中的不同操作域
			        				{	    
			        					map[I-1][I] = -1; //无论如何 这个不能添加
			        					break;
			        				}
			        			}
			        			//map
			        		}	
			        		
			        		// 对par交接处的处理	        		 标记map
			        		if(I != 0 && hasPar(table.get(I-1))&&hasPar(table.get(I)))//两个都有par   不能添加par中的交接处
			        		{
			        			int thelength = table.get(I).size()<table.get(I-1).size()? table.get(I).size():table.get(I-1).size();
			        			//不同于break的算法 ，par只需要看短的一个就可以了
			        			for(int c =0;c<thelength;c++)
			        			{
			        				if(table.get(I-1).get(c).getFragType() .equals ("par")&&
			        						table.get(I).get(c).getFragType() .equals ("par")&&
			        					!table.get(I-1).get(c).getFragId() .equals (table.get(I).get(c).getFragId())&&
			        						table.get(I-1).get(c).getComId().equals(table.get(I).get(c).getComId())
			        						)//同一个alt中的不同操作域
			        				{	        
			        					Display.println("标记par组合片段的操作域分割位置");
			        					Display.println("第"+(I-1)+"条消息与第"+(I) +"条消息之间是处于不同操作域的par组合片段中"); 
			        					//map
//			        					k1+="标记par组合片段的操作域分割位置";
//										value1+="第"+(I-1)+"条消息与第"+(I) +"条消息之间是处于不同操作域的par组合片段中\n";
			        					//
			        					map[I-1][I] = -1; //无论如何 这个不能添加
			        					break;
			        				}
			        			}
			        			
			        		}
			    		        				    	    		    	    		
		    	    		
		        		
	
			    		
			    	
			    	}//遍历message 顺序添加除了break和alt之间外的transition
			    	
			    	
	//填充table
		    	int maxLength = 0;
		    	for(int i = 0 ; i<table.size();i++)
		    	{
		    		if(table.get(i).size()>maxLength)
		    			maxLength = table.get(i).size();
		    	}//找最大长度
		    	WJFragment none = new WJFragment();
		    	none.setBigId("nothingID");
		    	none.setFragId("none");
		    	none.setFragType("none");
		    	
		    	for(int i = 0 ; i<=table.size();i++)
		    	{//填充none
		    		
			    	
		    		if(i==table.size())//填充底部
		    		{
		    			ArrayList <WJFragment> t = new ArrayList <WJFragment>();
		    			for(int j = 0; j <=maxLength;j++)
		    			t.add(none);
		    			
		    			table.add(i, t);
		    			break;
		    		}
		    		else//填充右边部分
		    		{
		    			for(int j = table.get(i).size(); j <=maxLength;j++)
		    			table.get(i).add(j, none);
		    		}
		    	}
		    	
		    	altEndTo = new int[table.size() * 2];
		    	parEndTo = new int[table.size() * 2];
		    	Display.println("\n-------------------------根据消息所在的组合片段，构建组合片段嵌套表-------------------------");
	//打印table  并且找出所有交接处i的出alt位置放入altEndTo 出的位置又是alt的交接处 while（altEndTo[altEndTo[i]]!=0）{i=altEndTo[i]}return altEndTo[i]
		    	//
		    	for(int i = 0;i<table.size();i++)
		    	{
		    		Display.print(String.format("%-2d:", i));
		    		
		    		for(int c = 0;c<table.get(i).size();c++)
		    		{
		    			if(i>=1&&table.get(i-1).get(c).getFragType().equals("alt")&&
	    						table.get(i).get(c).getFragType().equals("alt")&&
	    					!table.get(i-1).get(c).getFragId().equals(table.get(i).get(c).getFragId())&&
	    						table.get(i-1).get(c).getComId().equals(table.get(i).get(c).getComId())
	    						)//同一个alt中的不同操作域 是交接处的下一个
		    			altEndTo[i] = findOutOfAlt(i,c);//找到出alt的位置
		    			
		    			if(i>=1&&table.get(i-1).get(c).getFragType().equals("par")&&
	    						table.get(i).get(c).getFragType().equals("par")&&
	    					!table.get(i-1).get(c).getFragId().equals(table.get(i).get(c).getFragId())&&
	    						table.get(i-1).get(c).getComId().equals(table.get(i).get(c).getComId())
	    						)//同一个par中的不同操作域 是交接处的下一个
		    			parEndTo[i] = findOutOfAlt(i,c);//找到出par的位置  findOutOfAlt适用于par
		    			
		    			Display.print(String.format("%-6s", table.get(i).get(c).getFragType()));
		    		}
		    		Display.println();
		    	}
		    	Display.println();
		    	Display.println();
		    	loopFragment = new ArrayList <WJLoopFragment> ();
		    	parFragment.clear();
	//主要算法
		    	
		    	//初始化F & G
		    	F = new ArrayList<HashSet <Integer>>();	   
		    	F1 = new ArrayList<HashSet <Integer>>();	  
		    	G = new ArrayList<HashSet <Integer>>();
	
		    	HashSet <Integer> Fi = new HashSet <Integer>();
		    	F.add(Fi);//不可能到达0 所以添加空
		    	HashSet <Integer> Gi = new HashSet <Integer>();
		    	Gi.add(1);//0能到达1
		    	G.add(Gi);
		    	
		    	//初始化 falseCondition
		    	jumpCondition = new String[table.size()][table.size()];
		    	breakIgnoreLoopCondition = new String[table.size()][table.size()];
			    for(int i = 1; i < table.size()-1; i++)
			    {
			    	Fi = new HashSet <Integer>();
			    	Gi = new HashSet <Integer>();
			    	
			    	Fi.add(i);
			    	if(!hasLastBreak(i) && map[i][i+1]!=-1)//该消息不是 break的最后一个消息 && 不是alt的交界处
			    	{			
			    		Gi.add(i+1); // 可以直接到下一条消息
			    		// 添加 goCondition   A && b && c
			    	}
			    	for(int c = 2; c < table.get(i).size();c++ )
			    	{
			    		if(table.get(i).get(c).getFragType() .equals( "none"))
			    			break;
			    				    		
			    		if(table.get(i).get(c).getFragId() != table.get(i-1).get(c).getFragId())//是组合片段的第一个消息
			    		{
			    			Fi.addAll(findAlsoTo(i,c,table.get(i).get(c).getFragType()));
			    		}
			    		
			    		if(table.get(i).get(c).getFragId() != table.get(i+1).get(c).getFragId())///是组合片段的最后一个消息
			    		{
			    			Gi.addAll(findOutTo(i,c,table.get(i).get(c).getFragType()));	 
			    		}
			    	}
			    	
			    	F.add(Fi);
	    			G.add(Gi);
			    }
			    Fi = new HashSet<Integer>();
			    Fi.add(table.size()-1);
			    F.add(Fi);//最后一个F(i)并不存在，但是添加后作为终态的判定和loop的end判定
			    for(int i = 0; i < F.size(); i++) {//保存一次跳跃的F 到 F1
			    	HashSet<Integer> temp1 = new HashSet<>();
			    	for(int x : F.get(i)) {
			    		temp1.add(x);
			    	}
			    	F1.add(temp1);
			    }
			    fixF();//将F从一次跳跃扩张到多次跳跃
			    
			    
	
			    //将除了par和loop的特殊情况外  填入map
			    for(int i=0;i<F.size()-1;i++)
			    {
			    	//i可以到哪
		    		for(int Gii :G.get(i)) //i可以到Gii
		    		{
		    			for(int Fii: F.get(Gii))//能到达Gii的话又可能到达F(Gii)
				    	{	
		    				if (map[i][Fii] != -1) {
								map[i][Fii] = Fii;
							}
				    	}
		    		}
			    }
	//par的情况 //暂不考虑par的情况了
//			    while(!parFragment.isEmpty())
//			    {
//			    	WJParFragment pfrag = parFragment.pop();//先处理里层的par再处理外层的par
//			    	
//			    	int c = pfrag.getC();
//			    	
//			    	for(int i = pfrag.getStart();i<pfrag.getEnd();i++)
//			    	{
//			    		HashSet <Integer> fromList = new HashSet <Integer>();
//				    	HashSet <Integer> toList = new HashSet <Integer>();
//				    	
//			    		String fragId = table.get(i).get(c).getFragId();//i所在的操作域ID
//			    		if(table.get(i).get(c+1).getFragType().equals("none"))
//			    			fromList.add(i);
//			    		else if(
//			    				!table.get(i).get(c+1).getFragType().equals("alt") &&
//			    				!table.get(i).get(c+1).getFragType().equals("par")&&
//			    				!table.get(i).get(c+1).getFragId().equals(table.get(i+1).get(c+1).getFragId())  	    				
//			    				)//如果嵌套的不是alt和par 并且是嵌套的最后一个消息
//			    		{
//			    			int k = findRightI(i+1);
//			    			for(int j = findStartOfFrag(i,c+1);j<findOutOfFrag(i, c+1);j++)
//			    				if(map[j][k] >= 1)//此被嵌套的组合片段中那些消息能够到达k
//			    					fromList.add(j);
//			    		}
//			    		else if(		    				
//			    				table.get(i).get(c+1).getFragType().equals("alt") || table.get(i).get(c+1).getFragType().equals("par")&&
//			    				!table.get(i).get(c+1).getComId().equals(table.get(i+1).get(c+1).getComId())  	    						
//			    				)//如果是alt或者par的操作域  并且是操作域的最后一个消息
//			    		{
//			    			int k = findRightI(i+1);
//			    			for(int j = findStartOfAlt(i,c+1);j<findOutOfAlt(i, c+1);j++)
//			    				if(map[j][k] >= 1)
//			    					fromList.add(j);
//			    		}
//			    		else
//			    		{
//			    			continue;//是组合片段的中间部分 不管
//			    		}
//			    		
//			    		for(int t = pfrag.getStart();t<pfrag.getEnd();t++)//to what?
//			    		{
//			    			if(table.get(t).get(c).getFragId().equals(fragId))
//			    				continue;
//			    			if(table.get(t).get(c+1).getFragType().equals("none"))
//			    				toList.add(t);
//			    			else if(!table.get(t).get(c+1).getFragId().equals(table.get(t-1).get(c+1).getFragId())  	    						
//				    				)// 是组合片段的第一个
//			    			{
//			    				
//			    				for(Object o: F.get(t))
//			    		    	{
//			    					int fii = (int) o;
//			    					if(fii < findOutOfFrag(t, c))//跳转时不能跳到操作域之外
//			    						toList.add(fii);
//			    		    	}
//			    			}		  				
//			    		}
//			    		
//			    		setMap(fromList,toList);//设置map[fromList][toList]
//			    			
//			    	}
//			    }
			    
	//处理loop回去的情况		    
			    for(int k=0;k<loopFragment.size();k++)
			    {
			    	int start = loopFragment.get(k).getStart();
			    	int idEnd = loopFragment.get(k).getEnd();
			    	WJLoopFragment thisFragment = loopFragment.get(k);
			    	int realEnd = findRightLoopEnd(idEnd);//找出正确的end 不可处于alt交接处的下一个
			    	int c = loopFragment.get(k).getC();
			    	//谁可以到达end
			    	ArrayList<Integer> loopEndList = new ArrayList<Integer>();
			    	for(int i=start;i<idEnd;i++)//|id|
			    	{
			    		if(map[i][realEnd]>0 && !islastbreak(i,c+1))//i可以到达reaLend 并且不是该loop的内一层最后一个break
			    			loopEndList.add(i);
			    	}
			    	for(int i = start + 1; i < idEnd; i++) {
			    		if (F1.get(i).contains(realEnd)) {//这种情况是loop里层最下面是一个单域组合片段，则这个单域组合片段上面的消息可以不进入这个单域组合片段直接返回loop的start
			    			//i 可以一次跳跃 到达realEnd ，那么说明i可以loop回start， 条件要加上跳跃的条件（!opt）
							F1.get(i).add(start); //添加一次跳跃
							String condition = jumpCondition[i][realEnd];
							//添加跳跃条件
							jumpCondition[i][start] = condition;
						}
			    		if (G.get(i).contains(realEnd)) { // 最后一个可以到达第一个
							G.get(i).add(start);
						}
			    	}
			    	
			    	//谁是loop的开头
			    	ArrayList<Integer> loopStartList = new ArrayList<Integer>();
			    	
			    	for(Object obj2: F.get(start))
			    	{
			    		int startLocation = (int) obj2;
			    		if(startLocation<idEnd && startLocation>=start)//|id|
			    			loopStartList.add(startLocation);
			    	}
			    	//标记所有可达end的到loop的所有开头
			    	for(int i=0;i<loopEndList.size();i++)
			    		for(int j=0;j<loopStartList.size();j++)
			    			map[loopEndList.get(i)][loopStartList.get(j)] = loopStartList.get(j);
			    	
			    }
			    // 设置出loop的取反条件
			    outOfLoopCondition = new String[table.size()][table.size()];
			    Display.println("-------------------------获取跳出loop的条件-------------------------");
			    setOutOfLoopCondition();
			    Display.println();
//			   // 初始化falseConditions
//			    jumpConditions = new ArrayList<>();
//			    for(int i = 0; i < table.size(); i++) {
//			    	ArrayList<HashSet<String>> arrayListI = new ArrayList<HashSet<String>>();
//			    	for (int j = 0; j < table.size(); j++) {
//						HashSet<String> set = new HashSet<>();
//						arrayListI.add(set);
//					}
//			    	jumpConditions.add(arrayListI);
//			    }
			
	//最后的     跳跃条件	
			    Display.println("-------------------------获取组合片段的取反条件-------------------------");
			    fixF1ForFalseCondition();
			    Display.println();
	//在此处设置终态 
			    for(int i=0;i<locationList.size();i++)
			    {
			    	if(map[i][locationList.size()] >= 1)
			    		locationList.get(i).setFnal(true);
			    }
	
			    
	//打印map
			    Display.println("-------------------------得到邻接矩阵-------------------------");
			    for(int i=0;i<locationList.size()+1;i++)		
			    {	
			    	Display.print(String.format("%-2d:", i));
			    	for(int j=0;j<locationList.size()+1;j++){
			    		
			    		Display.print(String.format("%-3d", map[i][j]));
			    	}
			    	Display.println();
			    }
			    //table
			   Display.println("\n");
	//连接map
			   Display.println("--------------------------根据邻接矩阵连接状态--------------------------");
			    //Queue receiveAndSend = new LinkedList();
			    boolean isSelfMessage = false;
			    for(int i=0;i<locationList.size();i++) {
		    		for(int j=0;j<locationList.size();j++)
		    		{
		    			if (map[i][j] == -1) { // 状态的时间约束取自下一条消息  去掉alt操作域之间的时间约束
		    				// 实际上是 分支的状态可能有两个相同的时间约束 有一条无法读取
							locationList.get(i).setTimeDuration("null");
						}
		    			if(map[i][j] >= 1)
			    		{
		    				
			    			//message
		    				WJMessage messageJ = messageList.get(j-1);
		    				
							
							
							String receiveOrSend = "";
							
							if(messageJ.getReceiveAndSend().equals("null"))//处理自己到自己的消息造成的2个重复的transition 改为一个！一个？
							{	
						    		
								if(messageJ.getFromId().substring(13).equals(messageJ.getToId().substring(13)))
					    		{	
					    			receiveOrSend+="!?";
					    		}		
							
					    		if(receiveOrSend.equals("!?") && j < messageList.size())
					    		{
					    			messageList.get(j).setReceiveAndSend("?");//下一个是？
					    			receiveOrSend = "!";//这个改成！
					    			isSelfMessage = true;//是自己到自己的消息
					    		}
					    			
							}
							else
							{
								receiveOrSend = messageJ.getReceiveAndSend();
							}
							
							//需要加上opt后else 为 !(opt条件)
							String elseCondition = "";
							if (falseConditions[i][j] != null) {
								elseCondition = falseConditions[i][j];
							}
							String outCondition = "";
							if (outOfLoopCondition[i][j] != null) {
								outCondition = outOfLoopCondition[i][j];
							}
				    		
							//location 起点
							UppaalLocation lastLocation0=locationList.get(i);
							//location 终点
							UppaalLocation location=locationList.get(j);
							UppaalTransition transition = new UppaalTransition();
							String T1 = messageJ.getT1();
							String T2 = messageJ.getT2();
							if(isSelfMessage && receiveOrSend.equals("!"))
							{  
								T1 = "0";
								T2 = "0";
								isSelfMessage = false;
								
							}	
							
							String typeAndCondition = getTypeAndnCondition(messageJ) ;
							if(!elseCondition.equals("")) {
								if (typeAndCondition.equals("null")) {
									typeAndCondition = elseCondition;
								} else {
									typeAndCondition = typeAndCondition + "--"+ elseCondition ;
								}
							}
							if(!outCondition.equals("")) {
								if (typeAndCondition.equals("null")) {
									typeAndCondition = outCondition;
								} else {
									typeAndCondition = typeAndCondition + "--"+ outCondition ;
								}
							}
							transition = setTransition(messageJ,
									m_id++,
									messageJ.getName(),
									receiveOrSend,
									typeAndCondition,
		    	    				lastLocation0.getId(),lastLocation0.getName(),
		        					location.getId(),    location.getName(),
		        					messageJ.getT1(),messageJ.getT2());
							template.transitions.add(transition);
		    	    		
							Display.println("连接状态" + i +"到状态" + j + "\n" +  
									"消息名：" + transition.getNameText()  + 
									"\n消息的所在的组合片段以及条件" +transition.getTypeAndCondition() + "\n");
		    			}
		    		}
			    }
	//在最后根据map添加transition
			    
			    }//message
			    
			    template.setName("template_");
			    //+lifelineI.getlifeLineName());
			    template_names.add("template_");
			    //+lifelineI.getlifeLineName());
			    templates.add(template);  
		    Display.println("\n------------------------------------------------------------------");
		    Display.println("完成顺序图到自动机的转换，正在写入图名为"+diagramDaraI.name+"的xml");
		    
		    Write.creatXML("D:\\ModelDriverProjectFile\\WJXML\\"+diagramDaraI.name+".xml",templates,template_names);//给我
		    setDiagramDataName(diagramDaraI.name);
		    System.out.println("diagramDaraI.name-----"+diagramDaraI.name);
		    //给ckt
		    WriteForXStream.creatXML("D:\\ModelDriverProjectFile\\WJXML\\"+diagramDaraI.name+"ForXStream.xml", templates, template_names);
		    Display.println(".....写入完成!\n");
		    
	    }//遍历diagram结束
	    Display.println("================================所有顺序图转换完成================================");
	}//end
	


	private static void setMessagePreName(ArrayList<WJMessage> messages, ArrayList<WJLifeline> lifeLines) {
		HashMap<String, WJLifeline> findLifeLineByID = new HashMap<>();
		for(WJLifeline lifeline : lifeLines) {
			findLifeLineByID.put(lifeline.getLifeLineId().substring(13), lifeline);
		}
		for(WJMessage message : messages) {
	    	WJLifeline lifeline = findLifeLineByID.get(message.getToId().substring(13));
	    	message.setName(lifeline.getlifeLineName() + "." +message.getName());
	    }
	}



	private static String getFilePath() throws Exception {
		File file = new File(".\\");
		String fileNames[];
		fileNames = file.list();
		String readFileName = "0";
		for(String fileName : fileNames) {
			if (Character.isDigit(fileName.charAt(0)) && fileName.charAt(1) == '.') {
				if (compareVersion(fileName.replace(".xml", ""), readFileName.replace(".xml", "")) > 0) {
					readFileName = fileName;
				}
			}
		}
		return readFileName;
	}
	public static int compareVersion(String version1, String version2) throws Exception {  
		
	    if (version1 == null || version2 == null) {  
	        throw new Exception("compareVersion error:illegal params.");  
	    }  
	    String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；  
	    String[] versionArray2 = version2.split("\\.");  
	    int idx = 0;  
	    int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
	    int diff = 0;  
	    while (idx < minLength  
	            && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
	            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
	        ++idx;  
	    }  
	    //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
	    diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
	    return diff;  
	}  


	private static void setOutOfLoopCondition() {
		
		boolean isDisplayed = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] > 0) {
					ArrayList<String> loopConditionsI = new ArrayList<>();
					ArrayList<String> loopConditionsJ = new ArrayList<>();
					// 获得消息i的所有loop条件
					for (int c = 0; c < table.get(i).size(); c++) {
						if (table.get(i).get(c).getFragType().equals("loop")) {
							loopConditionsI.add(table.get(i).get(c).getFragCondition());
						}
					}
					// 获得消息j的所有loop条件
					for (int c = 0; c < table.get(j).size(); c++) {
						if (table.get(j).get(c).getFragType().equals("loop")) {
							loopConditionsJ.add(table.get(j).get(c).getFragCondition());
						}
					}
					// 获得I中有 J中没有的loop 说明I到J跳出了这些loop
					loopConditionsI.removeAll(loopConditionsJ);
					if (breakIgnoreLoopCondition[i][j] != null) {// 如果i到j是因为break跳出的 则可以无视loop的condition
						loopConditionsI.remove(breakIgnoreLoopCondition[i][j]);
					}
					if (loopConditionsI.size() == 0) {
						continue;
					}
					String allConditionsOutOfLoop = "";
					for (int k = 0; k < loopConditionsI.size(); k++) {
						if (k == 0) {
							allConditionsOutOfLoop = becomeFalseCondition(loopConditionsI.get(k));
						} else {
							allConditionsOutOfLoop += "--" + becomeFalseCondition(loopConditionsI.get(k));
						}
					}
					outOfLoopCondition[i][j] = allConditionsOutOfLoop;
					Display.println("消息" + i + "直接到消息" + j + "跳出了loop,所需的条件为：");
					Display.println(allConditionsOutOfLoop + "\n");
					isDisplayed = true;
				}
			}
		}
		if (!isDisplayed) {
			Display.println("----> 未找到跳出组合片段的情况\n");
		}
	}



	private static void fixF1ForFalseCondition() {
		boolean isDisplayed  = false;
		falseConditions = new String[table.size()][table.size()];
		
		
		int IjumpToK[][] = new int[table.size()][table.size()];
		// 标记一次跳跃条件 
		for(int i = 0; i < jumpCondition.length; i++) {
			for (int j = 0; j < jumpCondition[i].length; j++) {
				if (jumpCondition[i][j] != null) {
					IjumpToK[i][j] = 1; // 设置已经有跳跃条件 忽略多次跳跃
				}
			}
		}
		
		
		for(int i=0;i<F1.size();i++)//多次跳跃 条件
		{
			for(int j=i+1; j<F1.size();j++) {
				if(F1.get(i).contains(j)) {//如果i能到达j
					F1.get(i).addAll(F1.get(j)); // i能到  所有j能跳跃的位置  i->j->k
					
					for(int k : F1.get(j)) {
						if (j == k || map[i][j] == -1) {
							continue;
						}
						if (IjumpToK[i][k] != 1) {
							
							// 满足I跳到J的条件 然后也满足J跳到K的条件 则就是I到K的条件
							String jumpToJCondition = jumpCondition[i][j];
							String andJumpToKCondition = jumpCondition[j][k];
							if (andJumpToKCondition == null) {
								jumpCondition[i][k] = jumpToJCondition;
							} else {
								jumpCondition[i][k] = jumpToJCondition + "--" + andJumpToKCondition;
							}
							if (jumpCondition[i][k] != null) {
								Display.println("执行第" + i + "条消息后可跳过第" + j +"条消息，到达第" + k + "条消息，其条件为：");
								Display.println(jumpCondition[i][k] + "\n");
								isDisplayed = true;
							}
							
							IjumpToK[i][k] = 1;
						}
						
					}
				}
			}
		}
		int i = 0;
		for(HashSet<Integer> Gi :G) {// 第i条消息 能到达j
			for (int gotoJ : Gi) {//可以跳过j 直接能跳跃到k
				for (int jumptoK : F1.get(gotoJ)) {//可以跳过j 直接能跳跃到k
					String conditionInit = "";
					
					//i 到 k 需要添加跳跃条件
					if (gotoJ == jumptoK) { // 没有跳跃发生
						continue;
					} else { // 发生跳跃i->j->k
						String andJumpToKCondition = jumpCondition[gotoJ][jumptoK];						
						falseConditions[i][jumptoK] = andJumpToKCondition;

					}
				}
			}
			i++;
		}
		if (!isDisplayed) {
			Display.println("----> 没有需要取反条件的情况\n");
		}
	}

	private static String removeDuplicateCondition(String conditions) {
//		if (!conditions.contains("--")) {
//			return conditions;
//		}
//		conditions = conditions.replaceAll(" ", "");
//		HashSet<String> allConditionsSet = new HashSet<>();
//		
//		String[] strings = conditions.split("--");
//		for (int i = 0; i < strings.length; i++) {
//			allConditionsSet.add(strings[i]);
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		boolean first = true;
//		for(String string : allConditionsSet) {
//			if (first) {
//				sb.append(string);
//				first = false;
//			} else {
//				sb.append("&&");
//				sb.append(string);
//			}
//		}
//			
//		
//		return sb.toString();
		return conditions;
	}

	//这条消息 是不是最后break片段的最后一个消息
	private static boolean islastbreak(int i, int c) {
		if(table.get(i).get(c).getFragType().equals("break")&&
				!table.get(i+1).get(c).getFragId().equals(table.get(i).get(c).getFragId()))//是最后一个break
			return true;
		else
			return false;
	}
	private static void fixF() {
		
		for(int i=0;i<F.size();i++)
		{
			for(int j=i+1; j<F.size();j++)
			if(F.get(i).contains(j))
				F.get(i).addAll(F.get(j));
		}
	}
	public static int findRightLoopEnd(int i)//只管alt就可以了 处于par交接点时有多个，此时altEndTo[i]=0 取i即可
	{
		if(altEndTo[i] == 0 )
			return i;
		
		while(altEndTo[i]!=0)
		{
			//i不可能同时是alt和par的交接处
				i=altEndTo[i];			
		}
		
		return i;
	}
	//找到正确的出组合片段位置
	public static int findRightI(int i)//跳过alt的交接处 跳过par的交接处并且加上par的交接处
	{
		if(altEndTo[i] == 0 && parEndTo[i] == 0)
			return i;
		
		while(altEndTo[i]!=0 || parEndTo[i]!=0)
		{
			if(altEndTo[i]!=0)//i不可能同时是alt和par的交接处
				i=altEndTo[i];
			else if(parEndTo[i]!=0)
			{	
				exAdd.add(i);//par的交接处也需要进行添加
				i=parEndTo[i];
			}
		}
		
		return i;
	}
	//得到Gi集合 i一定是某个组合片段中最后一条消息
	private static HashSet<Integer> findOutTo(int i, int c, String fragType) {//返回G（i）
		HashSet<Integer> rt = new HashSet<Integer>();
		exAdd = new ArrayList <Integer>(); //par的交接处也需要进行添加
		int I = i; // 保存原始的I
		if(fragType.equals ("opt") || fragType.equals ("loop") )//只有一个
		{
			//eqwe
			i=findOutOfFrag(i,c);//找到出组合片段的i
			int righti = findRightI(i);
			rt.add(righti);//遇到alt交接点 往下找
//			// 设置出loop的条件
//			setOutOfLoopCondition(I, c, righti, fragType.equals ("loop"));
		} else if(fragType.equals ("break"))
		{
			c--;
			if(c == 1) //是最外层的break
			{	
				rt.add(table.size()-1);//到达终态
				return rt;
			} 
			boolean breakOfLoop = false;
			String breakCondtion = new String();
			if(table.get(i).get(c).getFragType().equals("loop")) {
				breakOfLoop = true;
				breakCondtion = table.get(i).get(c).getFragCondition();
			}
			if(!table.get(i).get(c).getFragType().equals("alt") && !table.get(i).get(c).getFragType().equals("par"))
				i=findOutOfFrag(i,c);//找到出组合片段的i
			else
				i=findOutOfAlt(i, c);
			
			
			int righti = findRightI(i);
			rt.add(righti);//遇到alt交接点 往下找
			if (breakOfLoop) {
				breakIgnoreLoopCondition[I][righti] = breakCondtion;
			}
			// 添加 goCondition   A && b && c
		}else if(fragType.equals ("alt") || fragType.equals ("par"))
		{
			i=findOutOfAlt(i,c);//找到出组合片段的i
			int righti = findRightI(i);
			rt.add(righti);//遇到alt交接点 往下找
			// 添加 goCondition   A && b && c
		}
		
		rt.addAll(exAdd);//par的交接处也需要进行添加
		return rt;
	}
	//得到Fi 一次跳跃集合 消息i一定是某个组合片段第一条消息
	private static HashSet<Integer> findAlsoTo(int i, int c, String fragType) {//返回1次跳跃F（i）
		HashSet<Integer> rt = new HashSet<Integer>();
		//exAdd = new ArrayList <Integer>(); //par的交接处不需要添加 ，这里将在par的特殊情况中添加
		
		if(fragType.equals ("opt") || fragType.equals ("loop") || fragType.equals ("break"))//只有一个
		{
			int I = i;
			i=findOutOfFrag(i,c);//找到出组合片段的i
			if(fragType.equals ("loop"))//记录loop的位置
			{
				WJLoopFragment temp = new WJLoopFragment();
				temp.setCondition(table.get(I).get(c).getFragCondition());
				temp.setStart(I);
				temp.setEnd(i);
				temp.setC(c);
				loopFragment.add(temp);
			}
			
			int rightI = findRightI(i);
			//添加falseCondition
			setJumpOnceContidion(I, rightI, c);
			rt.add(rightI);//遇到alt交接点 往下找
		}
		else if(fragType.equals( "alt" )|| fragType.equals("par"))//有多个
		{
			String comid = table.get(i).get(c).getComId();
			int I = i;
			while(table.get(i).get(c).getComId().equals( comid))
			{
				i=findOutOfFrag(i,c);//找到出组合片段的i
				
				if(table.get(i).get(c).getComId().equals(comid))//还在alt内部 i此时是交接点的下一个
					rt.add(i);
			}
			
			if(fragType.equals ("par") && !table.get(I-1).get(c).getComId().equals(comid))//记录par的位置
			{//是par并且是第一条消息
				WJParFragment temp = new WJParFragment();
				temp.setStart(I);
				temp.setEnd(i);
				temp.setC(c);
				parFragment.push(temp);
			}
			/*rt.add(findRightI(i));//出了alt那么添加正确的i*///不管 因为alt和par是一定会进入的 不能跳过所有alt的操作域到达alt外面
			String aString = new String();
		}
		
		return rt;
	}

	// 设置一次跳跃的条件  I是上个组合片段的首个消息 rightI是下个组合片段的首个消息
	private static void setJumpOnceContidion(int I, int rightI, int C) {
		ArrayList<String> intoIConditions = new ArrayList<>();
		String intoICondition = "";
		// 得到C层以及之前的条件
//		for(int k = 2; k <= C; k++) {
//			intoIConditions.add(table.get(I).get(k).getFragCondition());
//		}
//		// 连接所有进入I的条件 除了当前的组合片段 最终结果是  !(intoICondition)  才会跳出到rightI
//		for(String condition: intoIConditions) {
//			if (intoICondition.equals("")) {
//				intoICondition = condition;
//			} else {
//				intoICondition += "&&" + condition; 
//			}
//		}
//		// 似乎 必须 记录所有层的条件
//		if (jumpCondition[I][rightI] == null) { 
//			if (intoICondition.equals("")) {
//				jumpCondition[I][rightI] = null;
//			} else {//不进到I中 才能跳跃到rightI
//				jumpCondition[I][rightI] = becomeFalseCondition( + intoICondition + ")";
//			}
//		}
		// 只记录最外层的跳跃条件
		String thisCondition = table.get(I).get(C).getFragCondition();
		if (jumpCondition[I][rightI] == null) { // 只记录最外层的跳跃条件
			if (intoICondition.equals("")) {
				jumpCondition[I][rightI] = becomeFalseCondition(thisCondition);
			} else {
				jumpCondition[I][rightI] = intoICondition + "--" + becomeFalseCondition(thisCondition);
			}
		}
		

	}
	private static String becomeFalseCondition(String thisCondition) {
		if (thisCondition.contains("#")) {
			int indexOfJin = 0;
			for (int i = 0; i < thisCondition.length(); i++) {
				if (thisCondition.charAt(i) == '#') {
					indexOfJin = i;
					break;
				}
			}
//			Display.println("!(" + thisCondition.substring(0, indexOfJin) + ")"
//					+ thisCondition.substring(indexOfJin));
			return "!(" + thisCondition.substring(0, indexOfJin) + ")"
					+ thisCondition.substring(indexOfJin);
		} else {
			return "!(" + thisCondition + ")";
		}
		
	}



	private static boolean hasLastBreak(int i) {
		
		for(int a=2;a<table.get(i).size();a++)//i行是否存在最后一个break
			if(table.get(i).get(a).getFragType().equals ("break") && !table.get(i+1).get(a).getFragId().equals (table.get(i).get(a).getFragId()) )
				return true;
		
		return false;
	}
	private static int findStartOfFrag(int i, int c) {
		String id = table.get(i).get(c).getFragId();
		
		while(table.get(i).get(c).getFragId().equals(id) )
			i--;
		
		return i+1;
	}
	private static int findStartOfAlt(int i, int c) {
		String id = table.get(i).get(c).getComId();
		
		while(table.get(i).get(c).getComId().equals(id) )
			i--;
		
		return i+1;
	}
	
	private static int findOutOfFrag(int i, int c) {//找到除了alt和par出去的位置
		
		String id = table.get(i).get(c).getFragId();
		
		while(table.get(i).get(c).getFragId().equals(id) )
			i++;
		
		return i;
	}
	private static int findOutOfAlt(int i, int c) {//找到(i,c)alt的出去的位置 适用于par
		
		String comid = table.get(i).get(c).getComId();
		
		while(table.get(i).get(c).getComId().equals(comid))
			i++;
		
		return i;
	}

	
		
	public static boolean hasBreak(ArrayList <WJFragment> tableI)//messageI包含break
	{
		for(int i =0;i<tableI.size();i++)
		{
			if(tableI.get(i).getFragType().equals("break"))
				return true;
		}
		
			return false;
	}
	public static boolean hasAlt(ArrayList <WJFragment> tableI)//messageI包含alt
	{
		for(int i =0;i<tableI.size();i++)
		{
			if(tableI.get(i).getFragType().equals("alt"))
				return true;
		}
		
			return false;
	}
	public static boolean hasPar(ArrayList <WJFragment> tableI)//messageI包含par
	{
		for(int i =0;i<tableI.size();i++)
		{
			if(tableI.get(i).getFragType().equals("par"))
				return true;
		}
		
			return false;
	}
	public static ArrayList <WJFragment> setTableI(WJFragment fragment)
	{
		ArrayList <WJFragment> temp = new ArrayList <WJFragment>();
		Stack <WJFragment> s = new Stack <WJFragment>();
		while(!fragment.getFragType().equals("SD"))
		{
			s.push(fragment);
			fragment = id_fragment.get(fragment.getBigId());
		}
		s.push(fragment);
		WJFragment none = new WJFragment();
    	none.setBigId("meiyou");
    	none.setFragId("none");
    	none.setFragType("none");
    	s.push(none);
		//出栈 放入arraylist
		while(!s.isEmpty())
		{
			temp.add(s.pop());
		}
		return temp;
	}
	public static UppaalLocation setLocation(String id, String name)
	{
		UppaalLocation location = new UppaalLocation();
			location.setId(id);
		    
		    location.setName(name);
		    
		return location;
	}
	
	public static UppaalTransition setTransition(WJMessage messageI, 
			int message_id, 
			String message_name,
			String receiveOrSend,
			String typeAndCondition,
			String sourceId, 
			String source_name,
			String targetId, 
			String target_name, 
			String T1,String T2 
			)
	{
		UppaalTransition transition = new UppaalTransition();
		transition.setReceiveOrSend(receiveOrSend);
		transition.setTypeAndCondition(typeAndCondition);
		transition.setId(message_id);
		transition.setNameS(source_name);
		transition.setNameT(target_name);
		transition.setSourceId(sourceId);
		transition.setTargetId(targetId);
		transition.setNameText(message_name);
		transition.setT1(T1);
		transition.setT2(T2);
		transition.setDCBM(messageI.DCBM);
		transition.setSEQDC(messageI.SEQDC);
		transition.setSEQDO(messageI.SEQDO);
		transition.setSEQTC(messageI.SEQTC);
		transition.setSEQTO(messageI.SEQTO);
		transition.setInString(messageI.inString);
		transition.setOutString(messageI.outString);
		transition.setTypeId(messageI.getTypeId());
		transition.use = messageI.use;
		transition.def = messageI.def;
		transition.RESET = messageI.RESET;
		return transition;
	}
	
	
	public static void setMap( HashSet <Integer> a,HashSet <Integer> b)
	{//连接a到b
		
		Iterator<Integer> ii =a.iterator();
		Iterator<Integer> jj =b.iterator();
		while(ii.hasNext())
		{
			int i = (int)ii.next();
			
			jj =b.iterator();
			while(jj.hasNext())
			{
				int j = (int)jj.next();
				if(map[i][j]!=-1)
				{										
    	    		map[i][j] = j;
				}
			}
		}
		return ;
	}
	public static String getTypeAndnCondition(WJMessage messageI)
	{
		String nCondition = "";
		String type = "";
		String typeId = "";
		String id = messageI.getInFragId();
		boolean isInSameOpt = false;
		WJFragment fragment;
		while(!id.equals("null"))//对所有条件进行交集  
		{
			fragment = id_fragment.get(id);
			
			//条件
			nCondition =   fragment.getFragCondition()+"--"+nCondition; 
			// 组合片段种类
			type = fragment.getFragType()+"-"+type;
			// 组合片段的id （alt和par为comID）
			if (fragment.getFragType().equals("alt") || fragment.getFragType().equals("par")) {
				if (typeId .equals("")) {
					typeId = fragment.getComId();
				} else {
					typeId = fragment.getComId()+"-"+typeId;
				}
				
			} else {
				if (typeId .equals("")) {
					typeId = fragment.getFragId();
				} else {
					typeId = fragment.getFragId()+"-"+typeId;
				}
			}
			
			id=fragment.getBigId();
		}
		if (typeId.equals("")) {
			messageI.setTypeId("null");
		} else{
			messageI.setTypeId(typeId);
		}
		
		
		if(type.equals("")) {
			messageI.setConditions("null");
			return "null";
		} else {
			messageI.setConditions("["+type.substring(0,type.length()-1)+"]"+"/"+nCondition.substring(0,nCondition.length()-2));
			return "["+type.substring(0,type.length()-1)+"]"+"/"+nCondition.substring(0,nCondition.length()-2);
		}
	}

	public static String getDiagramDataName() {
		return diagramDataName;
	}

	public static void setDiagramDataName(String diagramDataName) {
		SD2UppaalMain.diagramDataName = diagramDataName;
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public static int getDiagramslistsize() {
		return diagramslistsize;
	}
	
	
	
	
}
