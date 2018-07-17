package views;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import settings.Settings;

public class Main extends JFrame implements Runnable {
	
	private void generateMenus() {
		JMenuBar menuBar=new JMenuBar();
		
		JMenu connection=new JMenu("Connection");
		
		JMenuItem listenForDevices=new JMenuItem("Start Listen For Devices");
		JMenuItem exitApplication=new JMenuItem("Exit");
		exitApplication.addActionListener(event->{
			Main.this.setVisible(false);
			System.exit(0);
		});
		
		connection.add(listenForDevices);
		connection.add(exitApplication);
		menuBar.add(connection);
		
		
		JMenu settings=new JMenu("Settings");
		JMenuItem portSettings=new JMenuItem("Port To User...");
		portSettings.addActionListener(event->{
			Settings.promptForPort(Main.this);
		});
		JMenuItem setDelay=new JMenuItem("Set Search Delay");
		JMenuItem setLooks=new JMenuItem("Set Look And Feel...");
		setLooks.addActionListener(event->{
			Settings.promptForTheme(Main.this);
		});
		settings.add(portSettings);
		settings.add(setDelay);
		settings.add(setLooks);
		menuBar.add(settings);
		
		JMenu help=new JMenu("Help");
		JMenuItem about=new JMenuItem("About");
		about.addActionListener(event->{
			SwingUtilities.invokeLater(new About(Main.this));
		});
		JMenuItem features=new JMenuItem("Features");
		features.addActionListener(event->{
			SwingUtilities.invokeLater(new Features(Main.this));
		});
		help.add(features);
		help.add(about);
		menuBar.add(help);
		
		this.setJMenuBar(menuBar);
	}
	
	private void startUpInfo() {
		Settings.setBackgroundDelayOnStartUp(Main.this);
	}

	public void run() {
		// TODO Auto-generated method stub
		this.setTitle("BOCyber");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		generateMenus();
		startUpInfo();
		
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
