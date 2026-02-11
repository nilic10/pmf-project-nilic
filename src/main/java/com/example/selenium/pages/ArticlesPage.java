package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Articles page.
 * Provides methods for searching, navigating, and creating articles.
 */
public class ArticlesPage extends BasePage {

    private By pageHeader = By.id("menu-main-gui");
    private By searchInput = By.id("search-input");
    private By searchButton = By.cssSelector("[data-testid='search-button']");
    private By titleInput = By.cssSelector("[data-testid='title-input']");
    private By bodyInput = By.cssSelector("[data-testid='body-text']");
    private By saveButton = By.cssSelector("[data-testid='save']");
    private By alertPopup = By.id("alertPopup");
    private By nextButton = By.cssSelector("#pagination .next");
    private By prevButton = By.cssSelector("#pagination .prev");
    private By pageLink = By.className("page-link");
    private By addArticleButton = By.id("btnArticlesAdd");

    /**
     * Constructor for ArticlesPage.
     * @param driver WebDriver instance.
     */
    public ArticlesPage(WebDriver driver) {
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
     * Searches for articles matching the specified text.
     * @param text Text to search for.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage searchFor(String text) {
        type(searchInput, text);
        click(searchButton);
        return this;
    }

    /**
     * Enters the title for a new article in the quick add section (if available).
     * @param title Title of the article.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage enterTitle(String title) {
        type(titleInput, title);
        return this;
    }

    /**
     * Enters the body for a new article in the quick add section (if available).
     * @param body Content of the article.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage enterBody(String body) {
        type(bodyInput, body);
        return this;
    }

    /**
     * Clicks the save button for quick add.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage clickSave() {
        click(saveButton);
        return this;
    }

    /**
     * Navigates to the next page of articles.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage nextPage() {
        click(nextButton);
        return this;
    }

    /**
     * Navigates to the previous page of articles.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage prevPage() {
        click(prevButton);
        return this;
    }

    /**
     * Navigates to a specific page number in the pagination.
     * @param pageNumber The page number to navigate to.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage goToPage(int pageNumber) {
        click(By.xpath("//div[@id='pagination']//a[text()='" + pageNumber + "']"));
        return this;
    }

    /**
     * Verifies that the Articles page is displayed by checking for the search input.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage verifyArticlesPageIsDisplayed() {
        verifyElementDisplayed(searchInput, "Articles page is not displayed (Search input not found: " + searchInput + ")");
        return this;
    }

    /**
     * Verifies the alert message text.
     * @param expectedMessage The expected message.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }

    /**
     * Verifies that the pagination controls are visible.
     * @return This ArticlesPage instance.
     */
    public ArticlesPage verifyPaginationVisible() {
        verifyElementDisplayed(By.id("pagination"), "Pagination is not visible");
        return this;
    }

    /**
     * Navigates to the Add Article page by clicking the add button.
     * @return A new instance of AddArticlePage.
     */
    public AddArticlePage goToAddArticle() {
        verifyElementDisplayed(addArticleButton, "Add Article button is not displayed: " + addArticleButton);
        click(addArticleButton);
        return new AddArticlePage(driver);
    }
}
