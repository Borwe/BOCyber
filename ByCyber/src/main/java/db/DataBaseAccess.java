package db;

import java.io.File;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataBaseAccess {

	private static SimpleDriverDataSource driver;
	private static JdbcTemplate access;
	
	public static synchronized JdbcTemplate getAccess() {
		if(driver==null) {
			driver=new SimpleDriverDataSource();
			driver.setDriverClass(org.sqlite.JDBC.class);
			File db=new File("byCyber.db");
			System.out.println(db.getAbsolutePath());
			driver.setUrl("jdbc:sqlite:"+db.getAbsolutePath());
		}
		
		if(access==null) {
			access=new JdbcTemplate(driver);
			access.execute("CREATE TABLE test("
					+ " id INT PRIVATE_KEY"
					+ ")");
		}
		return access;
	}
}
