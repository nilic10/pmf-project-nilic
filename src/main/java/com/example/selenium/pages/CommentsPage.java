package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Comments page.
 * Provides methods for searching and navigating through comments.
 */
public class CommentsPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By searchInput = By.id("search-input");
    private By searchButton = By.cssSelector("[data-testid='search-button']");
    private By alertPopup = By.id("alertPopup");
    private By nextButton = By.cssSelector("#pagination .next");
    private By prevButton = By.cssSelector("#pagination .prev");

    /**
     * Constructor for CommentsPage.
     * @param driver WebDriver instance.
     */
    public CommentsPage(WebDriver driver) {
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
     * Searches for comments matching the specified text.
     * @param text Text to search for.
     * @return This CommentsPage instance.
     */
    public CommentsPage searchFor(String text) {
        type(searchInput, text);
        click(searchButton);
        return this;
    }

    /**
     * Navigates to the next page of comments.
     * @return This CommentsPage instance.
     */
    public CommentsPage nextPage() {
        click(nextButton);
        return this;
    }

    /**
     * Navigates to the previous page of comments.
     * @return This CommentsPage instance.
     */
    public CommentsPage prevPage() {
        click(prevButton);
        return this;
    }

    /**
     * Navigates to a specific page number in the pagination.
     * @param pageNumber The page number to navigate to.
     * @return This CommentsPage instance.
     */
    public CommentsPage goToPage(int pageNumber) {
        click(By.xpath("//div[@id='pagination']//a[text()='" + pageNumber + "']"));
        return this;
    }

    /**
     * Verifies that the Comments page is displayed by checking title and search input.
     * @return This CommentsPage instance.
     */
    public CommentsPage verifyCommentsPageIsDisplayed() {
        verifyTitle("🦎 GAD | Comments");
        verifyElementDisplayed(searchInput, "Comments page search input not found: " + searchInput);
        return this;
    }

    /**
     * Verifies the alert message text.
     * @param expectedMessage The expected message.
     * @return This CommentsPage instance.
     */
    public CommentsPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }

    /**
     * Verifies that the pagination controls are visible.
     * @return This CommentsPage instance.
     */
    public CommentsPage verifyPaginationVisible() {
        verifyElementDisplayed(By.id("pagination"), "Pagination is not visible on Comments page");
        return this;
    }
}
