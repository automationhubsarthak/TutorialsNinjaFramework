package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorials.qa.testbase.TestBase;

public class HomePage extends TestBase{
	public WebDriver driver;
	
	@FindBy(linkText = "My Account")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement signInButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;	
	PageFactory.initElements(driver, this);	
	}
	
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}
	
	public void clickOnLoginOption() {
		loginOption.click();
	}
	
	

}
