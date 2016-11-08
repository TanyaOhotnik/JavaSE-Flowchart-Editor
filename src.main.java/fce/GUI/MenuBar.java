package fce.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Class which extend standard Swing element JMenuBar and
 * set needed button, action listeners, accelerators
 * @author Tanya Ohotnik
 */
public class MenuBar extends JMenuBar {

	private FlowChartEditor main;

	/**
	 * Class constructor calling method to create MenuBar
	 * 
	 * @param main
	 */
	public MenuBar(FlowChartEditor main) {
		super();
		this.main = main;
		make();
	}
	/**
	 * Method which set needed button, action listeners, accelerators
	 */
	public void make() {
		this.setBounds(0, 0, main.frame.getWidth(), 30);
		JMenu fileMenu = new JMenu("File");
		Action newFileAction = new AbstractAction("Create new") {
			public void actionPerformed(ActionEvent event) {
				main.flowChart.getList().clear();
				main.panel.repaint();
			}
		};
		JMenuItem newFileMenu = new JMenuItem(newFileAction);
		fileMenu.add(newFileMenu);
		
		
		Action saveasAction = new AbstractAction("Save as...") {
			public void actionPerformed(ActionEvent event) {
				try {
					String fileName = null;
					;
					main.image = main.copyOnBufferedImage();
					JFileChooser jf = new JFileChooser();
					TextFileFilter pngFilter = new TextFileFilter(".png");
					jf.addChoosableFileFilter(pngFilter);
					int result = jf.showSaveDialog(null);

					if (result == JFileChooser.APPROVE_OPTION) {
						fileName = jf.getSelectedFile().getAbsolutePath();
					}
					if (fileName == null)
						fileName = "UnnamedDiagram.png";
					if (jf.getFileFilter() == pngFilter) {
						ImageIO.write(main.image, "png", new File(fileName + ".png"));
					}
					
					main.image = null;
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(main.frame, "Ошибка ввода-вывода");
				}
			}
		};
		JMenuItem saveasMenu = new JMenuItem(saveasAction);
		fileMenu.add(saveasMenu);
		fileMenu.addSeparator();
		
		Action exitAction = new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent event) {
				main.frame.dispatchEvent(new WindowEvent(main.frame, WindowEvent.WINDOW_CLOSING));
			}
		};
		JMenuItem exitMenu = new JMenuItem(exitAction);
		fileMenu.add(exitMenu);
		
		this.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		Action undo = new AbstractAction("Undo ") {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.undo();
			}
		};
		undo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		JMenuItem undoMenu = new JMenuItem(undo);
		editMenu.add(undoMenu);

		Action redo = new AbstractAction("Redo ") {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.redo();
			}
		};
		redo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		JMenuItem redoMenu = new JMenuItem(redo);
		editMenu.add(redoMenu);

		 Action delete = new AbstractAction("Delete ") {
		 @Override
		 public void actionPerformed(ActionEvent e) {
		
		 main.delete();
		 }
		 };

		 JMenuItem delMenu = new JMenuItem(delete);
		 delMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		 editMenu.add(delMenu);

		this.add(editMenu);
	}

}
