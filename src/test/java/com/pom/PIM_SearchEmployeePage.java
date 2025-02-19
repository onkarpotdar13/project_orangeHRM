package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;

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
	public String title_Section() {
		try {
			return (search_section_Title.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public void searchByName(String name) {
		search_ByName.sendKeys(name);
	}
	
	public void click_SearchBtn() {
		btn_Search.click();
	}
	
	public String foundNameMessage() {
		try {
			return (msg_FoundName.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	public void click_ResetBtn() {
		btn_Reset.click();
	}
	
	public void searchById(String id) {
		search_ById.sendKeys(id);
	}
	
	public void click_CheckBox() {
		if(msg_FoundId.isDisplayed()) {
			checkbox_FoundEmp.click();
		}
	}
	

}
