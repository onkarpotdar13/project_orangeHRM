package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("User Authentication") // High-level module
@Feature("Login Functionality") // Specific feature
public class TC001_LoginTest extends BaseTest{

	@Test(groups = "regression")
	@Description("Verify that a user can log in successfully with valid credentials") // Detailed test description.
	@Severity(SeverityLevel.BLOCKER) // Define the importance of the test
	@Story("Valid User Login") // User story from requirement
	public void login() {
		
		try {
			logger.info("*** START THE <-- TC001_LoginTest --> TEST ***");
			LoginPage lp = new LoginPage(driver);
			
			String currentPage = lp.pageDisplay();
			System.out.println("CURRENT-PAGE : " + currentPage);
			Assert.assertEquals("Login", currentPage);
			logger.info("*** PASS VALUES --> TEST ***");
			lp.inputUsername(properties.getProperty("usernameI"));
			lp.inputPassword(properties.getProperty("passwordI"));
			lp.clickLoginBtn();
			
			logger.info("*** DISPLAY ACCSEPTED PAGE --> TEST ***");
			String nextPage = lp.nextPageDisplay();
			System.out.println("NEXT PAGE : " + nextPage);
			Assert.assertEquals("Dashboard", nextPage);
			
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("*** FINISH THE <-- TC001_LoginTest --> TEST ***");
		
	}
	
}
