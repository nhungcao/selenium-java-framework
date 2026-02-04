package com.cognni.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MSLoginPage extends BasePage {
    // --- Locators (Atomic elements) ---
    private By microsoftSsoButton = By.xpath("//button[contains(., 'Login with MSFT SSO')]");
    private By emailField = By.name("loginfmt");
    private By nextButton = By.id("idSIButton9");
    private By passwordField = By.name("passwd");
    private By signInButton = By.id("idSIButton9");
    private By staySignedInNo = By.id("idBtn_Back");

    public MSLoginPage(WebDriver driver) {
        super(driver);
    }

    // --- Atomic Actions with Fluent Interface ---

    public MSLoginPage clickLoginWithMicrosoft() {
        click(microsoftSsoButton);
        // after clicking, wait for redirection to Microsoft login page
        waitForUrlToContain("microsoftonline");
        return this;
    }

    public MSLoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public MSLoginPage clickNext() {
        click(nextButton);
        return this;
    }

    public MSLoginPage enterPassword(String password) {
        // wait for password field to be visible
        waitForElementVisible(passwordField);
        type(passwordField, password);
        return this;
    }

    public MSLoginPage clickSignIn() {
        click(signInButton);
        return this;
    }

    public void handleStaySignedIn(boolean stay) {
        if (!stay && isElementDisplayed(staySignedInNo, 5)) {
            click(staySignedInNo);
        }
        // No return needed as this is the end of the flow
    }

    // --- Login Flow ---
    public void loginToCognni(String email, String pass) {
        this.clickLoginWithMicrosoft()
                .enterEmail(email)
                .clickNext()
                .enterPassword(pass)
                .clickSignIn()
                .handleStaySignedIn(false);
    }
}