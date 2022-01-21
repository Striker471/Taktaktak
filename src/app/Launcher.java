package app;

import java.awt.*;

public class Launcher {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final Window pandemic = new Window();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}