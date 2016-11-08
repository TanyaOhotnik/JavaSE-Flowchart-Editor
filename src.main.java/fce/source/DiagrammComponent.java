/**
 * 
 */
package fce.source;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Interface, which realize target of Composite pattern
 * @author Tanya Ohotnik
 *
 */
public interface DiagrammComponent {
    

	/**
	 * Method, which drawing shapes of flow chart or block
	 */
	ArrayList<Shape> draw();
	/**
	 * set next element
	 * @param value
	 */
	void setNext(DiagrammComponent value);
	/**
	 * 
	 * @return next element 
	 */
	DiagrammComponent getNext();
	/**
	 * 
	 * @param el - element, which we want to add in our list of blocks
	 * @return true if operation was successful
	 */
	boolean add(DiagrammComponent el);
	/**
	 * 
	 * @param el - element, which we want to remove from our list of blocks
	 * @return true if operation was successful
	 */
	boolean remove(DiagrammComponent el);
	/**
	 * 
	 * @param index of required element
	 * @return required element
	 */
	DiagrammComponent getElement(int index);
	/**
	 * 
	 * @param clicked point
	 * @return true - if element contains p point
	 */
	boolean contains(Point p);
	/**
	 * set x coordinate
	 * @param x - x coordinate of shape
	 */
	void setX(int x);
	/**
	 * set y coordinate
	 * @param y - y coordinate of shape
	 */
	void setY(int y);
	/**
	 * 
	 * @return x coordinate of shape
	 */
	int getX();
	/**
	 * 
	 * @return y - y coordinate of shape
	 */
	int getY();
	/**
	 * 
	 * @return shape color
	 */
	Color getColor();
	/**
	 * 
	 * @param color - color which will set to shape
	 */
	void setColor(Color color);
	/**
	 * 
	 * @return shape - graphic representation of element
	 */
	Shape getShape();
	/**
	 * 
	 * @return text of block
	 */
	String getText();
	/**
	 * 
	 * @param textValue - text which will installed in element 
	 */
	void setText(String textValue);
	/**
	 * 
	 * @return x coordinate of string in center of block
	 */
	int getStringX();
	/**
	 * 
	 * @return y coordinate of string in center of block
	 */
	int getStringY();
	/**
	 * 
	 * @return width of shape
	 */
	int getWidth();
	/**
	 * 
	 * @return height of shape
	 */
	int getHeight();
	/**
	 * 
	 * @return independent cloned element
	 */
	DiagrammComponent clone();
	/**
	 * 
	 * @return list of in arrows
	 */
	public ArrayList<Arrow> getInArrow();
	/**
	 * Add arrow in list
	 * @param arrow 
	 */
	public void setInArrow(Arrow arrow);
	/** 
	 * @return list of out arrows
	 */
	public ArrayList<Arrow> getOutArrow();
	/**
	 * Add arrow in list
	 * @param arrow 
	 */
	public void setOutArrow(Arrow arrow);
	public boolean canAddInArrow();
	public boolean canAddOutArrow();
}
