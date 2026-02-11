package com.example.selenium.base;

import com.example.selenium.utils.DriverFactory;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Base class for all Selenium tests.
 * Handles WebDriver lifecycle and provides utility methods like screenshot capture on failure.
 */
public class BaseTest {
    protected WebDriver driver;

    /**
     * Extension that takes a screenshot if a test fails.
     */
    @RegisterExtension
    AfterTestExecutionCallback screenshotCallback = context -> {
        if (context.getExecutionException().isPresent()) {
            System.out.println("[DEBUG_LOG] Test failed, taking screenshot before driver quit...");
            if (driver != null) {
                saveScreenshot(context.getDisplayName());
            }
        }
    };

    /**
     * Setup method to initialize WebDriver before each test.
     * Default browser is Chrome, but can be overridden by system property 'browser'.
     */
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browser);
    }

    /**
     * Tear down method to quit WebDriver after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Captures a screenshot and attaches it to the Allure report.
     * @param name Name of the attachment in the report.
     */
    public void saveScreenshot(String name) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
            System.out.println("[DEBUG_LOG] Screenshot added to Allure: " + name);
        }
    }
}
