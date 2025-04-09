package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;

import io.qameta.allure.Step;

public class PIM_SearchEmployeePage extends BasePage{

	// constructor
	public PIM_SearchEmployeePage(WebDriver driver) {
		super(driver);
	}
	
	// locator
	@FindBy(xpath = "//h5[normalize-space()='Employee Information']")
	WebElement search_section_Title;
	
	@FindBy(xpath = "//form/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input")
	WebElement search_ByName;
	
	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement btn_Search;
	
	@FindBy(xpath = "//span[contains(.,'Record Found')]")
	WebElement msg_FoundName;
	
	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement btn_Reset;
	
	@FindBy(xpath = "//form/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement search_ById;
	
	@FindBy(xpath = "//span[contains(.,'Record Found')]")
	WebElement msg_FoundId;
	
	@FindBy(xpath = "//div[@role='columnheader']//div[@class='oxd-checkbox-wrapper']//span")
	WebElement checkbox_FoundEmp;
	

	// action method
	@Step("Display Profile Name")
	public String title_Section() {
		try {
			return (search_section_Title.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@Step("Search By Employee Name: {0}")
	public void searchByName(String name) {
		search_ByName.sendKeys(name);
	}
	
	@Step("Click on Search Button")
	public void click_SearchBtn() {
		btn_Search.click();
	}
	
	@Step("Fetch Success Message")
	public String foundNameMessage() {
		try {
			return (msg_FoundName.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	@Step("Click on Reset Button")
	public void click_ResetBtn() {
		btn_Reset.click();
	}
	
	@Step("Search By Employee ID: {0}")
	public void searchById(String id) {
		search_ById.sendKeys(id);
	}
	
	@Step("Click on Check Box")
	public void click_CheckBox() {
		if(msg_FoundId.isDisplayed()) {
			checkbox_FoundEmp.click();
		}
	}
	

}
