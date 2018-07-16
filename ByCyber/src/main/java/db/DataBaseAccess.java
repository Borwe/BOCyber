package db;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataBaseAccess {

	private static SimpleDriverDataSource driver;
	private static JdbcTemplate access;
	
	public static synchronized JdbcTemplate getAccess() {
		if(driver==null) {
			driver=new SimpleDriverDataSource();
			driver.setDriverClass(org.apache.derby.jdbc.EmbeddedDriver.class);
			driver.setUrl("jdbc:derby:db;create=true;");
		}
		
		if(access==null) {
			access=new JdbcTemplate(driver);
			try {
				DatabaseMetaData dbData=access.getDataSource().getConnection().getMetaData();
				ResultSet rs=dbData.getTables(null, null, "COMPUTERS", null);
				if(rs.next()==false) {
					//create computers table
					access.execute("CREATE TABLE computers("
							+ "computer_id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY" + 
							" (START WITH 1, INCREMENT BY 1),"
							+ "computer_name varchar(255),"
							+ "computer_ip varchar(255)"
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
