package fce.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
/**
 * Class which extend standard Swing element JToolBar and
 * set needed button, icons, action listeners
 * @author Tanya Ohotnik
 */
public class HorizontalToolbar extends JToolBar{

	FlowChartEditor main;
	/**
	 * Class constructor which set toolbar orientation
	 * and call method to create toolbar
	 * @param main 
	 */
	public HorizontalToolbar(FlowChartEditor main) {
		super("ToolbarHorizontal", JToolBar.HORIZONTAL);
		this.main = main;
		make();
	}
	/**
	 * Method which set needed button, icons, action listeners
	 */
	public void make() {
//		JToolBar toolbar = new JToolBar();
		// toolbar.setEnabled(false);
		this.setFloatable(false);
		JToggleButton delButton = new JToggleButton(new ImageIcon("images/del.png"));
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.delete();
				main.buttonGroup.clearSelection();
			}
		});
		delButton.setToolTipText("Удалить");
		this.add(delButton);
		this.addSeparator();
		JToggleButton unactiveButton = new JToggleButton(new ImageIcon("images/unactive.png"));
		unactiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				main.drawFigure.setDrawFigure(DrawFigure.NONE);
				main.drawFigure = DrawFigure.NONE;
				
				// JOptionPane.showMessageDialog(frame, "unactive");
			}
		});
		unactiveButton.setToolTipText("Обнулить елемент рисования");
		this.add(unactiveButton);
		this.addSeparator();
		JToggleButton undoButton = new JToggleButton(new ImageIcon("images/undo.png"));
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				main.undo();
//				JOptionPane.showMessageDialog(frame, "undo");
			}
		});
		undoButton.setToolTipText("Отменить");
		this.add(undoButton);
		this.addSeparator();
		JToggleButton redoButton = new JToggleButton(new ImageIcon("images/redo.png"));
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				JOptionPane.showMessageDialog(frame, "redo");
				main.redo();
			}
		});
		redoButton.setToolTipText("Вернуть");
		this.add(redoButton);
		this.addSeparator();
		JToggleButton textButton = new JToggleButton(new ImageIcon("images/text.png"));
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// l.setText(Integer.toString(DrawFigure.getSelectedIndex()));
//				main.drawFigure.setDrawFigure(DrawFigure.TEXT);
				main.drawFigure = DrawFigure.TEXT;
			}
		});
		textButton.setToolTipText("Добавить текст");
		this.add(textButton);
		this.addSeparator();

		main.buttonGroup.add(delButton);
		// buttonGroup.add(new String("n"));
		// buttonGroup.add(DrawFigure);
		main.buttonGroup.add(unactiveButton);
		main.buttonGroup.add(undoButton);
		main.buttonGroup.add(redoButton);
		main.buttonGroup.add(textButton);
		
	}

}
