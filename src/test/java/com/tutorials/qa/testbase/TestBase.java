package com.tutorials.qa.testbase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class TestBase {

	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public FileInputStream ip;

	public TestBase() {
		prop = new Properties();
		try {
			ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (Throwable e) {
          e.printStackTrace();
		}

		dataprop = new Properties();
		try {
		 ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
	     dataprop.load(ip);
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public WebDriver initBrowserOpenApplicationUrl(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadTime));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utilities.scriptLoadTime));
		driver.get(prop.getProperty("url"));

		return driver;
	}

}
