package com.cognni.framework.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.cognni.tests.BaseTest;

public class TestListener implements ITestListener {
    // this function is called when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        // get driver from BaseTest
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        // take screenshot and attach to Allure report
        if (driver != null) {
            System.out.println("Screeshot captured for test case: " + result.getName());
            saveScreenshotPNG(driver);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
