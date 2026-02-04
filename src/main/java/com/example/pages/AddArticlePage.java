package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddArticlePage extends BasePage {

    private By titleField = By.cssSelector("[data-testid='title-input']");
    private By bodyField = By.cssSelector("[data-testid='body-text']");
    private By saveButton = By.cssSelector("[data-testid='save']");
    private By alertPopup = By.id("alertPopup");

    public AddArticlePage(WebDriver driver) {
        super(driver);
    }

    public AddArticlePage enterTitle(String title) {
        type(titleField, title);
        return this;
    }

    public AddArticlePage enterContent(String content) {
        type(bodyField, content);
        return this;
    }

    public AddArticlePage clickSave() {
        click(saveButton);
        return this;
    }

    public AddArticlePage verifyAddArticlePageIsDisplayed() {
        verifyElementDisplayed(titleField);
        return this;
    }

    public AddArticlePage verifySuccessMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage);
        return this;
    }
}
