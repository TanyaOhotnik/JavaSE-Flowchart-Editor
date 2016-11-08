package fce.GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import fce.source.*;
import fce.undoRedo.Memento;

public class PanelMouseListener extends MouseAdapter {

//	DrawPanel panel;
	private boolean isClickedElement = false;
	// private DiagrammComponent clickedElement;
	private boolean pressed;
	private int xMove;
	private int yMove;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private boolean flag = true;
	private FlowChartEditor main;
	private DiagrammComponent bufferedText = null;
	Arrow arrow = null;
	private boolean textWasSaved = true;
	private boolean wasDragged = false;
	public PanelMouseListener(FlowChartEditor main) {
//		this.panel = main.panel;
		this.main = main;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		main.flowChart.setColor(Color.black);
		wasDragged = true;
		main.text.setVisible(false);
		main.text.setText("");
		if (main.clickedElement!=null) {
			//!!!!!!!! crash sometimes
			main.clickedElement.setX(xMove + e.getX());
			main.clickedElement.setY(yMove + e.getY());
//			main.clickedElement.ar
			pressed = false;
			main.panel.repaint();
			return;
		}
		isClickedElement = false;
//		switch (main.drawFigure.getDrawFigure()) {
		switch (main.drawFigure) {
		case RECTANGLE:
			OperationBlock ob = new OperationBlock(xStart, yStart, Math.abs(xStart - e.getX()),
					Math.abs(yStart - e.getY()));
			main.bufImage = ob.draw();
			break;
		case PARALLELOGRAM:
			InputOutputBlock iob = new InputOutputBlock(xStart, yStart, Math.abs(xStart - e.getX()),
					Math.abs(yStart - e.getY()));
			main.bufImage = iob.draw();
			break;
		case ELLIPSE:
			StartEndBlock seb = new StartEndBlock(xStart, yStart, Math.abs(xStart - e.getX()),
					Math.abs(yStart - e.getY()));
			main.bufImage = seb.draw();
			break;
		case RHOMBUS:
			ConditionalBlock cb = new ConditionalBlock(xStart, yStart, Math.abs(xStart - e.getX()),
					Math.abs(yStart - e.getY()));
			main.bufImage = cb.draw();
			break;
		default:
			break;
		}
		main.panel.repaint();
	}


	

	public void drawText() {
//		main.flowChart.setColor(Color.black);
		main.clickedElement = findClickedElement(new Point(xStart, yStart));
		
		if (main.clickedElement == null) {
			return;
		}
		textWasSaved = false;
		main.text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					main.text.setVisible(false);
					if (bufferedText != null){
						bufferedText.setText(main.text.getText());
					bufferedText = null;
					main.text.setText("");
					main.panel.repaint();
					textWasSaved = true;
					main.flowChart.setColor(Color.black);
					createMemento();
					
					}
				}
			}
		});

