package database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DatabaseClient {

	private static SimpleDriverDataSource driver;
	private static JdbcTemplate access;
	
	public static synchronized JdbcTemplate getAccess() {
		if(driver==null) {
			driver=new SimpleDriverDataSource();
			driver.setDriverClass(org.apache.derby.jdbc.EmbeddedDriver.class);
			driver.setUrl("jdbc:derby:dbClient;create=true;");
		}
		if(access==null) {
			access=new JdbcTemplate(driver);
			try {
				DatabaseMetaData data=access.getDataSource().getConnection().getMetaData();
				ResultSet table_settings=data.getTables(null, null, "SETTINGS", null);
				if(table_settings.next()==false) {
					//create a settings table to store data
					access.execute("CREATE TABLE SETTINGS("
							+ "SettingsName VARCHAR(255) NOT NULL PRIMARY KEY,"
							+ "SettingsValue VARCHAR(255)"
							+ ")");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return access;
	}
}
