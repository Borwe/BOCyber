package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.LookAndFeelInfo;

import views.ThemeSetter;

public class Settings {

	private static final String PORT_NAME="port";
	private static final String LOOKS_NAME="theme";
	private static final String PROPERTIES_FILE="bo_cyber.prop";
	private static final String PROP_COMMENTS="Property shit";
	
	public static void promptForTheme(JFrame parent) {
		List<LookAndFeelInfo> looks=ThemeSetter.getAllThemes();
		
		//get name of looks
		String[] looks_Array =new String[looks.size()];
		for(int i=0;i<looks.size();i++) {
			looks_Array[i]=looks.get(i).getName();
		}
		
		String theme_name=null;
		for(LookAndFeelInfo look:looks) {
			if(look.getClassName().equals(getThemeInProp())) {
				theme_name=look.getName();
			}
		}
		String result=(String) JOptionPane.showInputDialog(parent, "Select a Look ( current: "+theme_name+" )", "Looks/Themes",
				JOptionPane.DEFAULT_OPTION, null,looks_Array,looks_Array[0]);
		
		if(result!=null) {
			looks.stream().forEach(l->{
				if(l.getName().equals(result)) {
					ThemeSetter.setTheme(l);
					SwingUtilities.updateComponentTreeUI(parent);
				}
			});
		}else {
			
		}
	}
	
	public static void setThemeInProp(LookAndFeelInfo look) {
		Properties properties=null;
		try {
			properties=getPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(properties==null) {
			properties=new Properties();
		}
		
		properties.setProperty(LOOKS_NAME, look.getClassName());
		try {
			saveProptertiesFile(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getThemeInProp() {
		Properties properties=null;
		try {
			properties = getPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(properties!=null) {
			return properties.getProperty(LOOKS_NAME);
		}else {
			return null;
		}
	}
	
	public static void promptForPort(JFrame parent) {
		String question="Enter your Port Number here";
		
		//get portnumber from properties
		try {
			Properties prop=getPropertiesFile();
			if(prop!=null) {
				question+="(previous port: "+prop.getProperty(PORT_NAME)+" )";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String port_numer=JOptionPane.showInputDialog(parent, question);
		
		try {
			int port=Integer.parseInt(port_numer);
			setPortNumber(port);
			JOptionPane.showMessageDialog(parent, "Set Prot to: "+port);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(parent, "Please enter a valid Port, between 0 and 65535");
		}
	}
	
	private static Properties getPropertiesFile() throws IOException {
		File bo_cyber_props=new File(PROPERTIES_FILE);
		
		if(bo_cyber_props.exists()==false) {
			return null;
		}else {
			FileInputStream input=new FileInputStream(bo_cyber_props);
			Properties properties=new Properties();
			properties.load(input);
			input.close();
			return properties;
		}
	}
	
	private static void saveProptertiesFile(Properties properties) throws IOException {
		if(properties!=null) {
			File bo_cyber_props=new File(PROPERTIES_FILE);
			
			FileOutputStream out=new FileOutputStream(bo_cyber_props);
			properties.store(out, PROP_COMMENTS);
		}
	}
	
	private static String getPortNumber() {
		Properties protProps=null;
		try {
			protProps = getPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return protProps.getProperty(PORT_NAME);
	}
	
	private static void setPortNumber(int portNumber) throws IOException {
		Properties properties=getPropertiesFile();
		File file=new File(PROPERTIES_FILE);
		
		FileOutputStream outputStream=new FileOutputStream(file);
		
		//if no properties file exists
		if(properties==null) {
			properties=new Properties();
		}
		
		properties.setProperty(PORT_NAME, portNumber+"");
		
		properties.store(outputStream, PROP_COMMENTS);
		outputStream.close();
	}
}