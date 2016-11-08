package fce.source;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

public abstract  class EmptyMethods implements DiagrammComponent{

	 
	public void setNext(DiagrammComponent value) {
		// TODO Auto-generated method stub
		
	}

	 
	public DiagrammComponent getNext() {
		// TODO Auto-generated method stub
		return null;
	}



	 
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	 
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	 
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void setText(String textValue) {
		// TODO Auto-generated method stub
		
	}

	 
	public int getStringX() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int getStringY() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	public ArrayList<Arrow> getInArrow() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setInArrow(Arrow arrow) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Arrow> getOutArrow() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setOutArrow(Arrow arrow) {
		// TODO Auto-generated method stub
		
	}
	public boolean canAddInArrow(){
		return false;
	}
	public boolean canAddOutArrow(){
		return false;
	}
	public abstract DiagrammComponent clone();
}
