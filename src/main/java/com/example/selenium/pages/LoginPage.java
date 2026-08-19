package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Login page.
 * Provides methods for entering credentials and logging into the application.
 */
public class LoginPage extends BasePage {

    private By emailField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.id("loginButton");
    private By loginError = By.xpath("//p[@data-testid='login-error'][text()='Invalid username or password']");

    /**
     * Constructor for LoginPage.
     * @param driver WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the email address into the username field.
     * @param email Email address.
     * @return This LoginPage instance.
     */
    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    /**
     * Enters the password into the password field.
     * @param password Password.
     * @return This LoginPage instance.
     */
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /**
     * Clicks the login button.
     * @return A new instance of HomePage.
     */
    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage(driver);
    }


    public LoginPage clickLoginExpectingError() {
        click(loginButton);
        return this;
    }
    
    /**
     * Performs a full login operation.
     * @param email Email address.
     * @param password Password.
     * @return A new instance of HomePage.
     */
    public HomePage login(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .clickLogin();
    }

    public LoginPage loginWithError(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .clickLoginExpectingError().verifyLoginErrorIsDisplayed();
    }

    public LoginPage verifyLoginErrorIsDisplayed() {
        verifyElementDisplayed(loginError, "Login error is not displayed (Login error not found: " + loginError + ")");
        return this;
    }

    /**
     * Verifies that the Login page is displayed by checking for the login button.
     * @return This LoginPage instance.
     */
    public LoginPage verifyLoginPageIsDisplayed() {
        verifyElementDisplayed(loginButton, "Login page is not displayed (Login button not found: " + loginButton + ")");
        return this;
    }
}
