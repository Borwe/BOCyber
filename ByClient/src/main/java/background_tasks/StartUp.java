package background_tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.borwe.ByClient.App;

import database.DatabaseClient;
import views.Main;

public class StartUp implements Runnable{

	private Main parent;
	
	public StartUp(Main parent) {
		// TODO Auto-generated constructor stub
		this.parent=parent;
	}
	
	
	private void setupDatabase() {
		setStatus("Loading Database");
		Future<Boolean> done=App.dbTasks.submit(()->{
			DatabaseClient.getAccess();
			return true;
		});
		try {
			done.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		removeStatus();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setupDatabase();		
	}

	
	private void setStatus(String label) {
		parent.statusInfo.setText(label);
		parent.statusProgress.setVisible(true);
	}
	
	private void removeStatus() {
		parent.statusInfo.setText("Done..");
		parent.statusProgress.setVisible(false);
	}
}
