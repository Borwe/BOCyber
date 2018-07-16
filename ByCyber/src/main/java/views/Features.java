package views;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Features extends JDialog implements Runnable {
	
	JFrame parent;
	
	public Features(JFrame parent) {
		// TODO Auto-generated constructor stub
		this.parent=parent;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setSize(500,500);
		this.setModal(true);
		this.setTitle("ByCyber Features");
		
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setVisible(true);
	}

}
