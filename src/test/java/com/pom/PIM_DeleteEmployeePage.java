package com.pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

import io.qameta.allure.Step;

public class PIM_DeleteEmployeePage extends BasePage{
	
	WebDriverWait wait;

	public PIM_DeleteEmployeePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// locators
	@FindBy(xpath = "//ul[@class='oxd-pagination__ul']//li[last()-1]")
	WebElement lastPage;
	
	@FindBy(xpath = "//ul[@class='oxd-pagination__ul']//*[text()='{0}']")
	WebElement nextPage;
	
	@FindBy(xpath = "//div[@class='orangehrm-paper-container']/div[3]/div[1]/div[2]/div")
	List<WebElement> rows;
	
	@FindBy(xpath = "//button[normalize-space()='Delete Selected']")
	WebElement btn_Delete;
	
	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	WebElement btn_cnfmDelete;
	
	
	// methods
	@Step("Get total pages in pagination")
	public int getTotalPages() {
		String totalPages = wait.until(ExpectedConditions.visibilityOf(lastPage)).getText();
		return Integer.parseInt(totalPages);
	}
	
	@Step("Search for employee by last name: {0}")
	public boolean searchAndSelectEmployee(String lastName) {
		
		boolean isFound = false;
		int totalPages = getTotalPages();
		
		for(int page = 1; page <= 1; page++) {
			
			if(page > 1) {
				WebElement pageButton = wait.until(ExpectedConditions.elementToBeClickable(nextPage));
				pageButton.click();
			}
			
			for(int row = 1; row <= rows.size(); row++) {
			
				WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div[3]/div[1]/div[2]/div[" + row + "]/div[1]/div[4]"))));
				String actualLastName = lastNameElement.getText();
				
				if(actualLastName.equalsIgnoreCase(lastName)) {
					WebElement checkBox = driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div[3]/div[1]/div[2]/div[" + row + "]/div[1]/div[1]//label//span"));
					wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
					isFound = true;
					break;
				}
			}
			if(isFound) break;
		}
		return isFound;
	}
	
	@Step("Delete the selected employee(last name)")
	public void deleteSelectedEmployee() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_Delete)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_cnfmDelete)).click();
	}

}
