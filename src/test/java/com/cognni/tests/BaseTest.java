package com.cognni.tests;

import com.cognni.framework.drivers.DriverManager;
import com.cognni.framework.utils.Helpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    // Shared static variable to store the session URL with JWT token across test
    // classes
    protected static String dashboardUrlWithToken;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        boolean isServer = System.getenv("GITHUB_ACTIONS") != null;

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isServer) {
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage",
                        "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isServer) {
                options.addArguments("-headless", "--width=1920", "--height=1080");
            }
            driver = new FirefoxDriver(options);
        }

        // Sync the driver instance with DriverManager for thread-safe access
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
        driver.get(Helpers.getValue("baseUrl"));
    }

    // Getter method used by TestListener to capture screenshots on failure
    public WebDriver getDriver() {
        return this.driver;
    }

    // Capture the current URL containing the session token
    public void storeSessionUrl() {
        dashboardUrlWithToken = driver.getCurrentUrl();
    }

    // Navigate directly to the dashboard using the stored token URL
    public void goToDashboardViaStoredUrl() {
        if (dashboardUrlWithToken != null) {
            driver.get(dashboardUrlWithToken);
        } else {
            throw new IllegalStateException(
                    "Pre-condition failed: No dashboard URL with token found. Run MSLoginTest first.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Cleanup the driver and remove the ThreadLocal reference
        DriverManager.quitDriver();
    }
}