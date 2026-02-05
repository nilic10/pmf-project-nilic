package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommentsPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By searchInput = By.id("search-input");
    private By searchButton = By.cssSelector("[data-testid='search-button']");
    private By alertPopup = By.id("alertPopup");
    private By nextButton = By.cssSelector("#pagination .next");
    private By prevButton = By.cssSelector("#pagination .prev");

    public CommentsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public CommentsPage searchFor(String text) {
        type(searchInput, text);
        click(searchButton);
        return this;
    }

    public CommentsPage nextPage() {
        click(nextButton);
        return this;
    }

    public CommentsPage prevPage() {
        click(prevButton);
        return this;
    }

    public CommentsPage goToPage(int pageNumber) {
        click(By.xpath("//div[@id='pagination']//a[text()='" + pageNumber + "']"));
        return this;
    }

    public CommentsPage verifyCommentsPageIsDisplayed() {
        verifyTitle("🦎 GAD | Comments");
        verifyElementDisplayed(searchInput);
        return this;
    }

    public CommentsPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage);
        return this;
    }

    public CommentsPage verifyPaginationVisible() {
        verifyElementDisplayed(By.id("pagination"));
        return this;
    }
}
