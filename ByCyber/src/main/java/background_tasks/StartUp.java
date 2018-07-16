package background_tasks;

import javax.swing.JFrame;

import settings.Settings;

public class StartUp extends JFrame implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//check if application has a listening port to communicate with
		String port=Settings.getPortNumber();
		if(port==null) {
			//prompt for port number
			Settings.promptForPort(this);
		}
	}
}
