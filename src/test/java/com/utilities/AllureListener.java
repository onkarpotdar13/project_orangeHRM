package com.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.BaseTest;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {

	@Attachment(value = "{testName} - Failure Screenshot", type = "image/png")
	public byte[] attachScreenshotToAllure(String testName) {
		WebDriver driver = BaseTest.driver;
		
		if (driver == null) {
			System.out.println("Driver is null, cannot capture screenshot.");
			return null;
		}
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        attachScreenshotToAllure(result.getName());
        BaseTest.saveScreenshotLocally(result.getName());
    }


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Partially Failed: " + result.getName());
    }
}
