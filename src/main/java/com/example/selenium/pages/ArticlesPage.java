package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public ArticlesPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public ArticlesPage searchFor(String text) {
        type(searchInput, text);
        click(searchButton);
        return this;
    }

    public ArticlesPage enterTitle(String title) {
        type(titleInput, title);
        return this;
    }

    public ArticlesPage enterBody(String body) {
        type(bodyInput, body);
        return this;
    }

    public ArticlesPage clickSave() {
        click(saveButton);
        return this;
    }

    public ArticlesPage nextPage() {
        click(nextButton);
        return this;
    }

    public ArticlesPage prevPage() {
        click(prevButton);
        return this;
    }

    public ArticlesPage goToPage(int pageNumber) {
        click(By.xpath("//div[@id='pagination']//a[text()='" + pageNumber + "']"));
        return this;
    }

    public ArticlesPage verifyArticlesPageIsDisplayed() {
        verifyElementDisplayed(searchInput, "Articles page is not displayed (Search input not found: " + searchInput + ")");
        return this;
    }

    public ArticlesPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }

    public ArticlesPage verifyPaginationVisible() {
        verifyElementDisplayed(By.id("pagination"), "Pagination is not visible");
        return this;
    }

    public AddArticlePage goToAddArticle() {
        verifyElementDisplayed(addArticleButton, "Add Article button is not displayed: " + addArticleButton);
        click(addArticleButton);
        return new AddArticlePage(driver);
    }
}
