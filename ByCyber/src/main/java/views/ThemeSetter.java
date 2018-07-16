package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import settings.Settings;

public class ThemeSetter {

	//called when app starts to set themes
	public static void startupThemeSetter() {
		String prev_theme=Settings.getThemeInProp();
		if(prev_theme!=null) {
			setLoadedTheme(prev_theme);
		}else {
			List<LookAndFeelInfo> themes=getAllThemes();
			
			setTheme(themes.get(0));
		}
	}
	
	//get all available themes
	public static List<LookAndFeelInfo> getAllThemes() {
		List<LookAndFeelInfo> looks=Arrays.asList(UIManager.getInstalledLookAndFeels())
				.parallelStream().filter(look_and_feel->{
					
					
					String name=look_and_feel.getName();
					if(name.equals("Windows")) {
						return true;
					}
					if(name.equals("GTK+")) {
						return true;
					}
					if(name.equals("Nimbus")) {
						return true;
					}
					if(name.equals("Metal")) {
						return true;
					}
					
					return false;
				}).collect(Collectors.toList());
		
		Collections.reverse(looks);
		return looks;
	}
	
	//set theme
	public static void setTheme(LookAndFeelInfo theme) {
		try {
			UIManager.setLookAndFeel(theme.getClassName());
			Settings.setThemeInProp(theme);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setLoadedTheme(String className) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
