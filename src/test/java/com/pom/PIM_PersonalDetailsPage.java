package com.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;

import io.qameta.allure.Step;

public class PIM_PersonalDetailsPage extends BasePage{

	// constructor
	public PIM_PersonalDetailsPage(WebDriver driver) {
		super(driver);
	}

	// locator
	@FindBy(xpath = "//h6[normalize-space()='Raoo Paoo']")
	WebElement pro_Name;
	
/*<-- SECTION PERSNAL DETILS -->*/		
	@FindBy(xpath = "//h6[normalize-space()='Personal Details']")	
	WebElement title_section_1;
	
	@FindBy(xpath = "//form/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement ip_OtherId;
	
	@FindBy(xpath = "//form/div[2]/div[2]/div[1]/div[1]/div[2]/input")
	WebElement ip_LicenceId;
	
	/* license expire date --> Date Pickers */	
	@FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")
	WebElement dateBarLED;
	
	@FindBy(xpath = "//li[@class='oxd-calendar-selector-month']")
	WebElement current_month_LED;
	
	@FindBy(xpath = "//li[@class='oxd-calendar-selector-year']")
	WebElement current_year_LED;
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-chevron-right']")
	WebElement btn_nextDate_LED;
	
	@FindBy(xpath = "//div[@class='oxd-calendar-dates-grid']//div//div")
	List<WebElement> all_Dates_LED;
	
	/* Date of Birth --> Date Pickers */
	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")
	WebElement dateBarDOB;
	
	@FindBy(xpath = "//li[@class='oxd-calendar-selector-month']")
	WebElement current_month_DOB;
	
	@FindBy(xpath = "//li[@class='oxd-calendar-selector-year']")
	WebElement current_year_DOB;
	
	@FindBy(xpath = "//button[@class='oxd-icon-button']/i[@class='oxd-icon bi-chevron-left']")
	WebElement btn_nextDate_DOB;
	
	@FindBy(xpath = "//div[@class='oxd-calendar-dates-grid']//div//div")
	List<WebElement> all_Dates_DOB;
	
	// radio button gender
	@FindBy(xpath = "//label[normalize-space()='Male']")
	WebElement rbtn_Gender;

	// drop down nationality
	@FindBy(xpath = "//form/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/i")
	WebElement dd_Nationality;
	
	@FindBy(xpath = "//div[@role='listbox']/div")
	List<WebElement> list_Country;
	
	// drop down marital status
	@FindBy(xpath = "//form/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/i")
	WebElement dd_MaritalSatus;
	
	@FindBy(xpath = "//div[@role='listbox']/div")
	List<WebElement> list_MartialSatus;

/*<-- SECTION CUSTOM FIELDS -->*/			
	// drop down blood type
	@FindBy(xpath = "//h6[normalize-space()='Custom Fields']")
	WebElement title_section_2;
	
	@FindBy(xpath = "//form/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/i")
	WebElement dd_BloodType;
	
	@FindBy(xpath = "//div[@role='listbox']/div")
	List<WebElement> list_BloodType;
	
	@FindBy(xpath = "//form/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement ip_testField;
	
/*<-- SECTION Attachments -->*/			
	@FindBy(xpath = "//h6[normalize-space()='Attachments']")
	WebElement title_section_3;
	
	@FindBy(xpath = "//button/i[contains(@class,'oxd-button-icon')]")
	WebElement btn_addAttachment;
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-upload oxd-file-input-icon']")
	WebElement btn_fileBrowse;
	
	@FindBy(xpath = "//div[@class='oxd-file-input-div']")
	WebElement select_fileName;
	
	@FindBy(xpath = "//div[@class='oxd-file-input-div']")
	WebElement txt_fileName;
	
	// action methods
	@Step("Display Profile Name")
	public String display_Profile() {
		try {
			return (pro_Name.getText());
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	@Step("Display Section First Title")
	public String display_SectionFirst() {
		try {
			return (title_section_1.getText());
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	@Step("Enter Input other ID: {0}")
	public void inputOtherId(String id) {
		ip_OtherId.sendKeys(id);
	}
	
	@Step("Enter License ID: {0}")
	public void inputLicenseId(String liid) {
		ip_LicenceId.sendKeys(liid);
	}
	
	/* license expire date Date Pickers --> Future */	
	@Step("Click on License Expiry Date-Picker")
	public void click_DateBarLED() {
		dateBarLED.click();
	}
	
	@Step("Select License Expiry Date: {0}-{1}-{2}")
	public void selectLicenseDate(String mm, String dd, String yyyy) throws InterruptedException {
		
		while(true) {
			
			String now_month = current_month_LED.getText();
			String now_year = current_year_LED.getText();
			
			if(now_month.equals(mm) && now_year.equals(yyyy)) {
				break;
			}
			
			btn_nextDate_LED.click();
			
		}
		for(WebElement date : all_Dates_LED) {
			if(date.getText().equals(dd)) {
				Thread.sleep(1000);
				date.click();
				break;
			}
		}
	}
	
	
	/* Date of Birth --> Date Pickers --> Past */ 
	@Step("Click on Date-OF-Birth Picker")
	public void click_DateDOB() {
		dateBarDOB.click();
	}
	
	@Step("Select Date-OF-Birth: {0}-{1}-{2}")
	public void selectDOBDate(String mm, String dd, String yyyy) throws InterruptedException {
		
		while(true) {
			
			String current_month = current_month_DOB.getText();
			String current_year = current_year_DOB.getText();
			
			if(current_month.equals(mm) && current_year.equals(yyyy)) {
				break;
			}
			
			btn_nextDate_DOB.click();
		}
		
		for(WebElement date : all_Dates_DOB) {
			if(date.getText().equals(dd)){
				Thread.sleep(1000);
				date.click();
				break;
			}
		}
	}
	
	// radio button
	@Step("Click on Gender Radio Button")
	public void click_GenderBtn() {
		rbtn_Gender.click();
	}
	
	// handle drop down selecting country
	@Step("Select Country: {0}")
	public void selectCountry(String nationality) {
		
		dd_Nationality.click();
		
		for(WebElement country : list_Country) {
			if(country.getText().equals(nationality)) {
				country.click();
				break;
			}
		}
	}
	
	// handle drop down selecting marital status 
	@Step("Select State: {0}")
	public void selectingMarriageStatus(String status) {
		
		dd_MaritalSatus.click();
		
		for(WebElement marry : list_MartialSatus) {
			if(marry.getText().equals(status)) {
				marry.click();
				break;
			}
		}
	}
	
	@Step("Display Section Second Title")
	public String display_SectionSecond() {
		try {
			return (title_section_2.getText());
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	// handle drop down selecting blood type
	@Step("Select Blood Type: {0}")
	public void selectiongBloodType(String blood) {
		
		dd_BloodType.click();
		
		for(WebElement bloodTypes : list_BloodType) {
			if(bloodTypes.getText().equals(blood)) {
				bloodTypes.click();
				break;
			}
		}
	}
	
	@Step("Enter Test Field Name: {0}")
	public void inputTestField(String test) {
		ip_testField.sendKeys(test);
	}
	
	@Step("Display Section Third Title")
	public String display_SectionThird() {
		try {
			return (title_section_3.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@Step("Click on Add Attacment Button")
	public void click_fileAddBtn() {
		btn_addAttachment.click();
	}
	
	@Step("Click on Browse File Button")
	public void click_fileBrowseBtn() {
		btn_fileBrowse.click();
	}
	
	@Step("Select File: {0}")
	public void select_File(String file) {
		select_fileName.sendKeys(file);
	}
	
	@Step("Get Selected File Name")
	public String name_File() {
		return txt_fileName.getText();
	}
}
