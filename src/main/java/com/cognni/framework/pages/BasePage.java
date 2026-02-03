package com.cognni.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Khởi tạo với thời gian chờ mặc định từ file config hoặc 10s
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // find the element until it appears in DOM
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // check element is visible on the screen
    protected void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // click element
    protected void click(By locator) {
        waitForElementVisible(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // type into element
    protected void type(By locator, String text) {
        WebElement element = findElement(locator);
        waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // wait for URL to contain specific fraction
    protected void waitForUrlToContain(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    // check if element is displayed
    protected boolean isElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // get text of an element
    protected String getElementText(By locator) {
        WebElement element = findElement(locator);
        waitForElementVisible(locator);
        return element.getText();
    }
}
