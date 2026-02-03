package com.cognni.tests;

import com.cognni.framework.utils.Helpers;
import com.cognni.framework.pages.DashboardPage;
import com.cognni.framework.pages.MSLoginPage;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MSLoginTest extends BaseTest {

    @Test(description = "Verify MSFT SSO with Fluent Interface")
    public void testMSFTLoginFluent() {
        MSLoginPage loginPage = new MSLoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        // Perform login using fluent interface
        loginPage.clickLoginWithMicrosoft()
                .enterEmail(Helpers.getValue("msft_user"))
                .clickNext()
                .enterPassword(Helpers.getValue("msft_pass"))
                .clickSignIn()
                .handleStaySignedIn(false);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("?token="));

        // Store the session URL with token for reuse
        storeSessionUrl();

        // Verify successful login by checking dashboard title
        String dashboardTitle = dashboardPage.getDashboardTitle();
        Assert.assertTrue(dashboardTitle.contains("Overview of"), "Login failed: dashboard title mismatch.");
    }
}
