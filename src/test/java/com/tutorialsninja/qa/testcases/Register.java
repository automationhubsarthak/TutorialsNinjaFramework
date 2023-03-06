package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testbase.TestBase;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public Register() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		driver = initBrowserOpenApplicationUrl(prop.getProperty("browserName"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority = 1)
	public void verifyRegisterAccountWithMandatoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ActualAccountCreationMessage = driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! Your new account has been successfully created!')]")).getText();
		String ExpectedAccountCreationMessage = dataprop.getProperty("accountSuccessfullyCreatedHeading");
		softassert.assertTrue(ActualAccountCreationMessage.contains(ExpectedAccountCreationMessage));
		softassert.assertAll();
	}
	
	
	@Test(priority = 2)
	public void verifyRegisterAccountWithAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = 1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ActualAccountCreationMessage = driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! Your new account has been successfully created!')]")).getText();
		String ExpectedAccountCreationMessage = dataprop.getProperty("accountSuccessfullyCreatedHeading");
		softassert.assertTrue(ActualAccountCreationMessage.contains(ExpectedAccountCreationMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = 1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ActualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ExpectedWarning = dataprop.getProperty("duplicateEmailWarning");
		softassert.assertTrue(ActualWarning.contains(ExpectedWarning));
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void verifyRegisterAccountWithoutFillingAnyDetails() {
	
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String ActualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String ExpectedPrivacyPolicyWarning = dataprop.getProperty("privacyPolicyWarning");
		softassert.assertTrue(ActualPrivacyPolicyWarning.contains(ExpectedPrivacyPolicyWarning));
		
		String ActualFirstNameWarning = driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][text() = 'First Name must be between 1 and 32 characters!']")).getText();
		String ExpectedFirstNameWarning = dataprop.getProperty("firstNameWarning");
		softassert.assertTrue(ActualFirstNameWarning.contains(ExpectedFirstNameWarning));
		
		String ActualLastNameWarning = driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][text() = 'Last Name must be between 1 and 32 characters!']")).getText();
		String ExpectedLastNameWarning = dataprop.getProperty("lastNameWarning");
		softassert.assertTrue(ActualLastNameWarning.contains(ExpectedLastNameWarning));
		
		String ActualEmailWarning = driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][text() = 'E-Mail Address does not appear to be valid!']")).getText();
		String ExpectedEmailWarning = dataprop.getProperty("emailAddressWarning");
		softassert.assertTrue(ActualEmailWarning.contains(ExpectedEmailWarning));
		
		String ActualTelephoneWarning = driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][text() = 'Telephone must be between 3 and 32 characters!']")).getText();
		String ExpectedTelephoneWarning = dataprop.getProperty("telephoneWarning");
		softassert.assertTrue(ActualTelephoneWarning.contains(ExpectedTelephoneWarning));
		
		String ActualPasswordWarning = driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][text() = 'Password must be between 4 and 20 characters!']")).getText();
		String ExpectedPasswordWarning = dataprop.getProperty("passwordWarning");
		softassert.assertTrue(ActualPasswordWarning.contains(ExpectedPasswordWarning));
		
		
		softassert.assertAll();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	


}
