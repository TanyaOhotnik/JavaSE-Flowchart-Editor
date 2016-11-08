package fce.source;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

public abstract class Figure implements DiagrammComponent {
	protected Shape shape;
	protected Color color = Color.black;
	public ArrayList<Arrow> inArrow = new ArrayList<Arrow>(2);
	public ArrayList<Arrow> outArrow = new ArrayList<Arrow>(2);
	protected int x, y, w, h;
	

	public Figure() {
		inArrow = new ArrayList<>(2);
	}
	
	public Figure(int x, int y, int w, int h) {
		inArrow = new ArrayList<>(2);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void setX(int x) {
		this.x = x;
		updateShape();
		updateArrows();
	}

	public void setY(int y) {
		this.y = y;
		updateShape();
		updateArrows();
	}

	private void updateShape() {
		
	}
	private void updateArrows(){
		for(Arrow a:outArrow)
		if(a!=null)
			a.updateArrow();
		for(Arrow a:inArrow)
			if(a!=null)
				a.updateArrow();
//		updatePoints();
	}
	public void updatePoints(){
		for(Arrow a:outArrow){
			if(!a.points.isEmpty())a.points.remove(0);
			a.points.add(0,new Point(x+w/2,y+h));
		}
		for(Arrow a:inArrow){
			a.points.remove(a.points.size()-1);
			a.points.add(new Point(x+w/2,y));
		}
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean contains(Point p){
		if(shape.contains(p)){
			color = Color.red;
			return true;	
			
		}
		else
			return false;
	}
	public Shape getShape(){
		return shape;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight(){
		return h;
	}

	public ArrayList<Arrow> getInArrow() {
		if(inArrow.isEmpty()) return null;
		return inArrow;
	}


	public void setInArrow(Arrow arrow) {
//		inArrow = new ArrayList<>(1);
		inArrow.add(arrow);
//		updateArrows();
	}
	public void setOutArrow(Arrow arrow) {
		outArrow.add(0,arrow);
//		updateArrows();
	}
	public ArrayList<Arrow> getOutArrow() {
		if(outArrow.isEmpty()) return null;
		return outArrow;
	}
	public boolean canAddInArrow(){
		return inArrow.size()==2?false:true;
	}
	public boolean canAddOutArrow(){
		return outArrow.size()==1?false:true;
	}
//	public void clearArrow(){
//		this.setInArrow(null);
//		this.setOutArrow(null);
//	}
	public abstract DiagrammComponent clone();
}
