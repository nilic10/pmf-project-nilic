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

public class BaseTest {
    protected WebDriver driver;

    @RegisterExtension
    AfterTestExecutionCallback screenshotCallback = context -> {
        if (context.getExecutionException().isPresent()) {
            System.out.println("[DEBUG_LOG] Test failed, taking screenshot before driver quit...");
            if (driver != null) {
                saveScreenshot(context.getDisplayName());
            }
        }
    };

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browser);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void saveScreenshot(String name) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
            System.out.println("[DEBUG_LOG] Screenshot added to Allure: " + name);
        }
    }
}
