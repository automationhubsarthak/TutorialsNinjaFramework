package youtube.datadriven.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCasesExcel {
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	
	@Test(priority = 1, dataProvider = "TutorialsNinjaDATASupplier", dataProviderClass = DataProvidersExcel.class )
	public static void tnLoginTest(String username, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Edit your account information')]")).isDisplayed());
		softassert.assertAll();
		
	}
	
	@Test(priority = 2, dataProvider = "RediffDATASupplier", dataProviderClass = DataProvidersExcel.class)
	public static void rediffLoginTest(String username, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.findElement(By.id("login1")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.signinbtn")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//a[contains(@class, 'rd_logout')]")).isDisplayed());
		softassert.assertAll();
		
		
	}
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}

}
