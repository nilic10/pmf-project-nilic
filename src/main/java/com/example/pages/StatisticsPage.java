package com.example.pages;

import com.example.base.BasePage;
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
        verifyElementDisplayed(btnUserStats);
        return this;
    }
}
