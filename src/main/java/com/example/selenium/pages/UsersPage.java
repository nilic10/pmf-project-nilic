package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By saveButton = By.id("save");
    private By alertPopup = By.id("alertPopup");

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public UsersPage enterFirstName(String firstName) {
        type(firstNameField, firstName);
        return this;
    }

    public UsersPage enterLastName(String lastName) {
        type(lastNameField, lastName);
        return this;
    }

    public UsersPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public UsersPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public UsersPage clickSave() {
        click(saveButton);
        return this;
    }

    public UsersPage verifyUsersPageIsDisplayed() {
        verifyElementDisplayed(By.id("btnUsers"), "Users page is not displayed (btnUsers not found)");
        return this;
    }

    public UsersPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }
}
