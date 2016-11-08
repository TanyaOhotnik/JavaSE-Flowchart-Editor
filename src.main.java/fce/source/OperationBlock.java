/**
 * 
 */
package fce.source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Primitive block
 * @author Tanya Ohotnik
 *
 */
public class OperationBlock  extends Figure implements DiagrammComponent {
		private String text = "";
		protected DiagrammComponent next = null;
		
		public OperationBlock(){
			
		}
		
		public OperationBlock(  int x,int y,int w, int h){
			super(x,y,w,h);		
			shape = draw(x,y,w,h).get(0);
		}
				
		@Override
		public ArrayList<Shape> draw(){
		return draw(x,y,w,h);
		}

		public ArrayList<Shape> draw(int x,int y,int w, int h) {
			ArrayList<Shape> buf = new ArrayList<Shape>(1);
			buf.add(new Rectangle2D.Float(x,y,w,h));
			shape = buf.get(0);
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
			return x + (w - width)/2;
		}

		@Override
		public int getStringY() {
			return y + h/2 + 5;
		}
		@Override
		public DiagrammComponent clone(){
			OperationBlock buf = new OperationBlock();
			buf.setText(this.getText());
			buf.next = this.next;
//			buf.shape = this.shape;
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
	}


