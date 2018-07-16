package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class About extends JDialog implements Runnable{
	
	JFrame parent;
	
	public About(JFrame parent) {
		// TODO Auto-generated constructor stub
		this.parent=parent;
		//run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setTitle("About The ByCyber");
		this.setSize(500, 400);
		this.setModal(true);
		this.setLocationRelativeTo(parent);
		
		//string to display
		String about="<html><h4><center>This is an application used to manage a cyber cafe<br>"
				+ "It contains a long list of features that are easy for anyone to understand<br>"
				+ "Features are available on click in the Features section under Help Menu<br>"
				+ "This Application was developed by:</h4><br>"
				+ "<h1><b><center>Brian Orwe</b><h1></center><br>"
				+ "<h4><center>To Contact him please check the Contact Us Option and Help Menu<br><br>"
				+ "Thank You</h4></center><html>";
		
		JLabel aboutLabel=new JLabel(about, JLabel.CENTER);
		this.add(aboutLabel, BorderLayout.CENTER);
		
		JButton okToExit=new JButton("OK");
		okToExit.addActionListener(event->{
			About.this.setVisible(false);
			About.this.dispose();
		});
		
		JPanel button_panel=new JPanel();
		button_panel.setLayout(new BorderLayout(0,2));

		button_panel.add(new JSeparator(),BorderLayout.NORTH);
		button_panel.add(okToExit,BorderLayout.SOUTH);
		this.add(button_panel,BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setVisible(true);
	}
}
