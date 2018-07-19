package com.borwe.ByClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import database.DatabaseClient;
import network.CreatedSocketClient;
import views.Main;
import views.ThemeSetter;


public class App 
{
	
	public static ExecutorService backgroundTasks=null;
	public static ExecutorService dbTasks;
	
    public static void main( String[] args )
    {
    	ThemeSetter.startupThemeSetter();
    	
        backgroundTasks=Executors.newCachedThreadPool();
        dbTasks=Executors.newCachedThreadPool();
        
        SwingUtilities.invokeLater(new Main());
    }
}
