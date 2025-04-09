package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPage;
import com.pom.PIM_AddEmployeePage;
import com.pom.PIM_DeleteEmployeePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Employee Management")
@Feature("Delete Employee")
public class TC005_DeleteEmployeeTest extends BaseTest{
	
	@Test(groups = {"functional"})
	@Description("Verify employee can be searched by last name and romve from records")
	@Severity(SeverityLevel.NORMAL)
	@Story("Admin search employee by last name and remove")
	public void delete_Employee() {
		
		try {

			logger.info("****** START: TC005_DeleteEmployeeTest ******");
			//login
			logger.info("Step 1: Navigating to Login Page");
			LoginPage lp = new LoginPage(driver);
			lp.inputUsername(properties.getProperty("username"));
			lp.inputPassword(properties.getProperty("password"));
			lp.clickLoginBtn();
			Assert.assertTrue(lp.nextPageDisplay().contains("Dashboard"), "Login failed!");
			
			PIM_AddEmployeePage addEmp = new PIM_AddEmployeePage(driver);
			logger.info("Step 2: Navigating to PIM Module");
			addEmp.click_PIM();
			Assert.assertTrue(addEmp.display_PIMPage());
			
			PIM_DeleteEmployeePage removeEmp = new PIM_DeleteEmployeePage(driver);
			logger.info("Step 2: Action on Delete Operation");
			boolean isFound = removeEmp.searchAndSelectEmployee(properties.getProperty("lastName"));
			
			if(isFound) {
				removeEmp.deleteSelectedEmployee();
				System.out.println("Employee with last name '" + "' deleted successfully.");
			}else {
				System.out.println("Employee with last name '" + "' not found!");
				Assert.assertTrue(isFound);
			}
			
			logger.info("****** END: TC005_DeleteEmployeeTest ******");
		
		}catch(Exception e) {
			logger.error("Test Case Failed due to Exception: ", e);
			Assert.fail();
		}
	}

}








////div[@class='orangehrm-paper-container']/div[3]/div[1]/div[2]/div/div[1]/div[4]/div --> total table
////ul[@class='oxd-pagination__ul']//following-sibling::li[4] --> next button
////div[@class='orangehrm-paper-container']/div[3]/div[1]/div[2]/div/div[1]/div[1]//label//span --> checkbox