package com.example.pages;

import com.example.base.BasePage;
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
        verifyText(welcomeMessage, expectedMessage);
        return this;
    }

    public UsersPage goToUsers() {
        click(usersLink);
        return new UsersPage(driver);
    }

    public StatisticsPage goToStatistics() {
        click(statisticsLink);
        return new StatisticsPage(driver);
    }

    public ArticlesPage goToArticles() {
        click(articlesLink);
        return new ArticlesPage(driver);
    }

    public UsersPage goToProfile() {
        click(profileLink);
        return new UsersPage(driver);
    }

    public UploadPage goToUpload() {
        verifyElementDisplayed(uploadButton);
        click(uploadButton);
        return new UploadPage(driver);
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage(driver);
    }

    public HomePage verifyHomePageIsDisplayed() {
        verifyElementDisplayed(logoutButton);
        return this;
    }

    public HomePage verifyUploadButtonVisible() {
        verifyElementDisplayed(uploadButton);
        return this;
    }
}
