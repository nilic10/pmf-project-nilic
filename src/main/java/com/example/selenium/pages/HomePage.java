package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By welcomeMessage = By.cssSelector("[data-testid='hello']");
    private By logoutButton = By.id("btnLogoutWelcome");
    private By usersLink = By.id("btnUsers");
    private By statisticsLink = By.id("btnStats");
    private By articlesLink = By.id("btnArticles");
    private By profileLink = By.id("btnEdit");
    private By uploadButton = By.id("upload-new");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public HomePage verifyWelcomeMessage(String expectedMessage) {
        verifyText(welcomeMessage, expectedMessage, "Welcome message is not correct. Expected: '" + expectedMessage + "' at " + welcomeMessage);
        return this;
    }

    public UsersPage goToUsers() {
        verifyElementDisplayed(usersLink, "Users link is not displayed: " + usersLink);
        click(usersLink);
        return new UsersPage(driver);
    }

    public StatisticsPage goToStatistics() {
        verifyElementDisplayed(statisticsLink, "Statistics link is not displayed: " + statisticsLink);
        click(statisticsLink);
        return new StatisticsPage(driver);
    }

    public ArticlesPage goToArticles() {
        verifyElementDisplayed(articlesLink, "Articles link is not displayed: " + articlesLink);
        click(articlesLink);
        return new ArticlesPage(driver);
    }

    public UsersPage goToProfile() {
        verifyElementDisplayed(profileLink, "Profile link is not displayed: " + profileLink);
        click(profileLink);
        return new UsersPage(driver);
    }

    public UploadPage goToUpload() {
        verifyElementDisplayed(uploadButton, "Upload button is not displayed: " + uploadButton);
        click(uploadButton);
        return new UploadPage(driver);
    }

    public LoginPage logout() {
        verifyElementDisplayed(logoutButton, "Logout button is not displayed: " + logoutButton);
        click(logoutButton);
        return new LoginPage(driver);
    }

    public HomePage verifyHomePageIsDisplayed() {
        verifyElementDisplayed(logoutButton, "Home page is not displayed (Logout button not found: " + logoutButton + ")");
        return this;
    }

    public HomePage verifyUploadButtonVisible() {
        verifyElementDisplayed(uploadButton, "Upload button should be visible: " + uploadButton);
        return this;
    }
}
