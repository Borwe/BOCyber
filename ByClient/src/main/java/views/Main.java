package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.borwe.ByClient.App;

import background_tasks.StartUp;

public class Main extends JFrame implements Runnable {
	
	
	public JLabel statusInfo;
	public JProgressBar statusProgress;
	
	public void createMenus() {
		JMenuBar mainBar=new JMenuBar();
		this.setJMenuBar(mainBar);
		
		JMenu settings=new JMenu("Settings");
		mainBar.add(settings);
	}
	
	public void generateStatusBar() {
		JPanel bottomStatus=new JPanel(new BorderLayout());
		this.add(bottomStatus,BorderLayout.SOUTH);
		
		statusInfo=new JLabel("Started...");
		statusProgress=new JProgressBar();
		statusProgress.setValue(90);
		
		JPanel bottomLeft=new JPanel(new FlowLayout());
		
		bottomLeft.add(statusInfo);
		bottomLeft.add(statusProgress);
		bottomStatus.add(bottomLeft,BorderLayout.EAST);
		
		statusProgress.setVisible(false);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setTitle("ByClient Application");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenus();
		generateStatusBar();
		
		this.setVisible(true);
		
		App.backgroundTasks.execute(new StartUp(Main.this));
	}

}
