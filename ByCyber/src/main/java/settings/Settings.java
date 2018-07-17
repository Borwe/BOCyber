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
	private static final String SEARCH_DELAY="delay";
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
	
	public static void setBackgroundDelayOnStartUp(JFrame parent) {
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
		
		String delay;
		if((delay=properties.getProperty(SEARCH_DELAY))!=null) {
			double d=Integer.parseInt(delay)/(1000*60);//turn to minutes
			JOptionPane.showMessageDialog(parent, "NOTE: Current search time is "+d+" minutes");
		}else {
			//set to 5 minutes to search for devices
			properties.setProperty(SEARCH_DELAY, (1000*60*5)+"");
			try {
				saveProptertiesFile(properties);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(parent, "NOTE: Default wait time for search is 5 minutes\n"
					+ "Value can be changed on Settings");
		}
	}
	
	public static void setBackgroundDelay(JFrame parent) {
		String prompt="Enter the time to wait when searching for \n"
				+ "devices to connect to to with ByCyer in minutes";
		
		String current_minutes=getBackgroundDelay();
		if(current_minutes!=null) {
			prompt+="\n(Current Delay Time: "+current_minutes+" minutes)";
		}
		
		
		String delay=JOptionPane.showInputDialog(parent, prompt);
		
		
		
		try {
			//turn to miliseconds
			double d=Double.parseDouble(delay);
			d=d*1000*60;
			
			//get propertyfile
			Properties property=getPropertiesFile();
			if(property==null) {
				property=new Properties();
			}
			property.setProperty(SEARCH_DELAY, d+"");
			saveProptertiesFile(property);
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(parent, "Please enter time in minutes",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static String getBackgroundDelay() {
		try {
			Properties properties=getPropertiesFile();
			if(properties!=null) {
				String time=properties.getProperty(SEARCH_DELAY);
				double minutes=Double.parseDouble(time)/(1000*60);
				return minutes+"";
			}else {
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
	
	public static String getPortNumber() {
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
