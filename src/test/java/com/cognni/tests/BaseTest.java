package com.cognni.tests;

import com.cognni.framework.drivers.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
//import com.cognni.framework.utils.Helpers;

public class BaseTest {
    // protected driver so that subclasses can access it
    protected WebDriver driver;
    // to store url with token
    protected static String dashboardUrlWithToken;

    // run before each test method
    @BeforeMethod
    @Parameters("browser") // get browser parameter from testng.xml
    /*
     * public void setUp(@Optional("chrome") String browser) {
     * // initialize WebDriver based on browser parameter
     * DriverManager.setDriver(browser);
     * 
     * // get the WebDriver instance
     * driver = DriverManager.getDriver();
     * 
     * // maximize window and navigate to base URL
     * driver.manage().window().maximize();
     * driver.get(Helpers.getValue("baseUrl"));
     * 
     * }
     */

    public void setUp(String browser) {
        boolean isServer = System.getenv("GITHUB_ACTIONS") != null;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isServer) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isServer) {
                options.addArguments("-headless"); // Firefox dùng 1 dấu gạch ngang
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void storeSessionUrl() {
        // get current URL with token
        dashboardUrlWithToken = driver.getCurrentUrl();
    }

    public void goToDashboardViaStoredUrl() {
        if (dashboardUrlWithToken != null) {
            driver.get(dashboardUrlWithToken);
        } else {
            throw new IllegalStateException("No stored dashboard URL with token found.");
        }
    }

    // run after each test method
    @AfterMethod
    public void tearDown() {
        // quit the WebDriver instance
        DriverManager.quitDriver();
    }
}