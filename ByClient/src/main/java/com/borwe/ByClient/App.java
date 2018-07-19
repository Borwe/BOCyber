package com.borwe.ByClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import views.Main;


public class App 
{
	
	public static ExecutorService backgroundTasks=null;
	
    public static void main( String[] args )
    {
        backgroundTasks=Executors.newCachedThreadPool();
        
        SwingUtilities.invokeLater(new Main());
    }
}
