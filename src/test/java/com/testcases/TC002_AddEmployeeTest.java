package com.testcases;

import org.testng.Assert; 
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPage;
import com.pom.PIM_AddEmployeePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Employee Management") // High-level module
@Feature("Add Employee") // Specific feature
public class TC002_AddEmployeeTest extends BaseTest {

	@Test(groups = { "functional", "master" })
	@Description("Verify that an admin can successfully add a new employee.") // Test description
	@Severity(SeverityLevel.CRITICAL) // Test severity level
	@Story("Admin adds a new employee") // User story reference
	public void add_employee_Details() {

		try {

			logger.info("****** START: TC002_AddEmployeeTest ******");
			// login
			logger.info("Step 1: Navigating to Login Page");
			LoginPage lp = new LoginPage(driver);

			String currentPage = lp.pageDisplay();
			System.out.println("CURRENT-PAGE : " + currentPage);
			Assert.assertEquals("Login", currentPage);

			lp.inputUsername(properties.getProperty("username"));
			lp.inputPasswor(properties.getProperty("password"));
			lp.clickLoginBtn();

			String nextPage = lp.nextPageDisplay();
			System.out.println("NEXT PAGE : " + nextPage);
			Assert.assertEquals("Dashboard", nextPage);

			// add employee details
			PIM_AddEmployeePage addEmp = new PIM_AddEmployeePage(driver);
			logger.info("Step 2: Navigating to PIM Module");

			addEmp.click_PIM();
			boolean title_PIMPage = addEmp.display_PIMPage();
			Assert.assertTrue(title_PIMPage);
			String pimPage = (title_PIMPage == true) ? "PIM PAGE" : "NOT RICH AT PIM PAGE";
			System.out.println("TITLE OF PAGE : " + pimPage);

			Thread.sleep(2000);
			logger.info("Step 3: Clicking on Add Employee Button");
			addEmp.click_AddBtn();
			boolean title_Page = addEmp.display_EmpPage();
			Assert.assertTrue(title_Page);
			logger.info("Add Employee Page Displayed: " + (title_Page ? "YES" : "NO"));

			String empPage = (title_Page == true) ? "ADD-EMPLOYEE PAGE" : "NOT RICH AT PAGE";
			System.out.println("TITLE OF PAGE : " + empPage);

			Thread.sleep(1000);
			logger.info("Step 4: Entering Employee Details");
			addEmp.inputFName(properties.getProperty("FirstName"));
			Thread.sleep(1000);
			addEmp.inputMName(properties.getProperty("MiddleName"));
			Thread.sleep(1000);
			addEmp.inputLName(properties.getProperty("LastName"));

//			Thread.sleep(1000);
//			addEmp.clearEmpId();
//			Thread.sleep(2000);
//			addEmp.inputEmpId("0555");

			Thread.sleep(2000);
			logger.info("Step 5: Clicking Save Button");
			addEmp.click_SaveBtn();

			Thread.sleep(2000);
			String displaysuccess_Msg = addEmp.successMsg();
			System.out.println("ADD EMPLOYEE : " + displaysuccess_Msg);

			logger.info("****** END: TC002_AddEmployeeTest ******");
			
		} catch (Exception e) {
			logger.error("Test Case Failed due to Exception: ", e);
			Assert.fail();
		}
	}

}
