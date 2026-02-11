package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Add Article page.
 * Provides methods for entering article details and verifying the page state.
 */
public class AddArticlePage extends BasePage {

    private By titleField = By.cssSelector("[data-testid='title-input']");
    private By bodyField = By.cssSelector("[data-testid='body-text']");
    private By saveButton = By.cssSelector("[data-testid='save']");
    private By alertPopup = By.id("alertPopup");

    /**
     * Constructor for AddArticlePage.
     * @param driver WebDriver instance.
     */
    public AddArticlePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the article title.
     * @param title Title of the article.
     * @return This AddArticlePage instance.
     */
    public AddArticlePage enterTitle(String title) {
        type(titleField, title);
        return this;
    }

    /**
     * Enters the article content.
     * @param content Body content of the article.
     * @return This AddArticlePage instance.
     */
    public AddArticlePage enterContent(String content) {
        type(bodyField, content);
        return this;
    }

    /**
     * Clicks the save button to create the article.
     * @return This AddArticlePage instance.
     */
    public AddArticlePage clickSave() {
        click(saveButton);
        return this;
    }

    /**
     * Verifies that the Add Article page is displayed by checking for the title field.
     * @return This AddArticlePage instance.
     */
    public AddArticlePage verifyAddArticlePageIsDisplayed() {
        verifyElementDisplayed(titleField, "Add Article page is not displayed (Title field not found: " + titleField + ")");
        return this;
    }

    /**
     * Verifies the success message after saving an article.
     * @param expectedMessage The expected success message text.
     * @return This AddArticlePage instance.
     */
    public AddArticlePage verifySuccessMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Success message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }
}
