package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Statistics page.
 * Displays various statistics related to users and articles.
 */
public class StatisticsPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By btnUserStats = By.id("btnUserStats");
    private By btnArticleStats = By.id("btnArticleStats");

    /**
     * Constructor for StatisticsPage.
     * @param driver WebDriver instance.
     */
    public StatisticsPage(WebDriver driver) {
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
     * Verifies that the Statistics page is displayed by checking for the user statistics button.
     * @return This StatisticsPage instance.
     */
    public StatisticsPage verifyStatisticsPageIsDisplayed() {
        verifyElementDisplayed(btnUserStats, "Statistics page is not displayed (btnUserStats not found: " + btnUserStats + ")");
        return this;
    }
}
