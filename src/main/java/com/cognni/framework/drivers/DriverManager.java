package com.cognni.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    // using ThreadLocal to support parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // function to initialize WebDriver based on browser type
    public static void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }
    }

    // function to get the WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // function to quit the WebDriver instance
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}