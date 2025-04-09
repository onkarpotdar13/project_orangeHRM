package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pom.LoginPageAI;
import com.utilities.AIUtils;

public class TC006_LoginAITest extends BaseTest{
	
	@Test(groups = "regression")
	public void login() {
	    try {
	        logger.info("*** START THE <-- TC006_LoginTestAI --> TEST ***");
	        LoginPageAI lpAI = new LoginPageAI(driver);

	        String currentPage = lpAI.pageDisplay();
	        Assert.assertEquals("Login", currentPage);

	        lpAI.inputUsername(properties.getProperty("usernameI"));
	        lpAI.inputPassword(properties.getProperty("passwordI"));
	        lpAI.clickLoginBtn();

	        String nextPage = lpAI.nextPageDisplay();
	        Assert.assertEquals("Dashboard", nextPage);
	    } catch (Exception e) {
	        String aiAnalysis = AIUtils.analyzeFailure("TC006_LoginTestAI", e.getMessage());
	        System.out.println(aiAnalysis);
	        Assert.fail(aiAnalysis);
	    }
	    logger.info("*** FINISH THE <-- TC006_LoginTestAI --> TEST ***");
	}


}
