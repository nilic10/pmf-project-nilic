package com.example.selenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

/**
 * Base class for all Page Objects.
 * Provides common functionality and assertion wrappers for web pages.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor for BasePage.
     * @param driver WebDriver instance to be used by the page object.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Clicks on the element identified by the locator.
     * Waits for the element to be clickable before clicking.
     * @param locator By locator of the element.
     */
    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    /**
     * Types text into the element identified by the locator.
     * Waits for the element to be visible and clears it if possible before typing.
     * @param locator By locator of the element.
     * @param text Text to type into the element.
     */
    protected void type(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        try {
            element.clear();
        } catch (Exception e) {
            // Some elements might not support clear or have issues in specific browser versions
        }
        element.sendKeys(text);
    }

    /**
     * Gets the text content of the element identified by the locator.
     * Waits for the element to be visible before retrieving text.
     * @param locator By locator of the element.
     * @return String representing the text content of the element.
     */
    protected String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    /**
     * Checks if the element identified by the locator is displayed.
     * @param locator By locator of the element.
     * @return true if the element is displayed, false otherwise.
     */
    protected boolean isDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Waits for the element identified by the locator to be visible.
     * @param locator By locator of the element.
     * @return WebElement once it is visible.
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for the element identified by the locator to be clickable.
     * @param locator By locator of the element.
     * @return WebElement once it is clickable.
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Navigates to the specified URL.
     * @param url URL to navigate to.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Assertion wrappers

    /**
     * Verifies that the text of the element identified by the locator matches the expected text.
     * @param locator By locator of the element.
     * @param expectedText Expected text content.
     */
    public void verifyText(By locator, String expectedText) {
        verifyText(locator, expectedText, "Text for element " + locator + " should be " + expectedText);
    }

    /**
     * Verifies that the text of the element identified by the locator matches the expected text.
     * @param locator By locator of the element.
     * @param expectedText Expected text content.
     * @param errorMessage Custom error message if the assertion fails.
     */
    public void verifyText(By locator, String expectedText, String errorMessage) {
        Assertions.assertEquals(expectedText, getText(locator), errorMessage);
    }

    /**
     * Verifies that the element identified by the locator is displayed.
     * @param locator By locator of the element.
     */
    public void verifyElementDisplayed(By locator) {
         verifyElementDisplayed(locator, "Element " + locator + " should be displayed");
    }

    /**
     * Verifies that the element identified by the locator is displayed.
     * @param locator By locator of the element.
     * @param errorMessage Custom error message if the assertion fails.
     */
    public void verifyElementDisplayed(By locator, String errorMessage) {
        Assertions.assertTrue(isDisplayed(locator), errorMessage);
    }

    /**
     * Verifies that the page title matches the expected title.
     * @param expectedTitle Expected page title.
     */
    public void verifyTitle(String expectedTitle) {
         verifyTitle(expectedTitle, "Title should be " + expectedTitle);
    }

    /**
     * Verifies that the page title matches the expected title.
     * @param expectedTitle Expected page title.
     * @param errorMessage Custom error message if the assertion fails.
     */
    public void verifyTitle(String expectedTitle, String errorMessage) {
        Assertions.assertEquals(expectedTitle, driver.getTitle(), errorMessage);
    }
}
