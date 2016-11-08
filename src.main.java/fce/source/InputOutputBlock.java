/**
 * 
 */
package fce.source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Conditional block with true and false branches
 * @author Tanya Ohotnik
 *
 */
public class InputOutputBlock extends Figure implements DiagrammComponent {
	private String text = "";
	protected DiagrammComponent next = null;
	
	
	public InputOutputBlock(){
		
	}
	
	public InputOutputBlock( int x,int y,int w, int h){
		super(x,y,w,h);
		shape = draw(x,y,w,h).get(0);
	}
	
	
	@Override
	public ArrayList<Shape> draw(){
	return draw(x,y,w,h);
	}

	public ArrayList<Shape> draw(int x,int y,int w, int h) {
		ArrayList<Shape> buf = new ArrayList<Shape>(0);
		Polygon parallelogram = new Polygon();
		parallelogram.addPoint(x + h/4, y);
		parallelogram.addPoint(x+h/4+w, y);
		parallelogram.addPoint(x + w, y + h);
		parallelogram.addPoint(x, y + h);
		shape = parallelogram;
		buf.add(parallelogram);
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
		this.next = next;
	}

	public DiagrammComponent getNext() {
		return next;
	}

	@Override
	public DiagrammComponent getElement(int index) {
		return this;
	}

	@Override
	public int getStringX() {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		int width = (int)(font.getStringBounds(text, frc).getWidth());
		return x + (w - width)/2+h/8;
	}

	@Override
	public int getStringY() {
		return y + h/2 + 5;
	}
	@Override
	public DiagrammComponent clone(){
		InputOutputBlock buf = new InputOutputBlock();
		buf.setText(this.getText());
		buf.next = this.next;
		buf.x = this.x;
		buf.y = this.y;
		buf.w = this.w;
		buf.h = this.h;
		
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
		buf.color = Color.black;
		return buf;
	}
	@Override
	public void updatePoints(){
//		for(Arrow a:outArrow){
//			if(!a.points.isEmpty())a.points.remove(0);
//			a.points.add(0,new Point(x+w/2+h/4,y+h));
//		}
//		for(Arrow a:inArrow){
//			a.points.remove(a.points.size()-1);
//			a.points.add(new Point(x+w/2+h/4,y));
//		}
	}
	
}
