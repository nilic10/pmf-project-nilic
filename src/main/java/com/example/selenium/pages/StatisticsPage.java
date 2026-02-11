package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatisticsPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By btnUserStats = By.id("btnUserStats");
    private By btnArticleStats = By.id("btnArticleStats");

    public StatisticsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public StatisticsPage verifyStatisticsPageIsDisplayed() {
        verifyElementDisplayed(btnUserStats, "Statistics page is not displayed (btnUserStats not found: " + btnUserStats + ")");
        return this;
    }
}
