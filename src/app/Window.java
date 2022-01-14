package app;

import javax.swing.JFrame;
import java.awt.Color;

@SuppressWarnings("serial")
public class Window extends JFrame {
	/* pewnie potem sie przydadza wymiary */
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	public static int DELTA_SPEED = 1;

	public Window() {
		setTitle("Symulacja");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(47, 47, 47));
		setResizable(false);
		setLayout(null);
				
		addContent();	
		setVisible(true);
	}
	
	private void addContent() {
		// komponenty UI
	}
}
