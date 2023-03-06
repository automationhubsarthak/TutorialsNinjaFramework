package youtube.datadriven.testing;

import org.testng.annotations.DataProvider;

public class Data {
	
	@DataProvider(name = "TNData")
	public static Object[][] ninjaDataSupply() {
		Object[][] data = {{"seleniumpanda@gmail.com", "Selenium@123"},
				{"seleniumpanda1@gmail.com", "Selenium@123"},
				{"seleniumpanda2@gmail.com", "Selenium@123"},
				{"seleniumpanda3@gmail.com", "Selenium@1234"}};
		
		return data;
		}
	
	
	@DataProvider(name = "RediffData")
	public static Object[][] rediffDataSupply() {
		Object[][] data = {{"seleniumpanda@rediffmail.com", "Selenium@123"},
				{"seleniumpanda1@rediffmail.com", "Donkey@123"},
				{"seleniumpanda2@rediffmail.com", "Selenium@123"},
				{"seleniumpanda3@rediffmail.com", "Selenium@1234"}};
		
		return data;
		}
	
	}


