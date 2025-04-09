package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;

import io.qameta.allure.Step;

public class PIM_AddEmployeePage extends BasePage{

	// constructor
	public PIM_AddEmployeePage(WebDriver driver) {
		super(driver);
	}
	
	// locator
	
	@FindBy(xpath = "//a[contains(@href, 'PimModule')]//*[name()='svg']") // SVG element
	WebElement btn_PIM;
	
	@FindBy(xpath = "//h6[text()='PIM']")
	WebElement title_PIMPage;
	
	@FindBy(xpath = "//h6[text()='Add Employee']")
	WebElement title_EmpPage;
	
	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement btn_Add;
	
	@FindBy(name = "firstName")
	WebElement ip_FName;
	
	@FindBy(name = "middleName")
	WebElement ip_MName;
	
	@FindBy(name = "lastName")
	WebElement ip_LName;
	
//	@FindBy(xpath = "//div[contains(@class,'oxd-input-group')]//div//input[@class='oxd-input oxd-input--active']")
//	WebElement clr_EmpID;
//	
//	@FindBy(xpath = "//div[contains(@class,'oxd-input-group')]//div//input[@class='oxd-input oxd-input--active']")
//	WebElement ip_EmpID;
	
	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement btn_save;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]")
	WebElement success_Msg;
	
	// action methods
	
	@Step("Click on PIM module")
	public void click_PIM() {
		btn_PIM.click();
	}
	
	@Step("Check if PIM Page is displayed")
	public boolean display_PIMPage() {
		try {
			return title_PIMPage.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	@Step("Click on Add button")
	public void click_AddBtn() {
		btn_Add.click();
	}
	
	public boolean display_EmpPage() {
		try {
			return title_EmpPage.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	@Step("Enter First Name: {0}")
	public void inputFName(String fname) {
		ip_FName.sendKeys(fname);
	}
	
	@Step("Enter Middle Name: {0}")
	public void inputMName(String mname) {
		ip_MName.sendKeys(mname);
	}
	
	@Step("Enter Last Name: {0}")
	public void inputLName(String lname) {
		ip_LName.sendKeys(lname);
	}
	
//	@Step("Clear Employee ID field")
//	public void clearEmpId() {
//		clr_EmpID.clear();
//	}
//	
//	@Step("Enter Employee ID: {0}")
//	public void inputEmpId(String empID) {
//		ip_EmpID.sendKeys(empID);
//	}
	
	@Step("Click ont save button")
	public void click_SaveBtn() {
		btn_save.click();
	}
	
	@Step("Fetch Success Message")
	public String successMsg() {
		try {
			return (success_Msg.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
