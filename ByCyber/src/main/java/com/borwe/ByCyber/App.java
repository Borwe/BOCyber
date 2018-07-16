package com.borwe.ByCyber;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import background_tasks.StartUp;
import db.DataBaseAccess;
import views.Main;
import views.ThemeSetter;

/**
 * Hello world!
 *
 */
public class App {
	// handle execution of db
	public static ExecutorService dbExecutor = null;
	
	//handle background tasks
	public static ExecutorService backgroundExecutor = null;

	public static void main(String[] args) {
		ThemeSetter.startupThemeSetter();

		dbExecutor = Executors.newCachedThreadPool();
		backgroundExecutor = Executors.newCachedThreadPool();

		dbExecutor.execute(() -> {
			DataBaseAccess.getAccess();
		});

		try {
			SwingUtilities.invokeAndWait(new StartUp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Main());
	}
}
