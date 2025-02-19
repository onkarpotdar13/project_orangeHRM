package com.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPage;
import com.pom.PIM_AddEmployeePage;
import com.pom.PIM_PersonalDetailsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Employee Management")
@Feature("Add Employee Personal Details")
public class TC003_PersonalDetailsTest extends BaseTest {

	@Test(groups = { "functional", "master" })
	@Description("Verify that an admin can successfully add personal details of an employee.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Admin adds employee personal details")
	public void add_employee_personalDeatails() {

		try {
			logger.info("****** START: TC003_PersonalDetailsTest ******");

			// Step 1: Login
			logger.info("Step 1: Logging in to the system");
			LoginPage lp = new LoginPage(driver);
			Thread.sleep(2000);

			lp.inputUsername(properties.getProperty("username"));
			lp.inputPasswor(properties.getProperty("password"));
			lp.clickLoginBtn();
			Thread.sleep(2000);

			// Step 2: Add Employee Details
			logger.info("Step 2: Adding Employee Details");
			PIM_AddEmployeePage addEmp = new PIM_AddEmployeePage(driver);
			addEmp.click_PIM();
			addEmp.click_AddBtn();
			Thread.sleep(2000);

			addEmp.inputFName(properties.getProperty("FirstName"));
			addEmp.inputMName(properties.getProperty("MiddleName"));
			addEmp.inputLName(properties.getProperty("LastName"));
			addEmp.click_SaveBtn();
			Thread.sleep(2000);

			String displaySuccess_Msg = addEmp.successMsg();
			logger.info("Employee added successfully: " + displaySuccess_Msg);
			System.out.println("ADD EMPLOYEE : " + displaySuccess_Msg);
			Thread.sleep(2000);

			// Step 3: Verify Employee Profile Name
			logger.info("Step 3: Verifying Employee Profile Name");
			PIM_PersonalDetailsPage empPD = new PIM_PersonalDetailsPage(driver);
			String profileName = empPD.display_Profile();
			Assert.assertEquals(profileName, "Raoo Paoo", "Profile name does not match!");
			logger.info("Employee Profile Name: " + profileName);
			System.out.println("EMPLOYEE NAME : " + profileName);
			Thread.sleep(2000);

			System.out.println("+---------------------+---------------------+");
			// Step 4: Verify Personal Details Section
			logger.info("Step 4: Verifying Personal Details Section");
			String sectionFirst = empPD.display_SectionFirst();
			Thread.sleep(2000);

			Assert.assertEquals(sectionFirst, "Personal Details", "Personal Details section not found!");
			logger.info("Section: " + sectionFirst);
			System.out.println("SECTION : " + sectionFirst);
			Thread.sleep(2000);

			// Step 5: Entering Personal Details
			logger.info("Step 5: Entering Employee Personal Details");
			empPD.inputOtherId(properties.getProperty("otherID"));
			Thread.sleep(2000);

			empPD.inputLicenseId(properties.getProperty("licenseID"));
			Thread.sleep(2000);

			/* license expire date Date Pickers --> Future */
			empPD.click_DateBarLED();
			Thread.sleep(2000);

			empPD.selectLicenseDate("February", "10", "2030");
			Thread.sleep(2000);

			/* drop down --> Nationality */
			empPD.selectCountry(properties.getProperty("nationality"));
			Thread.sleep(2000);

			/* drop down --> Marriage Status */
			empPD.selectingMarriageStatus(properties.getProperty("marriageSatus"));
			Thread.sleep(2000);

			/* Date of Birth --> Date Pickers --> Past */
			empPD.click_DateDOB();
			Thread.sleep(2000);

			empPD.selectDOBDate("April", "27", "2015");
			Thread.sleep(2000);

			/* radio button --> gender */
			empPD.click_GenderBtn();
			logger.info("Personal details entered successfully");
			Thread.sleep(2000);

			System.out.println("+---------------------+---------------------+");
			/* SECTION 2 : Custom Fields */
			// Step 6: Verify Custom Fields Section
			logger.info("Step 6: Verifying Custom Fields Section");
			String sectionSecond = empPD.display_SectionSecond();
			System.out.println("SECTION : " + sectionSecond);
			Assert.assertEquals(sectionSecond, "Custom Fields", "Custom Fields section not found!");
			logger.info("Section: " + sectionSecond);
			Thread.sleep(2000);

			/* drop down --> Blood Types */
			empPD.selectiongBloodType(properties.getProperty("bloodType"));
			Thread.sleep(2000);

			empPD.inputTestField(properties.getProperty("testField"));
			Thread.sleep(2000);
			System.out.println("+---------------------+---------------------+");

			/* SECTION 3 : Attachments */
			// Step 7: Verify Attachments Section
			logger.info("Step 7: Verifying Attachments Section");
			String sectionThird = empPD.display_SectionThird();
			System.out.println("SECTION : " + sectionThird);
			Assert.assertEquals(sectionThird, "Attachments", "Attachments section not found!");
			logger.info("Section: " + sectionThird);
			Thread.sleep(2000);

			/*
			 * NOTE : FILE NOT UPLOAD BECAUSE WE NOT USE WINDOW BASE APPLICATION IF HANDLE
			 * THEN USE AutoIT tool
			 */
			
			// Step 8: Attempt File Upload (AutoIT required for actual upload)
            logger.info("Step 8: Clicking file upload buttons");
			empPD.click_fileAddBtn();
			Thread.sleep(1000);

			empPD.click_fileBrowseBtn();
			Thread.sleep(1000);

			 logger.info("****** END: TC003_PersonalDetailsTest ******");
		} catch (Exception e) {
			logger.error("Test Case Failed due to Exception: ", e);
			Assert.fail();
		}
	}

}
