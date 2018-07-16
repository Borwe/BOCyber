package com.borwe.ByCyber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import db.DataBaseAccess;
import views.Main;
import views.ThemeSetter;

/**
 * Hello world!
 *
 */
public class App 
{
	//handle execution of db
	public static ExecutorService dbExecutor=null;
	
	
    public static void main( String[] args )
    {
       ThemeSetter.startupThemeSetter();
       dbExecutor=Executors.newCachedThreadPool();
       dbExecutor.execute(()->{
    	   DataBaseAccess.getAccess();
       });
        
        SwingUtilities.invokeLater(new Main());
    }
}
