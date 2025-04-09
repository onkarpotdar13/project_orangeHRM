package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPage;
import com.pom.PIM_AddEmployeePage;
import com.pom.PIM_SearchEmployeePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Employee Management")
@Feature("Search Employee")
public class TC004_SearchEmployeeTest extends BaseTest{
	
	@Test(groups = "sanity")
	@Description("Verify that an employee can be searched by name and ID")
	@Severity(SeverityLevel.MINOR)
	@Story("Admin search employee by Name or ID")
	public void search_Employee() {
		
		try {
			logger.info("*** START TEST CASE: TC004_SearchEmployee ***");
			//login
			logger.info("Step 1: Navigating to Login Page");
			LoginPage lp = new LoginPage(driver);
			
			lp.inputUsername(properties.getProperty("username"));
			Thread.sleep(2000);
			lp.inputPassword(properties.getProperty("password"));
			Thread.sleep(2000);
			lp.clickLoginBtn();
			Thread.sleep(2000);
			
			//add employee
			PIM_AddEmployeePage addEmp = new PIM_AddEmployeePage(driver);
			logger.info("Navigating to PIM section");
			
			addEmp.click_PIM();
			boolean pageTitle = addEmp.display_PIMPage();
			Assert.assertTrue(pageTitle);
			Thread.sleep(2000);
			
			// search employee
			PIM_SearchEmployeePage searchEmp = new PIM_SearchEmployeePage(driver);
			
			logger.info("Verifying Employee Information section");
			String sectionTitle = searchEmp.title_Section();
			Assert.assertEquals(sectionTitle, "Employee Information", "Section title mismatch");
			logger.info("SECTION : " + sectionTitle);;
			System.out.println("SECTION : " + sectionTitle);
			Thread.sleep(2000);
			
			logger.info("Searching employee by name");
			searchEmp.searchByName(properties.getProperty("employeeName"));
			Thread.sleep(2000);
			
			searchEmp.click_SearchBtn();
			Thread.sleep(2000);
			
			String msgName = searchEmp.foundNameMessage();
			Assert.assertEquals(msgName, "(1) Record Found");
			logger.info("Message : " + msgName);
			System.out.println("Message : " + msgName);
			Thread.sleep(2000);
			
			logger.info("Resetting search criteria");
			searchEmp.click_ResetBtn();
			Thread.sleep(2000);
			
			logger.info("Searching employee by ID");
			searchEmp.searchById(properties.getProperty("employeeId"));
			Thread.sleep(2000);
			
			searchEmp.click_SearchBtn();
			Thread.sleep(2000);
			
			logger.info("Selecting employee checkbox");
			searchEmp.click_CheckBox();
			Thread.sleep(2000);
		
			logger.info("*** END TEST CASE: TC004_SearchEmployee ***");
		}catch(Exception e) {
			logger.error("Test case failed due to exception: " + e.getMessage());
			Assert.fail();
		}
	}

}
