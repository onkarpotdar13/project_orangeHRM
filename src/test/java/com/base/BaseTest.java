package com.base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.qameta.allure.Attachment;


public class BaseTest {

    public static WebDriver driver;
    public Properties properties;
    public Logger logger;

    @BeforeClass(groups = {"sanity", "regression", "functional"})
    @Parameters({"os", "browser"})
    public void startUp(String os, String browser) throws IOException {

        // Load properties from config file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());

        // Set up WebDriver based on browser choice
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        // Configure WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"sanity", "regression", "functional"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Capture and attach a screenshot to Allure report on test failure.
     */
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] captureScreenshotOnFailure(String testName) {
        if (driver == null) {
            return null;
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Save a screenshot locally in the project folder with a timestamp.
     */
    public static void saveScreenshotLocally(String testName) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
