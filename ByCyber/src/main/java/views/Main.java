package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import settings.Settings;

public class Main extends JFrame implements Runnable {
	
	private void generateMenus() {
		JMenuBar menuBar=new JMenuBar();
		
		JMenu connection=new JMenu("Connection");
		menuBar.add(connection);
		
		
		JMenu settings=new JMenu("Settings");
		JMenuItem portSettings=new JMenuItem("Port To User...");
		portSettings.addActionListener(event->{
			Settings.promptForPort(Main.this);
		});
		JMenuItem setLooks=new JMenuItem("Set Look And Feel...");
		setLooks.addActionListener(event->{
			Settings.promptForTheme(Main.this);
		});
		settings.add(portSettings);
		settings.add(setLooks);
		menuBar.add(settings);
		
		this.setJMenuBar(menuBar);
	}

	public void run() {
		// TODO Auto-generated method stub
		this.setTitle("BOCyber");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		generateMenus();
		
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
