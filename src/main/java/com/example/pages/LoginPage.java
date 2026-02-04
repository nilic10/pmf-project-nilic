package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.id("loginButton");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage(driver);
    }
    
    public HomePage login(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .clickLogin();
    }

    public LoginPage verifyLoginPageIsDisplayed() {
        verifyElementDisplayed(loginButton);
        return this;
    }
}