//		main.clickedElement.setColor(Color.black);
		main.text.setOpaque(false);
		main.text.setVisible(true);
		main.text.setText(main.clickedElement.getText());
		main.clickedElement.setText("");
		main.text.setEditable(true);
		main.text.setBounds(main.clickedElement.getX() + 10, main.clickedElement.getStringY() - 15,
				main.clickedElement.getWidth() - 20, 20);
		Border border = BorderFactory.createDashedBorder(Color.gray, 1, 1);
		main.text.setBorder(border);
		main.text.setVisible(true);
		main.panel.repaint();
	
	}

	public void mouseClicked(MouseEvent e) {
//		 JOptionPane.showMessageDialog(main.frame, e.getComponent());
		xStart = e.getX();
		yStart = e.getY();
		wasDragged = false;
//		isClickedElement = false;
		DiagrammComponent dc = findClickedElement(new Point(xStart,yStart));
		main.flowChart.setColor(Color.black);
		if(dc!=null) {
			dc.setColor(Color.red);
		}
		main.panel.repaint();
//		else main.flowChart.setColor(Color.black);
		if(!textWasSaved && bufferedText!=null){
			main.text.setVisible(false);
			bufferedText.setText(main.text.getText());
			main.text.setText("");
			bufferedText = null;
			textWasSaved = true;
			createMemento();
			main.panel.repaint();
			return;
		}
		if (e.getClickCount() == 2) {
			drawText();
			return;
		}
		bufferedText = null;
//		if (main.drawFigure.getDrawFigure() == DrawFigure.ARROW) {
		if (main.drawFigure == DrawFigure.ARROW) {
			if(findClickedElement(new Point(xStart,yStart))==null){
				flag = true;
//				main.drawFigure.setDrawFigure(DrawFigure.NONE);
				main.drawFigure = DrawFigure.NONE;

				arrow=null;
//				main.flowChart.setColor(Color.black);
//				panel.repaint();
				return;
			}
			if (flag) { 
				arrow = new Arrow();
				arrow.changedOutBlock = findClickedElement(new Point(xStart, yStart));
//				arrow.setOutBlock(findClickedElement(new Point(xStart, yStart)));
				
				flag = false;
			} else {
				
				arrow.changedInBlock = findClickedElement(new Point(xStart, yStart));
				flag = true;
				main.flowChart.setColor(Color.black);
//				if(arrow.changedInBlock==arrow.changedOutBlock){
//						arrow = null;
////						main.drawFigure.setDrawFigure(DrawFigure.NONE);
//						main.drawFigure = DrawFigure.NONE;
//
//						return;
//					}
				if(arrow.changedInBlock!=null && arrow.changedOutBlock!=null){
					
					if(arrow.changedInBlock.canAddInArrow() && arrow.changedOutBlock.canAddOutArrow()){
						arrow.setInBlock(arrow.changedInBlock);
						arrow.setOutBlock(arrow.changedOutBlock);
						arrow.changedInBlock = null;
						arrow.changedOutBlock = null;
						main.flowChart.add(arrow);		
					}
				}
					
				arrow = null;
			}
			
		}
//		if(main.drawFigure.getDrawFigure()!=DrawFigure.NONE && arrow==null){
		if(main.drawFigure!=DrawFigure.NONE && arrow==null){

		findChoosedFigure(xStart, yStart, 100, 40);
		createMemento();
		}
		if(arrow == null)
			main.drawFigure = DrawFigure.NONE;
//			main.drawFigure.setDrawFigure(DrawFigure.NONE);
		pressed = false;
		
		main.panel.repaint();
		
//		
	}

	public void mousePressed(MouseEvent e) {
		// JOptionPane.showMessageDialog(frame, "pressed");
		main.buttonGroup.clearSelection();
		main.xStart = e.getX();
		main.yStart = e.getY();
		xStart = e.getX();
		yStart = e.getY();
		Point p = e.getPoint();
		if (findClickedElement(p) != null) {
			isClickedElement = true;
			main.clickedElement = findClickedElement(p);
			xMove = main.clickedElement.getX() - xStart;
			yMove = main.clickedElement.getY() - yStart;
			return;
		}
		pressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		isClickedElement = false;
		main.clickedElement = null;
		if (pressed == true) {

			xEnd = e.getX();
			yEnd = e.getY();
			if (Math.abs(xStart - xEnd) == 0 || Math.abs(yStart - yEnd) == 0)
				return;
			findChoosedFigure(xStart, yStart, Math.abs(xStart - xEnd), Math.abs(yStart - yEnd));

			pressed = false;
//			main.drawFigure.setDrawFigure(DrawFigure.NONE);
			main.drawFigure=DrawFigure.NONE;
			main.panel.repaint();
//			createMemento();
			
	   
		}
		
		main.bufImage = null;
		xEnd = 0;
		yEnd = 0;
		if(wasDragged) {
			createMemento();
			wasDragged = false;
		}
	}


	public DiagrammComponent findClickedElement(Point p) {
		ArrayList<DiagrammComponent> a = new ArrayList<DiagrammComponent>();
		a = main.flowChart.getList();
		int i = 0;
		for (DiagrammComponent dc : a) {
			if (dc.getElement(i).contains(p)) {
				
				// чтобы не перезаписать редактируемый елемент
				if (bufferedText == null)
					bufferedText = dc.getElement(i);
				isClickedElement = true;
				return dc.getElement(i);
			}
			i++;
		}
		isClickedElement = true;
		return null;
		// JOptionPane.showMessageDialog(frame, "find");
	}

	public void findChoosedFigure(int x1, int y1, int x2, int y2) {
//		switch (main.drawFigure.getDrawFigure()) {
		switch (main.drawFigure) {
		case RECTANGLE:
			OperationBlock ob = new OperationBlock(x1, y1, x2, y2);
			// imageArrays.addAll(ob.draw());
			main.flowChart.add(ob);
			break;
		case PARALLELOGRAM:
			InputOutputBlock iob = new InputOutputBlock(x1, y1, x2, y2);
			// imageArrays.addAll(iob.draw());
			main.flowChart.add(iob);
			// JOptionPane.showMessageDialog(frame, drawFigure);
			break;
		case ELLIPSE:
			StartEndBlock seb = new StartEndBlock(x1, y1, x2, y2);
			// imageArrays.addAll(seb.draw());
			main.flowChart.add(seb);
			break;
		case RHOMBUS:
			ConditionalBlock cb = new ConditionalBlock(x1, y1, x2, y2);
			// imageArrays.addAll(cb.draw());
			main.flowChart.add(cb);
			break;
		case TEXT:
			// устанавливаем фокус для панели,
			// чтобы печатать на ней текст
			drawText();
			break;
//		case ARROW:
//			Arrow a = new Arrow(x1, y1, x2, y2);
//			// imageArrays.addAll(cb.draw());
//			flowChart.add(a);
		default:
			;
		}
	}
	public void createMemento(){
		main.caretaker.add(new Memento(main.flowChart.clone()));
	}
}
