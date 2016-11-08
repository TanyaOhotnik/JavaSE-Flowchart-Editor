package fce.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

import fce.source.*;
import fce.undoRedo.Caretaker;
import fce.undoRedo.Memento;

public class FlowChartEditor {

	JFrame frame;
//	DrawFigureClass drawFigure = new DrawFigureClass(DrawFigure.NONE);
	DrawFigure drawFigure = DrawFigure.NONE;
	DrawPanel panel;
//	String fileName = null;
	ButtonGroup buttonGroup = new ButtonGroup();
	BufferedImage image = null;
	DiagrammComponent clickedElement = null;
	ArrayList<Shape> bufImage = null;
	Caretaker caretaker = new Caretaker();
	FlowChart flowChart = new FlowChart("Diagramm1");
	int xStart, yStart;
	JTextArea text;
	JScrollPane scrollPane;

	
	public FlowChartEditor() {
		makeProject();
	}
	/**
	 * Create the application.
	 */
	public void makeProject() {
		initialize();
		setpanel();
		menuBar();
		toolBarHorizontal();
		toolBarVertical();
		setVisibleSetSize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Flow chart editor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setVisibleSetSize() {
		frame.setSize(400, 700);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public BufferedImage copyOnBufferedImage() {
		int maxWidth = 0, maxHeight = 0, maxX = 0, maxY = 0;
		for (DiagrammComponent c : flowChart.getList()) {
			if (maxX < c.getX()) {
				maxX = c.getX();
				maxWidth = c.getWidth();
			}
			if (maxY < c.getY()) {
				maxY = c.getY();
				maxHeight = c.getHeight();
			}
		}
		image = new BufferedImage(maxX + maxWidth + 30, maxY + maxHeight + 30, BufferedImage.TYPE_INT_ARGB);
		panel.repaint();
		return image;
	}

	public void setpanel() {
		panel = new DrawPanel(this);
		panel.setBackground(Color.white);
		text = new JTextArea();
		text.setVisible(true);
		panel.add(text);
		PanelMouseListener ml = new PanelMouseListener(this);
		panel.addMouseListener(ml);
		panel.addMouseMotionListener(ml);
		panel.setPreferredSize(new Dimension(1500, 4000));
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.pack();
		// JOptionPane.showMessageDialog(frame,frame.getSize().width);
	}

	public void menuBar() {

		MenuBar menuBar = new MenuBar(this);
		frame.setJMenuBar(menuBar);

	}

	public void toolBarHorizontal() {
		HorizontalToolbar toolbar = new HorizontalToolbar(this);
		frame.getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	public void toolBarVertical() {
		VerticalToolbar toolbar = new VerticalToolbar(this);
		frame.getContentPane().add(toolbar, BorderLayout.WEST);
	}

	public DiagrammComponent findClickedElement(Point p) {
		ArrayList<DiagrammComponent> a = new ArrayList<DiagrammComponent>();
		a = flowChart.getList();
		int i = 0;
		flowChart.setColor(Color.black);
		for (DiagrammComponent dc : a) {
			if (dc.getElement(i).contains(new Point(xStart, yStart))) {
				return dc.getElement(i);
			}
			i++;
		}
		return null;
		// JOptionPane.showMessageDialog(frame, "find");
	}

	public void undo() {

		flowChart = caretaker.getUndoFlowChart().clone();
		clickedElement = null;
		panel.repaint();
	}

	public void redo() {
		flowChart = caretaker.getRedoFlowChart();
		clickedElement = null;
		panel.repaint();
	}

	public void delete() {
		DiagrammComponent del = findClickedElement(new Point(xStart, yStart));
		ArrayList<DiagrammComponent> list = flowChart.getList();
		text.setVisible(false);
		text.setText("");
		for (DiagrammComponent i : list)
			if (del == i) {
				caretaker.add(new Memento(flowChart.clone()));
//				del.getInArrow().clean();
				flowChart.remove(del);
				caretaker.add(new Memento(flowChart.clone()));
				break;
			}
		panel.repaint();
	}

}
