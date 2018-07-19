package settings;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.UIManager.LookAndFeelInfo;

import com.borwe.ByClient.App;

import database.DatabaseClient;

public class SettingsClient {
	
	private static final String PROPERTY_COMMENT="Some client shit";
	private static final String THEME="theme";
	private static final String PORT_NUMBER="port";
	private static final String SETTINGSNAME="SETTINGSNAME";
	private static final String SETTINGSVALUE="SETTINGSVALUE";
	private static final String SETTINGSTABLE="SETTINGS";
	
	
	public static String getThemeInDB() {
		// TODO Auto-generated method stub
		Future<String> themeinDBFuture=App.dbTasks.submit(()->{
			String value=DatabaseClient.getAccess().
				query("SELECT * FROM SETTINGS WHERE SETTINGSNAME="+THEME, row->{
					return row.getString(SETTINGSVALUE);
				});
			
			if(value!=null) {
				return value;
			}
			return null;
		});
		try {
			return themeinDBFuture.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Future<Boolean> setThemeInDB(LookAndFeelInfo theme) {
		// TODO Auto-generated method stub
		return App.dbTasks.submit(()->{
			if(getThemeInDB()==null) {
				//check if row exists with theme value, if not, then add one
				Future<Boolean> fut=App.dbTasks.submit(()->{
					DatabaseClient.getAccess().update("INSERT INTO ?"
							+ " VALUES (?,?)", new Object[] {
									SETTINGSTABLE,THEME,theme.getClassName()
							});
					return true;
				});
				return fut.get();
			}else {
				//if it does, then update the row in db.
				Future<Boolean> fut=App.dbTasks.submit(()->{
					DatabaseClient.getAccess().update("UPDATE ?"
							+ " SET ? = ? WHERE ? = ?", new Object[] {
									SETTINGSTABLE,SETTINGSVALUE,theme.getClassName(),
									SETTINGSNAME,THEME
							});
					return true;
				});
				return fut.get();
			}
		});
	}

}
