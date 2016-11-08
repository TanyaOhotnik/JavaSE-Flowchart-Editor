/**
 * 
 */
package fce.source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Block structure, which consist of some other blocks
 * 
 * @author Tanya Ohotnik
 *
 */
public class CompositeBlock extends EmptyMethods implements DiagrammComponent {
	ArrayList<DiagrammComponent> elements = new ArrayList<DiagrammComponent>(5);

	public CompositeBlock() {

	}
	// public CompositeBlock(String text){
	// super(text);
	// }

	@Override
	public ArrayList<Shape> draw() {
		ArrayList<Shape> s = new ArrayList<Shape>();
		for (DiagrammComponent i : elements)
			s.addAll(i.draw());
		return s;
	}

	//
	// public ArrayList<Figure> getFigure() {
	// ArrayList<Figure> s = new ArrayList<Figure>();
	// for(DiagrammComponent j:elements)
	// if(j.getClass()!=this.getClass())
	// s.addAll(j.getFigure());
	// else
	// s.add(j.getFigure());
	// return null;
	// }
	@Override
	public boolean add(DiagrammComponent el) {
		boolean fl = elements.add(el);
		if (!fl)
			return false;
		// if(elements.size()>1)
		// (elements.get(elements.size()-2)).setNext(el);
		return true;
	}

	@Override
	public boolean remove(DiagrammComponent el) {
		int ind = elements.indexOf(el);
		Arrow arrow = new Arrow();
		if (el.getClass() == arrow.getClass()) {
			((Arrow) el).inBlock.getInArrow().remove(el);
			((Arrow) el).outBlock.getOutArrow().remove(el);
		}
		boolean fl = elements.remove(el);
		//delete all arrows
		if (el.getInArrow() != null)
			for (Arrow a : el.getInArrow()) {
				elements.remove(a);
			}
		if (el.getOutArrow() != null)
			for (Arrow a : el.getOutArrow()) {
				elements.remove(a);
			}
		if (!fl)
			return false;
		if (ind == elements.size())
			return true;
		// if(elements.size()>1 && ind!=0)
		// (elements.get(ind-1)).setNext(elements.get(ind+1));
		return true;
	}

	@Override
	public DiagrammComponent getElement(int index) {
		CompositeBlock c = new CompositeBlock();
		if (elements.get(index).getClass() == c.getClass()) {
			DiagrammComponent comp = elements.get(index);
			comp.getElement(index);
		} else
			return elements.get(index);
		return null;
	}
	// @Override
	// public boolean contains(Point p){
	// return false;
	// }
	// @Override
	// public void setX(int x){
	//
	// }
	// @Override
	// public void setY(int y){
	//
	// }
	// @Override
	// public int getX(){
	// return -1;
	// }
	// @Override
	// public int getY(){
	// return -1;
	// }
	//
	// @Override
	// public void setNext(DiagrammComponent value) {
	// // TODO Auto-generated method stub
	//
	// }
	// @Override
	// public void setText(String textValue) {
	//
	// }
	// @Override
	// public String getText() {
	// return null;
	// }
	// @Override
	// public DiagrammComponent getNext() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return elements.get(0).getColor();
	}

	@Override
	public void setColor(Color color) {
		for (DiagrammComponent component : elements)
			component.setColor(Color.black);
	}

	// @Override
	// public Shape getShape() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int getStringX() {
	// return 0;
	// }
	//
	// @Override
	// public int getStringY() {
	// return 0;
	// }
	// @Override
	// public int getWidth() {
	// return 0;
	// }
	//
	// @Override
	// public int getHeight() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	@Override
	public DiagrammComponent clone() {
		CompositeBlock buf = new CompositeBlock();
		for (DiagrammComponent i : elements)
			buf.add(i.clone());
		return buf;
	}

	// public void updateArrows(){
	// for(DiagrammComponent i:elements)
	//
	// }

}
