package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Main extends JFrame implements Runnable {
	
	public void createMenus() {
		JMenuBar mainBar=new JMenuBar();
		this.setJMenuBar(mainBar);
		
		JMenu settings=new JMenu("Settings");
		mainBar.add(settings);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setTitle("ByClient Application");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenus();
		
		this.setVisible(true);
	}

}
