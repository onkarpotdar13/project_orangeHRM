package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;
import com.utilities.AIUtils;

import io.qameta.allure.Step;

public class LoginPageAI extends BasePage{
	
	//constructor
	public LoginPageAI(WebDriver driver) {
		super(driver);
	}
	
	// locator
		@FindBy(xpath = "//h5[text()='Login']")
		WebElement txt_Page;

		@FindBy(xpath = "//input[@placeholder='Username']")
		WebElement ip_Username;

		@FindBy(xpath = "//input[@placeholder='Password']")
		WebElement ip_Password;

		@FindBy(xpath = "//button[normalize-space()='Login']")
		WebElement btn_Login;
		
		@FindBy(xpath = "//span/h6[text()='Dashboard']")
		WebElement txt_NextPaage;

	//action methods
		
	@Step("Get the current page title")
	public String pageDisplay() {
		try {
			return(txt_Page.getText());
		}catch(Exception e) {
				return (e.getMessage());
			}
	}
	
	@Step("Enter username: {0}")
	public void inputUsername(String username) {
	    String aiLocator = AIUtils.suggestLocator("username");
	    System.out.println("AI Suggested Locator for Username: " + aiLocator);
	    ip_Username.sendKeys(username);
	}

	@Step("Enter password: {0}")
	public void inputPassword(String password) {
	    String aiLocator = AIUtils.suggestLocator("password");
	    System.out.println("AI Suggested Locator for Password: " + aiLocator);
	    ip_Password.sendKeys(password);
	}

	@Step("Click Login button")
	public void clickLoginBtn() {
	    String aiLocator = AIUtils.suggestLocator("loginButton");
	    System.out.println("AI Suggested Locator for Login Button: " + aiLocator);
	    btn_Login.click();
	}
	
	@Step("Verify the next page after login")
	public String nextPageDisplay() {
		try {
			return (txt_NextPaage.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}


}
