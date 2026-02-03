package com.cognni.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class DashboardPage extends BasePage {
    // --- Locators (Atomic elements) ---
    private By dashboardHeader = By.xpath("//h1[text()='Overview of']");

    // Element High Severity Incidents
    private By highSeverityBtn = By.cssSelector(".card-bg button:nth-of-type(1) button");

    // Elment Top Risky Users
    private By topRiskyBtn = By.cssSelector(".card-bg button:nth-of-type(2) button");

    // Element Mass Download Incidents
    private By massDownloadBtn = By.cssSelector(".card-bg button:nth-of-type(3) button");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // --- Atomic Actions ---

    public DashboardPage waitForDashboardLoaded() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        return this;
    }

    public String getDashboardTitle() {
        // intialize a longer wait for dashboard elements
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // long wait for dashboard header to be visible
        WebElement header = longWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        return header.getText();
    }

    // get value of Key Findings - High Severity
    @Step("Retrieve High Severity Incidents count from dashboard")
    public String getHighSeverityValue() {
        // Implementation to retrieve High Severity value from dashboard
        return getElementText(highSeverityBtn).trim();
    }

    // get value of Key Findings - Top Risky Users
    @Step("Retrieve Top Risky Users count from dashboard")
    public String getTopRiskyUsersValue() {
        // Implementation to retrieve Top Risky Users value from dashboard
        return getElementText(topRiskyBtn).trim();
    }

    // get value of Key Findings - Mass Downloads
    @Step("Retrieve Mass Download Incidents count from dashboard")
    public String getMassDownloadValue() {
        // Implementation to retrieve Mass Download value from dashboard
        return getElementText(massDownloadBtn).trim();
    }

}