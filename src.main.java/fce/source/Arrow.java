package fce.source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Arrow implements DiagrammComponent {
	private String text = "";
	public DiagrammComponent outBlock, inBlock;
	public DiagrammComponent changedOutBlock, changedInBlock;
	Shape shape;
	Color color = Color.BLACK;
	ArrayList<Point> points = new ArrayList<Point>(2);
	int x,y;
	boolean flag = true;
	public Arrow(){
		
	}
	
//	public Arrow( int x1,int y1,int x2, int y2){		
		
//		points.add(new Point(x1,y1));
//		points.add(new Point(x2,y2));
//		points.add(new Point(x2-5,y2-10));
//		points.add(new Point(x2+5,y2+10));
//		points.add(new Point(x2,y2));
//		shape = draw().get(0);
//	}

	@Override
	public ArrayList<Shape> draw(){
//		updatePoints();
		ArrayList<Shape> buf = new ArrayList<Shape>(1);
		Polygon arrow = new Polygon();
//		Shape s = new Line2D.Float();
		for(int i = 0;i<points.size();i++){
		arrow.addPoint((int)points.get(i).getX(),(int)points.get(i).getY());
		}
		shape = arrow;
		buf.add(arrow);
		return buf;
	}

	@Override
	public boolean add(DiagrammComponent el) {
		return false;
	}
	
	@Override
	public boolean remove(DiagrammComponent el) {
		return false;
	}
	@Override
	public void setText(String textValue) {
		this.text = textValue;
	}
	@Override
	public String getText() {
		return text;
	}

	public void setNext(DiagrammComponent next) {
//		this.next = next;
	}

	public DiagrammComponent getNext() {
		return null;
	}

	@Override
	public DiagrammComponent getElement(int index) {
		return this;
	}

	@Override
	public int getStringX() {
		if(Math.abs(inBlock.getX()-outBlock.getX())<40)
			return (int)points.get(0).getX()+10;
		if(inBlock.getX()>=outBlock.getX())
			return (int)points.get(0).getX()+50;
		else 
			return (int)points.get(0).getX()-50;
	}

	@Override
	public int getStringY() {
		int height = (int)(points.get(1).getY()- points.get(0).getY())/2;
		return (int)points.get(0).getY()+height;
	}

	@Override
	public boolean contains(Point p) {
//		int a,b, pX = (int)p.getX(),pY = (int)p.getY();
//		for(int i=0;i<points.size()-3;i++){
//			a = (int) points.get(i).getX();
//			b = (int) points.get(i).getY();
//			if(pX>=a-5 && pX<=a+5 && pY<=points.get(i+1).getY()){
//				return true;
//			}
//			}
//		Shape line = new Line
		Polygon polygon = new Polygon();
		int startX = (int)points.get(0).getX(), startY = (int)points.get(0).getY();
		polygon.addPoint(startX-5, startY-5);
		polygon.addPoint(startX+5, startY+5);
		int a,b;//, pX = (int)p.getX(),pY = (int)p.getY();
//		for(int i=1;i<points.size()-3;i++){
//		for(int i=1;i<points.size()-1;i++){
//			polygon.addPoint(pX, pY);
			a = (int) points.get(1).getX();
			b = (int) points.get(1).getY();
			polygon.addPoint(a+5, b+5);
			polygon.addPoint(a-5, b-5);
			
//		}
		polygon.addPoint(startX-5, startY-5);
//		Graphics g = new ;
		
		
		return polygon.contains(p);
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getX() {
		if(Math.abs(inBlock.getX()-outBlock.getX())<40)
			return (int)points.get(0).getX()-10;
		if(inBlock.getX()>=outBlock.getX())
			return (int)points.get(0).getX()+40;
		else 
			return (int)points.get(0).getX()-60;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int)points.get(0).getY();
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Shape getShape() {
		shape = draw().get(0);
		return shape;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 40;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 40;
	}
	public void addPoint(int x, int y){
		points.add(new Point(x,y));
		if(flag && points.size()>1) {
			//при добавлении 2й точки
//			points.add(new Point(x-15,y-5));
//			points.add(new Point(x,y));
//			points.add(new Point(x-15,y+5));
//			points.add(new Point(x,y));
			points.add(new Point(x+5,y-10));
			points.add(new Point(x,y));
			points.add(new Point(x-5,y-10));
			points.add(new Point(x,y));
			flag = false;
		}
	}
	
	@Override
	public DiagrammComponent clone(){
		Arrow buf = new Arrow();
//		updatePoints();
		buf.setText(this.getText());
		buf.x = this.x;
		buf.y = this.y;
		//создаем форму
		buf.inBlock = this.changedInBlock;
		buf.outBlock = this.changedOutBlock;
//		if(changedOutBlock!=null)
//			buf.outBlock2
		changedInBlock = null;
		changedOutBlock = null;
//		((Figure)buf.inBlock).inArrow = new ArrayList<>(1);
		
//		((Figure)buf.outBlock).outArrow = new ArrayList<>(1);
		buf.outBlock.setOutArrow(buf);
		ConditionalBlock cb = new ConditionalBlock();
		if( this.outBlock.getOutArrow().size()==2 )
			buf.outBlock.setOutArrow(this.outBlock.getOutArrow().get(0));
		buf.inBlock.setInArrow(buf);
		if( this.inBlock.getInArrow().size()==2 )
			buf.inBlock.setInArrow(this.inBlock.getInArrow().get(0));
		for(Point p:points)
		buf.points.add(new Point((int)p.getX(), (int)p.getY()));
//		buf.updatePoints();
		buf.draw();
		buf.color = this.color;
		return buf;
	}

	
	private void updatePoints(){
		int x1 = outBlock.getX()+outBlock.getWidth()/2,
				y1 = outBlock.getY()+outBlock.getHeight();
		points.remove(0);
		points.add(0,new Point(x1, y1));
		x1 = inBlock.getX()+inBlock.getWidth()/2;
		y1 = inBlock.getY();
		ArrayList<Point> p = updateEnd(outBlock.getX()+outBlock.getWidth()/2, outBlock.getY()+outBlock.getHeight(),
				x1, y1);
		points.addAll(p);
//		points.remove(points.size()-1);
//		points.add(new Point(x1, y1));
//		points.remove(index);
//		points.remove(points.size()-2);
//		points.add(points.size()-1,new Point(x1-3, y1-3));
//		points.remove(points.size()-3);
//		points.add(points.size()-2,new Point(x1, y1));
//		points.remove(points.size()-4);
//		points.add(points.size()-3,new Point(x1+3, y1-3));
//		points.remove(points.size()-5);
//		points.add(points.size()-4,new Point(x1, y1));
//		int lengtha = (int) Math.sqrt(Math.pow(outBlock.getX()-inBlock.getX(), 2) +	Math.pow(outBlock.getY()-inBlock.getY(), 2));
//		int lengthb = (int) Math.sqrt(Math.pow(outBlock.getX()-inBlock.getX(), 2) +	Math.pow(outBlock.getY()-inBlock.getY(), 2));

		
	}
//	public void updateArrows(int pos, int x,int y){
//		if(pos==0){
//			//вых стрелка
//		points.remove(0);
//		points.add(0,new Point(x, y));
//		points.add(1,new Point(x, y));
//		}
//	}
	private ArrayList<Point> updateEnd(int x1,int y1, int x2, int y2){
		int dx = x2-x1, dy = y2-y1, d = 10, h = 6;
		double D = Math.sqrt(dx*dx+dy*dy); 
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy/D, cos = dx/D;
		x = xm * cos - ym * sin +x1;
		ym = xm*sin + ym*cos +y1;
		xm = x;
		x = xn * cos - yn*sin +x1;
		yn = xn*sin +yn*cos+y1;
		xn = x;
		for(int i=1;i<=4;i++)
		points.remove(points.size()-1);
		ArrayList<Point> pointsm = new ArrayList<Point>();
		pointsm.add( new Point(x2,y2));
		pointsm.add(new Point((int)xm,(int)ym));
//		pointsm.add( new Point(x2,y2));
		pointsm.add(new Point((int)xn,(int)yn));
		pointsm.add( new Point(x2,y2));
		return pointsm;
	}
	public void setOutBlock(DiagrammComponent outBlock) {

		this.outBlock = outBlock;	
		int x = outBlock.getX()+outBlock.getWidth()/2,
		y = outBlock.getY()+outBlock.getHeight();
		points.add(new Point(x, y));
	
		outBlock.setOutArrow(this);
	}
	public void setInBlock(DiagrammComponent inBlock) {
//		if(inBlock.getInArrow()!=null) return;
		this.inBlock = inBlock;
		int x = inBlock.getX()+inBlock.getWidth()/2,
				y = inBlock.getY();
		points.add(new Point(x, y));
		points.add(new Point(x+3,y-3));
//		points.add(new Point(x,y));
		points.add(new Point(x-3,y-3));
		points.add(new Point(x,y));
		inBlock.setInArrow(this);
	}

	public DiagrammComponent getOutBlock() {
		return outBlock;
	}

	public DiagrammComponent getInBlock() {
		return inBlock;
	}

	public void updateArrow() {
		// TODO Auto-generated method stub
		updatePoints();
	}

	

	@Override
	public ArrayList<Arrow> getInArrow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInArrow(Arrow arrow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Arrow> getOutArrow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutArrow(Arrow arrow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canAddInArrow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAddOutArrow() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}