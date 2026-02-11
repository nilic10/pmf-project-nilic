package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Users page.
 * Provides methods for managing user profiles and verifying user-related information.
 */
public class UsersPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By saveButton = By.id("save");
    private By alertPopup = By.id("alertPopup");

    /**
     * Constructor for UsersPage.
     * @param driver WebDriver instance.
     */
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the page header text.
     * @return String representing the page header.
     */
    public String getPageHeader() {
        return getText(pageHeader);
    }

    /**
     * Enters the first name.
     * @param firstName First name.
     * @return This UsersPage instance.
     */
    public UsersPage enterFirstName(String firstName) {
        type(firstNameField, firstName);
        return this;
    }

    /**
     * Enters the last name.
     * @param lastName Last name.
     * @return This UsersPage instance.
     */
    public UsersPage enterLastName(String lastName) {
        type(lastNameField, lastName);
        return this;
    }

    /**
     * Enters the email address.
     * @param email Email address.
     * @return This UsersPage instance.
     */
    public UsersPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    /**
     * Enters the password.
     * @param password Password.
     * @return This UsersPage instance.
     */
    public UsersPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /**
     * Clicks the save button.
     * @return This UsersPage instance.
     */
    public UsersPage clickSave() {
        click(saveButton);
        return this;
    }

    /**
     * Verifies that the Users page is displayed.
     * @return This UsersPage instance.
     */
    public UsersPage verifyUsersPageIsDisplayed() {
        verifyElementDisplayed(By.id("btnUsers"), "Users page is not displayed (btnUsers not found)");
        return this;
    }

    /**
     * Verifies the alert message text.
     * @param expectedMessage The expected message.
     * @return This UsersPage instance.
     */
    public UsersPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }
}
