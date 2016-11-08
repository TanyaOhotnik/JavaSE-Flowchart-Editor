package fce.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
/**
 * Class which extend standard Swing element JToolBar and
 * set needed button, icons, action listeners
 * @author Tanya Ohotnik
 */
public class VerticalToolbar extends JToolBar {
	FlowChartEditor main;
	/**
	 * Class constructor which set toolbar orientation
	 * and call method to create toolbar
	 * @param main 
	 */
	public VerticalToolbar(FlowChartEditor main) {
		super("ToolbarVertical", JToolBar.VERTICAL);
		this.main = main;
		make();
	}
	/**
	 * Method which set needed button, icons, action listeners
	 */
	public void make() {
//		JToolBar toolbar = new JToolBar();

		this.setFloatable(false);

		JLabel fc = new JLabel("Элементы блок-схемы:");

		// fc.setSize(100, 20);
		// fc.setEditable(false);
		this.add(fc);
		this.addSeparator();
		JToggleButton ellipseButton = new JToggleButton(new ImageIcon("images/ellipse.png"));
		ellipseButton.setToolTipText("Блок начала/конца");

		ellipseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				main.drawFigure.setDrawFigure(DrawFigure.ELLIPSE);
				main.drawFigure = DrawFigure.ELLIPSE;
			}
		});

		JToggleButton parallelogramButton = new JToggleButton(new ImageIcon("images/parallelogram.png"));
		parallelogramButton.setToolTipText("Блок ввода/вывода");

		parallelogramButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

//				main.drawFigure.setDrawFigure(DrawFigure.PARALLELOGRAM);
				main.drawFigure = DrawFigure.PARALLELOGRAM;
			}
		});

		JToggleButton rectangleButton = new JToggleButton(new ImageIcon("images/rectangle.png"));
		rectangleButton.setToolTipText("Операторный блок");
		rectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				main.drawFigure.setDrawFigure(DrawFigure.RECTANGLE);
				main.drawFigure = DrawFigure.RECTANGLE;
				// JOptionPane.showMessageDialog(frame, "rectangle click.");

			}
		});

		JToggleButton rhombusButton = new JToggleButton(new ImageIcon("images/rhombus.png"));
		rhombusButton.setToolTipText("Условный блок");
		rhombusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				main.drawFigure.setDrawFigure(DrawFigure.RHOMBUS);
				main.drawFigure = DrawFigure.RHOMBUS;
			}
		});
		
		JToggleButton arrowButton = new JToggleButton(new ImageIcon("images/association.png"));
		arrowButton.setToolTipText("Стрелка");
		arrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				main.drawFigure.setDrawFigure(DrawFigure.ARROW);
				main.drawFigure = DrawFigure.ARROW;
			}
		});

		main.buttonGroup.add(ellipseButton);
		main.buttonGroup.add(rectangleButton);
		main.buttonGroup.add(rhombusButton);
		main.buttonGroup.add(parallelogramButton);
		main.buttonGroup.add(arrowButton);
		this.add(ellipseButton);
		this.add(parallelogramButton);
		this.add(rectangleButton);
		this.add(rhombusButton);
		this.add(arrowButton);
		
		

	}
}
