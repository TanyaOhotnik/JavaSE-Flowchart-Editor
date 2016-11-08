package fce.GUI;

import java.awt.EventQueue;
/**
 * @author Tanya Ohotnik
 */
public class DesktopApplication {

	/**
	 * Launch the FlowChartEditor Application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlowChartEditor window = new FlowChartEditor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
