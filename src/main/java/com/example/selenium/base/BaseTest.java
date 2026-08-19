package com.example.selenium.base;

import com.example.AppConfig;
import com.example.selenium.utils.BrowserMobProxyServiceCreator;
import com.example.selenium.utils.DriverFactory;
import io.qameta.allure.Allure;
import net.lightbody.bmp.core.har.Har;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Base class for all Selenium tests.
 * Handles WebDriver lifecycle and provides utility methods like screenshot capture on failure.
 */
public class BaseTest {
    protected WebDriver driver;

    protected static final Logger _logger = LogManager
            .getLogger(BaseTest.class);

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
     * Navigates the browser to the application login page.
     */
    public void openApp() {
        driver.get(AppConfig.APP_URL + "/login");
    }

    /**
     * Setup method to initialize WebDriver before each test.
     * Default browser is Chrome, but can be overridden by system property 'browser'.
     */
    @BeforeEach
    public void createDriver() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browser);
    }

    /**
     * Tear down method to quit WebDriver after each test.
     */
    @AfterEach
    public void tearDown() {
        stopBrowserMobProxyService();
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

    public void newHar() {
        if (BrowserMobProxyServiceCreator.getService() != null) {
            BrowserMobProxyServiceCreator.getInstance().newHar();
        }
    }

    /**
     * Captures the HAR file and attaches it to the Allure report.
     */
    public void saveHar(String fileName) {
        if (BrowserMobProxyServiceCreator.getService() != null) {
            Har har = BrowserMobProxyServiceCreator.getInstance().getHar();
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                har.writeTo(bos);
                har.writeTo(new File("target/" + fileName));
                Allure.addAttachment("network-log.har", "application/json", new ByteArrayInputStream(bos.toByteArray()), ".har");
                System.out.println("[DEBUG_LOG] HAR file added to Allure");
            } catch (IOException e) {
                System.err.println("[DEBUG_LOG] Failed to save HAR file: " + e.getMessage());
            }
        }
    }
    public void stopBrowserMobProxyService() {

        if (BrowserMobProxyServiceCreator.getService() != null
                && BrowserMobProxyServiceCreator.getInstance().isStarted()) {
            BrowserMobProxyServiceCreator.getInstance().stopService();
        }
        if (_logger.isDebugEnabled()) {
            _logger.debug("BrowserMobProxy service is stopped!");
        }
    }
}
