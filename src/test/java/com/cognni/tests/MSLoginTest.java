package com.cognni.tests;

import com.cognni.framework.pages.DashboardPage;
import com.cognni.framework.pages.MSLoginPage;
import com.cognni.framework.utils.Helpers;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MSLoginTest extends BaseTest {

    @Test(description = "Verify MSFT SSO Login")
    public void testMSFTLoginFluent() {
        // Use the 'driver' initialized in BaseTest.setUp()
        MSLoginPage loginPage = new MSLoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        loginPage.clickLoginWithMicrosoft()
                .enterEmail(Helpers.getValue("msft_user"))
                .clickNext()
                .enterPassword(Helpers.getValue("msft_pass"))
                .clickSignIn()
                .handleStaySignedIn(false);

        // Wait until the URL changes to include the token
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains("token="));

        // Store the URL with JWT token for subsequent test classes
        storeSessionUrl();

        String dashboardTitle = dashboardPage.getDashboardTitle();
        Assert.assertTrue(dashboardTitle.contains("Overview of"), "Dashboard title did not match.");
    }
}