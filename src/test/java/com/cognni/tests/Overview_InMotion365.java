package com.cognni.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import com.cognni.framework.pages.DashboardPage;
import io.qameta.allure.*;

@Epic("Dashboard Verification")
@Feature("InMotion365 Features")
public class Overview_InMotion365 extends BaseTest {

    @Test(description = "Verify Key Findings metrics for InMotion365")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check if High Severity, Top Risky, and Mass Download counts match expected values via Token URL")
    @Story("User verifies dashboard metrics")
    public void testInMotion365_KeyFindings() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        SoftAssert softAssert = new SoftAssert();

        // Check if the Token URL was captured in the previous Login test
        if (dashboardUrlWithToken != null) {
            driver.get(dashboardUrlWithToken);
            System.out.println("Accessing Dashboard using captured JWT Token URL...");
        } else {
            // Hard fail if no token is available as subsequent steps will fail anyway
            Assert.fail(
                    "Pre-condition failed: No stored dashboard URL with token found. Ensure MSLoginTest runs first.");
        }

        // Wait for the dashboard elements to be fully rendered
        dashboardPage.waitForDashboardLoaded();

        // Retrieve and verify High Severity Incidents count
        String highSeverity = dashboardPage.getHighSeverityValue();
        softAssert.assertEquals(highSeverity, "1", "High Severity Incidents count mismatch!");

        // Retrieve and verify Top Risky Users count
        String topRiskyUsers = dashboardPage.getTopRiskyUsersValue();
        softAssert.assertEquals(topRiskyUsers, "1", "Top Risky Users count mismatch!");

        // Retrieve and verify Mass Download Incidents count
        String massDownloads = dashboardPage.getMassDownloadValue();
        softAssert.assertEquals(massDownloads, "0", "Mass Download Incidents count mismatch!");

        // Mandatory call to collect and report all assertion failures
        softAssert.assertAll();

        System.out.println("InMotion365 Key Findings verification completed.");
    }
}