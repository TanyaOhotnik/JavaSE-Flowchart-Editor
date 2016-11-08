/**
 * 
 */
package fce.source;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Conditional block with true and false branches
 * @author Tanya Ohotnik
 *
 */
public class ConditionalBlock extends OperationBlock{
	private DiagrammComponent falseBranch;
	private DiagrammComponent trueBranch;
//	private Shape shape;
	public ConditionalBlock(){
		super();
		outArrow = new ArrayList<>(2);
	}
	public ConditionalBlock(int x,int y, int w, int h){
		super(x,y,w,h);
	}
	/**
	 * 
	 * @param trueBranch - set true branch of conditional structure
	 * @param falseBranch - set false branch of conditional structure
	 */
	public ConditionalBlock(DiagrammComponent trueBranch, DiagrammComponent falseBranch){
		this.trueBranch = trueBranch;
		this.falseBranch = falseBranch;
	}
	@Override
	public ArrayList<Shape> draw(){

		ArrayList<Shape> buf = new ArrayList<Shape>();
		buf.addAll(draw(x,y,w,h));
		if(trueBranch!=null) 
			buf.addAll(trueBranch.draw());
		if(falseBranch!=null) 
			buf.addAll(falseBranch.draw());
		return buf;
	}
	public ArrayList<Shape> draw(int x,int y,int w, int h){
		ArrayList<Shape> buf = new ArrayList<Shape>(0);
		Polygon rhombus = new Polygon();
		rhombus.addPoint(x + w/2, y);
		rhombus.addPoint(x, y + h/2);
		rhombus.addPoint(x + w/2, y + h);
		rhombus.addPoint(x + w, y + h/2);
	
//		buf.add(new  Line2D.Float(x+w/2,y,x,y+h/2));
//		buf.add(new  Line2D.Float(x+w/2,y,x+w,y+h/2));
//		buf.add(new  Line2D.Float(x,y+h/2,x+w/2,y+h));
//		buf.add(new  Line2D.Float(x+w,y+h/2,x+w/2,y+h));
		shape = rhombus;
		buf.add(rhombus);
		return buf;
	}
	/**
	 * @return the falseBranch
	 */
	public DiagrammComponent getFalseBranch() {
		return falseBranch;
	}
	/**
	 * @param falseBranch the falseBranch to set
	 */
	public void setFalseBranch(DiagrammComponent falseBranch) {
		this.falseBranch = falseBranch;
		if(falseBranch!=null)
			falseBranch.setNext(next);
	}
	/**
	 * @return the trueBranch
	 */
	public DiagrammComponent getTrueBranch() {
		return trueBranch;
	}
	/**
	 * @param trueBranch the trueBranch to set
	 */
	public void setTrueBranch(DiagrammComponent trueBranch) {
		this.trueBranch = trueBranch;
		if(trueBranch!=null)
			trueBranch.setNext(next);
	}
//	public boolean contains(Point p){
//		if(shape.contains(p)){
//			return true;	
//		}
//		else
//			return false;
//	}
	@Override
	public DiagrammComponent clone(){
		ConditionalBlock buf = new ConditionalBlock();
		buf.setText(this.getText());
		buf.next = this.next;
//		buf.shape = this.shape;
		buf.x = this.x;
		buf.y = this.y;
		buf.w = this.w;
		buf.h = this.h;
		
		buf.color = Color.black;
		buf.inArrow = this.inArrow;
		buf.outArrow = this.outArrow;
		if(buf.getOutArrow()!=null){
			for(Arrow a:outArrow){
				a.changedOutBlock = buf;
				a.updateArrow();
			}
		}
		if(buf.getInArrow()!=null){
			for(Arrow a:inArrow){
				a.changedInBlock = buf;
				a.updateArrow();
				}
		}
		//создаем форму
		buf.draw(x, y, w, h);
		if(falseBranch != null)
		buf.setFalseBranch(falseBranch.clone());
		else buf.setFalseBranch(null);
		if(trueBranch != null)
		buf.setTrueBranch(trueBranch.clone());
		else buf.setTrueBranch(null);
		return buf;
	}
	@Override
	public ArrayList<Arrow> getOutArrow() {
		if(outArrow.isEmpty()) return null;
		return outArrow;
	}
	@Override
	public void setOutArrow(Arrow arrow) {
//		if(outArrow.isEmpty()) outArrow = new ArrayList<>();
		outArrow.add(arrow);
//		updatePoints();
	}
	
	@Override
	public boolean canAddOutArrow(){
		return outArrow.size()==2?false:true;
	}
	@Override
	public void updatePoints(){
//		for(Arrow a:outArrow){
//			if(!a.points.isEmpty())a.points.remove(0);
//			a.points.add(0,new Point(x,y+h/2));
//			a.points.add(1,new Point(x-100,y+h/2));
//		}
//		for(Arrow a:inArrow){
//			a.points.remove(a.points.size()-1);
//			a.points.add(new Point(x+w/2,y));
//		}
	}
}
