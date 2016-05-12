package com.horstmann.violet.product.diagram.timing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.StringTokenizer;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.edge.IHorizontalChild;
import com.horstmann.violet.product.diagram.abstracts.edge.StatelineParent;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.node.RectangularNode;
import com.horstmann.violet.product.diagram.abstracts.node.TimingDiragramConstants;
import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;



public class State_Lifeline extends RectangularNode implements StatelineParent{

	private MultiLineString state0;
	private double width=1150;
    private double height=150;
    private StateLine s;
    private MultiLineString name;
    private MultiLineString states; 
	public State_Lifeline(){
	    s=new StateLine();
		state0=new MultiLineString();
		state0.setText("Ĭ��");
		states=new MultiLineString();
		states.setJustification(MultiLineString.LEFT);
		name=new MultiLineString();
		name.setJustification(MultiLineString.LEFT);
		name.setJustification(MultiLineString.LARGE);		
	}	
	public void setStates(MultiLineString states) {
	
		this.states = states;
	}
	@Override
	public MultiLineString getStates()
	{
		return states;
	}	
	@Override
	public Point2D getConnectionPoint(IEdge e) {
	
		if(this==e.getStart())
		{
			return this.getChild().gethorizontalChild().get(e.getBelongtoStartFlag()).getEnd();
		}
		if(this==e.getEnd())
		{
			return this.getChild().gethorizontalChild().get(e.getBelongtoEndFlag()).getEnd();
		}
		return new Point2D.Double(0,0);
	}	                   	       
    @Override  
    public Rectangle2D getBounds()
   {	   
       Point2D currentLocation = getLocation();
       double x = currentLocation.getX();
       double y = currentLocation.getY();    
       double stateheight= (states.getLabel().getNum_lines()-1 )* TimingDiragramConstants.StateSpace;
       if(stateheight+50>getHeight())//�����ӵ�״̬�߶ȳ���ԭʼ�߶ȣ���ı��������
       {     	     	  
    	  y=y-(stateheight+80-getHeight());    	
 	      this.setHeight(stateheight+80);  
 	      this.setLocation(new Point2D.Double(x,y));	      
      }    
       Rectangle2D currentBounds=new Rectangle2D.Double(x,y,getWidth(),getHeight());           
       return currentBounds;  
   }
	   public MultiLineString getName() {
		return name;
	}
	public void setName(MultiLineString name) {
		this.name = name;
	}
	public Shape getShape(){	
		
		   Rectangle2D bounds = this.getBounds();
	       GeneralPath path = new GeneralPath();
	       path.moveTo((float) bounds.getX(), (float) bounds.getY()); 
	       path.lineTo((float) (bounds.getMaxX()), (float) bounds.getY());
	       path.lineTo((float) bounds.getMaxX(), (float) bounds.getY()) ;
	       path.lineTo((float) bounds.getMaxX(), (float) bounds.getMaxY());
	       path.lineTo((float) bounds.getX(), (float) bounds.getMaxY());
	       path.closePath();
	       return path;
	   }
	//����Stateline��path·��
	public GeneralPath CreateinitLinePath(){
		  Rectangle2D bounds=this.getBounds();	
		  GeneralPath path1 = new GeneralPath();
		  //��ȡbounds���·�Y����		
		  //��ȡbounds���·�X����			  
		  Point2D p1=new Point2D.Double((double)(bounds.getX()+150), (double)(bounds.getMaxY()-TimingDiragramConstants.fisrtStateLocationY));
		  Point2D p2=new Point2D.Double((double)(bounds.getX()+width), (double)(bounds.getMaxY()-TimingDiragramConstants.fisrtStateLocationY));			 
		  if(s.gethorizontalChild().size()==0){
			  //stateline�ĳ�ʼ״̬
			 path1.moveTo(p1.getX(), p1.getY()); 
			 path1.lineTo(p2.getX(), p2.getY());		
	      }else if(s.gethorizontalChild().size()>0){ 	    	 	
	    	//���۵㲻Ϊ�յ�ʱ�򣬸����۵��뿪ʼ��ľ���w�����µ�·��
	    	int horizontalchildsize=s.gethorizontalChild().size();	    	  
	    	for(int i=0;i<horizontalchildsize;i++)
	    	{		
	    		Point2D startPoint=s.gethorizontalChild().get(i).getStart();
	    		Point2D endPoint=s.gethorizontalChild().get(i).getEnd();	    	
	    		path1.moveTo(startPoint.getX(), startPoint.getY());	    	
	    		path1.lineTo(endPoint.getX(),endPoint.getY());//�ӵ�һ�����Ƶ�
	    		path1.moveTo(endPoint.getX(),endPoint.getY());
	    		if(i+1<horizontalchildsize){
	    		Point2D nextStartPoint=s.gethorizontalChild().get(i+1).getStart();	    	
	    		path1.lineTo(nextStartPoint.getX(), nextStartPoint.getY());	
	    		}
	    	} 
	    	  }
	      return path1;	 
	}
	   public void draw(Graphics2D g2)   
	   {		  
		   int j=0;
		   int d =5;
		   g2.setColor(Color.BLACK);		  
	       Rectangle2D bounds = getBounds();	      	      
	       g2.draw(bounds);	     
	      //���û���stateline��path	
	       this.CreateinitLinePath().reset();               
	       g2.draw(this.CreateinitLinePath());   	     
	       g2.drawString(state0.getText(),(int)(bounds.getX()+3*d), (int)(bounds.getMaxY()-TimingDiragramConstants.fisrtStateLocationY));//Ĭ��״̬�Ļ��� 
	       //���������	        
	       states.drawTimingDiagram(g2, bounds); 
	       g2.drawString(name.getText(),(int)bounds.getMinX(),(int)bounds.getMaxY()-10);
	       for(double i=bounds.getX()+150;i<=bounds.getX()+width;i=i+(bounds.getWidth()-150)/20){
	    	   g2.drawLine((int)i, (int)(bounds.getY()+height), (int) i,(int)(bounds.getY()+height-d));   
	    	if(this.getFlag()){
	    	   if(j%5==0&&j<=95){
	    	    g2.drawString(String.valueOf(j), (int)i-d, (int)(bounds.getY()+height+10));
	    	   }
	    	   if(j==100){
	    		   g2.drawString(String.valueOf(j), (int)i-3*d, (int)(bounds.getY()+height+2*d));
	    	   }
	    	   j=j+d;
	       }	 
	    }  
	   	this.addchild(s);
	   for(IHorizontalChild hchild:this.getChild().gethorizontalChild())
	   {	
		   if(hchild.getCondition()!=null&&hchild.getContinuetime()!=null){
		g2.drawString(hchild.getCondition(),(int) hchild.getStart().getX(),(int) hchild.getStart().getY());
		g2.drawString(hchild.getContinuetime(),(int) hchild.getStart().getX(),
			(int) this.getBounds().getMinY()+10);				
			}
	   }
	   }
	   
   public State_Lifeline clone(){
	      State_Lifeline cloned = (State_Lifeline) super.clone();
	       cloned.state0=(MultiLineString)state0.clone();
	       cloned.states = (MultiLineString) states.clone();
	       cloned.name=(MultiLineString)name.clone();
	       s=new StateLine();
	       cloned.s = s.clone();      
	       return cloned;
	   }
	//��ȡstateline����
	public StateLine getStateLine(){
		return s;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double witdh) {
		this.width = witdh;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	@Override
	public MultiLineString getState0() {
		return state0;
	}
	public void setState0(MultiLineString state0) {
		this.state0 = state0;
	}
	
}
