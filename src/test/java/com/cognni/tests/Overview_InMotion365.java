package com.cognni.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.cognni.framework.pages.DashboardPage;
import io.qameta.allure.*;

/**
 * Test class focused on verifying the metrics within the InMotion365 Dashboard.
 * Inherits BaseTest to leverage automated browser setup and session management.
 */
@Epic("Dashboard Verification")
@Feature("InMotion365 Features")
public class Overview_InMotion365 extends BaseTest {

    @Test(description = "Verify Key Findings metrics for InMotion365")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Navigates to Dashboard using stored JWT token and validates High Severity, Top Risky, and Mass Download counts.")
    @Story("User verifies dashboard metrics accurately reflect system data.")
    public void testInMotion365_KeyFindings() {
        // Initialize DashboardPage using the driver from BaseTest
        DashboardPage dashboardPage = new DashboardPage(driver);
        SoftAssert softAssert = new SoftAssert();

        // Navigate directly to the dashboard using the URL + Token captured in
        // MSLoginTest
        // This method is defined in BaseTest and throws an exception if the token is
        // missing
        goToDashboardViaStoredUrl();
        System.out.println("Accessing Dashboard using captured JWT Token URL...");

        // Ensure dashboard elements are rendered before proceeding
        dashboardPage.waitForDashboardLoaded();

        // Verification 1: High Severity Incidents
        String highSeverity = dashboardPage.getHighSeverityValue();
        softAssert.assertEquals(highSeverity, "1", "High Severity Incidents count mismatch!");

        // Verification 2: Top Risky Users
        String topRiskyUsers = dashboardPage.getTopRiskyUsersValue();
        softAssert.assertEquals(topRiskyUsers, "0", "Top Risky Users count mismatch!");

        // Verification 3: Mass Download Incidents
        String massDownloads = dashboardPage.getMassDownloadValue();
        softAssert.assertEquals(massDownloads, "0", "Mass Download Incidents count mismatch!");

        System.out.println("InMotion365 Key Findings verification completed successfully.");
    }
}