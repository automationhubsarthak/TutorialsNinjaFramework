package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testbase.TestBase;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.utils.Utilities;
 
public class Login extends TestBase {
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public Login() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
	    driver = initBrowserOpenApplicationUrl(prop.getProperty("browserName"));
	    HomePage homepage = new HomePage(driver);
	    homepage.clickOnMyAccountDropMenu();
	    homepage.clickOnLoginOption();
			
	}
	
	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
	
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		softassert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		softassert.assertAll();
		
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] getDataSupply() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ExpectedWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ActualWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
			
	}
	
	@Test(priority = 3)
	public void verifyLoginWithValidEmailInvalidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ExpectedWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ActualWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
			
	}
	
	@Test(priority = 4)
	public void verifyLoginWithInvalidEmailValidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ExpectedWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ActualWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
			
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ExpectedWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ActualWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
			
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
