package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Home page.
 * Provides navigation links to other parts of the application and displays the welcome message.
 */
public class HomePage extends BasePage {

    private By welcomeMessage = By.cssSelector("[data-testid='hello']");
    private By logoutButton = By.id("btnLogoutWelcome");
    private By usersLink = By.id("btnUsers");
    private By statisticsLink = By.id("btnStats");
    private By articlesLink = By.id("btnArticles");
    private By profileLink = By.id("btnEdit");
    private By uploadButton = By.id("upload-new");

    /**
     * Constructor for HomePage.
     * @param driver WebDriver instance.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the welcome message text displayed on the page.
     * @return String representing the welcome message.
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    /**
     * Verifies the welcome message text.
     * @param expectedMessage The expected welcome message.
     * @return This HomePage instance.
     */
    public HomePage verifyWelcomeMessage(String expectedMessage) {
        verifyText(welcomeMessage, expectedMessage, "Welcome message is not correct. Expected: '" + expectedMessage + "' at " + welcomeMessage);
        return this;
    }

    /**
     * Navigates to the Users page.
     * @return A new instance of UsersPage.
     */
    public UsersPage goToUsers() {
        verifyElementDisplayed(usersLink, "Users link is not displayed: " + usersLink);
        click(usersLink);
        return new UsersPage(driver);
    }

    /**
     * Navigates to the Statistics page.
     * @return A new instance of StatisticsPage.
     */
    public StatisticsPage goToStatistics() {
        verifyElementDisplayed(statisticsLink, "Statistics link is not displayed: " + statisticsLink);
        click(statisticsLink);
        return new StatisticsPage(driver);
    }

    /**
     * Navigates to the Articles page.
     * @return A new instance of ArticlesPage.
     */
    public ArticlesPage goToArticles() {
        verifyElementDisplayed(articlesLink, "Articles link is not displayed: " + articlesLink);
        click(articlesLink);
        return new ArticlesPage(driver);
    }

    /**
     * Navigates to the User Profile (Users) page.
     * @return A new instance of UsersPage.
     */
    public UsersPage goToProfile() {
        verifyElementDisplayed(profileLink, "Profile link is not displayed: " + profileLink);
        click(profileLink);
        return new UsersPage(driver);
    }

    /**
     * Navigates to the Upload page.
     * @return A new instance of UploadPage.
     */
    public UploadPage goToUpload() {
        verifyElementDisplayed(uploadButton, "Upload button is not displayed: " + uploadButton);
        click(uploadButton);
        return new UploadPage(driver);
    }

    /**
     * Logs out of the application.
     * @return A new instance of LoginPage.
     */
    public LoginPage logout() {
        verifyElementDisplayed(logoutButton, "Logout button is not displayed: " + logoutButton);
        click(logoutButton);
        return new LoginPage(driver);
    }

    /**
     * Verifies that the Home page is displayed by checking for the logout button.
     * @return This HomePage instance.
     */
    public HomePage verifyHomePageIsDisplayed() {
        verifyElementDisplayed(logoutButton, "Home page is not displayed (Logout button not found: " + logoutButton + ")");
        return this;
    }

    /**
     * Verifies that the upload button is visible.
     * @return This HomePage instance.
     */
    public HomePage verifyUploadButtonVisible() {
        verifyElementDisplayed(uploadButton, "Upload button should be visible: " + uploadButton);
        return this;
    }
}
