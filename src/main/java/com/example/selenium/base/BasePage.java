package com.example.selenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        try {
            element.clear();
        } catch (Exception e) {
            // Some elements might not support clear or have issues in specific browser versions
        }
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    // Assertion wrappers
    public BasePage verifyText(By locator, String expectedText) {
        Assertions.assertEquals(expectedText, getText(locator));
        return this;
    }

    public BasePage verifyElementDisplayed(By locator) {
        Assertions.assertTrue(isDisplayed(locator), "Element " + locator + " should be displayed");
        return this;
    }

    public BasePage verifyTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
        return this;
    }
}
