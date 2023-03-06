package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testbase.TestBase;

public class Search extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public Search() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		driver = initBrowserOpenApplicationUrl(prop.getProperty("browserName"));
		
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		WebElement searchBar = driver.findElement(By.name("search"));
		searchBar.sendKeys(dataprop.getProperty("validProduct"));
		searchBar.sendKeys(Keys.ENTER);
		softassert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		softassert.assertAll();
		
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
	}
	
	@Test(priority=2)
	public void verifySearchWithNonExistingProduct() {
		WebElement searchBar = driver.findElement(By.name("search"));
		searchBar.sendKeys(dataprop.getProperty("invalidProduct"));
		searchBar.sendKeys(Keys.ENTER);
		
		String ActualWarningMessage = driver.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]")).getText();
		String ExpectedWarningMessage = dataprop.getProperty("noProductWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
		
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
	}
	
	@Test(priority=3)
	public void verifySearchWithoutEnteringAnyProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		
		String ActualWarningMessage = driver.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]")).getText();
		String ExpectedWarningMessage = dataprop.getProperty("noProductWarning");
		
		softassert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage));
		softassert.assertAll();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
